package PagesNykaa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import FrameWorkNykaa.Framework;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.regexp.RE;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import DataNykaa.AccountData;
import DataNykaa.ProductDetailData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Locator;
import FrameWorkNykaa.TestListner;


public class ListingPage_React extends BrowserAction {

	public ListingPage_React(WebDriver driver) {
		this.driver = driver;
	}

	/*	Locator ShopAllLink() {
		return new Locator(By.partialLinkText("//a[contains(text(),'shop all')]"), "Shop all Link");
	}*/
	Locator ShopAllLink() {
		return new Locator(By.xpath("//a[contains(@href,'shop-all')]"), "Shop all Link");
	}

	Locator fullPageLoadingIcon() {
		return new Locator(By.id("overlay"), "full Page Loading Icon");
	}

	Locator addToBagLoadingIcon() {
		return new Locator(By.xpath("//div[@class='circle-loader']"), "Add to bag Loading Icon");
	}

	/*Locator NotifyMe(){
		return new Locator(By.xpath("//*[text()='Notify me']"),"Notify Me");
	}*/

	Locator filter(String FilterName) {
		return new Locator(By.xpath("//div[@class='filter-sidebar__filter-title pull-left'][contains(text(),'"+FilterName+"')]"), " Filter " + FilterName + "");
	}

	Locator brandSearchBoxLocator() {
		return new Locator(By.xpath("//input[@class='filter__search-input form-control']"), " Brand Filter Search box");
	}

	Locator filterStatus() {
		return new Locator(By.xpath("/ancestor::div/ancestor::div/following-sibling::div[contains(@class,'slide-filter')]"), " Filter Status");
	}

	Locator wishlistPane() {
		return new Locator(By.xpath("//p[contains(@class,'add-to-wishlist wishlist-id-')]"), "Wishlist pane");
	}

	Locator FilterCheckbox() {
		return new Locator(By.xpath("/ancestor::div/ancestor::div/following-sibling::div[contains(@class,'slide-filter')]/div[@class='filter-options-list active']/div/div[@class='control-box']/label"),
				"Filter Checkbox");
	}
	Locator ClearAllFiltersLocator() {
		return new Locator(By.xpath("//button[@class='clear-all-filter']"),	" Clear All Filters");
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
		return new Locator(By.xpath("//button[contains(@class,'primary-btn')]"), "Product button Name");
	}

	Locator ExtendedProductType_option() {
		return new Locator(By.xpath("//div[@class='litre']/following-sibling::p/a"), "Product type");
	}

	Locator ExtendedProductType_wishlist() {
		return new Locator(By.xpath("//h3[@class='product-name']/following-sibling::p/a"), "Product type");
	}

	Locator ExtendedWishlistLocator() {
		return new Locator(By.xpath("//div[contains(@class,'listing_wishlist')]"), "WishList");
	}

