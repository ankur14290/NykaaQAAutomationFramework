package PagesNykaa_NykaaMan;

import FrameWorkNykaa.AppAction;
import FrameWorkNykaa.Locator;
import DataNykaa.AccountData;
import DataNykaa.CheckoutData;
import DataNykaa.ProductDetailData;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends AppAction {

    public ProductPage(AndroidDriver driver) {
        this.driver = driver;
    }

    Locator shades() {
        return new Locator(By.xpath("//android.widget.ImageView[contains(@resource-id,'img_shade')]/ancestor::android.widget.FrameLayout[contains(@resource-id,'layout_container')]"), "Shades");
    }

    Locator sizes() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_size')]/ancestor::android.widget.LinearLayout"), "Sizes");
    }

    Locator AddToBag() {
        return new Locator(By.xpath("//android.widget.TextView[@text='ADD TO BAG']"), "Add To Bag");
    }

    Locator AddToBagCustomerAlsoBought() {
        return new Locator(By.xpath("//android.support.v7.widget.RecyclerView[contains(@resource-id,'r_customer_bought_list')]//android.widget.FrameLayout"), "Add To Bag CAB");
    }

    Locator AddToBagCustomerAlsoViewd() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'btn_add_to_bag')]/ancestor::android.support.v7.widget.RecyclerView[contains(@resource-id,'r_customer_viewed_list')]//android.widget.FrameLayout"), "Add To Bag CAV");
    }

    Locator ProductText() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvProductTitle')]"), "Product Text");
    }

    Locator productLabel() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'product_title_text')]"), "Product Name");
    }

    Locator productNameInCAB() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_product_name')]"), "Product Name Text");
    }

    Locator WishlistIcon() {
        return new Locator(By.xpath("//android.widget.ImageView[contains(@resource-id,'wish_img')]"), "Wishlist Icon");
    }

    Locator AddToBagDynamicComboButton() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_btn_title')][contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'add combo to bag')]"), "Add to Bag Combo");
    }

    Locator ComboProductNames() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'product_grid_title_text')]"), "Product Name of Combo");
    }

    Locator TryItOnButton() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'tryiton_prd_img')]"), "Try It On Button");
    }

    Locator TryItOnShadeSelector() {
        return new Locator(By.xpath("//android.widget.Gallery[contains(@resource-id,'option_picker')]"), "Try It Shade Selector");
    }

    Locator shareButton() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'share_prd_img')]"), "Share Button");
    }

    Locator shareViaBox() {
        return new Locator(By.xpath("//android.widget.TextView[@text='Share Via']"), "Share Via");
    }

    Locator allowButton() {
        return new Locator(By.xpath("//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_allow_button']"), "Allow Button");
    }

    Locator ratingOfProduct() {
        return new Locator(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'ratings_button')]"), "Ratings");
    }

    Locator reviewTitle() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'review_title')]"), "Review Title");
    }

    Locator reviewDescription() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'review_description')]"), "Description");
    }

    Locator doneRating() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'textViewDone')]"), "Done");
    }

    Locator ratingSubmitted() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id, 'dialog_description')][@text='Your review is under moderation. Approval typically takes 24 hours.']"), "Rating Done");
    }

    Locator readAllReviewButton() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'btn_read_reviews')]"), "Read All Review Button");
        //return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'topreview')]"),"Read All Review Button");
    }

    Locator ReviewLikeButton() {
        return new Locator(By.xpath("//android.widget.ImageView[contains(@resource-id,'like_img')]"), "Review Like Button");
    }

    Locator ReviewCountButton() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'user_review_count')][contains(@text,'You')]"), "Review Count Button");
    }

    Locator subscribeBar() {
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'layout_subscribe')]"), "Subscribe Bar");
    }

    Locator subscribeNowButton() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'text_view_subscribe_now')]"), "Subscribe Button");
    }

    Locator shipToThisAddress() {
        return new Locator(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'btn_select_address')]"), "Select Address");
    }

    Locator confirmSubscription() {
        return new Locator(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'btn_confirm_subscription')]"), "Confirm Subscription");
    }

    Locator subscriptionConfirmedMsg() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_confirmed')][contains(@text,'Your Subscription is Confirmed')]"), "Subscription Confirmed");
    }

    Locator likeButtonCell() {
        return new Locator(By.xpath("//android.widget.LinearLayout[contains(@resource-id,'layout_review_row_container')]"), "Reviews Cell");
    }

    Locator reviewSubmit() {
        return new Locator(By.xpath("//*[@text='OK']"), "OK Button");
    }

    Locator faltu() {
        return new Locator(By.xpath("//sfjdnskfncsdj"), "faltu");
    }

    Locator addNewAddress() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'btn_add_address')]"), "Add New Address Button");
    }

    Locator tryItOnShadeSelector() {
        return new Locator(By.xpath("//android.widget.ImageView[not(contains(@resource-id,'state_icon'))]/ancestor::android.widget.RelativeLayout/ancestor::android.widget.LinearLayout/ancestor::android.widget.Gallery[contains(@resource-id,'option_picker')]//android.widget.LinearLayout"), "Option Selector");
    }

    Locator averageRating() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_avg_rating')]"), "Average Rating");
    }

    Locator mostRecent() {
        return new Locator(By.xpath("//android.widget.TextView[@text='MOST RECENT']"), "Most Recent");
    }

    /******************************************Services*****************************************************************************/

    public ProductDetailData addToBagShadeProductPage() throws InterruptedException, IllegalAccessException, InstantiationException {

        boolean isShadeAvailable = false;
        List<WebElement> shadeElements = getWebElements(shades());
        for (WebElement shadeElement : shadeElements) {
            Locator shadeLocator = new Locator(shadeElement, By.xpath("//android.widget.ImageView[contains(@resource-id,'img_shade')]"), "Shade Selector");
            click(shadeLocator);
            isShadeAvailable = true;
            break;
        }
        ProductDetailData productdetail = getProductDetail();
        click(AddToBag());

        return productdetail;
    }

    public ProductDetailData addToBagSimpleProductPage() throws IllegalAccessException, InstantiationException {
        ProductDetailData productdetail = getProductDetail();
        click(AddToBag());
        return productdetail;
    }

    public ProductDetailData addToBagSizeProductPage() throws InterruptedException, IllegalAccessException, InstantiationException {

        boolean isSizeAvailable = false;
        List<WebElement> sizeElements = getWebElements(sizes());
        for (WebElement sizeElement : sizeElements) {
            Locator sizeLocator = new Locator(sizeElement, By.xpath("//android.widget.TextView[contains(@resource-id,'txt_size')]"), "Size Selector");
            click(sizeLocator);
            isSizeAvailable = true;
            break;
        }
        ProductDetailData productdetail = getProductDetail();
        click(AddToBag());

        return productdetail;
    }

    public ProductDetailData addToBagCustomerAlsoBought() throws InterruptedException, IllegalAccessException, InstantiationException {
        ProductDetailData productDetail = new ProductDetailData();
        List<WebElement> Elements = getWebElements(AddToBagCustomerAlsoBought());
        for (WebElement element : Elements) {
            Locator productLocator = new Locator(element, By.xpath("//android.widget.Button[contains(@resource-id,'btn_add_to_bag')]"), "Added first product");
            productDetail = getProductDetail(element);
            if (getText(productLocator).contains("PREVIEW SIZES")) {
                click(productLocator);
                click(AddToBag());
                break;
            } else if (getText(productLocator).contains("PREVIEW SHADES")) {
                click(productLocator);
                click(AddToBag());
                break;
            } else if (getText(productLocator).contains("ADD TO BAG")) {
                click(productLocator);
                break;
            }
        }
        return productDetail;
    }

    public ProductDetailData addToBagCustomerAlsoViewed() throws InterruptedException, IllegalAccessException, InstantiationException {
        bringElementIntoViewDown(AddToBagCustomerAlsoViewd(), 6);
        ProductDetailData productDetail = new ProductDetailData();
        List<WebElement> Elements = getWebElements(AddToBagCustomerAlsoViewd());
        for (WebElement element : Elements) {
            Locator productLocator = new Locator(element, By.xpath("//android.widget.Button[contains(@resource-id,'btn_add_to_bag')]"), "Added first product");
            productDetail = getProductDetail(element);
            if (getText(productLocator).contains("PREVIEW SIZES")) {
                click(productLocator);
                click(AddToBag());
                break;
            } else if (getText(productLocator).contains("PREVIEW SHADES")) {
                click(productLocator);
                click(AddToBag());
                break;
            } else if (getText(productLocator).contains("ADD TO BAG")) {
                click(productLocator);
                break;
            }
        }
        return productDetail;
    }

    public boolean verifyTryitOn() throws Throwable {
        bringElementIntoViewDown(TryItOnButton(), 10);
        click(TryItOnButton());
        clickRandomlyOnWebElements(tryItOnShadeSelector());
        boolean TryItOn = waitUntilDisplayed(TryItOnShadeSelector(), 10);
        HeaderBar headerBar = new HeaderBar(driver);
        headerBar.BackButtonClick();
        return TryItOn;
    }

    public ProductDetailData addProductToWishlist() throws IllegalAccessException, InstantiationException {
        click(WishlistIcon());
        ProductDetailData productdetail = getProductDetail();
        return productdetail;
    }

    public List<ProductDetailData> addDynamicCombo() throws InterruptedException, IllegalAccessException, InstantiationException {
        bringElementIntoViewDown(AddToBagDynamicComboButton(), 20);
        List<ProductDetailData> combodetail = getComboProductDetail();
        click(AddToBagDynamicComboButton());
        return combodetail;
    }

    public boolean isProductDetailedPageOpened(ProductDetailData productDetailData) {
        boolean productPageOpened = false;
        String ProductName = productDetailData.getProductName().split(" ")[0] + " " + productDetailData.getProductName().split(" ")[1] + " " + productDetailData.getProductName().split(" ")[2];
        // String ProductNameFromUI = getText(ProductText()).split(" ")[0]+" "+getText(ProductText()).split(" ")[1]+" "+getText(ProductText()).split(" ")[2];
        String ProductNameFromUI = getText(productLabel());
        if (ProductNameFromUI.contains(ProductName)) {
            productPageOpened = true;
        }
        return productPageOpened;
    }

    public boolean checkShareFunctionality() throws IllegalAccessException, InstantiationException {
        click(shareButton());
        if (waitUntilDisplayed(allowButton(), 3)) {
            click(allowButton());
        }
        boolean shareVia = waitUntilDisplayed(shareViaBox(), 10);
        androidBackButton();
        return shareVia;
    }

    protected ProductDetailData getProductDetail() {
        ProductDetailData product = new ProductDetailData();
        String productName = getText(ProductText());
        product.setProductName(productName);
        return product;
    }

    private ProductDetailData getProductDetail(WebElement productLayoutElement) {
        ProductDetailData product = new ProductDetailData();
        Locator ProductNameLocator = new Locator(productLayoutElement, productNameInCAB().getBy(), "Product Name Text");
        String productName = getText(ProductNameLocator);
        product.setProductName(productName);
        return product;
    }

    private List<ProductDetailData> getComboProductDetail() throws InterruptedException {
        List<ProductDetailData> listOfComboProduct = new ArrayList<ProductDetailData>();
        List<WebElement> ComboProduct = getWebElements(ComboProductNames());
        for (WebElement product : ComboProduct) {
            String productname = getText(ComboProductNames());
            ProductDetailData productDetailData = new ProductDetailData();
            productDetailData.setProductName(productname);
            listOfComboProduct.add(productDetailData);

        }
        return listOfComboProduct;
    }

    public boolean productRating(ProductDetailData productDetailData) throws InterruptedException, InstantiationException, IllegalAccessException {
        EnterValue(reviewTitle(), productDetailData.getReviewtitle());
        click(doneRating());
        EnterValue(reviewDescription(), productDetailData.getReviews());
        click(doneRating());
        boolean ratingSubmission = waitUntilDisplayed(ratingSubmitted(), 25);
        click(reviewSubmit());
        return ratingSubmission;
    }

    public boolean verifyRating(ProductDetailData productDetailData) throws InterruptedException, IllegalAccessException, InstantiationException {
        click(averageRating());
        click(ratingOfProduct());
        return productRating(productDetailData);
    }

    public boolean GuestRatingButton() throws InterruptedException, InstantiationException, IllegalAccessException {
        bringElementIntoViewDown(ratingOfProduct(), 7);
        click(ratingOfProduct());
        HeaderBar headerBar = new HeaderBar(driver);
        return headerBar.isGuestLoginPopUp();
    }

    public boolean productReviewLike() throws InterruptedException, InstantiationException, IllegalAccessException {
        bringElementIntoViewDown(readAllReviewButton(), 10);
        click(readAllReviewButton());
        Thread.sleep(4000);
        bringElementIntoViewDown(mostRecent(), 5);
        List<WebElement> likes = getWebElements(likeButtonCell());
        for (WebElement iterator : likes) {
            Locator likeButton = new Locator(iterator, By.xpath("//android.widget.ImageView[contains(@resource-id,'like_img')]"), "Like Button");
            bringElementIntoViewDown(likeButton, 5);
            click(likeButton);
            Thread.sleep(1500);
            Locator reviewCount = new Locator(iterator, By.xpath("//android.widget.TextView[contains(@resource-id,'user_review_count')]"), "User Review Count");
            if (getText(reviewCount).contains("You")) {
                return true;
            } else {
                break;
            }
        }
        return false;
    }

    public void doSubscription(CheckoutData checkoutData) throws InterruptedException, InstantiationException, IllegalAccessException {
        bringElementIntoViewDown(subscribeBar(), 15);
        click(subscribeBar());
        bringElementIntoViewDown(subscribeNowButton(), 3);
        click(subscribeNowButton());
        if (!waitUntilDisplayed(shipToThisAddress(), 3)) {
            click(addNewAddress());
            CheckOutPage checkOutPage = new CheckOutPage(driver);
            checkOutPage.fillAddress(checkoutData);
        }
        click(shipToThisAddress());
        click(confirmSubscription());

    }

    public boolean isSubscriptionDone() {
        return waitUntilDisplayed(subscriptionConfirmedMsg(), 10);
    }

    public boolean GuestSubscribe(AccountData accountData, CheckoutData checkoutData) throws InterruptedException, InstantiationException, IllegalAccessException {
        bringElementIntoViewDown(subscribeBar(), 10);
        click(subscribeBar());
        HeaderBar headerBar = new HeaderBar(driver);
        LoginPage loginPage = new LoginPage(driver);
        MoreSection moreSection = new MoreSection(driver);
        if (headerBar.isGuestLoginPopUp()) {
            loginPage.RegisterNewUser(accountData);
            bringElementIntoViewDown(subscribeNowButton(), 7);
            click(subscribeNowButton());
            moreSection.fillNewAddress(checkoutData);
            click(shipToThisAddress());
            click(confirmSubscription());
            return waitUntilDisplayed(subscriptionConfirmedMsg(), 5);
        } else return false;
    }

    public boolean GuestReviewLike(AccountData accountData) throws InterruptedException, InstantiationException, IllegalAccessException {
        bringElementIntoViewDown(readAllReviewButton(), 10);
        click(readAllReviewButton());
        bringElementIntoViewDown(ReviewLikeButton(), 7);
        click(ReviewLikeButton());
        LoginPage loginPage = new LoginPage(driver);
        HeaderBar headerBar = new HeaderBar(driver);
        if (headerBar.isGuestLoginPopUp()) {
            loginPage.RegisterNewUser(accountData);
            return waitUntilDisplayed(ReviewCountButton(), 7);
        } else return false;
    }
}

