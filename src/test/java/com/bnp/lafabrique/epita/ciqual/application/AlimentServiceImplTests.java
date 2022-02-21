package com.bnp.lafabrique.epita.ciqual.application;

import com.bnp.lafabrique.epita.ciqual.dto.FoodDto;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.util.List;


public class AlimentServiceImplTests {

    @Test
    void testChargementFichier() {
        System.out.println("test");
        CsvFileCiqualService csvFileCiqualService =new CsvFileCiqualServiceImpl();
        try {
            String path="C:\\LaFabrique\\Exercices\\Java\\epita\\TP_VACANCE_FEV\\ciqual\\src\\main\\resources\\Table Ciqual 2020_FR_2020 07 07.csv";

            List<FoodDto> aliments= csvFileCiqualService.loadFile(path);

            FoodDto alimentDto=aliments.get(2);

            System.out.println(aliments.get(2));

            assertThat(alimentDto.getCode()).isEqualTo("25601");
            assertThat(alimentDto.getName()).isEqualTo("Salade de thon et légumes, appertisée");






        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
