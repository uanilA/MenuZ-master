package com.menuz.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by mindiii on 29/10/18.
 */
@Entity (tableName = "order_zone")
public class OrderZoneModel {


    @ColumnInfo(name = "zoneId")
    private String zoneId;

    @ColumnInfo(name = "zoneName")
    private String zoneName;

    @ColumnInfo(name = "zonePlace")
    private String zonePlace;

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

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }


    @NonNull
    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(@NonNull String zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZonePlace() {
        return zonePlace;
    }

    public void setZonePlace(String zonePlace) {
        this.zonePlace = zonePlace;
    }

    public  int itemImg;
    public  int itemImgActive;
    public boolean isSelect = false;
    public String itemName;
}
