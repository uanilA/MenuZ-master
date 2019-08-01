package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.EmployeeModel;


import java.util.List;

/**
 * Created by mindiii on 22/10/18.
 */
@Dao
public interface EmployeeDao {
    @Query("SELECT * FROM employee")
    List<EmployeeModel> loadEmployee();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EmployeeModel bean);


}
