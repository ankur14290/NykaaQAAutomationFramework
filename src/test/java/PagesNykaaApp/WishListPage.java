package PagesNykaaApp;

import FrameWorkNykaa.Locator;
import DataNykaa.ProductDetailData;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class WishListPage extends ListingPage {

    public WishListPage(AndroidDriver driver) {
        super(driver);
    }

    Locator productNameInListing(){
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'wishlist_parent_layout')]//android.widget.TextView[contains(@resource-id,'txt_product_name')]"),"Product NameText");
    }

    public DataNykaa.ProductDetailData goToProductDetailPageFromWIshList() throws IllegalAccessException, InstantiationException {
        DataNykaa.ProductDetailData productDetailData = getProductDetail();
        click(productNameInListing());
        return productDetailData;
    }
}