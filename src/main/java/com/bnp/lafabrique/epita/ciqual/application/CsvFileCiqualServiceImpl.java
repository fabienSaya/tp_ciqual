package com.bnp.lafabrique.epita.ciqual.application;

import com.bnp.lafabrique.epita.ciqual.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvFileCiqualServiceImpl implements CsvFileCiqualService {
    private String FIRST_HEADER_FIELD = "alim_grp_code";

    @Autowired
    private FoodService foodService;

    @Override
    public List<FoodDto> loadFile(String path) throws IOException {
        return Files.lines(Paths.get(path))
                .skip(1)
                .map(this::convertCsvLineToAlimentDto)
                .collect(Collectors.toList());
    }

    @Override
    public int loadFileAndInsertIntoDatabase(String path) throws IOException {
        List<FoodDto> foodDtoList = loadFile(path);

        //insert objects into DB using the food service
        foodDtoList.stream().limit(2).forEach(foodService::create);

        return foodDtoList.size();
    }

    private FoodDto convertCsvLineToAlimentDto(String csvLine) {
        List<String> fields = Arrays.asList(csvLine.trim().split(";"));

        //I do nothing if the line is the header
        if (fields.get(0).equalsIgnoreCase(FIRST_HEADER_FIELD)) return null;

        //otherwise I build the DTO

        //build the group definition of the food
        FoodGroupDto groupDto = new FoodGroupDto(fields.get(0), fields.get(3));
        FoodSubGroupDto subGroupDto = new FoodSubGroupDto(groupDto, fields.get(1), fields.get(4));
        FoodSubSubGroupDto subSubGroupDto = new FoodSubSubGroupDto(subGroupDto, fields.get(2), fields.get(5));

        //build the aliment scientific name
        FoodScientificNameDto alimentScientificNameDto = new FoodScientificNameDto(fields.get(8));

        return new FoodDto(fields.get(6), fields.get(7), alimentScientificNameDto, subSubGroupDto, null);

    }

}
