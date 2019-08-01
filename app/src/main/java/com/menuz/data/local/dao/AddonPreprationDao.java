package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.AddonPreprationModel;
import com.menuz.data.model.db.OrderPreparationAddonModel;

import java.util.List;

@Dao
public interface AddonPreprationDao {


    @Query("DELETE  FROM addonprepration")
    void deleteAddonPrepration();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAddonPrep(AddonPreprationModel bean);

    @Query("SELECT DISTINCT(addonprepration.preparationName) AS preparationName, addonprepration.* FROM addonprepration AS addonprepration WHERE ItemIdAddon=:ItemIdAddon AND addonsGroupId=:addonsGroupId AND addonId=:addonId")
    List<AddonPreprationModel> loadAllpreprationByAddon(String ItemIdAddon, String addonsGroupId,String addonId);

    @Query("SELECT * FROM addonprepration WHERE preparationId =:preparationId")
    AddonPreprationModel getDetailPrepration(String preparationId);


}
