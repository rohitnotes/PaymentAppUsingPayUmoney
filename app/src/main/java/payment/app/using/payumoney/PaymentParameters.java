package payment.app.using.payumoney;

public class PaymentParameters {

    /*
     * Amount in String Double Value
     * ""+Double.parseDouble("totalPrices")+""
     */
    private String amount;
    /*
     * Transaction Id
     */
    private String transactionId;
    /*
     * Provide a valid phone number of the customer to fetch saved cards
     */
    private String customerPhone;
    /*
     * Product Name or ProductInfo
     */
    private String productName;
    /*
     * Customer Name
     */
    private String customerFirstName;
    /*
     * Provide a valid phone number of the customer to fetch saved cards
     */
    private String customerLastName;
    /*
     * Provide valid email of customer to fetch saved cards
     */
    private String customerEmail;
    /*
     * User Defined Fields – Free form field to include additional information about the transaction
     */
    private String userDefineFieldOne;
    private String userDefineFieldTwo;
    private String userDefineFieldThree;
    private String userDefineFieldFour;
    private String userDefineFieldFive;
    private String userDefineFieldSix;
    private String userDefineFieldSeven;
    private String userDefineFieldEight;
    private String userDefineFieldNine;
    private String userDefineFieldTen;

    public PaymentParameters(String amount, String transactionId, String customerPhone, String productName, String customerFirstName, String customerLastName, String customerEmail, String userDefineFieldOne, String userDefineFieldTwo, String userDefineFieldThree, String userDefineFieldFour, String userDefineFieldFive, String userDefineFieldSix, String userDefineFieldSeven, String userDefineFieldEight, String userDefineFieldNine, String userDefineFieldTen) {
        this.amount = amount;
        this.transactionId = transactionId;
        this.customerPhone = customerPhone;
        this.productName = productName;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerEmail = customerEmail;
        this.userDefineFieldOne = userDefineFieldOne;
        this.userDefineFieldTwo = userDefineFieldTwo;
        this.userDefineFieldThree = userDefineFieldThree;
        this.userDefineFieldFour = userDefineFieldFour;
        this.userDefineFieldFive = userDefineFieldFive;
        this.userDefineFieldSix = userDefineFieldSix;
        this.userDefineFieldSeven = userDefineFieldSeven;
        this.userDefineFieldEight = userDefineFieldEight;
        this.userDefineFieldNine = userDefineFieldNine;
        this.userDefineFieldTen = userDefineFieldTen;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getUserDefineFieldOne() {
        return userDefineFieldOne;
    }

    public void setUserDefineFieldOne(String userDefineFieldOne) {
        this.userDefineFieldOne = userDefineFieldOne;
    }

    public String getUserDefineFieldTwo() {
        return userDefineFieldTwo;
    }

    public void setUserDefineFieldTwo(String userDefineFieldTwo) {
        this.userDefineFieldTwo = userDefineFieldTwo;
    }

    public String getUserDefineFieldThree() {
        return userDefineFieldThree;
    }

    public void setUserDefineFieldThree(String userDefineFieldThree) {
        this.userDefineFieldThree = userDefineFieldThree;
    }

    public String getUserDefineFieldFour() {
        return userDefineFieldFour;
    }

    public void setUserDefineFieldFour(String userDefineFieldFour) {
        this.userDefineFieldFour = userDefineFieldFour;
    }

    public String getUserDefineFieldFive() {
        return userDefineFieldFive;
    }

    public void setUserDefineFieldFive(String userDefineFieldFive) {
        this.userDefineFieldFive = userDefineFieldFive;
    }

    public String getUserDefineFieldSix() {
        return userDefineFieldSix;
    }

    public void setUserDefineFieldSix(String userDefineFieldSix) {
        this.userDefineFieldSix = userDefineFieldSix;
    }

    public String getUserDefineFieldSeven() {
        return userDefineFieldSeven;
    }

    public void setUserDefineFieldSeven(String userDefineFieldSeven) {
        this.userDefineFieldSeven = userDefineFieldSeven;
    }

    public String getUserDefineFieldEight() {
        return userDefineFieldEight;
    }

    public void setUserDefineFieldEight(String userDefineFieldEight) {
        this.userDefineFieldEight = userDefineFieldEight;
    }

    public String getUserDefineFieldNine() {
        return userDefineFieldNine;
    }

    public void setUserDefineFieldNine(String userDefineFieldNine) {
        this.userDefineFieldNine = userDefineFieldNine;
    }

    public String getUserDefineFieldTen() {
        return userDefineFieldTen;
    }

    public void setUserDefineFieldTen(String userDefineFieldTen) {
        this.userDefineFieldTen = userDefineFieldTen;
    }
}
