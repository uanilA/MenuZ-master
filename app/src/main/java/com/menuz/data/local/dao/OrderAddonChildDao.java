package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.menuz.data.model.db.OrderAddOnChild;

import java.util.List;

/**
 * Created by mindiii on 29/10/18.
 */
@Dao
public interface OrderAddonChildDao {

    @Query("SELECT * FROM order_addonchild WHERE addonId =:addonId AND OrderId=:OrderId")
    List<OrderAddOnChild> loadallOrderAddonChiild(String addonId,String OrderId);

    @Query("SELECT * FROM order_addonchild WHERE ItemIdAddon =:ItemIdAddon AND OrderId=:OrderId AND isSelect=:isSelect")
    List<OrderAddOnChild> loadSelectedAddonByItemId(String ItemIdAddon, String OrderId, boolean isSelect);


    @Query("DELETE FROM order_addonchild WHERE ItemIdAddon = :ItemIdAddon AND addonsGroupId=:addonsGroupId AND addonId=:addonId")
    void deletebyItemID(String ItemIdAddon,String addonsGroupId,String addonId);

    @Query("UPDATE order_addonchild SET isSelect=:isSelect WHERE addonId = :addonId AND OrderId=:OrderId AND ItemIdAddon=:ItemIdAddon")
    void update(boolean isSelect, String addonId, String OrderId, String ItemIdAddon);


    @Query("UPDATE order_addonchild SET isSelect=:isSelect WHERE ItemIdAddon = :ItemIdAddon AND addonsGroupId=:addonsGroupId AND OrderId=:OrderId")
    void updateAddon(boolean isSelect,String ItemIdAddon,String addonsGroupId,String OrderId);



    @Query("DELETE FROM order_addonchild WHERE addonchildselectedPrep = :addonchildselectedPrep")
    void deletebyItemPrep(String addonchildselectedPrep);


    @Query("SELECT * FROM order_addonchild WHERE isSelect =:isSelect AND OrderId=:OrderId")
    List<OrderAddOnChild> loadAllItemSelected(boolean isSelect,String OrderId);

    @Query("SELECT * FROM order_addonchild WHERE OrderId=:OrderId")
    List<OrderAddOnChild> loadAllItem(String OrderId);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(OrderAddOnChild bean);

    @Query("DELETE FROM order_addonchild WHERE ItemIdAddon = :ItemIdAddon")
    void deleteByAddon(String ItemIdAddon);



    @Query("SELECT * FROM order_addonchild WHERE addonsGroupId =:addonsGroupId AND ItemIdAddon=:ItemIdAddon AND OrderId=:OrderId")
    List<OrderAddOnChild> loadallAddonByitem(String addonsGroupId,String ItemIdAddon,String OrderId);

    @Query("SELECT * FROM order_addonchild WHERE isSelect =:isSelect AND OrderId=:OrderId AND ItemIdAddon=:ItemIdAddon AND addonsGroupId=:addonsGroupId")
    List<OrderAddOnChild> loadAllSelectdAddon(boolean isSelect, String OrderId, String ItemIdAddon,String addonsGroupId);


    @Query("SELECT * FROM order_addonchild WHERE itemPrimaryKey =:itemPrimaryKey AND OrderId=:OrderId")
    List<OrderAddOnChild> loadAllSelectedAddons(String itemPrimaryKey, String OrderId);

    @Query("UPDATE   order_addonchild SET  addOnPrimaryKey =:addOnPrimaryKey")
    void UpdateAddons(String addOnPrimaryKey);

    @Query("DELETE FROM  order_addonchild WHERE  addOnPrimaryKey =:addOnPrimaryKey")
    void DeleteAddons(String addOnPrimaryKey);

    @Query("SELECT * FROM  order_addonchild WHERE  itemPrimaryKey =:itemPrimaryKey")
    List<OrderAddOnChild> getAddonsByItemPrimary(String itemPrimaryKey);

    @Query("SELECT * FROM order_addonchild")
    List<OrderAddOnChild> loadAlladdons();


    @Query("UPDATE  order_addonchild SET  OrderId =:OrderId WHERE addonId=:addonId")
    void UpdateOrderId(String OrderId,String addonId);

    @Query("UPDATE order_addonchild SET isSyncSelect=:isSyncSelect WHERE addonId=:addonId AND ItemIdAddon=:ItemIdAddon AND addonGroupId=:addonGroupId")
    void updateIsSyncInAddons(boolean isSyncSelect,String addonId,String ItemIdAddon,String addonGroupId);

/*
    @Query("UPDATE  order_addonchild SET  isSelect =:isSelect WHERE addonId=:addonId")
    void UpdateSelectedStatus(String isSelect,String addonId);*/

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(OrderAddOnChild bean);
}
