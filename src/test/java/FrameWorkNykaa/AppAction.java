package FrameWorkNykaa;


import com.relevantcodes.extentreports.LogStatus;

import FrameWorkNykaa.TestListner;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author nevil
 *
 */

public class AppAction {

	protected AndroidDriver driver;

	static int DefaultTime = 60;

	protected String getAttribute(Locator locator,String Attribute){
		String attributevalue ;
		if(locator.getElement()==null) {
			WebElement element = driver.findElement(locator.getBy());
			attributevalue = element.getAttribute(Attribute);
		}else {
			System.out.println(locator.getBy());
			System.out.println(locator.getElement());
			WebElement element = locator.getElement().findElement(locator.getBy());
			attributevalue = element.getAttribute(Attribute);
		}
		return attributevalue;
	}

	protected void clickEnter(Locator locator){
		// driver.pressKeyCode(66);

		// WebElement element = driver.findElement(locator.getBy());
		// element.sendKeys(Keys.KEYCODE_NUMPAD_ENTER);

		Reporter.log("Enter Button pressed",true);
	}

	protected void EnterValue(Locator locator, String value) {

		WebDriverWait webdriverWait = new WebDriverWait(driver, 60);
		webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
		if(locator.getElement()==null) {
			driver.findElement(locator.getBy()).sendKeys(value);
		}else{
			locator.getElement().findElement(locator.getBy()).sendKeys(value);
		}
		Reporter.log("Entered value  '" + value + "' in '" + locator.getName() + "'",true);
		TestListner.testing.get().log(LogStatus.INFO,"Entered value  '" + value + "' in '" + locator.getName() + "'");
	}

	protected void click(Locator locator) throws InstantiationException, IllegalAccessException {

		WebDriverWait webdriverWait = new WebDriverWait(driver, DefaultTime);
		if (locator.getElement() == null) {
			webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
			driver.findElement(locator.getBy()).click();
		} else {
			System.out.println(locator.getBy());
			System.out.println(locator.getElement());
			WebElement element = locator.getElement().findElement(locator.getBy());
			webdriverWait.until(ExpectedConditions.elementToBeClickable(element));
			driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
			element.click();
		}
		Reporter.log("Clicked On " + locator.getName() + "", true);
		TestListner.testing.get().log(LogStatus.INFO,"Clicked On " + locator.getName() + "");
	}


	protected String getText(Locator locator){
		String Text = "No data";
		try {
			WebDriverWait wait = new WebDriverWait(driver, DefaultTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator.getBy()));
			if(locator.getElement()==null) {
				WebElement webElement = driver.findElement(locator.getBy());
				Text = webElement.getText();
			}else{
				WebElement webElement = locator.getElement().findElement(locator.getBy());
				Text = webElement.getText();
			}
			return Text;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Text;
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

	protected void switchToWebView(){
		Set<String> availableContexts = driver.getContextHandles();
		Reporter.log("Total No of Context Found After we reach to WebView = " + availableContexts.size(),true);
		for (String context : availableContexts) {
			if (context.contains("WEBVIEW")) {
				Reporter.log("Context Name is " + context, true);
				TestListner.testing.get().log(LogStatus.INFO, "Switched to "+context+"for WebView");
				driver.context(context);
				break;
			}
		}
	}

	protected void switchToNativeApp(){
		driver.context("NATIVE_APP");
		Reporter.log("Context switched to " + "NATIVE_APP",true);
		TestListner.testing.get().log(LogStatus.INFO, "Switched To Native App");
	}

	protected boolean waitUntilDisplayed(Locator locator, int Timeout) {
		WebDriverWait webdriverWait = new WebDriverWait(driver, Timeout);
		driver.manage().timeouts().implicitlyWait(Timeout,TimeUnit.SECONDS);
		try {
			if (locator.getElement() == null) {
				webdriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator.getBy()));
			} else {
				System.out.println(locator.getBy());
				System.out.println(locator.getElement());
				WebElement element = locator.getElement().findElement(locator.getBy());
				webdriverWait.until(ExpectedConditions.elementToBeClickable(element));
			}
			return true;

		}catch(Exception e){
			return false;
		}

