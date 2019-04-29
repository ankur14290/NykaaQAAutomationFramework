package PagesNykaaApp;


import FrameWorkNykaa.AppAction;
import FrameWorkNykaa.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * Created by Abhishek Anand on 2nd August 2018.
 */
public class NykaaNetwork extends AppAction{

    public NykaaNetwork(AndroidDriver driver) {
        this.driver = driver;
    }

    /******************************************Locators*****************************************************************************/
    Locator userProfile(){
        return new Locator(By.xpath("//android.widget.RelativeLayout[@index='1']//android.widget.RelativeLayout//android.widget.ImageView"), "UserProfile");
    }

    Locator nykaaNetwork() {
        return new Locator(By.xpath("//android.widget.TextView[@text='Nykaa Network']"), "nykaa network");
    }
    /******************************************Services*****************************************************************************/
    public void getUserProfile() throws IllegalAccessException, InstantiationException {
        click(userProfile());
    }

    public boolean getNykaaNetwork() throws IllegalAccessException, InstantiationException {
        String lbl = getText(nykaaNetwork());
        click(nykaaNetwork());
         if (lbl.equals("Nykaa Network")) {
             return true;
         }
         else {
             return false;
         }
    }
}
