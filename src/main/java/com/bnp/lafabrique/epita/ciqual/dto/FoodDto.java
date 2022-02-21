package com.bnp.lafabrique.epita.ciqual.dto;

import java.util.List;

public class FoodDto {
    private long id;

    private String code;
    private String name;

    private FoodScientificNameDto scientificName;

    private FoodSubSubGroupDto subSubGroup;

    private List<FoodComponentDto> componentList;

    public FoodDto() {
    }

    public FoodDto(String code, String name, FoodScientificNameDto scientificName, FoodSubSubGroupDto subSubGroup, List<FoodComponentDto> componentList) {
        this.code = code;
        this.name = name;
        this.scientificName = scientificName;
        this.subSubGroup = subSubGroup;
        this.componentList = componentList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
