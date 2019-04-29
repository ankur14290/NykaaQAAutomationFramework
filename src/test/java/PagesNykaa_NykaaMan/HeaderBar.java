package PagesNykaa_NykaaMan;

import FrameWorkNykaa.AppAction;
import FrameWorkNykaa.Locator;
import DataNykaa.AccountData;
import DataNykaa.Header_FooterBarData;
import DataNykaa.ProductDetailData;
import android.view.KeyEvent;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;

/**
 * Created by nevil on 7/24/2017.
 */
public class HeaderBar extends AppAction {

    public HeaderBar(AndroidDriver driver) {
        this.driver = driver;
    }


    /******************************************Locators*****************************************************************************/

    Locator ActionMenubar() {
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'menu_overflow')]"), "Action Menu bar");
    }

    Locator LoggedInUserName() {
        //return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'txt_user_name')]"), "User name Text");
        return new Locator(By.xpath("//android.widget.EditText[@resource-id='com.fsn.nykaa.man:id/user_editable_detail']"), "User Name Text");
    }

    Locator myAccountButton() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'action_my_account')]"), "My Account");
    }

    Locator referAndEarn() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'refer_and_win')]"), "Refer And Earn");
    }

    Locator notificationButton() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'action_notifications')]"), "Notification");
    }

    Locator trackOrder() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'action_current_orders')]"), "Track Order");
    }

    Locator helpCenter() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'action_support_center')]"), "Help Center");
    }

    Locator LogOutButton() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'com.fsn.nykaa.man:id/logout_btn')]"), "Logout button");
    }

    Locator MenuButton() {
        return new Locator(By.xpath("//*[contains(@resource-id,'action_bar')]//android.widget.ImageButton[@content-desc='Navigate up']"), "Menu Button");
    }

    Locator listMenu(String LableName) {
        return new Locator(By.xpath("//android.widget.TextView[@text='" + LableName + "']"), "category " + LableName + "");
    }

    Locator CartIcon() {
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'/action_shopping_cart')]"), "Cart Button");
    }

    Locator BackButton() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'goBackBtn')] | //android.widget.FrameLayout[contains(@resource-id,'/action_bar')]//android.widget.ImageButton[@index='0'] | //android.view.ViewGroup[contains(@resource-id,'action_bar')]//android.widget.ImageButton | //android.widget.TextView[contains(@resource-id,'btn_back')] | //android.widget.ImageView[contains(@resource-id,'ivClose')] "), "Menu Back Button");
    }

    Locator CartTotalLocator() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'badge_count')]"), "Badge Count");
    }

    Locator MenuBackButton() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'goBackBtn')] | //android.widget.TextView[contains(@resource-id,'goBackBtn')]"), "Menu Back Button");
    }

    Locator WishlistButton() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'action_wish_list')][@text='Wishlist']"), "Wishlist Button");
    }

    Locator SearchBrandTextBox() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'search_field_brand')]"), "Search Brand TextBox");
    }

    Locator BrandsName(String BrandName) {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'brand_name')][@text='" + BrandName + "']"), "Brand List");
    }

    Locator WishlistParent() {
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'wishlist_parent_layout')]"), "Wishlist Parent");
    }

    Locator homeButton() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'menu_item_label')][@text='Home']"), "Home Button");
    }

    Locator offerButton() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'menu_item_label')][@text='Offers']"), "Offer Button");
    }

    Locator searchLocator() {
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'search_field')]"), "Search Bar");
    }

    Locator loader() {
        return new Locator(By.xpath("//android.widget.ProgressBar[contains(@resource-id,'progressBarMain')]"), "loader icon");
    }

    Locator searchIcon() {
        // return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'action_search')]"),"Search Icon");
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'search_field')]"), "Search Icon");
    }

    Locator styleDiva() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'menu_item_label')][@text='Style Divas']"), "");
    }

    Locator TncLocator() {
        return new Locator(By.xpath("//android.view.View[contains(@text,'Conditions')] | //android.view.View[@content-desc='Terms & Conditions']"), "Terms And Conditions");
    }

    Locator contactUs() {
        return new Locator(By.xpath("//android.view.View[@text='CONTACT US']|//android.view.View[@content-desc='CONTACT US']"), "Contact Us");
    }

    Locator topCategoriesProduct() {
        return new Locator(By.xpath("//android.widget.FrameLayout[contains(@resource-id,'card_view_top_category')]//android.widget.FrameLayout[contains(@resource-id,'root_layout_chipview')]"), "Top Categories");
    }

    Locator orderListing() {
        return new Locator(By.xpath("//android.view.View[contains(@text,'ORDER')]/ancestor::android.view.View"), "Order Listing");
    }

    Locator orderPage() {
        return new Locator(By.xpath("//android.view.View[contains(@text,'ORDER #')]"), "Order Page");
    }

    Locator searchSuggestion() {
        return new Locator(By.xpath("//android.widget.ListView[contains(@resource-id,'searchList')]//android.widget.RelativeLayout | //android.widget.ExpandableListView[contains(@resource-id,'expandable_view_auto_suggestions')]//android.widget.LinearLayout"), "Search Suggestions");
    }

    Locator topProducts() {
        return new Locator(By.xpath("//android.support.v7.widget.RecyclerView[contains(@resource-id,'recycler_view_auto_suggestions')]//android.widget.LinearLayout"), "Top Products");
    }

    Locator MyAccountLoginButton() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tv_login_register_guest')]"), "GuestLoginButton MyAccount");
    }

    Locator GuestLoginPopUp() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvTitleLoginSignUp')]"), "GuestLoginPopUp");
    }

    Locator MySubscriptionButton() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'action_my_subscriptions')]"), "MySubscription");
    }

    Locator InviteButton() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id, 'btnInviteFriend')]"), "IR Button");
    }

    Locator SubscriptionPage() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id, 'txt_toolbar_title')]"), "Subscription Page");
    }

    Locator firstClick() {
        return new Locator(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"), "firstClick");
    }

    Locator secondClick() {
        return new Locator(By.xpath("//android.widget.TextView[@text='Makeup']"), "secondClick");
    }

    Locator thirdClick() {
        return new Locator(By.xpath("//android.widget.TextView[@text='Face']"), "thirdClick");
    }

    Locator forthClick() {
        return new Locator(By.xpath("//android.widget.TextView[@text='Face Primer']"), "forthClick");
    }

    Locator seventhClick() {
        return new Locator(By.xpath("//android.support.v7.widget.LinearLayoutCompat//android.widget.RelativeLayout[@index='0']//android.widget.RelativeLayout[@index='0']//android.widget.ImageView"), "seventhClick");
    }

    Locator eightClick() {
        return new Locator(By.xpath("//android.widget.EditText[@text='Search']"), "eightClick");
    }

    Locator ninthClick() {
        return new Locator(By.xpath("//android.widget.TextView[@text='Lakme']"), "ninthClick");
    }

    Locator SearchHistoryClick() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id, 'primaryText')]"), "Search History");
    }

    Locator topBrands() {
        return new Locator(By.xpath("//android.widget.FrameLayout[contains(@resource-id,'card_view_top_brands')]//android.widget.FrameLayout[contains(@resource-id,'root_layout_chipview')]"), "Top Brands");
    }

    Locator LogoutYes() {
        return new Locator(By.xpath("//android.widget.TextView[@text='YES']"), "Yes Logout");
    }

    /******************************************Services*****************************************************************************/
    public void getNinthClick() throws IllegalAccessException, InstantiationException {
        click(ninthClick());
    }

    public void getEightClick() throws IllegalAccessException, InstantiationException {
        EnterValue(eightClick(), "lakme");
    }

    public void getSeventhClick() throws IllegalAccessException, InstantiationException {
        click(seventhClick());
    }

    public void getForthClick() throws IllegalAccessException, InstantiationException {
        click(forthClick());
    }

    public void getThirdClick() throws IllegalAccessException, InstantiationException {
        click(thirdClick());
    }


    public void getSecondClick() throws IllegalAccessException, InstantiationException {
        click(secondClick());
    }

    public void getFirstClick() throws IllegalAccessException, InstantiationException {
        click(firstClick());
    }


    public String getLoggedInUserName() throws IllegalAccessException, InstantiationException {
        try {
            click(ActionMenubar());
            Thread.sleep(2000);
            click(myAccountButton());
            return getText(LoggedInUserName());
        } catch (Throwable e) {
            Reporter.log(e.toString());
            return "No Text";
        }
    }

    public void navigateToWishlistPage() throws IllegalAccessException, InstantiationException, InterruptedException {
        if (waitUntilDisplayed(ActionMenubar(), 5)) {
            click(ActionMenubar());
            click(WishlistButton());
        }
    }

    public boolean isProductPresentInWishList(ProductDetailData productDetailData) throws InterruptedException {
        boolean isProductPresent = false;
        String productName = productDetailData.getProductName();
        List<WebElement> webElements = getWebElements(WishlistParent());
        for (WebElement element : webElements) {
            Locator ProductName = new Locator(element, By.xpath("//android.widget.TextView[contains(@resource-id,'txt_product_name')]"), "Product Name");
            String ProductNameFromUI = getText(ProductName);
            // if (ProductNameFromUI.contains(productName))
            if (productName.contains(ProductNameFromUI)) {
                isProductPresent = true;
                break;
            }
        }
        return isProductPresent;
    }

    public boolean Logout() throws IllegalAccessException, InstantiationException {
        try {
            if (waitUntilDisplayed(LogOutButton(), 5)) {
                click(LogOutButton());
                Thread.sleep(2000);
                click(LogoutYes());
            } else {
                /*click(ActionMenubar());
                Thread.sleep(2000);
                click(myAccountButton());
                Thread.sleep(2000);*/
                bringElementIntoViewDown(LogOutButton(), 3);
                click(LogOutButton());
                Thread.sleep(2000);
                click(LogoutYes());
            }
            LoginPage loginPage = new LoginPage(driver);
            return waitUntilDisplayed(loginPage.GuestLogin(), 60);
        } catch (Throwable e) {
            Reporter.log(e.toString());
            return false;
        }
    }

    public boolean navigateToListingPage(Header_FooterBarData headerFooterBarData) throws IllegalAccessException, InstantiationException, InterruptedException {

        click(MenuButton());
        if (waitUntilDisplayed(MenuBackButton(), 3)) {
            click(MenuBackButton());
        }
        if (headerFooterBarData.getMainmenu().contains(">")) {
            String[] values = headerFooterBarData.getMainmenu().split(">");
            for (String value : values) {
                bringElementIntoViewDown(listMenu(value), 15);
                click(listMenu(value));
                Thread.sleep(2000);
                //     waitUntilElementDisappear(listMenu(value),5);
            }

        } else {
            click(listMenu(headerFooterBarData.getMainmenu()));
        }
        ListingPage listingPage = new ListingPage(driver);
        String breadcrumb = listingPage.getBreadcrumbsValue().split(" ")[0];

        return headerFooterBarData.getMainmenu().contains(breadcrumb);
    }

    public void search(Header_FooterBarData headerFooterBarData) throws InterruptedException, IllegalAccessException, InstantiationException {
        if (waitUntilDisplayed(searchIcon(), 4)) {
            click(searchIcon());
        }
        ClearValue(searchLocator());
        EnterValue(searchLocator(), headerFooterBarData.get_search_keyword());
        if (waitUntilDisplayed(searchSuggestion(), 3)) {
            List<WebElement> searchSuggestion = getWebElements(searchSuggestion());
            for (WebElement search : searchSuggestion) {
                search.click();
                break;
            }
        } else {
            List<WebElement> topProduct = getWebElements(topProducts());
            for (WebElement element : topProduct) {
                Locator firstProduct = new Locator(element, By.xpath("//android.widget.RelativeLayout"), "First product");
                click(firstProduct);
            }
        }

        waitUntilElementDisappear(loader(), 15);
    }

    public boolean navigateToBrandsPage(Header_FooterBarData headerFooterBarData) throws IllegalAccessException, InstantiationException, InterruptedException {
        click(MenuButton());
        if (waitUntilDisplayed(MenuBackButton(), 4)) {
            click(MenuBackButton());
        }
        click(listMenu(headerFooterBarData.getMainmenu()));
        EnterValue(SearchBrandTextBox(), headerFooterBarData.getSubmenu());

        Thread.sleep(2000);
        click(BrandsName(headerFooterBarData.getSubmenu()));
        ListingPage listingPage = new ListingPage(driver);
        String breadcrumb = listingPage.getBreadcrumbsValue().split(" ")[0];
        return headerFooterBarData.getSubmenu().contains(breadcrumb);
    }

    public boolean navigateToOffersPage() throws IllegalAccessException, InstantiationException {
        click(MenuButton());
        if (waitUntilDisplayed(MenuBackButton(), 4)) {
            click(MenuBackButton());
        }
        click(offerButton());
        HomePage homePage = new HomePage(driver);
        return waitUntilDisplayed(homePage.offerHomePage(), 10);
    }

    public boolean navigateToStyleDiva() throws IllegalAccessException, InstantiationException, InterruptedException {
        click(MenuButton());
        if (waitUntilDisplayed(MenuBackButton(), 4)) {
            click(MenuBackButton());
        }
        bringElementIntoViewDown(styleDiva(), 5);
        click(styleDiva());
        HomePage homePage = new HomePage(driver);
        return waitUntilDisplayed(homePage.styleDivaPage(), 7);
    }

    public boolean navigateToHomePage() throws IllegalAccessException, InstantiationException {
        click(MenuButton());
        if (waitUntilDisplayed(MenuBackButton(), 4)) {
            click(MenuBackButton());
        }
        click(homeButton());
        HomePage homePage = new HomePage(driver);
        return waitUntilDisplayed(homePage.homePageLayout(), 10);
    }

    public boolean navigateToTnC() throws IllegalAccessException, InstantiationException, InterruptedException {
        click(ActionMenubar());
        click(helpCenter());
        bringElementIntoViewDown(TncLocator(), 5);
        click(TncLocator());
        HomePage homePage = new HomePage(driver);
        return waitUntilDisplayed(homePage.TnCPage(), 10);
    }

    public void navigateToMyOrders() throws IllegalAccessException, InstantiationException {
        click(ActionMenubar());
        click(trackOrder());
    }

    public boolean isMyAccountGuestLoginPresent(AccountData accountData) throws IllegalAccessError, InstantiationException, IllegalAccessException {
        click(ActionMenubar());
        click(MyAccountLoginButton());
        if (waitUntilDisplayed(GuestLoginPopUp(), 5)) {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(accountData);
            click(ActionMenubar());
            return waitUntilDisplayed(LoggedInUserName(), 5);
        } else return false;

    }

    public boolean isGuestReferPresent(AccountData accountData) throws IllegalAccessError, InstantiationException, IllegalAccessException {
        click(ActionMenubar());
        click(referAndEarn());
        if (waitUntilDisplayed(GuestLoginPopUp(), 5)) {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(accountData);
            return waitUntilDisplayed(InviteButton(), 5);
        } else return false;
    }

    public boolean isGuestTrackOrderPresent(AccountData accountData) throws IllegalAccessError, InstantiationException, IllegalAccessException {
        click(ActionMenubar());
        click(trackOrder());
        if (waitUntilDisplayed(GuestLoginPopUp(), 5)) {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(accountData);
            return waitUntilDisplayed(orderListing(), 5);
        } else return false;
    }

    public boolean isGuestSubscriptionPresent(AccountData accountData) throws IllegalAccessError, InstantiationException, IllegalAccessException {
        click(ActionMenubar());
        click(MySubscriptionButton());
        if (waitUntilDisplayed(GuestLoginPopUp(), 5)) {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(accountData);
            return waitUntilDisplayed(SubscriptionPage(), 5);
        } else return false;
    }

    public boolean isGuestWishlistPresent(AccountData accountData) throws IllegalAccessError, InstantiationException, IllegalAccessException {
        click(ActionMenubar());
        click(WishlistButton());
        if (waitUntilDisplayed(GuestLoginPopUp(), 5)) {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(accountData);
            return waitUntilDisplayed(WishlistParent(), 5);
        } else return false;
    }

    public boolean isGuestHelpCenterPresent(AccountData accountData) throws IllegalAccessError, InstantiationException, IllegalAccessException {
        click(ActionMenubar());
        click(helpCenter());
        if (waitUntilDisplayed(GuestLoginPopUp(), 5)) {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(accountData);
            return waitUntilDisplayed(contactUs(), 15);
        } else return false;
    }

    public boolean isGuestLoginPopUp() throws IllegalAccessError, InstantiationException, IllegalAccessException {
        return waitUntilDisplayed(GuestLoginPopUp(), 10);
    }

    public boolean isOrdersListed() {
        // return waitUntilDisplayed(orderListing(),10);
        return waitUntilDisplayed(orderPage(), 20);
    }

    public void openCart() throws IllegalAccessException, InstantiationException {
        click(CartIcon());
    }

    public void BackButtonClick() throws IllegalAccessException, InstantiationException {

        androidBackButton();
        //         click(BackButton());

    }

    public void clearCart() throws IllegalAccessException, InstantiationException, InterruptedException {
        CartPage cartPage = new CartPage(driver);
        click(CartIcon());
        if (waitUntilDisplayed(cartPage.ProductsInBagsLocator(), 4)) {
            cartPage.removeAllProduct();
        } else {
            androidBackButton();
        }
    }

    public boolean isAccountSectionPresent() throws IllegalAccessException, InstantiationException {
        click(ActionMenubar());
        //boolean accountSection = waitUntilDisplayed(myAccountButton(),5) && waitUntilDisplayed(referAndEarn(),5) && waitUntilDisplayed(notificationButton(),5) && waitUntilDisplayed(trackOrder(),5) && waitUntilDisplayed(helpCenter(),5) && waitUntilDisplayed(LogOutButton(),5) && waitUntilDisplayed(WishlistButton(),5);
        boolean accountSection = waitUntilDisplayed(myAccountButton(), 5) && waitUntilDisplayed(notificationButton(), 5) && waitUntilDisplayed(trackOrder(), 5) && waitUntilDisplayed(WishlistButton(), 5);
        androidBackButton();
        return accountSection;
    }

    public boolean isHelpCenterOpened() throws IllegalAccessException, InstantiationException {
        click(ActionMenubar());
        click(helpCenter());
        boolean helpCenter = waitUntilDisplayed(contactUs(), 10);
        androidBackButton();
        return helpCenter;
    }

    public boolean isMenuButtonPresent() {
        return waitUntilDisplayed(MenuButton(), 5);
    }

    public boolean isCartIconPresent() {
        return waitUntilDisplayed(CartIcon(), 5);
    }

    public boolean isActionBarPresent() {
        return waitUntilDisplayed(ActionMenubar(), 5);
    }

    public boolean isSearchBarPresesnt() {
        return waitUntilDisplayed(searchLocator(), 5);
    }

    public String searchTrendingByCategories() throws IllegalAccessException, InstantiationException, InterruptedException {
        waitUntilDisplayed(searchLocator(), 10);
        click(searchLocator());
        bringElementIntoViewDown(topCategoriesProduct(), 5);
        String categoryName = null;
        List<WebElement> productInTopCategories = getWebElements(topCategoriesProduct());
        for (WebElement product : productInTopCategories) {
            Locator firstproduct = new Locator(product, By.xpath("//android.widget.TextView[contains(@resource-id,'android:id/text1')]"), "1st Trending Product");
            categoryName = getText(firstproduct);
            click(firstproduct);
            break;

        }
        return categoryName;
    }

    public void pressBackButtonUntilHomePage() {
        while (isDisplayed(BackButton())) {
            androidBackButton();
        }
    }


    public void searchBarClick() throws InterruptedException, IllegalAccessException, InstantiationException {
        click(searchLocator());
    }

    public void searchIconClick() throws InterruptedException, IllegalAccessException, InstantiationException {
        if (waitUntilDisplayed(searchIcon(), 4)) {
            click(searchIcon());
        }
    }

    public void searchHistory(Header_FooterBarData headerFooterBarData) throws InterruptedException, IllegalAccessException, InstantiationException {

        ClearValue(searchLocator());

        if (waitUntilDisplayed(SearchHistoryClick(), 4)) {
            click(SearchHistoryClick());
        }
        waitUntilElementDisappear(loader(), 15);
    }

    public void searchKeyword(Header_FooterBarData headerFooterBarData) throws InterruptedException, IllegalAccessException, InstantiationException {
        //System.out.println("asd");
        //ClearValue(searchLocator());
        // EnterValue(searchLocator(), headerFooterBarData.get_search_keyword());
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);
        // driver.pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);
        //waitUntilElementDisappear(loader(),15 );
    }

    public String searchBytopBrands() throws IllegalAccessException, InstantiationException, InterruptedException {
        waitUntilDisplayed(searchLocator(), 10);
        click(searchLocator());
        driver.pressKeyCode(KeyEvent.KEYCODE_BACK);
        bringElementIntoViewDown(topBrands(), 5);
        String brandName = null;
        List<WebElement> productInTopCategories = getWebElements(topBrands());
        for (WebElement product : productInTopCategories) {
            Locator firstproduct = new Locator(product, By.xpath("//android.widget.TextView[contains(@resource-id,'android:id/text1')]"), "1st brand Product");
            brandName = getText(firstproduct);
            click(firstproduct);
            break;

        }
        return brandName;
    }
}


