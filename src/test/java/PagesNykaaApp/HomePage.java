package PagesNykaaApp;

import FrameWorkNykaa.AppAction;
import FrameWorkNykaa.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class HomePage extends AppAction {

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
    }

    /******************************************Locators*****************************************************************************/

    Locator homePageLayout(){
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'home_parent_Layout')]"),"Home Page");
    }

    Locator offerHomePage(){
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'rootOfferLayout')]"),"Offer Layout");
    }

    Locator  styleDivaPage(){
        return new Locator(By.xpath("//android.widget.Image[contains(@text,'style_divas_banner_mobile')]"),"Style Divas");
    }

    Locator USP(){
        return new Locator(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'card_view')]//android.widget.ImageView[contains(@resource-id,'componentImageView')]"),"Nykaa's USP");
    }

    Locator TnCPage(){
        return new Locator(By.xpath("//android.view.View[@text='TERMS & CONDITIONS'] | //android.view.View[@content-desc='TERMS & CONDITIONS']"),"Terms And Condition Heading");
    }

    Locator privacyPolicyPage(){
        return new Locator(By.xpath("//"),"");
    }

    Locator recentlyViewedWidget(){
        return new Locator(By.xpath("//android.widget.TextView[@text='RECENTLY VIEWED']"),"Recently Viewed Widget");
    }

    public boolean isUSPPresent() throws InterruptedException, IllegalAccessException, InstantiationException {
        bringElementIntoViewDown(USP(),50);
        return waitUntilDisplayed(USP(),5);
    }

    public boolean isRecentlyViewedWidgetPresent() throws InterruptedException, IllegalAccessException, InstantiationException {
        bringElementIntoViewDown(recentlyViewedWidget(),50);
        return waitUntilDisplayed(recentlyViewedWidget(),5);
    }
}