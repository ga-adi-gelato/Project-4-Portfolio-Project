package com.wan.ubun17.purchasedecision;

/**
 * Created by ubun17 on 9/10/16.
 */
public class AdapterFireBase {
    private String mName, mPrice, mImageUrl;

    AdapterFireBase() {}

    public AdapterFireBase(String str, String price, String url) {
        mName = str;
        mPrice = price;
        mImageUrl = url;
    }

    public String getItemName() {
        return mName;
    }

    public String  getmPrice(){
        return mPrice;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setItemName(String testOne) {
        this.mName = testOne;
    }

    public void setmPrice(String str) {
        this.mPrice = str;
    }

    public void setmImageUrl(String str) {
        this.mImageUrl = str;
    }
}
