package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.EmployeeModel;
import com.menuz.data.model.db.NewOrderModel;
import com.menuz.data.model.db.OrderEmployeeModel;

import java.util.List;

/**
 * Created by mindiii on 29/10/18.
 */
@Dao
public interface OrderEmployeeDao {

    @Query("SELECT * FROM order_employee WHERE OrderId=:OrderId")
   OrderEmployeeModel loadOrderEmployee(String OrderId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(OrderEmployeeModel bean);



    @Query("UPDATE order_employee SET Nuofguest=:Nuofguest WHERE OrderId=:OrderId")
    void updateOrderinOrderEmployee(String Nuofguest,String OrderId);




}
