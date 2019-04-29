package Mobile_Pages_Nykaa;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import DataNykaa.ProductDetailData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Framework;
import FrameWorkNykaa.Locator;
import FrameWorkNykaa.TestListner;
import PagesNykaa.ProductPage_React;


public class ListingPage_Mobile extends BrowserAction {

	public ListingPage_Mobile(WebDriver driver) {
		this.driver = driver;
	}

	Locator ShopAllLink() {
		return new Locator(By.xpath("//a[contains(text(),'shop all')]|//a[contains(text(),'SHOP ALL')]"), "Shop all Link");
	}
	
	
	Locator PostReviewButton() {
		return new Locator(By.xpath("//button[contains(text(),'POST REVIEW')]"), "Select POST REVIEW button");
	}
	Locator SelectReviewAllStars() {
		return new Locator(By.xpath("//div[@class='selecting-rating-star']//span[2]"), "Select Review All Stars");
	}
	
	Locator NickNameReviewAll() {
		return new Locator(By.xpath("//label[contains(text(),'Nick Name')]"), "Nick Name Review All");
	}

	Locator TitleReviewAll() {
		return new Locator(By.xpath("//label[contains(text(),'Review Title')]"), "Title Review All");
	}
	
	Locator DescReviewAll() {
		return new Locator(By.xpath("//label[contains(text(),'Description')]"), "Desc Review All");
	}
	Locator fullPageLoadingIcon() {
		return new Locator(By.id("overlay"), "full Page Loading Icon");
	}

	Locator filterLoader() {
		return new Locator(By.xpath("//div[@class='overlay-bg']"), "filter Loading Icon");
	}

	Locator NotifyMe(){
		return new Locator(By.xpath("//*[text()='Notify me']"),"Notify Me");


	}

	/*Locator filter(String FilterName) {
		return new Locator(By.xpath("//div[@filt-typ='" + FilterName + "']"), " Filter " + FilterName + "");
	}*/

	Locator filter(String FilterName) {
		return new Locator(By.xpath("//ul[contains(@class,'filter-keys')]//div[@class='filter-option-box__title'][contains(text(),'"+FilterName+"')]"), " Filter " + FilterName + "");
	}

	Locator FilterCheckbox() {
		return new Locator(By.xpath("/ancestor::div/ancestor::div/following-sibling::div[contains(@class,'slide-filter')]/div[@class='filter-options-list active']/div/div[@class='control-box']/label"),
				"Filter Checkbox");
	}

	Locator wishlistPane() {
		return new Locator(By.xpath("//p[contains(@class,'add-to-wishlist wishlist-id-')]"), "Wishlist pane");
	}

	/*Locator FilterCheckbox(String FilterCheckOption) {
		return new Locator(By.xpath("//a[@class='m-checkbox-unchecked'][@title='" + FilterCheckOption + "']"),
				"Filter Checkbox " + FilterCheckOption + "");
	}*/

	Locator PriceFilterCheckbox(String FilterCheckOption) {
		return new Locator(By.xpath("//input[@name='priceRangeCheckbox[]'][@id='"+FilterCheckOption+"']/parent::div/label/div"),
				"Filter Checkbox " + FilterCheckOption + "");
	}

	Locator FilterCheckbox(String FilterCheckOption) {
		return new Locator(By.xpath("//a[@class='m-checkbox-unchecked'][@title='" + FilterCheckOption + "']"),
				"Filter Checkbox " + FilterCheckOption + "");
	}

	Locator FilterOptions() {
		return new Locator(By.xpath("//div[@class='filter-options-list active']/div"),	"Filter Options");
	}

	Locator ClearAllFiltersLocator(){
		return new Locator(By.xpath("//span[@class='clear-filter']"),	"Clear All Filters");
	}

	Locator FilterButton(){
		return new Locator(By.xpath("//ul[@class='filterTab']//i"),"Filter Button On Listing Page");
	}

	Locator WidgetProducts() {
		return new Locator(
				By.xpath(
						"//div[@class='slick-slide slick-active']/descendant::a/ancestor::h3/following-sibling::div[@class='litre']"),
				"widget");

	}
	Locator MywishListForm(){
		return new Locator(By.id("my-wishlist"),"Wishlist form");
	}
	Locator DataType() {
		return new Locator(By.xpath("//p[@data]"), "Data type of Product");
	}

	Locator RatingBox() {
		return new Locator(By.xpath("//div[@class='rating-box']"), "Rating Box");
	}

	Locator ExtendedProductType() {
		return new Locator(By.xpath("//div[@class='litre']"), "Product type");
	}

	Locator SelectCity(String CityName) {
		return new Locator(By
				.xpath("//div[@class='city-content']//div[@class='city-box filter-active']//div[@class='city-label'][text()='"
						+ CityName + "']"),
				" City " + CityName + "");

	}

	Locator SelectRegionalLocation(String RegionName) {
		return new Locator(By.xpath(
				"//div[@class='location-content']//a[@class='label'][@title='" + RegionName.toUpperCase() + "']"),
				"Region Name");

	}

	Locator addToBagButtonOfWishlistProduct()
	{
		return new Locator(By.xpath("//p[contains(@class,'add-to-cart')]/a[@title='ADD TO BAG' or @class='button btn-cart' or @title='Add to Bag']"),"add to bag button");
	}

	Locator addToBagWishlistShadeSize()
	{
		return new Locator(By.xpath("//div[@title='ADD TO BAG']"),"add to bag button");
	}

	Locator SubmitButtonOFLocator() {
		return new Locator(By.xpath("//div[@class='loc_popup_submit']"), "Submit Button");
	}

	Locator LoaderIconForServicePage() {
		return new Locator(By.id("overlay"), "Loader Icon");
	}


	Locator WishlistedProduct(){
		return new Locator(By.xpath("//p[contains(@class,'add-to-wishlist wishlist-id')]"),"Wish Listed product");
	}

	public Locator widgetProducts() {
		return new Locator(
				By.xpath(
						"//div[@class='slick-slide slick-active']/li[contains(@id,'product-opt-')]|//ul[@class='product_list_ul slick-initialized slick-slider']//li[@class='n_prod_block product-box item slick-slide slick-active'][contains(@id,'product-opt-')]"),
				"widget Produts");
	}

	public Locator wishlistProducts() {
		return new Locator(By.xpath("//li[contains(@id,'product-opt-')]"), "wishlist product List");
	}


	Locator searchedProductsList() {
		return new Locator(By.xpath("//li[contains(@id,'product-opt-')]"), "search product List");
	}
	// div[@class='slick-slide slick-active']/li[contains(@id,'product-opt-')]
	// wishadded
	// Added to wishlist
	// Removed to wishlist
	// [contains(text(),'" + TextMessage + "')][contains(@Style,'block')]"
	Locator WishlistMessage(String ProductID, String TextMessage) {
		return new Locator(By.xpath(
				"//span[contains(@class,'wishlist_disp_msg wishlist_msg_" + ProductID + " " + TextMessage + "')]"),
				" " + TextMessage + "");

	}

	/*Locator OfferDetailText() {
		return new Locator(By.xpath("/ancestor::div/preceding-sibling::div[contains(@id,'offer_detail_l')]//ul//a"),
				"Offer Detail");
	}*/

	Locator OfferDetailText() {
		return new Locator(By.xpath("//div[@class='more-offers-wrap visible']/ul/li"),
				"Offer Detail");
	}

	
	
	public List<WebElement> bestSellerAnyCheckout() {

		List<WebElement> webElement = driver.findElements(By.xpath(
				"//div[@class='slick-slide slick-active']/descendant::a/ancestor::h3/following-sibling::div[@class='litre']"));
		return webElement;
	}

	public List<WebElement> AllProductAnyCheckout() {

		List<WebElement> webElement = driver.findElements(By.xpath(
				"//a[text()='All Products']/ancestor::div[@class='content_inner ']/following-sibling::div[@class='category-products content_inner']/descendant::div[@class='litre']"));
		return webElement;
	}

	public List<WebElement> AllServicesAnyCheckout() {

		List<WebElement> webElement = driver.findElements(By.xpath(
				"//a[text()='All Services']/ancestor::div[@class='content_inner ']/following-sibling::div[@class='category-products content_inner']/descendant::div[@class='litre']"));
		return webElement;
	}

	public List<WebElement> SearchedProductAnyCheckout() {

		List<WebElement> webElement = driver
				.findElements(By.xpath("//div[@class='ratings']/following-sibling::div[@class='litre']"));
		return webElement;
	}

	Locator peopleWhoboughtThisAlsoBought() {

		return new Locator(
				By.xpath(
						"//div[@class='bestseller-slider widget-sliderbox slick-initialized slick-slider']/button[@class='slick-prev slick-disabled']"),
				"people Also bought widget");
	}

	Locator Filter() {
		return new Locator(By.xpath("//ul[@class='filterTab']/li[1]"), "Filter Tab");
	}

	Locator SortDropDown() {
		return new Locator(By.xpath("//ul[@class='filterTab']/li[2]"), "Filter Tab");
	}
	Locator SortRadioButtons() {
		return new Locator(By.xpath("//div[@class='sort-wrap show-sort']/div[@class='ripple']"), "Filter Tab");
	}

	Locator PriceLocator() {
		return new Locator(By.xpath("//div[@class='price-info']/span"), "price");
	}

	Locator OutOfStockLabel() {
		return new Locator(
				By.xpath(
						"//div[@class='slick-slide slick-active']/descendant::a/ancestor::h3/following-sibling::div[@class='litre']"),
				"Out Of Stock");

	}

	Locator productList() {
		return new Locator(By.xpath("//div[@class='product-list-box card']"), "Product List");
	}

	Locator quantityplusButton() {
		return new Locator(By.xpath("//img[@id='Sym'][@class='update_p_qty'][not(contains(@style,'hidden'))]"),
				"quantity plus");
	}

	Locator AddToBagButton(String ProductName) {
		return new Locator(By.xpath("//div[@class='product-list-box card' and not(ancestor::div[contains(@class,'height-animate ')])]//span[contains(text(),'"+ProductName+"')]/ancestor::div[@class='product-list-box card']//button"),"Add To Bag");
	}

