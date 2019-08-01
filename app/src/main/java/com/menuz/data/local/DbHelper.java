package com.menuz.data.local;


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


public interface DbHelper {

    List<TableModel> getalltable(String zoneId);

    Boolean insertZone(Zonemodel zonesBean);

    void insertZoneTable(TableModel tablemodel);

    void insertAllZoneList(List<Zonemodel> zonemodels);

    List<TableModel> getalltable();

    List<Zonemodel> getallZone();

    List<EmployeeModel> getAllEmp();

    void insertEmployee(EmployeeModel employeeModel);

    void insertMenu(MenuModel menuModel);

    List<MenuModel> getallMenu();

    void insertItem(ItemModel itemModel);

    List<ItemModel> getallItem(String groupId);

    void insertPreparation(PreparationModel preparationModel);

    List<PreparationModel> getallDatabtId(List<String> array);

    List<PreparationModel> getallPrepation();

    void insetAddon(AddOnModel addOnModel);

    List<AddOnModel> getAddons(String itemId);

    void insertAddOnChild(AdddonChildModel adddonChildModel);

    List<AdddonChildModel> getAddOnChild(String addOnId, String itemIdAdoons);

    void insertPrefix(PrefixModel prefixModel);

    List<PrefixModel> getAllPrefix();

    void insertOrderZone(OrderZoneModel orderZoneModel);

    OrderZoneModel getallOrderZone(String orderId);

    void insertOrderTable(OrderTableModel orderTableModel);

    OrderTableModel getallOrderTable(String orderId);

    void insertOrderEmployee(OrderEmployeeModel orderEmployeeModel);

    OrderEmployeeModel getAllOrderEmployee(String orderId);

    void insertOrderItem(OrderItemModel orderItemModel);

    List<OrderItemModel> getAllorderItem(String OrderId);

    void insertOrderMenu(OrderMenuModel orderMenuModel);

    List<OrderMenuModel> getAllOrderMenu();

    void insertOrderPrearation(OrderPreparationModel orderPreparationModel);

    List<OrderPreparationModel> getAllpreparation();

    void insertOrderAddOn(OrderAddOnModel orderAddOnModel);

    List<OrderAddOnModel> getAllOrderAddOn(String OrderId);

    void insertAddonChild(OrderAddOnChild orderAddOnChild);

    List<OrderAddOnChild> getAllOrderAddonChild(String OrderId);

    void deleteByItemId(String itemIDAddon, String addonsGroupId, String addonId);

    void deleteByPrep(String prepId);

    void update(boolean isSelect, String addOnGrupId, String OrderId, String itemId);

    List<OrderAddOnChild> getDataById(String addoNId, String OrderId);

    void insertOrderAddonPreparation(OrderPreparationAddonModel orderPreparationAddonModel);

    List<OrderPreparationAddonModel> loadAllByAddonId(String aadonGroupId, String ItemidAddon, String addonId, boolean isSelect, String OrderId);

    List<PreparationModel> loadAllByAddonPrepId(String prepaID);

    PreparationModel loadByPrepId(String preparationId);

    List<OrderAddOnChild> loadAllItemSelected(boolean isSelect, String OrderID);

    List<OrderAddOnModel> loadallAddonByItem(String itemID, String OrderId);


    List<OrderAddOnChild> loadallAddonByaddonItemID(String addonsGroupId, String itemIdAddon, String OrderId);

    List<OrderItemModel> loadByIteId(String itemId, String OrderId);

    List<OrderPreparationAddonModel> getprearationByAddonAndItemId(String ItemId, String addonId, String addongroupId, String OrderId);

    void updateAddonGroup(boolean isSelect, String addonGroupId, String OrderId, String itemId);

    void updatePreprationAddon( String addonsGroupId, String ItemIdAddon, String addonId, String preprationId, String OrderId, String PrefixName, String prefixId);

