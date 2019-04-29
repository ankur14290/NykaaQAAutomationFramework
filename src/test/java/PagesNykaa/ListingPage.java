package PagesNykaa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import DataNykaa.ProductDetailData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Locator;
import FrameWorkNykaa.TestListner;



public class ListingPage extends BrowserAction {

	public ListingPage(WebDriver driver) {
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

	/*Locator NotifyMe(){
	return new Locator(By.xpath("//*[text()='Notify me']"),"Notify Me");
	}*/

	Locator filter(String FilterName) {
	return new Locator(By.xpath("//div[@filt-typ='" + FilterName + "']"), " Filter " + FilterName + "");
	}

	Locator wishlistPane() {
	return new Locator(By.xpath("//p[contains(@class,'add-to-wishlist wishlist-id-')]"), "Wishlist pane");
	}

	Locator FilterCheckbox(String FilterCheckOption) {
	return new Locator(By.xpath("//a[@class='m-checkbox-unchecked'][@title='" + FilterCheckOption + "']"),
	"Filter Checkbox " + FilterCheckOption + "");
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
	return new Locator(By.xpath("//div[@class='litre']"), "");
	}

	Locator ExtendedProductType_option() {
	return new Locator(By.xpath("//div[@class='litre']/following-sibling::p/a"), "Product type");
	}
	Locator ExtendedProductType_price() {
	return new Locator(By.xpath("//preceding-sibling::h3[@class='product-name']/following-sibling::div[@class='price-box']//span"), "Product price");
	}

	Locator ExtendedProductType_wishlist() {
	return new Locator(By.xpath("//h3[@class='product-name']/following-sibling::p/a"), "Product type");
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

	Locator OfferDetailText() {
	return new Locator(By.xpath("/ancestor::div/preceding-sibling::div[contains(@id,'offer_detail_l')]//ul//a"),
	"Offer Detail");
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

	Locator PriceLocator() {
	return new Locator(By.xpath("//span[@class='price']"), "price");
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
	return new Locator(By.xpath("//div[@class='add-cartr-btn']"),"Add To Bag Button");
	}



	Locator AddToBagButtonFromView(String productid){
	return new Locator(By.xpath("//div[@class='add-cartr-btn'][contains(@onclick,'"+productid+"')]"),"Add To Bag Button");
	}
	// a[contains(@onclick,'" + ProductID + "')][@class='button btn-cart']|
	Locator shades(String productid) {
	return new Locator(
	By.xpath("//li[@id='product-opt-" + productid
	+ "']//div[@class='product-view my_product_view'][not(contains(@style,'display: none'))]/descendant::p[@class='pickshade'][contains(text(),'Available shade')]/following-sibling::div/descendant::img[contains(@id,'ch_img_')]|//div[@id='product_view_"+ productid
	+ "']//img[contains(@id,'ch_img_')]"),
	"shades");
	}

	public Locator AllProducts() {
	return new Locator(
	By.xpath(
	"//a[text()='All Products']/ancestor::div[@class='content_inner ']/following-sibling::div[@class='category-products content_inner']/descendant::div[@class='litre']"),
	"All Product");

	}

	public Locator allProducts() {
	return new Locator(
	By.xpath(
	"//a[text()='All Products']/ancestor::div[@class='content_inner ']/following-sibling::div[@class='category-products content_inner']//ul[@id='product_list_ul']/li[contains(@id,'product-opt')] //div[@class='ratings'][not(contains(text(),'Out of stock'))]//ancestor::li[contains(@id,'product-opt')]"),
	"All Product");

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
	return new Locator(By.xpath("//div[@id='tagclickoffer']"), "Offer Tag");
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
	return new Locator(By.xpath("//preceding-sibling::h3[@class='product-name']/a[1]"), " product Names");
	}

	Locator ExtendedLocatorProductType() {
	return new Locator(By.xpath("//ancestor::h3[@class='product-name']/following-sibling::div[contains(@class,'litre')]/following-sibling::p[contains(@class,'hover-addtocart')]/a"),
	"Product Type");

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
	return new Locator(By.xpath("//p[@class='desk-disc-tag']"), "Discount Tag");
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
	"//p[@id='exceed_message'][contains(text(),'out of stock') or contains(text(),'Out Of Stock') or contains(text(),'is not available.')]"),
	"Out of Stock");
	}

	Locator breadCrumb() {
	return new Locator(By.xpath("//div[@class='breadcrumb-cat']/ul| //ol[@class='breadcrumb product_des-breadcrumb']"), "bread crumb");
	}

	Locator totalSearchedProductLocator() {

	return new Locator(By.id("totalprod"), "total Search product result text");
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

	Locator NotifyMe(){
	return new Locator(By.xpath("/ancestor::h3/following-sibling::p/a"),"Notify Me");
	}

	Locator NotifyEmail () {
	return new Locator(By.xpath("//div[@id!='outofstockSubscriptionOnCategory']//input[@name='subscription_email']"),"Notify Me Email");
	}

	Locator ShadeSizeSelection (){
	return new Locator(By.xpath("//select[contains(@id,'select')]"), "Shade and Size Selction");
	}

	Locator NotifyMeButtonPopup(){
	return new Locator(By.xpath("//div[@id!='outofstockSubscriptionOnCategory']//*[@value='NOTIFY ME']"), "Notify Me Button in Pop-up");
	}

	Locator outOfStockNotifyEmail () {
	return new Locator(By.xpath("//div[@class='out-of-stock']//li[@class='aj_msg success-msg']//span"), "Notification Message Check");
	}

	Locator notifyMePopupclose(){
	return new Locator(By.id("closelb"),"close Tab");
	}

	public Locator OOSProducts () {
	return new Locator(By.xpath("//a[text()='All Products']/ancestor::div[@class='content_inner ']/following-sibling::div[@class='category-products content_inner']//ul[@id='product_list_ul']/li[contains(@id,'product-opt')] //div[@class='ratings'][(contains(text(),'Out of stock'))]//ancestor::li[contains(@id,'product-opt')]") , "OOS");
	}

	Locator outOfStockNotifyMessage(){
	return new Locator(By.xpath("//ul[@class='messages']//li[@class='aj_msg success-msg']//span"), "Notification Success message");
	}

	Locator NotifyEmailPD(){
	return new Locator(By.xpath("//input[@id='subscription_email']"),"Notify Email in PD");
	}

	public static Locator SearchProductlist(){
	   return new Locator(By.xpath("//li[contains(@class,'n_prod_block product-box item')]"),"SearchResultList");
	}
	
	public ProductDetailData addAnyShadeProductFromWidget() throws Throwable {
	CartPage cartPage = new CartPage(driver);
	if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
	click(cartPage.BackMenuCart());
	}
	return addShadeProductFromList(widgetProducts());
	}

	public ProductDetailData addAnyShadeProductFromPeopleWhoBoughtWidget() throws Throwable {
	CartPage cartPage = new CartPage(driver);
	if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
	click(cartPage.BackMenuCart());
	}
	bringElementIntoView(peopleWhoboughtFrame());
	//click(leftArrow());
	return addShadeProductFromList(peopleAlsoBoughtProduts());
	}

