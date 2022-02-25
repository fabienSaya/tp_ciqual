package com.bnp.lafabrique.epita.ciqual.domaine;

import javax.persistence.*;

@Entity
public class FoodComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private FoodComponentType componentType;

    private Double quantity;

    /* comparator on the quantity:
        - null if no comparator
        - otherwise could be for example: > or  <

     */
    private String comparator;

    public FoodComponent() {
    }

    public FoodComponent(FoodComponentType componentType, Double quantity, String comparator) {
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

    /**
     *
     * @return quantity that can be null if we didn't get the info
     */
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
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
