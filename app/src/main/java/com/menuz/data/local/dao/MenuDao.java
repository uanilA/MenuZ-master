package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.EmployeeModel;
import com.menuz.data.model.db.MenuModel;
import com.menuz.data.model.db.TableModel;

import java.util.List;

/**
 * Created by mindiii on 23/10/18.
 */
@Dao
public interface MenuDao {
    @Query("SELECT * FROM menu")
    List<MenuModel> loadAlltables();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MenuModel bean);

}
