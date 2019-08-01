package com.menuz.data.model.db;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by mindiii on 16/11/18.
 */

public class OrderJsonModel {


    private List<OrdersBean> orders;

    public List<OrdersBean> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersBean> orders) {
        this.orders = orders;
    }

    public static class OrdersBean {
        /**
         * orderID :
         * orderTableID : 14
         * orderDiners :
         * orderEmployeeID :
         * orderStart :
         * orderDiscountPCT :
         * orderTerminalID :
         * orderRemark :
         * items : [{"itemOrderID":"","itemID":"21","itemCourse":"","itemQuantity":"","itemDiner":"","itemPrice":"","itemRemark":"","itemChoiceID":"","itemPriceID":"","itemEmployeeID":"","itemTerminalID":"","itempreps":[{"ipreparationID":"","ipreparationPrefixID":""}],"addons":[{"addonOrderID":"","addonID":"","addonCourse":"","addonQuantity":"","addonDiner":"","addonPrice":"","addonRemark":"","addonChoiceID":"","addonPriceID":"","addonEmployeeID":"","addonTerminalID":"","addonpreps":[{"apreparationID":"","apreparationPrefixID":""}]}]}]
         * payments : [{"paymentID":"","paymentPrice":"","paymentIdentification":"","paymentTerminalID":"","paymentApproved":""}]
         */

        private String orderID;
        private String orderTableID;
        private String orderDiners;
        private String orderEmployeeID;
        private String orderStart;
        private String orderDiscountPCT;
        private String orderTerminalID;
        private String orderRemark;
        private List<ItemsBean> items;
        private List<PaymentsBean> payments;

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

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public List<PaymentsBean> getPayments() {
            return payments;
        }

        public void setPayments(List<PaymentsBean> payments) {
            this.payments = payments;
        }

        public static class ItemsBean {
            /**
             * itemOrderID :
             * itemID : 21
             * itemCourse :
             * itemQuantity :
             * itemDiner :
             * itemPrice :
             * itemRemark :
             * itemChoiceID :
             * itemPriceID :
             * itemEmployeeID :
             * itemTerminalID :
             * itempreps : [{"ipreparationID":"","ipreparationPrefixID":""}]
             * addons : [{"addonOrderID":"","addonID":"","addonCourse":"","addonQuantity":"","addonDiner":"","addonPrice":"","addonRemark":"","addonChoiceID":"","addonPriceID":"","addonEmployeeID":"","addonTerminalID":"","addonpreps":[{"apreparationID":"","apreparationPrefixID":""}]}]
             */

            private String itemOrderID;
            private String itemID;
            private String itemCourse;
            private String itemQuantity;
            private String itemDiner;
            private String itemPrice;
            private String itemRemark;
            private String itemChoiceID;
            private String itemPriceID;
            private String itemEmployeeID;
            private String itemTerminalID;
            private List<ItemprepsBean> itempreps;
            private List<AddonsBean> addons;

            public String getItemOrderID() {
                return itemOrderID;
            }

            public void setItemOrderID(String itemOrderID) {
                this.itemOrderID = itemOrderID;
            }

            public String getItemID() {
                return itemID;
            }

            public void setItemID(String itemID) {
                this.itemID = itemID;
            }

            public String getItemCourse() {
                return itemCourse;
            }

            public void setItemCourse(String itemCourse) {
                this.itemCourse = itemCourse;
            }

            public String getItemQuantity() {
                return itemQuantity;
            }

            public void setItemQuantity(String itemQuantity) {
                this.itemQuantity = itemQuantity;
            }

            public String getItemDiner() {
                return itemDiner;
            }

            public void setItemDiner(String itemDiner) {
                this.itemDiner = itemDiner;
            }

            public String getItemPrice() {
                return itemPrice;
            }

            public void setItemPrice(String itemPrice) {
                this.itemPrice = itemPrice;
            }

            public String getItemRemark() {
                return itemRemark;
            }

            public void setItemRemark(String itemRemark) {
                this.itemRemark = itemRemark;
            }

            public String getItemChoiceID() {
                return itemChoiceID;
            }

            public void setItemChoiceID(String itemChoiceID) {
                this.itemChoiceID = itemChoiceID;
            }

            public String getItemPriceID() {
                return itemPriceID;
            }

            public void setItemPriceID(String itemPriceID) {
                this.itemPriceID = itemPriceID;
            }

            public String getItemEmployeeID() {
                return itemEmployeeID;
            }

            public void setItemEmployeeID(String itemEmployeeID) {
                this.itemEmployeeID = itemEmployeeID;
            }

            public String getItemTerminalID() {
                return itemTerminalID;
            }

            public void setItemTerminalID(String itemTerminalID) {
                this.itemTerminalID = itemTerminalID;
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
                 * ipreparationID :
                 * ipreparationPrefixID :
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
                 * addonOrderID :
                 * addonID :
                 * addonCourse :
                 * addonQuantity :
                 * addonDiner :
                 * addonPrice :
                 * addonRemark :
                 * addonChoiceID :
                 * addonPriceID :
                 * addonEmployeeID :
                 * addonTerminalID :
                 * addonpreps : [{"apreparationID":"","apreparationPrefixID":""}]
                 */

                private String addonOrderID;
                private String addonID;
                private String addonCourse;
                private String addonQuantity;
                private String addonDiner;
                private String addonPrice;
                private String addonRemark;
                private String addonChoiceID;
                private String addonPriceID;
                private String addonEmployeeID;
                private String addonTerminalID;
                private List<AddonprepsBean> addonpreps;

                public String getAddonOrderID() {
                    return addonOrderID;
                }

                public void setAddonOrderID(String addonOrderID) {
                    this.addonOrderID = addonOrderID;
                }

                public String getAddonID() {
                    return addonID;
                }

                public void setAddonID(String addonID) {
                    this.addonID = addonID;
                }

                public String getAddonCourse() {
                    return addonCourse;
                }

                public void setAddonCourse(String addonCourse) {
                    this.addonCourse = addonCourse;
                }

                public String getAddonQuantity() {
                    return addonQuantity;
                }

                public void setAddonQuantity(String addonQuantity) {
                    this.addonQuantity = addonQuantity;
                }

                public String getAddonDiner() {
                    return addonDiner;
                }

                public void setAddonDiner(String addonDiner) {
                    this.addonDiner = addonDiner;
                }

                public String getAddonPrice() {
                    return addonPrice;
                }

                public void setAddonPrice(String addonPrice) {
                    this.addonPrice = addonPrice;
                }

                public String getAddonRemark() {
                    return addonRemark;
                }

                public void setAddonRemark(String addonRemark) {
                    this.addonRemark = addonRemark;
                }

                public String getAddonChoiceID() {
                    return addonChoiceID;
                }

                public void setAddonChoiceID(String addonChoiceID) {
                    this.addonChoiceID = addonChoiceID;
                }

                public String getAddonPriceID() {
                    return addonPriceID;
                }

                public void setAddonPriceID(String addonPriceID) {
                    this.addonPriceID = addonPriceID;
                }

                public String getAddonEmployeeID() {
                    return addonEmployeeID;
                }

                public void setAddonEmployeeID(String addonEmployeeID) {
                    this.addonEmployeeID = addonEmployeeID;
                }

                public String getAddonTerminalID() {
                    return addonTerminalID;
                }

                public void setAddonTerminalID(String addonTerminalID) {
                    this.addonTerminalID = addonTerminalID;
                }

                public List<AddonprepsBean> getAddonpreps() {
                    return addonpreps;
                }

                public void setAddonpreps(List<AddonprepsBean> addonpreps) {
                    this.addonpreps = addonpreps;
                }

                public static class AddonprepsBean {
                    /**
                     * apreparationID :
                     * apreparationPrefixID :
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

        public static class PaymentsBean {
            /**
             * paymentID :
             * paymentPrice :
             * paymentIdentification :
             * paymentTerminalID :
             * paymentApproved :
             */

            private String paymentID;
            private String paymentPrice;
            private String paymentIdentification;
            private String paymentTerminalID;
            private String paymentApproved;

            public String getPaymentID() {
                return paymentID;
            }

            public void setPaymentID(String paymentID) {
                this.paymentID = paymentID;
            }

            public String getPaymentPrice() {
                return paymentPrice;
            }

            public void setPaymentPrice(String paymentPrice) {
                this.paymentPrice = paymentPrice;
            }

            public String getPaymentIdentification() {
                return paymentIdentification;
            }

            public void setPaymentIdentification(String paymentIdentification) {
                this.paymentIdentification = paymentIdentification;
            }

            public String getPaymentTerminalID() {
                return paymentTerminalID;
            }

            public void setPaymentTerminalID(String paymentTerminalID) {
                this.paymentTerminalID = paymentTerminalID;
            }

            public String getPaymentApproved() {
                return paymentApproved;
            }

            public void setPaymentApproved(String paymentApproved) {
                this.paymentApproved = paymentApproved;
            }

            public void setPaymentIdentification(JSONObject jObjectType) {

            }
        }
    }
}
