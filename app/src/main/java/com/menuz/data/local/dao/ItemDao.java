package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.ItemModel;
import com.menuz.data.model.db.OrderItemModel;
import com.menuz.data.model.db.TableModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mindiii on 23/10/18.
 */

@Dao
public interface ItemDao {
    @Query("SELECT * FROM item WHERE itemGroupId =:itemGroupId")
    List<ItemModel> loadAllByItem(String itemGroupId);

    @Query("SELECT * FROM item")
    List<ItemModel> loadAllItem();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ItemModel bean);

    @Query("UPDATE item SET itemPrice=:itemPrice WHERE itemId = :itemId")
    void updatePriceFromItem(String itemPrice, String itemId);







}
