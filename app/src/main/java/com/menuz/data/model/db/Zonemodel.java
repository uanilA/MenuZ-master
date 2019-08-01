package com.menuz.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by mindiii on 18/10/18.
 */

@Entity(tableName = "zoneTable")
public class Zonemodel implements Serializable {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "zoneId")
    private String zoneId;

    @ColumnInfo(name = "zoneName")
    private String zoneName;

    @ColumnInfo(name = "zonePlace")
    private String zonePlace;


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
