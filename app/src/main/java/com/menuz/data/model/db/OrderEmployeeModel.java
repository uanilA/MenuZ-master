package com.menuz.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by mindiii on 29/10/18.
 */

@Entity(tableName = "order_employee")
public class OrderEmployeeModel {


    @ColumnInfo(name = "employeeId")
    private String employeeId;

    @ColumnInfo(name = "employeeName")
    private String employeeName;

    @ColumnInfo(name = "Nuofguest")
    private String Nuofguest;

    @ColumnInfo(name = "OrderId")
    private String OrderId;

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

    @NonNull
    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(@NonNull String orderId) {
        OrderId = orderId;
    }

    public String getNuofguest() {
        return Nuofguest;
    }

    public void setNuofguest(String nuofguest) {
        Nuofguest = nuofguest;
    }

    @NonNull
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(@NonNull String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }


    public  int itemImg;
    public  int itemImgActive;
    public boolean isSelect = false;
    public String itemName;





}
