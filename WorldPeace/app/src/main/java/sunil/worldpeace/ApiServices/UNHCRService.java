package sunil.worldpeace.ApiServices;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sunil.worldpeace.Model_UNHCR_OriginMap.OriginKeyValPair;
import sunil.worldpeace.Model_UNHCR_Refugees.RefugeeData;

/**
 * Created by sunil on 9/4/16.
 */
public interface UNHCRService {
    // get refugee data
    @GET("stats/persons_of_concern.json")
    Call<List<RefugeeData>>getRefugeeInfo(
            @Query("year") String year,
            @Query("country_of_origin") String origin);

    // only 8 "instances" are currently available: car, cotedivoire, horn, liberia, mali, southsudan, syria, thai
    @GET("whos_doing_what_where/countries.json")
    // ResponseBody will have to be changed...
    Call<ResponseBody>getOrgInfo(
            @Query("instance_id") String id
    );

    // get key-val pair for "persons_of_concern" request
    @GET("stats/origin.json")
    Call<List<OriginKeyValPair>>getKeyValuePair();

}
