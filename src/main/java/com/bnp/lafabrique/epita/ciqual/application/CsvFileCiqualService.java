package com.bnp.lafabrique.epita.ciqual.application;

import com.bnp.lafabrique.epita.ciqual.dto.AlimentDto;

import java.io.IOException;
import java.util.List;

public interface CsvFileCiqualService {

    /**
     *
     * @param path : format should be c:\\xxxx
     * @return
     */
    List<AlimentDto> loadFile(String path) throws IOException;

}
