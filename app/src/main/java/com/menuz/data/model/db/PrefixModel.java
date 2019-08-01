package com.menuz.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by mindiii on 26/10/18.
 */

@Entity(tableName = "prefix")
public class PrefixModel {




    public PrefixModel() {
    }

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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "prefixId")
    private String prefixId;

    @ColumnInfo(name = "prefixName")
    private String prefixName;

    @NonNull
    public String getPrefixId() {
        return prefixId;
    }

    public void setPrefixId(@NonNull String prefixId) {
        this.prefixId = prefixId;
    }

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public  int itemImg;
    public  int itemImgActive;
    public boolean isSelect = false;
    public String itemName;

}
