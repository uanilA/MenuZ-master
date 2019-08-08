package com.menuz.data;

import android.content.Context;

import com.menuz.data.local.AppDbHelper;
import com.menuz.data.local.DbHelper;
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


public class AppDataManager implements DataManager {
    private static AppDataManager instance;
    private final DbHelper mDbHelper;

    private AppDataManager(Context context) {
        mDbHelper = AppDbHelper.getDbInstance(context);
    }

    public synchronized static AppDataManager getInstance(Context context) {
        if (instance == null) {
            instance = new AppDataManager(context);
        }
        return instance;
    }


    @Override
    public List<TableModel> getalltable(String zoneId) {
        return mDbHelper.getalltable(zoneId);
    }


    @Override
    public Boolean insertZone(Zonemodel zonesBean) {
        return mDbHelper.insertZone(zonesBean);
    }

    @Override
    public void insertZoneTable(TableModel tablemodel) {
        mDbHelper.insertZoneTable(tablemodel);
    }

    @Override
    public void insertAllZoneList(List<Zonemodel> zonemodels) {
        mDbHelper.insertAllZoneList(zonemodels);
    }

    @Override
    public List<TableModel> getalltable() {
        return mDbHelper.getalltable();
    }

    @Override
    public List<Zonemodel> getallZone() {
       return mDbHelper.getallZone();
    }

    @Override
    public List<EmployeeModel> getAllEmp() {
        return mDbHelper.getAllEmp();
    }

    @Override
    public void insertEmployee(EmployeeModel employeeModel) {
     mDbHelper.insertEmployee(employeeModel);
    }

    @Override
    public void insertMenu(MenuModel menuModel) {
        mDbHelper.insertMenu(menuModel);
    }

    @Override
    public List<MenuModel> getallMenu() {
        return mDbHelper.getallMenu();
    }

    @Override
    public void insertItem(ItemModel itemModel) {
        mDbHelper.insertItem(itemModel);
    }

    @Override
    public List<ItemModel> getallItem(String groupId) {
        return mDbHelper.getallItem(groupId);
    }

    @Override
    public void insertPreparation(PreparationModel preparationModel) {
        mDbHelper.insertPreparation(preparationModel);
    }

    @Override
    public List<PreparationModel> getallDatabtId(List<String> array) {
        return mDbHelper.getallDatabtId(array);
    }

    @Override
    public List<PreparationModel> getallPrepation() {
        return mDbHelper.getallPrepation();
    }

    @Override
    public void insetAddon(AddOnModel addOnModel) {
        mDbHelper.insetAddon(addOnModel);
    }

    @Override
    public List<AddOnModel> getAddons(String itemId) {
        return mDbHelper.getAddons(itemId);
    }

    @Override
    public void insertAddOnChild(AdddonChildModel adddonChildModel) {
        mDbHelper.insertAddOnChild(adddonChildModel);
    }

    @Override
    public List<AdddonChildModel> getAddOnChild(String addOnId, String itemIdAdoons) {
        return mDbHelper.getAddOnChild(addOnId,itemIdAdoons);
    }



    @Override
    public void insertPrefix(PrefixModel prefixModel) {
        mDbHelper.insertPrefix(prefixModel);
    }

    @Override
    public List<PrefixModel> getAllPrefix() {
        return mDbHelper.getAllPrefix();
    }

    @Override
    public void insertOrderZone(OrderZoneModel orderZoneModel) {
        mDbHelper.insertOrderZone(orderZoneModel);
    }

    @Override
    public OrderZoneModel getallOrderZone(String orderId) {
        return mDbHelper.getallOrderZone(orderId);
    }

    @Override
    public void insertOrderTable(OrderTableModel orderTableModel) {
        mDbHelper.insertOrderTable(orderTableModel);
    }

    @Override
    public OrderTableModel getallOrderTable(String orderId) {
        return mDbHelper.getallOrderTable(orderId);
    }


