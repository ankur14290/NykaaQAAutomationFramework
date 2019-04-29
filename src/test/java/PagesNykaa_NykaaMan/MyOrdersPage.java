package PagesNykaa_NykaaMan;

import FrameWorkNykaa.Locator;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyOrdersPage extends HeaderBar {


    public MyOrdersPage(AndroidDriver driver) {
        super(driver);
    }


    private String orderNo;

    Locator orderDetails() {
        return new Locator(By.xpath("//android.widget.TextView[@text='Order Details']"), "Order Details");
    }

    Locator orderDetails(String orderNo) {
        return new Locator(By.xpath("//android.view.View[@text='" + orderNo + "']"), "Order Details");
    }

    Locator OrderSummarySection() {
        return new Locator(By.xpath("//android.view.View[@text='PAYMENT DETAILS'] | //android.view.View[@content-desc='PAYMENT DETAILS']"), "Order Summary Section");
    }

    public void goToOrderDetailsPage() throws InterruptedException, IllegalAccessException, InstantiationException {
        List<WebElement> orderlisting = getWebElements(orderListing());
        for (WebElement orders : orderlisting) {
            Locator ordernumber = new Locator(orders, By.xpath("//android.view.View"), "Order Number");
            setOrderNo(getText(ordernumber));
            click(ordernumber);
            break;
        }
    }

    public boolean isOrderDetailedPageOpened() throws InterruptedException {
        return waitUntilDisplayed(orderDetails(getOrderNo()), 10);
    }

    public boolean goToOrderSummarySection() throws InterruptedException, IllegalAccessException, InstantiationException {
        bringElementIntoViewDown(OrderSummarySection(), 10);
        return waitUntilDisplayed(OrderSummarySection(), 5);
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
