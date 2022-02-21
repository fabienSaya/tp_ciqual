package com.bnp.lafabrique.epita.ciqual.dto;

import com.bnp.lafabrique.epita.ciqual.dto.enumerate.EnumComparator;

public class FoodComponentDto {

    private long id;



    private FoodComponentTypeDto componentType;

    private long quantity;

    /* comparator on the quantity:
        - null if no comparator
        - otherwise could be for example: > or  <

     */
    private EnumComparator comparator;

    public FoodComponentDto() {
    }

    public FoodComponentDto(FoodComponentTypeDto componentType, long quantity, EnumComparator comparator) {
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

    public FoodComponentTypeDto getComponentType() {
        return componentType;
    }

    public void setComponentType(FoodComponentTypeDto componentType) {
        this.componentType = componentType;
    }

    @Override
    public String toString() {
        return "AlimentComponentDto{" +
                "id=" + id +
                ", componentType=" + componentType +
                ", quantity=" + quantity +
                ", comparator=" + comparator +
                '}';
    }
}
