package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.MenuModel;
import com.menuz.data.model.db.OrderItemModel;
import com.menuz.data.model.db.OrderMenuModel;

import java.util.List;

/**
 * Created by mindiii on 29/10/18.
 */
@Dao
public interface OrderMenuDao {
    @Query("SELECT * FROM order_menu")
    List<OrderMenuModel> loadAllMenu();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(OrderMenuModel bean);


    @Query("SELECT * FROM order_menu WHERE OrderId=:OrderId")
    List<OrderMenuModel> loadAllorderMenu(String OrderId);

}
