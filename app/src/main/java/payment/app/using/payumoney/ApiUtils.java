package payment.app.using.payumoney;

public class ApiUtils {

    private static AppConstants appConstants = AppConstants.SANDBOX;
    private static final String API_BASE_URL_FOR_RETROFIT_2 =appConstants.generateHashURL();

    public static ApiService getAPIServiceString()
    {
        return ApiClient.getApiClientString(API_BASE_URL_FOR_RETROFIT_2).create(ApiService.class);
    }

    public static ApiService getAPIServiceJson()
    {
        return ApiClient.getApiClientJson(API_BASE_URL_FOR_RETROFIT_2).create(ApiService.class);
    }
}
