
package sunil.worldpeace.Model_ReliefWeb;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Fields {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("disaster_type")
    @Expose
    private List<DisasterType> disasterType = new ArrayList<DisasterType>();
    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("country")
    @Expose
    private List<Country> country = new ArrayList<Country>();
    @SerializedName("vulnerable_groups")
    @Expose
    private List<VulnerableGroup> vulnerableGroups = new ArrayList<VulnerableGroup>();

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The disasterType
     */
    public List<DisasterType> getDisasterType() {
        return disasterType;
    }

    /**
     * 
     * @param disasterType
     *     The disaster_type
     */
    public void setDisasterType(List<DisasterType> disasterType) {
        this.disasterType = disasterType;
    }

    /**
     * 
     * @return
     *     The date
     */
    public Date getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 
     * @return
     *     The country
     */
    public List<Country> getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    public void setCountry(List<Country> country) {
        this.country = country;
    }

    /**
     * 
     * @return
     *     The vulnerableGroups
     */
    public List<VulnerableGroup> getVulnerableGroups() {
        return vulnerableGroups;
    }

    /**
     * 
     * @param vulnerableGroups
     *     The vulnerable_groups
     */
    public void setVulnerableGroups(List<VulnerableGroup> vulnerableGroups) {
        this.vulnerableGroups = vulnerableGroups;
    }

}
