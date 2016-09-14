package sunil.worldpeace;

import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;

import sunil.worldpeace.CardObjects.Bulletin;
import sunil.worldpeace.CardObjects.CountryCard;
import sunil.worldpeace.Model_ReliefWeb.Datum;


/**
 * Created by sunil on 9/7/16.
 */

public class BulletinSingleton {

    public static BulletinSingleton sInstance;
    public ArrayList<Datum> BulletinList;
    public HashMap<String,String> mHashMap;
    private BulletinSingleton(){
        if(BulletinList==null)
            BulletinList = new ArrayList<>();
    }

    public static BulletinSingleton getInstance(){
        if(sInstance==null)
            sInstance = new BulletinSingleton();
        return sInstance;
    }

    public void addToPOJOList(Datum bulletin){
        BulletinList.add(bulletin);
    }

    public ArrayList<Datum>getBulletinList(){
        if(BulletinList==null) {
            Log.i("BULLETINLIST", "NOT INITIALIZED");
        }
            return BulletinList;
    }
    // Brute Force: convert POJO to Card-Ready objects, check for all relevant boolean values
    public ArrayList<CountryCard> convertPOJOtoBulletin(){
        ArrayList<CountryCard>convertedList = new ArrayList<>();
        for(int i = 0; i < BulletinList.size();i++){
            // Assign Title, URL, Date
            Bulletin bulletin = new Bulletin();
            bulletin.setmTitle(BulletinList.get(i).getFields().getTitle());
            bulletin.setmBulletinURL(BulletinList.get(i).getHref());
            bulletin.setmDate(BulletinList.get(i).getFields().getDate().getCreated());

            // Check for affected peoples
            for(int j = 0; j<BulletinList.get(i).getFields().getVulnerableGroups().size(); j++){
                // make sure it isn't empty
                if(!BulletinList.get(i).getFields().getVulnerableGroups().isEmpty()) {
                    String groups = BulletinList.get(i).getFields().getVulnerableGroups().get(j).getName();
                    switch (groups) {
                        case "Aged Persons":
                            bulletin.setAged(true);
                            break;
                        case "Children":
                            bulletin.setChildren(true);
                            break;
                        case "IDPs":
                            bulletin.setIDP(true);
                            break;
                        case "Persons with Disabilities":
                            bulletin.setDisabled(true);
                            break;
                        case "Refugees":
                            bulletin.setRefugee(true);
                            break;
                        case "Women":
                            bulletin.setWomen(true);
                            break;
                    }
                }
            }
            for(int k = 0; k < BulletinList.get(i).getFields().getDisasterType().size();k++){
                // make sure it isn't empty
                if(!BulletinList.get(i).getFields().getDisasterType().isEmpty()) {
                    String code = BulletinList.get(i).getFields().getDisasterType().get(k).getCode();

                    switch (code) {
                        case "CW":
                            bulletin.setColdWave(true);
                            break;
                        case "DR":
                            bulletin.setDrought(true);
                            break;
                        case "EQ":
                            bulletin.setEarthquake(true);
                            break;
                        case "ET":
                            bulletin.setExtraTropicalCyclone(true);
                            break;
                        case "FR":
                            bulletin.setFire(true);
                            break;
                        case "FF":
                            bulletin.setFlashFlood(true);
                            break;
                        case "FL":
                            bulletin.setFlood(true);
                            break;
                        case "HT":
                            bulletin.setHeatWave(true);
                            break;
                        case "IN":
                            bulletin.setInsectInfestation(true);
                            break;
                        case "LS":
                            bulletin.setLandSlide(true);
                            break;
                        case "OT":
                            bulletin.setOther(true);
                            break;
                        case "ST":
                            bulletin.setSevereLocalStorm(true);
                            break;
                        case "AV":
                            bulletin.setSnowAvalanche(true);
                            break;
                        case "SS":
                            bulletin.setStormSurge(true);
                            break;
                        case "AC":
                            bulletin.setTechnologicalDisaster(true);
                            break;
                        case "TC":
                            bulletin.setTropicalCyclone(true);
                            break;
                        case "TS":
                            bulletin.setTsunami(true);
                            break;
                        case "VO":
                            bulletin.setVolcano(true);
                            break;
                        case "WF":
                            bulletin.setWildFire(true);
                            break;
                        case "AI":
                            bulletin.setAlienInvasion(true);
                            break;
                    }
                }
            }
            convertedList.add(bulletin);
        }

        // clear the POJOList so that it always has a single country's bulletins
        BulletinList.clear();

        return convertedList; // convert by itself? or add and convert?
    }

    public void getHashMap(HashMap<String, String> unMap) {
        mHashMap = new HashMap<String,String>(unMap);
    }

    public String getValfromKey(String s){
        return mHashMap.get(s);
    }

}
