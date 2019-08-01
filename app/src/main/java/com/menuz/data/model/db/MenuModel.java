package com.menuz.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by mindiii on 23/10/18.
 */
@Entity(tableName = "menu")
public class MenuModel {

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
    @ColumnInfo(name = "groupId")
    private String groupId;

    @ColumnInfo(name = "groupName")
    private String groupName;

    public String getGroupImage() {
        return groupImage;
    }

    public void setGroupImage(String groupImage) {
        this.groupImage = groupImage;
    }

    @ColumnInfo(name = "groupImage")
    private String groupImage;

    @ColumnInfo(name = "groupPlace")
    private String groupPlace;

    @ColumnInfo(name = "groupActive")
    private String groupActive;

    @ColumnInfo(name = "groupParentId")
    private String groupParentId;

    @ColumnInfo(name = "groupUptoTime")
    private String groupUptoTime;

    @ColumnInfo(name = "groupFromTime")
    private String groupFromTime;

    @ColumnInfo(name = "igProperty")
    private String igProperty;

    @ColumnInfo(name = "chkPermission")
    private String chkPermission;


    @ColumnInfo(name = "subgroups")
    private String subgroups;

    public  int itemImg;
    public  int itemImgActive;
    public boolean isSelect = false;
    public String itemName;



    @NonNull
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(@NonNull String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupPlace() {
        return groupPlace;
    }

    public void setGroupPlace(String groupPlace) {
        this.groupPlace = groupPlace;
    }

    public String getGroupActive() {
        return groupActive;
    }

    public void setGroupActive(String groupActive) {
        this.groupActive = groupActive;
    }

    public String getGroupParentId() {
        return groupParentId;
    }

    public void setGroupParentId(String groupParentId) {
        this.groupParentId = groupParentId;
    }

    public String getGroupUptoTime() {
        return groupUptoTime;
    }

    public void setGroupUptoTime(String groupUptoTime) {
        this.groupUptoTime = groupUptoTime;
    }

    public String getGroupFromTime() {
        return groupFromTime;
    }

    public void setGroupFromTime(String groupFromTime) {
        this.groupFromTime = groupFromTime;
    }

    public String getIgProperty() {
        return igProperty;
    }

    public void setIgProperty(String igProperty) {
        this.igProperty = igProperty;
    }

    public String getChkPermission() {
        return chkPermission;
    }

    public void setChkPermission(String chkPermission) {
        this.chkPermission = chkPermission;
    }

    public String getSubgroups() {
        return subgroups;
    }

    public void setSubgroups(String subgroups) {
        this.subgroups = subgroups;
    }
}
