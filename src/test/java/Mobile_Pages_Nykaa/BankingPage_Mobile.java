package Mobile_Pages_Nykaa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Locator;



public class BankingPage_Mobile extends BrowserAction{
	
	public BankingPage_Mobile(WebDriver driver) {
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
		return new Locator(By.xpath("//div[@class='logintabs']"),"NetBanking LoginText");
	}
	
	Locator NetBankingCustomerLoginNykaaMan(){
		return new Locator(By.xpath("//form[@class='login']//input[@class='input_password']"),"NetBanking LoginText");
	}
	
	
	
	Locator PayUTransactinAmt()
	{
		return new Locator(By.xpath("//span[@id='DisplayAmount']"),"PayU Transaction Amount");
	}
	
	public  boolean isOTPofHDFCpresent() throws Throwable{
		System.out.println("Waiting for credit card page to load");
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