    @Override
    public void insertOrderEmployee(OrderEmployeeModel orderEmployeeModel) {
        mDbHelper.insertOrderEmployee(orderEmployeeModel);
    }

    @Override
    public OrderEmployeeModel getAllOrderEmployee(String orderId) {
        return mDbHelper.getAllOrderEmployee(orderId);
    }


    @Override
    public void insertOrderItem(OrderItemModel orderItemModel) {
        mDbHelper.insertOrderItem(orderItemModel);
    }

    @Override
    public List<OrderItemModel> getAllorderItem(String OrderId) {
        return mDbHelper.getAllorderItem(OrderId);
    }

    @Override
    public void insertOrderMenu(OrderMenuModel orderMenuModel) {
        mDbHelper.insertOrderMenu(orderMenuModel);
    }

    @Override
    public List<OrderMenuModel> getAllOrderMenu() {
        return mDbHelper.getAllOrderMenu();
    }

    @Override
    public void insertOrderPrearation(OrderPreparationModel orderPreparationModel) {
        mDbHelper.insertOrderPrearation(orderPreparationModel);
    }

    @Override
    public List<OrderPreparationModel> getAllpreparation() {
        return mDbHelper.getAllpreparation();
    }

    @Override
    public void insertOrderAddOn(OrderAddOnModel orderAddOnModel) {
        mDbHelper.insertOrderAddOn(orderAddOnModel);
    }

    @Override
    public List<OrderAddOnModel> getAllOrderAddOn(String OrderId) {
        return mDbHelper.getAllOrderAddOn(OrderId);
    }

    @Override
    public void insertAddonChild(OrderAddOnChild orderAddOnChild) {
        mDbHelper.insertAddonChild(orderAddOnChild );
    }

    @Override
    public List<OrderAddOnChild> getAllOrderAddonChild(String OrderId) {
        return mDbHelper.getAllOrderAddonChild(OrderId);
    }

    @Override
    public void deleteByItemId(String itemIDAddon, String addonsGroupId, String addonId) {
        mDbHelper.deleteByItemId(itemIDAddon,addonsGroupId,addonId);
    }


    @Override
    public void deleteByPrep(String prepId) {
        mDbHelper.deleteByPrep(prepId);
    }

    @Override
    public void update(boolean isSelect, String addOnGrupId, String OrderId, String itemId) {
        mDbHelper.update(isSelect, addOnGrupId, OrderId, itemId);
    }

    @Override
    public List<OrderAddOnChild> getDataById(String addoNId,String OrderId) {
        return mDbHelper.getDataById(addoNId,OrderId);
    }

    @Override
    public void insertOrderAddonPreparation(OrderPreparationAddonModel orderPreparationAddonModel) {
        mDbHelper.insertOrderAddonPreparation(orderPreparationAddonModel);
    }

    @Override
    public List<OrderPreparationAddonModel> loadAllByAddonId(String aadonGroupId, String ItemidAddon, String addonId,boolean isSelect,String OrderId) {
        return mDbHelper.loadAllByAddonId(aadonGroupId,ItemidAddon,addonId,isSelect,OrderId);
    }


    @Override
    public List<PreparationModel> loadAllByAddonPrepId(String prepaID) {
        return mDbHelper.loadAllByAddonPrepId(prepaID);
    }

    @Override
    public PreparationModel loadByPrepId(String preparationId) {
        return mDbHelper.loadByPrepId(preparationId);
    }

    @Override
    public List<OrderAddOnChild> loadAllItemSelected(boolean isSelect,String OrderId) {
        return mDbHelper.loadAllItemSelected(isSelect,OrderId);
    }

    @Override
    public List<OrderAddOnModel> loadallAddonByItem(String itemID,String OrderId) {
        return mDbHelper.loadallAddonByItem(itemID,OrderId);
    }

