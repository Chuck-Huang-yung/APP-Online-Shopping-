package com.example.appquiz20241204_12131316;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//設置我商品從主頁面點進去的商品介紹和進入購物車的功能，都在這裡執行，也需使用到Bundle

public class SecondActivity extends AppCompatActivity {

    private TextView descriptionTextView;
    private ImageView productImageView;
    private TextView priceTextView;
    private Button addToCartButton;
    private TextView studentInfoTextView;
    private TextView inforTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



       // productImageView = findViewById(R.id.productImageView);
       // Button selectImageButton = findViewById(R.id.selectImageButton);

       // selectImageButton.setOnClickListener(v -> {
        //    Toast.makeText(SecondActivity.this, "此處未設置圖片資源", Toast.LENGTH_SHORT).show();
       // });
   // }

        descriptionTextView = findViewById(R.id.descriptionTextView);
        productImageView = findViewById(R.id.productImageView);
        priceTextView = findViewById(R.id.priceTextView);
        addToCartButton = findViewById(R.id.addToCartButton);
        studentInfoTextView = findViewById(R.id.studentInfoTextView);
        inforTextView = findViewById(R.id.inforTextView);
        //Button selectImageButton = findViewById(R.id.selectImageButton);


        Intent intent = getIntent();
        String productDescription = intent.getStringExtra("product_description");
        int productImage = intent.getIntExtra("product_image", 0);
        double productPrice = intent.getDoubleExtra("product_price", 0.0);  // 直接接收 double，並設置默認值 0.0
        String productInfor = intent.getStringExtra("product_infor");

       // double productPrice = 0.0;
        //try {
         //   productPrice = Double.parseDouble(productPriceStr);
        //} catch (NumberFormatException e) {
          //  e.printStackTrace();
            //productPrice = 0.0;// 若轉換失敗，設置默認值 0.0
        //}


        descriptionTextView.setText(productDescription);
        if (productImage != 0) {
            productImageView.setImageResource(productImage);
            productImageView.setVisibility(View.VISIBLE);
        } else {
            productImageView.setVisibility(View.GONE);
        }
        priceTextView.setText("價格: " + productPrice);
        inforTextView.setText("商品信息: " + productInfor);

        String studentInfo = "12131316-黃俊洋";
        studentInfoTextView.setText(studentInfo);

//        selectImageButton.setOnClickListener(v ->
//                Toast.makeText(SecondActivity.this, "此處未設置圖片資源", Toast.LENGTH_SHORT).show()
//        );

        addToCartButton.setOnClickListener(v -> {
            DatabaseHelper dbHelper = new DatabaseHelper(SecondActivity.this);
            SceneInfo scene = new SceneInfo(productDescription, productImage, productPrice, productInfor);
            dbHelper.addItemToCart(scene);

            Toast.makeText(SecondActivity.this, productDescription + "加入購物車選購中!", Toast.LENGTH_SHORT).show();
            Intent cartIntent = new Intent(SecondActivity.this, CartActivity.class);
            startActivity(cartIntent);

            finish();
        });
    }

    public void onBackButtonClick(View view) {
        finish();
    }
}




