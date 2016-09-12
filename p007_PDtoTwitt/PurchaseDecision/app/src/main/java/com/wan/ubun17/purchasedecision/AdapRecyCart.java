package com.wan.ubun17.purchasedecision;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ubun17 on 9/11/16.
 */
public class AdapRecyCart extends RecyclerView.Adapter<ViewHolderCart> {
    ArrayList<AdapterFireBase> mFireBaseArray;

    public AdapRecyCart(ArrayList<AdapterFireBase> arg) {
        mFireBaseArray = arg;
    }

    @Override
    public ViewHolderCart onCreateViewHolder(ViewGroup parent, int viewType) {
        View parentView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_recycle, parent, false);

        ViewHolderCart viewHolderCart = new ViewHolderCart(parentView);

        return viewHolderCart;
    }

    @Override
    public void onBindViewHolder(ViewHolderCart holder,final int position) {

        String itemName, itemPrice, itemURL;
        itemName = mFireBaseArray.get(position).getItemName();
        itemPrice = mFireBaseArray.get(position).getmPrice();
        itemURL = mFireBaseArray.get(position).getmImageUrl();

        holder.tvName.setText(itemName);
        holder.tvPrice.setText(itemPrice);
        Picasso.with(holder.ivThum.getContext()).load(itemURL)
                .resize(100, 100).into(holder.ivThum);
    }

    @Override
    public int getItemCount() {
        return mFireBaseArray.size();
    }
}
