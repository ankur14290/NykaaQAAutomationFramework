package PagesNykaa;

import java.util.ArrayList;
import java.util.List;

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

public class HeaderBar extends BrowserAction {

	public HeaderBar(WebDriver driver) {
		this.driver = driver;

	}
	Locator autoSearchResultDataNykaaMan(){
		return new Locator(By.xpath("//li[@class='unbxd-as-keysuggestion']"),"autosearch search data");
	}
	Locator POPUPSNykaaMan(){
		return new Locator(By.xpath("//li[@class='CustomCl-Padding menu-dropdown-icon']/a[text()='popups']"),"Pop Ups");
	}
	Locator POPUPS(){
		return new Locator(By.xpath("//li[@class='CustomCl-Padding']/a[text()='popups']"),"Pop Ups");
	}


	public Locator productResultsNameList() {
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
		return new Locator(By.xpath("//div[@class='react-autosuggest__section-container react-autosuggest__section-container--first']//ul[@class='react-autosuggest__suggestions-list']/li"),"autosearch search data");
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

	Locator brandLink(){
		return new Locator(By.xpath("//a[text()='brands']"),"Brand Link");
	}

	Locator brandLogo(String BrandName, String campName)
	{
		return new Locator(By.xpath("//a[contains(@href,'intcmp=brand_menu,"+campName+","+BrandName+"')]")," "+BrandName+" brand");
	}
	Locator ExclusiveBrandLink(String MainMenu)
	{
		return new Locator(By.xpath("//a[contains(@class,'brandHeadingbox')][text()='"+MainMenu+"']"),"Exclusive Link");
	}

	Locator CartTotalLocator() {
		return new Locator(By.xpath("//div[@class='total-cart-product newheadercartproduct']/p | //div[contains(@class,'BagItems')]"), "Cart Total Value");
	}

	Locator AutoSearchresultname(){
		return new Locator(By.xpath("//div/b"),"Auto Search Result name");
	}

	static Locator CartLocator() {
		return new Locator(By.xpath("//dl[@id='cart-img'or @class='shopping-bag' or @class='shopping-bag shopping']|//div[@class='AddBagIcon']"),
				"Shoping Cart Icon");
	}

	Locator SearchSuggetionblock() {
		return new Locator(By.xpath("//div[@class='unbxd-as-wrapper'][contains(@style,'display: block')]"),
				"Search Suggetion auto suggest Box");
	}

	Locator MainmenuLocator(String Mainmenu) {
		return new Locator(By.xpath("//a[text()='"+Mainmenu+"']"),
				"" + Mainmenu + " of Mainmenu");
	}

	Locator LuxeLocator(){
		return new Locator(By.xpath("//li[@id='nykaa-lux-new']/a"),"Nykaa Luxe");
	}

	Locator subMenuLocator(String Submenu)
	{
		return new Locator(By.xpath("//a[@class='current_active'][translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='"+Submenu+"']| //a[@class='current_active'][text()='"+Submenu+"']")," of "+Submenu+" Locator");
	}
	Locator SecondLevelLocator(String Mainmenu,String Submenu) {
		//return new Locator(By.linkText("" + Submenu + ""), "" + Submenu + " of submenu");
		return new Locator(By.xpath("//li[contains(@class,'MegaDropdownHeadingbox')]/a[text()='"+Mainmenu+"']/ancestor::li[contains(@class,'MegaDropdownHeadingbox')]//div[contains(@class,'MegaDropdown-ContentHeading')]/a[normalize-space()='"+Submenu+"']"),Submenu);
		// return new Locator(By.xpath("//a[@class='current_active'][translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='"+Submenu+"']|//a[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='"+Submenu+"'] | //a[@class='current_active'][text()='"+Submenu+"']|//a[text()='"+Submenu+"']")," of "+Submenu+" Locator");
	}Locator ThirdLevelLocator(String Mainmenu,String Submenu) {
		//return new Locator(By.linkText("" + Submenu + ""), "" + Submenu + " of submenu");
		return new Locator(By.xpath("//li[contains(@class,'MegaDropdownHeadingbox')]/a[text()='"+Mainmenu+"']/ancestor::li[contains(@class,'MegaDropdownHeadingbox')]//ul/li/a[normalize-space()='"+Submenu+"']"),Submenu);
		// return new Locator(By.xpath("//a[@class='current_active'][translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='"+Submenu+"']|//a[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='"+Submenu+"'] | //a[@class='current_active'][text()='"+Submenu+"']|//a[text()='"+Submenu+"']")," of "+Submenu+" Locator");
	}

	Locator CategoryTab(){
		return new Locator(By.xpath("//a[text()='categories']"),"Category tab");
	}
	Locator SearchLocator() 
	{
		return new Locator(By.id("SearchInputBox"), "Search TextBox");
	}

	Locator NykaaLogo()
	{
		return new Locator(By.id("NykaaLogo"),"Nykaa Logo");
	}

	Locator nykaaNetwork(){
		//return new Locator(By.xpath("//a[@href='http://content2.nykaa.com/community']"),"NykaaNetwork");
		return new Locator(By.xpath("//a[text()='nykaa network']"),"NykaaNetwork");

	}

	Locator ProductName(String ProductName) {
		   return new Locator(By.xpath("//h3[@class='product-name']/a[contains(@title,\"" + ProductName + "\")]"),
		         " " + ProductName + "");
	}
	

	Locator SearchLocator_React(){
		   return new Locator(By.id("SearchInputBox"),"Search TextBox");
		}
		
	Locator allMainMenus() {
		return new Locator(By.xpath("//ul[@class='MegaDropdownHeading']/li/a"),"All categories");
	}
	
	public void clearCart() throws Throwable {

		CartPage_React cartPage = new CartPage_React(driver);
		System.out.println(getText(CartTotalLocator())+"************** output of cart");
		waitUntilDisplayed(CartTotalLocator(), 10);
		if ((!(getText(CartTotalLocator()).equals(""))) && (!(getText(CartTotalLocator()).equalsIgnoreCase("0")))) {
			bringElementIntoView(CartLocator());
			click(CartLocator());
			cartPage.removeAllProduct();

		}

	}
	
	public void clearCart_Magento() throws Throwable {
		if(waitUntilDisplayed(CartLocator(), 5))
		{
			click(CartLocator());
		}
		CartPage cartPage = new CartPage(driver);
		
			cartPage.removeAllProduct();

	}

	public void search(String keyword) throws InterruptedException {
		ClearValue(SearchLocator());
		EnterValue(SearchLocator(), keyword);
		waitUntilDisplayed(SearchSuggetionblock(), 4);
		do
		{
			Thread.sleep(1000);
		}
		while(! getAttribute(SearchLocator(), "value").equalsIgnoreCase(keyword));
		//KeyBoard(Keys.ENTER);
	}


	public PartialStringList getValueFromAutoSuggetionList(String platform) throws IllegalArgumentException, IllegalAccessException{

		//keyBoard(SearchLocator(), Keys.ENTER);
		List<WebElement> autoSuggestSearchResult;
		if(platform.contains("nykaaman"))
		{
			
			autoSuggestSearchResult = getWebElements(autoSearchResultDataNykaaMan());
		}
		else
		{
		autoSuggestSearchResult = getWebElements(autoSearchResultData());
		}
		PartialStringList productValueList = new PartialStringList();

		for (WebElement searchresultvalue : autoSuggestSearchResult) {
			Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(searchresultvalue).concat("/div/b")), "product");
			String TitleProducteName = getText(ProductLocator);
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
		   waitUntilDisplayed(trendingSearch(), 10);
		   List<WebElement> autoSuggestSearchResult = getWebElements(trendingSearch());

		   PartialStringList productValueList = new PartialStringList();

		   for (WebElement searchresultvalue : autoSuggestSearchResult) {
		      Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(searchresultvalue)), "product");
		      String TitleProducteName = getText(ProductLocator);
		      productValueList.add(TitleProducteName.toLowerCase());
		   }
		   TestListner.testing.get().log(LogStatus.INFO, String.valueOf(productValueList));
		   return productValueList;
		}


	public PartialStringList getSearchedResult() throws Throwable {
		keyBoard(SearchLocator(), Keys.ENTER);

		waitUntilDisplayed(searchPage(),20);
		waitForPagetoLoad();
		List<WebElement> ProductsList = getWebElements(productResultsNameList());
		PartialStringList productValueList = new PartialStringList();

		for (WebElement product : ProductsList) {
			Locator ProductLocator = new Locator(By.xpath(getAbsoluteXPath(product)), "product");
			String TitleProducteName = getAttribute(ProductLocator, "title");
			productValueList.add(TitleProducteName.toLowerCase());
		}

		return productValueList;

	}

	public PartialStringList getSearchResultFromPD(ProductDetailData productDetailData,String environmentURL) throws Throwable {
		   String productname = productDetailData.getProductName().replace("...", "");
		   bringElementIntoView(ProductName(productname));
		   mouseOver(ProductName(productname));
		   clickandNavigateWindow(ProductName(productname), productname,environmentURL);
		   Thread.sleep(5000);
		       PartialStringList productvaluelist=getTrendingSearchList();
		        return productvaluelist;
		}

	public boolean navigateToCategory(Header_FooterBarData headerbarData,String environmentURL) throws Throwable {
		if (waitUntilDisplayed(CartPage_React.BackMenuCart(), 3)) {
			click(CartPage_React.BackMenuCart());
			waitUntilDisappear(CartPage_React.BackMenuCart());
		}
		ScrollDown("-2000");
		bringElementIntoView(CategoryTab());
		mouseOver(CategoryTab());
		String navigationList  =headerbarData.getMainmenu();
		TestListner.testing.get().log(LogStatus.INFO,"Navigation Path :  '" + navigationList + "' ");
		String[] navigationMenu     = navigationList.split(">");

		if(navigationMenu.length==1)
		{
			clickandNavigateWindow(MainmenuLocator(navigationMenu[0]),navigationMenu[0],environmentURL);
		}
		else{
			mouseOver(MainmenuLocator(navigationMenu[0]));
		}
		if(navigationMenu.length==2)
		{
			clickandNavigateWindow(SecondLevelLocator(navigationMenu[0],navigationMenu[1]),navigationMenu[1],environmentURL);
		}
		if(navigationMenu.length==3)
		{
			clickandNavigateWindow(ThirdLevelLocator(navigationMenu[0],navigationMenu[2]),navigationMenu[2], environmentURL);
		}


		/*

		if(headerbarData.getMainmenu().equalsIgnoreCase("luxe"))
		{
			bringElementIntoView(LuxeLocator());
			mouseOver(LuxeLocator()	);
		}
		else{
		bringElementIntoView(MainmenuLocator(headerbarData.getMainmenu().toLowerCase()));
		//mouseOver(categoriesLocator());	
		mouseOver(MainmenuLocator(headerbarData.getMainmenu().toLowerCase()));
		}
		waitUntilDisplayed(SecondLevelLocator(headerbarData.getSubmenu()), 5);
		clickandNavigateWindow(SecondLevelLocator(headerbarData.getSubmenu()),headerbarData.getSubmenu().toLowerCase() );
		 */

		FooterBar footerbar = new FooterBar(driver);
		footerbar.CloseAdvertismentPopup();
		//ListingPage_React listingPage = new ListingPage_React(driver);
		ScrollDown("45");
		bringElementIntoView(NykaaLogo());
		mouseOver(NykaaLogo());
		//return	getText(listingPage.breadCrumb()).contains(headerbarData.getSubmenu());
return true;
	}

	public void navigateToQuickLink(Header_FooterBarData headerFooterBar,String environmentURL) throws Throwable, Throwable {
		CartPage cartPage = new CartPage(driver);
		if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
			click(cartPage.BackMenuCart());
			waitUntilDisappear(cartPage.BackMenuCart());
		}
		bringElementIntoView(MainmenuLocator("Makeup"));
		clickandNavigateWindow(beautyServiceLink(), "Beauty Services",environmentURL );
		bringElementIntoView(QuickLink(headerFooterBar.getMainmenu()));
		clickandNavigateWindow(QuickLink(headerFooterBar.getMainmenu()),"Beauty Partner",environmentURL);
		if(driver.getCurrentUrl().contains("http://www.nykaa.com"))
		{
			driver.get(driver.getCurrentUrl().replace("http://www.nykaa.com", environmentURL));
		};



	}

	public boolean navigateToPopUps(Header_FooterBarData headerbarData , String platform,String environmentURL) throws Throwable {CartPage cartPage = new CartPage(driver);
	if (waitUntilDisplayed(cartPage.BackMenuCart(), 3)) {
		click(cartPage.BackMenuCart());
		waitUntilDisappear(cartPage.BackMenuCart());
	}
	if(platform.contains("nykaaman"))
	{
		bringElementIntoView(POPUPSNykaaMan());
		click(POPUPSNykaaMan());
	}
	else
	{
	bringElementIntoView(POPUPS());
	click(POPUPS());
	}
	//bringElementIntoView(MainmenuLocator(headerbarData.getMainmenu().toLowerCase()));
	//mouseOver(categoriesLocator());
	clickandNavigateWindow(MainmenuLocator(headerbarData.getMainmenu()),"Beauty Service",environmentURL);
	//waitUntilDisplayed(subMenuLocator(headerbarData.getSubmenu()), 5);
	//click(subMenuLocator(headerbarData.getSubmenu()));


	/*FooterBar footerbar = new FooterBar(driver);
		footerbar.CloseAdvertismentPopup();
		ListingPage listingPage = new ListingPage(driver);
		return	getText(listingPage.breadCrumb()).contains(headerbarData.getSubmenu());*/
	return true;

	}

	public void navigateToBrand(Header_FooterBarData headerFooterBar,String environmentURL) throws Throwable
	{
		mouseOver(brandLink());
		Thread.sleep(2000);
		//click(brandLogo(headerFooterBar.getSubmenu(),headerFooterBar.getMainmenu()));
		clickandNavigateWindow(brandLogo(headerFooterBar.getSubmenu(),headerFooterBar.getMainmenu()),headerFooterBar.getSubmenu(),environmentURL);

	}

	public void navigateToNetwork(String environmentURL) throws Throwable {
	clickandNavigateWindow(nykaaNetwork()," - Test Site",environmentURL);

	}
	
	

	public List<String> getAllCategories() throws Throwable {
		List<WebElement> categories=new ArrayList<WebElement>();
		List<String> categoryNames=new ArrayList<String>();
		if (waitUntilDisplayed(CartPage_React.BackMenuCart(), 3)) {
			click(CartPage_React.BackMenuCart());
			waitUntilDisappear(CartPage_React.BackMenuCart());
		}
		ScrollDown("-2000");
		bringElementIntoView(CategoryTab());
		mouseOver(CategoryTab());
		categories=getWebElements(allMainMenus());
		for(WebElement categoryLink:categories)
		{
			categoryNames.add(categoryLink.getText());
		}
		return categoryNames;
	}
	
	public boolean navigateToCategory(String navigationList,String environmentURL) throws Throwable {
		if (waitUntilDisplayed(CartPage_React.BackMenuCart(), 3)) {
			click(CartPage_React.BackMenuCart());
			waitUntilDisappear(CartPage_React.BackMenuCart());
		}
		ScrollDown("-2000");
		bringElementIntoView(CategoryTab());
		mouseOver(CategoryTab());
		TestListner.testing.get().log(LogStatus.INFO,"Navigation Path :  '" + navigationList + "' ");
		String[] navigationMenu     = navigationList.split(">");

		if(navigationMenu.length==1)
		{
			clickandNavigateWindow(MainmenuLocator(navigationMenu[0]),navigationMenu[0],environmentURL);
		}
		else{
			mouseOver(MainmenuLocator(navigationMenu[0]));
		}
		if(navigationMenu.length==2)
		{
			clickandNavigateWindow(SecondLevelLocator(navigationMenu[0],navigationMenu[1]),navigationMenu[1],environmentURL);
		}
		if(navigationMenu.length==3)
		{
			clickandNavigateWindow(ThirdLevelLocator(navigationMenu[0],navigationMenu[2]),navigationMenu[2],environmentURL);
		}


		/*

		if(headerbarData.getMainmenu().equalsIgnoreCase("luxe"))
		{
			bringElementIntoView(LuxeLocator());
			mouseOver(LuxeLocator()	);
		}
		else{
		bringElementIntoView(MainmenuLocator(headerbarData.getMainmenu().toLowerCase()));
		//mouseOver(categoriesLocator());	
		mouseOver(MainmenuLocator(headerbarData.getMainmenu().toLowerCase()));
		}
		waitUntilDisplayed(SecondLevelLocator(headerbarData.getSubmenu()), 5);
		clickandNavigateWindow(SecondLevelLocator(headerbarData.getSubmenu()),headerbarData.getSubmenu().toLowerCase() );
		 */

		FooterBar footerbar = new FooterBar(driver);
		footerbar.CloseAdvertismentPopup();
		//ListingPage_React listingPage = new ListingPage_React(driver);
		ScrollDown("45");
		bringElementIntoView(NykaaLogo());
		mouseOver(NykaaLogo());
		//return	getText(listingPage.breadCrumb()).contains(headerbarData.getSubmenu());
return true;
	}
	

	public void search_React(String keyword) throws InterruptedException {
		   ClearValue(SearchLocator_React());
		   EnterValue(SearchLocator_React(), keyword);
		   waitUntilDisplayed(SearchSuggetionblock(), 4);
		   do
		   {
		      Thread.sleep(1000);
		   }
		   while(! getAttribute(SearchLocator_React(), "value").equalsIgnoreCase(keyword));

		   KeyBoard(Keys.ENTER);
		}


	public void AutosearchResultNykaaMan(String keyword) throws InterruptedException {
		ClearValue(SearchLocator());
		EnterValue(SearchLocator(), keyword);
		waitUntilDisplayed(SearchSuggetionblock(), 4);
		do
		{
			Thread.sleep(1000);
		}
		while(! getAttribute(SearchLocator(), "value").equalsIgnoreCase(keyword));
		
	}
}
