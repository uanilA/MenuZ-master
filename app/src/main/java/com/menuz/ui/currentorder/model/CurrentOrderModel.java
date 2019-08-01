package com.menuz.ui.currentorder.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mindiii on 27/11/18.
 */

public class CurrentOrderModel {


    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * orders : [{"orderID":"264063","orderTableID":"14","orderGuest":"","orderDiners":"2","orderEmployeeID":"1","orderEmployeeName":"Omar","orderStart":"30/10/2018 12:15:00","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0","items":[{"itemAutoID":"1596173","itemName":"Big Latte Soy","NULL":"","itemQuantity":"1","itemCourse":"0","itemDiner":"0","itemStatus":"0","itemPrice":"16","itemParentID":"","itemOrderID":"264063","itemPriceID":"0","itemRemark":"","itempreps":[{"ipreparationID":"15","ipreparationPrefixID":"0"},{"ipreparationID":"22","ipreparationPrefixID":"0"}]},{"itemAutoID":"1596172","itemName":"Capuchino Soy","NULL":"","itemQuantity":"1","itemCourse":"0","itemDiner":"0","itemStatus":"0","itemPrice":"14","itemParentID":"","itemOrderID":"264063","itemPriceID":"0","itemRemark":"","itempreps":[{"ipreparationID":"11","ipreparationPrefixID":"0"}]},{"itemAutoID":"1596171","itemName":"Ceviche","NULL":"","itemQuantity":"1","itemCourse":"0","itemDiner":"0","itemStatus":"0","itemPrice":"38","itemParentID":"","itemOrderID":"264063","itemPriceID":"0","itemRemark":"","itempreps":[{"ipreparationID":"45","ipreparationPrefixID":"5"}]},{"itemAutoID":"1596157","itemName":"Single Morning","NULL":"","itemQuantity":"1","itemCourse":"0","itemDiner":"0","itemStatus":"0","itemPrice":"63","itemParentID":"","itemOrderID":"264063","itemPriceID":"0","itemRemark":"","itempreps":[{"ipreparationID":"77","ipreparationPrefixID":"0"},{"ipreparationID":"10","ipreparationPrefixID":"0"}],"addons":[{"addonAutoID":"1596160","addonChoiceID":"1","addonID":"6","addonPrice":"0"},{"addonAutoID":"1596165","addonChoiceID":"2","addonID":"54","addonPrice":"2"},{"addonAutoID":"1596166","addonChoiceID":"2","addonID":"411","addonPrice":"0"},{"addonAutoID":"1596161","addonChoiceID":"35","addonID":"274","addonPrice":"4"},{"addonAutoID":"1596162","addonChoiceID":"37","addonID":"479","addonPrice":"10"},{"addonAutoID":"1596163","addonChoiceID":"37","addonID":"6","addonPrice":"0"},{"addonAutoID":"1596164","addonChoiceID":"37","addonID":"480","addonPrice":"10"},{"addonAutoID":"1596169","addonChoiceID":"39","addonID":"492","addonPrice":"0"},{"addonAutoID":"1596170","addonChoiceID":"39","addonID":"679","addonPrice":"5"},{"addonAutoID":"1596167","addonChoiceID":"41","addonID":"36","addonPrice":"0","addonpreps":[{"apreparationID":"41","apreparationPrefixID":"0"},{"apreparationID":"35","apreparationPrefixID":"0"}]},{"addonAutoID":"1596168","addonChoiceID":"41","addonID":"419","addonPrice":"0"},{"addonAutoID":"1596158","addonChoiceID":"42","addonID":"726","addonPrice":"0"},{"addonAutoID":"1596159","addonChoiceID":"44","addonID":"23","addonPrice":"3"}]}]},{"orderID":"264066","orderTableID":"21","orderGuest":"","orderDiners":"1","orderEmployeeID":"","orderEmployeeName":"","orderStart":"21/11/2018 12:05:05","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0","items":[{"itemAutoID":"1596176","itemName":"ת.פוטטוס","NULL":"","itemQuantity":"0","itemCourse":"0","itemDiner":"1","itemStatus":"0","itemPrice":"0","itemParentID":"","itemOrderID":"264066","itemPriceID":"0","itemRemark":""}]},{"orderID":"264069","orderTableID":"1","orderGuest":"","orderDiners":"1","orderEmployeeID":"","orderEmployeeName":"","orderStart":"21/11/2018 12:08:39","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0","items":[{"itemAutoID":"1596179","itemName":"ת.פוטטוס","NULL":"","itemQuantity":"0","itemCourse":"0","itemDiner":"1","itemStatus":"0","itemPrice":"0","itemParentID":"","itemOrderID":"264069","itemPriceID":"0","itemRemark":""}]},{"orderID":"264077","orderTableID":"15","orderGuest":"","orderDiners":"1","orderEmployeeID":"","orderEmployeeName":"","orderStart":"21/11/2018 15:26:19","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0","items":[{"itemAutoID":"1596189","itemName":"ת.פוטטוס","NULL":"","itemQuantity":"0","itemCourse":"0","itemDiner":"1","itemStatus":"0","itemPrice":"0","itemParentID":"","itemOrderID":"264077","itemPriceID":"0","itemRemark":""}]},{"orderID":"264078","orderTableID":"2","orderGuest":"","orderDiners":"1","orderEmployeeID":"1","orderEmployeeName":"Omar","orderStart":"21/11/2018 16:51:57","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0"},{"orderID":"264079","orderTableID":"16","orderGuest":"","orderDiners":"1","orderEmployeeID":"1","orderEmployeeName":"Omar","orderStart":"22/11/2018 12:53:45","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0"},{"orderID":"264080","orderTableID":"3","orderGuest":"","orderDiners":"1","orderEmployeeID":"1","orderEmployeeName":"Omar","orderStart":"22/11/2018 14:13:45","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0"},{"orderID":"264081","orderTableID":"19","orderGuest":"","orderDiners":"1","orderEmployeeID":"1","orderEmployeeName":"Omar","orderStart":"22/11/2018 15:24:56","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0"},{"orderID":"264082","orderTableID":"23","orderGuest":"","orderDiners":"1","orderEmployeeID":"1","orderEmployeeName":"Omar","orderStart":"23/11/2018 08:06:13","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0"},{"orderID":"264083","orderTableID":"20","orderGuest":"","orderDiners":"1","orderEmployeeID":"1","orderEmployeeName":"Omar","orderStart":"23/11/2018 08:38:42","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0"},{"orderID":"264084","orderTableID":"17","orderGuest":"","orderDiners":"1","orderEmployeeID":"1","orderEmployeeName":"Omar","orderStart":"23/11/2018 12:16:47","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0"},{"orderID":"264085","orderTableID":"26","orderGuest":"","orderDiners":"1","orderEmployeeID":"1","orderEmployeeName":"Omar","orderStart":"23/11/2018 12:28:13","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0"},{"orderID":"264086","orderTableID":"5","orderGuest":"","orderDiners":"1","orderEmployeeID":"1","orderEmployeeName":"Omar","orderStart":"23/11/2018 16:36:05","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0"},{"orderID":"264087","orderTableID":"1019","orderGuest":"","orderDiners":"1","orderEmployeeID":"1","orderEmployeeName":"Omar","orderStart":"23/11/2018 16:38:54","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0"},{"orderID":"264088","orderTableID":"1022","orderGuest":"","orderDiners":"1","orderEmployeeID":"1","orderEmployeeName":"Omar","orderStart":"24/11/2018 08:55:20","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0"},{"orderID":"264089","orderTableID":"13","orderGuest":"","orderDiners":"1","orderEmployeeID":"1","orderEmployeeName":"Omar","orderStart":"24/11/2018 12:33:21","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0"},{"orderID":"264090","orderTableID":"4","orderGuest":"","orderDiners":"1","orderEmployeeID":"1","orderEmployeeName":"Omar","orderStart":"24/11/2018 12:39:59","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0"},{"orderID":"264091","orderTableID":"40","orderGuest":"","orderDiners":"1","orderEmployeeID":"1","orderEmployeeName":"Omar","orderStart":"26/11/2018 12:03:07","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0"},{"orderID":"264092","orderTableID":"200","orderGuest":"","orderDiners":"1","orderEmployeeID":"","orderEmployeeName":"","orderStart":"26/11/2018 14:59:58","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0"},{"orderID":"264093","orderTableID":"6","orderGuest":"","orderDiners":"1","orderEmployeeID":"","orderEmployeeName":"","orderStart":"26/11/2018 16:18:28","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0"},{"orderID":"264094","orderTableID":"304","orderGuest":"","orderDiners":"1","orderEmployeeID":"","orderEmployeeName":"","orderStart":"27/11/2018 08:28:00","orderDiscountPCT":"0","orderBill":"","orderTerminalID":"","orderRemark":"","orderStatus":"0"}]
         * success : true
         */

