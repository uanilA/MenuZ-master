package com.menuz.data.local;

import android.content.Context;

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

import java.util.List;



public final class AppDbHelper  implements DbHelper {

    private static AppDbHelper instance;

    private final Appdatabase mAppDatabase;

    private AppDbHelper(Context context) {
        this.mAppDatabase = Appdatabase.getDatabaseInstance(context);
    }

    public synchronized static DbHelper getDbInstance(Context context) {
        if (instance == null) {
            instance = new AppDbHelper(context);
        }
        return instance;
    }


    @Override
    public List<TableModel> getalltable(String zoneId) {
        return mAppDatabase.tableDao().loadAllByIds(zoneId);
    }

    @Override
    public Boolean insertZone(Zonemodel zonesBean) {
        mAppDatabase.zoneDao().insert(zonesBean);
        return true;

    }

    @Override
    public void insertZoneTable(TableModel tablemodel) {
        mAppDatabase.tableDao().insert(tablemodel);

    }

    @Override
    public void insertAllZoneList(List<Zonemodel> zonemodels) {
        mAppDatabase.zoneDao().insertAll(zonemodels);
    }

    @Override
    public List<TableModel> getalltable() {
        return mAppDatabase.tableDao().loadAlltables();
    }

    @Override
    public List<Zonemodel> getallZone() {
        return mAppDatabase.zoneDao().loadsallZone();
    }

    @Override
    public List<EmployeeModel> getAllEmp() {
        return mAppDatabase.employeeDao().loadEmployee();
    }

    @Override
    public void insertEmployee(EmployeeModel employeeModel) {
        mAppDatabase.employeeDao().insert(employeeModel);
    }

    @Override
    public void insertMenu(MenuModel menuModel) {
        mAppDatabase.menuDao().insert(menuModel);
    }

    @Override
    public List<MenuModel> getallMenu() {
        return mAppDatabase.menuDao().loadAlltables();
    }

    @Override
    public void insertItem(ItemModel itemModel) {
        mAppDatabase.itemDao().insert(itemModel);
    }

    @Override
    public List<ItemModel> getallItem(String groupId) {
        return mAppDatabase.itemDao().loadAllByItem(groupId);
    }

    @Override
    public void insertPreparation(PreparationModel preparationModel) {
        mAppDatabase.preparationDao().insert(preparationModel);
    }

    @Override
    public List<PreparationModel> getallDatabtId(List<String> array) {
        return mAppDatabase.preparationDao().loadAllByIds(array);
    }

    @Override
    public List<PreparationModel> getallPrepation() {
        return mAppDatabase.preparationDao().loadPreparation();
    }

    @Override
    public void insetAddon(AddOnModel addOnModel) {
        mAppDatabase.addOnDao().insert(addOnModel);
    }

    @Override
    public List<AddOnModel> getAddons(String itemId) {
        return mAppDatabase.addOnDao().loadallAddon(itemId);
    }

    @Override
    public void insertAddOnChild(AdddonChildModel adddonChildModel) {
        mAppDatabase.addOnChildDao().insert(adddonChildModel);
    }

    @Override
    public List<AdddonChildModel> getAddOnChild(String addOnId, String itemIdAdoons) {
        return mAppDatabase.addOnChildDao().loadallAddonChiild(addOnId,itemIdAdoons);
    }


    @Override
    public void insertPrefix(PrefixModel prefixModel) {
        mAppDatabase.prefixDao().insert(prefixModel);
    }

    @Override
    public List<PrefixModel> getAllPrefix() {
        return mAppDatabase.prefixDao().loadAllPrefix();
    }

    @Override
    public void insertOrderZone(OrderZoneModel orderZoneModel) {
        mAppDatabase.orderZoneDao().insert(orderZoneModel);
    }

    @Override
    public OrderZoneModel getallOrderZone(String orderId) {
        return mAppDatabase.orderZoneDao().loadAllOrderZone(orderId);
    }

    @Override
    public void insertOrderTable(OrderTableModel orderTableModel) {
        mAppDatabase.orderTableDao().insert(orderTableModel);
    }

    @Override
    public OrderTableModel getallOrderTable(String orderId) {
        return mAppDatabase.orderTableDao().loadAllordertables(orderId);
    }


    @Override
    public void insertOrderEmployee(OrderEmployeeModel orderEmployeeModel) {
        mAppDatabase.orderEmployeeDao().insert(orderEmployeeModel);
    }

