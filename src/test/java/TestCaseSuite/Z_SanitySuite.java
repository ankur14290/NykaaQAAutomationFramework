//package TestCaseSuite;
//
//
//
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Properties;
//import java.util.stream.Collectors;
//
//import DataNykaa.*;
//import PagesNykaa.*;
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.Reporter;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;
//
//import DataNykaa.AccountData;
//import DataNykaa.AnalyticsData;
//import DataNykaa.AssertData;
//import DataNykaa.BeautyServiceData;
//import DataNykaa.CheckoutData;
//import DataNykaa.EnvironmentParameterData;
//import DataNykaa.Header_FooterBarData;
//import DataNykaa.ProductDetailData;
//import FrameWorkNykaa.CommonConstants;
//import FrameWorkNykaa.CustomSoftAssert;
//import FrameWorkNykaa.Framework;
//import FrameWorkNykaa.LoginSync;
//import FrameWorkNykaa.PartialStringList;
//import FrameWorkNykaa.PropertyConfiguration;
//import FrameWorkNykaa.TestListner;
//import PagesNykaa.AccountPage;
//import PagesNykaa.AnalyticsInfoPage;
//import PagesNykaa.BankingPage;
//import PagesNykaa.BeautyServicePage;
//import PagesNykaa.CartPage;
//import PagesNykaa.CartPage_React;
//import PagesNykaa.CheckOutPage;
//import PagesNykaa.ConfirmationPage;
//import PagesNykaa.HeaderBar;
//import PagesNykaa.HomePage;
//import PagesNykaa.ListingPage_React;
//import PagesNykaa.ProductPage;
//
//public class Z_SanitySuite {
//
//	Framework framework = new Framework();
//	TestListner testListener = new TestListner();
//	String ClassName = this.getClass().getSimpleName();
//	String PackageName = this.getClass().getPackage().getName();
//	Properties properties = System.getProperties();
//	String environmentURL ="";
//	AssertData SanAssertData = new AssertData("Sanity Checklist", "Desktop", "AutomationResult", "SubElements",
//			properties.getProperty("BUILD_URL") + "testngreports/" + PackageName + "/" + ClassName + "/");
//
//	/**********************************************************************
//	 * CheckoutTestCases
//	 ****************************************************************************************************************/
//	@BeforeClass(alwaysRun = true)
//	public void preSetupFortest() throws Throwable {
//		String resultField = System.getProperty("ResultFieldName");
//		if (resultField != null) {
//			SanAssertData = new AssertData("Sanity Checklist", "Desktop", resultField, "SubElements",
//					properties.getProperty("BUILD_URL") + "testngreports/" + PackageName + "/" + ClassName + "/");
//		}
//		EnvironmentParameterData EnvironmentData = framework.getData(EnvironmentParameterData.class, "Web");
//		String envURL = System.getProperty("URL");
//		if (envURL != null) {
//			environmentURL = envURL;
//
//		} else {
//			environmentURL = EnvironmentData.getBaseurl();
//			// environmentURL = "http://www.nykaa.com";
//		}
//
//	}
//
//	@AfterMethod(alwaysRun=true)
//	public void flushExtent()
//	{
//		Framework.extentReports.flush();
//	}
//
//	@Test(groups = { "sanity","Wsanity", "Checkout", "checkout01", "less500","live","react"})
//	public void Checkout_Flow_With_Greater_Than_500_Already_register_user() throws URISyntaxException, Throwable {
//
//		AccountData Login = LoginSync.getInstance().getLogin();
//		Header_FooterBarData headerbar1 = framework.getData(Header_FooterBarData.class, "shadenavigation");
//		Header_FooterBarData headerbar2 = framework.getData(Header_FooterBarData.class, "sizenavigation");
//		CheckoutData checKoutData = framework.getData(CheckoutData.class, "valid");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		String lastOrder=null;
//		boolean isShadeProductAdded=false;
//		boolean isSizeProductAdded=false;
//		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
//		try {
//			ExtentTest childTest1 = testListener.startChild("Existing User Login test");
//			childTest1.setDescription("This test verifies that user is able to login with existing credentials.");
//			TestListner.extentMap.get().put("child1", childTest1);
//
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			String LoggedUserName = accountPage.signInWithYourEmail(Login).toLowerCase();
//			SanCS.assertEquals(LoggedUserName, Login.getFirstName().toLowerCase(), "L1");
//			// Sign in with username
//
//			ExtentTest childTest13 = testListener.startChild("Note last order ID test");
//			childTest13.setDescription("This test notes the last order ID from My Orders. ");
//			TestListner.extentMap.get().put("child13", childTest13);
//			lastOrder=accountPage.getLastOrder();
//
//			ExtentTest childTest2 = testListener.startChild("Add simple and shade product to cart test");
//			childTest2.setDescription(
//					"This test verifies that user is able to add simple product and a product with shade variant to cart");
//			TestListner.extentMap.get().put("child2", childTest2);
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.clearCart();
//			browser.get(environmentURL);
//			headerBar.navigateToCategory(headerbar1);			
//			// Add to bag:
//			/**********************
//			 * test 500+
//			 *************************************************/
//			ListingPage_React listingPage = new ListingPage_React(browser);
//			ProductDetailData ProductDetailData=null;
//			ProductDetailData ShadeproductDetadata = null;
//			ProductDetailData SizeproductDetadata =null;
//			ShadeproductDetadata = listingPage.addAnyShadeProductFromAllProduct();
//			if(ShadeproductDetadata.getProductName()!=null)
//			{
//				isShadeProductAdded=true;
//				Integer.parseInt(ShadeproductDetadata.getProductPrize());
//			}
//			SanCS.assertTrue(isShadeProductAdded, "53A");
//
//			ExtentTest childTest3 = testListener.startChild("Add product with size variant to cart test");
//			childTest3
//			.setDescription("This test verifies that user is able to add a product with size variant to cart");
//			TestListner.extentMap.get().put("child3", childTest3);
//
//			headerBar.navigateToCategory(headerbar2);
//			
//			SizeproductDetadata = listingPage.addAnySizeProductFromAllProduct();
//			if(SizeproductDetadata.getProductName()!=null)
//			{
//				isSizeProductAdded=true;
//				Integer.parseInt(SizeproductDetadata.getProductPrize());
//			}
//			SanCS.assertTrue(isSizeProductAdded, "53B");
//			
//			ProductDetailData = listingPage.addAnySimpleProductFromAllProducts();
//			if(ProductDetailData.getProductName()!=null)
//			{
//				Integer.parseInt(ProductDetailData.getProductPrize());
//			}
//			/***********************************************************************************************/
//
//
//			ExtentTest childTest4 = testListener.startChild("Select Delivery Address test");
//			childTest4.setDescription("This test verifies that user is able to select/enter delivery address");
//			TestListner.extentMap.get().put("child4", childTest4);
//			CartPage_React cartPage=new CartPage_React(browser);
//			CheckOutPage checkOutPage = new CheckOutPage(browser);
//			cartPage.checkOut();
//			String checkouturl = "";
//			while (!browser.getCurrentUrl().contains("checkout")) {
//				Thread.sleep(1000);
//			}
//
//			checkouturl = browser.getCurrentUrl();
//
//			if (browser.getCurrentUrl().contains(environmentURL.substring(environmentURL.lastIndexOf("/") + 1))) {
//				checkOutPage.savedAddressSelect();
//
//				//SanCS.assertEquals(checkOutPage.isAddressSelected(), "true", "170");
//				// If signed in then it should land on shipping address page.
//
//			} if(checkOutPage.isAddressSelected()==null) {
//
//				checkOutPage.fillNewAddress(checKoutData);
//			}
//			SanCS.assertEquals(checkOutPage.isAddressSelected(), "true", "170");
//
//			ExtentTest childTest5 = testListener.startChild("Checkout with Debit/Credit card test");
//			childTest5.setDescription(
//					"This test verifies that user is able to checkout with Credit/Debit card as payment method");
//			TestListner.extentMap.get().put("child5", childTest5);
//
//			checkOutPage.checkoutWithDebitCard(checKoutData);
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
//			ExtentTest childTest6 = testListener.startChild("Checkout with Net Banking test");
//			childTest6.setDescription(
//					"This test verifies that user is able to checkout with Net Banking as payment method");
//			TestListner.extentMap.get().put("child6", childTest6);
//			browser.get(checkouturl);
//			System.out.println(checkouturl);
//			boolean isNetBanking = checkOutPage.netBankingCheckout();
//			SanCS.assertTrue(isNetBanking, "L235");
//
//			/*
//			 * browser.get(checkouturl); boolean isPayuCheckout =
//			 * checkOutPage.payumoneyCheckout();
//			 *
//			 * SanCS.assertTrue(isPayuCheckout, "243");
//			 */
//			ExtentTest childTest7 = testListener.startChild("Checkout with Wallet test");
//			childTest7.setDescription("This test verifies that user is able to checkout with Wallet as payment method");
//			TestListner.extentMap.get().put("child7", childTest7);
//			browser.get(checkouturl);
//			boolean isMobikwik = checkOutPage.MobikwikWalletCheckout();
//
//			SanCS.assertTrue(isMobikwik, "247");
//
//			ExtentTest childTest8 = testListener.startChild("Place COD order test");
//			childTest8.setDescription("This test verifies that user is able to place COD order");
//			TestListner.extentMap.get().put("child8", childTest8);
//
//			browser.get(checkouturl);
//			boolean isCashonDelivery = checkOutPage.CashOnDelivery();
//
//			SanCS.assertTrue(isCashonDelivery, "L241");
//
//			/*ExtentTest childTest9 = testListener.startChild("Convert Quote to Order by running Cron");
//			childTest9.setDescription("This test verifies that user is able to convert Quote to Order");
//			TestListner.extentMap.get().put("child9", childTest9);
//
//			String orderID=accountPage.convertCODQuoteToOrder();
//			if(orderID!=null)
//			{
//				orderID=orderID.substring(orderID.indexOf("OrderId:")).split("~")[0].replaceAll("[^0-9]", "");
//				TestListner.testing.get().log(LogStatus.INFO,"Order ID: "+orderID);
//				SanCS.assertTrue(orderID!=null, "Convert Quote to Order ");
//			}
//
//			ExtentTest childTest10 = testListener.startChild("My Orders Page Test");
//			childTest10.setDescription("This test verifies that the order just placed is present in My Orders");
//			TestListner.extentMap.get().put("child10", childTest10);
//			boolean isOrderPresent=accountPage.orderPresentInMyOrders(orderID,lastOrder);
//			SanCS.assertTrue(isOrderPresent, "Order present in My Orders");
//
//			if(isOrderPresent)
//			{
//
//				ExtentTest childTest11 = testListener.startChild("Order Details Verification in My Orders Test");
//				childTest11.setDescription("This test verifies that the Details displayed in My Orders are correct.");
//				TestListner.extentMap.get().put("child11", childTest11);
//
//				ExtentTest childTest12 = testListener.startChild("Cancel Order Test");
//				childTest12.setDescription("This test verifies that user is able to cancel the order just placed");
//				TestListner.extentMap.get().put("child12", childTest12);
//				SanCS.assertTrue(accountPage.cancelLatestOrder(),"Cancel Order Test");
//			}*/
//
//
//
//			//SanCS.assertAll();
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser, e);
//			Reporter.log(e.toString());
//			Assert.fail();
//		} finally {
//
//			LoginSync.getInstance().clearLoginLock(Login);
//			browser.quit();
//
//		}
//
//	}
//
//	@Test(groups = { "Checkout", "checkout02", "CheckoutGuest", "Analytics","live" })
//	public void Check_end_to_end_checkout_flow_with_Guest_User() throws URISyntaxException, Throwable {
//
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
//		AccountData Login = LoginSync.getInstance().getLogin();
//		Header_FooterBarData headerbar1 = framework.getData(Header_FooterBarData.class, "shadenavigation");
//		Header_FooterBarData headerbar2 = framework.getData(Header_FooterBarData.class, "sizenavigation");
//		CheckoutData checKoutData = framework.getData(CheckoutData.class, "valid");
//		AnalyticsData analyticsCODData = framework.getData(AnalyticsData.class, "checkoutcod");
//		try {
//			ExtentTest childTest1 = testListener
//					.startChild("Add products to cart and checkout as Guest with Credit/Debit Card");
//			childTest1.setDescription(
//					"This test verifies that guest user is able to add multiple products to cart and checkout with Debit/Credit card");
//			TestListner.extentMap.get().put("child1", childTest1);
//
//			browser.get(environmentURL);
//
//			// Sign in with username
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.clearCart();
//			headerBar.navigateToCategory(headerbar1);
//			ListingPage listingPage = new ListingPage(browser);
//			ProductDetailData ShadeproductDetadata = listingPage.addAnyShadeProductFromAllProduct();
//			CartPage cartPage = new CartPage(browser);
//			cartPage.isProductPresentInBag(ShadeproductDetadata);
//			headerBar.navigateToCategory(headerbar2);
//			ProductDetailData SizeproductDetadata = listingPage.addAnySizeProductFromAllProduct();
//			cartPage.isProductPresentInBag(SizeproductDetadata);
//			// Add to bag:
//			cartPage.openSlidingCart();
//			int subtotal = Integer.parseInt(cartPage.getShoppingTableData("Subtotal").split(" ")[1]);
//			while (subtotal < 500) {
//				cartPage.increaseFirstProduct();
//				cartPage.openSlidingCart();
//				subtotal = Integer.parseInt(cartPage.getShoppingTableData("Subtotal").split(" ")[1]);
//			}
//			CheckOutPage checkOutPage = new CheckOutPage(browser);
//			cartPage.checkOut();
//			AnalyticsInfoPage analyticinfo = new AnalyticsInfoPage(browser);
//			while (!browser.getCurrentUrl().contains("checkout")) {
//				Thread.sleep(1000);
//			}
//			String checkouturl = browser.getCurrentUrl();
//			checkOutPage.fillbillingEmailIDAndContinueAsGuest(checKoutData);
//			checkOutPage.fillNewAddress(checKoutData);
//			CheckoutData CheckoutDataOfUI = checkOutPage.FetchOrderDetail(checKoutData);
//			List<ProductDetailData> Items = checkOutPage.FetchProductDetail();
//			Collections.reverse(Items);
//			for (ProductDetailData item : Items) {
//				System.out.println(item.getProductName());
//				System.out.println(item.getProductPrize());
//			}
//			checkOutPage.checkoutWithDebitCard(checKoutData);
//			BankingPage bankPage = new BankingPage(browser);
//			SanCS.assertTrue(bankPage.isOTPofHDFCpresent(), "L233");
//
//			browser.get(checkouturl);
//
//			ExtentTest childTest2 = testListener.startChild("Guest User Checkout with Net Banking test");
//			childTest2.setDescription(
//					"This test verifies that Guest user is able to checkout with Net Banking as payment method");
//			TestListner.extentMap.get().put("child2", childTest2);
//
//			// BankingPage bankingPage = new BankingPage(browser);
//			browser.get(checkouturl);
//			System.out.println(checkouturl);
//			boolean isNetBanking = checkOutPage.netBankingCheckout();
//
//			SanCS.assertTrue(isNetBanking, "Net Banking Checkout");
//
//			ExtentTest childTest3 = testListener.startChild("Guest User Checkout with Wallet test");
//			childTest3.setDescription(
//					"This test verifies that Guest user is able to checkout with Wallet as payment method");
//			TestListner.extentMap.get().put("child3", childTest3);
//			browser.get(checkouturl);
//			boolean isMobikwik = checkOutPage.MobikwikWalletCheckout();
//
//			SanCS.assertTrue(isMobikwik, "Wallet Checkout");
//
//			ExtentTest childTest4 = testListener.startChild("Place COD order as Guest user test");
//			childTest4.setDescription("This test verifies that Guest user is able to place COD order");
//			TestListner.extentMap.get().put("child4", childTest4);
//			browser.get(checkouturl);
//			boolean cashonDelivery = checkOutPage.CashOnDelivery();
//			SanCS.assertTrue(cashonDelivery, "241");
//
//			ExtentTest childTest5 = testListener.startChild("Payment details verification for Guest user test");
//			childTest4.setDescription(
//					"This test verifies that Guest user payment details are displayed correctly on confirmation Page");
//			TestListner.extentMap.get().put("child5", childTest5);
//
//			ConfirmationPage confirmationPage = new ConfirmationPage(browser);
//			confirmationPage.getPaymentData();
//			boolean isPaymentDetail = confirmationPage.verifyPaymentDetailWithCheckoutData(CheckoutDataOfUI);
//			SanCS.assertTrue(isPaymentDetail, "257");
//
//			ExtentTest childTest6 = testListener.startChild("Delivery details verification for Guest user test");
//			childTest4.setDescription(
//					"This test verifies that Guest user delivery details are displayed correctly on confirmation Page");
//			TestListner.extentMap.get().put("child6", childTest6);
//
//			boolean isDeliveryDetail = confirmationPage.verifyDeliveryDetailWithCheckoutData(CheckoutDataOfUI);
//			SanCS.assertTrue(isDeliveryDetail, "256");
//			System.out.println(Items);
//			System.out.println(confirmationPage.verifyOrderItemDetail());
//
//			ExtentTest childTest7 = testListener.startChild("Item details verification for Guest user test");
//			childTest4.setDescription(
//					"This test verifies that Guest user Item details are displayed correctly on confirmation Page");
//			TestListner.extentMap.get().put("child7", childTest7);
//
//			SanCS.assertTrue(Items.toString().equals(confirmationPage.verifyOrderItemDetail().toString()), "259");
//			// SanCS.assertTrue(Items.equals(Items), "259");
//			analyticinfo.getEvar(analyticsCODData);
//			analyticinfo.getEvent(analyticsCODData);
//			//SanCS.assertAll();
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser, e);
//			Reporter.log(e.toString());
//			Assert.fail();
//		} finally {
//
//			LoginSync.getInstance().clearLoginLock(Login);
//			browser.quit();
//
//		}
//
//	}
//
//	@Test(groups = { "Smoke", "checkout03", "rewardpoint", "Checkout","live" })
//	public void Check_end_to_end_checkout_flow_order_less_than_500() throws URISyntaxException, Throwable {
//		// this.environmentURL = prop.getProperty("environmentName");
//
//		Framework framework = new Framework();
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//
//		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
//
//		AccountData accountData = framework.getData(AccountData.class, "newlogin");
//		Header_FooterBarData headerbar1 = framework.getData(Header_FooterBarData.class, "l2category");
//		CheckoutData checKoutData = framework.getData(CheckoutData.class, "valid");
//		// ProductDetailData productDetailData =
//		// framework.getData(ProductDetailData.class, "SimpleProduct");
//		try {
//			browser.get(environmentURL);
//
//			ExtentTest childTest1 = testListener.startChild("New User Register Test");
//			childTest1.setDescription("This test verifies that new user is able to register");
//			TestListner.extentMap.get().put("child1", childTest1);
//
//			AccountPage accountPage = new AccountPage(browser);
//
//			String loginUser = accountPage.NewRegister(accountData);
//			SanCS.assertTrue(loginUser.toLowerCase().equals(accountData.getFirstName()), "New user registration test");
//			// Sign in with username
//			ExtentTest childTest2 = testListener.startChild("Add Product to the Cart test");
//			childTest2.setDescription(
//					"This test verifies that new user is able to add product of price less than 500 into cart");
//			TestListner.extentMap.get().put("child2", childTest2);
//
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.clearCart();
//			headerBar.navigateToCategory(headerbar1);
//			ListingPage listingPage = new ListingPage(browser);
//			List<ProductDetailData> productList = listingPage.getProductList(listingPage.allProducts());
//			List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
//			productlistwithPrizeFilter = productList
//					.stream().filter(o -> Integer.parseInt(o.getProductPrize()) < 500
//							& !o.getProductType().contains("Size") & !o.getProductType().contains("Shade"))
//					.collect(Collectors.toList());
//			boolean isAdded = false;
//			for (ProductDetailData product : productlistwithPrizeFilter) {
//				isAdded = listingPage.addToBagSimpleProduct(product);
//				if (isAdded == true) {
//					break;
//				}
//			}
//			SanCS.assertTrue(isAdded, "Adding product in cart test");
//			// Add to bag:
//			ExtentTest childTest3 = testListener.startChild("Fill New Address Test");
//			childTest3.setDescription("This test verifies that new user is able to fill new address");
//			TestListner.extentMap.get().put("child3", childTest3);
//
//			CartPage cartPage = new CartPage(browser);
//			CheckOutPage checkOutPage = new CheckOutPage(browser);
//			cartPage.checkOut();
//			boolean addressAdded = checkOutPage.fillNewAddress(checKoutData);
//			SanCS.assertTrue(addressAdded, "Adding of new address test");
//
//			ExtentTest childTest4 = testListener.startChild("Apply Reward Points Test");
//			childTest4.setDescription("This test verifies that new user is able to apply reward points");
//			TestListner.extentMap.get().put("child4", childTest4);
//
//			boolean isRewardApplied = checkOutPage.isRewardPointApplied();
//			SanCS.assertTrue(isRewardApplied, "222");
//			//SanCS.assertAll();
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//
//			Reporter.log(e.toString());
//			Assert.fail();
//		} finally {
//			browser.quit();
//
//		}
//
//	}
//
//	@Test(groups = { "couponcode","live"})
//	public void Verify_user_is_Able_To_Apply_and_Remove_CouponCode() throws URISyntaxException, Throwable {
//		Framework framework = new Framework();
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
//		AccountData Login = LoginSync.getInstance().getLogin();
//
//		Header_FooterBarData headerbar2 = framework.getData(Header_FooterBarData.class, "sizenavigation");
//		CheckoutData checkoutData = framework.getData(CheckoutData.class, "valid");
//		framework.getData(AnalyticsData.class, "tc_applycoupon");
//		framework.getData(AnalyticsData.class, "tc_removecoupon");
//		AnalyticsData analyticsLogindata = framework.getData(AnalyticsData.class, "tc_login");
//
//		try {
//			ExtentTest childTest1 = testListener.startChild("New User Register test");
//			childTest1.setDescription("This test verifies that new user is able to register");
//			TestListner.extentMap.get().put("child1", childTest1);
//
//			AnalyticsInfoPage analyticInfoPge = new AnalyticsInfoPage(browser);
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			String loggedUsername = accountPage.signInWithYourEmail(Login);
//			SanCS.assertTrue(loggedUsername.toLowerCase().equals(Login.getFirstName()), "New user registration test");
//			analyticInfoPge.getEvar(analyticsLogindata);
//			analyticInfoPge.getEvent(analyticsLogindata);
//
//			// Sign in with username
//			ExtentTest childTest2 = testListener.startChild("Add Size Product into Cart Test");
//			childTest2.setDescription("This test verifies that user is able to add any size product into cart");
//			TestListner.extentMap.get().put("child2", childTest2);
//
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.clearCart();
//			headerBar.navigateToCategory(headerbar2);
//			ListingPage listingPage = new ListingPage(browser);
//			ProductDetailData SizeproductDetadata = listingPage.addAnySizeProductFromAllProduct();
//			CartPage cartPage = new CartPage(browser);
//			boolean isAdded = cartPage.isProductPresentInBag(SizeproductDetadata);
//			SanCS.assertTrue(isAdded, "Add any size product into cart test");
//			// Add to bag:
//
//			ExtentTest childTest3 = testListener.startChild("Apply/Remove Coupon Test");
//			childTest3.setDescription("This test verifies that user is able to apply/remove coupon code");
//			TestListner.extentMap.get().put("child3", childTest3);
//
//			// CheckOutPage checkOutPage = new CheckOutPage(browser);
//			SanCS.assertTrue(cartPage.ApplyCoupon(checkoutData).contains("-"), "150");
//			//analyticInfoPge.getEvarJSLoad(ApplyCouponCodeANData);
//			cartPage.CancelCoupon();
//			//analyticInfoPge.getEvarJSLoad(RemoveCouponCodeANData);
//			cartPage.checkOut();
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser, e);
//			Reporter.log(e.toString());
//			Assert.fail();
//		} finally {
//			LoginSync.getInstance().clearLoginLock(Login);
//			browser.quit();
//
//		}
//
//	}
//
//	@Test(groups = {  "coupon", "simple", "checkout02" })
//	public void Check_end_to_end_checkout_flow_with_new_Registered_User_using_SimplePay() throws Throwable {
//		Header_FooterBarData headerbar1 = framework.getData(Header_FooterBarData.class, "shadenavigation");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert sanityCS = new CustomSoftAssert(SanAssertData, browser);
//		AccountData accountData = framework.getData(AccountData.class, "newlogin");
//		CheckoutData checkOutData = framework.getData(CheckoutData.class, "simplepay");
//		try {
//			ExtentTest childTest1 = testListener.startChild("Add Any Shade product into Cart Test");
//			childTest1.setDescription("This test verifies that guest user is able to add any shade product into cart");
//			TestListner.extentMap.get().put("child1", childTest1);
//
//			browser.get(environmentURL);
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.clearCart();
//			headerBar.navigateToCategory(headerbar1);
//			ListingPage listingPage = new ListingPage(browser);
//			ProductDetailData ShadeproductDetadata = listingPage.addAnyShadeProductFromAllProduct();
//			CartPage cartPage = new CartPage(browser);
//			sanityCS.assertTrue(cartPage.isProductPresentInBag(ShadeproductDetadata),
//					"Add shade product into cart test");
//
//			ExtentTest childTest2 = testListener.startChild("Apply Coupon Code Test");
//			childTest2.setDescription("This test verifies that guest user is able to apply coupon code");
//			TestListner.extentMap.get().put("child2", childTest2);
//			boolean isCouponApplied = cartPage.ApplyCoupon(checkOutData).contains("-");
//			sanityCS.assertTrue(isCouponApplied, "150");
//
//			ExtentTest childTest3 = testListener.startChild("Shipping Charge Test");
//			childTest3.setDescription("This test verifies the shipping charges");
//			TestListner.extentMap.get().put("child3", childTest3);
//			String shippingText = cartPage.getShoppingTableData("Shipping");
//			TestListner.testing.get().log(LogStatus.INFO,"Shipping Charge  '" + shippingText + "'");
//			if(Integer.parseInt(ShadeproductDetadata.getProductPrize())<500)
//			{
//				sanityCS.assertEquals(shippingText, "Rs. 50", "153");
//			}
//			else
//			{
//				sanityCS.assertEquals(shippingText, "Free", "153");
//			}
//
//			cartPage.checkOut();
//			CheckOutPage checkOutPage = new CheckOutPage(browser);
//
//			ExtentTest childTest4 = testListener.startChild("New User Register Test");
//			childTest4.setDescription("This test verifies that new user is able to register");
//			TestListner.extentMap.get().put("child4", childTest4);
//			sanityCS.assertTrue(checkOutPage.newRegister(accountData), "270");
//
//			ExtentTest childTest5 = testListener.startChild("Fill New Address Test");
//			childTest5.setDescription("This test verifies that new user is able to fill new address");
//			TestListner.extentMap.get().put("child5", childTest5);
//			boolean addressAdded = checkOutPage.fillNewAddress(checkOutData);
//			sanityCS.assertTrue(addressAdded, "Adding of new address test");
//
//			ExtentTest childTest6 = testListener.startChild("Checkout With SimplePay Test");
//			childTest6.setDescription("This test verifies that new registerd user is able to checkout with SimplePay");
//			TestListner.extentMap.get().put("child6", childTest6);
//			String OTPConfirmTelephone = checkOutPage.checkoutWithSimple();
//			sanityCS.assertTrue(OTPConfirmTelephone.contains(checkOutData.getTelephone()), "L238");
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser, e);
//			Reporter.log(e.toString());
//			Assert.fail();
//		} finally {
//			browser.quit();
//		}
//	}
//
//	/*@Test(groups = { "Voidpayonvisit", "checkout01" })
//	public void Check_end_to_end_checkout_flow_with_all_type_of_products_and_services() throws Throwable {
//
//		//Header_FooterBarData Servicenavigation = framework.getData(Header_FooterBarData.class, "servicequicklink");
//		Header_FooterBarData brandNavigation = framework.getData(Header_FooterBarData.class, "brand");
//		//Header_FooterBarData headerBarData = framework.getData(Header_FooterBarData.class,"beautyservices_navigation");
//		//	BeautyServiceData beautyServiceData = framework.getData(BeautyServiceData.class, "cityNavigation");
//
//
//		// ProductDetailData staticproductDetailData =
//		// framework.getData(ProductDetailData.class, "staticcombo");
//
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
//		browser.get(environmentURL);
//		AccountData Login = LoginSync.getInstance().getLogin();
//		CheckoutData checkOutData = framework.getData(CheckoutData.class, "simplepay");
//		//ProductDetailData productDetailData = framework.getData(ProductDetailData.class, "service");
//
//		try {
//			ExtentTest childTest1 = testListener.startChild("Existing User Login test");
//			childTest1.setDescription("This test verifies that existing user is able to login");
//			TestListner.extentMap.get().put("child1", childTest1);
//			AccountPage accountPage = new AccountPage(browser);
//			String loggedInUser = accountPage.signInWithYourEmail(Login);
//			SanCS.assertTrue(loggedInUser.toLowerCase().equals(Login.getFirstName()));
//
//			ExtentTest childTest2 = testListener.startChild("Add Static Product into Cart test");
//			childTest2.setDescription("This test verifies that existing user is able to add static product into cart");
//			TestListner.extentMap.get().put("child2", childTest2);
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.clearCart();
//			headerBar.navigateToBrand(brandNavigation);
//			ListingPage listingPage = new ListingPage(browser);
//			listingPage.navigateToShopAll("shop-all");
//			ProductDetailData staticproductDetailData = listingPage.addAnyStaticProductFromAllProduct();
//			//listingPage.addToBagSimpleProduct(staticproductDetailData);
//			CartPage cartPage = new CartPage(browser);
//			boolean isStaticProductadded = cartPage.isProductPresentInBag(staticproductDetailData);
//			SanCS.assertTrue(isStaticProductadded, "L65");
//
//			ExtentTest childTest3 = testListener.startChild("Add Service into Cart test");
//			childTest3.setDescription("This test verifies that existing user is able to add service into cart");
//			TestListner.extentMap.get().put("child3", childTest3);
//			//headerBar.navigateToQuickLink(Servicenavigation, environmentURL);
//			headerBar.navigateToPopUps(headerBarData);
//			BeautyServicePage beautyServicePage = new BeautyServicePage(browser);
//			beautyServicePage.navigateToQuickLink(beautyServiceData,environmentURL);
//			beautyServicePage.selectCityAndLocation(beautyServiceData);
//			BeautyServiceData beautyServiceDataFromList = beautyServicePage.BookAnyService();
//			listingPage.serviceAddToCart(productDetailData);
//			boolean isServiceAdded = beautyServicePage.isServicePresentInBag(beautyServiceDataFromList);
//			SanCS.assertTrue(isServiceAdded, "L66");
//			CheckOutPage checkOutPage = new CheckOutPage(browser);
//			ExtentTest childTest5 = testListener.startChild("NetBanking Checkout test");
//			childTest5.setDescription("This test verifies that existing user is able to checkout through netbanking");
//			TestListner.extentMap.get().put("child5", childTest5);
//			cartPage.checkOut();
//			while (!browser.getCurrentUrl().contains("checkout")) {
//				Thread.sleep(1000);
//			}
//			if (browser.getCurrentUrl().contains(environmentURL.substring(environmentURL.lastIndexOf("/") + 1)) ) {
//				checkOutPage.savedAddressSelect();
//				// System.out.println(checkOutPage.isAddressSelected())
//				// If signed in then it should land on shipping address page.
//			} if(checkOutPage.isAddressSelected()==null) {
//				checkOutPage.fillNewAddress(checkOutData);
//			}
//			boolean isNetbanking = checkOutPage.netBankingCheckout();
//			SanCS.assertTrue(isNetbanking, "L235");
//			//SanCS.assertAll();
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser, e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//		} finally {
//			LoginSync.getInstance().clearLoginLock(Login);
//			browser.quit();
//		}
//
//	}
//*/
//	
//	/*******************************************************
//	 * AddToCartTestcase
//	 *******************************************/
//
//	@Test( groups = { "ProductPageaddtobag", "Analytics", "cart01" })
//	//retryAnalyzer = Retry.class,
//	public void Dynamic_Combo_ProductPage_Add_To_Bag() throws Throwable, Throwable {
//		Framework framework = new Framework();
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert CS = new CustomSoftAssert(SanAssertData, browser);
//		AccountData accountData = framework.getData(AccountData.class, "newlogin");
//		Header_FooterBarData headerbar1 = framework.getData(Header_FooterBarData.class, "shadenavigation");
//		try {
//			ExtentTest childTest1 = testListener.startChild("New User Register Test");
//			childTest1.setDescription("This test verifies that new user is able to register");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			String loggedInUser = accountPage.NewRegister(accountData);
//			CS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()), "New user registration test");
//
//			ExtentTest childTest2 = testListener.startChild("Add Dynamic Combo Product into Cart Test");
//			childTest2.setDescription("This test verifies that new user is able to add dynamic combo into cart");
//			TestListner.extentMap.get().put("child2", childTest2);
//			HeaderBar headerbar = new HeaderBar(browser);
//			headerbar.navigateToCategory(headerbar1);
//			// ListingPage listingPage = new ListingPage(browser);
//			ProductPage productPage = new ProductPage(browser);
//			// ProductDetailData productDetailData = new ProductDetailData();
//			CartPage cartPage = new CartPage(browser);
//			browser.navigate().to(environmentURL
//					+ "/makeup/eyes/kajal/lakme-absolute-kohl-ultimate.html?root=catg_Bestsellers&ptype=product&brand=catg_bestsellers");
//			List<ProductDetailData> comboProductDetaillist = productPage.addDynamicCombo();
//
//			CS.assertTrue(cartPage.isComboProductsPresentInBag(comboProductDetaillist), "96A");
//
//			ExtentTest childTest3 = testListener.startChild("Customer Also Viewed section Test");
//			childTest3.setDescription("This test verifies that Customer Also Viewed section is displayed on page");
//			TestListner.extentMap.get().put("child3", childTest3);
//
//			CS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "96C");
//
//			ExtentTest childTest4 = testListener.startChild("Add to WishList Test");
//			childTest4.setDescription("This test verifies that Add to Wishlist is working on PDP");
//			TestListner.extentMap.get().put("child4", childTest4);
//
//			browser.navigate().refresh();
//			CS.assertTrue(productPage.addToWishlist(), "96B");
//
//			ExtentTest childTest5 = testListener.startChild("People Also Bought section Test");
//			childTest5.setDescription("This test verifies that People Also Bought section is displayed on page");
//			TestListner.extentMap.get().put("child5", childTest5);
//
//			CS.assertTrue(productPage.isPeopleWhoboughtDisplayed(), "96D");
//
//			CS.assertAll();
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser, e);
//			Reporter.log(e.toString());
//			Assert.fail();
//
//		} finally {
//			browser.close();
//			browser.quit();
//		}
//
//	}
//
//	/*********************************************************
//	 * AccountTestCases
//	 *******************************************************************************************************************************/
//	//@Test(groups = { "sanity","Wsanity", "Login", "account01", "glogin","live" })
//	/*public void Verify_User_Is_Able_To_Login_With_Google_plus() throws Throwable, Throwable {
//		Framework framwork = new Framework();
//		AccountData accountData = framwork.getData(AccountData.class, "facebook");
//		WebDriver browser = framwork.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert sanCS = new CustomSoftAssert(SanAssertData, browser);
//		try {
//			ExtentTest childTest1 = testListener.startChild("User Login with Google+ Test");
//			childTest1.setDescription("This test verifies that user is able to login with google plus");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			String FirstName = accountPage.signInWithGooglePlus(accountData);
//			sanCS.assertEquals(FirstName.toLowerCase(), accountData.getFirstName().toLowerCase(), "174");
//			//SanCS.assertAll();
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framwork.captureScreenshot(browser);
//			Reporter.log(e.toString());
//			Assert.fail();
//		} finally {
//			//LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//	}*/
//
//	/*@Test(groups = { "sanity","Wsanity", "Login", "newlogintest", "account01", "glogin" })
//	public void Verify_for_New_Login_User() throws Throwable, Throwable {
//		Framework framwork = new Framework();
//		AccountData accountData = framwork.getData(AccountData.class, "newlogin");
//		WebDriver browser = framwork.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert sanCS = new CustomSoftAssert(SanAssertData, browser);
//		try {
//			ExtentTest childTest1 = testListener.startChild("New User Register test");
//			childTest1.setDescription("This test verifies that new user is able to register");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			String FirstName = accountPage.NewRegister(accountData);
//			sanCS.assertEquals(FirstName.toLowerCase(), accountData.getFirstName().toLowerCase(), "270");
//			//SanCS.assertAll();
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framwork.captureScreenshot(browser);
//			Reporter.log(e.toString());
//			Assert.fail();
//		} finally {
//			//LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//	}*/
//
//	/*@Test(groups = { "sanity","Wsanity", "Logout", "account02", "account01", "Login" ,"live"})
//	public void Verify_logout_functionality() throws Throwable, Throwable {
//		AccountData accountData = LoginSync.getInstance().getLogin();
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		try {
//			ExtentTest childTest1 = testListener.startChild("Logout test");
//			childTest1.setDescription("This test verifies that user is able to logout");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			SanAssertCS.assertEquals(accountPage.signInWithYourEmail(accountData).toLowerCase(),
//					accountData.getFirstName().toLowerCase(), "L1");
//			boolean isLogout = accountPage.LogOut();
//			SanAssertCS.assertTrue(isLogout, "175L");
//		} catch (Exception e) {
//			e.printStackTrace();
//			framework.logErrorWithSnapshot(browser, e);
//			Reporter.log(e.toString());
//			Assert.fail();
//		} finally {
//			LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//	}*/
//
//	/*@Test(groups = { "sanity","Wsanity", "Login", "FB", "account03", "mayankb" })
//	public void Verify_User_Is_Able_To_Login_With_Fb() throws Throwable, Throwable {
//		AccountData accountData = framework.getData(AccountData.class, "facebook");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
//		try {
//			ExtentTest childTest1 = testListener.startChild("User Login with Facebook Test");
//			childTest1.setDescription("This test verifies that user is able to login with facebook");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			String Firtsname = accountPage.signInWithFacebook(accountData);
//			SanCS.assertEquals(Firtsname, accountData.getFirstName(), "173");
//			//SanCS.assertAll();
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser, e);
//			Reporter.log(e.toString());
//			Assert.fail();
//		} finally {
//			//LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//	}
//*/
//	/**********************************************************
//	 * ListingPageTestCase
//	 *****************************************************************************************************************************/
//
//	@Test(groups = { "listing", "listingl1","AddToBag","live" })
//	public void Check_for_L1_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		AccountData accountData = framework.getData(AccountData.class, "newlogin");
//		framework.getData(AccountData.class, "newlogin");
//
//		try {
//			ExtentTest childTest1 = testListener.startChild("Add any Shade/Size product from L1 category page widget test ");
//			childTest1.setDescription("This test verifies that user is able to add any Shade/Size product from L1 category page widget into cart");
//			TestListner.extentMap.get().put("child1", childTest1);
//
//			browser.get(environmentURL);
//			HeaderBar headerbar = new HeaderBar(browser);
//
//			AccountPage accountPage = new AccountPage(browser);
//			accountPage.NewRegister(accountData);
//			headerbar.clearCart();
//			headerbar.navigateToCategory(headerbarData);
//			ListingPage listingPage = new ListingPage(browser);
//			// listingPage.addAnyShadeProductFromWidget();
//			CartPage cartPage = new CartPage(browser);
//			ProductDetailData productDetailData1 = listingPage.addAnyShadeProductFromWidget();
//			if(productDetailData1.getProductName()==null)
//			{
//				productDetailData1 = listingPage.addAnySizeProductFromWidget();
//				if(productDetailData1.getProductName()==null)
//				{
//					productDetailData1 = listingPage.addAnySimpleProductFromWidget();
//				}
//			}
//			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(productDetailData1), "79C");
//
//			ExtentTest childTest2 = testListener.startChild("Add any Shade/Size product from L1 category page all products section test ");
//			childTest2.setDescription("This test verifies that user is able to add any Shade/Size product from L1 category page all products section into cart");
//			TestListner.extentMap.get().put("child2", childTest2);
//
//			ProductDetailData ProductDetailData = listingPage.addAnyShadeProductFromAllProduct();
//			if(ProductDetailData.getProductName()==null)
//			{
//				ProductDetailData = listingPage.addAnySizeProductFromAllProduct();
//				if(ProductDetailData.getProductName()==null)
//				{
//					ProductDetailData = listingPage.addAnySimpleProductFromAllProducts();
//				}
//			}
//			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData), "79D");
//
//			ExtentTest childTest3 = testListener.startChild("People also bought section test ");
//			childTest3.setDescription("This test verifies that People also bought sections is displayed after adding a product to cart");
//			TestListner.extentMap.get().put("child3", childTest3);
//
//			browser.navigate().refresh();
//			boolean CustomerAlsoViewedAvailable;
//			CustomerAlsoViewedAvailable = listingPage.isCustomerAlsoViewedPresent(listingPage.allProducts());
//			SanAssertCS.assertTrue(CustomerAlsoViewedAvailable, "79I");
//
//			ExtentTest childTest4 = testListener.startChild("Add any Shade/Size product from L1 category page Customers also bought section test ");
//			childTest4.setDescription("This test verifies that user is able to add any Shade/Size product from L1 category page Customers also bought section into cart");
//			TestListner.extentMap.get().put("child4", childTest4);
//
//			ProductDetailData ProductDetailData2 = listingPage.addAnyShadeProductFromPeopleWhoBoughtWidget();
//			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData2), "79J");
//			browser.navigate().refresh();
//
//			ExtentTest childTest5 = testListener.startChild("Add any product from L1 category page widget to wishlist test ");
//			childTest5.setDescription("This test verifies that user is able to add any product from L1 category page widget section to wishlist");
//			TestListner.extentMap.get().put("child5", childTest5);
//
//			boolean widgetWishlistAdded = listingPage.addToWishList(listingPage.widgetProducts());
//			SanAssertCS.assertTrue(widgetWishlistAdded, "79A");
//
//			ExtentTest childTest6 = testListener.startChild("Add any product from L1 category page all products to wishlist test ");
//			childTest6.setDescription("This test verifies that user is able to add any product from L1 category page all products section to wishlist");
//			TestListner.extentMap.get().put("child6", childTest6);
//
//			boolean allProductWishlistAdded = listingPage.addToWishList(listingPage.allProducts());
//			SanAssertCS.assertTrue(allProductWishlistAdded, "79B");
//
//			ExtentTest childTest7 = testListener.startChild("Verify Offer text test ");
//			childTest7.setDescription("This test verifies the offer on products in all products section");
//			TestListner.extentMap.get().put("child7", childTest7);
//
//			String offerText = listingPage.verifyOfferOn(listingPage.allProducts());
//			SanAssertCS.assertTrue(offerText != null, "79H");
//
//			ExtentTest childTest8 = testListener.startChild("Scrolling till 4-5 slots test ");
//			childTest8.setDescription("This test verifies that products are loaded successfully on L1 category page when user scrolls by 4 to 5 slots");
//			TestListner.extentMap.get().put("child8", childTest8);
//
//			listingPage.ScrollDown("1000", 4);
//			int value = listingPage.getProductCountOfAllProducts();
//			if (value > 40) {
//				SanAssertCS.assertTrue(true, "79E");
//			}
//
//			ExtentTest childTest9 = testListener.startChild("Verify Apply Sort test ");
//			childTest9.setDescription("This test verifies the user is able to apply sorting");
//			TestListner.extentMap.get().put("child9", childTest9);
//
//			List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
//			productList = listingPage.ApplySort("NAME");
//			List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
//			productListFromUI.addAll(productList);
//			System.out.println(productListFromUI.toString());
//			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
//					.compareToIgnoreCase(a2.getProductName()));
//			SanAssertCS.assertEquals(productListFromUI, productList, "79F");
//
//			ExtentTest childTest10 = testListener.startChild("Apply Filter test ");
//			childTest10.setDescription("This test verifies that user is able to apply filter");
//			TestListner.extentMap.get().put("child10", childTest10);
//
//			List<ProductDetailData> productListPriceFilter = listingPage.ApplyFilter("price", "Rs. 0 - Rs. 500");
//			List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
//			// productlistwithPrizeFilter.addAll(productListPriceFilter);
//			productlistwithPrizeFilter = productListPriceFilter.stream()
//					.filter(o -> Integer.parseInt(o.getProductPrize()) < 500).collect(Collectors.toList());
//			System.out.println(productListPriceFilter);
//			System.out.println(productlistwithPrizeFilter);
//			SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "79G");
//
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally {
//
//			//LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "listing", "listingl2","AddToBag","live" })
//	public void Check_for_L2_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l2sizenavigation");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		AccountData accountData = framework.getData(AccountData.class, "newlogin");
//
//		try {
//
//			ExtentTest childTest1 = testListener.startChild("Add any Shade/Size product from L2 category page widget test ");
//			childTest1.setDescription("This test verifies that user is able to add any Shade/Size product from L2 category page widget into cart");
//			TestListner.extentMap.get().put("child1", childTest1);
//
//			browser.get(environmentURL);
//			HeaderBar headerbar = new HeaderBar(browser);
//
//			AccountPage accountPage = new AccountPage(browser);
//			accountPage.NewRegister(accountData);
//			headerbar.navigateToCategory(headerbarData);
//			ListingPage listingPage = new ListingPage(browser);
//
//			// listingPage.addAnyShadeProductFromWidget();
//
//			CartPage cartPage = new CartPage(browser);
//			ProductDetailData productDetailData1 = listingPage.addAnySizeProductFromWidget();
//			if(productDetailData1.getProductName()==null)
//			{
//				productDetailData1 = listingPage.addAnyShadeProductFromWidget();
//				if(productDetailData1.getProductName()==null)
//				{
//					productDetailData1 = listingPage.addAnySimpleProductFromWidget();
//				}
//			}
//			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(productDetailData1), "80C");
//
//			ExtentTest childTest2 = testListener.startChild("Add any Shade/Size product from L2 category page all products section test ");
//			childTest2.setDescription("This test verifies that user is able to add any Shade/Size product from L2 category page all products section into cart");
//			TestListner.extentMap.get().put("child2", childTest2);
//
//			ProductDetailData ProductDetailData = listingPage.addAnySizeProductFromAllProduct();
//			if(ProductDetailData.getProductName()==null)
//			{
//				ProductDetailData = listingPage.addAnyShadeProductFromAllProduct();
//				if(ProductDetailData.getProductName()==null)
//				{
//					ProductDetailData = listingPage.addAnySimpleProductFromAllProducts();
//				}
//			}
//			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData), "80D");
//
//			ExtentTest childTest3 = testListener.startChild("Prople also bought section test ");
//			childTest3.setDescription("This test verifies that People also bought sections is displayed on L2 category page");
//			TestListner.extentMap.get().put("child3", childTest3);
//
//			browser.navigate().refresh();
//			boolean CustomerAlsoViewedAvailable;
//			CustomerAlsoViewedAvailable = listingPage.isCustomerAlsoViewedPresent(listingPage.allProducts());
//			SanAssertCS.assertTrue(CustomerAlsoViewedAvailable, "80I");
//
//			ExtentTest childTest5 = testListener.startChild("Add any product from L2 category page widget to wishlist test ");
//			childTest5.setDescription("This test verifies that user is able to add any product from L2 category page widget section to wishlist");
//			TestListner.extentMap.get().put("child5", childTest5);
//
//			browser.navigate().refresh();
//			boolean widgetWishlistAdded = listingPage.addToWishList(listingPage.widgetProducts());
//			SanAssertCS.assertTrue(widgetWishlistAdded, "80A");
//
//			ExtentTest childTest6 = testListener.startChild("Add any product from L2 category page all products to wishlist test ");
//			childTest6.setDescription("This test verifies that user is able to add any product from L2 category page all products section to wishlist");
//			TestListner.extentMap.get().put("child6", childTest6);
//
//			boolean allProductWishlistAdded = listingPage.addToWishList(listingPage.allProducts());
//			SanAssertCS.assertTrue(allProductWishlistAdded, "80B");
//
//			ExtentTest childTest7 = testListener.startChild("Verify Offer text on L2 page test ");
//			childTest7.setDescription("This test verifies the offer on products in all products section");
//			TestListner.extentMap.get().put("child7", childTest7);
//
//			String offerText = listingPage.verifyOfferOn(listingPage.allProducts());
//			SanAssertCS.assertTrue(offerText != null, "80H");
//
//			ExtentTest childTest8 = testListener.startChild("Scrolling till 4-5 slots test ");
//			childTest8.setDescription("This test verifies that products are loaded successfully on L2 category page when user scrolls by 4 to 5 slots");
//			TestListner.extentMap.get().put("child8", childTest8);
//
//			listingPage.ScrollDown("2000", 4);
//			int value = listingPage.getProductCountOfAllProducts();
//			if (value > 40) {
//				SanAssertCS.assertTrue(true, "80E");
//			} else {
//				SanAssertCS.assertTrue(false, "80E");
//			}
//
//			ExtentTest childTest9 = testListener.startChild("Verify Apply Sort test ");
//			childTest9.setDescription("This test verifies the user is able to apply sorting");
//			TestListner.extentMap.get().put("child9", childTest9);
//
//			List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
//			productList = listingPage.ApplySort("NAME");
//			List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
//			productListFromUI.addAll(productList);
//			System.out.println(productListFromUI.toString());
//			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
//					.compareTo(a2.getProductName()));
//			SanAssertCS.assertEquals(productListFromUI, productList, "80F");
//
//			ExtentTest childTest10 = testListener.startChild("Apply Filter test ");
//			childTest10.setDescription("This test verifies that user is able to apply filter");
//			TestListner.extentMap.get().put("child10", childTest10);
//
//			List<ProductDetailData> productListPriceFilter = listingPage.ApplyFilter("price", "Rs. 0 - Rs. 500");
//			List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
//			// productlistwithPrizeFilter.addAll(productListPriceFilter);
//			productlistwithPrizeFilter = productListPriceFilter.stream()
//					.filter(o -> Integer.parseInt(o.getProductPrize()) <= 500).collect(Collectors.toList());
//			System.out.println(productListPriceFilter);
//			System.out.println(productlistwithPrizeFilter);
//			SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "80G");
//
//
//		} catch (Throwable e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString());
//			Assert.fail();
//
//		} finally {
//
//			//LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//	}
//
//	@Test(groups = {"listingl3", "listing","AddToBag","live"})
//	public void Check_for_L3_Category_page() throws Throwable {
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l3category");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		AccountData accountData = framework.getData(AccountData.class, "newlogin");
//
//		try {
//			ExtentTest childTest1 = testListener.startChild("New User Register Test");
//			childTest1.setDescription("This test verifies that new user is able to register");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			HeaderBar headerbar = new HeaderBar(browser);
//			{
//				AccountPage accountPage = new AccountPage(browser);
//				String loggedInUser = accountPage.NewRegister(accountData);
//				SanAssertCS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()),
//						"New user registration test");
//
//				ExtentTest childTest2 = testListener.startChild("Add Product to Cart from L3 Page Test");
//				childTest2.setDescription(
//						"This test verifies that user is able to add any shaded product into cart from L3 category page widget");
//				TestListner.extentMap.get().put("child2", childTest2);
//				headerbar.clearCart();
//				headerbar.navigateToCategory(headerbarData);
//				ListingPage listingPage = new ListingPage(browser);
//				CartPage cartPage = new CartPage(browser);
//				ProductDetailData productDetailData1 = listingPage.addAnyShadeProductFromWidget();
//				SanAssertCS.assertTrue(cartPage.isProductPresentInBag(productDetailData1), "81C");
//
//				ExtentTest childTest3 = testListener.startChild("Add Product to Cart from L3 Page All Product Widget Test");
//				childTest3.setDescription("This test verifies that user is able to add any shaded product into cart from L3 category page all product widget");
//				TestListner.extentMap.get().put("child3", childTest3);
//				ProductDetailData ProductDetailData = listingPage.addAnyShadeProductFromAllProduct();
//				SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData), "81D");
//
//				ExtentTest childTest4 = testListener.startChild("Add Product to Cart from L3 Page PAB Widget Test");
//				childTest4.setDescription("This test verifies that user is able to add product into cart from L3 category page PAB widget");
//				TestListner.extentMap.get().put("child4", childTest4);
//				browser.navigate().refresh();
//				boolean CustomerAlsoViewedAvailable;
//				CustomerAlsoViewedAvailable = listingPage.isCustomerAlsoViewedPresent(listingPage.allProducts());
//				SanAssertCS.assertTrue(CustomerAlsoViewedAvailable, "81I");
//
//				ExtentTest childTest5 = testListener.startChild("Add Product to Wishlist from L3 Page Widget Test");
//				childTest5.setDescription("This test verifies that user is able to add product to wishlist from L3 category page widget");
//				TestListner.extentMap.get().put("child5", childTest5);
//				browser.navigate().refresh();
//				boolean widgetWishlistAdded = listingPage.addToWishList(listingPage.widgetProducts());
//				SanAssertCS.assertTrue(widgetWishlistAdded, "81A");
//
//				ExtentTest childTest6 = testListener.startChild("Add Product to Wishlist from L3 Page All Products Widget Test");
//				childTest6.setDescription("This test verifies that user is able to add product to wishlist from L3 category page all products widget");
//				TestListner.extentMap.get().put("child6", childTest6);
//				boolean allProductWishlistAdded = listingPage.addToWishList(listingPage.allProducts());
//				SanAssertCS.assertTrue(allProductWishlistAdded, "81B");
//
//				ExtentTest childTest7 = testListener.startChild("On L3 Page Offer Text Verification Test");
//				childTest7.setDescription("This test verifies that user is able to view offer text on L3 category page");
//				TestListner.extentMap.get().put("child7", childTest7);
//				String offerText = listingPage.verifyOfferOn(listingPage.allProducts());
//				SanAssertCS.assertTrue(offerText != null, "81H");
//
//				ExtentTest childTest8 = testListener.startChild("On L3 Page Scrolling till 4-5 slot Test");
//				childTest8.setDescription("This test verifies that user is able to scroll 4-5 times on L3 category page");
//				TestListner.extentMap.get().put("child8", childTest8);
//				listingPage.ScrollDown("2000", 4);
//				int value = listingPage.getProductCountOfAllProducts();
//				if (value > 40) {
//					SanAssertCS.assertTrue(true, "81E");
//				} else {
//					SanAssertCS.assertTrue(false, "81E");
//				}
//
//				ExtentTest childTest9 = testListener.startChild("On L3 Page Apply Sort Test");
//				childTest9.setDescription("This test verifies that user is able to apply sort on L3 category page");
//				TestListner.extentMap.get().put("child9", childTest9);
//				List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
//				productList = listingPage.ApplySort("NAME");
//				List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
//				productListFromUI.addAll(productList);
//				Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
//						.compareTo(a2.getProductName()));
//				SanAssertCS.assertEquals(productListFromUI, productList, "81F");
//
//				ExtentTest childTest10 = testListener.startChild("On L3 Page Apply Filter Test");
//				childTest10.setDescription("This test verifies that user is able to apply filters on L3 category page");
//				TestListner.extentMap.get().put("child10", childTest10);
//				List<ProductDetailData> productListPriceFilter = listingPage.ApplyFilter("price", "Rs. 0 - Rs. 500");
//				List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
//				// productlistwithPrizeFilter.addAll(productListPriceFilter);
//				productlistwithPrizeFilter = productListPriceFilter.stream()
//						.filter(o -> Integer.parseInt(o.getProductPrize()) <= 500).collect(Collectors.toList());
//				SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "81G");
//			}
//		} catch (Throwable e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser, e);
//			Reporter.log(e.toString());
//			Assert.fail();
//		} finally {
//			//LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "listinglux", "listing","AddToBag","live"})
//	public void Check_for_Luxe_category_page() throws Throwable {
//
//		framework.getData(Header_FooterBarData.class, "luxebrand");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		AccountData accountData = framework.getData(AccountData.class, "newlogin");
//
//		try {
//			ExtentTest childTest1 = testListener.startChild("New User Register Test");
//			childTest1.setDescription("This test verifies that new user is able to register");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			HeaderBar headerbar = new HeaderBar(browser);
//			{
//				AccountPage accountPage = new AccountPage(browser);
//				String loggedInUser = accountPage.NewRegister(accountData);
//				SanAssertCS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()),
//						"New user registration test");
//
//				ExtentTest childTest2 = testListener.startChild("Add Shade Product to Cart Test");
//				childTest2.setDescription("This test verifies that new user is able to add any shaded product into cart form all products widget");
//				TestListner.extentMap.get().put("child2", childTest2);
//				headerbar.clearCart();
//				// headerbar.navigateToCategory(headerbarData);
//				ListingPage listingPage = new ListingPage(browser);
//				browser.get(environmentURL + "/luxe/makeup.html?root=nav_2&dir=desc&order=popularity");
//				// String offerText =
//				// listingPage.verifyOfferOn(listingPage.allProducts());
//				// SanAssertCS.assertTrue(offerText!= null, "83H");
//				/*
//				 * boolean widgetWishlistAdded =
//				 * listingPage.addToWishList(listingPage.widgetProducts());
//				 * SanAssertCS.assertTrue(widgetWishlistAdded,"83A"); boolean
//				 * allProductWishlistAdded =
//				 * listingPage.addToWishList(listingPage.allProducts());
//				 * SanAssertCS.assertTrue(allProductWishlistAdded,"83B");
//				 */
//				// listingPage.addAnyShadeProductFromWidget();
//				// listingPage.verifyOfferOn(listingPage.allProducts());
//				CartPage cartPage = new CartPage(browser);
//				/*
//				 * ProductDetailData productDetailData1 =
//				 * listingPage.addAnyShadeProductFromWidget();
//				 * SanAssertCS.assertTrue(cartPage.isProductPresentInBag(
//				 * productDetailData1),"81C");
//				 */
//				ProductDetailData ProductDetailData = listingPage.addAnyShadeProductFromAllProduct();
//				SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData), "83D");
//
//				ExtentTest childTest3 = testListener.startChild("Add to Wishlist Test");
//				childTest3.setDescription("This test verifies that new user is able to add product into wishlist");
//				TestListner.extentMap.get().put("child3", childTest3);
//				browser.navigate().refresh();
//				boolean allProductWishlistAdded = listingPage.addToWishList(listingPage.allProducts());
//				SanAssertCS.assertTrue(allProductWishlistAdded, "83B");
//
//				ExtentTest childTest4 = testListener.startChild("Scroll 4-5 slot Test");
//				childTest4.setDescription("This test verifies that new user is able to scroll the page 4-5 times");
//				TestListner.extentMap.get().put("child4", childTest4);
//				listingPage.ScrollDown("1000", 4);
//				int value = listingPage.getProductCountOfAllProducts();
//				if (value > 20) {
//					SanAssertCS.assertTrue(true, "83E");
//				} else {
//					SanAssertCS.assertTrue(false, "83E");
//				}
//
//				ExtentTest childTest5 = testListener.startChild("Apply Sort Test");
//				childTest5.setDescription("This test verifies that new user is able to apply sort");
//				TestListner.extentMap.get().put("child5", childTest5);
//				List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
//				productList = listingPage.ApplySort("NAME");
//				List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
//				productListFromUI.addAll(productList);
//				Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
//						.compareTo(a2.getProductName()));
//				SanAssertCS.assertEquals(productListFromUI, productList, "83F");
//
//				ExtentTest childTest6 = testListener.startChild("Apply Filter Test");
//				childTest6.setDescription("This test verifies that new user is able to apply filter");
//				TestListner.extentMap.get().put("child6", childTest6);
//				List<ProductDetailData> productListPriceFilter = listingPage.ApplyFilter("price",
//						"Rs. 1,000 - Rs. 2,000");
//				List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
//				// productlistwithPrizeFilter.addAll(productListPriceFilter);
//				productlistwithPrizeFilter = productListPriceFilter.stream()
//						.filter(o -> Integer.parseInt(o.getProductPrize()) >= 1000
//						& Integer.parseInt(o.getProductPrize()) <= 2000)
//						.collect(Collectors.toList());
//				SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "83G");
//			}
//		}
//
//		catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString());
//			Assert.fail();
//
//		} finally {
//
//			browser.quit();
//		}
//
//	}
//
//	private void printStackStraceinLog(Throwable ex) {
//
//		StringWriter errors = new StringWriter();
//		ex.printStackTrace(new PrintWriter(errors));
//		Reporter.log(errors.toString(),true);
//	}
//
//	@Test(groups = {"listingbrand", "listing" ,"AddToBag","live"})
//	public void Check_Brand_Page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "brand");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		AccountData accountData = framework.getData(AccountData.class, "newlogin");
//		try {
//			ExtentTest childTest1 = testListener.startChild("New User Register Test");
//			childTest1.setDescription("This test verifies that new user is able to register");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			HeaderBar headerbar = new HeaderBar(browser);
//			{
//				AccountPage accountPage = new AccountPage(browser);
//				String loggedInUser = accountPage.NewRegister(accountData);
//				SanAssertCS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()),
//						"New user registration test");
//
//				ExtentTest childTest2 = testListener.startChild("Add Simple Product to Cart Test");
//				childTest2.setDescription("This test verifies that new user is able to add simple product to cart");
//				TestListner.extentMap.get().put("child2", childTest2);
//				headerbar.clearCart();
//				headerbar.navigateToBrand(headerbarData);
//				ListingPage listingPage = new ListingPage(browser);
//				listingPage.navigateToShopAll("shop-all");
//				/*
//				 * boolean widgetWishlistAdded =
//				 * listingPage.addToWishList(listingPage.widgetProducts());
//				 * SanAssertCS.assertTrue(widgetWishlistAdded,"82A"); boolean
//				 * allProductWishlistAdded =
//				 * listingPage.addToWishList(listingPage.allProducts());
//				 * SanAssertCS.assertTrue(allProductWishlistAdded,"82B");
//				 */
//				// listingPage.addAnyShadeProductFromWidget();
//				CartPage cartPage = new CartPage(browser);
//				ProductDetailData productDetailData1 = listingPage.addAnySimpleProductFromWidget();
//				SanAssertCS.assertTrue(cartPage.isProductPresentInBag(productDetailData1), "82C");
//
//				ExtentTest childTest3 = testListener.startChild("Add to Wishlist Test");
//				childTest3.setDescription("This test verifies that new user is able to add product to wishlist");
//				TestListner.extentMap.get().put("child3", childTest3);
//				browser.navigate().refresh();
//				boolean widgetWishlistAdded = listingPage.addToWishList(listingPage.widgetProducts());
//				SanAssertCS.assertTrue(widgetWishlistAdded, "82A");
//
//				ExtentTest childTest4 = testListener.startChild("Add Shaded Product to Cart Test");
//				childTest4.setDescription("This test verifies that new user is able to add shade product to cart");
//				TestListner.extentMap.get().put("child4", childTest4);
//				listingPage.navigateToShopAll("shop-all");
//				ProductDetailData ProductDetailData = listingPage.addAnyShadeProductFromAllProduct();
//				SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData), "82D");
//
//				ExtentTest childTest5 = testListener.startChild("Verify Offer Test");
//				childTest5.setDescription("This test verifies that new user is able to view offer");
//				TestListner.extentMap.get().put("child5", childTest5);
//				String offerText = listingPage.verifyOfferOn(listingPage.allProducts());
//				SanAssertCS.assertTrue(offerText != null, "82H");
//
//				ExtentTest childTest6 = testListener.startChild("Customer Also Bought Test");
//				childTest6.setDescription("This test verifies that customer also bought widget is present or not");
//				TestListner.extentMap.get().put("child6", childTest6);
//				browser.navigate().refresh();
//				boolean CustomerAlsoViewedAvailable;
//				CustomerAlsoViewedAvailable = listingPage.isCustomerAlsoViewedPresent(listingPage.allProducts());
//				SanAssertCS.assertTrue(CustomerAlsoViewedAvailable, "82I");
//
//				ExtentTest childTest7 = testListener.startChild("Add to Wishlist from All Products Widget Test");
//				childTest7.setDescription(
//						"This test verifies that new user is able to add product to wishlist from all products widget");
//				TestListner.extentMap.get().put("child7", childTest7);
//				boolean allProductWishlistAdded = listingPage.addToWishList(listingPage.allProducts());
//				SanAssertCS.assertTrue(allProductWishlistAdded, "82B");
//
//				ExtentTest childTest8 = testListener.startChild("Scroll 4-5 slot Test");
//				childTest8.setDescription("This test verifies that new user is able to scroll the page 4-5 times");
//				TestListner.extentMap.get().put("child8", childTest8);
//				listingPage.ScrollDown("1000", 4);
//				int value = listingPage.getProductCountOfAllProducts();
//				if (value > 40) {
//					SanAssertCS.assertTrue(true, "82E");
//				} else {
//					SanAssertCS.assertTrue(false, "82E");
//				}
//
//				ExtentTest childTest9 = testListener.startChild("Apply Sort Test");
//				childTest9.setDescription("This test verifies that new user is able to apply sort");
//				TestListner.extentMap.get().put("child9", childTest9);
//				List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
//				productList = listingPage.ApplySort("NAME");
//				List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
//				productListFromUI.addAll(productList);
//				Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
//						.compareTo(a2.getProductName()));
//				SanAssertCS.assertEquals(productListFromUI, productList, "82F");
//
//				ExtentTest childTest10 = testListener.startChild("Apply Filter Test");
//				childTest10.setDescription("This test verifies that new user is able to apply filter");
//				TestListner.extentMap.get().put("child10", childTest10);
//				List<ProductDetailData> productListPriceFilter = listingPage.ApplyFilter("price", "Rs. 0 - Rs. 500");
//				List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
//				productlistwithPrizeFilter.addAll(productListPriceFilter);
//				productListPriceFilter.stream().filter(o -> Integer.parseInt(o.getProductPrize()) <= 500)
//				.collect(Collectors.toList());
//				SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "82G");
//			}
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally {
//
//			//LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "listingluxbrand", "listing","AddToBag","live" })
//	public void Check_Luxe_Brand_Page() throws Throwable {
//
//		framework.getData(Header_FooterBarData.class, "brand");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		AccountData accountData = framework.getData(AccountData.class, "newlogin");
//		try {
//			ExtentTest childTest1 = testListener.startChild("New User Register Test");
//			childTest1.setDescription("This test verifies that new user is able to register");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			// "/luxe/brands/mac.html";
//			HeaderBar headerbar = new HeaderBar(browser);
//			{
//				AccountPage accountPage = new AccountPage(browser);
//				String loggedInUser = accountPage.NewRegister(accountData);
//				SanAssertCS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()),
//						"New user registration test");
//
//				ExtentTest childTest2 = testListener.startChild("Add Shaded Product to Cart Test");
//				childTest2.setDescription("This test verifies that new user is able to add shade product to cart");
//				TestListner.extentMap.get().put("child2", childTest2);
//				headerbar.clearCart();
//				browser.get(environmentURL + "/luxe/brands/mac.html");
//				ListingPage listingPage = new ListingPage(browser);
//				CartPage cartPage = new CartPage(browser);
//				/*
//				 * ProductDetailData productDetailData1 =
//				 * listingPage.addAnySimpleProductFromWidget();
//				 * SanAssertCS.assertTrue(cartPage.isProductPresentInBag(
//				 * productDetailData1),"82C");
//				 */
//				ProductDetailData ProductDetailData = listingPage.addAnyShadeProductFromAllProduct();
//				SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData), "84D");
//
//				/*
//				 * boolean CustomerAlsoViewedAvailable;
//				 * CustomerAlsoViewedAvailable =
//				 * listingPage.isCustomerAlsoViewedPresent(listingPage.
//				 * allProducts());
//				 * SanAssertCS.assertTrue(CustomerAlsoViewedAvailable, "82I");
//				 * 
//				 * String offerText =
//				 * listingPage.verifyOfferOn(listingPage.allProducts());
//				 * SanAssertCS.assertTrue(offerText != null, "82H");
//				 */
//				/*
//				 * boolean widgetWishlistAdded =
//				 * listingPage.addToWishList(listingPage.widgetProducts());
//				 * SanAssertCS.assertTrue(widgetWishlistAdded,"84A");
//				 */
//				ExtentTest childTest3 = testListener.startChild("Add to Wishlist from All Products Widget Test");
//				childTest3.setDescription(
//						"This test verifies that new user is able to add product to wishlist from all products widget");
//				TestListner.extentMap.get().put("child3", childTest3);
//				browser.navigate().refresh();
//				boolean allProductWishlistAdded = listingPage.addToWishList(listingPage.allProducts());
//				SanAssertCS.assertTrue(allProductWishlistAdded, "84B");
//
//				ExtentTest childTest4 = testListener.startChild("Scroll 4-5 slot Test");
//				childTest4.setDescription("This test verifies that new user is able to scroll the page 4-5 times");
//				TestListner.extentMap.get().put("child4", childTest4);
//				listingPage.ScrollDown("1000", 4);
//				int value = listingPage.getProductCountOfAllProducts();
//				if (value > 20) {
//					SanAssertCS.assertTrue(true, "84E");
//				} else {
//					SanAssertCS.assertTrue(false, "84E");
//				}
//
//				ExtentTest childTest5 = testListener.startChild("Apply Sort Test");
//				childTest5.setDescription("This test verifies that new user is able to apply sort");
//				TestListner.extentMap.get().put("child5", childTest5);
//				List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
//				productList = listingPage.ApplySort("NAME");
//				List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
//				productListFromUI.addAll(productList);
//				Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
//						.compareTo(a2.getProductName()));
//				SanAssertCS.assertEquals(productListFromUI, productList, "84F");
//
//				ExtentTest childTest6 = testListener.startChild("Apply Filter Test");
//				childTest6.setDescription("This test verifies that new user is able to apply filter");
//				TestListner.extentMap.get().put("child6", childTest6);
//				List<ProductDetailData> productListPriceFilter = listingPage.ApplyFilter("price", "Rs. 0 - Rs. 500");
//				List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
//				// productlistwithPrizeFilter.addAll(productListPriceFilter);
//				productlistwithPrizeFilter = productListPriceFilter.stream()
//						.filter(o -> Integer.parseInt(o.getProductPrize()) <= 500).collect(Collectors.toList());
//				SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "84G");
//			}
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally {
//
//			//LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//	}
//
//	/******************************************************** ProductPageTestCases **************************************************************************************************************************/
//
//	@Test(groups = { "productaddtobag", "productaddtobagsimple1","AddToBag"})
//	public void Simple_Product_Page_for_L1_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		AccountData accountData = framework.getData(AccountData.class, "newlogin");
//
//		try {ExtentTest childTest1 = testListener.startChild("New User Register Test");
//		childTest1.setDescription("This test verifies that new user is able to register");
//		TestListner.extentMap.get().put("child1", childTest1);
//		browser.get(environmentURL);
//		HeaderBar headerbar = new HeaderBar(browser);
//		{
//			AccountPage accountPage = new AccountPage(browser);
//			String loggedInUser = accountPage.NewRegister(accountData);
//			SanAssertCS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()),
//					"New user registration test");
//
//			ExtentTest childTest2 = testListener.startChild("Add Simple Product from Product Page Test");
//			childTest2.setDescription("This test verifies that new user is able to add simple procuct from PDP");
//			TestListner.extentMap.get().put("child2", childTest2);
//			headerbar.clearCart();
//			headerbar.navigateToCategory(headerbarData);
//			ProductPage productPage = new ProductPage(browser);
//			CartPage cartPage = new CartPage(browser);
//			ProductDetailData ProductDetailData1 = productPage.addAnySimpleProductFromProductPAge();
//			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData1), "52A");
//
//			ExtentTest childTest3 = testListener.startChild("Customer Also Viewed Widget Test");
//			childTest3.setDescription("This test verifies that user is able to view PAV widget");
//			TestListner.extentMap.get().put("child3", childTest3);
//			SanAssertCS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "52C");
//
//			ExtentTest childTest4 = testListener.startChild("Customer Also Bought Widget Test");
//			childTest4.setDescription("This test verifies that user is able to view PAB widget");
//			TestListner.extentMap.get().put("child4", childTest4);
//			SanAssertCS.assertTrue(productPage.isPeopleWhoboughtDisplayed(), "52D");
//
//			ExtentTest childTest5 = testListener.startChild("Add to Wishlist Test");
//			childTest5.setDescription("This test verifies that user is able to add product to wishlist");
//			TestListner.extentMap.get().put("child5", childTest5);
//			browser.navigate().refresh();
//			SanAssertCS.assertTrue(productPage.addToWishlist(), "52B");
//
//			ExtentTest childTest6 = testListener.startChild("Add Product to Cart from Wishlist Test");
//			childTest6.setDescription("This test verifies that user is able to add product to cart from wishlist");
//			TestListner.extentMap.get().put("child6", childTest6);
//			headerbar.clearCart();
//			accountPage.navigateToWishlist();
//			ListingPage listingPage = new ListingPage(browser);
//			ProductDetailData wishlistProductDetaildata = listingPage.addAnySimpleProductFromWishlist();
//			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(wishlistProductDetaildata), "52E");
//		}
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			e.printStackTrace();
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally {
//
//			//LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "productaddtobag", "productaddtobagshade","AddToBag"})
//	public void Verify_Add_to_Bag_Shade_Product_From_Product_Page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1tryiton");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		AccountData accountData = framework.getData(AccountData.class, "newlogin");
//		framework.getData(ProductDetailData.class, "validreview");
//
//		try {
//
//			ExtentTest childTest1 = testListener.startChild("New User Register Test");
//			childTest1.setDescription("This test verifies that new user is able to register");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			HeaderBar headerbar = new HeaderBar(browser);
//			AccountPage accountPage = new AccountPage(browser);
//			String loggedInUser = accountPage.NewRegister(accountData);
//			SanAssertCS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()),
//					"New user registration test");
//
//			ExtentTest childTest2 = testListener.startChild("Add Shade Product to Cart Test");
//			childTest2.setDescription("This test verifies that new user is able to add shaded product to cart");
//			TestListner.extentMap.get().put("child2", childTest2);
//			headerbar.navigateToCategory(headerbarData);
//			ProductPage productPage = new ProductPage(browser);
//			ListingPage listingPage = new ListingPage(browser);
//			ProductDetailData ProductDetailData = productPage
//					.addAnyShadeProductFromProductPAge(listingPage.allProducts());
//			CartPage cartPage = new CartPage(browser);
//			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData), "53A");
//
//			ExtentTest childTest3 = testListener.startChild("Customer Also Viewed Widget Test");
//			childTest3.setDescription("This test verifies that user is able to view PAV widget");
//			TestListner.extentMap.get().put("child3", childTest3);
//			SanAssertCS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "53C");
//
//			ExtentTest childTest4 = testListener.startChild("Customer Also Bought Widget Test");
//			childTest4.setDescription("This test verifies that user is able to view PAB widget");
//			TestListner.extentMap.get().put("child4", childTest4);
//			SanAssertCS.assertTrue(productPage.isPeopleWhoboughtDisplayed(), "53D");
//
//			ExtentTest childTest5 = testListener.startChild("Add to Wishlist Test");
//			childTest5.setDescription("This test verifies that user is able to add product to wishlist");
//			TestListner.extentMap.get().put("child5", childTest5);
//			headerbar.clearCart();
//			browser.navigate().refresh();
//			SanAssertCS.assertTrue(productPage.addToWishlist(), "53B");
//
//			ExtentTest childTest6 = testListener.startChild("Try it On Test");
//			childTest6.setDescription("This test verifies that user is able to view try it on");
//			TestListner.extentMap.get().put("child6", childTest6);
//			SanAssertCS.assertTrue(productPage.verifyTryitOn(), "53E");
//
//			ExtentTest childTest7 = testListener.startChild("Add Product from Wishlist to Cart Test");
//			childTest7.setDescription("This test verifies that user is able to add product from wishlist to cart");
//			TestListner.extentMap.get().put("child7", childTest7);
//			accountPage.navigateToWishlist();
//			listingPage.addAnyShadeProductwishlist();
//			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData), "53F");
//			browser.navigate().refresh();
//			/*
//			 * SanAssertCS.assertTrue(productPage.addReview(productData,
//			 * accountData), "101");
//			 */
//			// SanAssertCS.assertTrue(productPage.likeReview(),"102");
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally {
//
//			//LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "productaddtobag", "productaddtobagsize", "AddToBag" })
//	public void Verify_Add_to_Bag_Size_Product_From_Product_Detail_Page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "sizenavigation");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		AccountData accountData = framework.getData(AccountData.class, "newlogin");
//
//		try {
//			ExtentTest childTest1 = testListener.startChild("New User Register Test");
//			childTest1.setDescription("This test verifies that new user is able to register");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			HeaderBar headerbar = new HeaderBar(browser);
//			{
//				AccountPage accountPage = new AccountPage(browser);
//				String loggedInUser = accountPage.NewRegister(accountData);
//				SanAssertCS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()),
//						"New user registration test");
//
//				ExtentTest childTest2 = testListener.startChild("Add Size Product to Cart Test");
//				childTest2.setDescription(
//						"This test verifies that new user is able to add size product to cart from PDP");
//				TestListner.extentMap.get().put("child2", childTest2);
//				headerbar.navigateToCategory(headerbarData);
//				ProductPage productPage = new ProductPage(browser);
//				ListingPage listingPage = new ListingPage(browser);
//				ProductDetailData ProductDetailData = productPage
//						.addAnySizeProductFromProductPage(listingPage.allProducts());
//				CartPage cartPage = new CartPage(browser);
//				SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData), "54A");
//
//				ExtentTest childTest3 = testListener.startChild("Customer Also Viewed Widget Test");
//				childTest3.setDescription("This test verifies that user is able to view PAV widget");
//				TestListner.extentMap.get().put("child3", childTest3);
//				SanAssertCS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "54C");
//
//				ExtentTest childTest4 = testListener.startChild("Customer Also Bought Widget Test");
//				childTest4.setDescription("This test verifies that user is able to view PAB widget");
//				TestListner.extentMap.get().put("child4", childTest4);
//				SanAssertCS.assertTrue(productPage.isPeopleWhoboughtDisplayed(), "54F");
//
//				ExtentTest childTest5 = testListener.startChild("Add to Wishlist Test");
//				childTest5.setDescription("This test verifies that user is able to add product to wishlist");
//				TestListner.extentMap.get().put("child5", childTest5);
//				headerbar.clearCart();
//				browser.navigate().refresh();
//				SanAssertCS.assertTrue(productPage.addToWishlist(), "54B");
//
//				ExtentTest childTest7 = testListener.startChild("Add Product from Wishlist to Cart Test");
//				childTest7.setDescription("This test verifies that user is able to add product from wishlist to cart");
//				TestListner.extentMap.get().put("child7", childTest7);
//				accountPage.navigateToWishlist();
//				ProductDetailData ProductDetailDataWishlist = listingPage.addAnySizeProductwishlist();
//				SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailDataWishlist), "54E");
//			}
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally {
//
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "productaddtobagLuxury","AddToBag" })
//	public void Verify_Add_to_Bag_From_Product_Detail_Page() throws Throwable {
//
//		Header_FooterBarData brandNavigation = framework.getData(Header_FooterBarData.class, "brand");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
//		browser.get(environmentURL);
//		AccountData Login = framework.getData(AccountData.class, "newlogin");
//		try {
//			ExtentTest childTest1 = testListener.startChild("New User Register Test");
//			childTest1.setDescription("This test verifies that new user is able to register");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			String loggedInUser = accountPage.NewRegister(Login);
//			SanCS.assertTrue(loggedInUser.toLowerCase().equals(Login.getFirstName()), "New user registration test");
//
//			ExtentTest childTest2 = testListener.startChild("Add Static Combo to Cart from PDP Test");
//			childTest2.setDescription(
//					"This test verifies that user is able to add static combo product to cart from PDP");
//			TestListner.extentMap.get().put("child2", childTest2);
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.clearCart();
//			headerBar.navigateToBrand(brandNavigation);
//			ListingPage listingPage = new ListingPage(browser);
//			listingPage.navigateToShopAll("shop-all");
//			ProductPage productPage = new ProductPage(browser);
//			ProductDetailData productDetailData = productPage
//					.addAnyStaticComboProductFromProductPage(listingPage.allProducts());
//			CartPage cartPage = new CartPage(browser);
//			SanCS.assertTrue(cartPage.isProductPresentInBag(productDetailData), "55A");
//
//			ExtentTest childTest3 = testListener.startChild("Customer Also Viewed Widget Test");
//			childTest3.setDescription("This test verifies that user is able to view PAV widget");
//			TestListner.extentMap.get().put("child3", childTest3);
//			SanCS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "55C");
//
//			ExtentTest childTest4 = testListener.startChild("Add to Wishlist Test");
//			childTest4.setDescription("This test verifies that user is able to add product to wishlist");
//			TestListner.extentMap.get().put("child4", childTest4);
//			headerBar.clearCart();
//			browser.navigate().refresh();
//			SanCS.assertTrue(productPage.addToWishlist(), "55B");
//
//			ExtentTest childTest5 = testListener.startChild("Add Product to Cart from Wishlist Test");
//			childTest5.setDescription("This test verifies that user is able to add product to cart from wishlist");
//			TestListner.extentMap.get().put("child5", childTest5);
//			accountPage.navigateToWishlist();
//			ProductDetailData productDetailDatawishList = listingPage.addAnySimpleProductFromWishlist();
//			SanCS.assertTrue(cartPage.isProductPresentInBag(productDetailDatawishList), "55D");
//		}
//		catch (Exception e) {
//			printStackStraceinLog(e);
//
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally {
//
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "luxeProductAddtobag", "AddToBag" })
//	public void Product_Page_Add_To_Bag_Luxe_Brand() throws Throwable {
//
//		framework.getData(Header_FooterBarData.class, "brand");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		AccountData accountData = framework.getData(AccountData.class, "newlogin");
//		try {
//			ExtentTest childTest1 = testListener.startChild("New User Register Test");
//			childTest1.setDescription("This test verifies that new user is able to register");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			// "/luxe/brands/mac.html";
//			HeaderBar headerbar = new HeaderBar(browser);
//			AccountPage accountPage = new AccountPage(browser);
//			String loggedInUser = accountPage.NewRegister(accountData);
//			SanAssertCS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()),
//					"New user registration test");
//
//			ExtentTest childTest2 = testListener.startChild("Add Shade Product to Cart Test");
//			childTest2.setDescription("This test verifies that new user is able to add shaded product to cart");
//			TestListner.extentMap.get().put("child2", childTest2);
//			headerbar.clearCart();
//			browser.get(environmentURL + "/luxe/brands/mac.html");
//			ListingPage listingPage = new ListingPage(browser);
//			CartPage cartPage = new CartPage(browser);
//			ProductPage productPage = new ProductPage(browser);
//			ProductDetailData productDetailData = productPage
//					.addAnyShadeProductFromProductPAge(listingPage.allProducts());
//			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(productDetailData), "56A");
//
//			ExtentTest childTest3 = testListener.startChild("Add to Wishlist Test");
//			childTest3.setDescription("This test verifies that user is able to add product to wishlist");
//			TestListner.extentMap.get().put("child3", childTest3);
//			browser.navigate().refresh();
//			SanAssertCS.assertTrue(productPage.addToWishlist(), "56B");
//		}
//
//		catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally {
//
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "productaddtobag", "productaddtobagstatic","AddToBag"})
//	public void Verify_Add_to_Bag_Static_Combo_Product_From_Product_Page() throws Throwable {
//
//		Header_FooterBarData brandNavigation = framework.getData(Header_FooterBarData.class, "brand");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
//		browser.get(environmentURL);
//		AccountData Login = framework.getData(AccountData.class, "newlogin");
//		try {
//			ExtentTest childTest1 = testListener.startChild("New User Register Test");
//			childTest1.setDescription("This test verifies that new user is able to register");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			String loggedInUser = accountPage.NewRegister(Login);
//			SanCS.assertTrue(loggedInUser.toLowerCase().equals(Login.getFirstName()), "New user registration test");
//
//			ExtentTest childTest2 = testListener.startChild("Add Static Combo to Cart from PDP Test");
//			childTest2.setDescription(
//					"This test verifies that user is able to add static combo product to cart from PDP");
//			TestListner.extentMap.get().put("child2", childTest2);
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.clearCart();
//			headerBar.navigateToBrand(brandNavigation);
//			ListingPage listingPage = new ListingPage(browser);
//			listingPage.navigateToShopAll("shop-all");
//			ProductPage productPage = new ProductPage(browser);
//			ProductDetailData productDetailData = productPage
//					.addAnyStaticComboProductFromProductPage(listingPage.allProducts());
//			CartPage cartPage = new CartPage(browser);
//			SanCS.assertTrue(cartPage.isProductPresentInBag(productDetailData), "55A");
//
//			ExtentTest childTest3 = testListener.startChild("Customer Also Viewed Widget Test");
//			childTest3.setDescription("This test verifies that user is able to view PAV widget");
//			TestListner.extentMap.get().put("child3", childTest3);
//			SanCS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "55C");
//
//			ExtentTest childTest4 = testListener.startChild("Add to Wishlist Test");
//			childTest4.setDescription("This test verifies that user is able to add product to wishlist");
//			TestListner.extentMap.get().put("child4", childTest4);
//			browser.navigate().refresh();
//			SanCS.assertTrue(productPage.addToWishlist(), "55B");
//		}
//
//		catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally {
//
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "ProductPageChecks", "productpage02" })
//	public void Product_Page_Checks() throws Throwable, Throwable {
//		Framework framwork = new Framework();
//		PropertyConfiguration pf = new PropertyConfiguration();
//		pf.getInstance();
//
//		AccountData accountData = framework.getData(AccountData.class, "newlogin");
//		WebDriver browser = framwork.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert CS = new CustomSoftAssert(SanAssertData, browser);
//		ProductDetailData productData = framwork.getData(ProductDetailData.class, "validreview");
//		try {
//			ExtentTest childTest1 = testListener.startChild("Existing User Login Test");
//			childTest1.setDescription("This test verifies that existing user is able to login");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL + "/hair-care.html?root=nav_1&dir=desc&order=popularity");
//			AccountPage accountPage = new AccountPage(browser);
//			String loggedInUser = accountPage.signInWithYourEmail(accountData);
//			CS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()), "Existing user login test");
//
//			ExtentTest childTest2 = testListener.startChild("Add Review test");
//			childTest2.setDescription("This test verifies that existing user is able to add review");
//			TestListner.extentMap.get().put("child2", childTest2);
//			browser.get(
//					environmentURL + "/makeup/lips/lipstick/lakme-9-to-5-matte-lip-color.html?root=catg&ptype=product");
//			ProductPage productPage = new ProductPage(browser);
//			productPage.checkListOfProductPage();
//			CS.assertTrue(productPage.addReview(productData, accountData), "101");
//
//			ExtentTest childTest3 = testListener.startChild("Ask Nykaa test");
//			childTest3.setDescription("This test verifies that existing user is able to ask Nykaa");
//			TestListner.extentMap.get().put("child3", childTest3);
//			CS.assertTrue(productPage.askNykaa(productData, accountData), "104");
//			// CS.assertTrue(productPage.readPost(), "108");
//			// CS.assertAll();
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString());
//
//		} finally {
//
//			//LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//			Assert.fail();
//		}
//
//	}
//
//	@Test(groups = { "ProductPageChecks", "productpage02" })
//	public void Verify_Shade_Product_Add_To_Bag_from_PDP() throws Throwable, Throwable {
//		Framework framwork = new Framework();
//		WebDriver browser = framwork.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert CS = new CustomSoftAssert(SanAssertData, browser);
//		AccountData accountData = LoginSync.getInstance().getLogin();
//		Header_FooterBarData headerBarData = framework.getData(Header_FooterBarData.class, "shadenavigation");
//
//		try {
//			ExtentTest childTest1 = testListener.startChild("Existing User Login Test");
//			childTest1.setDescription("This test verifies that existing user is able to login");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			String loggedInUser = accountPage.signInWithYourEmail(accountData);
//			CS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()), "Existing user login test");
//
//			ExtentTest childTest2 = testListener.startChild("Add Shade Product to Cart Test");
//			childTest2.setDescription("This test verifies that new user is able to add shaded product to cart");
//			TestListner.extentMap.get().put("child2", childTest2);
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.clearCart();
//			headerBar.navigateToCategory(headerBarData);
//			CartPage cartPage = new CartPage(browser);
//			ProductPage productPage = new ProductPage(browser);
//			ListingPage listingPage = new ListingPage(browser);
//			ProductDetailData productDetailData = productPage
//					.addAnyShadeProductFromProductPAge(listingPage.allProducts());
//			CS.assertTrue(cartPage.isProductPresentInBag(productDetailData), "Add shade product to cart test");
//			headerBar.clearCart();
//			/*
//			 * browser.get( environmentURL +
//			 * "/makeup/lips/lipstick/lakme-9-to-5-matte-lip-color.html?root=catg&ptype=product"
//			 * ); ProductPage productPage = new ProductPage(browser);
//			 * productPage.checkListOfProductPage();
//			 * CS.assertTrue(productPage.addReview(productData, accountData),
//			 * "101"); CS.assertTrue(productPage.askNykaa(productData,
//			 * accountData), "104"); CS.assertTrue(productPage.readPost(),
//			 * "108"); CS.assertAll();
//			 */
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//
//		} finally {
//
//			LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//			Assert.fail();
//		}
//
//	}
//
//	/***************************************************************************************
//	 * SearchTestCases
//	 ***********************************************************************************************************/
//
//	@Test(groups = { "searchFeature", "searchBrandName"})
//	public void Search_Keyword_Test() throws Throwable, Throwable {
//		Framework frameWork = new Framework();
//		AccountData accountData = LoginSync.getInstance().getLogin();
//		WebDriver browser = frameWork.getBrowser(CommonConstants.NKYAA);
//		PropertyConfiguration pf = new PropertyConfiguration();
//		pf.getInstance();
//		// this.environmentURL = prop.getProperty("environmentName");
//		List<Header_FooterBarData> headerbarDataList = frameWork.getDataList(Header_FooterBarData.class, "TD4");
//		CustomSoftAssert CS = new CustomSoftAssert(SanAssertData, browser);
//		// Header_FooterBarData headerbarData = new Header_FooterBarData();
//		try {
//			ExtentTest childTest1 = testListener.startChild("Existing User Login Test");
//			childTest1.setDescription("This test verifies that existing user is able to login");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			String loggedInUser = accountPage.signInWithYourEmail(accountData);
//			CS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()), "Existing user login test");
//
//			ExtentTest childTest2 = testListener.startChild("Search Keyword Test");
//			childTest2.setDescription("This test verifies that existing user is able to login");
//			TestListner.extentMap.get().put("child2", childTest2);
//			HeaderBar headerBar = new HeaderBar(browser);
//			for (Header_FooterBarData headerbarData : headerbarDataList) {
//				headerBar.search(headerbarData.get_search_keyword());
//				PartialStringList SearchedResult = headerBar.getSearchedResult();
//				CS.assertTrue(SearchedResult.contains(headerbarData.get_search_keyword().toLowerCase()), "271C");
//				CS.assertAll();
//				/*
//				 * ListingPage listingPage = new ListingPage(browser); CartPage
//				 * cartPage = new CartPage(browser); ProductDetailData
//				 * ProductDetailData2 =
//				 * listingPage.addAnyShadeProductFromPeopleWhoBoughtWidget();
//				 * CS.assertTrue(cartPage.isProductPresentInBag(
//				 * ProductDetailData2), "79J"); browser.navigate().refresh();
//				 * 
//				 * boolean allProductWishlistAdded =
//				 * listingPage.addToWishList(listingPage.wishlistProducts());
//				 * CS.assertTrue(allProductWishlistAdded, "79B"); String
//				 * offerText =
//				 * listingPage.verifyOfferOn(listingPage.wishlistProducts());
//				 * CS.assertTrue(offerText != null, "79H");
//				 * listingPage.ScrollDown("1000", 4); int value =
//				 * listingPage.getSearchedProductCount(); if (value > 40) {
//				 * CS.assertTrue(true, "79E"); } List<ProductDetailData>
//				 * productList = new ArrayList<ProductDetailData>(); productList
//				 * = listingPage.ApplySort("NAME"); List<ProductDetailData>
//				 * productListFromUI = new ArrayList<ProductDetailData>();
//				 * productListFromUI.addAll(productList);
//				 * System.out.println(productListFromUI.toString());
//				 * Collections.sort(productList, (ProductDetailData a1,
//				 * ProductDetailData a2) -> a1.getProductName()
//				 * .compareTo(a2.getProductName()));
//				 * CS.assertEquals(productListFromUI, productList, "79F");
//				 * List<ProductDetailData> productListPriceFilter =
//				 * listingPage.ApplyFilter("price", "Rs. 0 - Rs. 500");
//				 * List<ProductDetailData> productlistwithPrizeFilter = new
//				 * ArrayList<ProductDetailData>(); //
//				 * productlistwithPrizeFilter.addAll(productListPriceFilter);
//				 * productlistwithPrizeFilter = productListPriceFilter.stream()
//				 * .filter(o -> Integer.parseInt(o.getProductPrize()) <
//				 * 500).collect(Collectors.toList());
//				 * System.out.println(productListPriceFilter);
//				 * System.out.println(productlistwithPrizeFilter);
//				 * CS.assertEquals(productlistwithPrizeFilter,
//				 * productListPriceFilter, "79G");
//				 */
//			}
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Assert.fail();
//		} finally {
//
//			LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//
//	}
//
//	@Test(groups = { "searchFeature", "searchAddToBag"})
//	public void Search_and_Add_To_Bag() throws Throwable, Throwable {
//		Framework frameWork = new Framework();
//		AccountData accountData = LoginSync.getInstance().getLogin();
//		WebDriver browser = frameWork.getBrowser(CommonConstants.NKYAA);
//		// PropertyConfiguration pf = new PropertyConfiguration();
//		// Properties prop = pf.getInstance();
//		// this.environmentURL = prop.getProperty("environmentName");
//		List<Header_FooterBarData> headerbarDataList = frameWork.getDataList(Header_FooterBarData.class, "TD1");
//		CustomSoftAssert CS = new CustomSoftAssert(SanAssertData, browser);
//		// Header_FooterBarData headerbarData = new Header_FooterBarData();
//		try {
//			ExtentTest childTest1 = testListener.startChild("Existing User Login Test");
//			childTest1.setDescription("This test verifies that existing user is able to login");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			String loggedInUser = accountPage.signInWithYourEmail(accountData);
//			CS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()), "Existing user login test");
//
//			ExtentTest childTest2 = testListener.startChild("Popular Search Test");
//			childTest2.setDescription("This test verifies that existing user is able to search");
//			TestListner.extentMap.get().put("child2", childTest2);
//			HeaderBar headerBar = new HeaderBar(browser);
//			for (Header_FooterBarData headerbarData : headerbarDataList) {
//				headerBar.search(headerbarData.get_search_keyword());
//				PartialStringList SearchedResult = headerBar.getSearchedResult();
//				CS.assertTrue(SearchedResult.contains(headerbarData.get_search_keyword().toLowerCase()), "271C");
//
//				ExtentTest childTest3 = testListener.startChild("Add to Wishlist Test");
//				childTest3.setDescription("This test verifies that user is able to add product to wishlist");
//				TestListner.extentMap.get().put("child3", childTest3);
//				ListingPage listingPage = new ListingPage(browser);
//				// CartPage cartPage = new CartPage(browser);
//				browser.navigate().refresh();
//				boolean allProductWishlistAdded = listingPage.addToWishList(listingPage.wishlistProducts());
//				CS.assertTrue(allProductWishlistAdded, "79B");
//
//				ExtentTest childTest4 = testListener.startChild("Verify Offer Test");
//				childTest4.setDescription("This test verifies that new user is able to view offer");
//				TestListner.extentMap.get().put("child4", childTest4);
//				String offerText = listingPage.verifyOfferOn(listingPage.wishlistProducts());
//				CS.assertTrue(offerText != null, "79H");
//
//				ExtentTest childTest5 = testListener.startChild("Scroll 4-5 slot Test");
//				childTest5.setDescription("This test verifies that new user is able to scroll the page 4-5 times");
//				TestListner.extentMap.get().put("child5", childTest5);
//				listingPage.ScrollDown("1000", 4);
//				int value = listingPage.getSearchedProductCount();
//				if (value > 40) {
//					CS.assertTrue(true, "79E");
//				}
//
//				ExtentTest childTest6 = testListener.startChild("Apply Sort Test");
//				childTest6.setDescription("This test verifies that new user is able to apply sort");
//				TestListner.extentMap.get().put("child6", childTest6);
//				List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
//				productList = listingPage.ApplySort("NAME");
//				List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
//				productListFromUI.addAll(productList);
//				System.out.println(productListFromUI.toString());
//				Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
//						.compareTo(a2.getProductName()));
//				CS.assertEquals(productListFromUI, productList, "79F");
//
//				ExtentTest childTest7 = testListener.startChild("Apply Filter Test");
//				childTest7.setDescription("This test verifies that new user is able to apply filter");
//				TestListner.extentMap.get().put("child7", childTest7);
//				List<ProductDetailData> productListPriceFilter = listingPage.ApplyFilter("price", "Rs. 0 - Rs. 500");
//				List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
//				// productlistwithPrizeFilter.addAll(productListPriceFilter);
//				productlistwithPrizeFilter = productListPriceFilter.stream()
//						.filter(o -> Integer.parseInt(o.getProductPrize()) < 500).collect(Collectors.toList());
//				System.out.println(productListPriceFilter);
//				System.out.println(productlistwithPrizeFilter);
//				CS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "79G");
//			}
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Assert.fail();
//		} finally {
//
//			LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//
//	}
//
//	@Test(groups = { "searchFeature","sanity","Wsanity" })
//	public void Auto_Suggest_Test() throws Throwable, Throwable {
//		Framework frameWork = new Framework();
//		AccountData accountData = LoginSync.getInstance().getLogin();
//		WebDriver browser = frameWork.getBrowser(CommonConstants.NKYAA);
//		// PropertyConfiguration pf = new PropertyConfiguration();
//		// Properties prop = pf.getInstance();
//		// this.environmentURL = prop.getProperty("environmentName");
//		List<Header_FooterBarData> headerbarDataList = frameWork.getDataList(Header_FooterBarData.class, "TC3");
//		CustomSoftAssert CS = new CustomSoftAssert("automation Regression Coverage", "DeskTop");
//		// Header_FooterBarData headerbarData = new Header_FooterBarData();
//		try {
//			ExtentTest childTest1 = testListener.startChild("Auto Suggest in Search Test");
//			childTest1.setDescription("This test verifies that new user is able to view auto suggest in search");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			accountPage.signInWithYourEmail(accountData);
//			HeaderBar headerBar = new HeaderBar(browser);
//			for (Header_FooterBarData headerbarData : headerbarDataList) {
//				headerBar.search(headerbarData.get_search_keyword());
//				PartialStringList SearchedResult = headerBar.getValueFromAutoSuggetionList(CommonConstants.NKYAA);
//				CS.assertTrue(SearchedResult.contains(headerbarData.get_search_keyword().toLowerCase()), "271A");
//			}
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Assert.fail();
//		} finally {
//
//			LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "searchFeature", "searchtrending" })
//	public void Verify_Trending_Search() throws Throwable, Throwable {
//		Framework frameWork = new Framework();
//		AccountData accountData = LoginSync.getInstance().getLogin();
//		WebDriver browser = frameWork.getBrowser(CommonConstants.NKYAA);
//		// PropertyConfiguration pf = new PropertyConfiguration();
//		// Properties prop = pf.getInstance();
//		// this.environmentURL = prop.getProperty("environmentName");
//		List<Header_FooterBarData> headerbarDataList = frameWork.getDataList(Header_FooterBarData.class, "TC3");
//		CustomSoftAssert CS = new CustomSoftAssert("automation Regression Coverage", "DeskTop");
//		// Header_FooterBarData headerbarData = new Header_FooterBarData();
//		try {
//			ExtentTest childTest1 = testListener.startChild("Trending Search Test");
//			childTest1.setDescription("This test verifies that new user is able to view trending search");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			accountPage.signInWithYourEmail(accountData);
//			HeaderBar headerBar = new HeaderBar(browser);
//			for (@SuppressWarnings("unused") Header_FooterBarData headerbarData : headerbarDataList) {
//				headerBar.search("");
//				PartialStringList SearchedResult = headerBar.getTrendingSearchList();
//				CS.assertTrue(!(SearchedResult.isEmpty()), "271B");
//			}
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Assert.fail();
//		} finally {
//
//			LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//	}
//
//	/**********************************************************************
//	 * Home Page
//	 * 
//	 * @throws Throwable
//	 * @throws URISyntaxException
//	 ***********************************************************************************************************************/
//
//	@Test(groups = { "homePage", "Analytics", "homepage01" })
//
//	public void Verify_Banner_redirection() throws URISyntaxException, Throwable {
//
//		Framework framwork = new Framework();
//
//		AccountData accountData = LoginSync.getInstance().getLogin();
//		WebDriver browser = framwork.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert sanAssertData = new CustomSoftAssert(SanAssertData, browser);
//		// AnalyticsData bannerData = framwork.getData(AnalyticsData.class,
//		// "tc_campaignpage");
//
//		try{
//			ExtentTest childTest1 = testListener.startChild("Home Page Banner Redirection Test");
//			childTest1.setDescription("This test verifies that new user is able to access banner page");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			accountPage.signInWithYourEmail(accountData);
//			HomePage homePage = new HomePage(browser);
//			List<Boolean> bannerVerifiedList = homePage.verifyBannerRedirction();
//			// CS.assertFalse(bannerVerifiedList.contains(false), "39");
//			sanAssertData.assertFalse(bannerVerifiedList.contains(false), "39");
//			// CS.assertFalse(bannerVerifiedList.contains(false), "43L");
//			ExtentTest childTest2 = testListener.startChild("Home Page Mega Menu and Brand Selection Test");
//			childTest2.setDescription("This test verifies that new user is able to access mega menu and brand pages");
//			TestListner.extentMap.get().put("child2", childTest2);
//			sanAssertData.assertFalse(bannerVerifiedList.contains(false), "43L");
//			homePage.redirectToActiveBannerPage();
//			// AnalyticsInfoPage analyticsInfoPage = new
//			// AnalyticsInfoPage(browser);
//			// analyticsInfoPage.getEvar(bannerData);
//			// analyticsInfoPage.getEvent(bannerData);
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Assert.fail();
//		} finally {
//			LoginSync.getInstance().clearLoginLock(accountData);
//
//			browser.quit();
//		}
//	}
//
//	@Test(groups = {"sanity","Wsanity","serviceBook","payonvisit"})
//	public void View_Gallery_And_Book_Beauty_Service_test() throws Throwable {
//		Header_FooterBarData headerBarData = framework.getData(Header_FooterBarData.class,"beautyservices_navigation");
//		BeautyServiceData beautyServiceData = framework.getData(BeautyServiceData.class, "cityNavigation");
//		CheckoutData checkOutData = framework.getData(CheckoutData.class, "simplepay");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, browser);
//		AccountData Login = LoginSync.getInstance().getLogin();
//		try {
//			ExtentTest childTest = testListener.startChild("Navigate to Beauty Services");
//			childTest.setDescription("This test verifies that user is able to navigate to Beauty Services.");
//			TestListner.extentMap.get().put("child", childTest);
//			browser.get(environmentURL);
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.navigateToPopUps(headerBarData,CommonConstants.NKYAA);
//			BeautyServicePage beautyServicePage = new BeautyServicePage(browser);
//			beautyServicePage.navigateToQuickLink(beautyServiceData,environmentURL);
//			boolean isNavigatedToBeautyServices=beautyServicePage.selectCityAndLocation(beautyServiceData);
//			customSoftAssert.assertTrue(isNavigatedToBeautyServices,"L71");
//
//			ExtentTest childTest2 = testListener.startChild("View Gallery of Beauty Services");
//			childTest2.setDescription("This test verifies that user is able to see images in Gallery of any Beauty Service.");
//			TestListner.extentMap.get().put("child2", childTest2);
//
//			BeautyServiceData beautyServiceDataFromViewGallery = beautyServicePage.view_Gallery();
//			//CartPage cartPage = new CartPage(browser);
//			customSoftAssert.assertTrue(beautyServiceDataFromViewGallery!=null,"L67");
//			ExtentTest childTest1 = testListener.startChild("Book Beauty Service");
//			childTest1.setDescription("This test that verifies that user is able to add any Beauty Service to cart.");
//			TestListner.extentMap.get().put("child1", childTest1);
//
//			AccountPage accountPage = new AccountPage(browser);
//			accountPage.signInWithYourEmail(Login).toLowerCase();
//			headerBar.clearCart_Magento();
//
//			BeautyServiceData beautyServiceDataFromList = beautyServicePage.BookAnyService();
//			customSoftAssert.assertTrue((beautyServiceDataFromList!=null?true:false),"L66");
//			//login
//			ExtentTest childTest4 = testListener.startChild("Partial payment/Pay On Visit test");
//			childTest4.setDescription("This test verifies that existing user is able to apply Pay On visit services");
//			TestListner.extentMap.get().put("child4", childTest4);
//
//			CheckOutPage checkOutPage = new CheckOutPage(browser);
//			CartPage cartPage=new CartPage(browser);
//			cartPage.checkOut();
//			while (!browser.getCurrentUrl().contains("checkout")) {
//				Thread.sleep(1000);
//			}
//			if (browser.getCurrentUrl().contains(environmentURL.substring(environmentURL.lastIndexOf("/") + 1))) {
//				checkOutPage.savedAddressSelect();
//				// System.out.println(checkOutPage.isAddressSelected())
//				// If signed in then it should land on shipping address page.
//			} if(checkOutPage.isAddressSelected()==null) {
//				checkOutPage.fillNewAddressBeautyService(checkOutData);
//			}
//			boolean isPovApplied = checkOutPage.applyPOV();
//			customSoftAssert.assertTrue(isPovApplied, "L237");
//
//			ExtentTest childTest5 = testListener.startChild("NetBanking Checkout test");
//			childTest5.setDescription("This test verifies that existing user is able to checkout through netbanking"+ "");
//			TestListner.extentMap.get().put("child5", childTest5);
//			boolean isNetbanking = checkOutPage.netBankingCheckout();
//			customSoftAssert.assertTrue(isNetbanking, "L66");
//		}
//
//		catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Assert.fail();
//
//		} finally {
//
//			LoginSync.getInstance().clearLoginLock(Login);
//			browser.quit();
//		}
//
//	}
//
//
//	/**********************************************************************
//	 * Book Beauty Service test.
//	 ***********************************************************************/
//	@Test(groups = { "sanity","Wsanity","beautyfiltersort" })
//	public void Beauty_Service_Apply_Sort_And_Apply_Filter() throws Throwable {
//		//List<String> filterType = Arrays.asList("Service","Gender","Price");
//		Header_FooterBarData headerBarData = framework.getData(Header_FooterBarData.class,"beautyservices_navigation");
//		BeautyServiceData beautyServiceData = framework.getData(BeautyServiceData.class, "cityNavigation");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		//ListingPage listingPage = new ListingPage(browser);
//		try {
//			ExtentTest childTest = testListener.startChild("Beauty Service Apply Sort");
//			childTest.setDescription("This test verifies that user is able to apply sort on beauty service");
//			TestListner.extentMap.get().put("childTest", childTest);
//			browser.get(environmentURL);
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.navigateToPopUps(headerBarData ,CommonConstants.NKYAA);
//			BeautyServicePage beautyServicePage = new BeautyServicePage(browser);
//			beautyServicePage.navigateToQuickLink(beautyServiceData,environmentURL);
//			beautyServicePage.selectCityAndLocation(beautyServiceData);
//			List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
//			productList = beautyServicePage.ApplySort("NAME");
//			List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
//			productListFromUI.addAll(productList);
//			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
//					.compareTo(a2.getProductName()));
//			SanAssertCS.assertEquals(productListFromUI, productList, "L69");
//			ExtentTest childTest1 = testListener.startChild("Beauty Service Apply Filter");
//			childTest1.setDescription("This test verifies that user is able to apply filter on beauty service");
//			TestListner.extentMap.get().put("childTest1", childTest1);
//			List<ProductDetailData> productListPriceFilter = beautyServicePage.ApplyFilter("price",
//					"Rs. 500 - Rs. 1,000");
//			List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
//			// productlistwithPrizeFilter.addAll(productListPriceFilter);
//			productlistwithPrizeFilter = productListPriceFilter.stream()
//					.filter(o -> Integer.parseInt(o.getProductPrize()) >= 500
//					& Integer.parseInt(o.getProductPrize()) <= 1000)
//					.collect(Collectors.toList());
//			System.out.println(productlistwithPrizeFilter.size());
//			System.out.println(productListPriceFilter.size());
//			SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "L68");
//
//		}
//		catch (Exception e) {
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString());
//			Assert.fail();
//
//		} finally {
//			// LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//
//	}
//
//	/**********************************************************************
//	 * Book Beauty Service Apply Sort test
//	 ***********************************************************************/
//	@Test(groups = { "void" })
//	public void Beauty_Service_Apply_Sort() throws Throwable {
//		BeautyServiceData beautyServiceData = framework.getData(BeautyServiceData.class, "L69");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		boolean flag;
//		String message;
//		List<String> sortType = Arrays.asList("DISCOUNT","NAME","PRICE: HIGH TO LOW","PRICE: LOW TO HIGH");
//
//		try {
//			browser.get(environmentURL);
//			BeautyServicePage bs = new BeautyServicePage(browser);
//			{
//				flag = bs.navigateToBeautyService(beautyServiceData);
//				if (!flag) 
//				{
//					SanAssertCS.fail("Could not navigate to beauty service successfully");
//				}
//				for (String sortName : sortType)
//				{
//					System.out.println("Sort Type "+sortName);
//					flag = bs.applySort(sortName);
//					if (!flag) {
//						message = sortName + " Sort Failed";
//					}
//					else {
//						message = sortName + " Sort Passed";
//					}
//					SanAssertCS.assertTrue(flag, message);
//					SanAssertCS.assertAll();
//				}
//			}
//		}
//		catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Assert.fail();
//
//		} finally {
//
//			// LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "Mayank1" })
//	public void VerifyWriteQuestion() throws URISyntaxException, Throwable {
//
//		AccountData Login = LoginSync.getInstance().getLogin();
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//
//		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
//		try {
//
//			browser.get(environmentURL);
//			AccountPage accountPage = new AccountPage(browser);
//			String LoggedUserName = accountPage.signInWithYourEmail(Login).toLowerCase();
//			SanCS.assertEquals(LoggedUserName, Login.getFirstName().toLowerCase(), "104");
//
//
//		} catch (Throwable e) {
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString());
//			Assert.fail();
//		} finally {
//
//			LoginSync.getInstance().clearLoginLock(Login);
//			browser.quit();
//
//		}
//
//	}
//
//	@Test(groups = { "sanity","Wsanity" })
//	public void Verify_Nykaa_Network() throws Throwable {
//		NykaaNetworkData nykaaNetworkData = framework.getData(NykaaNetworkData.class, "acne");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		AccountData accountData = framework.getData(AccountData.class, "newlogin");
//		CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, browser);
//		try{
//			ExtentTest childTest1 = testListener.startChild("Nykaa Network Test");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			HeaderBar headerbar = new HeaderBar(browser);
//
//			AccountPage accountPage = new AccountPage(browser);
//			accountPage.NewRegister(accountData);
//			headerbar.navigateToNetwork();
//			CommunityPage communityPage = new CommunityPage(browser);
//			communityPage.getStartedWindow();
//			String topic = communityPage.topicSelectionWindow();
//			customSoftAssert.assertTrue(communityPage.isTopicSelected(topic),"C1T2");
//			String question =communityPage.postAQuestion(nykaaNetworkData);
//			customSoftAssert.assertTrue(communityPage.isQuestionPosted(question),"C1T3");
//			Thread.sleep(5000);
//			String reply = communityPage.replyToTheQuestion(nykaaNetworkData);
//			customSoftAssert.assertTrue(communityPage.isreplyPosted(reply),"C1T5");
//
//			communityPage.explore();
//			communityPage.userupdate();
//			browser.quit();
//
//
//
//		}catch (Throwable e){
//			e.printStackTrace();
//
//		}finally {
//			browser.quit();
//		}
//
//	}
//
//	/**********************************************************************
//	 * React Pages test- Listing Pages.
//	 ***********************************************************************/
//	@Test(groups = { "sanity","Wsanity","react"})
//	public void React_Page_Check_for_L1_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//
//		try {
//			ExtentTest childTest1 = testListener.startChild("Add any Shade/Size product to cart from L1 category React page from widget test ");
//			childTest1.setDescription("This test verifies that user is able to add any Shade/Size product from L1 category React page widget into cart");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			HeaderBar headerbar = new HeaderBar(browser);
//			AccountData accountData = framework.getData(AccountData.class, "newlogin");
//			AccountPage accountPage = new AccountPage(browser);
//			accountPage.NewRegister(accountData);
//			headerbar.navigateToCategory(headerbarData);
//			ListingPage_React listingPage = new ListingPage_React(browser);
//			CartPage_React cartPage = new CartPage_React(browser);
//			ProductDetailData productDetailData1 = listingPage.addAnyShadeProductFromWidget();
//			if(productDetailData1.getProductName()==null)
//			{
//				productDetailData1 = listingPage.addAnySizeProductFromWidget();
//				if(productDetailData1.getProductName()==null)
//				{
//					productDetailData1 = listingPage.addAnySimpleProductFromWidget();
//				}
//			}
//			if(productDetailData1.getProductName().equalsIgnoreCase("Widget not found"))
//			{
//				SanAssertCS.assertTrue(false, "R_79C");
//			}
//			else
//			{
//				SanAssertCS.assertTrue(cartPage.isProductPresentInBag(productDetailData1), "R_79C");
//			}
//			ExtentTest childTest2 = testListener.startChild("Add any Simple product to cart from L1 category React page all products section test ");
//			childTest2.setDescription("This test verifies that user is able to add any Simple product from L1 category React page all products section into cart");
//			TestListner.extentMap.get().put("child2", childTest2);
//			browser.navigate().refresh();
//			ProductDetailData SimpleProductDetailData = listingPage.addAnySimpleProductFromAllProducts();
//			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(SimpleProductDetailData), "R_79D_A");
//			
//			ExtentTest childTest9 = testListener.startChild("Add any Shade product to cart from L1 category React page all products section test ");
//			childTest9.setDescription("This test verifies that user is able to add any Shade product from L1 category React page all products section into cart");
//			TestListner.extentMap.get().put("child9", childTest9);
//			browser.navigate().refresh();
//			ProductDetailData ShadeProductDetailData = listingPage.addAnyShadeProductFromAllProduct();
//			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ShadeProductDetailData), "R_79D_B");
//			
//			ExtentTest childTest10 = testListener.startChild("Add any Size product to cart from L1 category React page all products section test ");
//			childTest10.setDescription("This test verifies that user is able to add any Size product from L1 category React page all products section into cart");
//			TestListner.extentMap.get().put("child10", childTest10);
//			browser.navigate().refresh();
//			ProductDetailData SizeProductDetailData = listingPage.addAnySizeProductFromAllProduct();
//			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(SizeProductDetailData), "R_79D_C");
//
//			ExtentTest childTest5 = testListener.startChild("Add any product from L1 category React page widget to wishlist test ");
//			childTest5.setDescription("This test verifies that user is able to add any product from L1 category React page widget section to wishlist");
//			TestListner.extentMap.get().put("child5", childTest5);
//			browser.navigate().refresh();
//			if(productDetailData1.getProductName().equalsIgnoreCase("Widget not found"))
//			{
//				SanAssertCS.assertTrue(false, "R_79A");
//			}
//			else
//			{
//				boolean widgetWishlistAdded = listingPage.addToWishList(listingPage.widgetProducts(),null);
//				SanAssertCS.assertTrue(widgetWishlistAdded, "R_79A");
//			}
//
//			ExtentTest childTest6 = testListener.startChild("Add Simple product to wishlist from L1 category React page from all products section test ");
//			childTest6.setDescription("This test verifies that user is able to add any simple product from L1 category React page all products section to wishlist");
//			TestListner.extentMap.get().put("child6", childTest6);
//
//			boolean simpleProductWishlistAdded = listingPage.addToWishList(ListingPage_React.allProducts(),"simple");
//			SanAssertCS.assertTrue(simpleProductWishlistAdded, "R_79B_A");
//			
//			ExtentTest childTest12 = testListener.startChild("Add Shade product to wishlist from L1 category React page from all products section test ");
//			childTest12.setDescription("This test verifies that user is able to add Shade product from L1 category React page all products section to wishlist");
//			TestListner.extentMap.get().put("child12", childTest12);
//
//			boolean shadeProductWishlistAdded = listingPage.addToWishList(ListingPage_React.allProducts(),"shade");
//			SanAssertCS.assertTrue(shadeProductWishlistAdded, "R_79B_B");
//			
//			ExtentTest childTest13 = testListener.startChild("Add Size product to wishlist from L1 category React page from all products section test ");
//			childTest13.setDescription("This test verifies that user is able to add Size product from L1 category React page all products section to wishlist");
//			TestListner.extentMap.get().put("child13", childTest13);
//
//			boolean sizeProductWishlistAdded = listingPage.addToWishList(ListingPage_React.allProducts(),"size");
//			SanAssertCS.assertTrue(sizeProductWishlistAdded, "R_79B_C");
//
//			ExtentTest childTest7 = testListener.startChild("Verify Offer text test ");
//			childTest7.setDescription("This test verifies the offer on products in all products section of React page");
//			TestListner.extentMap.get().put("child7", childTest7);
//
//			String offerText = listingPage.verifyOfferOn(ListingPage_React.allProducts(),CommonConstants.NKYAA);
//			SanAssertCS.assertTrue(offerText != null, "R_79K");
//
//			ExtentTest childTest8 = testListener.startChild("Load More Products test ");
//			childTest8.setDescription("This test verifies that products are loaded successfully on L1 category React page when user scrolls clicks Load More");
//			TestListner.extentMap.get().put("child8", childTest8);
//
//			listingPage.LoadMoreProducts(2,false);
//			int value = listingPage.getProductCountOfAllProducts();
//			if (value > 40) {
//				SanAssertCS.assertTrue(true, "R_79E");
//			}
//
//			ExtentTest childTest11 = testListener.startChild("Back to Top test ");
//			childTest11.setDescription("This test verifies that user is able go to top by clicking Back To Top button on React page");
//			TestListner.extentMap.get().put("child11", childTest11);
//
//			SanAssertCS.assertTrue(listingPage.backToTop(),"R_79F");
//
//
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally 
//		{
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "sanity","Wsanity","react","reactL2"})
//	public void React_Page_Check_for_L2_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l2category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//
//		try {
//			ExtentTest childTest1 = testListener.startChild("Add any Shade/Size product from L2 category React page widget test ");
//			childTest1.setDescription("This test verifies that user is able to add any Shade/Size product from L2 category React page widget into cart");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			HeaderBar headerbar = new HeaderBar(browser);
//			AccountData accountData = framework.getData(AccountData.class, "newlogin");
//			AccountPage accountPage = new AccountPage(browser);
//			accountPage.NewRegister(accountData);
//			headerbar.clearCart();
//			headerbar.navigateToCategory(headerbarData);
//			ListingPage_React listingPage = new ListingPage_React(browser);
//			listingPage.navigateToShopAll("shop-all");
//			// listingPage.addAnyShadeProductFromWidget();
//			CartPage_React cartPage = new CartPage_React(browser);
//			ProductDetailData productDetailData1 = listingPage.addAnyShadeProductFromWidget();
//			if(productDetailData1.getProductName()==null)
//			{
//				productDetailData1 = listingPage.addAnySizeProductFromWidget();
//				if(productDetailData1.getProductName()==null)
//				{
//					productDetailData1 = listingPage.addAnySimpleProductFromWidget();
//				}
//			}
//			if(productDetailData1.getProductName().equalsIgnoreCase("Widget not found"))
//			{
//				SanAssertCS.assertTrue(false, "R_80C");
//			}
//			else
//			{
//				SanAssertCS.assertTrue(cartPage.isProductPresentInBag(productDetailData1), "R_80C");
//			}
//			ExtentTest childTest2 = testListener.startChild("Add any Shade/Size product from L2 category React page all products section test ");
//			childTest2.setDescription("This test verifies that user is able to add any Shade/Size product from L2 category React page all products section into cart");
//			TestListner.extentMap.get().put("child2", childTest2);
//
//			ProductDetailData ProductDetailData = listingPage.addAnyShadeProductFromAllProduct();
//			if(ProductDetailData.getProductName()==null)
//			{
//				ProductDetailData = listingPage.addAnySizeProductFromAllProduct();
//				if(ProductDetailData.getProductName()==null)
//				{
//					ProductDetailData = listingPage.addAnySimpleProductFromAllProducts();
//				}
//			}
//			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData), "R_80D");
//
//			browser.navigate().refresh();
//
//			ExtentTest childTest5 = testListener.startChild("Add any product from L2 category React page widget to wishlist test ");
//			childTest5.setDescription("This test verifies that user is able to add any product from L2 category React page widget section to wishlist");
//			TestListner.extentMap.get().put("child5", childTest5);
//
//			if(productDetailData1.getProductName().equalsIgnoreCase("Widget not found"))
//			{
//				SanAssertCS.assertTrue(false, "R_80A");
//			}
//			else
//			{
//				boolean widgetWishlistAdded = listingPage.addToWishList(listingPage.widgetProducts(),null);
//				SanAssertCS.assertTrue(widgetWishlistAdded, "R_80A");
//			}
//
//			ExtentTest childTest6 = testListener.startChild("Add any product from L2 category React page all products to wishlist test ");
//			childTest6.setDescription("This test verifies that user is able to add any product from L2 category React page all products section to wishlist");
//			TestListner.extentMap.get().put("child6", childTest6);
//
//			boolean allProductWishlistAdded = listingPage.addToWishList(ListingPage_React.allProducts(),null);
//			SanAssertCS.assertTrue(allProductWishlistAdded, "R_80B");
//
//			ExtentTest childTest7 = testListener.startChild("Verify Offer text test ");
//			childTest7.setDescription("This test verifies the offer on products in all products section of React page");
//			TestListner.extentMap.get().put("child7", childTest7);
//
//			String offerText = listingPage.verifyOfferOn(ListingPage_React.allProducts(),CommonConstants.NKYAA);
//			SanAssertCS.assertTrue(offerText != null, "R_80K");
//
//			ExtentTest childTest8 = testListener.startChild("Load More Products test ");
//			childTest8.setDescription("This test verifies that products are loaded successfully on L2 category React page when user scrolls clicks Load More");
//			TestListner.extentMap.get().put("child8", childTest8);
//
//			listingPage.LoadMoreProducts(2,false);
//			int value = listingPage.getProductCountOfAllProducts();
//			if (value > 40) {
//				SanAssertCS.assertTrue(true, "R_80E");
//			}
//
//			ExtentTest childTest11 = testListener.startChild("Back to Top test ");
//			childTest11.setDescription("This test verifies that user is able go to top by clicking Back To Top button on React page");
//			TestListner.extentMap.get().put("child11", childTest11);
//
//			SanAssertCS.assertTrue(listingPage.backToTop(),"R_80F");
//
//
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally 
//		{
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "sanity","Wsanity","react"})
//	public void React_Page_Check_for_L3_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l3category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//
//		try {
//			ExtentTest childTest1 = testListener.startChild("Add any Shade/Size product from L2 category React page widget test ");
//			childTest1.setDescription("This test verifies that user is able to add any Shade/Size product from L3 category React page widget into cart");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			HeaderBar headerbar = new HeaderBar(browser);
//			AccountData accountData = framework.getData(AccountData.class, "newlogin");
//			AccountPage accountPage = new AccountPage(browser);
//			accountPage.NewRegister(accountData);
//			headerbar.clearCart();
//			headerbar.navigateToCategory(headerbarData);
//			ListingPage_React listingPage = new ListingPage_React(browser);
//			listingPage.navigateToShopAll("shop-all");
//			CartPage_React cartPage = new CartPage_React(browser);
//			ProductDetailData productDetailData1 = listingPage.addAnyShadeProductFromWidget();
//			if(productDetailData1.getProductName().equalsIgnoreCase("Product type not found"))
//			{
//				productDetailData1 = listingPage.addAnySizeProductFromWidget();
//				if(productDetailData1.getProductName().equalsIgnoreCase("Product type not found"))
//				{
//					productDetailData1 = listingPage.addAnySimpleProductFromWidget();
//				}
//			}
//			if(productDetailData1.getProductName().equalsIgnoreCase("Widget not found"))
//			{
//				SanAssertCS.assertTrue(false, "R_81C");
//			}
//			else
//			{
//				SanAssertCS.assertTrue(cartPage.isProductPresentInBag(productDetailData1), "R_81C");
//			}
//			ExtentTest childTest2 = testListener.startChild("Add any Shade/Size product from L3 category React page all products section test ");
//			childTest2.setDescription("This test verifies that user is able to add any Shade/Size product from L3 category React page all products section into cart");
//			TestListner.extentMap.get().put("child2", childTest2);
//
//			ProductDetailData ProductDetailData = listingPage.addAnyShadeProductFromAllProduct();
//			if(ProductDetailData.getProductName().equalsIgnoreCase("Product type not found"))
//			{
//				ProductDetailData = listingPage.addAnySizeProductFromAllProduct();
//				if(ProductDetailData.getProductName().equalsIgnoreCase("Product type not found"))
//				{
//					ProductDetailData = listingPage.addAnySimpleProductFromAllProducts();
//				}
//			}
//			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData), "R_81D");
//
//			browser.navigate().refresh();
//
//			ExtentTest childTest5 = testListener.startChild("Add any product from L3 category React page widget to wishlist test ");
//			childTest5.setDescription("This test verifies that user is able to add any product from L3 category React page widget section to wishlist");
//			TestListner.extentMap.get().put("child5", childTest5);
//
//			if(productDetailData1.getProductName().equalsIgnoreCase("Widget not found"))
//			{
//				SanAssertCS.assertTrue(false, "R_81A");
//			}
//			else
//			{
//				boolean widgetWishlistAdded = listingPage.addToWishList(listingPage.widgetProducts(),null);
//				SanAssertCS.assertTrue(widgetWishlistAdded, "R_81A");
//			}
//
//			ExtentTest childTest6 = testListener.startChild("Add any product from L3 category React page all products to wishlist test ");
//			childTest6.setDescription("This test verifies that user is able to add any product from L3 category React page all products section to wishlist");
//			TestListner.extentMap.get().put("child6", childTest6);
//
//			boolean allProductWishlistAdded = listingPage.addToWishList(ListingPage_React.allProducts(),null);
//			SanAssertCS.assertTrue(allProductWishlistAdded, "R_81B");
//
//			ExtentTest childTest7 = testListener.startChild("Verify Offer text test ");
//			childTest7.setDescription("This test verifies the offer on products in all products section of React page");
//			TestListner.extentMap.get().put("child7", childTest7);
//
//			String offerText = listingPage.verifyOfferOn(ListingPage_React.allProducts(),CommonConstants.NKYAA);
//			SanAssertCS.assertTrue(offerText != null, "R_81K");
//
//			ExtentTest childTest8 = testListener.startChild("Load More Products test ");
//			childTest8.setDescription("This test verifies that products are loaded successfully on L3 category React page when user scrolls clicks Load More");
//			TestListner.extentMap.get().put("child8", childTest8);
//
//			listingPage.LoadMoreProducts(2,false);
//			int value = listingPage.getProductCountOfAllProducts();
//			if (value > 40) {
//				SanAssertCS.assertTrue(true, "R_81E");
//			}
//
//			ExtentTest childTest11 = testListener.startChild("Back to Top test ");
//			childTest11.setDescription("This test verifies that user is able go to top by clicking Back To Top button on React page");
//			TestListner.extentMap.get().put("child11", childTest11);
//
//			SanAssertCS.assertTrue(listingPage.backToTop(),"R_81F");
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally 
//		{
//			browser.quit();
//		}
//	}
//
//	@Test(groups = {"sanity","Wsanity","react"})
//	public void React_Page_Sorting_on_L1_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
//		List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
//		List<String> printList = new ArrayList<String>();
//		List<String> printListSorted = new ArrayList<String>();
//		ListingPage_React listingPage = new ListingPage_React(browser);
//		HeaderBar headerbar = new HeaderBar(browser);
//
//		try {
//			ExtentTest childTest1 = testListener.startChild("Sort by Name test ");
//			childTest1.setDescription("This test verifies the user is able to sort products by Name on react page");
//			TestListner.extentMap.get().put("child1", childTest1);
//
//			browser.get(environmentURL);
//
//			headerbar.navigateToCategory(headerbarData);
//			productList = listingPage.ApplySort("NAME");
//			productListFromUI.addAll(productList);
//			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
//					.compareTo(a2.getProductName()));
//			for(int i=0;i<productList.size();i++)
//			{
//				printList.add(productList.get(i).getProductName());
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after Name Sorting: "+printList.toString());
//			SanAssertCS.assertEquals(productListFromUI, productList, "R_79G");
//
//			ExtentTest childTest2 = testListener.startChild("Sort by Price: Low To High test ");
//			childTest2.setDescription("This test verifies the user is able to sort products by Price: Low To High on react page");
//			TestListner.extentMap.get().put("child2", childTest2);
//			productList.clear();
//			productListFromUI.clear();
//			printList.clear();
//			productList = listingPage.ApplySort("PRICE: LOW TO HIGH");
//			productListFromUI.addAll(productList);
//			Collections.sort(productList, (ProductDetailData a2, ProductDetailData a1) -> Integer.parseInt(a2.getProductPrize()) - Integer.parseInt(a1.getProductPrize()));
//			for(int i=0;i<productList.size();i++)
//			{
//				printList.add(productList.get(i).getProductPrize());
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after PRICE: LOW TO HIGH Sorting: "+printList.toString());
//			SanAssertCS.assertEquals(productListFromUI, productList, "R_79H");
//
//			ExtentTest childTest3 = testListener.startChild("Sort by Price: High To Low test ");
//			childTest3.setDescription("This test verifies the user is able to sort products by Price: High To Low on react page");
//			TestListner.extentMap.get().put("child3", childTest3);
//			productList.clear();
//			productListFromUI.clear();
//			printList.clear();
//			productList = listingPage.ApplySort("PRICE: HIGH TO LOW");
//			productListFromUI.addAll(productList);
//			for(int i=0;i<productList.size();i++)
//			{
//				printList.add(productList.get(i).getProductPrize());
//			}
//			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> Integer.parseInt(a1.getProductPrize()) - Integer.parseInt(a2.getProductPrize()));
//			Collections.reverse(productList);
//			for(int i=0;i<productList.size();i++)
//			{
//				printListSorted.add(productList.get(i).getProductPrize());
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after PRICE: HIGH TO LOW Sorting: "+printList.toString());
//			SanAssertCS.assertEquals(printListSorted, printList, "R_79Z");
//
//			ExtentTest childTest4 = testListener.startChild("Sort by Discount test ");
//			childTest4.setDescription("This test verifies the user is able to sort products by Discount on react page");
//			TestListner.extentMap.get().put("child4", childTest4);
//			productList.clear();
//			productListFromUI.clear();
//			printList.clear();
//			printListSorted.clear();
//			productList = listingPage.ApplySort("DISCOUNT");
//			productListFromUI.addAll(productList);
//			for(int i=0;i<productList.size();i++)
//			{
//				printList.add(productList.get(i).getDiscount());
//			}
//			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> Integer.parseInt(a1.getDiscount())-Integer.parseInt( a2.getDiscount()));
//			Collections.reverse(productList);
//			for(int i=0;i<productList.size();i++)
//			{
//				printListSorted.add(productList.get(i).getDiscount());
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after DISCOUNT Sorting: "+printList.toString());
//			SanAssertCS.assertEquals(printListSorted, printList, "R_79I");
//
//
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally {
//			browser.quit();
//		}
//	}
//
//	@Test(groups = {"sanity","Wsanity","react","LiveSanity"})
//	public void React_Page_Sorting_on_L2_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l2category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
//		List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
//		List<String> printList = new ArrayList<String>();
//		List<String> printListSorted = new ArrayList<String>();
//		ListingPage_React listingPage = new ListingPage_React(browser);
//		HeaderBar headerbar = new HeaderBar(browser);
//
//		try {
//			ExtentTest childTest1 = testListener.startChild("Sort by Name test ");
//			childTest1.setDescription("This test verifies the user is able to sort products by Name on react page");
//			TestListner.extentMap.get().put("child1", childTest1);
//
//			browser.get(environmentURL);
//
//			headerbar.navigateToCategory(headerbarData);
//			listingPage.navigateToShopAll("shop-all");
//			productList = listingPage.ApplySort("NAME");
//			productListFromUI.addAll(productList);
//			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
//					.compareToIgnoreCase(a2.getProductName()));
//			for(int i=0;i<productList.size();i++)
//			{
//				printList.add(productList.get(i).getProductName());
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after Name Sorting: "+printList.toString());
//			SanAssertCS.assertEquals(productListFromUI, productList, "R_80G");
//
//			ExtentTest childTest2 = testListener.startChild("Sort by Price: Low To High test ");
//			childTest2.setDescription("This test verifies the user is able to sort products by Price: Low To High on react page");
//			TestListner.extentMap.get().put("child2", childTest2);
//			productList.clear();
//			productListFromUI.clear();
//			printList.clear();
//			productList = listingPage.ApplySort("PRICE: LOW TO HIGH");
//			productListFromUI.addAll(productList);
//			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductPrize().compareTo( a2.getProductPrize()));
//			for(int i=0;i<productList.size();i++)
//			{
//				printList.add(productList.get(i).getProductPrize());
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after PRICE: LOW TO HIGH Sorting: "+printList.toString());
//			SanAssertCS.assertEquals(productListFromUI, productList, "R_80H");
//
//			ExtentTest childTest3 = testListener.startChild("Sort by Price: High To Low test ");
//			childTest3.setDescription("This test verifies the user is able to sort products by Price: High To Low on react page");
//			TestListner.extentMap.get().put("child3", childTest3);
//			productList.clear();
//			productListFromUI.clear();
//			printList.clear();
//			productList = listingPage.ApplySort("PRICE: HIGH TO LOW");
//			productListFromUI.addAll(productList);
//			for(int i=0;i<productList.size();i++)
//			{
//				printList.add(productList.get(i).getProductPrize());
//			}
//			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> Integer.parseInt(a1.getProductPrize()) - Integer.parseInt(a2.getProductPrize()));
//			Collections.reverse(productList);
//			for(int i=0;i<productList.size();i++)
//			{
//				printListSorted.add(productList.get(i).getProductPrize());
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after PRICE: HIGH TO LOW Sorting: "+printList.toString());
//			SanAssertCS.assertEquals(printListSorted, printList, "R_80Z");
//
//			ExtentTest childTest4 = testListener.startChild("Sort by Discount test ");
//			childTest4.setDescription("This test verifies the user is able to sort products by Discount on react page");
//			TestListner.extentMap.get().put("child4", childTest4);
//			productList.clear();
//			productListFromUI.clear();
//			printList.clear();
//			printListSorted.clear();
//			productList = listingPage.ApplySort("DISCOUNT");
//			productListFromUI.addAll(productList);
//			for(int i=0;i<productList.size();i++)
//			{
//				printList.add(productList.get(i).getDiscount());
//			}
//			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> Integer.parseInt(a1.getDiscount())-Integer.parseInt( a2.getDiscount()));
//			Collections.reverse(productList);
//			for(int i=0;i<productList.size();i++)
//			{
//				printListSorted.add(productList.get(i).getDiscount());
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after DISCOUNT Sorting: "+printList.toString());
//			SanAssertCS.assertEquals(printListSorted, printList, "R_80I");
//
//
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally {
//			browser.quit();
//		}
//	}
//
//	@Test(groups = {"sanity","Wsanity","react"})
//	public void React_Page_Sorting_on_L3_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l3category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
//		List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
//		List<String> printList = new ArrayList<String>();
//		List<String> printListSorted = new ArrayList<String>();
//		ListingPage_React listingPage = new ListingPage_React(browser);
//		HeaderBar headerbar = new HeaderBar(browser);
//
//		try {
//			ExtentTest childTest1 = testListener.startChild("Sort by Name test ");
//			childTest1.setDescription("This test verifies the user is able to sort products by Name on react page");
//			TestListner.extentMap.get().put("child1", childTest1);
//
//			browser.get(environmentURL);
//
//			headerbar.navigateToCategory(headerbarData);
//			listingPage.navigateToShopAll("shop-all");
//			productList = listingPage.ApplySort("NAME");
//			productListFromUI.addAll(productList);
//			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
//					.compareToIgnoreCase(a2.getProductName()));
//			for(int i=0;i<productList.size();i++)
//			{
//				printList.add(productList.get(i).getProductName());
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after Name Sorting: "+printList.toString());
//			SanAssertCS.assertEquals(productListFromUI, productList, "R_81G");
//
//			ExtentTest childTest2 = testListener.startChild("Sort by Price: Low To High test ");
//			childTest2.setDescription("This test verifies the user is able to sort products by Price: Low To High on react page");
//			TestListner.extentMap.get().put("child2", childTest2);
//			productList.clear();
//			productListFromUI.clear();
//			printList.clear();
//			productList = listingPage.ApplySort("PRICE: LOW TO HIGH");
//			productListFromUI.addAll(productList);
//			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductPrize().compareTo( a2.getProductPrize()));
//			for(int i=0;i<productList.size();i++)
//			{
//				printList.add(productList.get(i).getProductPrize());
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after PRICE: LOW TO HIGH Sorting: "+printList.toString());
//			SanAssertCS.assertEquals(productListFromUI, productList, "R_81H");
//
//			ExtentTest childTest3 = testListener.startChild("Sort by Price: High To Low test ");
//			childTest3.setDescription("This test verifies the user is able to sort products by Price: High To Low on react page");
//			TestListner.extentMap.get().put("child3", childTest3);
//			productList.clear();
//			productListFromUI.clear();
//			printList.clear();
//			productList = listingPage.ApplySort("PRICE: HIGH TO LOW");
//			productListFromUI.addAll(productList);
//			for(int i=0;i<productList.size();i++)
//			{
//				printList.add(productList.get(i).getProductPrize());
//			}
//			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> Integer.parseInt(a1.getProductPrize()) - Integer.parseInt(a2.getProductPrize()));
//			Collections.reverse(productList);
//			for(int i=0;i<productList.size();i++)
//			{
//				printListSorted.add(productList.get(i).getProductPrize());
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after PRICE: HIGH TO LOW Sorting: "+printList.toString());
//			SanAssertCS.assertEquals(printListSorted, printList, "R_81Z");
//
//			ExtentTest childTest4 = testListener.startChild("Sort by Discount test ");
//			childTest4.setDescription("This test verifies the user is able to sort products by Discount on react page");
//			TestListner.extentMap.get().put("child4", childTest4);
//			productList.clear();
//			productListFromUI.clear();
//			printList.clear();
//			printListSorted.clear();
//			productList = listingPage.ApplySort("DISCOUNT");
//			productListFromUI.addAll(productList);
//			for(int i=0;i<productList.size();i++)
//			{
//				printList.add(productList.get(i).getDiscount());
//			}
//			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> Integer.parseInt(a1.getDiscount())-Integer.parseInt( a2.getDiscount()));
//			Collections.reverse(productList);
//			for(int i=0;i<productList.size();i++)
//			{
//				printListSorted.add(productList.get(i).getDiscount());
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after DISCOUNT Sorting: "+printList.toString());
//			SanAssertCS.assertEquals(printListSorted, printList, "R_81I");
//
//
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally {
//			browser.quit();
//		}
//	}
//
//	@SuppressWarnings("unlikely-arg-type")
//	@Test(groups = { "sanity","Wsanity","react"})
//	public void React_Page_Price_Filter_on_L1_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
//		List<ProductDetailData> productlistwithPrizeFilter=new ArrayList<ProductDetailData>();
//		List<String> printList = new ArrayList<String>();
//		HeaderBar headerbar = new HeaderBar(browser);
//		ListingPage_React listingPage = new ListingPage_React(browser);
//		boolean PriceMatches=true;
//		boolean filtersCleared=false;
//		List<String> filtersClearedList=new ArrayList<String>();
//		try 
//		{
//			ExtentTest childTest1 = testListener.startChild("Navigate to React Page test ");
//			childTest1.setDescription("This test verifies the user is able to navigate to a react page");
//			TestListner.extentMap.get().put("child1", childTest1);
//			int count=1;
//			browser.get(environmentURL);
//			headerbar.navigateToCategory(headerbarData);
//
//			ArrayList<String> allOptions=new ArrayList<String>();
//			allOptions=listingPage.getFilterOptionsList("Price");
//			String randomForMultipleFilters=new String();
//			HashSet<Integer> indexes=framework.randomNumbers(allOptions.size());
//
//			for(int i:indexes)
//			{
//				if(count<=2) 
//				{
//					randomForMultipleFilters=randomForMultipleFilters.concat("----"+allOptions.get(i-1).split("\\(")[0].trim());
//				}
//				count+=1;
//			}
//			randomForMultipleFilters=randomForMultipleFilters.replaceFirst("----", "");
//
//			for(String val:allOptions)
//			{
//				val=val.split("\\(")[0].trim();
//				printList.clear();
//				productListPriceFilter.clear();
//				productlistwithPrizeFilter.clear();
//				int lowerVal;
//				int upperVal;
//				if(!val.contains("Above"))
//				{
//					lowerVal=Integer.parseInt((val.split("-")[0]).replaceAll("Rs. ", "").trim());
//					upperVal=Integer.parseInt((val.split("-")[1]).replaceAll("Rs. ", "").trim());
//				}
//				else
//				{
//					lowerVal=Integer.parseInt((val.split("&")[0]).replaceAll("Rs. ", "").trim());
//					upperVal=999999;
//				}
//
//				ExtentTest childTest = testListener.startChild("Filter by Price "+ val+" test ");
//				childTest.setDescription("This test verifies the user is able to apply filter by Price "+ val+" on react page");
//				TestListner.extentMap.get().put("child".concat(val), childTest);
//				if(val.equals(allOptions.get(allOptions.size()-1).split("\\(")[0].trim()))
//				{
//					productListPriceFilter = listingPage.ApplyFilter("Price", val,true,true);
//				}
//				else
//				{
//					productListPriceFilter = listingPage.ApplyFilter("Price", val,false,true);
//				}
//				System.out.println(productListPriceFilter.toString());
//				productListPriceFilter.remove(" ");
//				System.out.println(productListPriceFilter.toString());
//				productlistwithPrizeFilter = productListPriceFilter.stream().filter(o -> Integer.parseInt(o.getProductPrize()) <= upperVal && Integer.parseInt(o.getProductPrize()) >= lowerVal).collect(Collectors.toList());
//				for(int i=0;i<productListPriceFilter.size();i++)
//				{
//					printList.add(productListPriceFilter.get(i).getProductPrize());
//					if(! (Integer.parseInt(productListPriceFilter.get(i).getProductPrize())>=lowerVal && Integer.parseInt (productListPriceFilter.get(i).getProductPrize())<=upperVal))
//					{
//						PriceMatches=false;
//						TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductPrize()+" :: Expected: "+val);
//					}
//				}
//				TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Price "+ val+": "+printList.toString());
//				SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter,"Price filterTest for "+val);
//				filtersCleared=listingPage.clearAllFilters();
//				filtersClearedList.add(filtersCleared?"Filters cleared for"+val :"Filters not cleared for"+val );
//			}
//			SanAssertCS.assertTrue(PriceMatches,"R_79Q");
//
//			ExtentTest childTestFilter = testListener.startChild("Clear All Filters test");
//			childTestFilter.setDescription("This test verifies the user is able to clear applied filters on react page");
//			TestListner.extentMap.get().put("childTestFilter", childTestFilter);
//			TestListner.testing.get().log(LogStatus.INFO, "Clear Filter status: "+filtersClearedList.toString());
//			SanAssertCS.assertTrue(! filtersClearedList.toString().contains("Filters not cleared"),"R_79V_1");
//
//			ExtentTest childTest6 = testListener.startChild("Filter by Price "+randomForMultipleFilters.replace("----", " and ")+"  test ");
//			childTest6.setDescription("This test verifies the user is able to apply filter by Price "+randomForMultipleFilters.replace("----", " and ")+" on react page");
//			TestListner.extentMap.get().put("child6", childTest6);
//			productListPriceFilter.clear();
//			productlistwithPrizeFilter.clear();
//			printList.clear();
//			int lowerVal[]=new int[2];
//			int upperVal[] =new int[2];
//			String vals[]=randomForMultipleFilters.split("----");
//			try
//			{
//				for(int i=0;i<vals.length;i++)
//				{
//					if(!vals[i].contains("Above"))
//					{
//						System.out.println((vals[i].split("-")[0]).trim());
//						if((vals[i].split("-")[0]).trim().equals("Rs. 0"))
//						{
//							lowerVal[i]=0;
//						}
//						else
//						{
//							lowerVal[i]=Integer.parseInt((vals[i].split("-")[0]).replaceAll("Rs. ", "").trim());
//						}
//
//						upperVal[i]=Integer.parseInt((vals[i].split("-")[1]).replaceAll("Rs. ", "").trim());
//					}
//					else
//					{
//						lowerVal[i]=Integer.parseInt((vals[i].split("&")[0]).replaceAll("Rs. ", "").trim());
//						upperVal[i]=999999;
//					}
//				}
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//			productListPriceFilter = listingPage.ApplyFilter("Price", randomForMultipleFilters,false,true);
//			productlistwithPrizeFilter = productListPriceFilter.stream()
//					.filter(o -> (Integer.parseInt(o.getProductPrize()) < upperVal[0] && Integer.parseInt(o.getProductPrize()) > lowerVal[0])||(Integer.parseInt(o.getProductPrize()) < upperVal[1] && Integer.parseInt(o.getProductPrize()) > lowerVal[1])).collect(Collectors.toList());
//			for(int i=0;i<productlistwithPrizeFilter.size();i++)
//			{
//				printList.add(productlistwithPrizeFilter.get(i).getProductPrize());
//				if(! ((Integer.parseInt(productListPriceFilter.get(i).getProductPrize())>=lowerVal[0] && Integer.parseInt (productListPriceFilter.get(i).getProductPrize())<=upperVal[0])||(Integer.parseInt(productListPriceFilter.get(i).getProductPrize())>=lowerVal[1] && Integer.parseInt (productListPriceFilter.get(i).getProductPrize())<=upperVal[1])))
//				{
//					PriceMatches=false;
//					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductPrize()+" :: Expected: "+randomForMultipleFilters.replace("----", " and "));
//				}
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Price "+randomForMultipleFilters.replace("----", " and ")+" : "+printList.toString());
//			//SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "R_79S");
//			SanAssertCS.assertTrue(PriceMatches,"R_79S");
//
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally 
//		{
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "sanity","Wsanity","react"})
//	public void React_Page_Price_Filter_on_L2_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l2category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
//		List<ProductDetailData> productlistwithPrizeFilter=new ArrayList<ProductDetailData>();
//		List<String> printList = new ArrayList<String>();
//		HeaderBar headerbar = new HeaderBar(browser);
//		ListingPage_React listingPage = new ListingPage_React(browser);
//		boolean PriceMatches=true;
//		boolean filtersCleared=false;
//		List<String> filtersClearedList=new ArrayList<String>();
//		try 
//		{
//			ExtentTest childTest1 = testListener.startChild("Navigate to React Page test ");
//			childTest1.setDescription("This test verifies the user is able to navigate to a react page");
//			TestListner.extentMap.get().put("child1", childTest1);
//			int count=1;
//			browser.get(environmentURL);
//			headerbar.navigateToCategory(headerbarData);
//			listingPage.navigateToShopAll("shop-all");
//			ArrayList<String> allOptions=new ArrayList<String>();
//			allOptions=listingPage.getFilterOptionsList("Price");
//			String randomForMultipleFilters=new String();
//			HashSet<Integer> indexes=framework.randomNumbers(allOptions.size());
//
//			for(int i:indexes)
//			{
//				if(count<=2) 
//				{
//					randomForMultipleFilters=randomForMultipleFilters.concat("----"+allOptions.get(i-1).split("\\(")[0].trim());
//				}
//				count+=1;
//			}
//			randomForMultipleFilters=randomForMultipleFilters.replaceFirst("----", "");
//
//			for(String val:allOptions)
//			{
//				val=val.split("\\(")[0].trim();
//				printList.clear();
//				productListPriceFilter.clear();
//				productlistwithPrizeFilter.clear();
//				int lowerVal;
//				int upperVal;
//				if(!val.contains("Above"))
//				{
//					lowerVal=Integer.parseInt((val.split("-")[0]).replaceAll("Rs. ", "").trim());
//					upperVal=Integer.parseInt((val.split("-")[1]).replaceAll("Rs. ", "").trim());
//				}
//				else
//				{
//					lowerVal=Integer.parseInt((val.split("&")[0]).replaceAll("Rs. ", "").trim());
//					upperVal=999999;
//				}
//
//				ExtentTest childTest = testListener.startChild("Filter by Price "+ val+" test ");
//				childTest.setDescription("This test verifies the user is able to apply filter by Price "+ val+" on react page");
//				TestListner.extentMap.get().put("child".concat(val), childTest);
//				if(val.equals(allOptions.get(allOptions.size()-1).split("\\(")[0].trim()))
//				{
//					productListPriceFilter = listingPage.ApplyFilter("Price", val,true,true);
//				}
//				else
//				{
//					productListPriceFilter = listingPage.ApplyFilter("Price", val,false,true);
//				}
//
//				productlistwithPrizeFilter = productListPriceFilter.stream().filter(o -> Integer.parseInt(o.getProductPrize()) <= upperVal && Integer.parseInt(o.getProductPrize()) >= lowerVal).collect(Collectors.toList());
//				for(int i=0;i<productListPriceFilter.size();i++)
//				{
//					printList.add(productListPriceFilter.get(i).getProductPrize());
//					if(! (Integer.parseInt(productListPriceFilter.get(i).getProductPrize())>=lowerVal && Integer.parseInt (productListPriceFilter.get(i).getProductPrize())<=upperVal))
//					{
//						PriceMatches=false;
//						TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductPrize()+" :: Expected: "+val);
//					}
//				}
//				TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Price "+ val+": "+printList.toString());
//				SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter,"Price filterTest for "+val);
//				filtersCleared=listingPage.clearAllFilters();
//				filtersClearedList.add(filtersCleared?"Filters cleared for"+val :"Filters not cleared for"+val );
//			}
//			SanAssertCS.assertTrue(PriceMatches,"R_80Q");
//
//			ExtentTest childTestFilter = testListener.startChild("Clear All Filters test");
//			childTestFilter.setDescription("This test verifies the user is able to clear applied filters on react page");
//			TestListner.extentMap.get().put("childTestFilter", childTestFilter);
//			TestListner.testing.get().log(LogStatus.INFO, "Clear Filter status: "+filtersClearedList.toString());
//			SanAssertCS.assertTrue(! filtersClearedList.toString().contains("Filters not cleared"),"R_80V_1");
//
//			ExtentTest childTest6 = testListener.startChild("Filter by Price "+randomForMultipleFilters.replace("----", " and ")+"  test ");
//			childTest6.setDescription("This test verifies the user is able to apply filter by Price "+randomForMultipleFilters.replace("----", " and ")+" on react page");
//			TestListner.extentMap.get().put("child6", childTest6);
//			productListPriceFilter.clear();
//			productlistwithPrizeFilter.clear();
//			printList.clear();
//			int lowerVal[]=new int[2];
//			int upperVal[] =new int[2];
//			String vals[]=randomForMultipleFilters.split("----");
//			try
//			{
//				for(int i=0;i<vals.length;i++)
//				{
//					if(!vals[i].contains("Above"))
//					{
//						System.out.println((vals[i].split("-")[0]).trim());
//						if((vals[i].split("-")[0]).trim().equals("Rs. 0"))
//						{
//							lowerVal[i]=0;
//						}
//						else
//						{
//							lowerVal[i]=Integer.parseInt((vals[i].split("-")[0]).replaceAll("Rs. ", "").trim());
//						}
//
//						upperVal[i]=Integer.parseInt((vals[i].split("-")[1]).replaceAll("Rs. ", "").trim());
//					}
//					else
//					{
//						lowerVal[i]=Integer.parseInt((vals[i].split("&")[0]).replaceAll("Rs. ", "").trim());
//						upperVal[i]=999999;
//					}
//				}
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//			productListPriceFilter = listingPage.ApplyFilter("Price", randomForMultipleFilters,false,true);
//			productlistwithPrizeFilter = productListPriceFilter.stream()
//					.filter(o -> (Integer.parseInt(o.getProductPrize()) < upperVal[0] && Integer.parseInt(o.getProductPrize()) > lowerVal[0])||(Integer.parseInt(o.getProductPrize()) < upperVal[1] && Integer.parseInt(o.getProductPrize()) > lowerVal[1])).collect(Collectors.toList());
//			for(int i=0;i<productlistwithPrizeFilter.size();i++)
//			{
//				printList.add(productlistwithPrizeFilter.get(i).getProductPrize());
//				if(! ((Integer.parseInt(productListPriceFilter.get(i).getProductPrize())>=lowerVal[0] && Integer.parseInt (productListPriceFilter.get(i).getProductPrize())<=upperVal[0])||(Integer.parseInt(productListPriceFilter.get(i).getProductPrize())>=lowerVal[1] && Integer.parseInt (productListPriceFilter.get(i).getProductPrize())<=upperVal[1])))
//				{
//					PriceMatches=false;
//					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductPrize()+" :: Expected: "+randomForMultipleFilters.replace("----", " and "));
//				}
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Price "+randomForMultipleFilters.replace("----", " and ")+" : "+printList.toString());
//			//SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "R_79S");
//			SanAssertCS.assertTrue(PriceMatches,"R_80S");
//
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally 
//		{
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "sanity","Wsanity","react","LiveSanity"})
//	public void React_Page_Price_Filter_on_L3_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l3category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
//		List<ProductDetailData> productlistwithPrizeFilter=new ArrayList<ProductDetailData>();
//		List<String> printList = new ArrayList<String>();
//		HeaderBar headerbar = new HeaderBar(browser);
//		ListingPage_React listingPage = new ListingPage_React(browser);
//		boolean PriceMatches=true;
//		boolean filtersCleared=false;
//		List<String> filtersClearedList=new ArrayList<String>();
//		try 
//		{
//			ExtentTest childTest1 = testListener.startChild("Navigate to React Page test ");
//			childTest1.setDescription("This test verifies the user is able to navigate to a react page");
//			TestListner.extentMap.get().put("child1", childTest1);
//			int count=1;
//			browser.get(environmentURL);
//			headerbar.navigateToCategory(headerbarData);
//			listingPage.navigateToShopAll("shop-all");
//			ArrayList<String> allOptions=new ArrayList<String>();
//			allOptions=listingPage.getFilterOptionsList("Price");
//			String randomForMultipleFilters=new String();
//			HashSet<Integer> indexes=framework.randomNumbers(allOptions.size());
//
//			for(int i:indexes)
//			{
//				if(count<=2) 
//				{
//					randomForMultipleFilters=randomForMultipleFilters.concat("----"+allOptions.get(i-1).split("\\(")[0].trim());
//				}
//				count+=1;
//			}
//			randomForMultipleFilters=randomForMultipleFilters.replaceFirst("----", "");
//
//			for(String val:allOptions)
//			{
//				val=val.split("\\(")[0].trim();
//				printList.clear();
//				productListPriceFilter.clear();
//				productlistwithPrizeFilter.clear();
//				int lowerVal;
//				int upperVal;
//				if(!val.contains("Above"))
//				{
//					lowerVal=Integer.parseInt((val.split("-")[0]).replaceAll("Rs. ", "").trim());
//					upperVal=Integer.parseInt((val.split("-")[1]).replaceAll("Rs. ", "").trim());
//				}
//				else
//				{
//					lowerVal=Integer.parseInt((val.split("&")[0]).replaceAll("Rs. ", "").trim());
//					upperVal=999999;
//				}
//
//				ExtentTest childTest = testListener.startChild("Filter by Price "+ val+" test ");
//				childTest.setDescription("This test verifies the user is able to apply filter by Price "+ val+" on react page");
//				TestListner.extentMap.get().put("child".concat(val), childTest);
//				if(val.equals(allOptions.get(allOptions.size()-1).split("\\(")[0].trim()))
//				{
//					productListPriceFilter = listingPage.ApplyFilter("Price", val,true,true);
//				}
//				else
//				{
//					productListPriceFilter = listingPage.ApplyFilter("Price", val,false,true);
//				}
//
//				productlistwithPrizeFilter = productListPriceFilter.stream().filter(o -> Integer.parseInt(o.getProductPrize()) <= upperVal && Integer.parseInt(o.getProductPrize()) >= lowerVal).collect(Collectors.toList());
//				for(int i=0;i<productListPriceFilter.size();i++)
//				{
//					printList.add(productListPriceFilter.get(i).getProductPrize());
//					if(! (Integer.parseInt(productListPriceFilter.get(i).getProductPrize())>=lowerVal && Integer.parseInt (productListPriceFilter.get(i).getProductPrize())<=upperVal))
//					{
//						PriceMatches=false;
//						TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductPrize()+" :: Expected: "+val);
//					}
//				}
//				TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Price "+ val+": "+printList.toString());
//				SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter,"Price filterTest for "+val);
//				filtersCleared=listingPage.clearAllFilters();
//				filtersClearedList.add(filtersCleared?"Filters cleared for"+val :"Filters not cleared for"+val );
//			}
//			SanAssertCS.assertTrue(PriceMatches,"R_81Q");
//
//			ExtentTest childTestFilter = testListener.startChild("Clear All Filters test");
//			childTestFilter.setDescription("This test verifies the user is able to clear applied filters on react page");
//			TestListner.extentMap.get().put("childTestFilter", childTestFilter);
//			TestListner.testing.get().log(LogStatus.INFO, "Clear Filter status: "+filtersClearedList.toString());
//			SanAssertCS.assertTrue(! filtersClearedList.toString().contains("Filters not cleared"),"R_81V_1");
//
//			ExtentTest childTest6 = testListener.startChild("Filter by Price "+randomForMultipleFilters.replace("----", " and ")+"  test ");
//			childTest6.setDescription("This test verifies the user is able to apply filter by Price "+randomForMultipleFilters.replace("----", " and ")+" on react page");
//			TestListner.extentMap.get().put("child6", childTest6);
//			productListPriceFilter.clear();
//			productlistwithPrizeFilter.clear();
//			printList.clear();
//			int lowerVal[]=new int[2];
//			int upperVal[] =new int[2];
//			String vals[]=randomForMultipleFilters.split("----");
//			try
//			{
//				for(int i=0;i<vals.length;i++)
//				{
//					if(!vals[i].contains("Above"))
//					{
//						System.out.println((vals[i].split("-")[0]).trim());
//						if((vals[i].split("-")[0]).trim().equals("Rs. 0"))
//						{
//							lowerVal[i]=0;
//						}
//						else
//						{
//							lowerVal[i]=Integer.parseInt((vals[i].split("-")[0]).replaceAll("Rs. ", "").trim());
//						}
//
//						upperVal[i]=Integer.parseInt((vals[i].split("-")[1]).replaceAll("Rs. ", "").trim());
//					}
//					else
//					{
//						lowerVal[i]=Integer.parseInt((vals[i].split("&")[0]).replaceAll("Rs. ", "").trim());
//						upperVal[i]=999999;
//					}
//				}
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//			productListPriceFilter = listingPage.ApplyFilter("Price", randomForMultipleFilters,false,true);
//			productlistwithPrizeFilter = productListPriceFilter.stream()
//					.filter(o -> (Integer.parseInt(o.getProductPrize()) < upperVal[0] && Integer.parseInt(o.getProductPrize()) > lowerVal[0])||(Integer.parseInt(o.getProductPrize()) < upperVal[1] && Integer.parseInt(o.getProductPrize()) > lowerVal[1])).collect(Collectors.toList());
//			for(int i=0;i<productlistwithPrizeFilter.size();i++)
//			{
//				printList.add(productlistwithPrizeFilter.get(i).getProductPrize());
//				if(! ((Integer.parseInt(productListPriceFilter.get(i).getProductPrize())>=lowerVal[0] && Integer.parseInt (productListPriceFilter.get(i).getProductPrize())<=upperVal[0])||(Integer.parseInt(productListPriceFilter.get(i).getProductPrize())>=lowerVal[1] && Integer.parseInt (productListPriceFilter.get(i).getProductPrize())<=upperVal[1])))
//				{
//					PriceMatches=false;
//					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductPrize()+" :: Expected: "+randomForMultipleFilters.replace("----", " and "));
//				}
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Price "+randomForMultipleFilters.replace("----", " and ")+" : "+printList.toString());
//			//SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "R_79S");
//			SanAssertCS.assertTrue(PriceMatches,"R_81S");
//
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally 
//		{
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "sanity","Wsanity","react","brandfilter" })
//	public void React_Page_Brand_Filter_on_L1_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
//		List<String> printList = new ArrayList<String>();
//		HeaderBar headerbar = new HeaderBar(browser);
//		ListingPage_React listingPage = new ListingPage_React(browser);
//		try {
//			ExtentTest childTest1 = testListener.startChild("Navigate to React PLP test ");
//			childTest1.setDescription("This test verifies the user is able to navigate to react page");
//			TestListner.extentMap.get().put("childTest1", childTest1);
//			int count=1;
//			browser.get(environmentURL);
//			headerbar.navigateToCategory(headerbarData);
//			List<String> BrandNameMatches=new ArrayList<String>();
//			boolean BrandNameMatchesTemp=true;
//			boolean countMatches=false;
//			ArrayList<String> allOptions=new ArrayList<String>();
//			allOptions=listingPage.getFilterOptionsList("Brand");
//			String randomForMultipleFilters=new String();
//			ArrayList<String> filterValues=new ArrayList<String>();
//			HashSet<Integer> indexes=framework.randomNumbers(allOptions.size());
//			String brandToSearchByName=allOptions.get(allOptions.size()-1);
//
//			for(int i:indexes)
//			{
//
//				filterValues.add(allOptions.get(i-1).split("\\(")[0].trim());
//				if(count<=3) 
//				{
//					randomForMultipleFilters=randomForMultipleFilters.concat("----"+allOptions.get(i-1).split("\\(")[0].trim());
//				}
//				count+=1;
//			}
//			randomForMultipleFilters=randomForMultipleFilters.replaceFirst("----", "");
//			for(String val:filterValues)
//			{
//				BrandNameMatchesTemp=true;
//				printList.clear();
//				productListPriceFilter.clear();
//				ExtentTest childTest = testListener.startChild("Filter by Brand "+ val+" test ");
//				childTest.setDescription("This test verifies the user is able to apply filter by Brand "+ val+" on react page");
//				TestListner.extentMap.get().put("child".concat(val), childTest);
//
//				productListPriceFilter = listingPage.ApplyFilter("Brand", val,false,true);
//				for(int i=0;i<productListPriceFilter.size();i++)
//				{
//					val=val.replace("OGX", "Organix");
//					printList.add(productListPriceFilter.get(i).getProductName());
//					if(val.equalsIgnoreCase("organix"))
//					{
//						if(! (productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(val.split(" ")[0].replace("'", "").toLowerCase().trim())|| (productListPriceFilter.get(i).getProductName().split("'")[0].toLowerCase().contains("ogx"))))
//						{
//							BrandNameMatchesTemp=false;
//							TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+val);
//						}
//					}
//					else if(! productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(val.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim()))
//					{
//						BrandNameMatchesTemp=false;
//						TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+val);
//					}
//
//				}
//				TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Brand "+ val+" : "+printList.toString());
//				TestListner.testing.get().log(LogStatus.INFO,"Brand match list :: "+BrandNameMatches.toString());
//				SanAssertCS.assertTrue(BrandNameMatchesTemp,"Brand Filter test for: "+val);
//				BrandNameMatches.add(BrandNameMatchesTemp?"Brand matches for "+val :"Brand does not match for "+val );
//			}
//			SanAssertCS.assertTrue(! BrandNameMatches.contains("not"), "R_79T");
//
//			ExtentTest childTest = testListener.startChild("Filter by Brand "+ randomForMultipleFilters.replaceAll("----", " and ")+" test ");
//			childTest.setDescription("This test verifies the user is able to apply filter by Brand "+ randomForMultipleFilters.replaceAll("----", " and ")+" on react page");
//			TestListner.extentMap.get().put("childTest", childTest);
//
//			BrandNameMatchesTemp=false;
//			printList.clear();
//			productListPriceFilter.clear();
//			BrandNameMatches.clear();
//			productListPriceFilter = listingPage.ApplyFilter("Brand", randomForMultipleFilters,true,true);
//			for(int i=0;i<productListPriceFilter.size();i++)
//			{
//				randomForMultipleFilters=randomForMultipleFilters.replace("OGX", "Organix");
//				printList.add(productListPriceFilter.get(i).getProductName());
//				String[] vals=randomForMultipleFilters.split("----");
//				for(String value:vals) {
//
//					if(value.equalsIgnoreCase("organix"))
//					{
//						if((productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(value.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim())|| (productListPriceFilter.get(i).getProductName().split("'")[0].toLowerCase().contains("ogx"))))
//						{
//							BrandNameMatchesTemp=true;
//							break;
//						}
//					}
//					else if( productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(value.split(" ")[0].replace("'", "").toLowerCase().trim()))
//					{
//						BrandNameMatchesTemp=true;
//						countMatches=true;
//						break;
//					}
//				}
//				if(!BrandNameMatchesTemp)
//				{
//					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+randomForMultipleFilters.replaceAll("----", " and "));
//				}
//				BrandNameMatches.add(BrandNameMatchesTemp?"Brand matches for"+randomForMultipleFilters.replaceAll("----", " and ") :"Brand does not match for"+randomForMultipleFilters.replaceAll("----", " and ") );
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Brand match list :: "+BrandNameMatches.toString());
//			SanAssertCS.assertTrue(countMatches && ! BrandNameMatches.contains("Brand does not match"), "R_79U");
//
//			ExtentTest childTest3 = testListener.startChild("Filter by Brand search :"+brandToSearchByName + " test ");
//			childTest3.setDescription("This test verifies the user is able to search and apply brand filter by brand nam ");
//			TestListner.extentMap.get().put("childTest3", childTest3);
//
//			BrandNameMatchesTemp=true;
//			printList.clear();
//			productListPriceFilter.clear();
//			BrandNameMatches.clear();
//			productListPriceFilter = listingPage.ApplyFilter("Brand", brandToSearchByName+"searchBrand",false,true);
//			for(int i=0;i<productListPriceFilter.size();i++)
//			{
//				brandToSearchByName=brandToSearchByName.replace("OGX", "Organix");
//				printList.add(productListPriceFilter.get(i).getProductName());
//				if(brandToSearchByName.equalsIgnoreCase("organix"))
//				{
//					if(! (productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(brandToSearchByName)|| (productListPriceFilter.get(i).getProductName().split("'")[0].toLowerCase().contains("ogx"))))
//					{
//						BrandNameMatchesTemp=false;
//						TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+brandToSearchByName);
//					}
//				}
//				else if(! productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(brandToSearchByName.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim()))
//				{
//					BrandNameMatchesTemp=false;
//					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+brandToSearchByName);
//				}
//
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after search and filter by Brand "+ brandToSearchByName+" : "+printList.toString());
//			SanAssertCS.assertTrue(BrandNameMatchesTemp,"R_79V");
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally 
//		{
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "sanity","Wsanity","react","react" })
//	public void React_Page_Brand_Filter_on_L2_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l2category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
//		List<String> printList = new ArrayList<String>();
//		HeaderBar headerbar = new HeaderBar(browser);
//		ListingPage_React listingPage = new ListingPage_React(browser);
//		try {
//			ExtentTest childTest1 = testListener.startChild("Navigate to React PLP test ");
//			childTest1.setDescription("This test verifies the user is able to navigate to react page");
//			TestListner.extentMap.get().put("childTest1", childTest1);
//			int count=1;
//			browser.get(environmentURL);
//			headerbar.navigateToCategory(headerbarData);
//			listingPage.navigateToShopAll("shop-all");
//			List<String> BrandNameMatches=new ArrayList<String>();
//			boolean BrandNameMatchesTemp=true;
//			boolean countMatches=false;
//			ArrayList<String> allOptions=new ArrayList<String>();
//			allOptions=listingPage.getFilterOptionsList("Brand");
//			String randomForMultipleFilters=new String();
//			ArrayList<String> filterValues=new ArrayList<String>();
//			HashSet<Integer> indexes=framework.randomNumbers(allOptions.size());
//			String brandToSearchByName=allOptions.get(allOptions.size()-1);
//
//			for(int i:indexes)
//			{
//
//				filterValues.add(allOptions.get(i-1).split("\\(")[0].trim());
//				if(count<=3) 
//				{
//					randomForMultipleFilters=randomForMultipleFilters.concat("----"+allOptions.get(i-1).split("\\(")[0].trim());
//				}
//				count+=1;
//			}
//			randomForMultipleFilters=randomForMultipleFilters.replaceFirst("----", "");
//			for(String val:filterValues)
//			{
//				BrandNameMatchesTemp=true;
//				printList.clear();
//				productListPriceFilter.clear();
//				ExtentTest childTest = testListener.startChild("Filter by Brand "+ val+" test ");
//				childTest.setDescription("This test verifies the user is able to apply filter by Brand "+ val+" on react page");
//				TestListner.extentMap.get().put("child".concat(val), childTest);
//
//				productListPriceFilter = listingPage.ApplyFilter("Brand", val,false,true);
//				for(int i=0;i<productListPriceFilter.size();i++)
//				{
//					val=val.replace("OGX", "Organix");
//					printList.add(productListPriceFilter.get(i).getProductName());
//					if(val.equalsIgnoreCase("organix"))
//					{
//						if(! (productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(val.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim())|| (productListPriceFilter.get(i).getProductName().split("'")[0].toLowerCase().contains("ogx"))))
//						{
//							BrandNameMatchesTemp=false;
//							TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+val);
//						}
//					}
//					else if(! productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(val.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim()))
//					{
//						BrandNameMatchesTemp=false;
//						TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+val);
//					}
//
//				}
//				TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Brand "+ val+": "+printList.toString());
//				TestListner.testing.get().log(LogStatus.INFO,"Brand match list :: "+BrandNameMatches.toString());
//				SanAssertCS.assertTrue(BrandNameMatchesTemp,"Brand Filter test for: "+val);
//				BrandNameMatches.add(BrandNameMatchesTemp?"Brand matches for"+val :"Brand does not match for"+val );
//			}
//			SanAssertCS.assertTrue(! BrandNameMatches.contains("Brand does not match"), "R_80T");
//
//			ExtentTest childTest = testListener.startChild("Filter by Brand "+ randomForMultipleFilters.replaceAll("----", " and ")+" test ");
//			childTest.setDescription("This test verifies the user is able to apply filter by Brand "+ randomForMultipleFilters.replaceAll("----", " and ")+" on react page");
//			TestListner.extentMap.get().put("childTest", childTest);
//
//			BrandNameMatchesTemp=false;
//			printList.clear();
//			productListPriceFilter.clear();
//			BrandNameMatches.clear();
//			productListPriceFilter = listingPage.ApplyFilter("Brand", randomForMultipleFilters,true,true);
//			for(int i=0;i<productListPriceFilter.size();i++)
//			{
//				randomForMultipleFilters=randomForMultipleFilters.replace("OGX", "Organix");
//				printList.add(productListPriceFilter.get(i).getProductName());
//				String[] vals=randomForMultipleFilters.split("----");
//				for(String value:vals) {
//
//					if(value.equalsIgnoreCase("organix"))
//					{
//						if((productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(value.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim())|| (productListPriceFilter.get(i).getProductName().split("'")[0].toLowerCase().contains("ogx"))))
//						{
//							BrandNameMatchesTemp=true;
//							break;
//						}
//					}
//					else if( productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(value.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim()))
//					{
//						BrandNameMatchesTemp=true;
//						countMatches=true;
//						break;
//					}
//				}
//				if(!BrandNameMatchesTemp)
//				{
//					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+randomForMultipleFilters.replaceAll("----", " and "));
//				}
//				BrandNameMatches.add(BrandNameMatchesTemp?"Brand matches for"+randomForMultipleFilters.replaceAll("----", " and ") :"Brand does not match for"+randomForMultipleFilters.replaceAll("----", " and ") );				
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Brand match list :: "+BrandNameMatches.toString());
//			SanAssertCS.assertTrue(countMatches && ! BrandNameMatches.contains("Brand does not match"), "R_80U");
//
//			ExtentTest childTest3 = testListener.startChild("Filter by Brand search :"+brandToSearchByName + " test ");
//			childTest3.setDescription("This test verifies the user is able to search and apply brand filter by brand nam ");
//			TestListner.extentMap.get().put("childTest3", childTest3);
//
//			BrandNameMatchesTemp=true;
//			printList.clear();
//			productListPriceFilter.clear();
//			BrandNameMatches.clear();
//			productListPriceFilter = listingPage.ApplyFilter("Brand", brandToSearchByName+"searchBrand",false,true);
//			for(int i=0;i<productListPriceFilter.size();i++)
//			{
//				brandToSearchByName=brandToSearchByName.replace("OGX", "Organix");
//				printList.add(productListPriceFilter.get(i).getProductName());
//				if(brandToSearchByName.equalsIgnoreCase("organix"))
//				{
//					if(! (productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(brandToSearchByName)|| (productListPriceFilter.get(i).getProductName().split("'")[0].toLowerCase().contains("ogx"))))
//					{
//						BrandNameMatchesTemp=false;
//						TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+brandToSearchByName);
//					}
//				}
//				else if(! productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(brandToSearchByName.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim()))
//				{
//					BrandNameMatchesTemp=false;
//					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+brandToSearchByName);
//				}
//
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after search and filter by Brand "+ brandToSearchByName+" : "+printList.toString());
//			SanAssertCS.assertTrue(BrandNameMatchesTemp,"R_80V");
//
//
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally 
//		{
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "sanity","Wsanity","react","LiveSanity" })
//	public void React_Page_Brand_Filter_on_L3_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l3category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
//		List<String> printList = new ArrayList<String>();
//		HeaderBar headerbar = new HeaderBar(browser);
//		ListingPage_React listingPage = new ListingPage_React(browser);
//		try {
//			ExtentTest childTest1 = testListener.startChild("Navigate to React PLP test ");
//			childTest1.setDescription("This test verifies the user is able to navigate to react page");
//			TestListner.extentMap.get().put("childTest1", childTest1);
//			int count=1;
//			browser.get(environmentURL);
//			headerbar.navigateToCategory(headerbarData);
//			listingPage.navigateToShopAll("shop-all");
//			List<String> BrandNameMatches=new ArrayList<String>();
//			boolean BrandNameMatchesTemp=true;
//			boolean countMatches=false;
//			ArrayList<String> allOptions=new ArrayList<String>();
//			allOptions=listingPage.getFilterOptionsList("Brand");
//			String randomForMultipleFilters=new String();
//			ArrayList<String> filterValues=new ArrayList<String>();
//			HashSet<Integer> indexes=framework.randomNumbers(allOptions.size());
//			String brandToSearchByName=allOptions.get(allOptions.size()-1);
//
//			for(int i:indexes)
//			{
//
//				filterValues.add(allOptions.get(i-1).split("\\(")[0].trim());
//				if(count<=3) 
//				{
//					randomForMultipleFilters=randomForMultipleFilters.concat("----"+allOptions.get(i-1).split("\\(")[0].trim());
//				}
//				count+=1;
//			}
//			randomForMultipleFilters=randomForMultipleFilters.replaceFirst("----", "");
//			for(String val:filterValues)
//			{
//				BrandNameMatchesTemp=true;
//				printList.clear();
//				productListPriceFilter.clear();
//				ExtentTest childTest = testListener.startChild("Filter by Brand "+ val+" test ");
//				childTest.setDescription("This test verifies the user is able to apply filter by Brand "+ val+" on react page");
//				TestListner.extentMap.get().put("child".concat(val), childTest);
//
//				productListPriceFilter = listingPage.ApplyFilter("Brand", val,false,true);
//				for(int i=0;i<productListPriceFilter.size();i++)
//				{
//					val=val.replace("OGX", "Organix");
//					printList.add(productListPriceFilter.get(i).getProductName());
//					if(val.equalsIgnoreCase("organix"))
//					{
//						if(! (productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(val.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim())|| (productListPriceFilter.get(i).getProductName().split("'")[0].toLowerCase().contains("ogx"))))
//						{
//							BrandNameMatchesTemp=false;
//							TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+val);
//						}
//					}
//					else if(! productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(val.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim()))
//					{
//						BrandNameMatchesTemp=false;
//						TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+val);
//					}
//
//				}
//				TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Brand "+ val+": "+printList.toString());
//				TestListner.testing.get().log(LogStatus.INFO,"Brand match list :: "+BrandNameMatches.toString());
//				SanAssertCS.assertTrue(BrandNameMatchesTemp,"Brand Filter test for: "+val);
//				BrandNameMatches.add(BrandNameMatchesTemp?"Brand matches for"+val :"Brand does not match for"+val );
//			}
//			SanAssertCS.assertTrue(! BrandNameMatches.contains("Brand does not match"), "R_81T");
//
//			ExtentTest childTest = testListener.startChild("Filter by Brand "+ randomForMultipleFilters.replaceAll("----", " and ")+" test ");
//			childTest.setDescription("This test verifies the user is able to apply filter by Brand "+ randomForMultipleFilters.replaceAll("----", " and ")+" on react page");
//			TestListner.extentMap.get().put("childTest", childTest);
//
//			BrandNameMatchesTemp=false;
//			printList.clear();
//			productListPriceFilter.clear();
//			BrandNameMatches.clear();
//			productListPriceFilter = listingPage.ApplyFilter("Brand", randomForMultipleFilters,true,true);
//			for(int i=0;i<productListPriceFilter.size();i++)
//			{
//				randomForMultipleFilters=randomForMultipleFilters.replace("OGX", "Organix");
//				printList.add(productListPriceFilter.get(i).getProductName());
//				String[] vals=randomForMultipleFilters.split("----");
//				for(String value:vals) {
//
//					if(value.equalsIgnoreCase("organix"))
//					{
//						if((productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(value.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim())|| (productListPriceFilter.get(i).getProductName().split("'")[0].toLowerCase().contains("ogx"))))
//						{
//							BrandNameMatchesTemp=true;
//							break;
//						}
//					}
//					else if( productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(value.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim()))
//					{
//						BrandNameMatchesTemp=true;
//						countMatches=true;
//						break;
//					}
//				}
//				if(!BrandNameMatchesTemp)
//				{
//					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+randomForMultipleFilters.replaceAll("----", " and "));
//				}
//				BrandNameMatches.add(BrandNameMatchesTemp?"Brand matches for"+randomForMultipleFilters.replaceAll("----", " and ") :"Brand does not match for"+randomForMultipleFilters.replaceAll("----", " and ") );				
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Brand match list :: "+BrandNameMatches.toString());
//			SanAssertCS.assertTrue(countMatches && ! BrandNameMatches.contains("Brand does not match"), "R_81U");
//
//			ExtentTest childTest3 = testListener.startChild("Filter by Brand search :"+brandToSearchByName + " test ");
//			childTest3.setDescription("This test verifies the user is able to search and apply brand filter by brand nam ");
//			TestListner.extentMap.get().put("childTest3", childTest3);
//
//			BrandNameMatchesTemp=true;
//			printList.clear();
//			productListPriceFilter.clear();
//			BrandNameMatches.clear();
//			productListPriceFilter = listingPage.ApplyFilter("Brand", brandToSearchByName+"searchBrand",false,true);
//			for(int i=0;i<productListPriceFilter.size();i++)
//			{
//				brandToSearchByName=brandToSearchByName.replace("OGX", "Organix");
//				printList.add(productListPriceFilter.get(i).getProductName());
//				if(brandToSearchByName.equalsIgnoreCase("organix"))
//				{
//					if(! (productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(brandToSearchByName)|| (productListPriceFilter.get(i).getProductName().split("'")[0].toLowerCase().contains("ogx"))))
//					{
//						BrandNameMatchesTemp=false;
//						TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+brandToSearchByName);
//					}
//				}
//				else if(! productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(brandToSearchByName.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim()))
//				{
//					BrandNameMatchesTemp=false;
//					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+brandToSearchByName);
//				}
//
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after search and filter by Brand "+ brandToSearchByName+" : "+printList.toString());
//			SanAssertCS.assertTrue(BrandNameMatchesTemp,"R_81V");
//
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally 
//		{
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "sanity","Wsanity" ,"react","LiveSanity"})
//	public void React_Page_Discount_Filter_on_L1_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
//		List<ProductDetailData> productlistwithPrizeFilter=new ArrayList<ProductDetailData>();
//		List<String> printList = new ArrayList<String>();
//		HeaderBar headerbar = new HeaderBar(browser);
//		ListingPage_React listingPage = new ListingPage_React(browser);
//		boolean PriceMatches=true;
//		boolean filtersCleared=false;
//		List<String> filtersClearedList=new ArrayList<String>();
//		List<String> priceMatchedList=new ArrayList<String>();
//		try 
//		{
//			ExtentTest childTest1 = testListener.startChild("Navigate to React Page test ");
//			childTest1.setDescription("This test verifies the user is able to navigate to a react page");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			headerbar.navigateToCategory(headerbarData);
//
//			ArrayList<String> allOptions=new ArrayList<String>();
//			allOptions=listingPage.getFilterOptionsList("Discount");
//
//			for(String val:allOptions)
//			{
//				String tempVal=val.split("\\(")[0].trim();
//				printList.clear();
//				productListPriceFilter.clear();
//				productlistwithPrizeFilter.clear();
//				if(tempVal.contains("Upto"))
//				{
//					//commenting less than 10 % discount case as it has null discount values as well which takes too long to find the element(due to timeout) as those locators are not present
//					//tempVal=tempVal.replaceAll("Upto ", "<").replaceAll("%", "").trim();
//					continue;
//				}
//				else
//				{
//					tempVal=tempVal.replaceAll("and above",">").replaceAll("%", "").trim();
//				}
//				String value=tempVal;
//				ExtentTest childTest = testListener.startChild("Filter by Discount "+val+" test ");
//				childTest.setDescription("This test verifies the user is able to apply filter by Brand "+ val+" on react page");
//				TestListner.extentMap.get().put("child".concat(val), childTest);
//				if(val.equals(allOptions.get(allOptions.size()-1).trim()))
//				{
//					productListPriceFilter = listingPage.ApplyFilter("Discount", val,true,true);
//				}
//				else
//				{
//					productListPriceFilter = listingPage.ApplyFilter("Discount", val,false,true);
//				}
//				if(value.contains("<"))
//				{
//					for(int i=0;i<productListPriceFilter.size();i++)
//					{
//						printList.add(productListPriceFilter.get(i).getDiscount());
//						if(! (Integer.parseInt(productListPriceFilter.get(i).getDiscount())<=Integer.parseInt(val.replaceAll("<", "").trim()))) 
//						{
//							PriceMatches=false;
//							TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getDiscount()+" :: Expected: "+tempVal);
//						}
//					}
//				}
//				else
//				{
//					for(int i=0;i<productListPriceFilter.size();i++)
//					{
//						printList.add(productListPriceFilter.get(i).getDiscount());
//						if(! (Integer.parseInt(productListPriceFilter.get(i).getDiscount())>=Integer.parseInt(value.replaceAll(">", "").trim()))) 
//						{
//							PriceMatches=false;
//							TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getDiscount()+" :: Expected: "+tempVal);
//						}
//					}
//				}
//
//				TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Discount "+ value.replaceAll(">"," and above ")+": "+printList.toString());
//				priceMatchedList.add(PriceMatches?"Discount matches for"+val :"Discount does not match for"+val );
//				SanAssertCS.assertTrue(PriceMatches,"Discount filterTest for "+val);
//				filtersCleared=listingPage.clearAllFilters();
//				filtersClearedList.add(filtersCleared?"Filters cleared for"+val :"Filters not cleared for"+val );
//			}
//			SanAssertCS.assertTrue(! priceMatchedList.toString().contains("Discount does not match"),"R_79X");
//
//			ExtentTest childTestFilter = testListener.startChild("Clear All Filters test");
//			childTestFilter.setDescription("This test verifies the user is able to clear applied filters on react page");
//			TestListner.extentMap.get().put("childTestFilter", childTestFilter);
//			TestListner.testing.get().log(LogStatus.INFO, "Clear Filter status: "+filtersClearedList.toString());
//			SanAssertCS.assertTrue(! filtersClearedList.toString().contains("Filters not cleared"),"R_79V_1");
//
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally 
//		{
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "sanity","Wsanity" ,"react"})
//	public void React_Page_Discount_Filter_on_L2_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l2category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
//		List<ProductDetailData> productlistwithPrizeFilter=new ArrayList<ProductDetailData>();
//		List<String> printList = new ArrayList<String>();
//		HeaderBar headerbar = new HeaderBar(browser);
//		ListingPage_React listingPage = new ListingPage_React(browser);
//		boolean PriceMatches=true;
//		boolean filtersCleared=false;
//		List<String> filtersClearedList=new ArrayList<String>();
//		List<String> priceMatchedList=new ArrayList<String>();
//		try 
//		{
//			ExtentTest childTest1 = testListener.startChild("Navigate to React Page test ");
//			childTest1.setDescription("This test verifies the user is able to navigate to a react page");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			headerbar.navigateToCategory(headerbarData);
//			listingPage.navigateToShopAll("shop-all");
//			ArrayList<String> allOptions=new ArrayList<String>();
//			allOptions=listingPage.getFilterOptionsList("Discount");
//
//			for(String val:allOptions)
//			{
//				String tempVal=val.split("\\(")[0].trim();
//				printList.clear();
//				productListPriceFilter.clear();
//				productlistwithPrizeFilter.clear();
//				if(tempVal.contains("Upto"))
//				{
//					//commenting less than 10 % discount case as it has null discount values as well which takes too long to find the element(due to timeout) as those locators are not present
//					//tempVal=tempVal.replaceAll("Upto ", "<").replaceAll("%", "").trim();
//					continue;
//				}
//				else
//				{
//					tempVal=tempVal.replaceAll("and above",">").replaceAll("%", "").trim();
//				}
//				String value=tempVal;
//				ExtentTest childTest = testListener.startChild("Filter by Discount "+val+" test ");
//				childTest.setDescription("This test verifies the user is able to apply filter by Brand "+ val+" on react page");
//				TestListner.extentMap.get().put("child".concat(val), childTest);
//				if(val.equals(allOptions.get(allOptions.size()-1).trim()))
//				{
//					productListPriceFilter = listingPage.ApplyFilter("Discount", val,true,true);
//				}
//				else
//				{
//					productListPriceFilter = listingPage.ApplyFilter("Discount", val,false,true);
//				}
//				if(value.contains("<"))
//				{
//					for(int i=0;i<productListPriceFilter.size();i++)
//					{
//						printList.add(productListPriceFilter.get(i).getDiscount());
//						if(! (Integer.parseInt(productListPriceFilter.get(i).getDiscount())<=Integer.parseInt(val.replaceAll("<", "").trim()))) 
//						{
//							PriceMatches=false;
//							TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getDiscount()+" :: Expected: "+tempVal);
//						}
//					}
//				}
//				else
//				{
//					for(int i=0;i<productListPriceFilter.size();i++)
//					{
//						printList.add(productListPriceFilter.get(i).getDiscount());
//						if(! (Integer.parseInt(productListPriceFilter.get(i).getDiscount())>=Integer.parseInt(value.replaceAll(">", "").trim()))) 
//						{
//							PriceMatches=false;
//							TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getDiscount()+" :: Expected: "+tempVal);
//						}
//					}
//				}
//
//				TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Discount "+ value.replaceAll(">"," and above ")+": "+printList.toString());
//				priceMatchedList.add(PriceMatches?"Discount matches for"+val :"Discount does not match for"+val );
//				SanAssertCS.assertTrue(PriceMatches,"Discount filterTest for "+val);
//				filtersCleared=listingPage.clearAllFilters();
//				filtersClearedList.add(filtersCleared?"Filters cleared for"+val :"Filters not cleared for"+val );
//			}
//			SanAssertCS.assertTrue(! priceMatchedList.toString().contains("Discount does not match"),"R_80X");
//
//			ExtentTest childTestFilter = testListener.startChild("Clear All Filters test");
//			childTestFilter.setDescription("This test verifies the user is able to clear applied filters on react page");
//			TestListner.extentMap.get().put("childTestFilter", childTestFilter);
//			TestListner.testing.get().log(LogStatus.INFO, "Clear Filter status: "+filtersClearedList.toString());
//			SanAssertCS.assertTrue(! filtersClearedList.toString().contains("Filters not cleared"),"R_80V_1");
//
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally 
//		{
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "sanity","Wsanity" ,"react"})
//	public void React_Page_Discount_Filter_on_L3_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l3category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
//		List<ProductDetailData> productlistwithPrizeFilter=new ArrayList<ProductDetailData>();
//		List<String> printList = new ArrayList<String>();
//		HeaderBar headerbar = new HeaderBar(browser);
//		ListingPage_React listingPage = new ListingPage_React(browser);
//		boolean PriceMatches=true;
//		boolean filtersCleared=false;
//		List<String> filtersClearedList=new ArrayList<String>();
//		List<String> priceMatchedList=new ArrayList<String>();
//		try 
//		{
//			ExtentTest childTest1 = testListener.startChild("Navigate to React Page test ");
//			childTest1.setDescription("This test verifies the user is able to navigate to a react page");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			headerbar.navigateToCategory(headerbarData);
//			listingPage.navigateToShopAll("shop-all");
//			ArrayList<String> allOptions=new ArrayList<String>();
//			allOptions=listingPage.getFilterOptionsList("Discount");
//
//			for(String val:allOptions)
//			{
//				String tempVal=val.split("\\(")[0].trim();
//				printList.clear();
//				productListPriceFilter.clear();
//				productlistwithPrizeFilter.clear();
//				if(tempVal.contains("Upto"))
//				{
//					//commenting less than 10 % discount case as it has null discount values as well which takes too long to find the element(due to timeout) as those locators are not present
//					//tempVal=tempVal.replaceAll("Upto ", "<").replaceAll("%", "").trim();
//					continue;
//				}
//				else
//				{
//					tempVal=tempVal.replaceAll("and above",">").replaceAll("%", "").trim();
//				}
//				String value=tempVal;
//				ExtentTest childTest = testListener.startChild("Filter by Discount "+val+" test ");
//				childTest.setDescription("This test verifies the user is able to apply filter by Brand "+ val+" on react page");
//				TestListner.extentMap.get().put("child".concat(val), childTest);
//				if(val.equals(allOptions.get(allOptions.size()-1).trim()))
//				{
//					productListPriceFilter = listingPage.ApplyFilter("Discount", val,true,true);
//				}
//				else
//				{
//					productListPriceFilter = listingPage.ApplyFilter("Discount", val,false,true);
//				}
//				if(value.contains("<"))
//				{
//					for(int i=0;i<productListPriceFilter.size();i++)
//					{
//						printList.add(productListPriceFilter.get(i).getDiscount());
//						if(! (Integer.parseInt(productListPriceFilter.get(i).getDiscount())<=Integer.parseInt(val.replaceAll("<", "").trim()))) 
//						{
//							PriceMatches=false;
//							TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getDiscount()+" :: Expected: "+tempVal);
//						}
//					}
//				}
//				else
//				{
//					for(int i=0;i<productListPriceFilter.size();i++)
//					{
//						printList.add(productListPriceFilter.get(i).getDiscount());
//						if(! (Integer.parseInt(productListPriceFilter.get(i).getDiscount())>=Integer.parseInt(value.replaceAll(">", "").trim()))) 
//						{
//							PriceMatches=false;
//							TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getDiscount()+" :: Expected: "+tempVal);
//						}
//					}
//				}
//
//				TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Discount "+ value.replaceAll(">"," and above ")+": "+printList.toString());
//				priceMatchedList.add(PriceMatches?"Discount matches for"+val :"Discount does not match for"+val );
//				SanAssertCS.assertTrue(PriceMatches,"Discount filterTest for "+val);
//				filtersCleared=listingPage.clearAllFilters();
//				filtersClearedList.add(filtersCleared?"Filters cleared for"+val :"Filters not cleared for"+val );
//			}
//			SanAssertCS.assertTrue(! priceMatchedList.toString().contains("Discount does not match"),"R_81X");
//
//			ExtentTest childTestFilter = testListener.startChild("Clear All Filters test");
//			childTestFilter.setDescription("This test verifies the user is able to clear applied filters on react page");
//			TestListner.extentMap.get().put("childTestFilter", childTestFilter);
//			TestListner.testing.get().log(LogStatus.INFO, "Clear Filter status: "+filtersClearedList.toString());
//			SanAssertCS.assertTrue(! filtersClearedList.toString().contains("Filters not cleared"),"R_81V_1");
//
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally 
//		{
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "sanity","Wsanity" ,"react"})
//	public void React_Page_Multiple_Filter_on_L1_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
//		List<String> printList = new ArrayList<String>();
//		HeaderBar headerbar = new HeaderBar(browser);
//		ListingPage_React listingPage = new ListingPage_React(browser);
//		boolean PriceMatches=true;
//		boolean filtersCleared=false;
//		try {
//			ExtentTest childTest1 = testListener.startChild("Navigate to React PLP test ");
//			childTest1.setDescription("This test verifies the user is able to navigate to react page");
//			TestListner.extentMap.get().put("childTest1", childTest1);
//			int count=1;
//			browser.get(environmentURL);
//			headerbar.navigateToCategory(headerbarData);
//			List<String> BrandNameMatches=new ArrayList<String>();
//			boolean BrandNameMatchesTemp=false;
//			ArrayList<String> allOptions=new ArrayList<String>();
//			allOptions=listingPage.getFilterOptionsList("Brand");
//			ArrayList<String> brandFilterValues=new ArrayList<String>();
//			HashSet<Integer> indexes=framework.randomNumbers(allOptions.size());
//			for(int i:indexes)
//			{
//				if(! allOptions.get(i-1).contains("OGX"))
//				{
//					if(count<=1) 
//					{
//						brandFilterValues.add(allOptions.get(i-1).split("\\(")[0].trim());
//					}
//					count+=1;
//				}
//			}
//
//			ExtentTest childTest = testListener.startChild("Filter by Brand and Price test ");
//			childTest.setDescription("This test verifies the user is able to apply multiple filters on react page");
//			TestListner.extentMap.get().put("childTest", childTest);
//			for(String val:brandFilterValues)
//			{
//				listingPage.ApplyFilter("Brand", val,false,true);
//			}
//
//			allOptions.clear();
//			allOptions=listingPage.getFilterOptionsList("Price");
//			String val=allOptions.get(0).split("\\(")[0].trim();
//			int lowerVal;
//			int upperVal;
//			if(!val.contains("Above"))
//			{
//				lowerVal=Integer.parseInt((val.split("-")[0]).replaceAll("Rs. ", "").trim());
//				upperVal=Integer.parseInt((val.split("-")[1]).replaceAll("Rs. ", "").trim());
//			}
//			else
//			{
//				lowerVal=Integer.parseInt((val.split("&")[0]).replaceAll("Rs. ", "").trim());
//				upperVal=999999;
//			}
//
//			productListPriceFilter = listingPage.ApplyFilter("Price", val,true,false);
//
//			for(int i=0;i<productListPriceFilter.size();i++)
//			{
//				printList.add(productListPriceFilter.get(i).getProductName()+" :: "+(productListPriceFilter.get(i).getProductPrize()));
//				String expected="";
//				String actual="";
//				for(String brand:brandFilterValues)
//				{
//					expected=brand.replace("OGX", "Organix");
//					actual=productListPriceFilter.get(i).getProductName().split("'")[0].toLowerCase();
//					if(actual.contains(expected.split("'")[0].toLowerCase()))
//					{
//						BrandNameMatchesTemp=true;
//					}
//				}
//				BrandNameMatches.add(BrandNameMatchesTemp?"true":"false");
//				if(! BrandNameMatchesTemp)
//				{
//					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+actual+" :: Expected: "+expected);
//				}
//
//				if(!(Integer.parseInt(productListPriceFilter.get(i).getProductPrize())>=lowerVal && Integer.parseInt (productListPriceFilter.get(i).getProductPrize())<=upperVal))
//				{
//					PriceMatches=false;
//					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductPrize()+" :: Expected: "+val);
//				}
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Brand and price || "+printList.toString());
//			SanAssertCS.assertTrue(BrandNameMatchesTemp && PriceMatches, "R_79Y");
//
//			ExtentTest childTestFilter = testListener.startChild("Clear All Filters test");
//			childTestFilter.setDescription("This test verifies the user is able to clear applied filters on react page");
//			TestListner.extentMap.get().put("childTestFilter", childTestFilter);
//			filtersCleared=listingPage.clearAllFilters();
//			TestListner.testing.get().log(LogStatus.INFO, "Clear Filter status: "+filtersCleared);
//			SanAssertCS.assertTrue(filtersCleared,"R_79V_1");
//
//
//
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally 
//		{
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "sanity","Wsanity" ,"react","LiveSanity"})
//	public void React_Page_Multiple_Filter_on_L2_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l2category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
//		List<String> printList = new ArrayList<String>();
//		HeaderBar headerbar = new HeaderBar(browser);
//		ListingPage_React listingPage = new ListingPage_React(browser);
//		boolean PriceMatches=true;
//		boolean filtersCleared=false;
//		try {
//			ExtentTest childTest1 = testListener.startChild("Navigate to React PLP test ");
//			childTest1.setDescription("This test verifies the user is able to navigate to react page");
//			TestListner.extentMap.get().put("childTest1", childTest1);
//			int count=1;
//			browser.get(environmentURL);
//			headerbar.navigateToCategory(headerbarData);
//			listingPage.navigateToShopAll("shop-all");
//			List<String> BrandNameMatches=new ArrayList<String>();
//			boolean BrandNameMatchesTemp=false;
//			ArrayList<String> allOptions=new ArrayList<String>();
//			allOptions=listingPage.getFilterOptionsList("Brand");
//			ArrayList<String> brandFilterValues=new ArrayList<String>();
//			HashSet<Integer> indexes=framework.randomNumbers(allOptions.size());
//			for(int i:indexes)
//			{
//				if(! allOptions.get(i-1).contains("OGX"))
//				{
//					if(count<=1) 
//					{
//						brandFilterValues.add(allOptions.get(i-1).split("\\(")[0].trim());
//					}
//					count+=1;
//				}
//			}
//
//			ExtentTest childTest = testListener.startChild("Filter by Brand and Price test ");
//			childTest.setDescription("This test verifies the user is able to apply multiple filters on react page");
//			TestListner.extentMap.get().put("childTest", childTest);
//			for(String val:brandFilterValues)
//			{
//				listingPage.ApplyFilter("Brand", val,false,true);
//			}
//
//			allOptions.clear();
//			allOptions=listingPage.getFilterOptionsList("Price");
//			String val=allOptions.get(0).split("\\(")[0].trim();
//			int lowerVal;
//			int upperVal;
//			if(!val.contains("Above"))
//			{
//				lowerVal=Integer.parseInt((val.split("-")[0]).replaceAll("Rs. ", "").trim());
//				upperVal=Integer.parseInt((val.split("-")[1]).replaceAll("Rs. ", "").trim());
//			}
//			else
//			{
//				lowerVal=Integer.parseInt((val.split("&")[0]).replaceAll("Rs. ", "").trim());
//				upperVal=999999;
//			}
//
//			productListPriceFilter = listingPage.ApplyFilter("Price", val,true,false);
//
//			for(int i=0;i<productListPriceFilter.size();i++)
//			{
//				printList.add(productListPriceFilter.get(i).getProductName()+" :: "+(productListPriceFilter.get(i).getProductPrize()));
//				String expected="";
//				String actual="";
//				for(String brand:brandFilterValues)
//				{
//					expected=brand.replace("OGX", "Organix");
//					actual=productListPriceFilter.get(i).getProductName().split("'")[0].toLowerCase();
//					if(actual.contains(expected.split("'")[0].toLowerCase()))
//					{
//						BrandNameMatchesTemp=true;
//					}
//				}
//				BrandNameMatches.add(BrandNameMatchesTemp?"true":"false");
//				if(! BrandNameMatchesTemp)
//				{
//					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+actual+" :: Expected: "+expected);
//				}
//
//				if(!(Integer.parseInt(productListPriceFilter.get(i).getProductPrize())>=lowerVal && Integer.parseInt (productListPriceFilter.get(i).getProductPrize())<=upperVal))
//				{
//					PriceMatches=false;
//					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductPrize()+" :: Expected: "+val);
//				}
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Brand and price || "+printList.toString());
//			SanAssertCS.assertTrue(BrandNameMatchesTemp && PriceMatches, "R_80Y");
//
//			ExtentTest childTestFilter = testListener.startChild("Clear All Filters test");
//			childTestFilter.setDescription("This test verifies the user is able to clear applied filters on react page");
//			TestListner.extentMap.get().put("childTestFilter", childTestFilter);
//			filtersCleared=listingPage.clearAllFilters();
//			TestListner.testing.get().log(LogStatus.INFO, "Clear Filter status: "+filtersCleared);
//			SanAssertCS.assertTrue(filtersCleared,"R_80V_1");
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally 
//		{
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "sanity","Wsanity" ,"react"})
//	public void React_Page_Multiple_Filter_on_L3_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l3category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
//		List<String> printList = new ArrayList<String>();
//		HeaderBar headerbar = new HeaderBar(browser);
//		ListingPage_React listingPage = new ListingPage_React(browser);
//		boolean PriceMatches=true;
//		boolean filtersCleared=false;
//		try {
//			ExtentTest childTest1 = testListener.startChild("Navigate to React PLP test ");
//			childTest1.setDescription("This test verifies the user is able to navigate to react page");
//			TestListner.extentMap.get().put("childTest1", childTest1);
//			int count=1;
//			browser.get(environmentURL);
//			headerbar.navigateToCategory(headerbarData);
//			listingPage.navigateToShopAll("shop-all");
//			List<String> BrandNameMatches=new ArrayList<String>();
//			boolean BrandNameMatchesTemp=false;
//			ArrayList<String> allOptions=new ArrayList<String>();
//			allOptions=listingPage.getFilterOptionsList("Brand");
//			ArrayList<String> brandFilterValues=new ArrayList<String>();
//			HashSet<Integer> indexes=framework.randomNumbers(allOptions.size());
//			for(int i:indexes)
//			{
//				if(! allOptions.get(i-1).contains("OGX"))
//				{
//					if(count<=1) 
//					{
//						brandFilterValues.add(allOptions.get(i-1).split("\\(")[0].trim());
//					}
//					count+=1;
//				}
//			}
//
//			ExtentTest childTest = testListener.startChild("Filter by Brand and Price test ");
//			childTest.setDescription("This test verifies the user is able to apply multiple filters on react page");
//			TestListner.extentMap.get().put("childTest", childTest);
//			for(String val:brandFilterValues)
//			{
//				listingPage.ApplyFilter("Brand", val,false,true);
//			}
//
//			allOptions.clear();
//			allOptions=listingPage.getFilterOptionsList("Price");
//			String val=allOptions.get(0).split("\\(")[0].trim();
//			int lowerVal;
//			int upperVal;
//			if(!val.contains("Above"))
//			{
//				lowerVal=Integer.parseInt((val.split("-")[0]).replaceAll("Rs. ", "").trim());
//				upperVal=Integer.parseInt((val.split("-")[1]).replaceAll("Rs. ", "").trim());
//			}
//			else
//			{
//				lowerVal=Integer.parseInt((val.split("&")[0]).replaceAll("Rs. ", "").trim());
//				upperVal=999999;
//			}
//
//			productListPriceFilter = listingPage.ApplyFilter("Price", val,true,false);
//
//			for(int i=0;i<productListPriceFilter.size();i++)
//			{
//				printList.add(productListPriceFilter.get(i).getProductName()+" :: "+(productListPriceFilter.get(i).getProductPrize()));
//				String expected="";
//				String actual="";
//				for(String brand:brandFilterValues)
//				{
//					expected=brand.replace("OGX", "Organix");
//					actual=productListPriceFilter.get(i).getProductName().split("'")[0].toLowerCase();
//					if(actual.contains(expected.split("'")[0].toLowerCase()))
//					{
//						BrandNameMatchesTemp=true;
//					}
//				}
//				BrandNameMatches.add(BrandNameMatchesTemp?"true":"false");
//				if(! BrandNameMatchesTemp)
//				{
//					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+actual+" :: Expected: "+expected);
//				}
//
//				if(!(Integer.parseInt(productListPriceFilter.get(i).getProductPrize())>=lowerVal && Integer.parseInt (productListPriceFilter.get(i).getProductPrize())<=upperVal))
//				{
//					PriceMatches=false;
//					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductPrize()+" :: Expected: "+val);
//				}
//			}
//			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Brand and price || "+printList.toString());
//			SanAssertCS.assertTrue(BrandNameMatchesTemp && PriceMatches, "R_81Y");
//
//			ExtentTest childTestFilter = testListener.startChild("Clear All Filters test");
//			childTestFilter.setDescription("This test verifies the user is able to clear applied filters on react page");
//			TestListner.extentMap.get().put("childTestFilter", childTestFilter);
//			filtersCleared=listingPage.clearAllFilters();
//			TestListner.testing.get().log(LogStatus.INFO, "Clear Filter status: "+filtersCleared);
//			SanAssertCS.assertTrue(filtersCleared,"R_81V_1");
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally 
//		{
//			browser.quit();
//		}
//	}
//
//	/**********************************************************************
//	 * React Pages test- Product Pages.
//	 ***********************************************************************/
//	@Test(groups = { "productaddtobag", "productaddtobagsimple1","sanity","Wsanity","LiveSanity"})
//	public void React_Page_Simple_Product_Page_for_L1_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		AccountData accountData = framework.getData(AccountData.class, "newlogin");
//
//		try 
//		{
//			ExtentTest childTest1 = testListener.startChild("New User Register Test");
//			childTest1.setDescription("This test verifies that new user is able to register");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			HeaderBar headerbar = new HeaderBar(browser);
//			{
//				AccountPage_React accountPage = new AccountPage_React(browser);
//				String loggedInUser = accountPage.NewRegister(accountData);
//				SanAssertCS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()),
//						"New user registration test");
//
//				ExtentTest childTest2 = testListener.startChild("Add Simple Product from Product Page Test");
//				childTest2.setDescription("This test verifies that new user is able to add simple procuct from PDP");
//				TestListner.extentMap.get().put("child2", childTest2);
//				headerbar.clearCart();
//				headerbar.navigateToCategory(headerbarData);
//				ProductPage_React productPage = new ProductPage_React(browser);
//				CartPage_React cartPage = new CartPage_React(browser);
//				ProductDetailData ProductDetailData1 = productPage.addAnySimpleProductFromProductPAge();
//				SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData1), "R_82A");
//
//				ExtentTest childTest3 = testListener.startChild("Customer Also Viewed Widget Test");
//				childTest3.setDescription("This test verifies that user is able to view PAV widget");
//				TestListner.extentMap.get().put("child3", childTest3);
//				SanAssertCS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "R_82C");
//
//				ExtentTest childTest4 = testListener.startChild("Customer Also Bought Widget Test");
//				childTest4.setDescription("This test verifies that user is able to view PAB widget");
//				TestListner.extentMap.get().put("child4", childTest4);
//				SanAssertCS.assertTrue(productPage.isPeopleWhoboughtDisplayed(), "R_82D");
//
//				ExtentTest childTest5 = testListener.startChild("Add to Wishlist Test");
//				childTest5.setDescription("This test verifies that user is able to add product to wishlist");
//				TestListner.extentMap.get().put("child5", childTest5);
//				browser.navigate().refresh();
//				SanAssertCS.assertTrue(productPage.addToWishlist(), "R_82B");
//
//				ExtentTest childTest6 = testListener.startChild("Add Product to Cart from Wishlist Test");
//				childTest6.setDescription("This test verifies that user is able to add product to cart from wishlist");
//				TestListner.extentMap.get().put("child6", childTest6);
//				headerbar.clearCart();
//				accountPage.navigateToWishlist();
//				ListingPage_React listingPage = new ListingPage_React(browser);
//				ProductDetailData wishlistProductDetaildata = listingPage.addAnySimpleProductFromWishlist();
//				headerbar.navigateToCategory(headerbarData);//to navigate to react page in order to use react cart methods
//				SanAssertCS.assertTrue(cartPage.isProductPresentInBag(wishlistProductDetaildata), "Add to bag from Wishlist");
//			}
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			e.printStackTrace();
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally {
//
//			//LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "productaddtobag", "productaddtobagsimple1","sanity","Wsanity","LiveSanity"})
//	public void React_Page_Shade_Product_Page_for_L1_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		AccountData accountData = framework.getData(AccountData.class, "newlogin");
//
//		try 
//		{
//			ExtentTest childTest1 = testListener.startChild("New User Register Test");
//			childTest1.setDescription("This test verifies that new user is able to register");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			HeaderBar headerbar = new HeaderBar(browser);
//			{
//				AccountPage_React accountPage = new AccountPage_React(browser);
//				String loggedInUser = accountPage.NewRegister(accountData);
//				SanAssertCS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()),
//						"New user registration test");
//
//				ExtentTest childTest2 = testListener.startChild("Add Shade Product from Product Page Test");
//				childTest2.setDescription("This test verifies that new user is able to add shade procuct from PDP");
//				TestListner.extentMap.get().put("child2", childTest2);
//				headerbar.clearCart();
//				headerbar.navigateToCategory(headerbarData);
//				ProductPage_React productPage = new ProductPage_React(browser);
//				CartPage_React cartPage = new CartPage_React(browser);
//				ProductDetailData ProductDetailData1 = productPage.addAnyShadeProductFromProductPAge();
//				SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData1), "R_82E");
//
//				ExtentTest childTest3 = testListener.startChild("Customer Also Viewed Widget Test");
//				childTest3.setDescription("This test verifies that user is able to view PAV widget");
//				TestListner.extentMap.get().put("child3", childTest3);
//				SanAssertCS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "R_82G");
//
//				ExtentTest childTest4 = testListener.startChild("Customer Also Bought Widget Test");
//				childTest4.setDescription("This test verifies that user is able to view PAB widget");
//				TestListner.extentMap.get().put("child4", childTest4);
//				SanAssertCS.assertTrue(productPage.isPeopleWhoboughtDisplayed(), "R_82I");
//
//				ExtentTest childTest5 = testListener.startChild("Add to Wishlist Test");
//				childTest5.setDescription("This test verifies that user is able to add product to wishlist");
//				TestListner.extentMap.get().put("child5", childTest5);
//				browser.navigate().refresh();
//				SanAssertCS.assertTrue(productPage.addToWishlist(), "R_82F");
//
//				/*ExtentTest childTest6 = testListener.startChild("Add Product to Cart from Wishlist Test");
//				childTest6.setDescription("This test verifies that user is able to add product to cart from wishlist");
//				TestListner.extentMap.get().put("child6", childTest6);*/
//				/*headerbar.clearCart_React();
//				accountPage.navigateToWishlist();
//				ListingPage listingPage = new ListingPage(browser);
//				ProductDetailData wishlistProductDetaildata = listingPage.addAnyShadeProductwishlist();
//				headerbar.navigateToCategory(headerbarData);//to navigate to react page in order to use react cart methods
//				 */				//SanAssertCS.assertTrue(cartPage.isProductPresentInBag(wishlistProductDetaildata), "Add to bag from Wishlist");
//			}
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			e.printStackTrace();
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally {
//
//			//LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//	}
//
//	@Test(groups = { "productaddtobag", "productaddtobagsimple1","AddToBag_React","sanity","Wsanity"})
//	public void React_Page_Size_Product_Page_for_L1_category_page() throws Throwable {
//
//		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
//		AccountData accountData = framework.getData(AccountData.class, "newlogin");
//
//		try 
//		{
//			ExtentTest childTest1 = testListener.startChild("New User Register Test");
//			childTest1.setDescription("This test verifies that new user is able to register");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			HeaderBar headerbar = new HeaderBar(browser);
//			{
//				AccountPage_React accountPage = new AccountPage_React(browser);
//				String loggedInUser = accountPage.NewRegister(accountData);
//				SanAssertCS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()),
//						"New user registration test");
//
//				ExtentTest childTest2 = testListener.startChild("Add Size Product from Product Page Test");
//				childTest2.setDescription("This test verifies that new user is able to add size procuct from PDP");
//				TestListner.extentMap.get().put("child2", childTest2);
//				headerbar.clearCart();
//				headerbar.navigateToCategory(headerbarData);
//				ProductPage_React productPage = new ProductPage_React(browser);
//				CartPage_React cartPage = new CartPage_React(browser);
//				ProductDetailData ProductDetailData1 = productPage.addAnySizeProductFromProductPage();
//				SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData1), "R_82J");
//
//				ExtentTest childTest3 = testListener.startChild("Customer Also Viewed Widget Test");
//				childTest3.setDescription("This test verifies that user is able to view PAV widget");
//				TestListner.extentMap.get().put("child3", childTest3);
//				SanAssertCS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "R_82L");
//
//				ExtentTest childTest4 = testListener.startChild("Customer Also Bought Widget Test");
//				childTest4.setDescription("This test verifies that user is able to view PAB widget");
//				TestListner.extentMap.get().put("child4", childTest4);
//				SanAssertCS.assertTrue(productPage.isPeopleWhoboughtDisplayed(), "R_82M");
//
//				ExtentTest childTest5 = testListener.startChild("Add to Wishlist Test");
//				childTest5.setDescription("This test verifies that user is able to add product to wishlist");
//				TestListner.extentMap.get().put("child5", childTest5);
//				browser.navigate().refresh();
//				SanAssertCS.assertTrue(productPage.addToWishlist(), "R_82K");
//
//				/*ExtentTest childTest6 = testListener.startChild("Add Product to Cart from Wishlist Test");
//				childTest6.setDescription("This test verifies that user is able to add product to cart from wishlist");
//				TestListner.extentMap.get().put("child6", childTest6);*/
//				/*headerbar.clearCart_React();
//				accountPage.navigateToWishlist();
//				ListingPage listingPage = new ListingPage(browser);
//				ProductDetailData wishlistProductDetaildata = listingPage.addAnyShadeProductwishlist();
//				headerbar.navigateToCategory(headerbarData);//to navigate to react page in order to use react cart methods
//				 */				//SanAssertCS.assertTrue(cartPage.isProductPresentInBag(wishlistProductDetaildata), "Add to bag from Wishlist");
//			}
//
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			e.printStackTrace();
//			framework.logErrorWithSnapshot(browser,e);
//			Reporter.log(e.toString(), true);
//			Assert.fail();
//
//		} finally {
//
//			//LoginSync.getInstance().clearLoginLock(accountData);
//			browser.quit();
//		}
//	}
//
//	/**********************************************************************
//	 * Login/ Logout tests.
//	 ***********************************************************************/
//	@Test(groups = {"sanityy", "React_Login","sanity","Wsanity","LiveSanity"})
//	public void Login_Logout_HomePage () throws Throwable {
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
//		browser.get(environmentURL);
//		//AccountData FbLogin = framework.getData(AccountData.class, "facebook");
//		AccountData GLogin = framework.getData(AccountData.class, "google");
//		AccountData Login = LoginSync.getInstance().getLogin();
//		try{
//			ExtentTest childTest1 = testListener.startChild("Login - Logout(Normal Login) from Home Page");
//			childTest1.setDescription("This test verifies that User is able to Login and Logout (Normal Login) Through Home Page");
//			TestListner.extentMap.get().put("child1", childTest1);
//			AccountPage accountPage = new AccountPage(browser);
//			//AccountPage_React accountPage_react = new AccountPage_React(browser);
//			String NLogin = accountPage.signInWithYourEmail(Login);
//			SanCS.assertEquals(NLogin.toLowerCase(), Login.getFirstName().toLowerCase(), "L1H1");
//			boolean isNLogout = accountPage.LogOut();
//			SanCS.assertTrue(isNLogout, "L1H2");
//			/*ExtentTest childTest2 = testListener.startChild("Login - Logout(Facebook) from Home Page");
//	         childTest2.setDescription("This test verifies that User is able to Login and Logout (Facebook) Through Home Page");
//	         TestListner.extentMap.get().put("child2", childTest2);
//	         String FLogin = accountPage.signInWithFacebook(FbLogin);
//	         SanCS.assertEquals(FLogin, Login.getFirstName(), "L1H3");
//	         boolean isFLogout = accountPage.LogOut();
//	         SanCS.assertTrue(isFLogout, "L1H4");*/
//			ExtentTest childTest3 = testListener.startChild("Login - Logout(Google) from Home Page");
//			childTest3.setDescription("This test verifies that User is able to Login and Logout (Google) Through Home Page");
//			TestListner.extentMap.get().put("child3", childTest3);
//			String GoLogin = accountPage.signInWithGooglePlus(GLogin);
//			System.out.println(GoLogin);
//			SanCS.assertEquals(GoLogin.toLowerCase(), Login.getFirstName().toLowerCase(), "L1H5");
//			boolean isGoLogout = accountPage.LogOut();
//			SanCS.assertTrue(isGoLogout, "L1H6");
//		}catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}
//		browser.quit();
//
//	}
//
//	/*@Test(groups = {"React_Login"})
//	public void Login_Logout_L1_Page () throws Throwable {
//		Header_FooterBarData L1category = framework.getData(Header_FooterBarData.class, "l1category");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
//		browser.get(environmentURL);
//		AccountData Login = LoginSync.getInstance().getLogin();
//		//AccountData FbLogin = framework.getData(AccountData.class, "facebook");
//		AccountData GLogin = framework.getData(AccountData.class, "google");
//		try{
//			ExtentTest childTest1 = testListener.startChild("Login - Logout(Normal Login) from Listing Page");
//			childTest1.setDescription("This test verifies that User is able to Login and Logout (Normal Login) Through Listing Page");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.clearCart();
//			headerBar.navigateToCategory(L1category);
//			AccountPage accountPage = new AccountPage(browser);
//			String NLogin = accountPage.signInWithYourEmail(Login);
//			SanCS.assertEquals(NLogin.toLowerCase(), Login.getFirstName().toLowerCase(), "L1L1");
//			accountPage.LogOut();
//			ExtentTest childTest2 = testListener.startChild("Login - Logout(Facebook) from Listing Page");
//	         childTest2.setDescription("This test verifies that User is able to Login and Logout (Facebook) Through Listing Page");
//	         TestListner.extentMap.get().put("child2", childTest2);
//	         SanCS.assertTrue(isNLogout, "L1L2");
//	         String FLogin = accountPage.signInWithFacebook(FbLogin);
//	         SanCS.assertEquals(FLogin, Login.getFirstName(), "L1L3");
//	         boolean isFLogout = accountPage.LogOut();
//	         SanCS.assertTrue(isFLogout, "L1L4");
//			ExtentTest childTest3 = testListener.startChild("Login - Logout(Goggle) from Listing Page");
//			childTest3.setDescription("This test verifies that User is able to Login and Logout (Google) Through Listing Page");
//			TestListner.extentMap.get().put("child3", childTest3);
//			String GoLogin = accountPage.signInWithGooglePlus(GLogin);
//			System.out.println(GoLogin);
//			SanCS.assertEquals(GoLogin.toLowerCase().toLowerCase(), Login.getFirstName().toLowerCase(), "L1L5");
//			boolean isGoLogout = accountPage.LogOut();
//			SanCS.assertTrue(isGoLogout, "L1L6");
//		}catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}
//		browser.quit();
//
//	}*/
//
//	@Test(groups = {"sanity", "React_Login","sanity","Wsanity","LiveSanity"})
//	public void Login_Logout_L1_ReactPage () throws Throwable {
//		Header_FooterBarData L1categoryReact = framework.getData(Header_FooterBarData.class, "l1category_react");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
//		browser.get(environmentURL);
//		AccountData Login = LoginSync.getInstance().getLogin();
//		//AccountData FbLogin = framework.getData(AccountData.class, "facebook");
//		AccountData GLogin = framework.getData(AccountData.class, "google");
//		try{
//			ExtentTest childTest1 = testListener.startChild("Login - Logout(Normal Login) from React Page");
//			childTest1.setDescription("This test verifies that User is able to Login and Logout (Normal Login) Through React Page");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.clearCart();
//			headerBar.navigateToCategory(L1categoryReact);
//			AccountPage_React accountPage_react = new AccountPage_React(browser);
//			String NLogin = accountPage_react.ReactSignInwithEmail(Login);
//			SanCS.assertTrue(Login.getFirstName().toLowerCase().contains(NLogin.replace("...","").toLowerCase()), "L1R1");
//			boolean isNLogout = accountPage_react.LogOut(CommonConstants.NKYAA);
//			SanCS.assertTrue(isNLogout, "L1R2");
//			/*ExtentTest childTest2 = testListener.startChild("Login - Logout(Facebook) from React Page");
//	         childTest2.setDescription("This test verifies that User is able to Login and Logout (Facebook) Through React Page");
//	         TestListner.extentMap.get().put("child2", childTest2);
//	         String FLogin= accountPage_react.signInWithFacebook(FbLogin);
//	         SanCS.assertEquals(FLogin, FbLogin.getFirstName(), "L1R3");
//	         boolean isFLogout = accountPage_react.LogOut();
//	         SanCS.assertTrue(isFLogout, "L1R4");*/
//			ExtentTest childTest3 = testListener.startChild("Login - Logout(Google) from React Page");
//			childTest3.setDescription("This test verifies that User is able to Login and Logout (Google) Through React Page");
//			TestListner.extentMap.get().put("child3", childTest3);
//			String GoLogin = accountPage_react.signInWithGooglePlus(GLogin);
//			System.out.println(GoLogin);
//			System.out.println(Login.getFirstName());
//			SanCS.assertTrue(Login.getFirstName().toLowerCase().contains(GoLogin.replace("...","").toLowerCase()), "L1R5");
//			boolean isGoLogout = accountPage_react.LogOut("");
//			SanCS.assertTrue(isGoLogout, "L1R6");
//		}catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}
//		browser.quit();
//
//	}
//
//	@Test(groups = {"sanity", "React_Login","sanity","Wsanity"})
//	public void Login_Logout_MyOrder () throws Throwable {
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
//		browser.get(environmentURL);
//		AccountData Login = LoginSync.getInstance().getLogin();
//		//AccountData FbLogin = framework.getData(AccountData.class, "facebook");
//		AccountData GLogin = framework.getData(AccountData.class, "google");
//		try{
//			ExtentTest childTest1 = testListener.startChild("Login - Logout(Normal Login) from My Order Page");
//			childTest1.setDescription("This test verifies that User is able to Login and Logout (Normal Login) Through My Order Page");
//			TestListner.extentMap.get().put("child1", childTest1);
//			browser.get(environmentURL);
//			//headerBar.navigateToCategory(L1category);
//			AccountPage accountPage = new AccountPage(browser);
//			AccountPage_React accountPage_react = new AccountPage_React(browser);
//			String NLogin = accountPage.signInWithYourEmail(Login);
//			SanCS.assertEquals(NLogin.toLowerCase(), Login.getFirstName().toLowerCase(), "L1M1");
//			boolean isNLogout = accountPage.Login_Logout_Myorder();
//			SanCS.assertTrue(isNLogout, "L1M2");
//			/*ExtentTest childTest2 = testListener.startChild("Login - Logout(Facebook) from My Order Page");
//		         childTest2.setDescription("This test verifies that User is able to Login and Logout (Facebook) Through My Order Page");
//		         TestListner.extentMap.get().put("child2", childTest2);
//		         String isFLogin = accountPage.signInWithFacebook(FbLogin);
//		         SanCS.assertEquals(isFLogin, Login.getFirstName(), "L1M3");
//		         boolean isFLogout = accountPage.Myorder();
//		         SanCS.assertTrue(isFLogout, "L1M4");*/
//			ExtentTest childTest3 = testListener.startChild("Login - Logout(Google) from My Order Page");
//			childTest3.setDescription("This test verifies that User is able to Login and Logout (Google) Through My Order Page");
//			TestListner.extentMap.get().put("child3", childTest3);
//			String isGoLogin = accountPage_react.signInWithGooglePlus(GLogin);
//			SanCS.assertEquals(isGoLogin.toLowerCase(), Login.getFirstName().toLowerCase(), "L1M5");
//			boolean isGoLogout = accountPage.Login_Logout_Myorder();
//			SanCS.assertTrue(isGoLogout, "L1M6");
//		}catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser, e);
//			Reporter.log(e.toString());
//			Assert.fail();
//		} finally {
//			browser.quit();
//
//		}
//	}
//
//	public void CheckNotifymefromListingPage() throws URISyntaxException, Throwable {
//		Header_FooterBarData headerbar1 = framework.getData(Header_FooterBarData.class, "sizenavigation");
//		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
//
//		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
//		try {
//			browser.get(environmentURL);
//			HeaderBar headerBar = new HeaderBar(browser);
//			headerBar.clearCart();
//			headerBar.navigateToCategory(headerbar1);
//			ListingPage listingPage = new ListingPage(browser);
//			listingPage.ApplyFilter("price", "Rs. 2,000 - Rs. 4,000");
//			List<ProductDetailData> productList = listingPage.getProductList(listingPage.OOSProducts());
//			ExtentTest childTest3 = testListener.startChild("Verify Notfiy Me From Magento Listing Page");
//			childTest3.setDescription("This test verifies that Notify Me is working from Magento Listin Page");
//			TestListner.extentMap.get().put("child3", childTest3);
//			boolean isNLogout=false;
//			for (ProductDetailData product : productList) {
//				isNLogout = listingPage.checkNotifyfromListing(product);
//				if (isNLogout == true) {
//					break;
//				}
//			}
//			SanCS.assertTrue(isNLogout, "81J");
//			/***********************************************************************************************/
//			boolean NotifyfromPD = false;
//			for (ProductDetailData product : productList) {
//				NotifyfromPD = listingPage.checkNotifyfromPD(product);
//				if(NotifyfromPD==true) {
//					break;
//				}
//			}
//			SanCS.assertTrue(NotifyfromPD, "56C");
//		} catch (Exception e) {
//			printStackStraceinLog(e);
//			framework.logErrorWithSnapshot(browser, e);
//			Reporter.log(e.toString());
//			Assert.fail();
//		} finally {
//			browser.quit();
//
//		}
//
//	}
//}
