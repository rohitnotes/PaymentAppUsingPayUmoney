package payment.app.using.payumoney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.payumoney.core.PayUmoneySdkInitializer;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager;
import com.payumoney.core.entity.TransactionResponse;
import com.payumoney.sdkui.ui.utils.ResultModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PayUmoneyPaymentActivity extends AppCompatActivity {

    private static final String TAG = PayUmoneyPaymentActivity.class.getSimpleName();

    private AppConstants appConstants;
    private PaymentParameters paymentParameters;
    private PayUmoneySdkInitializer.PaymentParam paymentParam;

    private LinearLayout paymentUsingPayUmoney;
    private TextView productNameTextView,productDescriptionTextView,productPriceTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_umoney_payment);
        initView();
        initObject();
        initEvent();
    }

    private void initView()
    {
        productNameTextView = findViewById(R.id.product_name);
        productPriceTextView = findViewById(R.id.product_price);
        productDescriptionTextView = findViewById(R.id.product_description);
        paymentUsingPayUmoney = findViewById(R.id.payment_using_payumoney);
    }

    private void initObject()
    {
        appConstants = AppConstants.SANDBOX;
        // String value =getProductInfo();
        paymentParameters = new PaymentParameters(appConstants.getDouble("1"),appConstants.getTransactionId(),"7898680304","Men's Basic Polyester T-shirts","Rohit","Yadav","rohit2403yadav@gmail.com","user define one","user define two","user define three","user define fourth","user define five","user define six","user define seven","user define eight","user define nine","user define ten");
    }

    private void initEvent()
    {
        paymentUsingPayUmoney.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ProgressUtils.showLoadingDialog(PayUmoneyPaymentActivity.this);
                onBuyPressed();
            }
        });
    }

    private void onBuyPressed()
    {
        PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();

        builder.setAmount(paymentParameters.getAmount());
        builder.setTxnId(paymentParameters.getTransactionId());
        builder.setPhone(paymentParameters.getCustomerPhone());
        builder.setProductName(paymentParameters.getProductName());
        builder.setFirstName(paymentParameters.getCustomerFirstName()+" "+paymentParameters.getCustomerLastName());
        builder.setEmail(paymentParameters.getCustomerEmail());
        builder.setsUrl(appConstants.successURL());
        builder.setfUrl(appConstants.failureURL());
        builder.setUdf1(paymentParameters.getUserDefineFieldOne());
        builder.setUdf2(paymentParameters.getUserDefineFieldTwo());
        builder.setUdf3(paymentParameters.getUserDefineFieldThree());
        builder.setUdf4(paymentParameters.getUserDefineFieldFour());
        builder.setUdf5(paymentParameters.getUserDefineFieldFive());
        builder.setUdf6(paymentParameters.getUserDefineFieldSix());
        builder.setUdf7(paymentParameters.getUserDefineFieldSeven());
        builder.setUdf8(paymentParameters.getUserDefineFieldEight());
        builder.setUdf9(paymentParameters.getUserDefineFieldNine());
        builder.setUdf10(paymentParameters.getUserDefineFieldTen());
        builder.setIsDebug(appConstants.debug());
        builder.setKey(appConstants.merchantKey());
        builder.setMerchantId(appConstants.merchantID());

        try
        {
            paymentParam = builder.build();

            /*
             * Do not use below code when going live mode
             * Below code is provided to generate hash from sdk.
             * It is recommended to generate hash from server side only.
             */
            //paymentParam=CalculateHash.calculateHashUsingJavaCode(appConstants,paymentParam);
            //PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam, PayUmoneyPaymentActivity.this, R.style.PayumoneyAppTheme, false);

            /*
             * Hash should always be generated from your server side.
             */
            CalculateHash.calculateHashFromServerSide(paymentParam,PayUmoneyPaymentActivity.this);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        /*
         * Result Code is -1 send from Payumoney activity
         */
        Log.d(TAG, "request code " + requestCode + " resultcode " + resultCode);

        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data != null)
        {
            TransactionResponse transactionResponse = data.getParcelableExtra(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE);

            ResultModel resultModel = data.getParcelableExtra(PayUmoneyFlowManager.ARG_RESULT);

            /*
             * Check which object is non-null
             */
            if (transactionResponse != null && transactionResponse.getPayuResponse() != null)
            {
                if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.SUCCESSFUL))
                {
                    /*
                     * Success Transaction
                     */
                    // Response from Payumoney
                    String payuResponse = transactionResponse.getPayuResponse();

                    // Response from SURl and FURL
                    String merchantResponse = transactionResponse.getTransactionDetails();
                    Log.d(TAG, "getPayuResponse() \n"+payuResponse);
                    Log.d(TAG, "getTransactionDetails() \n"+merchantResponse);
                    Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
                }
                else if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.CANCELLED))
                {
                    Toast.makeText(this, "Payment Cancelled", Toast.LENGTH_SHORT).show();
                }
                else if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.FAILED))
                {
                    /*
                     * Failure Transaction
                     */
                    Log.d(TAG, "***RESULT MODEL "+resultModel);
                    Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
                }
            }
            else if (resultModel != null && resultModel.getError() != null)
            {
                Toast.makeText(this, "Error check log", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Both objects are null", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_CANCELED)
        {
            Toast.makeText(this, "Payment Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isValidEmail(String strEmail)
    {
        return strEmail != null && android.util.Patterns.EMAIL_ADDRESS.matcher(strEmail).matches();
    }

    public static boolean isValidPhone(String phone)
    {
        Pattern pattern = Pattern.compile("^[987]\\d{9}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public String getProductInfo()
    {
        JSONObject productInfoJsonObject = new JSONObject();
        JSONArray productJsonArray = new JSONArray();
        JSONObject productJsonObject = new JSONObject();

        try {

            productJsonObject.put("name", "product one");
            productJsonObject.put("description", "product one description");
            productJsonObject.put("number_of_product", "1");
            productJsonObject.put("price", "100");
            productJsonArray.put(productJsonObject);
            productInfoJsonObject.put("productList", productJsonArray);
            productInfoJsonObject.put("date_time", "31/10/2017");
            productInfoJsonObject.put("transaction_id", "TXT");
            productInfoJsonObject.put("total_amount", "1500");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return productInfoJsonObject.toString();
    }
}
