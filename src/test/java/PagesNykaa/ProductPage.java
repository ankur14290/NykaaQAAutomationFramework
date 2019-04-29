package PagesNykaa;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import DataNykaa.AccountData;
import DataNykaa.AnalyticsData;
import DataNykaa.ProductDetailData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.CustomSoftAssert;
import FrameWorkNykaa.Framework;
import FrameWorkNykaa.Locator;
import FrameWorkNykaa.LoginSync;
import FrameWorkNykaa.PropertyConfiguration;
import FrameWorkNykaa.Retry;
import FrameWorkNykaa.TestListner;




public class ProductPage extends BrowserAction {
	public ProductPage(WebDriver driver) {
		this.driver = driver;
	}

	Locator ExtendedProductLink() {
		return new Locator(By.xpath("//preceding-sibling::h3[@class='product-name']/a"), "Product Link");
	}

	Locator TryItOnButton(){
		return new Locator(By.xpath("//div[@class='tryiton_thumb slick-slide slick-active']"),"Try IT On button");
	}

	Locator TryItOnShadeSelector()
	{
		return new Locator(By.id("canvas"),"Try it on Image");

	}

	Locator shades(){
		return new Locator(By.xpath(
				"//a[@class='prSizeSlct']/img[@id='ch_img_2'][not(contains(@onclick,'set_subscribtion_value'))]"),"Shades");
	}

	Locator addWistICon(){
		return new Locator(By.id("wishlist-add"),"WishlistIcon");
	}


	Locator wishlistMessageBlock(){
		return new Locator(By.xpath("//div[@id='ajax_msg_blck']//span"),"Message block");
	}
	Locator SelectShadeLabel(){
		return new Locator(By.id("select_shade_lbel"),"Maximise shade image");
	}

	Locator DescriptionLink(){

		return new Locator(By.id("description"),"Description link");

	}


	Locator CustomerAlsoViewed(){
		return new Locator(By.id("cbar_widget01"),"Customer also view Widget");
	}
	Locator ReadOnLinkExtended()
	{
		return new Locator(By.xpath("//a/span[text()='Read on']"),"read On Link");
	}

	Locator DescriptionContent(){
		return new Locator(By.id("Description-block"),"Description Content");
	}

	Locator ReviewLink(){
		return new Locator(By.id("rev_lnk"),"Review Link");
	}

	Locator AddToBag()
	{
		return new Locator(By.xpath("//a[@class='add_to_bag_button']| //a[@title='ADD TO BAG']"),"Add To Bag");
	}
	Locator likeIcon(){
		return new Locator(By.xpath("//li[contains(@id,'th-like')]"),"like Icon");
	}


	Locator SizedropDown()
	{
		return new Locator(By.xpath("//select[@class='shade']"),"DropDown Box");
	}

	Locator viewBag(){

		return new Locator(By.xpath("//div/span[text()='VIEW BAG']"),"View Bag");
	}

	Locator ratingReviewTab(){
		return new Locator(By.id("rating-review"),"Rating Review Tab");
	}

	Locator askNykaaTab(){
		return new Locator(By.id("product-qa"),"ask Nykaa tab");
	}

	Locator askAQuestionButton(){
		return new Locator(By.xpath("//a[@class='ask-question']"),"Ask A Question button");
	}

	Locator blogTab(){
		return new Locator(By.id("nykaa-decodes"),"Blog Tab");
	}

	Locator Postelements(){
		return new Locator(By.xpath("//div[@id='nykaa-decodes-block']/div[@class='nykaa-post']"),"read on");
	}

	Locator questionField(){
		return new Locator(By.id("question_content"),"question Field");
	}
	Locator QuestionerNickname(){
		return new Locator(By.id("question_author_name")," Questioner Nickname");
	}
	Locator submitQuestionButton(){
		return new Locator(By.id("submit_question"),"submit Question");
	}

	Locator writeReviewButton(){
		return new Locator(By.xpath("//a[@class='write-review']"),"write a Review button");
	}

	Locator rate(String Value)
	{
		return new Locator(By.xpath("//span[@id='give-rating']/div/img[@alt="+Value+"]"),"Rating Review Tab");	
	}


	Locator NotifyMe()
	{
		return new Locator(By.xpath("//input[@type='button'][@value='NOTIFY ME']"),"Notify Me Button");
	}
	
	Locator SubscriptionEmailTextBox()
	{
		return new Locator(By.xpath("//input[@id='subscription_email']"),"Subscription email text box");
	}
	
