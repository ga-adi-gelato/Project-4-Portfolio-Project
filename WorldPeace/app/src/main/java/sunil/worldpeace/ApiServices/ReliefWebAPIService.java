package sunil.worldpeace.ApiServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sunil.worldpeace.Model_ReliefWeb.Example;


/**
 * Created by sunil on 8/29/16.
 */

public interface ReliefWebAPIService {
    @GET("?appname=apidoc/")
    Call<Example>getCountryData(
             @Query("filter[operator]") String operator,
             @Query("filter[conditions][0][field]") String field1, //encodename=true
             @Query("filter[conditions][0][value]") String value1,
             @Query("filter[conditions][1][field]") String field2,
             @Query("filter[conditions][2][field]") String field3,
             @Query("filter[conditions][2][value]") String value2,
             @Query("fields[include][]") List<String> fields,
             @Query("order") String order,
             @Query("limit") String limit
    );

}
