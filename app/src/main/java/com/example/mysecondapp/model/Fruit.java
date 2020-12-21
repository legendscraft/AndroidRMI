package com.example.mysecondapp.model;

import java.io.Serializable;

public class Fruit implements Serializable {

    private static final long serialVersionUID = -6849393470754363710L;

    private int id;
    private String name;
    private int price;

    public Fruit(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
