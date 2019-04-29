package Mobile_Pages_Nykaa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import DataNykaa.AccountData;
import DataNykaa.ProductDetailData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.CustomSoftAssert;
import FrameWorkNykaa.Framework;
import FrameWorkNykaa.Locator;
import PagesNykaa.CartPage;
import PagesNykaa.HeaderBar;
import PagesNykaa.ListingPage;




public class ProductPage_Mobile extends BrowserAction  {
	public ProductPage_Mobile(WebDriver driver) {
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

	Locator shades() {
		return new Locator(
				By.xpath("//span[contains(@class,'color-pallets')][not(contains(@class,'color-pallets-cross'))]"),"shades");
	}
	
	Locator size() {
		return new Locator(
				By.xpath("//span[@class='size-pallets']/ancestor::li[@class!='opacity-dull']"),"size");
	}

	Locator addWistICon(){
		return new Locator(By.id("wishlist-add"),"WishlistIcon");
	}


	Locator wishlistWrapper(){
		return new Locator(By.xpath("//div[@class='wishlist']"),"Wishlist Wrapper");
	}
	Locator SelectShadeLabel(){
		return new Locator(By.id("select_shade_lbel"),"Maximise shade image");
	}

	Locator DescriptionLink(){

		return new Locator(By.id("description"),"Description link");

	}


	Locator CustomerAlsoViewed(){
		return new Locator(By.xpath("//div[@class='heading--fancy']/span[text()='Customers Also Bought']"),"Customer also view Widget");
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
		return new Locator(By.xpath("//button[contains(@class,'cart-btn')]"),"Add To Bag");
	}
	
	Locator AddToBagLoader()
	{
		return new Locator(By.xpath("//button[contains(@class,'cart-btn')]/div[contains(@class,'css-loader-circle')]"),"Add To Bag Loader");
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
		return new Locator(By.xpath("//i[contains(@class,'fa fa-heart')]"),"Wishlist icon");
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

	Locator Product(String ProductName) {
		return new Locator(By.xpath("//div[@class='product-list-box card' and not(ancestor::div[contains(@class,'height-animate ')])]//span[contains(text(),'"+ProductName+"')]/ancestor::div[@class='product-list-box card']"),"Add To Bag");
	}

	Locator ProductWidget(String ProductName) {
		return new Locator(By.xpath("//div[contains(@class,'widget_wrapper')]/div/div[@class='product-list-box card']//span[contains(text(),'"+ProductName+"')]/ancestor::div[@class='product-list-box card']"),"Add To Bag");
	}

	public ProductDetailData addAnyStaticComboProductFromProductPage(Locator locator) throws Throwable, Throwable{

		CartPage_Mobile cartPage = new CartPage_Mobile(driver);
		if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
			click(cartPage.BackMenuCart());
		}
		return addToBagStaticComboFromProductPageWithinList(locator);

	}
	
	public List<ProductDetailData> addDynamicCombo() throws Throwable, Throwable{
		bringElementIntoView(AddToBagDynamicComboButton());

		List<ProductDetailData> combodetail = getComboProductDetail();
		click(AddToBagDynamicComboButton());
		addConfigProductFromComboPopup();
		return  combodetail;	
	}


	Locator PeopleWhoboughtwidget(){

		return new Locator(By.xpath("//div[@class='heading--fancy']/span[text()='Customers Also Bought']"),"people Also bought widget");
	}


	public boolean verifyTryitOn() throws Throwable, Throwable{
		bringElementIntoView(TryItOnButton());
		click(TryItOnButton());
		return	waitUntilDisplayed(TryItOnShadeSelector(), 10);


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

	private List<ProductDetailData> getComboProductDetail() throws Throwable, Throwable {

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

	public ProductDetailData addAnySimpleProductFromProductPage() throws Throwable {
		CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
			click(CartPage_Mobile.BackMenuCart());
		}
		return addProductFromProductList("simple",false);

	}

	public ProductDetailData addAnyShadeProductFromProductPage() throws Throwable {
		CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
			click(CartPage_Mobile.BackMenuCart());
			waitUntilDisappear(CartPage_Mobile.BackMenuCart());
		}
		return addProductFromProductList("shade",false);

	}
	
	public ProductDetailData addAnySizeProductFromProductPage() throws Throwable {
		CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
			click(CartPage_Mobile.BackMenuCart());
			waitUntilDisappear(CartPage_Mobile.BackMenuCart());
		}
		return addProductFromProductList("size",false);

	}

