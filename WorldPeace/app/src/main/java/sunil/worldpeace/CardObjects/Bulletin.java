package sunil.worldpeace.CardObjects;

/**
 * Created by sunil on 9/2/16.
 */
public class Bulletin extends CountryCard {
    // Bulletin fields
    private String mBulletinURL;
    private int mBulletinID;
    private String mTitle;
    private String mDisasterName;
    private String mDisasterCode;
    private String mDate;

    // Persons of Concern
    private boolean isAged;
    private boolean isChildren;
    private boolean isIDP;
    private boolean isDisabled;
    private boolean isRefugee;
    private boolean isWomen;

    // Disasters (pull by code)
    private boolean isColdWave;
    private boolean isDrought;
    private boolean isEarthquake;
    private boolean isExtraTropicalCyclone;
    private boolean isFire;
    private boolean isFlashFlood;
    private boolean isFlood;
    private boolean isHeatWave;
    private boolean isInsectInfestation;
    private boolean isLandSlide;
    private boolean isOther;
    private boolean isSevereLocalStorm;
    private boolean isSnowAvalanche;
    private boolean isStormSurge;
    private boolean isTechnologicalDisaster;
    private boolean isTropicalCyclone;
    private boolean isTsunami;
    private boolean isVolcano;
    private boolean isWildFire;
    private boolean isAlienInvasion; // :D

    public boolean isColdWave() {
        return isColdWave;
    }

    public void setColdWave(boolean coldWave) {
        isColdWave = coldWave;
    }

    public boolean isDrought() {
        return isDrought;
    }

    public void setDrought(boolean drought) {
        isDrought = drought;
    }

    public boolean isEarthquake() {
        return isEarthquake;
    }

    public void setEarthquake(boolean earthquake) {
        isEarthquake = earthquake;
    }

    public boolean isExtraTropicalCyclone() {
        return isExtraTropicalCyclone;
    }

    public void setExtraTropicalCyclone(boolean extraTropicalCyclone) {
        isExtraTropicalCyclone = extraTropicalCyclone;
    }

    public boolean isFire() {
        return isFire;
    }

    public void setFire(boolean fire) {
        isFire = fire;
    }

    public boolean isFlashFlood() {
        return isFlashFlood;
    }

    public void setFlashFlood(boolean flashFlood) {
        isFlashFlood = flashFlood;
    }

    public boolean isFlood() {
        return isFlood;
    }

    public void setFlood(boolean flood) {
        isFlood = flood;
    }

    public boolean isHeatWave() {
        return isHeatWave;
    }

    public void setHeatWave(boolean heatWave) {
        isHeatWave = heatWave;
    }

    public boolean isInsectInfestation() {
        return isInsectInfestation;
    }

    public void setInsectInfestation(boolean insectInfestation) {
        isInsectInfestation = insectInfestation;
    }

    public boolean isLandSlide() {
        return isLandSlide;
    }

    public void setLandSlide(boolean landSlide) {
        isLandSlide = landSlide;
    }

    public boolean isOther() {
        return isOther;
    }

    public void setOther(boolean other) {
        isOther = other;
    }

    public boolean isSevereLocalStorm() {
        return isSevereLocalStorm;
    }

    public void setSevereLocalStorm(boolean severeLocalStorm) {
        isSevereLocalStorm = severeLocalStorm;
    }

    public boolean isSnowAvalanche() {
        return isSnowAvalanche;
    }

    public void setSnowAvalanche(boolean snowAvalanche) {
        isSnowAvalanche = snowAvalanche;
    }

    public boolean isStormSurge() {
        return isStormSurge;
    }

    public void setStormSurge(boolean stormSurge) {
        isStormSurge = stormSurge;
    }

    public boolean isTechnologicalDisaster() {
        return isTechnologicalDisaster;
    }

    public void setTechnologicalDisaster(boolean technologicalDisaster) {
        isTechnologicalDisaster = technologicalDisaster;
    }

    public boolean isTropicalCyclone() {
        return isTropicalCyclone;
    }

    public void setTropicalCyclone(boolean tropicalCyclone) {
        isTropicalCyclone = tropicalCyclone;
    }

    public boolean isTsunami() {
        return isTsunami;
    }

    public void setTsunami(boolean tsunami) {
        isTsunami = tsunami;
    }

    public boolean isVolcano() {
        return isVolcano;
    }

    public void setVolcano(boolean volcano) {
        isVolcano = volcano;
    }

    public boolean isWildFire() {
        return isWildFire;
    }

    public void setWildFire(boolean wildFire) {
        isWildFire = wildFire;
    }

    public boolean isAlienInvasion() {
        return isAlienInvasion;
    }

    public void setAlienInvasion(boolean alienInvasion) {
        isAlienInvasion = alienInvasion;
    }


    public boolean isAged() {
        return isAged;
    }

    public void setAged(boolean aged) {
        isAged = aged;
    }

    public boolean isChildren() {
        return isChildren;
    }

    public void setChildren(boolean children) {
        isChildren = children;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public boolean isIDP() {
        return isIDP;
    }

    public void setIDP(boolean IDP) {
        isIDP = IDP;
    }

    public boolean isWomen() {
        return isWomen;
    }

    public void setWomen(boolean women) {
        isWomen = women;
    }

    public int getmBulletinID() {
        return mBulletinID;
    }

    public void setmBulletinID(int mBulletinID) {
        this.mBulletinID = mBulletinID;
    }

    public String getmBulletinURL() {
        return mBulletinURL;
    }

    public void setmBulletinURL(String mBulletinURL) {
        this.mBulletinURL = mBulletinURL;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmDisasterCode() {
        return mDisasterCode;
    }

    public void setmDisasterCode(String mDisasterCode) {
        this.mDisasterCode = mDisasterCode;
    }

    public String getmDisasterName() {
        return mDisasterName;
    }

    public void setmDisasterName(String mDisasterName) {
        this.mDisasterName = mDisasterName;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public boolean isRefugee() {
        return isRefugee;
    }

    public void setRefugee(boolean refugee) {
        isRefugee = refugee;
    }

}