    @Override
    public OrderEmployeeModel getAllOrderEmployee(String orderId) {
        return mAppDatabase.orderEmployeeDao().loadOrderEmployee(orderId);
    }


    @Override
    public void insertOrderItem(OrderItemModel orderItemModel) {
        mAppDatabase.orderItemDao().insert(orderItemModel);
    }

    @Override
    public List<OrderItemModel> getAllorderItem(String OrderId) {
        return mAppDatabase.orderItemDao().loadAllorderItem(OrderId);
    }

    @Override
    public void insertOrderMenu(OrderMenuModel orderMenuModel) {
        mAppDatabase.orderMenuDao().insert(orderMenuModel);
    }

    @Override
    public List<OrderMenuModel> getAllOrderMenu() {
        return mAppDatabase.orderMenuDao().loadAllMenu();
    }

    @Override
    public void insertOrderPrearation(OrderPreparationModel orderPreparationModel) {
        mAppDatabase.orderPreparationDao().insert(orderPreparationModel);
    }

    @Override
    public List<OrderPreparationModel> getAllpreparation() {
        return mAppDatabase.orderPreparationDao().loadPreparation();
    }

    @Override
    public void insertOrderAddOn(OrderAddOnModel orderAddOnModel) {
        mAppDatabase.orderAddonDao().insert(orderAddOnModel);
    }

    @Override
    public List<OrderAddOnModel> getAllOrderAddOn(String OrderId) {
        return mAppDatabase.orderAddonDao().loadAllItem(OrderId);
    }

    @Override
    public void insertAddonChild(OrderAddOnChild orderAddOnChild) {
        mAppDatabase.orderAddonChildDao().insert(orderAddOnChild);
    }

    @Override
    public List<OrderAddOnChild> getAllOrderAddonChild(String OrderId) {
        return mAppDatabase.orderAddonChildDao().loadAllItem(OrderId);
    }

    @Override
    public void deleteByItemId(String itemIDAddon, String addonsGroupId, String addonId) {
        mAppDatabase.orderAddonChildDao().deletebyItemID(itemIDAddon,addonsGroupId,addonId);
    }


    @Override
    public void deleteByPrep(String prepId) {
        mAppDatabase.orderAddonChildDao().deletebyItemPrep(prepId);
    }

    @Override
    public void update(boolean isSelect, String addOnGrupId, String OrderId, String itemId) {
        mAppDatabase.orderAddonChildDao().update(isSelect, addOnGrupId, OrderId, itemId);
    }

    @Override
    public List<OrderAddOnChild> getDataById(String addoNId, String OrderId) {
        return mAppDatabase.orderAddonChildDao().loadallOrderAddonChiild(addoNId,OrderId);
    }

    @Override
    public void insertOrderAddonPreparation(OrderPreparationAddonModel orderPreparationAddonModel) {
        mAppDatabase.orderPreparationsAddonDao().insert(orderPreparationAddonModel);

    }

    @Override
    public List<OrderPreparationAddonModel> loadAllByAddonId(String addonsGroupId, String ItemidAddon, String addonId, boolean isSelect, String OrderId) {
        return mAppDatabase.orderPreparationsAddonDao().loadAllByAddonId(addonsGroupId,ItemidAddon,addonId,isSelect, OrderId);
    }


    @Override
    public List<PreparationModel> loadAllByAddonPrepId(String prepaID) {
        return mAppDatabase.preparationDao().loadAllByPrepId(prepaID);
    }

    @Override
    public PreparationModel loadByPrepId(String preparationId) {
        return mAppDatabase.preparationDao().loadByPrepId(preparationId);
    }

    @Override
    public List<OrderAddOnChild> loadAllItemSelected(boolean isSelect,String OrderId) {
        return mAppDatabase.orderAddonChildDao().loadAllItemSelected(isSelect,OrderId);
    }

    @Override
    public List<OrderAddOnModel> loadallAddonByItem(String itemID,String OrderId) {
        return mAppDatabase.orderAddonDao().loadallAddonByitem(itemID,OrderId);
    }

    @Override
    public List<OrderAddOnChild> loadallAddonByaddonItemID(String addonItemID, String addonItemId,String OrderId) {
        return mAppDatabase.orderAddonChildDao().loadallAddonByitem(addonItemID,addonItemId,OrderId);
    }


