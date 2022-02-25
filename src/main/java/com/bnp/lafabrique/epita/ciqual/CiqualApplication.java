package com.bnp.lafabrique.epita.ciqual;

import com.bnp.lafabrique.epita.ciqual.application.FoodComponentTypeService;
import com.bnp.lafabrique.epita.ciqual.application.FoodComponentTypeServiceImpl;
import com.bnp.lafabrique.epita.ciqual.cache.CacheFoodComponentType;
import com.bnp.lafabrique.epita.ciqual.dto.FoodComponentTypeDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CiqualApplication {

	public static void initDB(){

		//create the componentTypes in case they do not exist yet
		FoodComponentTypeService componentTypeService= new FoodComponentTypeServiceImpl();

		FoodComponentTypeDto alimentComponentTypeDto = new FoodComponentTypeDto("glucides", "Glucides (g/100 g)",16);
		componentTypeService.create(alimentComponentTypeDto);
		alimentComponentTypeDto = new FoodComponentTypeDto("lipides", "Lipides (g/100 g)",17);
		componentTypeService.create(alimentComponentTypeDto);
		alimentComponentTypeDto = new FoodComponentTypeDto("sucres", "Sucres (g/100 g)",18);
		componentTypeService.create(alimentComponentTypeDto);

	}


	public static void main(String[] args) {
		initDB();
		CacheFoodComponentType.initCacheFromBdd();

		SpringApplication.run(CiqualApplication.class, args);

	}

}
