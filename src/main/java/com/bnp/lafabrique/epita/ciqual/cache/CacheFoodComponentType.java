package com.bnp.lafabrique.epita.ciqual.cache;

import com.bnp.lafabrique.epita.ciqual.dao.DaoFactory;
import com.bnp.lafabrique.epita.ciqual.dao.FoodComponentTypeDao;
import com.bnp.lafabrique.epita.ciqual.domaine.FoodComponentType;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CacheFoodComponentType {
    //faudrait probablement utiliser un concurrentHashMap pour une appli web
    private static HashMap<String, FoodComponentType> cacheComponentType = new HashMap<>();


    public static FoodComponentType get(String name) {
        return cacheComponentType.get(name);
    }


    public static void add(FoodComponentType foodComponentType) {
        cacheComponentType.put(foodComponentType.getName(), foodComponentType);
    }

    public static void initCacheFromBdd() {
        FoodComponentTypeDao foodComponentTypeDao= DaoFactory.getFoodComponentTypeDao();
        List<FoodComponentType> foodComponentTypeList =foodComponentTypeDao.getAllComponentTypes();
        foodComponentTypeList.forEach(CacheFoodComponentType::add);
    }

    public static Collection<FoodComponentType> getAllComponentTypes() {
        return cacheComponentType.values();
    }

}
