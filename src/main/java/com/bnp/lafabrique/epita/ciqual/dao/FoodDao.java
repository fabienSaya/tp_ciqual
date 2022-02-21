package com.bnp.lafabrique.epita.ciqual.dao;

import com.bnp.lafabrique.epita.ciqual.domaine.Food;

public interface FoodDao {
    /**
     * create an aliment in the DB
     * @param aliment
     * @return id of the created aliment
     */
    Long create(Food aliment);
}
