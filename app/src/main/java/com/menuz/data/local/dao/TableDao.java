package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.TableModel;

import java.util.List;

/**
 * Created by mindiii on 18/10/18.
 */

@Dao
public interface TableDao {

    @Query("SELECT * FROM tables WHERE tableZoneId =:zoneId")
    List<TableModel> loadAllByIds(String zoneId);

    @Query("SELECT * FROM tables ORDER BY tableId")
    List<TableModel> loadAlltables();


    @Query("SELECT * FROM tables")
    List<TableModel> loadAlltablesData();



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TableModel bean);

    @Query("UPDATE tables SET isBusy=:isBusy WHERE tableId=:tableId")
    void updateTable(String isBusy,String tableId);

    @Query("UPDATE tables SET isLock=:isLock WHERE tableId=:tableId")
    void updateTableLock(String isLock,String tableId);







}