		finally {
			driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
		}
	}

	public void bringElementIntoViewUp(Locator locator, int ScrollCount){
		Dimension dimensions = driver.manage().window().getSize();

//    System.out.println(dimensions);

		Double screenHeightStart = dimensions.getHeight() * 0.9;

		int scrollStart = screenHeightStart.intValue();

		Double screenHeightEnd = dimensions.getHeight() * 0.4;

		int scrollEnd = screenHeightEnd.intValue();
		int i = 1;
		while (!waitUntilDisplayed(locator, 3)) {
			driver.swipe(0, scrollEnd, 0, scrollStart, 1000);
			i++;
			if (i >= ScrollCount) {
				if(!locator.getName().contains("XYZZZ")) {
					//TestListner.testing.get().log(LogStatus.WARNING, "Not Able To Find " + locator.getName() + ".");
					Reporter.log("Not Able To Find " + locator.getName() + ".", true);
				}
			}
		}
		if(i>=2) {
			TestListner.testing.get().log(LogStatus.INFO, "Scrolled " + locator.getName() + " into view.");
			Reporter.log("Scrolled " + locator.getName() + " into view.", true);
		}
		return;
	}



	public boolean bringElementIntoViewDown(Locator locator, int ScrollCount) throws InterruptedException, IllegalAccessException, InstantiationException {


		Dimension dimensions = driver.manage().window().getSize();

		//System.out.println(dimensions);

		Double screenHeightStart = dimensions.getHeight() * 0.9;

		int scrollStart = screenHeightStart.intValue();

		Double screenHeightEnd = dimensions.getHeight() * 0.25;

		int scrollEnd = screenHeightEnd.intValue();
		int i = 1;
		while (!waitUntilDisplayed(locator, 3)) {
			driver.swipe(0, scrollStart, 0, scrollEnd, 1000);
			if (i >= ScrollCount) {
				if(!locator.getName().contains("XYZZ")) {
					//TestListner.testing.get().log(LogStatus.WARNING, "Not Able To Find " + locator.getName() + ".");
					Reporter.log("Not Able To Find " + locator.getName() + ".", true);
				}
				return false;
			}
			i++;
		}
		if(i>=1) {
			TestListner.testing.get().log(LogStatus.INFO, "Scrolled " + locator.getName() + " into view.");
			Reporter.log("Scrolled " + locator.getName() + " into view.", true);

		}
		return true;
	}

	public void ScrollDown(int ScrollCount) {
		Dimension dimensions = driver.manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.9;
		int scrollStart = screenHeightStart.intValue();
		Double screenHeightEnd = dimensions.getHeight() * 0.25;
		int scrollEnd = screenHeightEnd.intValue();
		for (int x = 1; x <= ScrollCount; x++) {
			driver.swipe(0, scrollStart, 0, scrollEnd, 1000);
		}
	}


	public void bringElementIntoViewHorizontally(Locator ToBeFind, Locator ScrollableArea,int ScrollCount) throws InterruptedException, IllegalAccessException, InstantiationException {

		WebElement element =  driver.findElement(ScrollableArea.getBy());
		int uppery = element.getLocation().getY();
		int lowery = uppery + element.getSize().getHeight();
		Dimension dimensions = driver.manage().window().getSize();
		int width = dimensions.getWidth();
		int y = (uppery + lowery)/2;
		int startx = (int) (width*0.75);
		int endx = (int) (width*0.35);
		int i=1;
		while (!waitUntilDisplayed(ToBeFind,3)) {
			driver.swipe(startx, y, endx, y, 1000);
			i++;
			if(i>=ScrollCount){
				break;
			}
		}
	}

	public void waitUntilElementDisappear(Locator locator,int Timeout) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			WebDriverWait wait = new WebDriverWait(driver, Timeout);
			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[@style='overflow:
			// auto;']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator.getBy()));
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}

	protected List<WebElement> getWebElements(Locator locator) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,5);
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator.getBy()));
		}
		catch (Exception e){
			Reporter.log(" list of element not displayed with locator " + locator.getName() + "",true);
		}

		List<WebElement> webElementList = driver.findElements(locator.getBy());
		System.out.println("size aahe "+webElementList.size());
		if (!webElementList.isEmpty()) {
			Reporter.log("Viewed all list of " + locator.getName() + "",true);
		} else {
			Reporter.log("Unable to view list of " + locator.getName() + "",true);
		}
		driver.manage().timeouts().implicitlyWait(DefaultTime,TimeUnit.SECONDS);
		return webElementList;
	}

	protected void ClearValue(Locator locator) {
		try {
			WebDriverWait webdriverWait = new WebDriverWait(driver, 60);
			webdriverWait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
			driver.findElement(locator.getBy()).clear();
			Reporter.log("Cleared value from '" + locator.getName() + "'", true);
			TestListner.testing.get().log(LogStatus.INFO, "Cleared the Text of " + locator.getName());
		}catch (Throwable e){
			e.printStackTrace();
		}
	}

	protected WebElement convertToWebElement(Locator locator){
		WebElement element = driver.findElement(locator.getBy());
		Reporter.log("Converted '"+locator.getName()+"' To WebElement");
		return element;
	}

	protected String clickRandomlyOnWebElements(Locator locator) throws InterruptedException {
		List<WebElement> elementList = getWebElements(locator);
		int a = ThreadLocalRandom.current().nextInt(0, elementList.size());
		String selection = getText(elementList.get(a));
		elementList.get(a).click();
		if ((a + 1) == 1 | (a + 1) == 21) {
			Reporter.log("Clicked On " + (a + 1) + "st Element", true);
			TestListner.testing.get().log(LogStatus.INFO, "Clicked On " + (a + 1) + "st Element");
		} else if ((a + 1) == 2 | (a + 1) == 22) {
			Reporter.log("Clicked On " + (a + 1) + "nd Element", true);
			TestListner.testing.get().log(LogStatus.INFO, "Clicked On " + (a + 1) + "nd Element");
		} else if ((a + 1) == 3 | (a + 1) == 23) {
			Reporter.log("Clicked On " + (a + 1) + "rd Element", true);
			TestListner.testing.get().log(LogStatus.INFO, "Clicked On " + (a + 1) + "rd Element");
		} else {
			Reporter.log("Clicked On " + (a + 1) + "th Element", true);
			TestListner.testing.get().log(LogStatus.INFO,"Clicked On " + (a + 1) + "th Element");
		}
		return selection;
	}

	protected String selectRandomlyOnWebElements(Locator locator) throws InterruptedException {
		List<WebElement> elementList = getWebElements(locator);
		int a = ThreadLocalRandom.current().nextInt(0, elementList.size());
		String selection = getText(elementList.get(a));
		if ((a + 1) == 1 | (a + 1) == 21) {
			Reporter.log("Selection On " + (a + 1) + "st Element", true);
			TestListner.testing.get().log(LogStatus.INFO,"Selection On " + (a + 1) + "st Element");
		} else if ((a + 1) == 2 | (a + 1) == 22) {
			Reporter.log("Selection On " + (a + 1) + "nd Element", true);
			TestListner.testing.get().log(LogStatus.INFO,"Selection On " + (a + 1) + "nd Element");
		} else if ((a + 1) == 3 | (a + 1) == 23) {
			Reporter.log("Selection On " + (a + 1) + "rd Element", true);
			TestListner.testing.get().log(LogStatus.INFO,"Selection On " + (a + 1) + "rd Element");
		} else {
			Reporter.log("Selection On " + (a + 1) + "th Element", true);
			TestListner.testing.get().log(LogStatus.INFO,"Selection On " + (a + 1) + "th Element");
		}
		return selection;
	}

	protected void androidBackButton() {
		driver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		Reporter.log("Pressed Physical Back Button", true);
		TestListner.testing.get().log(LogStatus.INFO,"Pressed Physical Back Button");
	}

	protected boolean isDisplayed(Locator locator){
		WebDriverWait webdriverWait = new WebDriverWait(driver, 1);
		driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
		try {
			if (locator.getElement() == null) {
				webdriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator.getBy()));
			} else {
				System.out.println(locator.getBy());
				System.out.println(locator.getElement());
				WebElement element = locator.getElement().findElement(locator.getBy());
				webdriverWait.until(ExpectedConditions.elementToBeClickable(element));
			}
			return true;

		}catch(Exception e){
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(DefaultTime, TimeUnit.SECONDS);
		}
	}
}