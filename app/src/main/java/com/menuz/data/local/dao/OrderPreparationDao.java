package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.OrderAddOnChild;
import com.menuz.data.model.db.OrderPreparationModel;

import java.util.List;

/**
 * Created by mindiii on 29/10/18.
 */
@Dao
public interface OrderPreparationDao {
    @Query("SELECT * FROM order_preparation")
    List<OrderPreparationModel> loadPreparation();

    @Query("SELECT * FROM order_preparation WHERE preparationId IN (:preparationId)")
    List<OrderPreparationModel> loadAllByIds(List<String> preparationId);


    @Query("SELECT * FROM order_preparation WHERE itemId =:itemId AND OrderId=:OrderId")
    List<OrderPreparationModel> loadallPrepByGroupId(String itemId,String OrderId);

    @Query("SELECT * FROM order_preparation WHERE itemId =:itemId AND OrderId=:OrderId AND isSelect=:isSelect")
    List<OrderPreparationModel> loadallSelectedItemOnItemId(String itemId,String OrderId,boolean isSelect);


    @Query("UPDATE order_preparation SET prefixName=:prefixName , prefixId=:prefixId WHERE itemId = :itemId AND preparationId=:preparationId AND OrderId=:OrderId AND orderPrepPrimaryKey=:orderPrepPrimaryKey")
    void updateprep(String itemId,String preparationId,String OrderId,String prefixName ,String prefixId,String orderPrepPrimaryKey);

    @Query("SELECT * FROM order_preparation WHERE isSelect =:isSelect AND OrderId=:OrderId")
    List<OrderPreparationModel> loadAllprepSelected(boolean isSelect,String OrderId);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(OrderPreparationModel bean);

    @Query("SELECT * FROM order_preparation WHERE itemPrimaryKey =:itemPrimaryKey AND OrderId=:OrderId")
    List<OrderPreparationModel> loadAllSelectedPreprations(String itemPrimaryKey,String OrderId);

    @Query("UPDATE   order_preparation SET  orderPrepPrimaryKey =:addOnPrimaryKey")
    void UpdatePreprationofItem(String addOnPrimaryKey);

    @Query("DELETE FROM  order_preparation WHERE  orderPrepPrimaryKey =:addOnPrimaryKey")
    void deletePreps(String addOnPrimaryKey);


    @Query("SELECT * FROM  order_preparation WHERE  itemPrimaryKey =:itemPrimaryKey")
    List<OrderPreparationModel>getAllOrderItemPrep(String itemPrimaryKey);


    @Query("UPDATE   order_preparation SET  OrderId =:OrderId WHERE preparationId=:preparationId")
    void updatePrepration(String OrderId,String preparationId);

}
