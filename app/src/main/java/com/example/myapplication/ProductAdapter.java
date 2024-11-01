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
    private Cart cart = CartSingleton.getInstance();

    public ProductAdapter(List<Product> productList, Cart cart) {
        this.productList = productList;
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

        // ניהול הכמות בכפתור ה-`+`
        holder.increaseButton.setOnClickListener(v -> {
            int quantity = Integer.parseInt(holder.quantityTextView.getText().toString()) + 1;
            holder.quantityTextView.setText(String.valueOf(quantity));
        });

        // ניהול הכמות בכפתור ה-`-`
        holder.decreaseButton.setOnClickListener(v -> {
            int quantity = Integer.parseInt(holder.quantityTextView.getText().toString());
            if (quantity > 1) {
                quantity -= 1;
                holder.quantityTextView.setText(String.valueOf(quantity));
            }
        });

        // כפתור "הוסף לסל" מעדכן את הכמות בעגלה
        holder.addToCartButton.setOnClickListener(v -> {
            int quantity = Integer.parseInt(holder.quantityTextView.getText().toString());

            // if the product is not exist
            if (!cart.findProduct(product.getName())) {
                product.setAmount(quantity);
                cart.addProduct(product);

            }
            //if exist
            else {
                Product existingProduct = cart.getProduct(product.getName()); // if the product is exist
                existingProduct.setAmount(existingProduct.getAmount() + quantity); // update the amount
            }

            Toast.makeText(holder.itemView.getContext(), product.getName() + " נוסף לסל!", Toast.LENGTH_SHORT).show();
            holder.quantityTextView.setText(String.valueOf(1));

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