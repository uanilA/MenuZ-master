package com.menuz.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by mindiii on 6/12/18.
 */

@Entity(tableName = "order_payment")

public class PaymentModel {


    @ColumnInfo(name = "termId")
    private String termId;
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "addonIdN")
    private int addonIdN;


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

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSecuritycode() {
        return securitycode;
    }

    public void setSecuritycode(String securitycode) {
        this.securitycode = securitycode;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @ColumnInfo(name = "payMethodId")
    private String payMethodId;

    @ColumnInfo(name = "payMethodName")
    private String payMethodName;

    @ColumnInfo(name = "payMethodFixValue")
    private String payMethodFixValue;

    @ColumnInfo(name = "payMethodType")
    private String payMethodType;


    @ColumnInfo(name = "cardno")
    private String cardno;

    @ColumnInfo(name = "month")
    private String month;

    @ColumnInfo(name = "year")
    private String year;

    @ColumnInfo(name = "securitycode")
    private String securitycode;

    @ColumnInfo(name = "Id")
    private String Id;

    @ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "OrderId")
    private String OrderId;

    @ColumnInfo(name = "termName")
    private String termName;

    @ColumnInfo(name = "Cibus_PosId")
    private String Cibus_PosId;

    @ColumnInfo(name = "valueCardId")
    private String valueCardId;

    @ColumnInfo(name = "valueCardPassword")
    private String valueCardPassword;

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    @NonNull
    public int getAddonIdN() {
        return addonIdN;
    }

    public void setAddonIdN(@NonNull int addonIdN) {
        this.addonIdN = addonIdN;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getCibus_PosId() {
        return Cibus_PosId;
    }

    public void setCibus_PosId(String cibus_PosId) {
        Cibus_PosId = cibus_PosId;
    }

    public String getValueCardId() {
        return valueCardId;
    }

    public void setValueCardId(String valueCardId) {
        this.valueCardId = valueCardId;
    }

    public String getValueCardPassword() {
        return valueCardPassword;
    }

    public void setValueCardPassword(String valueCardPassword) {
        this.valueCardPassword = valueCardPassword;
    }

    public String getEmvData() {
        return emvData;
    }

    public void setEmvData(String emvData) {
        this.emvData = emvData;
    }

    public String getCibus_RestaurantID() {
        return Cibus_RestaurantID;
    }

    public void setCibus_RestaurantID(String cibus_RestaurantID) {
        Cibus_RestaurantID = cibus_RestaurantID;
    }

    public String getTenBis_Password() {
        return TenBis_Password;
    }

    public void setTenBis_Password(String tenBis_Password) {
        TenBis_Password = tenBis_Password;
    }

    public String getTenBis_ResID() {
        return TenBis_ResID;
    }

    public void setTenBis_ResID(String tenBis_ResID) {
        TenBis_ResID = tenBis_ResID;
    }

    public String getTenBis_User() {
        return TenBis_User;
    }

    public void setTenBis_User(String tenBis_User) {
        TenBis_User = tenBis_User;
    }

    @ColumnInfo(name = "emvData")
    private String emvData;

    @ColumnInfo(name = "Cibus_RestaurantID")
    private String Cibus_RestaurantID;


    @ColumnInfo(name = "TenBis_Password")
    private String TenBis_Password;


    @ColumnInfo(name = "TenBis_ResID")
    private String TenBis_ResID;


    @ColumnInfo(name = "TenBis_User")
    private String TenBis_User;





}
