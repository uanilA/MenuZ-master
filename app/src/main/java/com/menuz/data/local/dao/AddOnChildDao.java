package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.AdddonChildModel;

import java.util.List;

/**
 * Created by mindiii on 25/10/18.
 */
@Dao
public interface AddOnChildDao {

    @Query("SELECT * FROM addonchild WHERE addOnItemIdchild =:addonsGroupId AND ItemIdAddon =:addonIdItem")
    List<AdddonChildModel> loadallAddonChiild(String addonsGroupId,String addonIdItem);

    @Query("SELECT * FROM addonchild")
    List<AdddonChildModel> loadAllItem();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AdddonChildModel bean);

    @Query("UPDATE addonchild SET OrderId=:OrderId")
    void update(String OrderId);

    @Query("SELECT * FROM addonchild WHERE addOnItemIdchild =:addOnItemIdchild AND ItemIdAddon=:ItemIdAddon")
    List<AdddonChildModel> loadallAddonsByitemId(String addOnItemIdchild, String ItemIdAddon);

    @Query("UPDATE addonchild SET addonRemark=:addonRemark WHERE addonId=:addonId")
    void updateRemarkofAddon(String addonRemark,String addonId);

    @Query("SELECT * FROM addonchild WHERE addonId =:addonId")
    AdddonChildModel getDetail(String addonId);

    @Query("UPDATE addonchild SET isSyncSelect=:isSyncSelect WHERE addonId=:addonId AND ItemIdAddon=:ItemIdAddon AND addOnItemIdchild=:addOnItemIdchild")
    void updateIsSync(boolean isSyncSelect,String addonId,String ItemIdAddon,String addOnItemIdchild);





}
