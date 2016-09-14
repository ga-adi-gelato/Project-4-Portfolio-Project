
package sunil.worldpeace.Model_UNHCR_OriginMap;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Generated("org.jsonschema2pojo")
public class OriginKeyValPair extends RealmObject {

    @PrimaryKey // make the countryname the primary key
    @SerializedName("origin_en")
    @Expose
    protected String originEn;

    @SerializedName("origin")
    @Expose
    private String origin;

    /**
     * 
     * @return
     *     The originEn
     */
    public String getOriginEn() {
        return originEn;
    }

    /**
     * 
     * @param originEn
     *     The origin_en
     */
    public void setOriginEn(String originEn) {
        this.originEn = originEn;
    }

    /**
     * 
     * @return
     *     The origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * 
     * @param origin
     *     The origin
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

}
