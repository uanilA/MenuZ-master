package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.AddOnModel;


import java.util.List;

/**
 * Created by mindiii on 25/10/18.
 */

@Dao
public interface AddOnDao {

    @Query("SELECT * FROM addon WHERE addOnItemID =:itemId")
    List<AddOnModel> loadallAddon(String itemId);

    @Query("SELECT * FROM addon WHERE addOnItemID =:addOnItemID AND OrderId=:OrderId")
    List<AddOnModel> loadAddonsByItemId(String addOnItemID, String OrderId);

    @Query("UPDATE addon SET OrderId=:OrderId")
    void update(String OrderId);


    //UPDATE addon SET 'isSelect' = '0' WHERE 'addonsGroupName' = 'Couple Eggs';
    @Query("UPDATE addon SET isSelect=:isSelect WHERE addonsGroupPriId=:addonsGroupPriId AND addonsGroupId=:addonsGroupId")
    void updateAddonsIsSelect(boolean isSelect,int addonsGroupPriId,String addonsGroupId);


    @Query("SELECT * FROM addon")
    List<AddOnModel> loadAllItem();


    @Query("SELECT * FROM addon WHERE addOnItemID =:addOnItemID")
    List<AddOnModel> loadallAddonByitem(String addOnItemID);



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AddOnModel bean);

}