        private String success;
        private List<OrdersBean> orders;

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public List<OrdersBean> getOrders() {
            return orders;
        }

        public void setOrders(List<OrdersBean> orders) {
            this.orders = orders;
        }

        public static class OrdersBean {
            /**
             * orderID : 264063
             * orderTableID : 14
             * orderGuest :
             * orderDiners : 2
             * orderEmployeeID : 1
             * orderEmployeeName : Omar
             * orderStart : 30/10/2018 12:15:00
             * orderDiscountPCT : 0
             * orderBill :
             * orderTerminalID :
             * orderRemark :
             * orderStatus : 0
             * items : [{"itemAutoID":"1596173","itemName":"Big Latte Soy","NULL":"","itemQuantity":"1","itemCourse":"0","itemDiner":"0","itemStatus":"0","itemPrice":"16","itemParentID":"","itemOrderID":"264063","itemPriceID":"0","itemRemark":"","itempreps":[{"ipreparationID":"15","ipreparationPrefixID":"0"},{"ipreparationID":"22","ipreparationPrefixID":"0"}]},{"itemAutoID":"1596172","itemName":"Capuchino Soy","NULL":"","itemQuantity":"1","itemCourse":"0","itemDiner":"0","itemStatus":"0","itemPrice":"14","itemParentID":"","itemOrderID":"264063","itemPriceID":"0","itemRemark":"","itempreps":[{"ipreparationID":"11","ipreparationPrefixID":"0"}]},{"itemAutoID":"1596171","itemName":"Ceviche","NULL":"","itemQuantity":"1","itemCourse":"0","itemDiner":"0","itemStatus":"0","itemPrice":"38","itemParentID":"","itemOrderID":"264063","itemPriceID":"0","itemRemark":"","itempreps":[{"ipreparationID":"45","ipreparationPrefixID":"5"}]},{"itemAutoID":"1596157","itemName":"Single Morning","NULL":"","itemQuantity":"1","itemCourse":"0","itemDiner":"0","itemStatus":"0","itemPrice":"63","itemParentID":"","itemOrderID":"264063","itemPriceID":"0","itemRemark":"","itempreps":[{"ipreparationID":"77","ipreparationPrefixID":"0"},{"ipreparationID":"10","ipreparationPrefixID":"0"}],"addons":[{"addonAutoID":"1596160","addonChoiceID":"1","addonID":"6","addonPrice":"0"},{"addonAutoID":"1596165","addonChoiceID":"2","addonID":"54","addonPrice":"2"},{"addonAutoID":"1596166","addonChoiceID":"2","addonID":"411","addonPrice":"0"},{"addonAutoID":"1596161","addonChoiceID":"35","addonID":"274","addonPrice":"4"},{"addonAutoID":"1596162","addonChoiceID":"37","addonID":"479","addonPrice":"10"},{"addonAutoID":"1596163","addonChoiceID":"37","addonID":"6","addonPrice":"0"},{"addonAutoID":"1596164","addonChoiceID":"37","addonID":"480","addonPrice":"10"},{"addonAutoID":"1596169","addonChoiceID":"39","addonID":"492","addonPrice":"0"},{"addonAutoID":"1596170","addonChoiceID":"39","addonID":"679","addonPrice":"5"},{"addonAutoID":"1596167","addonChoiceID":"41","addonID":"36","addonPrice":"0","addonpreps":[{"apreparationID":"41","apreparationPrefixID":"0"},{"apreparationID":"35","apreparationPrefixID":"0"}]},{"addonAutoID":"1596168","addonChoiceID":"41","addonID":"419","addonPrice":"0"},{"addonAutoID":"1596158","addonChoiceID":"42","addonID":"726","addonPrice":"0"},{"addonAutoID":"1596159","addonChoiceID":"44","addonID":"23","addonPrice":"3"}]}]
             */

