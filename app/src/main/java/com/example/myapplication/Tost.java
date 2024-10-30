package com.example.myapplication;

public class Tost extends Product{
    private String type;           // סוג הסלט (למשל, יווני, טבולה)

    // Constructor
    public Tost(String name,String descrip, double price, int quantity,boolean avlb, String type) {
        super(name, descrip, price, quantity, avlb);
        this.type = type;
        setId(1);
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
