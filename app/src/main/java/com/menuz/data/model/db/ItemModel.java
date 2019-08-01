package com.menuz.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by mindiii on 23/10/18.
 */
@Entity(tableName = "item")

public class ItemModel implements Serializable {



    @ColumnInfo(name = "itemId")
    private String itemId;

    @NonNull
    public int getNewId() {
        return newId;
    }

    public void setNewId(@NonNull int newId) {
        this.newId = newId;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "newId")
    private int newId;





    @ColumnInfo(name = "itemName")
    private String itemName;

    @ColumnInfo(name = "itemGroupId")
    private String itemGroupId;

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
    }

    @ColumnInfo(name = "itemRemark")
    private String itemRemark;


    @ColumnInfo(name = "itemPrice")
    private String itemPrice;


    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    @ColumnInfo(name = "itemImage")
    private String itemImage;

    @ColumnInfo(name = "itemAddonPrice")
    private String itemAddonPrice;

    @ColumnInfo(name = "preparations")
    private String preparations;

    @ColumnInfo(name = "pricevalues")
    private String pricevalues;
    public  int itemImg;
    public  int itemImgActive;
    public boolean isSelect = false;



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
}
