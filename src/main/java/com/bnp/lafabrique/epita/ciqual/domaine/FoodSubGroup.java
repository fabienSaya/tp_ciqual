package com.bnp.lafabrique.epita.ciqual.domaine;

import javax.persistence.*;


/**
 * we could put group, subgroup, subsubgroup in only one table.
 * I chose to create different ones
 */
@Entity
public class FoodSubGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne  (cascade = CascadeType.ALL)
    private FoodGroup alimentGroup;

    private String code;
    //we could think of a way to store labels with different langage without changing the DB model.
    //but here I simplify. This could be an improvement for all class with labels
    private String nameFR;

    public FoodSubGroup() {
    }

    public FoodSubGroup(FoodGroup alimentGroup, String code, String nameFR) {
        this.alimentGroup = alimentGroup;
        this.code = code;
        this.nameFR = nameFR;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FoodGroup getGroup() {
        return alimentGroup;
    }

    public void setGroup(FoodGroup alimentGroup) {
        this.alimentGroup = alimentGroup;
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
