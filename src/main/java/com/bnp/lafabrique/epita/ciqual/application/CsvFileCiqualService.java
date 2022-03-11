package com.bnp.lafabrique.epita.ciqual.application;

import com.bnp.lafabrique.epita.ciqual.dto.FoodDto;

import java.io.IOException;
import java.util.List;

public interface CsvFileCiqualService {

    /**
     * just load the csv file into a list of Dto
     * @param path : format should be c:\\xxxx\\xx.csv
     * @return list of FoodDto corresponding to the file
     * @throws IOException if issue reading the file
     */
    List<FoodDto> loadFile(String path) throws IOException;


    /**
     * load the csv file and insert elements in the database
     *
     * @param path : format should be c:\\xxxx\\xx.csv
     * @return nb of elements inserted
     * @throws IOException if issue reading the file
     */
    int loadFileAndInsertIntoDatabase(String path) throws IOException;


}
