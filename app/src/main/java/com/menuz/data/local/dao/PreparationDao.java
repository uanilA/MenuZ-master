package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import com.menuz.data.model.db.PreparationModel;

import java.util.List;

/**
 * Created by mindiii on 24/10/18.
 */
@Dao
public interface PreparationDao {
    @Query("SELECT * FROM preparation")
    List<PreparationModel> loadPreparation();

    @Query("SELECT * FROM preparation WHERE preparationId IN (:preparationId)")
    List<PreparationModel> loadAllByIds(List<String> preparationId);

    @Query("SELECT * FROM preparation WHERE preparationId IN (:preparationId)")
    List<PreparationModel> loadAllByPrepId(String preparationId);

    @Query("SELECT * FROM preparation WHERE preparationId =:preparationId")
    PreparationModel loadByPrepId(String preparationId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PreparationModel bean);
}
