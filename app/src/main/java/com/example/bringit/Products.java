package com.example.bringit;

import java.util.ArrayList;
import java.util.List;

public class Products {
    private String description;
    private int cost;
    private List<String> foods = new ArrayList<>();

    public Products(String description, int cost, List<String> foods) {
        this.description = description;
        this.cost = cost;
        this.foods = foods;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<String> getFoods() {
        return foods;
    }

    public void setFoods(List<String> foods) {
        this.foods = foods;
    }
}
