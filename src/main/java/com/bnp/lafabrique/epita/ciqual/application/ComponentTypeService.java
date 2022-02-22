package com.bnp.lafabrique.epita.ciqual.application;

import com.bnp.lafabrique.epita.ciqual.dto.FoodComponentTypeDto;

public interface ComponentTypeService {
    /**
     * will create the component type only if no other component type of same name exist in DB.
     * @param alimentComponentTypeDto
     * @return id of the created Component type or id of the existing one
     */
    Long create(FoodComponentTypeDto alimentComponentTypeDto);

}
