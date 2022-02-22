package com.bnp.lafabrique.epita.ciqual.domaine;

import javax.persistence.*;


/**
 * we could put group, subgroup, subsubgroup in only one table.
 * I chose to create different ones
 */
@Entity
public class FoodSubSubGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private FoodSubGroup alimentSubGroup;

    private String code;
    //we could think of a way to store labels with different langage without changing the DB model.
    //but here I simplify. This could be an improvement for all class with labels
    private String nameFR;

    public FoodSubSubGroup() {
    }

    public FoodSubSubGroup(FoodSubGroup alimentSubGroup, String code, String nameFR) {
        this.alimentSubGroup = alimentSubGroup;
        this.code = code;
        this.nameFR = nameFR;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FoodSubGroup getSubGroup() {
        return alimentSubGroup;
    }

    public void setSubGroup(FoodSubGroup alimentSubGroup) {
        this.alimentSubGroup = alimentSubGroup;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameFR() {
        return nameFR;
    }

    public void setNameFR(String nameFR) {
        this.nameFR = nameFR;
    }
}
