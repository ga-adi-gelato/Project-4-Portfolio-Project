package com.wan.ubun17.purchasedecision;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MmShoppingCart extends AppCompatActivity {
    DatabaseReference mFirebaseRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mm_shopping_cart);

        mFirebaseRootRef = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference firebaseMessageRef = mFirebaseRootRef.child("WalMartSCart");
        AdapterFireBase fireBase = new AdapterFireBase();

        firebaseMessageRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                AdapterFireBase data = dataSnapshot.getValue(AdapterFireBase.class);
                Log.d("from Fire", data.getItemName()+">>>>>>>>>>>>>>>>>>>>>>>>>>>");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
