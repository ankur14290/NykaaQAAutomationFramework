package Mobile_Pages_Nykaa;

import java.util.ArrayList;
import java.util.List;

import org.apache.bcel.generic.POP;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import DataNykaa.Header_FooterBarData;
import DataNykaa.ProductDetailData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.Locator;
import FrameWorkNykaa.PartialStringList;
import FrameWorkNykaa.TestListner;

public class HeaderBar_Mobile extends BrowserAction {

	public HeaderBar_Mobile(WebDriver driver) {
		this.driver = driver;

	}

	Locator hamburgerIcon(){
		return new Locator(By.xpath("//button[@class='burgure-menu-icon mkr mkr-menu']"),"Hamburger Menu");
	}
	Locator CloseHamburgerIcon(){
		return new Locator(By.xpath("//span[@class='circle-icon']"),"Cross button");
	}

	Locator Categories(){
		return new Locator(By.xpath("//li[@id='categories']"),"Categories");
	}


	Locator POPUPS(){
		return new Locator(By.xpath("//li[@class='menu-dropdown-icon']/a[text()='popups']"),"Beauty Advice");
	}


	Locator productResultsNameList() {
		return new Locator(By.xpath("//h3[@class='product-name']/a"), "product Result List");
	}

	Locator searchPage(){
		return new Locator(By.id("search_page"),"Search Page");
	}
	Locator categoriesLocator(){
		return new Locator(By.id("shopspan"),"Categories");
	}

	Locator trendingSearch(){
		return new Locator(By.xpath("//li[@class='trending-search']/a"),"trending search");
	}

	Locator autoSearchResultData(){
		return new Locator(By.xpath("//li[@class='unbxd-as-keysuggestion']"),"autosearch search data");
	}

	Locator popularSearchResultData(){
		return new Locator(By.xpath("//div[@class='unbxd-as-popular-product-name']"),"Popular Search result");
	}

	Locator ProductList() {
		return new Locator(By.xpath("//div[@id='search_page'] |  //input[@id='brandsearchheader']"),
				"product Result List");

	}

	Locator beautyServiceLink() {
		return new Locator(By.xpath("//dl[@class='beauty-profile-img']"), "BeutyService link of Header");
	}

	Locator QuickLink(String link) {
		return new Locator(By.xpath("//div[@class='quiklink']/div[@class='quik1']/a[contains(text(),'" + link + "')]"),
				"on Link " + link + "");
	}

	Locator brandSearchPage() {
		return new Locator(By.id("brandsearchheader"), "Brand Search");
	}
	Locator brandLink()
	{
		return new Locator(By.xpath("//li[@id='brands']"),"Brand Link");
	}

	Locator brand(String brandName)
	{
		return new Locator(By.xpath("//a[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='"+brandName+"']")," "+brandName+" brand");
	}

	Locator brandLogo(String BrandName, String campName)
	{
		return new Locator(By.xpath("//li[@class='brand-logo menu-links']/a[contains(@href,'brand_menu|"+campName+"|"+BrandName+"')]")," "+BrandName+" brand");
	}
	Locator ExclusiveBrandLink(String MainMenu)
	{
		return new Locator(By.xpath("//li[@id='litablets']/descendant::span[text()='"+MainMenu+"']"),"Exclusive Link");
	}

	Locator CartTotalLocator() {
		return new Locator(By.xpath("//div[@class='cart-count']/p"), "Cart Total Value");
	}

	Locator CartLocator() {
		return new Locator(By.xpath("//span[@class='mkr-New-Shopping-Bag']"),"Shoping Cart Icon");
	}

	Locator SearchSuggetionblock() {
		return new Locator(By.xpath("//div[@class='unbxd-as-wrapper'][contains(@style,'display: block')]"),
				"Search Suggetion auto suggest Box");
	}

	Locator MainmenuLocator(String Mainmenu) {
		return new Locator(By.xpath("//a[text()='"+Mainmenu+"']"),
				"" + Mainmenu + " of Mainmenu");
	}
	Locator MainmenuLocatorNew(String Mainmenu) {
		List<WebElement> lists=new ArrayList<WebElement>();
		lists=driver.findElements(By.xpath("//div[@class='m-content__menu-categories-list-wrap']/ul/li"));
		for(int index=1;index<=lists.size();index++)
		{
			if (driver.findElement(By.xpath("//div[@class='m-content__menu-categories-list-wrap']/ul/li["+index+"]/a")).getText().contains(Mainmenu.toUpperCase()))
			{
				return new Locator(By.xpath("//div[@class='m-content__menu-categories-list-wrap']/ul/li["+index+"]/a"),"" + Mainmenu + " of Mainmenu");
			}
		}
		return null;
	}

	Locator LuxeLocator(){
		return new Locator(By.xpath("//li[@id='nykaa-lux-new']/a"),"Nykaa Luxe");
	}

	Locator SubmenuLocator(String Submenu) {
		//return new Locator(By.linkText("" + Submenu + ""), "" + Submenu + " of submenu");
		return new Locator(By.xpath("//a[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='"+Submenu+"'] | //li[@class='second-active']//a[text()='"+Submenu+"']")," of "+Submenu+" Locator");
	}

