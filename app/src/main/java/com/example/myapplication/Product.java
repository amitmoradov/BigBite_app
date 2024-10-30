package com.example.myapplication;

public class Product {

    protected double price;
    protected int amount;
    protected boolean available;
    protected String name;
    protected String description;
    protected int id;
    public  Product(){
        this.amount = 0;
        this.available = true;
        this.description = "";
        this.price = 0;
        this.name = "";
        this.id = 0;
    }
    public Product(String name, String des, double price, int amount, boolean avlb){
        setAmount(amount);
        this.name = name;
        this.available = avlb;
        setPrice(price);
        this.description = des;
        this.id = 0;
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

    public void setId(int id){
        if (id < 0){
            throw new IllegalArgumentException("The id cant be nagative");
        }
        this.id = id;
    }
    public int getId(){return this.id;}

    public void setPrice(double price) {
        if (price < 0){
            throw new IllegalArgumentException("The price cant be nagative");
        }
        this.price = price;
    }
    public double getPrice(){return this.price;}
}

