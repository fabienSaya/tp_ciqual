package com.bnp.lafabrique.epita.ciqual.cache;

import com.bnp.lafabrique.epita.ciqual.domaine.FoodScientificName;

import java.util.HashMap;

public class CacheScientificNames {

    //faudrait probablement utiliser un concurrentHashMap pour une appli web
    private static HashMap<String, FoodScientificName> cacheScientificName = new HashMap<>();

    public static FoodScientificName get(FoodScientificName foodScientificName) {
        return cacheScientificName.get(foodScientificName.getName());

    }

    public static void add(FoodScientificName foodScientificName) {
        cacheScientificName.put(foodScientificName.getName(), foodScientificName);
    }


}
