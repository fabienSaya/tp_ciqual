package com.bnp.lafabrique.epita.ciqual.dto;

import javax.persistence.*;


/**
 * we could put group, subgroup, subsubgroup in only one table.
 * I chose to create different ones
 */
public class SubGroupDto {
    private long id;

    private GroupDto group;

    private String code;
    //we could think of a way to store labels with different langage without changing the DB model.
    //but here I simplify. This could be an improvement for all class with labels
    private String nameFR;

    public SubGroupDto() {
    }

    public SubGroupDto(GroupDto group, String code, String nameFR) {
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

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
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

    @Override
    public String toString() {
        return "SubGroupDto{" +
                "id=" + id +
                ", group=" + group +
                ", code='" + code + '\'' +
                ", nameFR='" + nameFR + '\'' +
                '}';
    }
}