    @Override
    public List<OrderItemModel> loadByIteId(String itemId,String OrderId) {
        return mAppDatabase.orderItemDao().getItem(itemId,OrderId);
    }

    @Override
    public List<OrderPreparationAddonModel> getprearationByAddonAndItemId(String ItemId, String addongroupId, String addonid,String OrderId) {
        return mAppDatabase.orderPreparationsAddonDao().loadallAddonByitem(ItemId,addongroupId,addonid,OrderId);
    }

    @Override
    public void updateAddonGroup(boolean isSelect, String addonGroupId,String OrderId,String ItemId) {
        mAppDatabase.orderAddonDao().update(isSelect,addonGroupId,OrderId,ItemId);
    }

    @Override
    public void updatePreprationAddon( String addonsGroupId, String ItemIdAddon, String addonId,String preprationId,String OrderId,String PrefixName,String prefixId) {
        mAppDatabase.orderPreparationsAddonDao().updatePrepration(addonsGroupId,ItemIdAddon,addonId,preprationId,OrderId,PrefixName,prefixId);
    }

    @Override
    public void deletePrepration(boolean isSelect, String addonsGroupId, String ItemIdAddon, String addonId, String preprationId,String OrderId) {
        mAppDatabase.orderPreparationsAddonDao().deletePrepration(isSelect,addonsGroupId,ItemIdAddon,addonId,preprationId,OrderId);
    }

    @Override
    public List<OrderPreparationModel> loadallPrepByGroupId(String itemId,String OrderId) {
        return mAppDatabase.orderPreparationDao().loadallPrepByGroupId(itemId,OrderId);
    }

    @Override
    public List<OrderPreparationModel> loadallSelectedItemOnItemId(String itemId, String OrderId, boolean isSelect) {
        return mAppDatabase.orderPreparationDao().loadallSelectedItemOnItemId(itemId,OrderId,isSelect);
    }

    @Override
    public void updateprepByItem(String itemId,String prepId,String OrderId,String prefixName,String prefixId,String addonIdN) {
        mAppDatabase.orderPreparationDao().updateprep(itemId,prepId,OrderId,prefixName,prefixId, addonIdN);
    }

    @Override
    public List<OrderPreparationModel> loadAllprepSelected(boolean isSelect,String OrderId) {
        return mAppDatabase.orderPreparationDao().loadAllprepSelected(isSelect,OrderId);
    }

    @Override
    public void updateAddons(boolean isSelect, String addonGroupId, String itemId,String OrderId) {
        mAppDatabase.orderAddonChildDao().updateAddon(isSelect,addonGroupId,itemId,OrderId);
    }

    @Override
    public void updatePreprationondelete(boolean isSelect, String addonGroupId, String itemId,String addonId,String OrderId) {
        mAppDatabase.orderPreparationsAddonDao().updatePreprationondelete(isSelect,addonGroupId,itemId,addonId,OrderId);
    }

    @Override
    public void updatecount( String countPrice,String itemId) {
        mAppDatabase.orderItemDao().updatecount(countPrice,itemId);
    }

    @Override
    public OrderItemModel loadCount(String itemId) {
        return mAppDatabase.orderItemDao().loadCount(itemId);
    }

    @Override
    public void deleteByItemId(String itemId) {
        mAppDatabase.orderItemDao().deleteByItemId(itemId);
    }

    @Override
    public void deleteByAddon(String itemId) {
        mAppDatabase.orderAddonDao().deleteByAddon(itemId);
    }

    @Override
    public void deleteByAddonChild(String itemId) {
        mAppDatabase.orderAddonChildDao().deleteByAddon(itemId);
    }

    @Override
    public void deleteByAddonPrep(String itemId) {
        mAppDatabase.orderPreparationsAddonDao().deleteByAddonPrep(itemId);
    }

    @Override
    public void updateTotal(String Grandtotal) {
        mAppDatabase.orderItemDao().updateTotal(Grandtotal);
    }

    @Override
    public void insertNewOrder(NewOrderModel newOrderModel) {
        mAppDatabase.newOrderDao().insert(newOrderModel);
    }

    @Override
    public List<NewOrderModel> loadnewOrderId() {
        return mAppDatabase.newOrderDao().loadOrderId();
    }

    @Override
    public void updateAddonPrepration(boolean isSelect, String addonsGroupId, String OrderId, String addOnItemID) {
        mAppDatabase.orderPreparationsAddonDao().updateAddonPrepration(isSelect,addonsGroupId,OrderId,addOnItemID);
    }

