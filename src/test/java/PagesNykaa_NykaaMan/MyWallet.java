package PagesNykaa_NykaaMan;


import FrameWorkNykaa.AppAction;
import FrameWorkNykaa.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * Created by Abhishek Anand on 4th July 2018.
 */

public class MyWallet extends AppAction {

    public MyWallet(AndroidDriver driver) {
        this.driver = driver;
    }

    /******************************************Locators*****************************************************************************/

    Locator userProfile() {
        return new Locator(By.xpath("//android.widget.RelativeLayout[@index='1']//android.widget.RelativeLayout//android.widget.ImageView"), "UserProfile");
    }

    Locator myWalletText() {
        return new Locator(By.xpath("//android.widget.TextView[@text='My Wallet']"), "myWalletText");
    }

    Locator myAccount() {
        return new Locator(By.xpath("//android.widget.TextView[@text='My Account']"), "myAccount");
    }

    Locator myAccountEnable() {
        return new Locator(By.xpath("//android.widget.ImageView"), "enableBtn");
    }

    Locator cancelBtn() {
        return new Locator(By.xpath("//android.widget.Button[@text='CANCEL']"), "Cancel");
    }

    /******************************************Services*****************************************************************************/
    public void getUserProfile() throws IllegalAccessException, InstantiationException {
        click(userProfile());
    }

    public boolean getMyWalletText() throws IllegalAccessException, InstantiationException, InterruptedException {
        /*click(myAccount());
        click(myAccountEnable());
        click(cancelBtn());
        click(userProfile());*/
        String myWallet = getText(myWalletText());
        if (myWallet.equals("My Wallet")) {
            return true;
        } else {
            click(myAccount());
            // click(myAccountEnable());
            Thread.sleep(5000);
            click(cancelBtn());
            click(userProfile());
            String myWalletLbl = getText(myWalletText());
            if (myWalletLbl.equals("My Wallet")) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void clickMyWallet() throws IllegalAccessException, InstantiationException {
        click(myWalletText());
    }
}
