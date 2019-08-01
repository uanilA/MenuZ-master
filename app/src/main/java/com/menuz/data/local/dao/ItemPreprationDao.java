package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.ItemPreprationModel;
import com.menuz.data.model.db.OrderPreparationModel;

import java.util.List;

@Dao
public interface ItemPreprationDao {

   /* @Query("SELECT * FROM order_preparation WHERE itemId =:itemId AND OrderId=:OrderId")
    List<OrderPreparationModel> loadallPrepByGroupId(String itemId, String OrderId);*/


    //@Query("SELECT DISTINCT(item_prepration.preparationName) AS preparationName, item_prepration.* FROM item_prepration AS item_prepration WHERE itemId =:itemId")

    @Query("SELECT * FROM item_prepration WHERE itemId =:itemId")
    List<ItemPreprationModel> loadallPrepByItemId(String itemId);

    @Query("SELECT * FROM item_prepration WHERE preparationId =:preparationId")
    ItemPreprationModel getItemPreps(String preparationId);



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItemPrep(ItemPreprationModel bean);

    @Query("DELETE  FROM item_prepration")
    void deletePrepration();

    @Query("UPDATE item_prepration SET itemRemark=:itemRemark WHERE preparationId =:preparationId")
    void updateRemarkofPrepration(String itemRemark,String preparationId);

    @Query("SELECT * FROM item_prepration")
    List<ItemPreprationModel> loadAllPrep();

    @Query("UPDATE item_prepration SET OrderId=:OrderId WHERE preparationId =:preparationId AND itemId=:itemId")
    void updateItemPrepOrder(String OrderId,String preparationId,String itemId);

}
