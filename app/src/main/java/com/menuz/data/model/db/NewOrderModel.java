package com.menuz.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by mindiii on 15/11/18.
 */


@Entity(tableName = "new_order")
public class NewOrderModel implements Serializable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "OrderId")
    private String OrderId;
    @ColumnInfo(name = "tableId")
    private String tableId;
    @ColumnInfo(name = "zoneId")
    private String zoneId;
    @ColumnInfo(name = "zoneName")
    private String zoneName;
    @ColumnInfo(name = "Nuofguest")
    private String Nuofguest;
    @ColumnInfo(name = "employeeName")
    private String employeeName;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @ColumnInfo(name = "employeeId")
    private String employeeId;

    @ColumnInfo(name = "terminalId")
    private String terminalId;

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    @NonNull
    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(@NonNull String orderId) {
        OrderId = orderId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getNuofguest() {
        return Nuofguest;
    }

    public void setNuofguest(String nuofguest) {
        Nuofguest = nuofguest;
    }

}
