package com.example.appquiz20241204_12131316;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


//負責將購物車中的商品數據綁定到 RecyclerView 的每個項目佈局上，並處理與購物車操作相關的事件（如增加、減少數量或刪除商品）。

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private ArrayList<CartItem> cartItems;
    private DatabaseHelper dbHelper;


    // 回調接口，用於通知 CartActivity 更新總金額
    public interface OnCartUpdateListener {
        void onCartUpdated();
    }

    private OnCartUpdateListener cartUpdateListener;

    public CartAdapter(Context context, ArrayList<CartItem> cartItems, DatabaseHelper dbHelper, OnCartUpdateListener cartUpdateListener) {
        this.context = context;
        this.cartItems = cartItems;
        this.dbHelper = dbHelper;
        this.cartUpdateListener = cartUpdateListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the layout for the individual cart items
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);

        // Set the product image, description, price, and quantity
        holder.descriptionTextView.setText(cartItem.getDescription());
        holder.priceTextView.setText("價格: " + cartItem.getPrice());

        holder.increaseButton.setOnClickListener(v -> {
            updateQuantity(cartItem, position, cartItem.getQuantity() + 1);
        });

        holder.decreaseButton.setOnClickListener(v -> {
            if (cartItem.getQuantity() > 0) {
                updateQuantity(cartItem, position, cartItem.getQuantity() - 1);
            }
//            else {
//                Toast.makeText(context, "數量不能少於1", Toast.LENGTH_SHORT).show();
//            }
        });

        // Remove item when delete button is clicked
        holder.deleteButton.setOnClickListener(v -> {
            dbHelper.deleteItemFromCart(cartItem.getId());
            cartItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartItems.size());
            notifyCartUpdated();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public void updateCartItems(ArrayList<CartItem> newCartItems) {
        this.cartItems = newCartItems;
        notifyDataSetChanged(); // 刷新适配器数据
    }

    public void clearItems() {
        if (cartItems != null) {
            cartItems.clear();  // Clear the list
            notifyDataSetChanged();  // Refresh the adapter data
        }
    }


    // Update quantity in the database and notify the adapter
    private void updateQuantity(CartItem cartItem, int position, int newQuantity) {
        dbHelper.updateItemQuantity(cartItem.getId(), newQuantity);
        cartItem.setQuantity(newQuantity);
        notifyItemChanged(position);
        notifyCartUpdated();
    }

    // Notify the CartActivity to update total price or any other changes
    private void notifyCartUpdated() {
        if (cartUpdateListener != null) {
            cartUpdateListener.onCartUpdated();
        }
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        // Declare UI components
        TextView descriptionTextView;
        TextView priceTextView;
        Button increaseButton;
        Button decreaseButton;
        Button deleteButton;

        public CartViewHolder(View itemView) {
            super(itemView);
            // Initialize UI components by finding them in the layout

            descriptionTextView = itemView.findViewById(R.id.cartItemDescription);
            priceTextView = itemView.findViewById(R.id.cartItemPrice);
            increaseButton = itemView.findViewById(R.id.increaseQuantityButton);
            decreaseButton = itemView.findViewById(R.id.decreaseQuantityButton);
            deleteButton = itemView.findViewById(R.id.removeCartItemButton);
        }
    }
}
