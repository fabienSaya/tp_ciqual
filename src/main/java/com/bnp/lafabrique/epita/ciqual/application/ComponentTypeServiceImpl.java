package com.bnp.lafabrique.epita.ciqual.application;

import com.bnp.lafabrique.epita.ciqual.dao.FoodComponentTypeDao;
import com.bnp.lafabrique.epita.ciqual.dao.DaoFactory;
import com.bnp.lafabrique.epita.ciqual.domaine.FoodComponentType;
import com.bnp.lafabrique.epita.ciqual.dto.FoodComponentTypeDto;

public class ComponentTypeServiceImpl implements ComponentTypeService{

    /**
     * will create the component type only if no other component type of same name exist in DB.
     * @param alimentComponentTypeDto
     * @return id of the created Component type or id of the existing one
     */
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