    @Override
    public List<OrderAddOnChild> loadallAddonByaddonItemID(String addonsGroupId, String itemIdAddon,String OrderId) {
        return mDbHelper.loadallAddonByaddonItemID(addonsGroupId,itemIdAddon,OrderId);
    }

    @Override
    public List<OrderItemModel> loadByIteId(String itemId,String OrderId) {
        return mDbHelper.loadByIteId(itemId,OrderId);
    }

    @Override
    public List<OrderPreparationAddonModel> getprearationByAddonAndItemId(String ItemId, String addonId,String addongroupId,String OrderId) {
        return mDbHelper.getprearationByAddonAndItemId(ItemId,addonId,addongroupId,OrderId);
    }

    @Override
    public void updateAddonGroup(boolean isSelect, String addonGroupId,String OrderId,String itemId) {
        mDbHelper.updateAddonGroup(isSelect,addonGroupId,OrderId,itemId);
    }

    @Override
    public void updatePreprationAddon( String addonsGroupId, String ItemIdAddon, String addonId,String prerationId,String OrderId,String PrefixName,String PrefixId) {
        mDbHelper.updatePreprationAddon(addonsGroupId,ItemIdAddon,addonId,prerationId,OrderId,PrefixName,PrefixId);
    }

    @Override
    public void deletePrepration(boolean isSelect, String addonsGroupId, String ItemIdAddon, String addonId, String preprationId,String OrderId) {
        mDbHelper.deletePrepration(isSelect,addonsGroupId,ItemIdAddon,addonId,preprationId,OrderId);
    }

    @Override
    public List<OrderPreparationModel> loadallPrepByGroupId(String itemId,String OrderId) {
        return mDbHelper.loadallPrepByGroupId(itemId,OrderId);
    }

    @Override
    public List<OrderPreparationModel> loadallSelectedItemOnItemId(String itemId, String OrderId, boolean isSelect) {
        return mDbHelper.loadallSelectedItemOnItemId(itemId,OrderId,isSelect);
    }

    @Override
    public void updateprepByItem( String itemId,String prepId,String OrderId,String prefixName,String prefixId,String addonIdN) {
        mDbHelper.updateprepByItem(itemId,prepId,OrderId,prefixName,prefixId,addonIdN);
    }

    @Override
    public List<OrderPreparationModel> loadAllprepSelected(boolean isSelect,String OrderID) {
        return mDbHelper.loadAllprepSelected(isSelect,OrderID);
    }

    @Override
    public void updateAddons(boolean isSelect, String addonGroupId, String itemId,String OrderId) {
        mDbHelper.updateAddons(isSelect,addonGroupId,itemId,OrderId);
    }

    @Override
    public void updatePreprationondelete(boolean isSelect, String addonGroupId, String itemId,String addonId,String OrderId) {
        mDbHelper.updatePreprationondelete(isSelect,addonGroupId,itemId,addonId,OrderId);
    }

    @Override
    public void updatecount(String countPrice,String itemId) {
        mDbHelper.updatecount(countPrice,itemId);
    }

    @Override
    public OrderItemModel loadCount(String itemId) {
        return mDbHelper.loadCount(itemId);
    }

    @Override
    public void deleteByItemId(String itemId) {
        mDbHelper.deleteByItemId(itemId);
    }

    @Override
    public void deleteByAddon(String itemId) {
        mDbHelper.deleteByAddon(itemId);
    }

    @Override
    public void deleteByAddonChild(String itemId) {
        mDbHelper.deleteByAddonChild(itemId);
    }

    @Override
    public void deleteByAddonPrep(String itemId) {
        mDbHelper.deleteByAddonPrep(itemId);

    }

    @Override
    public void updateTotal(String Grandtotal) {
        mDbHelper.updateTotal(Grandtotal);
    }

    @Override
    public void insertNewOrder(NewOrderModel newOrderModel) {
        mDbHelper.insertNewOrder(newOrderModel);
    }

