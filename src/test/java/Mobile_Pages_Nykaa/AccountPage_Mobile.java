package Mobile_Pages_Nykaa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import DataNykaa.ObjectRepository;
import org.hibernate.criterion.Order;
import org.hibernate.hql.internal.ast.tree.IsNotNullLogicOperatorNode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import DataNykaa.AccountData;
import DataNykaa.ProductDetailData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Framework;
import FrameWorkNykaa.Locator;
import FrameWorkNykaa.LoginSync;
import FrameWorkNykaa.TestListner;

import javax.validation.constraints.Null;

public class AccountPage_Mobile extends BrowserAction {

	public AccountPage_Mobile(WebDriver driver) {

		this.driver = driver;
	}

	Locator DotMenuLocator() {
		return new Locator(
				By.xpath("//div[contains(@class,'m-header__nav__dot-menu-btn pull-left')]/a/span"),"Dot Menu Locator");
	}

	Locator LoginLink() {
		return new Locator(By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Log In')]"), "Log In link");
	}

	Locator WishListLink() {
		return new Locator(By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'My Wishlist')]"), "WishList link");
	}

	Locator LogOutLink() {
		return new Locator(By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Log Out')]"), "Log Out link");
	}

	Locator ProfileLink() {
		return new Locator(By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'My Profile')]"), "Profile link");
	}
	Locator MyOrderLocator() {
		return new Locator(By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'My Orders')]"), "My Orders Link");
	}


	Locator maleCheckbox() {
		return new Locator(By.xpath("//input[@value='1']"), "Male Checkbox");
	}

	Locator femaleCheckbox() {
		return new Locator(By.id("genderMiss"), "female Checkbox");
	}

	Locator firstName() {
		return new Locator(By.xpath("//input[@id='firstname']"), "First name");
	}

	Locator newRegister() {
		return new Locator(By.partialLinkText("Sign Up"), "New Regsiter");
	}

	Locator continueButton() {
		return new Locator(By.xpath("//button[text()='Continue']"), "Continue Button");
	}


	Locator SignIn() {

		return new Locator(By.partialLinkText("Sign In/Up"), "Sign In/Up");
	}

	Locator FBEmailidText() {
		return new Locator(By.id("email"), "FB Email Id Text");

	}

	Locator CancelOrder() {
		return new Locator(By.xpath("//a[@class='invoice-btn cancle-btn']"), "Cancel Order");
	}

	Locator FBPasswordText() {
		return new Locator(By.id("pass"), "Password Text");
	}

	Locator FBLoginButton() {
		return new Locator(By.id("loginbutton"), "Login Button");
	}

	Locator FBConfirmLoginButton() {
		return new Locator(By.xpath("//button[@name='__CONFIRM__'][@type='submit']"), "Fb Confirm Login Button");
	}

	Locator FirstNameForRegister() {
		return new Locator(By.xpath("//input[@name='firstname']"), "UserName Of Register");
	}

	Locator LoginLoadder() {
		return new Locator(By.xpath("//li[@class='ulogin']//img[@class='loader']"), "UserName Of Register");

	}

	Locator LastNameForRegister() {
		return new Locator(By.id("lastname"), "lastname Of Register");

	}

	Locator StatusOfOrder(String OrderStatus) {
		return new Locator(
				By.xpath("//span[@class='my-order-status status-delivered'][contains(text(),'" + OrderStatus + "')]"),
				OrderStatus + "Status");
	}

	Locator closeIcon() {

		return new Locator(By.id("closelb"), "close Icon");
	}


	Locator ConfirmationPasswordForRegistration() {

		return new Locator(By.xpath("//input[@type='password'][@name='confirmPassword']"), " Confirm Password Textbox");
	}

	Locator SignUpforRegisterButton() {

		return new Locator(By.xpath("//button[contains(text(),'Sign Up')]"), "Sign up button");
	}

	Locator LoaderIcon() {
		return new Locator(By.id("ajax_loading_img"), "Loader Icon");
	}

	Locator UserProfileImage() {
		return new Locator(By.id("user-profile-img"), "User Profile Image");

	}

	Locator IsUserLoginFlag() {

		return new Locator(By.id("isUserLoginFlag"), "IS user Login Flag ");
	}

	Locator LogoutButton() {

		return new Locator(By.partialLinkText("Log Out"), "LogOut Button");
	}

	Locator OrderList() {
		return new Locator(By.xpath("//div[@class='my-orders-lists-wrap']"), "Order List");
	}

	Locator orderDate() {
		return new Locator(By.xpath("//div[@class='pull-right  my-order__order-details-date']"), "Order Date");
	}

	Locator viewDetail() {
		return new Locator(By.xpath("//a[@class='my-order__order-details-btn']"), "View Detail button");
	}

	private Locator Email_textbox() {
		return new Locator(By.xpath("//input[@type='email']"), "Email Textbox");
	}

	private Locator Password_textbox() {
		return new Locator(By.xpath("//input[@type='password'][@name='password']"), "Password Textbox");
	}

	private Locator Continue_Button() {
		return new Locator(By.xpath("//button[contains(text(),'Continue')]"), "Continue Button");
	}

	private Locator Signin_Button() {
		return new Locator(By.xpath("//button[contains(text(),'Sign In')]"), "Signin Button");
	}

	private Locator LogoutMessage() {
		return new Locator(By.xpath("//h1[text()='You are now logged out']"), "Logout Message");

	}

	private Locator FbContinueWithNewLogin() {
		return new Locator(By.xpath("//a[text()='Create New Account']"), "Create new Account");
	}

	Locator SignInWithFacebookButton() {
		return new Locator(By.xpath("//form[@id='login-form']//li[@class='first']/button"),
				"Sign in With Facebook Button");
	}

	Locator SignInWithGooglePlusButton() {
		return new Locator(By.xpath("//a[@class='loginBtn loginBtn--google']"), "Sign in With Google");
	}

	Locator GoogleEmailidField() {

		return new Locator(By.id("identifierId"), "Google Email id Field");
	}

	Locator Next() {
		return new Locator(By.xpath("//span[text()='Next']"), "Next button");
	}

	Locator GooglePasswordText() {
		return new Locator(By.xpath("//input[@name='password']"), "google Password Text Field");
	}

	Locator PasswordNExt() {
		return new Locator(By.id("passwordNext"), "Password Next");
	}

	Locator CityTextBox() {
		return new Locator(By.id("knowledgeLoginLocationInput"), "City Text box");
	}

	Locator CityLoginNext() {
		return new Locator(By.id("next"), "Next");
	}

	Locator GoogleSignInButton() {
		return new Locator(By.id("signIn"), "Google Sign in Button");
	}

	Locator approveGoogle() {
		return new Locator(By.id("submit_approve_access"), "Allow button");
	}

	Locator UserNameValue() {
		return new Locator(By.xpath("//a[@id='userAccount']/descendant::span"), "Logged in UserName");
	}

	Locator usersContentNavigation() {
		return new Locator(By.xpath("//span[@class='mkr mkr-more']"), "users Content Icon");
	}

	Locator myProfileNavigation() {
		return new Locator(By.xpath("//a[contains(text(),'My Profile')]"), "My Profile Navigation");
	}

	Locator MyWishlistNavigation() {
		return new Locator(By.xpath("//span[@class='nav_icon wishlist']"), "My wishlist Icon");
	}

	Locator googleCityLoggedInFromOption()
	{
		return new Locator(By.xpath("//div[text()='Enter the city you usually sign in from']"),"Enter the city you usually sign in from");
	}

	/*Locator myOrderLoader(){
		return new Locator(By.xpath("//div[@class='myorder-loader-css text-center']"),"Loader");
	}*/

	Locator noOrder(){
		return new Locator(By.xpath("//h4[contains(@class,'no-order')]"),"No Order");
	}
	Locator OrderList_New(){
		return new Locator(By.xpath("//div[contains(@class,'m-order-details-container')][1]"),"Order List");
	}
	
	Locator OrderListNykaaMan(){
		return new Locator(By.xpath("//div[@class='my-order-m-order-details-container']/div/div[3]//span"),"Order List");
	}

	Locator extendedOrderLocator(){
		return new Locator(By.xpath("//p[contains(@class,'m-order-id')][not(contains(text(),'ORDER'))]"),"Order ID");
	}

	Locator ProductList(){
		return new Locator(By.xpath("//ul[contains(@class,'my-order-m-shipment-items-list')]/a"),"Product List");
	}
	Locator ProductListOrderListing(){
		return new Locator(By.xpath("//div[contains(@class,'slick-slide slick-active pos-relative')]"),"Product List From My Order List");
	}

	Locator ProductName_extended()
	{
		return new Locator(By.xpath("//p[contains(@class,'my-order-m-shipment-item-name')]"),"Product Name");
	}

	Locator Price_extended()
	{
		return new Locator(By.xpath("//p[contains(@class,'pull-right')]/span"),"Product Price");
	}

	Locator ItemCancelPopUp()
	{
		return new Locator(By.xpath("//div[contains(@class,'my-order-m-cancel-item-container')]"),"Product Price");
	}

	Locator ItemList()
	{
		return new Locator(By.xpath("//div[@class='my-order-m-product-item-box']"),"Product list");
	}

	Locator itemCheckboxes()
	{
		return new Locator(By.xpath("//div[contains(@class,'control__indicator')]"),"Product Checkboxes");
	}

	Locator ItemConfirmCancelButton(){
		return new Locator(By.xpath("//div[contains(@class,'cancel-item-btn my-order')]"),"Cancel Items Button");
	}

	Locator yesButton(){
		return new Locator(By.xpath("//div[contains(@class,'cancel-confirmation-modal')]//button[contains(@class,'yes')]"),"Yes Button");
	}

	Locator cancelSuccessfulMsg(){
		return new Locator(By.xpath("//div[@class='my-order-m-cancel-confirmation-msg']"),"Cancel Successful message");
	}
	Locator cancelNotifyMsg(){
		return new Locator(By.xpath("//p[contains(text(),'Your cancellation request has been taken')]"),"cancel Message");
	}

	Locator doneButton(){
		return new Locator(By.xpath("//button[contains(@class,'my-order-m-completed-btn')]"),"Done Button");
	}

	Locator orderDate_New(){
		return new Locator(By.xpath("/following-sibling::p/span[2]"),"Order Date");
	}

	Locator StatusOfOrder_New(String OrderStatus)
	{
		return new Locator(By.xpath("//p[contains(@class,'m-orange text')][contains(text(),'"+OrderStatus+"')]"),OrderStatus +"Status");
	}

	Locator CancelOrderLink()
	{
		return new Locator(By.xpath("//button[@class='my-order-m-order-details-btn my-order-m-red my-order-m-font-Semibold']"),"Cancel order");
	}

	Locator CancelItemsLink()
	{
		return new Locator(By.xpath("//p[contains(@class,'my-order-m-cancel-items-link')]"),"Cancel order");
	}

	Locator CancelReasonDropDown(){
		return new Locator(By.xpath("//select[contains(@id,'select_cancel')]|//div[@class='my-order-m-reason-box-border expand']//select[@class='reason-select-box']"),"Cancel Reason Drop Down");
	}

	Locator CancelReasonDropdown_OrderDetails() {
		return new Locator(By.xpath("//div[@class='cancel-confirmation-modal order-cancellation']//select[@class='reason-select-box']"),"Reason for cancel Order");
	}

	Locator ConfirmCancellationLocator(){
		return new Locator(By.xpath("//button[contains(text(),'Confirm Cancellation')] | //button[contains(text(),'yes')]"),"Confirm Cancellation button");
	}

	Locator OrderID(){
		return new Locator(By.xpath("//div[@class='pull-left my-order__order-number']"),"Order ID");
	}

	public Locator OrderId_conformationMsg() {
		return new Locator(By.xpath("//div[@class='content_inner']/div/div[@class='thanks-div']"), "OrderId_ConformationMesg");
	}

	Locator StatusLocator()
	{
		return new Locator(By.xpath("//span[contains(@class,'my-order-status status-delivered')]"),"Status");
	}
	Locator Item_selection_in_MyOrder(){
		return new Locator(By.xpath("//div[@class='control__indicator'][1]"), "Item Selection in My order");
	}

	Locator confirmationButton()
	{
		return new Locator(By.xpath("//a[text()='OK']"),"OK button");
	}
	
	Locator cancellationMessageLocator()
	{
		return new Locator(By.xpath("//div[@class=' mm-message mm-message-open']"),"Cancellation Message");
	}

	Locator CancelNotification(){
		return new Locator(By.xpath("//div[@class='mm-text-container']//span[contains(text(), 'Your order has been successfully cancelled')]"), "Cancel Notification");
	}
	Locator ItemNameOrderListing(){
		return new Locator(By.xpath("/following-sibling::div/div/img[@alt]"),"Item name");
	}

	/**
	 * @param accountData
	 * @return String Username
	 * @throws IllegalAccessException
	 * @throws Throwable
	 */
	public String signInWithYourEmail(AccountData accountData) throws IllegalAccessException, Throwable {
		if (!waitUntilDisplayed(DotMenuLocator(), 2)) {
			waitForPagetoLoad();
		}
		Locator locator = new Framework().getLocator(ObjectRepository.class, "DotMenuLocator");
		click(locator);
		//click(DotMenuLocator());
		waitUntilDisplayed(LoginLink(), 2);
		click(LoginLink());
		waitUntilDisplayed(Email_textbox(), 2);
		EnterValue(Email_textbox(), accountData.getUsername());
		click(Continue_Button());
		waitUntilDisplayed(Password_textbox(), 2);
		EnterValue(Password_textbox(), accountData.getPassword());
		click(Signin_Button());
		waitUntilDisappear(Email_textbox());
		if (checkLoginStatus())
			return getFirstName();
		else {
			TestListner.testing.get().log(LogStatus.FAIL,"Not Logged In");
			return "Not Logged In";
		}
	}

	public boolean checkLoginStatus() throws Throwable {
		TestListner.testing.get().log(LogStatus.INFO, "Checking Login Status from profile");
		if (!waitUntilDisplayed(DotMenuLocator(), 10)) {
			waitForPagetoLoad();
		}
		click(DotMenuLocator());
		return waitUntilDisplayed(LogOutLink(), 10);
	}

	public String getFirstName() throws Throwable {
		TestListner.testing.get().log(LogStatus.INFO,"Checking Logged in users' First name ") ;
		if (!waitUntilDisplayed(DotMenuLocator(), 10)) {
			waitForPagetoLoad();
		}
		if (!isPresent(ProfileLink())) {
			bringElementIntoView(DotMenuLocator());
			click(DotMenuLocator());
		}
		waitUntilDisplayed(ProfileLink(), 10);
		click(ProfileLink().getBy());
	    waitforPageReady();
	    waitForPagetoLoad();
	//	waitUntilPresent(firstName(), 10);
		String name=driver.findElement(firstName().getBy()).getAttribute("value");
		TestListner.testing.get().log(LogStatus.INFO,"First Name is: " +name);
		return name; 
	}
	
	public String getsignupName() throws Throwable {
		TestListner.testing.get().log(LogStatus.INFO,"Checking Logged in users' First name ") ;
		if (!waitUntilDisplayed(DotMenuLocator(), 10)) {
			waitForPagetoLoad();
		}
		if (!isPresent(ProfileLink())) {
			bringElementIntoView(DotMenuLocator());
			click(DotMenuLocator());
		}
		waitUntilDisplayed(ProfileLink(), 10);
		click(ProfileLink().getBy());
		waitUntilPresent(SignIn(), 10);
		String name=driver.findElement(SignIn().getBy()).getAttribute("value");
		TestListner.testing.get().log(LogStatus.INFO,"First Name is: " +name);
		return name; 
	}
	
	

	public String signInWithFacebook(AccountData accountData)
			throws InstantiationException, IllegalAccessException, Throwable {
		waitForPagetoLoad();
		if (!waitUntilDisplayed(DotMenuLocator(), 2)) {
			waitForPagetoLoad();
		}

		click(DotMenuLocator());
		waitUntilDisplayed(LoginLink(), 2);
		click(LoginLink());
		waitUntilDisplayed(SignInWithFacebookButton(), 2);
		String primarymainpage = driver.getWindowHandle();
		click(SignInWithFacebookButton());
		String secondarymainpage = driver.getWindowHandle();
		Set<String> pages = driver.getWindowHandles();
		for (String page : pages) {
			if (!page.equalsIgnoreCase(secondarymainpage))
				driver.switchTo().window(page);

		}
		waitUntilDisplayed(FbContinueWithNewLogin(), 5);
		EnterValue(FBEmailidText(), accountData.getUsername());
		EnterValue(FBPasswordText(), accountData.getPassword());
		click(FBLoginButton());
		driver.switchTo().window(primarymainpage);
		return getFirstName();
	}

	public void navigateToWishlist() throws Throwable, Throwable {
		click(DotMenuLocator());
		waitUntilDisplayed(WishListLink(), 2);
		click(WishListLink());
		Thread.sleep(2000);// other wait was not working
	}

	public String NewRegister(AccountData accountData) throws Throwable, Throwable {
		waitForPagetoLoad();
		closeGetAppPopUp();
		if (!waitUntilDisplayed(DotMenuLocator(), 2)) {
			waitForPagetoLoad();
		}
		click(DotMenuLocator());
		waitUntilDisplayed(LoginLink(), 2);
		click(LoginLink());
		waitUntilDisplayed(Email_textbox(), 2);
		Random random = new Random(System.currentTimeMillis());
		EnterValue(Email_textbox(),
				accountData.getFirstName() + "." + accountData.getLastName() + random.nextInt() + "@mailinator.com");
		click(continueButton());
		waitUntilDisplayed(SignUpforRegisterButton(), 10);
		click(maleCheckbox());
		waitUntilDisplayed(FirstNameForRegister(), 10);
		EnterValue(FirstNameForRegister(), accountData.getFirstName());
		EnterValue(Password_textbox(), accountData.getPassword());
		EnterValue(ConfirmationPasswordForRegistration(), accountData.getPassword());
		click(SignUpforRegisterButton());
		waitUntilDisappear(FirstNameForRegister());
		if (checkLoginStatus()) {
            TestListner.testing.get().log(LogStatus.PASS, "SignUp Successful");
            return getFirstName();
        }
		else {
            TestListner.testing.get().log(LogStatus.FAIL, "SignUp Unsuccessful");
			return "Not Logged In";
		}
	}

	public boolean LogOut() throws Throwable, Throwable {

		// click(UserProfileImage());
		click(DotMenuLocator());
		waitUntilDisplayed(LogoutButton(), 10);
		click(LogoutButton());
		waitForPagetoLoad();
		waitUntilDisappear(LogoutButton());
		TestListner.testing.get().log(LogStatus.INFO, "Checking Login Status from profile");
		if (!waitUntilDisplayed(DotMenuLocator(), 10)) {
			waitForPagetoLoad();
		}
		click(DotMenuLocator());
		return waitUntilDisplayed(LoginLink(), 10);
	}

	@Override
	public boolean isPresent(Locator locator) {
		// TODO Auto-generated method stub
		return super.isPresent(locator);
	}

	@Test
	public static void csetest() throws Throwable {
		LoginSync.getInstance().getLogin();

	}

	public String signInWithGoogle(AccountData accountData) throws Throwable {
		waitForPagetoLoad();
		if (!waitUntilDisplayed(DotMenuLocator(), 2)) {
			waitForPagetoLoad();
		}
		click(DotMenuLocator());
		waitUntilDisplayed(LoginLink(), 2);
		click(LoginLink());
		waitUntilDisplayed(SignInWithGooglePlusButton(), 2);
		click(SignInWithGooglePlusButton());
		waitUntilDisplayed(GoogleEmailidField(), 5);
		EnterValue(GoogleEmailidField(), accountData.getUsername());
		click(GoogleEmailidField());
		click(Next());
		EnterValue(GooglePasswordText(), accountData.getPassword());
		click(PasswordNExt());

		try 
		{
			if(waitUntilDisplayed(googleCityLoggedInFromOption(), 2))
			{
				click(googleCityLoggedInFromOption());
				EnterValue(CityTextBox(), "noida");
				click(CityLoginNext());
			}
		}
		catch(Exception e) {}
		if (checkLoginStatus())
			return getFirstName();
		else {
			return "Not Logged In";
		}

	}

	public void forGotPassword() {

	}

	public void createNewAccount(AccountData accountData) {

	}

	public void closeSignupPopup() throws InstantiationException, IllegalAccessException {
		waitUntilDisplayed(closeIcon(), 90);
		click(closeIcon());
	}

	public String convertCODQuoteToOrder()
	{
		String SFTPHOST = "52.77.0.29";
		int SFTPPORT = 22;
		String SFTPUSER = "developer";
		String privateKey = "C:\\Users\\Prashant\\Desktop\\KeyForAutomation.ppk";
		String command = "sudo php /var/www/nykaa/convert_quote_to_order.php";
		TestListner.testing.get().log(LogStatus.INFO,"Converting Quote to Order");
		return Framework.convertQuoteToOrder(SFTPHOST, SFTPPORT, SFTPUSER, privateKey, command);
	}

	public void navigateToMyOrders() throws IllegalArgumentException, Throwable {
		if(driver.getCurrentUrl().contains("order"))
		{
			return;
		}
		if (!waitUntilDisplayed(DotMenuLocator(), 10)) {
			waitForPagetoLoad();
		}
		if (!isDisplayed(MyOrderLocator())) {
			bringElementIntoView(DotMenuLocator());
			click(DotMenuLocator());
		}
		waitUntilDisplayed(MyOrderLocator(), 10);
		click(MyOrderLocator().getBy());
		waitForPagetoLoad();
		TestListner.testing.get().log(LogStatus.INFO,"Navigated to My Orders Screen");
	}

	public String getLastOrder() throws IllegalAccessException, Throwable
	{
		navigateToMyOrders();
		/*if(isDisplayed(noOrder()))
		{
			return "1";
		}*/
		waitUntilDisplayed(OrderList_New(), 60);
		List<WebElement>orders=getWebElements(OrderList_New());
		Locator TopOrderLocator = new Locator(By.xpath(getAbsoluteXPath(orders.get(0))),"Order");
		Locator OrderIDLocator=TopOrderLocator.concatLocator(extendedOrderLocator());
		String topOrderID=geteText(OrderIDLocator.getBy()).replaceAll("[^0-9]", "");
		return topOrderID;
	}
	
	public String getLastOrderNykaaMan() throws IllegalAccessException, Throwable
	{
		navigateToMyOrders();
		if(isDisplayed(noOrder()))
		{
			return "1";
		}
		waitUntilDisplayed(OrderListNykaaMan(), 60);
		List<WebElement>orders=getWebElements(OrderListNykaaMan());
		Locator TopOrderLocator = new Locator(By.xpath(getAbsoluteXPath(orders.get(0))),"Order");
		//Locator OrderIDLocator=TopOrderLocator.concatLocator(extendedOrderLocator());
		String topOrderID=geteText(TopOrderLocator.getBy());
		return topOrderID;
	}

	public boolean orderPresentInMyOrders(String neworderID, String lastOrderID) throws IllegalAccessException, Throwable
	{
		boolean orderPresent=false;
		closeGetAppPopUp();
		navigateToMyOrders();
		String topOrderID=null;
		int counter=0;
		topOrderID=getLastOrder();

		if((neworderID==null || neworderID.isEmpty() )&& ! topOrderID.equals(lastOrderID))
		{
			TestListner.testing.get().log(LogStatus.INFO,"Order ID : "+topOrderID+" found in My Orders");
			return true;
		}
		else
		{
			while (counter<30)
			{
				if(!lastOrderID.toString().contains(topOrderID))
				{
					orderPresent=true;
					TestListner.testing.get().log(LogStatus.INFO,"Order ID : "+neworderID+" found in My Orders");
					break;
				}
				else
				{
					Thread.sleep(30000);
					driver.navigate().refresh();
					topOrderID=getLastOrder();
				}
				counter+=1;
			}
			if(! orderPresent)
			{
				TestListner.testing.get().log(LogStatus.FAIL,"Order ID : "+neworderID.toString()+" not found in My Orders");
			}
		}

		return orderPresent;
	}

	
	public boolean orderPresentInMyOrdersNykaaMan(String neworderID, String lastOrderID) throws IllegalAccessException, Throwable
	{
		boolean orderPresent=false;
		try {
		closeGetAppPopUp();
		}
		catch(Exception e)
		{
			
		}
	//	navigateToMyOrders();
		String topOrderID=null;
		int counter=0;
		topOrderID=getLastOrderNykaaMan();

		if((neworderID==null || neworderID.isEmpty() )&& ! topOrderID.equals(lastOrderID))
		{
			TestListner.testing.get().log(LogStatus.INFO,"Order ID : "+topOrderID+" found in My Orders");
			return true;
		}
		else
		{
			while (counter<30)
			{
				if(!lastOrderID.toString().contains(topOrderID))
				{
					orderPresent=true;
					TestListner.testing.get().log(LogStatus.INFO,"Order ID : "+neworderID+" found in My Orders");
					break;
				}
				else
				{
					Thread.sleep(30000);
					driver.navigate().refresh();
					topOrderID=getLastOrder();
				}
				counter+=1;
			}
			if(! orderPresent)
			{
				TestListner.testing.get().log(LogStatus.FAIL,"Order ID : "+neworderID.toString()+" not found in My Orders");
			}
		}

		return orderPresent;
	}

	public boolean verifyOrderDetails(ArrayList<ProductDetailData> productObjectList) throws Throwable
	{
		int count=0;
		while(isDisplayed(noOrder()) && count<30)
		{
			navigateToMyOrders();
			count+=1;
		}
		boolean dataMatches=false;
		boolean flag=true;
		List<WebElement>orders=getWebElements(OrderList_New());
		Locator TopOrderLocator = new Locator(By.xpath(getAbsoluteXPath(orders.get(0))),"Order");
		click(TopOrderLocator.concatLocator(extendedOrderLocator()));
		List<WebElement> productsInOrder=getAllElements(ProductList().getBy());
		for(ProductDetailData data:productObjectList)
		{
			if(data!=null)
			{
				TestListner.testing.get().log(LogStatus.INFO,"Expected:  "+data.getProductName()+" ::Rs. "+data.getProductPrize() );
				for(WebElement nameOfProduct:productsInOrder)
				{
					String name=geteText(new Locator(By.xpath(getAbsoluteXPath(nameOfProduct)),"Name of Product").concatLocator(ProductName_extended()).getBy());
					String price=geteText(new Locator(By.xpath(getAbsoluteXPath(nameOfProduct)),"Name of Product").concatLocator(Price_extended()).getBy()).replaceAll("[^0-9]", "");
					if(Integer.parseInt(price)>=1 && name.contains(data.getProductName().replace("...", "")))
					{
						TestListner.testing.get().log(LogStatus.INFO,"Actual:  "+name+" ::Rs. "+price );
						if(price.equals(data.getProductPrize()))
						{
							dataMatches=true;
							break;
						}
						else
						{
							TestListner.testing.get().log(LogStatus.FAIL,"Product Price does not match" );
							flag=false;
						}
					}

				}
				if(!dataMatches)
				{
					TestListner.testing.get().log(LogStatus.FAIL,"Items not found in Order:  "+data.getProductName() );
					flag=false;
				}
			}
		}

		driver.navigate().back();
		return flag;
	}

	public void cancelOrder_NewMyorder() throws Throwable {
		navigateToMyOrders();
		LocalDate localDate = LocalDate.now();
		int currentDate = Integer.parseInt(DateTimeFormatter.ofPattern("dd").format(localDate));
		List<WebElement>orders=getWebElements(OrderList_New());
		int ordersize=orders.size();
		if(ordersize>1)
		{
			ordersize=1;
		}
		for(int i=0;i<ordersize;i++)
		{
			orders=getWebElements(OrderList_New());
			Locator AbsoluteOrderLocator = new Locator(By.xpath(getAbsoluteXPath(orders.get(i))),"Order");
			Locator OrderLocator=AbsoluteOrderLocator.concatLocator(extendedOrderLocator());
			System.out.println(OrderLocator.getBy());
			Locator OrderDateLocator =  OrderLocator.concatLocator(orderDate_New());
			String FullDate = getText(OrderDateLocator);
			String date  = FullDate.replaceAll("[^0-9]","");
			int Date = Integer.parseInt(date);
			if(Date>=currentDate-2)
			{
				Locator cancelLocator=AbsoluteOrderLocator.concatLocator(CancelOrderLink());
				if(isPresent(cancelLocator))
				{
					TestListner.testing.get().log(LogStatus.INFO,"Cancel:  "+geteText(OrderLocator.getBy()) );
					bringElementIntoView(cancelLocator);
					click(cancelLocator);
					if(waitUntilPresent(CancelReasonDropDown(), 10))
					{
						List<WebElement> selectDropDown=new ArrayList<WebElement>();
						List<WebElement> cancelBtn=new ArrayList<WebElement>();
						//List<WebElement> OKBtn=new ArrayList<WebElement>();
						Select sel=null;
						Thread.sleep(4000);
						selectDropDown=driver.findElements(CancelReasonDropDown().getBy());
						for(WebElement we: selectDropDown)
						{
							if(we.isDisplayed())
							{
								Thread.sleep(2000);
								sel=new Select(we);
								sel.selectByIndex(1);
								TestListner.testing.get().log(LogStatus.INFO,"Select cancellation reason from Confirm Cancellation Pop Up");
								Thread.sleep(5000);
								break;
							}
						}

						cancelBtn=driver.findElements(ConfirmCancellationLocator().getBy());
						for(WebElement ConfirmCancel: cancelBtn)
						{
							if(ConfirmCancel.isDisplayed())
							{
								ConfirmCancel.click();
								TestListner.testing.get().log(LogStatus.INFO,"Click on Confirm cancellation button on Confirm Cancellation Pop Up");
								Thread.sleep(1000);
								break;
							}
						}
					}

				}
			}

			//driver.navigate().back();
		}

	}

	public boolean cancelLatestOrder() throws Throwable {

		String topOrderID="";
		int counter=0;
		boolean isLatest=false;
		boolean isCancelled=false;
		navigateToMyOrders();
		if(driver.getCurrentUrl().contains("v2"))
		{
			return cancelLatestOrder_NewMyOrder();
		}
		List<WebElement>orders=getWebElements(OrderList());
		orders=getWebElements(OrderList());
		Locator TopOrderLocator = new Locator(By.xpath(getAbsoluteXPath(orders.get(0))),"Order");
		Locator OrderIDLocator=TopOrderLocator.concatLocator(OrderID());
		topOrderID=geteText(OrderIDLocator.getBy());
		while (! isLatest && counter<20)
		{
			orders=getWebElements(OrderList());
			TopOrderLocator = new Locator(By.xpath(getAbsoluteXPath(orders.get(0))),"Order");
			OrderIDLocator=TopOrderLocator.concatLocator(OrderID());
			String topOrderIDNew=geteText(OrderIDLocator.getBy());
			if(! topOrderID.equals(topOrderIDNew)|| counter<1)
			{
				Locator viewDetailLocator = TopOrderLocator.concatLocator(viewDetail());
				bringElementIntoView(viewDetailLocator);
				ScrollDown("50");
				Thread.sleep(1000);
				click(viewDetailLocator);
				bringElementIntoView(StatusLocator());
				if(!waitUntilDisplayed(StatusOfOrder("Cancelled"),10))
				{ 
					isLatest=true;
					TestListner.testing.get().log(LogStatus.INFO,"Cancel:  "+geteText(OrderID().getBy()) );
					bringElementIntoView(CancelOrder());
					click(CancelOrder());
					if(waitUntilPresent(CancelReasonDropDown(), 10))
					{
						List<WebElement> selectDropDown=new ArrayList<WebElement>();
						List<WebElement> cancelBtn=new ArrayList<WebElement>();
						List<WebElement> OKBtn=new ArrayList<WebElement>();
						Select sel=null;
						Thread.sleep(4000);
						selectDropDown=driver.findElements(CancelReasonDropDown().getBy());
						for(WebElement we: selectDropDown)
						{
							if(we.isDisplayed())
							{
								Thread.sleep(2000);
								sel=new Select(we);
								sel.selectByIndex(1);
								TestListner.testing.get().log(LogStatus.INFO,"Select cancellation reason from Confirm Cancellation Pop Up");
								Thread.sleep(5000);
								break;
							}
						}

						cancelBtn=driver.findElements(ConfirmCancellationLocator().getBy());
						for(WebElement ConfirmCancel: cancelBtn)
						{
							if(ConfirmCancel.isDisplayed())
							{
								ConfirmCancel.click();
								TestListner.testing.get().log(LogStatus.INFO,"Click on Confirm cancellation button on Confirm Cancellation Pop Up");
								Thread.sleep(1000);
								break;
							}
						}
						Thread.sleep(5000);
						OKBtn=driver.findElements(confirmationButton().getBy());
						if(isDisplayed(CancelNotification())){
							isCancelled =true;
					}
						for(WebElement OK: OKBtn)
						{
							if(OK.isDisplayed())
							{
								OK.click();
								Thread.sleep(1000);
								isCancelled=true;
								break;
							}
						}
						if(! isCancelled)
						{
							return isCancelled;
						}
					}
					break;
				}
				driver.navigate().back();
			}
			counter++;
			Thread.sleep(30000);
			//navigateToMyOrders();
			driver.navigate().refresh();
		}
		return isCancelled;
	}

	public boolean cancelLatestOrder_NewMyOrder() throws Throwable {
		String topOrderID="";
		int counter=0;
		boolean isLatest=false;
		boolean isCancelled=false;
		navigateToMyOrders();
		List<WebElement>orders=getWebElements(OrderList_New());
		//orders=getWebElements(OrderList());
		Locator TopOrderLocator = new Locator(By.xpath(getAbsoluteXPath(orders.get(0))),"Order");
		Locator OrderIDLocator=TopOrderLocator.concatLocator(extendedOrderLocator());
		topOrderID=geteText(OrderIDLocator.getBy()).replaceAll("[^0-9]", "");
		while (! isLatest && counter<20)
		{
			orders=getWebElements(OrderList_New());
			TopOrderLocator = new Locator(By.xpath(getAbsoluteXPath(orders.get(0))),"Order");
			OrderIDLocator=TopOrderLocator.concatLocator(extendedOrderLocator());
			String topOrderIDNew=geteText(OrderIDLocator.getBy()).replaceAll("[^0-9]", "");
			if(! topOrderID.equals(topOrderIDNew)|| counter<1)
			{
				Locator orderStatus=TopOrderLocator.concatLocator(StatusOfOrder_New("cancelled"));
				if(!waitUntilDisplayed(orderStatus,10))
				{ 
					click(TopOrderLocator.concatLocator(extendedOrderLocator()));
					Locator cancelLocator=CancelOrderLink();

					isLatest=true;
					TestListner.testing.get().log(LogStatus.INFO,"Cancel:  "+geteText(OrderIDLocator.getBy()) );
					bringElementIntoView(cancelLocator);
					click(cancelLocator);
					if(waitUntilPresent(CancelReasonDropdown_OrderDetails(), 10))
					{
						//List<WebElement> selectDropDown=new ArrayList<WebElement>();
						List<WebElement> cancelBtn=new ArrayList<WebElement>();
						Select sel=null;
						Thread.sleep(4000);
						List<WebElement> selectDropDown = driver.findElements(CancelReasonDropdown_OrderDetails().getBy());
						for(WebElement we: selectDropDown)
						{
							if(we.isDisplayed())
							{
								Thread.sleep(2000);
								sel=new Select(we);
								sel.selectByIndex(1);
								TestListner.testing.get().log(LogStatus.INFO,"Select cancellation reason from Confirm Cancellation Pop Up");
								Thread.sleep(5000);
								break;
							}
						}

						cancelBtn=driver.findElements(ConfirmCancellationLocator().getBy());
						for(WebElement ConfirmCancel: cancelBtn)
						{
							if(ConfirmCancel.isDisplayed())
							{
								ConfirmCancel.click();
								TestListner.testing.get().log(LogStatus.INFO,"Click on Confirm cancellation button on Confirm Cancellation Pop Up");
								waitUntilDisplayed(cancellationMessageLocator(), 30);
								Thread.sleep(1000);
								driver.navigate().back();
								orders=getWebElements(OrderList_New());
								TopOrderLocator = new Locator(By.xpath(getAbsoluteXPath(orders.get(0))),"Order");
								orderStatus=TopOrderLocator.concatLocator(StatusOfOrder_New("cancelled"));
								if(isDisplayed(CancelNotification())){
									isCancelled =true;
								}
								if(waitUntilDisplayed(orderStatus, 30))
								{
									isCancelled=true;
								}
								else
								{
									TestListner.testing.get().log(LogStatus.INFO,"Order Cancelled but status of order did not change");
								}
								break;
							}
						}
						Thread.sleep(5000);
						if(! isCancelled)
						{
							return isCancelled;
						}
					}
					break;
				}
				//driver.navigate().back();
			}
			counter++;
			Thread.sleep(30000);
			//navigateToMyOrders();
			driver.navigate().refresh();
		}
		return isCancelled;
	}

	public boolean cancel_Items() throws Throwable {
		String topOrderID="";
		int counter=0;
		boolean isLatest=false;
		boolean isCancelled=false;
		List<WebElement> cancelBtn=new ArrayList<WebElement>();
		navigateToMyOrders();
		List<WebElement>orders=getWebElements(OrderList_New());
		Locator TopOrderLocator = new Locator(By.xpath(getAbsoluteXPath(orders.get(0))),"Order");
		Locator OrderIDLocator=TopOrderLocator.concatLocator(extendedOrderLocator());
		topOrderID=geteText(OrderIDLocator.getBy()).replaceAll("[^0-9]", "");
		while (! isLatest && counter<20)
		{
			orders=getWebElements(OrderList_New());
			TopOrderLocator = new Locator(By.xpath(getAbsoluteXPath(orders.get(0))),"Order");
			OrderIDLocator=TopOrderLocator.concatLocator(extendedOrderLocator());
			String topOrderIDNew=geteText(OrderIDLocator.getBy()).replaceAll("[^0-9]", "");
			if(! topOrderID.equals(topOrderIDNew)|| counter<1)
			{
				Locator orderStatus=(StatusOfOrder_New("Cancelled"));
				click(TopOrderLocator.concatLocator(extendedOrderLocator()));
				if(!waitUntilDisplayed(orderStatus,10))
				{
					List<WebElement> productsInOrder=getAllElements((ProductList()).getBy());
					String nameList=" ";
					String itemsToCancel=" ";
					for(WebElement nameOfProduct: productsInOrder)
					{
						nameList=nameList+geteText(new Locator(By.xpath(getAbsoluteXPath(nameOfProduct)),"Name of Product").concatLocator(ProductName_extended()).getBy())+" , ";
					}

					productsInOrder.remove(productsInOrder.size()-1);
					for(WebElement nameOfProduct: productsInOrder)
					{
						itemsToCancel=itemsToCancel+geteText(new Locator(By.xpath(getAbsoluteXPath(nameOfProduct)),"Name of Product").concatLocator(ProductName_extended()).getBy())+" , ";
					}

					Locator cancelLocator=(CancelItemsLink());

					isLatest=true;
					TestListner.testing.get().log(LogStatus.INFO,"Cancel items in :  "+geteText(OrderIDLocator.getBy()) );
					TestListner.testing.get().log(LogStatus.INFO,"Products in Order:  "+nameList);
					TestListner.testing.get().log(LogStatus.INFO,"Products to be cancelled:  "+itemsToCancel);

					bringElementIntoView(cancelLocator);
					click(cancelLocator);
					click(Item_selection_in_MyOrder());
					if(waitUntilPresent(CancelReasonDropDown(), 10)) {
						Select sel=null;
						List<WebElement> selectDropDown = driver.findElements(CancelReasonDropDown().getBy());
						for (WebElement we : selectDropDown) {
							if (we.isDisplayed()) {
								Thread.sleep(2000);
								sel = new Select(we);
								sel.selectByIndex(2);
								TestListner.testing.get().log(LogStatus.INFO, "Select cancellation reason from Confirm Cancellation Pop Up");
								Thread.sleep(5000);
								break;
							}
						}
						cancelBtn=driver.findElements(ItemConfirmCancelButton().getBy());
						for(WebElement ConfirmCancel: cancelBtn)
						{
							if(ConfirmCancel.isDisplayed())
							{
								ConfirmCancel.click();
								TestListner.testing.get().log(LogStatus.INFO,"Click on Yes button on Confirm Cancellation Pop Up");
								Thread.sleep(1000);
								waitUntilDisplayed(yesButton(), 10);
								click(yesButton());
								if(waitUntilDisplayed(cancelSuccessfulMsg(), 30))
								{
									Thread.sleep(3000);
									click(doneButton());
									isCancelled=true;
								}else if(waitUntilDisplayed(cancelNotifyMsg(), 30)){
									click(doneButton());
								}
								break;
							}
						}
				//		verifyItemStatusCancelled();
						Thread.sleep(5000);
						if(! isCancelled)
						{
							return isCancelled;
						}

					}
					break;
				}
				//driver.navigate().back();
			}
			counter++;
			Thread.sleep(30000);
			navigateToMyOrders();
		}
		return isCancelled;
	}

	public void verifyItemStatusCancelled() throws Throwable {
		Thread.sleep(3000);
		navigateToMyOrders();
		List<WebElement> orders = getWebElements(OrderList_New());

		Locator CancelTag = OrderList_New().concatLocator(ProductListOrderListing());

		List<WebElement> Item_List = getWebElements(ProductListOrderListing());
		for (WebElement we : Item_List) {
			Locator ItemName = new Locator(By.xpath("/following-sibling::div/div/img[@alt]"), "Name of Product").concatLocator(CancelTag);
			try {
				if (getAttribute(we, "span").contains("cancelled") | (getAttribute(we, "span")) != null) {
					TestListner.testing.get().log(LogStatus.INFO, "Cancelled Tag displayed on : " + getAttribute(ItemName, "alt"));
				} else {
					TestListner.testing.get().log(LogStatus.FAIL, "Cancelled Tag not displayed on : " + getAttribute(ItemName, "alt"));
				}

			} catch (Exception e) {

			}
	    /*Locator TopOrderLocator = new Locator(By.xpath(getAbsoluteXPath(orders.get(0))),"Order");
		click(OrderList_New());
		List<WebElement> productsInOrder=getAllElements(ProductListOrderListing().getBy());
		productsInOrder.remove(productsInOrder.size()-1);
		for(WebElement nameOfProduct: productsInOrder)
		{
			if(getAttribute(OrderList_New(), "class").contains("cancelled"))
			{
				TestListner.testing.get().log(LogStatus.INFO,"Cancelled Tag displayed on : "+geteText(new Locator(By.xpath("//p[@class='my-order-m-order-id']"),"Name of Product").concatLocator(OrderList_New()).getBy()));
			}
			else
			{
				TestListner.testing.get().log(LogStatus.FAIL,"Cancelled Tag not displayed on : "+geteText(new Locator(By.xpath(getAbsoluteXPath(nameOfProduct)),"Name of Product").concatLocator(ProductName_extended()).getBy()));
			}
		}*/
		}
	}

}
