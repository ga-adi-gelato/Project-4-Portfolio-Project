package com.wan.ubun17.purchasedecision;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ubun17 on 9/10/16.
 */
public class ViewHolderCart extends RecyclerView.ViewHolder {
    public TextView tvName, tvPrice;
    public ImageView ivThum;

    public ViewHolderCart(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.cartName);
        tvPrice = (TextView) itemView.findViewById(R.id.cartPrice);
    }
}
