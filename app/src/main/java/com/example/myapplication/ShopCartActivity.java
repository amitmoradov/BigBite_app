package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShopCartActivity extends AppCompatActivity {

    private Cart cart = CartSingleton.getInstance();
    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private TextView totalPriceTextView;
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart);

        cart = (Cart) getIntent().getSerializableExtra("cart");

        if (cart == null) {
            cart = new Cart();
        }
        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartAdapter = new CartAdapter();
        cartRecyclerView.setAdapter(cartAdapter);
        Log.d("CartItems", "Number of items in cart: " + cart.getTotalQuantity());

        updateTotalPrice();
        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        checkoutButton = findViewById(R.id.checkoutButton);
        // הגדרת כפתור התשלום
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // כאן ניתן להוסיף את הפעולה למעבר לתשלום, כגון Intent למסך תשלום
                // לדוגמה:
                // Intent intent = new Intent(ShopCartActivity.this, PaymentActivity.class);
                // startActivity(intent);
            }
        });

    }
    private void updateTotalPrice() {
        double total = cart.getCartTotal();  // קבלת סך התשלום מהעגלה
        totalPriceTextView.setText("סה״כ לתשלום: ₪" + total);
    }
}