    @Override
    public List<NewOrderModel> loadnewOrderId() {
        return mDbHelper.loadnewOrderId();
    }

    @Override
    public void updateAddonPrepration(boolean isSelect, String addonsGroupId, String OrderId, String addOnItemID) {
        mDbHelper.updateAddonPrepration(isSelect,addonsGroupId,OrderId,addOnItemID);
    }

    @Override
    public void clearAllTable() {
        mDbHelper.clearAllTable();
    }

    @Override
    public NewOrderModel loadDataOnOrderId(String orderid) {
        return mDbHelper.loadDataOnOrderId(orderid);
    }

    @Override
    public void updateOrder(String OrderId, String NuofGuest) {
      mDbHelper.updateOrder(OrderId,NuofGuest);
    }

    @Override
    public void updateOrderinOrderEmployee(String NuofGuest,String OrderId) {
        mDbHelper.updateOrderinOrderEmployee(NuofGuest,OrderId);
    }

    @Override
    public void updateMySelectionAddonChild(boolean mySelect, String addonId) {
        mDbHelper.updateMySelectionAddonChild(mySelect,addonId);
    }

    @Override
    public void deleteByOrderId(String OrderId) {
        mDbHelper.deleteByOrderId(OrderId);
    }

    @Override
    public void updatetableandZone(String OrderId, String zoneName, String zoneId, String tableId) {
        mDbHelper.updatetableandZone(OrderId,zoneName,zoneId,tableId);
    }

    @Override
    public void updatenuofguestandEmployee(String OrderId, String employeeName, String Nuofguest, String zoneName, String zoneId, String tableId) {
        mDbHelper.updatenuofguestandEmployee(OrderId,employeeName,Nuofguest,zoneName,zoneId,tableId);
    }

    @Override
    public void updateOrderTable(String OrderId, String tableId) {
        mDbHelper.updateOrderTable(OrderId,tableId);
    }

    @Override
    public List<OrderMenuModel> loadAllorderMenu(String OrderId) {
        return mDbHelper.loadAllorderMenu(OrderId);

    }

    @Override
    public List<OrderAddOnChild> loadSelectedAddonByItemId(String ItemIdAddon, String OrderId, boolean isSelect) {
        return mDbHelper.loadSelectedAddonByItemId(ItemIdAddon, OrderId, isSelect);
    }

    @Override
    public List<OrderPreparationAddonModel> loadallAddonByitemId(String ItemIdAddon, boolean isSelect, String OrderId) {
        return mDbHelper.loadallAddonByitemId(ItemIdAddon, isSelect, OrderId);
    }

    @Override
    public List<OrderPreparationAddonModel> loadallAddonByitemId(String ItemIdAddon, String addonId, boolean isSelect, String OrderId) {
        return mDbHelper.loadallAddonByitemId(ItemIdAddon, addonId, isSelect, OrderId);
    }

    @Override
    public void insert(PaymentModel bean) {
        mDbHelper.insert(bean);
    }

    @Override
    public List<PayMethodsModel> loadAllPaymentType(String OrderId) {
        return mDbHelper.loadAllPaymentType(OrderId);
    }

    @Override
    public PayMethodsModel loadSinglePayment(String OrderId) {
        return mDbHelper.loadSinglePayment(OrderId);
    }

    @Override
    public List<OrderTableModel> loadAllTable() {
        return mDbHelper.loadAllTable();
    }

    @Override
    public void updateOrderIdinItem(String OrderId, String itemId) {
         mDbHelper.updateOrderIdinItem(OrderId,itemId);
    }

    @Override
    public void updateItemPrepOrder(String OrderId, String preparationId, String itemId) {
        mDbHelper.updateItemPrepOrder(OrderId,preparationId,itemId);
    }

    @Override
    public List<ItemPreprationModel> loadAllPrep() {
        return mDbHelper.loadAllPrep();
    }

