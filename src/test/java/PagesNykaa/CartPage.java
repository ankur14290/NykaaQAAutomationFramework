package PagesNykaa;

import java.util.ArrayList;
import java.util.List;

import DataNykaa.BeautyServiceData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import DataNykaa.AnalyticsData;
import DataNykaa.CheckoutData;
import DataNykaa.ProductDetailData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Framework;
import FrameWorkNykaa.Locator;


public class CartPage extends BrowserAction {

	public CartPage(WebDriver driver) {
		this.driver = driver;

	}
	Locator ShoppingCartTableRightColumn()
	{
		return new Locator(By.xpath("/following-sibling::td[@class='co-right pink' or @class='co-right']"),"Right columns");
	}
	Locator ShoppingCartTableLeftColumn()
	{
		return new Locator(By.xpath("//table[@id='shopping-cart-totals-table']/descendant::td[@class='co-left']"),"Left columns");
	}
	Locator ShoppingCartSubTotal()
	{
		return new Locator(By.xpath("//table[@id='shopping-cart-totals-table']/descendant::td[@class='co-left'][contains(text(),'Subtotal')]/following-sibling::td/span"),"Left columns");
	}
	
	Locator extendedProductNameLocator(){

		return new Locator(By.xpath("//td/a/span[@class='product-name']"),"ProductName ");
	}

	Locator inStockMessage(){
		return new Locator(By.xpath("//span[@class='product_qty_blck'][1]/ancestor::tr[@id='product_block']//span[contains(@style,'color:red')][contains(text(),'in stock')]"),"in stock message");
	}
	Locator CouponCodeTextBox(){
		return new Locator(By.id("coupon_code"),"Coupon Code TextBox");
	}

	Locator ApplyCouponButton(){
		return new Locator(By.xpath("//button[@title='Apply Coupon']"),"Apply Coupon Button");
	}


	Locator CancelCouponbutton(){

		return new Locator(By.xpath("//button[@title='Cancel Coupon']"),"Cancel Coupon Button");
	}
	Locator OutOfStockInbagMessage() {
		return new Locator(By.xpath(
				"//p[@class='item-msg error'][contains(text(),'currently out of stock')] | //span[@class='error-msg'] [contains(text(),'The requested quantity is not available')]"),"Out Of Stock Message");
	}
	Locator closelabel() {
		return new Locator(By.id("closelb"), "Close lb popup");
	}

	Locator Checkout_Button() {
		return new Locator(By.xpath("//span[text()='CHECKOUT ']"), "Checkout Button");
	}

	Locator LoaderIcon() {
		return new Locator(By.className("update_loader"), "Update Icon");
	}

	Locator exceedQuantityMessageinBag() {
		return new Locator(By.xpath("//p[@id='exceed_message'][contains(text(),'5 quantities')]"),
				"Exceed Quantity Message");
	}

	Locator productInBag(){
		return new Locator(By.id("product_block"),"product in bag");

	}

	Locator cartEmpty(){
		return new Locator(By.xpath("//div[@class='cart-empty']"),"cart Empty text");
	}

	Locator ProductRowsInBag(){

		return new Locator(By.xpath("//form[@id='sliding-cart-form']/descendant::table/tbody/tr"),"Product Row in Bag ");
	}


	Locator ServiceRowsInBag(){
		return new Locator(By.xpath("//tr[@id='product_block']"),"Services In Bag");
	}
	Locator BackMenuCart() {
		return new Locator(By.id("back_menu"), "Back Menu Of Cart");
	}

	Locator ProductsInBagsLocator() {
		return new Locator(By.xpath("//tr[@id='product_block']/descendant::span[@class='price']"),"products in bag");
		/*return new Locator(
				By.xpath(
						"//td[@class='last_col']/span/img[@title='Remove item'][not(contains(@style,'display:none'))]"),
				"Remove Icons");*/
	}

	Locator ExtendedRemoveProdutFromBagLocator(){
		return new Locator(By.xpath("//ancestor::span/img[@title='Remove item']"),"Remove Button Locator");

	}
	Locator QuantityUpdateButton() {

		return new Locator(By.xpath("//img[@id='Sym'][@class='update_p_qty'][not(contains(@style,'hidden'))]"),
				"Update Button");
	}

	Locator FreeShippingLable() {
		return new Locator(By.xpath("//label[text()='Free Shipping']"), "Free Shiping lable");

	}

	Locator SlidingCartLogo() {
		return new Locator(By.id("sliding_cart_bag_logo"), "Sliding Logo");

	}

	Locator clearCartMessage(){
		return new Locator(By.xpath("//div[@class='cart-empty']"),"Cart Empty Message");
	}

