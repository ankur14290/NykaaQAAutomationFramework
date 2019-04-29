package Mobile_Pages_Nykaa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DataNykaa.AccountData;
import DataNykaa.CheckoutData;
import DataNykaa.ProductDetailData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Locator;

public class CheckOutPage_Mobile extends BrowserAction {

	public CheckOutPage_Mobile(WebDriver browser) {
		this.driver = browser;
	}

	private Locator DeliveryAddressLocator(String id) {
		return new Locator(By.xpath(
				"//div[@id='savedAddressList']"),
				"Address");
	}

	private Locator DeliveryAddressLocator() {
		return new Locator(By.xpath(
				"//div[text()='Delivery Address']/following-sibling::div[@class='overview']//input"),
				"Selected Address");
	}

	private Locator AddreesId(String id) {
		return new Locator(By.xpath("//div[@id='sa_item" + id + "']/div[@class='sa_item_green_btn']"), "Address");
	}

	Locator CouponCodeTextBox() {
		return new Locator(By.id("coupon_code"), "Coupon Code TextBox");
	}

	Locator LoginStepComplete() {
		return new Locator(
				By.xpath("//div[@id='steponetitle'][@class='content_row_title_container steponetitleBlock complete']"),
				"Login Step Completed sign");

	}

	Locator ApplyReedemButton() {
		return new Locator(By.xpath("//div[@class='buttons-set apply_reward div_alignment']"), "Apply Reedem Button");
	}

	Locator removeReedemButton() {
		return new Locator(By.xpath("//div[@onclick='removeRewards()']"), "Apply Reedem Button");
	}

	Locator ApplyCouponButton() {
		return new Locator(By.xpath("//button[@title='Apply Coupon']"), "Apply Coupon Button");
	}

	Locator ShoppingCartTable() {
		return new Locator(By.xpath("//table[@id='shopping-cart-totals-table'])"), "Shopping Cart Table");
	}

	Locator ShoppingCartTableLeftColumn() {
		return new Locator(By.xpath("//table[@id='shopping-cart-totals-table']/descendant::td[@class='co-left']"),
				"Left columns");
	}

	Locator ShoppingCartTableRightColumn() {
		return new Locator(By.xpath("/following-sibling::td[@class='co-right pink']/span"), "Right columns");
	}

	Locator PartialPaymentCheckbox() {
		return new Locator(By.xpath("//input[@name='partial_payment']"), "Partial Payment Button");
	}

	Locator POVAppliedFlag() {
		return new Locator(By.xpath("//input[@id='pov_applied'][@value='1']"), "POV Applied");
	}

	private Locator AddressSelectedIcon() {
		return new Locator(By.id("steptwotext"), "Address Selection Icon");
	}

	private Locator Billing_street() {
		return new Locator(By.id("address_street"), "Shiping street In Address");
	}

	private Locator Continue() {
		return new Locator(By.id("isregisteredbtn"), "Continue Button");
	}

	Locator ContinueRegstration() {
		return new Locator(By.id("altcontinuebtn"), "Registration Continue button");
	}

	private Locator submitButton() {
		return new Locator(By.id("btn_add_sa_submit_1"), "Submit button");
	}

	private Locator ContinueAsGuest() {
		return new Locator(By.id("continueasguestbtn"), "Continue as guest Button");
	}

	Locator debitCardOption() {
		return new Locator(By.xpath("//div[@class='payment-method-name'][contains(text(), 'CREDIT/DEBIT')]"), "Debit Card Option");
	}

	Locator simplePayOption() {
		return new Locator(By.id("method_simpl"), "simple Pay Option 'of 'Payment");
	}

	Locator simplePayPayLaterButton() {

		return new Locator(By.id("simpl-button"), "Simple Button");
	}

	Locator iframeSimple() {
		return new Locator(By.id("simpl-iframe"), "Simple I frame");
	}

	Locator simpleOtpText() {
		return new Locator(By.xpath("//div[@class='bnl__otp__get-started']/p"), "Otp Mobile Text");
		// return new Locator(By.xpath("//div[@class='otp__mobile']"), "Otp
		// Mobile textArea");
	}

