package com.bnp.lafabrique.epita.ciqual.cache;

import com.bnp.lafabrique.epita.ciqual.dto.FoodLightInfoDto;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The aim is to avoid reloading all food list from DB
 * We will only get data from DB when we want the details
 *
 * we might need a method to reload the cache from DB on demand
 *
 *
 */
public class CacheFoodLightInfoDto {

    //faudrait probablement utiliser un concurrentHashMap pour une appli web
    private static HashMap<Long, FoodLightInfoDto> cache = new HashMap<>();

    public static FoodLightInfoDto get(Long id) {
        return cache.get(id);
    }

    public static void add(FoodLightInfoDto foodLightInfoDto) {
        cache.put(foodLightInfoDto.getId(), foodLightInfoDto);
    }

    public static boolean isEmpty(){
        return cache.isEmpty();
    }

    public static List<FoodLightInfoDto> getAllValues(){
        return cache.values().stream().collect(Collectors.toList());
    }


}
