package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.PricevaluesModel;

import java.util.List;

/**
 * Created by mindiii on 20/12/18.
 */
@Dao
public interface PricevalueDao {

    @Query("SELECT * FROM pricevalues")
    List<PricevaluesModel> loadPriceValue();

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    void inserPriceValue(PricevaluesModel bean);*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void inserPriceValue(List<PricevaluesModel> bean);

    @Query("Delete From pricevalues")
    void clearPriceValueData();

    @Query("SELECT pricevaluesPrice FROM pricevalues WHERE pricevaluesPriceId=:pricelistId AND pricevaluesKind='NORMAL'")
    String loadPriceValue(String pricelistId);
}
