package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.OrderTableModel;
import com.menuz.data.model.db.TableModel;

import java.util.List;

/**
 * Created by mindiii on 29/10/18.
 */
@Dao
public interface OrderTableDao {
    @Query("SELECT * FROM order_table WHERE tableZoneId =:zoneId")
    List<OrderTableModel> loadAllByIds(String zoneId);

    @Query("SELECT * FROM order_table WHERE OrderId=:OrderId")
    OrderTableModel loadAllordertables(String OrderId);


    @Query("UPDATE order_table SET tableId=:tableId WHERE OrderId=:OrderId")
    void  updateOrderTable(String OrderId,String tableId);

    @Query("SELECT * FROM order_table")
    List<OrderTableModel> loadAllTable();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(OrderTableModel bean);


}
