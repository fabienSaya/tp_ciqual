package com.bnp.lafabrique.epita.ciqual;

import com.bnp.lafabrique.epita.ciqual.application.ComponentTypeService;
import com.bnp.lafabrique.epita.ciqual.application.ComponentTypeServiceImpl;
import com.bnp.lafabrique.epita.ciqual.dto.FoodComponentTypeDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CiqualApplication {

	public static void initDB(){

		//create the componentTypes in case they do not exist yet
		ComponentTypeService componentTypeService= new ComponentTypeServiceImpl();

		FoodComponentTypeDto alimentComponentTypeDto = new FoodComponentTypeDto("glucides", "Glucides (g/100 g)");
		componentTypeService.create(alimentComponentTypeDto);
		alimentComponentTypeDto = new FoodComponentTypeDto("lipides", "Lipides (g/100 g)");
		componentTypeService.create(alimentComponentTypeDto);
		alimentComponentTypeDto = new FoodComponentTypeDto("sucres", "Sucres (g/100 g)");
		componentTypeService.create(alimentComponentTypeDto);

	}


	public static void main(String[] args) {
		initDB();
		SpringApplication.run(CiqualApplication.class, args);

	}

}
