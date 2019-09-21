package payment.app.using.payumoney;

import android.util.Log;
import com.payumoney.core.PayUmoneyConstants;
import com.payumoney.core.PayUmoneySdkInitializer;
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class CalculateHash {

    /*
     * Note:The entire hash logic is built on the assumption ‘Salt’ is always safe with the merchant.
     * Hence, it is very important for the merchant to keep the ‘Salt’ safe by adhering to the best security practices.
     * For eg: Merchant should always compute from the server side and never from the client side, should never share the ‘Salt’ & ‘Key’ over emails.
     */
    private static final String TAG = CalculateHash.class.getSimpleName();

    /*
     * Do not use below code when going live
     * Below code is provided to generate hash from sdk.
     * It is recommended to generate hash from server side only.
     */
    public static PayUmoneySdkInitializer.PaymentParam calculateHashUsingJavaCode(final AppConstants appEnvironment, final PayUmoneySdkInitializer.PaymentParam paymentParam) {

        Log.d(TAG,"*****************"+paymentParam);
        StringBuilder stringBuilder = new StringBuilder();
        HashMap<String, String> params = paymentParam.getParams();
        stringBuilder.append(params.get(PayUmoneyConstants.KEY)).append("|");
        stringBuilder.append(params.get(PayUmoneyConstants.TXNID)).append("|");
        stringBuilder.append(params.get(PayUmoneyConstants.AMOUNT)).append("|");
        stringBuilder.append(params.get(PayUmoneyConstants.PRODUCT_INFO)).append("|");
        stringBuilder.append(params.get(PayUmoneyConstants.FIRSTNAME)).append("|");
        stringBuilder.append(params.get(PayUmoneyConstants.EMAIL)).append("|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF1)).append("|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF2)).append("|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF3)).append("|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF4)).append("|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF5)).append("|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF6)).append("|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF7)).append("|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF8)).append("|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF9)).append("|");
        stringBuilder.append(params.get(PayUmoneyConstants.UDF10)).append("|");
        stringBuilder.append(appEnvironment.salt());

        String hash = generateHash("SHA-512",stringBuilder.toString());
        paymentParam.setMerchantHash(hash);
        return paymentParam;
    }

    public static String generateHash(String type, String string)
    {
        byte[] hashSequence = string.getBytes();
        StringBuilder hexString = new StringBuilder();
        try
        {
            MessageDigest algorithm = MessageDigest.getInstance(type);
            algorithm.reset();
            algorithm.update(hashSequence);
            byte messageDigest[] = algorithm.digest();

            for (int i = 0; i<messageDigest.length; i++)
            {
                String hex = Integer.toHexString(0xFF &messageDigest[i]);
                if (hex.length() == 1)
                {
                    hexString.append("0");
                }
                hexString.append(hex);
            }
        }
        catch (NoSuchAlgorithmException NoSuchAlgorithmException)
        {
            Log.d(TAG, "*****ERROR FROM HASH GENERATE USING CODE*****"+NoSuchAlgorithmException);
        }
        return hexString.toString();
    }

    public static void calculateHashFromServerSide(final PayUmoneySdkInitializer.PaymentParam paymentParam, PayUmoneyPaymentActivity payUmoneyPaymentActivity)
    {
        ApiService apiService = ApiUtils.getAPIServiceString();
        Call<String> call = apiService.getHash(paymentParam.getParams());

        call.enqueue(new Callback<String>()
        {
            @Override
            public void onResponse(Call<String> call, Response<String> response)
            {
                if (!response.isSuccessful())
                {
                    System.out.println("Response Code : " + response.code());
                    return;
                }
                String hashString = response.body();
                paymentParam.setMerchantHash(hashString);
                //PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam, payUmoneyPaymentActivity, R.style.PayumoneyAppTheme, false);
                PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam, payUmoneyPaymentActivity, R.style.AppTheme_default, false);
                /*
                 * Invoke the following function to open the checkout page.
                 */
               // PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam, payUmoneyPaymentActivity,-1, true);
                ProgressUtils.cancelLoading();
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable)
            {
                System.out.println("ERROR : " + throwable.getMessage());
            }
        });
    }
}
