package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.PayMethodsModel;

import java.util.List;

@Dao
public interface PaymethodsDao {
    @Query("SELECT * FROM paymethods WHERE OrderId=:OrderId")
    List<PayMethodsModel> loadAllPaymentType(String OrderId);

    @Query("SELECT * FROM paymethods WHERE OrderId=:OrderId limit 1")
    PayMethodsModel loadSinglePayment(String OrderId);

    @Query("SELECT * FROM paymethods")
    List<PayMethodsModel> loadAllPayment();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPaymemt(PayMethodsModel bean);

    @Query("UPDATE paymethods SET OrderId=:OrderId WHERE termId=:termId")
    void updateOrderId(String OrderId,String termId);

    @Query("UPDATE paymethods SET paidAmt=:amt WHERE OrderId=:OrderId")
    void updateOrderAmt(String amt, String OrderId);

    @Query("DELETE  FROM paymethods")
    void deletePaymethods();
}
