package com.example.appquiz20241204_12131316;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    private TextView studentIdTextView, totalPriceTextView, successMessageTextView;
    private RecyclerView cartItemsRecyclerView;
    private CartItemAdapter cartItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        studentIdTextView = findViewById(R.id.studentIdTextView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        successMessageTextView = findViewById(R.id.successMessageTextView);
       // cartItemsRecyclerView = findViewById(R.id.cartItemsRecyclerView);
        // 获取传递过来的数据
        Intent intent = getIntent();
        String studentId = intent.getStringExtra("studentId");
        double totalPrice = intent.getDoubleExtra("totalPrice", 0.0);
       // ArrayList<CartItem> cartItems = intent.getParcelableArrayListExtra("cartItems");

        // 设置显示的内容
        studentIdTextView.setText("購買人: " + studentId);
        totalPriceTextView.setText("總金額: " + totalPrice);
        successMessageTextView.setText("結帳成功! 謝謝惠顧! ");

        //cartItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
       // cartItemAdapter = new CartItemAdapter(cartItems);
      //  cartItemsRecyclerView.setAdapter(cartItemAdapter);
    }
}

