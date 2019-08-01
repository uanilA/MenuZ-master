package com.menuz.ui.Payment.model;

import java.util.List;

public class PaymentResponseModel {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * success : true
         * termId : 12
         * termName : צד
         * Cibus_PosId : 6061
         * valueCardId :
         * valueCardPassword : 435917
         * emvData :
         * Cibus_RestaurantID : 6858
         * TenBis_Password : dqepfbj
         * TenBis_ResID : 1886
         * TenBis_User : pesfqgq
         * paymethods : [{"payMethodId":"1","payMethodName":"מזומן","payMethodFixValue":"0","payMethodType":"1"},{"payMethodId":"2","payMethodName":"אשראית","payMethodFixValue":"0","payMethodType":"2"},{"payMethodId":"3","payMethodName":"אשראי ידני","payMethodFixValue":"0","payMethodType":"7"},{"payMethodId":"4","payMethodName":"המחאה","payMethodFixValue":"0","payMethodType":"3"},{"payMethodId":"5","payMethodName":"הקפה","payMethodFixValue":"0","payMethodType":"6"},{"payMethodId":"6","payMethodName":"סיבוס","payMethodFixValue":"0","payMethodType":"9"},{"payMethodId":"7","payMethodName":"תן ביס","payMethodFixValue":"0","payMethodType":"5"},{"payMethodId":"8","payMethodName":"כרטיס נטען","payMethodFixValue":"0","payMethodType":"21"}]
         */

        private String success;
        private String termId;
        private String termName;
        private String Cibus_PosId;
        private String valueCardId;
        private String valueCardPassword;
        private String emvData;
        private String Cibus_RestaurantID;
        private String TenBis_Password;
        private String TenBis_ResID;
        private String TenBis_User;
        private List<PaymethodsBean> paymethods;

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public String getTermId() {
            return termId;
        }

        public void setTermId(String termId) {
            this.termId = termId;
        }

        public String getTermName() {
            return termName;
        }

        public void setTermName(String termName) {
            this.termName = termName;
        }

        public String getCibus_PosId() {
            return Cibus_PosId;
        }

        public void setCibus_PosId(String Cibus_PosId) {
            this.Cibus_PosId = Cibus_PosId;
        }

        public String getValueCardId() {
            return valueCardId;
        }

        public void setValueCardId(String valueCardId) {
            this.valueCardId = valueCardId;
        }

        public String getValueCardPassword() {
            return valueCardPassword;
        }

        public void setValueCardPassword(String valueCardPassword) {
            this.valueCardPassword = valueCardPassword;
        }

        public String getEmvData() {
            return emvData;
        }

        public void setEmvData(String emvData) {
            this.emvData = emvData;
        }

        public String getCibus_RestaurantID() {
            return Cibus_RestaurantID;
        }

        public void setCibus_RestaurantID(String Cibus_RestaurantID) {
            this.Cibus_RestaurantID = Cibus_RestaurantID;
        }

        public String getTenBis_Password() {
            return TenBis_Password;
        }

        public void setTenBis_Password(String TenBis_Password) {
            this.TenBis_Password = TenBis_Password;
        }

        public String getTenBis_ResID() {
            return TenBis_ResID;
        }

        public void setTenBis_ResID(String TenBis_ResID) {
            this.TenBis_ResID = TenBis_ResID;
        }

        public String getTenBis_User() {
            return TenBis_User;
        }

        public void setTenBis_User(String TenBis_User) {
            this.TenBis_User = TenBis_User;
        }

        public List<PaymethodsBean> getPaymethods() {
            return paymethods;
        }

        public void setPaymethods(List<PaymethodsBean> paymethods) {
            this.paymethods = paymethods;
        }

        public static class PaymethodsBean {
            /**
             * payMethodId : 1
             * payMethodName : מזומן
             * payMethodFixValue : 0
             * payMethodType : 1
             */

            private String payMethodId;
            private String payMethodName;
            private String payMethodFixValue;
            private String payMethodType;

            public String getPayMethodId() {
                return payMethodId;
            }

            public void setPayMethodId(String payMethodId) {
                this.payMethodId = payMethodId;
            }

            public String getPayMethodName() {
                return payMethodName;
            }

            public void setPayMethodName(String payMethodName) {
                this.payMethodName = payMethodName;
            }

            public String getPayMethodFixValue() {
                return payMethodFixValue;
            }

            public void setPayMethodFixValue(String payMethodFixValue) {
                this.payMethodFixValue = payMethodFixValue;
            }

            public String getPayMethodType() {
                return payMethodType;
            }

            public void setPayMethodType(String payMethodType) {
                this.payMethodType = payMethodType;
            }
        }
    }
}
