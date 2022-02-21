package com.bnp.lafabrique.epita.ciqual.dto;

import javax.persistence.*;
import java.util.List;

public class AlimentDto {
    private long id;

    private String code;
    private String name;

    private ScientificNameDto scientificName;

    private SubSubGroupDto subSubGroup;

    private List<AlimentComponentDto> componentList;

    public AlimentDto() {
    }

    public AlimentDto(String code, String name, ScientificNameDto scientificName, SubSubGroupDto subSubGroup, List<AlimentComponentDto> componentList) {
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

    public ScientificNameDto getScientificName() {
        return scientificName;
    }

    public void setScientificName(ScientificNameDto scientificName) {
        this.scientificName = scientificName;
    }

    public SubSubGroupDto getSubSubGroup() {
        return subSubGroup;
    }

    public void setSubSubGroup(SubSubGroupDto subSubGroup) {
        this.subSubGroup = subSubGroup;
    }

    public List<AlimentComponentDto> getComponentList() {
        return componentList;
    }

    public void setComponentList(List<AlimentComponentDto> componentList) {
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
