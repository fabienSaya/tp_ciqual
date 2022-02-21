package com.bnp.lafabrique.epita.ciqual.dto;

public class ComponentTypeDto {
    private long id;

    private String name;

    public ComponentTypeDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ComponentTypeDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
