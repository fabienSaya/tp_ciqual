package com.bnp.lafabrique.epita.ciqual.application;

import com.bnp.lafabrique.epita.ciqual.cache.CacheFoodComponentType;
import com.bnp.lafabrique.epita.ciqual.cache.CacheFoodGroups;
import com.bnp.lafabrique.epita.ciqual.cache.CacheScientificNames;
import com.bnp.lafabrique.epita.ciqual.dao.FoodDao;
import com.bnp.lafabrique.epita.ciqual.dao.DaoFactory;
import com.bnp.lafabrique.epita.ciqual.domaine.*;
import com.bnp.lafabrique.epita.ciqual.dto.*;
import com.bnp.lafabrique.epita.ciqual.dto.enumerate.EnumComparator;
import com.bnp.lafabrique.epita.ciqual.exception.GroupDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodComponentTypeService foodComponentTypeService;

    @Override
    public Long create(FoodDto foodDto)  {
        FoodDao foodDao= DaoFactory.getFoodDao();
        Food food= convertFoodDtoToFood(foodDto);

        if (foodDao.findFoodByCode(food.getCode())!=null)
            //the food already exist in BDD
            return Long.valueOf(0);

        //we check if the food belong to already existing groups and update its ids according to what exist
        boolean isSubSubGrpAlreadyInCache= CacheFoodGroups.updateGroupsIds(food.getSubSubGroup());
        //if we find the subsubgroup it means ones already exist in DB so we reuse its ids
        //if (foodSubSubGroup!=null) food.setSubSubGroup(foodSubSubGroup);

        //we check if scientific name already in cache or not
        FoodScientificName foodScientificName= CacheScientificNames.get(food.getScientificName());
        if(foodScientificName!=null)
            food.setScientificName(foodScientificName);

        //create the food in DB
        food = foodDao.create(food);

        //if we didn't find the subsubgroup in the cache before adding it to the DB, then we add it with its ids after creation in DB
        if (!isSubSubGrpAlreadyInCache) {
            //Code à revoir, pas top cette gestion d'exception. Ca devrait pas arriver en plus à cet endroit.
            try {
                CacheFoodGroups.add(food.getSubSubGroup());
            } catch (GroupDefinitionException e) {
                e.printStackTrace();
                //we fail to update the cache
            }
        }
        if (foodScientificName==null)
            CacheScientificNames.add(food.getScientificName());
        return food.getId();
    }

    @Override
    public FoodDto getFoodByCode(String code) throws GroupDefinitionException {
        FoodDao foodDao= DaoFactory.getFoodDao();
        Food food= foodDao.findFoodByCode(code);
        return convertFoodToDto(food);
    }

    @Override
    public List<FoodDto> getFoodByName(String name) throws GroupDefinitionException {
        FoodDao foodDao= DaoFactory.getFoodDao();
        List<Food> foodList= foodDao.findFoodByName(name);
        if (foodList==null) return null;

        ArrayList<FoodDto> resultList=new ArrayList<>();
        //because of the possible exception, I can't use stream to proceed with conversion
        for (Food food:foodList) {
            resultList.add(convertFoodToDto(food));
        }
        return resultList;
    }

    private Food convertFoodDtoToFood(FoodDto foodDto){
        FoodScientificName foodScientificName =new FoodScientificName(foodDto.getScientificName().getName());

        //creation des groupes

        //il faudra ajouter des controles sur la nullité des données
        FoodSubSubGroupDto subSubGroupDto=foodDto.getSubSubGroup();
        FoodSubGroupDto subGroupDto= subSubGroupDto.getSubGroup();
        FoodGroupDto groupDto = subGroupDto.getGroup();


        FoodGroup foodGroup = new FoodGroup(groupDto.getCode(),groupDto.getNameFR());
        FoodSubGroup foodSubGroup = new FoodSubGroup(foodGroup, subGroupDto.getCode(), subGroupDto.getNameFR());
        FoodSubSubGroup foodSubSubGroup = new FoodSubSubGroup(foodSubGroup,subSubGroupDto.getCode(), subSubGroupDto.getNameFR());

        List<FoodComponent> foodComponentList=null;
        if (foodDto.getComponentList()!=null)
            foodComponentList= foodDto.getComponentList().stream().map(this::convertDtoToFoodComponent).collect(Collectors.toList());

        return new Food(foodDto.getCode(), foodDto.getName(), foodScientificName, foodSubSubGroup,foodComponentList);

    }

    private FoodScientificNameDto convertFoodScientificNameToDto(FoodScientificName foodScientificName){
        if (foodScientificName==null) return null;
        return new FoodScientificNameDto(foodScientificName.getId(),foodScientificName.getName());
    }

    private FoodSubSubGroupDto convertFoodSubSubGroupsToDto(FoodSubSubGroup foodSubSubGroup) throws GroupDefinitionException{
        //we get all the group levels
        if (foodSubSubGroup==null) return null;
        FoodSubGroup foodSubGroup=foodSubSubGroup.getSubGroup();
        if (foodSubGroup==null) throw new GroupDefinitionException("We can't have a subsubgroup (id="+foodSubSubGroup.getId()+") without a subgroup");
        FoodGroup foodGroup = foodSubGroup.getGroup();
        if (foodGroup==null) throw new GroupDefinitionException("We can't have a subgroup (id="+foodSubGroup.getId()+") without a group");

        //now we start conversion starting from the higher level
        FoodGroupDto foodGroupDto = new FoodGroupDto(foodGroup.getCode(), foodGroup.getNameFR(), foodGroup.getId());
        FoodSubGroupDto foodSubGroupDto = new FoodSubGroupDto(foodGroupDto, foodSubGroup.getCode(), foodSubGroup.getNameFR(),foodSubGroup.getId());
        FoodSubSubGroupDto foodSubSubGroupDto= new FoodSubSubGroupDto(foodSubGroupDto,foodSubSubGroup.getCode(), foodSubSubGroup.getNameFR());

        return foodSubSubGroupDto;
    }


    private FoodDto convertFoodToDto(Food food) throws GroupDefinitionException{
        FoodScientificNameDto foodScientificNameDto = convertFoodScientificNameToDto(food.getScientificName());

        //creation of groups
        FoodSubSubGroupDto foodSubSubGroupDto = convertFoodSubSubGroupsToDto(food.getSubSubGroup());

        //creation of food components
        List<FoodComponentDto> foodComponentDtoList=null;
        if (food.getComponentList()!=null)
            foodComponentDtoList= food.getComponentList().stream().map(this::convertFoodComponentToDto).collect(Collectors.toList());

        return new FoodDto(food.getCode(), food.getName(), foodScientificNameDto, foodSubSubGroupDto,foodComponentDtoList);
    }


    private FoodComponent convertDtoToFoodComponent(FoodComponentDto foodComponentDto){
        FoodComponentType foodComponentType= CacheFoodComponentType.get(foodComponentDto.getComponentType().getName());

        String comparator = null;
        if(foodComponentDto.getComparator()!=null) comparator=foodComponentDto.getComparator().getLabel();

        return new FoodComponent(foodComponentType,foodComponentDto.getQuantity(),comparator);
    }


    private FoodComponentDto convertFoodComponentToDto(FoodComponent foodComponent){
        FoodComponentType foodComponentType= foodComponent.getComponentType();
        FoodComponentTypeDto foodComponentTypeDto=new FoodComponentTypeDto(foodComponentType.getName(), foodComponentType.getLabel(),foodComponentType.getExcelColumn(),foodComponentType.getId());

        EnumComparator comparator=EnumComparator.getEnumFromlabel(foodComponent.getComparator());

        return new FoodComponentDto(foodComponentTypeDto, foodComponent.getQuantity(), comparator,foodComponent.getId());
    }

}
