package com.bnp.lafabrique.epita.ciqual.application;

import com.bnp.lafabrique.epita.ciqual.cache.CacheFoodComponentType;
import com.bnp.lafabrique.epita.ciqual.domaine.FoodComponentType;
import com.bnp.lafabrique.epita.ciqual.dto.*;
import com.bnp.lafabrique.epita.ciqual.dto.enumerate.EnumComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CsvFileCiqualServiceImpl implements CsvFileCiqualService {
    private  static final String FIRST_HEADER_FIELD = "alim_grp_code";

    @Autowired
    private FoodService foodService;

    @Autowired
    private FoodComponentTypeService foodComponentTypeService;

    @Override
    public List<FoodDto> loadFile(String path) throws IOException {

        Stream<String> fileLines=null;
        try {
            fileLines = Files.lines(Paths.get(path));
            return fileLines
                    .skip(1)
                    .map(this::convertCsvLineToFoodDto)
                    .collect(Collectors.toList());
        } finally {
            if (fileLines!=null) fileLines.close();
        }

    }

    @Override
    public int loadFileAndInsertIntoDatabase(String path) throws IOException {
        //note: il y avait un soucis de doublons dans le csv source. Le produit 9621 apparait 2 fois. j'ai changé un des codes.
        //sinon ca oblige à cause d'une produit à autoriser un meme code, different categorie. Bref changer toute la structure pour ce qui
        //ressemble à un prob de consolidation. Pont à discuter mais ca me semble pas l'objet du TP

        List<FoodDto> foodDtoList = loadFile(path);

        //insert objects into DB using the food service
        //foodDtoList.stream().limit(2).forEach(foodService::create);
        foodDtoList.forEach(foodService::create);

        return foodDtoList.size();
    }

    private FoodDto convertCsvLineToFoodDto(String csvLine) {
        //we put -1 in the split so even empty fields at the end generates a field in the list
        List<String> fields = Arrays.asList(csvLine.trim().split(";",-1));

        //I do nothing if the line is the header
        if (fields.get(0).equalsIgnoreCase(FIRST_HEADER_FIELD)) return null;

        //otherwise I build the DTO

        //build the group definition of the food
        FoodGroupDto groupDto = new FoodGroupDto(fields.get(0), fields.get(3));
        FoodSubGroupDto subGroupDto = new FoodSubGroupDto(groupDto, fields.get(1), fields.get(4));
        FoodSubSubGroupDto subSubGroupDto = new FoodSubSubGroupDto(subGroupDto, fields.get(2), fields.get(5));

        //build the aliment scientific name
        FoodScientificNameDto alimentScientificNameDto = new FoodScientificNameDto(fields.get(8));

        //build the component list
        List<FoodComponentDto> foodComponentDtoList=new ArrayList<>();

        //for each component type we want to get the data (the ones that where defined in the DB)
        for (FoodComponentType foodComponentType: CacheFoodComponentType.getAllComponentTypes()) {
            //we get it's raw value
            String columnValue= null;
            try {
                columnValue = fields.get(foodComponentType.getExcelColumn());
            } catch (Exception e) {
                e.printStackTrace();
            }

            //we now check if there is a quantity and eventually a comparator
            Double quantity=null;
            EnumComparator enumComparatorFound=null;

            //if the value is not - or empty then we parse value and comparator
            if (columnValue!=null &&!columnValue.trim().equalsIgnoreCase("-")&&!columnValue.isEmpty()) {
                //we check if one of the comparator is in the value, if yes, we

                for (EnumComparator enumComparator : EnumComparator.values()) {
                    if(columnValue.contains(enumComparator.getLabel())) {
                        enumComparatorFound=enumComparator;
                        break;
                    }
                }

                //if we found a comparator, we remove it from the value and then we parse the quantity, else we just parse the quantity
                if (enumComparatorFound!=null) {
                    //if the value indicates only traces, we don't parse the quantity
                    if (enumComparatorFound!=EnumComparator.TRACE) {
                        //it is not traces so we parse the quantity
                        columnValue = columnValue.replaceFirst(enumComparatorFound.getLabel(), "");

                        try {
                            quantity = Double.valueOf(columnValue.trim().replace(",", "."));
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw e;
                        }
                    }
                } else {
                    //it is a simple value to parse
                    try {
                        quantity = Double.valueOf(columnValue.trim().replace(",", "."));
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw e;
                    }
                }

            }

            FoodComponentTypeDto foodComponentTypeDto =  foodComponentTypeService.convertComponentTypeToComponentTypeDto(foodComponentType);

            foodComponentDtoList.add(new FoodComponentDto(0,foodComponentTypeDto,quantity,enumComparatorFound));

        }

        return new FoodDto(0,fields.get(6), fields.get(7), alimentScientificNameDto, subSubGroupDto, foodComponentDtoList);

    }

}