	Locator SubmenuCategoryLocator(String SubmenuCategory) {
		//return new Locator(By.linkText("" + Submenu + ""), "" + Submenu + " of submenu");
		return new Locator(By.xpath("//a[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='"+SubmenuCategory+"'] | //a[text()='"+SubmenuCategory+"']")," of "+SubmenuCategory+" Locator");
	}

	Locator SearchLocator() {
		return new Locator(By.xpath("//input[@id='SearchInputBox']|//input[@type='search']|//input[@id='search']"), "Search TextBox");

	}
	
	


	public void clearCart()
			throws Throwable {
		closeGetAppPopUp();

		CartPage_Mobile_New cartPage = new CartPage_Mobile_New(driver);
		System.out.println(getText(CartTotalLocator())+"************** output of cart");
		waitUntilDisplayed(CartTotalLocator(), 10);
		if (!(getText(CartTotalLocator()).equalsIgnoreCase("0"))) 
		{
			TestListner.testing.get().log(LogStatus.INFO, "Clearing the cart !");
			bringElementIntoView(CartLocator());
			click(CartLocator());
			cartPage.removeAllProduct();

		}

	}
	
	public void clearCartNykaaMan()
			throws Throwable {
		try {
		closeGetAppPopUp();
		}
		catch(Exception e)
		{
			
		}

		CartPage_Mobile_New cartPage = new CartPage_Mobile_New(driver);
		System.out.println(getText(CartTotalLocator())+"************** output of cart");
		waitUntilDisplayed(CartTotalLocator(), 10);
		if (!(getText(CartTotalLocator()).equalsIgnoreCase("0"))) 
		{
			TestListner.testing.get().log(LogStatus.INFO, "Clearing the cart !");
			bringElementIntoView(CartLocator());
			click(CartLocator());
			cartPage.removeAllProductNykaaMan();

		}

	}

	public void search(String keyword) {
		closeGetAppPopUp();
		ClearValue(SearchLocator());
		EnterValue(SearchLocator(), keyword);
		waitUntilDisplayed(SearchSuggetionblock(), 4);
		keyBoard(SearchLocator(), Keys.ENTER);

		//waitUntilDisplayed(searchPage(),20);
		waitForPagetoLoad();


	}
	
	public void Autosearch(String keyword) {
		closeGetAppPopUp();
		ClearValue(SearchLocator());
		EnterValue(SearchLocator(), keyword);
		waitUntilDisplayed(SearchSuggetionblock(), 4);
		

		//waitUntilDisplayed(searchPage(),20);
		waitForPagetoLoad();


	}
	
	

