package com.menuz.data.ApiParse;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.android.volley.VolleyError;
import com.menuz.Utils.ConnectionDetector;
import com.menuz.Utils.NoConnectionDialog;
import com.menuz.data.model.db.AddOnModel;
import com.menuz.data.model.db.AdddonChildModel;
import com.menuz.data.model.db.EmployeeModel;
import com.menuz.data.model.db.ItemModel;
import com.menuz.data.model.db.MenuModel;
import com.menuz.data.model.db.PrefixModel;
import com.menuz.data.model.db.PreparationModel;
import com.menuz.data.model.db.PriceModel;
import com.menuz.data.model.db.TableModel;
import com.menuz.data.model.db.Zonemodel;
import com.menuz.network.HttpResponceListner;
import com.menuz.network.HttpTask;
import com.menuz.session.Session;
import com.menuz.ui.Order.model.ZoneDataInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;

import static com.menuz.application.MenuZ.getDataManager;
import static com.menuz.application.MenuZ.mInstance;


public class ParseData {
    private static final ParseData ourInstance = new ParseData();



    private ParseData() {

    }


    public static ParseData getInstance() {
        return ourInstance;
    }

    public  void
    loginProcess(Context context, SwipeRefreshLayout swipeContainer) {

        if (!ConnectionDetector.isConnected()) {
            new NoConnectionDialog(context, (dialog, isConnected) -> {
                if (isConnected) {
                    dialog.dismiss();
                    loginProcess(context, swipeContainer);
                }

            }).show();
        }

        new HttpTask(new HttpTask.Builder(context, "newSyncServer/0/admin/admin/1/01-10-2017 10:12:12", new HttpResponceListner.Listener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onResponse(String response, String apiName) {
                swipeContainer.setRefreshing(false);

                Log.d("syncRes",""+response);
                new Thread(() -> insertData(response)).start();
            }

            @Override
            public void ErrorListener(VolleyError error) {

            }
        })
                .setBody(null, HttpTask.ContentType.APPLICATION_JSON)
                .setProgress(true))
                .execute("");
    }

    private void insertData(String response) {
        try {
            JSONObject js = new JSONObject(response);
            JSONArray jsonArray = js.getJSONArray("result");
            JSONObject resultjson = jsonArray.getJSONObject(0);
            JSONArray zoneArray = resultjson.getJSONArray("zones");
            for (int i = 0; i < zoneArray.length(); i++) {

                JSONObject jsonObjectZone = zoneArray.getJSONObject(i);
                ZoneDataInfo.ResultBean.ZonesBean zoneDataInfo = new ZoneDataInfo.ResultBean.ZonesBean();
                zoneDataInfo.setZoneId(jsonObjectZone.getString("zoneId"));
                zoneDataInfo.setZonePlace(jsonObjectZone.getString("zonePlace"));
                zoneDataInfo.setZoneName(jsonObjectZone.getString("zoneName"));
                String getdata = jsonObjectZone.getString("zoneName");
                Log.d("getzone", "" + getdata);

                Zonemodel zonemodel = new Zonemodel();
                zonemodel.setZoneId(zoneDataInfo.getZoneId());
                zonemodel.setZoneName(zoneDataInfo.getZoneName());
                zonemodel.setZonePlace(zoneDataInfo.getZonePlace());
                getDataManager().insertZone(zonemodel);



                JSONArray tableArray = jsonObjectZone.getJSONArray("tables");
                ArrayList<TableModel>tableModelArrayList=new ArrayList<>();
                for (int k = 0; k < tableArray.length(); k++) {

                    JSONObject jsonObjTables = tableArray.getJSONObject(k);
                    ZoneDataInfo.ResultBean.ZonesBean.TablesBean tablesBean = new ZoneDataInfo.ResultBean.ZonesBean.TablesBean();

                    tablesBean.setTableGuestName(jsonObjTables.getString("tableGuestName"));
                    tablesBean.setTableId(jsonObjTables.getString("tableId"));
                    tablesBean.setTableName(jsonObjTables.getString("tableName"));
                    tablesBean.setTableHeight(jsonObjTables.getString("tableHeight"));
                    tablesBean.setTableWidth(jsonObjTables.getString("tableWidth"));
                    tablesBean.setTableTop(jsonObjTables.getString("tableTop"));
                    tablesBean.setTableHeight(jsonObjTables.getString("tableHeight"));
                    tablesBean.setTableLeft(jsonObjTables.getString("tableLeft"));
                    tablesBean.setTableOrderId(jsonObjTables.getString("tableOrderId"));
                    tablesBean.setTableZoneId(jsonObjTables.getString("tableZoneId"));

                    tablesBean.setTableTerminalId(jsonObjTables.getString("tableTerminalId"));
                    TableModel tableBean = new TableModel();
                    tableBean.setTableGuestName(tablesBean.getTableGuestName());
                    tableBean.setTableId(tablesBean.getTableId());
                    tableBean.setTableLeft(tablesBean.getTableLeft());
                    tableBean.setTableTerminalId(tablesBean.getTableTerminalId());
                    tableBean.setTableWidth(tablesBean.getTableWidth());
                    tableBean.setTableOrderId("");

                    tableBean.setTableZoneId(tablesBean.getTableZoneId());
                    tableBean.setTableHeight(tablesBean.getTableHeight());
                    tableBean.setTableTop(tableBean.getTableTop());
                    tableBean.setIsBusy("");
                    getDataManager().insertZoneTable(tableBean);
                }
            }

            JSONArray jsonArrayEmployee = resultjson.getJSONArray("employees");
            for (int j = 0; j < jsonArrayEmployee.length(); j++) {
                JSONObject employeeObj = jsonArrayEmployee.getJSONObject(j);
                EmployeeModel employeeModel = new EmployeeModel();
                employeeModel.setEmployeeId(employeeObj.getString("employeeId"));
                employeeModel.setEmployeeName(employeeObj.getString("employeeName"));
                getDataManager().insertEmployee(employeeModel);
            }


            JSONArray jsonArraypricelist = resultjson.getJSONArray("pricelist");
            for (int i = 0; i <jsonArraypricelist.length() ; i++) {
                JSONObject jsonArraypricelistJSONObject = jsonArraypricelist.getJSONObject(i);
                PriceModel priceModel=new PriceModel();
                priceModel.setPricelistId(jsonArraypricelistJSONObject.getString("pricelistId"));
                priceModel.setPricelistName(jsonArraypricelistJSONObject.getString("pricelistName"));
                getDataManager().insert(priceModel);
            }

            JSONArray jsonArrayPreparation = resultjson.getJSONArray("preparations");
            for (int i = 0; i < jsonArrayPreparation.length(); i++) {
                JSONObject jsonObjectPreparation = jsonArrayPreparation.getJSONObject(i);
                PreparationModel preparationModel = new PreparationModel();
                preparationModel.setPreparationId(jsonObjectPreparation.getString("preparationId"));
                preparationModel.setPreparationName(jsonObjectPreparation.getString("preparationName"));
                preparationModel.setPreparationIsPrefixed(jsonObjectPreparation.getString("preparationIsPrefixed"));
                getDataManager().insertPreparation(preparationModel);
            }

            JSONArray jsonArrayMenu = resultjson.getJSONArray("menu");

            for (int i = 0; i < jsonArrayMenu.length(); i++) {
                JSONObject menuObJ = jsonArrayMenu.getJSONObject(i);
                MenuModel menuModel = new MenuModel();
                menuModel.setChkPermission(menuObJ.getString("groupId"));
                menuModel.setGroupActive(menuObJ.getString("groupActive"));
                menuModel.setGroupName(menuObJ.getString("groupName"));
                menuModel.setGroupFromTime(menuObJ.getString("groupFromTime"));
                menuModel.setGroupImage(menuObJ.getString("groupImage"));
                menuModel.setGroupUptoTime(menuObJ.getString("groupUptoTime"));
                menuModel.setIgProperty(menuObJ.getString("igProperty"));
                menuModel.setChkPermission(menuObJ.getString("chkPermission"));
                menuModel.setGroupPlace(menuObJ.getString("groupPlace"));
                menuModel.setGroupId(menuObJ.getString("groupId"));
                menuModel.setGroupParentId(menuObJ.getString("groupParentId"));
                menuModel.setSubgroups(menuObJ.getString("subgroups"));
                getDataManager().insertMenu(menuModel);

                JSONArray jsonArrayItem = menuObJ.getJSONArray("items");
                for (int j = 0; j < jsonArrayItem.length(); j++) {
                    JSONObject jsonObject = jsonArrayItem.getJSONObject(j);
                    ItemModel itemModel = new ItemModel();
                    itemModel.setItemId(jsonObject.getString("itemId"));
                    itemModel.setItemName(jsonObject.getString("itemName"));
                    itemModel.setItemImage(jsonObject.getString("itemImage"));
                    itemModel.setItemGroupId(jsonObject.getString("itemGroupId"));

                    itemModel.setItemPrice(jsonObject.getString("itemPrice"));
                    itemModel.setItemAddonPrice(jsonObject.getString("itemAddonPrice"));
                    itemModel.setPreparations(jsonObject.getString("preparations"));
                    itemModel.setPricevalues(jsonObject.getString("pricevalues"));
                    getDataManager().insertItem(itemModel);

                    JSONArray jsonArrayAddOn = jsonObject.getJSONArray("addonsGroups");
                    for (int k = 0; k < jsonArrayAddOn.length(); k++) {
                        JSONObject jsonObjectaddOn = jsonArrayAddOn.getJSONObject(k);
                        AddOnModel addOnModel = new AddOnModel();
                        addOnModel.setAddOnItemID(jsonObject.getString("itemId"));// jsonObject.getString("itemId")
                        addOnModel.setAddonsGroupEnh(jsonObjectaddOn.getString("addonsGroupEnh"));
                        addOnModel.setAddonsGroupId(jsonObjectaddOn.getString("addonsGroupId"));
                        addOnModel.setAddonsGroupIsMandatory(jsonObjectaddOn.getString("addonsGroupIsMandatory"));
                        addOnModel.setAddonsGroupPlace(jsonObjectaddOn.getString("addonsGroupPlace"));
                        addOnModel.setAddonsGroupMax(jsonObjectaddOn.getString("addonsGroupMax"));
                        addOnModel.setAddonsGroupName(jsonObjectaddOn.getString("addonsGroupName"));
                        getDataManager().insetAddon(addOnModel);

                        JSONArray jsonArrayAddOnChild = jsonObjectaddOn.getJSONArray("addons");
                        for (int l = 0; l < jsonArrayAddOnChild.length(); l++) {
                            JSONObject jsonObjectAddOnChild = jsonArrayAddOnChild.getJSONObject(l);
                            AdddonChildModel adddonChildModel = new AdddonChildModel();
                            adddonChildModel.setAddOnItemIdchild(jsonObjectaddOn.getString("addonsGroupId"));
                            adddonChildModel.setAddonGroupId(jsonObjectAddOnChild.getString("addonGroupId"));
                            adddonChildModel.setAddonId(jsonObjectAddOnChild.getString("addonId"));
                            adddonChildModel.setAddonName(jsonObjectAddOnChild.getString("addonName"));
                            adddonChildModel.setItemIdAddon(addOnModel.getAddOnItemID());
                            adddonChildModel.setAddonPrice(jsonObjectAddOnChild.getString("addonPrice"));
                            adddonChildModel.setItemPrice(jsonObjectAddOnChild.getString("itemPrice"));
                            adddonChildModel.setPreparations(jsonObjectAddOnChild.getString("preparations"));
                            getDataManager().insertAddOnChild(adddonChildModel);
                        }
                    }
                }
                JSONArray jsonArrayPrefix = resultjson.getJSONArray("prefixes");
                for (int j = 0; j < jsonArrayPrefix.length(); j++) {
                    JSONObject jsonObjectPrefix = jsonArrayPrefix.getJSONObject(j);
                    PrefixModel prefixModel = new PrefixModel();
                    prefixModel.setPrefixId(jsonObjectPrefix.getString("prefixId"));
                    prefixModel.setPrefixName(jsonObjectPrefix.getString("prefixName"));
                    getDataManager().insertPrefix(prefixModel);
                    Session session=new Session(mInstance);
                    session.createSessionisUpdatedDb("updated");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public class TableCompare implements Comparator<TableModel> {

        @Override
        public int compare(TableModel o1, TableModel o2) {
            int tableId=Integer.parseInt(o1.getTableId());
            int tableId1=Integer.parseInt(o2.getTableId());
            return Integer.compare(tableId,tableId1);
        }
    }

}
