package com.menuz.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.menuz.data.local.dao.AddOnChildDao;
import com.menuz.data.local.dao.AddOnDao;
import com.menuz.data.local.dao.AddonPreprationDao;
import com.menuz.data.local.dao.EmployeeDao;
import com.menuz.data.local.dao.ItemDao;
import com.menuz.data.local.dao.ItemPreprationDao;
import com.menuz.data.local.dao.MenuDao;
import com.menuz.data.local.dao.NewOrderDao;
import com.menuz.data.local.dao.OrderAddonChildDao;
import com.menuz.data.local.dao.OrderAddonDao;
import com.menuz.data.local.dao.OrderEmployeeDao;
import com.menuz.data.local.dao.OrderItemDao;
import com.menuz.data.local.dao.OrderMenuDao;
import com.menuz.data.local.dao.OrderPreparationDao;
import com.menuz.data.local.dao.OrderPreparationsAddonDao;
import com.menuz.data.local.dao.OrderTableDao;
import com.menuz.data.local.dao.OrderZoneDao;
import com.menuz.data.local.dao.PaymentDao;
import com.menuz.data.local.dao.PaymethodsDao;
import com.menuz.data.local.dao.PrefixDao;
import com.menuz.data.local.dao.PreparationDao;
import com.menuz.data.local.dao.PriceDao;
import com.menuz.data.local.dao.PricevalueDao;
import com.menuz.data.local.dao.TableDao;
import com.menuz.data.local.dao.ZoneDao;
import com.menuz.data.model.db.AddOnModel;
import com.menuz.data.model.db.AdddonChildModel;
import com.menuz.data.model.db.AddonPreprationModel;
import com.menuz.data.model.db.EmployeeModel;
import com.menuz.data.model.db.ItemModel;
import com.menuz.data.model.db.ItemPreprationModel;
import com.menuz.data.model.db.MenuModel;
import com.menuz.data.model.db.NewOrderModel;
import com.menuz.data.model.db.OrderAddOnChild;
import com.menuz.data.model.db.OrderAddOnModel;
import com.menuz.data.model.db.OrderEmployeeModel;
import com.menuz.data.model.db.OrderItemModel;
import com.menuz.data.model.db.OrderMenuModel;
import com.menuz.data.model.db.OrderPreparationAddonModel;
import com.menuz.data.model.db.OrderPreparationModel;
import com.menuz.data.model.db.OrderTableModel;
import com.menuz.data.model.db.OrderZoneModel;
import com.menuz.data.model.db.PayMethodsModel;
import com.menuz.data.model.db.PaymentModel;
import com.menuz.data.model.db.PrefixModel;
import com.menuz.data.model.db.PreparationModel;
import com.menuz.data.model.db.PriceModel;
import com.menuz.data.model.db.PricevaluesModel;
import com.menuz.data.model.db.TableModel;
import com.menuz.data.model.db.Zonemodel;


@Database(entities = {TableModel.class, Zonemodel.class, EmployeeModel.class, MenuModel.class, ItemModel.class, PreparationModel.class, AddOnModel.class, AdddonChildModel.class, PrefixModel.class, OrderTableModel.class, OrderZoneModel.class, OrderEmployeeModel.class, OrderMenuModel.class, OrderItemModel.class, OrderPreparationModel.class, OrderAddOnModel.class, OrderAddOnChild.class, OrderPreparationAddonModel.class, NewOrderModel.class, PaymentModel.class, PriceModel.class, PricevaluesModel.class,AddonPreprationModel.class,ItemPreprationModel.class,PayMethodsModel.class},
        version = 10, exportSchema = false)
abstract  class Appdatabase extends RoomDatabase {
    private static Appdatabase mAppDatabase;

    synchronized static Appdatabase getDatabaseInstance(Context context) {
        if (mAppDatabase == null) {
            mAppDatabase = Room.databaseBuilder(context, Appdatabase.class, "MenuZDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mAppDatabase;
    }


    abstract TableDao tableDao();

    abstract ZoneDao zoneDao();

    abstract EmployeeDao employeeDao();

    abstract MenuDao menuDao();

    abstract ItemDao itemDao();

    abstract PreparationDao preparationDao();

    abstract AddOnChildDao addOnChildDao();

    abstract AddOnDao addOnDao();

    abstract PrefixDao prefixDao();

    abstract OrderZoneDao orderZoneDao();

    abstract OrderTableDao orderTableDao();

    abstract OrderEmployeeDao orderEmployeeDao();

    abstract OrderItemDao orderItemDao();

    abstract OrderMenuDao orderMenuDao();

    abstract OrderPreparationDao orderPreparationDao();

    abstract OrderAddonDao orderAddonDao();

    abstract OrderAddonChildDao orderAddonChildDao();

    abstract OrderPreparationsAddonDao orderPreparationsAddonDao();

    abstract NewOrderDao newOrderDao();

    abstract PaymentDao paymentDao();

    abstract PriceDao priceDao();

    abstract PricevalueDao pricevalueDao();

    abstract AddonPreprationDao addonPreprationDao();

    abstract ItemPreprationDao itemPreprationDao();

    abstract PaymethodsDao paymethodsDao();







}
