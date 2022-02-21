package com.bnp.lafabrique.epita.ciqual.dto;


public class SubSubGroupDto {
    private long id;

    private SubGroupDto subGroup;

    private String code;
    //we could think of a way to store labels with different langage without changing the DB model.
    //but here I simplify. This could be an improvement for all class with labels
    private String nameFR;

    public SubSubGroupDto() {
    }

    public SubSubGroupDto(SubGroupDto group, String code, String nameFR) {
        this.subGroup = group;
        this.code = code;
        this.nameFR = nameFR;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SubGroupDto getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(SubGroupDto group) {
        this.subGroup = group;
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
        return "SubSubGroupDto{" +
                "id=" + id +
                ", subGroup=" + subGroup +
                ", code='" + code + '\'' +
                ", nameFR='" + nameFR + '\'' +
                '}';
    }
}
