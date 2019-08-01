package com.menuz.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by mindiii on 29/10/18.
 */
@Entity (tableName = "order_addon")
public class OrderAddOnModel {
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

    @ColumnInfo(name = "addonsGroupPriId")
    private int addonsGroupPriId;

    @NonNull
    public int getAddonIdN() {
        return addonIdN;
    }

    public void setAddonIdN(@NonNull int addonIdN) {
        this.addonIdN = addonIdN;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "addonIdN")
    private int addonIdN;



    @ColumnInfo(name = "addonsGroupId")
    private String addonsGroupId;

    @ColumnInfo(name = "addonsGroupName")
    private String addonsGroupName;

    @ColumnInfo(name = "addonsGroupPlace")
    private String addonsGroupPlace;

    @ColumnInfo(name = "addonsGroupIsMandatory")
    private String addonsGroupIsMandatory;


    @ColumnInfo(name = "addonsGroupEnh")
    private String addonsGroupEnh;

    @ColumnInfo(name = "addonsGroupMax")
    private String addonsGroupMax;

    public String getAddonsGroupMax() {
        return addonsGroupMax;
    }

    public void setAddonsGroupMax(String addonsGroupMax) {
        this.addonsGroupMax = addonsGroupMax;
    }

    @ColumnInfo(name = "chProperty")
    private String chProperty;

    @ColumnInfo(name = "addOnItemID")
    private String addOnItemID;

    public int getAddonsGroupPriId() {
        return addonsGroupPriId;
    }

    public void setAddonsGroupPriId( int addonsGroupPriId) {
        this.addonsGroupPriId = addonsGroupPriId;
    }

    public String getAddonsGroupId() {
        return addonsGroupId;
    }

    public void setAddonsGroupId(String addonsGroupId) {
        this.addonsGroupId = addonsGroupId;
    }

    public String getAddonsGroupName() {
        return addonsGroupName;
    }

    public void setAddonsGroupName(String addonsGroupName) {
        this.addonsGroupName = addonsGroupName;
    }

    public String getAddonsGroupPlace() {
        return addonsGroupPlace;
    }

    public void setAddonsGroupPlace(String addonsGroupPlace) {
        this.addonsGroupPlace = addonsGroupPlace;
    }

    public String getAddonsGroupIsMandatory() {
        return addonsGroupIsMandatory;
    }

    public void setAddonsGroupIsMandatory(String addonsGroupIsMandatory) {
        this.addonsGroupIsMandatory = addonsGroupIsMandatory;
    }

    public String getAddonsGroupEnh() {
        return addonsGroupEnh;
    }

    public void setAddonsGroupEnh(String addonsGroupEnh) {
        this.addonsGroupEnh = addonsGroupEnh;
    }

    public String getChProperty() {
        return chProperty;
    }

    public void setChProperty(String chProperty) {
        this.chProperty = chProperty;
    }

    public String getAddOnItemID() {
        return addOnItemID;
    }

    public void setAddOnItemID(String addOnItemID) {
        this.addOnItemID = addOnItemID;
    }

    public  int itemImg;
    public  int itemImgActive;
    public boolean isSelect = false;
    public String itemName;

    @ColumnInfo(name = "OrderId")
    private String OrderId;

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

}
