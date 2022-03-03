package com.bnp.lafabrique.epita.ciqual.dto;

import java.util.List;

public class FoodDto extends FoodLightInfoDto{

    private FoodScientificNameDto scientificName;

    private FoodSubSubGroupDto subSubGroup;

    private List<FoodComponentDto> componentList;

    public FoodDto() {
    }

    public FoodDto(long id, String code, String name, FoodScientificNameDto scientificName, FoodSubSubGroupDto subSubGroup, List<FoodComponentDto> componentList) {
        super(id,code,name);
        this.scientificName = scientificName;
        this.subSubGroup = subSubGroup;
        this.componentList = componentList;
    }


    public FoodScientificNameDto getScientificName() {
        return scientificName;
    }

    public void setScientificName(FoodScientificNameDto scientificName) {
        this.scientificName = scientificName;
    }

    public FoodSubSubGroupDto getSubSubGroup() {
        return subSubGroup;
    }

    public void setSubSubGroup(FoodSubSubGroupDto subSubGroup) {
        this.subSubGroup = subSubGroup;
    }

    public List<FoodComponentDto> getComponentList() {
        return componentList;
    }

    public void setComponentList(List<FoodComponentDto> componentList) {
        this.componentList = componentList;
    }

    @Override
    public String toString() {
        return "AlimentDto{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", scientificName=" + scientificName +
                ", subSubGroup=" + subSubGroup +
                ", componentList=" + componentList +
                '}';
    }
}