            private String orderID;
            private String orderTableID;
            private String orderGuest;
            private String orderDiners;
            private String orderEmployeeID;
            private String orderEmployeeName;
            private String orderStart;
            private String orderDiscountPCT;
            private String orderBill;
            private String orderTerminalID;
            private String orderRemark;
            private String orderStatus;
            private List<ItemsBean> items;

            public String getOrderID() {
                return orderID;
            }

            public void setOrderID(String orderID) {
                this.orderID = orderID;
            }

            public String getOrderTableID() {
                return orderTableID;
            }

            public void setOrderTableID(String orderTableID) {
                this.orderTableID = orderTableID;
            }

            public String getOrderGuest() {
                return orderGuest;
            }

            public void setOrderGuest(String orderGuest) {
                this.orderGuest = orderGuest;
            }

            public String getOrderDiners() {
                return orderDiners;
            }

            public void setOrderDiners(String orderDiners) {
                this.orderDiners = orderDiners;
            }

            public String getOrderEmployeeID() {
                return orderEmployeeID;
            }

            public void setOrderEmployeeID(String orderEmployeeID) {
                this.orderEmployeeID = orderEmployeeID;
            }

            public String getOrderEmployeeName() {
                return orderEmployeeName;
            }

            public void setOrderEmployeeName(String orderEmployeeName) {
                this.orderEmployeeName = orderEmployeeName;
            }

