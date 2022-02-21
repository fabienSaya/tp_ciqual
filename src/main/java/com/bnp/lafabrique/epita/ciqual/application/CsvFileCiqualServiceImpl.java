package com.bnp.lafabrique.epita.ciqual.application;

import com.bnp.lafabrique.epita.ciqual.dto.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvFileCiqualServiceImpl implements CsvFileCiqualService {
    private String FIRST_HEADER_FIELD = "alim_grp_code";

    @Override
    public List<AlimentDto> loadFile(String pathStr) throws IOException {

/*
        try(BufferedReader in = new BufferedReader(new FileReader(pathname))) {
            String line;
            while ((line = in.readLine()) != null) {
                // process line here.
            }
        }

 */

        return Files.lines(Paths.get(pathStr))
                .map(this::convertCsvLineToAlimentDto)
                .collect(Collectors.toList());

    }

    private AlimentDto convertCsvLineToAlimentDto(String csvLine) {
        List<String> fields = Arrays.asList(csvLine.trim().split(";"));

        //I do nothing if the line is the header
        if (fields.get(0).equalsIgnoreCase(FIRST_HEADER_FIELD)) return null;

        //otherwise I build the DTO
        GroupDto groupDto = new GroupDto(fields.get(0), fields.get(3));
        SubGroupDto subGroupDto = new SubGroupDto(groupDto, fields.get(1), fields.get(4));
        SubSubGroupDto subSubGroupDto = new SubSubGroupDto(subGroupDto, fields.get(2), fields.get(5));

        ScientificNameDto scientificNameDto = new ScientificNameDto(fields.get(8));

        return new AlimentDto(fields.get(6), fields.get(7), scientificNameDto, subSubGroupDto, null);

    }

}
