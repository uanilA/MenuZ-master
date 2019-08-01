package com.menuz.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

/**
 * Created by mindiii on 29/10/18.
 */
/*@Entity (tableName = "order_addonchild", indices = {@Index(value = {"addonsGroupId"})},
        foreignKeys = @ForeignKey(entity = OrderAddOnChild.class,
                parentColumns = "addonsGroupId",
                childColumns = "addonsGroupId",
                onDelete = ForeignKey.CASCADE))*/

@Entity(tableName = "order_addonchild")
public class OrderAddOnChild {


    @ColumnInfo(name = "addonId")
    private String addonId;

    @NonNull
    public String getAddonsGroupId() {
        return addonsGroupId;
    }

    public void setAddonsGroupId(@NonNull String addonsGroupId) {
        this.addonsGroupId = addonsGroupId;
    }

    public boolean isSyncSelect() {
        return isSyncSelect;
    }

    public void setSyncSelect(boolean syncSelect) {
        isSyncSelect = syncSelect;
    }

    @ColumnInfo(name = "isSyncSelect")
    private boolean isSyncSelect;

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "addOnPrimaryKey")
    private String addOnPrimaryKey;

    @ColumnInfo(name = "itemPrimaryKey")
    private String itemPrimaryKey;

    public String getAddonChoiceID() {
        return addonChoiceID;
    }

    public void setAddonChoiceID(String addonChoiceID) {
        this.addonChoiceID = addonChoiceID;
    }

    @ColumnInfo(name = "addonChoiceID")
    private String addonChoiceID;


    public String getAddonRemark() {
        return addonRemark;
    }

    public void setAddonRemark(String addonRemark) {
        this.addonRemark = addonRemark;
    }

    @ColumnInfo(name = "addonRemark")
    private String addonRemark;

    @NonNull
    public String getAddOnPrimaryKey() {
        return addOnPrimaryKey;
    }

    public void setAddOnPrimaryKey(@NonNull String addOnPrimaryKey) {
        this.addOnPrimaryKey = addOnPrimaryKey;
    }

    public String getItemPrimaryKey() {
        return itemPrimaryKey;
    }

    public void setItemPrimaryKey(String itemPrimaryKey) {
        this.itemPrimaryKey = itemPrimaryKey;
    }

    @NonNull
    @ColumnInfo(name = "addonsGroupId")
    private String addonsGroupId;

    @ColumnInfo(name = "isSelect")
    private boolean isSelect;

    @ColumnInfo(name = "addonName")
    private String addonName;

    private boolean isSynced;

    @ColumnInfo(name = "ItemIdAddon")
    private String ItemIdAddon;

    public String getItemIdAddon() {
        return ItemIdAddon;
    }

    public void setItemIdAddon(String itemIdAddon) {
        ItemIdAddon = itemIdAddon;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Ignore
    private String totalPrice="";

    @ColumnInfo(name = "addonGroupId")
    private String addonGroupId;


    @ColumnInfo(name = "itemPrice")
    private String itemPrice;

    public String getAddonAutoID() {
        return addonAutoID;
    }

    public void setAddonAutoID(String addonAutoID) {
        this.addonAutoID = addonAutoID;
    }

    @ColumnInfo(name = "addonAutoID")
    private String addonAutoID;


    @ColumnInfo(name = "addonPrice")
    private String addonPrice;

    @ColumnInfo(name = "preparations")
    private String preparations;

    @ColumnInfo(name = "addOnItemIdchild")
    private String addOnItemIdchild;

    @ColumnInfo(name = "addonchildselectedPrep")
    private String addonchildselectedPrep;

    public String getAddonchildselectedPrep() {
        return addonchildselectedPrep;
    }

    public void setAddonchildselectedPrep(String addonchildselectedPrep) {
        this.addonchildselectedPrep = addonchildselectedPrep;
    }

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

    public  int itemImgActive;

    public String itemName;

    @ColumnInfo(name = "OrderId")
    private String OrderId;

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public boolean isSynced() {
        return isSynced;
    }

    public void setSynced(boolean synced) {
        isSynced = synced;
    }
}
