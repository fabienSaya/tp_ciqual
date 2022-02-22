package com.bnp.lafabrique.epita.ciqual.application;

import com.bnp.lafabrique.epita.ciqual.dao.FoodDao;
import com.bnp.lafabrique.epita.ciqual.dao.DaoFactory;
import com.bnp.lafabrique.epita.ciqual.domaine.*;
import com.bnp.lafabrique.epita.ciqual.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Override
    public Long create(FoodDto foodDto) {
        FoodDao foodDao= DaoFactory.getProduitDao();
        Food food= convertFoodDtoToFood(foodDto);
        return foodDao.create(food);
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



        //return new FoodComponent(foodComponentDto.getName(),unit,foodComponentDto.getQuantity(),foodComponentDto.getComparator().getLabel());

//note pour moi: les componant type sont une table de référence. Je devrais les charger au début de l'application.
        //return new FoodComponent(new ComponentType() foodComponentDto.getName(),foodComponentDto.getQuantity(),foodComponentDto.getComparator().getLabel());
        return null;
    }









}
