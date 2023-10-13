package com.wildcodeschool.milkshake.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Recette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int quantity;
    private String flavour;

    public Recette(){}

    public Recette(String name, int quantity, String flavour){
        this.name = name;
        this.quantity = quantity;
        this.flavour = flavour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String mainIngredient) {
        this.flavour = mainIngredient;
    }
}

