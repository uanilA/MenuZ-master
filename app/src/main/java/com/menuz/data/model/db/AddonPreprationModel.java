package com.menuz.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "addonprepration")
public class AddonPreprationModel {

    @ColumnInfo(name = "addonId")
    private String addonId;
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "addonIdN")
    private int addonIdN;
    @ColumnInfo(name = "addonName")
    private String addonName;
    @ColumnInfo(name = "addonGroupId")
    private String addonGroupId;
    @ColumnInfo(name = "addonsGroupId")
    private String addonsGroupId;
    @ColumnInfo(name = "prefixId")
    private String prefixId;
    @ColumnInfo(name = "prefixName")
    private String prefixName;
    @ColumnInfo(name = "itemPrice")
    private String itemPrice;
    @ColumnInfo(name = "itemRemark")
    private String itemRemark;
    @ColumnInfo(name = "itemPreprationRemark")
    private String itemPreprationRemark;
    @ColumnInfo(name = "preparations")
    private String preparations;
    @ColumnInfo(name = "addOnItemIdchild")
    private String addOnItemIdchild;
    @ColumnInfo(name = "preparationId")
    private String preparationId;
    @ColumnInfo(name = "isSelect")
    private boolean isSelect;
    @ColumnInfo(name = "ItemIdAddon")
    private String ItemIdAddon;
    @ColumnInfo(name = "preparationName")

    private String preparationName;
    @ColumnInfo(name = "preparationIsPrefixed")

    private String preparationIsPrefixed;
    @ColumnInfo(name = "OrderId")
    private String OrderId;

    @NonNull
    public int getAddonIdN() {
        return addonIdN;
    }

    public void setAddonIdN(@NonNull int addonIdN) {
        this.addonIdN = addonIdN;
    }

    public String getItemPreprationRemark() {
        return itemPreprationRemark;
    }

    public void setItemPreprationRemark(String itemPreprationRemark) {
        this.itemPreprationRemark = itemPreprationRemark;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
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

    public String getAddonsGroupId() {
        return addonsGroupId;
    }

    public void setAddonsGroupId(String addonsGroupId) {
        this.addonsGroupId = addonsGroupId;
    }

    public String getItemIdAddon() {
        return ItemIdAddon;
    }

    public void setItemIdAddon(String itemIdAddon) {
        ItemIdAddon = itemIdAddon;
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

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
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

    public String getPreparationId() {
        return preparationId;
    }

    public void setPreparationId(String preparationId) {
        this.preparationId = preparationId;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
