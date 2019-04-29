package PagesNykaa_NykaaMan;

import FrameWorkNykaa.AppAction;
import FrameWorkNykaa.Framework;
import FrameWorkNykaa.Locator;
import FrameWorkNykaa.TestListner;
import DataNykaa.CheckoutData;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.io.IOException;
import java.util.List;

/**
 * Created by nevil on 6/26/2017.
 */


public class CheckOutPage extends AppAction {

    public CheckOutPage(AndroidDriver driver) {
        this.driver = driver;
    }

    Framework frameWork = new Framework();

    Locator FullName() {
        //  return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'dialog_full_name')]"),"Full Name");
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'etUserName')]"), "Full Name");
    }

    Locator PinCode() {
        //return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'dialog_pincode')]"),"Pincode TextBox");
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'etPincode')]"), "Pincode TextBox");
    }

    Locator AddressLine1() {
        // return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'dialog_address_line_1')]"),"Address Line 1");
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'etAddress')]"), "Address Line 1");
    }

    Locator dialog_city() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'dialog_city')]"), "City");
    }

    Locator State() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'standard_spinner_format')]"), "State");
    }

    Locator ContactNo() {
        //return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'dialog_mobile_number')]"),"Contact No");
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'etMobileNo')]"), "Contact No");
    }

    Locator SaveButton() {
        //return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'dialog_submit')]"),"Save Button");
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvDone')]"), "Save Button");
    }

    Locator ProceedButton() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'checkout_btn')][@text='PROCEED']"), "Proceed To Chekout Button");
    }

    Locator cancelTransactionPopUp() {
        return new Locator(By.xpath("//android.widget.Button[@text='Ok'] | //android.widget.Button[@text='YES']"), "Cancel Transaction");
    }

    Locator PlaceOrderButton() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'checkout_btn')][@text='PLACE ORDER']"), "Place Order Button");
    }

    Locator PaySecurely() {
        //return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'checkout_btn')][@text='PAY SECURELY']"),"Pay Securely");
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'pay_button')]"), "Pay Securely");
    }

    Locator CardPaymentTab() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'psts_tab_title')][@text='Card\n" +
                "Payment']"), "Card Payment Tab");
    }

    Locator NetBankingTab() {
        /*return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'psts_tab_title')][@text='Net\n" +
                "Banking']"),"Net Banking Tab");*/
        return new Locator(By.xpath("//android.widget.TextView[@text='NETBANKING']"), "Net Banking Tab");
    }

    Locator MobikwikPaymentGateway() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'header_text')][@text='Payment Gateway']"), "Mobikwik Payment Gateway");
    }

    Locator WalletsTab() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'psts_tab_title')][@text='Wallets']"), "Wallets");
    }

    Locator CashOnDeliveryTab() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'psts_tab_title')][@text='Cash On\n" +
                "Delivery']"), "Cash On Delivery Tab");
    }

    Locator GiftCard() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'psts_tab_title')][@text='GiftCard']"), "Gift Card");
    }

    Locator OrderConfirmationSummaryPage() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'page_summary')]"), "Confirmation Page");
    }

    Locator CardNoTextBox() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'card_number')]"), "Card Number TextBox");
    }

    Locator MMYYTextBox() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'card_expiry')]"), "MMYY TextBox");
    }

    Locator CVVLocator() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'card_cvv')] | //android.widget.EditText[contains(@resource-id,'edit_cvv')]"), "Card CVV text");
    }

    Locator CardHolderName() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'card_holder_nam')]"), "Card Holder Name");
    }

    Locator bankLogoLocator() {
        //return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'axisLayout')] | //android.widget.Image[@text='Bank Logo'] | //android.widget.Image[@content-desc='Bank Logo'] | //android.widget.TextView[contains(@text,'AXIS')]"),"Netbanking Logo");
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'axisLayout')] | //android.widget.Image[@text='Bank Logo'] | //android.widget.Image[@content-desc='Bank Logo'] | //android.widget.TextView[contains(@text,'AXIS')]"), "Netbanking Logo");
    }

    Locator MobikwikPaymenetTab() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'walletTV')][contains(@text,'Mobikwik')]"), "Mobikwik tab");
    }

    Locator savedCardPaymentTab() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'psts_tab_title')][@text='SavedCard']"), "Saved Card");
    }

    Locator AxisCustomerId() {
        return new Locator(By.xpath("//android.view.View[contains(@resource-id,'userIdOption')] | //android.webkit.WebView[@text='Axis Bank Internet Banking']"), "Customer ID");
    }

    Locator personalMsg() {
        return new Locator(By.xpath("//android.view.View[contains(@text,'my name is yash')]"), "Personal Msg Check");
    }

    Locator savedCardNumber() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'card_number')]"), "Card Number");
    }

    Locator savedCardList() {
        return new Locator(By.xpath("//android.widget.ListView[contains(@resource-id,'save_card_list')]//android.widget.RelativeLayout"), "saved card list");
    }

    Locator PayNow() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'dialog_submit')]"), "Pay Now Button");
    }

    Locator savedCardTab() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@text,'Saved')]"), "Saved Card button");
    }

    Locator oKButton() {
        return new Locator(By.xpath("//android.widget.Button[@text='OK']"), "Ok button");
    }

    Locator UPITab() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@text,'UPI')]"), "Bhim UPI Tab");
    }

    Locator vpaAddress() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'new_upi_address')]"), "VPA Address");
    }

    Locator upiPaymentRequest() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_request_sent')]"), "UPI payment request");
    }

    Locator bankSelector() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'standard_spinner_format')]"), "");
    }

    Locator axisBankFromDropDown() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@text,'AXIS')]"), "Axis Bank");
    }

    /******************************************Services*****************************************************************************/

    public void fillAddress(CheckoutData checkoutData) throws IllegalAccessException, InstantiationException, InterruptedException {
        EnterValue(FullName(), checkoutData.getFirstname());
        EnterValue(PinCode(), checkoutData.getPostcode());
        click(PinCode());
        bringElementIntoViewDown(AddressLine1(), 3);
        EnterValue(AddressLine1(), checkoutData.getShipping_address1());
        driver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
        bringElementIntoViewDown(ContactNo(), 3);
        EnterValue(ContactNo(), checkoutData.getTelephone());
        click(SaveButton());


    }

    public boolean payViaCard(CheckoutData checkoutData) throws IllegalAccessException, InstantiationException, InterruptedException, IOException {
        try {
            if (waitUntilDisplayed(FullName(), 2)) {
                fillAddress(checkoutData);
            }
            if (waitUntilDisplayed(ProceedButton(), 2)) {
                click(ProceedButton());
            }
            if (waitUntilDisplayed(cancelTransactionPopUp(), 3)) {
                click(cancelTransactionPopUp());
            }
            click(CardPaymentTab());
            EnterValue(CardNoTextBox(), checkoutData.getCardno());
            EnterValue(MMYYTextBox(), checkoutData.getExpDate());
            click(MMYYTextBox());
            EnterValue(CVVLocator(), checkoutData.getCcv());

            click(PaySecurely());
            return waitUntilDisplayed(bankLogoLocator(), 60);
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            return false;
        }
    }

    public boolean payViaSavedCard(CheckoutData checkoutData) throws IllegalAccessException, InstantiationException, InterruptedException, IOException {
        try {
            if (waitUntilDisplayed(ProceedButton(), 2)) {
                click(ProceedButton());
            }
            if (waitUntilDisplayed(cancelTransactionPopUp(), 3)) {
                click(cancelTransactionPopUp());
            }
            click(savedCardTab());
            List<WebElement> savedCards = getWebElements(savedCardList());
            for (WebElement savedCard : savedCards) {
                Locator savedCardNumber = new Locator(savedCard, By.xpath("//android.widget.TextView[contains(@resource-id,'card_number')]"), "Saved Card Number");
                click(savedCardNumber);
                break;
            }
            EnterValue(CVVLocator(), checkoutData.getCcv());
            click(PayNow());

            return waitUntilDisplayed(bankLogoLocator(), 60);
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            return false;
        }
    }

    public boolean payViaNetbankingTopFive(CheckoutData checkoutData) throws IllegalAccessException, InstantiationException, IOException {
        try {
            if (waitUntilDisplayed(FullName(), 2)) {
                fillAddress(checkoutData);
            }
            if (waitUntilDisplayed(ProceedButton(), 2)) {
                click(ProceedButton());
            }
            if (waitUntilDisplayed(cancelTransactionPopUp(), 3)) {
                click(cancelTransactionPopUp());
            }
            click(NetBankingTab());
            click(bankLogoLocator());
            bringElementIntoViewDown(PaySecurely(), 2);
            click(PaySecurely());
            ProductPage productPage = new ProductPage(driver);
            if (waitUntilDisplayed(productPage.allowButton(), 15)) {
                click(productPage.allowButton());
            }
            boolean customerID = waitUntilDisplayed(AxisCustomerId(), 60);
            androidBackButton();
            return customerID;
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            return false;
        }

    }

    public boolean payViaNetBankingFromDropDown(CheckoutData checkoutData) throws IOException {
        try {
            if (waitUntilDisplayed(FullName(), 2)) {
                fillAddress(checkoutData);
            }
            if (waitUntilDisplayed(ProceedButton(), 3)) {
                click(ProceedButton());
            }
            if (waitUntilDisplayed(cancelTransactionPopUp(), 3)) {
                click(cancelTransactionPopUp());
            }
            click(NetBankingTab());
            click(bankSelector());
            click(axisBankFromDropDown());
            click(PaySecurely());
            ProductPage productPage = new ProductPage(driver);
            if (waitUntilDisplayed(productPage.allowButton(), 15)) {
                click(productPage.allowButton());
            }
            boolean customerID = waitUntilDisplayed(AxisCustomerId(), 60);
            androidBackButton();
            return customerID;
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            return false;
        }
    }

    public boolean payViaCOD(CheckoutData checkoutData) throws IllegalAccessException, InstantiationException, IOException {
        try {
            if (waitUntilDisplayed(FullName(), 2)) {
                fillAddress(checkoutData);
            }
            if (waitUntilDisplayed(ProceedButton(), 2)) {
                click(ProceedButton());
            }
            if (waitUntilDisplayed(cancelTransactionPopUp(), 3)) {
                click(cancelTransactionPopUp());
            }
            click(CashOnDeliveryTab());
            click(PlaceOrderButton());
            return waitUntilDisplayed(OrderConfirmationSummaryPage(), 60);
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            return false;
        }
    }

    public boolean payViaWalletsMobikwik(CheckoutData checkoutData) throws IllegalAccessException, InstantiationException, IOException {
        try {
            if (waitUntilDisplayed(FullName(), 2)) {
                fillAddress(checkoutData);
            }
            if (waitUntilDisplayed(ProceedButton(), 5)) {
                click(ProceedButton());
            }
            if (waitUntilDisplayed(cancelTransactionPopUp(), 3)) {
                click(cancelTransactionPopUp());
            }
            click(WalletsTab());
            click(MobikwikPaymenetTab());
            click(PaySecurely());
            return waitUntilDisplayed(MobikwikPaymentGateway(), 60);
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
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

    public boolean isOrderCreated() {
        return waitUntilDisplayed(OrderConfirmationSummaryPage(), 10);
    }

    public boolean payViaUPI(CheckoutData checkoutData) throws IllegalAccessException, InstantiationException, IOException {
        try {
            if (waitUntilDisplayed(FullName(), 2)) {
                fillAddress(checkoutData);
            }
            if (waitUntilDisplayed(ProceedButton(), 2)) {
                click(ProceedButton());
            }
            if (waitUntilDisplayed(cancelTransactionPopUp(), 3)) {
                click(cancelTransactionPopUp());
            }
            click(UPITab());
            EnterValue(vpaAddress(), checkoutData.getUsername());
            click(PaySecurely());
            return waitUntilDisplayed(upiPaymentRequest(), 60);

        } catch (Throwable e) {

            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            return false;
        }
    }

}
