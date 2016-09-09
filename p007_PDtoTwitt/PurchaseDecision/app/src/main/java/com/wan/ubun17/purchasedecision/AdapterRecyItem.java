package com.wan.ubun17.purchasedecision;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.wan.ubun17.purchasedecision.APIcall.TwitterAPI;
import com.wan.ubun17.purchasedecision.ResponseObject.Ebay.Example;
import com.wan.ubun17.purchasedecision.ResponseObject.TwitterObject.Statuses;
import com.wan.ubun17.purchasedecision.ResponseObject.WalMartObject.Item;
import com.wan.ubun17.purchasedecision.ResponseObject.WalMartObject.SingleWarSearch;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ubun17 on 8/29/16.
 */
public class AdapterRecyItem extends RecyclerView.Adapter<ViewHolderItemList> {
    ArrayList<Item> mItems;
    ArrayList<Example> mEbayExample;
    String stWalPrice, stEbayMin, stEbayMax, stEbayAev, stItemName;
    ArrayList<Statuses> twittArr;

    public AdapterRecyItem(ArrayList<Item> args, ArrayList<Example> examArr) {
        mItems = args;
        mEbayExample = examArr;
    }

    @Override
    public ViewHolderItemList onCreateViewHolder(ViewGroup parent, int viewType) {

        View parentView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recy_item_list, parent, false);
        ViewHolderItemList viewHolderItemList = new ViewHolderItemList(parentView);

        return viewHolderItemList;
    }

    @Override
    public void onBindViewHolder(final ViewHolderItemList holder, int position) {

        if (mEbayExample.get(position) != null) {
            int numItem = mEbayExample.get(position)
                    .getFindItemsByKeywordsResponse().get(0).getSearchResult().get(0)
                    .getItem().size();

            ArrayList<Double> priceArr = new ArrayList<Double>();

            Double ebaySum = Double.valueOf(0);
            for (int i = 0; i < numItem; i ++) {
                double ebayPrice = Double.parseDouble(mEbayExample.get(position)
                        .getFindItemsByKeywordsResponse().get(0).getSearchResult().get(0)
                        .getItem().get(i).getSellingStatus().get(0).getCurrentPrice()
                        .get(0).getValue());

                priceArr.add(ebayPrice);
                ebaySum = ebaySum + ebayPrice;
            }
            String pattern = "###,###.##";
            DecimalFormat decimalFormat = new DecimalFormat(pattern);

            stEbayMin = String.valueOf(Collections.min(priceArr));
            stEbayMax = String.valueOf(Collections.max(priceArr));
            stEbayAev = decimalFormat.format(ebaySum/numItem);


        } else {
            stEbayMin = "NA";
            stEbayMax = "NA";
            stEbayAev = "NA";
        }

       Log.d("The price", "=============================    "+"AdapterRecyltem");


        String thumbURL, thumbURLtwo;

        final SingleWarSearch warSearch = SingleWarSearch.getInstance();
        thumbURL = warSearch.getItemList().get(position).getThumbnailImage();
        thumbURLtwo = "https://i5.walmartimages.com/asr/8e0c3fb1-673b-4b29-9b8a-46cae3e0d917_1.c5d745d0e28796c3f8b53893ea6e064c.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF";

        stItemName = mItems.get(position).getName();
        holder.tvItemName.setText(stItemName);
        holder.tvWalPrice.setText((CharSequence) mItems.get(position).getSalePrice().toString());

        holder.ebayAverPrice.setText(stEbayAev);
        holder.ebayMinPrice.setText(stEbayMin);
        holder.ebayMaxPrice.setText(stEbayMax);
/////////////////////////////
        //holder.buTwitter.setText("asdfasdfasdfasdf");

        Picasso.with(holder.imageThumb.getContext()).load(thumbURL).resize(100, 100)
                .into(holder.imageThumb);

        View.OnClickListener buTwitter = new View.OnClickListener(){
            @Override
            public  void onClick(View view) {
//                SingleWarSearch warSearch1 = SingleWarSearch.getInstance();
//                String searchTerm = warSearch.getQuery();

//                TwitterAPI testTwett = new TwitterAPI(stItemName);
//                testTwett.TwitterCalling();
                TwitterAsyncCalling twittCalling = new TwitterAsyncCalling(holder);
                twittCalling.execute(stItemName);

                Log.d("twitter bu", "i am clikced");
            }
        };

        holder.buTwitter.setOnClickListener(buTwitter);
    }//End of onBindViewHolder

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class TwitterAsyncCalling extends AsyncTask<String, Void, ArrayList<Statuses>> {

        ViewHolderItemList holder;

        public TwitterAsyncCalling(ViewHolderItemList holder) {
            this.holder = holder;
        }

        @Override
        protected ArrayList<Statuses> doInBackground(String... strings) {
            TwitterAPI testTwett = new TwitterAPI(strings[0]);
            testTwett.TwitterCalling();
            ArrayList<Statuses> returnedStat = testTwett.getStatueses();
            //twittArr = returnedStat;
            if (returnedStat != null) {
                int num = returnedStat.size();
                for (int i = 0; i < num; i++) {
                    String testST = returnedStat.get(i).getText();
                    Log.d("twitt tt ", "tttttttttttttttttttttttttttttt   " + testST);
                }
            }
            return returnedStat;
        }//End of doInBackGround

        @Override
        protected void onPostExecute(ArrayList<Statuses> statuses) {
            String stTwitNum = String.valueOf(statuses.size());
            holder.buTwitter.setText("Twitt # :" + stTwitNum);
        }
    }

}
