package com.example.iscip;

import java.io.Serializable;

public class Fruit implements Serializable {
    private String Name;
    private String Color;
    private double Weight;
    public Fruit(String name, String color, double weight) {
        this.Name = name;
        this.Color = color;
        this.Weight = weight;
    }
    public String getName() { return this.Name; }
    public String getColor() { return this.Color; }
    public double getWeight() { return this.Weight; }
}