	Locator iframeOTPsimple() {
		return new Locator(By.xpath("//iframe[@id='i']"), "OTP box iframe");
	}

	Locator DebitCardNoText() {
		return new Locator(By.xpath("//div[@id='credit_options']/input[@id='credit_card_no']"), "Debit card number");
	}

	public Locator BackButton() {
		return new Locator(By.xpath("//i[@class='back-arrow']"), "Back Button");
	}

	Locator DomesticLoader() {
		return new Locator(By.xpath("//*[@class='isDomestic_load_message']/img"), "Loader");
	}

	Locator DebitCardName() {
		return new Locator(By.xpath("//div[@id='credit_options']/input[@id='credit_card_name']"), "Debit card name");
	}

	Locator DebitCardExp() {
		return new Locator(By.xpath("//div[@id='credit_options']//input[@id='credit_card_exp']"), "Debit card expiry");
	}

	Locator CashOnDeliveryMethod() {
		return new Locator(By.xpath("//div[@class='payment-method-name'][text()='CASH ON DELIVERY']"), "Cash On Delivery Method");
	}

	Locator COD_placeOrderBtn() {
		return new Locator(By.xpath("//div[@class='cod-payment-inner-section']//button[@class='btn payment-btn margin-adjust ']"), "Cod Place Order");
	}

	Locator RewardPointText() {
		return new Locator(By.xpath("//div[@class='redeem_points div_alignment rewards_point_cart']"),
				"Reward point Viewtext");

	}

	Locator DebitCard_ccvText_Locator() {

		return new Locator(By.xpath("//div[@id='credit_options']//input[@id='credit_card_ccv']"), "Debit card cvv");
	}

	Locator debitCardButn_Locator() {

		return new Locator(By.id("creditCardButn"), "Debit Card button ");

	}

	Locator COD_Confirmation() {
		return new Locator(By.xpath("//div[@class='title'][contains(text(),'Order Confirmed')]"),
				"Confirmation Cod Page");
	}

	Locator checkoutPageLoader() {
		return new Locator(By.xpath("//div[@id='pageblock'][contains(@style,'block')]"), "CheckOut Page Loader");
	}

	Locator checkoutLoader() {
		return new Locator(By.xpath("//span[@id='loading']"), "CheckOut Loader");
	}

	private Locator Checkout_Button() {
		return new Locator(By.xpath("//span[text()='CHECKOUT ']"), "Checkout Button");
	}

	private Locator Close_Button() {
		return new Locator(By.xpath("//div[@class='close']|//a[@class='close']"), "Close Button");
	}

	private Locator Billing_Address() {
		return new Locator(By.id("billing_email"), "Billing Address");
	}

	private Locator Billing_FirstName() {
		return new Locator(By.id("billingfirstname"), "Billing Firstname");
	}

	private Locator Billing_LastName() {
		return new Locator(By.id("billinglastname"), "Billing Lastname");
	}

	private Locator Billing_telephone() {
		return new Locator(By.id("billing_telephone"), "Billing Telephone");

	}

	Locator LoaderIcon() {
		return new Locator(By.id("ajax_loading_img"), "Loader Icon");
	}

	private Locator Billing_PostCode() {
		return new Locator(By.id("billing_pincode"), "Billing Postcode");
	}

	private Locator Billing_Email() {
		return new Locator(By.id("billing_email"), "Billing Email");
	}

	Locator NetBankingMethod() {
		return new Locator(By.id("payucheckout_shared_options"), "NetBankingMethod");

	}

	Locator NetBankingOption() {
		return new Locator(By.xpath("//h2[contains(@onclick,'payucheckout_shared')]"), "NetBanking Option");

	}

	Locator NetBankingDropdown() {
		return new Locator(By.id("netbanking_select"), "NetBanking Dropdown");

	}

	Locator proceedToNetBanking() {

		return new Locator(By.xpath("//input[@id='proceedNetBankingBut']"), "Pay Now");
	}

	Locator ProceedToPay() {

		return new Locator(By.xpath("//a[@class='order_btn']"), "Proceed To Pay");
	}
	Locator MorePaymentMethods() {
		return new Locator(By.xpath("//div[@class='payment-method-name'][text()='MORE PAYMENT METHODS']"),"More Payement OPtions");
	}