	public void removeAllProduct() throws InstantiationException, IllegalAccessException, InterruptedException {
		waitUntilDisplayed(BackMenuCart(), 5);
		List<WebElement> removableProducts = getWebElements(ProductsInBagsLocator());

		for (WebElement removeProduct : removableProducts) {
			Locator removeProductLocator = new Locator(By.xpath(getAbsoluteXPath(removableProducts.get(0))),"Product");
			Locator removeLocator = removeProductLocator.concatLocator(ExtendedRemoveProdutFromBagLocator());
			click(removeLocator);
			waitUntilDisappear(removeLocator);
			if(!waitUntilDisplayed(cartEmpty(),2)) {
				removableProducts = getWebElements(ProductsInBagsLocator());
			}
			if(waitUntilDisplayed(clearCartMessage(),2)){
				break;
			}
		}
		click(BackMenuCart());
		waitUntilDisappear(BackMenuCart());
	}

	public boolean isSlidingCartOpen() {

		return waitUntilDisplayed(SlidingCartLogo(), 4);

	}

	public boolean IncreaseQuantityOfProduct(WebDriver browser)
			throws InterruptedException, InstantiationException, IllegalAccessException {
		boolean isIncreasedSuccessfully = true;

		while (waitUntilDisplayed(FreeShippingLable(), 5)) {
			click(QuantityUpdateButton());
			/*
			 * WebElement quantityPlus = browser .findElement(By.xpath(
			 * "//img[@id='Sym'][@class='update_p_qty'][not(contains(@style,'hidden'))]"
			 * )); quantityPlus.click();
			 */
			if (waitUntilDisplayed(OutOfStockInbagMessage(), 5)) {

				click(BackMenuCart());
				waitUntilDisappear(BackMenuCart());
				// browser.findElement(By.id("back_menu")).click();
				isIncreasedSuccessfully = false;

				break;
			}
			if (waitUntilDisplayed(exceedQuantityMessageinBag(), 4)) {
				click(closelabel());
				// browser.findElement(By.id("closelb")).click();
				Thread.sleep(4000);
				click(BackMenuCart());
				waitUntilDisappear(BackMenuCart());
				// browser.findElement(By.id("back_menu")).click();
				isIncreasedSuccessfully = false;
				break;
			}

			if (isAlertPresent(browser)) {
				dismissAlert();
				isIncreasedSuccessfully = false;
				break;

			} else
				waitUntilDisappear(LoaderIcon());

			// waitForJQuery(browser);

		}
		return isIncreasedSuccessfully;
	}

	public boolean isProductPresentInBag(ProductDetailData productDetailData) throws Throwable, Throwable
	{
		boolean isProductPresent = false;
		String productName = productDetailData.getProductName();
		List<WebElement> productrow = getWebElements(ProductRowsInBag());
		for (WebElement product : productrow) {
			Locator ProductListBagLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "product List ");
			Locator productNameLocator = ProductListBagLocator.concatLocator(extendedProductNameLocator());

			String ProductNameFromUI = getText(productNameLocator);
			if(ProductNameFromUI.equalsIgnoreCase(productName)||ProductNameFromUI.contains(productName.replace("...", ""))||productName.contains(ProductNameFromUI.replace("...", "")))
			{
				isProductPresent = true;
				break;

			}


		}

