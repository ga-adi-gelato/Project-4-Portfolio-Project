package sunil.worldpeace;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.nio.charset.UnmappableCharacterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sunil.worldpeace.ApiServices.PopulationAPIService;
import sunil.worldpeace.ApiServices.ReliefWebAPIService;
import sunil.worldpeace.ApiServices.UNHCRService;
import sunil.worldpeace.Model_ReliefWeb.Datum;
import sunil.worldpeace.Model_ReliefWeb.Example;
import sunil.worldpeace.Model_UNHCR_OriginMap.OriginKeyValPair;
import sunil.worldpeace.Model_UNHCR_Refugees.RefugeeData;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final String reliefWebBase = "http://api.reliefweb.int/v1/reports/";
    private static final String unhcrBase = "http://data.unhcr.org/api/";
    private static final String populationBase = "http://api.population.io:80/1.0/";

    Realm realm;

    //TODO: MEDIAWIKI QUERY FOR COUNTRY PAGE

    //TODO: FIGURE OUT HOW YOU'RE GOING TO GET NGOs (besides UNHCR instances...)



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        // Realm Configuration...
         RealmConfiguration realmConfig = new RealmConfiguration.Builder(MapsActivity.this).name("Persisted Data").build();
         Realm.setDefaultConfiguration(realmConfig);

        // Make a HashMap from key-value pairs required for API queries (this is a GET request)
        getUNHCRMap();
        HashMap<String,String>UNMap = new HashMap<>(generateKVPair());
        // store hashmap in singleton for convenience
        BulletinSingleton.getInstance().getHashMap(UNMap);

        // phew... works...