	Locator Payoumoney_Methode() {
		return new Locator(By.id("method_payumoney"), "Payyoumoney Method");
	}

	Locator PayuMoneProceedToPayment() {

		return new Locator(By.xpath(
				"//div[@id='payumoney_options']/descendant::div[@class='payment_button']/input[@value='Proceed To Payment ']"),
				"Proceed To Payment");
	}

	Locator WalletProceedToPayment() {

		return new Locator(By.xpath("//input[@id='wallets_btn']"), "proceed To Pay");
	}

	Locator registerNow() {
		return new Locator(By.id("registernowbtn"), "register now Button");
	}

	Locator passwordText() {
		return new Locator(By.id("billing_customer_password"), "Password text");
	}

	Locator ConfirmPassword() {
		return new Locator(By.id("billing_confirm_password"), "Confirm Password");
	}

	Locator SimpleBrowserText() {
		return new Locator(By.xpath("//div[@id='simpl-backdrop-text'][contains(text(),'Simpl browser window ')]"),
				"simple browser alert");
	}

	Locator PayoumoneyIcon() {

		return new Locator(By.xpath("//div[@class='pymnt_main']/descendant::a/img[@title='PayUMoney Payment']"),
				"Payoumoney Icon");
	}

	Locator WalletMethod() {

		return new Locator(By.xpath("//h2[contains(@onclick,'wallets')]"), "Wallet Method");
	}

	Locator PayTMradioButton() {
		return new Locator(By.xpath("//input[@name='wallets_options_radio'][@value='paytm']"), "PayTm Radio Button");
	}

	Locator mobikwikRadioButton() {
		return new Locator(By.id("mobikwik_radion_btn"), "Mobikwik radio button");
	}

	Locator mobikwikLoginText() {
		return new Locator(By.id("logininput"), "Mobikwik login");
	}

	Locator PayByWallet() {
		return new Locator(By.id("wallets_btn"), "Proceed to payment");
	}

	Locator PayTMLogin() {

		return new Locator(By.xpath("//input[@name='username']"), "PayTM Login Popup");
	}

	Locator PayTMLoginForm() {
		return new Locator(By.id("login-iframe"), "login form");
	}

	Locator subTotalValueview() {
		return new Locator(By.xpath("//div[text()='Subtotal:']/following-sibling::div"), "subtotal value");
	}

	Locator ShippingValueView() {
		return new Locator(By.xpath("//div[contains(text(),'Shipping ')]/following-sibling::div"), "Shipping Value");
	}

	Locator RewardPointValueView() {

		return new Locator(By.xpath("//div[@id='totals_div']/descendant::div[@class='no-dis']"),
				"Reward point value view");

	}

	Locator OrderTotalValueView() {
		return new Locator(By.id("grand_total_no_cod"), "Order Total value");
	}

	Locator MultiCartItems() {
		return new Locator(By.xpath("//div[@class='product_txt']"), "Cart Items");

	}

	Locator ExtendedTitleofCartItem() {
		return new Locator(By.xpath("/div[@class='head_text']"), " name Of Product");
	}

	Locator ExtendedPrizeofCartItem() {

		return new Locator(By.xpath("/following-sibling::div/div[@class='right_amt']"), "prize of Cart Item");
	}

	Locator ExtendedQuantityOfCartItem() {
		return new Locator(By.xpath("//option[@value='1']"), "Extended Quatinty");
	}

	public void fillBillingAddress(CheckoutData checkOutData) throws Throwable {
		EnterValue(Billing_FirstName(), checkOutData.getFirstname());
		EnterValue(Billing_LastName(), checkOutData.getLastname());
		EnterValue(Billing_telephone(), checkOutData.getTelephone());
		EnterValue(Billing_Email(), checkOutData.getUsername());
		EnterValue(Billing_PostCode(), checkOutData.getPostcode());
		EnterValue(Billing_street(), checkOutData.getShipping_address1());
	}

	public void checkOut() throws InterruptedException, InstantiationException, IllegalAccessException {
		if (isNotificationAdvertisePresent(driver)) {
			click(Close_Button());
			driver.switchTo().defaultContent();
		}
		Thread.sleep(2000);
		click(Checkout_Button());

	}

