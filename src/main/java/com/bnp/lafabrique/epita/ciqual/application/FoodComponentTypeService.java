package com.bnp.lafabrique.epita.ciqual.application;

import com.bnp.lafabrique.epita.ciqual.domaine.FoodComponentType;
import com.bnp.lafabrique.epita.ciqual.dto.FoodComponentTypeDto;

public interface FoodComponentTypeService {
    /**
     * will create the component type only if no other component type of same name exist in DB.
     * @param foodComponentTypeDto
     * @return id of the created Component type or id of the existing one
     */
    FoodComponentType create(FoodComponentTypeDto foodComponentTypeDto);

    /**
     * return the FoodComponentType having that name
     * @param name
     * @return
     */
    FoodComponentType get(String name);

    FoodComponentTypeDto convertComponentTypeToComponentTypeDto(FoodComponentType foodComponentType);
}