	public ProductDetailData addAnyShadeProductFromSearchedProductList() throws Throwable {
	CartPage cartPage = new CartPage(driver);
	if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
	click(cartPage.BackMenuCart());
	}

	return addShadeProductFromList(searchedProductsList());
	}

	public ProductDetailData addAnyShadeProductFromSearchProducts() throws Throwable {
	   closeCart();
	   return addShadeProductFromList(SearchProductlist());
	}
	
	public ProductDetailData addSimpleProductFromSearchedProductList() throws Throwable {
	CartPage cartPage = new CartPage(driver);
	if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
	click(cartPage.BackMenuCart());
	}

	return addSimpleProductFromList(searchedProductsList());
	}

	public ProductDetailData addAnySimpleProductFromWidget() throws Throwable {
	CartPage cartPage = new CartPage(driver);
	if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
	click(cartPage.BackMenuCart());
	}
	return addSimpleProductFromList(widgetProducts());
	}

	public ProductDetailData addAnySimpleProductFromWishlist() throws Throwable {
	CartPage cartPage = new CartPage(driver);
	if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
	click(cartPage.BackMenuCart());
	}
	waitUntilDisplayed(MywishListForm(), 20);
	return addSimpleProductFromList(wishlistProducts());
	}

	public ProductDetailData addAnySimpleProductFromAllProducts() throws Throwable {
	CartPage cartPage = new CartPage(driver);
	if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
	click(cartPage.BackMenuCart());
	}
	return addSimpleProductFromList(allProducts());
	}

	public void navigateToShopAll(String PageName,String environmentURL) throws Throwable, Throwable {

	if (waitUntilDisplayed(ShopAllLink(), 5)) {
	bringElementIntoView(ShopAllLink());
	clickandNavigateWindow(ShopAllLink(), PageName, environmentURL );


	driver.get(driver.getCurrentUrl().replace("http://www.nykaa.com",environmentURL));

	}
	}

	public ProductDetailData addAnyShadeProductFromAllProduct() throws Throwable {
	CartPage cartPage = new CartPage(driver);
	if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
	click(cartPage.BackMenuCart());
	waitUntilDisappear(cartPage.BackMenuCart());
	}
	return addShadeProductFromList(allProducts());

	}

	public ProductDetailData addAnyShadeProductwishlist() throws Throwable {
	CartPage cartPage = new CartPage(driver);
	if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
	click(cartPage.BackMenuCart());
	waitUntilDisappear(cartPage.BackMenuCart());
	}
	return addShadeProductFromList(wishlistProducts());

	}

	public ProductDetailData addAnySizeProductwishlist() throws Throwable {
	CartPage cartPage = new CartPage(driver);
	if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
	click(cartPage.BackMenuCart());
	waitUntilDisappear(cartPage.BackMenuCart());
	}
	return addSizeProductFromList(wishlistProducts());

	}


	public ProductDetailData addAnyStaticProductFromAllProduct() throws Throwable {
	CartPage cartPage = new CartPage(driver);
	if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
	click(cartPage.BackMenuCart());
	waitUntilDisappear(cartPage.BackMenuCart());
	}
	return addStaticComboFromList(allProducts());

	}

	public ProductDetailData addSimpleProductFromAllProduct() throws Throwable {
	CartPage cartPage = new CartPage(driver);
	if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
	click(cartPage.BackMenuCart());
	}
	return addSimpleProductFromList(AllProducts());

	}

	public ProductDetailData addAnySizeProductFromAllProduct() throws Throwable {
	CartPage cartPage = new CartPage(driver);
	if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
	click(cartPage.BackMenuCart());
	}
	return addSizeProductFromList(allProducts());

	}

	public ProductDetailData addAnySizeProductFromWidget() throws Throwable {
	CartPage cartPage = new CartPage(driver);
	if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
	click(cartPage.BackMenuCart());
	}
	return addSizeProductFromList(widgetProducts());

	}

	public List<ProductDetailData> getProductList(Locator locator) throws Throwable, Throwable {

	List<WebElement> productElements = getWebElements(locator);
	List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
	for (WebElement webelement : productElements) {
	Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(webelement)), "Product");
	Locator productNameLocator = ProductLocator.concatLocator(productName());
	Locator PriceLocator = ProductLocator.concatLocator(PriceLocator());
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
	Thread.sleep(8000);// TODO : need to make wait until load
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
	driver.findElement(By.xpath("//p[@id='exceed_message']"));

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

	return getWebElements(AllProducts()).size();

	}

	public int getSearchedProductCount(){
	return getWebElements(wishlistProducts()).size();
	}

	public String verifyOfferOn(Locator locator) throws Throwable, Throwable {

	driver.navigate().refresh();
	Locator OfferLocators = locator.concatLocator(ExtendedtagOfferLocator());
	List<WebElement> productList = getWebElements(OfferLocators);
	String Offertext = null;
	for (WebElement offer : productList) {
	Locator Offer = new Locator(By.xpath(getAbsoluteXPath(offer)), "offer");
	bringElementIntoView(Offer);
	ScrollDown("250", 1);

	click(Offer);
	Locator OfferDetail = Offer.concatLocator(OfferDetailText());
	Offertext = getText(OfferDetail);
	break;
	}
	return Offertext;

	}

	public ProductDetailData addSimpleProductFromList(Locator locator) throws Throwable, Throwable {
	ProductDetailData productDetailData = new ProductDetailData();
	String ProductName = null;
	String productPrice=null;
	System.out.println(locator.getBy());
	List<WebElement> productList = getWebElements(locator);
	boolean isProductAdded = false;
	for (WebElement product : productList) {
	Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
	Locator extendedLocator = ProductLocator.concatLocator(productName());
	System.out.println(extendedLocator.getBy());
	System.out.println(ProductLocator.getBy());
	ProductName = getText(extendedLocator);
	String ProductType;
	if (isDisplayed(MywishListForm())) {
	System.out.println(ProductLocator.concatLocator(ExtendedProductType_wishlist()).getBy());
	ProductType = getAttribute(ProductLocator.concatLocator(ExtendedProductType_wishlist()),"title");
	if(ProductType==null || ProductType.equals(""))
	{
	ProductType = geteText(ProductLocator.concatLocator(ExtendedProductType_wishlist()).getBy());
	}
	} else
	{
	ProductType = getText(ProductLocator.concatLocator(ExtendedProductType()));
	ProductType=getAttribute(ProductLocator.concatLocator(ExtendedProductType_option()),"title");
	productPrice=getText(ProductLocator.concatLocator(ExtendedProductType_price())).replace(",", "").replaceAll("Rs.", "").trim();
	}
	if(Integer.parseInt(productPrice)<1)
	{
	continue;
	}
	if (ProductType.contains("Bag")|| ProductType.contains("BAG")) {
	TestListner.testing.get().log(LogStatus.INFO, "Trying to Add " + ProductName+" to cart");
	Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), ProductName);
	isProductAdded = addToBagSimple(productLocator);
	CartPage cartPage = new CartPage(driver);
	if (isProductAdded == true) {
	TestListner.testing.get().log(LogStatus.INFO, ProductName+" added to cart successfully! Checking if the product is present in Cart.");
	if (!waitUntilDisplayed(cartPage.SlidingCartLogo(), 4)) {
	bringElementIntoView(HeaderBar.CartLocator());
	click(HeaderBar.CartLocator());
	if (isInStockMessageInCart()) {

	}
	if (isAlertPresent(driver)) {
	switchToDefaultContent();
	isProductAdded = false;
	}

	}
	}
	}
	if (isProductAdded) {
	productDetailData.setProductName(ProductName);
	productDetailData.setProductPrize(productPrice);
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
	isProductAdded = addToBagSimple(productLocator);
	CartPage cartPage = new CartPage(driver);
	if (isProductAdded == true) {
	if (!waitUntilDisplayed(cartPage.SlidingCartLogo(), 4)) {

	bringElementIntoView(HeaderBar.CartLocator());
	click(HeaderBar.CartLocator());
	if (isInStockMessageInCart()) {

	}
	if (isAlertPresent(driver)) {
	switchToDefaultContent();
	isProductAdded = false;
	}

	/*
	 * else if (cartPage.IncreaseQuantityOfProduct(driver))
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
	List<WebElement> productList = getWebElements(locator);
	boolean isProductAdded = false;
	for (WebElement product : productList) {
	Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
	System.out.println(ProductLocator.getBy());
	Locator extendedLocator = ProductLocator.concatLocator(ExtendedLocatorProductName());

	System.out.println(extendedLocator.getBy());
	ProductName = getText(extendedLocator);
	if(ProductName!=null && ProductName.equals(""))
	{
	ProductName = getAttribute(extendedLocator,"title");
	}
	String ProductType;
	boolean isWishlist = false;
	System.out.println(locator.getName());
	if (locator.getName().contains("wishlist")) {
	ProductType = "Shade";
	isWishlist = true;
	} else {
	ProductType = getText(ProductLocator.concatLocator(ExtendedProductType()));
	ProductType=getAttribute(ProductLocator.concatLocator(ExtendedProductType_option()),"title");
	productPrice=getText(ProductLocator.concatLocator(ExtendedProductType_price())).replace(",", "").replaceAll("Rs.", "").trim();
	}
	if (ProductType.contains("Shade")||ProductType.contains("SHADES")) {
	TestListner.testing.get().log(LogStatus.INFO, "Trying to Add " + ProductName+" to cart");
	Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), ProductName);
	//productPrice=getText(productLocator.concatLocator(ExtendedProductType_price())).replace(",", "").replaceAll("Rs.", "").trim();
	isProductAdded = addToBagForShades(productLocator, isWishlist);
	CartPage cartPage = new CartPage(driver);
	if (isProductAdded == true) {
	TestListner.testing.get().log(LogStatus.INFO, ProductName+" added to cart successfully! Checking if the product is present in Cart.");
	if (!waitUntilDisplayed(cartPage.SlidingCartLogo(), 4)) {
	bringElementIntoView(HeaderBar.CartLocator());
	click(HeaderBar.CartLocator());
	if (isInStockMessageInCart()) {

	}
	if (isAlertPresent(driver)) {
	switchToDefaultContent();
	isProductAdded = false;
	}

	}
	}
	}

	if (isProductAdded) {

	productDetailData.setProductName(ProductName);
	productDetailData.setProductPrize(productPrice);
	break;
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

	public boolean isCustomerAlsoViewedPresent(Locator locator) throws Throwable {
	String ProductName = null;
	//int count=0;
	List<WebElement> productList = getWebElements(locator);
	boolean isProductAdded = false;
	for (WebElement product : productList) {
	Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
	Locator extendedLocator = ProductLocator.concatLocator(ExtendedLocatorProductName());
	String ProductId = getAttribute(ProductLocator, "id").replaceAll("[^0-9]", "");
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
	addToBagForSize(productLocator, ProductId,isWishlist);
	isProductAdded = waitUntilDisplayed(peopleWhoboughtThisAlsoBought(), 10);
	break;

	}

	}
	return isProductAdded;
	}

	public boolean addToWishList(Locator locator) throws IllegalArgumentException, IllegalAccessException, InstantiationException, Throwable {

	List<WebElement> productList = getWebElements(locator);
	if(productList.size()==0)
	{
	return false;
	}
	String productId = null;
	Locator selectedproduct=null;
	for (WebElement products : productList) {
	Locator product = new Locator(By.xpath(getAbsoluteXPath(products)), "pdoduct");
	Locator productNameLocator = product.concatLocator(ExtendedLocatorProductName());
	// String productName = getAttribute(productNameLocator, "title");
	String productName = getText(productNameLocator);
	product = new Locator(By.xpath(getAbsoluteXPath(products)), productName);
	productId = getAttribute(product, "id").replaceAll("[^0-9]", "");
	Locator wishlistPane = product.concatLocator(wishlistPane());
	bringElementIntoView(product);
	//ScrollDown("60");
	// bringElementIntoView(wishlistIcon(productId));

	mouseOver(product);
	mouseOver(product);
	// bringElementIntoView(wishlistIcon(productId));
	// mouseOver(product);
	if (waitUntilDisplayed(wishlistIcon(productId), 3)) {
	//ScrollDown("-50", 1);
	mouseOver(wishlistIcon(productId));
	mouseOver(wishlistIcon(productId));
	Thread.sleep(1000);
	click(wishlistIcon(productId));
	selectedproduct=product;
	break;
	}
	}
	Thread.sleep(1000);
	mouseOver(selectedproduct);
	return (getAttribute(wishlistIconStatus(productId), "class").equals("added"));
	//return waitUntilDisplayed(WishlistMessage(productId, "wishadded"), 6);

	}

	private ProductDetailData addSizeProductFromList(Locator locator) throws Throwable {
	String productName = null;
	String productPrice=null;
	ProductDetailData productDetailData = new ProductDetailData();
	List<WebElement> productList = getWebElements(locator);

	if(productList.size()==0)
	{
	productDetailData.setProductName("Widget not found");
	TestListner.testing.get().log(LogStatus.FAIL, "Widget not displayed on Page" );
	return productDetailData;
	}

	boolean isProductAdded = false;
	for (WebElement product : productList) {
	Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "");
	String ProductId = getAttribute(productLocator, "id").replaceAll("[^0-9]", "");
	String ProductType;
	boolean isWishlist = false;
	if(locator.getName().contains("wishlist"))
	{
	ProductType= "Size";
	isWishlist = true;
	}
	else{
	Locator productTypeLocator = productLocator.concatLocator(ExtendedProductType());
	ProductType = getText(productTypeLocator);
	ProductType = getAttribute(productLocator.concatLocator(ExtendedProductType_option()),"title");
	productPrice=getText(productLocator.concatLocator(ExtendedProductType_price())).replace(",", "").replaceAll("Rs.", "").trim();
	}
	Locator productNameLocator = productLocator.concatLocator(ExtendedLocatorProductName());
	// String productName = getAttribute(productNameLocator, "title");
	productName = getText(productNameLocator);
	System.out.println(productName);
	if (ProductType.contains("Size")||ProductType.contains("SIZES")||ProductType.contains("sizes")) {
	Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), productName);
	TestListner.testing.get().log(LogStatus.INFO, "Trying to Add " + productName+" to cart");
	isProductAdded = addToBagForSize(ProductLocator, ProductId,isWishlist);
	CartPage cartPage = new CartPage(driver);
	if (waitUntilDisplayed(cartPage.OutOfStockInbagMessage(), 3)) {
	click(closeButton());
	break;
	}
	if (isProductAdded == true) {
	TestListner.testing.get().log(LogStatus.INFO, productName+" added to cart. Checking if product is present in Cart.");
	if (!waitUntilDisplayed(cartPage.SlidingCartLogo(), 4)) {
	bringElementIntoView(HeaderBar.CartLocator());
	click(HeaderBar.CartLocator());
	if (isAlertPresent(driver)) {
	switchToDefaultContent();
	break;
	}
	}
	}
	}
	if (isProductAdded) {
	productDetailData.setProductName(productName);
	productDetailData.setProductPrize(productPrice);
	break;
	}
	}

	return productDetailData;
	}

	private boolean addToBagForShades(Locator productLocator, boolean iswishlist) throws Throwable {
	boolean isShadeAvailable = false;

	// Locator ProductILocator =
	// productLocator.concatLocator(extendedLocatorProductID());
	String productid = getAttribute(productLocator, "id").replaceAll("[^0-9]", "");
	System.out.println(productid);
	if (iswishlist == false) {
	AddToBagShadeProduct(productLocator, productid);
	} else {
	addToBagWishlistShade(productLocator, productid);
	}
	//	waitUntilOverlayDisappear(driver);
	Thread.sleep(5000);
	List<WebElement> shades = getWebElements(shades(productid));
	if (!shades.isEmpty()) {
	for (WebElement shade : shades) {
	Locator shadeLocator = new Locator(By.xpath(getAbsoluteXPath(shade)), "Shade");
	String ShadeName = getAttribute(shadeLocator, "title");
	//System.out.println(getAttribute(shadeLocator, "title"));
	bringElementIntoView(shadeLocator);
	//ScrollDown("200");
	click(shade, ShadeName);
	break;
	}
	//JavascriptExecutor js = (JavascriptExecutor) driver;
	// bringElementIntoView(AddToBagButton(productid));
	//ScrollDown("200");
	if(waitUntilDisplayed(AddToBagButtonFromView(), 3)){
	click(AddToBagButtonFromView());
	}
	else{
	if(iswishlist==true)
	{
	click( addToBagButtonOfWishlistProduct());
	}
	else{
	click(AddToBagButton(productid));
	}
	}
	if(isProductquantityExceeded())
	{
	isShadeAvailable = false;
	Actions action= new Actions(driver);
	action.sendKeys(Keys.ESCAPE);
	}
	else
	{
	isShadeAvailable = true;
	Thread.sleep(4000);
	if (isPresent(outOfStockMessage())) {
	click(closeButton());
	Thread.sleep(2000);
	if (isAlertAvailable()) {
	click(backButton());
	}
	waitUntilDisappear(backButton());
	click(closeLink(productid));
	isShadeAvailable = false;
	}
	}

	}

	else
	{
	click(closeShadeOverlay());
	}

	return isShadeAvailable;

	}

	/**
	 * @param productLocator
	 * @throws Throwable
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private void addToBagWishlistShade(Locator productLocator, String productId) throws IllegalArgumentException, IllegalAccessException, Throwable {
	// TODO Auto-generated method stub
	bringElementIntoView(seeShadeButton(productId));
	/* mouseOver(ProducttypeLocator); */
	System.out.println(seeShadeButton(productId));
	click(seeShadeButton(productId));
	}

	public boolean addToBagSimpleProduct(ProductDetailData productDetailData) throws Throwable, Throwable {
	String ProductName = productDetailData.getProductName().replace("...", "");
	ScrollDown("500");
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
	ScrollDown("50");
	mouseOver(ProductName(ProductName));
	click(AddToBagButton(productid));
	waitUntilDisappear(fullPageLoadingIcon());
	isSimpleProductAdded = true;

	Thread.sleep(4000);
	CartPage cartPage = new CartPage(driver);
	if (isPresent(outOfStockMessage())) {
	click(closeButton());
	Thread.sleep(2000);
	if (isAlertAvailable()) {
	clickJScript(backButton());
	}
	/*waitUntilDisappear(backButton());
	click(closeLink(productid));*/
	isSimpleProductAdded = false;



	}
	else
	if(cartPage.isInstockMessageDisplay())
	{
	cartPage.removeFirstProduct();
	isSimpleProductAdded = false;
	}
	}
	}

	return isSimpleProductAdded;

	}

	private boolean addToBagSimple(Locator productLocator) throws Throwable {
	ProductDetailData productDetailData = new ProductDetailData();
	String ProductName = null;
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
	ScrollDown("50");
	mouseOver(ProductILocator);
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

	public boolean addToBagForSize(Locator ProductLocator, String ProductId, boolean isWishlist) throws Throwable, Throwable {
	boolean isSizeAvailable = false;
	if(isWishlist==false)
	{
	selectSize(ProductLocator);
	}else{
	addToBagWishlistSize(ProductLocator,ProductId);
	}
	if(waitUntilDisplayed(AddToBagButtonFromView(), 3))
	{
	selectDefaultValueFromDropDownBox(dropdown());
	}else{
	Locator dropdownLocator = ProductLocator.concatLocator(dropdown());
	selectDefaultValueFromDropDownBox(dropdownLocator);
	}
	//mouseOver(ProductLocator);
	if(waitUntilDisplayed(AddToBagButtonFromView(ProductId), 3))
	{
	click(AddToBagButtonFromView(ProductId));
	}else{
	click(addToBagButton(ProductId));
	}

	isSizeAvailable = true;
	if (waitUntilDisplayed(outOfStockMessage(), 4)) {


	//click(closeButton());
	Thread.sleep(2000);
	if (waitUntilPresent(backButton(), 4)) {
	KeyBoard(Keys.ESCAPE);
	clickJScript(backButton());


	}
	/*waitUntilDisplayed(closeLink(ProductId), 4);
	click(closeLink(ProductId));*/  //commented for new code change on web
	isSizeAvailable = false;
	}
	/**************************above code temporarily commented as web application doesnt work and blocking cases**********************************/
	/*if (waitUntilDisplayed(outOfStockMessage(), 4)) {
	driver.navigate().refresh();

	isSizeAvailable = false;
	}*/
	/**********************************************************************************************************************************************/	


	return isSizeAvailable;

	}

	/**
	 * @param enter
	 */


	/**
	 * @param productLocator
	 * @param productId 
	 * @throws Throwable 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private void addToBagWishlistSize(Locator productLocator, String productId) throws IllegalArgumentException, IllegalAccessException, Throwable {
	// TODO Auto-generated method stub
	bringElementIntoView(productLocator);
	mouseOver(productLocator);
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
	bringElementIntoView(locator);
	ScrollDown("100");
	mouseOver(locator);
	Locator selectSizebutton = locator.concatLocator(extendedSelectSizeButton());
	// mouseOver(selectSizeButton());
	waitUntilDisplayed(selectSizebutton, 5);
	click(selectSizebutton);
	}

	private void AddToBagShadeProduct(Locator locator, String productid) throws Throwable {
	// Locator ratingbox = locator.concatLocator(productName());
	Locator ProducttypeLocator = locator.concatLocator(ExtendedProductType());
	Thread.sleep(4000);
	bringElementIntoView(ProducttypeLocator);
	ScrollDown("100");
	mouseOver(ProducttypeLocator);
	System.out.println(seeShadeButton(productid));
	click(seeShadeButton(productid));

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
	bringElementIntoView(Filter());
	selectFromDropDownBox(Filter(), sortName);
	waitUntilDisappear(fullPageLoadingIcon());
	List<ProductDetailData> ProductDataList = getListOfProduct(sortName);
	return ProductDataList;
	}

	/**
	 * @param sortName
	 * @return
	 * @throws IllegalAccessException
	 */
	private List<ProductDetailData> getListOfProduct(String sortName) throws IllegalAccessException {
	List<WebElement> productList = getWebElements(productList());
	List<ProductDetailData> ProductDataList = new ArrayList<ProductDetailData>();
	for (WebElement product : productList) {
	String Discount = null;
	String ReviewCount = null;
	Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product Parent Loactor");
	String prizeOfProduct = (getText(productLocator.concatLocator(PriceLocator())).replaceAll("\\D+", ""));
	String ProductName = getAttribute(productLocator.concatLocator(productName()), "title");
	if (sortName.contains("discount")) {
	Discount = getText(productLocator.concatLocator(DiscountTag())).replaceAll("\\D+", "");
	}
	if (sortName.contains("rated")) {
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
	bringElementIntoView(filter(FilterName));
	mouseOver(filter(FilterName));
	click(FilterCheckbox(Criteria));
	waitUntilDisappear(fullPageLoadingIcon());
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

	public boolean checkNotifyfromListing(ProductDetailData productDetailData) throws Throwable, Throwable {
	String ProductName = productDetailData.getProductName().replace("...", "");
	bringElementIntoView(ProductName(ProductName));
	boolean isNotifybuttonDisplayed = false;
	Locator ProductTypeLocator = ProductName(ProductName).concatLocator(NotifyMe());
	System.out.println(ProductTypeLocator.getBy());
	mouseOver(ProductName(ProductName));
	String productType = getText(ProductTypeLocator);
	if (productType.contains("NOTIFY")) {
	Locator productIDLocator = ProductName(ProductName).concatLocator(extendedLocatorProductID());
	String productid = getAttribute(productIDLocator, "id").replaceAll("[^0-9]", "");
	Locator NotifyMeLocator = ProductLayoutByProductName(ProductName).concatLocator(NotifyMe());
	System.out.println(NotifyMeLocator.getBy());
	mouseOver(ProductName(ProductName));
	if(waitUntilDisplayed(ProductTypeLocator, 2)){
	click(ProductTypeLocator);
	waitUntilDisplayed(NotifyEmail(), 5);
	if(isDisplayed(ShadeSizeSelection())){
	selectDefaultValueFromDropDownBox(ShadeSizeSelection());
	}
	}
	click(NotifyEmail());
	ClearValue(NotifyEmail());
	EnterValue(NotifyEmail(), "test@mail.com");
	click(NotifyMeButtonPopup());
	Thread.sleep(700);
	String OutofStockmsg = driver.findElement(outOfStockNotifyEmail().getBy()).getText();
	System.out.println(OutofStockmsg);
	isNotifybuttonDisplayed = OutofStockmsg.contains("We will notify");
	click(notifyMePopupclose());
	}

	return isNotifybuttonDisplayed;
	}

	public boolean checkNotifyfromPD(ProductDetailData productDetailData) throws Throwable, Throwable {
	String ProductName = productDetailData.getProductName().replace("...", "");
	bringElementIntoView(ProductName(ProductName));
	boolean isNotifybuttonDisplayed = false;
	Locator ProductTypeLocator = ProductName(ProductName).concatLocator(NotifyMe());
	mouseOver(ProductName(ProductName));
	String productType = getText(ProductTypeLocator);
	if (productType.contains("NOTIFY")) {
	click(ProductName(ProductName));
	waitUntilDisplayed(NotifyEmailPD(), 5);
	if(isDisplayed(ShadeSizeSelection())){
	selectDefaultValueFromDropDownBox(ShadeSizeSelection());
	}
	click(NotifyEmailPD());
	//ClearValue(NotifyEmailPD());
	EnterValue(NotifyEmailPD(), "test@mail.com");
	click(NotifyMeButtonPopup());
	Thread.sleep(700);
	String OutofStockmsg = driver.findElement(outOfStockNotifyMessage().getBy()).getText();
	isNotifybuttonDisplayed = OutofStockmsg.contains("We will notify");
	click(notifyMePopupclose());
	}

	return isNotifybuttonDisplayed;
	}

}