    @Override
    public void clearAllTable() {
        mAppDatabase.clearAllTables();
    }

    @Override
    public NewOrderModel loadDataOnOrderId(String orderid) {
        return mAppDatabase.newOrderDao().loadDataOnOrderId(orderid);
    }

    @Override
    public void updateOrder(String OrderId, String NuofGuest) {
        mAppDatabase.newOrderDao().updateOrder(OrderId,NuofGuest);
    }

    @Override
    public void updateOrderinOrderEmployee(String NuofGuest,String OrderId) {
        mAppDatabase.orderEmployeeDao().updateOrderinOrderEmployee(NuofGuest,OrderId);
    }

    @Override
    public void updateMySelectionAddonChild(boolean mySelect, String addonId) {
       mAppDatabase.addOnChildDao().updateMySelectionAddonChild(mySelect,addonId);
    }

    @Override
    public void deleteByOrderId(String OrderId) {
        mAppDatabase.newOrderDao().deleteByOrderId(OrderId);
    }

    @Override
    public void updatetableandZone(String OrderId, String zoneName, String zoneId, String tableId) {
        mAppDatabase.newOrderDao().updatetableandZone(OrderId,zoneName,zoneId,tableId);
    }

    @Override
    public void updatenuofguestandEmployee(String OrderId, String employeeName, String Nuofguest, String zoneName, String zoneId, String tableId) {
        mAppDatabase.newOrderDao().updatenuofguestandEmployee(OrderId,employeeName,Nuofguest,zoneName,zoneId,tableId);
    }

    @Override
    public void updateOrderTable(String OrderId, String tableId) {
        mAppDatabase.orderTableDao().updateOrderTable(OrderId,tableId);
    }

    @Override
    public List<OrderMenuModel> loadAllorderMenu(String OrderId) {
        return mAppDatabase.orderMenuDao().loadAllorderMenu(OrderId);
    }

    @Override
    public List<OrderAddOnChild> loadSelectedAddonByItemId(String ItemIdAddon, String OrderId, boolean isSelect) {
        return mAppDatabase.orderAddonChildDao().loadSelectedAddonByItemId(ItemIdAddon, OrderId, isSelect);
    }

    @Override
    public List<OrderPreparationAddonModel> loadallAddonByitemId(String ItemIdAddon, boolean isSelect, String OrderId) {
        return mAppDatabase.orderPreparationsAddonDao().loadallAddonByitemId(ItemIdAddon, isSelect, OrderId);
    }

    @Override
    public List<OrderPreparationAddonModel> loadallAddonByitemId(String ItemIdAddon, String addonId, boolean isSelect, String OrderId) {
        return mAppDatabase.orderPreparationsAddonDao().loadallAddonByitemId(ItemIdAddon, addonId, isSelect, OrderId);
    }

    @Override
    public void insert(PaymentModel bean) {
        mAppDatabase.paymentDao().insert(bean);
    }

    @Override
    public List<PayMethodsModel> loadAllPaymentType(String OrderId) {
        return mAppDatabase.paymethodsDao().loadAllPaymentType(OrderId);
    }

    @Override
    public PayMethodsModel loadSinglePayment(String OrderId) {
        return mAppDatabase.paymethodsDao().loadSinglePayment(OrderId);
    }

    @Override
    public List<OrderTableModel> loadAllTable() {
        return mAppDatabase.orderTableDao().loadAllTable();
    }

    @Override
    public void updateOrderIdinItem(String OrderId, String itemId) {
        mAppDatabase.orderItemDao().updateOrderIdinItem(OrderId,itemId);
    }

    @Override
    public void updateItemPrepOrder(String OrderId, String preparationId,String itemId) {
        mAppDatabase.itemPreprationDao().updateItemPrepOrder(OrderId,preparationId,itemId);
    }

    @Override
    public List<ItemPreprationModel> loadAllPrep() {
        return mAppDatabase.itemPreprationDao().loadAllPrep();
    }

    @Override
    public List<OrderItemModel> getAllItems() {
        return mAppDatabase.orderItemDao().getAllItems();
    }

    @Override
    public void UpdateOrderId(String OrderId, String addonId) {
      mAppDatabase.orderAddonChildDao().UpdateOrderId(OrderId,addonId);
    }