	Locator ExtendedWishlistStatusLocator() {
		return new Locator(By.xpath("//div[contains(@class,'listing_wishlist')]/span/i"), "WishList");
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
				By.xpath("//div[contains(@class,'slick-list')]//div[contains(@class,'slick-active')]"),	"widget Produts");
	}

	public Locator sliderNextButton() {
		return new Locator(
				By.xpath("//button[@class='slick-arrow slick-next']"),	"slider next button");
	}


	public Locator wishlistProducts() {
		return new Locator(By.xpath("//li[contains(@id,'product-opt-')]"), "wishlist product List");
	}


	Locator searchedProductsList() {
		return new Locator(By.xpath("//li[contains(@id,'product-opt-')]"), "search product List");
	}
	
	Locator searchedProductsListMan() {
		return new Locator(By.xpath("//div[@class='row clearfix plp-desktop']//div"), "search product List");
	}

	Locator searchProductList_React(){
		return new Locator(By.xpath("//div[@class='card-wrapper-container col-xs-12 col-sm-6 col-md-4']"), "React Page Search List");
	}

	Locator WishlistMessage(String ProductID, String TextMessage) {
		return new Locator(By.xpath(
				"//span[contains(@class,'wishlist_disp_msg wishlist_msg_" + ProductID + " " + TextMessage + "')]"),
				" " + TextMessage + "");
	}

	Locator OfferDetailText() {
		return new Locator(By.xpath("/ancestor::span/ancestor::div/following-sibling::div[contains(@class,'offer-quick-view')]/ul/li"),
				"Offer Detail");
	}

	Locator OfferDetailCloseIcon() {
		return new Locator(By.xpath("/ancestor::span/ancestor::div/following-sibling::div[contains(@class,'offer-quick-view')]/span[@class='closeWindow']"),
				"Offer Detail");
	}
	Locator OfferDetailCloseIconNykaaMan() {
		return new Locator(By.xpath("//ancestor::span/ancestor::div/following-sibling::div[contains(@class,'offer-quick-view')]/span[@class='closeWindow']"),
				"Offer Detail");
	}
	

	Locator LoadMoreButton() {
		return new Locator(By.xpath("//button[@class='primary-btn common-btn'][contains(text(),'Load More')]"),	"Load More button");
	}

	Locator backToTopButton() {
		return new Locator(By.xpath("//div[@class='scrollToTop']"),	"Back To Top button");
	}

	Locator closeShadeOverlay() {
		return new Locator(By.xpath("//a[@id='closelb']"), "Close Shade Selection Overlay");
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
		return new Locator(By.id("sortaction"), "Filter button");
	}

	Locator SortDropDown() {
		return new Locator(By.xpath("//div[@class='sort-btn clearfix']"), "Filter button");
	}

	Locator SortOptionsContainer() {
		return new Locator(By.xpath("//div[@class='sort-btn clearfix']/following-sibling::div[contains(@class,'sort-container')]"), "Filter button");
	}

	Locator SortOptions() {
		return new Locator(By.xpath("//div[@class='sort-btn clearfix']/following-sibling::div[contains(@class,'sort-container')]/div/div"), "Filter button");
	}

	Locator PriceLocator() {
		return new Locator(By.xpath("//span[@class='actual-price']"), "price");
	}

	Locator PriceLocator_new(){
		return new Locator(By.xpath("//span[@class='post-card__content-price-offer']"),"Price");
	}

	Locator OutOfStockLabel() {
		return new Locator(
				By.xpath(
						"//div[@class='slick-slide slick-active']/descendant::a/ancestor::h3/following-sibling::div[@class='litre']"),
				"Out Of Stock");

	}

	Locator productList() {
		return new Locator(By.xpath("//ul[@id='product_list_ul']/li[contains(@id,'product-opt')]"), "Product List");
	}

	Locator quantityplusButton() {
		return new Locator(By.xpath("//img[@id='Sym'][@class='update_p_qty'][not(contains(@style,'hidden'))]"),
				"quantity plus");
	}

	Locator AddToBagButton(String ProductID) {
		return new Locator(
				By.xpath("//p[contains(@class,'add-to-cart')][contains(@style,'block')]/a[contains(@onclick,'"
						+ ProductID
						+ "')][@title='ADD TO BAG' or @class='button btn-cart' or @title='Add to Bag']"),
				"Add To Button");
	}

	Locator AddToBagButtonOfWishlist(String ProductID) {
		return new Locator(
				By.xpath("//p[contains(@class,'add-to-cart')]/a[contains(@onclick,'"
						+ ProductID
						+ "')][@title='ADD TO BAG' or @class='button btn-cart' or @title='Add to Bag']"),
				"Add To Button");
	}

	Locator AddToBagButtonFromView(){
		return new Locator(By.xpath("//button[contains(@class,'nk-btn combo-add-to-btn  atc-configurable m-content__product-list__cart-btn  ')]| //button[contains(@class,'nk-btn combo-add-to-btn luxury_plpButton atc-configurable m-content__product-list__cart-btn  ')]"),"Add To Bag Button");
	}

	Locator AddToBagButtonFromView_Simple(){
		return new Locator(By.xpath("//button[contains(@class,'nk-btn combo-add-to-btn  atc-simple m-content__product-list__cart-btn  ')]"),"Add To Bag Button");
	} 

	Locator AddToBagButtonFromView(String productid){
		return new Locator(By.xpath("//div[@class='add-cartr-btn'][contains(@onclick,'"+productid+"')]"),"Add To Bag Button");
	}

	Locator shades() {
		return new Locator(
				By.xpath("//ul[@class='options-colors-lists clearfix']//span[@class='color-pallets  ']|//ul[@class='options-colors-lists clearfix']//span[contains(@class,'color-pallets-tick')]"),"shades");
	}

	Locator shadeName() {
		return new Locator(
				By.xpath("/div/img"),"shadeName");
	}

	Locator sizes() {
		return new Locator(
				By.xpath("//li[contains(@class,'size-pallet')]"),"sizes");
	}
	Locator sizeValue() {
		return new Locator(
				By.xpath("//span[@class='size-pallets']"),"sizeValue");
	}

	public Locator AllProducts() {
		return new Locator(
				By.xpath(
						"//a[text()='All Products']/ancestor::div[@class='content_inner ']/following-sibling::div[@class='category-products content_inner']/descendant::div[@class='litre']"),
				"All Product");

	}

	public static Locator allProducts() {
		return new Locator(
				By.xpath(
						"//div[contains(@class,'main-product-listing-page')]//div[contains(@class,'product-list-box card desktop-cart')][not(contains(@class,'tip-tile'))]"),	"All Product");

	}

	public static Locator TiptileProducts(String position){
		return new Locator(By.xpath("//div[@class='row clearfix']/div[" + position + "]"), "tiptile");
	}

	Locator bookNowButtonFromListing(String serviceName, String location) {
		return new Locator(
				By.xpath("//a[@title='" + serviceName
						+ "']/ancestor::div[contains(@id,'product-opt')]//a[@class='book_now show-slidecart-button']"),
				"book now button of " + serviceName + " ");
	}

	Locator extendedLocatorProductID() {
		return new Locator(By.xpath("//ancestor::li[contains(@id,'product-opt')]"), "ProductId");
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
		return new Locator(By.xpath("//span[@class='offer']"), "Offer Tag");
	}

	Locator AllServices() {
		return new Locator(
				By.xpath(
						"//a[text()='All Services']/ancestor::div[@class='content_inner ']/following-sibling::div[@class='category-products content_inner']/descendant::div[@class='litre']"),
				"All Service");

	}

	Locator productName() {
		return new Locator(By.xpath("//h3[@class='product-name']/a[1]"), "product Name");
	}

	Locator ProductName_New(){
		return new Locator(By.xpath("//div[@class='m-content__product-list__title']/h2[1]"),"product name");
	}



	Locator peopleAlsoBoughtProduts() {
		return new Locator(
				By.xpath(
						"//div[@id='product-onclick-widget-list']//div[@class='slick-slide slick-active']/li[contains(@id,'product-opt')]"),
				"people also bought");

	}

	Locator peopleWhoboughtFrame() {
		return new Locator(By.xpath("//div[@id='product-onclick-widget-list'] [not (contains(@style,'display: none'))]"), "people who bought");
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

	Locator wishlistIconStatus(String productId) {
		return new Locator(By.xpath("//p[contains(@class,'add-to-wishlist wishlist-id-" + productId
				+ "')][contains(@style,'block')]/a"), "Wishlist Icon");
	}

	Locator ExtendedLocatorProductName() {
		return new Locator(By.xpath("//div[@class='m-content__product-list__title']/h2/span"), " product Names");
	}

	Locator ExtendedLocatorPrice() {
		return new Locator(By.xpath("//div[@class='clearfix m-content__product-list__price']/div//span[@class='post-card__content-price-offer']"), " product price");
	}

	Locator ExtendedLocatorProductType() {
		return new Locator(By.xpath("//ancestor::h3[@class='product-name']/following-sibling::div[contains(@class,'litre')]/following-sibling::p[contains(@class,'add-to-cart org_add category_addtocart hover-addtocart')]/a"),
				"Product Type");

	}
	
	Locator ExtendedLocatorProductNameWishlist() {
		return new Locator(By.xpath("//h3[@class='product-name']/a"), " product Names");
	}

	Locator ExtendedLocatorPriceWishlist() {
		return new Locator(By.xpath("//div[@class='price-box']/h3/span"), " product price");
	}

	Locator ProductName(String ProductName) {
		return new Locator(By.xpath("//h3[@class='product-name']/a[contains(@title,\"" + ProductName + "\")]"),
				" " + ProductName + "");

	}

	Locator ProductLayoutByProductName(String ProductName)
	{
		return new Locator(By.xpath("//h3[@class='product-name']/a[contains(@title,\"" + ProductName + "\")]/ancestor::li[contains(@id,'product-opt')]"),
				" " + ProductName + "");
	}

	Locator DiscountTag() {
		return new Locator(By.xpath("//div[@class='clearfix m-content__product-list__price']/div/span[contains(@class,'post-card__offers-offer')]"), " product discount");
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
		return new Locator(By.xpath("//p/a[@title='Select Sizes']"), " Select Size Button");
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
						"//div[@class='mm-text-container-outer text-container-desktop  mm-message-error']/div//div[@class='mm-text']/span"),
		"Out of Stock");
	}

	Locator breadCrumb() {
		return new Locator(By.xpath("//div[@class='breadcrumb-cat']/ul"), "bread crumb");
	}

	Locator totalSearchedProductLocator() {

		return new Locator(By.id("totalprod"), "total Search product result text");
	}

	Locator pageLoader() {
		return new Locator(By.xpath("//div[@class='overlay-bg']"), "filter Loading Icon");
	}

	private Locator closeButton() {
		return new Locator(By.id("closelb"), "close button");
	}

	private Locator closeLink(String ProductId) {
		return new Locator(By.xpath("//a[@id='closebutn'][contains(@onclick,'" + ProductId + "')]"), "close link");
	}

	private Locator backButton() {
		return new Locator(By.id("closebutn_alert"), "Back Icon");

	}

	public Locator variantPrice()
	{
		return new Locator(By.xpath("//div[contains(@class,'shadeComponentColorDescription')]//span[@class='post-card__content-price-offer']"),"variant price");
	}

	public Locator footerHelp()
	{
		return new Locator(By.xpath("//div[@class='footer__help']"),"Footer help");
	}

	Locator NotifyMe(){
		return new Locator(By.xpath("/ancestor::h3/following-sibling::p/a"),"Notify Me");
	}

	Locator NotifyEmail () {
		return new Locator(By.xpath("//div[@id!='outofstockSubscriptionOnCategory']//input[@name='subscription_email']"),"Notify Me Email");
	}

	Locator ShadeSizeSelectionPLP (){
		return new Locator(By.xpath("//select[contains(@id,'select')]"), "Shade and Size Selction");
	}

	Locator NotifyMeButtonPopup(){
		return new Locator(By.xpath("//div[@id!='outofstockSubscriptionOnCategory']//*[@value='NOTIFY ME']"), "Notify Me Button in Pop-up");
	}

	Locator outOfStockNotifyEmail () {
		return new Locator(By.xpath("//div[@class='out-of-stock']//li[@class='aj_msg success-msg']//span"), "Notification Message Check");
	}

	Locator OutofStcockMsg(){
		return new Locator(By.xpath("//i[@class='glyphicon glyphicon-exclamation-sign mm-icon']"),"Out of Stock message when Notify Me Button not shown on product");
	}

	Locator notifyMePopupclose(){
		return new Locator(By.id("closelb"),"close Tab");
	}

	Locator featuredTag() {
		return new Locator(By.xpath("/div//span[@class='featured']"),"Featured tag");
	}

	public static Locator oosProducts(){
		return new Locator(By.xpath("//div[contains(@class,'main-product-listing-page')]//div[contains(@class,'product-list-box card desktop-cart')]//button[text()='Notify Me']//ancestor::div[@class='card-wrapper-container col-xs-12 col-sm-6 col-md-4']"),"OOS Products");
	}


	//-----------------------------------------------------------------------------------------------------------------------------
	//									Methods
	//-----------------------------------------------------------------------------------------------------------------------------


	public ProductDetailData addAnyShadeProductFromWidget() throws Throwable {
		closeCart();
		return addShadeProductFromList(widgetProducts());
	}

	public ProductDetailData addAnyShadeProductFromPeopleWhoBoughtWidget() throws Throwable {
		closeCart();
		bringElementIntoView(peopleWhoboughtFrame());
		//click(leftArrow());
		return addShadeProductFromList(peopleAlsoBoughtProduts());
	}

	public ProductDetailData addAnyShadeProductFromSearchedProductList() throws Throwable {
		closeCart();

		return addShadeProductFromList(searchedProductsList());
	}

	public ProductDetailData addAnyShadeProductfromReactSearchPage() throws Throwable{
			closeCart();
			return addShadeProductFromList(searchProductList_React());
	}

	public ProductDetailData addSimpleProductFromSearchedProductList() throws Throwable {
		closeCart();

		return addSimpleProductFromList(searchedProductsListMan());
	}

	public ProductDetailData addAnySimpleProductFromWidget() throws Throwable {
		closeCart();
		return addSimpleProductFromList(widgetProducts());
	}

	public ProductDetailData addAnySimpleProductFromWishlist() throws Throwable {
		closeCart();
		waitUntilDisplayed(MywishListForm(), 20);
		return addSimpleProductFromList(wishlistProducts());
	}

	public ProductDetailData addAnySimpleProductFromAllProducts() throws Throwable {
		closeCart();
		return addSimpleProductFromList(allProducts());
	}

	public void navigateToShopAll(String PageName,String environmentURL) throws Throwable, Throwable 
	{

		Locator shopAll=null;
		if (waitUntilDisplayed(ShopAllLink(), 5)) {
			if(driver.findElements(ShopAllLink().getBy()).size()>0)
			{
				List<WebElement> li=driver.findElements(ShopAllLink().getBy());
				shopAll=new Locator(By.xpath(getAbsoluteXPath(li.get(li.size()-1))),"Shop All");
			}
			else
			{
				shopAll=ShopAllLink();
			}
			bringElementIntoView(shopAll);
			clickandNavigateWindow(shopAll, PageName,environmentURL );


			driver.get(driver.getCurrentUrl().replace("http://www.nykaa.com",environmentURL));

		}
	}

	public ProductDetailData addAnyShadeProductFromAllProduct() throws Throwable {
		closeCart();
		return addShadeProductFromList(allProducts());

	}

	public ProductDetailData addAnyShadeProductwishlist() throws Throwable {
		closeCart();
		return addShadeProductFromList(wishlistProducts());

	}

	public ProductDetailData addAnySizeProductwishlist() throws Throwable {
		closeCart();
		return addSizeProductFromList(wishlistProducts());

	}


	public ProductDetailData addAnyStaticProductFromAllProduct() throws Throwable {
		closeCart();
		return addStaticComboFromList(allProducts());

	}

	public ProductDetailData addSimpleProductFromAllProduct() throws Throwable {
		closeCart();
		return addSimpleProductFromList(allProducts());

	}

	public ProductDetailData addAnySizeProductFromAllProduct() throws Throwable {
		closeCart();
		return addSizeProductFromList(allProducts());

	}

	public ProductDetailData addAnySizeProductFromWidget() throws Throwable {
		closeCart();
		return addSizeProductFromList(widgetProducts());

	}

	public List<ProductDetailData> getProductList(Locator locator) throws Throwable, Throwable {

		List<WebElement> productElements = getWebElements(locator);
		List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
		for (WebElement webelement : productElements) {
			Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(webelement)), "Product");
			Locator productNameLocator = ProductLocator.concatLocator(ExtendedLocatorProductName());
			Locator PriceLocator = ProductLocator.concatLocator(ExtendedLocatorPrice());
			Locator productTypeLocator = ProductLocator.concatLocator(ExtendedProductType());
			ProductDetailData productDetail = new ProductDetailData();
			productDetail.setProductName(getText(productNameLocator));
			productDetail.setProductPrize(getText(PriceLocator).replaceAll("\\D+", ""));
			productDetail.setProductType(getText(productTypeLocator));
			productList.add(productDetail);

		}

		return productList;

	}

	public void ScrollDown(String pixels, int numberOfTime) throws Throwable {

		for (int x = 1; x <= numberOfTime; x++) {
			ScrollDown(pixels);
			Thread.sleep(2000);// TODO : need to make wait until load
			waitUntilDisappear(fullPageLoadingIcon());
		}

	}

	@SuppressWarnings("unused")
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

	public String verifyOfferOn(Locator locator , String platform) throws Throwable, Throwable {

		//driver.navigate().refresh();
		ScrollDown("2000", 5);
		Locator OfferLocators = locator.concatLocator(ExtendedtagOfferLocator());
		List<WebElement> productList = getWebElements(OfferLocators);
		String Offertext = null;
		for (WebElement offer : productList) {
			Locator Offer = new Locator(By.xpath(getAbsoluteXPath(offer)), "offer");
			if(! isDisplayed(Offer))
			{
				TestListner.testing.get().log(LogStatus.INFO,"Offer text not present");
			}
			bringElementIntoView(Offer);
			ScrollDown("250", 1);

			click(Offer);
			Locator OfferDetail = Offer.concatLocator(OfferDetailText());
			Offertext = getText(OfferDetail);
			TestListner.testing.get().log(LogStatus.INFO,"Offer text : "+ Offertext);
			if(platform.contains("nykaaman"))
			{
				click(Offer.concatLocator(OfferDetailCloseIconNykaaMan()));
			}
			else
			{
			click(Offer.concatLocator(OfferDetailCloseIcon()));
			}break;
		}
		return Offertext;

	}

	public ProductDetailData addSimpleProductFromList(Locator locator) throws Throwable, Throwable {
		ProductDetailData productDetailData = new ProductDetailData();
		String ProductName = null;
		String ProductPrice = null;
		System.out.println(locator.getBy());
		List<WebElement> productList = getWebElements(locator);
		boolean isProductAdded = false;
		boolean isWishlist=false;
		for (WebElement product : productList) {
			Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
			Locator extendedLocator;
			Locator extendedLocatorPrice;
			String ProductType;
			if (isDisplayed(MywishListForm())) {
				isWishlist=true;
				extendedLocator = ProductLocator.concatLocator(ExtendedLocatorProductNameWishlist());
				extendedLocatorPrice = ProductLocator.concatLocator(ExtendedLocatorPriceWishlist());
				//System.out.println(extendedLocator.getBy());
				//System.out.println(ProductLocator.getBy());
				ProductName = getText(extendedLocator);
				ProductPrice=getText(extendedLocatorPrice);
				//System.out.println(ProductLocator.concatLocator(ExtendedProductType_wishlist()).getBy());
				ProductType = getAttribute(ProductLocator.concatLocator(ExtendedProductType_wishlist()),"title");
				if(ProductType==null || ProductType.equals(""))
				{
					mouseOver(ProductLocator);
					ProductType = geteText(ProductLocator.concatLocator(ExtendedProductType_wishlist()).getBy());
				}
			} else
			{
				ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
				extendedLocator = ProductLocator.concatLocator(ExtendedLocatorProductName());
				extendedLocatorPrice = ProductLocator.concatLocator(ExtendedLocatorPrice());
				System.out.println(extendedLocator.getBy());
				System.out.println(ProductLocator.getBy());
				ProductName = getText(extendedLocator);
				ProductPrice=getText(extendedLocatorPrice);
				mouseOver(ProductLocator);
				ProductType = getText(ProductLocator.concatLocator(ExtendedProductType()));
				//ProductType=getAttribute(ProductLocator.concatLocator(ExtendedProductType_option()),"title");
			}
			//ProductType=getAttribute(ProductLocator.concatLocator(ExtendedProductType_option()),"title");
			if (ProductType.contains("Bag")|| ProductType.contains("BAG")) {
				TestListner.testing.get().log(LogStatus.INFO, "Trying to Add " + ProductName+" to cart");
				Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), ProductName);
				isProductAdded = addToBagSimple(productLocator,isWishlist);
				if (isProductAdded == true) {
					TestListner.testing.get().log(LogStatus.INFO, ProductName+" added to cart successfully! Checking if the product is present in Cart.");
					/*if (!waitUntilDisplayed(CartPage_React.SlidingCartLogo(), 4)) {
						bringElementIntoView(headerbar.CartLocator());
						click(headerbar.CartLocator());
						if (isInStockMessageInCart()) {

						}
						if (isAlertPresent(driver)) {
							switchToDefaultContent();
							isProductAdded = false;
						}

					}*/
				}
			}
			if (isProductAdded) {
				productDetailData.setProductName(ProductName);
				productDetailData.setProductPrize(replaceRuppeeSymbol(ProductPrice).replace("Rs.", "").trim());
				productDetailData.setProductQuantity("1");
				break;

			}
		}
		return productDetailData;
	}

	public ProductDetailData addSimpleProductFromFilterList(Locator locator) throws Throwable, Throwable {
		ProductDetailData productDetailData = new ProductDetailData();
		String ProductName = null;
		String ProductPrice = null;
		System.out.println(locator.getBy());
		List<WebElement> productList = getWebElements(locator);
		boolean isProductAdded = false;
		boolean isWishlist=false;
		for (WebElement product : productList) {
			Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
			Locator extendedLocator;
			Locator extendedLocatorPrice;
			String ProductType;
			if (isDisplayed(MywishListForm())) {
				isWishlist=true;
				extendedLocator = ProductLocator.concatLocator(ExtendedLocatorProductNameWishlist());
				extendedLocatorPrice = ProductLocator.concatLocator(ExtendedLocatorPriceWishlist());
				//System.out.println(extendedLocator.getBy());
				//System.out.println(ProductLocator.getBy());
				ProductName = getText(extendedLocator);
				ProductPrice=getText(extendedLocatorPrice);
				//System.out.println(ProductLocator.concatLocator(ExtendedProductType_wishlist()).getBy());
				ProductType = getAttribute(ProductLocator.concatLocator(ExtendedProductType_wishlist()),"title");
				if(ProductType==null || ProductType.equals(""))
				{
					mouseOver(ProductLocator);
					ProductType = geteText(ProductLocator.concatLocator(ExtendedProductType_wishlist()).getBy());
				}
			} else
			{
				ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
				extendedLocator = ProductLocator.concatLocator(ExtendedLocatorProductName());
				extendedLocatorPrice = ProductLocator.concatLocator(ExtendedLocatorPrice());
				System.out.println(extendedLocator.getBy());
				System.out.println(ProductLocator.getBy());
				ProductName = getText(extendedLocator);
				ProductPrice=getText(extendedLocatorPrice);
				mouseOver(ProductLocator);
				ProductType = getText(ProductLocator.concatLocator(ExtendedProductType()));
				//ProductType=getAttribute(ProductLocator.concatLocator(ExtendedProductType_option()),"title");
			}
			//ProductType=getAttribute(ProductLocator.concatLocator(ExtendedProductType_option()),"title");
			if (ProductType.contains("Bag")|| ProductType.contains("BAG")) {
				TestListner.testing.get().log(LogStatus.INFO, "Trying to Add " + ProductName+" to cart");
				Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), ProductName);
				isProductAdded = addToBagSimple(productLocator,isWishlist);
				if (isProductAdded == true) {
					TestListner.testing.get().log(LogStatus.INFO, ProductName+" added to cart successfully! Checking if the product is present in Cart.");
					/*if (!waitUntilDisplayed(CartPage_React.SlidingCartLogo(), 4)) {
						bringElementIntoView(headerbar.CartLocator());
						click(headerbar.CartLocator());
						if (isInStockMessageInCart()) {

						}
						if (isAlertPresent(driver)) {
							switchToDefaultContent();
							isProductAdded = false;
						}

					}*/
				}
			}
			if (isProductAdded) {
				productDetailData.setProductName(ProductName);
				productDetailData.setProductPrize(replaceRuppeeSymbol(ProductPrice).replace("Rs.", "").trim());
				productDetailData.setProductQuantity("1");
				break;

			}
		}
		return productDetailData;
	}

	private ProductDetailData addStaticComboFromList(Locator locator) throws Throwable, Throwable {
		System.out.println(locator.getBy());
		ProductDetailData productDetailData = new ProductDetailData();
		String ProductName = null;
		ScrollDown("2000", 5);
		List<WebElement> productList = getWebElements(locator);
		boolean isProductAdded = false;
		for (WebElement product : productList) {
			Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
			Locator extendedLocator = ProductLocator.concatLocator(ExtendedLocatorProductName());
			System.out.println(extendedLocator);
			ProductName = getText(extendedLocator);
			String ProductType = getAttribute(ProductLocator.concatLocator(DataType()), "data");
			System.out.println(ProductType);
			if (ProductType.contains("bundle")) {
				Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), ProductName);
				isProductAdded = addToBagSimple(productLocator,false);
				CartPage_React CartPage_React = new CartPage_React(driver);
				new HeaderBar(driver);
				if (isProductAdded == true) {
					if (!waitUntilDisplayed(CartPage_React.SlidingCartLogo(), 4)) {

						bringElementIntoView(HeaderBar.CartLocator());
						click(HeaderBar.CartLocator());
						if (isInStockMessageInCart()) {

						}
						if (isAlertPresent(driver)) {
							switchToDefaultContent();
							isProductAdded = false;
						}

						/*
						 * else if (CartPage_React.IncreaseQuantityOfProduct(driver))
						 * { isProductAdded = true;
						 * 
						 * }
						 */
					}
				}

				if (isProductAdded) {
					productDetailData.setProductName(ProductName);
					return productDetailData;
				}
			}

		}
		return productDetailData;
	}

	private ProductDetailData addShadeProductFromList(Locator locator) throws Throwable {
		ProductDetailData productDetailData = new ProductDetailData();
		String ProductName = null;
		String productPrice=null;
		boolean isProductAdded = false;
		int count=0;
		waitForPagetoLoad();
		while(! isProductAdded && count<3)
		{
			count+=1;
			List<WebElement> productList_all = new ArrayList<WebElement>();
			List<WebElement> productList = new ArrayList<WebElement>();
			//System.out.println(locator.getBy());
			productList_all = getWebElements(locator);
			if(productList_all.size()>0)
			{
				for(WebElement pr : productList_all)
				{
					if(pr.isDisplayed())
					{
						productList.add(pr);
					}
				}
			}
			else
			{
				productDetailData.setProductName("Widget not found");
				//TestListner.testing.get().log(LogStatus.FAIL, "Widget not displayed on Page" );
				return productDetailData;
			}

			for (WebElement product : productList) {
				Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
				Locator extendedLocator = ProductLocator.concatLocator(ExtendedLocatorProductName());
				Locator extendedLocatorPrice = ProductLocator.concatLocator(ExtendedLocatorPrice());

				//System.out.println(extendedLocatorPrice.getBy());

				//System.out.println(extendedLocator.getBy());
				ProductName = getText(extendedLocator);
				productPrice=getText(extendedLocatorPrice);
				if(ProductName!=null && ProductName.equals(""))
				{
					ProductName = getAttribute(extendedLocator,"title");
				}
				String ProductType;
				boolean isWishlist = false;
				//System.out.println(locator.getName());
				if (locator.getName().contains("wishlist")) {
					ProductType = "Shade";
					isWishlist = true;
				} else {
					mouseOver(ProductLocator);
					ProductType = getText(ProductLocator.concatLocator(ExtendedProductType()));
					//ProductType=getAttribute(ProductLocator.concatLocator(ExtendedProductType_option()),"title");
				}
				if (ProductType.contains("Shade")||ProductType.contains("SHADES")) {
					TestListner.testing.get().log(LogStatus.INFO, "Trying to Add " + ProductName+" to cart");
					Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), ProductName);
					productPrice=addToBagForShades(productLocator, isWishlist);
					isProductAdded = (!productPrice.equalsIgnoreCase("false")?true:false);
					new CartPage_React(driver);
					if (isProductAdded == true) {
						TestListner.testing.get().log(LogStatus.INFO, ProductName+" added to cart successfully! Checking if the product is present in Cart.");
						/*if (!waitUntilDisplayed(CartPage_React.SlidingCartLogo(), 4)) {
							bringElementIntoView(HeaderBar.CartLocator());
							click(HeaderBar.CartLocator());
							if (isInStockMessageInCart()) {

							}
							if (isAlertPresent(driver)) {
								switchToDefaultContent();
								isProductAdded = false;
							}

						}*/
					}
				}

				if (isProductAdded) {

					productDetailData.setProductName(ProductName);
					productDetailData.setProductPrize(replaceRuppeeSymbol(productPrice.replace("Rs.", "").trim()));
					productDetailData.setProductQuantity("1");
					break;
				}

			}

			if(isProductAdded)
			{
				break;
			}
			else
			{
				if(isDisplayed(sliderNextButton()))
				{
					for(WebElement slidernext:getAllElements(sliderNextButton().getBy()))
					{
						bringElementIntoView(sliderNextButton());
						click(slidernext,"Slider Next");
					}
				}
				else
				{
					productDetailData.setProductName("Product type not found");
					break;
				}
			}
		}
		return productDetailData;
	}

	private boolean isInStockMessageInCart() {
		// TODO Auto-generated method stub

		return false;
	}

	public boolean isCustomerAlsoViewedPresent(Locator locator) throws Throwable {
		//ProductDetailData productDetailData = new ProductDetailData();
		String ProductName = null;
		//int count=0;
		List<WebElement> productList = getWebElements(locator);
		boolean isProductAdded = false;
		for (WebElement product : productList) {
			Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
			Locator extendedLocator = ProductLocator.concatLocator(ExtendedLocatorProductName());
			getAttribute(ProductLocator, "id").replaceAll("[^0-9]", "");
			System.out.println(extendedLocator);
			ProductName = getText(extendedLocator);
			String ProductType = getText(ProductLocator);
			ProductType = getAttribute(ProductLocator.concatLocator(ExtendedProductType_option()),"title");
			if (ProductType.contains("Shade")||ProductType.contains("SHADES")||ProductType.contains("shade")) {

				/*	if(count<1)
				{
					count+=1;
					continue;
				}*/
				Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), ProductName);
				boolean isWishlist = false;
				addToBagForShades(productLocator, isWishlist);
				isProductAdded = waitUntilDisplayed(peopleWhoboughtThisAlsoBought(), 10);
				break;

			}
			if (ProductType.contains("Size")||ProductType.contains("SIZE")||ProductType.contains("size")) {
				Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
				boolean isWishlist = false;
				addToBagForSize(productLocator,isWishlist);
				isProductAdded = waitUntilDisplayed(peopleWhoboughtThisAlsoBought(), 10);
				break;

			}

		}
		return isProductAdded;
	}

	public boolean addToWishList(Locator locator, String productType) throws IllegalArgumentException, IllegalAccessException, InstantiationException, Throwable {
		if(productType!=null && productType.equalsIgnoreCase("simple"))
		{
			productType="BAG";
		}
		List<WebElement> productList = new ArrayList<WebElement>();
		List<WebElement> productList_all = new ArrayList<WebElement>();
		productList_all = getWebElements(locator);
		Locator selectedproduct=null;
		Locator wishlistIconStatus=null;
		//System.out.println(locator.getBy());
		productList_all = getWebElements(locator);
		{
			for(WebElement pr : productList_all)
			{
				if(pr.isDisplayed())
				{
					productList.add(pr);
				}
			}
		}
		for (WebElement products : productList) {
			Locator product = new Locator(By.xpath(getAbsoluteXPath(products)), "product");
			//System.out.println(product.getBy());
			Locator productNameLocator = product.concatLocator(ExtendedLocatorProductName());
			// String productName = getAttribute(productNameLocator, "title");
			String productName = getText(productNameLocator);
			product = new Locator(By.xpath(getAbsoluteXPath(products)), productName);
			bringElementIntoView(product);
			//ScrollDown("60");
			// bringElementIntoView(wishlistIcon(productId));

			mouseOver(product);
			mouseOver(product);
			String ProductType = getText(product.concatLocator(ExtendedProductType()));
			if(productType==null || (productType!=null && ProductType.contains(productType.toUpperCase())))
			{
				Locator wishlistIcon=product.concatLocator(ExtendedWishlistLocator());

				// in case product is already in wishlist
				wishlistIconStatus=product.concatLocator(ExtendedWishlistStatusLocator());
				if(!(getAttribute(wishlistIconStatus, "class").equals("fa fa-heart-o")))
				{
					return true;
				}

				if (waitUntilDisplayed(wishlistIcon, 3)) {
					ScrollDown("50");
					mouseOver(wishlistIcon);
					mouseOver(wishlistIcon);
					Thread.sleep(1000);
					click(wishlistIcon);
					selectedproduct=product;
					wishlistIconStatus=product.concatLocator(ExtendedWishlistStatusLocator());
					break;
				}
			}
		}
		Thread.sleep(2000);
		mouseOver(selectedproduct);
		return (getAttribute(wishlistIconStatus, "class").equals("fa fa-heart"));
		//return waitUntilDisplayed(WishlistMessage(productId, "wishadded"), 6);

	}

	private ProductDetailData addSizeProductFromList(Locator locator) throws Throwable {
		String ProductName = null;
		String ProductPrice = null;
		ProductDetailData productDetailData = new ProductDetailData();
		boolean isProductAdded = false;
		int count=0;
		while(! isProductAdded && count<3)
		{
			count+=1;
			List<WebElement> productList_all = new ArrayList<WebElement>();
			List<WebElement> productList = new ArrayList<WebElement>();
			//System.out.println(locator.getBy());
			productList_all = getWebElements(locator);
			if(productList_all.size()>0)
			{
				for(WebElement pr : productList_all)
				{
					if(pr.isDisplayed())
					{
						productList.add(pr);
					}
				}
			}
			else
			{
				productDetailData.setProductName("Widget not found");
				TestListner.testing.get().log(LogStatus.FAIL, "Widget not displayed on Page" );
				return productDetailData;
			}

			for (WebElement product : productList) {
				Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
				Locator extendedLocator = ProductLocator.concatLocator(ExtendedLocatorProductName());
				Locator extendedLocatorPrice = ProductLocator.concatLocator(ExtendedLocatorPrice());
				//System.out.println(ProductLocator.getBy());

				System.out.println(extendedLocator.getBy());
				ProductName = getText(extendedLocator);
				ProductPrice=getText(extendedLocatorPrice);
				if(ProductName!=null && ProductName.equals(""))
				{
					ProductName = getAttribute(extendedLocator,"title");
				}
				String ProductType;
				boolean isWishlist = false;
				//System.out.println(locator.getName());
				if (locator.getName().contains("wishlist")) {
					ProductType = "Shade";
					isWishlist = true;
				} else {
					mouseOver(ProductLocator);
					ProductType = getText(ProductLocator.concatLocator(ExtendedProductType()));
					//ProductType=getAttribute(ProductLocator.concatLocator(ExtendedProductType_option()),"title");
				}
				if (ProductType.contains("Size")||ProductType.contains("SIZE")) {
					TestListner.testing.get().log(LogStatus.INFO, "Trying to Add " + ProductName+" to cart");
					Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), ProductName);
					ProductPrice=addToBagForSize(productLocator, isWishlist);
					isProductAdded = (!ProductPrice.equalsIgnoreCase("false")?true:false);
					if (isProductAdded == true) {
						TestListner.testing.get().log(LogStatus.INFO, ProductName+" added to cart successfully! Checking if the product is present in Cart.");
						/*if (!waitUntilDisplayed(CartPage_React.SlidingCartLogo(), 4)) {
							bringElementIntoView(HeaderBar.CartLocator());
							click(HeaderBar.CartLocator());
							if (isInStockMessageInCart()) {

							}
							if (isAlertPresent(driver)) {
								switchToDefaultContent();
								isProductAdded = false;
							}

						}*/
					}
				}

				if (isProductAdded) {

					productDetailData.setProductName(ProductName);
					productDetailData.setProductPrize(replaceRuppeeSymbol(ProductPrice).replace("Rs.", "").replace(",", "").trim());
					productDetailData.setProductQuantity("1");
					break;
				}

			}

			if(isProductAdded)
			{
				break;
			}
			else
			{
				if(isDisplayed(sliderNextButton()))
				{
					for(WebElement slidernext:getAllElements(sliderNextButton().getBy()))
					{
						bringElementIntoView(sliderNextButton());
						click(slidernext,"Slider Next");
					}
				}
				else
				{
					productDetailData.setProductName("Product type not found");
					break;
				}
			}
		}
		return productDetailData;
	}

	private String addToBagForShades(Locator productLocator, boolean iswishlist) throws Throwable {
		String variantprice = "false";

		// Locator ProductILocator =
		// productLocator.concatLocator(extendedLocatorProductID());
		String productid = getAttribute(productLocator, "id").replaceAll("[^0-9]", "");
		//	System.out.println(productid);
		if (iswishlist == false) {
			AddToBagShadeProduct(productLocator);
		} else {
			addToBagWishlistShade(productLocator, productid);
		}
		//	waitUntilOverlayDisappear(driver);
		Thread.sleep(5000);
		List<WebElement> shades = getWebElements(shades());
		if (!shades.isEmpty()) {
			for (WebElement shade : shades) {
				Locator shadeLocator = new Locator(By.xpath(getAbsoluteXPath(shade)), "Shade");
				//System.out.println(shadeLocator.getBy());
				String ShadeName = getAttribute(shadeLocator.concatLocator(shadeName()), "title");
				//	System.out.println(shadeLocator.getBy());
				bringElementIntoView(shadeLocator);
				//ScrollDown("200");
				click(shade,ShadeName);
				break;
			}
			//JavascriptExecutor js = (JavascriptExecutor) driver;
			// bringElementIntoView(AddToBagButton(productid));
			//ScrollDown("200");
			if(waitUntilDisplayed(AddToBagButtonFromView(), 3))
			{
				bringElementIntoView(AddToBagButtonFromView());
				variantprice=geteText(productLocator.concatLocator(variantPrice()).getBy()).replaceAll("[^0-9]", "");
				click(AddToBagButtonFromView());

				//waitUntilDisappear(addToBagLoadingIcon());
			}
			else
			{
				if(iswishlist==true)
				{
					variantprice=geteText(productLocator.concatLocator(variantPrice()).getBy()).replaceAll("[^0-9]", "");
					click( addToBagButtonOfWishlistProduct());
				}
				else{
					click(AddToBagButton(productid));
				}
			}
			Thread.sleep(7000);
			if (isPresent(outOfStockMessage())) {
				//click(closeButton());
				Thread.sleep(2000);
				if (isAlertAvailable()) {
					click(backButton());
				}
				waitUntilDisappear(backButton());
				if(isDisplayed(closeLink(productid))){
					click(closeLink(productid));
				}

			}

		}

		else
		{
			click(closeShadeOverlay());
		}

		return variantprice;

	}

	private void addToBagWishlistShade(Locator productLocator, String productId) throws IllegalArgumentException, IllegalAccessException, Throwable {
		// TODO Auto-generated method stub
		bringElementIntoView(seeShadeButton(productId));
		/* mouseOver(ProducttypeLocator); */
		System.out.println(seeShadeButton(productId));
		click(seeShadeButton(productId));
	}

	/*public boolean addToBagSimpleProduct(ProductDetailData productDetailData) throws Throwable, Throwable {
		String ProductName = productDetailData.getProductName().replace("...", "");
		bringElementIntoView(ProductName(ProductName));
		boolean isSimpleProductAdded = false;
		mouseOver(ProductName(ProductName));
		Locator ProductTypeLocator = ProductName(ProductName).concatLocator(ExtendedLocatorProductType());
		String productType = getText(ProductTypeLocator);
		if (productType.contains("BAG")) {
			Locator productIDLocator = ProductName(ProductName).concatLocator(extendedLocatorProductID());
			String productid = getAttribute(productIDLocator, "id").replaceAll("[^0-9]", "");
			Locator NotifyMeLocator = ProductLayoutByProductName(ProductName).concatLocator(NotifyMe());
			System.out.println(NotifyMeLocator.getBy());
			mouseOver(ProductName(ProductName));
			if(waitUntilDisplayed(NotifyMeLocator, 2)){
				isSimpleProductAdded = false;
			}
			else{
				//bringElementIntoView(AddToBagButton(productid));
				click(AddToBagButton(productid));
				isSimpleProductAdded = true;

				Thread.sleep(4000);
				CartPage_React CartPage_React = new CartPage_React(driver);
				if (isPresent(outOfStockMessage())) {
					click(closeButton());
					Thread.sleep(2000);
					if (isAlertAvailable()) {
						clickJScript(backButton());
					}
					waitUntilDisappear(backButton());
				click(closeLink(productid));
					isSimpleProductAdded = false;



				}
				else
					if(CartPage_React.isInstockMessageDisplay())
					{
						CartPage_React.removeFirstProduct();
						isSimpleProductAdded = false;
					}
			}
		}

		return isSimpleProductAdded;

	}*/

	public boolean addToBagSimple(Locator productLocator,boolean isWishlist) throws Throwable {
		boolean isProductAdded=false;
		mouseOver(productLocator);
		Locator ProducttypeLocator = productLocator.concatLocator(ExtendedProductType());
		if(waitUntilDisplayed(ProducttypeLocator, 3))
		{
			bringElementIntoView(ProducttypeLocator);
			click(ProducttypeLocator);
			isProductAdded = true;
			//waitUntilDisappear(addToBagLoadingIcon());
		}
		else if(isWishlist==true)
		{

			click( addToBagButtonOfWishlistProduct());
			isProductAdded = true;
		}

		Thread.sleep(4000);
		if (isPresent(outOfStockMessage())) {
			click(closeButton());
			Thread.sleep(2000);
			if (isAlertAvailable()) {
				click(backButton());
			}
			waitUntilDisappear(backButton());
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

	public String addToBagForSize(Locator productLocator, boolean isWishlist) throws Throwable, Throwable {
		String variantprice="false";
		System.out.println(productLocator.getBy().toString());
		String productId = getAttribute(productLocator, "id").replaceAll("[^0-9]", "");
		if (isWishlist == false) {
			selectSize(productLocator);
		} else {
			addToBagWishlistSize(productLocator,productId);
		}
		//	waitUntilOverlayDisappear(driver);
		Thread.sleep(5000);
		List<WebElement> sizes = getWebElements(sizes());
		if (!sizes.isEmpty()) {
			for (WebElement size : sizes) {
				Locator sizeLocator = new Locator(By.xpath(getAbsoluteXPath(size)), "Size");
				System.out.println(sizeLocator.getBy());
				String SizeValue = getText(sizeLocator.concatLocator(sizeValue()));
				//	System.out.println(shadeLocator.getBy());
				bringElementIntoView(sizeLocator);
				//ScrollDown("200");
				click(size,SizeValue);
				break;
			}
			//JavascriptExecutor js = (JavascriptExecutor) driver;
			// bringElementIntoView(AddToBagButton(productid));
			//ScrollDown("200");
			if(waitUntilDisplayed(AddToBagButtonFromView(), 3))
			{
				bringElementIntoView(AddToBagButtonFromView());
				variantprice=geteText(productLocator.concatLocator(variantPrice()).getBy()).replaceAll("[^0-9]", "");
				click(AddToBagButtonFromView());
				//waitUntilDisappear(addToBagLoadingIcon());
			}
			else
			{
				if(isWishlist==true)
				{
					click( addToBagButtonOfWishlistProduct());
					variantprice=geteText(productLocator.concatLocator(variantPrice()).getBy()).replaceAll("[^0-9]", "");
				}
				else{
					click(AddToBagButton(productId));
				}
			}
			Thread.sleep(4000);
			if (isPresent(outOfStockMessage())) {
				click(closeButton());
				Thread.sleep(2000);
				if (isAlertAvailable()) {
					click(backButton());
				}
				waitUntilDisappear(backButton());
				click(closeLink(productId));
				variantprice = "false";

			}

		}

		else
		{
			click(closeShadeOverlay());
		}

		return variantprice;

	}

	private void addToBagWishlistSize(Locator productLocator, String productId) throws IllegalArgumentException, IllegalAccessException, Throwable {
		// TODO Auto-generated method stub
		bringElementIntoView(productLocator);
		mouseOver(productLocator);
		productLocator.concatLocator(extendedLocatorProductID());
		click(seeShadeButton(productId));


	}

	private boolean isAlertAvailable() {
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
		Locator ProducttypeLocator = locator.concatLocator(ExtendedProductType());
		Thread.sleep(4000);
		mouseOver(locator);
		bringElementIntoView(ProducttypeLocator);
		ScrollDown("100");
		click(ProducttypeLocator);
	}

	private void AddToBagShadeProduct(Locator locator) throws Throwable {
		// Locator ratingbox = locator.concatLocator(productName());
		Locator ProducttypeLocator = locator.concatLocator(ExtendedProductType());
		Thread.sleep(4000);
		mouseOver(locator);
		bringElementIntoView(ProducttypeLocator);
		ScrollDown("100");
		mouseOver(locator);
		click(ProducttypeLocator);

	}

	public void serviceAddToCart(ProductDetailData productDetailData) throws Throwable, Throwable {
		SelectLocationOfBeutyService("Mumbai", productDetailData.getLocation());
		bringElementIntoView(
				bookNowButtonFromListing(productDetailData.getProductName(), productDetailData.getLocation()));
		click(bookNowButtonFromListing(productDetailData.getProductName(), productDetailData.getLocation()));

	}

	/*private void clickaddToCart(WebElement webElement) throws InterruptedException {
		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(false);",
		// driver.findElement(By.xpath("//a[text()='Irresistible Combos']")));
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",
				webElement
	 * webElement.findElement(By.xpath(
	 * "//following-sibling::div[@class='litre']"))
				 );
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
	 */
	public void waitUntilOverlayDisappear(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 90);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='overlay']")));

	}

	/*public boolean addToBagStaticComboProduct(WebElement bestSellingProduct) throws InterruptedException {
		// TODO Auto-generated method stub
		try {
			clickaddToCart(bestSellingProduct);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}*/

	public List<ProductDetailData> ApplySort(String sortName) throws Throwable {
		ArrayList<String> printList=new ArrayList<String>();
		bringElementIntoView(SortDropDown());
		if(getAttribute(SortOptionsContainer(), "class").contains("hide"))
		{
			click(SortDropDown());
		}
		selectFromRadioOptions(SortOptions(), sortName);
		waitUntilDisappear(fullPageLoadingIcon());
		ScrollDown("1000", 3);
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

	private List<ProductDetailData> getListOfProduct(String sortName) throws IllegalAccessException {
		List<WebElement> productList = getWebElements(allProducts());
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

			//System.out.println(productLocator.concatLocator(ExtendedLocatorPrice()).getBy());
			String prizeOfProduct = (getText(productLocator.concatLocator(ExtendedLocatorPrice())).replaceAll("\\D+", ""));
			String ProductName = getText(productLocator.concatLocator(ExtendedLocatorProductName()));
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

	public List<ProductDetailData> ApplyFilter(String FilterName, String Criteria,boolean countProducts,boolean clearFilter) throws Throwable, Throwable {
		int count=0;
		String allCriteria[]=Criteria.split("----");
		List<ProductDetailData> ProductDataList =new ArrayList<ProductDetailData>();
		try
		{
			if(clearFilter)
			{
				clearAllFilters();
				clearAllFilters();
			}
			Locator options = expandFilter(FilterName);

			for(int i=0;i<allCriteria.length;i++)
			{
				if(allCriteria[i].contains("searchBrand"))
				{
					allCriteria[i]=allCriteria[i].split("\\(")[0].replaceAll("searchBrand", "");
					ClearValue(brandSearchBoxLocator());
					EnterValue(brandSearchBoxLocator(),allCriteria[i]);
				}

				for(WebElement option: getAllElements(options.getBy()))
				{
					waitForPagetoLoad();
					System.out.println(option.getText());

					if(option.getText().contains(allCriteria[i].trim()))
					{
						count+=Integer.parseInt(option.getText().split("\\(")[1].replaceAll("\\)", ""));
						click(option, allCriteria[i]);
						waitUntilDisappear(pageLoader());
						break;
					}
				}
			}
			waitUntilDisappear(pageLoader());
			waitForPagetoLoad();
			LoadMoreProducts(3,false);
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
				LoadMoreProducts(1,true);
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
		try
		{
			if(isDisplayed(ClearAllFiltersLocator()))
			{
				flag=false;
				bringElementIntoView(ClearAllFiltersLocator());
				ScrollDown("50");
				click(ClearAllFiltersLocator());
				waitForPagetoLoad();
				waitUntilDisappear(pageLoader());
				flag=true;
			}
		}
		catch(Exception e)
		{
			flag=false;
		}
		return flag;
	}

	public Locator expandFilter(String FilterName) throws IllegalAccessException, Throwable, InstantiationException {
		bringElementIntoView(filter(FilterName));
		if(getAttribute(filter(FilterName).concatLocator(filterStatus()), "class").contains("slide-up"))
		{
			click(filter(FilterName));
		}
		Locator options=filter(FilterName).concatLocator(FilterCheckbox());
		waitUntilDisplayed(options, 3);
		return options;
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
						bringElementIntoView(footerHelp());
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
						click(LoadMoreButton());
						waitForPagetoLoad();
					}
					else
					{
						waitForPagetoLoad();
						bringElementIntoView(footerHelp());
						endReached=true;
					}
				}
			}
		}
		catch (Exception e)
		{

		}
	}

	public boolean backToTop() throws Throwable {
		try 
		{
			waitUntilDisplayed(backToTopButton(),3);
			click(backToTopButton());
			Thread.sleep(1000);
			Long value = (Long)((JavascriptExecutor) driver).executeScript("return window.pageYOffset;");
			return(value==0?true:false);
		}
		catch (Exception e)
		{
			return false;
		}
	}


	public boolean Notifyme(AccountData Login) throws Throwable {
		List<WebElement> productList = oosproductlist();
		boolean NotifyfromPLP=false;
		for (WebElement product : productList) {
			NotifyfromPLP = checkNotifyFromListing(product, Login);
			if (NotifyfromPLP == true) {
				break;
			}
		}
		return NotifyfromPLP;
	}

	public boolean checkNotifyFromListing(WebElement product, AccountData accountData) throws Throwable, Throwable {
		Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
		Locator ProductTypeLocator = ProductLocator.concatLocator(NotifyMe());
		bringElementIntoView(ProductTypeLocator);
		ScrollDown("50");
		boolean isNotifybuttonDisplayed = false;
		System.out.print(ProductTypeLocator);
		mouseOver(ProductLocator);
		String productType = getText(NotifyMe());
		if (productType.contains("NOTIFY")) {
			click(ProductTypeLocator);
			waitUntilDisplayed(NotifyEmail(), 5);
			if(isDisplayed(ShadeSizeSelectionPLP())){
				selectDefaultValueFromDropDownBox(ShadeSizeSelectionPLP());
			}
			EnterValue(NotifyEmail(), accountData.getUsername());
			click(NotifyMeButtonPopup());
			String OutofStockmsg="";
			int counter=0;
			while(OutofStockmsg.equals("") && counter<20) {
				counter+=1;
				OutofStockmsg = driver.findElement(outOfStockNotifyEmail().getBy()).getText();
				if(!OutofStockmsg.equals("")) {
					break;
				}
			}
			System.out.print(OutofStockmsg);
			isNotifybuttonDisplayed = OutofStockmsg.contains("You are subscribed");
		}

		return isNotifybuttonDisplayed;
	}

	public List<WebElement> oosproductlist() throws Throwable {
		ArrayList<String> allOptions = new ArrayList<String>();
		allOptions = getFilterOptionsList("Price");
		ApplyFilter("Price", allOptions.get(4), false, false);
		List<WebElement> productList = getWebElements(oosProducts());
		return productList;
	}

	public boolean IslistingPagereactornot() {
		WebDriverWait web = new WebDriverWait(driver, 1000);

		try {
			//web.until(ExpectedConditions.presenceOfElementLocated(SortDropDown().getBy()));
			if(isDisplayed(SortDropDown())){
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}


	public boolean Tiptile(String environmentURL) throws Throwable, Throwable {
		Framework framework = new Framework();
		String url=driver.getCurrentUrl().toString();
		String catId= url.substring(url.indexOf("id")+2,url.indexOf("root")-1);
		String status = null;
		String API_URL = "https://www.nykaa.com/app-api/index.php/react/tip_tile?category_id" + catId;
		JsonObject Result = framework.Get_API_Response(driver, API_URL);
		status = Result.get("status").toString();
		String resultTrimmed=Result.get("result").toString().replace("[","").replace("]","");
		JsonObject result=new JsonParser().parse(resultTrimmed).getAsJsonObject();
		if(result.isJsonObject() ) {
			String URL_D = result.get("url_d").toString();
			String Position = result.get("position").toString().replace("\"","");
			Integer Test = (Integer.parseInt(Position) + 1);
			Position = Test.toString();
			if (status != "0" && URL_D != null){
				String test = String.valueOf(TiptileProducts(Position));
				clickandNavigateWindow(TiptileProducts(Position), URL_D,environmentURL );
				String redirect_Url=driver.getCurrentUrl();
				if(URL_D != redirect_Url){
					status = "0";
				}

			}
			if(status.equals("0")){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}

}