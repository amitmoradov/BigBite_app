package com.example.myapplication;

import com.example.myapplication.model.Cart;

/**The CartSingleton class creates a design structure
 *  called a Singleton. This structure ensures that there is only a single instance of the entire application lifecycle, and its advantages are**/
public class CartSingleton {

    private static Cart cartInstance = null;

    // private constructor to prevent the creation of new instances
    private CartSingleton() {}

    //return the one show of the cart
    public static Cart getInstance() {
        if (cartInstance == null) {
            cartInstance = new Cart();
        }
        return cartInstance;
    }
}
