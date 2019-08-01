package com.menuz.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.menuz.data.model.db.PaymentModel;

import java.util.List;

/**
 * Created by mindiii on 6/12/18.
 */
@Dao
public interface PaymentDao {

    @Query("SELECT * FROM order_payment WHERE OrderId=:OrderId")
    List<PaymentModel> loadPaymentRef(String OrderId);

    @Query("UPDATE order_payment SET OrderId=:OrderId WHERE termId=:termId")
    void updateOrderIdPayment(String OrderId,String termId);

    @Query("UPDATE order_payment SET payMethodId=:payMethodId , payMethodFixValue=:payMethodFixValue , payMethodName=:payMethodName , payMethodType=:payMethodType , cardno=:cardno , month=:month , year=:year , securitycode=:securitycode , Id=:Id , phone=:phone WHERE OrderId=:OrderId")
    void updatePaymentmethods(String OrderId,String payMethodId,String payMethodFixValue,String payMethodName,String payMethodType,String cardno,String month,String year,String securitycode,String Id,String phone);


    @Query("SELECT * FROM order_payment")
    List<PaymentModel> loadAllPayments();


    @Query("DELETE  FROM order_payment")
    void deleteFromPayment();



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PaymentModel bean);
}
