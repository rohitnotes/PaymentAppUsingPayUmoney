package payment.app.using.payumoney;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("HashGenerator.php")
    Call<String> getHash(@FieldMap Map<String, String> fields);
}