//        Log.i(">>>>>TEST<<<<<",BulletinSingleton.getInstance().getValfromKey("Afghanistan"));

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(">>>>>>>>>ONSTART","STARTED");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(">>>>>>>>ONRESUME","RESUMED");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(">>>>>>>>ONPAUSE","PAUSED");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }



    // generate & return HashMap from RealmObjects stored by getUNHCRMap()
    private HashMap<String,String> generateKVPair() {

        RealmResults<OriginKeyValPair> results = realm.where(OriginKeyValPair.class).findAll();
        HashMap<String,String> map = new HashMap<>();
        for(OriginKeyValPair key : results) {
            //Log.i("***RESULTS***", key.toString());
            map.put(key.getOriginEn(),key.getOrigin());
        }
        return map;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        // TODO: SET ZOOMLEVEL BACK TO ZERO WHEN YOU BACK OUT OF THE RECYCLERVIEW (LOWER PRIORITY)

        // TODO: USE SHARED PREFERENCES TO STORE MAP LOCATIONDATA

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                try {

                    Geocoder coder = new Geocoder(MapsActivity.this, Locale.getDefault());
                            List<Address> geoInfo = coder.getFromLocation(latLng.latitude, latLng.longitude,1);

                    // use GoogleMap.animateCamera() with CameraUpdateFactory and Geocoder data for an animation

                    // prevent crashes from clicking on bodies of water (null references for geocoder)
                    if(!geoInfo.isEmpty()) {
                        final LatLng clickPosition = new LatLng(geoInfo.get(0).getLatitude(), geoInfo.get(0).getLongitude());


                        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(clickPosition,5));

                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(clickPosition, 5), new GoogleMap.CancelableCallback() {
                            @Override
                            public void onFinish() {
                                Intent intent = new Intent(MapsActivity.this, BulletinActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onCancel() {
                                //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(clickPosition,1));
                            }
                        });

                    }
                    // Show Geocoder Output with a Toast...
                    if(geoInfo.size() > 0){
                        // for debugging... find incongruencies btwn geocoder & APIs...
                        Toast.makeText(MapsActivity.this, "COUNTRY: "+geoInfo.get(0).getCountryName()+"\n"+ "CODE: "+geoInfo.get(0).getCountryCode()+"\n"+ "ADMINAREA: "+geoInfo.get(0).getAdminArea()+"\n"+ "LOCALITY: "+geoInfo.get(0).getLocality()+"\n"+ "LONGITUDE: "+geoInfo.get(0).getLongitude()+"\n"+ "LATITUDE: "+geoInfo.get(0).getLatitude(), Toast.LENGTH_LONG).show();

                        // Pass Geocoder.get().getCountryName as parameter to my getRWCountryData() method
                        // TODO: HANDLE WATER IN SOME WAY
                        if(!geoInfo.get(0).getCountryName().isEmpty()) {
                            getRWCountryData(geoInfo.get(0).getCountryName());
                            // test this out...
                            getUNRefugeeData(geoInfo.get(0).getCountryName());
                        }
                        // TODO: KEEP THIS HANDY JUST IN CASE...
                        /*try {
                            Thread.sleep(100);// was 150
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/

                    }
                } catch (IOException e) {
                     e.printStackTrace();
                }
            }
        });


        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }


    /********************************** [API CALLS BELOW]  *****************************************/

    /** ReliefWeb API call for filtered results **/
    public ArrayList<Datum> getRWCountryData(String countryName){

        // Handle name differences between Geocoder and ReliefWeb's API
        switch (countryName) {
            case "United States":
                countryName = "USA";
                break;
            case "Vietnam":
                countryName = "Viet Nam";
                break;
            case "Laos":
                countryName = "Lao People's Democratic Republic (the)";
                break;
            case"Republic of the Congo":
                countryName = "Congo";
                break;
            case"Macedonia (FYROM)":
                countryName = "the former Yugoslav Republic of Macedonia";
                break;
            case"Myanmar (Burma)":
                countryName = "Myanmar";
                break;
            case"Brunei":
                countryName = "Brunei Darussalam";
                break;
            case"Taiwan":
                countryName = "China - Taiwan Province";
                break;
            case"South Korea":
                countryName = "Republic of Korea";
                break;
            case"North Korea":
                countryName = "DPRK";
                break;
            case"United Kingdom":
                countryName = "United Kingdom of Great Britain and Northern Ireland";
                break;
            case"Cape Verde":
                countryName = "Cabo Verde";
                break;
            case"Guadeloupe":
                countryName = "Guadeloupe (France)";
                break;
            case"The Bahamas":
                countryName = "Bahamas";
                break;
            case"Puerto Rico":
                countryName = "Puerto Rico (The United States of America)";
                break;
            case"Aruba":
                countryName = "Aruba (The Netherlands)";
                break;
            case"U.S. Virgin Islands":
                countryName = "United States Virgin Islands";
                break;

        }

        // Create logging interceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.HEADERS);

        // Create client, add logging interceptor to client
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        // create retrofit instance, add baseURL, client, and a GSON converter factory,
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(reliefWebBase)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // list of filtered queries to pass to Retrofit API Service
        ArrayList<String>filterFields = new ArrayList<>();
        filterFields.add("disaster_type.code");
        filterFields.add("disaster_type");
        filterFields.add("date.created");
        filterFields.add("vulnerable_groups");

        // create the retrofit API service
        ReliefWebAPIService reliefWebService = retrofit.create(ReliefWebAPIService.class);
        Call<Example> reliefCall = reliefWebService.getCountryData(
                "AND",
                "primary_country.exact",
                countryName,
                "disaster",
                "language.name",
                "English",
                filterFields,
                "asc",
                "15");

        final ArrayList<Datum>bulletinList = new ArrayList<>();

        reliefCall.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.isSuccessful()) {
                    Log.i("RELIEFWEB", "CONNECTION SUCCESSFUL");

                    for(int i = 0; i < response.body().getData().size();i++){
                        bulletinList.add(response.body().getData().get(i));
                        Log.i("BULLETIN"+i,bulletinList.get(i).getFields().getTitle());
                        if(!bulletinList.get(i).getFields().getDisasterType().isEmpty()){
                            Log.i("CODE",bulletinList.get(i).getFields().getDisasterType().get(0).getCode());
                        }
                        if(!bulletinList.get(i).getFields().getVulnerableGroups().isEmpty()) {
                            Log.i("PEOPLE", bulletinList.get(i).getFields().getVulnerableGroups().get(0).getName());
                        }

                        BulletinSingleton.getInstance().addToPOJOList(response.body().getData().get(i));

                        // convert POJO list to CardObjectList
                        // ArrayList<Bulletin>test = new ArrayList<Bulletin>(BulletinSingleton.getInstance().convertPOJOtoBulletin());
                        // Log.i("CARDLIST",test.get(0).getmTitle()); // fuck yes it works
                    }
                    // Log.i("MAIN THREAD?",String.valueOf(Looper.getMainLooper()));
                    // Log.i("BULLETINLIST SIZE", String.valueOf(BulletinSingleton.getInstance().getBulletinList().size()));
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.i("RELIEFWEB","CONNECTION FAILED");
            }
        });
        return bulletinList;
    }

    // Get refugee data with API's country codes
    public String getUNRefugeeData(String country){

        // Switch Statement to handle conflicts between UNHCR's API & MAPS API's Geocoder
        switch(country){
            case"Republic of the Congo":
                country = "Congo";
                break;
            case"Tanzania":
                country = "United Republic of Tanzania";
                break;
            case"Macedonia (FYROM)":
                country = "The former Yugoslav Republic of Macedonia";
                break;
            case"Serbia":
                country = "Serbia (and Kosovo: S/RES/1244 (1999))";
                break;
            case"Moldova":
                country = "Republic of Moldova";
                break;
            case"Iran":
                country = "Islamic Republic of Iran";
                break;
            case"Myanmar (Burma)":
                country = "Myanmar";
                break;
            case"Vietnam":
                country = "Viet Nam";
                break;
            case"Brunei":
                country = "Brunei Darussalam";
                break;
            case"North Korea":
                country = "Democratic People's Republic of Korea";
                break;
            case"South Korea":
                country = "Republic of Korea";
                break;
            case"Venezuela":
                country = "Bolivarian Republic of Venezuela";
                break;
            case"Bolivia":
                country = "Plurinational State of Bolivia";
                break;
            case"Russia":
                country = "Russian Federation";
                break;
            case"The Bahamas":
                country = "Bahamas";
                break;
            case"Puerto Rico":
                country = "United States";
                break;
            case"U.S. Virgin Islands":
                country = "United States";
                break;
            case"British Virgin Islands":
                country = "United Kingdom";
                break;
            case"Syria":
                country = "Syrian Arab Republic";
                break;
        }

        // convert country to code for API call, i.e.: getRWCountryData(Afghanistan) --> "AFG"
        country = BulletinSingleton.getInstance().getValfromKey(country);

        // Create logging interceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.HEADERS);

        // Create client, add logging interceptor to client
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        // create retrofit instance, add baseURL, client, and a GSON converter factory,
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(unhcrBase)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UNHCRService UNService = retrofit.create(UNHCRService.class);
        //  List<POJO> to grab our data
        final ArrayList<RefugeeData>refugeeData = new ArrayList<>();

        // get a realm instance
        realm = Realm.getDefaultInstance();

        // UNHCR's most recent "refugee census" is from 2013, hence the "2013" param...
        Call<List<RefugeeData>>refugeeDataCall = UNService.getRefugeeInfo("2013",country);
        refugeeDataCall.enqueue(new Callback<List<RefugeeData>>() {
            @Override
            public void onResponse(Call<List<RefugeeData>> call, Response<List<RefugeeData>> response) {
                if(response.isSuccessful()){

                    Log.i("UNHCR","CONNECTION SUCCESSFUL");

                    // add our JSON data to our List<POJO>
                    for(int i = 0; i < response.body().size(); i++){
                            refugeeData.add(response.body().get(i));
                            // Log.i("ITEM#" + i,refugeeData.get(i).getCountryOfResidenceEn() +" "+ refugeeData.get(i).getRefugees());
                    }
                    Log.i("SIZE BEFORE REMOVALS",String.valueOf(refugeeData.size()));

                    // there are many <RefugeeData> elements that do not include useful data,
                    // so I identify and remove them before storing them in Realm
                    for(int i = 0; i < refugeeData.size();i++){
                        if(refugeeData.get(i).getRefugees()==null) {
                            // Log.i("NULL VALUE",refugeeData.get(i).getCountryOfResidenceEn());
                            refugeeData.remove(refugeeData.get(i));
                        }
                    }
                    Log.i("SIZE AFTER REMOVALS",String.valueOf(refugeeData.size()));

                    realm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            // add refugee data without null instances to Realm
                            for(int i = 0; i < refugeeData.size();i++){
                                RefugeeData data = realm.createObject(RefugeeData.class);
                                // Total Population is the primary key
                                data.setCountryOfOriginEn(refugeeData.get(i).getCountryOfOriginEn());
                                data.setCountryOfResidenceEn(refugeeData.get(i).getCountryOfResidenceEn());
                                data.setAsylumSeekers(refugeeData.get(i).getAsylumSeekers());
                                data.setRefugees(refugeeData.get(i).getRefugees());
                                data.setTotalPopulation(refugeeData.get(i).getTotalPopulation());
                            }
                        }
                    }, new Realm.Transaction.OnSuccess() {
                        @Override
                        public void onSuccess() {
                            Log.i("REALM WRITE >>>>>","SUCCESS");
                        }
                    }, new Realm.Transaction.OnError() {
                        @Override
                        public void onError(Throwable error) {
                            Log.i("REALM WRITE >>>>>","ERROR: "+error.getMessage());
                        }
                    });

                }
            }
            @Override
            public void onFailure(Call<List<RefugeeData>> call, Throwable t) {
                Log.i("UNHCR","CONNECTION FAILED");
            }
        });
        return country; // return the converted country, to be used to query realm later
    }

    public void getUNHCRMap(){
        final HashMap<String,String>methodHash = new HashMap<>();

        realm = Realm.getDefaultInstance();

        // Create logging interceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.HEADERS);

        // Create client, add logging interceptor to client
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        // create retrofit instance, add baseURL, client, and a GSON converter factory,
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(unhcrBase)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UNHCRService UNService = retrofit.create(UNHCRService.class);


        // map is created from a GET request, is used for getUNRefugeeData
        Call<List<OriginKeyValPair>> mapRequest = UNService.getKeyValuePair();
        mapRequest.enqueue(new Callback<List<OriginKeyValPair>>() {
            @Override
            public void onResponse(Call<List<OriginKeyValPair>> call, final Response<List<OriginKeyValPair>> response) {
                if(response.isSuccessful()){
                    Log.i("UN ORIGIN","CONNECTION SUCCESSFUL");

                    // Store values in our hashmap
                    for(int i = 0; i < response.body().size();i++){
                        // Log.i("KEY",response.body().get(i).getOriginEn());
                        // Log.i("VALUE",response.body().get(i).getOrigin());

                        // this is how you would create a HashMap in onResponse(), but we want to store the list of of OriginKeyValPairs
                        // and create the Hashmap from our Realm database
                        methodHash.put(response.body().get(i).getOriginEn(),response.body().get(i).getOrigin());
                    }
                    Log.i("SIZE",String.valueOf(methodHash.size())); // at this point, everything's there

                    // write data to realm, create hashmap out of it...
                    // RealmList<OriginKeyValPair> realmList = new RealmList<OriginKeyValPair>();

                    realm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            for(int i = 0; i < response.body().size();i++){
                                OriginKeyValPair origin = realm.createObject(OriginKeyValPair.class);
                                origin.setOriginEn(response.body().get(i).getOriginEn());
                                origin.setOrigin(response.body().get(i).getOrigin());
                            }

                        }
                    }, new Realm.Transaction.OnSuccess() {
                        @Override
                        public void onSuccess() {
                            Log.i(">>>>>>REALM WRITE","SUCCESS");
                        }
                    }, new Realm.Transaction.OnError() {
                        @Override
                        public void onError(Throwable error) {
                            Log.i(">>>>>>REALM WRITE","FAILURE "+error.getMessage());
                        }
                    });

                }

            }

            @Override
            public void onFailure(Call<List<OriginKeyValPair>> call, Throwable t) {
                Log.i("UN ORIGIN CALL","CONNECTION FAILED");
            }
        });
    }

    public void getCountryPop(){

        // Create logging interceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.HEADERS);

        // Create client, add logging interceptor to client
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        // create retrofit instance, add baseURL, client, and a GSON converter factory,
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(populationBase)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PopulationAPIService populationService = retrofit.create(PopulationAPIService.class);

    }

}
