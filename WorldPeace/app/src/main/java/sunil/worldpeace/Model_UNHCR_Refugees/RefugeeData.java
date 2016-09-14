
package sunil.worldpeace.Model_UNHCR_Refugees;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

// POJO to also be used with realm... pretty neat...
@Generated("org.jsonschema2pojo")
public class RefugeeData extends RealmObject {

    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("country_of_residence")
    @Expose
    private String countryOfResidence;
    @SerializedName("country_of_residence_en")
    @Expose
    private String countryOfResidenceEn;
    @SerializedName("country_of_origin")
    @Expose
    private String countryOfOrigin;
    @SerializedName("country_of_origin_en")
    @Expose
    private String countryOfOriginEn;
    @SerializedName("refugees")
    @Expose
    private String refugees;
    @SerializedName("asylum_seekers")
    @Expose
    private String asylumSeekers;
    @SerializedName("returned_refugees")
    @Expose
    private String returnedRefugees;
    @SerializedName("idps")
    @Expose
    private String idps;
    @SerializedName("returned_idps")
    @Expose
    private String returnedIdps;
    @SerializedName("stateless_persons")
    @Expose
    private String statelessPersons;
    @SerializedName("others_of_concern")
    @Expose
    private String othersOfConcern;

    @PrimaryKey
    @SerializedName("total_population")
    @Expose
    private String totalPopulation;

    /**
     * 
     * @return
     *     The year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 
     * @param year
     *     The year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * 
     * @return
     *     The countryOfResidence
     */
    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    /**
     * 
     * @param countryOfResidence
     *     The country_of_residence
     */
    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    /**
     * 
     * @return
     *     The countryOfResidenceEn
     */
    public String getCountryOfResidenceEn() {
        return countryOfResidenceEn;
    }

    /**
     * 
     * @param countryOfResidenceEn
     *     The country_of_residence_en
     */
    public void setCountryOfResidenceEn(String countryOfResidenceEn) {
        this.countryOfResidenceEn = countryOfResidenceEn;
    }

    /**
     * 
     * @return
     *     The countryOfOrigin
     */
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    /**
     * 
     * @param countryOfOrigin
     *     The country_of_origin
     */
    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    /**
     * 
     * @return
     *     The countryOfOriginEn
     */
    public String getCountryOfOriginEn() {
        return countryOfOriginEn;
    }

    /**
     * 
     * @param countryOfOriginEn
     *     The country_of_origin_en
     */
    public void setCountryOfOriginEn(String countryOfOriginEn) {
        this.countryOfOriginEn = countryOfOriginEn;
    }

    /**
     * 
     * @return
     *     The refugees
     */
    public Object getRefugees() {
        return refugees;
    }

    /**
     * 
     * @param refugees
     *     The refugees
     */
    public void setRefugees(Object refugees) {
        refugees = refugees;
    }

    /**
     * 
     * @return
     *     The asylumSeekers
     */
    public Object getAsylumSeekers() {
        return asylumSeekers;
    }

    /**
     * 
     * @param asylumSeekers
     *     The asylum_seekers
     */
    public void setAsylumSeekers(Object asylumSeekers) {
        asylumSeekers = asylumSeekers;
    }

    /**
     * 
     * @return
     *     The returnedRefugees
     */
    public Object getReturnedRefugees() {
        return returnedRefugees;
    }

    /**
     * 
     * @param returnedRefugees
     *     The returned_refugees
     */
    public void setReturnedRefugees(Object returnedRefugees) {
        returnedRefugees = returnedRefugees;
    }

    /**
     * 
     * @return
     *     The idps
     */
    public Object getIdps() {
        return idps;
    }

    /**
     * 
     * @param idps
     *     The idps
     */
    public void setIdps(Object idps) {
        idps = idps;
    }

    /**
     * 
     * @return
     *     The returnedIdps
     */
    public Object getReturnedIdps() {
        return returnedIdps;
    }

    /**
     * 
     * @param returnedIdps
     *     The returned_idps
     */
    public void setReturnedIdps(Object returnedIdps) {
        returnedIdps = returnedIdps;
    }

    /**
     * 
     * @return
     *     The statelessPersons
     */
    public Object getStatelessPersons() {
        return statelessPersons;
    }

    /**
     * 
     * @param statelessPersons
     *     The stateless_persons
     */
    public void setStatelessPersons(Object statelessPersons) {
        statelessPersons = statelessPersons;
    }

    /**
     * 
     * @return
     *     The othersOfConcern
     */
    public Object getOthersOfConcern() {
        return othersOfConcern;
    }

    /**
     * 
     * @param othersOfConcern
     *     The others_of_concern
     */
    public void setOthersOfConcern(Object othersOfConcern) {
        othersOfConcern = othersOfConcern;
    }

    /**
     * 
     * @return
     *     The totalPopulation
     */
    public Object getTotalPopulation() {
        return totalPopulation;
    }

    /**
     * 
     * @param totalPopulation
     *     The total_population
     */
    public void setTotalPopulation(Object totalPopulation) {
        totalPopulation = totalPopulation;
    }

}
