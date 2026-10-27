package com.example.iscip.data;

public class Fruit {

    private int id;
    private String name;
    private String type;
    private String description;
    private String calories;

    public Fruit() {
    }

    public Fruit(int id, String name, String type,
                 String description, String calories) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.calories = calories;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
}