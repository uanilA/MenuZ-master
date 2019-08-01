package com.menuz.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by mindiii on 22/10/18.
 */
@Entity(tableName = "employee")

public class EmployeeModel {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "employeeId")
    private String employeeId;

    @ColumnInfo(name = "employeeName")
    private String employeeName;


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
