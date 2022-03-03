package com.bnp.lafabrique.epita.ciqual.dao;

import com.bnp.lafabrique.epita.ciqual.domaine.Food;

import java.util.List;

public interface FoodDao {


    /**
     * Look if a food exist in DB
     * @param foodCode
     * @return 0 if food does not exist, or the id of the food if it does exist
     */
    Long foodExist(String foodCode);

    /**
     * create an aliment in the DB
     * @param aliment
     * @return id of the created aliment
     */
    Food create(Food aliment);

    Food findFoodByCode(String foodCode);

    List<Food> findFoodByName(String name);

    List<Food> getAllFood();
}
