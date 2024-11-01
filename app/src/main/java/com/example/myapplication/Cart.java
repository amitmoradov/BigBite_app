package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    protected List<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product){
        if(!findProduct(product.getName())) {
            products.add(product);
        }
        else {
            product.setAmount(product.getAmount()+1);
        }

    }

    //remove the product from the list. if have more 1 amount so amount--
    public void removeProduct(Product product){
        if(products.isEmpty()){
            throw new IllegalArgumentException("The DataBase is Empty");
        }

        if(findProduct(product.getName())) {
            if(product.getAmount() > 1){
                product.setAmount(product.getAmount()-1);
            }
            else {
                products.remove(product);
            }
        }
    }

    public double getCartTotal(){
        double sum = 0;
        for (Product product : products) {
            sum += product.getPrice() * product.getAmount();
        }
        return sum;
    }


    public boolean findProduct(String name){
        if(!products.isEmpty()) {
            for (Product product : products) {
                if (product.getName().equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Product getProduct(String name){
        if(!products.isEmpty()) {
            for (Product product : products) {
                if (product.getName().equals(name)) {
                    return product;
                }
            }
        }
        throw new IllegalArgumentException("The Product is not in DataBase");
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
        List<Product> productList = new ArrayList<>();
        for (int i =0; i < products.size(); i++) {
           productList.add( products.get(i));
        }
        return productList; // מחזיר רשימה חדשה כדי למנוע שינויים ברשימה המקורית
    }

    public void clearCart(){
        products.clear();
    }

}
