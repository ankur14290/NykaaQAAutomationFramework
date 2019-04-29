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


public class CartPage_React extends BrowserAction {

	public CartPage_React(WebDriver driver) {
		this.driver = driver;

	}
	Locator ShoppingCartTableRightColumn()
	{
		return new Locator(By.xpath("/following-sibling::td"),"Right columns");
	}
	Locator ShoppingCartTableLeftColumn()
	{
		return new Locator(By.xpath("//div[@class='order-history']/table//tr/td[1]"),"Left columns");
	}
	Locator extendedProductNameLocator(){

		return new Locator(By.xpath("//div[@class='media-heading']//div/h2"),"ProductName ");
	}

	Locator inStockMessage(){
		return new Locator(By.xpath("//span[@class='product_qty_blck'][1]/ancestor::tr[@id='product_block']//span[contains(@style,'color:red')][contains(text(),'in stock')]"),"in stock message");
	}
	Locator CouponCodeTextBox(){
		return new Locator(By.xpath("//input[@id='couponCode']"),"Coupon Code TextBox");
	}

	Locator ApplyCouponButton(){
		return new Locator(By.xpath("//button[text()='Apply']"),"Apply Coupon Button");
	}


	Locator CancelCouponbutton(){

		return new Locator(By.xpath("//span[@class='remove-coupon-code']"),"Remove Coupon Button");
	}
	Locator OutOfStockInbagMessage() {
		return new Locator(By.xpath("//div[@class='error-msg']"),"Out Of Stock Message");
	}

	Locator OutOfStockRemoveButton() {
		return new Locator(By.xpath("//div[@class='error-msg']//ancestor::div[@class='media-body']/i"),"Out Of Stock Message");
	}
	Locator closelabel() {
		return new Locator(By.id("closelb"), "Close lb popup");
	}

	Locator Checkout_Button() {
		return new Locator(By.xpath("//button[text()='CHECKOUT']|//span[contains(text(),'CHECKOUT')]"), "Checkout Button");
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

		return new Locator(By.xpath("//div[@class='media sliding-media-obj']"),"Product Row in Bag ");
	}


	Locator ServiceRowsInBag(){
		return new Locator(By.xpath("//tr[@id='product_block']"),"Services In Bag");
	}
	public static Locator BackMenuCart() {
		return new Locator(By.xpath("//i[@class='fa fa-angle-left']"), "Back Menu Of Cart");
	}

	Locator ProductsInBagsLocator() {
		return new Locator(By.xpath("//tr[@id='product_block']/descendant::span[@class='price']"),"products in bag");
		/*return new Locator(
				By.xpath(
						"//td[@class='last_col']/span/img[@title='Remove item'][not(contains(@style,'display:none'))]"),
				"Remove Icons");*/
	}

	Locator ExtendedRemoveProdutFromBagLocator(){
		return new Locator(By.xpath("/div[@class='media-body']/i"),"Remove Icon");

	}

	Locator removeButton(){
		return new Locator(By.xpath("//button[contains(@class,'remove-btn')]"),"Remove Button");

	}

	Locator QuantityUpdateButton() {

		return new Locator(By.xpath("//span[@class='quantity']/following-sibling::*[not(contains(@class,'svg-fade-color'))]"),"Update Button");
	}

	Locator FreeShippingLable() {
		return new Locator(By.xpath("//label[text()='Free Shipping']"), "Free Shiping lable");

	}

	Locator SlidingCartLogo() {
		return new Locator(By.id("sliding_cart_bag_logo"), "Sliding Logo");

	}

	Locator clearCartMessage(){
		return new Locator(By.xpath("//div[@class='cart-items-not-found']"),"Cart Empty Message");
	}

	Locator cartLoader(){
		return new Locator(By.xpath("//div[@class='css-loader-circle ']"),"Cart Loader");
	}

	Locator collapseCartFooterLocator(){
		return new Locator(By.xpath("//i[@class='fa fa-angle-down']"),"Collapse Cart Footer");
	}
	Locator expandCartFooterLocator(){
		return new Locator(By.xpath("//i[@class='fa fa-angle-up']"),"Expand Cart Footer");
	}

	public void removeAllProduct() throws InstantiationException, IllegalAccessException, InterruptedException {
		Thread.sleep(1000);
		waitUntilDisappear(cartLoader());
		collapseCartFooter();

		List<WebElement> productrow = null;
		while(isDisplayed(ProductRowsInBag()) && getWebElements(ProductRowsInBag()).size()>0)
		{
			productrow = getWebElements(ProductRowsInBag().concatLocator(ExtendedRemoveProdutFromBagLocator()));
			for (WebElement product : productrow) {
				try 
				{
					click(product,"Remove icon");
					waitUntilDisplayed(removeButton(),10);
					click(removeButton());
					waitUntilDisappear(cartLoader());
				}
				catch(Exception e)
				{
					break;
				}
				if((waitUntilDisplayed(clearCartMessage(), 3))) 
				{
					break;
				}
			}
		}
		click(BackMenuCart());
		waitUntilDisappear(BackMenuCart());
	}
	public void collapseCartFooter() {
		if(isDisplayed(collapseCartFooterLocator()))
		{
			try {
				click(collapseCartFooterLocator());
			}catch(Exception e) {}
		}
	}

	public void expandCartFooter() {
		if(! isDisplayed(ShoppingCartTableLeftColumn()))
		{
			try 
			{
				click(expandCartFooterLocator());
			}
			catch(Exception e) {}
		}
	}

