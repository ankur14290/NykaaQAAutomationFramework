package Mobile_Pages_Nykaa;

import DataNykaa.AccountData;
import DataNykaa.CheckoutData;
import DataNykaa.ProductDetailData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Locator;
import android.app.ActionBar;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CheckOutPage_Mobile_New extends BrowserAction {
    public CheckOutPage_Mobile_New(WebDriver browser) {
        this.driver = browser;
    }

    private Locator DeliveryAddressLocator(String id) {
        return new Locator(By.xpath(
                "//div[text()='Delivery Address']/following-sibling::div[@class='overview']//input["+ id +"]"),
                "Address");
    }

    private Locator DeliveryAddressLocator() {
        return new Locator(By.xpath(
                "//div[@class='address-details']/div[@class='user-address']"),
                "Selected Address");
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

    Locator removeReedemButton() {
        return new Locator(By.xpath("//div[@onclick='removeRewards()']"), "Apply Reedem Button");
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

    Locator POVAppliedFlag() {
        return new Locator(By.xpath("//input[@id='pov_applied'][@value='1']"), "POV Applied");
    }

    private Locator AddressSelectedIcon() {
        return new Locator(By.id("steptwotext"), "Address Selection Icon");
    }

    private Locator Billing_streetNykaaMan() {
        return new Locator(By.xpath("//textarea[@id='address_street']"), "Shiping street In Address");
    }
    
    private Locator Billing_street() {
        return new Locator(By.xpath("//div[@class='input-row']//textarea[@placeholder=\"Address\"]"), "Shiping street In Address");
    }

    private Locator Continue() {
        return new Locator(By.id("isregisteredbtn"), "Continue Button");
    }

    Locator ContinueRegstration() {
        return new Locator(By.id("altcontinuebtn"), "Registration Continue button");
    }

    private Locator submitButton() {
        return new Locator(By.id("btn_add_sa_submit_1"), "Submit button");
    }

    private Locator ContinueAsGuest() {
        return new Locator(By.id("continueasguestbtn"), "Continue as guest Button");
    }

    Locator debitCardOption() {
        return new Locator(By.xpath("//div[@class='payment-method-name'][contains(text(), 'CREDIT/DEBIT')]"), "Debit Card Option");
    }
    Locator debitCardOptionNykaaMan() {
        return new Locator(By.xpath("//div[@id='parentVerticalTab']/div/div/h2[contains(text(),'Credit Card / Debit Card')]/span"), "Debit Card Option");
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
        return new Locator(By.xpath("//div[@class='bnl__otp__get-started']/p"), "Otp Mobile Text");
        // return new Locator(By.xpath("//div[@class='otp__mobile']"), "Otp
        // Mobile textArea");
    }

    Locator iframeOTPsimple() {
        return new Locator(By.xpath("//iframe[@id='i']"), "OTP box iframe");
    }

    Locator DebitCardNoText() {
        return new Locator(By.xpath("//div[@class='inner-wrap']/input[@name='cardNumber']"), "Debit card number");
    }
    
    Locator DebitCardNoTextNykaaMan() {
        return new Locator(By.xpath("//input[@id='credit_card_no']"), "Debit card number");
    }

    public Locator BackButton() {
        return new Locator(By.xpath("//button[@class='back-btn']"), "Back Button");
    }

    Locator DomesticLoader() {
        return new Locator(By.xpath("//*[@class='isDomestic_load_message']/img"), "Loader");
    }

    Locator DebitCardName() {
        return new Locator(By.xpath("//div[@id='credit_options']/input[@id='credit_card_name']"), "Debit card name");
    }

    Locator DebitCardExp_MM() {
        return new Locator(By.xpath("//div[@class='expiry-date']//input[@name='expiryMonth']"), "Debit card expiry month");
    }
    
    Locator DebitCardExpNykaaMan() {
        return new Locator(By.xpath("//input[@id='credit_card_exp']"), "Debit card expiry month");
    }

    Locator DebitCardExp_YY() {
        return new Locator(By.xpath("//div[@class='expiry-date']//input[@name='expiryYear']"),"DEBIT Card Expiry Year");
    }

    Locator CashOnDeliveryMethod() {
        return new Locator(By.xpath("//div[@class='payment-method-name'][text()='CASH ON DELIVERY']"), "Cash On Delivery Method");
    }
    
    Locator CashOnDeliveryMethodNykaaMan() {
        return new Locator(By.xpath("//div[@id='parentVerticalTab']/div/div/h2[contains(text(),'Cash on Delivery')]/span"), "Cash On Delivery Method");
    }

    Locator COD_placeOrderBtn() {
        return new Locator(By.xpath("//div[@class='cod-payment-inner-section']//button[@class='btn payment-btn margin-adjust ']"), "Cod Place Order");
    }
    
    Locator COD_placeOrderBtnNykaaMan() {
        return new Locator(By.xpath("//input[@id='phoenix_cashondelivery_btn']"), "Cod Place Order");
    }

    Locator RewardPointText() {
        return new Locator(By.xpath("//div[@class='media-body content']"),
                "Reward point Viewtext");

    }

    Locator DebitCard_ccvText_Locator() {

        return new Locator(By.xpath("//div[@class='cvv-control']//input[@class='cvv']"), "Debit card cvv");
    }
    
    
    Locator DebitCard_ccvText_LocatorNykaaMan() {

        return new Locator(By.xpath("//input[@id='credit_card_ccv']"), "Debit card cvv");
    }
    
    Locator DebitCard_name_LocatorNykaaMan() {

        return new Locator(By.xpath("//input[@id='credit_card_name']"), "Debit card name ");
    }

    Locator debitCardButn_Locator() {

        return new Locator(By.xpath("//button[@class='btn payment-btn']"), "Debit Card button ");

    }
    

    Locator debitCardButn_LocatorNykaaMan() {

        return new Locator(By.xpath(" //input[@id='creditCardButn']"), "Debit Card button ");

    }
    
 

    Locator ProccedtoNewaddress(){
        return new Locator(By.xpath("//button[@class='btn full fill no-radius proceed big']"),"Ship to This address Button");
    }

    Locator COD_Confirmation() {
        return new Locator(By.xpath("//div[@class='title'][contains(text(),'Order Confirmed')]"),
                "Confirmation Cod Page");
    }
    
    Locator COD_ConfirmationNykaaMan() {
        return new Locator(By.xpath("//div[@class='thanks-div']/h2[contains(text(),'Confirmed')]"),
                "Confirmation Cod Page");
    }
    
  

    Locator checkoutPageLoader() {
        return new Locator(By.xpath("//div[@id='pageblock'][contains(@style,'block')]"), "CheckOut Page Loader");
    }

    Locator checkoutLoader() {
        return new Locator(By.xpath("//span[@id='loading']"), "CheckOut Loader");
    }

    private Locator Checkout_Button() {
        return new Locator(By.xpath("//span[text()='CHECKOUT ']"), "Checkout Button");
    }

    private Locator Close_Button() {
        return new Locator(By.xpath("//div[@class='close']|//a[@class='close']"), "Close Button");
    }

    private Locator Billing_Address() {
        return new Locator(By.id("billing_email"), "Billing Address");
    }

    private Locator Billing_FirstName() {
        return new Locator(By.xpath("//div[@class='form-field single-field  ']//input[@name=\"name\"]"), "Billing Firstname");
    }
    
    
    private Locator Billing_FirstNameNykaaMan() {
        return new Locator(By.xpath("//input[@id='billingfirstname']"), "Billing Firstname");
    }

    private Locator Billing_LastName() {
        return new Locator(By.id("billinglastname"), "Billing Lastname");
    }
    
   

    private Locator Billing_telephone() {
        return new Locator(By.xpath("//div[@class='form-field single-field  ']//input[@name=\"phoneNumber\"]"), "Billing Telephone");

    }
    
    private Locator Billing_telephoneNykaaMan() {
        return new Locator(By.xpath("//input[@id='billing_telephone']"), "Billing Telephone");

    }

    Locator LoaderIcon() {
        return new Locator(By.id("ajax_loading_img"), "Loader Icon");
    }

    private Locator Billing_PostCode() {
        return new Locator(By.xpath("//div[@class='form-field single-field  ']//input[@name=\"pinCode\"]"), "Billing Postcode");
    }
    
    
    private Locator Billing_PostCodeNykaaMan() {
        return new Locator(By.xpath("//input[@id='billing_pincode']"), "Billing Postcode");
    }

    private Locator Billing_Email() {
        return new Locator(By.xpath("//div[@class='form-field single-field  ']//input[@name=\"email\"]"), "Billing Email");
    }
    
    private Locator Billing_EmailNykaaMan() {
        return new Locator(By.xpath("//input[@id='billing_email']"), "Billing Email");
    }

    Locator NetBankingMethod() {
        return new Locator(By.id("payucheckout_shared_options"), "NetBankingMethod");

    }

    Locator NetBankingOption() {
        return new Locator(By.xpath("//div[@class='payment-method-name'][contains(text(), 'NETBANKING')]"), "NetBanking Option");

    }
    
    Locator NetBankingOptionNykaaMan() {
        return new Locator(By.xpath("//div[@id='parentVerticalTab']/div/div/h2[contains(text(),'Net Banking')]/span"), "NetBanking Option");

    }
    
  

    Locator BhimUPI() {
        return new Locator(By.xpath("//div[@class='payment-method-name'][contains(text(), 'BHIM UPI')]"),"BHIM UPI");
    }
    Locator NetBankingDropdown() {
        return new Locator(By.xpath("//ul[@class='prl5 radio-stack']"), "NetBanking Dropdown");

    }
    Locator NetBankingDropdownbutton(){
        return new Locator(By.xpath("//div[@class='other-bank-list-section']/i[@class='others-bank-arrow up-arrow']"),"Other Bank Dropdown");
    }

    Locator proceedToNetBanking() {

        return new Locator(By.xpath("//button[@class='btn payment-btn ']"), "Pay Now");
    }
    
    Locator proceedToNetBankingNykaaMan() {

        return new Locator(By.xpath("//input[@id='proceedNetBankingBut']"), "Pay Now");
    }
  
    Locator ProceedToPay() {

        return new Locator(By.xpath("//div[@class='payment-method-name'][contains(text(), 'NETBANKING')]/ancestor::div//button[@class='btn payment-btn ']"), "Proceed To Pay");
    }
    
    Locator ProceedToPayNykaaMan() {

        return new Locator(By.xpath("//a[contains(text(),'Proceed To pay')]"), "Proceed To Pay");
    }

    Locator ProceedToPay_New(){
        return new Locator(By.xpath("//button[@class='btn full fill no-radius proceed']"),"Proceed To Pay Button");
    }

    Locator MorePaymentMethods() {
        return new Locator(By.xpath("//div[@class='payment-method-name'][text()='MORE PAYMENT METHODS']"),"More Payement OPtions");
    }

    Locator Payoumoney_Methode() {
        return new Locator(By.id("method_payumoney"), "Payyoumoney Method");
    }

    Locator PayuMoneProceedToPayment() {

        return new Locator(By.xpath(
                "//div[@id='payumoney_options']/descendant::div[@class='payment_button']/input[@value='Proceed To Payment ']"),
                "Proceed To Payment");
    }

    Locator WalletProceedToPayment() {

        return new Locator(By.xpath("//div[@class='payment-method-name'][contains(text(), 'MOBILE WALLETS')]/ancestor::div//button[@class='btn payment-btn margin-adjust']"), "proceed To Pay");
    }
    
    Locator WalletProceedToPaymentNykaaMan() {

        return new Locator(By.xpath("//input[@id='wallets_btn']"), "proceed To Pay");
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
        return new Locator(By.xpath("//div[@id='simpl-backdrop-text'][contains(text(),'Simpl browser window ')]"),
                "simple browser alert");
    }

    Locator PayoumoneyIcon() {

        return new Locator(By.xpath("//div[@class='pymnt_main']/descendant::a/img[@title='PayUMoney Payment']"),
                "Payoumoney Icon");
    }

    Locator WalletMethod() {

        return new Locator(By.xpath("//div[@class='payment-method-name'][contains(text(), 'MOBILE WALLETS')]"), "Wallet Method");
    }
    
    Locator WalletMethodNykaaMan() {

        return new Locator(By.xpath("//div[@id='parentVerticalTab']/div/div/h2[contains(text(),'Wallets ')]/span"), "Wallet Method");
    }

    Locator PayTMradioButton() {
        return new Locator(By.xpath("//div[@class='wallet-name'][contains(text(),'Paytm')]"), "PayTm Radio Button");
    }

    Locator PayTMradioButtonNykaaMan() {
        return new Locator(By.xpath("//input[@id='paytm_radion_btn']"), "PayTm Radio Button");
    }

    Locator mobikwikRadioButton() {
        return new Locator(By.id("mobikwik_radion_btn"), "Mobikwik radio button");
    }

    Locator PaytmConfirmation() {
        return new Locator(By.id("//div[@class='inner-wrap']/input[@name='phoneNumber']"), "Paytm Confirmation Page");
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
        return new Locator(By.xpath("//div[text()='Sub Total']/following-sibling::div"), "subtotal value");
    }
    
    Locator subTotalValueviewNykaaMan() {
        return new Locator(By.xpath("//div[text()='Subtotal:']/following-sibling::div"), "subtotal value");
    }

    Locator PNBNetbanking() {
        return new Locator(By.xpath("//ul[@class='prl5 radio-stack']//span[contains(text(),'Bank of Maharashtra')]"),"BOM Radio Button From Other Bank Options in NetBanking");
    }
    
    
    Locator HDFCNetbankingNykaaMan() {
        return new Locator(By.xpath("//div[@id='payucheckout_shared_options']/li"),"HDFC in NetBanking");
    }
 
    Locator ShippingValueView() {
        return new Locator(By.xpath("//div[contains(text(),'Shipping ')]/following-sibling::div"), "Shipping Value");
    }

    Locator RewardPointValueView() {

        return new Locator(By.xpath("//div[@id='totals_div']/descendant::div[@class='no-dis']"),
                "Reward point value view");

    }

    Locator OrderTotalValueView() {
        return new Locator(By.xpath("//div[contains(text(),'Grand Total')]/following-sibling::div"), "Order Total value");
    }

    Locator MultiCartItems() {
        return new Locator(By.xpath("//div[@class=\"media card-product remove-margin\"]"), "Cart Items");

    }
    
    
    Locator MultiCartItemsNykaaMan() {
        return new Locator(By.xpath("//form[@id='mobile-checkout-item']//div[@class='content_wrapper']"), "Cart Items");

    }

    Locator ExtendedTitleofCartItem() {
        return new Locator(By.xpath("//div[@class='product-name']"), " name Of Product");
    }
    
    Locator ExtendedTitleofCartItemNykaaMan() {
        return new Locator(By.xpath("//div[@class='head_text']"), " name Of Product");
    }

    Locator ExtendedPrizeofCartItem() {

        return new Locator(By.xpath("/ancestor::div//div[@class='float-right text-right']//span[@class='actual-price']"), "prize of Cart Item");
    }
    
    Locator ExtendedPrizeofCartItemNykaaMan() {

        return new Locator(By.xpath("//div[@class='right_amt']"), "prize of Cart Item");
    }

    Locator ExtendedQuantityOfCartItem() {
        return new Locator(By.xpath("/ancestor::div//div[contains(@class,'qty')]"), "Extended Quatinty");
    }
    
    Locator ExtendedQuantityOfCartItemNykaaMan() {
        return new Locator(By.xpath("//select//option[1]"), "Extended Quatinty");
    }
  
    Locator BackButton_Checkout() {
        return new Locator(By.xpath("//i[@class='back-arrow']"),"Back button of Checkoutcart");
    }

    Locator Shiptothisaddress() {
        return new Locator(By.xpath("//button[@class='btn full fill no-radius proceed big']"),"Ship to this address Button");
    }

    Locator UPIId() {
        return new Locator(By.xpath("//div[@class='upi-section payment-accordion open']//input[@class='input-element  undefined']"),"UPIId");
    }

    Locator UPIPaymentButton() {
        return new Locator(By.xpath("//div[@class='upi-section payment-accordion open']//button[@class='btn payment-btn']"),"UPI Payment Button");
    }

    Locator closeUPIwindow() {
        return new Locator(By.xpath("//div[@class='server-error-popup']//button[text()='Close']"),"Close UPI Window");
    }

    Locator shiptoThisAddress(){
        return new Locator(By.xpath("//button[contains(text(),'SHIP TO THIS ADDRESS')]"), "Ship to this address Button");
    }

    public void fillBillingAddress(CheckoutData checkOutData) throws Throwable {
        EnterValue(Billing_FirstName(), checkOutData.getFirstname());
        EnterValue(Billing_Email(), checkOutData.getUsername());
        EnterValue(Billing_telephone(), checkOutData.getTelephone());
        EnterValue(Billing_PostCode(), checkOutData.getPostcode());
        Thread.sleep(3000);
        EnterValue(Billing_street(), checkOutData.getShipping_address1());
    }
    
    public void fillBillingAddressNykaaMan(CheckoutData checkOutData) throws Throwable {
        EnterValue(Billing_FirstNameNykaaMan(), checkOutData.getFirstname());
        EnterValue(Billing_LastName(), checkOutData.getFirstname());
        EnterValue(Billing_EmailNykaaMan(), checkOutData.getUsername());
        EnterValue(Billing_telephoneNykaaMan(), checkOutData.getTelephone());
        EnterValue(Billing_PostCodeNykaaMan(), checkOutData.getPostcode());
        Thread.sleep(3000);
        EnterValue(Billing_streetNykaaMan(), checkOutData.getShipping_address1());
    }

    public void shiptothisaddress() throws InterruptedException, IllegalAccessException, InstantiationException {
        if(isDisplayed(Shiptothisaddress())){
            click(Shiptothisaddress());
        }
        Thread.sleep(3000);
    }

    public void checkOut() throws InterruptedException, InstantiationException, IllegalAccessException {
        if (isNotificationAdvertisePresent(driver)) {
            click(Close_Button());
            driver.switchTo().defaultContent();
        }
        Thread.sleep(2000);
        click(Checkout_Button());

    }

    public void savedAddressSelect() throws Throwable, IllegalAccessException {
        // waitUntilPageBlockDisappear(driver);
        bringElementIntoView(DeliveryAddressLocator("1"));
        Thread.sleep(1000);
        click(DeliveryAddressLocator("1"));
    }

    public void proceedToPay() throws Throwable, IllegalAccessException {
        click(ProceedToPay());
    }

    public boolean isAddressSelected() throws Throwable {
        waitUntilDisplayed(DeliveryAddressLocator(), 3);
        bringElementIntoView(DeliveryAddressLocator());
        System.out.println(getAttribute(DeliveryAddressLocator(), "selected"));
        return (getAttribute(DeliveryAddressLocator(), "selected").equals("true"));
    }

    public boolean savedAddressPresent() throws IllegalArgumentException, IllegalAccessException {
        try {
            return driver.findElement(DeliveryAddressLocator().getBy()).isDisplayed();
        }
        catch(Exception e)
        {
            return false;
        }

    }

  /*  public boolean applyRewardPoint() throws Throwable {
        try {
            String ButtonName = getText(ApplyReedemButton());
            Locator Applybutton = ApplyReedemButton().concatLocator(ApplyRemoveButtonofRewardPoint());
            if(ButtonName.contains("Remove"))
            {
                click(Applybutton);
            }
            click(ApplyReedemButton());
            Wait(2);
            waitUntilDisappear(checkoutLoader());
            Locator RemoveButton = ApplyReedemButton().concatLocator(ApplyRemoveButtonofRewardPoint());
            waitUntilDisplayed(RemoveButton, 5);
            return driver.findElement(RemoveButton.getBy()).isDisplayed();
        }catch (Exception e){
            System.out.println("No Reward Point applicable to user");
            return true;
        }
    }*/

    public boolean isRewardPointApplied() throws IllegalArgumentException, IllegalAccessException {
        // waitUntilDisplayed(RewardPointText(), 10);
        String rewardPointText = getText(RewardPointText());
        System.out.println(rewardPointText);
        return rewardPointText.contains("Redeemed");

    }

    public void checkoutWithDebitCardNykaaMan(CheckoutData checkoutData) throws Throwable {
    	Thread.sleep(3000); 
    	
    	 if(isDisplayed(ProceedToPayNykaaMan())) {
             bringElementIntoView(ProceedToPayNykaaMan());
             click(ProceedToPayNykaaMan());
         }
         if(isDisplayed(MorePaymentMethods())){
             click(MorePaymentMethods());
         }
    	
    	
       /* if(isDisplayed(MorePaymentMethods())){
            click(MorePaymentMethods());
        }*/
        
       
        String Expdate = checkoutData.getExpDate();
        waitUntilDisplayed(debitCardOptionNykaaMan(), 10);
        if (!isDisplayed(DebitCardNoTextNykaaMan())) {
            click(debitCardOption());
        }
        waitUntilDisplayed(DebitCardNoTextNykaaMan(), 2);
        EnterValue(DebitCardNoTextNykaaMan(), checkoutData.getCardno());
        waitUntilDisappear(DomesticLoader());
        Thread.sleep(2000);
        if (!getAttribute(DebitCardNoTextNykaaMan(), "value").replace(" ", "").equals(checkoutData.getCardno())) {
            click(DebitCardNoTextNykaaMan());
            ClearValue(DebitCardNoTextNykaaMan());
            EnterValue(DebitCardNoTextNykaaMan(), checkoutData.getCardno());
            waitUntilDisappear(DomesticLoader());
            Thread.sleep(2000);
        }
       // click(DebitCardName());
       // EnterValue(DebitCardName(), checkoutData.getCardname());

      WebElement webElement = driver.findElement(DebitCardNoTextNykaaMan().getBy());
        webElement.sendKeys(Keys.TAB);
        EnterValue(DebitCard_name_LocatorNykaaMan(), checkoutData.getCardname());
        EnterValue(DebitCardExpNykaaMan(), Expdate);
       
        EnterValue(DebitCard_ccvText_LocatorNykaaMan(), checkoutData.getCcv());
        click(debitCardButn_LocatorNykaaMan());
        
        
        Thread.sleep(5000);
        waitUntilDisappear(checkoutLoader());
    }

    
    
    public void checkoutWithDebitCard(CheckoutData checkoutData) throws Throwable {
        if(isDisplayed(ProceedToPay())) {
            bringElementIntoView(ProceedToPay());
            click(ProceedToPay());
        }
        if(isDisplayed(shiptoThisAddress())){
            click(shiptoThisAddress());
        }
        if(isDisplayed(MorePaymentMethods())){
            click(MorePaymentMethods());
        }
        String Expdate = checkoutData.getExpDate();
        String ArrExpdate[] = Expdate.split("/");
        waitUntilDisplayed(debitCardOption(), 10);
        if (!isDisplayed(DebitCardNoText())) {
            click(debitCardOption());
        }
        waitUntilDisplayed(DebitCardNoText(), 2);
        EnterValue(DebitCardNoText(), checkoutData.getCardno());
        waitUntilDisappear(DomesticLoader());
        Thread.sleep(2000);
        if (!getAttribute(DebitCardNoText(), "value").replace(" ", "").equals(checkoutData.getCardno())) {
            click(DebitCardNoText());
            ClearValue(DebitCardNoText());
            EnterValue(DebitCardNoText(), checkoutData.getCardno());
            waitUntilDisappear(DomesticLoader());
            Thread.sleep(2000);
        }
       // click(DebitCardName());
       // EnterValue(DebitCardName(), checkoutData.getCardname());

      WebElement webElement = driver.findElement(DebitCardNoTextNykaaMan().getBy());
        webElement.sendKeys(Keys.TAB);
        EnterValue(DebitCardExp_MM(), ArrExpdate[0]);
        EnterValue(DebitCardExp_YY(), ArrExpdate[1]);
        EnterValue(DebitCard_ccvText_Locator(), checkoutData.getCcv());
        click(debitCardButn_Locator());
        
        
        Thread.sleep(2000);
        waitUntilDisappear(checkoutLoader());
    }

    public void checkoutWithBhim(CheckoutData checkoutData) throws Throwable {
        if(isDisplayed(ProceedToPay_New())) {
            bringElementIntoView(ProceedToPay_New());
            click(ProceedToPay_New());
        }
        if(isDisplayed(MorePaymentMethods())){
            click(MorePaymentMethods());
        }
        if(isDisplayed(shiptoThisAddress())){
            click(shiptoThisAddress());
        }
        click(BhimUPI());
        waitUntilDisappear(checkoutPageLoader());
        String upiid = checkoutData.getTelephone().concat("@upi");
        EnterValue(UPIId(), upiid);
        click(UPIPaymentButton());
        Thread.sleep(3000);
        click(closeUPIwindow());
    }
    
    
    public void checkoutWithBhimNykaaMan(CheckoutData checkoutData) throws Throwable {
        if(isDisplayed(ProceedToPayNykaaMan())) {
            bringElementIntoView(ProceedToPayNykaaMan());
            click(ProceedToPayNykaaMan());
        }
        if(isDisplayed(MorePaymentMethods())){
            click(MorePaymentMethods());
        }
        click(BhimUPI());
        waitUntilDisappear(checkoutPageLoader());
        String upiid = checkoutData.getTelephone().concat("@upi");
        EnterValue(UPIId(), upiid);
        click(UPIPaymentButton());
        Thread.sleep(3000);
        click(closeUPIwindow());
    }


    public boolean applyPOV() throws Exception, Throwable {
        click(PartialPaymentCheckbox());
        return waitUntilPresent(POVAppliedFlag(), 5);
    }

    public boolean CashOnDelivery() throws Throwable {

        if(isDisplayed(ProceedToPay_New())) {
            bringElementIntoView(ProceedToPay_New());
            click(ProceedToPay_New());
        }
        if(isDisplayed(shiptoThisAddress())){
            click(shiptoThisAddress());
        }
        if(isDisplayed(MorePaymentMethods())){
            click(MorePaymentMethods());
        }
        waitUntilDisplayed(CashOnDeliveryMethod(), 60);
        click(CashOnDeliveryMethod());
        if(!waitUntilDisplayed(CashOnDeliveryMethod(), 5))
        {
            click(CashOnDeliveryMethod());
            waitUntilDisplayed(COD_placeOrderBtn(), 5);
        }
        Thread.sleep(1000);
        click(COD_placeOrderBtn());
        waitUntilDisappear(checkoutLoader());
        return waitUntilDisplayed(COD_Confirmation(), 60);

    }
    
    
    public boolean CashOnDeliveryNykaaMan() throws Throwable {

    	
    	 if(isDisplayed(ProceedToPayNykaaMan())) {
             bringElementIntoView(ProceedToPayNykaaMan());
             click(ProceedToPayNykaaMan());
         }
        if(isDisplayed(MorePaymentMethods())){
            click(MorePaymentMethods());
        }
        if(isDisplayed(ProceedToPayNykaaMan())) {
            bringElementIntoView(ProceedToPayNykaaMan());
            click(ProceedToPayNykaaMan());
        }
        waitUntilDisplayed(CashOnDeliveryMethodNykaaMan(), 60);
        click(CashOnDeliveryMethodNykaaMan());
        if(!waitUntilDisplayed(COD_placeOrderBtnNykaaMan(), 5))
        {
            click(CashOnDeliveryMethodNykaaMan());
            waitUntilDisplayed(COD_placeOrderBtnNykaaMan(), 5);
        }
        Thread.sleep(1000);
        click(COD_placeOrderBtnNykaaMan());
        waitUntilDisappear(checkoutLoader());
        return waitUntilDisplayed(COD_ConfirmationNykaaMan(), 60);

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
        if(isDisplayed(ProceedToPay_New())) {
            bringElementIntoView(ProceedToPay_New());
            click(ProceedToPay_New());
        }
        if(isDisplayed(shiptoThisAddress())){
            click(shiptoThisAddress());
        }
        if(isDisplayed(MorePaymentMethods())){
            click(MorePaymentMethods());
        }
        if (isPresent(DebitCardNoText())) {
            click(debitCardOption());
        }
        Thread.sleep(2000);
        waitUntilDisplayed(NetBankingOption(), 60);
        click(NetBankingOption());
        click(NetBankingDropdownbutton());
        Thread.sleep(1000);
        // waitUntilDisappear(DomesticLoader());
        waitUntilDisplayed(NetBankingDropdown(), 5);
        Thread.sleep(1000);
        click(PNBNetbanking());
       // bringElementIntoView(NetBankingDropdown());
        //selectFromDropDownByValue(NetBankingDropdown(), "IDBI");
        waitUntilDisappear(DomesticLoader());
        Thread.sleep(2000);
        waitUntilDisplayed(proceedToNetBanking(), 5);
        click(proceedToNetBanking());
        waitUntilDisappear(checkoutLoader());
        BankingPage_Mobile bankingPage = new BankingPage_Mobile(driver);
        //SwitchToFrameIFAvaialble(bankingPage.HDFCFrame());
        boolean netbankLoginavailable = waitUntilDisplayed(bankingPage.NetBankingCustomerLogin(), 60);
//        switchToDefaultContent();
        return netbankLoginavailable;

    }

    
    public boolean netBankingCheckoutNykaaMan() throws Throwable {
    	if(isDisplayed(ProceedToPayNykaaMan())) {
            bringElementIntoView(ProceedToPayNykaaMan());
            click(ProceedToPayNykaaMan());
        }
       if(isDisplayed(MorePaymentMethods())){
           click(MorePaymentMethods());
       }
       if(isDisplayed(ProceedToPayNykaaMan())) {
           bringElementIntoView(ProceedToPayNykaaMan());
           click(ProceedToPayNykaaMan());
       }
      
        Thread.sleep(2000);
        waitUntilDisplayed(NetBankingOptionNykaaMan(), 60);
        click(NetBankingOptionNykaaMan());
        Thread.sleep(2000);
        click(HDFCNetbankingNykaaMan());
        waitUntilDisappear(DomesticLoader());
        Thread.sleep(2000);
        waitUntilDisplayed(proceedToNetBankingNykaaMan(), 5);
        click(proceedToNetBankingNykaaMan());
        waitUntilDisappear(checkoutLoader());
        BankingPage_Mobile bankingPage = new BankingPage_Mobile(driver);
        Thread.sleep(3000);
        driver.switchTo().frame("bottom_frame");
        boolean netbankLoginavailable = waitUntilPresent(bankingPage.NetBankingCustomerLoginNykaaMan(), 60);
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

    public boolean PaytmWalletCheckoutNykaaMan() throws Throwable {
        if(isDisplayed(ProceedToPayNykaaMan())) {
            bringElementIntoView(ProceedToPayNykaaMan());
            click(ProceedToPayNykaaMan());
        }
        if(isDisplayed(MorePaymentMethods())){
            click(MorePaymentMethods());
        }
        waitUntilDisplayed(WalletMethodNykaaMan(), 60);
        bringElementIntoView(WalletMethodNykaaMan());
        ScrollDown("50");
        waitUntilDisappear(DomesticLoader());
        Thread.sleep(2000);
        click(WalletMethodNykaaMan());
        waitUntilDisappear(DomesticLoader());
        Thread.sleep(2000);
        waitUntilDisplayed(PayTMradioButtonNykaaMan(), 60);
        click(PayTMradioButtonNykaaMan());
        click(WalletProceedToPaymentNykaaMan());
//        waitUntilDisappear(checkoutLoader());
        return true;
		/*
		 * boolean frameAvailable = SwitchToFrameIFAvaialble(PayTMLoginForm());
		 * switchToDefaultContent(); return frameAvailable;
		 */

    }
    
    
    public boolean PaytmWalletCheckout() throws Throwable {
        if(isDisplayed(ProceedToPayNykaaMan())) {
            bringElementIntoView(ProceedToPayNykaaMan());
            click(ProceedToPayNykaaMan());
        }
        if(isDisplayed(shiptoThisAddress())){
            click(shiptoThisAddress());
        }
        if(isDisplayed(MorePaymentMethods())){
            click(MorePaymentMethods());
        }
        waitUntilDisplayed(WalletMethod(), 60);
        bringElementIntoView(WalletMethod());
        ScrollDown("50");
        waitUntilDisappear(DomesticLoader());
        Thread.sleep(2000);
        click(WalletMethod());
        waitUntilDisappear(DomesticLoader());
        Thread.sleep(2000);
        waitUntilDisplayed(PayTMradioButton(), 60);
        click(PayTMradioButton());
        click(WalletProceedToPayment());
//        waitUntilDisappear(checkoutLoader());
        return true;
		/*
		 * boolean frameAvailable = SwitchToFrameIFAvaialble(PayTMLoginForm());
		 * switchToDefaultContent(); return frameAvailable;
		 */

    }

    public void fillbillingEmailIDAndContinueAsGuest(CheckoutData checkoutData) throws Throwable, Throwable {
        EnterValue(Billing_Address(), checkoutData.getUsername());
        if (!isDisappear(Continue(), 3)) {
            click(Continue());
            waitUntilDisappear(checkoutPageLoader());
        }
        click(ContinueAsGuest());
        waitUntilDisappear(checkoutPageLoader());
    }

    public boolean fillNewAddressLoginUser(CheckoutData checkOutData) throws Throwable {
        EnterValue(Billing_FirstName(), checkOutData.getFirstname());
        //EnterValue(Billing_LastName(), checkOutData.getLastname());
        EnterValue(Billing_telephone(), checkOutData.getTelephone());
        EnterValue(Billing_PostCode(), checkOutData.getPostcode());
        Thread.sleep(3000);
        EnterValue(Billing_street(), checkOutData.getShipping_address1());
        click(ProccedtoNewaddress());
        waitUntilDisappear(checkoutPageLoader());
        return waitUntilDisplayed(NetBankingOption(), 10);
    }

    public boolean fillNewAddressGuestNykaaMan(CheckoutData checkOutData) throws Throwable {
		/*
		 * EnterValue(Billing_Address(), checkOutData.getUsername()); click(Continue());
		 * click(ContinueAsGuest()); waitUntilDisappear(checkoutPageLoader());
		 */
        EnterValue(Billing_FirstNameNykaaMan(), checkOutData.getFirstname());
        EnterValue(Billing_LastName(), checkOutData.getLastname());
        EnterValue(Billing_EmailNykaaMan(), checkOutData.getUsername());
        EnterValue(Billing_telephoneNykaaMan(), checkOutData.getTelephone());
        EnterValue(Billing_PostCodeNykaaMan(), checkOutData.getPostcode());
        Thread.sleep(3000);
        EnterValue(Billing_streetNykaaMan(), checkOutData.getShipping_address1());
        waitUntilDisappear(checkoutPageLoader());
        //click(Shiptothisaddress());
        return waitUntilDisplayed(DebitCardNoTextNykaaMan(), 10);

    }
    
    
    public boolean fillNewAddressGuest(CheckoutData checkOutData) throws Throwable {
		/*
		 * EnterValue(Billing_Address(), checkOutData.getUsername()); click(Continue());
		 * click(ContinueAsGuest()); waitUntilDisappear(checkoutPageLoader());
		 */
        EnterValue(Billing_FirstName(), checkOutData.getFirstname());
        EnterValue(Billing_Email(), checkOutData.getUsername());
        EnterValue(Billing_telephone(), checkOutData.getTelephone());
        EnterValue(Billing_PostCode(), checkOutData.getPostcode());
        Thread.sleep(3000);
        EnterValue(Billing_street(), checkOutData.getShipping_address1());
        waitUntilDisappear(checkoutPageLoader());
        click(Shiptothisaddress());
        return waitUntilDisplayed(DebitCardNoText(), 10);

    }

    public List<ProductDetailData> FetchProductDetail() throws IllegalArgumentException, IllegalAccessException {

        List<ProductDetailData> CartItemsDetail = new ArrayList<ProductDetailData>();
        List<WebElement> multiCartItems = getWebElements(MultiCartItems());
        for (WebElement CartItem : multiCartItems) {
            ProductDetailData productDetailData = new ProductDetailData();
            Locator SingleCartItem = new Locator(By.xpath(getAbsoluteXPath(CartItem)), "Cart Item");
            Locator CartItemName = SingleCartItem.concatLocator(ExtendedTitleofCartItem());
            Locator CartPrizeName = SingleCartItem.concatLocator(ExtendedPrizeofCartItem());
            Locator CartProductQty = SingleCartItem.concatLocator(ExtendedQuantityOfCartItem());
            String itemNameTemp = getText(CartItemName);
            String itemName = itemNameTemp.split(" -")[0];
            productDetailData.setProductName(itemName);
            productDetailData.setProductPrize(getText(CartPrizeName).replaceAll("\\D+", ""));
            productDetailData.setProductQuantity(getText(CartProductQty).replaceAll("\\D+", ""));
            CartItemsDetail.add(productDetailData);

        }
        return CartItemsDetail;
    }

    
    public List<ProductDetailData> FetchProductDetailNykaaMan() throws IllegalArgumentException, IllegalAccessException {

        List<ProductDetailData> CartItemsDetail = new ArrayList<ProductDetailData>();
        List<WebElement> multiCartItems = getWebElements(MultiCartItemsNykaaMan());
        for (WebElement CartItem : multiCartItems) {
            ProductDetailData productDetailData = new ProductDetailData();
            Locator SingleCartItem = new Locator(By.xpath(getAbsoluteXPath(CartItem)), "Cart Item");
            Locator CartItemName = SingleCartItem.concatLocator(ExtendedTitleofCartItemNykaaMan());
            Locator CartPrizeName = SingleCartItem.concatLocator(ExtendedPrizeofCartItemNykaaMan());
            Locator CartProductQty = SingleCartItem.concatLocator(ExtendedQuantityOfCartItemNykaaMan());
            String itemNameTemp = getText(CartItemName);
            String itemName = itemNameTemp.split(" -")[0];
            productDetailData.setProductName(itemName);
            productDetailData.setProductPrize(getText(CartPrizeName).replaceAll("\\D+", ""));
            productDetailData.setProductQuantity(getText(CartProductQty).replaceAll("\\D+", ""));
            CartItemsDetail.add(productDetailData);

        }
        return CartItemsDetail;
    }
    public CheckoutData FetchOrderDetailNykaaMan(CheckoutData checkoutData) throws Throwable, Throwable

    {

        checkoutData.setSubtotal(getText(subTotalValueviewNykaaMan()).replaceAll("\\D+", ""));
        checkoutData.setShipping(getText(ShippingValueView()));
        checkoutData.setOrdertotal(getText(OrderTotalValueView()).replaceAll("\\D+", ""));
        if (waitUntilDisplayed(RewardPointValueView(), 3)) {
            checkoutData.setRewardpoint(getText(RewardPointValueView()));
        }
        return checkoutData;

    }
    
    public CheckoutData FetchOrderDetail(CheckoutData checkoutData) throws Throwable, Throwable

    {

        checkoutData.setSubtotal(getText(subTotalValueview()).replaceAll("\\D+", ""));
        checkoutData.setShipping(getText(ShippingValueView()));
        checkoutData.setOrdertotal(getText(OrderTotalValueView()).replaceAll("\\D+", ""));
        if (waitUntilDisplayed(RewardPointValueView(), 3)) {
            checkoutData.setRewardpoint(getText(RewardPointValueView()));
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

    public void gotoHome(){
        try{
            if(waitUntilDisplayed(BackButton_Checkout(),5)) {
                click(BackButton_Checkout());
            }
        }catch (Throwable e){

        }
    }

    public void Goback() {
        try{
            if(waitUntilDisplayed(BackButton_Checkout(),5)) {
                click(BackButton_Checkout());
            }
        }catch (Throwable e){

        }
    }

public Boolean CheckSubTotalValueGreaterThan500() throws IllegalArgumentException, IllegalAccessException
{
String subVal = getText(subTotalValueviewNykaaMan()).replaceAll("\\D+", "");
System.out.println("sub value is :   " + subVal);
int subvalue = Integer.parseInt(subVal);
if(subvalue<500)
{
	return false;
}
else
{
	return true;
}
}


public void IncreaseCartSubValueMoreThan500() throws InterruptedException
{
	Thread.sleep(5000);
	Select dropdown = new Select(driver.findElement(By.xpath("//form[@id='mobile-checkout-item']/div[@class='content_wrapper'][1]//select")));
	dropdown.selectByValue("5");
	Thread.sleep(5000);
	try
	{
		Thread.sleep(5000);
		Select dropdown2 = new Select(driver.findElement(By.xpath("//form[@id='mobile-checkout-item']/div[@class='content_wrapper'][2]//select")));
		dropdown2.selectByValue("5");
		Thread.sleep(5000);
		
	}
	catch(Exception e)
	{
		
	}
	
	Thread.sleep(5000);
}
}