	public void savedAddressSelect() throws Throwable, IllegalAccessException {
		// waitUntilPageBlockDisappear(driver);
		bringElementIntoView(DeliveryAddressLocator("1"));
		Thread.sleep(1000);
		click(DeliveryAddressLocator("1"));
	}

	public void proceedToPay() throws Throwable, IllegalAccessException {
		click(ProceedToPay());
	}

	public boolean isAddressSelected() throws Throwable {
		waitUntilDisplayed(DeliveryAddressLocator(), 3);
		bringElementIntoView(DeliveryAddressLocator());
		System.out.println(getAttribute(DeliveryAddressLocator(), "selected"));
		return (getAttribute(DeliveryAddressLocator(), "selected").equals("true"));
	}

	public boolean savedAddressPresent() throws IllegalArgumentException, IllegalAccessException {
		try {
			return driver.findElement(DeliveryAddressLocator().getBy()).isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
		
	}

	public boolean applyRewardPoint() throws Throwable {
		click(ApplyReedemButton());
		Wait(2);
		waitUntilDisappear(checkoutLoader());
		waitUntilDisplayed(removeReedemButton(), 5);
		return driver.findElement(removeReedemButton().getBy()).isDisplayed();
	}

	public boolean isRewardPointApplied() throws IllegalArgumentException, IllegalAccessException {
		// waitUntilDisplayed(RewardPointText(), 10);
		String rewardPointText = getText(RewardPointText());
		System.out.println(rewardPointText);
		return rewardPointText.contains("Redeemed");

	}

	public void checkoutWithDebitCard(CheckoutData checkoutData) throws Throwable {
		bringElementIntoView(ProceedToPay());
		Thread.sleep(1000);
		click(ProceedToPay());
		waitUntilDisplayed(debitCardOption(), 10);
		if (!isDisplayed(DebitCardNoText())) {
			click(debitCardOption());
		}
		waitUntilDisplayed(DebitCardNoText(), 2);
		EnterValue(DebitCardNoText(), checkoutData.getCardno());
		waitUntilDisappear(DomesticLoader());
		Thread.sleep(2000);
		if (!getAttribute(DebitCardNoText(), "value").replace(" ", "").equals(checkoutData.getCardno())) {
			click(DebitCardNoText());
			ClearValue(DebitCardNoText());
			EnterValue(DebitCardNoText(), checkoutData.getCardno());
			waitUntilDisappear(DomesticLoader());
			Thread.sleep(2000);
		}
		click(DebitCardName());
		EnterValue(DebitCardName(), checkoutData.getCardname());
		EnterValue(DebitCardExp(), checkoutData.getExpDate());
		EnterValue(DebitCard_ccvText_Locator(), checkoutData.getCcv());
		click(debitCardButn_Locator());
		Thread.sleep(2000);
		waitUntilDisappear(checkoutLoader());
	}

	public String checkoutWithSimple() throws Throwable {
		waitUntilDisappear(checkoutPageLoader());
		click(simplePayOption());
		waitUntilDisappear(checkoutPageLoader());
		String mainWindow = driver.getWindowHandle();
		SwitchToFrameIFAvaialble(iframeSimple());
		click(simplePayPayLaterButton());
		waitUntilDisplayed(SimpleBrowserText(), 5);
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			if (!window.equalsIgnoreCase(mainWindow)) {

				driver.switchTo().window(window);
				waitForPagetoLoad();
				break;
			}
		}

		SwitchToFrameIFAvaialble(iframeOTPsimple());

		waitUntilDisplayed(simpleOtpText(), 60);
		String OtpText = getText(simpleOtpText());
		System.out.println(OtpText);

		return OtpText;

	}

	public boolean applyPOV() throws Exception, Throwable {
		click(PartialPaymentCheckbox());
		return waitUntilPresent(POVAppliedFlag(), 5);
	}