	public boolean isSlidingCartOpen() {

		//return waitUntilDisplayed(SlidingCartLogo(), 4);
		return waitUntilPresent(BackMenuCart(), 4);

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
		if(isDisplayed(SlidingCartLogo()))
		{
			click(SlidingCartLogo());
		}
		else
		{
			openSlidingCart();
		}
		waitUntilDisplayed(BackMenuCart(), 5);
		boolean isProductPresent = false;
		waitUntilDisappear(cartLoader());
		collapseCartFooter();
		if(isDisplayed(cartEmpty()))
		{
			return false;
		}
		String productName = productDetailData.getProductName();
		List<WebElement> productrow = getWebElements(ProductRowsInBag());
		for (WebElement product : productrow) {
			Locator ProductListBagLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "product List ");
			Locator productNameLocator = ProductListBagLocator.concatLocator(extendedProductNameLocator());

			String ProductNameFromUI = getAttribute(productNameLocator,"title");
			System.out.println(ProductNameFromUI);
			if(ProductNameFromUI.equalsIgnoreCase(productName)||ProductNameFromUI.contains(productName.replace("...", ""))||productName.contains(ProductNameFromUI.replace("...", "")))
			{
				isProductPresent = true;
				break;
			}
		}
		closeCart();
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

		new CartPage_React(driver);
		if (waitUntilDisplayed(CartPage_React.BackMenuCart(), 3)) {
			click(CartPage_React.BackMenuCart());
			waitUntilDisappear(CartPage_React.BackMenuCart());}
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
		new HeaderBar(driver);
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

	public String checkOut() throws Throwable {
		FooterBar footerbar = new FooterBar(driver);
		footerbar.CloseAdvertismentPopup();
		if (!waitUntilDisplayed(CartPage_React.BackMenuCart(), 3))
		{
			click(HeaderBar.CartLocator());
		}
		Thread.sleep(2000);
		waitUntilDisappear(cartLoader());
		expandCartFooter();
		waitUntilDisplayed(Checkout_Button(), 5);
		click(Checkout_Button());
		if(removeOOSProduct())
		{
			if(Integer.parseInt(getShoppingTableData("subtotal"))<500)
			{
				return "add more products";
			}
		}
		for(int i=0;i<10;i++)
		{
			if(!driver.getCurrentUrl().contains("shoppingBag"))
			{
				Thread.sleep(1000);
			}
			else
			{
				break;
			}
		}
		CheckOutPage checkoutPage = new CheckOutPage(driver);
		checkoutPage.waitUntilDisappear(checkoutPage.checkoutPageLoader());
		return"successful";
	}
	public boolean removeOOSProduct() {
		if(waitUntilPresent(OutOfStockRemoveButton(),5))
		{
			try 
			{
				click(OutOfStockRemoveButton());
				waitUntilDisplayed(removeButton(),10);
				click(removeButton());
				waitUntilDisappear(cartLoader());
				return true;
			}
			catch(Exception e)
			{return false;}
		}
		else
		{
			return false;
		}
	}

	public String ApplyCoupon(CheckoutData checkoutData) throws Throwable, Throwable{
		if(! isSlidingCartOpen())
		{
			openSlidingCart();
		}
		waitUntilDisappear(cartLoader());
		System.out.println(checkoutData.getCouponcode());
		if(isDisplayed(CancelCouponbutton()))
		{
			return 	getShoppingTableData("Discount");
		}
		if(! isDisplayed(ShoppingCartTableLeftColumn()))
		{
			expandCartFooter();
		}
		waitUntilDisplayed(ShoppingCartTableLeftColumn(), 10);
		EnterValue(CouponCodeTextBox(), checkoutData.getCouponcode());
		waitUntilDisplayed(ApplyCouponButton(),10);
		click(ApplyCouponButton());
		waitUntilDisappear(cartLoader());
		waitUntilDisplayed(CancelCouponbutton(), 30);

		return 	getShoppingTableData("Discount");

	}

	public boolean CancelCoupon() throws Throwable, Throwable{
		click(CancelCouponbutton());
		return waitUntilDisplayed(CancelCouponbutton(),20);
	}


	public String getShoppingTableData(String Rowname) throws Throwable, Throwable
	{
		openSlidingCart();
		waitUntilDisappear(cartLoader());
		if((waitUntilDisplayed(clearCartMessage(), 3))) 
		{
			return "1";
		}
		expandCartFooter();
		waitUntilDisplayed(ShoppingCartTableLeftColumn(), 5);
		List<WebElement> LeftColumns = getWebElements(ShoppingCartTableLeftColumn());
		Locator rightColumn = null ;
		for(WebElement column: LeftColumns)
		{
			Locator leftColumnLocator = new Locator(By.xpath(getAbsoluteXPath(column))," Column value" );
			String value = getText(leftColumnLocator);
			rightColumn = leftColumnLocator.concatLocator(ShoppingCartTableRightColumn());
			getText(rightColumn);
			if(value.contains(Rowname))
				break;
		}
		System.out.println(rightColumn.getBy());
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
		new HeaderBar(driver);

		try
		{
			openSlidingCart();
			collapseCartFooter();

			click(QuantityUpdateButton());
			waitUntilDisappear(cartLoader());
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
		new HeaderBar(driver);
		if(!isSlidingCartOpen() )
		{
			List<WebElement>carts=getAllElements(HeaderBar.CartLocator().getBy());
			for(WebElement cart:carts)
			{
				if(cart.isDisplayed())
					click(cart,"Cart");
			}
			waitUntilDisappear(cartLoader());
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
