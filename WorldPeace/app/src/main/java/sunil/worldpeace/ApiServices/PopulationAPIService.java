package sunil.worldpeace.ApiServices;

import java.net.URLEncoder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sunil on 9/6/16.
 */
public interface PopulationAPIService {

    @GET("population/{country}/2016-01-01")
    Call<ResponseBody>getPopData(@Path("country") String country);

}
