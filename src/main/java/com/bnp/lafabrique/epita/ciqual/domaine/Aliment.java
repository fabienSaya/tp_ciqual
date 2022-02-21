package com.bnp.lafabrique.epita.ciqual.domaine;

import javax.persistence.*;
import java.util.List;

@Entity
public class Aliment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String code;
    private String name;

    @OneToOne
    private ScientificName scientificName;

    @ManyToOne
    private SubSubGroup subSubGroup;





    public Aliment() {
    }

    public Aliment(String code, String name, ScientificName scientificName, SubSubGroup subSubGroup, List<AlimentComponent> componentList) {
        this.code = code;
        this.name = name;
        this.scientificName = scientificName;
        this.subSubGroup = subSubGroup;
        //this.componentList = componentList;
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

    public ScientificName getScientificName() {
        return scientificName;
    }

    public void setScientificName(ScientificName scientificName) {
        this.scientificName = scientificName;
    }

    public SubSubGroup getSubSubGroup() {
        return subSubGroup;
    }

    public void setSubSubGroup(SubSubGroup subSubGroup) {
        this.subSubGroup = subSubGroup;
    }
/*
    public List<AlimentComponent> getComponentList() {
        return componentList;
    }

    public void setComponentList(List<AlimentComponent> componentList) {
        this.componentList = componentList;
    }*/
}