    @Override
    public List<OrderItemModel> getAllItems() {
        return mDbHelper.getAllItems();
    }

    @Override
    public void UpdateOrderId(String OrderId, String addonId) {
       mDbHelper.UpdateOrderId(OrderId,addonId);
    }

    @Override
    public List<OrderAddOnChild> loadAlladdons() {
        return mDbHelper.loadAlladdons();
    }

    @Override
    public List<OrderPreparationAddonModel> loadPreparation() {
        return mDbHelper.loadPreparation();
    }

    @Override
    public void upadteOrderId(String addonsGroupId, String ItemIdAddon, String addonId, String OrderId) {
      mDbHelper.upadteOrderId(addonsGroupId,ItemIdAddon,addonId,OrderId);
    }

    @Override
    public void deletePaymethods() {
        mDbHelper.deletePaymethods();
    }

    @Override
    public void updateOrderIdPayment(String OrderId, String termId) {
        mDbHelper.updateOrderIdPayment(OrderId,termId);
    }

    @Override
    public List<PaymentModel> loadAllPayments() {
        return mDbHelper.loadAllPayments();
    }

    @Override
    public void updatePaymentmethods(String OrderId, String payMethodId, String payMethodFixValue, String payMethodName, String payMethodType, String cardno, String month, String year, String securitycode, String Id, String phone) {
     mDbHelper.updatePaymentmethods(OrderId,payMethodId,payMethodFixValue,payMethodName,payMethodType,cardno,month,year,securitycode,Id,phone);
    }

    @Override
    public List<PaymentModel> loadPaymentRef(String OrderId) {
        return mDbHelper.loadPaymentRef(OrderId);
    }

    @Override
    public void deleteFromPayment() {
        mDbHelper.deleteFromPayment();
    }

    @Override
    public void updateTableLock(String isLock, String tableId) {
        mDbHelper.updateTableLock(isLock,tableId);
    }

    @Override
    public String getOrderId(String OrderId) {
        return mDbHelper.getOrderId(OrderId);
    }

    @Override
    public AddonPreprationModel getDetailPrepration(String preparationId) {
        return mDbHelper.getDetailPrepration(preparationId);
    }

    @Override
    public PrefixModel getDetailPrefix(String prefix) {
        return mDbHelper.getDetailPrefix(prefix);
    }

    @Override
    public ItemPreprationModel getItemPreps(String preparationId) {
        return mDbHelper.getItemPreps(preparationId);
    }

    @Override
    public void updateIsSync(boolean isSyncSelect, String addonId, String ItemIdAddon, String addOnItemIdchild) {
        mDbHelper.updateIsSync(isSyncSelect,addonId,ItemIdAddon,addOnItemIdchild);
    }

    @Override
    public void updateIsSyncInAddons(boolean isSyncSelect, String addonId, String ItemIdAddon, String addOnItemIdchild) {
        mDbHelper.updateIsSyncInAddons(isSyncSelect,addonId,ItemIdAddon,addOnItemIdchild);
    }

    @Override
    public void updateAddonsIsSelect(boolean isSelect, int addonsGroupId,String addonGroupId) {
        mDbHelper.updateAddonsIsSelect(isSelect,addonsGroupId,addonGroupId);
    }

    @Override
    public void updatePrepration(String OrderId, String preparationId) {
        mDbHelper.updatePrepration(OrderId,preparationId);
    }

    @Override
    public void updateSelectedAddonGroupId(String selectedAddonGroupId) {
         mDbHelper.updateSelectedAddonGroupId(selectedAddonGroupId);
    }



    @Override
    public void update(OrderAddOnChild orderAddOnChild) {
        mDbHelper.update(orderAddOnChild);
    }


    @Override
    public List<OrderAddOnChild> loadAllSelectdAddon(boolean isSelect, String OrderId, String ItemIdAddon,String addonsGroupId) {
        return  mDbHelper.loadAllSelectdAddon(isSelect, OrderId, ItemIdAddon, addonsGroupId);
    }