	Locator ProductName(){

		return new Locator(By.xpath("//h1[@class='product-name']"),"product name of product Page");
	}
	Locator reviewCount(){

		return new Locator(By.xpath("//span[@id='rev_lnk']/span[@itemprop='reviewCount']"),"review Count");
	}

	Locator QandACount(){
		return new Locator(By.id("desk-questnAns-total-count"),"AskNykaa Count");
	}

	Locator WishListButton(){
		return new Locator(By.id("wishlist-add"),"Wishlist icon");
	}

	Locator CustomerAlsoWidget(){
		return new Locator(By.id("cbar_widget01"),"Customer also viewed widget");
	}

	Locator AddToBagDynamicComboButton(){
		return new Locator(By.xpath("//button[@title='ADD ITEMS TO BAG']"),"Add combo Items to Bag");
	}
	Locator ComboProductNames(){

		return new Locator(By.xpath("//li[@class='simplebundle'][@id='combo1']/descendant::span[@class='recommend-deal-name']"),"Combo ProductName");
	}
	Locator ComboPopup(){
		return new Locator(By.xpath("//div[@class='sb-popup']"),"combo Popup");
	}

	Locator SubProductInComboPopup(){

		return new Locator(By.xpath("//div[@class='products-header']/following-sibling::ul/li"),"sub product");
	}

	Locator ExtendedShadeProductpresent(){

		return new Locator(By.xpath("//div[@class='shade-color']"),"shade product in combo popup");
	}

	Locator ExtendedShadeLocatorInPopup(){

		return new Locator(By.xpath("//descendant::img[@id='ch_img_2'][contains(@onclick,'showShadeOnSelection')]"),"shades");
	}
	Locator AddToBagButtonOfcombo(){

		return new Locator(By.id("sb-popup-addtocart"),"Add to Cart");
	}

	Locator ReviewTitle(){

		return new Locator(By.id("summary_field"),"Review Title");
	}

	Locator yourReview(){
		return new Locator(By.id("review_field"),"Review Field");

	}

	Locator ReviewNicknameField(){

		return new Locator(By.id("nickname_field"),"NickName");
	}

	Locator SubmitReviewButton(){
		return new Locator(By.xpath("//input[@value='SUBMIT']"),"submit review button");
	}

	Locator backToProduct(){
		return new Locator(By.xpath("//span[@class='tryitonbar2txt']"),"Back To Product");
	}




	public ProductDetailData addToBagShadeproductviaProductPagefromListing(Locator locator) throws Throwable {
		boolean isProductAdded = false;
		ProductDetailData productDetailData = new ProductDetailData();
		String ProductName = null;
		List<WebElement> productList = getWebElements(locator);
		String MainPage = driver.getWindowHandle();
		for (WebElement product : productList) {
			driver.switchTo().window(MainPage);
			Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
			ListingPage listingPage = new ListingPage(driver);
			Locator ProductNameLocator = ProductLocator.concatLocator(listingPage.ExtendedLocatorProductName());
			ProductName = getText(ProductNameLocator);
			isProductAdded = addToBagShadeProductPage(ProductLocator);
			String tagvalue3 = (String) ((JavascriptExecutor) driver).executeScript("return s.events;");
			System.out.println(tagvalue3);
			CartPage cartPage = new CartPage(driver);
			HeaderBar headerbar = new HeaderBar(driver);
			if (!waitUntilDisplayed(cartPage.SlidingCartLogo(), 4)) {
				bringElementIntoView(HeaderBar.CartLocator());
				click(HeaderBar.CartLocator());
				if (isAlertPresent(driver)) {
					switchToDefaultContent();
					break;
				}
				if (cartPage.IncreaseQuantityOfProduct(driver)) {
					break;

				}
			}

		}
		if (isProductAdded) {

			productDetailData.setProductName(ProductName);
		}

		return productDetailData;
	}


	public List<ProductDetailData> addDynamicCombo() throws Throwable, Throwable{
		bringElementIntoView(AddToBagDynamicComboButton());
		TestListner.testing.get().log(LogStatus.INFO, "Product Page URL: " +driver.getCurrentUrl() );
		List<ProductDetailData> combodetail = getComboProductDetail();
		click(AddToBagDynamicComboButton());
		addConfigProductFromComboPopup();
		return  combodetail;	
	}


