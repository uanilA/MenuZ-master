package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.PriceModel;

import java.util.List;

/**
 * Created by mindiii on 20/12/18.
 */
@Dao
public interface PriceDao {

    @Query("SELECT * FROM price")
     List<PriceModel> loadPrice();

    @Query("SELECT price.*, priceVal.pricevaluesPrice FROM price as price JOIN pricevalues as priceVal ON price.pricelistId=priceVal.pricevaluesPriceId WHERE priceVal.pricevaluesKind='NORMAL'")
    List<PriceModel> loadPriceData();

    @Query("SELECT * FROM price WHERE pricelistId IN (:pricelistId)")
     List<PriceModel> loadAllByIds(List<String> pricelistId);

    @Query("SELECT * FROM price WHERE pricelistId IN (:pricelistId)")
     List<PriceModel> loadAllByPrepId(String pricelistId);

    @Query("SELECT * FROM price WHERE pricelistId =:pricelistId")
     PriceModel loadByPrepId(String pricelistId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     void insert(PriceModel bean);
}
