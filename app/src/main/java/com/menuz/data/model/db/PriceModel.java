package com.menuz.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by mindiii on 20/12/18.
 */

@Entity(tableName = "price")

public class PriceModel {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "pricelistId")
    private String pricelistId;

    @NonNull
    public String getPricelistId() {
        return pricelistId;
    }

    public void setPricelistId(@NonNull String pricelistId) {
        this.pricelistId = pricelistId;
    }

    public String getPricelistName() {
        return pricelistName;
    }

    public void setPricelistName(String pricelistName) {
        this.pricelistName = pricelistName;
    }

    @ColumnInfo(name = "pricelistName")
    private String pricelistName;

    private String pricevaluesPrice="";

    public String getPricevaluesPrice() {
        return pricevaluesPrice;
    }

    public void setPricevaluesPrice(String pricevaluesPrice) {
        this.pricevaluesPrice = pricevaluesPrice;
    }

    public  int itemImg;

    public int getItemImg() {
        return itemImg;
    }

    public void setItemImg(int itemImg) {
        this.itemImg = itemImg;
    }

    public int getItemImgActive() {
        return itemImgActive;
    }

    public void setItemImgActive(int itemImgActive) {
        this.itemImgActive = itemImgActive;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public  int itemImgActive;
    public boolean isSelect = false;

}