	Locator AddToBagButtonWidget(String ProductName) {
		return new Locator(By.xpath("//div[contains(@class,'widget_wrapper')]/div/div[@class='product-list-box card']//span[contains(text(),'"+ProductName+"')]/ancestor::div[@class='product-list-box card']//button"),"Add To Bag");
	}

	Locator AddToWishlistButton(String ProductName) {
		return new Locator(By.xpath("//div[@class='product-list-box card' and not(ancestor::div[contains(@class,'height-animate ')])]/meta[contains(@content,'"+ProductName+"')]/ancestor::div[@class='product-list-box card']//div[@class='listing_wishlist false']/span/i"),"Add To WishList");
	}

	Locator AddToWishlistButtonWidget(String ProductName) {
		return new Locator(By.xpath("//div[contains(@class,'widget_wrapper')]/div/div[@class='product-list-box card']//span[contains(text(),'"+ProductName+"')]/ancestor::div[@class='product-list-box card']//div[@class='listing_wishlist false']/span/i"),"Add To WishList");
	}

	Locator AddToBagButtonOfWishlist(String ProductID) {
		return new Locator(
				By.xpath("//p[contains(@class,'add-to-cart')]/a[contains(@onclick,'"
						+ ProductID
						+ "')][@title='ADD TO BAG' or @class='button btn-cart' or @title='Add to Bag']"),
				"Add To Button");
	}

	Locator AddToBagButtonFromView(){
		return new Locator(By.xpath("//div[@class='shades-addtocart']/button"),"Add To Bag Button");
	}



	Locator AddToBagButtonFromView(String productid){
		return new Locator(By.xpath("//div[@class='add-cartr-btn'][contains(@onclick,'"+productid+"')]"),"Add To Bag Button");
	}
	// a[contains(@onclick,'" + ProductID + "')][@class='button btn-cart']|

	Locator shades() {
		return new Locator(
				By.xpath("//span[contains(@class,'color-pallets')][not(contains(@class,'color-pallets-cross'))]"),"shades");
	}

	Locator shadesWishlist() {
		return new Locator(
				By.xpath("//ul[@class='mobile_prSizeList']/li[1][contains(@onclick,'checkMobileOption')]"),"shades");
	}


	Locator size() {
		return new Locator(
				By.xpath("//span[@class='size-pallets']/ancestor::li[@class!='opacity-dull']"),"size");
	}

	Locator sizesWishlist() {
		return new Locator(
				By.xpath("//ul[@class='mobile-module-box-center']/li/label[contains(@onclick,'selectMobileSize')]"),"sizes");
	}

	public Locator AllProducts() {
		return new Locator(
				By.xpath(
						"//a[text()='All Products']/ancestor::div[@class='content_inner ']/following-sibling::div[@class='category-products content_inner']/descendant::div[@class='litre']"),
				"All Product");

	}

	public Locator allProducts() {
		return new Locator(By.xpath("//div[@class='product-list-box card']"),"All Product");

	}

	// a[text()='All Products']/ancestor::div[@class='content_inner
	// ']/following-sibling::div[@class='category-products
	// content_inner']//ul[@id='product_list_ul']/li[contains(@id,'product-opt')]

	Locator bookNowButtonFromListing(String serviceName, String location) {
		return new Locator(
				By.xpath("//a[@title='" + serviceName
						+ "']/ancestor::div[contains(@id,'product-opt')]//a[@class='book_now show-slidecart-button']"),
				"book now button of " + serviceName + " ");
	}

	Locator extendedLocatorProductID() {
		return new Locator(By.xpath("//ancestor::div[@class='product-list-box card']/a"), "ProductId");
	}

	Locator seeShadeButton(String ProductId) {

		return new Locator(By
				.xpath("//p[contains(@class,'add-to-cart org_add')][@style='display: block;']/a[@title='PREVIEW SHADES' or contains(@title,'See Shades')][contains(@onclick,'"
						+ ProductId
						+ "')]|//p[contains(@class,'add-to-cart')]/a[@title='Add to Bag'][contains(@onclick,'"
						+ ProductId + "')]"),
				"preview Shade Button");
	}

	Locator ExtendedtagOfferLocator() {
		return new Locator(By.xpath("//span[@class='label label-primary']"), "Offer Tag");
	}

	Locator AllServices() {
		return new Locator(
				By.xpath(
						"//a[text()='All Services']/ancestor::div[@class='content_inner ']/following-sibling::div[@class='category-products content_inner']/descendant::div[@class='litre']"),
				"All Service");

	}

	Locator productName() {
		return new Locator(By.xpath("//div[@class='m-content__product-list__title']/h2"), "product Name");
	}

	Locator peopleAlsoBoughtProduts() {
		return new Locator(
				By.xpath(
						"//div[@id='product-onclick-widget-list']//div[@class='slick-slide slick-active']/li[contains(@id,'product-opt')]"),
				"people also bought");

	}

	Locator peopleWhoboughtFrame() {
		return new Locator(By.xpath("//div[@id='product-onclick-widget-list'][1]"), "people who bought");
	}

	Locator leftArrow() {
		return new Locator(By.xpath("//button[@class='slick-prev slick-disabled']"), "Left Click");
	}

	Locator ProductReviewcount() {

		return new Locator(By.xpath("//span[@class='review-label']"), "Review count");
	}

	Locator wishlistIcon(String productId) {
		return new Locator(By.xpath("//p[contains(@class,'add-to-wishlist wishlist-id-" + productId
				+ "')][contains(@style,'block')]/a[@class='add']"), "Wishlist Icon");
	}

	Locator ExtendedLocatorProductName() {
		return new Locator(By.xpath("//preceding-sibling::h3[@class='product-name']/a[1]"), " product Names");
	}

	Locator ExtendedLocatorProductType() {
		return new Locator(By.xpath("//ancestor::div[@class='product-list-box card']//button"),
				"Product Type");

	}

	Locator ProductName(String ProductName) {
		return new Locator(By.xpath("//div[@class='product-list-box card']//span[contains(text(),'" + ProductName +"')]")," " + ProductName + "");

	}

	Locator ProductLayoutByProductName(String ProductName)
	{
		return new Locator(By.xpath("//h3[@class='product-name']/a[contains(@title,\"" + ProductName + "\")]/ancestor::li[contains(@id,'product-opt')]"),
				" " + ProductName + "");
	}

	Locator DiscountTag() {
		return new Locator(By.xpath("//span[contains(@class,'post-card__offers-offer')]"), "Discount Tag");
	}

	Locator dropdown() {

		return new Locator(By.xpath("//select[@id='select_604']"), "Dropdown");
	}

	Locator selectSizeButton() {
		return new Locator(
				By.xpath(
						"//div[@class='ratings white-overlay']/following-sibling::p/a[text()='Add to Bag'or text()='ADD TO BAG']|//div[@class='litre white-overlay']/following-sibling::p/a[@title='Select Sizes']"),
				"Select Size Button");
	}

	Locator extendedSelectSizeButton() {
		return new Locator(By.xpath("//p/a[@title='Select Sizes']"), "Select Size Button");
	}

	Locator backMenu() {
		return new Locator(By.id("back_menu"), "Back Menu");
	}

	Locator addToBagButton(String productId) {
		return new Locator(By.xpath("//a[@title='ADD TO BAG'][contains(@onclick,'"+productId+"')]"), "Add To Bag Button");
	}

	Locator outOfStockMessage() {
		return new Locator(
				By.xpath(
						"//p[@id='exceed_message'][contains(text(),'out of stock') or contains(text(),'Out Of Stock') or contains(text(),'is not available.')]"),
				"Out of Stock");
	}

	Locator breadCrumb() {
		return new Locator(By.xpath("//h1[@class='page-title']"), "Page title");
	}

	Locator totalSearchedProductLocator() {

		return new Locator(By.id("totalprod"), "total Search product result text");
	}

	Locator closeButton() {
		return new Locator(By.id("closelb"), "close button");
	}

	Locator closeLink(String ProductId) {
		return new Locator(By.xpath("//a[@id='closebutn'][contains(@onclick,'" + ProductId + "')]"), "close link");
	}

	Locator backButton() {
		return new Locator(By.id("closebutn_alert"), "Back Icon");

	}

	Locator showMoreButton() {
		return new Locator(By.xpath("//div[contains(@class,'show_more_widget')]/button[contains(text(),'Show More')]"), "Show More");

	}

	Locator widgetProductsLocator()
	{
		return new Locator(By.xpath("//div[@name='widget_listing_0']/ancestor::div/div/div/div/div/div[@class='product-list-box card']//div[@class='m-content__product-list__title']"),"Widget Products");
	}

	Locator widgests (){
		return new Locator(By.xpath("//div[contains(@name,\"widget_listing\")]"), "Listing Page Widgets");
	}

	Locator allProductsLocator()
	{
		return new Locator(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div/div[contains(@class,'product-list-box card')]"),"All Products");
	}

	Locator scrollToTopLocator(){
		return new Locator(By.xpath("//div[@class='scroll-to-top']"),"Scroll to top icon");
	}

	Locator BestOffersMissedframeLocator()
	{
		return new Locator(By.xpath("//iframe[@id='__ta_notif_frame_2']"),"Missed Best Offers Frame");
	}

	Locator frameCloseButton()
	{
		return new Locator(By.xpath("//div[@class='close']"),"Frame close button");
	}

	Locator ApplyFilterButton()
	{
		return new Locator(By.xpath("//button[text()='Apply']"),"Apply button");
	}

	Locator shadeImageLocator()
	{
		return new Locator(By.xpath("/ancestor::label/ancestor::div"),"Shade image");
	}

	Locator filterCount()
	{
		return new Locator(By.xpath("//span[@class='filter-count']"), "Filter Count Locator");
	}

	Locator LoadMoreButton() {
		return new Locator(By.xpath("//button[@class='primary-btn common-btn'][contains(text(),'Load More')]"),	"Load More button");
	}

	Locator CountofFilter(){
		return new Locator(By.xpath("//div[@class='flex-1 total-result']"), "Count of Filter");
	}

	public Locator newsletterText()
	{
		return new Locator(By.xpath("//div[@class='m-footer__newsletter-text']"),"newsletter textbox");
	}

	public Locator reactModalPrice()
	{
		return new Locator(By.xpath("//div[contains(@class,'ReactModal__Content ')]//span[@class='post-card__content-price-offer']"),"Price");
	}
	
	Locator featuredTag() {
		return new Locator(By.xpath("/a//span[contains(text(),'featured')]"),"Featured tag");
	}

	Locator allProductsSearchLocator(){
		return new Locator(By.xpath("//div[@class='m-content__product-list']/div[1]/div[contains(@class,'product-list-box card')]"), "All Products List from Search window");
	}
	
	public ProductDetailData addAnyShadeProductFromWidget() throws Throwable {
		CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
			click(CartPage_Mobile.BackMenuCart());
		}
		return addProductFromList("shade",true);
	}

