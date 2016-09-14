package sunil.worldpeace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import sunil.worldpeace.CardObjects.Bulletin;
import sunil.worldpeace.CardObjects.CountryCard;

public class BulletinActivity extends AppCompatActivity {


    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin);


        BulletinSingleton singleton = BulletinSingleton.getInstance();

        Log.i("ACTIVITY2 | ONCREATE", String.valueOf(BulletinSingleton.getInstance().getBulletinList().size()));

        //use singleton to generate content for recyclerview
        ArrayList<CountryCard> bulletinList = new ArrayList<>(singleton.convertPOJOtoBulletin());
        if(!bulletinList.isEmpty()) {
            Log.i("ACTIVITY2 | CONTENTS", String.valueOf(bulletinList.size()));
        }

        // now we need to get our refugee data into the recyclerview
        // how do we know what to query?

        /***** RecylerView *****/
        recyclerView = (RecyclerView) findViewById(R.id.bulletin_activity_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        CardAdapter adapter = new CardAdapter(bulletinList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ACTIVITY2 | ONRESUME", String.valueOf(BulletinSingleton.getInstance().getBulletinList().size()));
    }
}
