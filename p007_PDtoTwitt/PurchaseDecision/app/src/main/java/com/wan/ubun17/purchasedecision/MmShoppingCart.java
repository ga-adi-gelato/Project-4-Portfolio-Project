package com.wan.ubun17.purchasedecision;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MmShoppingCart extends AppCompatActivity {
    DatabaseReference mFirebaseRootRef;
    ArrayList<AdapterFireBase> fireBaseArray = new ArrayList<>();

    RecyclerView mRecyclerView;
    AdapRecyCart adapRecyCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mm_shopping_cart);

        mFirebaseRootRef = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference firebaseMessageRef = mFirebaseRootRef.child("WalMartSCart");

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleCart);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapRecyCart = new AdapRecyCart(fireBaseArray);
        mRecyclerView.setAdapter(adapRecyCart);

        firebaseMessageRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                AdapterFireBase data = dataSnapshot.getValue(AdapterFireBase.class);

                fireBaseArray.add(data);
               // dataSnapshot.getRef();
                Log.d("from Fire", data.getItemName()+">>>>>>>>>>>>>>>>>>>>>>>>>>>");
                Log.d("from Fire", String.valueOf(dataSnapshot.getRef()));
                adapRecyCart.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                adapRecyCart.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adapRecyCart.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });//End of fireBase

    }
}
