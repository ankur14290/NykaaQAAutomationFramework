package PagesNykaa_NykaaMan;

import FrameWorkNykaa.AppAction;
import FrameWorkNykaa.Locator;
import FrameWorkNykaa.TestListner;
import DataNykaa.AccountData;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;
import java.util.Random;

/**
 * Created by nevil on 6/26/2017.
 */


public class LoginPage extends AppAction {

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    Locator Email_textbox() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvEnterEmailId')]"), "Email Textbox");
    }

    Locator Email() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'etUserEmailId')]"), "Enter Email ID");
    }

    Locator Button_Go() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'buttonLogin')]"), "Proceed button");
    }

    Locator PassWord() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'etPassword')]"), "Password text");
    }

    Locator LoginButton() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'buttonRegister')]"), "Login button");
    }

    Locator FirstName() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'etUserFName')]"), "FirstName");
    }

    Locator LastName() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'etUserLName')]"), "Last Name");
    }

    Locator FaceBookButton() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvFbSignIn')]"), "FaceBook Button");
    }

    Locator DOB() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'dob')]"), "Date Of Birth");
    }

    Locator FemaleRadioButton() {
        return new Locator(By.xpath("//android.widget.RadioButton[contains(@resource-id,'female')]"), "Female Radio Button");
    }

    Locator MaleRadioButton() {
        return new Locator(By.xpath("//android.widget.RadioButton[contains(@resource-id,'male')]"), "Female Radio Button");
    }

    Locator FacebookEmailBox() {
        // return new Locator(By.xpath("//android.widget.EditText[contains(@text,'hone')]"), "Facebook Email Box");
        return new Locator(By.xpath("//android.widget.EditText[@index='0']"), "Facebook Email Box");
    }

    Locator PasswordBox() {
        //return new Locator(By.xpath("//android.widget.EditText[contains(@text,'assword')] | //android.widget.EditText[contains(@resource-id,'password')]"),"Facebook password box");
        return new Locator(By.xpath("//android.widget.EditText[@index='1']"), "Facebook Password Box");
    }

    Locator FBLoginButton() {
        return new Locator(By.xpath("//android.widget.Button"), "Login Button");
    }

    Locator Google_login() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvGoogleSignIn')]"), "G+ Login button");
    }

    Locator GplusAccount() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'account_display_name')]"), "G+ Account Select");
    }

    Locator GplusAccountList() {
        return new Locator(By.xpath("//android.widget.LinearLayout[@resource-id='com.google.android.gms:id/account_picker']//android.widget.LinearLayout"), "Account List");
    }

    Locator GplusAccount_add() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'button2')]"), "G+ Add button");
    }

    Locator GplusAdd_email() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'identifierId')]"), "G+ Email or Phone");
    }

    Locator GplusAdd_Next() {
        return new Locator(By.xpath("//*[contains(@resource-id,'identifierNext')]"), "G+ Email Next Button");
    }

    Locator GplusAdd_password() {
        return new Locator(By.xpath("//android.widget.EditText"), "G+ Password");
    }

    Locator GplusPwd_Next() {
        return new Locator(By.xpath("//*[contains(@resource-id,'passwordNext')]"), "G+ Password Next Button");
    }

    Locator GplusConsent_Next() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'signinconsentNext')]"), "G+ Consent Button");
    }

    Locator MsgOfAlreadyAssociatedAccount() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@text,'already registered')]"), "Account already linked");
    }

    Locator AcceptTnC() {
        return new Locator(By.xpath("//android.view.View[contains(@resource-id,'next')]"), "Accept TnC");
    }

    Locator next() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'suw_navbar_more')] | //android.widget.Button[contains(@resource-id,'suw_navbar_next')]"), "Next");
    }

    Locator continueFB() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'u_0_3')]"), "Continue Button");
    }

    Locator GuestLogin() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvSkipButton')]"), "Skip Button(Guest Login)");
    }

    Locator confirmRecoveryEmail() {
        return new Locator(By.xpath("//*[contains(@text,'your recovery')]"), "Confirm your recovery email Button");
    }

    /******************************************Services*****************************************************************************/

    public String LoginWithUserNameAndPassword(AccountData accountData) throws IllegalAccessException, InstantiationException {
        try {
            while (waitUntilDisplayed(Email_textbox(), 5)) {
                click(Email_textbox());
            }
            EnterValue(Email(), accountData.getUsername());
            click(Button_Go());
            EnterValue(PassWord(), accountData.getPassword());
            click(Button_Go());
            return accountData.getFirstName().toLowerCase();
        } catch (Throwable e) {
            Reporter.log(e.toString(), true);
            return "nothing";
        }
    }

    public String LoginWithExistingGoogleId(AccountData accountData) throws IllegalAccessException, InstantiationException, InterruptedException {
        try {
            while (waitUntilDisplayed(Google_login(), 5)) {
                click(Google_login());
            }
            String AccountName = null;
            if (waitUntilDisplayed(GplusAccountList(), 3)) {
                List<WebElement> accounts = getWebElements(GplusAccountList());
                for (WebElement account : accounts) {
                    Locator accountIterator = new Locator(account, GplusAccount().getBy(), "Gplus Account");
                    AccountName = getText(accountIterator);
                    click(accountIterator);
                    break;
                }
            } else {
                EnterValue(GplusAdd_email(), accountData.getUsername());
                click(GplusAdd_Next());
                EnterValue(GplusAdd_password(), accountData.getPassword());
                click(GplusPwd_Next());
                if (waitUntilDisplayed(confirmRecoveryEmail(), 4)) {
                    click(confirmRecoveryEmail());
                }
                click(AcceptTnC());
                click(next());
                click(next());
                AccountName = accountData.getFirstName();
            }
            return AccountName;
        } catch (Throwable e) {
            Reporter.log(e.toString());
            return "nothing";
        }
    }

    public String LoginWithFacebook(AccountData accountData) throws IllegalAccessException, InstantiationException, InterruptedException {
        try {
            click(FaceBookButton());
            EnterValue(FacebookEmailBox(), accountData.getUsername());
            EnterValue(PasswordBox(), accountData.getPassword());
            click(FBLoginButton());
            if (waitUntilDisplayed(continueFB(), 15)) {
                click(continueFB());
            }
            //return accountData.getFirstName().toLowerCase();
            return accountData.getUsername().toLowerCase();
        } catch (Throwable e) {
            Reporter.log(e.toString());
            return "no data";
        }

    }

    public void LoginWithFacebookWithAssociatedUser() throws IllegalAccessException, InstantiationException {
        click(FaceBookButton());
    }

    public String RegisterNewUser(AccountData accountData) throws IllegalAccessException, InstantiationException, InterruptedException {
        try {
            Random random = new Random(System.currentTimeMillis());
            while (waitUntilDisplayed(Email_textbox(), 5)) {
                click(Email_textbox());
            }
            EnterValue(Email(), accountData.getFirstName().concat(String.valueOf(random.nextInt() + "@mailinator.com")));
            click(Button_Go());
            EnterValue(PassWord(), accountData.getPassword());
            EnterValue(FirstName(), accountData.getFirstName());
            EnterValue(LastName(), accountData.getLastName());
            if (isDisplayed(LoginButton())) {
                click(LoginButton());
            } else {
                androidBackButton();
                bringElementIntoViewDown(LoginButton(), 5);
                click(LoginButton());
            }
            return accountData.getFirstName().toLowerCase();
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            Reporter.log(e.toString());
            return "nothing";
        }
    }

    public boolean LoginWithEmailAlreadyAssociatedWithFB(AccountData accountData) throws IllegalAccessException, InstantiationException, InterruptedException {
        while (waitUntilDisplayed(Email_textbox(), 5)) {
            click(Email_textbox());
        }
        EnterValue(Email(), accountData.getUsername());
        click(Button_Go());
        return waitUntilDisplayed(MsgOfAlreadyAssociatedAccount(), 10);

    }

    public boolean LoginWithEmailAlreadyAssociatedWithGplus(AccountData accountData) throws IllegalAccessException, InstantiationException, InterruptedException {
        while (waitUntilDisplayed(Email_textbox(), 5)) {
            click(Email_textbox());
        }
        EnterValue(Email(), accountData.getUsername());
        click(Button_Go());
        return waitUntilDisplayed(MsgOfAlreadyAssociatedAccount(), 10);

    }

    public void LoginWithGuestUser() throws IllegalAccessException, InstantiationException, InterruptedException {
        waitUntilDisplayed(GuestLogin(), 7);
        click(GuestLogin());

    }

}
