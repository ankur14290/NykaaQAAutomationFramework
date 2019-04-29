package PagesNykaa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import DataNykaa.AccountData;
import DataNykaa.ProductDetailData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Framework;
import FrameWorkNykaa.Locator;
import FrameWorkNykaa.LoginSync;
import FrameWorkNykaa.TestListner;



public class AccountPage_React extends BrowserAction {

	public AccountPage_React(WebDriver driver) {

		this.driver = driver;
	}

	Locator myOrderLoader(){
		return new Locator(By.xpath("//div[@class='myorder-loader-css text-center']"),"Loader");
	}

	Locator newRegister(){
		return new Locator(By.partialLinkText("Sign Up"),"New Regsiter");
	}

	Locator MyOrderLocator(){
		return new Locator(By.xpath("//*[@class='nav_icon orders']"),"My Order Tab");
	}

	Locator SignIn(){

		return new Locator(By.partialLinkText("Sign In/Up"),"Sign In/Up");
	}
	Locator FBEmailidText(){
		return new Locator(By.id("email"),"FB Email Id Text");

	}

	Locator CancelOrder(){
		return new Locator(By.xpath("//a[@class='invoice-btn cancle-btn'][contains(text(),'Order')]"),"Cancel Order");
	}

	Locator CancelReasonDropDown(){
		return new Locator(By.xpath("//select[contains(@id,'select_cancel')]|//select[@class='reason-select-box']"),"Cancel Reason Drop Down");
	}
	Locator ConfirmCancellationLocator(){
		return new Locator(By.xpath("//button[contains(text(),'Confirm Cancellation')] | //button[contains(text(),'yes')]"),"Confirm Cancellation button");
	}

	Locator OrderID(){
		return new Locator(By.xpath("//div[@class='pull-left my-order__order-number']"),"Order ID");
	}

	Locator FBPasswordText(){
		return new Locator(By.id("pass"),"Password Text");
	}

	Locator FBLoginButton(){
		return new Locator(By.id("loginbutton"),"Login Button");
	}

	

	Locator FBConfirmLoginButton(){
		return new Locator(By.xpath("//button[@name='__CONFIRM__'][@type='submit']"),"Fb Confirm Login Button");
	}
	Locator FirstNameForRegister(){
		return new Locator(By.id("firstname"),"UserName Of Register");

	}
	Locator LastNameForRegister(){
		return new Locator(By.id("lastname"),"lastname Of Register");

	}

	Locator StatusOfOrder(String OrderStatus)
	{
		return new Locator(By.xpath("//span[@class='my-order-status status-delivered'][contains(text(),'"+OrderStatus+"')]"),OrderStatus +"Status");
	}

	Locator StatusOfOrder_New(String OrderStatus)
	{
		return new Locator(By.xpath("//span[contains(@class,'toggle-progress-bar')][contains(text(),'"+OrderStatus+"')]"),OrderStatus +"Status");
	}

	Locator CancelOrderLink()
	{
		return new Locator(By.xpath("//span[@class='my-order-cancel-link']"),"Cancel order");
	}

	Locator CancelItemsLink()
	{
		return new Locator(By.xpath("//span[contains(@class,'my-order-cancel-item')]"),"Cancel order");
	}

	Locator StatusLocator()
	{
		return new Locator(By.xpath("//span[contains(@class,'my-order-status status-delivered')]"),"Status");
	}

	Locator confirmationButton()
	{
		return new Locator(By.xpath("//a[text()='OK']"),"OK button");
	}
	
	Locator confirmLoginButtonNykaaMan()
	{
		return new Locator(By.xpath("//button[contains(text(),'Continue')]"),"confirm button");
	}

