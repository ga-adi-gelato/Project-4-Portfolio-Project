package com.example.ubun17.purchasedecision;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ubun17 on 8/29/16.
 */
public class ViewHolderItemList extends RecyclerView.ViewHolder {
    public TextView tvItemName,tvWalPrice, ebayMinPrice, ebayMaxPrice, ebayAverPrice;
    public ImageView imageThumb;
    public Button buTwitter;

    public ViewHolderItemList(View itemView) {
        super(itemView);

        imageThumb = (ImageView) itemView.findViewById(R.id.imaThumb);

        tvItemName = (TextView) itemView.findViewById(R.id.tvItemName);
        tvWalPrice = (TextView) itemView.findViewById(R.id.WalPrice) ;
        ebayAverPrice = (TextView) itemView.findViewById(R.id.ebayAverPrice);
        ebayMinPrice = (TextView) itemView.findViewById(R.id.ebayMinPrice);
        ebayMaxPrice = (TextView) itemView.findViewById(R.id.ebayMaxPrice);
        buTwitter = (Button) itemView.findViewById(R.id.buTwitter);
    }
}
