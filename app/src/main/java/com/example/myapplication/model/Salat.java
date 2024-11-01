package com.example.myapplication.model;

public class Salat extends Product {

    private String size;           // גודל הסלט (קטן או גדול)
    private String type;           // סוג הסלט (למשל, יווני, טבולה)

    // Constructor
    public Salat(String name,String descrip, double price, int quantity,boolean avlb, int image, String size, String type) {
        super(name, descrip, price, quantity, avlb);
        this.size = size;
        this.type = type;
        setImageResource(image);
    }

    // Getters and Setters
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
