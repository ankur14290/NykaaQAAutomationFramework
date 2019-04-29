package PagesNykaaApp;

import FrameWorkNykaa.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyOrdersPage extends HeaderBar {


    public MyOrdersPage(AndroidDriver driver) {
        super(driver);
    }

    Locator OrderDetails(){
        return new Locator(By.xpath("//android.widget.TextView[@text='Order Details']"),"Order Details");
    }

    Locator OrderSummarySection(){
        return new Locator(By.xpath("//android.view.View[@text='ORDER SUMMARY'] | //android.webkit.WebView[@resource_id='webView1']"),"Order Summary Section");
    }



    public void goToOrderDetailsPage() throws InterruptedException, IllegalAccessException, InstantiationException {
        List<WebElement> orderlisting = getWebElements(orderListing());
        for (WebElement orders:orderlisting){
            Locator ordernumber = new Locator (By.xpath("//android.view.View[contains(@text, 'Nykaa')]"),"Order Number");
            // ordernumber.getBy();
            click(ordernumber);
            break;
        }
    }

    public boolean isOrderDetailedPageOpened() throws InterruptedException {
        return waitUntilDisplayed(OrderDetails(),10);
    }

    public boolean goToOrderSummarySection() throws InterruptedException, IllegalAccessException, InstantiationException {
        goToOrderDetailsPage();
        bringElementIntoViewDown(OrderSummarySection(),2);
        return waitUntilDisplayed(OrderSummarySection(),5);
    }
}