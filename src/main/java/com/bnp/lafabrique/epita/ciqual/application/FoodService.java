package com.bnp.lafabrique.epita.ciqual.application;

import com.bnp.lafabrique.epita.ciqual.dto.FoodDto;
import com.bnp.lafabrique.epita.ciqual.dto.FoodLightInfoDto;
import com.bnp.lafabrique.epita.ciqual.exception.GroupDefinitionException;

import java.util.List;

public interface FoodService {
    /**
     * Create the food only if it does not already exist in BDD. (a BDD check is done)
     *
     * (note: peut etre gérer des exception plutot que -1...)
     *
     * @param alimentDto
     * @return the id of the food if creation succeeded. -1 if there was an error, 0 if already in bdd
     */
    Long create(FoodDto alimentDto) ;

    FoodDto getFoodByCode(String code) throws GroupDefinitionException;

    List<FoodDto> getFoodByName(String name) throws GroupDefinitionException;

    List<FoodLightInfoDto> getAllFoodLightInfo(boolean forceReloadFromDB);

}
