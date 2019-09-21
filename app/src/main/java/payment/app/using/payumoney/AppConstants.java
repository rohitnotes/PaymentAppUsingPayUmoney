package payment.app.using.payumoney;

import java.util.UUID;

public enum AppConstants {

    /*
     * Success URL for LIVE mode
     */
    PRODUCTION {

        @Override
        public String merchantKey()
        {
            return "";
        }

        @Override
        public String merchantID()
        {
            return "";
        }

        @Override
        public String salt()
        {
            return "";
        }

        @Override
        public String successURL()
        {
            return "https://www.payumoney.com/mobileapp/payumoney/success.php";
        }

        @Override
        public String failureURL()
        {
            return "https://www.payumoney.com/mobileapp/payumoney/failure.php";
        }

        @Override
        public String generateHashURL()
        {
            return "https://hellomajorproject.000webhostapp.com/payment/payumoney/";
        }

        @Override
        public boolean debug()
        {
            return false;
        }

        @Override
        public String getDouble(String string) {
            return ""+Double.parseDouble(string);
        }

        @Override
        public String getTransactionId() {
            UUID uuid = UUID.randomUUID();
            //return "TXNID"+uuid.getMostSignificantBits();
            return "TXNID"+System.currentTimeMillis();
        }
    },
    /*
     * Success URL for TEST mode
     */
    SANDBOX {

        @Override
        public String merchantKey()
        {
            return "";
        }

        @Override
        public String merchantID()
        {
            return "";
        }

        @Override
        public String salt()
        {
            return "";
        }

        @Override
        public String successURL()
        {
            return "https://www.payumoney.com/mobileapp/payumoney/success.php";
        }

        @Override
        public String failureURL()
        {
            return "https://www.payumoney.com/mobileapp/payumoney/failure.php";
        }

        @Override
        public String generateHashURL()
        {
            return "https://hellomajorproject.000webhostapp.com/payment/payumoney/";
        }

        @Override
        public boolean debug()
        {
            return true;
        }

        @Override
        public String getDouble(String string) {
            return ""+Double.parseDouble(string);
        }

        @Override
        public String getTransactionId() {
            UUID uuid = UUID.randomUUID();
            //return "TXNID"+uuid.getMostSignificantBits();
            return "TXNID"+System.currentTimeMillis();
        }
    };

    public abstract String merchantKey();
    public abstract String merchantID();
    public abstract String salt();
    /*
     * PayUmoney will give a call back to this URL on successful transactions
     */
    public abstract String successURL();
    /*
     * PayUmoney will give a call back to this URL on failed transactions
     */
    public abstract String failureURL();

    /*
     * Your server side hash generator API URL
     * public static String hashSequence = "MERCHANT_KEY|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10|MERCHANT_SALT";
     * public static String hashSequence2 = MERCHANT_KEY+"|"+txnId+"|"+Double.parseDouble(totalPrices)+"|"+productName+"|"+userName+"|"+userEmail+"|"+udf1+"|"+udf2+"|"+udf3+"|"+udf4+"|"+udf5+"|"+MERCHANT_SALT;
     */
    public abstract String generateHashURL();
    /*
     * Integration environment - true ( Debug ) / false( Production )
     */
    public abstract boolean debug();

    public abstract String getTransactionId();

    public abstract String getDouble(String string);
}