	Locator PeopleWhoboughtwidget(){

		return new Locator(By.xpath("//div[@class='content_inner product-onclick-widget product-combo-deal peoplewhobought']"),
				"people Also bought widget");
	}


	public boolean verifyTryitOn() throws Throwable{
		bringElementIntoView(TryItOnButton());
		click(TryItOnButton());
		boolean isTryItOnOpened = waitUntilDisplayed(TryItOnShadeSelector(),10);
		waitUntilDisplayed(backToProduct(), 3);
		bringElementIntoView(backToProduct());
		ScrollDown("100");
		click(backToProduct());
		waitUntilDisplayed(TryItOnButton(), 5);
		return	isTryItOnOpened;

	}
	private void addConfigProductFromComboPopup() throws Throwable, Throwable {
		// TODO Auto-generated method stub
		if(waitUntilDisplayed(ComboPopup(), 4))
		{
			Locator subproductLocator = ComboPopup().concatLocator(SubProductInComboPopup());
			List<WebElement> subproducts = getWebElements(subproductLocator);
			for(WebElement product:subproducts)
			{
				Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)),"product");
				Locator shadeProductpresent = productLocator.concatLocator(ExtendedShadeProductpresent());
				if(waitUntilDisplayed(shadeProductpresent,4))

				{
					Locator shadesLocator = productLocator.concatLocator(ExtendedShadeLocatorInPopup());
					List<WebElement> shades = getWebElements(shadesLocator);
					for(WebElement shade :shades){
						Locator shadelocator = new Locator(By.xpath(getAbsoluteXPath(shade)),"shade of the one of the combo");
						click(shadelocator);
						break;
					}

				}
				click(AddToBagButtonOfcombo());
			}

		}




	}

	private List<ProductDetailData> getComboProductDetail() throws  Throwable {

		List<ProductDetailData> listOfComboProduct = new ArrayList<ProductDetailData>();

		List<WebElement> ComboProduct = getWebElements(ComboProductNames());

		for(WebElement product:ComboProduct){
			Locator comboProduct = new Locator(By.xpath(getAbsoluteXPath(product)), " Combo Product");
			String productname = getText(comboProduct).trim();
			ProductDetailData productDetailData = new ProductDetailData();
			productDetailData.setProductName(productname);
			listOfComboProduct.add(productDetailData);
		}
		// TODO Auto-generated method stub
		return listOfComboProduct;

	}



	public ProductDetailData addAnyShadeProductFromProductPAge(Locator locator) throws Throwable {
		CartPage cartPage = new CartPage(driver);
		if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
			click(cartPage.BackMenuCart());
		}
		return addToBagShadeFromProductPageWithinList(locator);

	}

	public ProductDetailData addAnySizeProductFromProductPage(Locator locator) throws Throwable, Throwable
	{
		CartPage cartPage = new CartPage(driver);
		if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
			click(cartPage.BackMenuCart());
		}
		ListingPage listingPage = new ListingPage(driver);
		return addToBagSizeFromProductPageWithinList(locator);

	}


	public ProductDetailData addAnyStaticComboProductFromProductPage(Locator locator) throws Throwable, Throwable{

		CartPage cartPage = new CartPage(driver);
		if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
			click(cartPage.BackMenuCart());
		}
		ListingPage listingPage = new ListingPage(driver);
		return addToBagStaticComboFromProductPageWithinList(locator);

	}
	public ProductDetailData addAnySimpleProductFromProductPAge() throws Throwable {
		CartPage cartPage = new CartPage(driver);
		if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
			click(cartPage.BackMenuCart());
		}
		ListingPage listingPage = new ListingPage(driver);
		return addToBagSimpleFromProductPageWithinList(listingPage.allProducts());

	}

	public boolean addToWishlist() throws IllegalArgumentException, Throwable{
		bringElementIntoView(WishListButton());
		click(WishListButton());
		waitUntilDisplayed(wishlistMessageBlock(), 4);
		Wait(2);
		String wishlistSuccessmessage = getText(wishlistMessageBlock());
		return wishlistSuccessmessage.contains("added to your wishlist");

	}

	/**
	 * @return
	 * @throws Throwable 
	 * @throws IllegalArgumentException 
	 */
	private ProductDetailData addToBagSimpleFromProductPageWithinList(Locator locator) throws IllegalArgumentException, Throwable {
		ProductDetailData productDetailData = new ProductDetailData();
		String ProductName = null;
		ListingPage listingPage = new ListingPage(driver);
		List<WebElement> productList = getWebElements(locator);
		boolean isProductAdded = false;
		for (WebElement product : productList) {
			Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
			Locator extendedLocator = ProductLocator.concatLocator(listingPage.ExtendedLocatorProductName());

			System.out.println(extendedLocator);
			ProductName = getText(extendedLocator);
			String ProductType = getText(ProductLocator.concatLocator(listingPage.ExtendedProductType()));
			if (!ProductType.contains("Shade")&&!ProductType.contains("Size")) {
				Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), ProductName);
				isProductAdded = addToBagSimpleProductPage(productLocator);
				CartPage cartPage = new CartPage(driver);
				HeaderBar headerbar = new HeaderBar(driver);
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

						else if (cartPage.IncreaseQuantityOfProduct(driver)) {
							isProductAdded = true;

						}
					}
				}
			}

			if (isProductAdded) {

				productDetailData.setProductName(ProductName);

				break;
			}

		}
		return productDetailData;
	}


	public boolean isPeopleWhoboughtDisplayed(){
		try
		{
			bringElementIntoView(AddToBag());
			click(AddToBag());
		}
		catch(Throwable e)
		{

		}
		return waitUntilDisplayed(PeopleWhoboughtwidget(), 6);
	}

	public boolean isCustomerAlsoviewedDisplayed() throws InstantiationException, IllegalAccessException, Throwable{
		CartPage cartPage = new CartPage(driver);
		if(waitUntilDisplayed(cartPage.BackMenuCart(), 3)){
			click(cartPage.BackMenuCart());
			waitUntilDisappear(cartPage.BackMenuCart());
		}
		return waitUntilDisplayed(CustomerAlsoViewed(),6);
	}

	private ProductDetailData addToBagStaticComboFromProductPageWithinList(Locator locator) throws IllegalArgumentException, Throwable {
		ProductDetailData productDetailData = new ProductDetailData();
		String ProductName = null;
		ListingPage listingPage = new ListingPage(driver);
		listingPage.ScrollDown("2000", 5);
		List<WebElement> productList = getWebElements(locator);
		boolean isProductAdded = false;
		for (WebElement product : productList) {
			Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
			Locator extendedLocator = ProductLocator.concatLocator(listingPage.ExtendedLocatorProductName());

			System.out.println(extendedLocator);
			ProductName = getText(extendedLocator);
			String ProductType = getAttribute(ProductLocator.concatLocator(listingPage.DataType()),"data");
			if (ProductType.contains("bundle") ){
				Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), ProductName);
				isProductAdded = addToBagSimpleProductPage(productLocator);
				CartPage cartPage = new CartPage(driver);
				HeaderBar headerbar = new HeaderBar(driver);
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

						/*	else if (cartPage.IncreaseQuantityOfProduct(driver)) {
							isProductAdded = true;

						}*/
					}
				}
			}

			if (isProductAdded) {

				productDetailData.setProductName(ProductName);

				break;
			}

		}
		return productDetailData;
	}


	private ProductDetailData addToBagShadeFromProductPageWithinList(Locator locator) throws IllegalArgumentException, Throwable {
		ProductDetailData productDetailData = new ProductDetailData();
		String ProductName = null;
		ListingPage listingPage = new ListingPage(driver);
		List<WebElement> productList = getWebElements(locator);
		boolean isProductAdded = false;
		for (WebElement product : productList) {
			Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
			Locator extendedLocator = ProductLocator.concatLocator(listingPage.ExtendedLocatorProductName());

			System.out.println(extendedLocator);
			ProductName = getText(extendedLocator);
			String ProductType = getText(ProductLocator.concatLocator(listingPage.ExtendedProductType()));
			if (ProductType.contains("Shade")) {
				Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), ProductName);
				isProductAdded = addToBagShadeProductPage(productLocator);
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

						else if (cartPage.IncreaseQuantityOfProduct(driver)) {
							isProductAdded = true;

						}
					}
				}
			}

			if (isProductAdded) {

				productDetailData.setProductName(ProductName);

				break;
			}

		}
		return productDetailData;
	}

	private ProductDetailData addToBagSizeFromProductPageWithinList(Locator locator) throws IllegalArgumentException, Throwable {
		ProductDetailData productDetailData = new ProductDetailData();
		String ProductName = null;
		ListingPage listingPage = new ListingPage(driver);
		List<WebElement> productList = getWebElements(locator);
		boolean isProductAdded = false;
		for (WebElement product : productList) {
			Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
			Locator extendedLocator = ProductLocator.concatLocator(listingPage.ExtendedLocatorProductName());

			System.out.println(extendedLocator);
			ProductName = getText(extendedLocator);
			String ProductType = getAttribute(ProductLocator.concatLocator(listingPage.ExtendedProductType_option()),"title");
			if (ProductType.contains("Size")) {
				Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), ProductName);
				isProductAdded = addToBagSizeProductPage(productLocator);
				CartPage cartPage = new CartPage(driver);
				HeaderBar headerbar = new HeaderBar(driver);
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

						/*else if (cartPage.IncreaseQuantityOfProduct(driver)) {
							isProductAdded = true;

						}*/
					}
				}
			}

			if (isProductAdded) {

				productDetailData.setProductName(ProductName);

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


	private boolean addToBagSizeProductPage(Locator locator) throws Throwable, Throwable {

		boolean isSizeAvailable = false;
		String MainPage = driver.getWindowHandle();
		Locator ProductLink = locator.concatLocator(ExtendedProductLink());
		String ProductName = getText(ProductLink);
		bringElementIntoView(ProductLink);
		click(ProductLink);
		Set<String> MultipleTabs = driver.getWindowHandles();
		System.out.println(MultipleTabs.toString());
		for (String producttab : MultipleTabs) {
			if (!MainPage.equalsIgnoreCase(producttab)) {
				if (driver.switchTo().window(producttab).getTitle().contains(ProductName)) 
				{
					break;
				}
			}
		}

		selectDefaultValueFromDropDownBox(SizedropDown());
		for(int i=0;i<(new Select(driver.findElement(SizedropDown().getBy())).getOptions()).size();i++)
		{
			if(! isDisplayed(SubscriptionEmailTextBox()))
			{
				break;
			}
			selectFromDropDownBoxByIndex(SizedropDown(),i+2);
		}

		bringElementIntoView(AddToBag());
		click(AddToBag());


		Framework framework = new Framework();



		isSizeAvailable = true;



		return isSizeAvailable;
	}		



	private boolean addToBagShadeProductPage(Locator locator) throws Throwable, Throwable {

		boolean isShadeAvailable = false;
		String MainPage = driver.getWindowHandle();
		Locator ProductLink = locator.concatLocator(ExtendedProductLink());
		String ProductName = getText(ProductLink);
		bringElementIntoView(ProductLink);
		click(ProductLink);
		Set<String> MultipleTabs = driver.getWindowHandles();
		System.out.println(MultipleTabs.toString());
		for (String producttab : MultipleTabs) {
			if (!MainPage.equalsIgnoreCase(producttab)) {
				if (driver.switchTo().window(producttab).getTitle().contains(ProductName)) {
					break;
				}
			}
		}
		waitUntilDisplayed(SelectShadeLabel(), 30);
		waitForPagetoLoad()  ;  
		List<WebElement> shades = getWebElements(shades());
		if (!shades.isEmpty()) {
			for (WebElement shade : shades) {
				Locator shadeLocator = new Locator(By.xpath(getAbsoluteXPath(shade)), "Shade");
				click(shadeLocator);
				// shade.findElement(By.xpath("ancestor::li/following-sibling::li/a/img[@class='tickmark']")).click();
				break;
			}

			if (waitUntilDisplayed(NotifyMe(), 3)) {
				driver.close();
				isShadeAvailable = false;
				driver.switchTo().window(MainPage);
			} else {
				bringElementIntoView(AddToBag());
				click(AddToBag());


				Framework framework = new Framework();

				//AnalyticsData Analyticsaddtocart = framework.getData(AnalyticsData.class, "addtocart");
				//AnalyticsInfoPage analyticsInfoPage = new AnalyticsInfoPage(driver);
				//analyticsInfoPage.getEvarJSLoad(Analyticsaddtocart);

				isShadeAvailable = true;
			}
		}

		return isShadeAvailable;
	}		


	private boolean addToBagSimpleProductPage(Locator locator) throws Throwable, Throwable{

		boolean isproductadded = false;
		String MainPage = driver.getWindowHandle();
		Locator ProductLink = locator.concatLocator(ExtendedProductLink());
		String ProductName = getText(ProductLink);
		bringElementIntoView(ProductLink);
		click(ProductLink);
		Set<String> MultipleTabs = driver.getWindowHandles();
		System.out.println(MultipleTabs.toString());
		for (String producttab : MultipleTabs) {
			if (!MainPage.equalsIgnoreCase(producttab)) {
				if (driver.switchTo().window(producttab).getTitle().contains(ProductName)) {
					break;
				}
			}
		}


		{


			if (waitUntilDisplayed(NotifyMe(), 3)) {
				driver.close();
				isproductadded = false;
				driver.switchTo().window(MainPage);
			} else {
				bringElementIntoView(AddToBag());
				click(AddToBag());


				Framework framework = new Framework();

				/*AnalyticsData Analyticsaddtocart = framework.getData(AnalyticsData.class, "addtocart");
					AnalyticsInfoPage analyticsInfoPage = new AnalyticsInfoPage(driver);
					analyticsInfoPage.getEvarJSLoad(Analyticsaddtocart);*/

				isproductadded = true;
			}
		}

		return isproductadded;

	}

	public boolean addReview(ProductDetailData productDetail,AccountData accountData) throws InstantiationException, IllegalAccessException, Throwable
	{  // bringElementIntoView(ratingReviewTab());
		click(ratingReviewTab());
		click(writeReviewButton());
		click(rate(productDetail.getRating()));
		EnterValue(ReviewTitle(),productDetail.getReviewtitle());
		EnterValue(yourReview(),productDetail.getReviews());
		EnterValue(ReviewNicknameField(),accountData.getNickName());
		click(SubmitReviewButton());
		return isDisappear(ReviewTitle(),10);

	}


	public boolean likeReview() throws Throwable, Throwable{
		bringElementIntoView(ratingReviewTab());
		click(ratingReviewTab());
		List<WebElement> likeIcons = getWebElements(likeIcon());
		WebElement likeFirstReview = likeIcons.get(0);
		Locator likeLocator = new Locator(By.xpath(getAbsoluteXPath(likeFirstReview)),"First like Button");
		bringElementIntoView(likeLocator);
		String id = getAttribute(likeLocator, "id");
		click(likeLocator);
		Locator unlikeLocator = new Locator(By.xpath("//li[@id='"+id+"']"),"unlikeLocator");

		return waitUntilDisplayed(unlikeLocator, 10);


	}

	public boolean askNykaa(ProductDetailData productDetail,AccountData accountData) throws Throwable, Throwable
	{
		bringElementIntoView(askNykaaTab());
		click(askNykaaTab());
		click(askAQuestionButton());
		EnterValue(QuestionerNickname(),accountData.getNickName());
		EnterValue(questionField(), productDetail.getQuestion());
		click(submitQuestionButton());
		return isDisappear(questionField(), 10);

	}

	public boolean readPost() throws Throwable, Throwable, Throwable
	{boolean isbeutybookpage = false;
	bringElementIntoView(blogTab());
	click(blogTab());
	CustomSoftAssert CS = new CustomSoftAssert("automation Regrestion Coverage", "DeskTop");
	List<WebElement> postWebElemnt = getWebElements(Postelements());
	CS.assertTrue(postWebElemnt.size()=='3',"107");
	Locator PostLocator  = new Locator(By.xpath(getAbsoluteXPath(postWebElemnt.get(0))),"PostLocator");
	Locator	readon = PostLocator.concatLocator(ReadOnLinkExtended());
	click(readon);
	String mainPage = driver.getWindowHandle();
	Set<String> pages = driver.getWindowHandles();
	for(String page:pages)
	{
		if(!page.equalsIgnoreCase(mainPage))
		{
			driver.switchTo().window(page);
			isbeutybookpage = driver.getCurrentUrl().contains("beautybook");
		}
	}
	driver.switchTo().defaultContent();
	return isbeutybookpage;


	}


	public void checkListOfProductPage() throws Throwable, Throwable, Throwable 
	{
		CustomSoftAssert CS = new CustomSoftAssert("automation Regrestion Coverage", "DeskTop");
		CS.assertTrue(waitUntilDisplayed(DescriptionContent(), 3),"99");
		CS.assertTrue(waitUntilDisplayed(reviewCount(), 3),"85");
		CS.assertTrue(waitUntilDisplayed(QandACount(), 3),"87");
		CS.assertTrue(waitUntilDisplayed(WishListButton(),3),"88");
		CS.assertFalse(getText(ProductName()).isEmpty(),"83");
		CS.assertAll();
	}

	/**
	 * @param allProducts
	 * @return
	 */



}

