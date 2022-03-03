package com.bnp.lafabrique.epita.ciqual.dto;

import com.bnp.lafabrique.epita.ciqual.dto.enumerate.EnumComparator;

public class FoodComponentDto {

    private long id;

    private FoodComponentTypeDto componentType;

    //object double as quantity can be null
    private Double quantity;

    /* comparator on the quantity:
        - null if no comparator
        - otherwise could be for example: > or  <

     */
    private EnumComparator comparator;

    public FoodComponentDto() {
    }


    /**
     *
     * @param id put 0 if it is a new element to create in BDD
     * @param componentType
     * @param quantity
     * @param comparator
     */
    public FoodComponentDto(long id, FoodComponentTypeDto componentType, Double quantity, EnumComparator comparator) {
        this.id=id;
        this.componentType=componentType;
        this.quantity=quantity;
        this.comparator=comparator;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
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
