package com.example.ubun17.purchasedecision.ResponseObject.WalMartObject;

import com.example.ubun17.purchasedecision.ResponseObject.Ebay.Example;

import java.util.ArrayList;

/**
 * Created by ubun17 on 8/26/16.
 */
public class SingleWarSearch {
    private static SingleWarSearch walSingleton = null;
    private static String query;
    private static ArrayList<Item> itemList;
    private static ArrayList<Example> ebayExampleList;

    public static SingleWarSearch getInstance() {
        if (walSingleton == null) {
            walSingleton = new SingleWarSearch();
        }
        return walSingleton;
    }

    public void setWalSingleton(String str, ArrayList<Item> itemArray) {
        query = str;
        itemList = itemArray;
        ebayExampleList = new ArrayList<Example>();
    }

    public void setQuery(String str) {
        query = str;
    }

    public String getQuery() {
        return query;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void addExample(Example example) {
        ebayExampleList.add(example);
    }

    public ArrayList<Example> getEbayExampleList() {
        return ebayExampleList;
    }


}