    void deletePrepration(boolean isSelect, String addonsGroupId, String ItemIdAddon, String addonId, String preprationId, String OrderId);

    List<OrderPreparationModel> loadallPrepByGroupId(String itemId, String OrderId);

    List<OrderPreparationModel> loadallSelectedItemOnItemId(String itemId, String OrderId, boolean isSelect);

    void updateprepByItem(String itemId, String prepId, String OrderId, String prefixName, String prefixId, String addonIdN);

    List<OrderPreparationModel> loadAllprepSelected(boolean isSelect, String OrderId);


    void updateAddons(boolean isSelect, String addonGroupId, String itemId, String OrderId);

    void updatePreprationondelete(boolean isSelect, String addonGroupId, String itemId, String addonId, String OrderId);

    void updatecount(String countPrice, String ItemID);

    OrderItemModel loadCount(String itemId);

    void deleteByItemId(String itemId);


    void deleteByAddon(String itemId);

    void deleteByAddonChild(String itemId);

    void deleteByAddonPrep(String itemId);

    void updateTotal(String Grandtotal);


    void insertNewOrder(NewOrderModel newOrderModel);

    List<NewOrderModel> loadnewOrderId();

    void updateAddonPrepration(boolean isSelect, String addonsGroupId, String OrderId, String addOnItemID);

    void clearAllTable();

    NewOrderModel loadDataOnOrderId(String orderid);

    void updateOrder(String OrderId,String NuofGuest);

    void updateOrderinOrderEmployee(String OrderId,String NuofGuest);

    void deleteByOrderId(String OrderId);

    void updatetableandZone(String OrderId,String zoneName,String zoneId,String tableId);

    void updatenuofguestandEmployee(String OrderId,String employeeName,String Nuofguest,String zoneName,String zoneId,String tableId);
    void  updateOrderTable(String OrderId,String tableId);

    List<OrderMenuModel> loadAllorderMenu(String OrderId);

    List<OrderAddOnChild> loadSelectedAddonByItemId(String ItemIdAddon, String OrderId, boolean isSelect);

    List<OrderPreparationAddonModel> loadallAddonByitemId(String ItemIdAddon, boolean isSelect, String OrderId);

    List<OrderPreparationAddonModel> loadallAddonByitemId(String ItemIdAddon, String addonId, boolean isSelect, String OrderId);

    void insert(PaymentModel bean);



    List<OrderAddOnChild> loadAllSelectdAddon(boolean isSelect, String OrderId, String ItemIdAddon,String addonsGroupId);

    void updateitemRemark(String itemRemark, String itemId, String OrderId);

    void updateitemPrepRemark(String itemRemark, String itemId, String OrderId);

    void updateCart(boolean isCartSelected, String OrderId);

    List<OrderItemModel> getSelectedCart(String OrderId, boolean isCartSelected);

    NewOrderModel geloadOrderId();

    void insert(PriceModel priceModel);

    List<PriceModel> loadPrice();

    void inserPriceValue(List<PricevaluesModel> bean);


    List<PriceModel> loadPriceData();

    void clearPriceValueData();
    List<PricevaluesModel> loadPriceValue();

    String loadPriceValue(String pricelistId);

    List<TableModel> loadAlltablesData();

    void updateTable(String isBusy,String tableId);

    /*For get addongroup */

    List<AddOnModel> loadallAddonByitems(String addOnItemID);

    /*For get addons */
    List<AdddonChildModel> loadallAddonsByitemId(String addOnItemIdchild, String ItemIdAddon);

    /*For get Prepration of addon */
    void insertAddonPrep(AddonPreprationModel bean);


    List<AddonPreprationModel> loadAllpreprationByAddon(String ItemIdAddon, String addonsGroupId, String addonId);

    /*For get all item prepration by itemid*/
    List<ItemPreprationModel> loadallPrepByItemId(String itemId);

    void insertItemPrep(ItemPreprationModel bean);

    void deletePrepration();

    /*Get selected item*/

