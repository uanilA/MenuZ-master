package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.AdddonChildModel;
import com.menuz.data.model.db.AddonPreprationModel;
import com.menuz.data.model.db.PrefixModel;

import java.util.List;

/**
 * Created by mindiii on 26/10/18.
 */
@Dao
public interface PrefixDao {

    @Query("SELECT * FROM prefix")
    List<PrefixModel> loadAllPrefix();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PrefixModel bean);

    @Query("SELECT * FROM prefix WHERE prefixId =:prefix")
    PrefixModel getDetailPrefix(String prefix);

}
