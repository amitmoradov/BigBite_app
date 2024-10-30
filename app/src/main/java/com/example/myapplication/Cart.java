package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    protected List<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product){
        products.add(product);
    }
    public void removeProduct(Product product){
        if(products.isEmpty()){
            throw new IllegalArgumentException("The DataBase is Empty");
        }
        products.remove(product); // remove the first element that found
    }

    public double getCartTotal(){
        double sum = 0;
        for (Product product : products) {
            sum += product.getPrice() * product.getAmount();
        }
        return sum;
    }

    public Product findProduct(String name){
        if(!products.isEmpty()) {
            for (Product product : products) {
                if (product.getName().equals(name)) {
                    return product;
                }
            }
        }
        throw new IllegalArgumentException("The Product is not exist");
    }

    public Product findProduct(int id){
        if(!products.isEmpty()) {
            for (Product product : products) {
                if (product.getId() == id) {
                    return product;
                }
            }
        }
        throw new IllegalArgumentException("The Product is not exist");
    }

    // הצגת כמות המוצרים בסל
    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (Product product : products) {
            totalQuantity += product.getAmount();
        }
        return totalQuantity;
    }

    public List<Product> getCartItems() {
        return new ArrayList<>(products); // מחזיר רשימה חדשה כדי למנוע שינויים ברשימה המקורית
    }

    public void clearCart(){
        products.clear();
    }

}
