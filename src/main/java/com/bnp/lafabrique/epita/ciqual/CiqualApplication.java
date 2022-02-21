package com.bnp.lafabrique.epita.ciqual;

import com.bnp.lafabrique.epita.ciqual.application.CsvFileCiqualService;
import com.bnp.lafabrique.epita.ciqual.application.CsvFileCiqualServiceImpl;
import com.bnp.lafabrique.epita.ciqual.dto.AlimentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class CiqualApplication {




	public static void main(String[] args) {
		SpringApplication.run(CiqualApplication.class, args);
	}

}
