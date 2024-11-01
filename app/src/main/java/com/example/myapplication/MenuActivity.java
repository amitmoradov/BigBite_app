package com.example.myapplication;

import android.content.Intent;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    private Cart cart = CartSingleton.getInstance();
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> availableProducts; // רשימת המוצרים הזמינים להצגה

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // הגדרת המוצרים הזמינים להצגה (לא לסל)
        availableProducts = new ArrayList<>();
        //TOST
        availableProducts.add(new Tost("טוסט יווני", "תיאור זמני", 28, 1, true,
                R.drawable.tost_yevani, "יווני"));
        availableProducts.add(new Tost("טוסט הבית", "תיאור זמני", 28, 1, true,
                R.drawable.tost_hbait, "הבית"));

        availableProducts.add(new Tost("טוסט חביתה", "תיאור זמני", 28, 1, true,
                R.drawable.baget_havita, "חביתה"));
        availableProducts.add(new Tost("באגט ביצים", "תיאור זמני", 28, 1, true,
                R.drawable.baget_beizim, "ביצים"));
        availableProducts.add(new Tost("באגט בולגרית", "תיאור זמני", 28, 1, true,
                R.drawable.baget_bulgarit, "בולגרית"));

        availableProducts.add(new Tost("באגט חביתה", "תיאור זמני", 28, 1, true,
                R.drawable.baget_havita, "חביתה"));
        availableProducts.add(new Tost("באגט טונה", "תיאור זמני", 28, 1, true,
                R.drawable.baget_tuna, "טונה"));


        //PIZZA
        availableProducts.add(new Pizza("פיצה רגילה", "תיאור זמני", 80, 1, true, R.drawable.pizza, "משפחתית"));
        availableProducts.add(new Pizza("פיצה זיתים", "תיאור זמני", 85, 1, true, R.drawable.pizza_olives, "משפחתית"));
        availableProducts.add(new Pizza("קלזון", "תיאור זמני", 20, 1, true, R.drawable.kalazon, "אישי"));
        availableProducts.add(new Pizza("פרצל", "תיאור זמני", 20, 1, true, R.drawable.prezel, "אישי"));
        availableProducts.add(new Pizza("לחם שום", "תיאור זמני", 15, 1, true, R.drawable.lehem_shum, "אישי"));

        //PASTA
        availableProducts.add(new Pasta("פנה שמנת", "תיאור זמני", 32, 1, true, R.drawable.pene, "שמנת", "פנה"));
        availableProducts.add(new Pasta("פנה עגבניות", "תיאור זמני", 32, 1, true, R.drawable.pene_agvanihot, "עגבניות", "פנה"));
        availableProducts.add(new Pasta("לזניה", "תיאור זמני", 32, 1, true, R.drawable.lazanya, "עגבניות", "לזניה"));

        //SALAD
        availableProducts.add(new Salat("סלט יווני קטן", "תיאור זמני", 30, 1, true, R.drawable.salat_katan_yevani, "קטן", "יווני"));
        availableProducts.add(new Salat("סלט טונה קטן", "תיאור זמני", 30, 1, true, R.drawable.salat_katan_tuna, "קטן", "טונה"));
        availableProducts.add(new Salat("סלט ביצים קטן", "תיאור זמני", 30, 1, true, R.drawable.salat_katan_beizim, "קטן", "ביצים"));
        availableProducts.add(new Salat("סלט בטטה גדול", "תיאור זמני", 50, 1, true, R.drawable.salat_batata, "גדול", "בטטה"));
        availableProducts.add(new Salat("סלט טונה גדול", "תיאור זמני", 50, 1, true, R.drawable.salat_tuna, "גדול", "טונה"));
        availableProducts.add(new Salat("סלט יווני גדול", "תיאור זמני", 50, 1, true, R.drawable.salat_yevani, "גדול", "יווני"));


        // הוסף עוד מוצרים אם צריך

        // יצירת ה-Adapter עם רשימת המוצרים הזמינים, והעברת ה-Cart לשימוש
        productAdapter = new ProductAdapter(availableProducts, cart);
        recyclerView.setAdapter(productAdapter);

        FloatingActionButton shop_cart_btn = findViewById(R.id.addToCartButton);
        shop_cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // מעבר ל-ShopCartActivity עם cart
                Intent intent = new Intent(MenuActivity.this, ShopCartActivity.class);
                intent.putExtra("cart", cart); // הוסף את cart ל-Intent
                startActivity(intent);
            }
        });
    }
}