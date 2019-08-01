package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.OrderZoneModel;
import com.menuz.data.model.db.Zonemodel;

import java.util.List;

/**
 * Created by mindiii on 29/10/18.
 */
@Dao
public interface OrderZoneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(OrderZoneModel orderZoneModel);

    @Query("SELECT * FROM order_zone WHERE OrderId=:orderId")
    OrderZoneModel loadAllOrderZone(String orderId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<OrderZoneModel> zonemodelList);

}
