package com.bnp.lafabrique.epita.ciqual.dto;

import com.bnp.lafabrique.epita.ciqual.dto.enumerate.EnumComparator;

public class AlimentComponentDto {

    private long id;

    private String name;

    private ComponentTypeDto componentType;

    private long quantity;

    /* comparator on the quantity:
        - null if no comparator
        - otherwise could be for example: > or  <

     */
    private EnumComparator comparator;

    public AlimentComponentDto() {
    }

    public AlimentComponentDto(String name, ComponentTypeDto componentType, long quantity, EnumComparator comparator) {
        this.name = name;
        this.componentType = componentType;
        this.quantity = quantity;
        this.comparator = comparator;
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



    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }



    public EnumComparator getComparator() {
        return comparator;
    }

    public void setComparator(EnumComparator comparator) {
        this.comparator = comparator;
    }

    public ComponentTypeDto getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentTypeDto componentType) {
        this.componentType = componentType;
    }

    @Override
    public String toString() {
        return "AlimentComponentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", componentType=" + componentType +
                ", quantity=" + quantity +
                ", comparator=" + comparator +
                '}';
    }
}
