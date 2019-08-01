package com.menuz.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by mindiii on 25/10/18.
 */

@Entity(tableName = "addonchild")

public class AdddonChildModel  {

    @ColumnInfo(name = "addonId")
    private String addonId;

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


    @ColumnInfo(name = "OrderId")
    private String OrderId="";

    public String getAddonRemark() {
        return addonRemark;
    }

    public void setAddonRemark(String addonRemark) {
        this.addonRemark = addonRemark;
    }

    @ColumnInfo(name = "addonRemark")
    private String addonRemark="";

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public boolean isSyncSelect() {
        return isSyncSelect;
    }

    public void setSyncSelect(boolean syncSelect) {
        isSyncSelect = syncSelect;
    }

    @ColumnInfo(name = "isSyncSelect")
    public boolean isSyncSelect=false;


    @ColumnInfo(name = "addonName")
    private String addonName;


    public String getAddonAutoID() {
        return addonAutoID;
    }

    public void setAddonAutoID(String addonAutoID) {
        this.addonAutoID = addonAutoID;
    }

    @ColumnInfo(name = "addonAutoID")
    private String addonAutoID;

    @ColumnInfo(name = "ItemIdAddon")
    private String ItemIdAddon;

    public String getItemIdAddon() {
        return ItemIdAddon;
    }

    public void setItemIdAddon(String itemIdAddon) {
        ItemIdAddon = itemIdAddon;
    }

    @ColumnInfo(name = "addonGroupId")
    private String addonGroupId;


    @ColumnInfo(name = "itemPrice")
    private String itemPrice;


    @ColumnInfo(name = "addonPrice")
    private String addonPrice;

    @ColumnInfo(name = "preparations")
    private String preparations;

    @ColumnInfo(name = "addOnItemIdchild")
    private String addOnItemIdchild;


    @NonNull
    public String getAddonId() {
        return addonId;
    }

    public void setAddonId(@NonNull String addonId) {
        this.addonId = addonId;
    }

    public String getAddonName() {
        return addonName;
    }

    public void setAddonName(String addonName) {
        this.addonName = addonName;
    }

    public String getAddonGroupId() {
        return addonGroupId;
    }

    public void setAddonGroupId(String addonGroupId) {
        this.addonGroupId = addonGroupId;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getAddonPrice() {
        return addonPrice;
    }

    public void setAddonPrice(String addonPrice) {
        this.addonPrice = addonPrice;
    }

    public String getPreparations() {
        return preparations;
    }

    public void setPreparations(String preparations) {
        this.preparations = preparations;
    }

    public String getAddOnItemIdchild() {
        return addOnItemIdchild;
    }

    public void setAddOnItemIdchild(String addOnItemIdchild) {
        this.addOnItemIdchild = addOnItemIdchild;
    }

    public  int itemImg;
    public  int itemImgActive;
    @ColumnInfo(name = "isSelect")
    private boolean isSelect = false;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String itemName;
    public String prepId = "";

}
