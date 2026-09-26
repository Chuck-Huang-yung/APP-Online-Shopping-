package com.example.appquiz20241204_12131316;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


//設置我主頁面的商品瀏覽，例如點進去的功能和商品文字、圖片顯示
public class SceneAdapter extends RecyclerView.Adapter<SceneAdapter.ViewHolder> {

    private ArrayList<SceneInfo> listdata;


    public SceneAdapter(ArrayList<SceneInfo> listdata) {
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public SceneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull SceneAdapter.ViewHolder holder, int position) {
        final SceneInfo sceneInfo = listdata.get(position);
        Log.d("SceneAdapter", "Image ID: " + sceneInfo.getImgId());

        // 顯示或隱藏 topTextView
     //   if (position == 0) {
      //      holder.topTextView.setVisibility(View.VISIBLE);
     //   } else {
      //      holder.topTextView.setVisibility(View.GONE);
      //  }

        holder.textView.setText(sceneInfo.getDescription());
        holder.priceTextView.setText("價格: " + sceneInfo.getPrice());
        holder.imageView.setImageResource(sceneInfo.getImgId());

        // 設置 cartButton 的點擊事件
     /*   holder.cartButton.setOnClickListener(v -> {
            SQLiteDatabase db = new DatabaseHelper(v.getContext()).getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("description", sceneInfo.getDescription());
            values.put("price", sceneInfo.getPrice());
            values.put("imageId", sceneInfo.getImgId());
            db.insert("cart", null, values);
            Toast.makeText(v.getContext(), sceneInfo.getDescription() + " 已加入購物車", Toast.LENGTH_SHORT).show();

            // 跳轉到購物車頁面
            Intent intent = new Intent(v.getContext(), CartActivity.class);
            intent.putExtra("product_description", sceneInfo.getDescription());
            intent.putExtra("product_image", sceneInfo.getImgId());
            intent.putExtra("product_price", sceneInfo.getPrice());
            v.getContext().startActivity(intent);
        });
*/
        // 設置 relativeLayout 的點擊事件
        holder.relativeLayout.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), SecondActivity.class);
            intent.putExtra("product_description", sceneInfo.getDescription());
            intent.putExtra("product_image", sceneInfo.getImgId());
            intent.putExtra("product_price", sceneInfo.getPrice());
            intent.putExtra("product_infor", sceneInfo.getInfor());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public TextView priceTextView;
        public RelativeLayout relativeLayout;
       // public TextView topTextView;
        public ImageView cartButton;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
           // topTextView = itemView.findViewById(R.id.topTextView);
            cartButton = itemView.findViewById(R.id.cartButton);


            if (cartButton == null) {
                Log.e("SceneAdapter", "cartButton is null!");
            } else {
                Log.d("SceneAdapter", "cartButton initialized successfully");
            }
        }
    }
}
