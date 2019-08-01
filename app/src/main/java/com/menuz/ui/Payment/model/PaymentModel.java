package com.menuz.ui.Payment.model;

public class PaymentModel {

    /**
     * restId : 1433
     * posId : 1328
     * function : charge
     * cardId : 72380755
     * refId : 0
     * price : 1.00
     */

    private String restId;
    private String posId;
    private String function;
    private String cardId;
    private String refId;
    private String price;

    public String getRestId() {
        return restId;
    }

    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
