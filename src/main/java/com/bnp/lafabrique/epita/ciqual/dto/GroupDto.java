package com.bnp.lafabrique.epita.ciqual.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class GroupDto {

    private long id;

    private String code;
    //we could think of a way to store labels with different langage without changing the DB model.
    //but here I simplify. This could be an improvement for all class with labels
    private String nameFR;

    public GroupDto() {
    }

    public GroupDto(String code, String nameFR) {
        this.code = code;
        this.nameFR = nameFR;
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

    public String getNameFR() {
        return nameFR;
    }

    public void setNameFR(String nameFR) {
        this.nameFR = nameFR;
    }

    @Override
    public String toString() {
        return "GroupDto{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", nameFR='" + nameFR + '\'' +
                '}';
    }
}
