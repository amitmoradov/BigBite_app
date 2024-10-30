package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MenuActivity extends AppCompatActivity {
    Cart cart = new Cart();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button addToCartButton = findViewById(R.id.addToCartButton);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // קבלת View של הפריט כולו
                View productView = (View) view.getParent().getParent();

                // שליפת שם המוצר
                TextView nameTextView = productView.findViewById(R.id.productName);
                String productName = nameTextView.getText().toString();

                // שליפת המחיר והמרתו למספר
                TextView priceTextView = productView.findViewById(R.id.productPrice);
                double price = Double.parseDouble(priceTextView.getText().toString().replace("₪", "").trim());

                // שליפת כמות
                TextView quantityTextView = productView.findViewById(R.id.quantityTextView);
                int quantity = Integer.parseInt(quantityTextView.getText().toString());

                // יצירת אובייקט של המוצר עם הפרטים הנוכחיים
                Product product = new Product(productName, "תיאור זמני", price, quantity, true);

                // הוספת המוצר לעגלה
                cart.addProduct(product);

                // הודעת אישור למשתמש
                Toast.makeText(MenuActivity.this, product.getName() + " התווסף להזמנה ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
