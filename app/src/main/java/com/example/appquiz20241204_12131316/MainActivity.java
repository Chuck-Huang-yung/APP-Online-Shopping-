package com.example.appquiz20241204_12131316;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//主頁面管理，連接購物車按鈕也是在這裡連接
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<SceneInfo> sceneList = SceneInfo.getArrayListFromJsonFile(this);
        SceneAdapter adapter = new SceneAdapter(sceneList);
        recyclerView.setAdapter(adapter);

        ImageView cartButton = findViewById(R.id.cartButton);
      //  cartButton = findViewById(R.id.cartButton);

        cartButton.setOnClickListener(v -> {
            Toast.makeText(this, "購物車點擊", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });

    }

}