            public String getOrderStart() {
                return orderStart;
            }

            public void setOrderStart(String orderStart) {
                this.orderStart = orderStart;
            }

            public String getOrderDiscountPCT() {
                return orderDiscountPCT;
            }

            public void setOrderDiscountPCT(String orderDiscountPCT) {
                this.orderDiscountPCT = orderDiscountPCT;
            }

            public String getOrderBill() {
                return orderBill;
            }

            public void setOrderBill(String orderBill) {
                this.orderBill = orderBill;
            }

            public String getOrderTerminalID() {
                return orderTerminalID;
            }

            public void setOrderTerminalID(String orderTerminalID) {
                this.orderTerminalID = orderTerminalID;
            }

            public String getOrderRemark() {
                return orderRemark;
            }

            public void setOrderRemark(String orderRemark) {
                this.orderRemark = orderRemark;
            }

            public String getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(String orderStatus) {
                this.orderStatus = orderStatus;
            }

            public List<ItemsBean> getItems() {
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            public static class ItemsBean {
                /**
                 * itemAutoID : 1596173
                 * itemName : Big Latte Soy
                 * NULL :
                 * itemQuantity : 1
                 * itemCourse : 0
                 * itemDiner : 0
                 * itemStatus : 0
                 * itemPrice : 16
                 * itemParentID :
                 * itemOrderID : 264063
                 * itemPriceID : 0
                 * itemRemark :
                 * itempreps : [{"ipreparationID":"15","ipreparationPrefixID":"0"},{"ipreparationID":"22","ipreparationPrefixID":"0"}]
                 * addons : [{"addonAutoID":"1596160","addonChoiceID":"1","addonID":"6","addonPrice":"0"},{"addonAutoID":"1596165","addonChoiceID":"2","addonID":"54","addonPrice":"2"},{"addonAutoID":"1596166","addonChoiceID":"2","addonID":"411","addonPrice":"0"},{"addonAutoID":"1596161","addonChoiceID":"35","addonID":"274","addonPrice":"4"},{"addonAutoID":"1596162","addonChoiceID":"37","addonID":"479","addonPrice":"10"},{"addonAutoID":"1596163","addonChoiceID":"37","addonID":"6","addonPrice":"0"},{"addonAutoID":"1596164","addonChoiceID":"37","addonID":"480","addonPrice":"10"},{"addonAutoID":"1596169","addonChoiceID":"39","addonID":"492","addonPrice":"0"},{"addonAutoID":"1596170","addonChoiceID":"39","addonID":"679","addonPrice":"5"},{"addonAutoID":"1596167","addonChoiceID":"41","addonID":"36","addonPrice":"0","addonpreps":[{"apreparationID":"41","apreparationPrefixID":"0"},{"apreparationID":"35","apreparationPrefixID":"0"}]},{"addonAutoID":"1596168","addonChoiceID":"41","addonID":"419","addonPrice":"0"},{"addonAutoID":"1596158","addonChoiceID":"42","addonID":"726","addonPrice":"0"},{"addonAutoID":"1596159","addonChoiceID":"44","addonID":"23","addonPrice":"3"}]
                 */

                private String itemAutoID;
                private String itemName;
                private String NULL;
                private String itemQuantity;

                public String getItemID() {
                    return itemID;
                }

                public void setItemID(String itemID) {
                    this.itemID = itemID;
                }

                private String itemID;
                private String itemCourse;
                private String itemDiner;
                private String itemStatus;
                private String itemPrice;
                private String itemParentID;
                private String itemOrderID;
                private String itemPriceID;
                private String itemRemark;
                private List<ItemprepsBean> itempreps;
                private List<AddonsBean> addons = new ArrayList<>();

                public String getItemAutoID() {
                    return itemAutoID;
                }

                public void setItemAutoID(String itemAutoID) {
                    this.itemAutoID = itemAutoID;
                }

                public String getItemName() {
                    return itemName;
                }

                public void setItemName(String itemName) {
                    this.itemName = itemName;
                }

                public String getNULL() {
                    return NULL;
                }

                public void setNULL(String NULL) {
                    this.NULL = NULL;
                }

                public String getItemQuantity() {
                    return itemQuantity;
                }

                public void setItemQuantity(String itemQuantity) {
                    this.itemQuantity = itemQuantity;
                }

                public String getItemCourse() {
                    return itemCourse;
                }

                public void setItemCourse(String itemCourse) {
                    this.itemCourse = itemCourse;
                }

                public String getItemDiner() {
                    return itemDiner;
                }

                public void setItemDiner(String itemDiner) {
                    this.itemDiner = itemDiner;
                }

                public String getItemStatus() {
                    return itemStatus;
                }

                public void setItemStatus(String itemStatus) {
                    this.itemStatus = itemStatus;
                }

                public String getItemPrice() {
                    return itemPrice;
                }

                public void setItemPrice(String itemPrice) {
                    this.itemPrice = itemPrice;
                }

                public String getItemParentID() {
                    return itemParentID;
                }

                public void setItemParentID(String itemParentID) {
                    this.itemParentID = itemParentID;
                }

                public String getItemOrderID() {
                    return itemOrderID;
                }

                public void setItemOrderID(String itemOrderID) {
                    this.itemOrderID = itemOrderID;
                }

                public String getItemPriceID() {
                    return itemPriceID;
                }

                public void setItemPriceID(String itemPriceID) {
                    this.itemPriceID = itemPriceID;
                }

                public String getItemRemark() {
                    return itemRemark;
                }

                public void setItemRemark(String itemRemark) {
                    this.itemRemark = itemRemark;
                }

                public List<ItemprepsBean> getItempreps() {
                    return itempreps;
                }

                public void setItempreps(List<ItemprepsBean> itempreps) {
                    this.itempreps = itempreps;
                }

                public List<AddonsBean> getAddons() {
                    return addons;
                }

                public void setAddons(List<AddonsBean> addons) {
                    this.addons = addons;
                }

                public static class ItemprepsBean {
                    /**
                     * ipreparationID : 15
                     * ipreparationPrefixID : 0
                     */

                    private String ipreparationID;
                    private String ipreparationPrefixID;

                    public String getIpreparationID() {
                        return ipreparationID;
                    }

                    public void setIpreparationID(String ipreparationID) {
                        this.ipreparationID = ipreparationID;
                    }

                    public String getIpreparationPrefixID() {
                        return ipreparationPrefixID;
                    }

                    public void setIpreparationPrefixID(String ipreparationPrefixID) {
                        this.ipreparationPrefixID = ipreparationPrefixID;
                    }
                }

                public static class AddonsBean {
                    /**
                     * addonAutoID : 1596160
                     * addonChoiceID : 1
                     * addonID : 6
                     * addonPrice : 0
                     * addonpreps : [{"apreparationID":"41","apreparationPrefixID":"0"},{"apreparationID":"35","apreparationPrefixID":"0"}]
                     */

                    private String addonAutoID;
                    private String addonChoiceID;
                    private String addonID;
                    private String addonPrice;
                    private List<AddonprepsBean> addonpreps;

                    public String getAddonAutoID() {
                        return addonAutoID;
                    }

                    public void setAddonAutoID(String addonAutoID) {
                        this.addonAutoID = addonAutoID;
                    }

                    public String getAddonChoiceID() {
                        return addonChoiceID;
                    }

                    public void setAddonChoiceID(String addonChoiceID) {
                        this.addonChoiceID = addonChoiceID;
                    }

                    public String getAddonID() {
                        return addonID;
                    }

                    public void setAddonID(String addonID) {
                        this.addonID = addonID;
                    }

                    public String getAddonPrice() {
                        return addonPrice;
                    }

                    public void setAddonPrice(String addonPrice) {
                        this.addonPrice = addonPrice;
                    }

                    public List<AddonprepsBean> getAddonpreps() {
                        return addonpreps;
                    }

                    public void setAddonpreps(List<AddonprepsBean> addonpreps) {
                        this.addonpreps = addonpreps;
                    }

                    public static class AddonprepsBean {
                        /**
                         * apreparationID : 41
                         * apreparationPrefixID : 0
                         */

                        private String apreparationID;
                        private String apreparationPrefixID;

                        public String getApreparationID() {
                            return apreparationID;
                        }

                        public void setApreparationID(String apreparationID) {
                            this.apreparationID = apreparationID;
                        }

                        public String getApreparationPrefixID() {
                            return apreparationPrefixID;
                        }

                        public void setApreparationPrefixID(String apreparationPrefixID) {
                            this.apreparationPrefixID = apreparationPrefixID;
                        }
                    }
                }
            }
        }
    }
}
