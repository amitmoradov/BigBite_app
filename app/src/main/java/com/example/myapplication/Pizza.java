package com.example.myapplication;

import java.util.List;

public class Pizza extends Product{
    private List<String> toppings;     // רשימת תוספות
    private String size;               // גודל הפיצה (קטן, בינוני, גדול)

    // Constructor
    public Pizza(String name,String descrip, double price, int quantity,boolean avlb, List<String> toppings, String size) {
        super(name,descrip,price,quantity,avlb);
        this.toppings = toppings;
        this.size = size;
        setId(2);
    }

    public List<String> getToppings() { return toppings; }
    public void setToppings(List<String> toppings) { this.toppings = toppings; }

    public String getSize() { return size; }
    public void setSize(String size) {
        if (size.isEmpty()) {
            throw new  IllegalArgumentException("The Size of Pizza can not be empty");
        }
        this.size = size;
    }

    // Calculate the total price including toppings
    public double getTotalPrice() {
        double toppingCost = 5 * toppings.size(); // נניח שכל תוספת מוסיפה 5 ש"ח
        return super.getPrice() + toppingCost;
    }
}
