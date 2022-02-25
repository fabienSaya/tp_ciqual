package com.bnp.lafabrique.epita.ciqual.application;

import com.bnp.lafabrique.epita.ciqual.cache.CacheFoodComponentType;
import com.bnp.lafabrique.epita.ciqual.dao.FoodComponentTypeDao;
import com.bnp.lafabrique.epita.ciqual.dao.DaoFactory;
import com.bnp.lafabrique.epita.ciqual.domaine.FoodComponentType;
import com.bnp.lafabrique.epita.ciqual.dto.FoodComponentTypeDto;
import org.springframework.stereotype.Service;

@Service
public class FoodComponentTypeServiceImpl implements FoodComponentTypeService {


    @Override
    public FoodComponentType create(FoodComponentTypeDto foodComponentTypeDto) {
        FoodComponentTypeDao componentTypeDao= DaoFactory.getFoodComponentTypeDao();

        //we create the food component type only if not in the cache (which would mean it already exist in DB)
        FoodComponentType foodComponentType = CacheFoodComponentType.get(foodComponentTypeDto.getName());
        if (foodComponentType==null) {
            //the component type is not in the cache so it does not exist in the cache
            //we create it and add it to the cache
            foodComponentType=convertComponentTypeDtoToComponentType(foodComponentTypeDto);
            foodComponentType=componentTypeDao.create(foodComponentType);
            CacheFoodComponentType.add(foodComponentType);
        }

        return foodComponentType;
    }


    private FoodComponentType convertComponentTypeDtoToComponentType(FoodComponentTypeDto foodComponentTypeDto){
        return new FoodComponentType(foodComponentTypeDto.getName(), foodComponentTypeDto.getLabel(), foodComponentTypeDto.getExcelColumn());
    }

    @Override
    public FoodComponentTypeDto convertComponentTypeToComponentTypeDto(FoodComponentType foodComponentType) {
        return new FoodComponentTypeDto(foodComponentType.getName(), foodComponentType.getLabel(), foodComponentType.getExcelColumn());
    }

    @Override
    public FoodComponentType get(String name) {
        //this impose the cache to be always up to date
        return CacheFoodComponentType.get(name);
    }
}
