package Mobile_Pages_Nykaa;

import java.util.ArrayList;
import java.util.List;

import DataNykaa.BeautyServiceData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import DataNykaa.CheckoutData;
import DataNykaa.ProductDetailData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Locator;

public class CartPage_Mobile extends BrowserAction {

	public CartPage_Mobile(WebDriver driver) {
		this.driver = driver;
	}

	Locator CartLocator() {
		return new Locator(By.xpath("//span[contains(@class,'mkr-New-Shopping-Bag')]"), "Shoping Cart Icon");
	}

	Locator CartProductLocator(int index) {
		return new Locator(By.xpath("//div[@class='content_wrapper'][" + index + "]/div[2]/div[@class='head_text']"),
				"Product name");
	}

	Locator EmptyCartLocator() {
		return new Locator(By.xpath("//div[@class='empty_cart']"), "Empty cart");
	}

	Locator EmptyCartHomeButtonLocator() {
		return new Locator(By.xpath("//div[@class='empty_cart_button' and contains(text(),'HOME')]"),
				"Home Button on Empty Cart Page");
	}

	Locator ShoppingCartTableRightColumn() {
		return new Locator(By.xpath("/following-sibling::div[@class='fr div_alignment']"),
				"Right columns");
	}

	Locator ShoppingCartTableLeftColumn() {
		return new Locator(By.xpath("//div[@class='content_wrapper one-step']//div[@class='total_info div_alignment']"),
				"Left columns");
	}

	Locator extendedProductNameLocator() {

		return new Locator(By.xpath("//td/a/span[@class='product-name']"), "ProductName ");
	}

	Locator inStockMessage() {
		return new Locator(By.xpath(
				"//span[@class='product_qty_blck'][1]/ancestor::tr[@id='product_block']//span[contains(@style,'color:red')][contains(text(),'in stock')]"),
				"in stock message");
	}

	Locator CouponCodeTextBox() {
		return new Locator(By.xpath("//div[@class='coupon-btn-wrap']/button[text()= 'Have A Coupon Code?']"), "Coupon Code TextBox");
	}

	Locator ApplyCouponButton() {
		return new Locator(By.xpath("//button[@title='Apply Coupon']"), "Apply Coupon Button");
	}

	Locator CancelCouponbutton() {

		return new Locator(By.xpath("//button[@title='Cancel Coupon']"), "Cancel Coupon Button");
	}

	Locator OutOfStockInbagMessage() {
		return new Locator(By.xpath(
				"//p[@class='item-msg error'][contains(text(),'currently out of stock')] | //span[@class='error-msg'] [contains(text(),'The requested quantity is not available')]"),
				"Out Of Stock Message");
	}

	Locator closelabel() {
		return new Locator(By.id("closelb"), "Close lb popup");
	}

	Locator Checkout_Button() {
		return new Locator(By.xpath("//span[text()='CHECKOUT ']"), "Checkout Button");
	}

	Locator ProceedToPayButton() {
		return new Locator(By.xpath("//a[@class='order_btn']"), "Proceed To Pay Button");
	}

	Locator LoaderIcon() {
		return new Locator(By.className("update_loader"), "Update Icon");
	}

	Locator LoadingBar() {
		return new Locator(By.xpath("//span[@id='loading']"), "Loading Bar");
	}

	Locator exceedQuantityMessageinBag() {
		return new Locator(By.xpath("//p[@id='exceed_message'][contains(text(),'5 quantities')]"),
				"Exceed Quantity Message");
	}

	Locator productInBag() {
		return new Locator(By.id("product_block"), "product in bag");

	}

	Locator ProductRowsInBag() {

		return new Locator(By.xpath("//form[@id='mobile-checkout-item']/div[@class='content_wrapper']"),
				"Product Row in Bag ");
	}

	Locator ServiceRowsInBag() {
		return new Locator(By.xpath("//tr[@id='product_block']"), "Services In Bag");
	}

	public Locator BackMenuCart() {
		return new Locator(By.id("back_button"), "Back Menu Of Cart");
	}

	Locator ProductsInBagsLocator() {
		return new Locator(
				By.xpath("//form[@id='mobile-checkout-item']/div//div[@class='right_amt']/preceding-sibling::img"),
				"products in bag");
		/*
		 * return new Locator( By.xpath(
		 * "//td[@class='last_col']/span/img[@title='Remove item'][not(contains(@style,'display:none'))]"
		 * ), "Remove Icons");
		 */
	}


