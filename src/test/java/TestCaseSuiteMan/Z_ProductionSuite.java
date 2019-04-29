//package TestCaseSuiteMan;
//
//
//import DataNykaa.
//*
//;
//import FrameWorkNykaa.CustomSoftAssert;
//import FrameWorkNykaa.Framework;
//import FrameWorkNykaa.LoginSync;
//import FrameWorkNykaa.Retry;
//import FrameWorkNykaa.TestListner;
//import FrameWorkNykaa.*;
//
//import PagesNykaa.AccountPage;
//import PagesNykaa.CartPage;
//import PagesNykaa.CheckOutPage;
//import PagesNykaa.HeaderBar;
//import PagesNykaa.ListingPage;
//import PagesNykaa.*;
//import io.restassured.RestAssured;
//import io.restassured.config.SSLConfig;
//import io.restassured.http.ContentType;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.Reporter;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Listeners;
//import org.testng.annotations.Test;
//
//import com.relevantcodes.extentreports.ExtentTest;
//
//import java.net.URISyntaxException;
//import java.security.KeyStore;
//import java.util.*;
//import java.util.stream.Collectors;
//
//import static org.hamcrest.Matchers.containsString;
////@Listeners(FrameWorkNykaaMan.TestListner.class)
//@Listeners({TestListner.class})
//public class Z_ProductionSuite {
//
//	Framework framework = new Framework();
//	TestListner testListener = new TestListner();
//	String ClassName = this.getClass().getSimpleName();
//	String PackageName = this.getClass().getPackage().getName();
//	Properties properties = System.getProperties();
//	AssertData SanAssertData = new AssertData("Sanity Checklist", "Desktop", "AutomationResult", "SubElements",
//			properties.getProperty("BUILD_URL") + "testngreports/" + PackageName + "/" + ClassName + "/");
//	String environmentURL;
//	AssertData APIAssertData = new AssertData("Automation Data","APIData","Result","","");
//	private String Token="";
//	private String CustomerId="";
//	@SuppressWarnings("deprecation")
//
//	/********************************************************************** CheckoutTestCases ****************************************************************************************************************/
//	@BeforeClass(alwaysRun = true)
//	public void preSetupFortest() throws Throwable {
//
//		EnvironmentParameterData EnvironmentData = framework.getData(EnvironmentParameterData.class, "WebMan");
//
//		String envURL = System.getProperty("URL");
//		if(envURL!=null)
//		{
//			environmentURL=envURL;
//
//		}else
//		{
//			environmentURL = EnvironmentData.getBaseurl();
//			//environmentURL = "http://www.nykaa.com";
//		}
//
//	}
//
//	@Test(retryAnalyzer = Retry.class, groups = { "production","shadeprod" })
//	public void LoggedInUser_Shade_Add_To_Bag_With_Debit_And_NetBanking() throws URISyntaxException, Throwable {
//
//		AccountData Login = LoginSync.getInstance().getLogin();
//		Header_FooterBarData headerbar1 = framework.getData(Header_FooterBarData.class, "shadenavigation");
//		framework.getData(Header_FooterBarData.class, "sizenavigation");
//		framework.getData(Header_FooterBarData.class, "TD_Face");
//		CheckoutData checKoutData = framework.getData(CheckoutData.class, "valid");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAAMAN);
//
//		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
//		try {
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			String LoggedUserName = accountPage.signInWithYourEmail(Login).toLowerCase();
//			SanCS.assertEquals(LoggedUserName, Login.getFirstName().toLowerCase(), "L1");
//			// Sign in with username
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.clearCart();
//
//			/**********************test 500+*************************************************/
//			ListingPage listingPage = new ListingPage(browser);
//			CartPage cartPage = new CartPage(browser);
//			headerBar.navigateToCategory(headerbar1);
//			ProductDetailData ShadeproductDetadata = listingPage.addAnyShadeProductFromAllProduct();
//			boolean isShadeProductAdded = cartPage.isProductPresentInBag(ShadeproductDetadata);
//			SanCS.assertTrue(isShadeProductAdded, "53A");
//
//			// Add to bag:
//			CheckOutPage checkOutPage = new CheckOutPage(browser);
//			cartPage.checkOut();
//
//			if (browser.getCurrentUrl().contains(environmentURL.substring(environmentURL.lastIndexOf("/") + 1))) {
//				checkOutPage.savedAddressSelect();
//				// System.out.println(checkOutPage.isAddressSelected());
//
//				SanCS.assertEquals(checkOutPage.isAddressSelected(), "", "170");
//				// If signed in then it should land on shipping address page.
//			} else {
//
//				checkOutPage.fillNewAddress(checKoutData);
//			}
//			String checkouturl = browser.getCurrentUrl();
//			checkOutPage.FetchOrderDetail(checKoutData);
//			List<ProductDetailData> Items = checkOutPage.FetchProductDetail();
//			Collections.reverse(Items);
//			for (ProductDetailData item : Items) {
//				System.out.println(item.getProductName());
//				System.out.println(item.getProductPrize());
//			}
//
//			checkOutPage.checkoutWithDebitCardMan(checKoutData);
//			BankingPage bankPage = new BankingPage(browser);
//
//			SanCS.assertTrue(bankPage.isOTPofHDFCpresent(), "L233");
//
//			// Enter "Card Number"
//
//			// Enter "Card Holder name"
//
//			// Enter "Expiry Date"
//
//			new BankingPage(browser);
//			browser.get(checkouturl);
//			System.out.println(checkouturl);
//			boolean isNetBanking = checkOutPage.netBankingCheckoutMan();
//			SanCS.assertTrue(isNetBanking, "L235");
//			SanCS.assertAll();
//
//		} catch (Throwable e) {
//			framework.captureScreenshot(browser);
//			Reporter.log(e.toString());
//			Assert.fail();
//		} finally {
//
//			LoginSync.getInstance().clearLoginLock(Login);
//
//			browser.quit();
//
//		}
//
//	}
//
//	@Test(retryAnalyzer = Retry.class, groups = { "production","simpleprod" })
//	public void Login_simple_add_toBag_With_CashOn_Delivery() throws URISyntaxException, Throwable {
//
//		AccountData Login = LoginSync.getInstance().getLogin();
//		framework.getData(Header_FooterBarData.class, "shadenavigation");
//		Header_FooterBarData headerbar2 = framework.getData(Header_FooterBarData.class, "sizenavigation");
//		framework.getData(Header_FooterBarData.class, "TD_Face");
//		CheckoutData checKoutData = framework.getData(CheckoutData.class, "valid");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAAMAN);
//
//		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
//		try {
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			String LoggedUserName = accountPage.signInWithYourEmail(Login).toLowerCase();
//			SanCS.assertEquals(LoggedUserName, Login.getFirstName().toLowerCase(), "L1");
//			// Sign in with username
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.clearCart();
//			headerBar.navigateToCategory(headerbar2);
//			/**********************test 500+*************************************************/
//			ListingPage listingPage = new ListingPage(browser);
//			List<ProductDetailData> productList = listingPage.getProductList(listingPage.allProducts());
//			List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
//			productlistwithPrizeFilter = productList
//					.stream().filter(o -> Integer.parseInt(o.getProductPrize()) > 500
//							& !o.getProductType().contains("Size") & !o.getProductType().contains("Shade"))
//					.collect(Collectors.toList());
//			ProductDetailData simpleProductData = null;
//			for (ProductDetailData product : productlistwithPrizeFilter) {
//				boolean isadded = listingPage.addToBagSimpleProduct(product);
//				if (isadded == true) {
//					simpleProductData =product;
//					break;
//				}
//			}
//			CartPage cartPage = new CartPage(browser);
//			cartPage.isProductPresentInBag(simpleProductData);
//
//			// Add to bag:
//			CheckOutPage checkOutPage = new CheckOutPage(browser);
//			cartPage.checkOut();
//			browser.getCurrentUrl();
//			if (browser.getCurrentUrl().contains(environmentURL.substring(environmentURL.lastIndexOf("/") + 1))) {
//				checkOutPage.savedAddressSelect();
//				// System.out.println(checkOutPage.isAddressSelected());
//
//				SanCS.assertEquals(checkOutPage.isAddressSelected(), "", "170");
//				// If signed in then it should land on shipping address page.
//			} else {
//
//				checkOutPage.fillNewAddress(checKoutData);
//			}
//
//			checkOutPage.FetchOrderDetail(checKoutData);
//			List<ProductDetailData> Items = checkOutPage.FetchProductDetail();
//			Collections.reverse(Items);
//			for (ProductDetailData item : Items) {
//				System.out.println(item.getProductName());
//				System.out.println(item.getProductPrize());
//			}
//
//			boolean isCashonDelivery = checkOutPage.CashOnDeliveryMan();
//
//			SanCS.assertTrue(isCashonDelivery, "L241");
//			/*
//			 * ConfirmationPage confirmationPage = new
//			 * ConfirmationPage(browser); confirmationPage.getPaymentData();
//			 * RegCS.assertTrue(confirmationPage.
//			 * verifyPaymentDetailWithCheckoutData(CheckoutDataOfUI),"257");
//			 * RegCS.assertTrue(confirmationPage.
//			 * verifyDeliveryDetailWithCheckoutData(CheckoutDataOfUI),"256");
//			 * RegCS.assertEquals(Items,
//			 * confirmationPage.verifyOrderItemDetail(),"259");
//			 */
//			SanCS.assertAll();
//
//		} catch (Throwable e) {
//			framework.captureScreenshot(browser);
//			Reporter.log(e.toString());
//			Assert.fail();
//		} finally {
//
//			LoginSync.getInstance().clearLoginLock(Login);
//			//browser.close();
//			browser.quit();
//
//		}
//	}	
//
//
//	@DataProvider(name = "Data Provider for homePage" ,parallel = true)
//	public Iterator<Object[]> HomePage_Links_200Status() throws Throwable, Throwable {
//		Framework frameWork = new Framework();
//		WebDriver browser = frameWork.getBrowser(CommonConstants.NKYAAMAN);
//		PropertyConfiguration pf = new PropertyConfiguration();
//		pf.getInstance();
//		frameWork.getDataList(Header_FooterBarData.class, "TC3");
//		new CustomSoftAssert(SanAssertData, browser);
//		// Header_FooterBarData headerbarData = new Header_FooterBarData();
//		ArrayList<Object[]> LinksStats = new ArrayList<Object[]>();
//		try {
//			browser.get(environmentURL+"/home_new");
//			HomePage homepage = new HomePage(browser);
//			List<Object[]> widgetlinks = homepage.getHomePagewidgetLinks();
//			List <Object[]> bannerlinks = homepage.getBannerLink();
//			for(Object bannerlink: bannerlinks)
//			{
//				LinksStats.add( new Object[]{bannerlink});
//
//			}
//
//			for(Object widgetlink: widgetlinks)
//			{
//				LinksStats.add( new Object[]{widgetlink});
//
//			}
//
//
//			return  LinksStats.listIterator();
//		}catch (Throwable e) {
//			frameWork.captureScreenshot(browser);
//			Reporter.log(e.toString());
//			Assert.fail();
//			return  LinksStats.listIterator();
//		} finally {
//
//			// LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//
//	}
//
//	@DataProvider(name = "Data Provider for cancel order" ,parallel = true)
//	public Iterator<Object[]> getLogins(){
//		List<Object[]> Logins = framework.getDataProvider(AccountData.class,"loginset");
//		return  Logins.listIterator();
//	}
//
//
//	@Test(groups={"cancelorder"},dataProvider="Data Provider for cancel order")
//	public void cancelProductsFromAutomationLogin(AccountData Login) throws Throwable {
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAAMAN);
//		try{
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			accountPage.signInWithYourEmail(Login);
//			accountPage.cancelOrder();
//		}catch(Exception E)
//		{
//			framework.captureScreenshot(browser);
//			Reporter.log(E.toString());
//			Assert.fail();
//		}
//		finally
//		{
//			browser.quit();
//		}
//
//
//	}
//
//
//	@Test(retryAnalyzer = Retry.class,groups = { "prodsearch", "searchautosuggest" ,"production","AddToBag"})
//	public void AutoSuggestTestCase() throws Throwable, Throwable {
//		Framework frameWork = new Framework();
//
//		WebDriver browser = frameWork.getBrowser(CommonConstants.NKYAAMAN);
//		PropertyConfiguration pf = new PropertyConfiguration();
//		pf.getInstance();
//		// this.environmentURL = prop.getProperty("environmentName");
//		List<Header_FooterBarData> headerbarDataList = frameWork.getDataList(Header_FooterBarData.class, "TC3");
//		CustomSoftAssert CS = new CustomSoftAssert(SanAssertData, browser);
//		// Header_FooterBarData headerbarData = new Header_FooterBarData();
//		try {
//			browser.get(environmentURL);
//			new AccountPage(browser);
//
//			HeaderBar headerBar = new HeaderBar(browser);
//			for (Header_FooterBarData headerbarData : headerbarDataList) {
//
//				PartialStringList trendingResult = headerBar.getTrendingSearchList();
//				CS.assertTrue(!(trendingResult.isEmpty()),"271B");
//				headerBar.search(headerbarData.get_search_keyword());
//				PartialStringList AutosuggestSearchedResult = headerBar.getValueFromAutoSuggetionList(CommonConstants.NKYAAMAN);
//				CS.assertTrue(AutosuggestSearchedResult.contains(headerbarData.get_search_keyword().toLowerCase()),
//						"271A");
//				PartialStringList PopularSearchResult = headerBar.getValuefromPopularSuggetionList();
//				CS.assertTrue(PopularSearchResult.contains(headerbarData.get_search_keyword().toLowerCase()),
//						"271C");
//				PartialStringList     searchResult =     headerBar.getSearchedResult();
//				CS.assertTrue(searchResult.contains(headerbarData.get_search_keyword().toLowerCase()),
//						"271D");
//				ListingPage listingPage = new ListingPage(browser);
//				ProductDetailData productDetailData = listingPage.addAnyShadeProductFromSearchedProductList();
//				CartPage CartPage = new CartPage(browser);
//				CS.assertTrue(CartPage.isProductPresentInBag(productDetailData),"271E");
//
//
//				CS.assertAll();
//
//
//			}
//		} catch (Throwable e) {
//			frameWork.captureScreenshot(browser);
//			Reporter.log(e.toString());
//			Assert.fail();
//		} finally {
//
//			// LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//	}
//
//
//	@Test(retryAnalyzer = Retry.class,groups = { "LIVE"})
//	public void Place_Order_On_Live() throws URISyntaxException, Throwable {
//		TestRunner.isLive=true;
//		int cartValue=0;
//		boolean addtocart=true;
//		String Live_URL="http://www.nykaa.com";
//		AccountData Login = framework.getData(AccountData.class, "LIVE");
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category_react");
//		CheckoutData checKoutData = framework.getData(CheckoutData.class, "LIVE");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAAMAN);
//		//String search_keyword="Grace Cole Summer Florals Invigorating Essentials Combo";
//		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
//		try {
//			Email.failureReason="Login";
//			Email.failureReasonMessage="User was not able to login from home page as home page is taking too long to load completely.";
//			ExtentTest childTest1 = testListener.startChild("Existing User Login Test");
//			childTest1.setDescription("This test verifies that user is able to login with existing credentials.");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(Live_URL);
//			AccountPage_React accountPage = new AccountPage_React(browser);
//			String LoggedUserName = accountPage.signInWithYourEmailMan(Login).toLowerCase();
//			SanCS.assertTrue((LoggedUserName.equalsIgnoreCase(Login.getFirstName().toLowerCase())?true:false),"Existing User Login Test");
//
//			/*//Search a product
//
//			ExtentTest childTest2 = testListener.startChild("Search Test");
//			childTest2.setDescription("This test verifies that existing user is able to search");
//			TestListner.extentMap.get().put("child2", childTest2);
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.search(search_keyword);
//			PartialStringList SearchedResult = headerBar.getSearchedResult();
//			SanCS.assertTrue(SearchedResult.contains(search_keyword.toLowerCase()));*/
//
//			ExtentTest childTest3 = testListener.startChild("Cancel previous order if present");
//			childTest3.setDescription("This test verifies that user is able to cancel the order if there is any uncancelled order in my orders section.");
//			TestListner.extentMap.get().put("child3", childTest3);
//			Email.failureReason="Cancel Order";
//			Email.failureReasonMessage="Order details are not present in My order details section.";
//			accountPage.cancelOrder();
//
//			ExtentTest childTest2 = testListener.startChild("Add products to cart Test");
//			childTest2.setDescription("This test verifies that user is able to add any Shade/Size product from L1 category page all products section into cart");
//			TestListner.extentMap.get().put("child2", childTest2);
//			Email.failureReason="Add Product to cart";
//			Email.failureReasonMessage="User is not able to add products to cart.";
//			HeaderBar headerBar = new HeaderBar(browser);
//			//browser.get(Live_URL);
//			headerBar.navigateToCategory(headerbarData);
//			ListingPage_React listingPage = new ListingPage_React(browser);
//			CartPage_React cartPage = new CartPage_React(browser);
//			cartValue=Integer.parseInt(cartPage.getShoppingTableData("Subtotal").replaceAll("[^0-9]", ""));
//			if(cartValue>=500)
//			{
//				addtocart=false;
//			}
//			ProductDetailData ProductDetailData=null;
//			while(cartValue<500)
//			{
//				browser.navigate().refresh();
//				ProductDetailData = listingPage.addAnyShadeProductFromAllProduct();
//				if(ProductDetailData.getProductName()!=null)
//				{
//					cartValue=Integer.parseInt(cartPage.getShoppingTableData("Subtotal").replaceAll("[^0-9]", ""));
//				}
//				if(cartValue<500 )
//				{
//					browser.navigate().refresh();
//					ProductDetailData = listingPage.addAnySimpleProductFromAllProducts();
//
//					if(ProductDetailData.getProductName()!=null)
//					{
//						cartValue=Integer.parseInt(cartPage.getShoppingTableData("Subtotal").replaceAll("[^0-9]", ""));
//					}
//					if(cartValue<500)
//					{
//						browser.navigate().refresh();
//						ProductDetailData = listingPage.addAnySizeProductFromAllProduct();
//						if(ProductDetailData.getProductName()!=null)
//						{
//							cartValue=Integer.parseInt(cartPage.getShoppingTableData("Subtotal").replaceAll("[^0-9]", ""));
//						}
//					}
//				}
//			}
//			if(!addtocart)
//			{
//				SanCS.assertTrue(true,"Add products to cart Test");
//			}
//			else
//			{
//				SanCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData),"Add products to cart Test");
//			}
//			/*if(!cartPage.increaseFirstProduct())
//			{
//				ProductDetailData ProductDetailDataSize = listingPage.addAnySizeProductFromAllProduct();
//				SanCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailDataSize));
//			}*/
//
//			/*ExtentTest childTest3 = testListener.startChild("Add product to cart test");
//			childTest3.setDescription(
//					"This test verifies that user is able to add product to cart");
//			TestListner.extentMap.get().put("child3", childTest3);
//			headerBar.clearCart();
//			ListingPage listingPage = new ListingPage(browser);
//			ProductDetailData simpleProductData = listingPage.addSimpleProductFromSearchedProductList();
//			CartPage cartPage = new CartPage(browser);
//			cartPage.isProductPresentInBag(simpleProductData);*/
//
//			ExtentTest childTest9 = testListener.startChild("Apply Coupon Test");
//			childTest9.setDescription("This test verifies that user is able to apply/remove coupon code");
//			TestListner.extentMap.get().put("child9", childTest9);
//			Email.failureReason="Apply Coupon";
//			Email.failureReasonMessage="User is not able to apply coupon on sliding cart.";
//			CheckoutData checkoutData = framework.getData(CheckoutData.class, "valid");
//
//			SanCS.assertTrue(cartPage.ApplyCoupon(checkoutData).contains("-"),"Apply Coupon Test");
//
//			ExtentTest childTest4 = testListener.startChild("Select Delivery Address Test");
//			childTest4.setDescription("This test verifies that user is able to select/enter delivery address");
//			TestListner.extentMap.get().put("child4", childTest4);
//			Email.failureReason="Enter/Select Delivery Address";
//			Email.failureReasonMessage="User is not able to Enter/Select delivery address and proceed due to page loading issue.";
//			CheckOutPage checkOutPage = new CheckOutPage(browser);
//			String checkoutMessage=cartPage.checkOut();
//			if(checkoutMessage.contains("add more"))
//			{
//				browser.navigate().refresh();
//				ProductDetailData = listingPage.addAnyShadeProductFromAllProduct();
//			}
//			while (!browser.getCurrentUrl().contains("checkout")) {
//				Thread.sleep(1000);
//			}
//
//			browser.getCurrentUrl();
//
//			if (browser.getCurrentUrl().contains(Live_URL.substring(Live_URL.lastIndexOf("/") + 1))) {
//				checkOutPage.savedAddressSelect();
//
//			} if(checkOutPage.isAddressSelected()==null) {
//
//				checkOutPage.fillNewAddress(checKoutData);
//			}
//			SanCS.assertTrue((checkOutPage.isAddressSelected().equals("true")? true:false),"Select Delivery Address Test");
//
//			/*ExtentTest childTest5 = testListener.startChild("Checkout with Debit/Credit card test");
//			childTest5.setDescription(
//					"This test verifies that user is able to checkout with Credit/Debit card as payment method");
//			TestListner.extentMap.get().put("child5", childTest5);
//
//			checkOutPage.checkoutWithDebitCard(checKoutData);
//			BankingPage bankPage = new BankingPage(browser);
//
//			SanCS.assertTrue(bankPage.isOTPofHDFCpresent());
//
//			ExtentTest childTest6 = testListener.startChild("Checkout with Net Banking test");
//			childTest6.setDescription(
//					"This test verifies that user is able to checkout with Net Banking as payment method");
//			TestListner.extentMap.get().put("child6", childTest6);
//			browser.get(checkouturl);
//			System.out.println(checkouturl);
//			boolean isNetBanking = checkOutPage.netBankingCheckout();
//			SanCS.assertTrue(isNetBanking);
//			ExtentTest childTest7 = testListener.startChild("Checkout with Wallet test");
//			childTest7.setDescription("This test verifies that user is able to checkout with Wallet as payment method");
//			TestListner.extentMap.get().put("child7", childTest7);
//			browser.get(checkouturl);
//			boolean isMobikwik = checkOutPage.MobikwikWalletCheckout();
//
//			SanCS.assertTrue(isMobikwik);*/
//
//			ExtentTest childTest8 = testListener.startChild("Place COD order Test");
//			childTest8.setDescription("This test verifies that user is able to place COD order");
//			TestListner.extentMap.get().put("child8", childTest8);
//			Email.failureReason="Place COD order";
//			Email.failureReasonMessage="User is not able to place COD order.";
//			//browser.get(checkouturl);
//			boolean isCashonDelivery = checkOutPage.CashOnDeliveryMan();
//
//			SanCS.assertTrue(isCashonDelivery,"Place COD order Test");
//			if(isCashonDelivery) {
//				ExtentTest childTest10 = testListener.startChild("Cancel Order Test");
//				childTest10.setDescription("This test verifies that user is able to cancel the order just placed");
//				TestListner.extentMap.get().put("child10", childTest10);
//				Email.failureReason="Cancel Order";
//				Email.failureReasonMessage="Order details are not present in My order details section.";
//				SanCS.assertTrue(accountPage.cancelLatestOrder(),"Cancel Order Test");
//			}
//			//SanCS.assertAll();
//
//		} catch (Exception e) {
//
//			if(e.getMessage()!=null && e.getMessage().contains("timeout"))
//			{
//				System.out.println(e.getMessage());
//				Email.failureReasonMessage="Page Timeout on "+browser.getCurrentUrl();
//				Framework.webPageLoadTestResultUrl=framework.hitWebPageTestAPI(browser);
//			}
//			framework.logErrorWithSnapshot(browser, e);
//			Reporter.log(e.toString());
//			Assert.fail();
//		} finally {
//
//			LoginSync.getInstance().clearLoginLock(Login);
//			browser.quit();
//		}
//
//	}
//
//	@Test(retryAnalyzer = Retry.class,groups = { })
//	public void Place_Order_On_Live_Magento() throws URISyntaxException, Throwable {
//		TestRunner.isLive=true;
//		int cartValue=0;
//		String Live_URL="http://www.nykaa.com";
//		AccountData Login = framework.getData(AccountData.class, "LIVE");
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category");
//		CheckoutData checKoutData = framework.getData(CheckoutData.class, "LIVE");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAAMAN);
//		//String search_keyword="Grace Cole Summer Florals Invigorating Essentials Combo";
//		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
//		try {
//			Email.failureReason="Login";
//			Email.failureReasonMessage="User was not able to login from home page as home page is taking too long to load completely.";
//			ExtentTest childTest1 = testListener.startChild("Existing User Login Test");
//			childTest1.setDescription("This test verifies that user is able to login with existing credentials.");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(Live_URL);
//			AccountPage accountPage = new AccountPage(browser);
//			String LoggedUserName = accountPage.signInWithYourEmail(Login).toLowerCase();
//			SanCS.assertTrue((LoggedUserName.equalsIgnoreCase(Login.getFirstName().toLowerCase())?true:false),"Existing User Login Test");
//
//			/*//Search a product
//			ExtentTest childTest2 = testListener.startChild("Search Test");
//			childTest2.setDescription("This test verifies that existing user is able to search");
//			TestListner.extentMap.get().put("child2", childTest2);
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.search(search_keyword);
//			PartialStringList SearchedResult = headerBar.getSearchedResult();
//			SanCS.assertTrue(SearchedResult.contains(search_keyword.toLowerCase()));*/
//
//			ExtentTest childTest3 = testListener.startChild("Cancel previous order if present");
//			childTest3.setDescription("This test verifies that user is able to cancel the order if there is any uncancelled order in my orders section.");
//			TestListner.extentMap.get().put("child3", childTest3);
//			Email.failureReason="Cancel Order";
//			Email.failureReasonMessage="Order details are not present in My order details section.";
//			accountPage.cancelOrder();
//
//			ExtentTest childTest2 = testListener.startChild("Add products to cart Test");
//			childTest2.setDescription("This test verifies that user is able to add any Shade/Size product from L1 category page all products section into cart");
//			TestListner.extentMap.get().put("child2", childTest2);
//			Email.failureReason="Add Product to cart";
//			Email.failureReasonMessage="User is not able to add products to cart.";
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.navigateToCategory(headerbarData);
//			ListingPage listingPage = new ListingPage(browser);
//			CartPage cartPage = new CartPage(browser);
//			headerBar.clearCart_Magento();
//			ProductDetailData ProductDetailData=null;
//			while(cartValue<500)
//			{
//				ProductDetailData = listingPage.addAnyShadeProductFromAllProduct();
//				if(ProductDetailData.getProductName()!=null)
//				{
//					cartValue+=Integer.parseInt(ProductDetailData.getProductPrize());
//				}
//				if(cartValue<500 )
//				{
//					ProductDetailData = listingPage.addAnySizeProductFromAllProduct();
//					if(ProductDetailData.getProductName()!=null)
//					{
//						cartValue+=Integer.parseInt(ProductDetailData.getProductPrize());
//					}
//					/*if(cartValue<500)
//					{
//						ProductDetailData = listingPage.addAnySimpleProductFromAllProducts();
//						if(ProductDetailData.getProductName()!=null)
//						{
//							cartValue+=Integer.parseInt(ProductDetailData.getProductPrize());
//						}
//					}*/
//				}
//			}
//			SanCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData),"Add products to cart Test");
//			/*if(!cartPage.increaseFirstProduct())
//			{
//				ProductDetailData ProductDetailDataSize = listingPage.addAnySizeProductFromAllProduct();
//				SanCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailDataSize));
//			}*/
//
//			/*ExtentTest childTest3 = testListener.startChild("Add product to cart test");
//			childTest3.setDescription(
//					"This test verifies that user is able to add product to cart");
//			TestListner.extentMap.get().put("child3", childTest3);
//			headerBar.clearCart();
//			ListingPage listingPage = new ListingPage(browser);
//			ProductDetailData simpleProductData = listingPage.addSimpleProductFromSearchedProductList();
//			CartPage cartPage = new CartPage(browser);
//			cartPage.isProductPresentInBag(simpleProductData);*/
//
//			ExtentTest childTest9 = testListener.startChild("Apply Coupon Test");
//			childTest9.setDescription("This test verifies that user is able to apply/remove coupon code");
//			TestListner.extentMap.get().put("child9", childTest9);
//			Email.failureReason="Apply Coupon";
//			Email.failureReasonMessage="User is not able to apply coupon on sliding cart.";
//			CheckoutData checkoutData = framework.getData(CheckoutData.class, "valid");
//
//			SanCS.assertTrue(cartPage.ApplyCoupon(checkoutData).contains("-"),"Apply Coupon Test");
//
//			ExtentTest childTest4 = testListener.startChild("Select Delivery Address Test");
//			childTest4.setDescription("This test verifies that user is able to select/enter delivery address");
//			TestListner.extentMap.get().put("child4", childTest4);
//			Email.failureReason="Enter/Select Delivery Address";
//			Email.failureReasonMessage="User is not able to Enter/Select delivery address and proceed due to page loading issue.";
//			CheckOutPage checkOutPage = new CheckOutPage(browser);
//			cartPage.checkOut();
//			String checkouturl = "";
//			while (!browser.getCurrentUrl().contains("checkout")) {
//				Thread.sleep(1000);
//			}
//
//			checkouturl = browser.getCurrentUrl();
//
//			if (browser.getCurrentUrl().contains(Live_URL.substring(Live_URL.lastIndexOf("/") + 1))) {
//				checkOutPage.savedAddressSelect();
//
//			} if(checkOutPage.isAddressSelected()==null) {
//
//				checkOutPage.fillNewAddress(checKoutData);
//			}
//			SanCS.assertTrue((checkOutPage.isAddressSelected().equals("true")? true:false),"Select Delivery Address Test");
//
//			/*ExtentTest childTest5 = testListener.startChild("Checkout with Debit/Credit card test");
//			childTest5.setDescription(
//					"This test verifies that user is able to checkout with Credit/Debit card as payment method");
//			TestListner.extentMap.get().put("child5", childTest5);
//			checkOutPage.checkoutWithDebitCard(checKoutData);
//			BankingPage bankPage = new BankingPage(browser);
//			SanCS.assertTrue(bankPage.isOTPofHDFCpresent());
//			ExtentTest childTest6 = testListener.startChild("Checkout with Net Banking test");
//			childTest6.setDescription(
//					"This test verifies that user is able to checkout with Net Banking as payment method");
//			TestListner.extentMap.get().put("child6", childTest6);
//			browser.get(checkouturl);
//			System.out.println(checkouturl);
//			boolean isNetBanking = checkOutPage.netBankingCheckout();
//			SanCS.assertTrue(isNetBanking);
//			ExtentTest childTest7 = testListener.startChild("Checkout with Wallet test");
//			childTest7.setDescription("This test verifies that user is able to checkout with Wallet as payment method");
//			TestListner.extentMap.get().put("child7", childTest7);
//			browser.get(checkouturl);
//			boolean isMobikwik = checkOutPage.MobikwikWalletCheckout();
//			SanCS.assertTrue(isMobikwik);*/
//
//			ExtentTest childTest8 = testListener.startChild("Place COD order Test");
//			childTest8.setDescription("This test verifies that user is able to place COD order");
//			TestListner.extentMap.get().put("child8", childTest8);
//			Email.failureReason="Place COD order";
//			Email.failureReasonMessage="User is not able to place COD order.";
//			browser.get(checkouturl);
//			boolean isCashonDelivery = checkOutPage.CashOnDeliveryMan();
//
//			SanCS.assertTrue(isCashonDelivery,"Place COD order Test");
//			if(isCashonDelivery) {
//				ExtentTest childTest10 = testListener.startChild("Cancel Order Test");
//				childTest10.setDescription("This test verifies that user is able to cancel the order just placed");
//				TestListner.extentMap.get().put("child10", childTest10);
//				Email.failureReason="Cancel Order";
//				Email.failureReasonMessage="Order details are not present in My order details section.";
//				SanCS.assertTrue(accountPage.cancelLatestOrder(),"Cancel Order Test");
//			}
//			//SanCS.assertAll();
//
//		} catch (Exception e) {
//			
//			if(e.getMessage()!=null && e.getMessage().contains("timeout"))
//			{
//				Email.failureReasonMessage="Page Timeout on "+browser.getCurrentUrl();
//				Framework.webPageLoadTestResultUrl=framework.hitWebPageTestAPI(browser);
//			}
//			framework.logErrorWithSnapshot(browser, e);
//			Reporter.log(e.toString());
//			Assert.fail();
//		} finally {
//
//			LoginSync.getInstance().clearLoginLock(Login);
//			browser.quit();
//		}
//
//	}
//
//
//
//	@Test(retryAnalyzer = Retry.class, groups = {"production"})
//	public void Login_add_to_bagSize_with_WalletAndPayumoney() throws URISyntaxException, Throwable {
//
//		AccountData Login = LoginSync.getInstance().getLogin();
//		framework.getData(Header_FooterBarData.class, "shadenavigation");
//		Header_FooterBarData headerbar2 = framework.getData(Header_FooterBarData.class, "sizenavigation");
//		framework.getData(Header_FooterBarData.class, "TD_Face");
//		CheckoutData checKoutData = framework.getData(CheckoutData.class, "valid");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAAMAN);
//
//		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
//		try {
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			String LoggedUserName = accountPage.signInWithYourEmail(Login).toLowerCase();
//			SanCS.assertEquals(LoggedUserName, Login.getFirstName().toLowerCase(), "L1");
//			// Sign in with username
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.clearCart();
//
//			/**********************test 500+*************************************************/
//			ListingPage listingPage = new ListingPage(browser);
//
//			CartPage cartPage = new CartPage(browser);
//
//			headerBar.navigateToCategory(headerbar2);
//			ProductDetailData SizeproductDetadata = listingPage.addAnySizeProductFromAllProduct();
//			boolean isSizeProductAdded = cartPage.isProductPresentInBag(SizeproductDetadata);
//
//			SanCS.assertTrue(isSizeProductAdded, "53B");
//			// Add to bag:
//			CheckOutPage checkOutPage = new CheckOutPage(browser);
//			cartPage.checkOut();
//			String checkouturl = browser.getCurrentUrl();
//			if (browser.getCurrentUrl().contains(environmentURL.substring(environmentURL.lastIndexOf("/") + 1))) {
//				checkOutPage.savedAddressSelect();
//				// System.out.println(checkOutPage.isAddressSelected());
//
//				SanCS.assertEquals(checkOutPage.isAddressSelected(), "", "170");
//				// If signed in then it should land on shipping address page.
//			} else {
//
//				checkOutPage.fillNewAddress(checKoutData);
//			}
//
//
//			boolean isPayuCheckout = checkOutPage.payumoneyCheckout();
//
//			SanCS.assertTrue(isPayuCheckout, "243");
//
//			browser.get(checkouturl);
//			boolean isMobikwik = checkOutPage.MobikwikWalletCheckoutMan();
//
//			SanCS.assertTrue(isMobikwik, "247");
//
//			SanCS.assertAll();
//
//		} catch (Throwable e) {
//			framework.captureScreenshot(browser);
//			Reporter.log(e.toString());
//			Assert.fail();
//		} finally {
//
//			LoginSync.getInstance().clearLoginLock(Login);
//
//			browser.quit();
//
//		}
//
//
//
//	}
//
//	@DataProvider(name = "Data Provide For Live" ,parallel = true)
//	public Iterator<Object[]> dataListForSearch() {
//		Framework framework = new Framework();
//		List<Object[]> apiDataList = framework.getDataProvider(APIData.class, "LiveURL");
//		return apiDataList.listIterator();
//
//	}
//	@Test(dataProvider="Data Provide For Live",groups = {"apiprod","production"},dependsOnMethods="LoginCredentialApi")
//	public void Live_URL_OK_Status_Check(APIData apiData) throws Throwable
//	{
//		CustomSoftAssert CS = new CustomSoftAssert(APIAssertData);
//		RestAssured.baseURI = environmentURL;
//		RestAssured.basePath = apiData.getPath();
//		String APIParam ;
//		if(apiData.getParameter().equalsIgnoreCase("null"))
//		{APIParam = "";
//
//		}else
//		{APIParam = apiData.getParameter();
//
//		}
//		try {
//			if(apiData.getMethod().equalsIgnoreCase("post"))
//			{
//				Response resp = RestAssured.given().config(RestAssured.config().sslConfig(
//						new SSLConfig().relaxedHTTPSValidation())).contentType(ContentType.URLENC).body(apiData.getParameter().replace("TokenValue", Token).replace("CustomerValue", CustomerId)).
//						when()
//						.post("");
//				System.out.println(apiData.getParameter());
//				System.out.println(resp.asString());
//				Reporter.log(resp.asString());
//				resp.then().assertThat().statusCode(200);
//				CS.assertAll();
//			}
//			if(apiData.getMethod().equalsIgnoreCase("get"))
//			{   Response resp ; 
//
//			if(APIParam.equalsIgnoreCase(""))
//			{
//				resp= RestAssured.given().config(RestAssured.config().sslConfig(
//						new SSLConfig().relaxedHTTPSValidation())).contentType(ContentType.URLENC).
//						when()
//						.get("");
//
//
//			}
//			else{
//				resp = RestAssured.given().config(RestAssured.config().sslConfig(
//						new SSLConfig().relaxedHTTPSValidation())).contentType(ContentType.URLENC).
//						when()
//						.get("?"+apiData.getParameter().replace("TokenValue", Token).replace("CustomerValue", CustomerId));
//			}
//
//			System.out.println(resp.asString());
//			Reporter.log(resp.asString());
//			/*CS.assertTrue(resp.asString().contains(apiData.getExpected()),apiData.getTestcaseid());
//						CS.assertAll();
//						resp.then().assertThat().body(containsString(apiData.getExpected()));*/
//			resp.then().assertThat().statusCode(200);
//
//
//
//
//			}
//		} catch (AssertionError e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			Assert.fail("Failed Testcase");
//		}
//
//
//
//
//
//
//	}
//
//
//
//	@Test
//	public void LoginCredentialApi() throws Throwable
//	{     Framework frameWork = new Framework();
//	APIData    apiData  = frameWork.getData(APIData.class, "login");
//	CustomSoftAssert CS = new CustomSoftAssert(APIAssertData);
//	RestAssured.baseURI = environmentURL;
//	RestAssured.basePath =apiData.getPath();
//
//	try {
//		if(apiData.getMethod().equalsIgnoreCase("post"))
//		{
//
//			Response resp = RestAssured.given().config(RestAssured.config().sslConfig(
//					new SSLConfig().relaxedHTTPSValidation())).contentType(ContentType.URLENC).body(apiData.getParameter()).
//					when()
//					.post("");
//			System.out.println(resp.asString());
//			resp.then().assertThat().statusCode(200);
//			Reporter.log(resp.asString());
//			CS.assertTrue(resp.asString().contains(apiData.getExpected()),apiData.getTestcaseid());
//
//
//			resp.then().assertThat().body(containsString(apiData.getExpected()));
//			String responseBody = resp.getBody().asString();
//
//
//			JsonPath jsonPath = new JsonPath(responseBody);
//			Token = jsonPath.get("response.authentication_token");
//			CustomerId = jsonPath.getString("response.customer_id");
//			CS.assertAll();
//		}
//	} catch (AssertionError e) {
//		// TODO Auto-generated catch block
//		Assert.fail("Failed Testcase Please check log");
//	}
//	}
//
//
//
//
//	@Test(dataProvider="Data Provider for homePage",groups = {"homepage"})
//	public void HomePageCheck_200Status(String URL) throws Throwable
//	{
//		new CustomSoftAssert(APIAssertData);
//		RestAssured.baseURI = URL;
//		System.out.println(URL);
//
//
//
//
//
//
//
//
//
//	}
//
//
//
//
//
//	/* @DataProvider(name = "Data Provide For TC1" ,parallel = true)
//			  public Iterator<Object>  dataListForSearch() {
//			 	Framework framework = new Framework();
//			 	List<Object> apiDataList = framework.getDataProvider(APIData.class, "ITR_TC1");
//					return apiDataList.iterator();
//
//			 }
//				@Test(dataProvider="Data Provide For TC1",groups = {"api"},dependsOnMethods="API_TestCase_Execution_Status")
//				public void API_USERID_TOKEN(APIData apiData) throws Throwable
//				{
//					//CustomSoftAssert CS = new CustomSoftAssert(APIAssertData);
//					RestAssured.baseURI ="http://www.nykaa.com";
//					RestAssured.basePath =apiData.getPath();
//
//					try {
//						if(apiData.getMethod().equalsIgnoreCase("post"))
//						{
//						Response resp = RestAssured.given().config(RestAssured.config().sslConfig(
//								new SSLConfig().relaxedHTTPSValidation())).contentType(ContentType.URLENC).body(apiData.getParameter().replace("TokenValue", Token).replace("CustomerValue", CustomerId)).
//								when()
//								.post("");
//						System.out.println(apiData.getParameter().replace("TokenValue", Token).replace("CustomerValue", CustomerId));
//						System.out.println(resp.asString());
//						Reporter.log(resp.asString());
//			            resp.then().assertThat().statusCode(200);
//			            CS.assertTrue(resp.asString().contains(apiData.getExpected()),apiData.getTestcaseid());
//			            CS.assertAll();
//			            resp.then().assertThat().body(containsString(apiData.getExpected()));
//						}
//						if(apiData.getMethod().equalsIgnoreCase("get"))
//						{
//							Response resp = RestAssured.given().config(RestAssured.config().sslConfig(
//									new SSLConfig().relaxedHTTPSValidation())).contentType(ContentType.URLENC).
//									when()
//									.get("?"+apiData.getParameter());
//							System.out.println(resp.asString());
//							Reporter.log(resp.asString());
//							CS.assertTrue(resp.asString().contains(apiData.getExpected()),apiData.getTestcaseid());
//							CS.assertAll();
//							resp.then().assertThat().body(containsString(apiData.getExpected()));
//						    resp.then().assertThat().statusCode(200);
//
//
//
//
//						}
//					} catch (AssertionError e) {
//						// TODO Auto-generated catch block
//						Assert.fail("Failed Testcase");
//					}
//
//
//
//
//
//
//
//				}*/
//
//}
