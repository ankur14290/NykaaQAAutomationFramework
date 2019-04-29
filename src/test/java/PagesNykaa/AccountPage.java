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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import DataNykaa.AccountData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Framework;
import FrameWorkNykaa.Locator;
import FrameWorkNykaa.LoginSync;
import FrameWorkNykaa.TestListner;



public class AccountPage extends BrowserAction {

	public AccountPage(WebDriver driver) {

		this.driver = driver;
	}


	Locator newRegister(){
		return new Locator(By.partialLinkText("Sign Up"),"New Regsiter");
	}

	Locator MyOrderLocator(){
		return new Locator(By.xpath("//div[@id='headerDropdown']//li/a/i[contains(@class, 'orders')]"),"My Order Tab");
	}
	Locator MyOrderLocator_Magento(){
		return new Locator(By.xpath("//div[@id='header_top']//li/a/span[contains(@class, 'orders')]"),"My Order Tab in magento");
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
		return new Locator(By.xpath("//select[contains(@id,'select_cancel')] | //select[contains(@class,'reason-select-box')]"),"Cancel Reason Drop Down");
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
	Locator StatusLocator()
	{
		return new Locator(By.xpath("//span[contains(@class,'my-order-status status-delivered')]"),"Status");
	}

	Locator CancelOrderLink()
	{
		return new Locator(By.xpath("//span[@class='my-order-cancel-link']"),"Cancel order");
	}

	Locator confirmationButton()
	{
		return new Locator(By.xpath("//a[text()='OK']"),"OK button");
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

		return new Locator(By.partialLinkText("Logout"),"LogOut Button");
	}

	Locator OrderList(){
		return new Locator(By.xpath("//div[@class='my-orders-lists-wrap']"),"Order List");
	}

	Locator OrderList_New(){
		return new Locator(By.xpath("//div[contains(@class,'my-order-details-container')]"),"Order List");
	}

	Locator extendedOrderLocator(){
		return new Locator(By.xpath("//p[contains(@class,'my-order-font-bold')][contains(text(),'ORDER')]"),"Order List");
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
	Locator Sign_Up_error_msg(){
		return new Locator(By.xpath("//div[@id='signup-error-msg']"),"Sign-up-Error-msg");
	}



	private Locator Email_textbox() {
		return new Locator(By.id("email"), "Email Textbox");
	}
	private Locator Password_textbox() {
		return new Locator(By.id("pass"), "Password Textbox");
	}
	private Locator Signin_Button() {
		return new Locator(By.id("send2"), "Signin Button");
	}
	private Locator Signup_Popup() {
		return new Locator(By.xpath("//span[@class='AccountText']"), "Signup Link");

	}

	private Locator FbContinueWithNewLogin(){
		return new Locator(By.xpath("//a[text()='Create New Account']"),"Create new Account");
	}


	Locator SignInWithFacebookButton()
	{
		return new Locator(By.xpath("//form[@id='login-form']/descendant::p[text()='Sign in with Facebook']"),"Sign in With Facebook Button");
	}


	Locator SignInWithGooglePlusButton()
	{
		return new Locator(By.xpath("//form[@id='login-form']/descendant::p[text()='Sign in with Google+']"),"Sign in With Google plus");
	}
	Locator GoogleEmailidField(){

		return new Locator(By.id("identifierId"),"Google Email id Field");
	}

	Locator Next_old(){
		return new Locator(By.xpath("//span[@class='RveJvd snByac']"),"Next button");
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
		return new Locator(By.xpath("//a[@id='userAccount']/descendant::span | //span[@class='AccountText']"), "Logged in UserName");
	}


	Locator MyWishlistNavigation(){
		return new Locator(By.xpath("//span[@class='nav_icon wishlist']"),"My wishlist Icon");
	}

	Locator Next(){
		return new Locator(By.xpath("//span[contains(text(), 'Next')]"),"Next button");
	}

	Locator AccountButton() {
		return new Locator(By.xpath("//div[@class ='UserAccount']"), "Account");
	}

	Locator captchaCheckbox()
	{
		return new Locator(By.xpath("//div[@class='recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked rc-anchor-checkbox']"),"Captcha Checkbox");
	}

	Locator LogoutButton_react(){

		return new Locator(By.xpath("//a/i[contains(@class,'log')]"),"LogOut Button");
	}
	public String signInWithYourEmail(AccountData accountData) throws IllegalAccessException, Throwable {
		if(!waitUntilDisplayed(Email_textbox(), 2)){
			waitForPagetoLoad();
			click(Signup_Popup());
		}
		waitForPagetoLoad();
		waitUntilDisplayed(Email_textbox(), 10);
		System.out.println(accountData.getUsername());
		EnterValue(Email_textbox(),accountData.getUsername());
		EnterValue(Password_textbox(),accountData.getPassword());
		click(Signin_Button());
		waitUntilDisappear(Email_textbox());
		waitUntilDisplayed(UserNameValue(), 10);
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
	public String NewRegister(AccountData accountData) throws Throwable
	{
		waitForPagetoLoad();
		//		if(!waitUntilDisplayed(Email_textbox(), 2)){
		click(Signup_Popup());
		//			}
		//click(SignIn());
		click(newRegister());
		EnterValue(FirstNameForRegister(),accountData.getFirstName());
		EnterValue(LastNameForRegister(),accountData.getLastName());
		Random random = new Random(System.currentTimeMillis());
		EnterValue(EmailAddressForRegistration(),accountData.getFirstName()+"."+accountData.getLastName()+random.nextInt()+"@mailinator.com");
		EnterValue(PasswordForRegistration(),accountData.getPassword());
		EnterValue(ConfirmationPasswordForRegistration(),accountData.getPassword());
		if(waitUntilDisplayed(captchaCheckbox(), 2))
		{
			click(captchaCheckbox());
		}
		click(SignUpforRegisterButton());
		waitForPagetoLoad();
		
		int counter=0;
		while(getText(UserNameValue()).equalsIgnoreCase("account") && counter<20||getText(UserNameValue()).equalsIgnoreCase("test") && counter<20)
		{
			Thread.sleep(1000);
			counter+=1;
		}
		Thread.sleep(5000);
		return getText(UserNameValue());
	}
	public String NewRegister_nonprod(AccountData accountData) throws Throwable
	{
		waitForPagetoLoad();
		Thread.sleep(5000);
		//		if(!waitUntilDisplayed(Email_textbox(), 2)){
		click(Signup_Popup());
		//			}
		//click(SignIn());
		click(newRegister());
		EnterValue(FirstNameForRegister(),accountData.getFirstName());
		EnterValue(LastNameForRegister(),accountData.getLastName());
		EnterValue(EmailAddressForRegistration(),accountData.getUsername());
		//Random random = new Random(System.currentTimeMillis());
		//EnterValue(EmailAddressForRegistration(),accountData.getFirstName()+"."+accountData.getLastName()+random.nextInt()+"@mailinator.com");
		EnterValue(PasswordForRegistration(),accountData.getPassword());
		EnterValue(ConfirmationPasswordForRegistration(),accountData.getPassword());
		if(waitUntilDisplayed(captchaCheckbox(), 2))
		{
			click(captchaCheckbox());
		}
		click(SignUpforRegisterButton());
		if(isDisplayed(Sign_Up_error_msg()))
		{
			click(closeIcon());
			return "User already registered";

		}
		waitForPagetoLoad();
		int counter=0;
		while(getText(UserNameValue()).equalsIgnoreCase("account") && counter<20)
		{
			Thread.sleep(5000);
			counter+=1;
		}
		Thread.sleep(5000);
		return getText(UserNameValue());
	}


	public boolean LogOut() throws Throwable, Throwable{

		//	click(UserProfileImage());
		if( driver instanceof ChromeDriver) {
			click(UserNameValue());
			click(UserNameValue());
		}else{
			click(UserNameValue());
		}

		waitUntilDisplayed(LogoutButton(), 10);
		click(LogoutButton());

		return waitUntilDisplayed(Signup_Popup(), 60);


	}

	public void navigateToMyOrders() throws InstantiationException, IllegalAccessException {
		while(! isDisplayed(MyOrderLocator()))
		{List<WebElement>usernames=getAllElements(UserNameValue().getBy());
		for(WebElement user:usernames)
		{
			if(user.isDisplayed())
				click(user,"UserName");
		}
		}
		click(MyOrderLocator());
		TestListner.testing.get().log(LogStatus.INFO,"Navigated to My Orders Screen");
		waitForPagetoLoad();
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

	public String signInWithGooglePlus(AccountData accountData) throws  Throwable {
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
	public void forGotPassword() {

	}
	public void createNewAccount(AccountData accountData) {




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
								if(waitUntilDisplayed(orderStatus, 30))
								{
									isCancelled=true;
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
		Thread.sleep(3000);
		if(isDisplayed(MyOrderLocator_Magento())){
			click(MyOrderLocator_Magento());
		}
		else{

		}
		Thread.sleep(10000);
		waitUntilDisplayed(AccountButton(),15);
		click(AccountButton());
		if (! waitUntilDisplayed(LogoutButton_react(), 5) )
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

	public boolean orderPresentInMyOrders(String neworderID,String lastOrderID) throws IllegalAccessException, Throwable
	{
		boolean orderPresent=false;
		navigateToMyOrders();
		String topOrderID=null;
		int counter=0;
		topOrderID=getLastOrder();

		if(neworderID==null && ! topOrderID.equals(lastOrderID))
		{
			TestListner.testing.get().log(LogStatus.INFO,"Order ID : "+topOrderID+" found in My Orders");
			return true;
		}
		else
		{
			while (counter<30)
			{
				if(topOrderID.equals(neworderID))
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
			}
			if(! orderPresent)
			{
			TestListner.testing.get().log(LogStatus.FAIL,"Order ID : "+neworderID+" not found in My Orders");
			}
		}

		return orderPresent;
	}

	public String getLastOrder() throws IllegalAccessException, Throwable
	{
		navigateToMyOrders();
		waitUntilDisplayed(OrderList_New(), 60);
		List<WebElement>orders=getWebElements(OrderList_New());
		Locator TopOrderLocator = new Locator(By.xpath(getAbsoluteXPath(orders.get(0))),"Order");
		Locator OrderIDLocator=TopOrderLocator.concatLocator(extendedOrderLocator());
		String topOrderID=geteText(OrderIDLocator.getBy()).replaceAll("[^0-9]", "");
		return topOrderID;
	}

}
