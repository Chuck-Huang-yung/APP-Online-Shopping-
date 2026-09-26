package com.example.appquiz20241204_12131316;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


//SQLite執行的地方，把我Json檔的資料傳到我購物車當中，以及執行方法的內部程式
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shop.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CART = "cart";
    private static final String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "description TEXT, " +
            "price TEXT, " +
            "imageId INTEGER, " +
            "quantity INTEGER)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }

    // 新增商品至購物車
    public void addItemToCart(SceneInfo sceneInfo) {
        SQLiteDatabase dbWritable = this.getWritableDatabase();

        // 檢查是否已存在相同商品
        Cursor cursor = dbWritable.query(TABLE_CART, null, "description = ?", new String[]{sceneInfo.getDescription()}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            // 如果商品已存在，增加數量
            int currentQuantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));
            updateItemQuantity(cursor.getInt(cursor.getColumnIndexOrThrow("id")), currentQuantity + 1);
            cursor.close();
        } else {
            // 如果商品不存在，新增
            ContentValues values = new ContentValues();
            values.put("description", sceneInfo.getDescription());
            values.put("price", sceneInfo.getPrice());
            values.put("imageId", sceneInfo.getImgId());
            values.put("quantity", 0); // 預設數量為 0
            dbWritable.insert(TABLE_CART, null, values);
        }
    }

    // 更新數量
    public void updateItemQuantity(int itemId, int quantity) {
        SQLiteDatabase dbWritable = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("quantity", quantity);
        dbWritable.update(TABLE_CART, values, "id = ?", new String[]{String.valueOf(itemId)});
    }

    // 刪除商品
    public void deleteItemFromCart(int itemId) {
        SQLiteDatabase dbWritable = this.getWritableDatabase();
        dbWritable.delete(TABLE_CART, "id = ?", new String[]{String.valueOf(itemId)});
    }

    // 獲取購物車所有商品
    public ArrayList<CartItem> getAllCartItems() {
        ArrayList<CartItem> cartItems = new ArrayList<>();
        SQLiteDatabase dbReadable = this.getReadableDatabase();
        Cursor cursor = dbReadable.query(TABLE_CART, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                String priceString = cursor.getString(cursor.getColumnIndexOrThrow("price"));
                int imageId = cursor.getInt(cursor.getColumnIndexOrThrow("imageId"));
                int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));

                // 將 price 字符串轉換為 double
                double price = 0;
                try {
                    price = Double.parseDouble(priceString);
                } catch (NumberFormatException e) {
                    e.printStackTrace(); // 當 price 無法轉換為數字時，默認為 0
                }

                CartItem cartItem = new CartItem(id, description, price, imageId, quantity);
                cartItems.add(cartItem);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return cartItems;
    }

    // 獲取購物車總金額
    public double getTotalPrice() {
        SQLiteDatabase dbReadable = this.getReadableDatabase();
        double totalPrice = 0;

        Cursor cursor = dbReadable.rawQuery("SELECT price, quantity FROM " + TABLE_CART, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String priceString = cursor.getString(cursor.getColumnIndexOrThrow("price"));
                int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));

                // 將 price 字符串轉換為 double
                double price = 0;
                try {
                    price = Double.parseDouble(priceString);
                } catch (NumberFormatException e) {
                    e.printStackTrace(); // 當 price 無法轉換為數字時，默認為 0
                }

                totalPrice += price * quantity;
            } while (cursor.moveToNext());
            cursor.close();
        }

        return totalPrice;
    }

    // 清空購物車
    public void clearCart() {
        SQLiteDatabase dbWritable = this.getWritableDatabase();
        dbWritable.delete(TABLE_CART, null, null);
    }
}





