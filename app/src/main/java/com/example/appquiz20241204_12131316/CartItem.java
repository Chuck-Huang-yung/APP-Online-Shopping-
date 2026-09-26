package com.example.appquiz20241204_12131316;
import android.os.Parcel;
import android.os.Parcelable;



//用於存儲和管理購物車中每個商品的數據和操作邏輯。它通常包含商品的基本屬性（例如名稱、價格、數量等）
public class CartItem implements Parcelable {  //相比於 Java 的 Serializable，Parcelable 的效率更高
    private int id;                            //Parcelable 是一種序列化接口
    private String description;
    private double price;
    private int imageId;
    private int quantity;

    public CartItem(int id, String description, double price, int imageId, int quantity) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.imageId = imageId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return String.valueOf(price);
    }

    public int getImageId() {
        return imageId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    protected CartItem(Parcel in) {
        id = in.readInt();
        description = in.readString();
        price = in.readDouble();
        imageId = in.readInt();
        quantity = in.readInt();
    }
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(description);
        dest.writeDouble(price);
        dest.writeInt(imageId);
        dest.writeInt(quantity);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };
}
