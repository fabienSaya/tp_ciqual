package com.bnp.lafabrique.epita.ciqual.application;

import com.bnp.lafabrique.epita.ciqual.dao.FoodComponentTypeDao;
import com.bnp.lafabrique.epita.ciqual.dao.DaoFactory;
import com.bnp.lafabrique.epita.ciqual.domaine.FoodComponentType;
import com.bnp.lafabrique.epita.ciqual.dto.FoodComponentTypeDto;
import org.springframework.stereotype.Service;

@Service
public class ComponentTypeServiceImpl implements ComponentTypeService{


    @Override
    public Long create(FoodComponentTypeDto alimentComponentTypeDto) {
        FoodComponentTypeDao componentTypeDao= DaoFactory.getComponentTypeDao();
        FoodComponentType componentType=convertComponentTypeDtoToComponentType(alimentComponentTypeDto);
        return componentTypeDao.create(componentType);
    }

    private FoodComponentType convertComponentTypeDtoToComponentType(FoodComponentTypeDto alimentComponentTypeDto){
        return new FoodComponentType(alimentComponentTypeDto.getName(), alimentComponentTypeDto.getLabel());
    }
}