	public PartialStringList getValueFromAutoSuggetionList(){
		//keyBoard(SearchLocator(), Keys.ENTER);

		List<WebElement> autoSuggestSearchResult = getWebElements(autoSearchResultData());

		PartialStringList productValueList = new PartialStringList();

		for (WebElement searchresultvalue : autoSuggestSearchResult) {
			Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(searchresultvalue)), "product");
			String TitleProducteName = getAttribute(ProductLocator, "data-value");
			productValueList.add(TitleProducteName.toLowerCase());
		}

		return productValueList;
	}


	public PartialStringList getValuefromPopularSuggetionList() throws Throwable, IllegalAccessException{
		//keyBoard(SearchLocator(), Keys.ENTER);

		List<WebElement> popularSearchResult = getWebElements(popularSearchResultData());

		PartialStringList productValueList = new PartialStringList();

		for (WebElement searchresultvalue : popularSearchResult) {
			Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(searchresultvalue)), "product");
			String TitleProducteName = getText(ProductLocator);
			productValueList.add(TitleProducteName.toLowerCase());
		}

		return productValueList;
	}


	public PartialStringList getTrendingSearchList() throws Throwable, Throwable{
		click(SearchLocator());
		click(SearchLocator());
		waitUntilDisplayed(trendingSearch(), 10);
		List<WebElement> autoSuggestSearchResult = getWebElements(trendingSearch());

		PartialStringList productValueList = new PartialStringList();

		for (WebElement searchresultvalue : autoSuggestSearchResult) {
			Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(searchresultvalue)), "product");
			String TitleProducteName = getText(ProductLocator);
			productValueList.add(TitleProducteName.toLowerCase());
		}

		return productValueList;
	}


	public PartialStringList getSearchedResult() throws Throwable {
		ListingPage_Mobile lpm= new ListingPage_Mobile(driver);
		keyBoard(SearchLocator(), Keys.ENTER);

		//waitUntilDisplayed(searchPage(),20);
		waitForPagetoLoad();
		List<WebElement> productList = getWebElements(lpm.productList());
		PartialStringList productValueList = new PartialStringList();

		for (WebElement product : productList) {
			Locator productLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "Product Parent Loactor");
			String ProductName = getAttribute(productLocator.concatLocator(lpm.productName()), "title");
			productValueList.add(ProductName.toLowerCase());
		}

		return productValueList;

	}



	public boolean navigateToCategory(Header_FooterBarData headerbarData) throws Throwable {
		closeGetAppPopUp();
		waitUntilDisplayed(hamburgerIcon(), 10);
		click(hamburgerIcon());
		waitUntilDisplayed(Categories(), 10);
		String navigationList=headerbarData.getMainmenu();
		String[] navigationMenu=navigationList.split(">");
		bringElementIntoView(MainmenuLocatorNew(navigationMenu[0]));
		click(MainmenuLocatorNew(navigationMenu[0]));
		if(navigationMenu.length>=2)
		{
			for(WebElement we: getAllElements(SubmenuCategoryLocator(navigationMenu[1]).getBy()))
			{
				if(we.isDisplayed())
				{
					bringElementIntoView(SubmenuCategoryLocator(navigationMenu[1]));
					Thread.sleep(1000);
					click(we,navigationMenu[1]);
					break;
				}
			}

		}
		else if(headerbarData.getSubmenuCategory()!=null && ! headerbarData.getSubmenuCategory().equalsIgnoreCase("null"))
		{
			for(WebElement we: getAllElements(SubmenuCategoryLocator(headerbarData.getSubmenuCategory()).getBy()))
			{
				System.out.println(we.getText());
				if(we.isDisplayed())
				{
					bringElementIntoView(SubmenuCategoryLocator(headerbarData.getSubmenuCategory()));
					Thread.sleep(1000);
					click(we,headerbarData.getSubmenuCategory());
					break;
				}
			}

		}

		if(navigationMenu.length>=3)
		{
			List<WebElement> locators=getAllElements(SubmenuLocator(navigationMenu[2]).getBy());
			for(WebElement subMenu:locators)
			{
				if(subMenu.isDisplayed())
				{
					bringElementIntoViewWebElement(subMenu);
					click((subMenu),navigationMenu[2]);
					break;
				}
			}
		}
		else if(headerbarData.getSubmenu()!=null && ! headerbarData.getSubmenu().equalsIgnoreCase("null"))
		{
			bringElementIntoView(SubmenuLocator(headerbarData.getSubmenu()));
			Thread.sleep(1000);
			click(SubmenuLocator(headerbarData.getSubmenu()));
		}

		try 
		{
			driver.switchTo().alert().accept();
		}
		catch(Exception e) {}
		ListingPage_Mobile listingPage = new ListingPage_Mobile(driver);
		return	getText(listingPage.breadCrumb()).contains(headerbarData.getSubmenu());

	}


	public void navigateToQuickLink(Header_FooterBarData headerFooterBar,String environmentURL) throws Throwable, Throwable {
		CartPage_Mobile cartPage = new CartPage_Mobile(driver);
		if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
			click(cartPage.BackMenuCart());
			waitUntilDisappear(cartPage.BackMenuCart());
		}
		bringElementIntoView(MainmenuLocator("Makeup"));
		clickandNavigateWindow(beautyServiceLink(), "Beauty Services",environmentURL);
		bringElementIntoView(QuickLink(headerFooterBar.getMainmenu()));
		clickandNavigateWindow(QuickLink(headerFooterBar.getMainmenu()),"Beauty Partner",environmentURL);
		if(driver.getCurrentUrl().contains("http://www.nykaa.com"))
		{
			driver.get(driver.getCurrentUrl().replace("http://www.nykaa.com", environmentURL));
		};



	}

	public boolean navigateToPopUps(Header_FooterBarData headerbarData) throws Throwable {CartPage_Mobile cartPage = new CartPage_Mobile(driver);
	if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
		click(cartPage.BackMenuCart());
		waitUntilDisappear(cartPage.BackMenuCart());
	}
	bringElementIntoView(POPUPS());
	click(POPUPS());
	//bringElementIntoView(MainmenuLocator(headerbarData.getMainmenu().toLowerCase()));
	//mouseOver(categoriesLocator());
	mouseOver(MainmenuLocator(headerbarData.getMainmenu()));
	waitUntilDisplayed(SubmenuLocator(headerbarData.getSubmenu()), 5);
	click(SubmenuLocator(headerbarData.getSubmenu()));

	FooterBar_Mobile footerbar = new FooterBar_Mobile(driver);
	footerbar.CloseAdvertismentPopup();
	ListingPage_Mobile listingPage = new ListingPage_Mobile(driver);
	return	getText(listingPage.breadCrumb()).contains(headerbarData.getSubmenu());

	}




	public void navigateToBrand(Header_FooterBarData headerFooterBar) throws Throwable
	{
		waitUntilDisplayed(hamburgerIcon(), 10);
		click(hamburgerIcon());
		closeGetAppPopUp();
		waitUntilDisplayed(brandLink(), 10);
		click(brandLink());
		System.out.println(brand(headerFooterBar.getSubmenu()).getBy());
		bringElementIntoView(brand(headerFooterBar.getSubmenu()));
 		click(brand(headerFooterBar.getSubmenu()));

	}



}
