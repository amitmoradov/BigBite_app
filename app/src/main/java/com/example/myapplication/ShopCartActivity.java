package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.Cart;

public class ShopCartActivity extends AppCompatActivity {

    private Cart cart = CartSingleton.getInstance(); // שימוש ב-Singleton
    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private TextView totalPriceTextView;
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        checkoutButton = findViewById(R.id.checkoutButton);

        // הגדרת RecyclerView
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(totalPriceTextView); // יצירת CartAdapter
        cartRecyclerView.setAdapter(cartAdapter);

        // עדכון סכום כולל
        updateTotalPrice();

        //payment button
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopCartActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateTotalPrice() {
        double total = cart.getCartTotal();
        totalPriceTextView.setText("סה״כ לתשלום: ₪" + total);
    }
}