	public boolean CashOnDelivery() throws Throwable {

		if(isDisplayed(ProceedToPay())) {
			bringElementIntoView(ProceedToPay());
			click(ProceedToPay());
		}
		if(isDisplayed(MorePaymentMethods())){
			click(MorePaymentMethods());
		}
		waitUntilDisplayed(CashOnDeliveryMethod(), 60);
		click(CashOnDeliveryMethod());
		if(!waitUntilDisplayed(COD_placeOrderBtn(), 5))
		{
			click(CashOnDeliveryMethod());
			waitUntilDisplayed(COD_placeOrderBtn(), 5);
		}
		Thread.sleep(1000);
		click(COD_placeOrderBtn());
		waitUntilDisappear(checkoutLoader());
		return waitUntilDisplayed(COD_Confirmation(), 60);

	}

	public boolean newRegister(AccountData accountData) throws Throwable, Throwable, Throwable {
		Random random = new Random(System.currentTimeMillis());
		EnterValue(Billing_Address(),
				accountData.getFirstName() + "." + accountData.getLastName() + random.nextInt() + "@mailinator.com");
		if (!isDisappear(Continue(), 3)) {
			click(Continue());
			waitUntilDisappear(checkoutPageLoader());
			click(registerNow());
			waitUntilDisappear(checkoutPageLoader());
		}
		EnterValue(passwordText(), "Password123");
		EnterValue(ConfirmPassword(), "Password123");
		click(ContinueRegstration());

		return waitUntilDisplayed(LoginStepComplete(), 5);

	}

	public boolean netBankingCheckout() throws Throwable {
		bringElementIntoView(ProceedToPay());
		click(ProceedToPay());
		if (isPresent(DebitCardNoText())) {
			click(debitCardOption());
		}
		Thread.sleep(2000);
		waitUntilDisplayed(NetBankingOption(), 60);
		click(NetBankingOption());
		Thread.sleep(1000);
		// waitUntilDisappear(DomesticLoader());
		waitUntilDisplayed(NetBankingDropdown(), 5);
		Thread.sleep(1000);
		bringElementIntoView(NetBankingDropdown());
		selectFromDropDownByValue(NetBankingDropdown(), "HDFB");
		waitUntilDisappear(DomesticLoader());
		Thread.sleep(2000);
		waitUntilDisplayed(proceedToNetBanking(), 5);
		click(proceedToNetBanking());
		waitUntilDisappear(checkoutLoader());
		BankingPage_Mobile bankingPage = new BankingPage_Mobile(driver);
		SwitchToFrameIFAvaialble(bankingPage.HDFCFrame());
		boolean netbankLoginavailable = waitUntilDisplayed(bankingPage.NetBankingCustomerLogin(), 60);
		switchToDefaultContent();
		return netbankLoginavailable;

	}

	public boolean payumoneyCheckout() throws Throwable {
		waitUntilDisappear(checkoutPageLoader());
		waitUntilPageBlockDisappear(driver);
		click(Payoumoney_Methode());
		waitUntilPageBlockDisappear(driver);
		click(PayuMoneProceedToPayment());
		return waitUntilDisplayed(PayoumoneyIcon(), 60);

	}

	public boolean MobikwikWalletCheckout() throws Throwable {
		bringElementIntoView(ProceedToPay());
		click(ProceedToPay());
		waitUntilDisplayed(WalletMethod(), 60);
		bringElementIntoView(WalletMethod());
		ScrollDown("50");
		waitUntilDisappear(DomesticLoader());
		Thread.sleep(2000);
		click(WalletMethod());
		waitUntilDisappear(DomesticLoader());
		Thread.sleep(2000);
		waitUntilDisplayed(mobikwikRadioButton(), 60);
		click(mobikwikRadioButton());
		click(WalletProceedToPayment());
		waitUntilDisappear(checkoutLoader());
		return waitUntilDisplayed(mobikwikLoginText(), 60);

		/*
		 * boolean frameAvailable = SwitchToFrameIFAvaialble(PayTMLoginForm());
		 * switchToDefaultContent(); return frameAvailable;
		 */

	}

	public void fillbillingEmailIDAndContinueAsGuest(CheckoutData checkoutData) throws Throwable, Throwable {
		EnterValue(Billing_Address(), checkoutData.getUsername());
		if (!isDisappear(Continue(), 3)) {
			click(Continue());
			waitUntilDisappear(checkoutPageLoader());
		}
		click(ContinueAsGuest());
		waitUntilDisappear(checkoutPageLoader());
	}