    @Override
    public List<OrderAddOnChild> loadAlladdons() {
        return mAppDatabase.orderAddonChildDao().loadAlladdons();
    }

    @Override
    public List<OrderPreparationAddonModel> loadPreparation() {
        return mAppDatabase.orderPreparationsAddonDao().loadPreparation();
    }

    @Override
    public void upadteOrderId(String addonsGroupId, String ItemIdAddon, String addonId, String OrderId) {
       mAppDatabase.orderPreparationsAddonDao().upadteOrderId(addonsGroupId,ItemIdAddon,addonId,OrderId);
    }

    @Override
    public void deletePaymethods() {
        mAppDatabase.paymethodsDao().deletePaymethods();
    }

    @Override
    public void updateOrderIdPayment(String OrderId, String termId) {
        mAppDatabase.paymentDao().updateOrderIdPayment(OrderId,termId);
    }

    @Override
    public List<PaymentModel> loadAllPayments() {
        return mAppDatabase.paymentDao().loadAllPayments();
    }

    @Override
    public void updatePaymentmethods(String OrderId, String payMethodId, String payMethodFixValue, String payMethodName, String payMethodType, String cardno, String month, String year, String securitycode, String Id, String phone) {
     mAppDatabase.paymentDao().updatePaymentmethods(OrderId,payMethodId,payMethodFixValue,payMethodName,payMethodType,cardno,month,year,securitycode,Id,phone);

    }

    @Override
    public List<PaymentModel> loadPaymentRef(String OrderId) {
        return mAppDatabase.paymentDao().loadPaymentRef(OrderId);
    }

    @Override
    public void deleteFromPayment() {
        mAppDatabase.paymentDao().deleteFromPayment();
    }

    @Override
    public void updateTableLock(String isLock, String tableId) {
        mAppDatabase.tableDao().updateTableLock(isLock,tableId);
    }

    @Override
    public String getOrderId(String OrderId) {
        return mAppDatabase.orderItemDao().getOrderId(OrderId);
    }

    @Override
    public AddonPreprationModel getDetailPrepration(String preparationId) {
        return mAppDatabase.addonPreprationDao().getDetailPrepration(preparationId);
    }

    @Override
    public PrefixModel getDetailPrefix(String prefix) {
        return mAppDatabase.prefixDao().getDetailPrefix(prefix);
    }

    @Override
    public ItemPreprationModel getItemPreps(String preparationId) {
        return mAppDatabase.itemPreprationDao().getItemPreps(preparationId);
    }

    @Override
    public void updateIsSync(boolean isSyncSelect, String addonId, String ItemIdAddon, String addOnItemIdchild) {
        mAppDatabase.addOnChildDao().updateIsSync(isSyncSelect,addonId,ItemIdAddon,addOnItemIdchild);
    }

    @Override
    public void updateIsSyncInAddons(boolean isSyncSelect, String addonId, String ItemIdAddon, String addOnItemIdchild) {
        mAppDatabase.orderAddonChildDao().updateIsSyncInAddons(isSyncSelect,addonId,ItemIdAddon,addOnItemIdchild);
    }

    @Override
    public void updateAddonsIsSelect(boolean isSelect, int addonsGroupId,String addonGroupId) {
        mAppDatabase.addOnDao().updateAddonsIsSelect(isSelect,addonsGroupId,addonGroupId);
    }

    @Override
    public void updatePrepration(String OrderId, String preparationId) {
        mAppDatabase.orderPreparationDao().updatePrepration(OrderId,preparationId);
    }

    @Override
    public void updateSelectedAddonGroupId(String selectedAddonGroupId) {
         mAppDatabase.addOnChildDao().updateSelectedAddonGroupId(selectedAddonGroupId);
    }

    @Override
    public void update(OrderAddOnChild orderAddOnChild) {
        mAppDatabase.orderAddonChildDao().update(orderAddOnChild);
    }

  /*  @Override
    public void updateSelectedStatus(String isSelect, String addonId) {
      mAppDatabase.orderAddonChildDao().UpdateSelectedStatus(isSelect, addonId);
    }*/

    @Override
    public List<OrderAddOnChild> loadAllSelectdAddon(boolean isSelect, String OrderId, String ItemIdAddon,String addonsGroupId) {
        return mAppDatabase.orderAddonChildDao().loadAllSelectdAddon(isSelect, OrderId, ItemIdAddon,addonsGroupId);
    }

