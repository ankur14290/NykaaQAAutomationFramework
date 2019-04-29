package TestCaseSuite;

import DataNykaa.*;
import FrameWorkNykaa.*;
import Mobile_Pages_Nykaa.*;
import PagesNykaa.AnalyticsInfoPage;

import PagesNykaa.CheckOutPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class Z_SanitySuite_Mobile {

	Framework framework = new Framework();
	Logger log = Logger.getLogger(Z_SanitySuite_New.class.getName());
	TestListner testListener = new TestListner();
	String ClassName = this.getClass().getSimpleName();
	String PackageName = this.getClass().getPackage().getName();
	Properties properties = System.getProperties();
	AssertData SanAssertData = new AssertData("Sanity Checklist", "Msite", "AutomationResult", "SubElements",
			properties.getProperty("BUILD_URL") + "testngreports/" + PackageName + "/" + ClassName + "/");
	String environmentURL;
	

	@SuppressWarnings("deprecation")

	/**********************************************************************
	 * CheckoutTestCases
	 ****************************************************************************************************************/
	@BeforeClass(alwaysRun = true)
	public void preSetupFortest() throws Throwable {

		EnvironmentParameterData EnvironmentData = framework.getData(EnvironmentParameterData.class, "Msite");
		String envURL = System.getProperty("URL");
		if (envURL != null) {
			environmentURL = envURL;

		} else {
			environmentURL = EnvironmentData.getBaseurl();
			// environmentURL = "http://www.nykaa.com";
		}

	}


	@Test( groups = { "sanity", "Msanity","sanity_Live","N_sanity_Live","N_sanity_Live","Mcheckout","checkout"})

	public void Mobile_Checkout_Flow_With_Greater_Than_500_Already_register_user()
			throws URISyntaxException, Throwable {
		AccountData Login = LoginSync.getInstance().getLogin();
		// TestListner tL = new TestListner();
		Header_FooterBarData HeaderBar_Mobile1 = framework.getData(Header_FooterBarData.class, "shadenavigation_Mobile");
		Header_FooterBarData HeaderBar_Mobile2 = framework.getData(Header_FooterBarData.class, "sizenavigation_Mobile");
		CheckoutData checKoutData = framework.getData(CheckoutData.class, "valid");
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		AccountPage_Mobile accountPage = new AccountPage_Mobile(browser);
		String lastOrder=null;
		ArrayList<ProductDetailData> productDataList=new ArrayList<ProductDetailData>();
		try {
			ExtentTest childTest = testListener.startChild("Existing User Login test");
			childTest.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child", childTest);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			String LoggedUserName = AccountPage_Mobile.signInWithYourEmail(Login).toLowerCase();
			SanCS.assertEquals(LoggedUserName, Login.getFirstName().toLowerCase(), "L1");
			
			ExtentTest childTest13 = testListener.startChild("Note last order ID test");
			childTest13.setDescription("This test notes the last order ID from My Orders. ");
			TestListner.extentMap.get().put("child13", childTest13);
			lastOrder=accountPage.getLastOrder();

			
			ExtentTest childTest8 = testListener.startChild("Simple Product add to cart Test");
			childTest8
			.setDescription("This test verifies that user is able to add simple product to cart");
			TestListner.extentMap.get().put("child8", childTest8);
			
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);
			browser.get(environmentURL);
			HeaderBar_Mobile.clearCart();
			HeaderBar_Mobile.navigateToCategory(HeaderBar_Mobile2);

			ListingPage_Mobile ListingPage_Mobile = new ListingPage_Mobile(browser);
			List<ProductDetailData> productList = ListingPage_Mobile.getProductList();
			List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
			productlistwithPrizeFilter = productList
					.stream().filter(o -> Integer.parseInt(o.getProductPrize()) > 500
							& !o.getProductType().contains("Size") & !o.getProductType().contains("Shade"))
					.collect(Collectors.toList());
			ProductDetailData simpleProductData = null;
			if((productlistwithPrizeFilter.size())==0){
				childTest8.log(LogStatus.INFO, "No product available below 500");
			}
			for (ProductDetailData product : productlistwithPrizeFilter) {
				boolean isadded = ListingPage_Mobile.addToBagSimpleProduct(product);
				if (isadded == true) {
					simpleProductData = product;
					break;
				}
			}
			CartPage_Mobile_New CartPage_Mobile = new CartPage_Mobile_New(browser);
			CartPage_Mobile.gotoHome();
			boolean isSimmpleProductAdded = CartPage_Mobile.isProductPresentInBag(simpleProductData);
			SanCS.assertTrue(isSimmpleProductAdded, "Simple Product add to cart test");
			
			ExtentTest childTest7 = testListener.startChild("Product with Shade Variants add to cart Test");
			childTest7
			.setDescription("This test verifies that user is able to add product with shade variants to cart");
			TestListner.extentMap.get().put("child7", childTest7);
			// Sign in with username

			HeaderBar_Mobile.navigateToCategory(HeaderBar_Mobile1);
			ProductDetailData ShadeproductDetadata = ListingPage_Mobile.addAnyShadeProductFromAllProduct();
			boolean isShadeProductAdded = CartPage_Mobile.isProductPresentInBag(ShadeproductDetadata);
			SanCS.assertTrue(isShadeProductAdded, "79L");
			ExtentTest childTest1 = testListener.startChild("Product with Size Variants add to cart test");
			childTest1.setDescription("This test verifies that user is able to add product with size variants to cart");
			TestListner.extentMap.get().put("child1", childTest1);
			HeaderBar_Mobile.navigateToCategory(HeaderBar_Mobile2);
			ProductDetailData SizeproductDetadata = ListingPage_Mobile.addAnySizeProductFromAllProduct();
			boolean isSizeProductAdded = CartPage_Mobile.isProductPresentInBag(SizeproductDetadata);
			SanCS.assertTrue(isSizeProductAdded, "79M");
			
			productDataList.add(simpleProductData);
			productDataList.add(ShadeproductDetadata);
			productDataList.add(SizeproductDetadata);
			
			ExtentTest childTest2 = testListener.startChild("Select Delivery Address test");
			childTest2.setDescription(
					"This test verifies that user is able to select existing delivery address during checkout");
			TestListner.extentMap.get().put("child2", childTest2);
			// Add to bag:
			CheckOutPage_Mobile_New CheckOutPage_Mobile = new CheckOutPage_Mobile_New(browser);
			CartPage_Mobile.goToCart();
			CartPage_Mobile.gotoProccedtWithoutFreeBies();
			String checkouturl = browser.getCurrentUrl();
			//CartPage_Mobile.gotoProceedToPayButton();
			//CartPage_Mobile_New CartPage_Mobile = new CartPage_Mobile_New(browser);
			CartPage_Mobile.goToCart();
			CartPage_Mobile.gotoProccedtWithoutFreeBies();
			//String checkouturl = browser.getCurrentUrl();
			ExtentTest childTest3 = testListener.startChild("Credit/Debit card payment test");
			childTest3.setDescription(
					"This test verifies that user is able to checkout with credit/debit card as payment method.");
			TestListner.extentMap.get().put("child3", childTest3);
			//CheckOutPage_Mobile_New CheckOutPage_Mobile = new CheckOutPage_Mobile_New(browser);
			CheckOutPage_Mobile.checkoutWithDebitCard(checKoutData);
			//BankingPage_Mobile bankPage = new BankingPage_Mobile(browser);
			//SanCS.assertTrue(bankPage.isPayUPage(), "L233");
			ExtentTest childTest4 = testListener.startChild("NetBanking payment test");
			childTest4.setDescription(
					"This test verifies that user is able to checkout with Net Banking as payment method.");
			TestListner.extentMap.get().put("child4", childTest4);
			// Enter "Card Number"
			// Enter "Card Holder name"
			// Enter "Expiry Date"
			//BankingPage_Mobile BankingPage_Mobile = new BankingPage_Mobile(browser);
			browser.get(checkouturl);
			System.out.println(checkouturl);
			boolean isNetBanking = CheckOutPage_Mobile.netBankingCheckout();
			SanCS.assertTrue(isNetBanking, "L235");
			ExtentTest childTest5 = testListener.startChild("Wallet payment test");
			childTest5
			.setDescription("This test verifies that user is able to checkout with Wallet as payment method.");
			TestListner.extentMap.get().put("child5", childTest5);
			/*
			 * browser.get(checkouturl); boolean isPayuCheckout =
			 * CheckOutPage_Mobile.payumoneyCheckout();
			 *
			 * SanCS.assertTrue(isPayuCheckout, "243");
			 */
			browser.get(checkouturl);
			boolean isPaytm = CheckOutPage_Mobile.PaytmWalletCheckout();
			SanCS.assertTrue(isPaytm, "247");
			ExtentTest childTest6 = testListener.startChild("Cash On Delivery place order test");
			childTest6.setDescription("This test verifies that user is able to place order with COD.");
			TestListner.extentMap.get().put("child6", childTest6);
			browser.get(checkouturl);
			boolean isCashonDelivery = CheckOutPage_Mobile.CashOnDelivery();
			SanCS.assertTrue(isCashonDelivery, "L241");
			
			ExtentTest childTest10 = testListener.startChild("My Orders Page Test");
			childTest10.setDescription("This test verifies that the order just placed is present in My Orders");
			TestListner.extentMap.get().put("child10", childTest10);
			CheckOutPage_Mobile.Goback();
			boolean isOrderPresent=accountPage.orderPresentInMyOrders(null,lastOrder);
			SanCS.assertTrue(isOrderPresent, "O_1");

			if(isOrderPresent)
			{

				ExtentTest childTest11 = testListener.startChild("Order Details Verification in My Orders Test");
				childTest11.setDescription("This test verifies that the Details displayed in My Orders are correct.");
				TestListner.extentMap.get().put("child11", childTest11);
				SanCS.assertTrue(accountPage.verifyOrderDetails(productDataList),"O_2");

				ExtentTest childTest12 = testListener.startChild("Cancel Order Test");
				childTest12.setDescription("This test verifies that user is able to cancel the order just placed");
				TestListner.extentMap.get().put("child12", childTest12);
				SanCS.assertTrue(accountPage.cancelLatestOrder(),"O_3");
			}

			
		} catch (Throwable e) {
			framework.logErrorWithSnapshot(browser, e);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {
			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();
		}

	}


	@Test(groups = {"Msanity","guestMob","sanity" ,"sanity_Live","N_sanity_Live","Mcheckout","checkout","Sourabh"})

	public void Mobile_Check_end_to_end_checkout_flow_with_Guest_User() throws URISyntaxException, Throwable {
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		Header_FooterBarData HeaderBar_Mobile1 = framework.getData(Header_FooterBarData.class, "shadenavigation_Mobile");
		Header_FooterBarData HeaderBar_Mobile2 = framework.getData(Header_FooterBarData.class, "sizenavigation_Mobile");
		CheckoutData checKoutData = framework.getData(CheckoutData.class, "valid");
		AnalyticsData analyticsCODData = framework.getData(AnalyticsData.class,
		 "checkoutcod");
		 AnalyticsData analyticCheckoutClick = framework.getData(AnalyticsData.class,
		 "checkoutclick");
		try {
			ExtentTest childTest1 = testListener
					.startChild("Add products to cart and checkout as Guest with Credit/Debit Card");
			childTest1.setDescription(
					"This test verifies that guest user is able to add multiple products to cart and checkout with Debit/Credit card");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			// AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);
			HeaderBar_Mobile.clearCart();
			HeaderBar_Mobile.navigateToCategory(HeaderBar_Mobile1);
			ListingPage_Mobile ListingPage_Mobile = new ListingPage_Mobile(browser);
			ProductDetailData ShadeproductDetadata = ListingPage_Mobile.addAnyShadeProductFromAllProduct();
			CartPage_Mobile_New CartPage_Mobile = new CartPage_Mobile_New(browser);
			CartPage_Mobile.isProductPresentInBag(ShadeproductDetadata);
			HeaderBar_Mobile.navigateToCategory(HeaderBar_Mobile2);
			ProductDetailData SizeproductDetadata = ListingPage_Mobile.addAnySizeProductFromAllProduct();
			CheckOutPage_Mobile_New CheckOutPage_Mobile = new CheckOutPage_Mobile_New(browser);

			CartPage_Mobile.isProductPresentInBag(SizeproductDetadata);
			CartPage_Mobile.goToCart();
			//CheckoutData CheckoutDataOfUI = CheckOutPage_Mobile.FetchOrderDetail(checKoutData);
			List<ProductDetailData> Items = CheckOutPage_Mobile.FetchProductDetail();
			Collections.reverse(Items);
			for (ProductDetailData item : Items) {
				System.out.println(item.getProductName());
				System.out.println(item.getProductPrize());
			}
			String checkouturl = browser.getCurrentUrl();
			// Add to bag:
			CartPage_Mobile.checkOut();
			//String checkouturl = browser.getCurrentUrl();
			//CheckoutData CheckoutDataOfUI = CheckOutPage_Mobile.FetchOrderDetail(checKoutData);

			// AnalyticsInfoPage analyticinfo = new AnalyticsInfoPage(browser);

			// CheckOutPage_Mobile.fillbillingEmailIDAndContinueAsGuest(checKoutData);

			CheckOutPage_Mobile.fillNewAddressGuest(checKoutData);

			CheckOutPage_Mobile.checkoutWithDebitCard(checKoutData);
			BankingPage_Mobile bankPage = new BankingPage_Mobile(browser);
			SanCS.assertTrue(bankPage.isPayUPage(), "L233");
			// SanCS.assertTrue(true, "226");
			// Enter "Card Number"
			// SanCS.assertTrue(true, "228");
			// Enter "Card Holder name"
			// SanCS.assertTrue(true, "230");
			// Enter "Expiry Date"
			// SanCS.assertTrue(true, "" + "");
			ExtentTest childTest2 = testListener.startChild("Guest User Checkout with Net Banking test");
			childTest2.setDescription(
					"This test verifies that Guest user is able to checkout with Net Banking as payment method");
			TestListner.extentMap.get().put("child2", childTest2);
			// BankingPage bankingPage = new BankingPage(browser);
			browser.get(checkouturl);
			//System.out.println(checkouturl);
			CartPage_Mobile.gotoProccedtWithoutFreeBies();
			boolean isNetBanking = CheckOutPage_Mobile.netBankingCheckout();
			SanCS.assertTrue(isNetBanking, "Net Banking Checkout");
			ExtentTest childTest3 = testListener.startChild("Guest User Checkout with Wallet test");
			childTest3.setDescription(
					"This test verifies that Guest user is able to checkout with Wallet as payment method");
			TestListner.extentMap.get().put("child3", childTest3);
			browser.get(checkouturl);
			CartPage_Mobile.gotoProccedtWithoutFreeBies();
			boolean isMobikwik = CheckOutPage_Mobile.PaytmWalletCheckout();

			//SanCS.assertTrue(isMobikwik, "Wallet Checkout");

			ExtentTest childTest4 = testListener.startChild("Place COD order as Guest user test");
			childTest4.setDescription("This test verifies that Guest user is able to place COD order");
			TestListner.extentMap.get().put("child4", childTest4);
			browser.get(checkouturl);
            //CartPage_Mobile.closefooterPopup();
			CartPage_Mobile.gotoProccedtWithoutFreeBies();
			boolean cashonDelivery = CheckOutPage_Mobile.CashOnDelivery();
			SanCS.assertTrue(cashonDelivery, "241");
		/*	ExtentTest childTest5 = testListener.startChild("Payment details verification for Guest user test");
			childTest5.setDescription(
					"This test verifies that Guest user payment details are displayed correctly on confirmation Page");
			TestListner.extentMap.get().put("child5", childTest5);
			ConfirmationPage_Mobile confirmationPage = new ConfirmationPage_Mobile(browser);
			confirmationPage.getPaymentData();
			boolean isPaymentDetail = confirmationPage.verifyPaymentDetailWithCheckoutData(CheckoutDataOfUI);
		//	SanCS.assertTrue(isPaymentDetail, "257");
			ExtentTest childTest6 = testListener.startChild("Delivery details verification for Guest user test");
			childTest6.setDescription(
					"This test verifies that Guest user delivery details are displayed correctly on confirmation Page");
			TestListner.extentMap.get().put("child6", childTest6);
			boolean isDeliveryDetail = confirmationPage.verifyDeliveryDetailWithCheckoutData(CheckoutDataOfUI);
			SanCS.assertTrue(isDeliveryDetail, "256");
			System.out.println(Items);
			System.out.println(confirmationPage.verifyOrderItemDetail());
			ExtentTest childTest7 = testListener.startChild("Item details verification for Guest user test");
			childTest7.setDescription(
					"This test verifies that Guest user Item details are displayed correctly on confirmation Page");
			TestListner.extentMap.get().put("child7", childTest7);
			System.out.println(confirmationPage.verifyOrderItemDetail().toString());
			SanCS.assertTrue(Items.toString().equals(confirmationPage.verifyOrderItemDetail().toString()), "259");
			// SanCS.assertTrue(Items.equals(Items), "259");
			 analyticinfo.getEvar(analyticsCODData);
			 analyticinfo.getEvent(analyticsCODData);
			 System.out.println( analyticinfo.getEvar(analyticsCODData));*/
			//SanCS.assertAll();

		} catch (Throwable e) {
			framework.logErrorWithSnapshot(browser, e);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {
			browser.quit();
		}

	}

	@Test(groups = {"Msanity","sanity","Mcheckout","checkout","Sourabh"})
	public void Mobile_Check_end_to_end_checkout_flow_order_less_than_500() throws URISyntaxException, Throwable {
		// PropertyConfiguration pf = new PropertyConfiguration();
		// Properties prop = pf.getInstance();
		// this.environmentURL = prop.getProperty("environmentName");
		AccountData Login = LoginSync.getInstance().getLogin();
		Framework framework = new Framework();
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		AccountData accountData = framework.getData(AccountData.class, "newlogin");
		Header_FooterBarData HeaderBar_Mobile1 = framework.getData(Header_FooterBarData.class, "l3category_Mobile");
		CheckoutData checKoutData = framework.getData(CheckoutData.class, "valid");
		// ProductDetailData productDetailData =
		// framework.getData(ProductDetailData.class, "SimpleProduct");
		try {
			browser.get(environmentURL);
			ExtentTest childTest1 = testListener.startChild("New User Register Test");
			childTest1.setDescription("This test verifies that new user is able to register");
			TestListner.extentMap.get().put("child1", childTest1);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			String loginUser = AccountPage_Mobile.NewRegister(accountData);
			// System.out.println(loginUser);
			SanCS.assertTrue((Login.getFirstName().toLowerCase().contains(loginUser.toLowerCase().replaceAll("[^0-9]", ""))),  "New User registration test");
			// Sign in with username
			ExtentTest childTest2 = testListener.startChild("Add Product to the Cart test");
			childTest2.setDescription(
					"This test verifies that new user is able to add product of price less than 500 into cart");
			TestListner.extentMap.get().put("child2", childTest2);
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);
			browser.get(environmentURL);
			HeaderBar_Mobile.clearCart();
			HeaderBar_Mobile.navigateToCategory(HeaderBar_Mobile1);
			ListingPage_Mobile ListingPage_Mobile = new ListingPage_Mobile(browser);
			List<ProductDetailData> productList = ListingPage_Mobile.getProductList();
			List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
			productlistwithPrizeFilter = productList
					.stream().filter(o -> Integer.parseInt(o.getProductPrize()) < 500
							& !o.getProductType().contains("Size") & !o.getProductType().contains("Shade"))
					.collect(Collectors.toList());
			boolean isAdded = false;
			for (ProductDetailData product : productlistwithPrizeFilter) {
				isAdded = ListingPage_Mobile.addToBagSimpleProduct(product);
				if (isAdded == true) {
					break;
				}
			}
			if(! isAdded)
			{
				ProductDetailData pd=ListingPage_Mobile.addAnyShadeProductFromAllProduct();
				if(pd.getProductName()!=null)
				{
					isAdded=true;
				}
			}
			SanCS.assertTrue(isAdded, "Adding product in cart test");
			ExtentTest childTest3 = testListener.startChild("Apply Reward Points Test");
			childTest3.setDescription("This test verifies that new user is able to apply reward points");
			TestListner.extentMap.get().put("child3", childTest3);
			CartPage_Mobile_New CartPage_Mobile = new CartPage_Mobile_New(browser);
			CheckOutPage_Mobile_New CheckOutPage_Mobile = new CheckOutPage_Mobile_New(browser);
			CartPage_Mobile.goToCart();
			boolean isRewardApplied = CartPage_Mobile.applyRewardPoint();
			//System.out.println(isRewardApplied);
			SanCS.assertTrue(isRewardApplied, "222");
			ExtentTest childTest4 = testListener.startChild("Fill New Address Test");
			childTest4.setDescription("This test verifies that new user is able to fill new address");
			TestListner.extentMap.get().put("child4", childTest4);
			CartPage_Mobile.gotoProccedtWithoutFreeBies();
			boolean addressAdded = CheckOutPage_Mobile.fillNewAddressLoginUser(checKoutData);
			//SanCS.assertTrue(addressAdded, "Adding of new address test");
			ExtentTest childTest5 = testListener.startChild("Checkout with help of Netbanking");
			childTest5.setDescription(
					"This test verifies that new user is able to do checkout through netbanking when cart price is less than 500");
			TestListner.extentMap.get().put("child5", childTest5);
			CheckOutPage_Mobile.netBankingCheckout();
			/*
			 * boolean isCheckedOutPayumoney = CheckOutPage_Mobile.payumoneyCheckout();
			 * SanCS.assertTrue(isCheckedOutPayumoney, "243");
			 */
			//SanCS.assertAll();
		} catch (Throwable e) {
			framework.logErrorWithSnapshot(browser, e);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {
			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();
		}

	}

	@Test(groups = { "Msanity","sanity","sanity_Live","N_sanity_Live"})
	public void Mobile_Verify_user_is_Able_To_Apply_and_Remove_CouponCode() throws URISyntaxException, Throwable {
		Framework framework = new Framework();
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		AccountData Login = LoginSync.getInstance().getLogin();
		Header_FooterBarData HeaderBar_Mobile2 = framework.getData(Header_FooterBarData.class, "sizenavigation_Mobile");
		CheckoutData checkoutData = framework.getData(CheckoutData.class, "valid");

		try {
			ExtentTest childTest1 = testListener.startChild("Existing User Login Test");
			childTest1.setDescription("This test verifies that user is able to login using existing credentials");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			String loggedUsername = AccountPage_Mobile.signInWithYourEmail(Login);
			SanCS.assertTrue(loggedUsername.toLowerCase().equals(Login.getFirstName()), "Existing user login test");

			ExtentTest childTest2 = testListener.startChild("Add Size Product into Cart Test");
			childTest2.setDescription("This test verifies that user is able to add any size product into cart");
			TestListner.extentMap.get().put("child2", childTest2);
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);
			browser.get(environmentURL);
			HeaderBar_Mobile.clearCart();
			HeaderBar_Mobile.navigateToCategory(HeaderBar_Mobile2);
			ListingPage_Mobile ListingPage_Mobile = new ListingPage_Mobile(browser);
			ProductDetailData SizeproductDetadata = ListingPage_Mobile.addAnySizeProductFromAllProduct();
			CartPage_Mobile_New CartPage_Mobile = new CartPage_Mobile_New(browser);
			boolean isAdded = CartPage_Mobile.isProductPresentInBag(SizeproductDetadata);
			SanCS.assertTrue(isAdded, "Add any size product into cart test");

			ExtentTest childTest3 = testListener.startChild("Apply Coupon Code Test");
			childTest3.setDescription("This test verifies that user is able to apply coupon code");
			TestListner.extentMap.get().put("child3", childTest3);
			CartPage_Mobile.goToCart();
			SanCS.assertTrue(CartPage_Mobile.ApplyCoupon(checkoutData), "150");

			ExtentTest childTest4 = testListener.startChild("Remove Coupon Code Test");
			childTest4.setDescription("This test verifies that user is able to remove coupon code");
			TestListner.extentMap.get().put("child4", childTest4);
			Boolean isCanceled = CartPage_Mobile.CancelCoupon();
			SanCS.assertTrue(isCanceled, "Remove coupon code test");
			CartPage_Mobile.checkOut();
		} catch (Throwable e) {
			framework.logErrorWithSnapshot(browser, e);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {
			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();
		}

	}

	@Test(groups = {"Msanity","sanity" ,"Mcheckout","checkout"})
	public void Mobile_Check_end_to_end_checkout_flow_with_guest_User_using_BhimUPI() throws Throwable {
		Header_FooterBarData HeaderBar_Mobile1 = framework.getData(Header_FooterBarData.class, "shadenavigation");
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert CS = new CustomSoftAssert(SanAssertData, browser);
		CheckoutData checkOutData = framework.getData(CheckoutData.class, "simplepay");
		try {
			ExtentTest childTest1 = testListener.startChild("Add Shaded Product into Cart Test");
			childTest1.setDescription("This test verifies that user is able to add shaded product into cart");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);
			HeaderBar_Mobile.clearCart();
			HeaderBar_Mobile.navigateToCategory(HeaderBar_Mobile1);
			ListingPage_Mobile ListingPage_Mobile = new ListingPage_Mobile(browser);
			ProductDetailData ShadeproductDetadata = ListingPage_Mobile.addAnyShadeProductFromAllProduct();
			CartPage_Mobile_New CartPage_Mobile = new CartPage_Mobile_New(browser);
			CS.assertTrue(CartPage_Mobile.isProductPresentInBag(ShadeproductDetadata),
					"Add shaded product into cart test");
			ExtentTest childTest2 = testListener.startChild("SimplePay Checkout Test");
			childTest2.setDescription("This test verifies that new user is able to checkout using simplepay");
			TestListner.extentMap.get().put("child2", childTest2);
			CheckOutPage_Mobile_New CheckOutPage_Mobile = new CheckOutPage_Mobile_New(browser);
			CartPage_Mobile.goToCart();
			CartPage_Mobile.gotoProccedtWithoutFreeBies();
			CheckOutPage_Mobile.fillBillingAddress(checkOutData);
			CheckOutPage_Mobile.shiptothisaddress();
			CheckOutPage_Mobile.checkoutWithBhim(checkOutData);
		} catch (Throwable e) {
			framework.captureScreenshot(browser);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {
			browser.quit();
		}
	}

	@Test(groups = { "todo" })
	public void Mobile_Check_end_to_end_checkout_flow_with_all_type_of_products_and_services() throws Throwable {

		Header_FooterBarData Servicenavigation = framework.getData(Header_FooterBarData.class, "servicequicklink");
		Header_FooterBarData brandNavigation = framework.getData(Header_FooterBarData.class, "brand");
		// ProductDetailData staticproductDetailData =
		// framework.getData(ProductDetailData.class, "staticcombo");

		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		browser.get(environmentURL);
		AccountData Login = LoginSync.getInstance().getLogin();
		CheckoutData checkOutData = framework.getData(CheckoutData.class, "simplepay");
		ProductDetailData productDetailData = framework.getData(ProductDetailData.class, "service");

		try {
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			AccountPage_Mobile.signInWithYourEmail(Login);

			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);
			HeaderBar_Mobile.clearCart();
			HeaderBar_Mobile.navigateToBrand(brandNavigation);
			ListingPage_Mobile ListingPage_Mobile = new ListingPage_Mobile(browser);
			ListingPage_Mobile.navigateToShopAll("Nykaa Cosmetic Products", environmentURL);
			ProductDetailData staticproductDetailData = ListingPage_Mobile.addAnyStaticProductFromAllProduct();
			// ListingPage_Mobile.addToBagSimpleProduct(staticproductDetailData);
			CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(browser);
			boolean isStaticProductadded = CartPage_Mobile.isProductPresentInBag(staticproductDetailData);
			SanCS.assertTrue(isStaticProductadded, "L65");
			HeaderBar_Mobile.navigateToQuickLink(Servicenavigation, environmentURL);

			ListingPage_Mobile.serviceAddToCart(productDetailData);
			boolean isServiceAdded = CartPage_Mobile.isProductPresentInBag(productDetailData);
			SanCS.assertTrue(isServiceAdded, "L66");
			CheckOutPage_Mobile CheckOutPage_Mobile = new CheckOutPage_Mobile(browser);
			CartPage_Mobile.checkOut();
			String checkouturl = browser.getCurrentUrl();
			if (browser.getCurrentUrl().contains(environmentURL.substring(environmentURL.lastIndexOf("/") + 1))) {
				CheckOutPage_Mobile.savedAddressSelect();
				// System.out.println(CheckOutPage_Mobile.isAddressSelected())
				// If signed in then it should land on shipping address page.
			} else {

				CheckOutPage_Mobile.fillNewAddressLoginUser(checkOutData);
			}
			boolean isPovApplied = CheckOutPage_Mobile.applyPOV();

			SanCS.assertTrue(isPovApplied, "L237");
			boolean isNetbanking = CheckOutPage_Mobile.netBankingCheckout();

			SanCS.assertTrue(isNetbanking, "L235");
			//SanCS.assertAll();
		} catch (Throwable e) {
			framework.captureScreenshot(browser);
			Reporter.log(e.toString(), true);
			Assert.fail();
		} finally {
			browser.quit();

		}

	}

	/*******************************************************
	 * AddToCartTestcase
	 *******************************************/

	@Test(groups = { "todo" })
	public void Dynamic_Combo_ProductPage_Add_To_BAg() throws Throwable, Throwable {
		PropertyConfiguration pf = new PropertyConfiguration();
		Properties prop = pf.getInstance();
		// this.environmentURL = prop.getProperty("environmentName");

		Framework framework = new Framework();
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert CS = new CustomSoftAssert(SanAssertData, browser);
		AccountData accountData = framework.getData(AccountData.class, "newlogin");
		AnalyticsData analyticsDataHomePage = framework.getData(AnalyticsData.class, "homepage");
		Header_FooterBarData HeaderBar_Mobile1 = framework.getData(Header_FooterBarData.class, "shadenavigation");
		try {
			browser.get(environmentURL);
			// AnalyticsInfoPage analyticsInfoPage = new
			// AnalyticsInfoPage(browser);
			// analyticsInfoPage.getEvent(analyticsDataHomePage);
			// analyticsInfoPage.getEvar(analyticsDataHomePage);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			AccountPage_Mobile.NewRegister(accountData);
			// CS.assertTrue(true, "268");
			// CS.assertTrue(true, "269");
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);
			HeaderBar_Mobile.navigateToCategory(HeaderBar_Mobile1);
			ProductPage_Mobile productPage = new ProductPage_Mobile(browser);
			CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(browser);
			/*
			 * productDetailData =
			 * productPage.addToBagShadeproductviaProductPagefromListing(
			 * ListingPage_Mobile.AllProducts()); String tagvalue3 = (String)
			 * ((JavascriptExecutor) browser).executeScript("return s.events;");
			 * System.out.println(tagvalue3);
			 * 
			 * CS.assertTrue(CartPage_Mobile.isProductPresentInBag(productDetailData),
			 * "96"); CS.assertTrue(true, "92");
			 */
			browser.navigate().to(environmentURL
					+ "/makeup/eyes/kajal/lakme-absolute-kohl-ultimate.html?root=catg_Bestsellers&ptype=product&brand=catg_bestsellers");
			;
			List<ProductDetailData> comboProductDetaillist = productPage.addDynamicCombo();
			CS.assertTrue(CartPage_Mobile.isComboProductsPresentInBag(comboProductDetaillist), "96A");
			CS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "96C");
			CS.assertTrue(productPage.isPeopleWhoboughtDisplayed(), "96D");
			browser.navigate().refresh();
			CS.assertTrue(productPage.addToWishlist(), "96B");

			//CS.assertAll();

		} catch (Throwable e) {

			Reporter.log(e.toString());
			Assert.fail();

		} finally {
			browser.close();
			browser.quit();
		}

	}

	/*********************************************************
	 * AccountTestCases
	 *******************************************************************************************************************************/
	@Test(groups = { "Msanity","sanity","sanity_Live","N_sanity_Live","login","Mlogin"  })
	public void Mobile_Verify_User_Is_Able_To_Login_With_Google() throws Throwable, Throwable {
		Framework framwork = new Framework();
		// PropertyConfiguration pf = new PropertyConfiguration();
		// Properties prop = pf.getInstance();
		// this.environmentURL = prop.getProperty("environmentName");

		AccountData accountData = framwork.getData(AccountData.class, "google");
		WebDriver browser = framwork.getBrowser("Msite");
		CustomSoftAssert sanCS = new CustomSoftAssert(SanAssertData, browser);
		try {
			ExtentTest childTest1 = testListener.startChild("User Login with Google+ Test");
			childTest1.setDescription("This test verifies that user is able to login with google plus");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			String FirstName = AccountPage_Mobile.signInWithGoogle(accountData);
			// System.out.println(AccountPage_Mobile.signInWithFacebook(accountData));
			sanCS.assertEquals(FirstName.toLowerCase(), accountData.getFirstName().toLowerCase(), "174");
			//sanCS.assertAll();
		} catch (Throwable e) {
			framework.logErrorWithSnapshot(browser, e);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {
			browser.quit();

		}

	}

	@Test(groups = {"Msanity","sanity","sanity_Live","N_sanity_Live","login","Mlogin" })
	public void Mobile_Verify_New_User_Registration() throws Throwable, Throwable {
		Framework framwork = new Framework();
		// PropertyConfiguration pf = new PropertyConfiguration();
		// Properties prop = pf.getInstance();
		// this.environmentURL = prop.getProperty("environmentName");

		AccountData accountData = framwork.getData(AccountData.class, "newlogin");
		WebDriver browser = framwork.getBrowser("Msite");
		CustomSoftAssert sanCS = new CustomSoftAssert(SanAssertData, browser);
		try {
			ExtentTest childTest1 = testListener.startChild("New User Register test");
			childTest1.setDescription("This test verifies that new user is able to register");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			String FirstName = AccountPage_Mobile.NewRegister(accountData);
			sanCS.assertTrue((accountData.getFirstName().toLowerCase().contains(FirstName.toLowerCase().replaceAll("[^0-9]", ""))),  "270");
			//sanCS.assertAll();
		} catch (Throwable e) {
			framwork.captureScreenshot(browser);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {
			LoginSync.getInstance().clearLoginLock(accountData);
			browser.quit();
		}
	}

	@Test( groups = {"Msanity","sanity","sanity_Live","N_sanity_Live","login","Mlogin"})
	public void Mobile_Verify_logout_functionality() throws Throwable, Throwable {
		AccountData accountData = LoginSync.getInstance().getLogin();
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		try {
			ExtentTest childTest1 = testListener.startChild("Logout test");
			childTest1.setDescription("This test verifies that user is able to logout");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			SanAssertCS.assertEquals(AccountPage_Mobile.signInWithYourEmail(accountData).toLowerCase(),
					accountData.getFirstName().toLowerCase(), "L1");
			browser.get(environmentURL);
			boolean isLogout = AccountPage_Mobile.LogOut();
			SanAssertCS.assertTrue(isLogout, "175L");

		} catch (Throwable e) {
			framework.captureScreenshot(browser);
			Reporter.log(e.toString());
			Assert.fail();

		} finally {
			LoginSync.getInstance().clearLoginLock(accountData);
			browser.quit();
		}
	}

	@Test(groups = { "todo","done" })
	public void Mobile_Verify_User_Is_Able_To_Login_With_Fb() throws Throwable, Throwable {

		AccountData accountData = framework.getData(AccountData.class, "facebook");
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		try {
			ExtentTest childTest1 = testListener.startChild("User Login with Facebook Test");
			childTest1.setDescription("This test verifies that user is able to login with facebook");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			String Firtsname = AccountPage_Mobile.signInWithFacebook(accountData);
			SanCS.assertEquals(Firtsname, accountData.getFirstName(), "173");
			// System.out.println(AccountPage_Mobile.signInWithGooglePlus(accountData));
			//SanCS.assertAll();

		} catch (Exception e) {
			framework.logErrorWithSnapshot(browser, e);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {
			LoginSync.getInstance().clearLoginLock(accountData);
			browser.quit();
		}
	}

	/**********************************************************
	 * ListingPage_MobileTestCase
	 *****************************************************************************************************************************/


	@Test(groups = { "Msanity","sanity" ,"sanity_Live","N_sanity_Live","Mlisting","listing"})

	public void Mobile_Check_for_L1_category_page() throws Throwable {

		Header_FooterBarData HeaderBar_MobileData = framework.getData(Header_FooterBarData.class, "l1category_Mobile");
		WebDriver browser = framework.getBrowser("Msite");
		AccountData Login = LoginSync.getInstance().getLogin();
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		try {
			ExtentTest childTest = testListener.startChild("Existing User Login test");
			childTest.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child", childTest);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			String LoggedUserName = AccountPage_Mobile.signInWithYourEmail(Login).toLowerCase();
			SanAssertCS.assertEquals(LoggedUserName, Login.getFirstName().toLowerCase(), "L1");

			ExtentTest childTest1 = testListener.startChild("Add any Shade/Size product from L1 category page widget test ");
			childTest1.setDescription("This test verifies that user is able to add any Shade/Size product from L1 category page widget into cart");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);
			HeaderBar_Mobile.clearCart();
			HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
			ListingPage_Mobile ListingPage_Mobile = new ListingPage_Mobile(browser);
			CartPage_Mobile_New CartPage_Mobile = new CartPage_Mobile_New(browser);
			ProductDetailData productDetailData1 = ListingPage_Mobile.addAnySimpleProductFromAllProducts();
			SanAssertCS.assertTrue(CartPage_Mobile.isProductPresentInBag(productDetailData1), "79C");

			ExtentTest childTest2 = testListener.startChild("Add any Shade/Size product from L1 category page all products section test ");
			childTest2.setDescription("This test verifies that user is able to add any Shade/Size product from L1 category page all products section into cart");
			TestListner.extentMap.get().put("child2", childTest2);
			HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
			ProductDetailData ProductDetailData = ListingPage_Mobile.addAnyShadeProductFromAllProduct();
			SanAssertCS.assertTrue(CartPage_Mobile.isProductPresentInBag(ProductDetailData), "79D");

			/*ExtentTest childTest5 = testListener.startChild("Add any product from L1 category page widget to wishlist test ");
			childTest5.setDescription("This test verifies that user is able to add any product from L1 category page widget section to wishlist");
			TestListner.extentMap.get().put("child5", childTest5);

			boolean widgetWishlistAdded = ListingPage_Mobile.addToWishList(false);
			SanAssertCS.assertTrue(widgetWishlistAdded, "79A");*/

			ExtentTest childTest6 = testListener.startChild("Add any product from L1 category page all products to wishlist test ");
			childTest6.setDescription("This test verifies that user is able to add any product from L1 category page all products section to wishlist");
			TestListner.extentMap.get().put("child6", childTest6);
			HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
			boolean allProductWishlistAdded = ListingPage_Mobile.addToWishList(false);
			SanAssertCS.assertTrue(allProductWishlistAdded, "79B");

			ExtentTest childTest7 = testListener.startChild("Verify Offer text test ");
			childTest7.setDescription("This test verifies the offer on products in all products section");
			TestListner.extentMap.get().put("child7", childTest7);
			HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
			String offerText = ListingPage_Mobile.verifyOfferOn(ListingPage_Mobile.allProducts());
			SanAssertCS.assertTrue(offerText != null, "79H");

			ExtentTest childTest8 = testListener.startChild("Scrolling till 4-5 slots test ");
			childTest8.setDescription("This test verifies that products are loaded successfully on L1 category page when user scrolls by 4 to 5 slots");
			TestListner.extentMap.get().put("child8", childTest8);

			ListingPage_Mobile.LoadMoreProducts(6,false);
			int value = ListingPage_Mobile.getProductCountOfAllProducts();
			if (value > 40) {
				SanAssertCS.assertTrue(true, "79E");
			}

			ExtentTest childTest11 = testListener.startChild("Back to Top test ");
			childTest11.setDescription("This test verifies that Back To Top functionality works fine on L1 category page");
			TestListner.extentMap.get().put("child11", childTest11);

			SanAssertCS.assertTrue(ListingPage_Mobile.backToTop(), "79K");

			ExtentTest childTest9 = testListener.startChild("Verify Apply Sort test ");
			childTest9.setDescription("This test verifies the user is able to apply sorting");
			TestListner.extentMap.get().put("child9", childTest9);

			List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
			productList = ListingPage_Mobile.ApplySort("NAME");
			List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
			productListFromUI.addAll(productList);
			System.out.println(productListFromUI.toString());

			//Needs to Update---
			//Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName().compareTo(a2.getProductName()));
			//SanAssertCS.assertEquals(productListFromUI, productList, "79F");

			ExtentTest childTest10 = testListener.startChild("Apply Filter test ");
			childTest10.setDescription("This test verifies that user is able to apply filter");
			TestListner.extentMap.get().put("child10", childTest10);
			HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
			List<ProductDetailData> productListPriceFilter = ListingPage_Mobile.ApplyFilter("Price",
					"Rs. 0 - Rs. 499");
			List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
			// productlistwithPrizeFilter.addAll(productListPriceFilter);
			productlistwithPrizeFilter = productListPriceFilter.stream()
					.filter(o -> Integer.parseInt(o.getProductPrize()) < 500).collect(Collectors.toList());
			System.out.println(productListPriceFilter);
			System.out.println(productlistwithPrizeFilter);
			SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "79G");

		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.captureScreenshot(browser);
			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally {

			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();
		}
	}

	@Test(groups = { "Msanity","sanity","Mlisting","listing"})
	public void Mobile_Check_for_L2_category_page() throws Throwable {
		Header_FooterBarData HeaderBar_MobileData = framework.getData(Header_FooterBarData.class, "sizenavigation_Mobile");
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		AccountData Login = LoginSync.getInstance().getLogin();
		try {
			ExtentTest childTest = testListener.startChild("Existing User Login test");
			childTest.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child", childTest);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			String LoggedUserName = AccountPage_Mobile.signInWithYourEmail(Login).toLowerCase();
			SanAssertCS.assertEquals(LoggedUserName, Login.getFirstName().toLowerCase(), "L1");

			ExtentTest childTest1 = testListener.startChild("Add any Shade/Size product from L2 category page widget test ");
			childTest1.setDescription("This test verifies that user is able to add any Shade/Size product from L2 category page widget into cart");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);

			HeaderBar_Mobile.clearCart();
			HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
			ListingPage_Mobile ListingPage_Mobile = new ListingPage_Mobile(browser);

			CartPage_Mobile_New CartPage_Mobile = new CartPage_Mobile_New(browser);
			ProductDetailData productDetailData1 = ListingPage_Mobile.addAnySizeProductFromWidget();
			SanAssertCS.assertTrue(CartPage_Mobile.isProductPresentInBag(productDetailData1), "80C");

			ExtentTest childTest2 = testListener.startChild("Add any Shade/Size product from L2 category page all products section test ");
			childTest2.setDescription("This test verifies that user is able to add any Shade/Size product from L2 category page all products section into cart");
			TestListner.extentMap.get().put("child2", childTest2);

			HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
			ProductDetailData ProductDetailData = ListingPage_Mobile.addAnySizeProductFromAllProduct();
			SanAssertCS.assertTrue(CartPage_Mobile.isProductPresentInBag(ProductDetailData), "80D");

			ExtentTest childTest5 = testListener.startChild("Add any product from L2 category page widget to wishlist test ");
			childTest5.setDescription("This test verifies that user is able to add any product from L2 category page widget section to wishlist");
			TestListner.extentMap.get().put("child5", childTest5);

			browser.navigate().refresh();
			HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
			boolean widgetWishlistAdded = ListingPage_Mobile.addToWishList(true);
			SanAssertCS.assertTrue(widgetWishlistAdded, "80A");

			ExtentTest childTest6 = testListener.startChild("Add any product from L2 category page all products to wishlist test ");
			childTest6.setDescription("This test verifies that user is able to add any product from L2 category page all products section to wishlist");
			TestListner.extentMap.get().put("child6", childTest6);
			HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
			boolean allProductWishlistAdded = ListingPage_Mobile.addToWishList(false);
			SanAssertCS.assertTrue(allProductWishlistAdded, "80B");

			ExtentTest childTest7 = testListener.startChild("Verify Offer text test ");
			childTest7.setDescription("This test verifies the offer on products in all products section");
			TestListner.extentMap.get().put("child7", childTest7);
			HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
			String offerText = ListingPage_Mobile.verifyOfferOn(ListingPage_Mobile.allProducts());
			SanAssertCS.assertTrue(offerText != null, "80H");

			ExtentTest childTest8 = testListener.startChild("Scrolling till 4-5 slots test ");
			childTest8.setDescription("This test verifies that products are loaded successfully on L2 category page when user scrolls by 4 to 5 slots");
			TestListner.extentMap.get().put("child8", childTest8);
			HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
			ListingPage_Mobile.LoadMoreProducts(6,false);
			int value = ListingPage_Mobile.getProductCountOfAllProducts();
			if (value > 40) {
				SanAssertCS.assertTrue(true, "80E");
			} else {
				SanAssertCS.assertTrue(false, "80E");
			}

			ExtentTest childTest11 = testListener.startChild("Back to Top test ");
			childTest11.setDescription("This test verifies that Back To Top functionality works fine on L2 category page");
			TestListner.extentMap.get().put("child11", childTest11);
			HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
			SanAssertCS.assertTrue(ListingPage_Mobile.backToTop());

			ExtentTest childTest9 = testListener.startChild("Verify Apply Sort test ");
			childTest9.setDescription("This test verifies the user is able to apply sorting");
			TestListner.extentMap.get().put("child9", childTest9);
			HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
			List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
			productList = ListingPage_Mobile.ApplySort("NAME");
			List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
			productListFromUI.addAll(productList);
			System.out.println(productListFromUI.toString());
			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
					.compareTo(a2.getProductName()));
			SanAssertCS.assertEquals(productListFromUI, productList, "80F");

			ExtentTest childTest10 = testListener.startChild("Apply Filter test ");
			childTest10.setDescription("This test verifies that user is able to apply filter");
			TestListner.extentMap.get().put("child10", childTest10);
			HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
			List<ProductDetailData> productListPriceFilter = ListingPage_Mobile.ApplyFilter("Price",
					"Rs. 0 - Rs. 499");
			List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
			productlistwithPrizeFilter = productListPriceFilter.stream()
					.filter(o -> Integer.parseInt(o.getProductPrize()) <= 500).collect(Collectors.toList());
			System.out.println(productListPriceFilter);
			System.out.println(productlistwithPrizeFilter);
			SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "80G");

		} catch (Exception e) {
			framework.captureScreenshot(browser);
			Reporter.log(e.toString());
			Assert.fail();

		} finally {

			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();
		}
	}

	@Test(groups = { "Msanity","sanity","Mlisting","listing"})
	public void Mobile_Check_for_L3_category_page() throws Throwable {

		Header_FooterBarData HeaderBar_MobileData = framework.getData(Header_FooterBarData.class, "l3category_Mobile");
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		AccountData Login = LoginSync.getInstance().getLogin();
		try {
			ExtentTest childTest = testListener.startChild("Existing User Login test");
			childTest.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child", childTest);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			String LoggedUserName = AccountPage_Mobile.signInWithYourEmail(Login).toLowerCase();
			SanAssertCS.assertEquals(LoggedUserName, Login.getFirstName().toLowerCase(), "L1");

			ExtentTest childTest1 = testListener.startChild("Add any Shade/Size product from L3 category page widget test ");
			childTest1.setDescription("This test verifies that user is able to add any Shade/Size product from L3 category page widget into cart");
			TestListner.extentMap.get().put("child1", childTest1);
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);

			{
				HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
				HeaderBar_Mobile.clearCart();
				HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
				ListingPage_Mobile ListingPage_Mobile = new ListingPage_Mobile(browser);

				CartPage_Mobile_New CartPage_Mobile = new CartPage_Mobile_New(browser);
				ProductDetailData productDetailData1 = ListingPage_Mobile.addAnyShadeProductFromWidget();
				SanAssertCS.assertTrue(CartPage_Mobile.isProductPresentInBag(productDetailData1), "81C");

				ExtentTest childTest2 = testListener.startChild("Add any Shade/Size product from L3 category page all products section test ");
				childTest2.setDescription("This test verifies that user is able to add any Shade/Size product from L3 category page all products section into cart");
				TestListner.extentMap.get().put("child2", childTest2);
				HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
				ProductDetailData ProductDetailData = ListingPage_Mobile.addAnyShadeProductFromAllProduct();
				SanAssertCS.assertTrue(CartPage_Mobile.isProductPresentInBag(ProductDetailData), "81D");
				ExtentTest childTest5 = testListener.startChild("Add any product from L3 category page widget to wishlist test ");
				childTest5.setDescription("This test verifies that user is able to add any product from L3 category page widget section to wishlist");
				TestListner.extentMap.get().put("child5", childTest5);
				HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
				browser.navigate().refresh();
				boolean widgetWishlistAdded = ListingPage_Mobile.addToWishList(true);
				SanAssertCS.assertTrue(widgetWishlistAdded, "81A");

				ExtentTest childTest6 = testListener.startChild("Add any product from L3 category page all products to wishlist test ");
				childTest6.setDescription("This test verifies that user is able to add any product from L3 category page all products section to wishlist");
				TestListner.extentMap.get().put("child6", childTest6);
				HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
				boolean allProductWishlistAdded = ListingPage_Mobile.addToWishList(false);
				SanAssertCS.assertTrue(allProductWishlistAdded, "81B");

				ExtentTest childTest7 = testListener.startChild("Verify Offer text test ");
				childTest7.setDescription("This test verifies the offer on products in all products section");
				TestListner.extentMap.get().put("child7", childTest7);
				String offerText = ListingPage_Mobile.verifyOfferOn(ListingPage_Mobile.allProducts());
				SanAssertCS.assertTrue(offerText != null, "81H");
				HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
				ExtentTest childTest8 = testListener.startChild("Scrolling till 4-5 slots test ");
				childTest8.setDescription("This test verifies that products are loaded successfully on L3 category page when user scrolls by 4 to 5 slots");
				TestListner.extentMap.get().put("child8", childTest8);
				HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
				ListingPage_Mobile.LoadMoreProducts(6,false);
				int value = ListingPage_Mobile.getProductCountOfAllProducts();
				if (value > 40) {
					SanAssertCS.assertTrue(true, "81E");
				} else {
					SanAssertCS.assertTrue(false, "81E");
				}

				ExtentTest childTest11 = testListener.startChild("Back to Top test ");
				childTest11.setDescription("This test verifies that Back To Top functionality works fine on L3 category page");
				TestListner.extentMap.get().put("child11", childTest11);
				HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
				SanAssertCS.assertTrue(ListingPage_Mobile.backToTop());

				ExtentTest childTest9 = testListener.startChild("Verify Apply Sort test ");
				childTest9.setDescription("This test verifies the user is able to apply sorting");
				TestListner.extentMap.get().put("child9", childTest9);
				HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
				List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
				productList = ListingPage_Mobile.ApplySort("NAME");
				List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
				productListFromUI.addAll(productList);
				System.out.println(productListFromUI.toString());
				Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
						.compareTo(a2.getProductName()));
				SanAssertCS.assertEquals(productListFromUI, productList, "81F");

				ExtentTest childTest10 = testListener.startChild("Apply Filter test ");
				childTest10.setDescription("This test verifies that user is able to apply filter");
				TestListner.extentMap.get().put("child10", childTest10);
				HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
				List<ProductDetailData> productListPriceFilter = ListingPage_Mobile.ApplyFilter("Price","Rs. 0 - Rs. 499");
				List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
				productlistwithPrizeFilter = productListPriceFilter.stream()
						.filter(o -> Integer.parseInt(o.getProductPrize()) <= 500).collect(Collectors.toList());
				System.out.println(productListPriceFilter);
				System.out.println(productlistwithPrizeFilter);
				SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "81G");

			}
		}

		catch (Exception e) {
			framework.captureScreenshot(browser);
			Reporter.log(e.toString());
			Assert.fail();

		} finally {

			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();
		}

	}

	@Test(groups = {  "Msanity","sanity","sanity_Live","N_sanity_Live","Mlisting","listing"})
	public void Mobile_Check_for_Luxe_category_page() throws Throwable {

		//Header_FooterBarData HeaderBar_MobileData = framework.getData(Header_FooterBarData.class, "luxebrand");
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		AccountData Login = LoginSync.getInstance().getLogin();
		try {
			ExtentTest childTest = testListener.startChild("Existing User Login test");
			childTest.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child", childTest);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			String LoggedUserName = AccountPage_Mobile.signInWithYourEmail(Login).toLowerCase();
			SanAssertCS.assertEquals(LoggedUserName, Login.getFirstName().toLowerCase(), "L1");

			ExtentTest childTest1 = testListener.startChild("Add any Shade/Size product from Luxe category page all products test ");
			childTest1.setDescription("This test verifies that user is able to add any Shade/Size product from Luxe category page all products section into cart");
			TestListner.extentMap.get().put("child1", childTest1);

			browser.get(environmentURL);
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);

			{
				HeaderBar_Mobile.clearCart();
				// HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
				ListingPage_Mobile ListingPage_Mobile = new ListingPage_Mobile(browser);
				browser.get(environmentURL + "/luxe/makeup.html?root=nav_2&dir=desc&order=popularity");
				// String offerText =
				// ListingPage_Mobile.verifyOfferOn(ListingPage_Mobile.allProducts());
				// SanAssertCS.assertTrue(offerText!= null, "83H");
				/*
				 * boolean widgetWishlistAdded =
				 * ListingPage_Mobile.addToWishList(ListingPage_Mobile.widgetProducts());
				 * SanAssertCS.assertTrue(widgetWishlistAdded,"83A"); boolean
				 * allProductWishlistAdded =
				 * ListingPage_Mobile.addToWishList(ListingPage_Mobile.allProducts());
				 * SanAssertCS.assertTrue(allProductWishlistAdded,"83B");
				 */
				// ListingPage_Mobile.addAnyShadeProductFromWidget();
				// ListingPage_Mobile.verifyOfferOn(ListingPage_Mobile.allProducts());
				CartPage_Mobile_New CartPage_Mobile = new CartPage_Mobile_New(browser);
				/*
				 * ProductDetailData productDetailData1 =
				 * ListingPage_Mobile.addAnyShadeProductFromWidget();
				 * SanAssertCS.assertTrue(CartPage_Mobile.isProductPresentInBag(
				 * productDetailData1),"81C");
				 */
				ProductDetailData ProductDetailData = ListingPage_Mobile.addAnyShadeProductFromAllProduct();
				SanAssertCS.assertTrue(CartPage_Mobile.isProductPresentInBag(ProductDetailData), "83D");

				ExtentTest childTest2 = testListener.startChild("Add any product from Luxe category page all products to wishlist test ");
				childTest2.setDescription("This test verifies that user is able to add any product from Luxe category page all products section to wishlist");
				TestListner.extentMap.get().put("child2", childTest2);
				browser.get(environmentURL + "/luxe/makeup.html?root=nav_2&dir=desc&order=popularity");
				browser.navigate().refresh();
				boolean allProductWishlistAdded = ListingPage_Mobile.addToWishList(false);
				SanAssertCS.assertTrue(allProductWishlistAdded, "83B");

				ExtentTest childTest8 = testListener.startChild("Scrolling till 4-5 slots test ");
				childTest8.setDescription("This test verifies that products are loaded successfully on Luxe category page when user scrolls by 4 to 5 slots");
				TestListner.extentMap.get().put("child8", childTest8);
				browser.get(environmentURL + "/luxe/makeup.html?root=nav_2&dir=desc&order=popularity");
				ListingPage_Mobile.LoadMoreProducts(6,false);
				int value = ListingPage_Mobile.getProductCountOfAllProducts();
				if (value > 20) {
					SanAssertCS.assertTrue(true, "83E");
				} else {
					SanAssertCS.assertTrue(false, "83E");
				}

				ExtentTest childTest11 = testListener.startChild("Back to Top test ");
				childTest11.setDescription("This test verifies that Back To Top functionality works fine on Luxe category page");
				TestListner.extentMap.get().put("child11", childTest11);
				browser.get(environmentURL + "/luxe/makeup.html?root=nav_2&dir=desc&order=popularity");
				SanAssertCS.assertTrue(ListingPage_Mobile.backToTop());

				ExtentTest childTest9 = testListener.startChild("Verify Apply Sort test ");
				childTest9.setDescription("This test verifies the user is able to apply sorting");
				TestListner.extentMap.get().put("child9", childTest9);
				browser.get(environmentURL + "/luxe/makeup.html?root=nav_2&dir=desc&order=popularity");
				List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
				productList = ListingPage_Mobile.ApplySort("NAME");
				List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
				productListFromUI.addAll(productList);
				System.out.println(productListFromUI.toString());
				Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
						.compareTo(a2.getProductName()));
				SanAssertCS.assertEquals(productListFromUI, productList, "83F");

				ExtentTest childTest10 = testListener.startChild("Apply Filter test ");
				childTest10.setDescription("This test verifies that user is able to apply filter");
				TestListner.extentMap.get().put("child10", childTest10);
				browser.get(environmentURL + "/luxe/makeup.html?root=nav_2&dir=desc&order=popularity");
				List<ProductDetailData> productListPriceFilter = ListingPage_Mobile.ApplyFilter("Price",
						"Rs. 1000 - Rs. 1999");
				List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
				// productlistwithPrizeFilter.addAll(productListPriceFilter);
				productlistwithPrizeFilter = productListPriceFilter.stream()
						.filter(o -> Integer.parseInt(o.getProductPrize()) >= 1000
						& Integer.parseInt(o.getProductPrize()) <= 2000)
						.collect(Collectors.toList());
				SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "83G");

			}
		}

		catch (Exception e) {
			framework.captureScreenshot(browser);
			Reporter.log(e.toString());
			Assert.fail();

		} finally {
			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();
		}

	}


	@Test(groups = {  "Msanity","sanity","sanity_Live","N_sanity_Live","Mlisting","listing"})

	public void Mobile_Check_Brand_Page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "brand_mobile");
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		AccountData Login = LoginSync.getInstance().getLogin();
		try {
			ExtentTest childTest = testListener.startChild("Existing User Login test");
			childTest.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child", childTest);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			String LoggedUserName = AccountPage_Mobile.signInWithYourEmail(Login).toLowerCase();
			SanAssertCS.assertEquals(LoggedUserName, Login.getFirstName().toLowerCase(), "L1");
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);

			ExtentTest childTest2 = testListener.startChild("Add Simple Product to Cart Test");
			childTest2.setDescription("This test verifies that new user is able to add simple product to cart");
			TestListner.extentMap.get().put("child2", childTest2);
			browser.get(environmentURL);
			HeaderBar_Mobile.clearCart();
			HeaderBar_Mobile.navigateToBrand(headerbarData);
			ListingPage_Mobile ListingPage_Mobile = new ListingPage_Mobile(browser);
			CartPage_Mobile_New cartPage = new CartPage_Mobile_New(browser);
			ProductDetailData productDetailData1 = ListingPage_Mobile.addAnySimpleProductFromAllProducts();
			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(productDetailData1), "82C");

			ExtentTest childTest3 = testListener.startChild("Add to Wishlist Test");
			childTest3.setDescription("This test verifies that new user is able to add product to wishlist");
			TestListner.extentMap.get().put("child3", childTest3);
			browser.navigate().refresh();
			HeaderBar_Mobile.navigateToBrand(headerbarData);
			boolean widgetWishlistAdded = ListingPage_Mobile.addToWishList(false);
			SanAssertCS.assertTrue(widgetWishlistAdded, "82A");

			ExtentTest childTest4 = testListener.startChild("Add Shaded Product to Cart Test");
			childTest4.setDescription("This test verifies that new user is able to add shade product to cart");
			TestListner.extentMap.get().put("child4", childTest4);
			HeaderBar_Mobile.navigateToBrand(headerbarData);
			ProductDetailData ProductDetailData = ListingPage_Mobile.addAnyShadeProductFromAllProduct();
			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData), "82D");

			ExtentTest childTest5 = testListener.startChild("Verify Offer Test");
			childTest5.setDescription("This test verifies that new user is able to view offer");
			TestListner.extentMap.get().put("child5", childTest5);
			HeaderBar_Mobile.navigateToBrand(headerbarData);
			String offerText = ListingPage_Mobile.verifyOfferOn(ListingPage_Mobile.allProducts());
			childTest5.log(LogStatus.INFO, offerText);
			SanAssertCS.assertTrue(offerText != null, "82H");

			ExtentTest childTest7 = testListener.startChild("Add to Wishlist from All Products Widget Test");
			childTest7.setDescription(
					"This test verifies that new user is able to add product to wishlist from all products widget");
			TestListner.extentMap.get().put("child7", childTest7);
			HeaderBar_Mobile.navigateToBrand(headerbarData);
			boolean allProductWishlistAdded = ListingPage_Mobile.addToWishList(false);
			SanAssertCS.assertTrue(allProductWishlistAdded, "82B");

			ExtentTest childTest8 = testListener.startChild("Scroll 4-5 slot Test");
			childTest8.setDescription("This test verifies that new user is able to scroll the page 4-5 times");
			TestListner.extentMap.get().put("child8", childTest8);
			HeaderBar_Mobile.navigateToBrand(headerbarData);
			ListingPage_Mobile.ScrollDown("1000", 6);
			int value = ListingPage_Mobile.getProductCountOfAllProducts();
			if (value > 40) {
				SanAssertCS.assertTrue(true, "82E");
			} else {
				SanAssertCS.assertTrue(false, "82E");
			}

			ExtentTest childTest11 = testListener.startChild("Back to Top test ");
			childTest11.setDescription("This test verifies that Back To Top functionality works fine on L3 category page");
			TestListner.extentMap.get().put("child11", childTest11);
			HeaderBar_Mobile.navigateToBrand(headerbarData);
			SanAssertCS.assertTrue(ListingPage_Mobile.backToTop());

			ExtentTest childTest9 = testListener.startChild("Apply Sort Test");
			childTest9.setDescription("This test verifies that new user is able to apply sort");
			TestListner.extentMap.get().put("child9", childTest9);
			List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
			HeaderBar_Mobile.navigateToBrand(headerbarData);
			productList = ListingPage_Mobile.ApplySort("NAME");
			List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
			productListFromUI.addAll(productList);
			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
					.compareTo(a2.getProductName()));
			SanAssertCS.assertEquals(productListFromUI, productList, "82F");

			ExtentTest childTest10 = testListener.startChild("Apply Filter Test");
			childTest10.setDescription("This test verifies that new user is able to apply filter");
			TestListner.extentMap.get().put("child10", childTest10);
			List<ProductDetailData> productListPriceFilter = ListingPage_Mobile.ApplyFilter("Price", "Rs. 0 - Rs. 500");
			List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
			HeaderBar_Mobile.navigateToBrand(headerbarData);
			productlistwithPrizeFilter.addAll(productListPriceFilter);
			productListPriceFilter.stream().filter(o -> Integer.parseInt(o.getProductPrize()) <= 500)
			.collect(Collectors.toList());
			SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "82G");
		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser,e);
			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally {

			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();
		}
	}


	@Test(groups = { "Msanity","sanity","sanity_Live","N_sanity_Live","Mlisting","listing"})

	public void Mobile_Check_Luxe_Brand_Page() throws Throwable {

		//Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "brand");
		WebDriver browser = framework.getBrowser("Msite");
		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "brand_mobile1");
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		AccountData Login = LoginSync.getInstance().getLogin();
		try {
			ExtentTest childTest = testListener.startChild("Existing User Login test");
			childTest.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child", childTest);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);
			String LoggedUserName = AccountPage_Mobile.signInWithYourEmail(Login).toLowerCase();
			SanAssertCS.assertEquals(LoggedUserName, Login.getFirstName().toLowerCase(), "L1");

			// "/luxe/brands/mac.html";
			HeaderBar_Mobile headerbar = new HeaderBar_Mobile(browser);
			ExtentTest childTest2 = testListener.startChild("Add Shaded Product to Cart Test");
			childTest2.setDescription("This test verifies that new user is able to add shade product to cart");
			TestListner.extentMap.get().put("child2", childTest2);
			browser.get(environmentURL);
			headerbar.clearCart();
			HeaderBar_Mobile.navigateToBrand(headerbarData);
/*
			if(!(environmentURL.contains(".com/"))){
				environmentURL=(environmentURL+"/");
			}
			browser.get(environmentURL + "luxe/brands/mac.html?dir=desc&order=popularity");*/
			ListingPage_Mobile listingPage = new ListingPage_Mobile(browser);
			CartPage_Mobile_New cartPage = new CartPage_Mobile_New(browser);
			/*
			 * ProductDetailData productDetailData1 =
			 * listingPage.addAnySimpleProductFromWidget();
			 * SanAssertCS.assertTrue(cartPage.isProductPresentInBag(
			 * productDetailData1),"82C");
			 */
			cartPage.closeGetAppPopUp();
			HeaderBar_Mobile.navigateToBrand(headerbarData);
			ProductDetailData ProductDetailData = listingPage.addAnyShadeProductFromAllProduct();
			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData), "84D");

			/*
			 * boolean CustomerAlsoViewedAvailable;
			 * CustomerAlsoViewedAvailable =
			 * listingPage.isCustomerAlsoViewedPresent(listingPage.
			 * allProducts());
			 * SanAssertCS.assertTrue(CustomerAlsoViewedAvailable, "82I");
			 * 
			 * String offerText =
			 * listingPage.verifyOfferOn(listingPage.allProducts());
			 * SanAssertCS.assertTrue(offerText != null, "82H");
			 */
			/*
			 * boolean widgetWishlistAdded =
			 * listingPage.addToWishList(listingPage.widgetProducts());
			 * SanAssertCS.assertTrue(widgetWishlistAdded,"84A");
			 */
			ExtentTest childTest3 = testListener.startChild("Add to Wishlist from All Products Widget Test");
			childTest3.setDescription(
					"This test verifies that new user is able to add product to wishlist from all products widget");
			TestListner.extentMap.get().put("child3", childTest3);
			browser.navigate().refresh();
			HeaderBar_Mobile.navigateToBrand(headerbarData);
			boolean allProductWishlistAdded = listingPage.addToWishList(false);
			SanAssertCS.assertTrue(allProductWishlistAdded, "84B");

			ExtentTest childTest4 = testListener.startChild("Scroll 4-5 slot Test");
			childTest4.setDescription("This test verifies that new user is able to scroll the page 4-5 times");
			TestListner.extentMap.get().put("child4", childTest4);
			listingPage.LoadMoreProducts(3, false);;
			int value = listingPage.getProductCountOfAllProducts();
			if (value > 20) {
				SanAssertCS.assertTrue(true, "84E");
			} else {
				SanAssertCS.assertTrue(false, "84E");
			}

			ExtentTest childTest11 = testListener.startChild("Back to Top test ");
			childTest11.setDescription("This test verifies that Back To Top functionality works fine on L3 category page");
			TestListner.extentMap.get().put("child11", childTest11);

			SanAssertCS.assertTrue(listingPage.backToTop());

			ExtentTest childTest5 = testListener.startChild("Apply Sort Test");
			childTest5.setDescription("This test verifies that new user is able to apply sort");
			TestListner.extentMap.get().put("child5", childTest5);
			List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
			productList = listingPage.ApplySort("NAME");
			List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
			productListFromUI.addAll(productList);
			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
					.compareTo(a2.getProductName()));
			SanAssertCS.assertEquals(productListFromUI, productList, "84F");

			ExtentTest childTest6 = testListener.startChild("Apply Filter Test");
			childTest6.setDescription("This test verifies that new user is able to apply filter");
			TestListner.extentMap.get().put("child6", childTest6);
			List<ProductDetailData> productListPriceFilter = listingPage.ApplyFilter("Price", "Rs. 2000 - Rs. 3999");
			List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
			// productlistwithPrizeFilter.addAll(productListPriceFilter);
			productlistwithPrizeFilter = productListPriceFilter.stream().filter(o -> Integer.parseInt(o.getProductPrize()) <= 3999 && Integer.parseInt(o.getProductPrize()) >= 2000).collect(Collectors.toList());

			//productlistwithPrizeFilter = productListPriceFilter.stream()
			//		.filter(o -> Integer.parseInt(o.getProductPrize()) <= 500).collect(Collectors.toList());

			SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "84G");
		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser,e);
			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally {

			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();
		}
	}

	/********************************************************
	 * ProductPageTestCases
	 **************************************************************************************************************************/

	@Test(groups = { "Msanity","sanity","sanity_Live","N_sanity_Live","productPage", "MproductPage"})
	public void Mobile_simple_Product_Page_for_L1_category() throws Throwable {

		Header_FooterBarData HeaderBar_MobileData = framework.getData(Header_FooterBarData.class, "l1category_Mobile");
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		AccountData Login = LoginSync.getInstance().getLogin();
		try {
			ExtentTest childTest = testListener.startChild("Existing User Login test");
			childTest.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child", childTest);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			String LoggedUserName = AccountPage_Mobile.signInWithYourEmail(Login).toLowerCase();
			SanAssertCS.assertEquals(LoggedUserName, Login.getFirstName().toLowerCase(), "L1");
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);

			ExtentTest childTest3 = testListener.startChild("Customer Also Viewed Test");
			childTest3.setDescription("This test verifies that Customer Also Viewed section is present on Product page");
			TestListner.extentMap.get().put("child3", childTest3);

			browser.get(environmentURL);
			HeaderBar_Mobile.clearCart();

			HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
			ProductPage_Mobile productPage = new ProductPage_Mobile(browser);
			CartPage_Mobile_New CartPage_Mobile = new CartPage_Mobile_New(browser);
			ProductDetailData ProductDetailData1 = productPage.addAnySimpleProductFromProductPage();

			SanAssertCS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "52C");

			ExtentTest childTest4 = testListener.startChild("Customer Also Bought Test");
			childTest4.setDescription("This test verifies that Customer Also Bought section is present on Product page");
			TestListner.extentMap.get().put("child4", childTest4);


			SanAssertCS.assertTrue(productPage.isPeopleWhoboughtDisplayed(), "52D");

			ExtentTest childTest2 = testListener.startChild("Add Simple Product to Cart from Product Page Test");
			childTest2.setDescription("This test verifies that new user is able to add simple product to cart from product page");
			TestListner.extentMap.get().put("child2", childTest2);
			String checkouturl=  browser.getCurrentUrl();
			SanAssertCS.assertTrue(CartPage_Mobile.isProductPresentInBag(ProductDetailData1), "52A");

			ExtentTest childTest5 = testListener.startChild("Add Simple Product to WishList from Product Page Test");
			childTest5.setDescription("This test verifies that new user is able to add simple product to Wishlist from product page");
			TestListner.extentMap.get().put("child5", childTest5);
            browser.get(checkouturl);
			SanAssertCS.assertTrue(productPage.addToWishlist(), "52B");

			ExtentTest childTest6 = testListener.startChild("Add Simple Product from WishList to cart Test");
			childTest6.setDescription("This test verifies that new user is able to add simple product from Wishlist to cart");
			TestListner.extentMap.get().put("child6", childTest6);

			HeaderBar_Mobile.clearCart();
			AccountPage_Mobile.navigateToWishlist();
			ListingPage_Mobile ListingPage_Mobile = new ListingPage_Mobile(browser);
			ProductDetailData wishlistProductDetaildata = ListingPage_Mobile.addAnySimpleProductFromWishlist();
			SanAssertCS.assertTrue(CartPage_Mobile.isProductPresentInBag(wishlistProductDetaildata), "52E");


		} catch (Exception e) {
			framework.captureScreenshot(browser);
			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally {

			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();
		}
	}


	@Test(groups = { "Msanity","sanity","sanity_Live","N_sanity_Live" ,"productPage", "MproductPage","Sourabh"})

	public void Mobile_Verify_Add_to_Bag_Shade_Product_From_Product_Page() throws Throwable {

		Header_FooterBarData HeaderBar_MobileData = framework.getData(Header_FooterBarData.class, "shadenavigation_Mobile");
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		AccountData Login = LoginSync.getInstance().getLogin();
		try {
			ExtentTest childTest = testListener.startChild("Existing User Login test");
			childTest.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child", childTest);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			String LoggedUserName = AccountPage_Mobile.signInWithYourEmail(Login).toLowerCase();
			SanAssertCS.assertEquals(LoggedUserName, Login.getFirstName().toLowerCase(), "L1");
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);

			ExtentTest childTest3 = testListener.startChild("Customer Also Viewed Test");
			childTest3.setDescription("This test verifies that Customer Also Viewed section is present on Product page");
			TestListner.extentMap.get().put("child3", childTest3);

			browser.get(environmentURL);
			HeaderBar_Mobile.clearCart();

			HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
			ProductPage_Mobile productPage = new ProductPage_Mobile(browser);
			CartPage_Mobile_New CartPage_Mobile = new CartPage_Mobile_New(browser);
			ProductDetailData ProductDetailData1 = productPage.addAnyShadeProductFromProductPage();

			SanAssertCS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "53C");

			ExtentTest childTest4 = testListener.startChild("Customer Also Bought Test");
			childTest4.setDescription("This test verifies that Customer Also Bought section is present on Product page");
			TestListner.extentMap.get().put("child4", childTest4);


			SanAssertCS.assertTrue(productPage.isPeopleWhoboughtDisplayed(), "53D");

			ExtentTest childTest2 = testListener.startChild("Add Shade Product to Cart from Product Page Test");
			childTest2.setDescription("This test verifies that new user is able to add shade product to cart from product page");
			TestListner.extentMap.get().put("child2", childTest2);

			SanAssertCS.assertTrue(CartPage_Mobile.isProductPresentInBag(ProductDetailData1), "53A");

			ExtentTest childTest5 = testListener.startChild("Add Shade Product to WishList from Product Page Test");
			childTest5.setDescription("This test verifies that new user is able to add shade product to Wishlist from product page");
			TestListner.extentMap.get().put("child5", childTest5);

			SanAssertCS.assertTrue(productPage.addToWishlist(), "53B");

			ExtentTest childTest6 = testListener.startChild("Add Shade Product from WishList to cart Test");
			childTest6.setDescription("This test verifies that new user is able to add shade product from Wishlist to cart");
			TestListner.extentMap.get().put("child6", childTest6);

			HeaderBar_Mobile.clearCart();
			AccountPage_Mobile.navigateToWishlist();
			ListingPage_Mobile ListingPage_Mobile = new ListingPage_Mobile(browser);
			ProductDetailData wishlistProductDetaildata = ListingPage_Mobile.addAnyShadeProductwishlist();
			SanAssertCS.assertTrue(CartPage_Mobile.isProductPresentInBag(wishlistProductDetaildata), "53F");



		} catch (Exception e) {
			framework.captureScreenshot(browser);
			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally {

			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();
		}
	}

	@Test(groups = { "Msanity","sanity" ,"productPage", "MproductPage", "local"})
	public void Mobile_Verify_Add_to_Bag_Size_Product_From_Product_Page() throws Throwable {

		Header_FooterBarData HeaderBar_MobileData = framework.getData(Header_FooterBarData.class, "l1category_Mobile");
		WebDriver browser = framework.getBrowser("Msite");
		//ObjectRepository locator = framework.getLocator(ObjectRepository.class, "MyOrderLocator");
		//System.out.println("==>>>>>>>>>>>>>>>>>>>> "+locator.getByLocator());
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		AccountData Login = LoginSync.getInstance().getLogin();
		try {
			ExtentTest childTest = testListener.startChild("Existing User Login test");
			childTest.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child", childTest);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			String LoggedUserName = AccountPage_Mobile.signInWithYourEmail(Login).toLowerCase();
			log.info("Testing of Logger Info");
			log.debug("Testing of Logger debug");
			log.error("Testing of Logger info");
			SanAssertCS.assertEquals(LoggedUserName, Login.getFirstName().toLowerCase(), "L1");
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);

			ExtentTest childTest3 = testListener.startChild("Customer Also Viewed Test");
			childTest3.setDescription("This test verifies that Customer Also Viewed section is present on Product page");
			TestListner.extentMap.get().put("child3", childTest3);

			browser.get(environmentURL);
			HeaderBar_Mobile.clearCart();

			HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
			ProductPage_Mobile productPage = new ProductPage_Mobile(browser);
			CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(browser);
			ProductDetailData ProductDetailData1 = productPage.addAnySizeProductFromProductPage();

			SanAssertCS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "54C");

			ExtentTest childTest4 = testListener.startChild("Customer Also Bought Test");
			childTest4.setDescription("This test verifies that Customer Also Bought section is present on Product page");
			TestListner.extentMap.get().put("child4", childTest4);


			SanAssertCS.assertTrue(productPage.isPeopleWhoboughtDisplayed(), "54D");

			ExtentTest childTest2 = testListener.startChild("Add Size Product to Cart from Product Page Test");
			childTest2.setDescription("This test verifies that new user is able to add Size product to cart from product page");
			TestListner.extentMap.get().put("child2", childTest2);

			SanAssertCS.assertTrue(CartPage_Mobile.isProductPresentInBag(ProductDetailData1), "54A");

			ExtentTest childTest5 = testListener.startChild("Add Size Product to WishList from Product Page Test");
			childTest5.setDescription("This test verifies that new user is able to add Size product to Wishlist from product page");
			TestListner.extentMap.get().put("child5", childTest5);

			SanAssertCS.assertTrue(productPage.addToWishlist(), "54B");

			ExtentTest childTest6 = testListener.startChild("Add Size Product from WishList to cart Test");
			childTest6.setDescription("This test verifies that new user is able to add Size product from Wishlist to cart");
			TestListner.extentMap.get().put("child6", childTest6);

			HeaderBar_Mobile.clearCart();
			AccountPage_Mobile.navigateToWishlist();
			ListingPage_Mobile ListingPage_Mobile = new ListingPage_Mobile(browser);
			ProductDetailData wishlistProductDetaildata = ListingPage_Mobile.addAnySizeProductwishlist();
			SanAssertCS.assertTrue(CartPage_Mobile.isProductPresentInBag(wishlistProductDetaildata), "54F");



		} catch (Exception e) {
			framework.captureScreenshot(browser);
			log.error(e.getMessage(), e);
			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally {

			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();
		}
	}

	@Test(groups = { "todo" })
	public void Static_Combo_Add_To_Bag() throws Throwable {

		Header_FooterBarData brandNavigation = framework.getData(Header_FooterBarData.class, "brand");
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		browser.get(environmentURL);
		AccountData Login = framework.getData(AccountData.class, "newlogin");
		try 
		{
			ExtentTest childTest1 = testListener.startChild("New User Register Test");
			childTest1.setDescription("This test verifies that new user is able to register");
			TestListner.extentMap.get().put("child1", childTest1);

			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			String loggedInUser=AccountPage_Mobile.NewRegister(Login).toLowerCase();
			SanCS.assertTrue((Login.getFirstName().toLowerCase().contains(loggedInUser.toLowerCase().replaceAll("[^0-9]", ""))),  "New User registration test");

			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);
			browser.get(environmentURL);
			HeaderBar_Mobile.clearCart();
			HeaderBar_Mobile.navigateToBrand(brandNavigation);
			ListingPage_Mobile ListingPage_Mobile = new ListingPage_Mobile(browser);
			ListingPage_Mobile.navigateToShopAll("Nykaa Cosmetic Products", environmentURL);
			ProductPage_Mobile productPage = new ProductPage_Mobile(browser);
			ProductDetailData productDetailData = productPage.addAnyStaticComboProductFromProductPage(ListingPage_Mobile.allProducts());
			CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(browser);
			SanCS.assertTrue(CartPage_Mobile.isProductPresentInBag(productDetailData), "55A");
			SanCS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "55C");

			HeaderBar_Mobile.clearCart();
			browser.navigate().refresh();
			SanCS.assertTrue(productPage.addToWishlist(), "55B");
			AccountPage_Mobile.navigateToWishlist();
			ProductDetailData productDetailDatawishList = ListingPage_Mobile.addAnySimpleProductFromWishlist();
			SanCS.assertTrue(CartPage_Mobile.isProductPresentInBag(productDetailDatawishList), "55D");

		}

		catch (Exception e) {

			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally {

			browser.quit();
		}
	}


	@Test(groups = { "Msanity","sanity","sanity_Live","N_sanity_Live" ,"productPage", "MproductPage","Sourabh"})

	public void Mobile_Luxe_Product_Page_AddToBag() throws Throwable {

		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		AccountPage_Mobile AccountPage_Mobile=new AccountPage_Mobile(browser);
		AccountData Login = LoginSync.getInstance().getLogin();
		try {
			ExtentTest childTest = testListener.startChild("Existing User Login test");
			childTest.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child", childTest);
			browser.get(environmentURL);
			String LoggedUserName = AccountPage_Mobile.signInWithYourEmail(Login).toLowerCase();
			SanAssertCS.assertEquals(LoggedUserName, Login.getFirstName().toLowerCase(), "L1");

			ExtentTest childTest2 = testListener.startChild("Add Shade Product to cart from Luxe Product Page Test");
			childTest2.setDescription("This test verifies that new user is able to add Shade product to cart from Luxe product page");
			TestListner.extentMap.get().put("child2", childTest2);

			browser.get(environmentURL + "/luxe/brands/mac.html");
			CartPage_Mobile_New CartPage_Mobile = new CartPage_Mobile_New(browser);
			ProductPage_Mobile productPage = new ProductPage_Mobile(browser);
			ProductDetailData productDetailData = productPage.addAnyShadeProductFromProductPage();
			SanAssertCS.assertTrue(CartPage_Mobile.isProductPresentInBag(productDetailData), "56A");


			ExtentTest childTest3 = testListener.startChild("Add Shade Product to Wishlist from Luxe Product Page Test");
			childTest3.setDescription("This test verifies that new user is able to add Shade product to Wishlist from Luxe product page");
			TestListner.extentMap.get().put("child3", childTest3);
			System.out.println("Please remove below line after 100% checkout");
			browser.get(environmentURL + "/luxe/brands/mac.html");
			browser.navigate().refresh();
			SanAssertCS.assertTrue(productPage.addToWishlist_Lux(), "56B");

		}

		catch (Exception e) {

			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally {
			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();
		}
	}

	/*@Test(groups = { "todo" })
	public void Verify_Add_to_Bag_staticcomboProductFromBag() throws Throwable {

		Header_FooterBarData brandNavigation = framework.getData(Header_FooterBarData.class, "brand");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		browser.get(environmentURL);
		AccountData Login = framework.getData(AccountData.class, "newlogin");
		try {
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			AccountPage_Mobile.NewRegister(Login);
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);
			HeaderBar_Mobile.clearCart();
			HeaderBar_Mobile.navigateToBrand(brandNavigation);
			ListingPage_Mobile ListingPage_Mobile = new ListingPage_Mobile(browser);
			ListingPage_Mobile.navigateToShopAll("Nykaa Cosmetic Products", environmentURL);
			ProductPage_Mobile productPage = new ProductPage_Mobile(browser);
			ProductDetailData productDetailData = productPage
					.addAnyStaticComboProductFromProductPage(ListingPage_Mobile.allProducts());
			CartPage_Mobile CartPage_Mobile = new CartPage_Mobile(browser);
			SanCS.assertTrue(CartPage_Mobile.isProductPresentInBag(productDetailData), "55A");
			SanCS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "55C");
			browser.navigate().refresh();
			SanCS.assertTrue(productPage.addToWishlist(), "55B");
		}

		catch (Exception e) {

			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally {

			browser.quit();
		}
	}

	@Test(groups = { "todo" })
	public void ProductPageChecks() throws Throwable, Throwable {
		Framework framwork = new Framework();
		PropertyConfiguration pf = new PropertyConfiguration();
		Properties prop = pf.getInstance();
		// this.environmentURL = prop.getProperty("environmentName");

		AccountData accountData = framework.getData(AccountData.class, "newlogin");
		WebDriver browser = framwork.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert CS = new CustomSoftAssert(SanAssertData, browser);
		ProductDetailData productData = framwork.getData(ProductDetailData.class, "validreview");
		try {
			browser.get(environmentURL + "/hair-care.html?root=nav_1&dir=desc&order=popularity");
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			AccountPage_Mobile.signInWithYourEmail(accountData);
			browser.get(
					environmentURL + "/makeup/lips/lipstick/lakme-9-to-5-matte-lip-color.html?root=catg&ptype=product");
			ProductPage_Mobile productPage = new ProductPage_Mobile(browser);
			productPage.checkListOfProductPage();
			CS.assertTrue(productPage.addReview(productData, accountData), "101");
			CS.assertTrue(productPage.askNykaa(productData, accountData), "104");
			// CS.assertTrue(productPage.readPost(), "108");
			// CS.assertAll();

		} catch (Throwable e) {
			framwork.captureScreenshot(browser);
			Reporter.log(e.toString());

		} finally {

			LoginSync.getInstance().clearLoginLock(accountData);
			browser.quit();
			Assert.fail();
		}

	}

	@Test(groups = { "todo" })
	public void VerifyShadeProductPageAddTobag() throws Throwable, Throwable {
		Framework framwork = new Framework();
		PropertyConfiguration pf = new PropertyConfiguration();
		Properties prop = pf.getInstance();
		// this.environmentURL = prop.getProperty("environmentName");

		AccountData accountData = LoginSync.getInstance().getLogin();
		Header_FooterBarData HeaderBar_MobileData = framework.getData(Header_FooterBarData.class, "shadenavigation");

		WebDriver browser = framwork.getBrowser(CommonConstants.NKYAA);
		ProductDetailData productData = framwork.getData(ProductDetailData.class, "validreview");
		try {
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			AccountPage_Mobile.signInWithYourEmail(accountData);
			browser.get(environmentURL);
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);
			HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);
			ProductPage_Mobile productPage = new ProductPage_Mobile(browser);
			ListingPage_Mobile ListingPage_Mobile = new ListingPage_Mobile(browser);
			productPage.addAnyShadeProductFromProductPAge(ListingPage_Mobile.allProducts());


	 * browser.get( environmentURL +
	 * "/makeup/lips/lipstick/lakme-9-to-5-matte-lip-color.html?root=catg&ptype=product"
	 * ); ProductPage productPage = new ProductPage(browser);
	 * productPage.checkListOfProductPage();
	 * CS.assertTrue(productPage.addReview(productData, accountData), "101");
	 * CS.assertTrue(productPage.askNykaa(productData, accountData), "104");
	 * CS.assertTrue(productPage.readPost(), "108"); CS.assertAll();


		} catch (Throwable e) {
			framwork.captureScreenshot(browser);
			Reporter.log(e.toString());

		} finally {

			LoginSync.getInstance().clearLoginLock(accountData);
			browser.quit();
			Assert.fail();
		}

	}

	 *//***************************************************************************************
	 * SearchTestCases
	 ***********************************************************************************************************/

	// @DataProvider(name = "Data Provide For TC1" ,parallel = true)
	// public Iterator<Object> dataListForSearch() {
	// Framework framework = new Framework();
	// List<Object> HeaderBar_Mobile =
	// framework.getDataProvider(Header_FooterBarData.class, "TC1");
	// return HeaderBar_Mobile.iterator();
	//
	// }


	@Test( groups = {"sanity","Msanity","sanity_Live","N_sanity_Live","Wsearch","search","Sourabh" })

	public void Mobile_Search_Keyword() throws Throwable, Throwable {
		Framework frameWork = new Framework();
		AccountData accountData = LoginSync.getInstance().getLogin();
		WebDriver browser = frameWork.getBrowser("Msite");
		List<Header_FooterBarData> HeaderBar_MobileDataList=frameWork.getDataList(Header_FooterBarData.class, "TD4");
		CustomSoftAssert CS = new CustomSoftAssert(SanAssertData, browser);
		try {
			ExtentTest childTest1 = testListener.startChild("Login Test ");
			childTest1.setDescription("This test verifies that user is able to login with valid credentials");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			String loggedInUserName= AccountPage_Mobile.signInWithYourEmail(accountData);
			CS.assertTrue(accountData.getFirstName().equalsIgnoreCase(loggedInUserName),"Login with Valid Credentials");
			browser.get(environmentURL);
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);
			ListingPage_Mobile lpm= new ListingPage_Mobile(browser);
			
			for (Header_FooterBarData HeaderBar_MobileData : HeaderBar_MobileDataList) 
			{
				boolean finalResult=true;
				String search_keyword= HeaderBar_MobileData.get_search_keyword();
				ExtentTest childTest = testListener.startChild("Search with a keyword/Brand: "+search_keyword+" name test ");
				childTest.setDescription("This test verifies that user is able to search with a keyword/Brand name: "+ search_keyword+" on page");
				TestListner.extentMap.get().put("child".concat(search_keyword), childTest);
				HeaderBar_Mobile.search(search_keyword);
				//PartialStringList SearchedResult = HeaderBar_Mobile.getSearchedResult();
				List<ProductDetailData> productList;
				//if(lpm.ListPageIsReactPageOrNot())
				if(!browser.getCurrentUrl().contains("search/result"))
				{
					lpm.LoadMoreProducts(3, false);
					productList = lpm.getProductList();
				}
				else
				{
					productList = lpm.getProductNameList_Magento(); 
				}

				for(ProductDetailData  pd : productList)
				{
					String productName = pd.getProductName().toLowerCase();
					Boolean bol= productName.contains(search_keyword.toLowerCase());
					//CS.assertTrue(bol,"verify Search landing page result");
					if(bol)
					{
						TestListner.testing.get().log(LogStatus.INFO, productName +" have search keyword : " +  search_keyword);

					}
					else
					{
						TestListner.testing.get().log(LogStatus.FAIL, productName +" doesn't have search keyword : " +  search_keyword);
						finalResult=false;
					}

				}
				CS.assertTrue(finalResult,"271D");
			}

			
			ExtentTest childTest3 = testListener.startChild("Add to Bag from Search page");
			childTest3.setDescription("This test verifies that user is able to add product to bag from search page");
			TestListner.extentMap.get().put("child3", childTest3);
			CartPage_Mobile_New CartPage_Mobile = new CartPage_Mobile_New(browser);
			ListingPage_Mobile ListingPage_Mobile= new ListingPage_Mobile(browser);
			ProductDetailData productDetadata = ListingPage_Mobile.addAnySimpleProductFromSearchResult();
			boolean isProductAdded = CartPage_Mobile.isProductPresentInBag(productDetadata);

			CS.assertTrue(isProductAdded, "271E");

		}
		catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser,e);
			Reporter.log(e.toString(), true);
			Assert.fail();


		}

		finally {

			LoginSync.getInstance().clearLoginLock(accountData);
			browser.quit();
		}

	}

	@Test(groups = { "sanity","Msanity","sanity_Live","N_sanity_Live","Wsearch","search" })
	public void Mobile_Auto_Suggest() throws Throwable, Throwable {
		Framework frameWork = new Framework();
		AccountData accountData = LoginSync.getInstance().getLogin();
		WebDriver browser = frameWork.getBrowser("Msite");
		// PropertyConfiguration pf = new PropertyConfiguration();
		// Properties prop = pf.getInstance();
		// this.environmentURL = prop.getProperty("environmentName");
		List<Header_FooterBarData> HeaderBar_MobileDataList = frameWork.getDataList(Header_FooterBarData.class, "TC3");
		CustomSoftAssert CS = new CustomSoftAssert(SanAssertData,browser);
		// Header_FooterBarData HeaderBar_MobileData = new
		// Header_FooterBarData();
		try {
			ExtentTest childTest1 = testListener.startChild("Auto Suggest in Search Test");
			childTest1.setDescription("This test verifies that user is able to view auto suggest in search");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			AccountPage_Mobile.signInWithYourEmail(accountData);
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);
			for (Header_FooterBarData HeaderBar_MobileData : HeaderBar_MobileDataList) {
				String keyWord =HeaderBar_MobileData.get_search_keyword();
				HeaderBar_Mobile.Autosearch(keyWord);
				PartialStringList SearchedResult = HeaderBar_Mobile.getValueFromAutoSuggetionList();
				Boolean bol=true;
				for(String AutoSearchResponse : SearchedResult)
				{
					if(!AutoSearchResponse.toLowerCase().contains(keyWord.toLowerCase()))
					{
						bol=false;
						TestListner.testing.get().log(LogStatus.INFO,keyWord +" is not present in auto search result :"+ AutoSearchResponse);
					}
					else
					{
						TestListner.testing.get().log(LogStatus.INFO,keyWord +" is present in auto search result :"+ AutoSearchResponse);
					}
				}
				CS.assertTrue(bol, "271A");
			}
		} catch (Throwable e) {
			frameWork.captureScreenshot(browser);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {

			LoginSync.getInstance().clearLoginLock(accountData);
			browser.quit();
		}
	}

	@Test(groups = {"sanity","Msanity","sanity_Live","N_sanity_Live","Wsearch","search"})
	public void Mobile_Verify_Trending_Search() throws Throwable, Throwable {
		Framework frameWork = new Framework();
		AccountData accountData = LoginSync.getInstance().getLogin();
		WebDriver browser = frameWork.getBrowser("Msite");
		PropertyConfiguration pf = new PropertyConfiguration();
		// Properties prop = pf.getInstance();
		// this.environmentURL = prop.getProperty("environmentName");
		//List<Header_FooterBarData> HeaderBar_MobileDataList = frameWork.getDataList(Header_FooterBarData.class, "TC3");
		CustomSoftAssert CS = new CustomSoftAssert(SanAssertData,browser);
		boolean bolFinal=true;
		ListingPage_Mobile lpm= new ListingPage_Mobile(browser);
		try {
			ExtentTest childTest1 = testListener.startChild("Trending Search Test");
			childTest1.setDescription("This test verifies that user is able to view trending search");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			AccountPage_Mobile.signInWithYourEmail(accountData);
			browser.get(environmentURL);
			HomePage_Mobile HomePage_Mobile = new HomePage_Mobile(browser);
			CS.assertTrue(HomePage_Mobile.NaviagetoTrendingSearch(),"271B");
			for ( int i=0 ;i< 2 ;i++) {
				String selectionTrendingSearch =	HomePage_Mobile.SelectRandomTrendingSerach();
				ExtentTest childTest = testListener.startChild("Trending searching with a keyword/Brand: "+selectionTrendingSearch+" name test ");
				childTest.setDescription("This test verifies that user is able to search Trending products : "+ selectionTrendingSearch+" on page");
				TestListner.extentMap.get().put("child".concat(selectionTrendingSearch), childTest);

				List<ProductDetailData> productList;
				if(lpm.ListPageIsReactPageOrNot())
				{
					lpm.LoadMoreProducts(3, false);
					productList = lpm.getProductList();
				}
				else
				{
					productList = lpm.getProductNameList_Magento(); 
				}

				for(ProductDetailData  pd : productList)
				{
					String productName = pd.getProductName();
					/*String newProductName=ba.removeSpaces(pd.getProductName());
				    String selectionTrendingSearchNew = ba.removeSpaces(selectionTrendingSearch);
					Boolean bol= newProductName.contains(selectionTrendingSearchNew);*/
					Boolean bol = HomePage_Mobile.validatesearchKeywordIsInProduct(productName,selectionTrendingSearch);
					//CS.assertTrue(bol,"verify Search landing page is : " +productName);
					if(bol)
					{
						TestListner.testing.get().log(LogStatus.INFO, productName +" has Trending search keyword : " +  selectionTrendingSearch);
						//CS.assertTrue(bol,productName +" have Trending search keyword : " +  selectionTrendingSearch);
					}
					else
					{
						TestListner.testing.get().log(LogStatus.FAIL, productName +" doesn't have Trending search keyword : " +  selectionTrendingSearch);
						bolFinal=bol;
						//CS.assertTrue(bol,productName +" dont have Trending search keyword : " +  selectionTrendingSearch);
					}

				}
				CS.assertTrue(bolFinal,"271D");
				browser.get(environmentURL);
			}

			


		} catch (Throwable e) {
			frameWork.captureScreenshot(browser);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {

			LoginSync.getInstance().clearLoginLock(accountData);
			browser.quit();
		}
	}
	/**********************************************************************
	 * Home Page
	 * 
	 * @throws Throwable
	 * @throws URISyntaxException
	 ***********************************************************************************************************************/

	@Test(groups = {"sanity","Msanity","sanity_Live","N_sanity_Live"})
	public void Mobile_Verify_Banner_redirection() throws URISyntaxException, Throwable {

		Framework framwork = new Framework();

		AccountData accountData = LoginSync.getInstance().getLogin();
		WebDriver browser = framwork.getBrowser("Msite");
		CustomSoftAssert CS = new CustomSoftAssert(SanAssertData, browser);
		Boolean bolFinal = true;

		try {
			ExtentTest childTest1 = testListener.startChild("Home Page Banner images are not broken ");
			childTest1.setDescription("This test verifies that Home page Banner images are not broken ");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			HomePage_Mobile hm = new HomePage_Mobile(browser);
			Map<Integer , Boolean> BrokenImages = hm.BrokenImagesList();
			for(Integer a : BrokenImages.keySet())
			{
				CS.assertFalse(BrokenImages.get(a),"verify Home page banner  Broken images");
				if (!BrokenImages.get(a))
				{
					TestListner.testing.get().log(LogStatus.INFO,"verify Home page banner image is not broken at position " + a);
				}
				else
				{
					TestListner.testing.get().log(LogStatus.INFO, "verify Home page banner image is broken at position " + a);
					bolFinal=false;
				}
			}
			Thread.sleep(4000);
			ExtentTest childTest2 = testListener.startChild("Home Page Banner Images are redirecting to new page ");
			childTest2.setDescription("This test verifies that Home page Banner Images are redirecting to new page ");
			TestListner.extentMap.get().put("child2", childTest2);
			Map<Integer , Boolean> BannerImagesRedirectingList = hm.BannerImagesRedirectingList();
			for(Integer a : BannerImagesRedirectingList.keySet())
			{
				CS.assertTrue(BannerImagesRedirectingList.get(a),"verify Home page banner  redirection");
				if (BannerImagesRedirectingList.get(a))
				{
					TestListner.testing.get().log(LogStatus.INFO,"verify Home page banner at position :" + a + "redirected successfully ");
				}
				else
				{
					TestListner.testing.get().log(LogStatus.INFO, "verify Home page banner at position :" + a + "dont redirected successfully ");
					bolFinal=false;
				}
			}

			CS.assertTrue(bolFinal,"39");			
		} catch (Exception e) {
			framwork.captureScreenshot(browser);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {
			LoginSync.getInstance().clearLoginLock(accountData);

			browser.quit();
		}
	}


	@Test(groups = { "Msanity","sanity","filter","Mfilter","sanity_Live","N_sanity_Live","Local"})

	public void Mobile_Price_Filter() throws Throwable {
		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
		List<ProductDetailData> productlistwithPrizeFilter=new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		Header_FooterBarData HeaderBar_MobileData = framework.getData(Header_FooterBarData.class, "l3category_Mobile_1");
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);
		ListingPage_Mobile listingPage = new ListingPage_Mobile(browser);
		boolean PriceMatches=true;
		boolean filtersCleared=false;
		List<String> filtersClearedList=new ArrayList<String>();
		try 
		{
			ExtentTest childTest1 = testListener.startChild("Navigate to React Page test ");
			childTest1.setDescription("This test verifies the user is able to navigate to a react page");
			TestListner.extentMap.get().put("child1", childTest1);
			int count=1;
			browser.get(environmentURL);
			HeaderBar_Mobile.navigateToCategory(HeaderBar_MobileData);

			ArrayList<String> allOptions=new ArrayList<String>();
			allOptions=listingPage.getFilterOptionsList("Price");
			String randomForMultipleFilters=new String();
			HashSet<Integer> indexes=framework.randomNumbers(allOptions.size());

			for(int i:indexes)
			{
				if(count<=2) 
				{
					randomForMultipleFilters=randomForMultipleFilters.concat("----"+allOptions.get(i-1).split("\\(")[0].trim());
				}
				count+=1;
			}
			randomForMultipleFilters=randomForMultipleFilters.replaceFirst("----", "");

			for(String val:allOptions)
			{
				val=val.split("\\(")[0].trim();
				printList.clear();
				productListPriceFilter.clear();
				productlistwithPrizeFilter.clear();
				int lowerVal;
				int upperVal;
				if(!val.contains("Above"))
				{
					lowerVal=Integer.parseInt((val.split("-")[0]).replaceAll("Rs. ", "").trim());
					upperVal=Integer.parseInt((val.split("-")[1]).replaceAll("Rs. ", "").trim());
				}
				else
				{
					lowerVal=Integer.parseInt((val.split("&")[0]).replaceAll("Rs. ", "").trim());
					upperVal=999999;
				}

				ExtentTest childTest = testListener.startChild("Filter by Price "+ val+" test ");
				childTest.setDescription("This test verifies the user is able to apply filter by Price "+ val+" on react page");
				TestListner.extentMap.get().put("child".concat(val), childTest);
				if(val.equals(allOptions.get(allOptions.size()-1).split("\\(")[0].trim()))
				{
					productListPriceFilter = listingPage.ApplyFilter("Price", val,true,true);
				}
				else
				{
					productListPriceFilter = listingPage.ApplyFilter("Price", val,true,true);
				}
				System.out.println(productListPriceFilter.toString());
				productListPriceFilter.remove(" ");
				System.out.println(productListPriceFilter.toString());
				productlistwithPrizeFilter = productListPriceFilter.stream().filter(o -> Integer.parseInt(o.getProductPrize()) <= upperVal && Integer.parseInt(o.getProductPrize()) >= lowerVal).collect(Collectors.toList());
				for(int i=0;i<productListPriceFilter.size();i++)
				{
					printList.add(productListPriceFilter.get(i).getProductPrize());
					if(! (Integer.parseInt(productListPriceFilter.get(i).getProductPrize())>=lowerVal && Integer.parseInt (productListPriceFilter.get(i).getProductPrize())<=upperVal))
					{
						PriceMatches=false;
						TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductPrize()+" :: Expected: "+val);
					}
				}
				TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Price "+ val+": "+printList.toString());
				SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter,"Price filterTest for "+val);
				allOptions=listingPage.getFilterOptionsList("Price");
				filtersCleared=listingPage.clearAllFilters();
				filtersClearedList.add(filtersCleared?"Filters cleared for"+val :"Filters not cleared for"+val );
			}
			SanAssertCS.assertTrue(PriceMatches,"R_79Q");

			ExtentTest childTestFilter = testListener.startChild("Clear All Filters test");
			childTestFilter.setDescription("This test verifies the user is able to clear applied filters on react page");
			TestListner.extentMap.get().put("childTestFilter", childTestFilter);
			TestListner.testing.get().log(LogStatus.INFO, "Clear Filter status: "+filtersClearedList.toString());
			SanAssertCS.assertTrue(! filtersClearedList.toString().contains("Filters not cleared"),"R_79V_1");

			ExtentTest childTest6 = testListener.startChild("Filter by Price "+randomForMultipleFilters.replace("----", " and ")+"  test ");
			childTest6.setDescription("This test verifies the user is able to apply filter by Price "+randomForMultipleFilters.replace("----", " and ")+" on react page");
			TestListner.extentMap.get().put("child6", childTest6);
			productListPriceFilter.clear();
			productlistwithPrizeFilter.clear();
			printList.clear();
			int lowerVal[]=new int[2];
			int upperVal[] =new int[2];
			String vals[]=randomForMultipleFilters.split("----");
			try
			{
				for(int i=0;i<vals.length;i++)
				{
					if(!vals[i].contains("Above"))
					{
						System.out.println((vals[i].split("-")[0]).trim());
						if((vals[i].split("-")[0]).trim().equals("Rs. 0"))
						{
							lowerVal[i]=0;
						}
						else
						{
							lowerVal[i]=Integer.parseInt((vals[i].split("-")[0]).replaceAll("Rs. ", "").trim());
						}

						upperVal[i]=Integer.parseInt((vals[i].split("-")[1]).replaceAll("Rs. ", "").trim());
					}
					else
					{
						lowerVal[i]=Integer.parseInt((vals[i].split("&")[0]).replaceAll("Rs. ", "").trim());
						upperVal[i]=999999;
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			productListPriceFilter = listingPage.ApplyFilter("Price", randomForMultipleFilters,false,true);
			productlistwithPrizeFilter = productListPriceFilter.stream()
					.filter(o -> (Integer.parseInt(o.getProductPrize()) < upperVal[0] && Integer.parseInt(o.getProductPrize()) > lowerVal[0])||(Integer.parseInt(o.getProductPrize()) < upperVal[1] && Integer.parseInt(o.getProductPrize()) > lowerVal[1])).collect(Collectors.toList());
			for(int i=0;i<productlistwithPrizeFilter.size();i++)
			{
				printList.add(productlistwithPrizeFilter.get(i).getProductPrize());
				if(! ((Integer.parseInt(productListPriceFilter.get(i).getProductPrize())>=lowerVal[0] && Integer.parseInt (productListPriceFilter.get(i).getProductPrize())<=upperVal[0])||(Integer.parseInt(productListPriceFilter.get(i).getProductPrize())>=lowerVal[1] && Integer.parseInt (productListPriceFilter.get(i).getProductPrize())<=upperVal[1])))
				{
					PriceMatches=false;
					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductPrize()+" :: Expected: "+randomForMultipleFilters.replace("----", " and "));
				}
			}
			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Price "+randomForMultipleFilters.replace("----", " and ")+" : "+printList.toString());
			//SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "R_79S");
			SanAssertCS.assertTrue(PriceMatches,"R_79S");


		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser,e);
			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally 
		{
			browser.quit();
		}

	}

	@Test(groups = { "sanity","Msanity","filter","Mfilter" })
	public void Mobile_Brand_Filter() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l2category_Mobile");
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		HeaderBar_Mobile headerbar = new HeaderBar_Mobile(browser);
		ListingPage_Mobile listingPage = new ListingPage_Mobile(browser);
		try {
			ExtentTest childTest1 = testListener.startChild("Navigate to L2 category test ");
			childTest1.setDescription("This test verifies the user is able to navigate to category listing page");
			TestListner.extentMap.get().put("childTest1", childTest1);
			int count=1;
			browser.get(environmentURL);
			headerbar.navigateToCategory(headerbarData);
			List<String> BrandNameMatches=new ArrayList<String>();
			boolean BrandNameMatchesTemp=true;
			boolean countMatches=false;
			ArrayList<String> allOptions=new ArrayList<String>();
			allOptions=listingPage.getFilterOptionsList("Brand");
			String randomForMultipleFilters=new String();
			ArrayList<String> filterValues=new ArrayList<String>();
			HashSet<Integer> indexes=framework.randomNumbers(allOptions.size());
			String brandToSearchByName=allOptions.get(allOptions.size()-1);

			for(int i:indexes)
			{

				filterValues.add(allOptions.get(i-1).split("\\(")[0].trim());
				if(count<=3) 
				{
					randomForMultipleFilters=randomForMultipleFilters.concat("----"+allOptions.get(i-1).split("\\(")[0].trim());
				}
				count+=1;
			}
			randomForMultipleFilters=randomForMultipleFilters.replaceFirst("----", "");
			for(String val:filterValues)
			{
				BrandNameMatchesTemp=true;
				printList.clear();
					productListPriceFilter.clear();
				ExtentTest childTest = testListener.startChild("Filter by Brand "+ val+" test ");
				childTest.setDescription("This test verifies the user is able to apply filter by Brand "+ val+" on react page");
				TestListner.extentMap.get().put("child".concat(val), childTest);

				productListPriceFilter = listingPage.ApplyFilter("Brand", val,false,true);
				for(int i=0;i<productListPriceFilter.size();i++)
				{
					val=val.replace("OGX", "Organix");
					printList.add(productListPriceFilter.get(i).getProductName());
					if(val.equalsIgnoreCase("organix"))
					{
						if(! (productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(val.split(" ")[0].replace("'", "").toLowerCase().trim())|| (productListPriceFilter.get(i).getProductName().split("'")[0].toLowerCase().contains("ogx"))))
						{
							BrandNameMatchesTemp=false;
							TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+val);
						}
					}
					else if(! productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(val.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim()))
					{
						BrandNameMatchesTemp=false;
						TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+val);
					}

				}
				TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Brand "+ val+" : "+printList.toString());
				TestListner.testing.get().log(LogStatus.INFO,"Brand match list :: "+BrandNameMatches.toString());
				SanAssertCS.assertTrue(BrandNameMatchesTemp,"Brand Filter test for: "+val);
				BrandNameMatches.add(BrandNameMatchesTemp?"Brand matches for "+val :"Brand does not match for "+val );
			}
			SanAssertCS.assertTrue(! BrandNameMatches.contains("not"), "R_79T");
			listingPage.clearAllFilters();
			ExtentTest childTest = testListener.startChild("Filter by Brand "+ randomForMultipleFilters.replaceAll("----", " and ")+" test ");
			childTest.setDescription("This test verifies the user is able to apply filter by Brand "+ randomForMultipleFilters.replaceAll("----", " and ")+" on react page");
			TestListner.extentMap.get().put("childTest", childTest);

			BrandNameMatchesTemp=false;
			printList.clear();
			productListPriceFilter.clear();
			BrandNameMatches.clear();
			productListPriceFilter = listingPage.ApplyFilter("Brand", randomForMultipleFilters,true,true);
			for(int i=0;i<productListPriceFilter.size();i++)
			{
				randomForMultipleFilters=randomForMultipleFilters.replace("OGX", "Organix");
				printList.add(productListPriceFilter.get(i).getProductName());
				String[] vals=randomForMultipleFilters.split("----");
				for(String value:vals) {

					if(value.equalsIgnoreCase("organix"))
					{
						if((productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(value.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim())|| (productListPriceFilter.get(i).getProductName().split("'")[0].toLowerCase().contains("ogx"))))
						{
							BrandNameMatchesTemp=true;
							break;
						}
					}
					else if( productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(value.split(" ")[0].replace("'", "").toLowerCase().trim()))
					{
						BrandNameMatchesTemp=true;
						countMatches=true;
						break;
					}
				}
				if(!BrandNameMatchesTemp)
				{
					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+randomForMultipleFilters.replaceAll("----", " and "));
				}
				BrandNameMatches.add(BrandNameMatchesTemp?"Brand matches for"+randomForMultipleFilters.replaceAll("----", " and ") :"Brand does not match for"+randomForMultipleFilters.replaceAll("----", " and ") );
			}
			TestListner.testing.get().log(LogStatus.INFO,"Brand match list :: "+BrandNameMatches.toString());
			SanAssertCS.assertTrue(countMatches && ! BrandNameMatches.contains("Brand does not match"), "R_79U");

			ExtentTest childTest3 = testListener.startChild("Filter by Brand search :"+brandToSearchByName + " test ");
			childTest3.setDescription("This test verifies the user is able to search and apply brand filter by brand nam ");
			TestListner.extentMap.get().put("childTest3", childTest3);

			BrandNameMatchesTemp=true;
			printList.clear();
			productListPriceFilter.clear();
			BrandNameMatches.clear();
			productListPriceFilter = listingPage.ApplyFilter("Brand", brandToSearchByName+"searchBrand",false,true);
			for(int i=0;i<productListPriceFilter.size();i++)
			{
				brandToSearchByName=brandToSearchByName.replace("OGX", "Organix");
				printList.add(productListPriceFilter.get(i).getProductName());
				if(brandToSearchByName.equalsIgnoreCase("organix"))
				{
					if(! (productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(brandToSearchByName)|| (productListPriceFilter.get(i).getProductName().split("'")[0].toLowerCase().contains("ogx"))))
					{
						BrandNameMatchesTemp=false;
						TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+brandToSearchByName);
					}
				}
				else if(! productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(brandToSearchByName.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim()))
				{
					BrandNameMatchesTemp=false;
					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductName()+" :: Expected: "+brandToSearchByName);
				}

			}
			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after search and filter by Brand "+ brandToSearchByName+" : "+printList.toString());
			SanAssertCS.assertTrue(BrandNameMatchesTemp,"R_79V");

		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser,e);
			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally 
		{
			browser.quit();
		}
	}

	@Test(groups = { "sanity","Msanity","filter","Mfilter"})
	public void Mobile_Discount_Filter() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l2category_Mobile");
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
		List<ProductDetailData> productlistwithPrizeFilter=new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		HeaderBar_Mobile headerbar = new HeaderBar_Mobile(browser);
		ListingPage_Mobile listingPage = new ListingPage_Mobile(browser);
		boolean PriceMatches=true;
		boolean filtersCleared=false;
		List<String> filtersClearedList=new ArrayList<String>();
		List<String> priceMatchedList=new ArrayList<String>();
		try 
		{
			ExtentTest childTest1 = testListener.startChild("Navigate to L2 category test ");
			childTest1.setDescription("This test verifies the user is able to navigate to category listing page");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			headerbar.navigateToCategory(headerbarData);

			ArrayList<String> allOptions=new ArrayList<String>();
			allOptions=listingPage.getFilterOptionsList("Discount");

			for(String val:allOptions)
			{
				String tempVal=val.split("\\(")[0].trim();
				printList.clear();
				productListPriceFilter.clear();
				productlistwithPrizeFilter.clear();
				if(tempVal.contains("Upto"))
				{
					//commenting less than 10 % discount case as it has null discount values as well which takes too long to find the element(due to timeout) as those locators are not present
					//tempVal=tempVal.replaceAll("Upto ", "<").replaceAll("%", "").trim();
					continue;
				}
				else
				{
					tempVal=tempVal.replaceAll("and above",">").replaceAll("%", "").trim();
				}
				String value=tempVal;
				ExtentTest childTest = testListener.startChild("Filter by Discount "+val+" test ");
				childTest.setDescription("This test verifies the user is able to apply filter by Brand "+ val+" on react page");
				TestListner.extentMap.get().put("child".concat(val), childTest);
				if(val.equals(allOptions.get(allOptions.size()-1).trim()))
				{
					productListPriceFilter = listingPage.ApplyFilter("Discount", val,true,true);
				}
				else
				{
					productListPriceFilter = listingPage.ApplyFilter("Discount", val,false,true);
				}
				if(value.contains("<"))
				{
					for(int i=0;i<productListPriceFilter.size();i++)
					{
						printList.add(productListPriceFilter.get(i).getDiscount());
						if(! (Integer.parseInt(productListPriceFilter.get(i).getDiscount())<=Integer.parseInt(val.replaceAll("<", "").trim()))) 
						{
							PriceMatches=false;
							TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getDiscount()+" :: Expected: "+tempVal);
						}
					}
				}
				else
				{
					for(int i=0;i<productListPriceFilter.size();i++)
					{
						printList.add(productListPriceFilter.get(i).getDiscount());
						if(! (Integer.parseInt(productListPriceFilter.get(i).getDiscount())>=Integer.parseInt(value.replaceAll(">", "").trim()))) 
						{
							PriceMatches=false;
							TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getDiscount()+" :: Expected: "+tempVal);
						}
					}
				}

				TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Discount "+ value.replaceAll(">"," and above ")+": "+printList.toString());
				priceMatchedList.add(PriceMatches?"Discount matches for"+val :"Discount does not match for"+val );
				SanAssertCS.assertTrue(PriceMatches,"Discount filterTest for "+val);
				filtersCleared=listingPage.clearAllFilters();
				filtersClearedList.add(filtersCleared?"Filters cleared for"+val :"Filters not cleared for"+val );
			}
			SanAssertCS.assertTrue(! priceMatchedList.toString().contains("Discount does not match"),"R_79X");

			ExtentTest childTestFilter = testListener.startChild("Clear All Filters test");
			childTestFilter.setDescription("This test verifies the user is able to clear applied filters on react page");
			TestListner.extentMap.get().put("childTestFilter", childTestFilter);
			TestListner.testing.get().log(LogStatus.INFO, "Clear Filter status: "+filtersClearedList.toString());
			SanAssertCS.assertTrue(! filtersClearedList.toString().contains("Filters not cleared"),"R_79V_1");


		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser,e);
			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally 
		{
			browser.quit();
		}
	}


	@Test(groups = { "sanity","Msanity","filter","Mfilter","sanity_Live","N_sanity_Live"})

	public void Mobile_Multiple_Filter() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l2category_Mobile");
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		HeaderBar_Mobile headerbar = new HeaderBar_Mobile(browser);
		ListingPage_Mobile listingPage = new ListingPage_Mobile(browser);
		boolean PriceMatches=true;
		boolean filtersCleared=false;
		try {
			ExtentTest childTest1 = testListener.startChild("Navigate to L2 category test ");
			childTest1.setDescription("This test verifies the user is able to navigate to category listing page");
			TestListner.extentMap.get().put("childTest1", childTest1);
			int count=1;
			browser.get(environmentURL);
			headerbar.navigateToCategory(headerbarData);
			List<String> BrandNameMatches=new ArrayList<String>();
			boolean BrandNameMatchesTemp=false;
			ArrayList<String> allOptions=new ArrayList<String>();
			allOptions=listingPage.getFilterOptionsList("Brand");
			ArrayList<String> brandFilterValues=new ArrayList<String>();
			HashSet<Integer> indexes=framework.randomNumbers(allOptions.size());
			for(int i:indexes)
			{
				if(! allOptions.get(i-1).contains("OGX"))
				{
					if(count<=1) 
					{
						brandFilterValues.add(allOptions.get(i-1).split("\\(")[0].trim());
					}
					count+=1;
				}
			}

			ExtentTest childTest = testListener.startChild("Filter by Brand and Price test ");
			childTest.setDescription("This test verifies the user is able to apply multiple filters on react page");
			TestListner.extentMap.get().put("childTest", childTest);
			for(String val:brandFilterValues)
			{
				listingPage.ApplyFilter("Brand", val,false,true);
			}

			allOptions.clear();
			allOptions=listingPage.getFilterOptionsList("Price");
			String val=allOptions.get(0).split("\\(")[0].trim();
			int lowerVal;
			int upperVal;
			if(!val.contains("Above"))
			{
				lowerVal=Integer.parseInt((val.split("-")[0]).replaceAll("Rs. ", "").trim());
				upperVal=Integer.parseInt((val.split("-")[1]).replaceAll("Rs. ", "").trim());
			}
			else
			{
				lowerVal=Integer.parseInt((val.split("&")[0]).replaceAll("Rs. ", "").trim());
				upperVal=999999;
			}

			productListPriceFilter = listingPage.ApplyFilter("Price", val,true,false);

			for(int i=0;i<productListPriceFilter.size();i++)
			{
				printList.add(productListPriceFilter.get(i).getProductName()+" :: "+(productListPriceFilter.get(i).getProductPrize()));
				String expected="";
				String actual="";
				for(String brand:brandFilterValues)
				{
					expected=brand.replace("OGX", "Organix");
					actual=productListPriceFilter.get(i).getProductName().split("'")[0].toLowerCase();
					if(actual.contains(expected.split("'")[0].toLowerCase()))
					{
						BrandNameMatchesTemp=true;
					}
				}
				BrandNameMatches.add(BrandNameMatchesTemp?"true":"false");
				if(! BrandNameMatchesTemp)
				{
					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+actual+" :: Expected: "+expected);
				}

				if(!(Integer.parseInt(productListPriceFilter.get(i).getProductPrize())>=lowerVal && Integer.parseInt (productListPriceFilter.get(i).getProductPrize())<=upperVal))
				{
					PriceMatches=false;
					TestListner.testing.get().log(LogStatus.FAIL,"Failed Values ||||  Actual: "+productListPriceFilter.get(i).getProductPrize()+" :: Expected: "+val);
				}
			}
			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Brand and price || "+printList.toString());
			SanAssertCS.assertTrue(BrandNameMatchesTemp && PriceMatches, "R_79Y");

			ExtentTest childTestFilter = testListener.startChild("Clear All Filters test");
			childTestFilter.setDescription("This test verifies the user is able to clear applied filters on react page");
			TestListner.extentMap.get().put("childTestFilter", childTestFilter);
			filtersCleared=listingPage.clearAllFilters();
			TestListner.testing.get().log(LogStatus.INFO, "Clear Filter status: "+filtersCleared);
			SanAssertCS.assertTrue(filtersCleared,"R_79V_1");




		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser,e);
			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally 
		{
			browser.quit();
		}
	}


	@Test(groups = {"sanity","Msanity","sanity_Live","N_sanity_Live","sorting","Msorting"})

	public void Mobile_Sorting() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l2category_Mobile");
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
		List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		List<String> printListSorted = new ArrayList<String>();
		ListingPage_Mobile listingPage = new ListingPage_Mobile(browser);
		HeaderBar_Mobile headerbar = new HeaderBar_Mobile(browser);

		try {
			ExtentTest childTest1 = testListener.startChild("Sort by Name test ");
			childTest1.setDescription("This test verifies the user is able to sort products by Name");
			TestListner.extentMap.get().put("child1", childTest1);

			browser.get(environmentURL);

			headerbar.navigateToCategory(headerbarData);
			productList = listingPage.ApplySort("NAME");
			productListFromUI.addAll(productList);
			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName().compareToIgnoreCase(a2.getProductName()));
			for(int i=0;i<productList.size();i++)
			{
				printList.add(productList.get(i).getProductName());
			}
			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after Name Sorting: "+printList.toString());
			SanAssertCS.assertEquals(productListFromUI, productList, "R_79G");

			ExtentTest childTest2 = testListener.startChild("Sort by Price: Low To High test ");
			childTest2.setDescription("This test verifies the user is able to sort products by Price: Low To High");
			TestListner.extentMap.get().put("child2", childTest2);
			productList.clear();
			productListFromUI.clear();
			printList.clear();
			productList = listingPage.ApplySort("PRICE: LOW TO HIGH");
			productListFromUI.addAll(productList);
			Collections.sort(productList, (ProductDetailData a2, ProductDetailData a1) -> Integer.parseInt(a2.getProductPrize()) - Integer.parseInt(a1.getProductPrize()));
			for(int i=0;i<productList.size();i++)
			{
				printList.add(productList.get(i).getProductPrize());
			}
			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after PRICE: LOW TO HIGH Sorting: "+printList.toString());
			SanAssertCS.assertEquals(productListFromUI, productList, "R_79H");

			ExtentTest childTest3 = testListener.startChild("Sort by Price: High To Low test ");
			childTest3.setDescription("This test verifies the user is able to sort products by Price: High To Low ");
			TestListner.extentMap.get().put("child3", childTest3);
			productList.clear();
			productListFromUI.clear();
			printList.clear();
			productList = listingPage.ApplySort("PRICE: HIGH TO LOW");
			productListFromUI.addAll(productList);
			for(int i=0;i<productList.size();i++)
			{
				printList.add(productList.get(i).getProductPrize());
			}
			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> Integer.parseInt(a1.getProductPrize()) - Integer.parseInt(a2.getProductPrize()));
			Collections.reverse(productList);
			for(int i=0;i<productList.size();i++)
			{
				printListSorted.add(productList.get(i).getProductPrize());
			}
			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after PRICE: HIGH TO LOW Sorting: "+printList.toString());
			SanAssertCS.assertEquals(printListSorted, printList, "R_79Z");

			ExtentTest childTest4 = testListener.startChild("Sort by Discount test ");
			childTest4.setDescription("This test verifies the user is able to sort products by Discount");
			TestListner.extentMap.get().put("child4", childTest4);
			productList.clear();
			productListFromUI.clear();
			printList.clear();
			printListSorted.clear();
			productList = listingPage.ApplySort("DISCOUNT");
			productListFromUI.addAll(productList);
			for(int i=0;i<productList.size();i++)
			{
				String Discount = productList.get(i).getDiscount();
				if(Discount==null){
					Discount="";
				}
				printList.add(Discount);
			}
			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> Integer.parseInt(a1.getDiscount())-Integer.parseInt( a2.getDiscount()));
			Collections.reverse(productList);
			for(int i=0;i<productList.size();i++)
			{
				printListSorted.add(productList.get(i).getDiscount());
			}
			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after DISCOUNT Sorting: "+printList.toString());
			SanAssertCS.assertEquals(printListSorted, printList, "R_79I");



		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser,e);
			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally {
			browser.quit();
		}
	}

	private void printStackStraceinLog(Throwable ex) {

		StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));
		Reporter.log(errors.toString(),true);
	}

	@Test(groups = { "sanity","Msanity","cancellation","Mcancellation","Sourabh"})
	public void Check_end_to_end_checkout_flow_and_Item_Level_Cancellation() throws Throwable {
		Header_FooterBarData headerbar1 = framework.getData(Header_FooterBarData.class, "shadenavigation");
		Header_FooterBarData headerbar2 = framework.getData(Header_FooterBarData.class, "sizenavigation");
		AccountData Login = LoginSync.getInstance().getLogin();
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert sanityCS = new CustomSoftAssert(SanAssertData, browser);
		CheckoutData checkOutData = framework.getData(CheckoutData.class, "valid");
		try {
			ExtentTest childTest7 = testListener.startChild("Login test");
			childTest7.setDescription("This test verifies that  user is able to Login");
			TestListner.extentMap.get().put("child7", childTest7);

			browser.get(environmentURL);
			AccountPage_Mobile accountPage = new AccountPage_Mobile(browser);
			String loggedUsername = accountPage.signInWithYourEmail(Login);
			sanityCS.assertTrue(loggedUsername.toLowerCase().equals(Login.getFirstName()), "Login test");

			String lastOrder=accountPage.getLastOrder();

			ExtentTest childTest1 = testListener.startChild("Add Any Shade product into Cart Test");
			childTest1.setDescription("This test verifies that user is able to add any shade product into cart");
			TestListner.extentMap.get().put("child1", childTest1);

			browser.get(environmentURL);
			HeaderBar_Mobile headerBar = new HeaderBar_Mobile(browser);
			headerBar.clearCart();
			headerBar.navigateToCategory(headerbar1);
			ListingPage_Mobile listingPage = new ListingPage_Mobile(browser);
			ProductDetailData ShadeproductDetadata = listingPage.addAnyShadeProductFromAllProduct();
			CartPage_Mobile_New cartPage = new CartPage_Mobile_New(browser);
			/*sanityCS.assertTrue(cartPage.isProductPresentInBag(ShadeproductDetadata),
					"Add shade product into cart test");

			ExtentTest childTest2 = testListener.startChild("Apply Coupon Code Test");
			childTest2.setDescription("This test verifies that guest user is able to apply coupon code");
			TestListner.extentMap.get().put("child2", childTest2);*/
			CartPage_Mobile_New CartPage_Mobile = new CartPage_Mobile_New(browser);
			CartPage_Mobile.goToCart();
			//boolean isCouponApplied = CartPage_Mobile.ApplyCoupon(checkOutData);
			//sanityCS.assertTrue(isCouponApplied, "150");

			ExtentTest childTest3 = testListener.startChild("Shipping Charge Test");
			childTest3.setDescription("This test verifies the shipping charges");
			TestListner.extentMap.get().put("child3", childTest3);
			String shippingText = cartPage.getShoppingTableData("Shipping");
			TestListner.testing.get().log(LogStatus.INFO,"Shipping Charge  '" + shippingText + "'");
			if(Integer.parseInt(ShadeproductDetadata.getProductPrize())<500)
			{
				sanityCS.assertEquals(shippingText, "50", "153");
			}
			else
			{
				sanityCS.assertEquals(shippingText, "Free", "153");
			}

			ExtentTest childTest4 = testListener.startChild("Add product with size variant to cart test");
			childTest4
					.setDescription("This test verifies that user is able to add a product with size variant to cart");
			TestListner.extentMap.get().put("child4", childTest4);
			CartPage_Mobile.gotoHome();
			headerBar.navigateToCategory(headerbar2);
			ProductDetailData SizeproductDetadata = listingPage.addAnySizeProductFromAllProduct();
			sanityCS.assertTrue(cartPage.isProductPresentInBag(SizeproductDetadata), "Add Size product to cart");

			ArrayList<ProductDetailData> productDataList=new ArrayList<ProductDetailData>();
			productDataList.add(ShadeproductDetadata);
			productDataList.add(SizeproductDetadata);

			/*ExtentTest childTest5 = testListener.startChild("Fill New Address/Select Address Test");
			childTest5.setDescription("This test verifies that new user is able to fill new address/select existing address");
			TestListner.extentMap.get().put("child5", childTest5);
			cartPage.checkOut();
			CheckOutPage_Mobile checkOutPage = new CheckOutPage_Mobile(browser);
			boolean addressAdded = checkOutPage.fillNewAddressLoginUser(checkOutData);
			sanityCS.assertTrue(addressAdded, "Adding of new address test")*/;

			ExtentTest childTest6 = testListener.startChild("Place COD order test");
			childTest6.setDescription("This test verifies that user is able to place COD order");
			TestListner.extentMap.get().put("child6", childTest6);
			cartPage.checkOut();
			CheckOutPage_Mobile checkOutPage = new CheckOutPage_Mobile(browser);
			boolean isCashonDelivery = checkOutPage.CashOnDelivery();

			sanityCS.assertTrue(isCashonDelivery, "L241");

			ExtentTest childTest10 = testListener.startChild("My Orders Page Test");
			childTest10.setDescription("This test verifies that the order just placed is present in My Orders");
			TestListner.extentMap.get().put("child10", childTest10);
			CheckOutPage_Mobile_New checkOutPage_mobile = new CheckOutPage_Mobile_New(browser);
			checkOutPage_mobile.gotoHome();
			String Order_id = String.valueOf(accountPage.OrderId_conformationMsg());
			boolean isOrderPresent=accountPage.orderPresentInMyOrders(Order_id,lastOrder);
			sanityCS.assertTrue(isOrderPresent, "O_1");

			if(isOrderPresent)
			{
				ExtentTest childTest11 = testListener.startChild("Order Details Verification in My Orders Test");
				childTest11.setDescription("This test verifies that the Details displayed in My Orders are correct.");
				TestListner.extentMap.get().put("child11", childTest11);
				sanityCS.assertTrue(accountPage.verifyOrderDetails(productDataList),"O_2");

				ExtentTest childTest12 = testListener.startChild("Item Level Cancellation Test");
				childTest12.setDescription("This test verifies that user is able to cancel individual items in the order");
				TestListner.extentMap.get().put("child12", childTest12);
				sanityCS.assertTrue(accountPage.cancel_Items(),"O_4");
			}

		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser, e);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {
			browser.quit();
		}
	}

	
	@Test( groups = {"MProdBug"})
	public void Mobile_Checkout_Check_User_Cart_Is_Not_Empty_register_user()
			throws URISyntaxException, Throwable {
		AccountData Login = LoginSync.getInstance().getLogin();
		// TestListner tL = new TestListner();
		Header_FooterBarData HeaderBar_Mobile1 = framework.getData(Header_FooterBarData.class, "shadenavigation_Mobile");
		Header_FooterBarData HeaderBar_Mobile2 = framework.getData(Header_FooterBarData.class, "sizenavigation_Mobile");
		CheckoutData checKoutData = framework.getData(CheckoutData.class, "valid");
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		AccountPage_Mobile accountPage = new AccountPage_Mobile(browser);
		String lastOrder=null;
		ArrayList<ProductDetailData> productDataList=new ArrayList<ProductDetailData>();
		try {
			
			ExtentTest childTest = testListener.startChild("Existing User Login test");
			childTest.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child", childTest);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			String LoggedUserName = AccountPage_Mobile.signInWithYourEmail(Login).toLowerCase();
			SanCS.assertEquals(LoggedUserName, Login.getFirstName().toLowerCase(), "L1");
			
		
			ExtentTest childTest8 = testListener.startChild("Simple Product add to cart Test");
			childTest8
			.setDescription("This test verifies that product is present in cart");
			TestListner.extentMap.get().put("child8", childTest8);
			
			
			browser.get(environmentURL);
			CartPage_Mobile_New CartPage_Mobile = new CartPage_Mobile_New(browser);
			boolean isProductPresent = CartPage_Mobile.isProductPresentInBag();
			SanCS.assertTrue(isProductPresent, "Product Present in Cart");
			
			browser.get(environmentURL+"/shoppingBag");
			isProductPresent = CartPage_Mobile.isProductPresentInBagReact();
			SanCS.assertTrue(isProductPresent, "Product Present in React Cart");
			

			
		} catch (Throwable e) {
			framework.logErrorWithSnapshot(browser, e);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {
			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();
		}

	}
	
	
	@Test( groups = { "sanity", "Msanity","sanity_Live","N_sanity_Live","Mcheckout","checkout" ,"sabAut"})
	public void Mobile_ReviewAll_Page_Login_user()
			throws URISyntaxException, Throwable {
		AccountData Login = LoginSync.getInstance().getLogin();
		// TestListner tL = new TestListner();
		Header_FooterBarData HeaderBar_Mobile1 = framework.getData(Header_FooterBarData.class, "shadenavigation_Mobile");
		CheckoutData checKoutData = framework.getData(CheckoutData.class, "valid");
		WebDriver browser = framework.getBrowser("Msite");
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		ArrayList<ProductDetailData> productDataList=new ArrayList<ProductDetailData>();
		try {
			ExtentTest childTest = testListener.startChild("Existing User Login test");
			childTest.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child", childTest);
			browser.get(environmentURL);
			AccountPage_Mobile AccountPage_Mobile = new AccountPage_Mobile(browser);
			String LoggedUserName = AccountPage_Mobile.signInWithYourEmail(Login).toLowerCase();
			SanCS.assertEquals(LoggedUserName, Login.getFirstName().toLowerCase(), "L1");
			
			ExtentTest childTest8 = testListener.startChild("Navigating to PDP page of any product with reviews ");
			childTest8
			.setDescription("This test verifies that user is able to see all the reviews of a product ");
			TestListner.extentMap.get().put("child8", childTest8);
			HeaderBar_Mobile HeaderBar_Mobile = new HeaderBar_Mobile(browser);
			browser.get(environmentURL);
			HeaderBar_Mobile.navigateToCategory(HeaderBar_Mobile1);
			ListingPage_Mobile listingPage = new ListingPage_Mobile(browser);
			String message = 	listingPage.AddreviewOnALLReviewPage();
			SanCS.assertEquals(message, "Please validate captcha");

		
		} catch (Throwable e) {
			framework.logErrorWithSnapshot(browser, e);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {
			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();
		}

	}
}
