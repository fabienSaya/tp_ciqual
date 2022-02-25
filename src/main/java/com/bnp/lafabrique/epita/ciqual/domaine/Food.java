package com.bnp.lafabrique.epita.ciqual.domaine;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NaturalId
    @Column(nullable = false,unique = true)
    private String code;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private FoodScientificName alimentScientificName;

    @ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private FoodSubSubGroup alimentSubSubGroup;

    @OneToMany (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<FoodComponent> componentList;

    public Food() {
    }

    public Food(String code, String name, FoodScientificName alimentScientificName, FoodSubSubGroup alimentSubSubGroup, List<FoodComponent> componentList) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.alimentScientificName = alimentScientificName;
        this.alimentSubSubGroup = alimentSubSubGroup;
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

    public FoodScientificName getScientificName() {
        return alimentScientificName;
    }

    public void setScientificName(FoodScientificName alimentScientificName) {
        this.alimentScientificName = alimentScientificName;
    }

    public FoodSubSubGroup getSubSubGroup() {
        return alimentSubSubGroup;
    }

    public void setSubSubGroup(FoodSubSubGroup alimentSubSubGroup) {
        this.alimentSubSubGroup = alimentSubSubGroup;
    }

    public List<FoodComponent> getComponentList() {
        return componentList;
    }

    public void setComponentList(List<FoodComponent> componentList) {
        this.componentList = componentList;
    }
}