    @Override
    public void updateitemRemark(String itemRemark, String itemId, String OrderId) {
        mAppDatabase.orderItemDao().updateitemRemark(itemRemark, itemId, OrderId);
    }

    @Override
    public void updateitemPrepRemark(String itemRemark, String itemId, String OrderId) {
        mAppDatabase.orderItemDao().updateitemPrepRemark(itemRemark, itemId, OrderId);
    }

    @Override
    public void updateCart(boolean isCartSelected, String OrderId) {
        mAppDatabase.orderItemDao().updateCart(isCartSelected, OrderId);
    }

    @Override
    public List<OrderItemModel> getSelectedCart(String OrderId, boolean isCartSelected) {
        return mAppDatabase.orderItemDao().getSelectedCart(OrderId, isCartSelected);
    }

    @Override
    public NewOrderModel geloadOrderId() {
        return mAppDatabase.newOrderDao().geloadOrderId();
    }

    @Override
    public void insert(PriceModel priceModel) {
        mAppDatabase.priceDao().insert(priceModel);
    }

    @Override
    public List<PriceModel> loadPrice() {
        return mAppDatabase.priceDao().loadPrice();
    }

    @Override
    public void inserPriceValue(List<PricevaluesModel> bean) {
        mAppDatabase.pricevalueDao().inserPriceValue(bean);
    }


    @Override
    public List<PriceModel> loadPriceData() {
        return mAppDatabase.priceDao().loadPriceData();
    }

    @Override
    public void clearPriceValueData() {
        mAppDatabase.pricevalueDao().clearPriceValueData();
    }

    @Override
    public List<PricevaluesModel> loadPriceValue() {
        return mAppDatabase.pricevalueDao().loadPriceValue();
    }

    @Override
    public String loadPriceValue(String pricelistId) {
        return mAppDatabase.pricevalueDao().loadPriceValue(pricelistId);
    }

    @Override
    public List<TableModel> loadAlltablesData() {
        return mAppDatabase.tableDao().loadAlltables();
    }

    @Override
    public void updateTable(String isBusy, String tableId) {
        mAppDatabase.tableDao().updateTable(isBusy,tableId);
    }

    @Override
    public List<AddOnModel> loadallAddonByitems(String addOnItemID) {
        return mAppDatabase.addOnDao().loadallAddonByitem(addOnItemID);
    }

    @Override
    public List<AdddonChildModel> loadallAddonsByitemId(String addOnItemIdchild, String ItemIdAddon) {
        return mAppDatabase.addOnChildDao().loadallAddonsByitemId(addOnItemIdchild,ItemIdAddon);
    }

    @Override
    public void insertAddonPrep(AddonPreprationModel bean) {
        mAppDatabase.addonPreprationDao().insertAddonPrep(bean);
    }

    @Override
    public List<AddonPreprationModel> loadAllpreprationByAddon(String ItemIdAddon, String addonsGroupId, String addonId) {
        return mAppDatabase.addonPreprationDao().loadAllpreprationByAddon(ItemIdAddon,addonsGroupId,addonId);
    }

    @Override
    public List<ItemPreprationModel> loadallPrepByItemId(String itemId) {
        return mAppDatabase.itemPreprationDao().loadallPrepByItemId(itemId);
    }

    @Override
    public void insertItemPrep(ItemPreprationModel bean) {
      mAppDatabase.itemPreprationDao().insertItemPrep(bean);
    }

    @Override
    public void deletePrepration() {
        mAppDatabase.itemPreprationDao().deletePrepration();
    }

    @Override
    public List<OrderItemModel> getSelectedItem(String OrderId) {
        return mAppDatabase.orderItemDao().getSelectedItem(OrderId);
    }

    @Override
    public List<OrderPreparationModel> loadAllSelectedPreprations(String itemId, String OrderId) {
        return mAppDatabase.orderPreparationDao().loadAllSelectedPreprations(itemId,OrderId);
    }

    @Override
    public List<OrderAddOnChild> loadAllSelectedAddons(String ItemIdAddon, String OrderId) {
        return mAppDatabase.orderAddonChildDao().loadAllSelectedAddons(ItemIdAddon,OrderId);
    }

    @Override
    public List<OrderPreparationAddonModel> loadallAddongroup(String ItemIdAddon, String addonId, String OrderId,String itemPrimaryKey) {
        return mAppDatabase.orderPreparationsAddonDao().loadallAddongroup(ItemIdAddon,addonId,OrderId, itemPrimaryKey);
    }

