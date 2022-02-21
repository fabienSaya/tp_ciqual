package com.bnp.lafabrique.epita.ciqual.domaine;

import javax.persistence.*;


/**
 * we could put group, subgroup, subsubgroup in only one table.
 * I chose to create different ones
 */
@Entity
public class SubSubGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private SubGroup group;

    private String code;
    //we could think of a way to store labels with different langage without changing the DB model.
    //but here I simplify. This could be an improvement for all class with labels
    private String nameFR;

    public SubSubGroup() {
    }

    public SubSubGroup(SubGroup group, String code, String nameFR) {
        this.group = group;
        this.code = code;
        this.nameFR = nameFR;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SubGroup getGroup() {
        return group;
    }

    public void setGroup(SubGroup group) {
        this.group = group;
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
