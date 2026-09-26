package com.example.appquiz20241204_12131316;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;


//將Json資料傳進我的頁面的最主要的地方
public class SceneInfo {

    private int id;
    private String description;
    private int imgId;
    private double price;
    private String infor;

    public SceneInfo(int id, String description, int imgId, double price, String infor) {
        this.id = id;
        this.description = description;
        this.imgId = imgId;
        this.price = price;
        this.infor = infor;
    }

    public SceneInfo(String description, int imgId, double price, String infor) {
        this.description = description;
        this.imgId = imgId;
        this.price = price;
        this.infor = infor;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public int getImgId() {
        return imgId;
    }

    public double getPrice() {
        return price;
    }

    public String getInfor() {
        return infor;
    }

    public void saveToDatabase(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        dbHelper.addItemToCart(this);
    }


    //從Json檔讀取資料，資料放在Assets當中
    public static ArrayList<SceneInfo> getArrayListFromJsonFile(Context context) {
        ArrayList<SceneInfo> sceneList = new ArrayList<>();
        try {
            InputStream inputStream = context.getAssets().open("sceneInfo_array.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject sceneObject = jsonArray.getJSONObject(i);
                String name = sceneObject.getString("name");
                double price = Double.parseDouble(sceneObject.getString("price"));
                String infor = sceneObject.getString("infor");
                String imgFile = sceneObject.getString("imgFile");
                int imgId = context.getResources().getIdentifier(imgFile, "drawable", context.getPackageName());

                if (imgId == 0) {
                    imgId = R.drawable.pic1;
                }

                SceneInfo sceneInfo = new SceneInfo(name, imgId, price, infor);
                sceneList.add(sceneInfo);

                sceneInfo.saveToDatabase(context);
            }
        } catch (Exception e) {
            Log.e("SceneInfo", "Error parsing JSON from assets", e);
        }

        return sceneList;
    }
}



