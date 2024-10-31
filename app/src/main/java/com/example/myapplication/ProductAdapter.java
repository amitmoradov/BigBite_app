package com.example.myapplication; // שנה את שם החבילה לשם החבילה שלך

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;



import com.example.myapplication.Cart;
import com.example.myapplication.Product;

import java.io.File;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;  // המוצרים הזמינים לבחירה
    private Cart cart;  // הסל שמכיל את המוצרים שהמשתמש בחר

    public ProductAdapter(List<Product> productList, Cart cart) {
        this.productList = productList;
        this.cart = cart;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText("₪" + product.getPrice());
        holder.quantityTextView.setText(String.valueOf(product.getAmount()));
        Glide.with(holder.itemView.getContext())
                .load(product.getImageResource()) // כאן זה נטען ישירות מתוך ה-Resource ID
                .placeholder(R.drawable.bigbite) // תמונת placeholder
                .error(R.drawable.baget_beizim) // תמונת שגיאה
                .into(holder.productImage);

        // ניהול כמות
        holder.increaseButton.setOnClickListener(v -> {
            int quantity = Integer.parseInt(holder.quantityTextView.getText().toString()) + 1;
            holder.quantityTextView.setText(String.valueOf(quantity));
            product.setAmount(quantity); // עדכון כמות המוצר
        });

        holder.decreaseButton.setOnClickListener(v -> {
            int quantity = Integer.parseInt(holder.quantityTextView.getText().toString());
            if (quantity > 1) {
                quantity--;
                holder.quantityTextView.setText(String.valueOf(quantity));
                product.setAmount(quantity); // עדכון כמות המוצר
            }
        });

        holder.addToCartButton.setOnClickListener(v -> {
            cart.addProduct(product); // הוספת המוצר לסל
            Toast.makeText(holder.itemView.getContext(), product.getName() + " נוסף לסל", Toast.LENGTH_SHORT).show();
            Log.d("CartSize", "Cart size after adding product: " + cart.getCartItems());

        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice, quantityTextView;
        Button addToCartButton, increaseButton, decreaseButton;
        ImageView productImage;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
            increaseButton = itemView.findViewById(R.id.increaseQuantityButton);
            decreaseButton = itemView.findViewById(R.id.decreaseQuantityButton);
            productImage = itemView.findViewById(R.id.productImage);
        }
    }
}