	Locator ExtendedRemoveProdutFromBagLocator() {
		return new Locator(By.xpath("//ancestor::span/img[@title='Remove item']"), "Remove Button Locator");

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
	Locator EmptyCartLocatorReact() {
		return new Locator(By.xpath("//*[@class='remove-product']"), "Remove cart");
	}

	public void removeAllProduct()
			throws InstantiationException, IllegalAccessException, InterruptedException, Throwable {
		waitUntilDisplayed(BackMenuCart(), 10);

		int count = getWebElements(ProductsInBagsLocator()).size();

		for (int i = 0; i < count; i++) {
			waitUntilDisappear(LoadingBar());
			List<WebElement> removableProducts = getWebElements(ProductsInBagsLocator());
			for (WebElement removeProduct : removableProducts) {
				click(removeProduct, "Remove button of item : " + i);
				break;
			}
		}
		exitEmptyCart();
	}

	private void exitEmptyCart() throws Throwable {
		waitUntilDisplayed(EmptyCartLocator(), 10);
		bringElementIntoView(EmptyCartHomeButtonLocator());
		click(EmptyCartHomeButtonLocator());
		waitUntilDisappear(EmptyCartHomeButtonLocator());
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
			 * "//img[@id='Sym'][@class='update_p_qty'][not(contains(@style,'hidden'))]" ));
			 * quantityPlus.click();
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

	public boolean isProductPresentInBag(ProductDetailData productDetailData) throws Throwable, Throwable {
		boolean isProductPresent = false;
		closeGetAppPopUp();
		goToCart();
		closeGetAppPopUp();
		if (isPresent(EmptyCartLocator())) {
			exitEmptyCart();
			return false;
		} else {
			String productName = productDetailData.getProductName();
			System.out.println(productName.replaceAll("\\(.*?\\)", ""));
			int itemsInCart = getWebElements(ProductRowsInBag()).size();
			for (int i = 1; i <= itemsInCart; i++) {
				Locator productNameLocator = CartProductLocator(i);

				String ProductNameFromUI = getText(productNameLocator).split("-")[0];
				System.out.println(ProductNameFromUI.replaceAll("\\(.*?\\)", ""));
				String name1= ProductNameFromUI.replaceAll("\\(.*?\\)", "");
				System.out.println(productName.replaceAll("\\(.*?\\)", ""));
				String name2= ProductNameFromUI.replaceAll("\\(.*?\\)", "");
				System.out.println(productName.replaceAll("\\(.*?\\)", ""));
				String name3= ProductNameFromUI.replaceAll("\\(.*?\\)", "");
				System.out.println(ProductNameFromUI.replaceAll("\\(.*?\\)", ""));
				String name4= ProductNameFromUI.replaceAll("\\(.*?\\)", "");
				if (productName.contains("...")) {
					String test = productName.replace("...", "");
					System.out.println(test);
					String test1=ProductNameFromUI.replace("...", "");
					System.out.println(test1);
					if (ProductNameFromUI.contains(test) || productName.contains(test1)) {
						isProductPresent = true;
						break;
					}
				} else if (name1.contains(name2)||name3.contains(name4)) {
					isProductPresent = true;
					break;

				}

			}
		}

		if (waitUntilDisplayed(BackMenuCart(), 3)) {
			click(BackMenuCart());
			waitUntilDisappear(BackMenuCart());
		}
		return isProductPresent;
	}
	
	
	public boolean isProductPresentInBagNykaaMan(ProductDetailData productDetailData) throws Throwable, Throwable {
		boolean isProductPresent = false;
		try {
		closeGetAppPopUp();
		}
		catch(Exception e)
		{
			
		}
		goToCart();
		closeGetAppPopUp();
		if (isPresent(EmptyCartLocator())) {
			exitEmptyCart();
			return false;
		} else {
			String productName = productDetailData.getProductName();
			System.out.println(productName.replaceAll("\\(.*?\\)", ""));
			int itemsInCart = getWebElements(ProductRowsInBag()).size();
			for (int i = 1; i <= itemsInCart; i++) {
				Locator productNameLocator = CartProductLocator(i);

				String ProductNameFromUI = getText(productNameLocator).split("-")[0];
				System.out.println(ProductNameFromUI.replaceAll("\\(.*?\\)", ""));
				String name1= ProductNameFromUI.replaceAll("\\(.*?\\)", "");
				System.out.println(productName.replaceAll("\\(.*?\\)", ""));
				String name2= ProductNameFromUI.replaceAll("\\(.*?\\)", "");
				System.out.println(productName.replaceAll("\\(.*?\\)", ""));
				String name3= ProductNameFromUI.replaceAll("\\(.*?\\)", "");
				System.out.println(ProductNameFromUI.replaceAll("\\(.*?\\)", ""));
				String name4= ProductNameFromUI.replaceAll("\\(.*?\\)", "");
				if (productName.contains("...")) {
					String test = productName.replace("...", "");
					System.out.println(test);
					String test1=ProductNameFromUI.replace("...", "");
					System.out.println(test1);
					if (ProductNameFromUI.contains(test) || productName.contains(test1)) {
						isProductPresent = true;
						break;
					}
				} else if (name1.contains(name2)||name3.contains(name4)) {
					isProductPresent = true;
					break;

				}

			}
		}

		if (waitUntilDisplayed(BackMenuCart(), 3)) {
			click(BackMenuCart());
			waitUntilDisappear(BackMenuCart());
		}
		return isProductPresent;
	}

