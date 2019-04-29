package PagesNykaa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.relevantcodes.extentreports.LogStatus;

import DataNykaa.AccountData;
import DataNykaa.CheckoutData;
import DataNykaa.ProductDetailData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.CustomSoftAssert;
import FrameWorkNykaa.Framework;
import FrameWorkNykaa.Locator;
import FrameWorkNykaa.TestListner;




public class ProductPage_React extends BrowserAction {
	public ProductPage_React(WebDriver driver) {
		this.driver = driver;
	}

	Locator ExtendedProductLink() {
		return new Locator(By.xpath("//preceding-sibling::h3[@class='product-name']/a"), "Product Link");
	}
	
	public Locator SeeMoreReviewLink() {
		return new Locator(By.xpath("//a[contains(text(),'SEE MORE REVIEWS')]"), "See More Review Link");
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
				"//span[@class='color-pallets  ']"),"Shades");
	}

	Locator sizes(){
		return new Locator(By.xpath(
				"//span[@class='size-pallets']"),"Sizes");
	}

	Locator addWistICon(){
		return new Locator(By.id("wishlist-add"),"WishlistIcon");
	}


	Locator wishlistMessageBlock(){
		return new Locator(By.xpath("//div[@id='ajax_msg_blck']//span"),"Message block");
	}
	Locator SelectShadeLabel(){
		return new Locator(By.xpath("//div[contains(@class,'options-colors-lists-wrap horizontalScoll')]"),"shade options");
	}

	Locator SelectSizeLabel(){
		return new Locator(By.xpath("//div[contains(@class,'options-sizes-lists-wrap horizontalScoll')]"),"shade options");
	}

	Locator DescriptionLink(){

		return new Locator(By.id("description"),"Description link");

	}


	Locator CustomerAlsoViewed(){
		return new Locator(By.xpath("//div[@class='slick-track']"),"Customer also view Widget");
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
		return new Locator(By.xpath("//button[contains(@class,'add-to-btn')]"),"Add To Bag");
	}

	Locator AddToBagMessage()
	{
		return new Locator(By.xpath("div[@class='mm-message-open-desktop mm-message ']//div[@class='mm-text']/span"),"Product Added to Bag Message");
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
		return new Locator(By.xpath("//span[@class='wishListIconWrapper']/i"),"Wishlist icon");
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

	Locator IsWhishlisted(){
		return new Locator(By.xpath("//div[@class='col-md-5 col-sm-5 product-img-wrap']//i[@class='fa fa-heart']"),"Is Product Already in Whishlist");
	}

	Locator PriceLocator()
	{
		return new Locator(By.xpath("//span[@class='post-card__content-price-offer']"),"Product Price");
	}

	Locator ExtendedLocatorProductName() {
		return new Locator(By.xpath("//div[@class='m-content__product-list__title']/h2/span"), " product Names");
	}
	Locator SubscribeButton(){
		return new Locator(By.xpath("//span[@class='subscription-txt']"),"Subscribe Button");
	}
	Locator SubscribeConfirmButton(){
		return new Locator(By.xpath("//button[text()='CONFIRM']"),"Subscribe Confirm Button");
	}

	Locator SubscriptionFinalConfirm()
	{
		return new Locator(By.xpath("//input[@name='subscription_confirm_submit']"),"Subscription email text box");
	}
	Locator SubscribeMsg(){
		return new Locator(By.xpath("//div[@class='thanks-div']//h2"),"Thanks You");
	}

	Locator NotifyEmailPD()
	{
		return new Locator(By.xpath("//input[@placeholder='Enter the Email Address']"),"NotifyEmailPD");
	}

	Locator NotifyMeButtonPopup(){
		return new Locator(By.xpath("//i[@class='notify-icon']"),"Notify Me button in PD");
	}

	Locator outOfStockNotifyEmail(){
		return new Locator(By.xpath("//div[@class='mm-text']//span"),"Notify Message");
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
			isProductAdded = addToBagShadeProductPage(ProductLocator,ProductName);
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

		return new Locator(By.xpath("//div[@id='customerAlsoBought']/div//div[@class='slick-initialized slick-slider']"),
				"Customer Also bought widget");
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



	public ProductDetailData addAnyShadeProductFromProductPAge() throws Throwable {
		closeCart();
		return addToBagShadeFromProductPageWithinList(ListingPage_React.allProducts());

	}

	public ProductDetailData addAnySizeProductFromProductPage() throws Throwable, Throwable
	{
		closeCart();
		return addToBagSizeFromProductPageWithinList(ListingPage_React.allProducts());

	}


	public ProductDetailData addAnyStaticComboProductFromProductPage(Locator locator) throws Throwable, Throwable{

		closeCart();
		return addToBagStaticComboFromProductPageWithinList(locator);

	}
	public ProductDetailData addAnySimpleProductFromProductPAge() throws Throwable {
		closeCart();
		return addToBagSimpleFromProductPageWithinList(ListingPage_React.allProducts());

	}

	public boolean addToWishlist() throws IllegalArgumentException, Throwable{
		bringElementIntoView(WishListButton());
		if(!isDisplayed(IsWhishlisted())){


			click(WishListButton());
		}
		//waitUntilDisplayed(wishlistMessageBlock(), 4);
		Wait(2);
		//String wishlistSuccessmessage = getText(wishlistMessageBlock());
		return getAttribute(WishListButton(), "class").equals("fa fa-heart");

	}

	/**
	 * @return
	 * @throws Throwable 
	 * @throws IllegalArgumentException 
	 */
	private ProductDetailData addToBagSimpleFromProductPageWithinList(Locator locator) throws IllegalArgumentException, Throwable {
		ListingPage_React listingPage=new ListingPage_React(driver);
		ProductDetailData productDetailData = new ProductDetailData();
		String ProductName = null;
		boolean isProductAdded = false;
		int count=0;
		while(! isProductAdded && count<3)
		{
			count+=1;
			List<WebElement> productList_all = new ArrayList<WebElement>();
			List<WebElement> productList = new ArrayList<WebElement>();
			System.out.println(locator.getBy());
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
			Locator ProductLocator=null;
			for (WebElement product : productList) {
				ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
				Locator extendedLocator = ProductLocator.concatLocator(listingPage.ExtendedLocatorProductName());
				System.out.println(ProductLocator.getBy());

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
					mouseOver(ProductLocator);
					ProductType = getText(ProductLocator.concatLocator(listingPage.ExtendedProductType()));
					//ProductType=getAttribute(ProductLocator.concatLocator(ExtendedProductType_option()),"title");
				}
				if (ProductType.contains("Bag")||ProductType.contains("BAG")) {
					TestListner.testing.get().log(LogStatus.INFO, "Trying to Add " + ProductName+" to cart");
					Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), ProductName);
					isProductAdded = addToBagSimpleProductPage(productLocator,ProductName);
					CartPage_React CartPage_React = new CartPage_React(driver);
					HeaderBar headerbar = new HeaderBar(driver);
					if (isProductAdded == true) {
						TestListner.testing.get().log(LogStatus.INFO, ProductName+" added to cart successfully! Checking if the product is present in Cart.");
						if (!waitUntilDisplayed(CartPage_React.SlidingCartLogo(), 4)) {
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
					closeCart();
					productDetailData.setProductName(ProductName);
					productDetailData.setProductPrize(replaceRuppeeSymbol(getText(PriceLocator()).replace("Rs.", "").trim()));
					break;
				}

			}

			if(isProductAdded)
			{
				break;
			}
			else
			{
				if(isDisplayed(listingPage.sliderNextButton()))
				{
					for(WebElement slidernext:getAllElements(listingPage.sliderNextButton().getBy()))
					{
						bringElementIntoView(listingPage.sliderNextButton());
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
	
	public boolean isSeeAllReviewLinkDisplay(){
		try
		{
			bringElementIntoView(SeeMoreReviewLink());
			
		}
		catch(Throwable e)
		{

		}
		return waitUntilDisplayed(PeopleWhoboughtwidget(), 6);
	}
	
	public void clickAllReview() throws InstantiationException, IllegalAccessException
	{
		click(SeeMoreReviewLink());
	}

	public boolean isCustomerAlsoviewedDisplayed() throws InstantiationException, IllegalAccessException, Throwable{
		closeCart();
		return waitUntilDisplayed(CustomerAlsoViewed(),6);
	}

	private ProductDetailData addToBagStaticComboFromProductPageWithinList(Locator locator) throws IllegalArgumentException, Throwable {
		ProductDetailData productDetailData = new ProductDetailData();
		String ProductName = null;
		ListingPage_React listingPage = new ListingPage_React(driver);
		listingPage.ScrollDown("100", 5);
		List<WebElement> productList = getWebElements(locator);
		boolean isProductAdded = false;
		for (WebElement product : productList) {
			Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
			Locator extendedLocator = ProductLocator.concatLocator(listingPage.ExtendedLocatorProductName());

			System.out.println(extendedLocator);
			ProductName = getText(extendedLocator);
			//String ProductType = getAttribute(ProductLocator.concatLocator(listingPage.DataType()),"data");
			if (ProductName.contains("Combo") ){
				Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), ProductName);
				isProductAdded = addToBagSimpleProductPage(productLocator,ProductName);
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
		ListingPage_React listingPage=new ListingPage_React(driver);
		ProductDetailData productDetailData = new ProductDetailData();
		String ProductName = null;
		boolean isProductAdded = false;
		int count=0;
		while(! isProductAdded && count<3)
		{
			count+=1;
			List<WebElement> productList_all = new ArrayList<WebElement>();
			List<WebElement> productList = new ArrayList<WebElement>();
			System.out.println(locator.getBy());
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
			Locator ProductLocator=null;
			for (WebElement product : productList) {
				ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
				Locator extendedLocator = ProductLocator.concatLocator(listingPage.ExtendedLocatorProductName());
				System.out.println(ProductLocator.getBy());

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
					mouseOver(ProductLocator);
					ProductType = getText(ProductLocator.concatLocator(listingPage.ExtendedProductType()));
					//ProductType=getAttribute(ProductLocator.concatLocator(ExtendedProductType_option()),"title");
				}
				if (ProductType.contains("shade")||ProductType.contains("SHADE")) {
					TestListner.testing.get().log(LogStatus.INFO, "Trying to Add " + ProductName+" to cart");
					Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), ProductName);
					isProductAdded = addToBagShadeProductPage(productLocator,ProductName);
					CartPage_React CartPage_React = new CartPage_React(driver);
					if (isProductAdded == true) {
						TestListner.testing.get().log(LogStatus.INFO, ProductName+" added to cart successfully! Checking if the product is present in Cart.");
						if (!waitUntilDisplayed(CartPage_React.SlidingCartLogo(), 4)) {
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
					closeCart();
					productDetailData.setProductName(ProductName);
					productDetailData.setProductPrize(replaceRuppeeSymbol(getText(PriceLocator()).replace("Rs.", "").trim()));
					break;
				}

			}

			if(isProductAdded)
			{
				break;
			}
			else
			{
				if(isDisplayed(listingPage.sliderNextButton()))
				{
					for(WebElement slidernext:getAllElements(listingPage.sliderNextButton().getBy()))
					{
						bringElementIntoView(listingPage.sliderNextButton());
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

	private ProductDetailData addToBagSizeFromProductPageWithinList(Locator locator) throws IllegalArgumentException, Throwable {
		ListingPage_React listingPage=new ListingPage_React(driver);
		ProductDetailData productDetailData = new ProductDetailData();
		String ProductName = null;
		boolean isProductAdded = false;
		int count=0;
		while(! isProductAdded && count<3)
		{
			count+=1;
			List<WebElement> productList_all = new ArrayList<WebElement>();
			List<WebElement> productList = new ArrayList<WebElement>();
			System.out.println(locator.getBy());
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
			Locator ProductLocator=null;
			for (WebElement product : productList) {
				ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
				Locator extendedLocator = ProductLocator.concatLocator(listingPage.ExtendedLocatorProductName());
				System.out.println(ProductLocator.getBy());

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
					mouseOver(ProductLocator);
					ProductType = getText(ProductLocator.concatLocator(listingPage.ExtendedProductType()));
					//ProductType=getAttribute(ProductLocator.concatLocator(ExtendedProductType_option()),"title");
				}
				if (ProductType.contains("size")||ProductType.contains("SIZE")) {
					TestListner.testing.get().log(LogStatus.INFO, "Trying to Add " + ProductName+" to cart");
					Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), ProductName);
					isProductAdded = addToBagSizeProductPage(productLocator,ProductName);
					CartPage_React CartPage_React = new CartPage_React(driver);
					if (isProductAdded == true) {
						TestListner.testing.get().log(LogStatus.INFO, ProductName+" added to cart successfully! Checking if the product is present in Cart.");
						if (!waitUntilDisplayed(CartPage_React.SlidingCartLogo(), 4)) {
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
					closeCart();
					productDetailData.setProductName(ProductName);
					productDetailData.setProductPrize(replaceRuppeeSymbol(getText(PriceLocator()).replace("Rs.", "").trim()));
					break;
				}

			}

			if(isProductAdded)
			{
				break;
			}
			else
			{
				if(isDisplayed(listingPage.sliderNextButton()))
				{
					for(WebElement slidernext:getAllElements(listingPage.sliderNextButton().getBy()))
					{
						bringElementIntoView(listingPage.sliderNextButton());
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



	/**
	 * @return
	 */
	private boolean isInStockMessageInCart() {
		// TODO Auto-generated method stub
		return false;
	}


	private boolean addToBagSizeProductPage(Locator locator,String ProductName) throws Throwable, Throwable {
		boolean isSizeAvailable = false;
		String MainPage = driver.getWindowHandle();
		bringElementIntoView(locator);
		click(locator);
		Set<String> MultipleTabs = driver.getWindowHandles();
		System.out.println(MultipleTabs.toString());
		for (String producttab : MultipleTabs) {
			if (!MainPage.equalsIgnoreCase(producttab)) {
				if (driver.switchTo().window(producttab).getTitle().contains(ProductName)) {
					break;
				}
			}
		}

		waitUntilDisplayed(SelectSizeLabel(), 30);
		waitForPagetoLoad()  ;  
		List<WebElement> sizes = getWebElements(sizes());
		if (!sizes.isEmpty()) {
			for (WebElement size : sizes) {
				Locator sizeLocator = new Locator(By.xpath(getAbsoluteXPath(size)), "Size");
				click(sizeLocator);
				// shade.findElement(By.xpath("ancestor::li/following-sibling::li/a/img[@class='tickmark']")).click();
				break;
			}

			if (waitUntilDisplayed(NotifyMe(), 3)) {
				driver.close();
				isSizeAvailable = false;
				driver.switchTo().window(MainPage);
			} else {
				bringElementIntoView(AddToBag());
				click(AddToBag());
				isSizeAvailable = true;
			}
		}

		return isSizeAvailable;
	}		



	private boolean addToBagShadeProductPage(Locator locator,String ProductName) throws Throwable, Throwable {

		boolean isShadeAvailable = false;
		String MainPage = driver.getWindowHandle();
		bringElementIntoView(locator);
		click(locator);
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
				isShadeAvailable = true;
			}
		}

		return isShadeAvailable;
	}		


	private boolean addToBagSimpleProductPage(Locator locator,String ProductName ) throws Throwable, Throwable{
		boolean isproductadded = false;
		String MainPage = driver.getWindowHandle();
		bringElementIntoView(locator);
		click(locator);
		Set<String> MultipleTabs = driver.getWindowHandles();
		System.out.println(MultipleTabs.toString());
		for (String producttab : MultipleTabs) {
			if (!MainPage.equalsIgnoreCase(producttab)) {
				if (driver.switchTo().window(producttab).getTitle().contains(ProductName)) {
					break;
				}
			}
		}

		if (waitUntilDisplayed(AddToBag(), 3)) {
			bringElementIntoView(AddToBag());
			click(AddToBag());
			//String message=getText(AddToBagMessage());
			//if(! message.equals("No data"))
			//{
			isproductadded = true;
			TestListner.testing.get().log(LogStatus.PASS, "message");
			//	}
			//	else
			//	{
			//		TestListner.testing.get().log(LogStatus.FAIL, "Product Added message not displayed on clicking Add to Cart button");
			//	}

		} else {
			driver.close();
			isproductadded = false;
			driver.switchTo().window(MainPage);


			//Framework framework = new Framework();

			/*AnalyticsData Analyticsaddtocart = framework.getData(AnalyticsData.class, "addtocart");
					AnalyticsInfoPage analyticsInfoPage = new AnalyticsInfoPage(driver);
					analyticsInfoPage.getEvarJSLoad(Analyticsaddtocart);*/
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


	public boolean Subscribe(AccountData Login, CheckoutData checKoutData,String environmentURL) throws Throwable {
		ListingPage_React listingPage = new ListingPage_React(driver);
		List<WebElement> productList = getWebElements(ListingPage_React.allProducts());
		boolean Subscription = false;

		//ProductPage_React productPage = new ProductPage_React(driver);
		for (WebElement product : productList) {
			Subscription = checkSubsciption(product, Login, checKoutData,environmentURL);

			if (Subscription == true) {
				break;
			}
		}
		return  Subscription;
	}

	public boolean checkSubsciption(WebElement product, AccountData accountData, CheckoutData checKoutData,String environmentURL) throws Throwable, Throwable {
		Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
		Locator extendedLocator = ProductLocator.concatLocator(ExtendedLocatorProductName());
		bringElementIntoView(ProductLocator);
		ScrollDown("50");
		boolean isSubscibeButtonDisplayed = false;
		String ProductName= getText(extendedLocator);
		//System.out.print(ProductName);
		String primarymainpage = driver.getWindowHandle();

		CheckOutPage checkOutPage = new CheckOutPage(driver);
		clickandNavigateWindow(ProductLocator, ProductName,environmentURL);
		waitUntilDisplayed(SubscribeButton(), 5);
		TestListner.testing.get().log(LogStatus.INFO, "Trying to check Subscription on " + ProductName);
		if(isDisplayed(SubscribeButton()))
		{
			TestListner.testing.get().log(LogStatus.INFO, "Subscribe Button Displayed on " + ProductName);
			List<WebElement> sizes = getWebElements(sizes());
			if (!sizes.isEmpty()) {
				for (WebElement size : sizes) {
					Locator sizeLocator = new Locator(By.xpath(getAbsoluteXPath(size)), "Size");
					click(sizeLocator);
					// shade.findElement(By.xpath("ancestor::li/following-sibling::li/a/img[@class='tickmark']")).click();
					break;
				}
			}
			click(SubscribeButton());
			click(SubscribeConfirmButton());
			checkOutPage.fillNewAddressSubscribe(checKoutData);
			waitUntilDisplayed(SubscriptionFinalConfirm(), 5);
			bringElementIntoView(SubscriptionFinalConfirm());
			click(SubscriptionFinalConfirm());

			String OutofStockmsg="";
			int counter=0;
			while(OutofStockmsg.equals("") && counter<20) {
				counter+=1;
				OutofStockmsg = driver.findElement(SubscribeMsg().getBy()).getText();
				if(!OutofStockmsg.equals("")) {
					break;
				}
			}
			System.out.print(OutofStockmsg);
			isSubscibeButtonDisplayed = OutofStockmsg.contains("Thank You");
			TestListner.testing.get().log(LogStatus.INFO, "Subscription Performed on " + ProductName);
		}else {
			TestListner.testing.get().log(LogStatus.INFO, "Subscribe Button Not Displayed on" + ProductName);
			driver.close();
			driver.switchTo().window(primarymainpage);
		}

		return isSubscibeButtonDisplayed;
	}

	public boolean Notifyme(AccountData Login,String environmentURL) throws Throwable {
		List<WebElement> productList = getWebElements(ListingPage_React.oosProducts());
		boolean NotifyfromPLP=false;
		for (WebElement product : productList) {
			NotifyfromPLP = checkNotifyFromPD(product, Login,environmentURL);
			if (NotifyfromPLP == true) {
				break;
			}
		}
		return NotifyfromPLP;
	}

	public boolean checkNotifyFromPD(WebElement product, AccountData accountData,String environmentURL) throws Throwable, Throwable {
		Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
		Locator ProductTypeLocator = ProductLocator.concatLocator(NotifyMe());
		bringElementIntoView(ProductTypeLocator);
		ScrollDown("50");
		boolean isNotifybuttonDisplayed = false;
		System.out.print(ProductTypeLocator);
		mouseOver(ProductLocator);
		String ProductName= ProductLocator.getName();
		String productType = getText(NotifyMe());
		if (productType.contains("NOTIFY")) {
			clickandNavigateWindow(ProductLocator, ProductName, environmentURL);
			waitUntilDisplayed(NotifyEmailPD(), 5);
			/*if(isDisplayed(ShadeSizeSelectionPLP())){
		selectDefaultValueFromDropDownBox(ShadeSizeSelectionPLP());
		}*/
			EnterValue(NotifyEmailPD(), accountData.getUsername());
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
	/**
	 * @param allProducts
	 * @return
	 */



}

