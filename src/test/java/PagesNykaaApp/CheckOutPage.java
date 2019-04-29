package PagesNykaaApp;

import FrameWorkNykaa.AppAction;
import FrameWorkNykaa.Locator;
import FrameWorkNykaa.TestListner;

import DataNykaa.CheckoutData;
import FrameWorkNykaa.Framework;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.io.IOException;
import java.util.List;

/**
 * Created by nevil on 6/26/2017.
 */

public class CheckOutPage extends AppAction
{

    public CheckOutPage(AndroidDriver driver){
        this.driver = driver;
    }

    Framework frameWork = new Framework();

    Locator FullName(){
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'dialog_full_name')]"),"Full Name");
    }

    Locator PinCode(){
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'etPincode')]"),"Pincode TextBox");
    }

    Locator AddressLine1(){
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'etAddress')]"),"Address Line 1");
    }

    Locator NewAddress(){
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id, 'address_line_1')]"), "New Address");
    }

    Locator dialog_city(){
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'dialog_city')]"),"City");
    }

    Locator State(){
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'standard_spinner_format')]"),"State");
    }

    Locator ContactNo(){
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'etMobileNo')]"),"Contact No");
    }

    Locator SaveButton(){
        return new Locator(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'llProceed')][1]"),"Save Button");
    }

    Locator ProceedButton(){
        return new Locator(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'llProceedBtn')][2]"),"Proceed To Chekout Button");
    }
    Locator ProceedButton_New(){
        return new Locator(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'llProceedBtn')]"), "Proceed to checkOut");
    }

    Locator MorePaymentMethod(){
        return new Locator(By.id("tvMorePayment"),"More payment methods");
    }

    Locator cancelTransactionPopUp(){
        return new Locator(By.xpath("//android.widget.Button[@text='Ok'] | //android.widget.Button[@text='YES'] | //android.widget.Button[@text='OK'] | //android.widget.Button[@text='TRY AGAIN']"),"Cancel Transaction");
    }

    Locator PlaceOrderButton(){
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'pay_button')]"),"Place Order Button");
    }

    Locator PaySecurely(){
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'checkout_btn')][@text='PAY SECURELY']"),"Pay Securely");
    }

    Locator CardPaymentTab(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'payment_mode_title_tv')][@text='DEBIT/CREDIT CARD']"),"Card Payment Tab");
    }

    Locator NetBankingTab(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'payment_mode_title_tv')][@text='NETBANKING']"),"Net Banking Tab");
    }

    Locator MobikwikPaymentGateway(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'header_text')][@text='Payment Gateway']"),"Mobikwik Payment Gateway");
    }

    Locator WalletsTab(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'psts_tab_title')][@text='Wallets']"),"Wallets");
    }

    Locator CashOnDeliveryTab(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'payment_mode_title_tv')][@text='CASH ON DELIVERY']"),"Cash On Delivery Tab");
    }

    Locator GiftCard(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'psts_tab_title')][@text='GiftCard']"),"Gift Card");
    }

    Locator OrderConfirmationSummaryPage(){
        return new Locator(By.xpath("//android.widget.TextView[@text='Order Confirmed!']"), "Confirmation Page");
    }

    Locator CardNoTextBox(){
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'card_number')]"),"Card Number TextBox");
    }

    Locator MMYYTextBox(){
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'card_expiry')]"),"MMYY TextBox");
    }

    Locator CVVLocator(){
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'Cvv')] | //android.widget.EditText[contains(@resource-id,'edit_cvv')]"),"Card CVV text");
    }

    Locator CardHolderName(){
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'card_holder_nam')]"),"Card Holder Name");
    }

    Locator bankLogoLocator(){
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'axisLayout')] | //android.widget.Image[@text='Bank Logo'] | //android.widget.Image[@content-desc='Bank Logo'] | //android.widget.TextView[contains(@text,'AXIS')]"),"Netbanking Logo");
    }

    Locator OTPpage(){
        return new Locator(By.xpath("//android.view.View[contains(@text,'WWW NYKAA COM IBIBO')]"),"OTP Page");
    }

    Locator MobikwikPaymenetTab(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'walletTV')][contains(@text,'Mobikwik')]"),"Mobikwik tab");
    }

    Locator savedCardPaymentTab(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'psts_tab_title')][@text='SavedCard']"),"Saved Card");
    }

    Locator AxisCustomerId(){
        return new Locator(By.xpath("//android.view.View[contains(@resource-id,'userIdOption')] | //android.webkit.WebView[@text='Axis Bank Internet Banking']"),"Customer ID");
    }

    Locator personalMsg(){
        return new Locator(By.xpath("//android.view.View[contains(@text,'my name is yash')]"),"Personal Msg Check");
    }

    Locator savedCardNumber(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'card_number')]"),"Card Number");
    }

    Locator savedCardList(){
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'SavedCard')]"),"saved card list");
    }

    Locator PayNow(){
        return new Locator(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'ProceedBtn')]"),"Pay Now Button");
    }

    Locator PayNow1(){
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'pay_button')]"), "pay now final");
    }

    Locator savedCardTab(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@text,'Saved')]"), "Saved Card button");
    }

    Locator oKButton(){
        return new Locator(By.xpath("//android.widget.Button[@text='OK']"),"Ok button");
    }

    Locator UPITab(){
        return new Locator(By.xpath("//android.widget.TextView[@text='BHIM UPI']"),"Bhim UPI Tab");
    }

    Locator vpaAddress(){
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'new_vpa_et')]"),"VPA Address");
    }

    Locator upiPaymentRequest(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_approve_request')]"),"UPI payment request");
    }

    Locator bankSelector() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'standard_spinner_format')]"), "");
    }

    Locator axisBankFromDropDown() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@text,'AXIS')]"), "Axis Bank");
    }

    Locator nykaaWallet(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'payment_mode_title_tv')][contains(@text,'NYKAA WALLET')]"), "Nykaa wallet");
    }

    Locator walletMoney(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'amount_label_tv')]"), "Amount in wallet");
    }

    Locator AddressList(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id, 'address_tv')]"), "Saved Address");
    }

    Locator UPICancelButton(){
        return new Locator(By.xpath("//android.widget.Button[contains(@text, 'CANCEL')]"), "Cancel Button");
    }

    Locator NewPincode(){
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id, 'pincode')]"), "Pincode for Subcribe Button");
    }

    Locator MobileNumber(){
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id, 'mobile_number')]"), "Mobile Number");
    }


    /******************************************Services*****************************************************************************/

    public void fillAddress(CheckoutData checkoutData) throws IllegalAccessException, InstantiationException, InterruptedException {
        //EnterValue(FullName(),checkoutData.getFirstname());
        EnterValue(ContactNo(),checkoutData.getTelephone());
        EnterValue(PinCode(),checkoutData.getPostcode());
        bringElementIntoViewDown(AddressLine1(),3);
        EnterValue(AddressLine1(),checkoutData.getShipping_address1());
        //driver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
        click(SaveButton());


    }

    public void fillAddressSubscribe(CheckoutData checkoutData) throws IllegalAccessException, InstantiationException, InterruptedException {
        EnterValue(FullName(),checkoutData.getFirstname());
        // EnterValue(ContactNo(),checkoutData.getTelephone());
        EnterValue(NewPincode(),checkoutData.getPostcode());
        click(NewPincode());
        bringElementIntoViewDown(NewAddress(),3);
        click(NewAddress());
        ScrollDown(2);
        EnterValue(NewAddress(),checkoutData.getShipping_address1());
        bringElementIntoViewDown(MobileNumber(), 1);
        EnterValue(MobileNumber(), checkoutData.getTelephone());
        //driver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
        click(SaveButton());


    }

    public boolean payViaCard(DataNykaa.CheckoutData checkoutData) throws IllegalAccessException, InstantiationException, InterruptedException, IOException {
        try {
            if (waitUntilDisplayed(ContactNo(), 2)) {
                fillAddress(checkoutData);
            }
            if (waitUntilDisplayed(MorePaymentMethod(), 2)) {
                click(MorePaymentMethod());
            }
            if (waitUntilDisplayed(cancelTransactionPopUp(), 3)) {
                click(cancelTransactionPopUp());
            }
            click(CardPaymentTab());
            EnterValue(CardNoTextBox(), checkoutData.getCardno());
            EnterValue(MMYYTextBox(), checkoutData.getExpDate());
            click(MMYYTextBox());
            EnterValue(CVVLocator(), checkoutData.getCcv());

            click(PayNow1());
            return waitUntilDisplayed(OTPpage(), 60);
        }catch (Throwable e){
            TestListner.testing.get().log(LogStatus.FAIL,e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            return false;
        }
    }

    public boolean payViaSavedCard(CheckoutData checkoutData) throws IllegalAccessException, InstantiationException, InterruptedException, IOException {
        try {
            if (waitUntilDisplayed(ProceedButton_New(), 2)) {
                click(ProceedButton_New());
            }
            if (waitUntilDisplayed(cancelTransactionPopUp(), 3)) {
                click(cancelTransactionPopUp());

            }
            //click(savedCardTab());
            List<WebElement> savedCards = getWebElements(savedCardList());
            for (WebElement savedCard : savedCards) {
                Locator savedCardNumber = new Locator(savedCard, By.xpath("//android.widget.TextView[contains(@resource-id,'tvDesc')]"), "Saved Card Number");
                click(savedCardNumber);
                break;
            }
            EnterValue(CVVLocator(), checkoutData.getCcv());
            click(PayNow());

            return waitUntilDisplayed(bankLogoLocator(), 60);
        }catch (Throwable e){
            TestListner.testing.get().log(LogStatus.FAIL,e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            return false;
        }
    }

    public boolean payViaNetbankingTopFive(CheckoutData checkoutData) throws IllegalAccessException, InstantiationException, IOException {
        try {
            if (waitUntilDisplayed(ContactNo(), 2)) {
                fillAddress(checkoutData);
            }
            if (waitUntilDisplayed(MorePaymentMethod(), 2)) {
                click(MorePaymentMethod());
            }
            if (waitUntilDisplayed(cancelTransactionPopUp(), 3)) {
                click(cancelTransactionPopUp());
            }
            if(isDisplayed(ProceedButton_New())){
                click(ProceedButton_New());
            }
            click(NetBankingTab());
            click(bankLogoLocator());
            bringElementIntoViewDown(PayNow1(), 1);
            click(PayNow1());
            ProductPage productPage = new ProductPage(driver);
            if(waitUntilDisplayed(productPage.allowButton(),15)){
                click(productPage.allowButton());
            }
            boolean customerID = waitUntilDisplayed(AxisCustomerId(), 60);
            androidBackButton();
            return customerID;
        }catch (Throwable e){
            TestListner.testing.get().log(LogStatus.FAIL,e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            return false;
        }

    }

    public boolean payViaNetBankingFromDropDown(CheckoutData checkoutData) throws IOException {
        try {
            if (waitUntilDisplayed(ContactNo(), 2)) {
                fillAddress(checkoutData);
            }
            if (waitUntilDisplayed(MorePaymentMethod(), 3)) {
                click(MorePaymentMethod());
            }
            if (waitUntilDisplayed(cancelTransactionPopUp(), 3)) {
                click(cancelTransactionPopUp());
            }
            if(isDisplayed(ProceedButton_New())){
                click(ProceedButton_New());
            }
            click(NetBankingTab());
            click(bankLogoLocator());
            click(axisBankFromDropDown());
            bringElementIntoViewDown(PayNow1(), 1);
            click(PayNow1());
            ProductPage productPage = new ProductPage(driver);
            if (waitUntilDisplayed(productPage.allowButton(), 15)) {
                click(productPage.allowButton());
            }
            return waitUntilDisplayed(AxisCustomerId(), 60);
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL,e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            return false;
        }
    }

    public boolean payViaCOD(CheckoutData checkoutData) throws IllegalAccessException, InstantiationException, IOException {
        try {
            if (waitUntilDisplayed(ContactNo(), 2)) {
                fillAddress(checkoutData);
            }
            if(isDisplayed(AddressList())){
                click(ProceedButton_New());
            }
            if (waitUntilDisplayed(MorePaymentMethod(), 2)) {
                click(MorePaymentMethod());
            }
            if (waitUntilDisplayed(cancelTransactionPopUp(), 3)) {
                click(cancelTransactionPopUp());
            }
            if(isDisplayed(ProceedButton_New())){
                click(ProceedButton_New());
            }
            bringElementIntoViewDown(CashOnDeliveryTab(), 1);
            click(CashOnDeliveryTab());
            click(PayNow1());
            return waitUntilDisplayed(OrderConfirmationSummaryPage(), 60);
        }catch (Throwable e){
            TestListner.testing.get().log(LogStatus.FAIL,e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            return false;
        }
    }

    public boolean payViaWalletsMobikwik(CheckoutData checkoutData) throws IllegalAccessException, InstantiationException, IOException {
        try {
            if (waitUntilDisplayed(ContactNo(), 2)) {
                fillAddress(checkoutData);
            }
            if (waitUntilDisplayed(MorePaymentMethod(), 5)) {
                click(MorePaymentMethod());
            }
            if (waitUntilDisplayed(cancelTransactionPopUp(), 3)) {
                click(cancelTransactionPopUp());
            }
            click(WalletsTab());
            click(MobikwikPaymenetTab());
            click(PayNow1());
            return waitUntilDisplayed(MobikwikPaymentGateway(), 60);
        }catch (Throwable e){
            TestListner.testing.get().log(LogStatus.FAIL,e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            return false;
        }
    }

    public boolean payViaNykaaWallet(CheckoutData checkoutData) throws IllegalAccessException, InstantiationException, InterruptedException, IOException {
        try {
            if (waitUntilDisplayed(nykaaWallet(), 5)) {
                String pricesInString = getText(PayNow());
                String price = pricesInString.split(" ")[1].replaceAll(".*₹([^&]+).*", "$1");
                int x = Integer.parseInt(price);
                String money = getText(walletMoney());
                String money1 = money.replaceAll("[^0-9]", "");
                money1 = money1.trim();
                int y = Integer.parseInt(money1);
                if (y >= x) {
                    click(PayNow());
                    return waitUntilDisplayed(OrderConfirmationSummaryPage(), 60);
                } else {
                    click(PayNow());
                    click(UPITab());
                    EnterValue(vpaAddress(), checkoutData.getUsername());
                    click(PayNow1());
                    return waitUntilDisplayed(upiPaymentRequest(), 60);
                }

            }
            else {
                click(MorePaymentMethod());
                click(nykaaWallet());
            }
            return waitUntilDisplayed(nykaaWallet(), 10);
        }catch (Throwable e){
            TestListner.testing.get().log(LogStatus.FAIL,e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            return false;
        }

    }

    public void payViaGiftCard() throws IllegalAccessException, InstantiationException {
        click(CashOnDeliveryTab());
        click(PlaceOrderButton());
    }

    public boolean isOrderCreated(){
        return waitUntilDisplayed(OrderConfirmationSummaryPage(),10);
    }

    public boolean payViaUPI(CheckoutData checkoutData) throws IllegalAccessException, InstantiationException, IOException {
        try {
            if (waitUntilDisplayed(ContactNo(), 2)) {
                fillAddress(checkoutData);
            }
            if (waitUntilDisplayed(MorePaymentMethod(), 2)) {
                click(MorePaymentMethod());
            }
            if (waitUntilDisplayed(cancelTransactionPopUp(), 3)) {
                click(cancelTransactionPopUp());
            }
            if(isDisplayed(ProceedButton_New())){
                click(ProceedButton_New());
            }
            click(UPITab());
            EnterValue(vpaAddress(), checkoutData.getUsername());
            click(PayNow1());
            Thread.sleep(4000);
            if(isDisplayed(UPICancelButton())){
                click(UPICancelButton());
            }
            return waitUntilDisplayed(upiPaymentRequest(), 60);

        } catch (Throwable e) {

            TestListner.testing.get().log(LogStatus.FAIL,e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            return false;
        }
    }

}
