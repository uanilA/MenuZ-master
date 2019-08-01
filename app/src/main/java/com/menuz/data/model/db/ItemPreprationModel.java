package com.menuz.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "item_prepration")

public class ItemPreprationModel {
    @ColumnInfo(name = "preparationId")
    private String preparationId;

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "itemPrepPrimaryKey")
    private int itemPrepPrimaryKey;

    @ColumnInfo(name = "itemPrimaryKey")
    private int itemPrimaryKey;

    @ColumnInfo(name = "itemId")
    private String itemId;
    @ColumnInfo(name = "itemGroupId")
    private String itemGroupId;
    @ColumnInfo(name = "preparationName")

    private String preparationName;
    @ColumnInfo(name = "prefixId")

    private String prefixId;
    @ColumnInfo(name = "prefixName")

    private String prefixName="";
    @ColumnInfo(name = "itemRemark")
    private String itemRemark;
    @ColumnInfo(name = "itemPreprationRemark")
    private String itemPreprationRemark;
    @ColumnInfo(name = "preparationIsPrefixed")

    private String preparationIsPrefixed;
    @ColumnInfo(name = "isSelect")
    private boolean isSelect;
    @ColumnInfo(name = "OrderId")
    private String OrderId;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @ColumnInfo(name = "itemName")
    private String itemName;


    @NonNull
    public int getItemPrepPrimaryKey() {
        return itemPrepPrimaryKey;
    }

    public void setItemPrepPrimaryKey(@NonNull int itemPrepPrimaryKey) {
        this.itemPrepPrimaryKey = itemPrepPrimaryKey;
    }

    public int getItemPrimaryKey() {
        return itemPrimaryKey;
    }

    public void setItemPrimaryKey(int itemPrimaryKey) {
        this.itemPrimaryKey = itemPrimaryKey;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemGroupId() {
        return itemGroupId;
    }

    public void setItemGroupId(String itemGroupId) {
        this.itemGroupId = itemGroupId;
    }

    public String getPreparationName() {
        return preparationName;
    }

    public void setPreparationName(String preparationName) {
        this.preparationName = preparationName;
    }

    public String getPreparationIsPrefixed() {
        return preparationIsPrefixed;
    }

    public void setPreparationIsPrefixed(String preparationIsPrefixed) {
        this.preparationIsPrefixed = preparationIsPrefixed;
    }

    public String getPrefixId() {
        return prefixId;
    }

    public void setPrefixId(String prefixId) {
        this.prefixId = prefixId;
    }

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
    }

    public String getItemPreprationRemark() {
        return itemPreprationRemark;
    }

    public void setItemPreprationRemark(String itemPreprationRemark) {
        this.itemPreprationRemark = itemPreprationRemark;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @NonNull
    public String getPreparationId() {
        return preparationId;
    }

    public void setPreparationId(@NonNull String preparationId) {
        this.preparationId = preparationId;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

}
