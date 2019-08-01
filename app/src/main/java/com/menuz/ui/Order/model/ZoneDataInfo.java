package com.menuz.ui.Order.model;

import java.util.List;

/**
 * Created by mindiii on 18/10/18.
 */

public class ZoneDataInfo {


    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {


        private String success;
        private List<ZonesBean> zones;
        private List<EmployeesBean> employees;
        private List<PreparationsBean> preparations;
        private List<PricelistBean> pricelist;
        private List<PrefixesBean> prefixes;
        private List<MenuBean> menu;

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public List<ZonesBean> getZones() {
            return zones;
        }

        public void setZones(List<ZonesBean> zones) {
            this.zones = zones;
        }

        public List<EmployeesBean> getEmployees() {
            return employees;
        }

        public void setEmployees(List<EmployeesBean> employees) {
            this.employees = employees;
        }

        public List<PreparationsBean> getPreparations() {
            return preparations;
        }

        public void setPreparations(List<PreparationsBean> preparations) {
            this.preparations = preparations;
        }

        public List<PricelistBean> getPricelist() {
            return pricelist;
        }

        public void setPricelist(List<PricelistBean> pricelist) {
            this.pricelist = pricelist;
        }

        public List<PrefixesBean> getPrefixes() {
            return prefixes;
        }

        public void setPrefixes(List<PrefixesBean> prefixes) {
            this.prefixes = prefixes;
        }

        public List<MenuBean> getMenu() {
            return menu;
        }

        public void setMenu(List<MenuBean> menu) {
            this.menu = menu;
        }

        public static class ZonesBean {
            public int itemImg;
            public int itemImgActive;
            public boolean isSelect = false;
            public String itemName;
            /**
             * zoneId : 1
             * zoneName : אולם
             * zonePlace : 0
             * tables : [{"tableId":"1","tableZoneId":"1","tableName":"","tableTop":"408","tableLeft":"736","tableHeight":"88","tableWidth":"64","tableOrderId":"","tableGuestName":"","tableTerminalId":""}]
             */

            private String zoneId;
            private String zoneName;
            private String zonePlace;
            private List<TablesBean> tables;

            public String getZoneId() {
                return zoneId;
            }

            public void setZoneId(String zoneId) {
                this.zoneId = zoneId;
            }

            public String getZoneName() {
                return zoneName;
            }

            public void setZoneName(String zoneName) {
                this.zoneName = zoneName;
            }

            public String getZonePlace() {
                return zonePlace;
            }

            public void setZonePlace(String zonePlace) {
                this.zonePlace = zonePlace;
            }

            public List<TablesBean> getTables() {
                return tables;
            }

            public void setTables(List<TablesBean> tables) {
                this.tables = tables;
            }

            public static class TablesBean {
                /**
                 * tableId : 1
                 * tableZoneId : 1
                 * tableName :
                 * tableTop : 408
                 * tableLeft : 736
                 * tableHeight : 88
                 * tableWidth : 64
                 * tableOrderId :
                 * tableGuestName :
                 * tableTerminalId :
                 */

                public  int itemImg;
                public  int itemImgActive;
                public boolean isSelect = false;
                public String itemName;


                private String tableId;
                private String tableZoneId;
                private String tableName;
                private String tableTop;
                private String tableLeft;
                private String tableHeight;
                private String tableWidth;
                private String tableOrderId;
                private String tableGuestName;
                private String tableTerminalId;

                public String getTableId() {
                    return tableId;
                }

                public void setTableId(String tableId) {
                    this.tableId = tableId;
                }

                public String getTableZoneId() {
                    return tableZoneId;
                }

                public void setTableZoneId(String tableZoneId) {
                    this.tableZoneId = tableZoneId;
                }

                public String getTableName() {
                    return tableName;
                }

                public void setTableName(String tableName) {
                    this.tableName = tableName;
                }

                public String getTableTop() {
                    return tableTop;
                }

                public void setTableTop(String tableTop) {
                    this.tableTop = tableTop;
                }

                public String getTableLeft() {
                    return tableLeft;
                }

                public void setTableLeft(String tableLeft) {
                    this.tableLeft = tableLeft;
                }

                public String getTableHeight() {
                    return tableHeight;
                }

                public void setTableHeight(String tableHeight) {
                    this.tableHeight = tableHeight;
                }

                public String getTableWidth() {
                    return tableWidth;
                }

                public void setTableWidth(String tableWidth) {
                    this.tableWidth = tableWidth;
                }

                public String getTableOrderId() {
                    return tableOrderId;
                }

                public void setTableOrderId(String tableOrderId) {
                    this.tableOrderId = tableOrderId;
                }

                public String getTableGuestName() {
                    return tableGuestName;
                }

                public void setTableGuestName(String tableGuestName) {
                    this.tableGuestName = tableGuestName;
                }

                public String getTableTerminalId() {
                    return tableTerminalId;
                }

                public void setTableTerminalId(String tableTerminalId) {
                    this.tableTerminalId = tableTerminalId;
                }
            }
        }

        public static class EmployeesBean {
            /**
             * employeeId : 1
             * employeeName : Omar
             */


            private String employeeId;
            private String employeeName;
            private int itemImg;
            private int itemImgActive;
            private boolean isSelect = false;
            private String itemName;

            public int getItemImg() {
                return itemImg;
            }

            public void setItemImg(int itemImg) {
                this.itemImg = itemImg;
            }

            public int getItemImgActive() {
                return itemImgActive;
            }

            public void setItemImgActive(int itemImgActive) {
                this.itemImgActive = itemImgActive;
            }

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public String getEmployeeId() {
                return employeeId;
            }

            public void setEmployeeId(String employeeId) {
                this.employeeId = employeeId;
            }

            public String getEmployeeName() {
                return employeeName;
            }

            public void setEmployeeName(String employeeName) {
                this.employeeName = employeeName;
            }
        }

        public static class PreparationsBean {
            public int itemImg;
            public int itemImgActive;
            public boolean isSelect = false;
            public String itemName;
            /**
             * preparationId : 1
             * preparationName : MW
             * preparationIsPrefixed : 1
             */

            private String preparationId;
            private String preparationName;
            private String preparationIsPrefixed;

            public int getItemImg() {
                return itemImg;
            }

            public void setItemImg(int itemImg) {
                this.itemImg = itemImg;
            }

            public int getItemImgActive() {
                return itemImgActive;
            }

            public void setItemImgActive(int itemImgActive) {
                this.itemImgActive = itemImgActive;
            }

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public String getPreparationId() {
                return preparationId;
            }

            public void setPreparationId(String preparationId) {
                this.preparationId = preparationId;
            }

            public String getPreparationName() {
                return preparationName;
            }

            public void setPreparationName(String preparationName) {
                this.preparationName = preparationName;
            }

            public String getPreparationIsPrefixed() {
                return preparationIsPrefixed;
            }

            public void setPreparationIsPrefixed(String preparationIsPrefixed) {
                this.preparationIsPrefixed = preparationIsPrefixed;
            }
        }

        public static class PricelistBean {
            /**
             * pricelistId : 1
             * pricelistName : חברות
             */

            private String pricelistId;
            private String pricelistName;

            public String getPricelistId() {
                return pricelistId;
            }

            public void setPricelistId(String pricelistId) {
                this.pricelistId = pricelistId;
            }

            public String getPricelistName() {
                return pricelistName;
            }

            public void setPricelistName(String pricelistName) {
                this.pricelistName = pricelistName;


            }
        }

        public static class PrefixesBean {
            /**
             * prefixId : 1
             * prefixName : בלי
             */
            public  int itemImg;
            public  int itemImgActive;
            public boolean isSelect = false;
            public String itemName;
            private String prefixId;
            private String prefixName;

            public int getItemImg() {
                return itemImg;
            }

            public void setItemImg(int itemImg) {
                this.itemImg = itemImg;
            }

            public int getItemImgActive() {
                return itemImgActive;
            }

            public void setItemImgActive(int itemImgActive) {
                this.itemImgActive = itemImgActive;
            }

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public String getPrefixId() {
                return prefixId;
            }

            public void setPrefixId(String prefixId) {
                this.prefixId = prefixId;
            }

            public String getPrefixName() {
                return prefixName;
            }

            public void setPrefixName(String prefixName) {
                this.prefixName = prefixName;
            }
        }

        public static class MenuBean {

            public  int itemImg;
            public  int itemImgActive;
            public boolean isSelect = false;
            public String itemName;
            /**
             * groupId : 2
             * groupName : Breakfast
             * groupPlace : 0
             * groupActive : 1
             * groupParentId : 0
             * groupFromTime :
             * groupUptoTime :
             * igProperty : 0
             * chkPermission : False
             * items : [{"itemId":"841","itemName":"Chachuka","itemGroupId":"2","itemPrice":"65","itemAddonPrice":"0","addonsGroups":[{"addonsGroupId":"44","addonsGroupName":"Drink Addons","addonsGroupPlace":"0","addonsGroupIsMandatory":"0","addonsGroupMax":"4","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"23","addonName":"Oranges squeezed","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]}]}]},{"itemId":"65","itemName":"Couple Morning","itemGroupId":"2","itemPrice":"116","itemAddonPrice":"0","preparations":["69"],"addonsGroups":[{"addonsGroupId":"11","addonsGroupName":"Couple Eggs","addonsGroupPlace":"0","addonsGroupIsMandatory":"1","addonsGroupMax":"2","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"6","addonName":"ביצה קשה","addonGroupId":"8","itemPrice":"0","addonPrice":"0","preparations":["117"]}]}]},{"itemId":"437","itemName":"Mini Sandwich","itemGroupId":"2","itemPrice":"20","itemAddonPrice":"0","pricevalues":[{"pricevaluesId":"72226","pricevaluesPriceId":"2","pricevaluesKind":"NORMAL","pricevaluesPrice":"20"}],"addonsGroups":[{"addonsGroupId":"12","addonsGroupName":"סוגי מיני כריך","addonsGroupPlace":"1","addonsGroupIsMandatory":"1","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"409","addonName":"מיני כריך בולגרית","addonGroupId":"9","itemPrice":"0","addonPrice":"0","preparations":["30","59","58","32"]},{"addonId":"84","addonName":"מיני כריך בריאות","addonGroupId":"9","itemPrice":"0","addonPrice":"0","preparations":["63","46","74","59","153","58"]},{"addonId":"83","addonName":"מיני כריך חביתה","addonGroupId":"9","itemPrice":"0","addonPrice":"0","preparations":["154","106","64","59","58"]},{"addonId":"82","addonName":"מיני כריך חביתת ירק","addonGroupId":"9","itemPrice":"0","addonPrice":"0","preparations":["154","106","64","59","58"]},{"addonId":"81","addonName":"מיני כריך טונה","addonGroupId":"9","itemPrice":"0","addonPrice":"0","preparations":["64","59","91","58"]},{"addonId":"663","addonName":"מיני כריך לאבנה","addonGroupId":"9","itemPrice":"0","addonPrice":"0","preparations":["25","74","32"]},{"addonId":"923","addonName":"מיני כריך סלמון","addonGroupId":"9","itemPrice":"5","addonPrice":"5","preparations":["106","60","59","58"]}]}]},{"itemId":"840","itemName":"Morning Omlet Sanwic","itemGroupId":"2","itemPrice":"48","itemAddonPrice":"0","preparations":["106","64","59"],"addonsGroups":[{"addonsGroupId":"40","addonsGroupName":"בחירת לחם","addonsGroupPlace":"0","addonsGroupIsMandatory":"0","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"496","addonName":"גבטה לבנה כ/ט","addonGroupId":"70","itemPrice":"0","addonPrice":"0"}]}]},{"itemId":"425","itemName":"Morning pastry","itemGroupId":"2","itemPrice":"16","itemAddonPrice":"0","addonsGroups":[{"addonsGroupId":"31","addonsGroupName":"מאפה בוקר","addonsGroupPlace":"0","addonsGroupIsMandatory":"1","addonsGroupMax":"2","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"103","addonName":"קרואסון חמאה","addonGroupId":"10","itemPrice":"0","addonPrice":"0"}]}]},{"itemId":"741","itemName":"Muzzli","itemGroupId":"2","itemPrice":"42","itemAddonPrice":"0","preparations":["7","5","6"]},{"itemId":"78","itemName":"Muzzli Breakfast","itemGroupId":"2","itemPrice":"42","itemAddonPrice":"0","preparations":["7"],"pricevalues":[{"pricevaluesId":"72196","pricevaluesPriceId":"2","pricevaluesKind":"NORMAL","pricevaluesPrice":"42"}],"addonsGroups":[{"addonsGroupId":"27","addonsGroupName":"חמה או טבעית ","addonsGroupPlace":"0","addonsGroupIsMandatory":"0","addonsGroupMax":"2","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"21","addonName":"Americano","addonGroupId":"3","itemPrice":"13","addonPrice":"0","preparations":["24"]}]}]}]
             * subgroups : [{"groupId":"44","groupName":"Deal Fishes","groupPlace":"44","groupActive":"1","groupParentId":"42","groupFromTime":"","groupUptoTime":"","igProperty":"0","chkPermission":"False","items":[{"itemId":"793","itemName":"Bory fillet","itemGroupId":"44","itemPrice":"74","itemAddonPrice":"0","preparations":["3"],"pricevalues":[{"pricevaluesId":"70391","pricevaluesPriceId":"1","pricevaluesKind":"NORMAL","pricevaluesPrice":"67"},{"pricevaluesId":"70392","pricevaluesPriceId":"1","pricevaluesKind":"ADDONS","pricevaluesPrice":"0"},{"pricevaluesId":"70393","pricevaluesPriceId":"1","pricevaluesKind":"CLUB","pricevaluesPrice":"0"},{"pricevaluesId":"70394","pricevaluesPriceId":"1","pricevaluesKind":"TAKEAWAY","pricevaluesPrice":"63"},{"pricevaluesId":"70395","pricevaluesPriceId":"1","pricevaluesKind":"DELIVERY","pricevaluesPrice":"0"}],"addonsGroups":[{"addonsGroupId":"24","addonsGroupName":"Deals Entry","addonsGroupPlace":"0","addonsGroupIsMandatory":"0","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"305","addonName":"ברוסקט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"308","addonName":"חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"383","addonName":"טונה צרובה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"681","addonName":"לאבנה  עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"307","addonName":"מרק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"545","addonName":"סיגר חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"384","addonName":"סלט בורגול עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"952","addonName":"סלט ג'וליינים עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"309","addonName":"סלט ירוק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["76","77","75","74","45","8"]},{"addonId":"701","addonName":"סלט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"304","addonName":"סלט קצוץ ראשונה ע","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["42","94","32"]},{"addonId":"961","addonName":"סמבוסק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"415","addonName":"פוקצ'ה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"543","addonName":"פלפל קלוי עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"544","addonName":"קינואה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"683","addonName":"קרוסטיני קממבר עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"306","addonName":"קרפצ'ו סלמון עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"495","addonName":"ראשונת היום עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]}]},{"addonsGroupId":"3","addonsGroupName":"Single Cold Drinks","addonsGroupPlace":"2","addonsGroupIsMandatory":"0","addonsGroupMax":"4","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"185","addonName":"1/3 טובורג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"182","addonName":"1/3 קאלסברג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"38","addonName":"Apple Cyder","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"37","addonName":"Apple Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"413","addonName":"Caraffe Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["127","128","129","130","131","132","133","126","35","41","38"]},{"addonId":"27","addonName":"Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"419","addonName":"Cup of Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["35","41"]},{"addonId":"28","addonName":"Diet Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"44","addonName":"Diet Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"31","addonName":"Diet Sprite","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"32","addonName":"Fanta","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"34","addonName":"Fuze Tea","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"414","addonName":"Grapefruit Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"39","addonName":"Grapes","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"43","addonName":"Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"412","addonName":"Ice Cup","addonGroupId":"4","itemPrice":"0","addonPrice":"0"},{"addonId":"25","addonName":"Lemonade","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"26","addonName":"Mineral Water","addonGroupId":"4","itemPrice":"9","addonPrice":"0","preparations":["35","41","38","155"]},{"addonId":"24","addonName":"Oranges","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"23","addonName":"Oranges squeezed","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"35","addonName":"Peach Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"36","addonName":"Red grapfruit","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"33","addonName":"Soda","addonGroupId":"4","itemPrice":"11","addonPrice":"0","preparations":["35","41"]},{"addonId":"30","addonName":"Sprite","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41"]},{"addonId":"29","addonName":"Zero Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"47","addonName":"גזר סחוט במקום","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"521","addonName":"דיאט ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"520","addonName":"דיאט קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"665","addonName":"כ. יין אדום ע","addonGroupId":"48","itemPrice":"12","addonPrice":"0"},{"addonId":"666","addonName":"כ. יין לבן ע","addonGroupId":"48","itemPrice":"15","addonPrice":"0"},{"addonId":"426","addonName":"לימונענע גרוס","addonGroupId":"4","itemPrice":"18","addonPrice":"5","preparations":["35","41","38"]},{"addonId":"42","addonName":"מאלט בירה שחורה","addonGroupId":"4","itemPrice":"17","addonPrice":"0"},{"addonId":"524","addonName":"ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"41","addonName":"פראללה מוגז קטן","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"522","addonName":"קולה זירו פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"523","addonName":"קוקה קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"45","addonName":"קפה קר אספרסו","addonGroupId":"4","itemPrice":"16","addonPrice":"5","preparations":["24","35","17","18","22","16","170","171"]},{"addonId":"46","addonName":"תה ירוק קר","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"410","addonName":"תפוגזר","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["76","35"]}]}]},{"itemId":"893","itemName":"M. Salmon today","itemGroupId":"44","itemPrice":"79","itemAddonPrice":"0","pricevalues":[{"pricevaluesId":"70331","pricevaluesPriceId":"1","pricevaluesKind":"NORMAL","pricevaluesPrice":"66"}],"addonsGroups":[{"addonsGroupId":"24","addonsGroupName":"Deals Entry","addonsGroupPlace":"0","addonsGroupIsMandatory":"0","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"305","addonName":"ברוסקט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"308","addonName":"חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"383","addonName":"טונה צרובה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"681","addonName":"לאבנה  עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"307","addonName":"מרק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"545","addonName":"סיגר חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"384","addonName":"סלט בורגול עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"952","addonName":"סלט ג'וליינים עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"309","addonName":"סלט ירוק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["76","77","75","74","45","8"]},{"addonId":"701","addonName":"סלט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"304","addonName":"סלט קצוץ ראשונה ע","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["42","94","32"]},{"addonId":"961","addonName":"סמבוסק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"415","addonName":"פוקצ'ה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"543","addonName":"פלפל קלוי עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"544","addonName":"קינואה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"683","addonName":"קרוסטיני קממבר עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"306","addonName":"קרפצ'ו סלמון עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"495","addonName":"ראשונת היום עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]}]},{"addonsGroupId":"3","addonsGroupName":"Single Cold Drinks","addonsGroupPlace":"2","addonsGroupIsMandatory":"0","addonsGroupMax":"4","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"185","addonName":"1/3 טובורג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"182","addonName":"1/3 קאלסברג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"38","addonName":"Apple Cyder","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"37","addonName":"Apple Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"413","addonName":"Caraffe Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["127","128","129","130","131","132","133","126","35","41","38"]},{"addonId":"27","addonName":"Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"419","addonName":"Cup of Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["35","41"]},{"addonId":"28","addonName":"Diet Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"44","addonName":"Diet Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"31","addonName":"Diet Sprite","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"32","addonName":"Fanta","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"34","addonName":"Fuze Tea","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"414","addonName":"Grapefruit Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"39","addonName":"Grapes","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"43","addonName":"Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"412","addonName":"Ice Cup","addonGroupId":"4","itemPrice":"0","addonPrice":"0"},{"addonId":"25","addonName":"Lemonade","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"26","addonName":"Mineral Water","addonGroupId":"4","itemPrice":"9","addonPrice":"0","preparations":["35","41","38","155"]},{"addonId":"24","addonName":"Oranges","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"23","addonName":"Oranges squeezed","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"35","addonName":"Peach Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"36","addonName":"Red grapfruit","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"33","addonName":"Soda","addonGroupId":"4","itemPrice":"11","addonPrice":"0","preparations":["35","41"]},{"addonId":"30","addonName":"Sprite","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41"]},{"addonId":"29","addonName":"Zero Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"47","addonName":"גזר סחוט במקום","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"521","addonName":"דיאט ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"520","addonName":"דיאט קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"665","addonName":"כ. יין אדום ע","addonGroupId":"48","itemPrice":"12","addonPrice":"0"},{"addonId":"666","addonName":"כ. יין לבן ע","addonGroupId":"48","itemPrice":"15","addonPrice":"0"},{"addonId":"426","addonName":"לימונענע גרוס","addonGroupId":"4","itemPrice":"18","addonPrice":"5","preparations":["35","41","38"]},{"addonId":"42","addonName":"מאלט בירה שחורה","addonGroupId":"4","itemPrice":"17","addonPrice":"0"},{"addonId":"524","addonName":"ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"41","addonName":"פראללה מוגז קטן","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"522","addonName":"קולה זירו פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"523","addonName":"קוקה קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"45","addonName":"קפה קר אספרסו","addonGroupId":"4","itemPrice":"16","addonPrice":"5","preparations":["24","35","17","18","22","16","170","171"]},{"addonId":"46","addonName":"תה ירוק קר","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"410","addonName":"תפוגזר","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["76","35"]}]}]},{"itemId":"680","itemName":"Tuna skewers p","itemGroupId":"44","itemPrice":"69","itemAddonPrice":"0","pricevalues":[{"pricevaluesId":"70421","pricevaluesPriceId":"1","pricevaluesKind":"NORMAL","pricevaluesPrice":"63"},{"pricevaluesId":"70422","pricevaluesPriceId":"1","pricevaluesKind":"ADDONS","pricevaluesPrice":"0"},{"pricevaluesId":"70423","pricevaluesPriceId":"1","pricevaluesKind":"CLUB","pricevaluesPrice":"0"},{"pricevaluesId":"70424","pricevaluesPriceId":"1","pricevaluesKind":"TAKEAWAY","pricevaluesPrice":"60"},{"pricevaluesId":"70425","pricevaluesPriceId":"1","pricevaluesKind":"DELIVERY","pricevaluesPrice":"0"}],"addonsGroups":[{"addonsGroupId":"24","addonsGroupName":"Deals Entry","addonsGroupPlace":"0","addonsGroupIsMandatory":"0","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"305","addonName":"ברוסקט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"308","addonName":"חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"383","addonName":"טונה צרובה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"681","addonName":"לאבנה  עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"307","addonName":"מרק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"545","addonName":"סיגר חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"384","addonName":"סלט בורגול עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"952","addonName":"סלט ג'וליינים עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"309","addonName":"סלט ירוק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["76","77","75","74","45","8"]},{"addonId":"701","addonName":"סלט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"304","addonName":"סלט קצוץ ראשונה ע","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["42","94","32"]},{"addonId":"961","addonName":"סמבוסק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"415","addonName":"פוקצ'ה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"543","addonName":"פלפל קלוי עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"544","addonName":"קינואה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"683","addonName":"קרוסטיני קממבר עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"306","addonName":"קרפצ'ו סלמון עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"495","addonName":"ראשונת היום עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]}]},{"addonsGroupId":"16","addonsGroupName":"Fish Addons","addonsGroupPlace":"0","addonsGroupIsMandatory":"1","addonsGroupMax":"3","addonsGroupEnh":"True","chProperty":"0","addons":[{"addonId":"726","addonName":"סלט ירוק","addonGroupId":"15","itemPrice":"0","addonPrice":"0","preparations":["185","194","196","195","193","197","192"]},{"addonId":"128","addonName":"סלט קצוץ","addonGroupId":"15","itemPrice":"0","addonPrice":"0","preparations":["185","187","191","190","186","192"]},{"addonId":"126","addonName":"ת.אורז","addonGroupId":"15","itemPrice":"0","addonPrice":"0"},{"addonId":"125","addonName":"ת.ירקות קלויים","addonGroupId":"15","itemPrice":"0","addonPrice":"0"},{"addonId":"123","addonName":"ת.פוטטוס","addonGroupId":"15","itemPrice":"0","addonPrice":"0"},{"addonId":"124","addonName":"ת.פירה","addonGroupId":"15","itemPrice":"0","addonPrice":"0"},{"addonId":"122","addonName":"ת.צ'יפס","addonGroupId":"15","itemPrice":"0","addonPrice":"0"},{"addonId":"127","addonName":"ת.צ'יפס בטטה","addonGroupId":"15","itemPrice":"0","addonPrice":"5"},{"addonId":"129","addonName":"ת.קוביות בטטה","addonGroupId":"15","itemPrice":"0","addonPrice":"0"}]},{"addonsGroupId":"26","addonsGroupName":"לחם / שתייה לקחת","addonsGroupPlace":"1","addonsGroupIsMandatory":"0","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"27","addonName":"Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"28","addonName":"Diet Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"31","addonName":"Diet Sprite","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"68","addonName":"Full ciabatta","addonGroupId":"5","itemPrice":"7","addonPrice":"0"},{"addonId":"25","addonName":"Lemonade","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"26","addonName":"Mineral Water","addonGroupId":"4","itemPrice":"9","addonPrice":"0","preparations":["35","41","38","155"]},{"addonId":"24","addonName":"Oranges","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"23","addonName":"Oranges squeezed","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"36","addonName":"Red grapfruit","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"70","addonName":"Rye","addonGroupId":"5","itemPrice":"7","addonPrice":"0"},{"addonId":"33","addonName":"Soda","addonGroupId":"4","itemPrice":"11","addonPrice":"0","preparations":["35","41"]},{"addonId":"30","addonName":"Sprite","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41"]},{"addonId":"69","addonName":"White ciabatta","addonGroupId":"5","itemPrice":"7","addonPrice":"0"},{"addonId":"29","addonName":"Zero Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"47","addonName":"גזר סחוט במקום","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]}]},{"addonsGroupId":"3","addonsGroupName":"Single Cold Drinks","addonsGroupPlace":"2","addonsGroupIsMandatory":"0","addonsGroupMax":"4","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"185","addonName":"1/3 טובורג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"182","addonName":"1/3 קאלסברג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"38","addonName":"Apple Cyder","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"37","addonName":"Apple Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"413","addonName":"Caraffe Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["127","128","129","130","131","132","133","126","35","41","38"]},{"addonId":"27","addonName":"Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"419","addonName":"Cup of Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["35","41"]},{"addonId":"28","addonName":"Diet Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"44","addonName":"Diet Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"31","addonName":"Diet Sprite","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"32","addonName":"Fanta","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"34","addonName":"Fuze Tea","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"414","addonName":"Grapefruit Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"39","addonName":"Grapes","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"43","addonName":"Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"412","addonName":"Ice Cup","addonGroupId":"4","itemPrice":"0","addonPrice":"0"},{"addonId":"25","addonName":"Lemonade","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"26","addonName":"Mineral Water","addonGroupId":"4","itemPrice":"9","addonPrice":"0","preparations":["35","41","38","155"]},{"addonId":"24","addonName":"Oranges","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"23","addonName":"Oranges squeezed","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"35","addonName":"Peach Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"36","addonName":"Red grapfruit","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"33","addonName":"Soda","addonGroupId":"4","itemPrice":"11","addonPrice":"0","preparations":["35","41"]},{"addonId":"30","addonName":"Sprite","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41"]},{"addonId":"29","addonName":"Zero Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"47","addonName":"גזר סחוט במקום","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"521","addonName":"דיאט ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"520","addonName":"דיאט קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"665","addonName":"כ. יין אדום ע","addonGroupId":"48","itemPrice":"12","addonPrice":"0"},{"addonId":"666","addonName":"כ. יין לבן ע","addonGroupId":"48","itemPrice":"15","addonPrice":"0"},{"addonId":"426","addonName":"לימונענע גרוס","addonGroupId":"4","itemPrice":"18","addonPrice":"5","preparations":["35","41","38"]},{"addonId":"42","addonName":"מאלט בירה שחורה","addonGroupId":"4","itemPrice":"17","addonPrice":"0"},{"addonId":"524","addonName":"ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"41","addonName":"פראללה מוגז קטן","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"522","addonName":"קולה זירו פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"523","addonName":"קוקה קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"45","addonName":"קפה קר אספרסו","addonGroupId":"4","itemPrice":"16","addonPrice":"5","preparations":["24","35","17","18","22","16","170","171"]},{"addonId":"46","addonName":"תה ירוק קר","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"410","addonName":"תפוגזר","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["76","35"]}]}]}]},{"groupId":"54","groupName":"Deal Fried","groupPlace":"44","groupActive":"1","groupParentId":"42","groupFromTime":"","groupUptoTime":"","igProperty":"0","chkPermission":"False","items":[{"itemId":"894","itemName":"מנת היום סלמון","itemGroupId":"54","itemPrice":"69","itemAddonPrice":"0","pricevalues":[{"pricevaluesId":"70796","pricevaluesPriceId":"1","pricevaluesKind":"NORMAL","pricevaluesPrice":"59"}],"addonsGroups":[{"addonsGroupId":"24","addonsGroupName":"Deals Entry","addonsGroupPlace":"0","addonsGroupIsMandatory":"0","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"305","addonName":"ברוסקט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"308","addonName":"חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"383","addonName":"טונה צרובה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"681","addonName":"לאבנה  עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"307","addonName":"מרק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"545","addonName":"סיגר חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"384","addonName":"סלט בורגול עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"952","addonName":"סלט ג'וליינים עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"309","addonName":"סלט ירוק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["76","77","75","74","45","8"]},{"addonId":"701","addonName":"סלט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"304","addonName":"סלט קצוץ ראשונה ע","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["42","94","32"]},{"addonId":"961","addonName":"סמבוסק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"415","addonName":"פוקצ'ה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"543","addonName":"פלפל קלוי עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"544","addonName":"קינואה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"683","addonName":"קרוסטיני קממבר עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"306","addonName":"קרפצ'ו סלמון עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"495","addonName":"ראשונת היום עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]}]},{"addonsGroupId":"3","addonsGroupName":"Single Cold Drinks","addonsGroupPlace":"2","addonsGroupIsMandatory":"0","addonsGroupMax":"4","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"185","addonName":"1/3 טובורג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"182","addonName":"1/3 קאלסברג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"38","addonName":"Apple Cyder","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"37","addonName":"Apple Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"413","addonName":"Caraffe Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["127","128","129","130","131","132","133","126","35","41","38"]},{"addonId":"27","addonName":"Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"419","addonName":"Cup of Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["35","41"]},{"addonId":"28","addonName":"Diet Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"44","addonName":"Diet Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"31","addonName":"Diet Sprite","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"32","addonName":"Fanta","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"34","addonName":"Fuze Tea","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"414","addonName":"Grapefruit Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"39","addonName":"Grapes","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"43","addonName":"Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"412","addonName":"Ice Cup","addonGroupId":"4","itemPrice":"0","addonPrice":"0"},{"addonId":"25","addonName":"Lemonade","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"26","addonName":"Mineral Water","addonGroupId":"4","itemPrice":"9","addonPrice":"0","preparations":["35","41","38","155"]},{"addonId":"24","addonName":"Oranges","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"23","addonName":"Oranges squeezed","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"35","addonName":"Peach Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"36","addonName":"Red grapfruit","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"33","addonName":"Soda","addonGroupId":"4","itemPrice":"11","addonPrice":"0","preparations":["35","41"]},{"addonId":"30","addonName":"Sprite","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41"]},{"addonId":"29","addonName":"Zero Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"47","addonName":"גזר סחוט במקום","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"521","addonName":"דיאט ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"520","addonName":"דיאט קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"665","addonName":"כ. יין אדום ע","addonGroupId":"48","itemPrice":"12","addonPrice":"0"},{"addonId":"666","addonName":"כ. יין לבן ע","addonGroupId":"48","itemPrice":"15","addonPrice":"0"},{"addonId":"426","addonName":"לימונענע גרוס","addonGroupId":"4","itemPrice":"18","addonPrice":"5","preparations":["35","41","38"]},{"addonId":"42","addonName":"מאלט בירה שחורה","addonGroupId":"4","itemPrice":"17","addonPrice":"0"},{"addonId":"524","addonName":"ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"41","addonName":"פראללה מוגז קטן","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"522","addonName":"קולה זירו פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"523","addonName":"קוקה קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"45","addonName":"קפה קר אספרסו","addonGroupId":"4","itemPrice":"16","addonPrice":"5","preparations":["24","35","17","18","22","16","170","171"]},{"addonId":"46","addonName":"תה ירוק קר","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"410","addonName":"תפוגזר","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["76","35"]}]}]}]},{"groupId":"45","groupName":"Deal Pasta","groupPlace":"44","groupActive":"1","groupParentId":"42","groupFromTime":"","groupUptoTime":"","igProperty":"0","chkPermission":"False","items":[{"itemId":"928","itemName":"Add salmon cubes","itemGroupId":"45","itemPrice":"10","itemAddonPrice":"0","pricevalues":[{"pricevaluesId":"58661","pricevaluesPriceId":"1","pricevaluesKind":"NORMAL","pricevaluesPrice":"59"}]},{"itemId":"385","itemName":"Cheese ravioli p","itemGroupId":"45","itemPrice":"69","itemAddonPrice":"0","pricevalues":[{"pricevaluesId":"58616","pricevaluesPriceId":"1","pricevaluesKind":"NORMAL","pricevaluesPrice":"62"}],"addonsGroups":[{"addonsGroupId":"24","addonsGroupName":"Deals Entry","addonsGroupPlace":"0","addonsGroupIsMandatory":"0","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"305","addonName":"ברוסקט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"308","addonName":"חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]}]},{"addonsGroupId":"23","addonsGroupName":"Pasta Sauces","addonsGroupPlace":"0","addonsGroupIsMandatory":"1","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"677","addonName":"אלי אולי תרד ובזיליק","addonGroupId":"40","itemPrice":"0","addonPrice":"0"}]},{"addonsGroupId":"26","addonsGroupName":"לחם / שתייה לקחת","addonsGroupPlace":"1","addonsGroupIsMandatory":"0","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"27","addonName":"Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"68","addonName":"Full ciabatta","addonGroupId":"5","itemPrice":"7","addonPrice":"0"}]}]}]},{"groupId":"43","groupName":"Deal Salads","groupPlace":"44","groupActive":"1","groupParentId":"42","groupFromTime":"","groupUptoTime":"","igProperty":"0","chkPermission":"False","items":[{"itemId":"693","itemName":"Bulgur Salad","itemGroupId":"43","itemPrice":"59","itemAddonPrice":"0","preparations":["63","38","48","84","108","160","161"],"pricevalues":[{"pricevaluesId":"69821","pricevaluesPriceId":"1","pricevaluesKind":"NORMAL","pricevaluesPrice":"53"},{"pricevaluesId":"69822","pricevaluesPriceId":"1","pricevaluesKind":"ADDONS","pricevaluesPrice":"0"},{"pricevaluesId":"69823","pricevaluesPriceId":"1","pricevaluesKind":"CLUB","pricevaluesPrice":"0"},{"pricevaluesId":"69824","pricevaluesPriceId":"1","pricevaluesKind":"TAKEAWAY","pricevaluesPrice":"49"},{"pricevaluesId":"69825","pricevaluesPriceId":"1","pricevaluesKind":"DELIVERY","pricevaluesPrice":"0"}],"addonsGroups":[{"addonsGroupId":"24","addonsGroupName":"Deals Entry","addonsGroupPlace":"0","addonsGroupIsMandatory":"0","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"305","addonName":"ברוסקט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"308","addonName":"חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"383","addonName":"טונה צרובה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"681","addonName":"לאבנה  עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"307","addonName":"מרק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"545","addonName":"סיגר חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"384","addonName":"סלט בורגול עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"952","addonName":"סלט ג'וליינים עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"309","addonName":"סלט ירוק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["76","77","75","74","45","8"]},{"addonId":"701","addonName":"סלט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"304","addonName":"סלט קצוץ ראשונה ע","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["42","94","32"]},{"addonId":"961","addonName":"סמבוסק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"415","addonName":"פוקצ'ה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"543","addonName":"פלפל קלוי עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"544","addonName":"קינואה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"683","addonName":"קרוסטיני קממבר עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"306","addonName":"קרפצ'ו סלמון עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"495","addonName":"ראשונת היום עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]}]},{"addonsGroupId":"26","addonsGroupName":"לחם / שתייה לקחת","addonsGroupPlace":"1","addonsGroupIsMandatory":"0","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"27","addonName":"Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"28","addonName":"Diet Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"31","addonName":"Diet Sprite","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"68","addonName":"Full ciabatta","addonGroupId":"5","itemPrice":"7","addonPrice":"0"},{"addonId":"25","addonName":"Lemonade","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"26","addonName":"Mineral Water","addonGroupId":"4","itemPrice":"9","addonPrice":"0","preparations":["35","41","38","155"]},{"addonId":"24","addonName":"Oranges","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"23","addonName":"Oranges squeezed","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"36","addonName":"Red grapfruit","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"70","addonName":"Rye","addonGroupId":"5","itemPrice":"7","addonPrice":"0"},{"addonId":"33","addonName":"Soda","addonGroupId":"4","itemPrice":"11","addonPrice":"0","preparations":["35","41"]},{"addonId":"30","addonName":"Sprite","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41"]},{"addonId":"69","addonName":"White ciabatta","addonGroupId":"5","itemPrice":"7","addonPrice":"0"},{"addonId":"29","addonName":"Zero Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"47","addonName":"גזר סחוט במקום","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]}]},{"addonsGroupId":"3","addonsGroupName":"Single Cold Drinks","addonsGroupPlace":"2","addonsGroupIsMandatory":"0","addonsGroupMax":"4","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"185","addonName":"1/3 טובורג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"182","addonName":"1/3 קאלסברג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"38","addonName":"Apple Cyder","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"37","addonName":"Apple Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"413","addonName":"Caraffe Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["127","128","129","130","131","132","133","126","35","41","38"]},{"addonId":"27","addonName":"Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"419","addonName":"Cup of Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["35","41"]},{"addonId":"28","addonName":"Diet Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"44","addonName":"Diet Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"31","addonName":"Diet Sprite","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"32","addonName":"Fanta","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"34","addonName":"Fuze Tea","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"414","addonName":"Grapefruit Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"39","addonName":"Grapes","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"43","addonName":"Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"412","addonName":"Ice Cup","addonGroupId":"4","itemPrice":"0","addonPrice":"0"},{"addonId":"25","addonName":"Lemonade","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"26","addonName":"Mineral Water","addonGroupId":"4","itemPrice":"9","addonPrice":"0","preparations":["35","41","38","155"]},{"addonId":"24","addonName":"Oranges","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"23","addonName":"Oranges squeezed","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"35","addonName":"Peach Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"36","addonName":"Red grapfruit","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"33","addonName":"Soda","addonGroupId":"4","itemPrice":"11","addonPrice":"0","preparations":["35","41"]},{"addonId":"30","addonName":"Sprite","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41"]},{"addonId":"29","addonName":"Zero Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"47","addonName":"גזר סחוט במקום","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"521","addonName":"דיאט ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"520","addonName":"דיאט קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"665","addonName":"כ. יין אדום ע","addonGroupId":"48","itemPrice":"12","addonPrice":"0"},{"addonId":"666","addonName":"כ. יין לבן ע","addonGroupId":"48","itemPrice":"15","addonPrice":"0"},{"addonId":"426","addonName":"לימונענע גרוס","addonGroupId":"4","itemPrice":"18","addonPrice":"5","preparations":["35","41","38"]},{"addonId":"42","addonName":"מאלט בירה שחורה","addonGroupId":"4","itemPrice":"17","addonPrice":"0"},{"addonId":"524","addonName":"ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"41","addonName":"פראללה מוגז קטן","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"522","addonName":"קולה זירו פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"523","addonName":"קוקה קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"45","addonName":"קפה קר אספרסו","addonGroupId":"4","itemPrice":"16","addonPrice":"5","preparations":["24","35","17","18","22","16","170","171"]},{"addonId":"46","addonName":"תה ירוק קר","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"410","addonName":"תפוגזר","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["76","35"]}]}]}]},{"groupId":"55","groupName":"Deal Sandwiches","groupPlace":"44","groupActive":"1","groupParentId":"42","groupFromTime":"","groupUptoTime":"","igProperty":"0","chkPermission":"False","items":[{"itemId":"510","itemName":"טוסט איטלקי ע","itemGroupId":"55","itemPrice":"56","itemAddonPrice":"0","preparations":["28"],"pricevalues":[{"pricevaluesId":"58676","pricevaluesPriceId":"1","pricevaluesKind":"NORMAL","pricevaluesPrice":"53"}],"addonsGroups":[{"addonsGroupId":"24","addonsGroupName":"Deals Entry","addonsGroupPlace":"0","addonsGroupIsMandatory":"0","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"305","addonName":"ברוסקט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"308","addonName":"חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]}]},{"addonsGroupId":"40","addonsGroupName":"בחירת לחם","addonsGroupPlace":"0","addonsGroupIsMandatory":"0","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"496","addonName":"גבטה לבנה כ/ט","addonGroupId":"70","itemPrice":"0","addonPrice":"0"},{"addonId":"497","addonName":"גבטה מלאה כ/ט","addonGroupId":"70","itemPrice":"0","addonPrice":"0"},{"addonId":"675","addonName":"לחם ללא גלוטן כ/ט","addonGroupId":"70","itemPrice":"0","addonPrice":"5"},{"addonId":"498","addonName":"שיפון כ/ט","addonGroupId":"70","itemPrice":"0","addonPrice":"0"}]},{"addonsGroupId":"26","addonsGroupName":"לחם / שתייה לקחת","addonsGroupPlace":"1","addonsGroupIsMandatory":"0","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"27","addonName":"Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"28","addonName":"Diet Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"31","addonName":"Diet Sprite","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"68","addonName":"Full ciabatta","addonGroupId":"5","itemPrice":"7","addonPrice":"0"},{"addonId":"25","addonName":"Lemonade","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"26","addonName":"Mineral Water","addonGroupId":"4","itemPrice":"9","addonPrice":"0","preparations":["35","41","38","155"]},{"addonId":"24","addonName":"Oranges","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"23","addonName":"Oranges squeezed","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"36","addonName":"Red grapfruit","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"70","addonName":"Rye","addonGroupId":"5","itemPrice":"7","addonPrice":"0"},{"addonId":"33","addonName":"Soda","addonGroupId":"4","itemPrice":"11","addonPrice":"0","preparations":["35","41"]},{"addonId":"30","addonName":"Sprite","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41"]},{"addonId":"69","addonName":"White ciabatta","addonGroupId":"5","itemPrice":"7","addonPrice":"0"},{"addonId":"29","addonName":"Zero Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"47","addonName":"גזר סחוט במקום","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]}]},{"addonsGroupId":"3","addonsGroupName":"Single Cold Drinks","addonsGroupPlace":"2","addonsGroupIsMandatory":"0","addonsGroupMax":"4","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"185","addonName":"1/3 טובורג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"182","addonName":"1/3 קאלסברג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"38","addonName":"Apple Cyder","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"37","addonName":"Apple Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"413","addonName":"Caraffe Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["127","128","129","130","131","132","133","126","35","41","38"]},{"addonId":"27","addonName":"Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"419","addonName":"Cup of Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["35","41"]},{"addonId":"28","addonName":"Diet Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"44","addonName":"Diet Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"31","addonName":"Diet Sprite","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"32","addonName":"Fanta","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"34","addonName":"Fuze Tea","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"414","addonName":"Grapefruit Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"39","addonName":"Grapes","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"43","addonName":"Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"412","addonName":"Ice Cup","addonGroupId":"4","itemPrice":"0","addonPrice":"0"},{"addonId":"25","addonName":"Lemonade","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"26","addonName":"Mineral Water","addonGroupId":"4","itemPrice":"9","addonPrice":"0","preparations":["35","41","38","155"]},{"addonId":"24","addonName":"Oranges","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"23","addonName":"Oranges squeezed","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"35","addonName":"Peach Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"36","addonName":"Red grapfruit","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"33","addonName":"Soda","addonGroupId":"4","itemPrice":"11","addonPrice":"0","preparations":["35","41"]},{"addonId":"30","addonName":"Sprite","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41"]},{"addonId":"29","addonName":"Zero Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"47","addonName":"גזר סחוט במקום","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"521","addonName":"דיאט ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"520","addonName":"דיאט קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"665","addonName":"כ. יין אדום ע","addonGroupId":"48","itemPrice":"12","addonPrice":"0"},{"addonId":"666","addonName":"כ. יין לבן ע","addonGroupId":"48","itemPrice":"15","addonPrice":"0"},{"addonId":"426","addonName":"לימונענע גרוס","addonGroupId":"4","itemPrice":"18","addonPrice":"5","preparations":["35","41","38"]},{"addonId":"42","addonName":"מאלט בירה שחורה","addonGroupId":"4","itemPrice":"17","addonPrice":"0"},{"addonId":"524","addonName":"ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"41","addonName":"פראללה מוגז קטן","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"522","addonName":"קולה זירו פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"523","addonName":"קוקה קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"45","addonName":"קפה קר אספרסו","addonGroupId":"4","itemPrice":"16","addonPrice":"5","preparations":["24","35","17","18","22","16","170","171"]},{"addonId":"46","addonName":"תה ירוק קר","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"410","addonName":"תפוגזר","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["76","35"]}]}]}]}]
             */

            private String groupId;
            private String groupName;
            private String groupPlace;
            private String groupActive;
            private String groupParentId;
            private String groupFromTime;
            private String groupUptoTime;
            private String igProperty;
            private String chkPermission;
            private List<ItemsBean> items;
            private List<SubgroupsBean> subgroups;

            public int getItemImg() {
                return itemImg;
            }

            public void setItemImg(int itemImg) {
                this.itemImg = itemImg;
            }

            public int getItemImgActive() {
                return itemImgActive;
            }

            public void setItemImgActive(int itemImgActive) {
                this.itemImgActive = itemImgActive;
            }

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public String getGroupId() {
                return groupId;
            }

            public void setGroupId(String groupId) {
                this.groupId = groupId;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public String getGroupPlace() {
                return groupPlace;
            }

            public void setGroupPlace(String groupPlace) {
                this.groupPlace = groupPlace;
            }

            public String getGroupActive() {
                return groupActive;
            }

            public void setGroupActive(String groupActive) {
                this.groupActive = groupActive;
            }

            public String getGroupParentId() {
                return groupParentId;
            }

            public void setGroupParentId(String groupParentId) {
                this.groupParentId = groupParentId;
            }

            public String getGroupFromTime() {
                return groupFromTime;
            }

            public void setGroupFromTime(String groupFromTime) {
                this.groupFromTime = groupFromTime;
            }

            public String getGroupUptoTime() {
                return groupUptoTime;
            }

            public void setGroupUptoTime(String groupUptoTime) {
                this.groupUptoTime = groupUptoTime;
            }

            public String getIgProperty() {
                return igProperty;
            }

            public void setIgProperty(String igProperty) {
                this.igProperty = igProperty;
            }

            public String getChkPermission() {
                return chkPermission;
            }

            public void setChkPermission(String chkPermission) {
                this.chkPermission = chkPermission;
            }

            public List<ItemsBean> getItems() {
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            public List<SubgroupsBean> getSubgroups() {
                return subgroups;
            }

            public void setSubgroups(List<SubgroupsBean> subgroups) {
                this.subgroups = subgroups;
            }

            public static class ItemsBean {
                /**
                 * itemId : 841
                 * itemName : Chachuka
                 * itemGroupId : 2
                 * itemPrice : 65
                 * itemAddonPrice : 0
                 * addonsGroups : [{"addonsGroupId":"44","addonsGroupName":"Drink Addons","addonsGroupPlace":"0","addonsGroupIsMandatory":"0","addonsGroupMax":"4","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"23","addonName":"Oranges squeezed","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]}]}]
                 * preparations : ["69"]
                 * pricevalues : [{"pricevaluesId":"72226","pricevaluesPriceId":"2","pricevaluesKind":"NORMAL","pricevaluesPrice":"20"}]
                 */


                public  int itemImg;
                public int itemImgActive;
                public boolean isSelect = false;
                private String itemId;
                private String itemName;
                private String preparations;
                private String itemGroupId;
                private String itemPrice;
                private String itemAddonPrice;
                private List<AddonsGroupsBean> addonsGroups;
                private String pricevalues;

                public int getItemImg() {
                    return itemImg;
                }

                public void setItemImg(int itemImg) {
                    this.itemImg = itemImg;
                }

                public int getItemImgActive() {
                    return itemImgActive;
                }

                public void setItemImgActive(int itemImgActive) {
                    this.itemImgActive = itemImgActive;
                }

                public boolean isSelect() {
                    return isSelect;
                }

                public void setSelect(boolean select) {
                    isSelect = select;
                }

                public String getPreparations() {
                    return preparations;
                }

                public void setPreparations(String preparations) {
                    this.preparations = preparations;
                }

                public String getItemId() {
                    return itemId;
                }

                public void setItemId(String itemId) {
                    this.itemId = itemId;
                }

                public String getItemName() {
                    return itemName;
                }

                public void setItemName(String itemName) {
                    this.itemName = itemName;
                }

                public String getItemGroupId() {
                    return itemGroupId;
                }

                public void setItemGroupId(String itemGroupId) {
                    this.itemGroupId = itemGroupId;
                }

                public String getItemPrice() {
                    return itemPrice;
                }

                public void setItemPrice(String itemPrice) {
                    this.itemPrice = itemPrice;
                }

                public String getItemAddonPrice() {
                    return itemAddonPrice;
                }

                public void setItemAddonPrice(String itemAddonPrice) {
                    this.itemAddonPrice = itemAddonPrice;
                }

                public List<AddonsGroupsBean> getAddonsGroups() {
                    return addonsGroups;
                }

             /*   public List<String> getPreparations() {
                    return preparations;
                }

                public void setPreparations(List<String> preparations) {
                    this.preparations = preparations;
                }*/

                public void setAddonsGroups(List<AddonsGroupsBean> addonsGroups) {
                    this.addonsGroups = addonsGroups;
                }

                public String getPricevalues() {
                    return pricevalues;
                }

                public void setPricevalues(String pricevalues) {
                    this.pricevalues = pricevalues;
                }
             /*  public List<PricevaluesBean> getPricevalues() {
                    return pricevalues;
                }

                public void setPricevalues(List<PricevaluesBean> pricevalues) {
                    this.pricevalues = pricevalues;
                }*/

                public static class AddonsGroupsBean {
                    /**
                     * addonsGroupId : 44
                     * addonsGroupName : Drink Addons
                     * addonsGroupPlace : 0
                     * addonsGroupIsMandatory : 0
                     * addonsGroupMax : 4
                     * addonsGroupEnh : False
                     * chProperty : 0
                     * addons : [{"addonId":"23","addonName":"Oranges squeezed","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]}]
                     */

                   public String addOnItemID;
                    public int itemImg;
                    public int itemImgActive;
                    public boolean isSelect = false;
                    public String itemName;
                    private String addonsGroupId;
                    private String addonsGroupName;
                    private String addonsGroupPlace;
                    private String addonsGroupIsMandatory;
                    private String addonsGroupMax;
                    private String addonsGroupEnh;
                    private String chProperty;
                    private List<AddonsBean> addons;

                    public String getAddOnItemID() {
                        return addOnItemID;
                    }

                    public void setAddOnItemID(String addOnItemID) {
                        this.addOnItemID = addOnItemID;
                    }

                    public int getItemImg() {
                        return itemImg;
                    }

                    public void setItemImg(int itemImg) {
                        this.itemImg = itemImg;
                    }

                    public int getItemImgActive() {
                        return itemImgActive;
                    }

                    public void setItemImgActive(int itemImgActive) {
                        this.itemImgActive = itemImgActive;
                    }

                    public boolean isSelect() {
                        return isSelect;
                    }

                    public void setSelect(boolean select) {
                        isSelect = select;
                    }

                    public String getItemName() {
                        return itemName;
                    }

                    public void setItemName(String itemName) {
                        this.itemName = itemName;
                    }

                    public String getAddonsGroupId() {
                        return addonsGroupId;
                    }

                    public void setAddonsGroupId(String addonsGroupId) {
                        this.addonsGroupId = addonsGroupId;
                    }

                    public String getAddonsGroupName() {
                        return addonsGroupName;
                    }

                    public void setAddonsGroupName(String addonsGroupName) {
                        this.addonsGroupName = addonsGroupName;
                    }

                    public String getAddonsGroupPlace() {
                        return addonsGroupPlace;
                    }

                    public void setAddonsGroupPlace(String addonsGroupPlace) {
                        this.addonsGroupPlace = addonsGroupPlace;
                    }

                    public String getAddonsGroupIsMandatory() {
                        return addonsGroupIsMandatory;
                    }

                    public void setAddonsGroupIsMandatory(String addonsGroupIsMandatory) {
                        this.addonsGroupIsMandatory = addonsGroupIsMandatory;
                    }

                    public String getAddonsGroupMax() {
                        return addonsGroupMax;
                    }

                    public void setAddonsGroupMax(String addonsGroupMax) {
                        this.addonsGroupMax = addonsGroupMax;
                    }

                    public String getAddonsGroupEnh() {
                        return addonsGroupEnh;
                    }

                    public void setAddonsGroupEnh(String addonsGroupEnh) {
                        this.addonsGroupEnh = addonsGroupEnh;
                    }

                    public String getChProperty() {
                        return chProperty;
                    }

                    public void setChProperty(String chProperty) {
                        this.chProperty = chProperty;
                    }

                    public List<AddonsBean> getAddons() {
                        return addons;
                    }

                    public void setAddons(List<AddonsBean> addons) {
                        this.addons = addons;
                    }

                    public static class AddonsBean {
                        /**
                         * addonId : 23
                         * addonName : Oranges squeezed
                         * addonGroupId : 4
                         * itemPrice : 15
                         * addonPrice : 3
                         * preparations : ["35"]
                         */

                       public String addOnItemIdchild;
                        public int itemImg;
                        public int itemImgActive;
                        public boolean isSelect = false;
                        public String itemName;
                        private String addonId;
                        private String addonName;
                        private String addonGroupId;
                        private String itemPrice;
                        private String addonPrice;
                        private List<String> preparations;

                        public String getAddOnItemIdchild() {
                            return addOnItemIdchild;
                        }

                        public void setAddOnItemIdchild(String addOnItemIdchild) {
                            this.addOnItemIdchild = addOnItemIdchild;
                        }

                        public int getItemImg() {
                            return itemImg;
                        }

                        public void setItemImg(int itemImg) {
                            this.itemImg = itemImg;
                        }

                        public int getItemImgActive() {
                            return itemImgActive;
                        }

                        public void setItemImgActive(int itemImgActive) {
                            this.itemImgActive = itemImgActive;
                        }

                        public boolean isSelect() {
                            return isSelect;
                        }

                        public void setSelect(boolean select) {
                            isSelect = select;
                        }

                        public String getItemName() {
                            return itemName;
                        }

                        public void setItemName(String itemName) {
                            this.itemName = itemName;
                        }

                        public String getAddonId() {
                            return addonId;
                        }

                        public void setAddonId(String addonId) {
                            this.addonId = addonId;
                        }

                        public String getAddonName() {
                            return addonName;
                        }

                        public void setAddonName(String addonName) {
                            this.addonName = addonName;
                        }

                        public String getAddonGroupId() {
                            return addonGroupId;
                        }

                        public void setAddonGroupId(String addonGroupId) {
                            this.addonGroupId = addonGroupId;
                        }

                        public String getItemPrice() {
                            return itemPrice;
                        }

                        public void setItemPrice(String itemPrice) {
                            this.itemPrice = itemPrice;
                        }

                        public String getAddonPrice() {
                            return addonPrice;
                        }

                        public void setAddonPrice(String addonPrice) {
                            this.addonPrice = addonPrice;
                        }

                        public List<String> getPreparations() {
                            return preparations;
                        }

                        public void setPreparations(List<String> preparations) {
                            this.preparations = preparations;
                        }
                    }
                }

                public static class PricevaluesBean {
                    /**
                     * pricevaluesId : 72226
                     * pricevaluesPriceId : 2
                     * pricevaluesKind : NORMAL
                     * pricevaluesPrice : 20
                     */

                    private String pricevaluesId;
                    private String pricevaluesPriceId;
                    private String pricevaluesKind;
                    private String pricevaluesPrice;

                    public String getPricevaluesId() {
                        return pricevaluesId;
                    }

                    public void setPricevaluesId(String pricevaluesId) {
                        this.pricevaluesId = pricevaluesId;
                    }

                    public String getPricevaluesPriceId() {
                        return pricevaluesPriceId;
                    }

                    public void setPricevaluesPriceId(String pricevaluesPriceId) {
                        this.pricevaluesPriceId = pricevaluesPriceId;
                    }

                    public String getPricevaluesKind() {
                        return pricevaluesKind;
                    }

                    public void setPricevaluesKind(String pricevaluesKind) {
                        this.pricevaluesKind = pricevaluesKind;
                    }

                    public String getPricevaluesPrice() {
                        return pricevaluesPrice;
                    }

                    public void setPricevaluesPrice(String pricevaluesPrice) {
                        this.pricevaluesPrice = pricevaluesPrice;
                    }
                }
            }

            public static class SubgroupsBean {
                /**
                 * groupId : 44
                 * groupName : Deal Fishes
                 * groupPlace : 44
                 * groupActive : 1
                 * groupParentId : 42
                 * groupFromTime :
                 * groupUptoTime :
                 * igProperty : 0
                 * chkPermission : False
                 * items : [{"itemId":"793","itemName":"Bory fillet","itemGroupId":"44","itemPrice":"74","itemAddonPrice":"0","preparations":["3"],"pricevalues":[{"pricevaluesId":"70391","pricevaluesPriceId":"1","pricevaluesKind":"NORMAL","pricevaluesPrice":"67"},{"pricevaluesId":"70392","pricevaluesPriceId":"1","pricevaluesKind":"ADDONS","pricevaluesPrice":"0"},{"pricevaluesId":"70393","pricevaluesPriceId":"1","pricevaluesKind":"CLUB","pricevaluesPrice":"0"},{"pricevaluesId":"70394","pricevaluesPriceId":"1","pricevaluesKind":"TAKEAWAY","pricevaluesPrice":"63"},{"pricevaluesId":"70395","pricevaluesPriceId":"1","pricevaluesKind":"DELIVERY","pricevaluesPrice":"0"}],"addonsGroups":[{"addonsGroupId":"24","addonsGroupName":"Deals Entry","addonsGroupPlace":"0","addonsGroupIsMandatory":"0","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"305","addonName":"ברוסקט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"308","addonName":"חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"383","addonName":"טונה צרובה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"681","addonName":"לאבנה  עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"307","addonName":"מרק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"545","addonName":"סיגר חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"384","addonName":"סלט בורגול עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"952","addonName":"סלט ג'וליינים עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"309","addonName":"סלט ירוק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["76","77","75","74","45","8"]},{"addonId":"701","addonName":"סלט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"304","addonName":"סלט קצוץ ראשונה ע","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["42","94","32"]},{"addonId":"961","addonName":"סמבוסק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"415","addonName":"פוקצ'ה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"543","addonName":"פלפל קלוי עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"544","addonName":"קינואה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"683","addonName":"קרוסטיני קממבר עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"306","addonName":"קרפצ'ו סלמון עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"495","addonName":"ראשונת היום עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]}]},{"addonsGroupId":"3","addonsGroupName":"Single Cold Drinks","addonsGroupPlace":"2","addonsGroupIsMandatory":"0","addonsGroupMax":"4","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"185","addonName":"1/3 טובורג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"182","addonName":"1/3 קאלסברג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"38","addonName":"Apple Cyder","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"37","addonName":"Apple Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"413","addonName":"Caraffe Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["127","128","129","130","131","132","133","126","35","41","38"]},{"addonId":"27","addonName":"Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"419","addonName":"Cup of Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["35","41"]},{"addonId":"28","addonName":"Diet Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"44","addonName":"Diet Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"31","addonName":"Diet Sprite","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"32","addonName":"Fanta","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"34","addonName":"Fuze Tea","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"414","addonName":"Grapefruit Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"39","addonName":"Grapes","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"43","addonName":"Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"412","addonName":"Ice Cup","addonGroupId":"4","itemPrice":"0","addonPrice":"0"},{"addonId":"25","addonName":"Lemonade","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"26","addonName":"Mineral Water","addonGroupId":"4","itemPrice":"9","addonPrice":"0","preparations":["35","41","38","155"]},{"addonId":"24","addonName":"Oranges","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"23","addonName":"Oranges squeezed","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"35","addonName":"Peach Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"36","addonName":"Red grapfruit","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"33","addonName":"Soda","addonGroupId":"4","itemPrice":"11","addonPrice":"0","preparations":["35","41"]},{"addonId":"30","addonName":"Sprite","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41"]},{"addonId":"29","addonName":"Zero Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"47","addonName":"גזר סחוט במקום","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"521","addonName":"דיאט ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"520","addonName":"דיאט קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"665","addonName":"כ. יין אדום ע","addonGroupId":"48","itemPrice":"12","addonPrice":"0"},{"addonId":"666","addonName":"כ. יין לבן ע","addonGroupId":"48","itemPrice":"15","addonPrice":"0"},{"addonId":"426","addonName":"לימונענע גרוס","addonGroupId":"4","itemPrice":"18","addonPrice":"5","preparations":["35","41","38"]},{"addonId":"42","addonName":"מאלט בירה שחורה","addonGroupId":"4","itemPrice":"17","addonPrice":"0"},{"addonId":"524","addonName":"ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"41","addonName":"פראללה מוגז קטן","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"522","addonName":"קולה זירו פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"523","addonName":"קוקה קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"45","addonName":"קפה קר אספרסו","addonGroupId":"4","itemPrice":"16","addonPrice":"5","preparations":["24","35","17","18","22","16","170","171"]},{"addonId":"46","addonName":"תה ירוק קר","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"410","addonName":"תפוגזר","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["76","35"]}]}]},{"itemId":"893","itemName":"M. Salmon today","itemGroupId":"44","itemPrice":"79","itemAddonPrice":"0","pricevalues":[{"pricevaluesId":"70331","pricevaluesPriceId":"1","pricevaluesKind":"NORMAL","pricevaluesPrice":"66"}],"addonsGroups":[{"addonsGroupId":"24","addonsGroupName":"Deals Entry","addonsGroupPlace":"0","addonsGroupIsMandatory":"0","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"305","addonName":"ברוסקט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"308","addonName":"חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"383","addonName":"טונה צרובה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"681","addonName":"לאבנה  עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"307","addonName":"מרק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"545","addonName":"סיגר חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"384","addonName":"סלט בורגול עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"952","addonName":"סלט ג'וליינים עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"309","addonName":"סלט ירוק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["76","77","75","74","45","8"]},{"addonId":"701","addonName":"סלט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"304","addonName":"סלט קצוץ ראשונה ע","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["42","94","32"]},{"addonId":"961","addonName":"סמבוסק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"415","addonName":"פוקצ'ה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"543","addonName":"פלפל קלוי עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"544","addonName":"קינואה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"683","addonName":"קרוסטיני קממבר עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"306","addonName":"קרפצ'ו סלמון עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"495","addonName":"ראשונת היום עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]}]},{"addonsGroupId":"3","addonsGroupName":"Single Cold Drinks","addonsGroupPlace":"2","addonsGroupIsMandatory":"0","addonsGroupMax":"4","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"185","addonName":"1/3 טובורג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"182","addonName":"1/3 קאלסברג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"38","addonName":"Apple Cyder","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"37","addonName":"Apple Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"413","addonName":"Caraffe Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["127","128","129","130","131","132","133","126","35","41","38"]},{"addonId":"27","addonName":"Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"419","addonName":"Cup of Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["35","41"]},{"addonId":"28","addonName":"Diet Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"44","addonName":"Diet Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"31","addonName":"Diet Sprite","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"32","addonName":"Fanta","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"34","addonName":"Fuze Tea","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"414","addonName":"Grapefruit Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"39","addonName":"Grapes","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"43","addonName":"Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"412","addonName":"Ice Cup","addonGroupId":"4","itemPrice":"0","addonPrice":"0"},{"addonId":"25","addonName":"Lemonade","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"26","addonName":"Mineral Water","addonGroupId":"4","itemPrice":"9","addonPrice":"0","preparations":["35","41","38","155"]},{"addonId":"24","addonName":"Oranges","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"23","addonName":"Oranges squeezed","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"35","addonName":"Peach Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"36","addonName":"Red grapfruit","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"33","addonName":"Soda","addonGroupId":"4","itemPrice":"11","addonPrice":"0","preparations":["35","41"]},{"addonId":"30","addonName":"Sprite","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41"]},{"addonId":"29","addonName":"Zero Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"47","addonName":"גזר סחוט במקום","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"521","addonName":"דיאט ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"520","addonName":"דיאט קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"665","addonName":"כ. יין אדום ע","addonGroupId":"48","itemPrice":"12","addonPrice":"0"},{"addonId":"666","addonName":"כ. יין לבן ע","addonGroupId":"48","itemPrice":"15","addonPrice":"0"},{"addonId":"426","addonName":"לימונענע גרוס","addonGroupId":"4","itemPrice":"18","addonPrice":"5","preparations":["35","41","38"]},{"addonId":"42","addonName":"מאלט בירה שחורה","addonGroupId":"4","itemPrice":"17","addonPrice":"0"},{"addonId":"524","addonName":"ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"41","addonName":"פראללה מוגז קטן","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"522","addonName":"קולה זירו פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"523","addonName":"קוקה קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"45","addonName":"קפה קר אספרסו","addonGroupId":"4","itemPrice":"16","addonPrice":"5","preparations":["24","35","17","18","22","16","170","171"]},{"addonId":"46","addonName":"תה ירוק קר","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"410","addonName":"תפוגזר","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["76","35"]}]}]},{"itemId":"680","itemName":"Tuna skewers p","itemGroupId":"44","itemPrice":"69","itemAddonPrice":"0","pricevalues":[{"pricevaluesId":"70421","pricevaluesPriceId":"1","pricevaluesKind":"NORMAL","pricevaluesPrice":"63"},{"pricevaluesId":"70422","pricevaluesPriceId":"1","pricevaluesKind":"ADDONS","pricevaluesPrice":"0"},{"pricevaluesId":"70423","pricevaluesPriceId":"1","pricevaluesKind":"CLUB","pricevaluesPrice":"0"},{"pricevaluesId":"70424","pricevaluesPriceId":"1","pricevaluesKind":"TAKEAWAY","pricevaluesPrice":"60"},{"pricevaluesId":"70425","pricevaluesPriceId":"1","pricevaluesKind":"DELIVERY","pricevaluesPrice":"0"}],"addonsGroups":[{"addonsGroupId":"24","addonsGroupName":"Deals Entry","addonsGroupPlace":"0","addonsGroupIsMandatory":"0","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"305","addonName":"ברוסקט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"308","addonName":"חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"383","addonName":"טונה צרובה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"681","addonName":"לאבנה  עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"307","addonName":"מרק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"545","addonName":"סיגר חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"384","addonName":"סלט בורגול עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"952","addonName":"סלט ג'וליינים עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"309","addonName":"סלט ירוק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["76","77","75","74","45","8"]},{"addonId":"701","addonName":"סלט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"304","addonName":"סלט קצוץ ראשונה ע","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["42","94","32"]},{"addonId":"961","addonName":"סמבוסק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"415","addonName":"פוקצ'ה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"543","addonName":"פלפל קלוי עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"544","addonName":"קינואה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"683","addonName":"קרוסטיני קממבר עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"306","addonName":"קרפצ'ו סלמון עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"495","addonName":"ראשונת היום עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]}]},{"addonsGroupId":"16","addonsGroupName":"Fish Addons","addonsGroupPlace":"0","addonsGroupIsMandatory":"1","addonsGroupMax":"3","addonsGroupEnh":"True","chProperty":"0","addons":[{"addonId":"726","addonName":"סלט ירוק","addonGroupId":"15","itemPrice":"0","addonPrice":"0","preparations":["185","194","196","195","193","197","192"]},{"addonId":"128","addonName":"סלט קצוץ","addonGroupId":"15","itemPrice":"0","addonPrice":"0","preparations":["185","187","191","190","186","192"]},{"addonId":"126","addonName":"ת.אורז","addonGroupId":"15","itemPrice":"0","addonPrice":"0"},{"addonId":"125","addonName":"ת.ירקות קלויים","addonGroupId":"15","itemPrice":"0","addonPrice":"0"},{"addonId":"123","addonName":"ת.פוטטוס","addonGroupId":"15","itemPrice":"0","addonPrice":"0"},{"addonId":"124","addonName":"ת.פירה","addonGroupId":"15","itemPrice":"0","addonPrice":"0"},{"addonId":"122","addonName":"ת.צ'יפס","addonGroupId":"15","itemPrice":"0","addonPrice":"0"},{"addonId":"127","addonName":"ת.צ'יפס בטטה","addonGroupId":"15","itemPrice":"0","addonPrice":"5"},{"addonId":"129","addonName":"ת.קוביות בטטה","addonGroupId":"15","itemPrice":"0","addonPrice":"0"}]},{"addonsGroupId":"26","addonsGroupName":"לחם / שתייה לקחת","addonsGroupPlace":"1","addonsGroupIsMandatory":"0","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"27","addonName":"Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"28","addonName":"Diet Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"31","addonName":"Diet Sprite","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"68","addonName":"Full ciabatta","addonGroupId":"5","itemPrice":"7","addonPrice":"0"},{"addonId":"25","addonName":"Lemonade","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"26","addonName":"Mineral Water","addonGroupId":"4","itemPrice":"9","addonPrice":"0","preparations":["35","41","38","155"]},{"addonId":"24","addonName":"Oranges","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"23","addonName":"Oranges squeezed","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"36","addonName":"Red grapfruit","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"70","addonName":"Rye","addonGroupId":"5","itemPrice":"7","addonPrice":"0"},{"addonId":"33","addonName":"Soda","addonGroupId":"4","itemPrice":"11","addonPrice":"0","preparations":["35","41"]},{"addonId":"30","addonName":"Sprite","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41"]},{"addonId":"69","addonName":"White ciabatta","addonGroupId":"5","itemPrice":"7","addonPrice":"0"},{"addonId":"29","addonName":"Zero Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"47","addonName":"גזר סחוט במקום","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]}]},{"addonsGroupId":"3","addonsGroupName":"Single Cold Drinks","addonsGroupPlace":"2","addonsGroupIsMandatory":"0","addonsGroupMax":"4","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"185","addonName":"1/3 טובורג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"182","addonName":"1/3 קאלסברג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"38","addonName":"Apple Cyder","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"37","addonName":"Apple Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"413","addonName":"Caraffe Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["127","128","129","130","131","132","133","126","35","41","38"]},{"addonId":"27","addonName":"Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"419","addonName":"Cup of Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["35","41"]},{"addonId":"28","addonName":"Diet Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"44","addonName":"Diet Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"31","addonName":"Diet Sprite","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"32","addonName":"Fanta","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"34","addonName":"Fuze Tea","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"414","addonName":"Grapefruit Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"39","addonName":"Grapes","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"43","addonName":"Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"412","addonName":"Ice Cup","addonGroupId":"4","itemPrice":"0","addonPrice":"0"},{"addonId":"25","addonName":"Lemonade","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"26","addonName":"Mineral Water","addonGroupId":"4","itemPrice":"9","addonPrice":"0","preparations":["35","41","38","155"]},{"addonId":"24","addonName":"Oranges","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"23","addonName":"Oranges squeezed","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"35","addonName":"Peach Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"36","addonName":"Red grapfruit","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"33","addonName":"Soda","addonGroupId":"4","itemPrice":"11","addonPrice":"0","preparations":["35","41"]},{"addonId":"30","addonName":"Sprite","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41"]},{"addonId":"29","addonName":"Zero Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"47","addonName":"גזר סחוט במקום","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"521","addonName":"דיאט ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"520","addonName":"דיאט קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"665","addonName":"כ. יין אדום ע","addonGroupId":"48","itemPrice":"12","addonPrice":"0"},{"addonId":"666","addonName":"כ. יין לבן ע","addonGroupId":"48","itemPrice":"15","addonPrice":"0"},{"addonId":"426","addonName":"לימונענע גרוס","addonGroupId":"4","itemPrice":"18","addonPrice":"5","preparations":["35","41","38"]},{"addonId":"42","addonName":"מאלט בירה שחורה","addonGroupId":"4","itemPrice":"17","addonPrice":"0"},{"addonId":"524","addonName":"ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"41","addonName":"פראללה מוגז קטן","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"522","addonName":"קולה זירו פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"523","addonName":"קוקה קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"45","addonName":"קפה קר אספרסו","addonGroupId":"4","itemPrice":"16","addonPrice":"5","preparations":["24","35","17","18","22","16","170","171"]},{"addonId":"46","addonName":"תה ירוק קר","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"410","addonName":"תפוגזר","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["76","35"]}]}]}]
                 */

                private String groupId;
                private String groupName;
                private String groupPlace;
                private String groupActive;
                private String groupParentId;
                private String groupFromTime;
                private String groupUptoTime;
                private String igProperty;
                private String chkPermission;
                private List<ItemsBeanX> items;

                public String getGroupId() {
                    return groupId;
                }

                public void setGroupId(String groupId) {
                    this.groupId = groupId;
                }

                public String getGroupName() {
                    return groupName;
                }

                public void setGroupName(String groupName) {
                    this.groupName = groupName;
                }

                public String getGroupPlace() {
                    return groupPlace;
                }

                public void setGroupPlace(String groupPlace) {
                    this.groupPlace = groupPlace;
                }

                public String getGroupActive() {
                    return groupActive;
                }

                public void setGroupActive(String groupActive) {
                    this.groupActive = groupActive;
                }

                public String getGroupParentId() {
                    return groupParentId;
                }

                public void setGroupParentId(String groupParentId) {
                    this.groupParentId = groupParentId;
                }

                public String getGroupFromTime() {
                    return groupFromTime;
                }

                public void setGroupFromTime(String groupFromTime) {
                    this.groupFromTime = groupFromTime;
                }

                public String getGroupUptoTime() {
                    return groupUptoTime;
                }

                public void setGroupUptoTime(String groupUptoTime) {
                    this.groupUptoTime = groupUptoTime;
                }

                public String getIgProperty() {
                    return igProperty;
                }

                public void setIgProperty(String igProperty) {
                    this.igProperty = igProperty;
                }

                public String getChkPermission() {
                    return chkPermission;
                }

                public void setChkPermission(String chkPermission) {
                    this.chkPermission = chkPermission;
                }

                public List<ItemsBeanX> getItems() {
                    return items;
                }

                public void setItems(List<ItemsBeanX> items) {
                    this.items = items;
                }

                public static class ItemsBeanX {
                    /**
                     * itemId : 793
                     * itemName : Bory fillet
                     * itemGroupId : 44
                     * itemPrice : 74
                     * itemAddonPrice : 0
                     * preparations : ["3"]
                     * pricevalues : [{"pricevaluesId":"70391","pricevaluesPriceId":"1","pricevaluesKind":"NORMAL","pricevaluesPrice":"67"},{"pricevaluesId":"70392","pricevaluesPriceId":"1","pricevaluesKind":"ADDONS","pricevaluesPrice":"0"},{"pricevaluesId":"70393","pricevaluesPriceId":"1","pricevaluesKind":"CLUB","pricevaluesPrice":"0"},{"pricevaluesId":"70394","pricevaluesPriceId":"1","pricevaluesKind":"TAKEAWAY","pricevaluesPrice":"63"},{"pricevaluesId":"70395","pricevaluesPriceId":"1","pricevaluesKind":"DELIVERY","pricevaluesPrice":"0"}]
                     * addonsGroups : [{"addonsGroupId":"24","addonsGroupName":"Deals Entry","addonsGroupPlace":"0","addonsGroupIsMandatory":"0","addonsGroupMax":"1","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"305","addonName":"ברוסקט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"308","addonName":"חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"383","addonName":"טונה צרובה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"681","addonName":"לאבנה  עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"307","addonName":"מרק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"545","addonName":"סיגר חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"384","addonName":"סלט בורגול עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"952","addonName":"סלט ג'וליינים עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"309","addonName":"סלט ירוק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["76","77","75","74","45","8"]},{"addonId":"701","addonName":"סלט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"304","addonName":"סלט קצוץ ראשונה ע","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["42","94","32"]},{"addonId":"961","addonName":"סמבוסק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"415","addonName":"פוקצ'ה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"543","addonName":"פלפל קלוי עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"544","addonName":"קינואה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"683","addonName":"קרוסטיני קממבר עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"306","addonName":"קרפצ'ו סלמון עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"495","addonName":"ראשונת היום עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]}]},{"addonsGroupId":"3","addonsGroupName":"Single Cold Drinks","addonsGroupPlace":"2","addonsGroupIsMandatory":"0","addonsGroupMax":"4","addonsGroupEnh":"False","chProperty":"0","addons":[{"addonId":"185","addonName":"1/3 טובורג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"182","addonName":"1/3 קאלסברג","addonGroupId":"23","itemPrice":"22","addonPrice":"0"},{"addonId":"38","addonName":"Apple Cyder","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"37","addonName":"Apple Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"413","addonName":"Caraffe Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["127","128","129","130","131","132","133","126","35","41","38"]},{"addonId":"27","addonName":"Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"419","addonName":"Cup of Water","addonGroupId":"4","itemPrice":"0","addonPrice":"0","preparations":["35","41"]},{"addonId":"28","addonName":"Diet Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"44","addonName":"Diet Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"31","addonName":"Diet Sprite","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"32","addonName":"Fanta","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"34","addonName":"Fuze Tea","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"414","addonName":"Grapefruit Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"39","addonName":"Grapes","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35"]},{"addonId":"43","addonName":"Ice Coffee","addonGroupId":"4","itemPrice":"18","addonPrice":"5"},{"addonId":"412","addonName":"Ice Cup","addonGroupId":"4","itemPrice":"0","addonPrice":"0"},{"addonId":"25","addonName":"Lemonade","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"26","addonName":"Mineral Water","addonGroupId":"4","itemPrice":"9","addonPrice":"0","preparations":["35","41","38","155"]},{"addonId":"24","addonName":"Oranges","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"23","addonName":"Oranges squeezed","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"35","addonName":"Peach Water","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"36","addonName":"Red grapfruit","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41","38"]},{"addonId":"33","addonName":"Soda","addonGroupId":"4","itemPrice":"11","addonPrice":"0","preparations":["35","41"]},{"addonId":"30","addonName":"Sprite","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35","41"]},{"addonId":"29","addonName":"Zero Coke","addonGroupId":"4","itemPrice":"13","addonPrice":"0","preparations":["35","41"]},{"addonId":"47","addonName":"גזר סחוט במקום","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["35"]},{"addonId":"521","addonName":"דיאט ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"520","addonName":"דיאט קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0"},{"addonId":"665","addonName":"כ. יין אדום ע","addonGroupId":"48","itemPrice":"12","addonPrice":"0"},{"addonId":"666","addonName":"כ. יין לבן ע","addonGroupId":"48","itemPrice":"15","addonPrice":"0"},{"addonId":"426","addonName":"לימונענע גרוס","addonGroupId":"4","itemPrice":"18","addonPrice":"5","preparations":["35","41","38"]},{"addonId":"42","addonName":"מאלט בירה שחורה","addonGroupId":"4","itemPrice":"17","addonPrice":"0"},{"addonId":"524","addonName":"ספרייט פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"41","addonName":"פראללה מוגז קטן","addonGroupId":"4","itemPrice":"12","addonPrice":"0","preparations":["35"]},{"addonId":"522","addonName":"קולה זירו פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"523","addonName":"קוקה קולה פחית","addonGroupId":"4","itemPrice":"8","addonPrice":"0","preparations":["35","41"]},{"addonId":"45","addonName":"קפה קר אספרסו","addonGroupId":"4","itemPrice":"16","addonPrice":"5","preparations":["24","35","17","18","22","16","170","171"]},{"addonId":"46","addonName":"תה ירוק קר","addonGroupId":"4","itemPrice":"14","addonPrice":"0","preparations":["35"]},{"addonId":"410","addonName":"תפוגזר","addonGroupId":"4","itemPrice":"15","addonPrice":"3","preparations":["76","35"]}]}]
                     */

                    private String itemId;
                    private String itemName;
                    private String itemGroupId;
                    private String itemPrice;
                    private String itemAddonPrice;
                    private List<String> preparations;
                    private List<PricevaluesBeanX> pricevalues;
                    private List<AddonsGroupsBeanX> addonsGroups;

                    public String getItemId() {
                        return itemId;
                    }

                    public void setItemId(String itemId) {
                        this.itemId = itemId;
                    }

                    public String getItemName() {
                        return itemName;
                    }

                    public void setItemName(String itemName) {
                        this.itemName = itemName;
                    }

                    public String getItemGroupId() {
                        return itemGroupId;
                    }

                    public void setItemGroupId(String itemGroupId) {
                        this.itemGroupId = itemGroupId;
                    }

                    public String getItemPrice() {
                        return itemPrice;
                    }

                    public void setItemPrice(String itemPrice) {
                        this.itemPrice = itemPrice;
                    }

                    public String getItemAddonPrice() {
                        return itemAddonPrice;
                    }

                    public void setItemAddonPrice(String itemAddonPrice) {
                        this.itemAddonPrice = itemAddonPrice;
                    }

                    public List<String> getPreparations() {
                        return preparations;
                    }

                    public void setPreparations(List<String> preparations) {
                        this.preparations = preparations;
                    }

                    public List<PricevaluesBeanX> getPricevalues() {
                        return pricevalues;
                    }

                    public void setPricevalues(List<PricevaluesBeanX> pricevalues) {
                        this.pricevalues = pricevalues;
                    }

                    public List<AddonsGroupsBeanX> getAddonsGroups() {
                        return addonsGroups;
                    }

                    public void setAddonsGroups(List<AddonsGroupsBeanX> addonsGroups) {
                        this.addonsGroups = addonsGroups;
                    }

                    public static class PricevaluesBeanX {
                        /**
                         * pricevaluesId : 70391
                         * pricevaluesPriceId : 1
                         * pricevaluesKind : NORMAL
                         * pricevaluesPrice : 67
                         */

                        private String pricevaluesId;
                        private String pricevaluesPriceId;
                        private String pricevaluesKind;
                        private String pricevaluesPrice;

                        public String getPricevaluesId() {
                            return pricevaluesId;
                        }

                        public void setPricevaluesId(String pricevaluesId) {
                            this.pricevaluesId = pricevaluesId;
                        }

                        public String getPricevaluesPriceId() {
                            return pricevaluesPriceId;
                        }

                        public void setPricevaluesPriceId(String pricevaluesPriceId) {
                            this.pricevaluesPriceId = pricevaluesPriceId;
                        }

                        public String getPricevaluesKind() {
                            return pricevaluesKind;
                        }

                        public void setPricevaluesKind(String pricevaluesKind) {
                            this.pricevaluesKind = pricevaluesKind;
                        }

                        public String getPricevaluesPrice() {
                            return pricevaluesPrice;
                        }

                        public void setPricevaluesPrice(String pricevaluesPrice) {
                            this.pricevaluesPrice = pricevaluesPrice;
                        }
                    }

                    public static class AddonsGroupsBeanX {
                        /**
                         * addonsGroupId : 24
                         * addonsGroupName : Deals Entry
                         * addonsGroupPlace : 0
                         * addonsGroupIsMandatory : 0
                         * addonsGroupMax : 1
                         * addonsGroupEnh : False
                         * chProperty : 0
                         * addons : [{"addonId":"305","addonName":"ברוסקט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"308","addonName":"חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"383","addonName":"טונה צרובה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"681","addonName":"לאבנה  עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"307","addonName":"מרק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"545","addonName":"סיגר חציל עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"384","addonName":"סלט בורגול עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"952","addonName":"סלט ג'וליינים עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"309","addonName":"סלט ירוק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["76","77","75","74","45","8"]},{"addonId":"701","addonName":"סלט עגבניות עסקי ","addonGroupId":"41","itemPrice":"0","addonPrice":"0"},{"addonId":"304","addonName":"סלט קצוץ ראשונה ע","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["42","94","32"]},{"addonId":"961","addonName":"סמבוסק עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"415","addonName":"פוקצ'ה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"543","addonName":"פלפל קלוי עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"544","addonName":"קינואה עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"683","addonName":"קרוסטיני קממבר עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]},{"addonId":"306","addonName":"קרפצ'ו סלמון עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["51","41","39","50"]},{"addonId":"495","addonName":"ראשונת היום עסקי","addonGroupId":"41","itemPrice":"0","addonPrice":"0","preparations":["94"]}]
                         */

                        private String addonsGroupId;
                        private String addonsGroupName;
                        private String addonsGroupPlace;
                        private String addonsGroupIsMandatory;
                        private String addonsGroupMax;
                        private String addonsGroupEnh;
                        private String chProperty;
                        private List<AddonsBeanX> addons;

                        public String getAddonsGroupId() {
                            return addonsGroupId;
                        }

                        public void setAddonsGroupId(String addonsGroupId) {
                            this.addonsGroupId = addonsGroupId;
                        }

                        public String getAddonsGroupName() {
                            return addonsGroupName;
                        }

                        public void setAddonsGroupName(String addonsGroupName) {
                            this.addonsGroupName = addonsGroupName;
                        }

                        public String getAddonsGroupPlace() {
                            return addonsGroupPlace;
                        }

                        public void setAddonsGroupPlace(String addonsGroupPlace) {
                            this.addonsGroupPlace = addonsGroupPlace;
                        }

                        public String getAddonsGroupIsMandatory() {
                            return addonsGroupIsMandatory;
                        }

                        public void setAddonsGroupIsMandatory(String addonsGroupIsMandatory) {
                            this.addonsGroupIsMandatory = addonsGroupIsMandatory;
                        }

                        public String getAddonsGroupMax() {
                            return addonsGroupMax;
                        }

                        public void setAddonsGroupMax(String addonsGroupMax) {
                            this.addonsGroupMax = addonsGroupMax;
                        }

                        public String getAddonsGroupEnh() {
                            return addonsGroupEnh;
                        }

                        public void setAddonsGroupEnh(String addonsGroupEnh) {
                            this.addonsGroupEnh = addonsGroupEnh;
                        }

                        public String getChProperty() {
                            return chProperty;
                        }

                        public void setChProperty(String chProperty) {
                            this.chProperty = chProperty;
                        }

                        public List<AddonsBeanX> getAddons() {
                            return addons;
                        }

                        public void setAddons(List<AddonsBeanX> addons) {
                            this.addons = addons;
                        }

                        public static class AddonsBeanX {
                            /**
                             * addonId : 305
                             * addonName : ברוסקט עגבניות עסקי
                             * addonGroupId : 41
                             * itemPrice : 0
                             * addonPrice : 0
                             * preparations : ["94"]
                             */

                            private String addonId;
                            private String addonName;
                            private String addonGroupId;
                            private String itemPrice;
                            private String addonPrice;
                            private List<String> preparations;

                            public String getAddonId() {
                                return addonId;
                            }

                            public void setAddonId(String addonId) {
                                this.addonId = addonId;
                            }

                            public String getAddonName() {
                                return addonName;
                            }

                            public void setAddonName(String addonName) {
                                this.addonName = addonName;
                            }

                            public String getAddonGroupId() {
                                return addonGroupId;
                            }

                            public void setAddonGroupId(String addonGroupId) {
                                this.addonGroupId = addonGroupId;
                            }

                            public String getItemPrice() {
                                return itemPrice;
                            }

                            public void setItemPrice(String itemPrice) {
                                this.itemPrice = itemPrice;
                            }

                            public String getAddonPrice() {
                                return addonPrice;
                            }

                            public void setAddonPrice(String addonPrice) {
                                this.addonPrice = addonPrice;
                            }

                            public List<String> getPreparations() {
                                return preparations;
                            }

                            public void setPreparations(List<String> preparations) {
                                this.preparations = preparations;
                            }
                        }
                    }
                }
            }
        }
    }
}