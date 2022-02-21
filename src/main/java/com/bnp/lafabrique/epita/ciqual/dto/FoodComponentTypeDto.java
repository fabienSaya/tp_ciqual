package com.bnp.lafabrique.epita.ciqual.dto;

public class FoodComponentTypeDto {
    private long id;

    private String name;
    private String label;

    public FoodComponentTypeDto(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public FoodComponentTypeDto() {
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