	public boolean addToWishlist() throws IllegalArgumentException, Throwable{
		boolean addedToWishlist=false;
		for(WebElement wishlistBtn:driver.findElements(WishListButton().getBy()))
		{
			if(wishlistBtn.isDisplayed()&& wishlistBtn.isEnabled())
			{
				bringElementIntoViewWebElement(wishlistBtn);
				ScrollDown("50");
				if(getAttribute(WishListButton(), "class").equalsIgnoreCase("fa fa-heart"))
				{
					wishlistBtn.click();
					Wait(3);
				}
			}
		}
		
		for(WebElement wishlistBtn:driver.findElements(WishListButton().getBy()))
		{

			if(wishlistBtn.isDisplayed()&& wishlistBtn.isEnabled())
			{
				bringElementIntoViewWebElement(wishlistBtn);
				ScrollDown("50");
				wishlistBtn.click();
				Thread.sleep(3000);
				waitUntilDisplayed(wishlistWrapper(), 5);
				Wait(3);
				System.out.println(getAttribute(WishListButton(), "class"));
				if(getAttribute(WishListButton(), "class").equalsIgnoreCase("fa fa-heart"))
				{
					addedToWishlist=true;
					break;
				}
			}
		
		}
		bringElementIntoView(WishListButton());
		ScrollDown("50");
		
		return addedToWishlist;

	}

	public boolean addToWishlist_Lux() throws IllegalArgumentException, Throwable{
		boolean addedToWishlist=false;
		for(WebElement wishlistBtn:driver.findElements(WishListButton().getBy()))
		{
			if(wishlistBtn.isDisplayed()&& wishlistBtn.isEnabled())
			{
				bringElementIntoViewWebElement(wishlistBtn);
				ScrollDown("50");
				if(getAttribute(WishListButton(), "class").equalsIgnoreCase("fa fa-heart luxury_clr"))
				{
					wishlistBtn.click();
					Wait(3);
				}
			}
		}

		for(WebElement wishlistBtn:driver.findElements(WishListButton().getBy()))
		{

			if(wishlistBtn.isDisplayed()&& wishlistBtn.isEnabled())
			{
				bringElementIntoViewWebElement(wishlistBtn);
				ScrollDown("50");
				wishlistBtn.click();
				Thread.sleep(3000);
				waitUntilDisplayed(wishlistWrapper(), 5);
				Wait(3);
				System.out.println(getAttribute(WishListButton(), "class"));
				if(getAttribute(WishListButton(), "class").equalsIgnoreCase("fa fa-heart luxury_clr"))
				{
					addedToWishlist=true;
					break;
				}
			}

		}
		bringElementIntoView(WishListButton());
		ScrollDown("50");

		return addedToWishlist;

	}

