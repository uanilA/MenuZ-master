package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.OrderAddOnChild;
import com.menuz.data.model.db.OrderItemModel;

import java.util.List;

/**
 * Created by mindiii on 29/10/18.
 */
@Dao
public interface OrderItemDao {

    @Query("SELECT * FROM order_item WHERE itemId =:itemid AND OrderId=:OrderId")
    List<OrderItemModel> getItem(String itemid,String OrderId);

    @Query("SELECT * FROM order_item WHERE itemGroupId =:groupId")
    List<OrderItemModel> loadAllOrderItem(String groupId);

    @Query("SELECT * FROM order_item WHERE OrderId=:OrderId")
    List<OrderItemModel> loadAllorderItem(String OrderId);

    @Query("UPDATE order_item SET countPrice=:countPrice WHERE itemId = :itemId")
    void updatecount(String countPrice, String itemId);

    @Query("UPDATE order_item SET itemRemark=:itemRemark WHERE itemId = :itemId AND OrderId =:OrderId")
    void updateitemRemark(String itemRemark, String itemId, String OrderId);

    @Query("UPDATE order_item SET itemRemark =:itemRemark WHERE itemId = :itemId AND OrderId =:OrderId")
    void updateitemPrepRemark(String itemRemark, String itemId, String OrderId);



    @Query("SELECT * FROM order_item WHERE  itemId = :itemId")
    OrderItemModel loadCount(String itemId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(OrderItemModel bean);

    @Query("DELETE FROM order_item WHERE itemId = :itemId")
     void deleteByItemId(String itemId);

    @Query("UPDATE order_item SET grandTotal=:grandTotal")
    void updateTotal(String grandTotal);

    @Query("UPDATE order_item SET isCartSelected=:isCartSelected WHERE OrderId=:OrderId")
    void updateCart(boolean isCartSelected, String OrderId);

    @Query("SELECT * FROM order_item WHERE OrderId=:OrderId AND isCartSelected=:isCartSelected")
    List<OrderItemModel> getSelectedCart(String OrderId, boolean isCartSelected);

    @Query("SELECT * FROM order_item WHERE OrderId=:OrderId")
    List<OrderItemModel> getSelectedItem(String OrderId);


    @Query("DELETE FROM  order_item WHERE  itemPrimaryKey =:itemPrimaryKey")
    void DeleteItem(String itemPrimaryKey);

    @Query("DELETE FROM  order_item WHERE  OrderId =:OrderId")
    void DeleteItemFromCurrent(String OrderId);

    @Query("DELETE FROM order_item")
    void DeleteAll();



    @Query("UPDATE order_item SET itemPrice=:itemPrice WHERE itemId = :itemId")
    void updatePrice(String itemPrice, String itemId);



    @Query("UPDATE order_item SET OrderId=:OrderId WHERE itemId = :itemId")
    void updateOrderIdinItem(String OrderId, String itemId);


    @Query("SELECT * FROM order_item")
    List<OrderItemModel> getAllItems();

    @Query("SELECT OrderId FROM order_item WHERE OrderId=:OrderId")
    String getOrderId(String OrderId);








}
