package com.menuz.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by mindiii on 24/10/18.
 */

@Entity(tableName = "preparation")

public class PreparationModel {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "preparationId")
    private String preparationId;

    @ColumnInfo(name = "preparationName")
    private String preparationName;

    @ColumnInfo(name = "preparationIsPrefixed")
    private String preparationIsPrefixed;

    @NonNull
    public String getPreparationId() {
        return preparationId;
    }

    public void setPreparationId(@NonNull String preparationId) {
        this.preparationId = preparationId;
    }

    public String getPreparationName() {
        return preparationName;
    }

    public void setPreparationName(String preparationName) {
        this.preparationName = preparationName;
    }

    public String getPreparationIsPrefixed() {
        return preparationIsPrefixed;
    }

    public void setPreparationIsPrefixed(String preparationIsPrefixed) {
        this.preparationIsPrefixed = preparationIsPrefixed;
    }

    public  int itemImg;
    public  int itemImgActive;
    public boolean isSelect = false;
    public String itemName;

}