	public boolean fillNewAddressLoginUser(CheckoutData checkOutData) throws Throwable {
		/*
		 * EnterValue(Billing_Address(), checkOutData.getUsername()); click(Continue());
		 * click(ContinueAsGuest()); waitUntilDisappear(checkoutPageLoader());
		 */
		EnterValue(Billing_FirstName(), checkOutData.getFirstname());
		EnterValue(Billing_LastName(), checkOutData.getLastname());
		EnterValue(Billing_telephone(), checkOutData.getTelephone());
		EnterValue(Billing_PostCode(), checkOutData.getPostcode());
		EnterValue(Billing_street(), checkOutData.getShipping_address1());
		waitUntilDisappear(checkoutPageLoader());
		return waitUntilDisplayed(ProceedToPay(), 10);
	}
	
	public boolean fillNewAddressGuest(CheckoutData checkOutData) throws Throwable {
		/*
		 * EnterValue(Billing_Address(), checkOutData.getUsername()); click(Continue());
		 * click(ContinueAsGuest()); waitUntilDisappear(checkoutPageLoader());
		 */
		EnterValue(Billing_FirstName(), checkOutData.getFirstname());
		EnterValue(Billing_LastName(), checkOutData.getLastname());
		EnterValue(Billing_telephone(), checkOutData.getTelephone());
		EnterValue(Billing_Email(), checkOutData.getUsername());
		EnterValue(Billing_PostCode(), checkOutData.getPostcode());
		EnterValue(Billing_street(), checkOutData.getShipping_address1());
		waitUntilDisappear(checkoutPageLoader());
		return waitUntilDisplayed(ProceedToPay(), 10);
	}

	public List<ProductDetailData> FetchProductDetail() throws IllegalArgumentException, IllegalAccessException {

		List<ProductDetailData> CartItemsDetail = new ArrayList<ProductDetailData>();
		List<WebElement> multiCartItems = getWebElements(MultiCartItems());
		for (WebElement CartItem : multiCartItems) {
			ProductDetailData productDetailData = new ProductDetailData();
			Locator SingleCartItem = new Locator(By.xpath(getAbsoluteXPath(CartItem)), "Cart Item");
			Locator CartItemName = SingleCartItem.concatLocator(ExtendedTitleofCartItem());
			Locator CartPrizeName = SingleCartItem.concatLocator(ExtendedPrizeofCartItem());
			Locator CartProductQty = SingleCartItem.concatLocator(ExtendedQuantityOfCartItem());
			String itemNameTemp = getText(CartItemName);
			String itemName = itemNameTemp.split(" -")[0];
			productDetailData.setProductName(itemName);
			productDetailData.setProductPrize(getText(CartPrizeName).replaceAll("\\D+", ""));
			productDetailData.setProductQuantity(getText(CartProductQty).replaceAll("\\D+", ""));
			CartItemsDetail.add(productDetailData);

		}
		return CartItemsDetail;
	}

	public CheckoutData FetchOrderDetail(CheckoutData checkoutData) throws Throwable, Throwable

	{

		checkoutData.setSubtotal(getText(subTotalValueview()).replaceAll("\\D+", ""));
		checkoutData.setShipping(getText(ShippingValueView()));
		checkoutData.setOrdertotal(getText(OrderTotalValueView()).replaceAll("\\D+", ""));
		if (waitUntilDisplayed(RewardPointValueView(), 3)) {
			checkoutData.setRewardpoint(getText(RewardPointValueView()));
		}
		return checkoutData;

	}

	private boolean isNotificationAdvertisePresent(WebDriver driver2) {
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		WebDriverWait ww = new WebDriverWait(driver, 5);
		try

		{
			ww.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(
					"//iframe[@id='__ta_notif_frame_0' or @id='webklipper-publisher-widget-container-notification-frame']")));
			return true;
		} // try
		catch (Exception Ex) {
			Ex.printStackTrace();
			return false;
		} // catch
		finally {

			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}

	public void gotoHome(){
		try{
			if(waitUntilDisplayed(BackButton(),5)) {
				click(BackButton());
			}
		}catch (Throwable e){

		}
	}

}
