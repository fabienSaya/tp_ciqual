package com.bnp.lafabrique.epita.ciqual.dto;


/**
 * we could put group, subgroup, subsubgroup in only one table.
 * I chose to create different ones
 */
public class FoodSubGroupDto {
    private long id;

    private FoodGroupDto group;

    private String code;
    //we could think of a way to store labels with different langage without changing the DB model.
    //but here I simplify. This could be an improvement for all class with labels
    private String nameFR;

    public FoodSubGroupDto() {
    }

    public FoodSubGroupDto(FoodGroupDto group, String code, String nameFR) {
        this.group = group;
        this.code = code;
        this.nameFR = nameFR;
    }

    public FoodSubGroupDto(FoodGroupDto group, String code, String nameFR, long id) {
        this(group,code,nameFR);
        this.id=id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FoodGroupDto getGroup() {
        return group;
    }

    public void setGroup(FoodGroupDto group) {
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
