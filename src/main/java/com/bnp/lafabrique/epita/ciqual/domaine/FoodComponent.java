package com.bnp.lafabrique.epita.ciqual.domaine;

import javax.persistence.*;

@Entity
public class FoodComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private FoodComponentType componentType;

    private long quantity;

    /* comparator on the quantity:
        - null if no comparator
        - otherwise could be for example: > or  <

     */
    private String comparator;

    public FoodComponent() {
    }

    public FoodComponent(FoodComponentType componentType, long quantity, String comparator) {
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

    public String getComparator() {
        return comparator;
    }

    public void setComparator(String comparator) {
        this.comparator = comparator;
    }

    public FoodComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(FoodComponentType componentType) {
        this.componentType = componentType;
    }
}