	public boolean isServicePresentInBag(BeautyServiceData beautyServiceData) throws Throwable, Throwable {
		boolean isServicePresent = false;
		String serviceName = beautyServiceData.getServicename();
		List<WebElement> servicerow = getWebElements(ServiceRowsInBag());
		for (WebElement service : servicerow) {
			Locator ServieListBagLocator = new Locator(By.xpath(getAbsoluteXPath(service)), "service List ");
			Locator serviceNameLocator = ServieListBagLocator.concatLocator(extendedProductNameLocator());

			String ServiceNameFromUI = getText(serviceNameLocator);
			if (ServiceNameFromUI.contains("...")) {
				if (serviceName.contains(ServiceNameFromUI.replace("...", ""))) {
					isServicePresent = true;
					break;
				}
			} else if (serviceName.equalsIgnoreCase(ServiceNameFromUI)) {
				isServicePresent = true;
				break;

			}

		}

		if (waitUntilDisplayed(BackMenuCart(), 3)) {
			click(BackMenuCart());
			waitUntilDisappear(BackMenuCart());
		}
		return isServicePresent;
	}

	public boolean isComboProductsPresentInBag(List<ProductDetailData> comboProductsDetail) throws Throwable {
		boolean isIntheBag = false;
		List<Boolean> isProductPresent = new ArrayList<Boolean>();

		for (ProductDetailData comboProduct : comboProductsDetail) {
			isProductPresent.add(isProductPresentInBag(comboProduct));

		}
		if (isProductPresent.contains(false)) {
			isIntheBag = false;
		} else {

			isIntheBag = true;
		}
		return isIntheBag;
	}

	public boolean isInstockMessageDisplay() throws Throwable, IllegalAccessException {
		HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(driver);
		if (waitUntilDisplayed(HeaderBar_Mobile.CartLocator(), 4)) {
			click(HeaderBar_Mobile.CartLocator());
			waitUntilDisplayed(productInBag(), 5);
			return waitUntilDisplayed(inStockMessage(), 3);
		} else {
			return waitUntilDisplayed(inStockMessage(), 3);
		}

	}

	public void checkOut() throws InterruptedException, InstantiationException, IllegalAccessException {

		if (!waitUntilDisplayed(BackMenuCart(), 3)) {
			goToCart();
		}
		if (isPresent(ProceedToPayButton())) {
			click(ProceedToPayButton());
		} else {
			click(Checkout_Button());

			CheckOutPage_Mobile checkoutPage = new CheckOutPage_Mobile(driver);
			checkoutPage.waitUntilDisappear(checkoutPage.checkoutPageLoader());
		}
	}

	public boolean ApplyCoupon(CheckoutData checkoutData) throws Throwable, Throwable {
		EnterValue(CouponCodeTextBox(), checkoutData.getCouponcode());
		click(ApplyCouponButton());
		return waitUntilDisplayed(CancelCouponbutton(), 20);

		// return getShoppingTableData("Coupon Discount");

	}

	public boolean CancelCoupon() throws Throwable, Throwable {
		click(CancelCouponbutton());
		Thread.sleep(5000);
		return waitUntilDisplayed(ApplyCouponButton(), 30);
	}

	public String getShoppingTableData(String Rowname) throws Throwable, Throwable {
		waitUntilDisappear(LoaderIcon());
		List<WebElement> LeftColumns = getWebElements(ShoppingCartTableLeftColumn());
		Locator rightColumn = null;
		for (WebElement column : LeftColumns) {
			Locator leftColumnLocator = new Locator(By.xpath(getAbsoluteXPath(column))," Column value" );
			String value = getText(leftColumnLocator);
			rightColumn = leftColumnLocator.concatLocator(ShoppingCartTableRightColumn());
			getText(rightColumn);
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

		Locator removeProductLocator = new Locator(By.xpath(getAbsoluteXPath(removableProducts.get(0))), "Product");
		Locator removeLocator = removeProductLocator.concatLocator(ExtendedRemoveProdutFromBagLocator());
		click(removeLocator);
		// waitUntilDisappear(removeLocator);
		removableProducts = getWebElements(ProductsInBagsLocator());

		click(BackMenuCart());
		waitUntilDisappear(BackMenuCart());

	}

	public void goToCart() {
		try {
			if(waitUntilDisplayed(CartLocator(), 5))
			{
				bringElementIntoView(CartLocator());
				click(CartLocator());
			}
		} catch (Throwable e) {
		}

	}

	public void gotoHome(){
		try{
			if(waitUntilDisplayed(BackMenuCart(),5)) {
				click(BackMenuCart());
			}
		}catch (Throwable e){

		}
	}
	
	public boolean isProductPresentInBag() throws Throwable, Throwable {
		try {
			goToCart();
			if (isPresent(ProductsInBagsLocator())) {
				return true;
			} else {
				return false;
			}
		} catch (Throwable e) {
			return false;
		}

	}
	
	public boolean isProductPresentInBagReact() throws Throwable, Throwable {
		try {
			
			if (isPresent(EmptyCartLocatorReact())) {
				
				return true;
			} else {
				return false;
			}
		} catch (Throwable e) {
			return false;
		}

	}

}