    @Override
    public void updateitemRemark(String itemRemark, String itemId, String OrderId) {
        mDbHelper.updateitemRemark(itemRemark, itemId, OrderId);
    }

    @Override
    public void updateitemPrepRemark(String itemRemark, String itemId, String OrderId) {
        mDbHelper.updateitemPrepRemark(itemRemark, itemId, OrderId);
    }

    @Override
    public void updateCart(boolean isCartSelected, String OrderId) {
        mDbHelper.updateCart(isCartSelected, OrderId);
    }

    @Override
    public List<OrderItemModel> getSelectedCart(String OrderId, boolean isCartSelected) {
        return mDbHelper.getSelectedCart(OrderId, isCartSelected);
    }

    @Override
    public NewOrderModel geloadOrderId() {
        return mDbHelper.geloadOrderId();
    }

    @Override
    public void insert(PriceModel priceModel) {
        mDbHelper.insert(priceModel);
    }

    @Override
    public List<PriceModel> loadPrice() {
        return mDbHelper.loadPrice();
    }

    @Override
    public void inserPriceValue(List<PricevaluesModel> bean) {
        mDbHelper.inserPriceValue(bean);
    }

    @Override
    public List<PriceModel> loadPriceData() {
        return mDbHelper.loadPriceData();
    }

    @Override
    public void clearPriceValueData() {
        mDbHelper.clearPriceValueData();
    }

    @Override
    public List<PricevaluesModel> loadPriceValue() {
        return mDbHelper.loadPriceValue();
    }

    @Override
    public String loadPriceValue(String pricelistId) {
        return mDbHelper.loadPriceValue(pricelistId);
    }

    @Override
    public List<TableModel> loadAlltablesData() {
        return mDbHelper.loadAlltablesData();
    }

    @Override
    public void updateTable(String isBusy, String tableId) {
        mDbHelper.updateTable(isBusy,tableId);
    }

    @Override
    public List<AddOnModel> loadallAddonByitems(String addOnItemID) {
        return mDbHelper.loadallAddonByitems(addOnItemID);
    }

    @Override
    public List<AdddonChildModel> loadallAddonsByitemId(String addOnItemIdchild, String ItemIdAddon) {
        return mDbHelper.loadallAddonsByitemId(addOnItemIdchild,ItemIdAddon);
    }

    @Override
    public void insertAddonPrep(AddonPreprationModel bean) {
        mDbHelper.insertAddonPrep(bean);
    }

    @Override
    public List<AddonPreprationModel> loadAllpreprationByAddon(String ItemIdAddon, String addonsGroupId, String addonId) {
        return mDbHelper.loadAllpreprationByAddon(ItemIdAddon,addonsGroupId,addonId);
    }

    @Override
    public List<ItemPreprationModel> loadallPrepByItemId(String itemId) {
        return mDbHelper.loadallPrepByItemId(itemId);
    }

    @Override
    public void insertItemPrep(ItemPreprationModel bean) {
        mDbHelper.insertItemPrep(bean);

    }

    @Override
    public void deletePrepration() {
        mDbHelper.deletePrepration();
    }

    @Override
    public List<OrderItemModel> getSelectedItem(String OrderId) {
        return mDbHelper.getSelectedItem(OrderId);
    }


    @Override
    public List<OrderPreparationModel> loadAllSelectedPreprations(String itemPrimaryKey, String OrderId) {
        return mDbHelper.loadAllSelectedPreprations(itemPrimaryKey,OrderId);
    }

    @Override
    public List<OrderAddOnChild> loadAllSelectedAddons(String itemPrimaryKey, String OrderId) {
        return mDbHelper.loadAllSelectedAddons(itemPrimaryKey,OrderId);
    }