    List<OrderItemModel>getSelectedItem(String OrderId);

    /*Get Cart addons and preprations*/

    List<OrderPreparationModel> loadAllSelectedPreprations(String itemPrimaryKey,String OrderId);

    List<OrderAddOnChild> loadAllSelectedAddons(String itemPrimaryKey, String OrderId);

    List<OrderPreparationAddonModel> loadallAddongroup(String ItemIdAddon, String addonId, String OrderId,String itemPrimaryKey);

    void deleteAddonPrepration();

     /*Update Addon group*/
   void UpdateAddons(String itemPrimaryKey);


    /*Update Addon Prepration*/
    void  UpdateAddonPrepration(String orderPrepAddOnPrimaryKey);


    /*Update Item Prep*/
    void UpdatePreprationofItem(String addOnPrimaryKey);

    void DeleteAddons(String addOnPrimaryKey);

    void DeleteItemFromCurrent(String OrderId);

    void DeleteAll();




    void  deleteAddonPrepration(String orderPrepAddOnPrimaryKey);

    void DeleteItem(String itemPrimaryKey);

    void deletePreps(String addOnPrimaryKey);

    List<OrderAddOnChild> getAddonsByItemPrimary(String itemPrimaryKey);

    List<OrderPreparationAddonModel>  getOrderAddonsPrep(String itemPrimaryKey);

    List<OrderPreparationModel>getAllOrderItemPrep(String itemPrimaryKey);

    void updateRemarkofAddon(String addonRemark,String addonId);

    void updateRemarkofPrepration(String itemRemark,String preparationId);


    /*get zone Detail*/
    List<Zonemodel>getZoneDetail(String tableId);

    List<NewOrderModel> loadAllOrderId();

    void updatePrice(String itemPrice, String itemId);

    void updatePriceFromItem(String itemPrice, String itemId);

    void insertPaymemt(PayMethodsModel bean);




    List<PayMethodsModel> loadAllPayment();

    void updateOrderId(String OrderId,String termId);

    void updateOrderAmt(String amt, String OrderId);

    AdddonChildModel getDetail(String addonId);



    List<PayMethodsModel> loadAllPaymentType(String OrderId);

    PayMethodsModel loadSinglePayment(String OrderId);

    List<OrderTableModel> loadAllTable();

    void updateOrderIdinItem(String OrderId, String itemId);

    void updateItemPrepOrder(String OrderId,String preparationId,String itemId);


    List<ItemPreprationModel> loadAllPrep();


    List<OrderItemModel> getAllItems();

    void UpdateOrderId(String OrderId,String addonId);

    List<OrderAddOnChild> loadAlladdons();


    List<OrderPreparationAddonModel> loadPreparation();

    void upadteOrderId(String addonsGroupId,String ItemIdAddon,String addonId,String OrderId);

    void deletePaymethods();

    void updateOrderIdPayment(String OrderId,String termId);

    List<PaymentModel> loadAllPayments();

    void updatePaymentmethods(String OrderId,String payMethodId,String payMethodFixValue,String payMethodName,String payMethodType,String cardno,String month,String year,String securitycode,String Id,String phone);


    List<PaymentModel> loadPaymentRef(String OrderId);

    void deleteFromPayment();


    void updateTableLock(String isLock,String tableId);

    String getOrderId(String OrderId);

    AddonPreprationModel getDetailPrepration(String preparationId);

    PrefixModel getDetailPrefix(String prefix);

    ItemPreprationModel getItemPreps(String preparationId);

    void updateIsSync(boolean isSyncSelect,String addonId,String ItemIdAddon,String addOnItemIdchild);

    void updateIsSyncInAddons(boolean isSyncSelect,String addonId,String ItemIdAddon,String addOnItemIdchild);

    void updateAddonsIsSelect(boolean isSelect,int addonsGroupPriId,String addonsGroupId);


    void updatePrepration(String OrderId,String preparationId);




































}
