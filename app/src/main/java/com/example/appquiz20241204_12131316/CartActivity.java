package com.example.appquiz20241204_12131316;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

     //   老師您好! 我是12131316 黃俊洋
          //主要操作購物車的地方
    public class CartActivity extends AppCompatActivity {

        private RecyclerView recyclerView;
        private CartAdapter cartAdapter;
        private DatabaseHelper dbHelper;
        private TextView totalPriceTextView;
        private Button backButton, checkoutButton;

        //Bundle傳資料(金額、商品)進購物車
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cart);

            dbHelper = new DatabaseHelper(this);
            //   dbHelper.clearCart();
            recyclerView = findViewById(R.id.recyclerView);
            totalPriceTextView = findViewById(R.id.totalPriceTextView);
            backButton = findViewById(R.id.backButton);
            checkoutButton = findViewById(R.id.checkoutButton);


            //resetCartItemsQuantity();
            loadCartItems();


            // 設置 RecyclerView
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            // 更新總金額
            updateTotalPrice();

           ArrayList<CartItem> cartItems = dbHelper.getAllCartItems();
            cartAdapter = new CartAdapter(this, cartItems, dbHelper, this::updateTotalPrice);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
           recyclerView.setAdapter(cartAdapter);

            updateTotalPrice();

            if (cartItems.isEmpty()) {
                Toast.makeText(this, "Your cart is empty.", Toast.LENGTH_SHORT).show();
            }

            backButton.setOnClickListener(v -> finish());  // 返回上一页

            // 设置结账按钮的点击事件
            checkoutButton.setOnClickListener(v -> {
           //     ArrayList<CartItem> cartItems = dbHelper.getAllCartItems();
                if (dbHelper.getAllCartItems().isEmpty()) {
                    Toast.makeText(this, "购物车为空，无法结账！", Toast.LENGTH_SHORT).show();
                } else {
                    // 结账成功，清空购物车并更新界面

                    String studentId = "12131316-黃俊洋"; // 假设你的学生 ID 是固定的
                    double totalPrice = dbHelper.getTotalPrice();

                    Toast.makeText(this, "结账成功！", Toast.LENGTH_SHORT).show();
                    dbHelper.clearCart(); // 清空购物车
                    loadCartItems(); // 刷新购物车显示
                    updateTotalPrice(); // 更新总金额

                    //ArrayList<CartItem> cartItems = dbHelper.getAllCartItems();

                   // ArrayList<CartItem> selectedItems = new ArrayList<>();
                //    for (CartItem item : cartItems) {
                  //      if (item.getQuantity() > 0) {
                   //         selectedItems.add(item); // 只将已选商品传递过去
                   //     }
                   // }

                    Intent intent = new Intent(this, CheckoutActivity.class);
                    intent.putExtra("studentId", studentId);
                    intent.putExtra("totalPrice", totalPrice);
                  //  intent.putParcelableArrayListExtra("cartItems", cartItems);
                    startActivity(intent);
                }
            });
        }

        //一旦結帳就會清空購物車數據
        private void loadCartItems() {
            ArrayList<CartItem> cartItems = dbHelper.getAllCartItems();
            cartAdapter = new CartAdapter(this, cartItems, dbHelper, this::updateTotalPrice);
            recyclerView.setAdapter(cartAdapter);

            if (cartItems.isEmpty()) {
                Toast.makeText(this, "您的購物車為空。", Toast.LENGTH_SHORT).show();
            }
        }




        private void resetCartItemsQuantity() {
            ArrayList<CartItem> cartItems = dbHelper.getAllCartItems();
            for (CartItem item : cartItems) {
                if (item.getQuantity() > 0) {
                    // 将商品数量设置为零
                    dbHelper.updateItemQuantity(item.getId(), 0);
                }
            }
        }

        private void updateTotalPrice() {
            double totalPrice = dbHelper.getTotalPrice();
            totalPriceTextView.setText("總金額: " + totalPrice);
        }

        @Override
        protected void onResume() {
            super.onResume();
            // 當返回此頁面時重新計算總金額
            loadCartItems();
            updateTotalPrice();
        }


    }


