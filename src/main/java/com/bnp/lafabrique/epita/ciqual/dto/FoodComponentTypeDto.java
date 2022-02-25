package com.bnp.lafabrique.epita.ciqual.dto;

public class FoodComponentTypeDto {
    private long id;

    private String name;
    private String label;

    private int excelColumn;

    public FoodComponentTypeDto(String name, String label, int excelColumn) {
        this.name = name;
        this.label = label;
        this.excelColumn= excelColumn;
    }

    public FoodComponentTypeDto(String name, String label, int excelColumn,long id) {
        this(name,label,excelColumn);
        this.id=id;
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

    public int getExcelColumn() {
        return excelColumn;
    }

    public void setExcelColumn(int excelColumn) {
        this.excelColumn = excelColumn;
    }
}
