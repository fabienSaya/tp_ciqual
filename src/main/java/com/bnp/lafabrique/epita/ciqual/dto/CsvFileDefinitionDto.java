package com.bnp.lafabrique.epita.ciqual.dto;

public class CsvFileDefinitionDto {
    private String path;

    public CsvFileDefinitionDto() {
    }

    public CsvFileDefinitionDto(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
