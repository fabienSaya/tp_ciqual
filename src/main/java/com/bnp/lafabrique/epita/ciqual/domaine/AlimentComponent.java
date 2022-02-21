package com.bnp.lafabrique.epita.ciqual.domaine;

import javax.persistence.*;

@Entity
public class AlimentComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    //private ComponentType componentType;

    private long quantity;

    /* comparator on the quantity:
        - null if no comparator
        - otherwise could be for example: > or  <

     */
    private String comparator;

    public AlimentComponent() {
    }


    //public AlimentComponent(String name, ComponentType componentType, long quantity, String comparator) {
    public AlimentComponent(String name, long quantity, String comparator) {
        this.name = name;
        //this.componentType = componentType;
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

    public String getComparator() {
        return comparator;
    }

    public void setComparator(String comparator) {
        this.comparator = comparator;
    }
}