	private ProductDetailData addProductFromProductList(String type, boolean isWidget) throws Throwable {
		ProductDetailData productDetailData = new ProductDetailData();
		ListingPage_Mobile ListingPage_Mobile = new ListingPage_Mobile(driver);
		CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		String ProductName = null;
		String ProductType=null;
		boolean isProductAdded = false;
		int productCount=0;
		if(isWidget)
		{
			if(isDisplayed(ListingPage_Mobile.showMoreButton()))
			{
				bringElementIntoView(ListingPage_Mobile.showMoreButton());
				ScrollDown("30");
				click(ListingPage_Mobile.showMoreButton());
			}
			productCount=driver.findElements(ListingPage_Mobile.widgetProductsLocator().getBy()).size();
		}
		else
		{
			productCount = driver.findElements(ListingPage_Mobile.allProductsLocator().getBy()).size();
		}
		for (int i=1;i<=productCount;i++) {
			String ProductName_updated="";
			//System.out.println(geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[@class='product-list-box card']//div[@class='m-content__product-list__title']")));
			if(isWidget)
			{
				ProductName=geteText(By.xpath("//div[@name='widget_listing_0']/ancestor::div/div/div[contains(@class,'widget-listing')]/div/div["+i+"]/div[@class='product-list-box card']//div[@class='m-content__product-list__title']"));
			}
			else
			{
				ProductName=geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[contains(@class,'product-list-box card')]//div[@class='m-content__product-list__title']"));
			}
			String[] name=ProductName.split(" ");
			for(int j=0;j<name.length;j++)
			{
				if(! name[j].contains("'"))
					ProductName_updated=ProductName_updated+" "+name[j];
			}
			ProductName_updated=ProductName_updated.trim();
			if(isWidget)
			{
				ProductType=geteText(By.xpath("//div[@name='widget_listing_0']/ancestor::div/div/div[contains(@class,'widget-listing')]/div/div["+i+"]/div[@class='product-list-box card']//button"));
			}
			else
			{
				ProductType=geteText(By.xpath("//div[@class='m-content__product-list']/div[2]/div/div/div["+i+"]/div[contains(@class,'product-list-box card')]//button"));
			}


			boolean isWishlist = false;
			if(ProductType.contains("BAG")&& type.equalsIgnoreCase("simple"))
			{
				ProductType = "Simple";
			}
			else if (ProductType.contains("SHADES")&& type.equalsIgnoreCase("shade")) {
				ProductType = "Shade";
				//isWishlist = true;
			} 
			else if (ProductType.contains("SIZE")&& type.equalsIgnoreCase("size")) {
				ProductType = "Size";
				//isWishlist = true;
			} 
			else if(ProductType.contains("BAG")&& type.equalsIgnoreCase("bundle"))
			{
				ProductType = "bundle";
			}


			if (ProductType.contains("Shade")) {
				if(isWidget)
				{
					//isProductAdded = addToBagForShades(AddToBagButtonWidget(ProductName_updated));
				}
				else
				{
					isProductAdded = addShadeProduct(Product(ProductName_updated), isWishlist);
				}
			}

			else if (ProductType.contains("Size")) {
				if(isWidget)
				{
					//isProductAdded = addToBagForSize(AddToBagButtonWidget(ProductName_updated), isWishlist);
				}
				else
				{
					isProductAdded = addSizeProduct(Product(ProductName_updated), isWishlist);
				}
			}
			else if (ProductType.contains("Simple"))
			{
				if(isWidget)
				{
					isProductAdded = addSimpleProduct(ProductWidget(ProductName_updated), isWishlist);
				}
				else
				{
					isProductAdded = addSimpleProduct(Product(ProductName_updated), isWishlist);
				}
			}
			
			else if (ProductType.contains("bundle"))
			{
				if(isWidget)
				{
					isProductAdded = addSimpleProduct(ProductWidget(ProductName_updated), isWishlist);
				}
				else
				{
					isProductAdded = addSimpleProduct(Product(ProductName_updated), isWishlist);
				}
			}
			/*
			if (isProductAdded == true) {
				if (!waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 4)) {
					CartPage_Mobile.goToCart();
					if (isAlertPresent(driver)) {
						switchToDefaultContent();
						isProductAdded = false;
					}

				}
			}*/


			if (isProductAdded) {

				productDetailData.setProductName(ProductName);
				try {
					if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
						click(CartPage_Mobile.BackMenuCart());
						waitUntilDisappear(CartPage_Mobile.BackMenuCart());}
				}
				catch(Exception e) {}

				break;
			}

		}
		return productDetailData;
	}

	public boolean addSimpleProduct(Locator productLocator, boolean isWishlist) throws Throwable, Throwable {
		boolean isSimpleProductAdded = true;
		try
		{
			ListingPage_Mobile ListingPage_Mobile = new ListingPage_Mobile(driver);
			bringElementIntoView(productLocator);
			ScrollDown("50");
			click(productLocator);
			closeMissedOfferFrame();
			bringElementIntoView(AddToBag());
			click(AddToBag());
			Thread.sleep(4000);
			//CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
			if (isPresent(ListingPage_Mobile.outOfStockMessage())) {
				click(ListingPage_Mobile.closeButton());
				Thread.sleep(2000);
				if (ListingPage_Mobile.isAlertAvailable()) {
					clickJScript(ListingPage_Mobile.backButton());
				}
				isSimpleProductAdded = false;
			}
			/*else
				if(CartPage_Mobile.isInstockMessageDisplay())
				{
					CartPage_Mobile.removeFirstProduct();
					isSimpleProductAdded = false;
				}*/
		}
		catch(Exception e)
		{
			isSimpleProductAdded=false;
		}

		return isSimpleProductAdded;

	}

	public boolean addShadeProduct(Locator productLocator, boolean isWishlist) throws Throwable, Throwable {
		boolean isProductAdded = false;
		try
		{
			bringElementIntoView(productLocator);
			ScrollDown("50");
			click(productLocator);
			driver.navigate().refresh();
			closeGetAppPopUp();
			List<WebElement> shades = getWebElements(shades());
			if (!shades.isEmpty()) {
				for (WebElement shade : shades) {

					click(shade, "Shade");
					break;
				}
				closeMissedOfferFrame();
				bringElementIntoView(AddToBag());
				click(AddToBag());
				waitUntilDisappear(AddToBagLoader());
				isProductAdded=true;
			}
			else
			{
				driver.navigate().back();
			}
		}
		catch(Exception e)
		{
			isProductAdded=false;
		}

		return isProductAdded;

	}
	
	public boolean addSizeProduct(Locator productLocator, boolean isWishlist) throws Throwable, Throwable {
		boolean isProductAdded = false;
		try
		{
			bringElementIntoView(productLocator);
			click(productLocator);
			driver.navigate().refresh();
			closeGetAppPopUp();
			List<WebElement> sizes = getWebElements(size());
			if (!sizes.isEmpty()) {
				for (WebElement size : sizes) {
					bringElementIntoViewWebElement(size);
					closeMissedOfferFrame();
					click(size, "Shade");
					break;
				}
				closeMissedOfferFrame();
				bringElementIntoView(AddToBag());
				click(AddToBag());
				isProductAdded=true;
			}
		}
		catch(Exception e)
		{
			isProductAdded=false;
		}

		return isProductAdded;

	}

	public boolean isPeopleWhoboughtDisplayed(){
		return waitUntilDisplayed(PeopleWhoboughtwidget(), 6);
	}

	public boolean isCustomerAlsoviewedDisplayed() throws InstantiationException, IllegalAccessException, Throwable{
		try
		{
			closeMissedOfferFrame();
			CartPage_Mobile_New CartPage_Mobile = new CartPage_Mobile_New(driver);
			if(waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)){
				click(CartPage_Mobile.BackMenuCart());
				waitUntilDisappear(CartPage_Mobile.BackMenuCart());
			}
		}
		catch(Exception e)
		{

		}
		return waitUntilDisplayed(CustomerAlsoViewed(),6);
	}
	
	public ProductDetailData addAnyStaticComboProductFromProductPage() throws Throwable {
		CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(driver);
		if (waitUntilDisplayed(CartPage_Mobile.BackMenuCart(), 3)) {
			click(CartPage_Mobile.BackMenuCart());
			waitUntilDisappear(CartPage_Mobile.BackMenuCart());
		}
		return addProductFromProductList("bundle",false);

	}

	private ProductDetailData addToBagStaticComboFromProductPageWithinList(Locator locator) throws IllegalArgumentException, Throwable {
		//return null;
		ProductDetailData productDetailData = new ProductDetailData();
		String ProductName = null;
		ListingPage_Mobile listingPage = new ListingPage_Mobile(driver);
		listingPage.ScrollDown("2000", 2);
		List<WebElement> productList = getWebElements(locator);
		boolean isProductAdded = false;
		for (WebElement product : productList) {
			Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product");
			Locator extendedLocator = ProductLocator.concatLocator(listingPage.ExtendedLocatorProductName());

			System.out.println(extendedLocator);
			ProductName = getText(extendedLocator);
			String ProductType = getAttribute(ProductLocator.concatLocator(listingPage.DataType()),"data");
			if (ProductType.contains("combo") ){
				Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), ProductName);
				isProductAdded = addSimpleProduct(productLocator, false);
				CartPage_Mobile cartPage = new CartPage_Mobile(driver);
				HeaderBar_Mobile headerbar = new HeaderBar_Mobile(driver);
				if (isProductAdded == true) {
					if (!waitUntilDisplayed(cartPage.SlidingCartLogo(), 4)) {
						bringElementIntoView(headerbar.CartLocator());
						click(headerbar.CartLocator());
						/*if (isInStockMessageInCart()) {

						}*/
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

