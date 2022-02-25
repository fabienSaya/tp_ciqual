package com.bnp.lafabrique.epita.ciqual.domaine;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
public class FoodComponentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NaturalId
    @Column(nullable = false,unique = true)
    private String name;
    private String label;

    @Column(unique=true)
    private int excelColumn;

    public FoodComponentType() {
    }

    public FoodComponentType(String name, String label, int excelColumn) {
        this.name = name;
        this.label = label;
        this.excelColumn = excelColumn;
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

    public int getExcelColumn() {
        return excelColumn;
    }

    public void setExcelColumn(int excelColumn) {
        this.excelColumn = excelColumn;
    }
}
