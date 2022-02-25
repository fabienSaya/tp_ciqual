package com.bnp.lafabrique.epita.ciqual.dao;

import com.bnp.lafabrique.epita.ciqual.domaine.FoodComponentType;

import java.util.List;

public interface FoodComponentTypeDao {
    /**
     *
     * @param componentType the object to create
     * @return the object with is DB id
     */
    FoodComponentType create(FoodComponentType componentType);

    List <FoodComponentType> getAllComponentTypes();

    FoodComponentType findComponentTypeByName(String name);


}