	Locator closeIcon(){

		return new Locator(By.id("closelb"),"close Icon");
	}
	Locator EmailAddressForRegistration(){
		return new Locator(By.xpath("//form[@id='signup-form']/descendant::input[@id='email']"),"Email Address for Registration");
	}
	Locator PasswordForRegistration(){

		return new Locator(By.id("password"),"Password For Registration");
	}
	Locator ConfirmationPasswordForRegistration(){

		return new Locator(By.id("confirmation"),"Password For Registration");
	}

	Locator SignUpforRegisterButton(){

		return new Locator(By.xpath("//button[@title='Submit']"),"Sign up button of Registration");
	}

	Locator UserProfileImage(){
		return new Locator(By.id("user-profile-img"),"User Profile Image");

	}

	Locator IsUserLoginFlag(){

		return new Locator(By.id("isUserLoginFlag"),"IS user Login Flag ");
	}

	Locator LogoutButton(){

		return new Locator(By.xpath("//a/span[contains(@class,'log-out')]|//a/span[contains(@class,'logout')]"),"LogOut Button");
	}

	Locator OrderList(){
		return new Locator(By.xpath("//div[@class='my-orders-lists-wrap']"),"Order List");
	}

	Locator orderDate(){
		return new Locator(By.xpath("//div[@class='pull-right  my-order__order-details-date']"),"Order Date");
	}

	Locator orderDate_New(){
		return new Locator(By.xpath("/following-sibling::p/span[2]"),"Order Date");
	}

	Locator viewDetail(){
		return new Locator(By.xpath("//a[@class='my-order__order-details-btn']"),"View Detail button");
	}

	Locator OrderList_New(){
		return new Locator(By.xpath("//div[contains(@class,'my-order-details-container')]"),"Order List");
	}

	Locator extendedOrderLocator(){
		return new Locator(By.xpath("//p[contains(@class,'my-order-font-bold')][contains(text(),'ORDER')]"),"Order List");
	}

	private Locator Email_textbox() {
		return new Locator(By.xpath("//input[@type='email']|//input[@id='email']"), "Email Textbox");
	}

	private Locator Password_textbox() {
		return new Locator(By.xpath("//input[@type='password']|//input[@id='pass']"), "Password Textbox");
	}
	
	private Locator ConfirmPassword_textbox() {
		return new Locator(By.xpath("//input[@type='password'][@name='confirmPassword']"), "Confirm Password Textbox");
	}
	
	private Locator Signin_Button() {
		return new Locator(By.xpath("//button[@class='auth-modal__btn']|//button[@class='sign-in']"),"Sign In Button");
	}
	private Locator Signup_Popup() {
		return new Locator(By.xpath("//span[@class='AccountText']"), "Signup Link");

	}

	private Locator FbContinueWithNewLogin(){
		return new Locator(By.xpath("//a[text()='Create New Account']"),"Create new Account");
	}

	Locator SignInWithFacebookButton()
	{
		return new Locator(By.xpath("//button[@class='loginBtn loginBtn--facebook metro']"),"Sign in With Facebook Button");
	}

	Locator SignInWithGooglePlusButton()
	{
		return new Locator(By.xpath("//a[@class='loginBtn loginBtn--google']"),"Sign in With Google plus");
	}
	Locator GoogleEmailidField(){

		return new Locator(By.id("identifierId"),"Google Email id Field");
	}

	Locator Next(){
		return new Locator(By.xpath("//span[contains(text(), 'Next')]"),"Next button");
	}

	Locator GooglePasswordText(){
		return new Locator(By.xpath("//input[@name='password']"),"google Password Text Field");
	}

	Locator PasswordNExt(){
		return new Locator(By.id("passwordNext"),"Password Next");
	}
	Locator GoogleSignInButton(){
		return new Locator(By.id("signIn"),"Google Sign in Button");
	}

	Locator approveGoogle(){
		return new Locator(By.id("submit_approve_access"),"Allow button");
	}

	Locator UserNameValue(){
		return new Locator(By.xpath("//span[@class='AccountText']/descendant::span | //span[@class='AccountText']"), "Logged in UserName");
	}

