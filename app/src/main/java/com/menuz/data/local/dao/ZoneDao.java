package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.TableModel;
import com.menuz.data.model.db.Zonemodel;

import java.util.List;

/**
 * Created by mindiii on 17/10/18.
 */

@Dao
public interface ZoneDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Zonemodel zonemodel);

    @Query("SELECT * FROM zoneTable ORDER BY zoneId Asc")
    List<Zonemodel> loadsallZone();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Zonemodel> zonemodelList);

    @Query("SELECT zoneTable.* FROM tables as tables JOIN zoneTable as zoneTable ON tables.tableZoneId=zoneTable.zoneId WHERE tables.tableId=:tableId")
    List<Zonemodel>getZoneDetail(String tableId);


}
