package PagesNykaa;

import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.relevantcodes.extentreports.LogStatus;

import DataNykaa.BeautyServiceData;
import DataNykaa.ProductDetailData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Locator;
import FrameWorkNykaa.TestListner;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class BeautyServicePage  extends BrowserAction {

	public BeautyServicePage(WebDriver driver) {
		this.driver = driver;

	}

	String selectedCity = "";
	String selectedLocation = "";


	Locator LoadingIcon() {
		return new Locator(By.id("loading"), "Loading Icon");
	}
	
	Locator CouponCodeTextBox(){
		return new Locator(By.id("coupon_code"),"Coupon Code TextBox");
	}
	
	Locator LocationPopup() {
		return new Locator(By.xpath("//div[@class='location-popup']"), "Location Popup");
	}

	Locator quickLink(String QuickLink) {
		return new Locator(By.xpath("//div[@class='quik1']/a[contains(text(),'"+QuickLink+"')]"), QuickLink + " of Quicklink");
	}

	Locator cityLocator(String CityName) {
		return new Locator(By.xpath("//div[@class='category-products content_inner']//div[@class='city-label'][text()='" + CityName + "']"), CityName + " City Label");
	}

	Locator closeButtonOfOOSMessage() {
		return new Locator(By.id("closelb"), "close button of OOS Message");
	}

	Locator ServicesLocator() {
		return new Locator(By.xpath("//div[contains(@id,'product-opt')]"), "services");
	}

	Locator ServiceNameText() {
		return new Locator(By.xpath("//div[@class='service_box']/h3/a"), "Service Name");
	}

	Locator BookNowButton(String ProductName) {
		return new Locator(By.xpath("//div[@class='category-products content_inner']//a[@title='" + ProductName + "']/ancestor::div[contains(@id,'product-opt')]//a[@class='book_now show-slidecart-button']"), "Book Now Button");
	}

	Locator ViewgalleryButton(String ProductName) {
		return new Locator(By.xpath("//a[@title='" + ProductName + "']/ancestor::div[contains(@id,'product-opt')]//a[@class='view_gallery']"), "View Gallery Button");
	}

	Locator ServiceDetail() {
		return new Locator(By.xpath("//h5[text()='Service Details:']/following-sibling::div[@class='service_det']"), "Service Detail");
	}


	Locator locationLocator(String Location) {
		return new Locator(By.xpath("//div[@class='location-popup']//a[@title='" + Location + "']"), Location + " Location");
	}

	Locator OutOfStockMessage() {
		return new Locator(By.xpath("//p[@id='exceed_message'][contains(text(),'out of stock')]"), "Out Of Stock Message");
	}

	Locator CloseGalleryButton() {
		return new Locator(By.xpath("//div[@class='overlay-content']/a[@class='closebtn']"), "Close Gallery Button");
	}


	private static ThreadLocal<Integer> subMenuIndex = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 1;
		}
	};
	private static ThreadLocal<Integer> initialProductCount = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};
	private static ThreadLocal<Integer> finalProductCount = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};


	Locator MenuLocator (String Menu)
	{
		return new Locator(By.xpath("//li[@class='menu-dropdown-icon']/a[text()='" + Menu + "']"), "" + Menu + " of Mainmenu");
	}

	Locator MainmenuLocator (String Mainmenu)
	{
		return new Locator(By.xpath("//div[@class='MegaDropdownHeading popUpsHeading1']/a[text()='" + Mainmenu + "']"), Mainmenu + " of Mainmenu");
	}

	Locator SubmenuLocator ()
	{
		int mainMenuSize = driver.findElements(By.xpath("//div[@id='BeautyServicesTab']/div/div")).size();
		int mainMenuIndex = (int) (Math.random() * mainMenuSize + 1);
		int subMenuSize = driver.findElements(By.xpath("//div[@id='BeautyServicesTab']/div/div[" + mainMenuIndex + "]/div[@class='glamnfitstoreBox megaDropdown']/ul/li")).size();
		subMenuIndex.set((int) (Math.random() * subMenuSize + 1));
		System.out.println(driver.findElement(By.xpath("//div[@id='BeautyServicesTab']/div/div[" + mainMenuIndex + "]/div[@class='glamnfitstoreBox megaDropdown']/ul/li[" + subMenuIndex.get() + "]")).getText());

		//because bridal is not clickable so adding this check
		System.out.println(driver.findElement(By.xpath("//div[@id='BeautyServicesTab']/div/div[" + mainMenuIndex + "]/div[@class='glamnfitstoreBox megaDropdown']/ul/li[" + subMenuIndex.get() + "]")).getText());
		if ((driver.findElement(By.xpath("//div[@id='BeautyServicesTab']/div/div[" + mainMenuIndex + "]/div[@class='glamnfitstoreBox megaDropdown']/ul/li[" + subMenuIndex.get() + "]")).getText() != null) && driver.findElement(By.xpath("//div[@id='BeautyServicesTab']/div/div[" + mainMenuIndex + "]/div[@class='glamnfitstoreBox megaDropdown']/ul/li[" + subMenuIndex.get() + "]")).getText().equalsIgnoreCase("Bridal")) {
			SubmenuLocator();
		}
		return new Locator(By.xpath("//div[@id='BeautyServicesTab']/div/div[" + mainMenuIndex + "]/div[@class='glamnfitstoreBox megaDropdown']/ul/li[" + subMenuIndex.get() + "]"), driver.findElement(By.xpath("//div[@id='BeautyServicesTab']/div/div[" + mainMenuIndex + "]/div[@class='glamnfitstoreBox megaDropdown']/ul/li[" + subMenuIndex.get() + "]")).getText());
	}

	Locator CityPopUp ()
	{
		return new Locator(By.xpath("//div[@class='city-content']"), "City PopUP");
	}

	Locator CityLocator ()
	{
		int cityCount = driver.findElements(By.xpath("//div[@class='city-content']/div")).size();
		int cityIndex = (int) (Math.random() * cityCount + 1);
		return new Locator(By.xpath("//div[@class='city-content']/div[" + cityIndex + "]"), driver.findElement(By.xpath("//div[@class='city-content']/div[" + cityIndex + "]")).getText());
	}

	Locator LocationLocator ()
	{
		int locationCount = driver.findElements(By.xpath("//div[@class='location-content']/div[2]/div")).size();
		int locationIndex = (int) (Math.random() * locationCount + 1);
		return new Locator(By.xpath("//div[@class='location-content']/div[2]/div[" + locationIndex + "]"), driver.findElement(By.xpath("//div[@class='location-content']/div[2]/div[" + locationIndex + "]")).getText());
	}



	Locator selectedLocationLocator ()
	{
		return new Locator(By.xpath("//div[@id='selected-filter-opt']//span[contains(text(),'Location:')]"), driver.findElement(By.xpath("//div[@id='selected-filter-opt']//span[contains(text(),'Location:')]")).getAttribute("id"));
	}

	Locator productLocator (String action){
		int rows = driver.findElements(By.xpath("//ul[@id='product_list_ul']/div")).size();
		int rowIndex = (int) (Math.random() * rows + 1);
		int lastrowSize = driver.findElements(By.xpath("//ul[@id='product_list_ul']/div[" + rows + "]/div/div")).size();
		int columns = driver.findElements(By.xpath("//ul[@id='product_list_ul']/div[" + rowIndex + "]/div/div")).size();
		int columnIndex = (int) (Math.random() * columns + 1);
		if (action.equalsIgnoreCase("initialCount")) {
			initialProductCount.set(((rows - 1) * columns) + lastrowSize);
			return null;
		} else if (action.equalsIgnoreCase("finalCount")) {
			finalProductCount.set(((rows - 1) * columns) + lastrowSize);
			return null;
		} else if (action.contains("Book")) {
			return new Locator(By.xpath("//ul[@id='product_list_ul']/div[" + rowIndex + "]/div/div[" + columnIndex + "]//div[@class='btn_topmargin']//a[contains(@class,'book_now')]"), driver.findElement(By.xpath("//ul[@id='product_list_ul']/div[" + rowIndex + "]/div/div[" + columnIndex + "]//div[@class='service_box']/h3")).getText());
		} else {
			return new Locator(By.xpath("//ul[@id='product_list_ul']/div[" + rowIndex + "]/div/div[" + columnIndex + "]//div[@class='btn_topmargin']//a[contains(@class,'view_gallery')]"), driver.findElement(By.xpath("//ul[@id='product_list_ul']/div[" + rowIndex + "]/div/div[" + columnIndex + "]//div[@class='service_box']/h3")).getText());
		}
	}

	Locator oosMessageLocator ()
	{
		return new Locator(By.xpath("//p[@class='item-msg error']"), "Out Of Stock");
	}
	Locator cartProdLocator ()
	{
		return new Locator(By.xpath("//tr[@id='product_block']"), "Cart Product");
	}

	Locator imageOverlayLocator ()
	{
		return new Locator(By.xpath("//div[@id='sync1']/div/div/div[@class='owl-item']"), "Image Overlay");
	}

	Locator imageNextButtonLocator ()
	{
		return new Locator(By.xpath("//div[@id='sync1']/div[2]/div/div[@class='owl-next']"), "Next Image button");
	}

	Locator pageLoadingBar ()
	{
		return new Locator(By.xpath("//span[@id='loading']"), "Loading...");
	}

	Locator filterLocator (String filterName)
	{
		return new Locator(By.xpath("//ul[@class='category_filt_sort_ul']//div[@filt-typ='" + filterName.toLowerCase() + "']"), filterName);
	}

	Locator filterDropDown (String filterName)
	{
		return new Locator(By.xpath("//div[@id='filter-innerbox']/div[@filter='" + filterName.toLowerCase() + "']"), "Filters DropDown");
	}

	Locator filterCheckBox (String filterName)
	{
		int filterSize = driver.findElements(By.xpath("//div[@id='filter-innerbox']/div[@filter='" + filterName.toLowerCase() + "']//ul/ol/li")).size();
		int index = 0;
		final int[] ints = new Random().ints(1, filterSize + 1).distinct().limit(filterSize).toArray();
		for (int i = 0; i < ints.length; i++) {
			WebElement googleSearchBtn = driver.findElement(By.xpath("//div[@id='filter-innerbox']/div[@filter='" + filterName.toLowerCase() + "']//ul/ol/li[" + ints[i] + "]/a"));
			String property = googleSearchBtn.getAttribute("class");
			System.out.println("property is" + property);
			if (property.equalsIgnoreCase("m-checkbox-unchecked")) {
				index = ints[i];
				System.out.println("Final Index=" + index);
				break;
			}
		}
		return new Locator(By.xpath("//div[@id='filter-innerbox']/div[@filter='" + filterName.toLowerCase() + "']//ul/ol/li[" + index + "]"), driver.findElement(By.xpath("//div[@id='filter-innerbox']/div[@filter='" + filterName.toLowerCase() + "']//ul/ol/li[" + index + "]")).getText());
	}

	Locator extendedProductNameLocator(){

		return new Locator(By.xpath("//td/a/span[@class='product-name']"),"ProductName ");
	}
	
	Locator ServiceRowsInBag(){
    	return new Locator(By.xpath("//tr[@id='product_block']"),"Services In Bag");
	}
	
	Locator sortTypeOption(String sortName)
	{
		return new Locator(By.xpath("//div[@class=\"refine-name\"]/select/option[contains(text(),'"+sortName+"')]"),sortName+" Sort Option");
	}
	
	Locator sortLocator() 
	{
		return new Locator(By.xpath("//div[@class=\"refine-name\"]/select"),"Sort Locator");
	}

	Locator subMitLocator() {
		return new Locator(By.xpath("//div[@class='loc_popup_submit']"), "Submit");
	}

	Locator Filter() {
		return new Locator(By.id("sortaction"), "Filter button");
	}
	
	Locator fullPageLoadingIcon() {
		return new Locator(By.id("overlay"), "full Page Loading Icon");
	}
	
	
	Locator productList() {
		return new Locator(By.xpath("//ul[@id='product_list_ul']//div[contains(@id,'product-opt')]"), "Product List");
	}
	
	Locator PriceLocator() {
		return new Locator(By.xpath("//span[@class='ser_price']"), "price");
	}
	
	Locator productName() {
		return new Locator(By.xpath("//div[@class='service_box']/h3/a[1]"), "product Name");
	}
	
	Locator DiscountTag() {
		return new Locator(By.xpath("//p[@class='desk-disc-tag']"), "Discount Tag");
	}

	Locator ProductReviewcount() {

		return new Locator(By.xpath("//span[@class='review-label']"), "Review count");
	}
	
	Locator filter(String FilterName) {
		return new Locator(By.xpath("//div[@filt-typ='" + FilterName + "']"), " Filter " + FilterName + "");
	}
	
	Locator FilterCheckbox(String FilterCheckOption) {
		return new Locator(By.xpath("//a[@class='m-checkbox-unchecked'][@title='" + FilterCheckOption + "']"),
				"Filter Checkbox " + FilterCheckOption + "");
	}
	
	
	public boolean navigateToBeautyService(BeautyServiceData beautyserviceData) throws Throwable {
		boolean flag = false;
		try {
			System.out.println(beautyserviceData.getMenu().toLowerCase());
			bringElementIntoView(MenuLocator(beautyserviceData.getMenu().toLowerCase()));
			mouseOver(MenuLocator(beautyserviceData.getMenu().toLowerCase()));
			System.out.println(beautyserviceData.getMainmenu());
			waitUntilDisplayed(MainmenuLocator(beautyserviceData.getMainmenu()), 5);
			mouseOver(MainmenuLocator(beautyserviceData.getMainmenu()));
			System.out.println(beautyserviceData.getSubmenu().toLowerCase());
			Locator subMenuLoc = SubmenuLocator();
			flag = waitUntilDisplayed(subMenuLoc, 5);
			if (!flag) {
				return flag;
			}
			click(subMenuLoc);
			selectCity();
			if (verifySelectedLocation()) {
				flag = true;
				TestListner.testing.get().log(LogStatus.PASS, "Correct city and location has been selected !");
				TestListner.testing.get().log(LogStatus.INFO, "User navigated to Beauty Services Successfully !");
			} else {
				TestListner.testing.get().log(LogStatus.FAIL, "Wrong city has been selected !");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	private void selectCity() throws InstantiationException, IllegalAccessException {
		if (waitUntilDisplayed(CityPopUp(), 10)) {
			Locator cityLoc = CityLocator();
			selectedCity = cityLoc.getName();
			TestListner.testing.get().log(LogStatus.INFO, "Selected City is : " + selectedCity);
			click(cityLoc);
			Locator loclocator = LocationLocator();
			int counter = 0;
			while (counter < 10) {
				counter += 1;
				loclocator = LocationLocator();
				if (driver.findElement(loclocator.getBy()).getAttribute("rel_city").equalsIgnoreCase(selectedCity)) {
					break;
				}
				System.out.println(driver.findElement(loclocator.getBy()).getAttribute("rel_city"));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//loclocator=LocationLocator();
			selectedLocation = loclocator.getName();
			TestListner.testing.get().log(LogStatus.INFO, "Selected Loction is: " + selectedLocation);
			click(loclocator);
			click(subMitLocator());
		}
	}

	private boolean verifySelectedLocation() throws InstantiationException, IllegalAccessException {
		boolean flag = waitUntilPresent(selectedLocationLocator(), 20);
		if (flag) {
			flag = false;
			if (selectedLocation.contains(selectedLocationLocator().getName().substring(0, 5))) {
				flag = true;
			}
		}
		return flag;
	}

	public String bookNow() throws Throwable {
		Locator prodLocator = productLocator("Book");
		String prodName = prodLocator.getName();
		TestListner.testing.get().log(LogStatus.INFO, "Product to be booked: " + prodName);
		boolean oosMsg = false;
		try {
			waitUntilDisplayed(prodLocator, 5);
			bringElementIntoView(prodLocator);
			bringElemetnIntoViewChrome(prodLocator);
			click(prodLocator);
			waitUntilElementDisappear(pageLoadingBar().getBy());
			waitUntilElementDisappear(By.xpath("//div[@class='cart-product']//img[@title='loading']"));
			if (waitUntilDisplayed(cartProdLocator(), 30))
				try {
					TestListner.testing.get().log(LogStatus.INFO, "Added product to cart");
					oosMsg = isPresent(oosMessageLocator());
					if (!oosMsg) {
						TestListner.testing.get().log(LogStatus.PASS, "Product added to cart successfully and is in Stock !");
					} else {
						TestListner.testing.get().log(LogStatus.FAIL, "Product added to cart successfully and is in Out Of Stock !");
					}
				} catch (Exception e) {
				}

		} catch (Exception e) {
			e.printStackTrace();
			TestListner.testing.get().log(LogStatus.FAIL, "Exception " + e.getMessage());
			return null;
		}
		return prodName + "----" + !oosMsg;
	}

	@SuppressWarnings({"resource"})
	public String viewGallery() throws Throwable {
		Locator prodLocator = productLocator("view Gallery");
		String prodName = prodLocator.getName();
		boolean imgBroken = true;
		try {
			waitUntilDisplayed(prodLocator, 5);
			bringElementIntoView(prodLocator);
			Locator images = imageOverlayLocator();
			bringElemetnIntoViewChrome(prodLocator);
			click(prodLocator);
			waitUntilElementDisappear(pageLoadingBar().getBy());
			if (waitUntilDisplayed(images, 10)) {
				HttpClient httpclient = new DefaultHttpClient();
				for (int i = 1; i <= driver.findElements(images.getBy()).size(); i++) {
					imgBroken = false;
					Boolean ImagePresent = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", driver.findElement(By.xpath((images.getBy().toString().replaceAll("By.xpath: ", "") + "[" + i + "]/div/img"))));
					if (!ImagePresent) {
						imgBroken = true;
						break;
					}
					HttpGet httpget = new HttpGet(driver.findElement(By.xpath((images.getBy().toString().replaceAll("By.xpath: ", "") + "[" + i + "]/div/img"))).getAttribute("src"));
					HttpResponse response = httpclient.execute(httpget);
					if (!(response.getStatusLine().toString().contains("200") || response.getStatusLine().toString().contains("304"))) {
						imgBroken = true;
						break;
					}
					if (i < driver.findElements(images.getBy()).size())
						click(imageNextButtonLocator());
					httpget.abort();
				}
			}

		} catch (Exception e) {
			imgBroken = true;
			e.printStackTrace();
			return prodName + "----" + !imgBroken;
		}
		return prodName + "----" + !imgBroken;
	}

	
	public String applyFilter(String filterName) throws Throwable {
		String message = "Filter applied successfully : ";
		boolean flag = false;
		try {
			TestListner.testing.get().log(LogStatus.INFO, "Applying Filter: " + filterName);
			productLocator("initialCount");
			Reporter.log("Initial Product Count " + initialProductCount.get());
			System.out.println(initialProductCount.get());
			TestListner.testing.get().log(LogStatus.INFO, initialProductCount.get() + " is the Initial Product Count");
			Locator filterLoc = filterLocator(filterName);
			if (filterName.equalsIgnoreCase("Brand")) {
				filterName = "Beauty";
			}
			//bringElementIntoView(filterLoc);
			bringElemetnIntoViewChrome(filterLoc);
			mouseOver(filterLoc);
			flag = isPresent(filterDropDown(filterName));
			if (flag) {
				int filterCount = selectFilter(filterName);
				waitUntilElementDisappear(pageLoadingBar().getBy());
				productLocator("finalCount");
				System.out.println(initialProductCount.get() + " : " + finalProductCount.get());
				Reporter.log("Apply Filter Product Count " + filterCount);
				Reporter.log("Final Product Count " + finalProductCount.get());
				TestListner.testing.get().log(LogStatus.INFO, filterCount + " is the Apply Filter Product Count");
				TestListner.testing.get().log(LogStatus.INFO, finalProductCount.get() + " is the Final Product Count");
				flag = ((filterCount + initialProductCount.get()) == finalProductCount.get() ? true : false || initialProductCount.get() == finalProductCount.get() ? true : false || filterCount == finalProductCount.get() ? true : false);
				if (flag) {
					return message + "Count Matches----" + flag;
				} else {
					return message + "Count does not match----" + flag;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Filter not applied----" + flag;
		}
		return null;
	}

	private int selectFilter(String filterName) {
		int expectedCount = 0;
		try {
			Locator chkBoxLocator = filterCheckBox(filterName);
			System.out.println(chkBoxLocator.getName());
			expectedCount = Integer.parseInt(chkBoxLocator.getName().split("\\(")[1].replace(")", "").trim());
			click(chkBoxLocator);
			waitUntilElementDisappear(pageLoadingBar().getBy());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return expectedCount;
	}

	public void navigateToQuickLink(BeautyServiceData beutyServiceData, String environmentURl) throws Throwable {
		System.out.println(beutyServiceData.getQuicklinks());
		bringElementIntoView(quickLink(beutyServiceData.getQuicklinks()));
		clickandNavigateWindow(quickLink(beutyServiceData.getQuicklinks()), "Beauty Partner",environmentURl);
		if (driver.getCurrentUrl().contains("http://www.nykaa.com")) {
			//driver.get(driver.getCurrentUrl().replace("http://www.nykaa.com", environmentURl));
		}
		;
	}

	public boolean selectCityAndLocation(BeautyServiceData beutyServiceData) throws Throwable {
		waitforPageReady();
		waitForPagetoLoad();
		waitUntilDisplayed(LocationPopup(), 15);
		click(cityLocator(beutyServiceData.getCity()));
		bringElementIntoView(locationLocator(beutyServiceData.getLocation()));
		click(locationLocator(beutyServiceData.getLocation()));
		click(subMitLocator());
		 waitUntilDisappear(LoadingIcon());
		 return true;
	}


	private List<BeautyServiceData> getAllServiceList() throws IllegalAccessException {
		List<BeautyServiceData> beautyServiceList = new ArrayList<BeautyServiceData>();
		List<WebElement> ServicesWebElements = getWebElements(ServicesLocator());

		for (WebElement service : ServicesWebElements) {
			BeautyServiceData beautyServiceData = new BeautyServiceData();
			Locator ServiceLocator = new Locator(By.xpath(getAbsoluteXPath(service)), "Service");
			Locator ServiceNameLocator = ServiceLocator.concatLocator(ServiceNameText());
			Locator ServiceDetail = ServiceLocator.concatLocator(ServiceDetail());
			beautyServiceData.setServicename(getText(ServiceNameLocator));
			beautyServiceData.setServicedetail(getText(ServiceDetail));
			beautyServiceList.add(beautyServiceData);
		}


		return beautyServiceList;
	}


	public BeautyServiceData BookAnyService() throws Throwable {
		List<BeautyServiceData> beautyServiceDataList = getAllServiceList();
		BeautyServiceData addedbeuatyServiceData = null;
		for (BeautyServiceData beautyServiceData : beautyServiceDataList) {
			TestListner.testing.get().log(LogStatus.INFO, "Service to be booked: "+beautyServiceData.getServicename());
			bringElementIntoView(BookNowButton(beautyServiceData.getServicename()));
			ScrollDown("100");
			click(BookNowButton(beautyServiceData.getServicename()));
			waitUntilDisappear(LoadingIcon());
			waitUntilDisplayed(CouponCodeTextBox(), 3);
			Thread.sleep(2000);
			if (waitUntilDisplayed(OutOfStockMessage(), 5)) {
				TestListner.testing.get().log(LogStatus.INFO, beautyServiceData.getServicename() +" is OOS");
				click(closeButtonOfOOSMessage());
			} else {
				addedbeuatyServiceData = beautyServiceData;
				TestListner.testing.get().log(LogStatus.INFO, beautyServiceData.getServicename() +" added to cart");
				break;
			}

		}
		CartPage cp=new CartPage(driver);
		cp.closeSlidingCart();
		return addedbeuatyServiceData;
	}

	@SuppressWarnings({"resource"})
	public BeautyServiceData view_Gallery() throws Throwable {
		Locator images = imageOverlayLocator();
		boolean imgBroken = true;
		List<BeautyServiceData> beautyServiceDataList = getAllServiceList();
		BeautyServiceData viewedbeuatyServiceData = null;
		for (BeautyServiceData beautyServiceData : beautyServiceDataList) {
			TestListner.testing.get().log(LogStatus.INFO, "Service to be checked for View Gallery: "+beautyServiceData.getServicename());
			bringElementIntoView(BookNowButton(beautyServiceData.getServicename()));
			ScrollDown("100");
			click(ViewgalleryButton(beautyServiceData.getServicename()));
			waitUntilElementDisappear(pageLoadingBar().getBy());
			if (waitUntilDisplayed(images, 10)) {
				HttpClient httpclient = new DefaultHttpClient();
				for (int i = 1; i <= driver.findElements(images.getBy()).size(); i++) {
					imgBroken = false;
					Boolean ImagePresent = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", driver.findElement(By.xpath((images.getBy().toString().replaceAll("By.xpath: ", "") + "[" + i + "]/div/img"))));
					if (!ImagePresent) {
						imgBroken = true;
						break;
					}
					HttpGet httpget = new HttpGet(driver.findElement(By.xpath((images.getBy().toString().replaceAll("By.xpath: ", "") + "[" + i + "]/div/img"))).getAttribute("src"));
					HttpResponse response = httpclient.execute(httpget);
					if (!(response.getStatusLine().toString().contains("200") || response.getStatusLine().toString().contains("304"))) {
						imgBroken = true;
						break;
					}
					if (i < driver.findElements(images.getBy()).size())
						click(imageNextButtonLocator());
					httpget.abort();
				}

			}
			click(CloseGalleryButton());
			if(!imgBroken)
			{
				viewedbeuatyServiceData=beautyServiceData;
				TestListner.testing.get().log(LogStatus.PASS, "Images displayed in Gallery for: "+beautyServiceData.getServicename());
				break;
			}
			else
			{
				TestListner.testing.get().log(LogStatus.INFO, "Images are not present for service: "+beautyServiceData.getServicename());
			}
		}
		return viewedbeuatyServiceData;
	}




	private void bringElemetnIntoViewChrome(Locator prodLocator) {
		try {
			Point xyCoordinate = driver.findElement(prodLocator.getBy()).getLocation();
			//int xCoordinate=xyCoordinate.getX();
			int yCoordinate = xyCoordinate.getY() - 200;
			((JavascriptExecutor) driver).executeScript("scroll(0, " + yCoordinate + ");");
		} catch (Exception e) {
		}
	}

	
	public boolean isServicePresentInBag(BeautyServiceData beautyServiceData) throws Throwable, Throwable
	{
		boolean isServicePresent = false;
		waitUntilDisplayed(CouponCodeTextBox(), 5);
		String serviceName = beautyServiceData.getServicename();
		List<WebElement> servicerow = getWebElements(ServiceRowsInBag());
		for (WebElement service : servicerow) {
			Locator ServieListBagLocator = new Locator(By.xpath(getAbsoluteXPath(service)), "service List ");
			Locator serviceNameLocator = ServieListBagLocator.concatLocator(extendedProductNameLocator());
			String ServiceNameFromUI = getText(serviceNameLocator);
			System.out.println(serviceName);
			System.out.println(ServiceNameFromUI);
			if(ServiceNameFromUI.contains(serviceName))
			{
				isServicePresent = true;
				break;

			}
		}

		CartPage cartPage = new CartPage(driver);
		if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
			click(cartPage.BackMenuCart());
			waitUntilDisappear(cartPage.BackMenuCart());}
		return isServicePresent;
	}

	public Boolean applySort(String sortName) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try
		{
			TestListner.testing.get().log(LogStatus.INFO, "Applying Sorting on: "+sortName);
			if(waitUntilDisplayed(sortLocator(), 10))
			{
				Locator sortOption=sortTypeOption(sortName);
				click(sortOption);
			}
			waitUntilElementDisappear(pageLoadingBar().getBy());
			flag=checkSortResults(sortName);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return  flag;
		}
		return flag;

	}

	public boolean checkSortResults(String sortName) {
		// TODO Auto-generated method stub
		boolean flag=false;
		try
		{
			List<WebElement> sortResult;
			List<String> finalResult = new ArrayList<>();
			List<String> finalResultByName = new ArrayList<>();
			switch (sortName) {
			case "DISCOUNT":
				sortResult=driver.findElements(By.xpath("//span[@class='ser_price_dis']"));
				if(sortResult.size()<=1) {
					flag=true;
				}
				if(sortResult.size()>1) {
					for ( WebElement result: sortResult) {   
						String text=result.getText();
						String d = new String(text.trim().replaceAll("[^0-9]+", ""));
						finalResult.add(d);
					}
					flag = isSorted(sortName,finalResult);
				}
				break;
			case "NAME": 
				sortResult=driver.findElements(By.xpath("//div[@class='service_box']/h3/a"));
				if(sortResult.size()<=1) {
					flag=true;
				}
				if(sortResult.size()>1) {
					for ( WebElement result: sortResult) {   
						String text=result.getText();
						finalResultByName.add(text);
					}
					flag = isSorted(sortName,finalResultByName);
				}
				break;
			case "PRICE: HIGH TO LOW":
				sortResult=driver.findElements(By.xpath("//span[@class='ser_price']/i"));
				if(sortResult.size()<=1) {
					flag=true;
				}
				if(sortResult.size()>1) {
					for ( WebElement result: sortResult) {   
						String text=result.getText();
						String d = new String(text.trim().replaceAll("[^0-9]+", ""));
						finalResult.add(d);
					}
					flag = isSorted(sortName,finalResult);
				}
				break;
			case "PRICE: LOW TO HIGH":
				sortResult=driver.findElements(By.xpath("//span[@class='ser_price']/i"));
				if(sortResult.size()<=1) {
					flag=true;
				}
				if(sortResult.size()>1) {
					for ( WebElement result: sortResult) {   
						String text=result.getText();
						String d = new String(text.trim().replaceAll("[^0-9]+", ""));
						finalResult.add(d);
					}
					flag = isSorted(sortName,finalResult);
				}
				break;
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			return  flag;
		}
		return flag;
	}


	public boolean isSorted(String sortType,List<String> list)
	{
		boolean sorted = false;  
		switch (sortType) {
		case "DISCOUNT":
			for (int i = 1; i < list.size(); i++) {
				if (Integer.parseInt(list.get(i-1))>=Integer.parseInt(list.get(i))) {
					System.out.println(list.get(i-1));
					System.out.println(list.get(i));
					sorted = true;
				}
				else {
					sorted = false;
					TestListner.testing.get().log(LogStatus.INFO, list.get(i-1) +" and "+list.get(i)+ " are the two comparition values");
					break;
				}
			}
			break;
		case "NAME":
			for (int i = 1; i < list.size(); i++) {
				int comparisonResult = list.get(i-1).compareTo(list.get(i));
				System.out.println(list.get(i-1));
				System.out.println(list.get(i));
				if(comparisonResult <= 0){
					sorted = true;
				}
				if(comparisonResult > 0){
					sorted = false;
					TestListner.testing.get().log(LogStatus.INFO, list.get(i-1) +" and "+list.get(i)+ " are the two comparision values");
					break;
				}
			}
			break;
		case "PRICE: HIGH TO LOW":
			for (int i = 1; i < list.size(); i++) {
				System.out.println(list.get(i-1));
				System.out.println(list.get(i));
				if (Integer.parseInt(list.get(i-1))>=Integer.parseInt(list.get(i))) {
					sorted = true;
				}
				else {
					sorted = false;
					TestListner.testing.get().log(LogStatus.INFO, list.get(i-1) +" and "+list.get(i)+ " are the two comparition values");
					break;
				}
			}
			break;
		case "PRICE: LOW TO HIGH":
			for (int i = 1; i < list.size(); i++) {
				System.out.println(list.get(i-1));
				System.out.println(list.get(i));
				if (Integer.parseInt(list.get(i-1))<=Integer.parseInt(list.get(i))) {
					sorted = true;
				}
				else {
					sorted = false;
					TestListner.testing.get().log(LogStatus.INFO, list.get(i-1) +" and "+list.get(i)+ " are the two comparision values");
					break;
				}
			}
			break;
		}
		return sorted;
	}
	
	public List<ProductDetailData> ApplySort(String sortName) throws Throwable {
		bringElementIntoView(Filter());
		selectFromDropDownBox(Filter(), sortName);
		waitUntilDisappear(fullPageLoadingIcon());
		List<ProductDetailData> ProductDataList = getListOfProduct(sortName);
		return ProductDataList;
	}
	
	
	/**
	 * @param sortName
	 * @return
	 * @throws IllegalAccessException
	 */
	private List<ProductDetailData> getListOfProduct(String sortName) throws IllegalAccessException {
		List<WebElement> productList = getWebElements(productList());
		List<ProductDetailData> ProductDataList = new ArrayList<ProductDetailData>();
		for (WebElement product : productList) {
			String Discount = null;
			String ReviewCount = null;
			Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product Parent Loactor");
			String prizeOfProduct = (getText(productLocator.concatLocator(PriceLocator())).replaceAll("\\D+", ""));
			String ProductName = getAttribute(productLocator.concatLocator(productName()), "title");
			if (sortName.contains("discount")) {
				Discount = getText(productLocator.concatLocator(DiscountTag())).replaceAll("\\D+", "");
			}
			if (sortName.contains("rated")) {
				ReviewCount = getText(productLocator.concatLocator(ProductReviewcount())).replaceAll("\\D+", "");
			}
			ProductDetailData productDetailData = new ProductDetailData();
			productDetailData.setProductPrize(prizeOfProduct);
			productDetailData.setProductName(ProductName);
			productDetailData.setDiscount(Discount);
			productDetailData.setReviewCount(ReviewCount);
			ProductDataList.add(productDetailData);
		}
		return ProductDataList;
	}
	
	
	public List<ProductDetailData> ApplyFilter(String FilterName, String Criteria) throws Throwable, Throwable {
		bringElementIntoView(filter(FilterName));
		ScrollDown("100");
		waitUntilDisplayed(filter(FilterName), 10);
		mouseOver(filter(FilterName));
		waitUntilDisplayed(FilterCheckbox(Criteria), 5);
		click(FilterCheckbox(Criteria));
		waitUntilDisappear(fullPageLoadingIcon());
		waitUntilDisappear(pageLoadingBar());
		List<ProductDetailData> ProductDataList = getListOfProduct(FilterName);
		return ProductDataList;
	}
	
}


