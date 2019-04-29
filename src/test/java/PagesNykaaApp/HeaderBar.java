package PagesNykaaApp;

import FrameWorkNykaa.AppAction;
import FrameWorkNykaa.Locator;
import DataNykaa.AccountData;
import DataNykaa.Header_FooterBarDataApp;
import DataNykaa.ProductDetailData;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;


public class HeaderBar extends AppAction {

    public HeaderBar(AndroidDriver driver) {
        this.driver = driver;
    }


    /******************************************Locators*****************************************************************************/

    Locator ActionMenubar() {
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'menu_overflow')]"), "Action Menu bar");
    }

    Locator LoggedInUserName() {
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'name_layout')]//android.widget.EditText[contains(@resource-id, 'user_editable_detail')]"), "User name Text");
    }

    Locator myAccountButton(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'action_my_account')]"),"My Account");
    }

    Locator referAndEarn(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'refer_and_win')]"),"Refer And Earn");
    }

    Locator notificationButton(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'action_notifications')]"),"Notification");
    }

    Locator trackOrder(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'action_current_orders')]"),"Track Order");
    }

    Locator helpCenter(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'action_support_center')]"),"Help Center");
    }

    Locator LogOutButton() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'logout')]"), "Logout button");
    }

    Locator Logout_YesButton() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@text,'YES')]"), "Logout button");
    }

    Locator MenuButton() {
        return new Locator(By.xpath("//android.view.ViewGroup[contains(@resource-id,'action_bar')]//android.widget.ImageButton[@content-desc='Navigate up']"), "Menu Button");
    }

    Locator listMenu(String LableName) {
        return new Locator(By.xpath("//android.widget.TextView[@text='" + LableName + "']"), "category " + LableName + "");
    }

    Locator CartIcon() {
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'/action_shopping_cart')]"), "Cart Button");
    }

    Locator BackButton() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id,'goBackBtn')] | //android.widget.FrameLayout[contains(@resource-id,'/action_bar')]//android.widget.ImageButton[@index='0'] | //android.view.ViewGroup[contains(@resource-id,'action_bar')]//android.widget.ImageButton | //android.widget.TextView[contains(@resource-id,'btn_back')] | //android.widget.ImageView[contains(@resource-id,'ivClose')] | //android.widget.ImageButton ") , "Menu Back Button");
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

    Locator WishlistParent(){
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'wishlist_parent_layout')]"),"Wishlist Parent");
    }

    Locator homeButton(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'menu_item_label')][@text='Home']"),"Home Button");
    }

    Locator offerButton(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'menu_item_label')][@text='Offers']"),"Offer Button");
    }

    Locator searchLocator(){
        return new Locator(By.xpath("//android.widget.EditText[contains(@resource-id,'search_field')]"),"Search Bar");
    }

    Locator loader(){
        return new Locator(By.xpath("//android.widget.ProgressBar[contains(@resource-id,'progressBarMain')]"),"loader icon");
    }

    Locator searchIcon(){
        return new Locator(By.xpath("//android.widget.RelativeLayout[contains(@resource-id,'action_search')]"),"Search Icon");
    }

    Locator styleDiva(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'menu_item_label')][@text='Style Divas']"),"");
    }

    Locator TncLocator(){
        return new Locator(By.xpath("//android.view.View[@text='Terms & Conditions'] | //android.view.View[@content-desc='Terms & Conditions']"),"Terms And Conditions");
    }

    Locator contactUs(){
        return new Locator(By.xpath("//android.view.View[@text='CONTACT US']|//android.view.View[@content-desc='CONTACT US']"),"Contact Us");
    }

    Locator HaveConcern(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@text, 'HAVE A CONCERN?')]"), "Have a Concern?");
    }

    Locator topCategoriesProduct(){
        return new Locator(By.xpath("//android.widget.FrameLayout[contains(@resource-id,'card_view_top_category')]//android.widget.FrameLayout[contains(@resource-id,'root_layout_chipview')]"),"Top Categories");
    }

    Locator orderListing(){
        return new Locator(By.xpath("//android.view.View[contains(@resource-id,'app')]//android.view.View[contains(@text, 'ORDER')]"),"Order Listing");
    }

    Locator searchSuggestion(){
        return new Locator(By.xpath("//android.widget.ListView[contains(@resource-id,'searchList')]//android.widget.RelativeLayout | //android.widget.ExpandableListView[contains(@resource-id,'expandable_view_auto_suggestions')]//android.widget.LinearLayout"),"Search Suggestions");
    }

    Locator topProducts(){
        return new Locator(By.xpath("//android.support.v7.widget.RecyclerView[contains(@resource-id,'recycler_view_auto_suggestions')]//android.widget.LinearLayout"),"Top Products");
    }

    Locator MyAccountLoginButton(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tv_login_register_guest')]"),"GuestLoginButton MyAccount");
    }

    Locator GuestLoginPopUp(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'tvTitleLoginSignUp')]"),"GuestLoginPopUp");
    }

    Locator MySubscriptionButton(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id,'action_my_subscriptions')]"),"MySubscription");
    }
    Locator InviteButton() {
        return new Locator(By.xpath("//android.widget.Button[contains(@resource-id, 'btnInviteFriend')]"), "IR Button");
    }

    Locator SubscriptionPage() {
        return new Locator(By.xpath("//android.widget.TextView[contains(@resource-id, 'txt_toolbar_title')]"), "Subscription Page");
    }

    Locator AlertPopup(){
        return new Locator(By.xpath("//android.widget.TextView[contains(@text, 'Avoid the ₹50')]"), "Alert Pop-up");
    }

    /******************************************Services*****************************************************************************/

    public String getLoggedInUserName() throws IllegalAccessException, InstantiationException {
        try {
            click(ActionMenubar());
            click(myAccountButton());
            return getText(LoggedInUserName());
        }catch (Throwable e){
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
        for (WebElement element:webElements)
        {
            Locator ProductName = new Locator(element, By.xpath("//android.widget.TextView[contains(@resource-id,'txt_product_name')]"),"Product Name");
            String ProductNameFromUI = getText(ProductName);
            if (productName.contains(ProductNameFromUI))
            {
                isProductPresent = true;
                break;
            }
        }
        return isProductPresent;
    }

    public boolean Logout() throws IllegalAccessException, InstantiationException {
        try {
            ScrollDown(1);
            if (waitUntilDisplayed(LogOutButton(), 5)) {
                click(LogOutButton());
            } else {
                click(ActionMenubar());
                bringElementIntoViewDown(LogOutButton(),2);
                click(LogOutButton());
            }
            click(Logout_YesButton());
            LoginPage loginPage = new LoginPage(driver);
            return waitUntilDisplayed(loginPage.GuestLogin(), 60);
        }catch (Throwable e){
            Reporter.log(e.toString());
            return false;
        }
    }

    public boolean navigateToListingPage(Header_FooterBarDataApp headerFooterBarData) throws IllegalAccessException, InstantiationException, InterruptedException {

        click(MenuButton());
        if (waitUntilDisplayed(MenuBackButton(), 4)) {
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

    public void search(Header_FooterBarDataApp headerFooterBarData) throws InterruptedException, IllegalAccessException, InstantiationException {
        if (waitUntilDisplayed(searchIcon(),4)){
            click(searchIcon());
        }
        ClearValue(searchLocator());
        click(searchLocator());
        EnterValue(searchLocator(), headerFooterBarData.get_search_keyword());
        if(waitUntilDisplayed(searchSuggestion(),3)) {
            List<WebElement> searchSuggestion = getWebElements(searchSuggestion());
            for (WebElement search : searchSuggestion) {
                search.click();
                break;
            }
        }else {
            List<WebElement> topProduct = getWebElements(topProducts());
            for( WebElement element:topProduct){
                Locator firstProduct = new Locator(element , By.xpath("//android.widget.RelativeLayout"),"First product");
                click(firstProduct);
            }
        }

        waitUntilElementDisappear(loader(),15 );
    }

    public boolean navigateToBrandsPage(Header_FooterBarDataApp headerFooterBarData) throws IllegalAccessException, InstantiationException, InterruptedException {
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
        return waitUntilDisplayed(homePage.offerHomePage(),10);
    }

    public boolean navigateToStyleDiva() throws IllegalAccessException, InstantiationException, InterruptedException {
        click(MenuButton());
        if(waitUntilDisplayed(MenuBackButton(),4)){
            click(MenuBackButton());
        }
        bringElementIntoViewDown(styleDiva(),5);
        click(styleDiva());
        HomePage homePage = new HomePage(driver);
        return waitUntilDisplayed(homePage.styleDivaPage(),7);
    }

    public boolean navigateToHomePage() throws IllegalAccessException, InstantiationException {
        click(MenuButton());
        if (waitUntilDisplayed(MenuBackButton(), 4)) {
            click(MenuBackButton());
        }
        click(homeButton());
        HomePage homePage = new HomePage(driver);
        return waitUntilDisplayed(homePage.homePageLayout(),10);
    }

    public boolean navigateToTnC() throws IllegalAccessException, InstantiationException, InterruptedException {
        click(ActionMenubar());
        click(helpCenter());
        bringElementIntoViewDown(TncLocator(),5);
        click(TncLocator());
        HomePage homePage = new HomePage(driver);
        return waitUntilDisplayed(homePage.TnCPage() , 10);
    }

    public void navigateToMyOrders() throws IllegalAccessException, InstantiationException {
        click(ActionMenubar());
        click(trackOrder());
    }

    public boolean isMyAccountGuestLoginPresent(AccountData accountData) throws IllegalAccessError, InstantiationException, IllegalAccessException {
        click(ActionMenubar());
        click(MyAccountLoginButton());
        if (waitUntilDisplayed(GuestLoginPopUp(),5)) {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(accountData);
            click(ActionMenubar());
            return waitUntilDisplayed(LoggedInUserName(),5);
        }
        else return false;

    }

    public boolean isGuestReferPresent(AccountData accountData) throws IllegalAccessError, InstantiationException, IllegalAccessException {
        click(ActionMenubar());
        click(referAndEarn());
        if (waitUntilDisplayed(GuestLoginPopUp(),5)) {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(accountData);
            return waitUntilDisplayed(InviteButton(),5);
        }
        else return false;
    }

    public boolean isGuestTrackOrderPresent(AccountData accountData) throws IllegalAccessError, InstantiationException, IllegalAccessException {
        click(ActionMenubar());
        click(trackOrder());
        if (waitUntilDisplayed(GuestLoginPopUp(),5)){
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(accountData);
            return waitUntilDisplayed(orderListing(),5);
        }
        else return false;
    }

    public boolean isGuestSubscriptionPresent(AccountData accountData) throws IllegalAccessError, InstantiationException, IllegalAccessException {
        click(ActionMenubar());
        click(MySubscriptionButton());
        if (waitUntilDisplayed(GuestLoginPopUp(),5)){
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(accountData);
            return waitUntilDisplayed(SubscriptionPage(),5);
        }
        else return false;
    }

    public boolean isGuestWishlistPresent(AccountData accountData) throws IllegalAccessError, InstantiationException, IllegalAccessException {
        click(ActionMenubar());
        click(WishlistButton());
        if (waitUntilDisplayed(GuestLoginPopUp(),5)){
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(accountData);
            return waitUntilDisplayed(WishlistParent(),5);
        }
        else return false;
    }

    public boolean isGuestHelpCenterPresent(AccountData accountData) throws IllegalAccessError, InstantiationException, IllegalAccessException {
        click(ActionMenubar());
        click(helpCenter());
        if (waitUntilDisplayed(GuestLoginPopUp(),5)){
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(accountData);
            return waitUntilDisplayed(contactUs(),15);
        }
        else return false;
    }

    public boolean isGuestLoginPopUp() throws IllegalAccessError, InstantiationException, IllegalAccessException {
        return waitUntilDisplayed(GuestLoginPopUp(),10);
    }

    public boolean isOrdersListed(){
        return waitUntilDisplayed(orderListing(),10);
    }

    public void openCart() throws IllegalAccessException, InstantiationException {
        click(CartIcon());
    }

    public void BackButtonClick() throws IllegalAccessException, InstantiationException {
        click(BackButton());
    }

    public void clearCart() throws IllegalAccessException, InstantiationException, InterruptedException {
        CartPage cartPage = new CartPage(driver);
        click(CartIcon());
        if(isDisplayed(AlertPopup())){
            click(AlertPopup());
            bringElementIntoViewUp(cartPage.ProductsInBagsLocator(), 3);
        }
        if(waitUntilDisplayed(cartPage.ProductsInBagsLocator(), 4)){
            cartPage.removeAllProduct();
        }else {
            click(BackButton());
        }
    }

    public boolean isAccountSectionPresent() throws IllegalAccessException, InstantiationException {
        click(ActionMenubar());
        boolean accountSection = waitUntilDisplayed(myAccountButton(),5) && waitUntilDisplayed(referAndEarn(),5) && waitUntilDisplayed(notificationButton(),5) && waitUntilDisplayed(trackOrder(),5) && waitUntilDisplayed(helpCenter(),5)  && waitUntilDisplayed(WishlistButton(),5);
        driver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
        return accountSection;
    }

    public boolean isHelpCenterOpened() throws IllegalAccessException, InstantiationException {
        click(ActionMenubar());
        click(helpCenter());
        boolean helpCenter = waitUntilDisplayed(HaveConcern(),10);
        driver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
        return helpCenter;
    }

    public boolean isMenuButtonPresent(){
        return  waitUntilDisplayed(MenuButton(),5);
    }

    public boolean isCartIconPresent(){
        return waitUntilDisplayed(CartIcon(),5);
    }

    public boolean isActionBarPresent(){
        return waitUntilDisplayed(ActionMenubar(),5);
    }

    public boolean isSearchBarPresesnt(){
        return waitUntilDisplayed(searchLocator(),5);
    }

    public String searchTrendingByCategories() throws IllegalAccessException, InstantiationException, InterruptedException {
        waitUntilDisplayed(searchLocator(),10);
        click(searchLocator());
        bringElementIntoViewDown(topCategoriesProduct(),5);
        String categoryName = null;
        List<WebElement> productInTopCategories = getWebElements(topCategoriesProduct());
        for (WebElement product: productInTopCategories){
            Locator firstproduct = new Locator(product,By.xpath("//android.widget.TextView[contains(@resource-id,'android:id/text1')]"),"1st Trending Product");
            categoryName = getText(firstproduct);
            click(firstproduct);
            break;

        }
        return categoryName;
    }
}