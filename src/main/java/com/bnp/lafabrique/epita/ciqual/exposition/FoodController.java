package com.bnp.lafabrique.epita.ciqual.exposition;

import com.bnp.lafabrique.epita.ciqual.application.FoodService;
import com.bnp.lafabrique.epita.ciqual.dto.CsvFileDefinitionDto;
import com.bnp.lafabrique.epita.ciqual.dto.FoodDto;
import com.bnp.lafabrique.epita.ciqual.exception.GroupDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ciqual")
public class FoodController {

    @Autowired
    FoodService foodService;

    @GetMapping(value = "/foodByCode/{code}")
    public FoodDto getFoodByCode(@PathVariable("code") String foodCode) throws GroupDefinitionException {
        return foodService.getFoodByCode(foodCode);
    }

    @GetMapping(value = "/foodByName/{name}")
    public List<FoodDto> getFoodByName(@PathVariable("name") String foodName) throws GroupDefinitionException {
        return foodService.getFoodByName(foodName);
    }

}
