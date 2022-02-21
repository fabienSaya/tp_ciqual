package com.bnp.lafabrique.epita.ciqual.dao;

import com.bnp.lafabrique.epita.ciqual.domaine.FoodComponentType;

import java.util.List;

public interface FoodComponentTypeDao {

    long create(FoodComponentType componentType);

    List <FoodComponentType> getAllComponentTypes();

    List<FoodComponentType> findComponentTypeByName(String name);


}