    @Override
    public List<OrderPreparationAddonModel> loadallAddongroup(String ItemIdAddon, String addonId, String OrderId,String itemPrimaryKey) {
        return mDbHelper.loadallAddongroup(ItemIdAddon,addonId,OrderId,itemPrimaryKey);
    }

    @Override
    public void deleteAddonPrepration() {
        mDbHelper.deleteAddonPrepration();
    }

    @Override
    public void UpdateAddons(String addOnPrimaryKey) {
        mDbHelper.UpdateAddons(addOnPrimaryKey);
    }

    @Override
    public void UpdateAddonPrepration(String orderPrepAddOnPrimaryKey) {
        mDbHelper.UpdateAddonPrepration(orderPrepAddOnPrimaryKey);
    }

    @Override
    public void UpdatePreprationofItem(String addOnPrimaryKey) {
        mDbHelper.UpdatePreprationofItem(addOnPrimaryKey);
    }

    @Override
    public void DeleteAddons(String addOnPrimaryKey) {
        mDbHelper.DeleteAddons(addOnPrimaryKey);
    }

    @Override
    public void DeleteItemFromCurrent(String OrderId) {
        mDbHelper.DeleteItemFromCurrent(OrderId);
    }

    @Override
    public void DeleteAll() {
        mDbHelper.DeleteAll();
    }

    @Override
    public void deleteAddonPrepration(String orderPrepAddOnPrimaryKey) {
     mDbHelper.deleteAddonPrepration(orderPrepAddOnPrimaryKey);
    }

    @Override
    public void DeleteItem(String itemPrimaryKey) {
        mDbHelper.DeleteItem(itemPrimaryKey);
    }

    @Override
    public void deletePreps(String addOnPrimaryKey) {
        mDbHelper.deletePreps(addOnPrimaryKey);
    }

    @Override
    public List<OrderAddOnChild> getAddonsByItemPrimary(String itemPrimaryKey) {
        return mDbHelper.getAddonsByItemPrimary(itemPrimaryKey);
    }

    @Override
    public List<OrderPreparationAddonModel> getOrderAddonsPrep(String itemPrimaryKey) {
        return mDbHelper.getOrderAddonsPrep(itemPrimaryKey);
    }

    @Override
    public List<OrderPreparationModel> getAllOrderItemPrep(String itemPrimaryKey) {
        return mDbHelper.getAllOrderItemPrep(itemPrimaryKey);
    }

    @Override
    public void updateRemarkofAddon(String addonRemark, String addonId) {
        mDbHelper.updateRemarkofAddon(addonRemark,addonId);
    }

    @Override
    public void updateRemarkofPrepration(String itemRemark, String preparationId) {
        mDbHelper.updateRemarkofPrepration(itemRemark,preparationId);
    }

    @Override
    public List<Zonemodel> getZoneDetail(String tableId) {
        return mDbHelper.getZoneDetail(tableId);
    }

    @Override
    public List<NewOrderModel> loadAllOrderId() {
        return mDbHelper.loadAllOrderId();
    }

    @Override
    public void updatePrice(String itemPrice, String itemId) {
        mDbHelper.updatePrice(itemPrice,itemId);
    }

    @Override
    public void updatePriceFromItem(String itemPrice, String itemId) {
        mDbHelper.updatePriceFromItem(itemPrice,itemId);
    }

    @Override
    public void insertPaymemt(PayMethodsModel bean) {
        mDbHelper.insertPaymemt(bean);
    }

    @Override
    public List<PayMethodsModel> loadAllPayment() {
        return mDbHelper.loadAllPayment();
    }

    @Override
    public void updateOrderId(String OrderId, String termId) {
      mDbHelper.updateOrderId(OrderId,termId);
    }

    @Override
    public void updateOrderAmt(String amt, String OrderId) {
        mDbHelper.updateOrderAmt(amt, OrderId);
    }

    @Override
    public AdddonChildModel getDetail(String addonId) {
        return mDbHelper.getDetail(addonId);
    }


}
