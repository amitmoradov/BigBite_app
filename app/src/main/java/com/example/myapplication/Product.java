package com.example.myapplication;

public class Product {

    protected double price;
    protected int amount;
    protected boolean available;
    protected String name;
    protected String description;
    protected int imageResource;


    public  Product(){
        this.amount = 0;
        this.available = true;
        this.description = "";
        this.price = 0;
        this.name = "";
        this.imageResource = 0;
    }
    public Product(String name, String des, double price, int amount, boolean avlb){
        setAmount(amount);
        this.name = name;
        this.available = avlb;
        setPrice(price);
        this.description = des;
        this.imageResource = 0;
    }
    //public double calculatePrice(){
    //     return this.price * this.amount;
    // }

    public String getName(){return this.name;}

    public void setAmount(int amount) {
        if(amount < 0){
            throw new IllegalArgumentException("The amount cannot be negative");
        }
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setPrice(double price) {
        if (price < 0){
            throw new IllegalArgumentException("The price cant be nagative");
        }
        this.price = price;
    }
    public double getPrice(){return this.price;}

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource){
        if (imageResource < 0){
            throw new IllegalArgumentException("The imageResource cant be empty");
        }
        this.imageResource = imageResource;
    }
}

