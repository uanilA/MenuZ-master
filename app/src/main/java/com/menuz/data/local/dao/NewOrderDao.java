package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.NewOrderModel;

import java.util.List;

/**
 * Created by mindiii on 15/11/18.
 */
@Dao
public interface NewOrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NewOrderModel bean);

    @Query("SELECT * FROM new_order ORDER BY OrderId")
    List<NewOrderModel> loadOrderId();

    @Query("SELECT * FROM new_order")
    List<NewOrderModel> loadAllOrderId();

    @Query("SELECT * FROM new_order")
    NewOrderModel geloadOrderId();

    @Query("SELECT * FROM new_order WHERE OrderId=:OrderId")
    NewOrderModel loadDataOnOrderId(String OrderId);

    @Query("UPDATE  new_order SET Nuofguest=:Nuofguest WHERE OrderId=:OrderId")
    void updateOrder(String OrderId,String Nuofguest);

    @Query("DELETE FROM new_order WHERE OrderId=:OrderId")
    void deleteByOrderId(String OrderId);

    @Query("UPDATE  new_order SET  zoneName=:zoneName,zoneId=:zoneId,tableId=:tableId WHERE OrderId=:OrderId")
    void updatetableandZone(String OrderId,String zoneName,String zoneId,String tableId);

    @Query("UPDATE  new_order SET  employeeName=:employeeName,Nuofguest=:Nuofguest,zoneName=:zoneName,zoneId=:zoneId,tableId=:tableId WHERE OrderId=:OrderId")
    void updatenuofguestandEmployee(String OrderId,String employeeName,String Nuofguest,String zoneName,String zoneId,String tableId);





}