    @Override
    public void deleteAddonPrepration() {
        mAppDatabase.addonPreprationDao().deleteAddonPrepration();
    }

    @Override
    public void UpdateAddons(String addOnPrimaryKey) {
        mAppDatabase.orderAddonChildDao().UpdateAddons(addOnPrimaryKey);
    }

    @Override
    public void UpdateAddonPrepration(String orderPrepAddOnPrimaryKey) {
        mAppDatabase.orderPreparationsAddonDao().UpdateAddonPrepration(orderPrepAddOnPrimaryKey);
    }

    @Override
    public void UpdatePreprationofItem(String addOnPrimaryKey) {
        mAppDatabase.orderPreparationDao().UpdatePreprationofItem(addOnPrimaryKey);
    }

    @Override
    public void DeleteAddons(String addOnPrimaryKey) {
        mAppDatabase.orderAddonChildDao().DeleteAddons(addOnPrimaryKey);
    }

    @Override
    public void DeleteItemFromCurrent(String OrderId) {
        mAppDatabase.orderItemDao().DeleteItemFromCurrent(OrderId);
    }

    @Override
    public void DeleteAll() {
        mAppDatabase.orderItemDao().DeleteAll();
    }

    @Override
    public void deleteAddonPrepration(String orderPrepAddOnPrimaryKey) {
      mAppDatabase.orderPreparationsAddonDao().deleteAddonPrepration(orderPrepAddOnPrimaryKey);
    }

    @Override
    public void DeleteItem(String itemPrimaryKey) {
        mAppDatabase.orderItemDao().DeleteItem(itemPrimaryKey);
    }

    @Override
    public void deletePreps(String addOnPrimaryKey) {
        mAppDatabase.orderPreparationDao().deletePreps(addOnPrimaryKey);
    }

    @Override
    public List<OrderAddOnChild> getAddonsByItemPrimary(String itemPrimaryKey) {
        return mAppDatabase.orderAddonChildDao().getAddonsByItemPrimary(itemPrimaryKey);
    }

    @Override
    public List<OrderPreparationAddonModel> getOrderAddonsPrep(String itemPrimaryKey) {
        return mAppDatabase.orderPreparationsAddonDao().getOrderAddonsPrep(itemPrimaryKey);
    }

    @Override
    public List<OrderPreparationModel> getAllOrderItemPrep(String itemPrimaryKey) {
        return mAppDatabase.orderPreparationDao().getAllOrderItemPrep(itemPrimaryKey);
    }

    @Override
    public void updateRemarkofAddon(String addonRemark, String addonId) {
        mAppDatabase.addOnChildDao().updateRemarkofAddon(addonRemark,addonId);
    }

    @Override
    public void updateRemarkofPrepration(String itemRemark, String preparationId) {
        mAppDatabase.itemPreprationDao().updateRemarkofPrepration(itemRemark,preparationId);
    }

    @Override
    public List<Zonemodel> getZoneDetail(String tableId) {
        return mAppDatabase.zoneDao().getZoneDetail(tableId);
    }

    @Override
    public List<NewOrderModel> loadAllOrderId() {
        return mAppDatabase.newOrderDao().loadAllOrderId();
    }

    @Override
    public void updatePrice(String itemPrice, String itemId) {
        mAppDatabase.orderItemDao().updatePrice(itemPrice,itemId);
    }

    @Override
    public void updatePriceFromItem(String itemPrice, String itemId) {
        mAppDatabase.itemDao().updatePriceFromItem(itemPrice,itemId);
    }

    @Override
    public void insertPaymemt(PayMethodsModel bean) {
        mAppDatabase.paymethodsDao().insertPaymemt(bean);
    }



    @Override
    public List<PayMethodsModel> loadAllPayment() {
        return mAppDatabase.paymethodsDao().loadAllPayment();
    }

    @Override
    public void updateOrderId(String OrderId, String termId) {
     mAppDatabase.paymethodsDao().updateOrderId(OrderId,termId);
    }

    @Override
    public void updateOrderAmt(String amt, String OrderId) {
        mAppDatabase.paymethodsDao().updateOrderAmt(amt,OrderId);
    }

    @Override
    public AdddonChildModel getDetail(String addonId) {
        return mAppDatabase.addOnChildDao().getDetail(addonId);
    }


}