	Locator MyProfile(){
		return new Locator(By.xpath("//*[@class='nav_icon account_edit ']"),"My Profile");
	}

	Locator MyWishlistNavigation(){
		return new Locator(By.xpath("//span[@class='nav_icon wishlist']"),"My wishlist Icon");
	}

	Locator AccountButton() {
		return new Locator(By.xpath("//div [@class ='UserAccount']"), "Account");
	}

	Locator MyOrderButton(){
		return new Locator((By.xpath("//a[@href='/sales/order/history/?ptype=account'])")), "MyOrder");
	}

	
	Locator LogoutButton_reactNykaaMan(){

		return new Locator(By.xpath("//a/span[contains(@class,'log-out')]"),"LogOut Button");
	}  
	Locator LogoutButton_react(){

		return new Locator(By.xpath("//div[@id='headerDropdown']//li/a/i[contains(@class,'log-out')]"),"LogOut Button");
	}

	Locator ProductList(){
		return new Locator(By.xpath("//div[contains(@class,'my-order-product-section')]/a/div[contains(@class,'my-order-product-details-section')]"),"Product List");
	}

	Locator ProductName_extended()
	{
		return new Locator(By.xpath("//div[contains(@class,'my-order-product-description')]/p[1]"),"Product Name");
	}

	Locator Price_extended()
	{
		return new Locator(By.xpath("//div[contains(@class,'my-order-product-description')]/p[3]/span"),"Product Price");
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

	Locator doneButton(){
		return new Locator(By.xpath("//button[contains(@class,'my-order-m-completed-btn')]"),"Done Button");
	}

	Locator noOrder(){
		return new Locator(By.xpath("//h4[contains(@class,'no-order')]"),"No Order");
	}
	
	Locator continueButton()
	{
		return new Locator(By.xpath("//button[@class='auth-modal__btn'][text()='Continue']"),"Continue Button");
	}

	Locator NameTextBoxRegistrationReact()
	{
		return new Locator(By.xpath("//input[@name='firstname']"),"Name Text Box");
	}
	
	Locator signUpButtonReact()
	{
		return new Locator(By.xpath("//button[@class='auth-modal__btn'][text()='Sign Up']"),"Sign Up Button");
	}
	
	Locator mrRadioButton()
	{
		return new Locator(By.xpath("//input[@type='radio'][@value='1']"),"Mr. Radio button");
	}

	Locator firstName(){
		return new Locator(By.xpath("//input[@id='firstname']"),"First Name");
	}
	
	public String signInWithYourEmailMan(AccountData accountData) throws IllegalAccessException, Throwable {
		if(!waitUntilDisplayed(Email_textbox(), 2)){
			waitForPagetoLoad();
			click(Signup_Popup());
		}
		try {
		EnterValue(Email_textbox(),accountData.getUsername());
		click(confirmLoginButtonNykaaMan());
		EnterValue(Password_textbox(),accountData.getPassword());
		click(Signin_Button());
		} 
		catch(Exception e){
		
		EnterValue(Password_textbox(),accountData.getPassword());
		click(Signin_Button());
		}
		waitUntilDisappear(Email_textbox());
		waitUntilDisplayed(UserNameValue(), 10);
		waitForPagetoLoad();
		int counter=0;
		while(getText(UserNameValue()).equalsIgnoreCase("account") && counter<10)
		{
			Thread.sleep(1000);
			counter+=1;
		}
		return  getText(UserNameValue());
	}
	
	public String signInWithYourEmail(AccountData accountData) throws IllegalAccessException, Throwable {
		if(!waitUntilDisplayed(Email_textbox(), 2)){
			waitForPagetoLoad();
			click(Signup_Popup());
		}
		
		EnterValue(Email_textbox(),accountData.getUsername());
		EnterValue(Password_textbox(),accountData.getPassword());
		click(Signin_Button());
		waitUntilDisappear(Email_textbox());
		waitUntilDisplayed(UserNameValue(), 10);
		waitForPagetoLoad();
		int counter=0;
		while(getText(UserNameValue()).equalsIgnoreCase("account") && counter<10)
		{
			Thread.sleep(1000);
			counter+=1;
		}
		return  getText(UserNameValue());
	}

	public String ReactSignInwithEmail (AccountData accountData) throws IllegalAccessException, InstantiationException, InterruptedException {
		if(!waitUntilDisplayed(Email_textbox(), 2)){
			waitForPagetoLoad();
			click(Signup_Popup());
		}
		EnterValue(Email_textbox(),accountData.getUsername());
		click(Signin_Button());
		EnterValue(Password_textbox(),accountData.getPassword());
		click(Signin_Button());
		waitUntilDisappear(Email_textbox());
		waitUntilDisplayed(UserNameValue(), 10);
		waitForPagetoLoad();
		int counter=0;
		while(getText(UserNameValue()).equalsIgnoreCase("account") && counter<10)
		{
			Thread.sleep(1000);
			counter+=1;
		}
		return  getText(UserNameValue());
	}

	public String signInWithFacebook(AccountData accountData) throws InstantiationException, IllegalAccessException, Throwable{
		if(!waitUntilDisplayed(Email_textbox(), 2)){
			waitForPagetoLoad();
			click(Signup_Popup());
		}
		String primarymainpage = driver.getWindowHandle();
		click(SignInWithFacebookButton());

		String secondarymainpage = driver.getWindowHandle();
		Set<String> pages = driver.getWindowHandles();
		for(String page:pages)
		{
			if(!page.equalsIgnoreCase(secondarymainpage))
				driver.switchTo().window(page);

		}
		waitUntilDisplayed(FbContinueWithNewLogin(), 5);
		EnterValue(FBEmailidText(),accountData.getUsername());
		EnterValue(FBPasswordText(),accountData.getPassword());
		click(FBLoginButton());
		driver.switchTo().window(primarymainpage);
		return getText(UserNameValue());



	}

	public void navigateToWishlist() throws  Throwable{

		mouseOver(UserNameValue());
		waitUntilDisplayed(MyWishlistNavigation(), 10);
		click(MyWishlistNavigation());
		Thread.sleep(6000);// other wait was not working
	}
	
	public String NewRegister_React(AccountData accountData) throws Throwable, Throwable
	{
		Random random = new Random(System.currentTimeMillis());
		waitForPagetoLoad();
		click(Signup_Popup());
		waitUntilDisplayed(Email_textbox(), 5);
		EnterValue(Email_textbox(),accountData.getFirstName()+"."+accountData.getLastName()+random.nextInt()+"@mailinator.com");
		click(continueButton());
		waitUntilDisplayed(NameTextBoxRegistrationReact(), 20);
		click(mrRadioButton());
		EnterValue(NameTextBoxRegistrationReact(),accountData.getFirstName()+" "+accountData.getLastName());
		EnterValue(Password_textbox(),accountData.getPassword());
		EnterValue(ConfirmPassword_textbox(),accountData.getPassword());
		click(signUpButtonReact());
		waitUntilDisplayed(UserNameValue(), 20);
		waitForPagetoLoad();
		int counter=0;
		Thread.sleep(5000);
		TestListner.testing.get().log(LogStatus.INFO, "Checking Login Status from profile");
		if (!waitUntilDisplayed(UserNameValue(), 10)) {
			waitForPagetoLoad();
		}
		click(UserNameValue());
		if(isDisplayed(LogoutButton_react())){
			TestListner.testing.get().log(LogStatus.PASS, "SignUp Successful");
		}else{
			TestListner.testing.get().log(LogStatus.FAIL, "SignUp Failed");
		}
		String UserName= getFirstName();
		TestListner.testing.get().log(LogStatus.INFO,"Logged In User Name: " + UserName);

		return UserName;
	}
	
	public String NewRegister(AccountData accountData) throws Throwable, Throwable
	{
		waitForPagetoLoad();
		click(Signup_Popup());
		click(newRegister());
		EnterValue(FirstNameForRegister(),accountData.getFirstName());
		EnterValue(LastNameForRegister(),accountData.getLastName());
		Random random = new Random(System.currentTimeMillis());
		EnterValue(EmailAddressForRegistration(),accountData.getFirstName()+"."+accountData.getLastName()+random.nextInt()+"@mailinator.com");
		EnterValue(PasswordForRegistration(),accountData.getPassword());
		EnterValue(ConfirmationPasswordForRegistration(),accountData.getPassword());
		click(SignUpforRegisterButton());
		waitUntilDisplayed(UserNameValue(), 10);
		waitForPagetoLoad();
		int counter=0;
		while(getText(UserNameValue()).equalsIgnoreCase("account") && counter<10)
		{
			Thread.sleep(1000);
			counter+=1;
		}
		Thread.sleep(5000);


		TestListner.testing.get().log(LogStatus.INFO,"Logged In User Name: " + getText(UserNameValue()));
		return getText(UserNameValue());
	}

/*<<<<<<< HEAD*/

	public String getFirstName() throws Throwable {
		TestListner.testing.get().log(LogStatus.INFO,"Checking Logged in users' First name ") ;
		if (!waitUntilDisplayed(AccountButton(), 10)) {
			waitForPagetoLoad();
		}
		if (!isPresent(MyProfile())) {
			bringElementIntoView(UserNameValue());
			click(UserNameValue());
		}
		//waitUntilDisplayed(My(), 10);
		click(MyProfile());
		waitUntilPresent(firstName(), 10);
		String name=driver.findElement(firstName().getBy()).getAttribute("value");
		TestListner.testing.get().log(LogStatus.INFO,"First Name is: " +name);
		return name;
	}

	/*public boolean LogOut() throws Throwable, Throwable{
=======*/
	public boolean LogOut(String platform) throws Throwable, Throwable{
		//end of new changes

		// click(UserProfileImage());
		//Actions action = new Actions(driver);
		//WebElement UserButton = driver.findElement(AccountButton().getBy());
		waitUntilDisplayed(AccountButton(), 10);
		click(AccountButton());
		if(platform.contains("nykaaman"))
		{
			if (! waitUntilDisplayed(LogoutButton(), 10) )
			{
				click(AccountButton());
			}
			//action.moveToElement(UserButton).click().build().perform();
			click(LogoutButton());
		}
		else
		{
		if (! waitUntilDisplayed(LogoutButton_react(), 10) )
		{
			click(AccountButton());
		}
		//action.moveToElement(UserButton).click().build().perform();
/*<<<<<<< HEAD
		click(LogoutButton());
=======*/
		click(LogoutButton_react());
		
		}
		//end of new changes
		return waitUntilDisplayed(Signup_Popup(), 60);
	}

	public void navigateToMyOrders() throws InstantiationException, IllegalAccessException, InterruptedException {
		while(! isDisplayed(MyOrderLocator()))
		{List<WebElement>usernames=getAllElements(UserNameValue().getBy());
			Actions action = new Actions(driver);
		for(WebElement user:usernames)
		{
			try {
			action.moveToElement(user).build().perform();
			isDisplayed(MyOrderLocator());
			
			}
			catch(Exception e)
			{
				action.moveToElement(user).build().perform();
			}
			//if(user.isDisplayed())
			//	click(user,"UserName");
			 //   click(user,"UserName");
		}
		}
		click(MyOrderLocator());
		TestListner.testing.get().log(LogStatus.INFO,"Navigated to My Orders Screen");
		waitForPagetoLoad();
		waitUntilDisappear(myOrderLoader());
	}

	@Override
	public boolean isPresent(Locator locator) {
		// TODO Auto-generated method stub
		return super.isPresent(locator);
	}

	@Test
	public static void csetest() throws Throwable{
		LoginSync.getInstance().getLogin();

	}

	public String signInWithGooglePlus(AccountData accountData) throws Throwable {
		waitForPagetoLoad();
		if(!waitUntilDisplayed(Email_textbox(), 2)){
			click(Signup_Popup());
		}
		click(SignInWithGooglePlusButton());
		EnterValue(GoogleEmailidField(),accountData.getUsername());
		click(Next());

		EnterValue(GooglePasswordText(), accountData.getPassword());
		click(PasswordNExt());
		waitForPagetoLoad();
		int counter=0;
		while(getText(UserNameValue()).equalsIgnoreCase("account") && counter<10)
		{
			Thread.sleep(1000);
			counter+=1;
		}
		return getText(UserNameValue());

	}
	
	public void closeSignupPopup() throws InstantiationException, IllegalAccessException{
		waitUntilDisplayed(closeIcon(), 90);
		click(closeIcon());
	}

	public void cancelOrder() throws Throwable {
		navigateToMyOrders();
		if(driver.getCurrentUrl().contains("v2"))
		{
			cancelOrder_NewMyorder();
			return;
		}
		LocalDate localDate = LocalDate.now();
		int currentDate = Integer.parseInt(DateTimeFormatter.ofPattern("dd").format(localDate));
		List<WebElement>orders=getWebElements(OrderList());
		int ordersize=orders.size();
		if(ordersize>1)
		{
			ordersize=1;
		}
		for(   int i=0;i<ordersize;i++)
		{
			orders=getWebElements(OrderList());
			Locator OrderLocator = new Locator(By.xpath(getAbsoluteXPath(orders.get(i))),"Order");
			System.out.println(OrderLocator.getBy());
			Locator OrderDateLocator =  OrderLocator.concatLocator(orderDate());
			String FullDate = getText(OrderDateLocator);
			String date  = FullDate.split("/")[0];
			int Date = Integer.parseInt(date);
			if(Date>=currentDate-2)
			{

				Locator viewDetailLocator = OrderLocator.concatLocator(viewDetail());
				bringElementIntoView(viewDetailLocator);
				ScrollDown("50");
				Thread.sleep(1000);

				click(viewDetailLocator);
			}
			bringElementIntoView(StatusLocator());
			if(!waitUntilDisplayed(StatusOfOrder("Cancelled"),10))
			{ 
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
					OKBtn=driver.findElements(confirmationButton().getBy());
					for(WebElement OK: OKBtn)
					{
						if(OK.isDisplayed())
						{
							OK.click();
							Thread.sleep(1000);
							break;
						}
					}
				}

			}

			driver.navigate().back();
		}

	}

