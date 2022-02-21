package com.bnp.lafabrique.epita.ciqual.dto;

public class FoodScientificNameDto {
    private long id;

    private String name;

    public FoodScientificNameDto() {
    }

    public FoodScientificNameDto(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "ScientificNameDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
