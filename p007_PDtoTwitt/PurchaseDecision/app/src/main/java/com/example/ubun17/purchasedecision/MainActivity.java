package com.example.ubun17.purchasedecision;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ubun17.purchasedecision.APIcall.EbayAPI;
import com.example.ubun17.purchasedecision.APIcall.TwitterAPI;
import com.example.ubun17.purchasedecision.APIcall.WalMartAPI;
import com.example.ubun17.purchasedecision.ResponseObject.Ebay.Example;
import com.example.ubun17.purchasedecision.ResponseObject.WalMartObject.Item;
import com.example.ubun17.purchasedecision.ResponseObject.WalMartObject.SingleWarSearch;

import java.util.ArrayList;
import java.util.List;

import static com.example.ubun17.purchasedecision.ResponseObject.WalMartObject.SingleWarSearch.getInstance;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    AdapterRecyItem adapter;
    ArrayList<Item> mItems;
    ArrayList<Example> mEbayExamples;

    EditText inputItem;
    Button buItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleViewItemList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mItems = new ArrayList<>();
        mEbayExamples = new ArrayList<>();
        adapter = new AdapterRecyItem(mItems, mEbayExamples);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        buItem = (Button) findViewById(R.id.buSearch);

        buItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputItem = (EditText) findViewById(R.id.inputSearch);

                String stSearchItem = inputItem.getText().toString();
                AsycAPIcalling asycAPIcalling = new AsycAPIcalling();
                asycAPIcalling.execute(stSearchItem);

                EbayAsyncCalling ebayCalling = new EbayAsyncCalling();
                ebayCalling.execute("calling");
            }
        });//End of button

    }//End of onStart


    class AsycAPIcalling extends AsyncTask<String, Double, List<Item>> {

        @Override
        protected List<Item> doInBackground(String... strings) {
            WalMartAPI walCalling = new WalMartAPI(strings[0]);
            walCalling.WalMartCall();

            SingleWarSearch singleWarSearch = getInstance();
            ArrayList<Item> walItems = singleWarSearch.getItemList();

            for (int i = 0; i < walItems.size(); i ++) {
                String searTerm = walItems.get(i).getName();

                singleWarSearch.getEbayExampleList().add(null);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Double... values) {
            super.onProgressUpdate(values);

            Toast.makeText(MainActivity.this, "asdf", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            SingleWarSearch singleWarSearch = getInstance();
            String strWal = singleWarSearch.getQuery();

            final ArrayList<Item> dataItem = singleWarSearch.getItemList();
            final ArrayList<Example> dataEbay = singleWarSearch.getEbayExampleList();

            mItems.clear();
            mItems.addAll(dataItem);
            mEbayExamples.clear();
            mEbayExamples.addAll(dataEbay);
            adapter.notifyDataSetChanged();
        }
    }//End of AsycAPIcalling

    class EbayAsyncCalling extends  AsyncTask<String, Double, List<Item>> {

        @Override
        protected List<Item> doInBackground(String... strings) {
            SingleWarSearch singleWarSearch = getInstance();
            ArrayList<Item> walItems = singleWarSearch.getItemList();

            for (int i = 0; i < walItems.size(); i ++) {
                String searTerm = walItems.get(i).getName();
                EbayAPI ebayCall = new EbayAPI(searTerm, i);
                ebayCall.EbayCall();

                try {
                    Thread.sleep(255);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               // singleWarSearch
            }
            return null;
        }

        @Override
        protected  void onProgressUpdate(Double... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(List<Item> items) {
            SingleWarSearch singleWarSearch = getInstance();
            String strWal = singleWarSearch.getQuery();

            final ArrayList<Item> dataItem = singleWarSearch.getItemList();
            final ArrayList<Example> dataEbay = singleWarSearch.getEbayExampleList();

//            mItems.clear();
//            mItems.addAll(dataItem);
            mEbayExamples.clear();
            mEbayExamples.addAll(dataEbay);
            adapter.notifyDataSetChanged();

        }
    }// End of EbayAsyncCalling

}
