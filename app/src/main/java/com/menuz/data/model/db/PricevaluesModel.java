package com.menuz.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by mindiii on 20/12/18.
 */

@Entity(tableName = "pricevalues")
public class PricevaluesModel {

    @NonNull
    public String getPricevaluesId() {
        return pricevaluesId;
    }

    public void setPricevaluesId(@NonNull String pricevaluesId) {
        this.pricevaluesId = pricevaluesId;
    }

    public String getPricevaluesPriceId() {
        return pricevaluesPriceId;
    }

    public void setPricevaluesPriceId(String pricevaluesPriceId) {
        this.pricevaluesPriceId = pricevaluesPriceId;
    }

    public String getPricevaluesKind() {
        return pricevaluesKind;
    }

    public void setPricevaluesKind(String pricevaluesKind) {
        this.pricevaluesKind = pricevaluesKind;
    }

    public String getPricevaluesPrice() {
        return pricevaluesPrice;
    }

    public void setPricevaluesPrice(String pricevaluesPrice) {
        this.pricevaluesPrice = pricevaluesPrice;
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "pricevaluesId")
    private String pricevaluesId;


    @ColumnInfo(name = "pricevaluesPriceId")
    private String pricevaluesPriceId;


    @ColumnInfo(name = "pricevaluesKind")
    private String pricevaluesKind;


    @ColumnInfo(name = "pricevaluesPrice")
    private String pricevaluesPrice;


}
