package com.bnp.lafabrique.epita.ciqual.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ScientificNameDto {
    private long id;

    private String name;

    public ScientificNameDto() {
    }

    public ScientificNameDto(String name) {
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
