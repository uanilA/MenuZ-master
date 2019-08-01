package com.menuz.data.model.db;

/**
 * Created by mindiii on 3/11/18.
 */

public class RefreshData {

    private boolean isrefresh = false;


    public RefreshData(boolean isRefresh){
        this.isrefresh = isRefresh;

    }

    public boolean isIsrefresh() {
        return isrefresh;
    }

    public void setIsrefresh(boolean isrefresh) {
        this.isrefresh = isrefresh;
    }
}