	public void cancelOrder_NewMyorder() throws Throwable {
		//navigateToMyOrders();
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
			navigateToMyOrders();
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
				Locator orderStatus=TopOrderLocator.concatLocator(StatusOfOrder_New("Cancelled"));
				if(!waitUntilDisplayed(orderStatus,10))
				{ 
					Locator cancelLocator=TopOrderLocator.concatLocator(CancelOrderLink());

					isLatest=true;
					TestListner.testing.get().log(LogStatus.INFO,"Cancel:  "+geteText(OrderIDLocator.getBy()) );
					bringElementIntoView(cancelLocator);
					click(cancelLocator);
					if(waitUntilPresent(CancelReasonDropDown(), 10))
					{
						List<WebElement> selectDropDown=new ArrayList<WebElement>();
						List<WebElement> cancelBtn=new ArrayList<WebElement>();
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
								navigateToMyOrders();
								orders=getWebElements(OrderList_New());
								TopOrderLocator = new Locator(By.xpath(getAbsoluteXPath(orders.get(0))),"Order");
								orderStatus=TopOrderLocator.concatLocator(StatusOfOrder_New("Cancelled"));
								if(waitUntilDisplayed(orderStatus, 30))
								{
									isCancelled=true;
								}
								else
								{
									TestListner.testing.get().log(LogStatus.FAIL,"Order Cancelled but status of order did not change");
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
			navigateToMyOrders();
		}
		return isCancelled;
	}

	public boolean Login_Logout_Myorder() throws IllegalAccessException, InstantiationException, InterruptedException {
		waitUntilDisplayed(AccountButton(), 5);
		click(AccountButton());
		if (! waitUntilDisplayed(LogoutButton(), 5) )
		{
			click(AccountButton());
		}
		waitUntilDisplayed(MyOrderLocator(),5);
		click(MyOrderLocator());
		Thread.sleep(10000);
		waitUntilDisplayed(AccountButton(),15);
		click(AccountButton());
		if (! waitUntilDisplayed(LogoutButton(), 5) )
		{
			click(AccountButton());
		}
		click(LogoutButton_react());
		return waitUntilDisplayed(Signup_Popup(), 60);

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

	public boolean orderPresentInMyOrders(List<String> neworderID,String lastOrderID , String plaform) throws IllegalAccessException, Throwable
	{
		boolean orderPresent=false;
		navigateToMyOrders();
		String topOrderID=null;
		int counter=0;
		topOrderID=getLastOrder(plaform);

		if((neworderID==null || neworderID.isEmpty()|| neworderID.size()==0 )&& ! topOrderID.equals(lastOrderID))
		{
			TestListner.testing.get().log(LogStatus.INFO,"Order ID : "+topOrderID+" found in My Orders");
			return true;
		}
		else
		{
			while (counter<30)
			{
				if(((neworderID==null || neworderID.isEmpty()|| neworderID.size()==0 )&&topOrderID.equals(lastOrderID)) ||  neworderID.toString().contains(topOrderID))
				{
					orderPresent=true;
					TestListner.testing.get().log(LogStatus.INFO,"Order ID : "+neworderID+" found in My Orders");
					break;
				}
				else
				{
					Thread.sleep(30000);
					driver.navigate().refresh();
					topOrderID=getLastOrder(plaform);
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

	public String getLastOrder(String platform) throws IllegalAccessException, Throwable
	{
		if(platform.contains("nykaaman"))
		{
				waitForPagetoLoad();
				navigateToMyOrders();
				waitForPagetoLoad();
				Thread.sleep(7000);
				try {
				driver.findElement(By.xpath("//h4[contains(@class,'no-order')]")).isDisplayed();
				
					return "1";
				}
				catch(Exception e)
				{
				waitUntilDisplayed(OrderList_New(), 60);
				List<WebElement>orders=getWebElements(OrderList_New());
				Locator TopOrderLocator = new Locator(By.xpath(getAbsoluteXPath(orders.get(0))),"Order");
				Locator OrderIDLocator=TopOrderLocator.concatLocator(extendedOrderLocator());
				String topOrderID=geteText(OrderIDLocator.getBy()).replaceAll("[^0-9]", "");
				return topOrderID;
				}
			
		}
		else
		{
		navigateToMyOrders();
		if(isDisplayed(noOrder()))
		{
			return "1";
		}
		waitUntilDisplayed(OrderList_New(), 60);
		List<WebElement>orders=getWebElements(OrderList_New());
		Locator TopOrderLocator = new Locator(By.xpath(getAbsoluteXPath(orders.get(0))),"Order");
		Locator OrderIDLocator=TopOrderLocator.concatLocator(extendedOrderLocator());
		String topOrderID=geteText(OrderIDLocator.getBy()).replaceAll("[^0-9]", "");
		return topOrderID;
		}
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
		List<WebElement> productsInOrder=getAllElements(TopOrderLocator.concatLocator(ProductList()).getBy());
		for(ProductDetailData data:productObjectList)
		{
			if(data!=null)
			{
				TestListner.testing.get().log(LogStatus.INFO,"Expected:  "+data.getProductName()+" ::Rs. "+data.getProductPrize() );
				for(WebElement nameOfProduct:productsInOrder)
				{
					String name=geteText(new Locator(By.xpath(getAbsoluteXPath(nameOfProduct)),"Name of Product").concatLocator(ProductName_extended()).getBy());
					String price=geteText(new Locator(By.xpath(getAbsoluteXPath(nameOfProduct)),"Name of Product").concatLocator(Price_extended()).getBy()).replaceAll("[^0-9]", "");

					if(!name.contains("Free") && name.contains(data.getProductName().replace("...", "")))
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


		return flag;

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
				Locator orderStatus=TopOrderLocator.concatLocator(StatusOfOrder_New("Cancelled"));
				if(!waitUntilDisplayed(orderStatus,10))
				{ 
					List<WebElement> productsInOrder=getAllElements(TopOrderLocator.concatLocator(ProductList()).getBy());
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

					Locator cancelLocator=TopOrderLocator.concatLocator(CancelItemsLink());

					isLatest=true;
					TestListner.testing.get().log(LogStatus.INFO,"Cancel items in :  "+geteText(OrderIDLocator.getBy()) );
					TestListner.testing.get().log(LogStatus.INFO,"Products in Order:  "+nameList);
					TestListner.testing.get().log(LogStatus.INFO,"Products to be cancelled:  "+itemsToCancel);
					bringElementIntoView(cancelLocator);
					click(cancelLocator);
					String SucessMSG = "test";
					if(waitUntilPresent(CancelReasonDropDown(), 10))
					{
						List<WebElement> Item_List=getAllElements(ItemList().getBy());
						//to not cancel last item
						Item_List.remove(Item_List.size()-1);
						for(WebElement Item:Item_List)
						{
							Locator selectItem= new Locator(By.xpath(getAbsoluteXPath(Item)),"Item");
							Locator checkbox=selectItem.concatLocator(itemCheckboxes());
							Locator reasonDropDown=selectItem.concatLocator(CancelReasonDropDown());
							bringElementIntoView(checkbox);
							click(checkbox);
							Select sel=null;
							Thread.sleep(4000);
							List<WebElement> selectDropDown = driver.findElements(reasonDropDown.getBy());
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
									SucessMSG = cancelSuccessfulMsg().toString();
									click(doneButton());
									isCancelled=true;
								}
								break;
							}
						}
						if(SucessMSG.contains("Your cancellation request has been taken")) {
							verifyItemStatusCancelled();
							Thread.sleep(5000);
						}
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

	public void verifyItemStatusCancelled() throws InterruptedException, IllegalAccessException {
		waitUntilDisappear(myOrderLoader());
		List<WebElement> orders=getWebElements(OrderList_New());
		Locator TopOrderLocator = new Locator(By.xpath(getAbsoluteXPath(orders.get(0))),"Order");
		List<WebElement> productsInOrder=getAllElements(TopOrderLocator.concatLocator(ProductList()).getBy());
		productsInOrder.remove(productsInOrder.size()-1);
		for(WebElement nameOfProduct: productsInOrder)
		{
			if(getAttribute(nameOfProduct, "class").contains("cancelled"))
			{
				TestListner.testing.get().log(LogStatus.INFO,"Cancelled Tag displayed on : "+geteText(new Locator(By.xpath(getAbsoluteXPath(nameOfProduct)),"Name of Product").concatLocator(ProductName_extended()).getBy()));
			}
			else
			{
				TestListner.testing.get().log(LogStatus.FAIL,"Cancelled Tag not displayed on : "+geteText(new Locator(By.xpath(getAbsoluteXPath(nameOfProduct)),"Name of Product").concatLocator(ProductName_extended()).getBy()));
			}
		}
	}
}