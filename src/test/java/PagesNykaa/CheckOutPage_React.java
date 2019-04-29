package PagesNykaa;

import DataNykaa.AccountData;
import DataNykaa.CheckoutData;
import DataNykaa.ProductDetailData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Locator;
import FrameWorkNykaa.TestListner;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CheckOutPage_React extends BrowserAction{
    public CheckOutPage_React(WebDriver browser) {
        this.driver = browser;
    }

    private Locator AddreesId(String id) {
        return new Locator(By.xpath("//div[@id='sa_item" + id + "']/div[@class='sa_item_green_btn']"), "Address");
    }

    Locator CouponCodeTextBox() {
        return new Locator(By.id("coupon_code"), "Coupon Code TextBox");
    }

    Locator LoginStepComplete() {
        return new Locator(
                By.xpath("//div[@id='steponetitle'][@class='content_row_title_container steponetitleBlock complete']"),
                "Login Step Completed sign");

    }

    Locator ApplyCouponButton() {
        return new Locator(By.xpath("//button[@title='Apply Coupon']"), "Apply Coupon Button");
    }

    Locator ShoppingCartTable() {
        return new Locator(By.xpath("//table[@id='shopping-cart-totals-table'])"), "Shopping Cart Table");
    }

    Locator ShoppingCartTableLeftColumn() {
        return new Locator(By.xpath("//table[@id='shopping-cart-totals-table']/descendant::td[@class='co-left']"),
                "Left columns");
    }

    Locator ShoppingCartTableRightColumn() {
        return new Locator(By.xpath("/following-sibling::td[@class='co-right pink']/span"), "Right columns");
    }

    Locator PartialPaymentCheckbox() {
        return new Locator(By.xpath("//input[@name='partial_payment']"), "Partial Payment Button");
    }
    Locator PartialPaymentAdvanceValue() {
        return new Locator(By.xpath("//div[text()='Advance Payment']/following-sibling::div"), "Advance Payment");
    }
    Locator PartialPaymentPOVValue() {
        return new Locator(By.xpath("//div[text()='Pay On Visit']/following-sibling::div"), "Pay On Visit");
    }

    Locator PartialPaymentTotalValue() {
        return new Locator(By.xpath("//div[text()='Total Amount']/following-sibling::div"), "Total");
    }

    Locator POVAppliedFlag() {
        return new Locator(By.xpath("//input[@id='pov_applied'][@value='1']"), "POV Applied");
    }

    private Locator Shipping_street1() {
        return new Locator(By.xpath("//div[contains(@class,'input-row')]//textarea"), "Shiping street1 In Address");
    }

    private Locator Shipping_street2() {
        return new Locator(By.id("shipping_street2"), "Shiping street2 In Address");
    }

    private Locator ProceedButton() {
        return new Locator(By.xpath("//button[contains(text(),'PROCEED')]"), "Continue Button");
    }

    Locator ContinueRegstration() {
        return new Locator(By.id("altcontinuebtn"), "Registration Continue button");
    }

    private Locator submitButton() {
        return new Locator(By.xpath("//button[@type='submit']"), "Submit button");
    }

    private Locator ContinueAsGuest() {
        return new Locator(By.xpath("//a[contains(text(),'Continue as guest')]"), "Continue as guest Button");
    }

    Locator debitCardOption() {
        return new Locator(By.xpath("//ul[@class='payment-sidebar-nav']//a[contains(text(), 'Credit/Debit Card')]"), "Debit Card Option");
    }
    Locator creditCardOption() {
        return new Locator(By.xpath("//ul[@class='payment-sidebar-nav']//a[contains(text(), 'Credit/Debit Card')]"), "Credit Card Option");
    }

    Locator simplePayOption() {
        return new Locator(By.id("method_simpl"), "simple Pay Option 'of 'Payment");
    }

    Locator simplePayPayLaterButton() {

        return new Locator(By.id("simpl-button"), "Simple Button");
    }

    Locator iframeSimple() {
        return new Locator(By.id("simpl-iframe"), "Simple I frame");
    }

    Locator simpleOtpText() {
        return new Locator(By.xpath("//div[@class='bnl__otp__get-started']/p"), "Otp Mobile textArea");
    }

    Locator iframeOTPsimple() {
        return new Locator(By.xpath("//iframe[@id='i']"), "OTP box iframe");
    }

    Locator DebitCardNoText() {
        return new Locator(By.xpath("//div[@class='credit-debit-card-form-group prl10']//input[@id='card-number-input-field']"), "Debit card number text");
    }

    Locator CreditCardNoText() {
        return new Locator(By.xpath("//div[@class='credit-debit-card-form-group prl10']//input[@id='card-number-input-field']"), "Credit card number text");
    }

    Locator DebitCardName() {
        return new Locator(By.id("debit_card_name"), "Debit Card Name");
    }

    Locator CreditCardName() {
        return new Locator(By.id("credit_card_name"), "Credit Card Name");
    }

    Locator DebitCardExpMonth() {
        return new Locator(By.xpath("//div[@class='credit-debit-card-form-group prl10']//input[@name='expiryMonth']"), "Debit Card Exp Month");
    }
    Locator DebitCardExpYear(){
        return new Locator(By.xpath("//div[@class='credit-debit-card-form-group prl10']//input[@name='expiryYear']"), "Debit Card Exp Year");
    }


    Locator CreditCardExp() {
        return new Locator(By.id("credit_card_exp"), "Credit Card Exp Text");
    }

    Locator CashOnDeliveryMethod() {
        return new Locator(By.xpath("//ul[@class='payment-sidebar-nav']//a[contains(text(), 'Cash on Delivery')]"), "Cash On Delivery Method");
    }

    Locator COD_placeOrderBtn() {
        return new Locator(By.xpath("//div[@class='payment-type-form prl10 open']//button[@class='btn fill full big proceed false']"), "Cod Place Order");
    }

    Locator RewardPointText() {
        return new Locator(By.id("reward_appl"), "Reward point Viewtext");

    }

    Locator DebitCard_ccvText_Locator() {

        return new Locator(By.xpath("//div[@class='credit-debit-card-form-group prl10']//input[@name='cvv']"), "Debit Card ccv");
    }

    Locator CreditCard_ccvText_Locator() {

        return new Locator(By.id("credit_card_ccv"), "Credit Card ccv");
    }

    Locator debitCardButn_Locator() {

        return new Locator(By.xpath("//div[@class='payment-type-form open prl10']//button[contains(@class,'proceed')]"), "Debit Card button ");

    }

    Locator creditCardButn_Locator() {

        return new Locator(By.id("creditCardButn"), "Debit Card button ");

    }

    Locator COD_Confirmation() {
        return new Locator(By.xpath("//div[@class='confirmed-information']/div[@class='order-detail-info']"),
                "Confirmation Cod Page");
    }

    Locator checkoutPageLoader() {
        return new Locator(By.xpath("//div[@id='pageblock'][contains(@style,'block')]"), "CheckOut Page Loader");
    }

    Locator shipping_Region() {
        return new Locator(By.xpath("//div[@class='row no-gutters mtb10']"), "Shipping Region");
    }

    private Locator Checkout_Button() {
        return new Locator(By.xpath("//span[text()='CHECKOUT ']"), "Checkout Button");
    }

    private Locator Close_Button() {
        return new Locator(By.xpath("//div[@class='close']|//a[@class='close']"), "Close Button");
    }

    private Locator guestemail() {
        return new Locator(By.xpath("//input[contains(@name,'email')]"), "Guest Continue Email");
    }

    private Locator Shipping_FirstName() {
        return new Locator(By.id("shipping_firstname"), "Shipping Firstname");
    }

    private Locator Shipping_LastName() {
        return new Locator(By.id("shipping_lastname"), "Shipping Lastname");
    }

    private Locator Shipping_telephone() {
        return new Locator(By.xpath("//input[contains(@name,'phoneNumber')]"), "Shipping PostCode");

    }

    Locator LoaderIcon() {
        return new Locator(By.id("ajax_loading_img"), "Loader Icon");
    }

    private Locator Shipping_PostCode() {
        return new Locator(By.xpath("//input[contains(@name,'pinCode')]"), "shipping_postcode");
    }

    Locator NetBankingMethode() {
        return new Locator(By.xpath("//ul[@class='payment-sidebar-nav']//a[contains(text(), 'Net Banking')]"), "NetBankingMethod");

    }

    Locator NetBankingDropdown() {
        return new Locator(By.id("netbanking_select"), "NetBanking Dropdown");

    }

    Locator ProceedToPayment() {

        return new Locator(By.xpath("//div[@class='payment-type-form prl10 open']//button[@class='btn fill full big proceed']"),
                "Proceed To Payment");
    }

    Locator Payoumoney_Methode() {
        return new Locator(By.id("method_payumoney"), "Payyoumoney Method");
    }

    Locator PayuMoneProceedToPayment() {

        return new Locator(
                By.xpath(
                        "//div[@id='payumoney_options']/descendant::div[@class='payment_button']/input[@value='Proceed To Payment ']"),
                "Proceed To Payment");
    }

    Locator WalletProceedToPayment() {

        return new Locator(By.xpath("//div[@class='preferred-payment-type mobile-wallet-type border-none pb0  open prl5']//button"), "proceed To Pay");
    }

    Locator registerNow() {
        return new Locator(By.id("registernowbtn"), "register now Button");
    }

    Locator passwordText() {
        return new Locator(By.id("billing_customer_password"), "Password text");
    }

    Locator ConfirmPassword() {
        return new Locator(By.id("billing_confirm_password"), "Confirm Password");
    }

    Locator SimpleBrowserText() {
        return new Locator(By.xpath("//div[@id='simpl-backdrop-text']"), "simple browser alert");
    }

    Locator PayoumoneyIcon() {

        return new Locator(By.xpath("//div[@class='pymnt_main']/descendant::a/img[@title='PayUMoney Payment']"),
                "Payoumoney Icon");
    }

    Locator WalletMethod() {

        return new Locator(By.xpath("//ul[@class='payment-sidebar-nav']//a[contains(text(), 'Mobile Wallets')]"), "Wallet Method");
    }

    Locator PayTMradioButton() {
        return new Locator(By.xpath("//input[@name='wallets_options_radio'][@value='paytm']"), "PayTm Radio Button");
    }

    Locator mobikwikRadioButton() {
        return new Locator(By.id("mobikwik_radion_btn"), "Mobikwik radio button");
    }

    Locator mobikwikLoginText() {
        return new Locator(By.xpath("//div[@title='Paytm Payments']"), "Mobikwik login");
    }

    Locator PayByWallet() {
        return new Locator(By.id("wallets_btn"), "Proceed to payment");
    }

    Locator PayTMLogin() {

        return new Locator(By.xpath("//input[@name='username']"), "PayTM Login Popup");
    }

    Locator PayTMLoginForm() {
        return new Locator(By.id("login-iframe"), "login form");
    }

    Locator subTotalValueview() {
        return new Locator(By.xpath("//div[contains(text(), 'Subtotal')]/ancestor::div[@class='payment-details-tbl']/div/span"),
                "subtotal value");
    }

    Locator ShippingValueView() {
        return new Locator(By.xpath("//div[contains(text(), 'Shipping Charge')]/ancestor::div[@class='payment-details-tbl']/div/span"),
                "Shipping Value");
    }

    Locator RewardPointValueView() {

        return new Locator(By.xpath("//div[contains(text(), 'Discount')]/ancestor::div[@class='payment-details-tbl']/div/span"),
                "Reward point value view");

    }

    Locator OrderTotalValueView() {
        return new Locator(By.xpath("//div[contains(text(), 'Grand Total')]/ancestor::div[@class='payment-details-tbl grand-total-cell prl20']/div/span"), "Order Total value");
    }

    Locator DiscountValueView() {

        return new Locator(By.xpath("//div[contains(text(), 'Bag Discount')]/ancestor::div[@class='payment-details-tbl']/div/span"),
                "Discount");

    }

    Locator MultiCartItems() {
        return new Locator(By.xpath("//div[@class='bag-details-box']/div"), "Cart Items");

    }

    Locator ExtendedTitleofCartItem() {
        return new Locator(By.xpath("//div[@class='product-name']"), " name Of Product");
    }

    Locator ExtendedPrizeofCartItem() {

        return new Locator(By.xpath("//div[@class='product-price']"), "prize of Cart Item");
    }

    Locator ExtendedQuantityOfCartItem() {
        return new Locator(By.xpath("//div[@class='product-quantity']"), "Extended Quatinty");
    }

    Locator Fname_Subscription(){
        return new Locator(By.id("firstname"), "First Name");
    }
    Locator LName_Subscription(){
        return new Locator(By.id("lastname"),"Last Name");
    }
    Locator Email_Subscription(){
        return new Locator(By.id("reqFldEmail"),"Subscribe Email");
    }
    Locator Saddress_Subscription(){
        return new Locator(By.id("street_1"),"Street Address");
    }
    Locator Pincode_Subscription(){
        return new Locator(By.id("zip"),"Pincode");
    }
    Locator Phone_Subscription(){
        return new Locator(By.id("telephone"),"Mobile Numner");
    }
    Locator SubscribeFinalConfirm(){
        return new Locator(By.xpath("//input[@value='Ship To This Address']"),"Final Confirmation For Subscribe");
    }
    Locator SubscribeConfirmation(){
        return new Locator(By.id("subscription_confirm_submit"),"Confirmation Button");
    }


    Locator quicklogin(){
        return new Locator(By.xpath("//div[contains(text(),'QUICK LOGIN USING')]"), "Quick Login");
    }

    Locator Shipping_Name(){
        return new Locator(By.xpath("//input[contains(@name,'name')]"), "User Name");
    }

    Locator Price_Details(){
        return new Locator(By.xpath("//div[@class='payment-details-tbl grand-total-cell prl20']/div/i[@class='icon']"), "Price Details icon");
    }

    Locator HDFCBankOption(){
        return new Locator(By.xpath("//div[@class='row net-banking-list-section mrl0 mb10']//div[contains(text(), 'hdfc')]"), "HDFC Bank Option in Net Banking");
    }

    Locator CartDetails(){
        return new Locator(By.xpath("//span[@class='details-info']/i"), "To View Cart Item");
    }

    private Locator Billing_Address() {
        return new Locator(By.id("billing_email"), "Billing Address");
    }

    private Locator Continue() {
        return new Locator(By.id("isregisteredbtn"), "Continue Button");
    }

    Locator AddressWindow(){
        return new Locator(By.xpath("//div[contains(text(),'2 - ADDRESS')]"), "Address Window");
    }

    Locator NewAddressButton(){
        return new Locator(By.xpath("//ul[@class='payment-sidebar-nav']//a[contains(text(),'New Address')]"), "New Addess Button");
    }

    public void checkOut() throws InterruptedException, InstantiationException, IllegalAccessException {
        if (isNotificationAdvertisePresent(driver)) {
            click(Close_Button());
            driver.switchTo().defaultContent();
        }
        Thread.sleep(2000);
        click(Checkout_Button());

    }

    public boolean savedAddressSelect() throws Throwable, IllegalAccessException {
        waitUntilPageBlockDisappear(driver);
        waitUntilDisappear(checkoutPageLoader());
        Thread.sleep(2000);
        if(isPresent(AddreesId("1")))
        {
            click(AddreesId("1"));
        }
        else
        {
            return false;
        }
        waitUntilDisappear(checkoutPageLoader());
        return true;
    }

    public String isAddressSelected() throws IllegalArgumentException, IllegalAccessException {
        if(waitUntilDisplayed(AddreesId("1"), 3))
        {
            return "true";
        }
        else if(waitUntilDisplayed(debitCardOption(), 3))
        {
            return "true";
        }
        else
        {
            return null;
        }
    }

    public boolean isRewardPointApplied() throws IllegalArgumentException, IllegalAccessException {
        waitUntilDisplayed(RewardPointText(), 10);

        String rewardPointText = getText(RewardPointText());

        return rewardPointText.contains("applied");

    }

    public void checkoutWithDebitCard(CheckoutData checkoutData) throws Throwable {
        int count=0;
        waitforPageReady();
        waitUntilDisappear(checkoutPageLoader());
        waitUntilDisplayed(debitCardOption(), 60);

        waitUntilDisappear(checkoutPageLoader());
        while(!isDisplayed(DebitCardNoText()) && count<10)
        {
            click(debitCardOption());
            Thread.sleep(1000);
            count+=1;
        }
        waitUntilPageBlockDisappear(driver);
        while(getAttribute(DebitCardNoText(), "value").equals(""))
        {
            ClearValue(DebitCardNoText());
            EnterValue(DebitCardNoText(), checkoutData.getCardno());
        }
       // click(DebitCardName());
        //waitUntilPageBlockDisappear(driver);
        String Expdate = checkoutData.getExpDate();
        String ArrExpdate[] = Expdate.split("/");
       // EnterValue(DebitCardName(), checkoutData.getCardname());
        waitUntilPageBlockDisappear(driver);
        EnterValue(DebitCardExpMonth(), ArrExpdate[0]);
        EnterValue(DebitCardExpYear(), ArrExpdate[1]);
        EnterValue(DebitCard_ccvText_Locator(), checkoutData.getCcv());
        click(debitCardButn_Locator());
    }

    public void checkoutWithCreditCard(CheckoutData checkoutData) throws Throwable {
        waitforPageReady();
        waitUntilDisappear(checkoutPageLoader());
        waitUntilDisplayed(creditCardOption(), 60);

        waitUntilDisappear(checkoutPageLoader());
        click(creditCardOption());
        waitUntilPageBlockDisappear(driver);
        click(CreditCardNoText());
        while(getAttribute(CreditCardNoText(), "value").equals(""))
        {
            ClearValue(CreditCardNoText());
            EnterValue(CreditCardNoText(), checkoutData.getCardno());
        }
        click(CreditCardName());
        Thread.sleep(1000);
        waitUntilPageBlockDisappear(driver);
        EnterValue(CreditCardName(), checkoutData.getCardname());
        waitUntilPageBlockDisappear(driver);
        EnterValue(CreditCardExp(), checkoutData.getExpDate());
        EnterValue(CreditCard_ccvText_Locator(), checkoutData.getCcv());
        click(creditCardButn_Locator());
    }

    public String checkoutWithSimple() throws Throwable {
        waitforPageReady();
        waitUntilDisappear(checkoutPageLoader());
        click(simplePayOption());
        waitUntilDisappear(checkoutPageLoader());
        String mainWindow = driver.getWindowHandle();
        SwitchToFrameIFAvaialble(iframeSimple());
        click(simplePayPayLaterButton());
        waitUntilDisplayed(SimpleBrowserText(), 5);
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equalsIgnoreCase(mainWindow)) {
                if (driver.switchTo().window(window).getTitle().equalsIgnoreCase("simpl")) {

                    waitForPagetoLoad();
                    break;
                }
            }
        }

        SwitchToFrameIFAvaialble(iframeOTPsimple());

        waitUntilDisplayed(simpleOtpText(), 60);
        String OtpText = getText(simpleOtpText());
        System.out.println(OtpText);

        return OtpText;

    }

    public boolean applyPOV() throws Exception, Throwable {
        waitUntilPresent(PartialPaymentCheckbox(), 3);
        Thread.sleep(1000);
        click(PartialPaymentCheckbox());
        waitUntilDisappear(PartialPaymentCheckbox());
        if(!VerifyPOVValue())
        {
            return false;
        }
        else
        {
            return waitUntilPresent(POVAppliedFlag(), 5);
        }
    }

    public boolean VerifyPOVValue() throws Exception, Throwable {
        Double rewardPoints=0.0;
        waitUntilDisplayed(PartialPaymentTotalValue(), 5);
        Double total=Double.parseDouble(geteText(PartialPaymentTotalValue().getBy()).replaceAll("Rs. ", "").replace(",", "").trim());
        Double subTotal=Double.parseDouble(geteText(subTotalValueview().getBy()).replaceAll("Rs. ", "").replaceAll(",", "").trim());
        Double advance=Double.parseDouble(geteText(PartialPaymentAdvanceValue().getBy()).replaceAll("Rs. ", "").replace(",", "").trim());
        Double pov=Double.parseDouble(geteText(PartialPaymentPOVValue().getBy()).replaceAll("Rs. ", "").replace(",", "").trim());
        if(isDisplayed(RewardPointValueView()))
        {
            rewardPoints=Double.parseDouble(geteText(RewardPointValueView().getBy()).replaceAll("-Rs. ", "").replace(",", "").trim());
        }
        if(advance+pov==total && advance<(((.20)*subTotal)-(rewardPoints)+1) && advance>(((.20)*subTotal)-(rewardPoints)-1))
        {
            return true;
        }
        TestListner.testing.get().log(LogStatus.FAIL,"POV Value is wrong- Advance should be 20% of Total. Advance value= " + advance+"  || Total Value: "+total);
        return false;
    }

    public boolean CashOnDelivery() throws Throwable {
        boolean flag = false;
        waitforPageReady();
        waitUntilDisappear(checkoutPageLoader());
        Thread.sleep(5000);
        waitUntilDisplayed(CashOnDeliveryMethod(), 5);
        click(CashOnDeliveryMethod());
        waitUntilDisappear(checkoutPageLoader());
        Thread.sleep(2000);
        if(!waitUntilDisplayed(COD_placeOrderBtn(), 4))
        {
            click(CashOnDeliveryMethod());
        }
        Thread.sleep(1000);
        click(COD_placeOrderBtn());
        if (waitUntilDisplayed(COD_Confirmation(), 60)) {
            ConfirmationPage cnfrmPage = new ConfirmationPage(driver);
            cnfrmPage.getConfirmationMessage();
            flag = true;
        }
        return flag;

    }

    public boolean newRegister(AccountData accountData) throws Throwable, Throwable, Throwable {
        Random random = new Random(System.currentTimeMillis());
        EnterValue(Billing_Address(),
                accountData.getFirstName() + "." + accountData.getLastName() + random.nextInt() + "@mailinator.com");
        if (!isDisappear(Continue(), 3)) {
            click(Continue());
            waitUntilDisappear(checkoutPageLoader());
            click(registerNow());
            waitUntilDisappear(checkoutPageLoader());
        }
        EnterValue(passwordText(), "Password123");
        EnterValue(ConfirmPassword(), "Password123");
        click(ContinueRegstration());

        return waitUntilDisplayed(LoginStepComplete(), 5);

    }

    public boolean netBankingCheckout() throws Throwable {
        waitforPageReady();
        waitUntilDisappear(checkoutPageLoader());
        Thread.sleep(2000);
        click(NetBankingMethode());
        click(HDFCBankOption());
        Thread.sleep(2000);
        //selectFromDropDownByValue(NetBankingDropdown(), "HDFB");
        //waitUntilPageBlockDisappear(driver);
        //waitUntilDisappear(checkoutPageLoader());
        //waitUntilDisplayed(ProceedToPayment(), 15);
        click(ProceedToPayment());
        waitUntilPageBlockDisappear(driver);
        BankingPage bankingPage = new BankingPage(driver);
        SwitchToFrameIFAvaialble(bankingPage.HDFCFrame());
        boolean netbankLoginavailable = waitUntilDisplayed(bankingPage.NetBankingCustomerLogin(), 3);
        switchToDefaultContent();
        return netbankLoginavailable;

    }

    public boolean payumoneyCheckout() throws Throwable {
        waitUntilDisappear(checkoutPageLoader());
        waitUntilPageBlockDisappear(driver);
        click(Payoumoney_Methode());
        waitUntilPageBlockDisappear(driver);
        click(PayuMoneProceedToPayment());
        return waitUntilDisplayed(PayoumoneyIcon(), 60);

    }

    public boolean PaytmWalletCheckout() throws Throwable {
        waitUntilDisappear(checkoutPageLoader());
        waitUntilPageBlockDisappear(driver);

        click(WalletMethod());
       // click(mobikwikRadioButton());

        // click(PayTMradioButton());
        click(WalletProceedToPayment());

        return waitUntilDisplayed(mobikwikLoginText(), 60);

		/*
		 * boolean frameAvailable = SwitchToFrameIFAvaialble(PayTMLoginForm());
		 * switchToDefaultContent(); return frameAvailable;
		 */

    }

    public void fillbillingEmailIDAndContinueAsGuest(CheckoutData checkoutData) throws Throwable, Throwable {
        if(waitUntilDisplayed(quicklogin(), 10)){
            EnterValue(guestemail(),checkoutData.getUsername() );
        }
        if (!isDisappear(ProceedButton(), 3)) {
            click(ProceedButton());
            Thread.sleep(3);
        }
        click(ContinueAsGuest());
        Thread.sleep(5000);
    }

    public boolean fillNewAddress(CheckoutData checkOutData) throws Throwable {
        boolean flag=false;
        if(!(isDisplayed(NewAddressButton()))){
             click(AddressWindow());
             Thread.sleep(3000);
        }
        if(isDisplayed(NewAddressButton())){
            click(NewAddressButton());
        }
		/*
		 * EnterValue(Billing_Address(), checkOutData.getUsername());
		 * click(Continue()); click(ContinueAsGuest());
		 * waitUntilDisappear(checkoutPageLoader());
		 */
        try
        {
            flag=savedAddressSelect();
        }
        catch(Exception e) {}
        if(!flag)
        {
            /*ClearValue(Shipping_FirstName());
            EnterValue(Shipping_FirstName(), checkOutData.getFirstname());
            ClearValue(Shipping_LastName());
            EnterValue(Shipping_LastName(), checkOutData.getLastname());*/
            ClearValue(Shipping_Name());
            EnterValue(Shipping_Name(), checkOutData.getFirstname());
            ClearValue(Shipping_telephone());
            EnterValue(Shipping_telephone(), checkOutData.getTelephone());
            ClearValue(Shipping_PostCode());
            EnterValue(Shipping_PostCode(), checkOutData.getPostcode());
            //ClearValue(Shipping_street1());
            EnterValue(Shipping_street1(), checkOutData.getShipping_address1());
         /*   ClearValue(Shipping_street2());
            EnterValue(Shipping_street2(), checkOutData.getShipping_address2());*/
            Thread.sleep(5000);
            //waitUntilDisappear(checkoutPageLoader());
            waitUntilDisplayed(shipping_Region(), 5);

            waitUntilDisplayed(submitButton(), 3);
            bringElementIntoView(submitButton());
            ScrollDown("200");
            click(submitButton());
            Thread.sleep(7000);
            return waitUntilDisplayed(debitCardOption(), 10);
        }
        else
        {
            return flag;
        }
    }

    public boolean fillNewAddressBeautyService(CheckoutData checkOutData) throws Throwable {
        EnterValue(Shipping_telephone(), checkOutData.getTelephone());

        waitUntilDisplayed(submitButton(), 3);
        bringElementIntoView(submitButton());
        ScrollDown("200");
        click(submitButton());
        return waitUntilDisplayed(debitCardOption(), 10);
    }

    public List<ProductDetailData> FetchProductDetail() throws IllegalArgumentException, IllegalAccessException, InstantiationException {

        List<ProductDetailData> CartItemsDetail = new ArrayList<ProductDetailData>();
        click(CartDetails());
        List<WebElement> multiCartItems = getWebElements(MultiCartItems());
        for (WebElement CartItem : multiCartItems) {
            ProductDetailData productDetailData = new ProductDetailData();
            Locator SingleCartItem = new Locator(By.xpath(getAbsoluteXPath(CartItem)), "Cart Item");
            Locator CartItemName = SingleCartItem.concatLocator(ExtendedTitleofCartItem());
            Locator CartPrizeName = SingleCartItem.concatLocator(ExtendedPrizeofCartItem());
            Locator CartProductQty = SingleCartItem.concatLocator(ExtendedQuantityOfCartItem());

            productDetailData.setProductName(getText(CartItemName));
            productDetailData.setProductPrize(getText(CartPrizeName).replaceAll("\\D+", ""));
            productDetailData.setProductQuantity(getText(CartProductQty).replaceAll("\\D+", ""));
            CartItemsDetail.add(productDetailData);

        }
        return CartItemsDetail;

    }

    public CheckoutData FetchOrderDetail(CheckoutData checkoutData) throws Throwable, Throwable

    {
        click(Price_Details());
        checkoutData.setSubtotal(getText(subTotalValueview()).replaceAll("\\D+", ""));
        checkoutData.setShipping(getText(ShippingValueView()));
        checkoutData.setOrdertotal(getText(OrderTotalValueView()).replaceAll("\\D+", ""));
        if (waitUntilDisplayed(RewardPointValueView(), 3)) {
            checkoutData.setRewardpoint(getText(RewardPointValueView()));
        }
        if (waitUntilDisplayed(DiscountValueView(), 3)) {
            checkoutData.setCouponcode(getText(DiscountValueView()).replaceAll("-Rs.", ""));
        }

        return checkoutData;

    }

    private boolean isNotificationAdvertisePresent(WebDriver driver2) {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        WebDriverWait ww = new WebDriverWait(driver, 5);
        try

        {
            ww.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(
                    "//iframe[@id='__ta_notif_frame_0' or @id='webklipper-publisher-widget-container-notification-frame']")));
            return true;
        } // try
        catch (Exception Ex) {
            Ex.printStackTrace();
            return false;
        } // catch
        finally {

            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
    }

    public boolean fillNewAddressSubscribe(CheckoutData checkOutData) throws Throwable {
        ClearValue(Fname_Subscription());
        EnterValue(Fname_Subscription(), checkOutData.getFirstname());
        ClearValue(LName_Subscription());
        EnterValue(LName_Subscription(), checkOutData.getLastname());
        ClearValue(Email_Subscription());
        EnterValue(Email_Subscription(), checkOutData.getUsername());
        ClearValue(Saddress_Subscription());
        EnterValue(Saddress_Subscription(), checkOutData.getShipping_address1());
        ClearValue(Pincode_Subscription());
        EnterValue(Pincode_Subscription(), checkOutData.getPostcode());
        ClearValue(Phone_Subscription());
        Thread.sleep(3000);
        EnterValue(Phone_Subscription(), checkOutData.getTelephone());
        click(SubscribeFinalConfirm());
        return waitUntilDisplayed(SubscribeConfirmation(), 10);

    }

}