		CartPage cartPage = new CartPage(driver);
		if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
			click(cartPage.BackMenuCart());
			waitUntilDisappear(cartPage.BackMenuCart());}
		return isProductPresent;
	}



	public boolean isServicePresentInBag(BeautyServiceData beautyServiceData) throws Throwable, Throwable
	{
		boolean isServicePresent = false;
		String serviceName = beautyServiceData.getServicename();
		List<WebElement> servicerow = getWebElements(ServiceRowsInBag());
		for (WebElement service : servicerow) {
			Locator ServieListBagLocator = new Locator(By.xpath(getAbsoluteXPath(service)), "service List ");
			Locator serviceNameLocator = ServieListBagLocator.concatLocator(extendedProductNameLocator());

			String ServiceNameFromUI = getText(serviceNameLocator);
			if(ServiceNameFromUI.contains("..."))
			{
				if(serviceName.contains(ServiceNameFromUI.replace("...", "")))
				{
					isServicePresent = true;
					break;
				}
			}
			else if(serviceName.equalsIgnoreCase(ServiceNameFromUI))
			{
				isServicePresent = true;
				break;

			}


		}

		CartPage cartPage = new CartPage(driver);
		if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
			click(cartPage.BackMenuCart());
			waitUntilDisappear(cartPage.BackMenuCart());}
		return isServicePresent;
	}





	public boolean isComboProductsPresentInBag(List<ProductDetailData> comboProductsDetail) throws Throwable{
		boolean isIntheBag = false;
		List<Boolean> isProductPresent = new ArrayList<Boolean>();

		for (ProductDetailData comboProduct:comboProductsDetail)
		{
			isProductPresent.add(isProductPresentInBag(comboProduct));

		}
		if(isProductPresent.contains(false))
		{
			isIntheBag = false;
		}
		else{

			isIntheBag = true;
		}
		return isIntheBag;
	}

	public boolean isInstockMessageDisplay() throws Throwable, IllegalAccessException{
		HeaderBar headerBar = new HeaderBar(driver);
		if(waitUntilDisplayed(HeaderBar.CartLocator(), 4))
		{
			click(HeaderBar.CartLocator());
			waitUntilDisplayed(productInBag(), 5);
			return waitUntilDisplayed(inStockMessage(), 3);
		}
		else
		{
			return waitUntilDisplayed(inStockMessage(), 3);
		}



	}



	public void checkOut() throws InterruptedException, InstantiationException, IllegalAccessException {
		Framework framwork = new Framework();
		AnalyticsData analyticCheckoutClick = framwork.getData(AnalyticsData.class, "checkoutclick");
		FooterBar footerbar = new FooterBar(driver);
		footerbar.CloseAdvertismentPopup();
		CartPage cartPage = new CartPage(driver);
		HeaderBar headerbar = new HeaderBar(driver);
		if (!waitUntilDisplayed(cartPage.BackMenuCart(), 3))
		{
			click(HeaderBar.CartLocator());
		}
		click(Checkout_Button());
		CheckOutPage checkoutPage = new CheckOutPage(driver);
		AnalyticsInfoPage analyticinfo = new AnalyticsInfoPage(driver);
		analyticinfo.getEvent(analyticCheckoutClick);
		checkoutPage.waitUntilDisappear(checkoutPage.checkoutPageLoader());
	}

	public String ApplyCoupon(CheckoutData checkoutData) throws Throwable, Throwable{
		if(! isSlidingCartOpen())
		{
			openSlidingCart();
		}
		System.out.println(checkoutData.getCouponcode());
		EnterValue(CouponCodeTextBox(), checkoutData.getCouponcode());
		waitUntilDisplayed(ApplyCouponButton(),10);
		click(ApplyCouponButton());
		waitUntilDisplayed(CancelCouponbutton(), 30);

		return 	getShoppingTableData("Coupon Discount");

	}

	public void CancelCoupon() throws Throwable, Throwable{
		click(CancelCouponbutton());
		waitUntilDisplayed(CancelCouponbutton(),20);
	}


	public String getShoppingTableData(String Rowname) throws Throwable, Throwable
	{
		waitUntilDisappear(LoaderIcon());
		List<WebElement> LeftColumns = getWebElements(ShoppingCartTableLeftColumn());
		Locator rightColumn = null ;
		for(WebElement column: LeftColumns)
		{
			Locator leftColumnLocator = new Locator(By.xpath(getAbsoluteXPath(column))," Column value" );
			String value = getText(leftColumnLocator);
			rightColumn = leftColumnLocator.concatLocator(ShoppingCartTableRightColumn());
			if(value.contains(Rowname))
				break;
		}
		return getText(rightColumn);
	}
	/**
	 * @throws Throwable 
	 * @throws InstantiationException 
	 * 
	 */
	public void removeFirstProduct() throws InstantiationException, Throwable {
		waitUntilDisplayed(BackMenuCart(), 5);
		List<WebElement> removableProducts = getWebElements(ProductsInBagsLocator());


		Locator removeProductLocator = new Locator(By.xpath(getAbsoluteXPath(removableProducts.get(0))),"Product");
		Locator removeLocator = removeProductLocator.concatLocator(ExtendedRemoveProdutFromBagLocator());
		click(removeLocator);
		//waitUntilDisappear(removeLocator);
		removableProducts = getWebElements(ProductsInBagsLocator());


		click(BackMenuCart());
		waitUntilDisappear(BackMenuCart());

	}

	public boolean increaseFirstProduct() throws InstantiationException, Throwable {
		HeaderBar hb=new HeaderBar(driver);
		
		try
		{
		if(! waitUntilDisplayed(CouponCodeTextBox(),2))
		{
			click(HeaderBar.CartLocator());
		}
		waitUntilDisplayed(CouponCodeTextBox(), 5);

		click(QuantityUpdateButton());

		click(BackMenuCart());
		waitUntilDisappear(BackMenuCart());
		return true;
		}
		catch(Exception e)
		{
			return false;
		}

	}
	public void openSlidingCart() throws Exception {
		HeaderBar hb=new HeaderBar(driver);
		if(!isSlidingCartOpen() )
		{
			click(HeaderBar.CartLocator());
			waitUntilDisplayed(CouponCodeTextBox(),2);
			Thread.sleep(1000);
		}
		
	}
	
	public void closeSlidingCart() throws Exception {
		if(isSlidingCartOpen() )
		{
			click(BackMenuCart());
			waitUntilDisappear(BackMenuCart());
		}
		
	}



}
