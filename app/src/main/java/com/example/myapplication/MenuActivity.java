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

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    private Cart cart = new Cart();  // ניהול המוצרים שנבחרו לסל
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
        availableProducts.add(new Tost("טוסט יווני", "תיאור זמני", 28, 1, true,"יווני"));
        availableProducts.add(new Tost("טוסט חביתה", "תיאור זמני", 28, 1, true,"חביתה"));
        availableProducts.add(new Tost("באגט ביצים", "תיאור זמני", 28, 1, true,"ביצים"));
        availableProducts.add(new Tost("באגט בולגרית", "תיאור זמני", 28, 1, true,"בולגרית"));
        availableProducts.add(new Tost("באגט חביתה", "תיאור זמני", 28, 1, true,"חביתה"));
        availableProducts.add(new Tost("באגט טונה", "תיאור זמני", 28, 1, true,"טונה"));

        //PIZZA
        availableProducts.add(new Pizza("פיצה רגילה", "תיאור זמני", 80, 1, true,"משפחתית"));
        availableProducts.add(new Pizza("פיצה זיתים", "תיאור זמני", 85, 1, true,"משפחתית"));
        availableProducts.add(new Pizza("קלזון", "תיאור זמני", 20, 1, true,"אישי"));
        availableProducts.add(new Pizza("פרצל", "תיאור זמני", 20, 1, true,"אישי"));
        availableProducts.add(new Pizza("לחם שום", "תיאור זמני", 15, 1, true,"אישי"));

        //PASTA
        availableProducts.add(new Pasta("פנה שמנת", "תיאור זמני", 32, 1, true,"שמנת","פנה"));
        availableProducts.add(new Pasta("פנה עגבניות", "תיאור זמני", 32, 1, true,"עגבניות","פנה"));
        availableProducts.add(new Pasta("לזניה", "תיאור זמני", 32, 1, true,"עגבניות","לזניה"));

        //SALAD
        availableProducts.add(new Salat("סלט יווני קטן", "תיאור זמני", 30, 1, true,"קטן","יווני"));
        availableProducts.add(new Salat("סלט טונה קטן", "תיאור זמני", 30, 1, true,"קטן","טונה"));
        availableProducts.add(new Salat("סלט ביצים קטן", "תיאור זמני", 30, 1, true,"קטן","ביצים"));
        availableProducts.add(new Salat("סלט ביצים גדול", "תיאור זמני", 50, 1, true,"גדול","ביצים"));
        availableProducts.add(new Salat("סלט טונה קטן", "תיאור זמני", 50, 1, true,"גדול","ביצים"));
        availableProducts.add(new Salat("סלט טונה גדול", "תיאור זמני", 50, 1, true,"גדול","יווני"));


        // הוסף עוד מוצרים אם צריך

        // יצירת ה-Adapter עם רשימת המוצרים הזמינים, והעברת ה-Cart לשימוש
        productAdapter = new ProductAdapter(availableProducts, cart);
        recyclerView.setAdapter(productAdapter);
    }
}