package com.example.myapplication;

public class Pasta extends Product{

    private String sauceType;         // סוג רוטב (עגבניות, שמנת)
    private String pastaType;         // סוג הפסטה (פנה, ספגטי)

    // Constructor
    public Pasta(String name, String descrip, double price, int quantity,boolean avlb,int image, String sauceType, String pastaType) {
        super(name,descrip, price, quantity,avlb);
        this.sauceType = sauceType;
        this.pastaType = pastaType;
        setImageResource(image);
    }

    // Getters and Setters
    public String getSauceType() { return sauceType; }
    public void setSauceType(String sauceType) { this.sauceType = sauceType; }

    public String getPastaType() { return pastaType; }
    public void setPastaType(String pastaType) { this.pastaType = pastaType; }
}
