package com.menuz.data.model.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "paymethods")
public class PayMethodsModel {

    @ColumnInfo(name = "termId")
    private String termId;


    @ColumnInfo(name = "payMethodId")
    private String payMethodId;

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "primary")
    private int primary;


    @ColumnInfo(name = "payMethodName")
    private String payMethodName;

    @ColumnInfo(name = "paidAmt")
    private String paidAmt;

    @ColumnInfo(name = "payMethodFixValue")
    private String payMethodFixValue;

    @ColumnInfo(name = "payMethodType")
    private String payMethodType;

    @ColumnInfo(name = "OrderId")
    private String OrderId;

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getPaidAmt() {
        return paidAmt;
    }

    public void setPaidAmt(String paidAmt) {
        this.paidAmt = paidAmt;
    }

    public String getPayMethodId() {
        return payMethodId;
    }

    public void setPayMethodId(String payMethodId) {
        this.payMethodId = payMethodId;
    }

    public String getPayMethodName() {
        return payMethodName;
    }

    public void setPayMethodName(String payMethodName) {
        this.payMethodName = payMethodName;
    }

    public String getPayMethodFixValue() {
        return payMethodFixValue;
    }

    public void setPayMethodFixValue(String payMethodFixValue) {
        this.payMethodFixValue = payMethodFixValue;
    }

    public String getPayMethodType() {
        return payMethodType;
    }

    public void setPayMethodType(String payMethodType) {
        this.payMethodType = payMethodType;
    }


    public String getOrderId() {
        return OrderId;
    }



    public void setOrderId( String orderId) {
        OrderId = orderId;
    }




    @NonNull
    public int getPrimary() {
        return primary;
    }

    public void setPrimary(@NonNull int primary) {
        this.primary = primary;
    }
}
