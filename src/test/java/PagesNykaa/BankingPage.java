package PagesNykaa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Locator;



public class BankingPage extends BrowserAction{
	
	public BankingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	Locator PayerFormOTP()
	{
		return new Locator(By.xpath("//label[text()='Bank of Baroda']"),"Payer form BOB");
		//return new Locator(By.xpath("//div[@id='c-container']/descendant::input[@id='submitBtn']"),"Payer form HDFC");
		//return new Locator(By.xpath("//*[@class='payerForm']/descendant::td[contains(text(),'OTP') or contains(text(),'Please re-attempt using a new')]"),"Payer Otp");
	}
	
	Locator HDFCFrame(){
		return new Locator(By.name("bottom_frame"), "Bottom Frame");
	}
	Locator NetBankingCustomerLogin(){
		return new Locator(By.xpath("//input[@class='input_password'][@name='fldLoginUserId']"),"NetBanking LoginText");
	}
	
	Locator PayUTransactinAmt()
	{
		return new Locator(By.xpath("//span[@id='totalAmount']"),"PayU Transaction Amount");
	}
	
	public  boolean isOTPofHDFCpresent() throws Throwable{
		return waitUntilDisplayed(PayerFormOTP(), 60);
		 
		 
	 }
	public boolean isNetbankingPageDisplayed()
	{
		
		return waitUntilDisplayed(NetBankingCustomerLogin(), 4);
		
	}
	
	
	public  boolean isPayUPage() throws Throwable{
		return waitUntilDisplayed(PayUTransactinAmt(), 60);
		 
	 }
	
	

}
