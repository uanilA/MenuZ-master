package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.AddOnModel;
import com.menuz.data.model.db.OrderAddOnModel;

import java.util.List;

/**
 * Created by mindiii on 29/10/18.
 */
@Dao
public interface OrderAddonDao {

    @Query("SELECT * FROM order_addon WHERE addOnItemID =:addOnItemID AND OrderId=:OrderId")
    List<OrderAddOnModel> loadallAddonByitem(String addOnItemID,String OrderId);

    @Query("UPDATE order_addon SET isSelect=:isSelect WHERE addonsGroupId = :addonsGroupId AND OrderId=:OrderId AND addOnItemID=:addOnItemID")
    void update(boolean isSelect,String addonsGroupId,String OrderId,String addOnItemID);



    @Query("SELECT * FROM order_addon WHERE OrderId=:OrderId")
    List<OrderAddOnModel> loadAllItem(String OrderId);


    @Query("DELETE FROM order_addon WHERE addOnItemID = :addOnItemID")
    void deleteByAddon(String addOnItemID);



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(OrderAddOnModel bean);
}
