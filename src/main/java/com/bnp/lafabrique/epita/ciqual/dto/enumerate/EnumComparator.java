package com.bnp.lafabrique.epita.ciqual.dto.enumerate;

public enum EnumComparator {
    BELOW("<"),
    ABOVE(">");

    private String label;

    EnumComparator(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }

}
