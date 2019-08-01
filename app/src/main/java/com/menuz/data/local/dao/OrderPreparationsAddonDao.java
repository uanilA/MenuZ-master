package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.AdddonChildModel;
import com.menuz.data.model.db.OrderAddOnChild;
import com.menuz.data.model.db.OrderPreparationAddonModel;
import com.menuz.data.model.db.OrderPreparationModel;

import java.util.List;

/**
 * Created by mindiii on 31/10/18.
 */
@Dao
public interface OrderPreparationsAddonDao {
    @Query("SELECT * FROM order_addon_prep")
    List<OrderPreparationAddonModel> loadPreparation();

    @Query("SELECT * FROM order_addon_prep WHERE addonsGroupId=:aaddonsGroupId AND ItemIdAddon=:ItemIdAddon AND addonId =:addonId AND isSelect=:isSelect AND OrderId=:OrderId")
    List<OrderPreparationAddonModel> loadAllByAddonId(String aaddonsGroupId,String ItemIdAddon,String addonId,boolean isSelect,String OrderId);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(OrderPreparationAddonModel bean);

    @Query("SELECT * FROM order_addon_prep WHERE ItemIdAddon=:ItemIdAddon AND addonsGroupId=:addonsGroupId AND addonId=:addonId AND OrderId=:OrderId")
    List<OrderPreparationAddonModel> loadallAddonByitem(String ItemIdAddon, String addonsGroupId,String addonId,String OrderId);

    @Query("UPDATE order_addon_prep SET isSelect=:isSelect WHERE addonsGroupId = :addonsGroupId AND OrderId=:OrderId AND ItemIdAddon=:ItemIdAddon")
    void updateAddonPrepration(boolean isSelect,String addonsGroupId,String OrderId,String ItemIdAddon);


    @Query("UPDATE order_addon_prep SET   prefixName=:prefixName , prefixId=:prefixId WHERE addonsGroupId = :addonsGroupId AND ItemIdAddon=:ItemIdAddon AND addonId=:addonId AND preparationId=:preparationId AND OrderId=:OrderId")
    void updatePrepration(String addonsGroupId,String ItemIdAddon,String addonId,String preparationId,String OrderId,String prefixName,String prefixId );

    @Query("UPDATE order_addon_prep SET isSelect=:isSelect WHERE addonsGroupId = :addonsGroupId AND ItemIdAddon=:ItemIdAddon AND addonId=:addonId AND OrderId=:OrderId")
    void updatePreprationondelete(boolean isSelect,String addonsGroupId,String ItemIdAddon,String addonId,String OrderId);


    @Query("DELETE FROM order_addon_prep WHERE isSelect=:isSelect AND addonsGroupId = :addonsGroupId AND ItemIdAddon=:ItemIdAddon AND addonId=:addonId AND preparationId=:preparationId AND OrderId=:OrderId")

    void deletePrepration(boolean isSelect,String addonsGroupId,String ItemIdAddon,String addonId,String preparationId,String OrderId );


    @Query("DELETE FROM order_addon_prep WHERE ItemIdAddon = :ItemIdAddon")
    void deleteByAddonPrep(String ItemIdAddon);

    @Query("SELECT * FROM order_addon_prep WHERE ItemIdAddon=:ItemIdAddon  AND isSelect=:isSelect AND OrderId=:OrderId")
    List<OrderPreparationAddonModel> loadallAddonByitemId(String ItemIdAddon, boolean isSelect, String OrderId);

    @Query("SELECT * FROM order_addon_prep WHERE ItemIdAddon=:ItemIdAddon  AND isSelect=:isSelect AND addonId=:addonId AND OrderId=:OrderId")
    List<OrderPreparationAddonModel> loadallAddonByitemId(String ItemIdAddon, String addonId, boolean isSelect, String OrderId);

    @Query("SELECT DISTINCT(order_addon_prep.preparationName) AS preparationName, order_addon_prep.* FROM order_addon_prep as order_addon_prep WHERE ItemIdAddon=:ItemIdAddon  AND addonId=:addonId AND OrderId=:OrderId AND itemPrimaryKey=:itemPrimaryKey")
    List<OrderPreparationAddonModel> loadallAddongroup(String ItemIdAddon, String addonId, String OrderId,String itemPrimaryKey);


    @Query("UPDATE   order_addon_prep SET  orderPrepAddOnPrimaryKey =:orderPrepAddOnPrimaryKey")
    void  UpdateAddonPrepration(String orderPrepAddOnPrimaryKey);


    @Query("DELETE FROM   order_addon_prep WHERE  orderPrepAddOnPrimaryKey =:orderPrepAddOnPrimaryKey")
    void  deleteAddonPrepration(String orderPrepAddOnPrimaryKey);


    @Query("SELECT * FROM   order_addon_prep WHERE  itemPrimaryKey =:itemPrimaryKey")
    List<OrderPreparationAddonModel>  getOrderAddonsPrep(String itemPrimaryKey);

    @Query("UPDATE order_addon_prep SET OrderId=:OrderId WHERE addonsGroupId = :addonsGroupId AND ItemIdAddon=:ItemIdAddon AND addonId=:addonId")
    void upadteOrderId(String addonsGroupId,String ItemIdAddon,String addonId,String OrderId);





}
