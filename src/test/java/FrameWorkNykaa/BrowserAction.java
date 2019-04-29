package FrameWorkNykaa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import PagesNykaa.CartPage_React;



/**
 * @author nevil
 *
 */

public class BrowserAction {

	private static final String Text = null;
	protected WebDriver driver;
	static int DefaultTime = 60;






	protected void waitforPageReady() {

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver browser) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 60);
		try {
			wait.until(expectation);
		} catch (Throwable error) {
			error.printStackTrace();

		}
	}


	public List<WebElement> getWebElements(Locator locator) {
		waitUntilDisplayed(locator,5);
		List<WebElement> webElementList = driver.findElements(locator.getBy());
		if (webElementList.isEmpty()) {
			Reporter.log("Viewed all list of " + locator.getName() + "");
		} else {
			Reporter.log("Unable to view list of " + locator.getName() + "");
		}
		return webElementList;
	}

	protected String getTextFromWebelement(WebElement webElement, Locator ExtendedLocatorForText) {

		return webElement.findElement(ExtendedLocatorForText.getBy()).getText();

	}

	protected String getTextFromWebelement(WebElement webElement) {

		return webElement.getText();

	}

	protected String getText(WebElement element){
		String Text = "No data";
		try {
			WebDriverWait wait = new WebDriverWait(driver, DefaultTime);
			wait.until(ExpectedConditions.visibilityOf(element));
			Text = element.getText();
			System.out.println(Text);
			return Text;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Text;

	}



	protected void ScrollDown(String pixel) throws Throwable{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,"+pixel+")", "");
		Thread.sleep(2000);
		Reporter.log("Scrolled Down the Page",true);
	}

	protected void EnterValue(Locator locator, String value) {
		WebDriverWait webdriverWait = new WebDriverWait(driver, 60);
		webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
		driver.findElement(locator.getBy()).sendKeys(value);
		Reporter.log("Entered value  '" + value + "' in '" + locator.getName() + "'",true);
		TestListner.testing.get().log(LogStatus.INFO,"Entered value  '" + value + "' in '" + locator.getName() + "'");
	}
	
	protected void EnterValueJScript(Locator locator, String value) {
		WebDriverWait webdriverWait = new WebDriverWait(driver, 60);
		webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
	WebElement element =	driver.findElement(locator.getBy());
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].value=arguments[1];", element ,value);
		Reporter.log("Entered value  '" + value + "' in '" + locator.getName() + "'",true);
		TestListner.testing.get().log(LogStatus.INFO,"Entered value  '" + value + "' in '" + locator.getName() + "'");
	}
	
	protected void ClearValue(Locator locator) {
		WebDriverWait webdriverWait = new WebDriverWait(driver, 60);
		webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
		driver.findElement(locator.getBy()).clear();
		Reporter.log("Cleared value from '" + locator.getName() + "'",true);
	}

	protected void keyBoard(Locator locator,Keys key){
		WebDriverWait webdriverWait = new WebDriverWait(driver, 60);
		webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
		driver.findElement(locator.getBy()).sendKeys(key);
		System.out.println("Keyboard Enter on "+ locator.getName() + "");
		Reporter.log("Keyboard Enter on " + locator.getName() + "");

	}


	protected void click(By by) throws InstantiationException, IllegalAccessException {
		WebDriverWait webdriverWait = new WebDriverWait(driver, DefaultTime);

		webdriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
		driver.findElement(by).click();

		Field[] fields = this.getClass().getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);
			if (by.equals(field.get(this))) {
				System.out.println("Clicked On " + field.getName().replace("_", " ") + "");
				Reporter.log("Clicked On " + field.getName().replace("_", " ") + "");
			}
		}
	}

	/**
	 * @param webElement
	 * @param ExtendedLocator
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	protected void click(WebElement webElement, Locator ExtendedLocator)
			throws InstantiationException, IllegalAccessException {
		WebDriverWait webdriverWait = new WebDriverWait(driver, DefaultTime);
		WebElement subWebElement = webElement.findElement(ExtendedLocator.getBy());
		webdriverWait.until(ExpectedConditions.elementToBeClickable(subWebElement));
		subWebElement.click();
		System.out.println("Clicked On " + ExtendedLocator.getName() + "");
		Reporter.log("Clicked On " + ExtendedLocator.getName() + "");
	}

	protected void click(WebElement webElement, String locatorname)
			throws InstantiationException, IllegalAccessException {
		WebDriverWait webdriverWait = new WebDriverWait(driver, DefaultTime);

		webdriverWait.until(ExpectedConditions.visibilityOf(webElement));
		webElement.click();
		System.out.println("Clicked On " + locatorname + "");
		Reporter.log("Clicked On " + locatorname + "");
		TestListner.testing.get().log(LogStatus.INFO,"Clicked On " + locatorname);
	}


	protected void click(Locator locator) throws InstantiationException, IllegalAccessException {


		WebDriverWait webdriverWait = new WebDriverWait(driver, DefaultTime);
		webdriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator.getBy()));
		driver.findElement(locator.getBy()).click();
		Reporter.log("Clicked On " + locator.getName() + "",true);
		TestListner.testing.get().log(LogStatus.INFO,"Clicked On " + locator.getName());


	}

	protected void clickJScript(Locator locator)
	{
		try {
			WebElement element = driver.findElement(locator.getBy());
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);

			System.out.println("Clicked On " + locator.getName() + "");
			Reporter.log("Clicked On " + locator.getName() + "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void KeyBoard(Locator locator,Keys keys){
		try {
			WebDriverWait webdriverWait = new WebDriverWait(driver, DefaultTime);
			//webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
			driver.findElement(locator.getBy()).sendKeys(keys);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void KeyBoard(Keys key)
	{
		Actions action = new Actions(driver);
		action.sendKeys(key).build().perform();

	}

	protected void clickandNavigateWindow(Locator locator, String WindowTitle,String environmentURL) throws InstantiationException, IllegalAccessException, Throwable {
		WebDriverWait webdriverWait = new WebDriverWait(driver, DefaultTime);
		webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.findElement(locator.getBy()).click();
		System.out.println(locator.getBy());
		Set<String> windows = null;
		for(int i = 0;i<=2;i++)
		{
			Thread.sleep(4000);
			windows = driver.getWindowHandles();
			if(windows!=null)
				if(windows.size()!=0)
				{
					break;
				}
		}
		Reporter.log("Clicked On " + locator.getName() + "",true);
		TestListner.testing.get().log(LogStatus.INFO, "Clicked On " + locator.getName());

		for (String window : windows) {
			if (driver.switchTo().window(window).getTitle().toLowerCase().contains(WindowTitle.toLowerCase())) 
			{  
				waitforPageReady();
				if (! TestRunner.isLive &&   driver.getCurrentUrl().contains("http://www.nykaa.com")) {
					driver.get(driver.getCurrentUrl().replace("http://www.nykaa.com", environmentURL));
				}
				driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
				Reporter.log("Swithed On " + WindowTitle + " Page",true);
				TestListner.testing.get().log(LogStatus.INFO,"Swithed On " + WindowTitle + " Page");
				//break;
			}
		}

	}


	protected void clickandNavigateWindow(WebElement locator, String WindowTitle,String LocatorName,String environmentURL) throws InstantiationException, IllegalAccessException, Throwable {
		WebDriverWait webdriverWait = new WebDriverWait(driver, DefaultTime);
		webdriverWait.until(ExpectedConditions.elementToBeClickable(locator));
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		locator.click();
		Set<String> windows = null;
		for(int i = 0;i<=2;i++)
		{
			Thread.sleep(4000);
			windows = driver.getWindowHandles();
			if(windows!=null)
				if(windows.size()!=0)
				{
					break;
				}
		}
		Reporter.log("Clicked On " + LocatorName + "",true);
		TestListner.testing.get().log(LogStatus.INFO, "Clicked On " + LocatorName);

		for (String window : windows) {
			if (driver.switchTo().window(window).getTitle().toLowerCase().contains(WindowTitle.toLowerCase())) 
			{  
				waitforPageReady();
				if (! TestRunner.isLive &&   driver.getCurrentUrl().contains("http://www.nykaa.com")) {
					driver.get(driver.getCurrentUrl().replace("http://www.nykaa.com", environmentURL));
				}
				driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
				Reporter.log("Swithed On " + WindowTitle + " Page",true);
				TestListner.testing.get().log(LogStatus.INFO,"Swithed On " + WindowTitle + " Page");
				break;
			}
		}

	}



	public void waitForPagetoLoad() {
		new WebDriverWait(driver, 60).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}       

	public void WaitForAllElementsVisble(Locator locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, DefaultTime);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				int elementCount = driver.findElements(locator.getBy()).size();
				if (elementCount > 1)
					return true;
				else
					return false;
			}
		});

	}


	public boolean waitForJstoLoad() {

		WebDriverWait wait = new WebDriverWait(driver, 60);

		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					return ((JavascriptExecutor)driver).executeScript("return jQuery.active").toString().equals("0");
					//	return true;
				}
				catch (Exception e) {
					return false;
				}
			}
		};

		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver).executeScript("return document.readyState")
						.toString().equals("complete");
			}
		};

		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}



	public void waitUntilPageBlockDisappear(WebDriver driver) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 90);
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[@style='overflow:
			// auto;']")));
			wait.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath("//div[@id='pageblock'][contains(@style,'block')]")));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}

	protected List<WebElement> getAllElements(By by) {
		return driver.findElements(by);

	}

	protected String getAttribute(Locator locator,String Attribute){

		WebElement element = driver.findElement(locator.getBy());
		String attributevalue= element.getAttribute(Attribute);


		return attributevalue;


	}

	protected String getAttribute(WebElement locator,String Attribute){

		String attributevalue= locator.getAttribute(Attribute);


		return attributevalue;


	}

	protected boolean isAlertPresent(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			WebDriverWait ww2 = new WebDriverWait(driver, 4);
			ww2.until(ExpectedConditions.alertIsPresent());
			String text = driver.switchTo().alert().getText();
			driver.switchTo().defaultContent();
			Reporter.log("Alert with text "+text+" displayed");
			return true;
		} // try
		catch (Exception Ex) {

			Reporter.log("Alert not displayed");
			return false;
		} // catch
		finally {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}

	protected void dismissAlert()
	{
		try {
			if(isAlertPresent(driver))
				driver.switchTo().alert().dismiss();
			Reporter.log("Alert closed");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Reporter.log("unable to close alert");
			e.printStackTrace();
		}
	}

	protected WebElement getExtendedWebelement(WebElement element,Locator extendedLocator)
	{
		return element.findElement(extendedLocator.getBy());

	}

	public void waitUntilElementDisappear(By by) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			WebDriverWait wait = new WebDriverWait(driver, DefaultTime);
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[@style='overflow:
			// auto;']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}

	public void waitUntilDisappear(Locator locator) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			WebDriverWait wait = new WebDriverWait(driver, DefaultTime);
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[@style='overflow:
			// auto;']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator.getBy()));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}

	public boolean isDisappear(Locator locator,int time) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[@style='overflow:
			// auto;']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator.getBy()));
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}

	}


	protected void switchToDefaultContent(){

		driver.switchTo().defaultContent();

	}
	protected void bringElementIntoView(Locator locator) throws IllegalArgumentException, IllegalAccessException, Throwable {
		try {
			WebElement webElement = driver.findElement(locator.getBy());
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", webElement);
			Thread.sleep(2000);

			TestListner.testing.get().log(LogStatus.INFO,"Scrolled " + locator.getName() + " into view.");
			System.out.println("Scrolled " + locator.getName() + "Into view");
			Reporter.log("Scrolled " + locator.getName() + "Into view");
		}catch (Exception E)
		{
			Reporter.log("Unable to Scroll " + locator.getName() + "Into view");
		}

		/*	Field[] fields = this.getClass().getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);
			if (locator.equals(field.get(this))) {
				System.out.println("Scrolled " + field.getName().replace("_", " ") + "Into view");
				Reporter.log("Scrolled " + field.getName().replace("_", " ") + "Into view");
			}
		}*/
	}

	protected void bringElementIntoViewWebElement(WebElement webElement) throws IllegalArgumentException, IllegalAccessException, Throwable {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", webElement);
			Thread.sleep(2000);

		}catch (Exception E)
		{
		}

		/*	Field[] fields = this.getClass().getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);
			if (locator.equals(field.get(this))) {
				System.out.println("Scrolled " + field.getName().replace("_", " ") + "Into view");
				Reporter.log("Scrolled " + field.getName().replace("_", " ") + "Into view");
			}
		}*/
	}


	protected void Wait(int sec) throws Throwable
	{
		Thread.sleep(sec*1000);
	}


	protected String getText(Locator locator) throws IllegalArgumentException, IllegalAccessException {
		String Text = "No data";
		try {
			WebDriverWait wait = new WebDriverWait(driver, DefaultTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator.getBy()));
			WebElement webElement = driver.findElement(locator.getBy());
			Text = webElement.getText();

			return Text;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Text;

	}

	protected String geteText(By by) throws IllegalArgumentException, IllegalAccessException {

		WebDriverWait wait = new WebDriverWait(driver, DefaultTime);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		WebElement webElement = driver.findElement(by);
		String Text = webElement.getText();
		/*Field[] fields = this.getClass().getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);
			if (locator.getBy().equals(field.get(this))) {
				System.out.println("Fetched Value " + Text + " From :" + field.getName().replace("_", " ") + "");
				Reporter.log("Fetched Value " + Text + " From :" + field.getName().replace("_", " ") + "");
			}
		}*/
		return Text;

	}

	protected boolean waitUntilDisplayed(Locator locator, int Timeout) {
		driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
		WebDriverWait ww = new WebDriverWait(driver, Timeout);
		try {
			ww.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
		}
	}

	protected boolean waitUntilPresent(Locator locator, int Timeout) {
		driver.manage().timeouts().implicitlyWait(Timeout, TimeUnit.SECONDS);
		WebDriverWait ww = new WebDriverWait(driver, Timeout);
		try {
			ww.until(ExpectedConditions.presenceOfElementLocated(locator.getBy()));
			return true;
		} catch (Exception e) {
			//e.printStackTrace();

			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
		}
	}


	protected String getXPath(WebElement element) { 
		String jscript = "function getPathTo(node) {" + 
				"  var stack = [];" + 
				"  while(node.parentNode !== null) {" + 
				"    stack.unshift(node.tagName);" + 
				"    node = node.parentNode;" + 
				"  }" + 
				"  return stack.join('/');" + 
				"}" + 
				"return getPathTo(arguments[0]);"; 
		return (String) ((JavascriptExecutor) driver).executeScript(jscript, element); 
	} 


	public  String getAbsoluteXPath(WebElement element)
	{ try{
		return (String) ((JavascriptExecutor) driver).executeScript(
				"function absoluteXPath(element) {"+
						"var comp, comps = [];"+
						"var parent = null;"+
						"var xpath = '';"+
						"var getPos = function(element) {"+
						"var position = 1, curNode;"+
						"if (element.nodeType == Node.ATTRIBUTE_NODE) {"+
						"return null;"+
						"}"+
						"for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling){"+
						"if (curNode.nodeName == element.nodeName) {"+
						"++position;"+
						"}"+
						"}"+
						"return position;"+
						"};"+

    "if (element instanceof Document) {"+
    "return '/';"+
    "}"+

    "for (; element && !(element instanceof Document); element = element.nodeType ==Node.ATTRIBUTE_NODE ? element.ownerElement : element.parentNode) {"+
    "comp = comps[comps.length] = {};"+
    "switch (element.nodeType) {"+
    "case Node.TEXT_NODE:"+
    "comp.name = 'text()';"+
    "break;"+
    "case Node.ATTRIBUTE_NODE:"+
    "comp.name = '@' + element.nodeName;"+
    "break;"+
    "case Node.PROCESSING_INSTRUCTION_NODE:"+
    "comp.name = 'processing-instruction()';"+
    "break;"+
    "case Node.COMMENT_NODE:"+
    "comp.name = 'comment()';"+
    "break;"+
    "case Node.ELEMENT_NODE:"+
    "comp.name = element.nodeName;"+
    "break;"+
    "}"+
    "comp.position = getPos(element);"+
    "}"+

    "for (var i = comps.length - 1; i >= 0; i--) {"+
    "comp = comps[i];"+
    "xpath += '/' + comp.name.toLowerCase();"+
    "if (comp.position !== null) {"+
    "xpath += '[' + comp.position + ']';"+
    "}"+
    "}"+

    "return xpath;"+

"} return absoluteXPath(arguments[0]);", element);
	}catch(Exception e){
		e.printStackTrace();
		return null;
	}
	}





	protected void selectFromDropDownByValue(Locator locator, String Value)
			throws InstantiationException, IllegalAccessException {
		Select select = new Select(driver.findElement(locator.getBy()));
		select.selectByValue(Value);
		System.out.println("Selected value: "+Value+ "From :"+locator.getName() +"Dropdown");
		Reporter.log("Selected value: "+Value+ "From :"+locator.getName() +"Dropdown");
		/*Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if (by.equals(field.get(this))) {
				System.out.println("Selected value: " + Value + " From :" + field.getName().replace("_", " ") + "");
				Reporter.log("Selected value: " + Value + " From :" + field.getName().replace("_", " ") + "");
			}
		}*/
	}

	protected void mouseOver(WebElement webElement, Locator ExtendedLocator) throws InterruptedException {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(webElement).build().perform();
			Thread.sleep(1000);
			action.moveToElement(webElement).build().perform();
			Thread.sleep(1000);
			Reporter.log("Hovered mouse on " + getTextFromWebelement(webElement, ExtendedLocator) + "");

		} catch (Exception e) {
			Reporter.log("Unable to hover " + getTextFromWebelement(webElement, ExtendedLocator) + "");
		}
	}

	protected void mouseOver(Locator locator) throws InterruptedException {
		try {
			waitUntilDisplayed(locator,4);
			Actions action = new Actions(driver);
			WebElement webElement = driver.findElement(locator.getBy());
			action.moveToElement(webElement).build().perform();
			Thread.sleep(1000);
			action.moveToElement(webElement).build().perform();
			Thread.sleep(1000);
			/*webElement.click();*/
			Reporter.log("Hovered mouse on " + locator.getName() + "",true);
			TestListner.testing.get().log(LogStatus.INFO,"Hovered mouse on '" + locator.getName() + "'");

		} catch (Exception e) {
			Reporter.log("Unable to hover " + locator.getName() + "",true);

		}
	}



	protected void selectFromDropDownBox(Locator locator, String value) {
		try {
			Select dropdown = new Select(driver.findElement(locator.getBy()));
			//List<WebElement> OptionList = dropdown.getOptions();
			dropdown.selectByVisibleText(value);
			Reporter.log(value + "picked from Dropdown");
		} catch (Exception e) {
			Reporter.log("unable to picked " + value + " from Dropdown");
		}
	}

	protected void selectFromRadioOptions(Locator locator, String value) throws Throwable {
		try {
			bringElementIntoView(locator);
			for(WebElement option:getAllElements(locator.getBy()))
			{
				System.out.println(option.getText());
				if(option.getText().equalsIgnoreCase(value))
				{
					bringElementIntoViewWebElement(option);
					click(option, option.getText()+" sort option");
					break;
				}
			}
			Reporter.log(value + "selected from radio options");
			TestListner.testing.get().log(LogStatus.INFO,value + " selected from radio options");
		} catch (Exception e) {
			Reporter.log("unable to pick " + value + " from Dropdown");
		}
	}

	protected void selectFromDropDownBoxByIndex(Locator locator, int value) {
		try {
			Select dropdown = new Select(driver.findElement(locator.getBy()));
			List<WebElement> OptionList = dropdown.getOptions();
			dropdown.selectByIndex(value);
			Reporter.log(OptionList.get(1).getText() + "picked from  " + locator.getName() + "Dropdown");
		} catch (Exception e) {
			Reporter.log("unable to picked " + value + " from Dropdown");
		}
	}

	protected void selectDefaultValueFromDropDownBox(Locator locator) {

		try {
			waitUntilDisplayed(locator, 7);
			Select dropdown = new Select(driver.findElement(locator.getBy()));
			List<WebElement> OptionList = dropdown.getOptions();
			int size = OptionList.size();
			dropdown.selectByVisibleText(OptionList.get(1).getText());
			Reporter.log(OptionList.get(1).getText() + "picked from  " + locator.getName() + "Dropdown");
		} catch (Exception e) {

			Reporter.log("Unable to picked value from " + locator.getName() + " Dropdown");
		}
	}

	protected boolean isPresent(Locator locator) {
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		try {
			driver.findElement(locator.getBy());
			
			/*
			 * ww2.until(ExpectedConditions.textToBePresentInElementLocated(By.
			 * className("exceed_message"),
			 * "Sorry, this fast selling product is currently out of stock."));
			 */
			return true;
		} // try
		catch (Exception Ex) {
			return false;
		} // catch
		finally {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	}

	protected boolean isDisplayed(Locator locator) {
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		try {
			WebDriverWait ww2 = new WebDriverWait(driver, 4);
			System.out.println(locator.getBy().toString());
			ww2.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
			return true;
		} // try
		catch (Exception Ex) {
			return false;
		} // catch
		finally {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}




	protected boolean SwitchToFrameIFAvaialble(Locator locator) {
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		WebDriverWait ww = new WebDriverWait(driver, 10);
		try

		{
			ww.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator.getBy()));
			// ww.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("__ta_notif_frame_0"));
			// driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
			Reporter.log("Switched To frame "+locator.getName()+"",true);
			// ebklipper-publisher-widget-container-notification-container
			return true;
		} // try
		catch (Exception Ex) {
			//Ex.printStackTrace();
			Reporter.log("Unable to Switch to frame "+locator.getName()+"",true);
			return false;
		} // catch
		finally {

			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}

	}
	protected boolean SwitchToFrameIFAvaialble(Locator locator,int time) {
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		WebDriverWait ww = new WebDriverWait(driver, time);
		try

		{
			ww.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator.getBy()));
			// ww.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("__ta_notif_frame_0"));
			// driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
			Reporter.log("Switched To frame "+locator.getName()+"",true);
			// ebklipper-publisher-widget-container-notification-container
			return true;
		} // try
		catch (Exception Ex) {
			//Ex.printStackTrace();
			Reporter.log("Unable to Switch to frame "+locator.getName()+"",true);
			return false;
		} // catch
		finally {

			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}

	}

	public void closeMissedOfferFrame() {
		try
		{
			Locator locator=new Locator(By.xpath("//iframe[@id='__ta_notif_frame_2']"),"Missed Best Offers Frame");
			Locator closeButton= new Locator(By.xpath("//div[@class='close']"),"Frame close button");
			if(isDisplayed(locator))
			{
				driver.switchTo().frame(driver.findElement(locator.getBy()));
				click(closeButton.getBy());
				driver.switchTo().defaultContent();
			}
		}
		catch(Exception e)
		{

		}
	}

	public void closeGetAppPopUp() {
		Locator frame=new Locator(By.id("branch-banner-iframe"), "Get App Frame");
		Locator closeButton=new Locator(By.xpath("//div[@id='branch-banner-close1']"), "Get App Close Button");
		if(SwitchToFrameIFAvaialble(frame))
		{
			try {
				if(isDisplayed(closeButton))
					click(closeButton);
				Thread.sleep(1000);
			}
			catch(Exception e) {}
		}
		switchToDefaultContent();
	}

	public void closeCart() throws InstantiationException, IllegalAccessException {
		try {
			
		if (waitUntilDisplayed(CartPage_React.BackMenuCart(), 3)) {
			click(CartPage_React.BackMenuCart());
			waitForPagetoLoad();
			Thread.sleep(1000);
		}
	}
	catch(Exception e) {}
	}
	
	public String replaceRuppeeSymbol(String str)
	{
		String refined = "";
		String tempString = "";

		char[] arr = str.toCharArray();
		tempString = str;
		for (int i = 0; i < arr.length; i++) 
		{
			String type = Character.UnicodeBlock.of(str.trim().charAt(i)).toString();
			if(type.trim().equalsIgnoreCase("CURRENCY_SYMBOLS"))
			{
				tempString = str.trim().replaceAll(String.valueOf(arr[i] ), "");
				break;
			}
		}
		refined = tempString.trim();
		return refined;
	}



public String removeSpaces(String actual)
{
	String productName = actual.toLowerCase();
	String temp[] = productName.split(" ");
	String newProductName = "";
	for(String s : temp)
	{
		newProductName=newProductName+s;
}
	return newProductName;
}


public String removeSpecialChar(String actual)
{
	String newProductName="";
	for(int i=0 ; i< actual.length() ; i++)
	{
		int asc = (int)actual.charAt(i);
		if(asc>=65&&asc<=90 || asc>=97&&asc<=122)
		{
			newProductName=newProductName+actual.charAt(i);
		}
	}
	return newProductName;
}


public String switchtocurrentenviornment(WebDriver driver,String Curl){

	   String [] arrOfStr2 = Curl.split("\\.", 2);
	   String str1 = driver.getCurrentUrl();
	   String [] arrOfStr1 = str1.split("\\.", 2);
	   String newurl = str1;
	   if(!arrOfStr2[0].equals(arrOfStr1[0]))
	   {
	      newurl = arrOfStr2[0]+"."+arrOfStr1[1];
	      System.out.println(newurl);
	   }
	   return newurl;
	}

}