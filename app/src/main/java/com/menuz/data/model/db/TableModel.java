package com.menuz.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by mindiii on 18/10/18.
 */

@Entity(tableName = "tables")
public class TableModel implements Serializable {


    public int itemImg;
    public int itemImgActive;


    @ColumnInfo(name = "isSelect")
    public boolean isSelect = false;

    public String getIsBusy() {
        return isBusy;
    }

    public void setIsBusy(String isBusy) {
        this.isBusy = isBusy;
    }

    @ColumnInfo(name = "isBusy")
    public String isBusy="";

    public String getIsLock() {
        return isLock;
    }

    public void setIsLock(String isLock) {
        this.isLock = isLock;
    }

    @ColumnInfo(name = "isLock")
    private String isLock="";



    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String itemName;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tableId")
    private String tableId;
    @ColumnInfo(name = "tableZoneId")
    private String tableZoneId;
    @ColumnInfo(name = "tableName")
    private String tableName;
    @ColumnInfo(name = "tableHeight")
    private String tableHeight;
    @ColumnInfo(name = "tableLeft")
    private String tableLeft;
    @ColumnInfo(name = "tableWidth")
    private String tableWidth;
    @ColumnInfo(name = "tableOrderId")
    private String tableOrderId;
     @ColumnInfo(name = "tableGuestName")
    private String tableGuestName;
     @ColumnInfo(name = "tableTerminalId")
    private String tableTerminalId;
        @ColumnInfo(name = "tableTop")
    private String tableTop;
    private String newOrderId = "";

    public String getNewOrderId() {
        return newOrderId;
    }

    public void setNewOrderId(String newOrderId) {
        this.newOrderId = newOrderId;
    }

    @NonNull
    public String getTableId() {
        return tableId;
    }

    public void setTableId(@NonNull String tableId) {
        this.tableId = tableId;
    }

    public String getTableZoneId() {
        return tableZoneId;
    }

    public void setTableZoneId(String tableZoneId) {
        this.tableZoneId = tableZoneId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableLeft() {
        return tableLeft;
    }

    public void setTableLeft(String tableLeft) {
        this.tableLeft = tableLeft;
    }

    public String getTableWidth() {
        return tableWidth;
    }

    public void setTableWidth(String tableWidth) {
        this.tableWidth = tableWidth;
    }

    public String getTableOrderId() {
        return tableOrderId;
    }

    public void setTableOrderId(String tableOrderId) {
        this.tableOrderId = tableOrderId;
    }

    public String getTableGuestName() {
        return tableGuestName;
    }

    public void setTableGuestName(String tableGuestName) {
        this.tableGuestName = tableGuestName;
    }

    public String getTableTerminalId() {
        return tableTerminalId;
    }

    public void setTableTerminalId(String tableTerminalId) {
        this.tableTerminalId = tableTerminalId;
    }


    public String getTableHeight() {
        return tableHeight;
    }

    public void setTableHeight(String tableHeight) {
        this.tableHeight = tableHeight;
    }

    public String getTableTop() {
        return tableTop;
    }

    public void setTableTop(String tableTop) {
        this.tableTop = tableTop;
    }


}