	public ProductDetailData addAnyShadeProductFromPeopleWhoBoughtWidget() throws Throwable {
		CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
			click(CartPage_Mobile.BackMenuCart());
		}
		bringElementIntoView(peopleWhoboughtFrame());
		click(leftArrow());
		return addProductFromList("shade",true);
	}


	public ProductDetailData addAnySimpleProductFromWidget() throws Throwable {
		CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
			click(CartPage_Mobile.BackMenuCart());
		}
		return addProductFromList("simple",true);
	}

	public ProductDetailData addAnySimpleProductFromWishlist() throws Throwable {
		CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
			click(CartPage_Mobile.BackMenuCart());
		}
		waitUntilDisplayed(MywishListForm(), 20);
		return addProductFromWishList("simple");
	}

	public ProductDetailData addAnySimpleProductFromAllProducts() throws Throwable {
		CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
			click(CartPage_Mobile.BackMenuCart());
		}
		return addProductFromList ("simple",false);
	}

	public ProductDetailData addAnySimpleProductFromSearchResult() throws Throwable {
		CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
			click(CartPage_Mobile.BackMenuCart());
		}
		return addProductFromSearch("simple",false);
	}

	public void navigateToShopAll(String PageName, String environmentURL) throws Throwable, Throwable {

		for(WebElement shopall:driver.findElements(ShopAllLink().getBy()))
		{
			if(shopall.isDisplayed())
			{
				bringElementIntoViewWebElement(shopall);
				clickandNavigateWindow(shopall, PageName, "Shop All button",environmentURL);
				driver.get(driver.getCurrentUrl().replace("http://www.nykaa.com", environmentURL));
				break;
			}
		}
	}

	public ProductDetailData addAnyShadeProductFromAllProduct() throws Throwable {
		closeGetAppPopUp();
		CartPage_Mobile_New CartPage_Mobile = new CartPage_Mobile_New(driver);
		if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
			click(CartPage_Mobile.BackMenuCart());
			waitUntilDisappear(CartPage_Mobile.BackMenuCart());
		}
		return addProductFromList("shade",false);

	}

	
	public ProductDetailData addAnyShadeProductFromAllProductNykaaMan() throws Throwable {
		try {
		closeGetAppPopUp();
		}
		catch(Exception e)
		{
			
		}
		CartPage_Mobile_New CartPage_Mobile = new CartPage_Mobile_New(driver);
		if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
			click(CartPage_Mobile.BackMenuCart());
			waitUntilDisappear(CartPage_Mobile.BackMenuCart());
		}
		return addProductFromList("shade",false);

	}

	public ProductDetailData addAnyShadeProductwishlist() throws Throwable {
		CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
			click(CartPage_Mobile.BackMenuCart());
			waitUntilDisappear(CartPage_Mobile.BackMenuCart());
		}
		return addProductFromWishList("shade");

	}

	public ProductDetailData addAnySizeProductwishlist() throws Throwable {
		CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
			click(CartPage_Mobile.BackMenuCart());
			waitUntilDisappear(CartPage_Mobile.BackMenuCart());
		}
		return addProductFromWishList("size");

	}
	
	
	public ProductDetailData addAnyStaticComboProductwishlist() throws Throwable {
		CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
			click(CartPage_Mobile.BackMenuCart());
			waitUntilDisappear(CartPage_Mobile.BackMenuCart());
		}
		return addProductFromWishList("bundle");

	}


	public ProductDetailData addAnyStaticProductFromAllProduct() throws Throwable {
		CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
			click(CartPage_Mobile.BackMenuCart());
			waitUntilDisappear(CartPage_Mobile.BackMenuCart());
		}
		return addStaticComboFromList(allProducts());

	}

	public ProductDetailData addSimpleProductFromAllProduct() throws Throwable {
		CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
			click(CartPage_Mobile.BackMenuCart());
		}
		return addProductFromList("simple",false);

	}

	public ProductDetailData addAnySizeProductFromAllProduct() throws Throwable {
		CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
			click(CartPage_Mobile.BackMenuCart());
		}
		return addProductFromList("size",false);

	}

	public ProductDetailData addAnySizeProductFromWidget() throws Throwable {
		CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
			click(CartPage_Mobile.BackMenuCart());
		}
		return addProductFromList("size",true);

	}

	public List<ProductDetailData> getProductList() throws Throwable, Throwable {

		int productCount = driver.findElements(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div/div[@class='product-list-box card']")).size();
		List<WebElement> listWeb  = driver.findElements(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div/div[@class='product-list-box card']"));
		List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
		for (int i=1;i<=productCount;i++) {
			try {
				ProductDetailData productDetail = new ProductDetailData();
				System.out.println(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div[" + i + "]/div[@class='product-list-box card']//div[@class='m-content__product-list__title']")));
				productDetail.setProductName(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div[" + i + "]/div[@class='product-list-box card']//div[@class='m-content__product-list__title']")));
				System.out.println(replaceRuppeeSymbol(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div[" + i + "]/div[@class='product-list-box card']//span[@class='post-card__content-price-offer']"))));
				productDetail.setProductPrize(replaceRuppeeSymbol(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div[" + i + "]/div[@class='product-list-box card']//span[@class='post-card__content-price-offer']"))));
				System.out.println(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div[" + i + "]/div[@class='product-list-box card']//button")));
				productDetail.setProductType(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div[" + i + "]/div[@class='product-list-box card']//button")));
				productList.add(productDetail);
			}catch(Throwable e){
			}

		}

		return productList;

	}

		/*for(WebElement web : listWeb)
		{
			ProductDetailData productDetail = new ProductDetailData();
			System.out.println(web.getText());
			productDetail.setProductName(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//div[@class='m-content__product-list__title']")));
			System.out.println(replaceRuppeeSymbol(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//span[@class='post-card__content-price-offer']"))));
			productDetail.setProductPrize(replaceRuppeeSymbol(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//span[@class='post-card__content-price-offer']"))));
			System.out.println(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//button")));
			productDetail.setProductType(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//button")));
			productList.add(productDetail)
		}*/

	public List<ProductDetailData> getProductNameList_Magento() throws Throwable, Throwable {

		List <WebElement> ProductList = driver.findElements(By.xpath("//div[@class='category-products content_inner']//ul//li//h3/a"));
		List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
		for (WebElement productElement : ProductList) {

			ProductDetailData productDetail = new ProductDetailData();
			System.out.println(productElement.getAttribute("title"));
			productDetail.setProductName(productElement.getAttribute("title"));
			productList.add(productDetail);

		}

		return productList;

	}



	public void ScrollDown(String pixels, int numberOfTime) throws Throwable {

		for (int x = 1; x <= numberOfTime; x++) {
			ScrollDown(pixels);
			Thread.sleep(5000);// TODO : need to make wait until load
		}

	}

	public void ScrollDownTillEnd() throws Throwable {

		while((((Long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight;"))-((Long) ((JavascriptExecutor) driver).executeScript("return window.pageYOffset;")))>1000)
		{
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			waitForPagetoLoad();
			Thread.sleep(4000);
		}
	}

	private boolean IncreaseQuantityOfProduct(WebDriver browser) throws InterruptedException, Throwable, Throwable {
		boolean isIncreasedSuccessfully = false;
		WebDriverWait ww = new WebDriverWait(browser, 50);
		while (isFreeShipping(browser)) {
			waitUntilDisplayed(quantityplusButton(), 4);
			click(quantityplusButton());
			isIncreasedSuccessfully = true;
			if (isProductOutOfStockinBag(browser)) {
				click(backMenu());
				isIncreasedSuccessfully = false;
				Thread.sleep(2000);
				break;
			}
			if (isProductquantityExceeded()) {
				browser.findElement(By.id("closelb")).click();
				Thread.sleep(4000);
				click(backMenu());
				isIncreasedSuccessfully = false;
				Thread.sleep(2000);
				break;
			}

			if (isAlertPresent(browser)) {
				browser.switchTo().alert().dismiss();
				isIncreasedSuccessfully = false;
				break;

			} else
				ww.until(ExpectedConditions.invisibilityOfElementLocated(By.className("update_loader")));

			// waitForJQuery(browser);

		}
		return isIncreasedSuccessfully;
	}

	private boolean isProductquantityExceeded() {
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		try {
			WebDriverWait ww2 = new WebDriverWait(driver, 4);
			driver.findElement(By.xpath("//p[@id='exceed_message'][contains(text(),'5 quantities')]"));

			return true;
		} // try
		catch (Exception Ex) {
			return false;
		} // catch
		finally {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}

	private boolean isProductOutOfStockinBag(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		try {
			WebDriverWait ww2 = new WebDriverWait(driver, 4);
			driver.findElement(By.xpath(
					"//p[@class='item-msg error'][contains(text(),'currently out of stock')] | //span[@class='error-msg'] [contains(text(),'The requested quantity is not available')]"));

			return true;
		} // try
		catch (Exception Ex) {
			return false;
		} // catch
		finally {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}

	private boolean isFreeShipping(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriverWait ww = new WebDriverWait(driver, 5);
		try {
			ww.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()='Free Shipping']")));
			return true;
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}

	public int getProductCountOfAllProducts() {


		return getWebElements(allProducts()).size();

	}

	public int getSearchedProductCount(){
		return getWebElements(wishlistProducts()).size();
	}

	public String verifyOfferOn(Locator locator) throws Throwable, Throwable {
		driver.navigate().refresh();
		waitForPagetoLoad();
		if(isDisplayed(showMoreButton()))
		{
			bringElementIntoView(showMoreButton());
			ScrollDown("30");
			click(showMoreButton());
		}
		Locator OfferLocators = locator.concatLocator(ExtendedtagOfferLocator());
		List<WebElement> productList = getWebElements(OfferLocators);
		if(productList.isEmpty())
		{
			return null;
		}
		String Offertext ="";
		for (WebElement offer : productList) {
			Locator Offer = new Locator(By.xpath(getAbsoluteXPath(offer)), "offer");
			bringElementIntoView(Offer);
			System.out.println(offer.toString());
			ScrollDown("50");
			Thread.sleep(1000);
			offer.click();
			waitForPagetoLoad();
			List<WebElement> offerTextLocators=getWebElements(OfferDetailText());
			for(WebElement we: offerTextLocators)
			{
				Offertext= Offertext+" || "+(we.getText());
			}
			TestListner.testing.get().log(LogStatus.INFO, "Offer Text  " + Offertext);
			System.out.println(Offertext);
			break;
		}
		driver.navigate().back();
		return Offertext;

	}

	/*public ProductDetailData addSimpleProductFromList(Locator locator) throws Throwable, Throwable {
		ProductDetailData productDetailData = new ProductDetailData();
		String ProductName = null;
		List<WebElement> productList = getWebElements(locator);
		boolean isProductAdded = false;
		for (WebElement product : productList) {
			Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
			Locator extendedLocator = ProductLocator.concatLocator(productName());
			System.out.println(extendedLocator);
			ProductName = getText(extendedLocator);
			String ProductType = getText(ProductLocator);
			if (!ProductType.contains("Shade") || !ProductType.contains("Size")) {
				//Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
				isProductAdded = addToBagSimple(ProductLocator);
				CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
				HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(driver);
				if (isProductAdded == true) {
					if (!waitUntilDisplayed(CartPage_Mobile.SlidingCartLogo(), 4)) {
						bringElementIntoView(HeaderBar_Mobile.CartLocator());
						click(HeaderBar_Mobile.CartLocator());
						if (isInStockMessageInCart()) {

						}
						if (isAlertPresent(driver)) {
							switchToDefaultContent();
							isProductAdded = false;
						}


	 * else if (CartPage_Mobile.IncreaseQuantityOfProduct(driver))
	 * { isProductAdded = true;
	 * 
	 * }

					}
				}

				if (isProductAdded) {
					productDetailData.setProductName(ProductName);
					break;
				}
			}
		}
		return productDetailData;
	}
	 */
	private ProductDetailData addStaticComboFromList(Locator locator) throws Throwable, Throwable {
		ProductDetailData productDetailData = new ProductDetailData();
		String ProductName = null;
		List<WebElement> productList = getWebElements(locator);
		boolean isProductAdded = false;
		for (WebElement product : productList) {
			Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
			Locator extendedLocator = ProductLocator.concatLocator(ExtendedLocatorProductName());

			System.out.println(extendedLocator);
			ProductName = getText(extendedLocator);
			String ProductType = getAttribute(ProductLocator.concatLocator(DataType()), "data");

			if (ProductType.contains("bundle")) {
				Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
				isProductAdded = addToBagSimple(productLocator);
				CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
				HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(driver);
				if (isProductAdded == true) {
					if (!waitUntilDisplayed(CartPage_Mobile.SlidingCartLogo(), 4)) {

						bringElementIntoView(HeaderBar_Mobile.CartLocator());
						click(HeaderBar_Mobile.CartLocator());
						if (isInStockMessageInCart()) {

						}
						if (isAlertPresent(driver)) {
							switchToDefaultContent();
							isProductAdded = false;
						}

						/*
						 * else if (CartPage_Mobile.IncreaseQuantityOfProduct(driver))
						 * { isProductAdded = true;
						 * 
						 * }
						 */
					}
				}

				if (isProductAdded) {
					productDetailData.setProductName(ProductName);
					break;
				}
			}

		}
		return productDetailData;
	}


	/**
	 * @return
	 */
	private boolean isInStockMessageInCart() {
		// TODO Auto-generated method stub

		return false;
	}

	public boolean isCustomerAlsoViewedPresent() throws Throwable {
		String ProductName = null;
		String ProductType=null;
		boolean isProductAdded = false;
		int productCount = driver.findElements(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div/div[@class='product-list-box card']")).size();
		for (int i=1;i<=productCount;i++) {
			String ProductName_updated="";
			System.out.println(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//div[@class='m-content__product-list__title']")));
			ProductName=geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//div[@class='m-content__product-list__title']"));
			String[] name=ProductName.split(" ");
			for(int j=0;j<name.length;j++)
			{
				if(! name[j].contains("'"))
					ProductName_updated=ProductName_updated+" "+name[j];
			}
			ProductName_updated=ProductName_updated.trim();
			//System.out.println(fw.replaceRuppeeSymbol(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//span[@class='post-card__content-price-offer']"))));
			//productDetail.setProductPrize(fw.replaceRuppeeSymbol(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//span[@class='post-card__content-price-offer']"))));
			System.out.println(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//button")));
			ProductType=geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//button"));

			boolean isWishlist = false;
			if (ProductType.contains("SHADES")) {
				addToBagForShades(AddToBagButton(ProductName_updated),isWishlist);
				isProductAdded = waitUntilDisplayed(peopleWhoboughtThisAlsoBought(), 10);
				break;
			} 
			else if(ProductType.contains("SIZE")) {
				addToBagForSize(AddToBagButton(ProductName_updated), isWishlist);
				isProductAdded = waitUntilDisplayed(peopleWhoboughtThisAlsoBought(), 10);
				break;
			}

		}
		return isProductAdded;
	}

	public boolean addToWishList(boolean isWidget)	throws IllegalArgumentException, IllegalAccessException, InstantiationException, Throwable {

		closeMissedOfferFrame();

		String ProductName = null;
		String ProductName_updated="";
		int productCount=0;
		boolean addedToWishlist=false;
		if(isWidget)
		{
			productCount=driver.findElements(widgetProductsLocator().getBy()).size();
		}
		else
		{
			productCount = driver.findElements(allProductsLocator().getBy()).size();
		}
		for (int i=1;i<=productCount;i++) {

			//System.out.println(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//div[@class='m-content__product-list__title']")));
			if(isWidget)
			{
				ProductName=geteText(By.xpath("//div[@name='widget_listing_0']/ancestor::div/div/div/div/div["+i+"]/div[@class='product-list-box card']//div[@class='m-content__product-list__title']"));
			}
			else
			{
				ProductName=geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//div[@class='m-content__product-list__title']"));
			}
			String[] name=ProductName.split(" ");
			for(int j=0;j<name.length;j++)
			{
				if(! name[j].contains("'"))
					ProductName_updated=ProductName_updated+" "+name[j];

			}
			if(ProductName_updated.contains("...")) {
				String[] name1 = ProductName_updated.split("...");
				ProductName_updated = name1[0];
			}
			ProductName_updated=ProductName_updated.trim();

			if(isWidget)
			{
				System.out.println(AddToWishlistButtonWidget(ProductName_updated).getBy());
				bringElementIntoView(AddToWishlistButtonWidget(ProductName_updated));
				ScrollDown("50");

				Thread.sleep(1000);

				//REMOVE FROM WISHLIST IF THE PRODUCT ALREADY IS IN WISHLIST
				if(getAttribute(AddToWishlistButtonWidget(ProductName_updated), "class").equalsIgnoreCase("fa fa-heart"))
				{
					click(AddToWishlistButtonWidget(ProductName_updated));
					Thread.sleep(3000);
				}
				bringElementIntoView(AddToWishlistButtonWidget(ProductName_updated));
				ScrollDown("50");
				click(AddToWishlistButtonWidget(ProductName_updated));
				Thread.sleep(3000);
				bringElementIntoView(AddToWishlistButtonWidget(ProductName_updated));
				ScrollDown("50");
				if(getAttribute(AddToWishlistButtonWidget(ProductName_updated), "class").equalsIgnoreCase("fa fa-heart"))
				{
					addedToWishlist=true;
					break;
				}
			}
			else
			{
				bringElementIntoView(AddToWishlistButton(ProductName_updated));
				ScrollDown("50");
				//REMOVE FROM WISHLIST IF THE PRODUCT ALREADY IS IN WISHLIST
				if(getAttribute(AddToWishlistButton(ProductName_updated), "class").equalsIgnoreCase("fa fa-heart")||getAttribute(AddToWishlistButton(ProductName_updated), "class").equalsIgnoreCase("fa fa-heart luxury_clr"))
				{
					click(AddToWishlistButton(ProductName_updated));
					Thread.sleep(4000);
				}
				bringElementIntoView(AddToWishlistButton(ProductName_updated));
				ScrollDown("50");
				click(AddToWishlistButton(ProductName_updated));
				waitUntilDisplayed(AddToWishlistButton(ProductName_updated), 10);
				if(getAttribute(AddToWishlistButton(ProductName_updated), "class").equalsIgnoreCase("fa fa-heart")||getAttribute(AddToWishlistButton(ProductName_updated), "class").equalsIgnoreCase("fa fa-heart luxury_clr"))
				{
					addedToWishlist=true;
					break;
				}
			}
		}

		return addedToWishlist;

	}


	private ProductDetailData addProductFromWishList(String type) throws Throwable {
		ProductDetailData productDetailData = new ProductDetailData();
		CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		String ProductName = null;
		boolean isProductAdded = false;
		String ProductName_updated="";
		ProductName=geteText(By.xpath("//h3[@class='product-name']/a"));
		String[] name=ProductName.split(" ");
		for(int j=0;j<name.length;j++)
		{
			if(! name[j].contains("'"))
				ProductName_updated=ProductName_updated+" "+name[j];
		}
		ProductName_updated=ProductName_updated.trim();

		if (type.equalsIgnoreCase("shade")) {
			isProductAdded = addToBagForShades(addToBagButtonOfWishlistProduct(),true).get("isShadeAvailable").equals("true");
		}

		else if (type.equalsIgnoreCase("size")) {
			isProductAdded = addToBagForSize(addToBagButtonOfWishlistProduct(),true).get("isSizeAvailable").equals("true");
		}
		else if (type.equalsIgnoreCase("simple"))
		{
			isProductAdded = addSimpleProduct(addToBagButtonOfWishlistProduct(), true);
		}
		else if (type.equalsIgnoreCase("bundle")) {
			isProductAdded = addStaticComboProduct(addToBagButtonOfWishlistProduct(),true);
		}
		if (isProductAdded == true) {
			driver.navigate().back();
			if (!waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 4)) {
				CartPage_Mobile.goToCart();
				if (isInStockMessageInCart()) {

				}
				if (isAlertPresent(driver)) {
					switchToDefaultContent();
					isProductAdded = false;
				}

			}
		}


		if (isProductAdded) {

			productDetailData.setProductName(ProductName);

		}
		return productDetailData;
	}

	private ProductDetailData addProductFromList(String type, boolean isWidget) throws Throwable {
		ProductDetailData productDetailData = new ProductDetailData();
		Map<String,String> resultMap=new HashMap<String,String>();
		//CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		String ProductName = null;
		String ProductType=null;
		String ProductPrice=null;
		boolean isProductAdded = false;
		//int widgetCount=0;
			int productCount = 0;
			if (isWidget) {
				if (isDisplayed(showMoreButton())) {
					bringElementIntoView(showMoreButton());
					ScrollDown("30");
					click(showMoreButton());
				}
				productCount = driver.findElements(widgetProductsLocator().getBy()).size();
			} else {
				productCount = driver.findElements(allProductsLocator().getBy()).size();
			}
			for (int i = 1; i <= productCount; i++) {
				String ProductName_updated = "";
				//System.out.println(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//div[@class='m-content__product-list__title']")));
				if (isWidget) {
					ProductName = geteText(By.xpath("//div[@name='widget_listing_0']/ancestor::div/div/div/div/div[" + i + "]/div[@class='product-list-box card']//div[@class='m-content__product-list__title']"));
					ProductPrice = geteText(By.xpath("//div[@name='widget_listing_0']/ancestor::div/div/div/div/div[" + i + "]/div[@class='product-list-box card']//div[@class='m-content__product-list__title']/following-sibling::div/div[@class='price-info']/span")).replaceAll("[^0-9]", "");
				} else {
					ProductName = geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div[" + i + "]/div[contains(@class,'product-list-box card')]//div[@class='m-content__product-list__title']"));
					ProductPrice = geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div[" + i + "]/div[contains(@class,'product-list-box card')]//div[@class='m-content__product-list__title']/following-sibling::div/div[@class='price-info']/span")).replaceAll("[^0-9]", "");
				}
				String[] name = ProductName.split(" ");
				for (int j = 0; j < name.length; j++) {
					if (!name[j].contains("'"))
						ProductName_updated = ProductName_updated + " " + name[j];
				}
				ProductName_updated = ProductName_updated.trim();
				//System.out.println(fw.replaceRuppeeSymbol(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//span[@class='post-card__content-price-offer']"))));
				//productDetail.setProductPrize(fw.replaceRuppeeSymbol(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//span[@class='post-card__content-price-offer']"))));
				//System.out.println(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//button")));
				if (isWidget) {
					ProductType = geteText(By.xpath("//div[@name='widget_listing_0']/ancestor::div/div/div/div/div[" + i + "]/div[@class='product-list-box card']//button"));
				} else {
					ProductType = geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div[" + i + "]/div[contains(@class,'product-list-box card')]//button"));
				}


				boolean isWishlist = false;
				if (ProductType.contains("BAG") && type.equalsIgnoreCase("simple")) {
					ProductType = "Simple";
				} else if (ProductType.contains("SHADES") && type.equalsIgnoreCase("shade")) {
					ProductType = "Shade";
					//isWishlist = true;
				} else if (ProductType.contains("SIZE") && type.equalsIgnoreCase("size")) {
					ProductType = "Size";
					//isWishlist = true;
				}


				if (ProductType.contains("Shade")) {
					TestListner.testing.get().log(LogStatus.INFO, "Trying to Add " + ProductName + " to cart");
					if (isWidget) {
						resultMap = addToBagForShades(AddToBagButtonWidget(ProductName_updated), false);
						isProductAdded = resultMap.get("isShadeAvailable").equals("true");
						ProductPrice = resultMap.get("price");
					} else {
						resultMap = addToBagForShades(AddToBagButton(ProductName_updated), false);
						isProductAdded = resultMap.get("isShadeAvailable").equals("true");
						ProductPrice = resultMap.get("price");
					}
				} else if (ProductType.contains("Size")) {
					TestListner.testing.get().log(LogStatus.INFO, "Trying to Add " + ProductName + " to cart");
					if (isWidget) {
						resultMap = addToBagForSize(AddToBagButtonWidget(ProductName_updated), false);
						isProductAdded = resultMap.get("isSizeAvailable").equals("true");
						ProductPrice = resultMap.get("price");
					} else {
						resultMap = addToBagForSize(AddToBagButton(ProductName_updated), false);
						isProductAdded = resultMap.get("isSizeAvailable").equals("true");
						ProductPrice = resultMap.get("price");
					}
				} else if (ProductType.contains("Simple")) {
					TestListner.testing.get().log(LogStatus.INFO, "Trying to Add " + ProductName + " to cart");
					if (isWidget) {
						isProductAdded = addSimpleProduct(AddToBagButtonWidget(ProductName_updated), isWishlist);
					} else {
						isProductAdded = addSimpleProduct(AddToBagButton(ProductName_updated), isWishlist);
					}
				}

				if (isProductAdded == true) {
					TestListner.testing.get().log(LogStatus.INFO, ProductName + " added to cart successfully! Checking if the product is present in Cart.");
				/*if (!waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 4)) {
					CartPage_Mobile.goToCart();
					if (isInStockMessageInCart()) {

					}
					if (isAlertPresent(driver)) {
						switchToDefaultContent();
						isProductAdded = false;
					}

				}*/
				}


				if (isProductAdded) {

					productDetailData.setProductName(ProductName);
					productDetailData.setProductPrize(ProductPrice);
				/*try {
					if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
						closeGetAppPopUp();
						click(CartPage_Mobile.BackMenuCart());
						waitUntilDisappear(CartPage_Mobile.BackMenuCart());}
				}
				catch(Exception e) {}*/

					break;
				}

			}
		return productDetailData;
	}

	private ProductDetailData addProductFromSearch(String type, boolean isWidget) throws Throwable {
		ProductDetailData productDetailData = new ProductDetailData();
		Map<String,String> resultMap=new HashMap<String,String>();
		//CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		String ProductName = null;
		String ProductType=null;
		String ProductPrice=null;
		boolean isProductAdded = false;
		//int widgetCount=0;
		int productCount = 0;
		if (isWidget) {
			if (isDisplayed(showMoreButton())) {
				bringElementIntoView(showMoreButton());
				ScrollDown("30");
				click(showMoreButton());
			}
			productCount = driver.findElements(widgetProductsLocator().getBy()).size();
		} else {
			productCount = driver.findElements(allProductsSearchLocator().getBy()).size();
		}
		for (int i = 1; i <= productCount; i++) {
			String ProductName_updated = "";
			//System.out.println(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//div[@class='m-content__product-list__title']")));
			if (isWidget) {
				ProductName = geteText(By.xpath("//div[@name='widget_listing_0']/ancestor::div/div/div/div/div[" + i + "]/div[@class='product-list-box card']//div[@class='m-content__product-list__title']"));
				ProductPrice = geteText(By.xpath("//div[@name='widget_listing_0']/ancestor::div/div/div/div/div[" + i + "]/div[@class='product-list-box card']//div[@class='m-content__product-list__title']/following-sibling::div/div[@class='price-info']/span")).replaceAll("[^0-9]", "");
			} else {
				ProductName = geteText(By.xpath("//div[@class='m-content__product-list']/div[" + i + "]/div[contains(@class,'product-list-box card')]//div[@class='m-content__product-list__title']"));
				ProductPrice = geteText(By.xpath("//div[@class='m-content__product-list']/div[" + i + "]/div[contains(@class,'product-list-box card')]//div[@class='m-content__product-list__title']/following-sibling::div/div[@class='price-info']/span[2]/span")).replaceAll("[^0-9]", "");
			}
			String[] name = ProductName.split(" ");
			for (int j = 0; j < name.length; j++) {
				if (!name[j].contains("'"))
					ProductName_updated = ProductName_updated + " " + name[j];
			}
			ProductName_updated = ProductName_updated.trim();
			//System.out.println(fw.replaceRuppeeSymbol(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//span[@class='post-card__content-price-offer']"))));
			//productDetail.setProductPrize(fw.replaceRuppeeSymbol(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//span[@class='post-card__content-price-offer']"))));
			//System.out.println(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//button")));
			if (isWidget) {
				ProductType = geteText(By.xpath("//div[@name='widget_listing_0']/ancestor::div/div/div/div/div[" + i + "]/div[@class='product-list-box card']//button"));
			} else {
				ProductType = geteText(By.xpath("//div[@class='m-content__product-list']/div["+ i +"]/div[contains(@class,'product-list-box card')]//button"));
			}


			boolean isWishlist = false;
			if (ProductType.contains("BAG") && type.equalsIgnoreCase("simple")) {
				ProductType = "Simple";
			} else if (ProductType.contains("SHADES") && type.equalsIgnoreCase("shade")) {
				ProductType = "Shade";
				//isWishlist = true;
			} else if (ProductType.contains("SIZE") && type.equalsIgnoreCase("size")) {
				ProductType = "Size";
				//isWishlist = true;
			}


			if (ProductType.contains("Shade")) {
				TestListner.testing.get().log(LogStatus.INFO, "Trying to Add " + ProductName + " to cart");
				if (isWidget) {
					resultMap = addToBagForShades(AddToBagButtonWidget(ProductName_updated), false);
					isProductAdded = resultMap.get("isShadeAvailable").equals("true");
					ProductPrice = resultMap.get("price");
				} else {
					resultMap = addToBagForShades(AddToBagButton(ProductName_updated), false);
					isProductAdded = resultMap.get("isShadeAvailable").equals("true");
					ProductPrice = resultMap.get("price");
				}
			} else if (ProductType.contains("Size")) {
				TestListner.testing.get().log(LogStatus.INFO, "Trying to Add " + ProductName + " to cart");
				if (isWidget) {
					resultMap = addToBagForSize(AddToBagButtonWidget(ProductName_updated), false);
					isProductAdded = resultMap.get("isSizeAvailable").equals("true");
					ProductPrice = resultMap.get("price");
				} else {
					resultMap = addToBagForSize(AddToBagButton(ProductName_updated), false);
					isProductAdded = resultMap.get("isSizeAvailable").equals("true");
					ProductPrice = resultMap.get("price");
				}
			} else if (ProductType.contains("Simple")) {
				TestListner.testing.get().log(LogStatus.INFO, "Trying to Add " + ProductName + " to cart");
				if (isWidget) {
					isProductAdded = addSimpleProduct(AddToBagButtonWidget(ProductName_updated), isWishlist);
				} else {
					isProductAdded = addSimpleProduct(AddToBagButton(ProductName_updated), isWishlist);
				}
			}

			if (isProductAdded == true) {
				TestListner.testing.get().log(LogStatus.INFO, ProductName + " added to cart successfully! Checking if the product is present in Cart.");
				/*if (!waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 4)) {
					CartPage_Mobile.goToCart();
					if (isInStockMessageInCart()) {

					}
					if (isAlertPresent(driver)) {
						switchToDefaultContent();
						isProductAdded = false;
					}

				}*/
			}


			if (isProductAdded) {

				productDetailData.setProductName(ProductName);
				productDetailData.setProductPrize(ProductPrice);
				/*try {
					if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
						closeGetAppPopUp();
						click(CartPage_Mobile.BackMenuCart());
						waitUntilDisappear(CartPage_Mobile.BackMenuCart());}
				}
				catch(Exception e) {}*/

				break;
			}

		}
		return productDetailData;
	}

	private Map<String,String> addToBagForShades(Locator productLocator,boolean isWishlist) throws Throwable {
		Map<String,String> resultMap=new HashMap<String,String>();
		boolean isShadeAvailable = false;
		String flag="";
		String price="";
		AddToBagShadeProduct(productLocator, null);
		Thread.sleep(3000);
		List<WebElement> shades = new ArrayList<WebElement>();
		if(isWishlist)
		{
			shades = getWebElements(shadesWishlist());
		}
		else
		{
			shades = getWebElements(shades());
		}


		if (!shades.isEmpty()) {
			for (WebElement shade : shades) {
				if(shade.isDisplayed())
				{
					if(!getAttribute(shade, "class").contains("tick"))
					{
						shade.click();
						if(getText(AddToBagButtonFromView()).contains("PRE"))
						{
							driver.navigate().back();
							if(isShadeAvailable)
							{
								flag="true";
							}
							else
							{
								flag="false";
							}
							resultMap.put("isShadeAvailable", flag);
							resultMap.put("price", price);
							return resultMap;
						}
						break;
					}
				}
			}
			// bringElementIntoView(AddToBagButton(productid));
			ScrollDown("200");
			if(isWishlist)
			{
				click(addToBagWishlistShadeSize());
			}
			else if(waitUntilDisplayed(AddToBagButtonFromView(), 3)){
				price=getText(reactModalPrice()).replaceAll("[^0-9]", "");
				click(AddToBagButtonFromView());
			}
			else{
				click( addToBagButtonOfWishlistProduct());
			}
			isShadeAvailable = true;
			Thread.sleep(4000);
			if (isPresent(outOfStockMessage())) {
				click(closeButton());
				Thread.sleep(2000);
				if (isAlertAvailable()) {
					click(backButton());
				}
				waitUntilDisappear(backButton());
				isShadeAvailable = false;
			}
		}
		else
		{
			driver.navigate().back();
		}
		if(isShadeAvailable)
		{
			flag="true";
		}
		else
		{
			flag="false";
		}
		resultMap.put("isShadeAvailable", flag);
		resultMap.put("price", price);
		return resultMap;

	}

	public boolean addSimpleProduct(Locator productLocator, boolean isWishlist) throws Throwable, Throwable {
		boolean isSimpleProductAdded = true;
		try
		{

			for(WebElement we: driver.findElements(productLocator.getBy()))
			{
				if(we.isDisplayed())
				{
					bringElementIntoViewWebElement(we);
					ScrollDown("50");
					we.click();
					Thread.sleep(4000);
					break;
				}
			}

			CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
			if (isPresent(outOfStockMessage())) {
				click(closeButton());
				Thread.sleep(2000);
				if (isAlertAvailable()) {
					clickJScript(backButton());
				}
				isSimpleProductAdded = false;
			}
			else
				if(CartPage_Mobile.isInstockMessageDisplay())
				{
					CartPage_Mobile.removeFirstProduct();
					isSimpleProductAdded = false;
				}
		}
		catch(Exception e)
		{
			isSimpleProductAdded=false;
		}

		return isSimpleProductAdded;

	}
	
	public boolean addStaticComboProduct(Locator productLocator, boolean isWishlist) throws Throwable, Throwable {
		boolean isStaticComboProductAdded = true;
		try
		{

			for(WebElement we: driver.findElements(productLocator.getBy()))
			{
				if(we.isDisplayed())
				{
					bringElementIntoViewWebElement(we);
					ScrollDown("50");
					we.click();
					Thread.sleep(4000);
				}
			}

			CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
			if (isPresent(outOfStockMessage())) {
				click(closeButton());
				Thread.sleep(2000);
				if (isAlertAvailable()) {
					clickJScript(backButton());
				}
				isStaticComboProductAdded = false;
			}
			else
				if(CartPage_Mobile.isInstockMessageDisplay())
				{
					CartPage_Mobile.removeFirstProduct();
					isStaticComboProductAdded = false;
				}
		}
		catch(Exception e)
		{
			isStaticComboProductAdded=false;
		}

		return isStaticComboProductAdded;

	}


	public boolean addToBagSimpleProduct(ProductDetailData productDetailData) throws Throwable, Throwable {
		String ProductName = productDetailData.getProductName().replace("...", "");
		String ProductName_updated="";
		String[] name=ProductName.split(" ");
		for(int j=0;j<name.length;j++)
		{
			if(! name[j].contains("'"))
				ProductName_updated=ProductName_updated+" "+name[j];
		}
		ProductName_updated=ProductName_updated.trim();
		bringElementIntoView(ProductName(ProductName_updated));
		boolean isSimpleProductAdded = false;

		Locator ProductTypeLocator = ProductName(ProductName_updated).concatLocator(ExtendedLocatorProductType());
		String productType = getText(ProductTypeLocator);
		if (productType.contains("BAG")) {
			bringElementIntoView(AddToBagButton(ProductName_updated));
			click(AddToBagButton(ProductName_updated));
			isSimpleProductAdded = true;

			Thread.sleep(4000);
			CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		
			if (isPresent(outOfStockMessage())) {
				click(closeButton());
				Thread.sleep(2000);
				if (isAlertAvailable()) {
					clickJScript(backButton());
				}
				isSimpleProductAdded = false;
			}
			else
			if(CartPage_Mobile.isInstockMessageDisplay())
				{
					CartPage_Mobile.removeFirstProduct();
					isSimpleProductAdded = false;
				}
			
		}

		return isSimpleProductAdded;

	}

	private boolean addToBagSimple(Locator productLocator) throws Throwable {
		boolean isProductAdded = false;
		Locator ProductILocator = productLocator.concatLocator(extendedLocatorProductID());
		Locator NotifyMeILocator = productLocator.concatLocator(NotifyMe());
		String productid = getAttribute(ProductILocator, "id").replaceAll("[^0-9]", "");
		Locator wishlistPresent = productLocator.concatLocator(WishlistedProduct());
		if(waitUntilDisplayed(wishlistPresent, 2))
		{
			productid = getAttribute(ProductILocator, "class").replaceAll("[^0-9]", "");
			System.out.println(productid);
			Thread.sleep(4000);
			bringElementIntoView(ProductILocator);
			//mouseOver(ProductILocator);
			if(waitUntilDisplayed(NotifyMeILocator, 2))
			{
				isProductAdded = false;
			}
			else{
				click(AddToBagButtonOfWishlist(productid));
				isProductAdded = true;
			}
		}
		else{	
			System.out.println(productid);
			Thread.sleep(4000);
			bringElementIntoView(ProductILocator);
			ScrollDown("-50", 1);
			mouseOver(ProductILocator);
			click(AddToBagButton(productid));
			isProductAdded = true;
		}

		return isProductAdded;
	}

	public void addDynamicComboConfigurableProduct(WebDriver driver) throws InterruptedException {

		WebElement popup = driver.findElement(By.xpath("//div[@class='sb-popup']"));
		List<WebElement> subProducts = popup
				.findElements(By.xpath("div[@class='products-header']/following-sibling::ul/li"));
		for (WebElement subProduct : subProducts) {
			if (isShadeColorProductAvailable(subProduct)) {
				List<WebElement> Shades = subProduct.findElements(
						By.xpath("descendant::img[@id='ch_img_2'][contains(@onclick,'showShadeOnSelection')]"));
				for (WebElement shade : Shades) {
					shade.click();
					Thread.sleep(2000);
					break;
				}
			}

		}
		driver.findElement(By.id("sb-popup-addtocart")).click();
	}

	public void addShopDlookConfigurableProduct(WebDriver driver) throws InterruptedException {

		WebElement popup = driver.findElement(By.xpath("//div[@class='sb-popup']"));
		List<WebElement> subProducts = popup.findElements(By.xpath("descendant::div[@class='product-right']"));
		for (WebElement subProduct : subProducts) {
			if (isShadeColorProductAvailable(subProduct)) {
				List<WebElement> Shades = subProduct.findElements(
						By.xpath("descendant::img[@id='ch_img_2'][contains(@onclick,'showShadeOnSelection')]"));
				for (WebElement shade : Shades) {
					shade.click();
					Thread.sleep(2000);
					break;
				}
			}

		}
		driver.findElement(By.id("sb-popup-addtocart")).click();
	}

	private boolean isShadeColorProductAvailable(WebElement subProduct) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		try {
			//WebDriverWait ww = new WebDriverWait(driver, 4);
			subProduct.findElement(By.xpath("//div[@class='shade-color']"));
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}

	public Map<String, String> addToBagForSize(Locator ProductLocator, boolean isWishlist) throws Throwable, Throwable {
		Map<String,String> resultMap=new HashMap<String,String>();
		String price="";
		List<WebElement> size = new ArrayList<WebElement>();
		selectSize(ProductLocator);
		if(isWishlist)
		{
			size = getWebElements(sizesWishlist());
		}
		else
		{
			size = getWebElements(size());
		}
		if (!size.isEmpty()) 
		{
			for (WebElement sz : size) 
			{
				click(sz, "Size");
				break;
			}
		}

		else
		{
			Locator dropdownLocator = ProductLocator.concatLocator(dropdown());
			selectDefaultValueFromDropDownBox(dropdownLocator);
		}
		if(waitUntilDisplayed(AddToBagButtonFromView(), 5))
		{
			price=getText(reactModalPrice()).replaceAll("[^0-9]", "");
			click(AddToBagButtonFromView());
			waitUntilDisappear(size());
		}
		else
		{
			click(addToBagWishlistShadeSize());
		}

		resultMap.put("isSizeAvailable", "true");
		resultMap.put("price", price);
		
		if (waitUntilDisplayed(outOfStockMessage(), 4)) {
			Thread.sleep(2000);
			if (waitUntilPresent(backButton(), 4)) {
				KeyBoard(Keys.ESCAPE);
				clickJScript(backButton());


			}
			resultMap.put("isSizeAvailable", "false");
		}

		return resultMap;

	}


	boolean isAlertAvailable() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		try {
			driver.findElement(By.linkText("Back"));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}

	private void selectSize(Locator locator) throws IllegalArgumentException, Throwable {
		/*System.out.println(locator.getBy());
		for(int i=0;i<driver.findElements(showMoreButton().getBy()).size();i++)
		{
			bringElementIntoView(showMoreButton());
			ScrollDown("100");
			click(showMoreButton());
		}*/
		//	List<WebElement> locators=new ArrayList<WebElement>();
		//	locators=driver.findElements(locator.getBy());
		//	if(locators.size()>1)
		//	{
		//		bringElementIntoViewWebElement(locators.get(1));
		//		ScrollDown("100");
		//		click(locators.get(1),"Preview Size");
		//	}
		//	else
		for(WebElement we: driver.findElements(locator.getBy()))
		{
			if(we.isDisplayed())
			{
				bringElementIntoViewWebElement(we);
				ScrollDown("50");
				we.click();
				Thread.sleep(4000);
			}
		}

	}

	private void AddToBagShadeProduct(Locator locator, String productid) throws Throwable {
		for(WebElement we: driver.findElements(locator.getBy()))
		{
			if(we.isDisplayed())
			{
				bringElementIntoViewWebElement(we);
				ScrollDown("50");
				we.click();
				Thread.sleep(4000);
			}
		}
	}

	public void serviceAddToCart(ProductDetailData productDetailData) throws Throwable, Throwable {
		SelectLocationOfBeutyService("Mumbai", productDetailData.getLocation());
		bringElementIntoView(
				bookNowButtonFromListing(productDetailData.getProductName(), productDetailData.getLocation()));
		click(bookNowButtonFromListing(productDetailData.getProductName(), productDetailData.getLocation()));

	}

	private void clickaddToCart(WebElement webElement) throws InterruptedException {
		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(false);",
		// driver.findElement(By.xpath("//a[text()='Irresistible Combos']")));
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",
				webElement/*
				 * webElement.findElement(By.xpath(
				 * "//following-sibling::div[@class='litre']"))
				 */);
		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(false);",
		// webElement);
		// ((JavascriptExecutor)
		// driver).executeScript("javascript:window.scrollBy(250 ,350)");
		System.out.println("scrolled by product");
		// ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,20)");

		Actions action = new Actions(driver);
		Thread.sleep(4000);
		action.moveToElement(webElement).build().perform();
		Thread.sleep(2000);
		WebElement AddToBagButton = driver.findElement(By.partialLinkText("ADD TO BAG"));
		action.moveToElement(AddToBagButton).build().perform();
		Thread.sleep(2000);
		AddToBagButton.click();
		waitUntilOverlayDisappear(driver);

	}

	public void waitUntilOverlayDisappear(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 90);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='overlay']")));

	}

	public boolean addToBagStaticComboProduct(WebElement bestSellingProduct) throws InterruptedException {
		// TODO Auto-generated method stub
		try {
			clickaddToCart(bestSellingProduct);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	public List<ProductDetailData> ApplySort(String sortName) throws Throwable {
		ArrayList<String> printList=new ArrayList<String>();
		waitUntilDisplayed(SortDropDown(), 2);
		click(SortDropDown());
		selectSortOption(sortName);
		waitForPagetoLoad();
		if(isDisplayed(SortDropDown()) && getAttribute(SortDropDown(), "class").equals("in"))
		{
			click(SortDropDown());
		}
		LoadMoreProducts(3,false);
		backToTop();

		List<ProductDetailData> ProductDataList = getListOfProduct(sortName);

		switch(sortName.toLowerCase())
		{
		case "name":
			for(int i=0;i<ProductDataList.size();i++)
			{
				printList.add(ProductDataList.get(i).getProductName());
			}
			break;
		case "price: high to low":
		case "price: low to high":
			for(int i=0;i<ProductDataList.size();i++)
			{
				printList.add(ProductDataList.get(i).getProductPrize());
			}
			break;
		case "discount":
			for(int i=0;i<ProductDataList.size();i++)
			{
				printList.add(ProductDataList.get(i).getDiscount());
			}
			break;
		}

		TestListner.testing.get().log(LogStatus.INFO,"Actual Result List after "+sortName +" Sorting: "+printList.toString());
		return ProductDataList;
	}


	private boolean selectSortOption(String sortName) 
	{
		boolean flag=false;
		List<WebElement> radios=new ArrayList<WebElement>();
		try
		{
			radios=driver.findElements(SortRadioButtons().getBy());
			for(int i=1;i<=radios.size();i++)
			{
				if(geteText(By.xpath("//div[@class='sort-wrap show-sort']/div[@class='ripple']["+i+"]/div/label/span")).equalsIgnoreCase(sortName))
				{
					click(By.xpath("//div[@class='sort-wrap show-sort']/div[@class='ripple']["+i+"]/div/label"));
					TestListner.testing.get().log(LogStatus.INFO,"Sorted By:  " + sortName);
					flag=true;
					waitUntilDisappear(filterLoader());
					break;
				}
			}
			return flag;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	private List<ProductDetailData> getListOfProduct(String sortName) throws IllegalAccessException {
		List<WebElement> productList = getWebElements(productList());
		List<ProductDetailData> ProductDataList = new ArrayList<ProductDetailData>();
		boolean isFeatured=true;
		for (WebElement product : productList) {
			String Discount = null;
			String ReviewCount = null;
			Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product Parent Loactor");
			if(isFeatured && isDisplayed(productLocator.concatLocator(featuredTag())) )
			{
				continue;
			}
			else
			{
				isFeatured=false;
			}
			String prizeOfProduct = (getText(productLocator.concatLocator(PriceLocator())).replaceAll("\\D+", ""));
			String ProductName = getAttribute(productLocator.concatLocator(productName()), "title");
			if (sortName.toLowerCase().contains("discount")) {
				Discount = getText(productLocator.concatLocator(DiscountTag())).replaceAll("\\D+", "");
			}
			if (sortName.toLowerCase().contains("rated")) {
				ReviewCount = getText(productLocator.concatLocator(ProductReviewcount())).replaceAll("\\D+", "");
			}
			ProductDetailData productDetailData = new ProductDetailData();
			productDetailData.setProductPrize(prizeOfProduct);
			productDetailData.setProductName(ProductName);
			productDetailData.setDiscount(Discount);
			productDetailData.setReviewCount(ReviewCount);
			ProductDataList.add(productDetailData);
		}
		return ProductDataList;
	}

	public List<ProductDetailData> ApplyFilter(String FilterName, String Criteria) throws Throwable, Throwable {
		waitUntilDisplayed(Filter(), 2);
		click(Filter());
		closeGetAppPopUp();
		bringElementIntoView(filter(FilterName));
		click(filter(FilterName));
		if(FilterName.equalsIgnoreCase("price"))
		{
			click(PriceFilterCheckbox(Criteria.split("-")[0].replaceAll("Rs.", "").trim()));
		}
		else
		{
			click(FilterCheckbox(Criteria));
		}
		waitUntilDisappear(filterLoader());
		waitUntilDisplayed(ApplyFilterButton(), 3);
		Thread.sleep(1000);
		click(ApplyFilterButton());
		waitUntilDisappear(fullPageLoadingIcon());
		//LoadMoreProducts(3, true);
		List<ProductDetailData> ProductDataList = getListOfProduct(FilterName);
		return ProductDataList;
	}

	public void SelectLocationOfBeutyService(String CityName, String RegionName)
			throws InstantiationException, IllegalAccessException, Throwable {
		click(SelectCity(CityName));
		Thread.sleep(40000);
		click(SelectRegionalLocation(RegionName));
		click(SubmitButtonOFLocator());
		waitUntilDisappear(LoaderIconForServicePage());

	}

	public List<WebElement> irresistibleComboProducts() {
		List<WebElement> webElement = driver.findElements(By.xpath(
				"//a[text()='All Products']/ancestor::div[@class='content_inner ']/following-sibling::div[@class='category-products content_inner']/descendant::p[@data='bundle']/preceding-sibling::div[@class='litre']"));
		return webElement;

	}

	public boolean backToTop() {
		try 
		{
			if(isDisplayed(scrollToTopLocator()))
				click(scrollToTopLocator());
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	public List<ProductDetailData> ApplyFilter(String FilterName, String Criteria,boolean countProducts,boolean clearFilter) throws Throwable, Throwable {
		int count=0;
		String allCriteria[]=Criteria.split("----");
		List<ProductDetailData> ProductDataList =new ArrayList<ProductDetailData>();
		try
		{
			backToTop();
			Locator options = expandFilter(FilterName);
			if(clearFilter)
			{
				clearAllFilters();
				Thread.sleep(1500);
			}

			for(int i=0;i<allCriteria.length;i++)
			{
				if(allCriteria[i].contains("searchBrand"))
				{
					allCriteria[i]=allCriteria[i].split("\\(")[0].replaceAll("searchBrand", "");
					//ClearValue(brandSearchBoxLocator());
					//EnterValue(brandSearchBoxLocator(),allCriteria[i]);

				}

				for(WebElement option: getAllElements(options.getBy()))
				{
					waitForPagetoLoad();
					System.out.println(option.getText());

					if(option.getText().contains(allCriteria[i].trim()))
					{
						count+=Integer.parseInt(option.getText().split("\\(")[1].replaceAll("\\)", ""));
						waitUntilDisappear(filterLoader());
						bringElementIntoViewWebElement(option);
						//System.out.println(getAbsoluteXPath(option).concat("/following-sibling::div"));
						try
						{
							Locator nextOption=new Locator(By.xpath(getAbsoluteXPath(option).concat("/following-sibling::div[2]")),"next option");
							//System.out.println(nextOption.getBy().toString());
							if(isDisplayed(nextOption))
							{
								bringElementIntoViewWebElement(driver.findElement(nextOption.getBy()));
							}
						}catch(Exception e) {}
						click(option, allCriteria[i]);
						waitUntilDisappear(filterLoader());
						break;
					}
				}
			}
			waitUntilDisappear(filterLoader());
			waitUntilDisplayed(ApplyFilterButton(), 3);
			Thread.sleep(1000);
			click(ApplyFilterButton());
			if(isDisplayed(SortDropDown()) && getAttribute(SortDropDown(), "class").equals("in"))
			{
				click(SortDropDown());
			}
			waitUntilDisappear(filterLoader());
			waitUntilDisappear(fullPageLoadingIcon());
			waitForPagetoLoad();
			LoadMoreProducts(3,true);
			backToTop();
			ProductDataList = getListOfProduct(FilterName);
			if( ProductDataList==null || ProductDataList.isEmpty())
			{
				ProductDataList = getListOfProduct(FilterName);
			}
			ArrayList<String> printList=new ArrayList<String>();

			switch(FilterName.toLowerCase())
			{
			case "price":
				for(int i=0;i<ProductDataList.size();i++)
				{
					printList.add(ProductDataList.get(i).getProductPrize());
				}
				break;
			case "discount":
				for(int i=0;i<ProductDataList.size();i++)
				{
					printList.add(ProductDataList.get(i).getDiscount());
				}
				break;
			case "brand":
				for(int i=0;i<ProductDataList.size();i++)
				{
					printList.add(ProductDataList.get(i).getProductName());
				}
				break;
			}
			TestListner.testing.get().log(LogStatus.INFO,"Actual Result List after "+FilterName+" "+Criteria.replace("----", " and ") +" Filter: "+printList.toString());
			if(countProducts)
			{
				TestListner.testing.get().log(LogStatus.INFO,"Matching product count in filter with actual product count ");
				LoadMoreProducts(3,true);
				int value = getProductCountOfAllProducts();
				TestListner.testing.get().log(LogStatus.INFO,"Product count in filter: "+count+" || Actual Product Count: "+value);
				if(value==count)
				{
					TestListner.testing.get().log(LogStatus.PASS,"Count matches");
				}
				else
				{
					TestListner.testing.get().log(LogStatus.FAIL,"Count does not match");
				}
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();	
		}
		return ProductDataList;
	}

	public boolean clearAllFilters() throws IllegalAccessException, Throwable, InstantiationException {
		boolean flag=true;
		int count=0;
		try
		{

			while(isDisplayed(filterCount()) && count<10)
			{
				flag=false;
				if(!(isDisplayed(CountofFilter()))){
					click(FilterButton());
				}
				bringElementIntoView(ClearAllFiltersLocator());
				click(ClearAllFiltersLocator());
				waitUntilDisappear(fullPageLoadingIcon());
				waitForPagetoLoad();
				flag=true;
				count+=1;
			}
		}
		catch(Exception e)
		{
			flag=false;
		}
		return flag;
	}

	public Locator expandFilter(String FilterName) throws IllegalAccessException, Throwable, InstantiationException {

		if(waitUntilDisplayed(Filter(), 2))
		{try
		{click(Filter());}
		catch(Exception e) {}
		}
		bringElementIntoView(filter(FilterName));
		click(filter(FilterName));
		waitUntilDisplayed(FilterOptions(), 3);
		return FilterOptions();
	}

	public ArrayList<String> getFilterOptionsList(String FilterName) throws IllegalAccessException, Throwable, InstantiationException 
	{
		ArrayList<String> FilterValues=new ArrayList<String>();
		Locator options=expandFilter(FilterName);
		waitForPagetoLoad();
		for(WebElement option: getAllElements(options.getBy()))
		{
			FilterValues.add(option.getText());
		}
		return FilterValues;
	}


	public Boolean ListPageIsReactPageOrNot()
	{
		WebDriverWait wait = new WebDriverWait(driver, 2);



		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(Filter().getBy()));

			return true;
		}
		catch(Exception e)
		{

			return false;
		}

	}

	public void LoadMoreProducts(int count,boolean tillEnd) throws Throwable {
		boolean endReached=false;
		try 
		{
			if(! tillEnd)
			{
				for(int i=0;i<count;i++)
				{
					waitForPagetoLoad();
					try 
					{
						{
							Thread.sleep(2000);
							while((((Long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight;"))-((Long) ((JavascriptExecutor) driver).executeScript("return window.pageYOffset;")))>1000)
							{
								((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
								waitForPagetoLoad();
								Thread.sleep(2000);
							}
						}

					}
					catch(Exception e) {}
					//bringElementIntoView(LoadMoreButton());
					if(isDisplayed(LoadMoreButton()))
					{
						bringElementIntoView(LoadMoreButton());
						click(LoadMoreButton());
						waitForPagetoLoad();
					}
					else
					{
						waitForPagetoLoad();
						bringElementIntoView(newsletterText());
						break;
					}
				}
			}
			else if (tillEnd)
			{
				while(! endReached)
				{
					waitForPagetoLoad();
					try 
					{
						{
							Thread.sleep(2000);
							while((((Long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight;"))-((Long) ((JavascriptExecutor) driver).executeScript("return window.pageYOffset;")))>1000)
							{
 								((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
								waitForPagetoLoad();
								Thread.sleep(2000);
							}
						}

					}
					catch(Exception e) {}
					//bringElementIntoView(LoadMoreButton());
					if(isDisplayed(LoadMoreButton()))
					{
						bringElementIntoView(LoadMoreButton());
						ScrollDown("100");
						click(LoadMoreButton());
						waitForPagetoLoad();
					}
					else
					{
						waitForPagetoLoad();
						bringElementIntoView(newsletterText());
						endReached=true;
					}
				}
			}
		}
		catch (Exception e)
		{

		}
	}
	
	public String AddreviewOnALLReviewPage () throws IllegalArgumentException, Throwable
	{
		getAllReviewproduct();
		waitForPagetoLoad();
		
		click(SelectReviewAllStars());
		Thread.sleep(3000);
		clickJScript(NickNameReviewAll());
		  Robot robot = new Robot();  // Robot class throws AWT Exception	
          Thread.sleep(2000); // Thread.sleep throws InterruptedException	
          robot.keyPress(KeyEvent.VK_T);
          robot.keyPress(KeyEvent.VK_E);
          robot.keyPress(KeyEvent.VK_S);
          robot.keyPress(KeyEvent.VK_T);
		//EnterValueJScript(NickNameReviewAll(),"test");
		clickJScript(TitleReviewAll());
		EnterValueJScript(TitleReviewAll(),"Our lab tests and consumers agreed that L'Oreal's \"vibrant\" Infallible Le Rouge lip color was one of the longest lasting in the bunch , Lancôme Rouge In Love Lipcolor was the most moisturizing lipstick in our test according to volunteers");
		clickJScript(PostReviewButton());
		String message = driver.findElement(By.xpath("//div[@class='review-captcha']//span")).getText();
		return message;
	
		
	}
	public void getAllReviewproduct() throws IllegalArgumentException, Throwable
	{

		List<WebElement> listWeb  = driver.findElements(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div/div[@class='product-list-box card']"));
		for(WebElement product : listWeb)
		{
			
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", product);
			Thread.sleep(2000);
			product.click();
			waitForPagetoLoad();
			ProductPage_React productpage = new ProductPage_React(driver);
			
				try
				{
					clickJScript(productpage.SeeMoreReviewLink());
					break;
				}
				 
				
				catch(Exception e)
				{
					driver.navigate().back();
					waitForPagetoLoad();
				}
				
		}
	}

}