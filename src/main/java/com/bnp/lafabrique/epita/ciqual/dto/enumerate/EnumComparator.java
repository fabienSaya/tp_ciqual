package com.bnp.lafabrique.epita.ciqual.dto.enumerate;

import java.util.Arrays;

public enum EnumComparator {
    BELOW("<"),
    ABOVE(">"),
    TRACE("traces");

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

    public static EnumComparator getEnumFromlabel(String label) {
        for (EnumComparator enumComparator:EnumComparator.values()) {
            if (enumComparator.getLabel().equalsIgnoreCase(label)) return enumComparator;
        }
        return null;
    }

}
