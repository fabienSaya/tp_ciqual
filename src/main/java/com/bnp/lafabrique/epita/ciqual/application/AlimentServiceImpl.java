package com.bnp.lafabrique.epita.ciqual.application;

import com.bnp.lafabrique.epita.ciqual.dao.AlimentDao;
import com.bnp.lafabrique.epita.ciqual.dao.DaoFactory;
import com.bnp.lafabrique.epita.ciqual.domaine.*;
import com.bnp.lafabrique.epita.ciqual.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlimentServiceImpl implements AlimentService{

    @Override
    public Long create(AlimentDto alimentDto) {
        AlimentDao alimentDao= DaoFactory.getProduitDao();
        Aliment aliment=alimentDtoToAliment(alimentDto);
        return alimentDao.create(aliment);
    }

    private Aliment alimentDtoToAliment(AlimentDto alimentDto){
        ScientificName scientificName=new ScientificName(alimentDto.getScientificName().getName());

        //creation des groupes

        //il faudra ajouter des controles sur la nullité des données
        SubSubGroupDto subSubGroupDto=alimentDto.getSubSubGroup();
        SubGroupDto subGroupDto= subSubGroupDto.getSubGroup();
        GroupDto groupDto = subGroupDto.getGroup();


        Group group= new Group(groupDto.getCode(),groupDto.getNameFR());
        SubGroup subGroup= new SubGroup(group, subGroupDto.getCode(), subGroupDto.getNameFR());
        SubSubGroup subSubGroup = new SubSubGroup(subGroup,subSubGroupDto.getCode(), subSubGroupDto.getNameFR());

        List<AlimentComponent> alimentComponentList= alimentDto.getComponentList().stream().map(this::alimentComponentDtoToAlimentComponent).collect(Collectors.toList());

        return new Aliment(alimentDto.getCode(), alimentDto.getName(), scientificName,subSubGroup,alimentComponentList);

    }

    private AlimentComponent alimentComponentDtoToAlimentComponent(AlimentComponentDto alimentComponentDto){



        //return new AlimentComponent(alimentComponentDto.getName(),unit,alimentComponentDto.getQuantity(),alimentComponentDto.getComparator().getLabel());

        return new AlimentComponent(alimentComponentDto.getName(),alimentComponentDto.getQuantity(),alimentComponentDto.getComparator().getLabel());
    }









}
