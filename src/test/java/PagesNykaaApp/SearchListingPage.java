package PagesNykaaApp;

import FrameWorkNykaa.Locator;
import FrameWorkNykaa.PartialStringList;
import DataNykaa.Header_FooterBarDataApp;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchListingPage extends ListingPage{

    public SearchListingPage(AndroidDriver driver) {
        super(driver);
    }



    Locator productName(String productName){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_product_name')][contains(@text,'"+productName+"')]"),"Product Name");
    }

    public PartialStringList getSearchedResult(Header_FooterBarDataApp header_footerBarData) throws Throwable {
        PartialStringList productValueList = new PartialStringList();

        List<WebElement> ProductList = getWebElements(productName(header_footerBarData.get_search_keyword()));

        for (WebElement product : ProductList) {
            String TitleProduct = product.getText();
            productValueList.add(TitleProduct.toLowerCase());
        }

        return productValueList;
    }
}