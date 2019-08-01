package com.menuz.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.menuz.ui.Order.adapter.ServiceConverter;

import java.util.ArrayList;

/**
 * Created by mindiii on 29/10/18.
 */

/*@Entity(tableName = "order_item",
        foreignKeys = @ForeignKey(entity = OrderAddOnModel.class,
                parentColumns = "itemId",
                childColumns = "addOnItemID", onDelete = CASCADE),@ForeignKey(entity = OrderAddOnModel.class,
        parentColumns = "itemId",
        childColumns = "addOnItemID", onDelete = CASCADE))*/

@TypeConverters(ServiceConverter.class)
@Entity( tableName = "order_item")
public class OrderItemModel implements Parcelable {
    public int itemImg;
    public int itemImgActive;
    public boolean isSelect = false;

    @SerializedName("addOnItemID")
    @ColumnInfo(name = "itemId")
    private String itemId;
    @ColumnInfo(name = "itemName")
    private String itemName;
    @ColumnInfo(name = "itemGroupId")
    private String itemGroupId;
    @ColumnInfo(name = "itemPrice")
    private String itemPrice;
    @ColumnInfo(name = "itemRemark")
    private String itemRemark;
    @ColumnInfo(name = "itemPreprationRemark")
    private String itemPreprationRemark;
    @ColumnInfo(name = "isCartSelected")
    private boolean isCartSelected;
    @ColumnInfo(name = "itemAddonPrice")
    private String itemAddonPrice;
    @ColumnInfo(name = "preparations")
    private String preparations;
    @ColumnInfo(name = "grandTotal")
    private String grandTotal;
    @ColumnInfo(name = "countPrice")
    private String countPrice;
    @ColumnInfo(name = "pricevalues")
    private String pricevalues;

    @ColumnInfo(name = "OrderId")
    private String OrderId;

    public OrderItemModel() {

    }

    public OrderItemModel(Parcel in) {
        itemImg = in.readInt();
        itemImgActive = in.readInt();
        isSelect = in.readByte() != 0;
        itemId = in.readString();
        itemName = in.readString();
        itemGroupId = in.readString();
        itemPrice = in.readString();
        itemRemark = in.readString();
        itemPreprationRemark = in.readString();
        isCartSelected = in.readByte() != 0;
        itemAddonPrice = in.readString();
        preparations = in.readString();
        grandTotal = in.readString();
        countPrice = in.readString();
        pricevalues = in.readString();
        OrderId = in.readString();
        itemAutoID = in.readString();
        itemPrimaryKey = in.readString();
        totalamount = in.readString();
        price = in.readString();
        pricevalue = in.readString();
        nuofguest = in.readString();
        coursename = in.readString();
        itemImage = in.readString();
        arrayList = in.createStringArrayList();
        positionItem = in.readInt();
    }

    public static final Creator<OrderItemModel> CREATOR = new Creator<OrderItemModel>() {
        @Override
        public OrderItemModel createFromParcel(Parcel in) {
            return new OrderItemModel(in);
        }

        @Override
        public OrderItemModel[] newArray(int size) {
            return new OrderItemModel[size];
        }
    };

    public String getItemAutoID() {
        return itemAutoID;
    }

    public void setItemAutoID(String itemAutoID) {
        this.itemAutoID = itemAutoID;
    }

    @ColumnInfo(name = "itemAutoID")
    private String itemAutoID;


    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "itemPrimaryKey")
    private String itemPrimaryKey;


    @NonNull
    public String getItemPrimaryKey() {
        return itemPrimaryKey;
    }

    public void setItemPrimaryKey(@NonNull String itemPrimaryKey) {
        this.itemPrimaryKey = itemPrimaryKey;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    @Ignore
    private String totalamount="";

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPricevalue() {
        return pricevalue;
    }

    public void setPricevalue(String pricevalue) {
        this.pricevalue = pricevalue;
    }

    public String getNuofguest() {
        return nuofguest;
    }

    public void setNuofguest(String nuofguest) {
        this.nuofguest = nuofguest;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    @ColumnInfo(name = "price")
    private String price;
    @ColumnInfo(name = "pricevalue")
    private String pricevalue;
    @ColumnInfo(name = "nuofguest")
    private String nuofguest;
    @ColumnInfo(name = "coursename")
    private String coursename;

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    @ColumnInfo(name = "itemImage")
    private String itemImage;



    private ArrayList<String> arrayList;
    private int positionItem;

    public String getItemPreprationRemark() {
        return itemPreprationRemark;
    }

    public void setItemPreprationRemark(String itemPreprationRemark) {
        this.itemPreprationRemark = itemPreprationRemark;
    }

    public boolean isCartSelected() {
        return isCartSelected;
    }

    public void setCartSelected(boolean cartSelected) {
        isCartSelected = cartSelected;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
    }

    public String getCountPrice() {
        return countPrice;
    }

    public void setCountPrice(String countPrice) {
        this.countPrice = countPrice;
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }



    @NonNull
    public String getItemId() {
        return itemId;
    }

    public void setItemId(@NonNull String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemGroupId() {
        return itemGroupId;
    }

    public void setItemGroupId(String itemGroupId) {
        this.itemGroupId = itemGroupId;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemAddonPrice() {
        return itemAddonPrice;
    }

    public void setItemAddonPrice(String itemAddonPrice) {
        this.itemAddonPrice = itemAddonPrice;
    }

    public String getPreparations() {
        return preparations;
    }

    public void setPreparations(String preparations) {
        this.preparations = preparations;
    }

    public String getPricevalues() {
        return pricevalues;
    }

    public void setPricevalues(String pricevalues) {
        this.pricevalues = pricevalues;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

    public int getPositionItem() {
        return positionItem;
    }

    public void setPositionItem(int positionItem) {
        this.positionItem = positionItem;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(itemImg);
        dest.writeInt(itemImgActive);
        dest.writeByte((byte) (isSelect ? 1 : 0));
        dest.writeString(itemId);
        dest.writeString(itemName);
        dest.writeString(itemGroupId);
        dest.writeString(itemPrice);
        dest.writeString(itemRemark);
        dest.writeString(itemPreprationRemark);
        dest.writeByte((byte) (isCartSelected ? 1 : 0));
        dest.writeString(itemAddonPrice);
        dest.writeString(preparations);
        dest.writeString(grandTotal);
        dest.writeString(countPrice);
        dest.writeString(pricevalues);
        dest.writeString(OrderId);
        dest.writeString(itemAutoID);
        dest.writeString(itemPrimaryKey);
        dest.writeString(totalamount);
        dest.writeString(price);
        dest.writeString(pricevalue);
        dest.writeString(nuofguest);
        dest.writeString(coursename);
        dest.writeString(itemImage);
        dest.writeStringList(arrayList);
        dest.writeInt(positionItem);
    }
}
