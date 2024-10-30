package com.example.myapplication; // שנה את שם החבילה לשם החבילה שלך

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Cart;
import com.example.myapplication.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private static Cart cart;

    public ProductAdapter(Cart cart) {
        this.cart = cart;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_menu, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = cart.findProduct(position);
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText(String.format("%.2f ₪", product.getPrice()));
      //  holder.productImageView.setImageResource(product.getImageResource());
        holder.addButton.setOnClickListener(v -> {
            cart.addProduct(product);
            Toast.makeText(v.getContext(), "המוצר נוסף לסל!: " + product.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return cart.getTotalQuantity();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView, quantityTextView;
        ImageView productImageView;
        Button addButton, increaseButton, decreaseButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.productName);
            priceTextView = itemView.findViewById(R.id.productPrice);
            productImageView = itemView.findViewById(R.id.productImage);
            addButton = itemView.findViewById(R.id.addToCartButton);
            increaseButton = itemView.findViewById(R.id.increaseQuantityButton);
            decreaseButton = itemView.findViewById(R.id.decreaseQuantityButton);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);

            // הכנת הוספת כמות
            increaseButton.setOnClickListener(v -> {
                int currentQuantity = Integer.parseInt(quantityTextView.getText().toString());
                currentQuantity++;
                quantityTextView.setText(String.valueOf(currentQuantity));
            });

            // הכנת הפחתת כמות
            decreaseButton.setOnClickListener(v -> {
                int currentQuantity = Integer.parseInt(quantityTextView.getText().toString());
                if (currentQuantity > 1) { // מוודא שהכמות לא תהיה פחות מ-1
                    currentQuantity--;
                    quantityTextView.setText(String.valueOf(currentQuantity));
                }
            });

            // הכנת הוספת מוצר לסל
            addButton.setOnClickListener(v -> {
                String name = nameTextView.getText().toString();
                String description = "תיאור זמני"; // תיאור זמני
                double price = Double.parseDouble(priceTextView.getText().toString().replace("₪", "").trim());
                int quantity = Integer.parseInt(quantityTextView.getText().toString());
                boolean available = true;

                Product product = new Product(name, description, price, quantity, available);
                cart.addProduct(product);
                Toast.makeText(itemView.getContext(), "המוצר נוסף לסל!", Toast.LENGTH_SHORT).show();
            });
        }
    }

}
