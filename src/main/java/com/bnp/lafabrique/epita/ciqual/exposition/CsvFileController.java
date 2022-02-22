package com.bnp.lafabrique.epita.ciqual.exposition;

import com.bnp.lafabrique.epita.ciqual.application.CsvFileCiqualService;
import com.bnp.lafabrique.epita.ciqual.dto.CsvFileDefinitionDto;
import com.bnp.lafabrique.epita.ciqual.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ciqual")
public class CsvFileController {

    @Autowired
    CsvFileCiqualService csvFileCiqualService;


    /**
     * just load the file into a list of object
     * @param csvFileDefinitionDto : expect json with a path parameter and full path as a value (c:\\xxx\\xxx.csv)
     * @return first five elements loaded
     */
    @PostMapping(value = "/loadfile")
    public List<FoodDto> loadFile(@RequestBody CsvFileDefinitionDto csvFileDefinitionDto) {
        try {
            String path=csvFileDefinitionDto.getPath();
            System.out.println("Path of file to load: " + path);
            List<FoodDto> foodDtoList = csvFileCiqualService.loadFile(path);
            System.out.println("nb line loaded:"+foodDtoList.size());
            System.out.println("First 5 elements");
            foodDtoList.stream().limit(5).forEach(System.out::println);
            return foodDtoList.stream().limit(5).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(value = "/loadfile/insertintodb")
    public int loadFileAndInsertIntoDatabase(@RequestBody CsvFileDefinitionDto csvFileDefinitionDto) {
        try {
            String path=csvFileDefinitionDto.getPath();
            System.out.println("Path of file to load: " + path);
            int nbElementCreated = csvFileCiqualService.loadFileAndInsertIntoDatabase(path);
            System.out.println("nb line loaded:"+nbElementCreated);
            return nbElementCreated;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }


    /**
     * load the csv file
     * @param path : full path of the file (c:\\xxx\\xxx.csv)
     * @return list of loaded FoodDto object
     * @throws IOException if issue with reading the file
     */
    private List<FoodDto> loadFile(String path) throws IOException{
            System.out.println("Path of file to load: " + path);
            List<FoodDto> foodDtoList = csvFileCiqualService.loadFile(path);
            System.out.println("nb line loaded:"+foodDtoList.size());
            System.out.println("First 5 elements");
            foodDtoList.stream().limit(5).forEach(System.out::println);
            return foodDtoList;
    }

}
