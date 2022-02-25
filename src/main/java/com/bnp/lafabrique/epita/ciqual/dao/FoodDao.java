package com.bnp.lafabrique.epita.ciqual.dao;

import com.bnp.lafabrique.epita.ciqual.domaine.Food;

import java.util.List;

public interface FoodDao {
    /**
     * create an aliment in the DB
     * @param aliment
     * @return id of the created aliment
     */
    Food create(Food aliment);

    Food findFoodByCode(String foodCode);

    List<Food> findFoodByName(String name);
}
