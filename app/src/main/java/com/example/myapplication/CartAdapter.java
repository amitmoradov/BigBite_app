package com.example.myapplication;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    private Cart cart = CartSingleton.getInstance();

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cart.getCartItems().get(position);
        holder.cartItemName.setText(product.getName());
        holder.cartItemPrice.setText("₪" + product.getPrice());
        holder.cartItemQuantity.setText("כמות: " + product.getAmount());
        holder.cartItemImage.setImageResource(product.getImageResource());

        // כפתור להסרת פריט מהעגלה
        holder.removeFromCartButton.setOnClickListener(v -> {
            cart.removeProduct(product);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
            Toast.makeText(holder.itemView.getContext(), product.getName() + " הוסר מהעגלה", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return cart.getCartItems().size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView cartItemName, cartItemPrice, cartItemQuantity;
        ImageView cartItemImage;
        Button removeFromCartButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartItemName = itemView.findViewById(R.id.cartItemName);
            cartItemPrice = itemView.findViewById(R.id.cartItemPrice);
            cartItemQuantity = itemView.findViewById(R.id.cartItemQuantity);
            cartItemImage = itemView.findViewById(R.id.cartItemImage);
            removeFromCartButton = itemView.findViewById(R.id.removeFromCartButton);
        }
    }
}
