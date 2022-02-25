package com.bnp.lafabrique.epita.ciqual.application;

import com.bnp.lafabrique.epita.ciqual.cache.CacheFoodComponentType;
import com.bnp.lafabrique.epita.ciqual.cache.CacheFoodGroups;
import com.bnp.lafabrique.epita.ciqual.cache.CacheScientificNames;
import com.bnp.lafabrique.epita.ciqual.dao.FoodDao;
import com.bnp.lafabrique.epita.ciqual.dao.DaoFactory;
import com.bnp.lafabrique.epita.ciqual.domaine.*;
import com.bnp.lafabrique.epita.ciqual.dto.*;
import com.bnp.lafabrique.epita.ciqual.exception.GroupDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            foodComponentList= foodDto.getComponentList().stream().map(this::convertFoodComponentDtoToFoodComponent).collect(Collectors.toList());

        return new Food(foodDto.getCode(), foodDto.getName(), foodScientificName, foodSubSubGroup,foodComponentList);

    }

    private FoodComponent convertFoodComponentDtoToFoodComponent(FoodComponentDto foodComponentDto){
        FoodComponentType foodComponentType= CacheFoodComponentType.get(foodComponentDto.getComponentType().getName());

        String comparator = null;
        if(foodComponentDto.getComparator()!=null) comparator=foodComponentDto.getComparator().getLabel();

        return new FoodComponent(foodComponentType,foodComponentDto.getQuantity(),comparator);
    }

}
