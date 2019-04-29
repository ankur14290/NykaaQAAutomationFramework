package TestCaseSuite;



import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import DataNykaa.*;
import PagesNykaa.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import DataNykaa.AccountData;
import DataNykaa.AssertData;
import DataNykaa.BeautyServiceData;
import DataNykaa.CheckoutData;
import DataNykaa.EnvironmentParameterData;
import DataNykaa.Header_FooterBarData;
import DataNykaa.ProductDetailData;
import FrameWorkNykaa.BrowserAction;
import FrameWorkNykaa.CommonConstants;
import FrameWorkNykaa.CustomSoftAssert;
import FrameWorkNykaa.Framework;
import FrameWorkNykaa.LoginSync;
import FrameWorkNykaa.PartialStringList;
import FrameWorkNykaa.PropertyConfiguration;
import FrameWorkNykaa.TestListner;

public class Z_SanitySuite_New {

	Framework framework = new Framework();
	Logger log = Logger.getLogger(Z_SanitySuite_New.class.getName());
	TestListner testListener = new TestListner();
	String ClassName = this.getClass().getSimpleName();
	String PackageName = this.getClass().getPackage().getName();
	Properties properties = System.getProperties();
	String environmentURL ="";
	AssertData SanAssertData = new AssertData("Sanity Checklist", "Desktop", "AutomationResult", "SubElements",
			properties.getProperty("BUILD_URL") + "testngreports/" + PackageName + "/" + ClassName + "/");

	@BeforeClass(alwaysRun = true)
	public void preSetupFortest() throws Throwable {
		String resultField = System.getProperty("ResultFieldName");
		log.info("Testing of Logger");
		if (resultField != null) {
			SanAssertData = new AssertData("Sanity Checklist", "Desktop", resultField, "SubElements",
					properties.getProperty("BUILD_URL") + "testngreports/" + PackageName + "/" + ClassName + "/");
		}
		EnvironmentParameterData EnvironmentData = framework.getData(EnvironmentParameterData.class, "Web");
		String envURL = System.getProperty("URL");
		if (envURL != null) {
			environmentURL = envURL;

		} else {
			environmentURL = EnvironmentData.getBaseurl();
			// environmentURL = "http://www.nykaa.com";
		}

	}

	@AfterMethod(alwaysRun=true)
	public void flushExtent()
	{
		Framework.extentReports.flush();
	}

	private void printStackStraceinLog(Throwable ex) {

		StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));
		Reporter.log(errors.toString(),true);
	}

	/**********************************************************************
	 * CheckoutTestCases
	 ****************************************************************************************************************/

	@Test(groups = { "sanity","Wsanity","Wcheckout","checkout","local"})
	public void Checkout_Flow_With_Greater_Than_500_Already_register_user() throws URISyntaxException, Throwable {

		AccountData Login = LoginSync.getInstance().getLogin();
		Header_FooterBarData headerbar1 = framework.getData(Header_FooterBarData.class, "shadenavigation");
		Header_FooterBarData headerbar2 = framework.getData(Header_FooterBarData.class, "sizenavigation");
		Header_FooterBarData headerbar3 = framework.getData(Header_FooterBarData.class, "l2category_react");
		CheckoutData checKoutData = framework.getData(CheckoutData.class, "valid");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		String lastOrder=null;
		String message=null;
		boolean isShadeProductAdded=false;
		boolean isSizeProductAdded=false;
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		ArrayList<ProductDetailData> productDataList=new ArrayList<ProductDetailData>();

		try {
			ExtentTest childTest1 = testListener.startChild("Existing User Login test");
			childTest1.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child1", childTest1);

			browser.get(environmentURL);
			AccountPage_React accountPage = new AccountPage_React(browser);
			String LoggedUserName = accountPage.ReactSignInwithEmail(Login).toLowerCase();
			SanCS.assertTrue((Login.getFirstName().toLowerCase().contains(LoggedUserName.toLowerCase().replaceAll("[^0-9]", ""))),  "L1");

			ExtentTest childTest13 = testListener.startChild("Note last order ID test");
			childTest13.setDescription("This test notes the last order ID from My Orders. ");
			TestListner.extentMap.get().put("child13", childTest13);
			lastOrder=accountPage.getLastOrder("");

			ExtentTest childTest2 = testListener.startChild("Add shade product to cart test");
			childTest2.setDescription(
					"This test verifies that user is able to add shade product to cart");
			TestListner.extentMap.get().put("child2", childTest2);
			HeaderBar headerBar = new HeaderBar(browser);
			headerBar.clearCart();
			browser.get(environmentURL);
			headerBar.navigateToCategory(headerbar1,environmentURL);			
			// Add to bag:
			/**********************
			 * test 500+
			 *************************************************/
			ListingPage_React listingPage = new ListingPage_React(browser);
			ProductDetailData SimpleProductDetailData=null;
			ProductDetailData ShadeproductDetadata = null;
			ProductDetailData SizeproductDetadata =null;
			ShadeproductDetadata = listingPage.addAnyShadeProductFromAllProduct();
			if(ShadeproductDetadata.getProductName()!=null)
			{
				isShadeProductAdded=true;
				Integer.parseInt(ShadeproductDetadata.getProductPrize());
			}
			SanCS.assertTrue(isShadeProductAdded, "R_79D_B");

			ExtentTest childTest14 = testListener.startChild("Add simple product to cart test");
			childTest14.setDescription(
					"This test verifies that user is able to add simple product to cart");
			TestListner.extentMap.get().put("child14", childTest14);
			headerBar.navigateToCategory(headerbar3,environmentURL);
			SimpleProductDetailData = listingPage.addAnySimpleProductFromAllProducts();
			if(SimpleProductDetailData.getProductName()!=null)
			{
				Integer.parseInt(SimpleProductDetailData.getProductPrize());
			}

			ExtentTest childTest3 = testListener.startChild("Add product with size variant to cart test");
			childTest3
			.setDescription("This test verifies that user is able to add a product with size variant to cart");
			TestListner.extentMap.get().put("child3", childTest3);

			headerBar.navigateToCategory(headerbar2,environmentURL);

			SizeproductDetadata = listingPage.addAnySizeProductFromAllProduct();
			if(SizeproductDetadata.getProductName()!=null)
			{
				isSizeProductAdded=true;
				Integer.parseInt(SizeproductDetadata.getProductPrize());
			}
			SanCS.assertTrue(isSizeProductAdded, "R_79D_C");

			/***********************************************************************************************/

			productDataList.add(SimpleProductDetailData);
			productDataList.add(ShadeproductDetadata);
			productDataList.add(SizeproductDetadata);

			ExtentTest childTest4 = testListener.startChild("Select Delivery Address test");
			childTest4.setDescription("This test verifies that user is able to select/enter delivery address");
			TestListner.extentMap.get().put("child4", childTest4);
			CartPage_React cartPage=new CartPage_React(browser);
			CheckOutPage_React checkOutPage = new CheckOutPage_React(browser);
			cartPage.checkOut();
			String checkouturl = "";
			while (!browser.getCurrentUrl().contains("checkout")) {
				Thread.sleep(1000);
			}

			checkouturl = browser.getCurrentUrl();

			if (browser.getCurrentUrl().contains(environmentURL.substring(environmentURL.lastIndexOf("/") + 1))) {
				checkOutPage.savedAddressSelect();

				//SanCS.assertEquals(checkOutPage.isAddressSelected(), "true", "170");
				// If signed in then it should land on shipping address page.

			} if(checkOutPage.isAddressSelected()==null) {

				checkOutPage.fillNewAddress(checKoutData);
			}
			SanCS.assertEquals(checkOutPage.isAddressSelected(), "true", "170");

			ExtentTest childTest5 = testListener.startChild("Checkout with Debit/Credit card test");
			childTest5.setDescription(
					"This test verifies that user is able to checkout with Credit/Debit card as payment method");
			TestListner.extentMap.get().put("child5", childTest5);

			checkOutPage.checkoutWithDebitCard(checKoutData);
			BankingPage bankPage = new BankingPage(browser);

			SanCS.assertTrue(bankPage.isPayUPage(), "L233");

			// Enter "Card Number"

			// Enter "Card Holder name"

			// Enter "Expiry Date"

			ExtentTest childTest6 = testListener.startChild("Checkout with Net Banking test");
			childTest6.setDescription(
					"This test verifies that user is able to checkout with Net Banking as payment method");
			TestListner.extentMap.get().put("child6", childTest6);
			browser.get(checkouturl);
			System.out.println(checkouturl);
			boolean isNetBanking = checkOutPage.netBankingCheckout();
			SanCS.assertTrue(isNetBanking, "L235");

			/*
			 * browser.get(checkouturl); boolean isPayuCheckout =
			 * checkOutPage.payumoneyCheckout();
			 *
			 * SanCS.assertTrue(isPayuCheckout, "243");
			 */
			ExtentTest childTest7 = testListener.startChild("Checkout with Wallet test");
			childTest7.setDescription("This test verifies that user is able to checkout with Wallet as payment method");
			TestListner.extentMap.get().put("child7", childTest7);
			browser.get(checkouturl);
			boolean isMobikwik = checkOutPage.PaytmWalletCheckout();

			SanCS.assertTrue(isMobikwik, "247");

			ExtentTest childTest8 = testListener.startChild("Place COD order test");
			childTest8.setDescription("This test verifies that user is able to place COD order");
			TestListner.extentMap.get().put("child8", childTest8);

			browser.get(checkouturl);
			boolean isCashonDelivery = checkOutPage.CashOnDelivery();

			SanCS.assertTrue(isCashonDelivery, "L241");


			ExtentTest childTest9 = testListener.startChild("Convert Quote to Order by running Cron");
			childTest9.setDescription("This test verifies that user is able to convert Quote to Order");
			TestListner.extentMap.get().put("child9", childTest9);

			message=accountPage.convertCODQuoteToOrder();
			List<String>ordersInCovertQTOCronMessage=new ArrayList<String>();
			if(message!=null)
			{
				try {
					String[] orderID=message.split("\n");
					for(int i=0;i<orderID.length;i++)
					{
						if(orderID[i].contains("IncrementId"))
							ordersInCovertQTOCronMessage.add(orderID[i].substring(orderID[i].indexOf("IncrementId:")).split("~")[0].replaceAll("[^0-9]", ""));
					}
				}
				catch(Exception e) {}
				TestListner.testing.get().log(LogStatus.INFO,"Order ID: "+ordersInCovertQTOCronMessage.toString());
				SanCS.assertTrue(message!=null, "Convert Quote to Order ");
			}

			ExtentTest childTest10 = testListener.startChild("My Orders Page Test");
			childTest10.setDescription("This test verifies that the order just placed is present in My Orders");
			TestListner.extentMap.get().put("child10", childTest10);
			boolean isOrderPresent=accountPage.orderPresentInMyOrders(ordersInCovertQTOCronMessage,lastOrder ,CommonConstants.NKYAA);
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



			//SanCS.assertAll();

		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser, e);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {

			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();

		}

	}


	@Test(groups = { "sanity","Wsanity","react","guest","sanity_Live","N_sanity_Live","Wcheckout","checkout","Sourabh" })

	public void Check_end_to_end_checkout_flow_with_Guest_User() throws URISyntaxException, Throwable {

		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		AccountData Login = LoginSync.getInstance().getLogin();
		Header_FooterBarData headerbar1 = framework.getData(Header_FooterBarData.class, "shadenavigation");
		Header_FooterBarData headerbar2 = framework.getData(Header_FooterBarData.class, "sizenavigation");
		CheckoutData checKoutData = framework.getData(CheckoutData.class, "valid");
		//AnalyticsData analyticsCODData = framework.getData(AnalyticsData.class, "checkoutcod");
		try {
			ExtentTest childTest1 = testListener
					.startChild("Add products to cart and checkout as Guest with Credit/Debit Card");
			childTest1.setDescription(
					"This test verifies that guest user is able to add multiple products to cart and checkout with Debit/Credit card");
			TestListner.extentMap.get().put("child1", childTest1);

			browser.get(environmentURL);

			// Sign in with username
			HeaderBar headerBar = new HeaderBar(browser);
			headerBar.clearCart();
			ListingPage_React listingPage = new ListingPage_React(browser);
			CartPage_React cartPage = new CartPage_React(browser);
			headerBar.navigateToCategory(headerbar1,environmentURL);
			listingPage.ApplyFilter("Price", "Rs. 500 - Rs. 999", false, false);
			ProductDetailData ShadeproductDetadata = listingPage.addAnyShadeProductFromAllProduct();
			cartPage.isProductPresentInBag(ShadeproductDetadata);
			int subtotal = Integer.parseInt(cartPage.getShoppingTableData("Subtotal").replaceAll("[^0-9]", ""));
			if(subtotal < 500)
			{
				headerBar.navigateToCategory(headerbar2,environmentURL);
				listingPage.ApplySort("price: high to low");
				ProductDetailData SizeproductDetadata = listingPage.addAnySizeProductFromAllProduct();
				cartPage.isProductPresentInBag(SizeproductDetadata);
				subtotal = Integer.parseInt(cartPage.getShoppingTableData("Subtotal").replaceAll("[^0-9]", ""));
				if(subtotal < 500)
				{
					listingPage.ApplySort("price: high to low");
					ProductDetailData SimpleproductDetadata = listingPage.addSimpleProductFromAllProduct();
					cartPage.isProductPresentInBag(SimpleproductDetadata);
				}
			}
			cartPage.openSlidingCart();
			subtotal = Integer.parseInt(cartPage.getShoppingTableData("Subtotal").replaceAll("[^0-9]", ""));
			while (subtotal < 500) {
				cartPage.increaseFirstProduct();
				cartPage.openSlidingCart();
				subtotal = Integer.parseInt(cartPage.getShoppingTableData("Subtotal").replaceAll("[^0-9]", ""));
			}
			CheckOutPage_React checkOutPage = new CheckOutPage_React(browser);
			cartPage.ApplyCoupon(checKoutData).contains("-");
			cartPage.checkOut();
			while (!browser.getCurrentUrl().contains("shoppingBag")) {
				Thread.sleep(1000);
			}
			//String checkouturl = browser.getCurrentUrl();
			checkOutPage.fillbillingEmailIDAndContinueAsGuest(checKoutData);
			checkOutPage.fillNewAddress(checKoutData);
			String checkouturl = browser.getCurrentUrl();
			CheckoutData CheckoutDataOfUI = checkOutPage.FetchOrderDetail(checKoutData);
			List<ProductDetailData> Items = checkOutPage.FetchProductDetail();
			Collections.reverse(Items);
			for (ProductDetailData item : Items) {
				System.out.println(item.getProductName());
				System.out.println(item.getProductPrize());
			}
			checkOutPage.checkoutWithDebitCard(checKoutData);
			BankingPage bankPage = new BankingPage(browser);
			SanCS.assertTrue(bankPage.isPayUPage(), "L233");

			browser.get(checkouturl);

			ExtentTest childTest2 = testListener.startChild("Guest User Checkout with Net Banking test");
			childTest2.setDescription(
					"This test verifies that Guest user is able to checkout with Net Banking as payment method");
			TestListner.extentMap.get().put("child2", childTest2);

			System.out.println(checkouturl);
			boolean isNetBanking = checkOutPage.netBankingCheckout();

			SanCS.assertTrue(isNetBanking, "Net Banking Checkout");

			ExtentTest childTest3 = testListener.startChild("Guest User Checkout with Wallet test");
			childTest3.setDescription(
					"This test verifies that Guest user is able to checkout with Wallet as payment method");
			TestListner.extentMap.get().put("child3", childTest3);
			browser.get(checkouturl);
			boolean isMobikwik = checkOutPage.PaytmWalletCheckout();

			SanCS.assertTrue(isMobikwik, "Wallet Checkout");

			ExtentTest childTest4 = testListener.startChild("Place COD order as Guest user test");
			childTest4.setDescription("This test verifies that Guest user is able to place COD order");
			TestListner.extentMap.get().put("child4", childTest4);
			browser.get(checkouturl);
			boolean cashonDelivery = checkOutPage.CashOnDelivery();
			SanCS.assertTrue(cashonDelivery, "241");
// below cases Will update Later
			/*ExtentTest childTest5 = testListener.startChild("Payment details verification for Guest user test");
			childTest5.setDescription(
					"This test verifies that Guest user payment details are displayed correctly on confirmation Page");
			TestListner.extentMap.get().put("child5", childTest5);

			ConfirmationPage confirmationPage = new ConfirmationPage(browser);
			confirmationPage.getPaymentData();
			boolean isPaymentDetail = confirmationPage.verifyPaymentDetailWithCheckoutData(CheckoutDataOfUI);
			SanCS.assertTrue(isPaymentDetail, "257");
			ExtentTest childTest6 = testListener.startChild("Delivery details verification for Guest user test");
			childTest6.setDescription(
					"This test verifies that Guest user delivery details are displayed correctly on confirmation Page");
			TestListner.extentMap.get().put("child6", childTest6);

			boolean isDeliveryDetail = confirmationPage.verifyDeliveryDetailWithCheckoutData(CheckoutDataOfUI);
			SanCS.assertTrue(isDeliveryDetail, "256");

			ExtentTest childTest7 = testListener.startChild("Item details verification for Guest user test");
			childTest7.setDescription(
					"This test verifies that Guest user Item details are displayed correctly on confirmation Page");
			TestListner.extentMap.get().put("child7", childTest7);
			String[] actualproductdata=Items.toString().split(",");
			String[] expectedproductdata=confirmationPage.verifyOrderItemDetail().toString().split(",");
			String actualProductDataString="";
			String expectedProductDataString="";
			for(String value:actualproductdata)
			{
				if(!value.contains("null"))
					actualProductDataString=actualProductDataString+" : "+value;
			}
			for(String value:expectedproductdata)
			{
				if(!value.contains("null"))
					expectedProductDataString=expectedProductDataString+" : "+value;
			}

			TestListner.testing.get().log(LogStatus.INFO, "Expected Item Details ::"+actualProductDataString);
			TestListner.testing.get().log(LogStatus.INFO, "Expected Item Details ::"+expectedProductDataString);


			SanCS.assertTrue(Items.toString().equals(confirmationPage.verifyOrderItemDetail().toString()), "259");*/
			// SanCS.assertTrue(Items.equals(Items), "259");
			//	analyticinfo.getEvar(analyticsCODData);
			//analyticinfo.getEvent(analyticsCODData);
			//SanCS.assertAll();

		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser, e);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {

			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();

		}

	}

	@Test(groups = { "Smoke", "checkout03", "rewardpoint","Wcheckout","checkout", "Debug" })
	public void Check_end_to_end_checkout_flow_order_less_than_500() throws URISyntaxException, Throwable {
		// this.environmentURL = prop.getProperty("environmentName");

		Framework framework = new Framework();
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);

		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);

		AccountData accountData = framework.getData(AccountData.class, "newlogin");
		Header_FooterBarData headerbar1 = framework.getData(Header_FooterBarData.class, "l2category");
		CheckoutData checKoutData = framework.getData(CheckoutData.class, "valid");
		// ProductDetailData productDetailData =
		// framework.getData(ProductDetailData.class, "SimpleProduct");
		try {
			browser.get(environmentURL);

			ExtentTest childTest1 = testListener.startChild("New User Register Test");
			childTest1.setDescription("This test verifies that new user is able to register");
			TestListner.extentMap.get().put("child1", childTest1);

			AccountPage_React accountPage = new AccountPage_React(browser);

			String loginUser = accountPage.NewRegister_React(accountData);
			SanCS.assertTrue(loginUser.toLowerCase().contains(accountData.getFirstName().toLowerCase()), "New user registration test");
			// Sign in with username
			ExtentTest childTest2 = testListener.startChild("Add Product to the Cart test");
			childTest2.setDescription(
					"This test verifies that new user is able to add product of price less than 500 into cart");
			TestListner.extentMap.get().put("child2", childTest2);

			HeaderBar headerBar = new HeaderBar(browser);

			headerBar.clearCart();
			headerBar.navigateToCategory(headerbar1,environmentURL);
			ListingPage listingPage = new ListingPage(browser);

			List<ProductDetailData> productList = listingPage.getProductList(listingPage.allProducts());
			List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
			productlistwithPrizeFilter = productList
					.stream().filter(o -> Integer.parseInt(o.getProductPrize()) < 500
							& !o.getProductType().contains("Size") & !o.getProductType().contains("Shade"))
					.collect(Collectors.toList());
			boolean isAdded = false;
			for (ProductDetailData product : productlistwithPrizeFilter) {
			//	isAdded = listingPage.addSimpleProductFromList();
				if (isAdded == true) {
					break;
				}
			}
			SanCS.assertTrue(isAdded, "Adding product in cart test");
			// Add to bag:
			ExtentTest childTest3 = testListener.startChild("Fill New Address Test");
			childTest3.setDescription("This test verifies that new user is able to fill new address");
			TestListner.extentMap.get().put("child3", childTest3);

			CartPage cartPage = new CartPage(browser);
			CheckOutPage_React checkOutPage = new CheckOutPage_React(browser);
			cartPage.checkOut();
			boolean addressAdded = checkOutPage.fillNewAddress(checKoutData);
			SanCS.assertTrue(addressAdded, "Adding of new address test");

			ExtentTest childTest4 = testListener.startChild("Apply Reward Points Test");
			childTest4.setDescription("This test verifies that new user is able to apply reward points");
			TestListner.extentMap.get().put("child4", childTest4);

			boolean isRewardApplied = checkOutPage.isRewardPointApplied();
			SanCS.assertTrue(isRewardApplied, "222");
			//SanCS.assertAll();

		} catch (Exception e) {
			printStackStraceinLog(e);

			Reporter.log(e.toString());
			Assert.fail();
		} finally {
			browser.quit();

		}

	}


	@Test(groups = { "sanity","Wsanity","sanity_Live","N_sanity_Live"})

	public void Verify_user_is_Able_To_Apply_and_Remove_CouponCode() throws URISyntaxException, Throwable {
		Header_FooterBarData headerbar1 = framework.getData(Header_FooterBarData.class, "shadenavigation");
		AccountData Login = LoginSync.getInstance().getLogin();
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert sanityCS = new CustomSoftAssert(SanAssertData, browser);
		CheckoutData checkOutData = framework.getData(CheckoutData.class, "valid");
		try {
			ExtentTest childTest7 = testListener.startChild("Login test");
			childTest7.setDescription("This test verifies that  user is able to Login");
			TestListner.extentMap.get().put("child7", childTest7);

			browser.get(environmentURL);
			AccountPage_React accountPage = new AccountPage_React(browser);
			String loggedUsername = accountPage.ReactSignInwithEmail(Login);
			System.out.println(Login.getFirstName());
			sanityCS.assertTrue((Login.getFirstName().toLowerCase().contains(loggedUsername.toLowerCase().replaceAll("[^0-9]", ""))),  "Login Test");
			HeaderBar headerBar = new HeaderBar(browser);
			headerBar.clearCart();
			ExtentTest childTest1 = testListener.startChild("Add Any Shade product into Cart Test");
			childTest1.setDescription("This test verifies that user is able to add any shade product into cart");
			TestListner.extentMap.get().put("child1", childTest1);

			browser.get(environmentURL);
			headerBar.navigateToCategory(headerbar1,environmentURL);
			ListingPage_React listingPage = new ListingPage_React(browser);
			ProductDetailData ShadeproductDetadata = listingPage.addAnyShadeProductFromAllProduct();
			CartPage_React cartPage = new CartPage_React(browser);
			sanityCS.assertTrue(cartPage.isProductPresentInBag(ShadeproductDetadata),
					"Add shade product into cart test");

			ExtentTest childTest2 = testListener.startChild("Apply Coupon Code Test");
			childTest2.setDescription("This test verifies that guest user is able to apply coupon code");
			TestListner.extentMap.get().put("child2", childTest2);
			boolean isCouponApplied = cartPage.ApplyCoupon(checkOutData).contains("-");
			sanityCS.assertTrue(isCouponApplied, "150");

			ExtentTest childTest8 = testListener.startChild("Remove Coupon Code Test");
			childTest8.setDescription("This test verifies that user is able to remove coupon code");
			TestListner.extentMap.get().put("child8", childTest8);
			isCouponApplied = cartPage.CancelCoupon();
			sanityCS.assertTrue(isCouponApplied, "151");
			isCouponApplied = cartPage.ApplyCoupon(checkOutData).contains("-");
			sanityCS.assertTrue(isCouponApplied, "150");

			ExtentTest childTest5 = testListener.startChild("Checkout after applying Coupon Test");
			childTest5.setDescription("This test verifies that user is able to checkout after applying coupon");
			TestListner.extentMap.get().put("child5", childTest5);
			sanityCS.assertEquals(cartPage.checkOut(), "successful"," Navigate to checkout after applying coupon");

		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser, e);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {
			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();

		}

	}

	@Test(groups = {  "coupon", "simple", "checkout02" })
	public void Check_end_to_end_checkout_flow_with_new_Registered_User_using_SimplePay() throws Throwable {
		Header_FooterBarData headerbar1 = framework.getData(Header_FooterBarData.class, "shadenavigation");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert sanityCS = new CustomSoftAssert(SanAssertData, browser);
		AccountData accountData = framework.getData(AccountData.class, "newlogin");
		CheckoutData checkOutData = framework.getData(CheckoutData.class, "simplepay");
		try {
			ExtentTest childTest1 = testListener.startChild("Add Any Shade product into Cart Test");
			childTest1.setDescription("This test verifies that guest user is able to add any shade product into cart");
			TestListner.extentMap.get().put("child1", childTest1);

			browser.get(environmentURL);
			HeaderBar headerBar = new HeaderBar(browser);
			headerBar.clearCart();
			headerBar.navigateToCategory(headerbar1,environmentURL);
			ListingPage listingPage = new ListingPage(browser);
			ProductDetailData ShadeproductDetadata = listingPage.addAnyShadeProductFromAllProduct();
			CartPage cartPage = new CartPage(browser);
			sanityCS.assertTrue(cartPage.isProductPresentInBag(ShadeproductDetadata),
					"Add shade product into cart test");

			ExtentTest childTest2 = testListener.startChild("Apply Coupon Code Test");
			childTest2.setDescription("This test verifies that guest user is able to apply coupon code");
			TestListner.extentMap.get().put("child2", childTest2);
			boolean isCouponApplied = cartPage.ApplyCoupon(checkOutData).contains("-");
			sanityCS.assertTrue(isCouponApplied, "150");

			ExtentTest childTest3 = testListener.startChild("Shipping Charge Test");
			childTest3.setDescription("This test verifies the shipping charges");
			TestListner.extentMap.get().put("child3", childTest3);
			String shippingText = cartPage.getShoppingTableData("Shipping");
			TestListner.testing.get().log(LogStatus.INFO,"Shipping Charge  '" + shippingText + "'");
			if(Integer.parseInt(ShadeproductDetadata.getProductPrize())<500)
			{
				sanityCS.assertEquals(shippingText, "Rs. 50", "153");
			}
			else
			{
				sanityCS.assertEquals(shippingText, "Free", "153");
			}

			cartPage.checkOut();
			CheckOutPage_React checkOutPage = new CheckOutPage_React(browser);

			ExtentTest childTest4 = testListener.startChild("New User Register Test");
			childTest4.setDescription("This test verifies that new user is able to register");
			TestListner.extentMap.get().put("child4", childTest4);
			sanityCS.assertTrue(checkOutPage.newRegister(accountData), "270");

			ExtentTest childTest5 = testListener.startChild("Fill New Address Test");
			childTest5.setDescription("This test verifies that new user is able to fill new address");
			TestListner.extentMap.get().put("child5", childTest5);
			boolean addressAdded = checkOutPage.fillNewAddress(checkOutData);
			sanityCS.assertTrue(addressAdded, "Adding of new address test");

			ExtentTest childTest6 = testListener.startChild("Checkout With SimplePay Test");
			childTest6.setDescription("This test verifies that new registerd user is able to checkout with SimplePay");
			TestListner.extentMap.get().put("child6", childTest6);
			String OTPConfirmTelephone = checkOutPage.checkoutWithSimple();
			sanityCS.assertTrue(OTPConfirmTelephone.contains(checkOutData.getTelephone()), "L238");
		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser, e);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {
			browser.quit();
		}
	}

	@Test(groups = { "sanity","Wsanity","cancellation","Wcancellation","Test" })
	public void Check_end_to_end_checkout_flow_and_Item_Level_Cancellation() throws Throwable {
		Header_FooterBarData headerbar1 = framework.getData(Header_FooterBarData.class, "shadenavigation");
		Header_FooterBarData headerbar2 = framework.getData(Header_FooterBarData.class, "sizenavigation");
		AccountData Login = LoginSync.getInstance().getLogin();
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert sanityCS = new CustomSoftAssert(SanAssertData, browser);
		CheckoutData checkOutData = framework.getData(CheckoutData.class, "valid");
		try {
			ExtentTest childTest7 = testListener.startChild("Login test");
			childTest7.setDescription("This test verifies that  user is able to Login");
			TestListner.extentMap.get().put("child7", childTest7);

			browser.get(environmentURL);
			AccountPage_React accountPage = new AccountPage_React(browser);
			String loggedUsername = accountPage.ReactSignInwithEmail(Login);
			sanityCS.assertTrue(loggedUsername.toLowerCase().equals(Login.getFirstName()), "Login test");

			String lastOrder=accountPage.getLastOrder("");

			ExtentTest childTest1 = testListener.startChild("Add Any Shade product into Cart Test");
			childTest1.setDescription("This test verifies that user is able to add any shade product into cart");
			TestListner.extentMap.get().put("child1", childTest1);

			browser.get(environmentURL);
			HeaderBar headerBar = new HeaderBar(browser);

			headerBar.clearCart();
			headerBar.navigateToCategory(headerbar1,environmentURL);

			ListingPage_React listingPage = new ListingPage_React(browser);
			ProductDetailData ShadeproductDetadata = listingPage.addAnyShadeProductFromAllProduct();
			CartPage_React cartPage = new CartPage_React(browser);
			sanityCS.assertTrue(cartPage.isProductPresentInBag(ShadeproductDetadata),
					"Add shade product into cart test");

			ExtentTest childTest2 = testListener.startChild("Apply Coupon Code Test");
			childTest2.setDescription("This test verifies that guest user is able to apply coupon code");
			TestListner.extentMap.get().put("child2", childTest2);
			boolean isCouponApplied = cartPage.ApplyCoupon(checkOutData).contains("-");
			sanityCS.assertTrue(isCouponApplied, "150");

			ExtentTest childTest3 = testListener.startChild("Shipping Charge Test");
			childTest3.setDescription("This test verifies the shipping charges");
			TestListner.extentMap.get().put("child3", childTest3);
			String shippingText = cartPage.getShoppingTableData("Shipping Charges").replaceAll(": Rs. ", "").replace(":", "").trim();
			TestListner.testing.get().log(LogStatus.INFO,"Shipping Charge  '" + shippingText + "'");
			if(Integer.parseInt(ShadeproductDetadata.getProductPrize())<500)
			{
				sanityCS.assertEquals(shippingText, "50", "153");
			}
			else
			{
				sanityCS.assertEquals(shippingText, "free", "153");
			}

			ExtentTest childTest4 = testListener.startChild("Add product with size variant to cart test");
			childTest4
			.setDescription("This test verifies that user is able to add a product with size variant to cart");
			TestListner.extentMap.get().put("child4", childTest4);

			headerBar.navigateToCategory(headerbar2,environmentURL);

			ProductDetailData SizeproductDetadata = listingPage.addAnySizeProductFromAllProduct();
			sanityCS.assertTrue(cartPage.isProductPresentInBag(SizeproductDetadata), "Add Size product to cart");

			ArrayList<ProductDetailData> productDataList=new ArrayList<ProductDetailData>();
			productDataList.add(ShadeproductDetadata);
			productDataList.add(SizeproductDetadata);

			ExtentTest childTest5 = testListener.startChild("Fill New Address/Select Address Test");
			childTest5.setDescription("This test verifies that new user is able to fill new address/select existing address");
			TestListner.extentMap.get().put("child5", childTest5);
			cartPage.checkOut();
			CheckOutPage_React checkOutPage = new CheckOutPage_React(browser);
			boolean addressAdded = checkOutPage.fillNewAddress(checkOutData);
			sanityCS.assertTrue(addressAdded, "Adding of new address test");

			ExtentTest childTest6 = testListener.startChild("Place COD order test");
			childTest6.setDescription("This test verifies that user is able to place COD order");
			TestListner.extentMap.get().put("child6", childTest6);
			boolean isCashonDelivery = checkOutPage.CashOnDelivery();

			sanityCS.assertTrue(isCashonDelivery, "L241");


			/*ExtentTest childTest9 = testListener.startChild("Convert Quote to Order by running Cron");
			childTest9.setDescription("This test verifies that user is able to convert Quote to Order");
			TestListner.extentMap.get().put("child9", childTest9);

			String message=accountPage.convertCODQuoteToOrder();
			List<String>ordersInCovertQTOCronMessage=new ArrayList<String>();
			if(message!=null)
			{
				try {
					String[] orderID=message.split("\n");
					for(int i=0;i<orderID.length;i++)
					{
						if(orderID[i].contains("IncrementId"))
							ordersInCovertQTOCronMessage.add(orderID[i].substring(orderID[i].indexOf("IncrementId:")).split("~")[0].replaceAll("[^0-9]", ""));
					}
				}
				catch(Exception e) {}
				TestListner.testing.get().log(LogStatus.INFO,"Order ID: "+ordersInCovertQTOCronMessage.toString());
				sanityCS.assertTrue(message!=null, "Convert Quote to Order ");
			}*/

			ExtentTest childTest10 = testListener.startChild("Item Level Cancellation Test");
			childTest10.setDescription("This test verifies that user is able to cancel individual items in the order");
			TestListner.extentMap.get().put("child10", childTest10);
			sanityCS.assertTrue(accountPage.cancel_Items(),"O_4");


			/*ExtentTest childTest10 = testListener.startChild("My Orders Page Test");
			childTest10.setDescription("This test verifies that the order just placed is present in My Orders");
			TestListner.extentMap.get().put("child10", childTest10);
			boolean isOrderPresent=accountPage.orderPresentInMyOrders(ordersInCovertQTOCronMessage,lastOrder ,CommonConstants.NKYAA);
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
			}*/

		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser, e);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {
			browser.quit();
		}
	}

	@Test( groups = { "ProductPageaddtobag", "Analytics", "cart01" })
	//retryAnalyzer = Retry.class,
	public void Dynamic_Combo_ProductPage_Add_To_Bag() throws Throwable, Throwable {
		Framework framework = new Framework();
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert CS = new CustomSoftAssert(SanAssertData, browser);
		AccountData accountData = framework.getData(AccountData.class, "newlogin");
		Header_FooterBarData headerbar1 = framework.getData(Header_FooterBarData.class, "shadenavigation");
		try {
			ExtentTest childTest1 = testListener.startChild("New User Register Test");
			childTest1.setDescription("This test verifies that new user is able to register");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			AccountPage accountPage = new AccountPage(browser);
			String loggedInUser = accountPage.NewRegister(accountData);
			CS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()), "New user registration test");

			/*ExtentTest childTest2 = testListener.startChild("Add Dynamic Combo Product into Cart Test");
			childTest2.setDescription("This test verifies that new user is able to add dynamic combo into cart");
			TestListner.extentMap.get().put("child2", childTest2);
			HeaderBar headerbar = new HeaderBar(browser);
			headerbar.navigateToCategory(headerbar1);
			// ListingPage listingPage = new ListingPage(browser);
			ProductPage productPage = new ProductPage(browser);
			// ProductDetailData productDetailData = new ProductDetailData();
			CartPage cartPage = new CartPage(browser);
			browser.navigate().to(environmentURL
					+ "/makeup/eyes/kajal/lakme-absolute-kohl-ultimate.html?root=catg_Bestsellers&ptype=product&brand=catg_bestsellers");
			List<ProductDetailData> comboProductDetaillist = productPage.addDynamicCombo();

			CS.assertTrue(cartPage.isComboProductsPresentInBag(comboProductDetaillist), "96A");

			ExtentTest childTest3 = testListener.startChild("Customer Also Viewed section Test");
			childTest3.setDescription("This test verifies that Customer Also Viewed section is displayed on page");
			TestListner.extentMap.get().put("child3", childTest3);

			CS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "96C");

			ExtentTest childTest4 = testListener.startChild("Add to WishList Test");
			childTest4.setDescription("This test verifies that Add to Wishlist is working on PDP");
			TestListner.extentMap.get().put("child4", childTest4);

			browser.navigate().refresh();
			CS.assertTrue(productPage.addToWishlist(), "96B");

			ExtentTest childTest5 = testListener.startChild("People Also Bought section Test");
			childTest5.setDescription("This test verifies that People Also Bought section is displayed on page");
			TestListner.extentMap.get().put("child5", childTest5);

			CS.assertTrue(productPage.isPeopleWhoboughtDisplayed(), "96D");

			CS.assertAll();*/

		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser, e);
			Reporter.log(e.toString());
			Assert.fail();

		} finally {
			browser.close();
			browser.quit();
		}

	}

	@Test(groups = { "productaddtobag", "productaddtobagstatic","AddToBag"})
	public void Verify_Add_to_Bag_Static_Combo_Product_From_Product_Page() throws Throwable {

		Header_FooterBarData brandNavigation = framework.getData(Header_FooterBarData.class, "Brands1");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		browser.get(environmentURL);
		String str2 = browser.getCurrentUrl();
		CheckoutData checKoutData = framework.getData(CheckoutData.class, "valid");
		checKoutData.setCouponcode(null);
		BrowserAction browserAction = new BrowserAction();
		//AccountData Login = framework.getData(AccountData.class, "newlogin");
		try {
			//String str2 = browser.getCurrentUrl();
			//String [] arrOfStr2 = str2.split("\\.", 2);
			ExtentTest childTest1 = testListener.startChild("Add Static Combo to Cart from PDP Test");
			childTest1.setDescription(
					"This test verifies that user is able to add static combo product to cart from PDP");
			TestListner.extentMap.get().put("child1", childTest1);
			HeaderBar headerBar = new HeaderBar(browser);
			headerBar.clearCart();
			headerBar.search(brandNavigation.get_search_keyword());
			//String str1 = browser.getCurrentUrl();
			//String [] arrOfStr1 = str1.split("\\.", 2);
			String newurl =browserAction.switchtocurrentenviornment(browser,str2);
			browser.navigate().to(newurl);
			ListingPage_React listingPage = new ListingPage_React(browser);
			ProductPage_React productPage = new ProductPage_React(browser);
			ProductDetailData productDetailData = productPage
					.addAnyStaticComboProductFromProductPage(listingPage.allProducts());
			CartPage_React cartPage = new CartPage_React(browser);
			SanCS.assertTrue(cartPage.isProductPresentInBag(productDetailData), "55A");

			/*ExtentTest childTest3 = testListener.startChild("Customer Also Viewed Widget Test");
			childTest3.setDescription("This test verifies that user is able to view PAV widget");
			TestListner.extentMap.get().put("child3", childTest3);
			SanCS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "55C");

			ExtentTest childTest4 = testListener.startChild("Add to Wishlist Test");
			childTest4.setDescription("This test verifies that user is able to add product to wishlist");
			TestListner.extentMap.get().put("child4", childTest4);
			browser.navigate().refresh();
			SanCS.assertTrue(productPage.addToWishlist(), "55B");*/
			ExtentTest childTest2 = testListener.startChild("Fill New Address Test");
			childTest2.setDescription("This test verifies that new user is able to fill new address");
			TestListner.extentMap.get().put("child2", childTest2);

			//CartPage cartPage = new CartPage(browser);
			CheckOutPage_React checkOutPage = new CheckOutPage_React(browser);
			cartPage.checkOut();
			String checkouturl = browser.getCurrentUrl();
			checkOutPage.fillbillingEmailIDAndContinueAsGuest(checKoutData);
			boolean addressAdded = checkOutPage.fillNewAddress(checKoutData);
			CheckoutData CheckoutDataOfUI = checkOutPage.FetchOrderDetail(checKoutData);
			List<ProductDetailData> Items = checkOutPage.FetchProductDetail();
			Collections.reverse(Items);
			for (ProductDetailData item : Items) {
				System.out.println(item.getProductName());
				System.out.println(item.getProductPrize());
			}
			ExtentTest childTest3 = testListener.startChild("Guest User Checkout with Debit Card");
			childTest3.setDescription("This test verifies that Guest user is able to checkout with Debit card");
			TestListner.extentMap.get().put("child3", childTest3);

			checkOutPage.checkoutWithDebitCard(checKoutData);
			BankingPage bankPage = new BankingPage(browser);
			SanCS.assertTrue(bankPage.isOTPofHDFCpresent(), "L233");

			browser.get(checkouturl);

			ExtentTest childTest4 = testListener.startChild("Guest User Checkout with Net Banking test");
			childTest4.setDescription(
					"This test verifies that Guest user is able to checkout with Net Banking as payment method");
			TestListner.extentMap.get().put("child4", childTest4);

			System.out.println(checkouturl);
			boolean isNetBanking = checkOutPage.netBankingCheckout();

			SanCS.assertTrue(isNetBanking, "Net Banking Checkout");

			ExtentTest childTest5 = testListener.startChild("Guest User Checkout with Wallet test");
			childTest5.setDescription(
					"This test verifies that Guest user is able to checkout with Wallet as payment method");
			TestListner.extentMap.get().put("child5", childTest5);
			browser.get(checkouturl);
			boolean isMobikwik = checkOutPage.PaytmWalletCheckout();

			SanCS.assertTrue(isMobikwik, "Wallet Checkout");

			ExtentTest childTest6 = testListener.startChild("Place COD order as Guest user test");
			childTest6.setDescription("This test verifies that Guest user is able to place COD order");
			TestListner.extentMap.get().put("child6", childTest6);
			browser.get(checkouturl);
			boolean cashonDelivery = checkOutPage.CashOnDelivery();
			SanCS.assertTrue(cashonDelivery, "241");

			ExtentTest childTest7 = testListener.startChild("Payment details verification for Guest user test");
			childTest7.setDescription(
					"This test verifies that Guest user payment details are displayed correctly on confirmation Page");
			TestListner.extentMap.get().put("child7", childTest7);

			ConfirmationPage confirmationPage = new ConfirmationPage(browser);
			confirmationPage.getPaymentData();
				boolean isPaymentDetail = confirmationPage.verifyPaymentDetailWithCheckoutData(CheckoutDataOfUI);
			SanCS.assertTrue(isPaymentDetail, "257");

			ExtentTest childTest8 = testListener.startChild("Delivery details verification for Guest user test");
			childTest8.setDescription(
					"This test verifies that Guest user delivery details are displayed correctly on confirmation Page");
			TestListner.extentMap.get().put("child8", childTest8);

			boolean isDeliveryDetail = confirmationPage.verifyDeliveryDetailWithCheckoutData(CheckoutDataOfUI);
			SanCS.assertTrue(isDeliveryDetail, "256");

			ExtentTest childTest9 = testListener.startChild("Item details verification for Guest user test");
			childTest9.setDescription(
					"This test verifies that Guest user Item details are displayed correctly on confirmation Page");
			TestListner.extentMap.get().put("child9", childTest9);
			String[] actualproductdata=Items.toString().split(",");
			String[] expectedproductdata=confirmationPage.verifyOrderItemDetail().toString().split(",");
			String actualProductDataString="";
			String expectedProductDataString="";
			for(String value:actualproductdata)
			{
				if(!value.contains("null"))
					actualProductDataString=actualProductDataString+" : "+value;
			}
			for(String value:expectedproductdata)
			{
				if(!value.contains("null"))
					expectedProductDataString=expectedProductDataString+" : "+value;
			}

			TestListner.testing.get().log(LogStatus.INFO, "Expected Item Details ::"+actualProductDataString);
			TestListner.testing.get().log(LogStatus.INFO, "Expected Item Details ::"+expectedProductDataString);


			SanCS.assertTrue(Items.toString().equals(confirmationPage.verifyOrderItemDetail().toString()), "259");
			// SanCS.assertTrue(Items.equals(Items), "259");
			//	analyticinfo.getEvar(analyticsCODData);
			//analyticinfo.getEvent(analyticsCODData);
			//SanCS.assertAll();



		}

		catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser,e);
			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally {

			browser.quit();
		}
	}

	@Test(groups = { "ProductPageChecks", "productpage02" })
	public void Product_Page_Checks() throws Throwable, Throwable {
		Framework framwork = new Framework();
		PropertyConfiguration pf = new PropertyConfiguration();
		pf.getInstance();

		AccountData accountData = framework.getData(AccountData.class, "newlogin");
		WebDriver browser = framwork.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert CS = new CustomSoftAssert(SanAssertData, browser);
		ProductDetailData productData = framwork.getData(ProductDetailData.class, "validreview");
		try {
			ExtentTest childTest1 = testListener.startChild("Existing User Login Test");
			childTest1.setDescription("This test verifies that existing user is able to login");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL + "/hair-care.html?root=nav_1&dir=desc&order=popularity");
			AccountPage accountPage = new AccountPage(browser);
			String loggedInUser = accountPage.signInWithYourEmail(accountData);
			CS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()), "Existing user login test");

			ExtentTest childTest2 = testListener.startChild("Add Review test");
			childTest2.setDescription("This test verifies that existing user is able to add review");
			TestListner.extentMap.get().put("child2", childTest2);
			browser.get(
					environmentURL + "/makeup/lips/lipstick/lakme-9-to-5-matte-lip-color.html?root=catg&ptype=product");
			ProductPage productPage = new ProductPage(browser);
			productPage.checkListOfProductPage();
			CS.assertTrue(productPage.addReview(productData, accountData), "101");

			ExtentTest childTest3 = testListener.startChild("Ask Nykaa test");
			childTest3.setDescription("This test verifies that existing user is able to ask Nykaa");
			TestListner.extentMap.get().put("child3", childTest3);
			CS.assertTrue(productPage.askNykaa(productData, accountData), "104");
			// CS.assertTrue(productPage.readPost(), "108");
			// CS.assertAll();
		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser,e);
			Reporter.log(e.toString());

		} finally {

			//LoginSync.getInstance().clearLoginLock(accountData);
			browser.quit();
			Assert.fail();
		}

	}

	@Test(groups = { "ProductPageChecks", "productpage02","Sourabh" })
	public void Verify_Shade_Product_Add_To_Bag_from_PDP() throws Throwable, Throwable {
		Framework framwork = new Framework();
		WebDriver browser = framwork.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert CS = new CustomSoftAssert(SanAssertData, browser);
		AccountData accountData = LoginSync.getInstance().getLogin();
		Header_FooterBarData headerBarData = framework.getData(Header_FooterBarData.class, "shadenavigation");

		try {
			ExtentTest childTest1 = testListener.startChild("Existing User Login Test");
			childTest1.setDescription("This test verifies that existing user is able to login");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			AccountPage accountPage = new AccountPage(browser);
			String loggedInUser = accountPage.signInWithYourEmail(accountData);
			CS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()), "Existing user login test");

			ExtentTest childTest2 = testListener.startChild("Add Shade Product to Cart Test");
			childTest2.setDescription("This test verifies that new user is able to add shaded product to cart");
			TestListner.extentMap.get().put("child2", childTest2);
			HeaderBar headerBar = new HeaderBar(browser);
			headerBar.clearCart();
			headerBar.navigateToCategory(headerBarData,environmentURL);
			CartPage cartPage = new CartPage(browser);
			ProductPage productPage = new ProductPage(browser);
			ListingPage listingPage = new ListingPage(browser);
			ProductDetailData productDetailData = productPage
					.addAnyShadeProductFromProductPAge(listingPage.allProducts());
			CS.assertTrue(cartPage.isProductPresentInBag(productDetailData), "Add shade product to cart test");
			headerBar.clearCart();
			/*
			 * browser.get( environmentURL +
			 * "/makeup/lips/lipstick/lakme-9-to-5-matte-lip-color.html?root=catg&ptype=product"
			 * ); ProductPage productPage = new ProductPage(browser);
			 * productPage.checkListOfProductPage();
			 * CS.assertTrue(productPage.addReview(productData, accountData),
			 * "101"); CS.assertTrue(productPage.askNykaa(productData,
			 * accountData), "104"); CS.assertTrue(productPage.readPost(),
			 * "108"); CS.assertAll();
			 */
		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser,e);

		} finally {

			LoginSync.getInstance().clearLoginLock(accountData);
			browser.quit();
			Assert.fail();
		}

	}

	/***************************************************************************************
	 * SearchTestCases
	 ***********************************************************************************************************/

	@Test(groups = { "searchFeature", "searchBrandName"})
	public void Search_Keyword_Test() throws Throwable, Throwable {
		Framework frameWork = new Framework();
		AccountData accountData = LoginSync.getInstance().getLogin();
		WebDriver browser = frameWork.getBrowser(CommonConstants.NKYAA);
		PropertyConfiguration pf = new PropertyConfiguration();
		pf.getInstance();
		// this.environmentURL = prop.getProperty("environmentName");
		List<Header_FooterBarData> headerbarDataList = frameWork.getDataList(Header_FooterBarData.class, "TD4");
		CustomSoftAssert CS = new CustomSoftAssert(SanAssertData, browser);
		// Header_FooterBarData headerbarData = new Header_FooterBarData();
		try {
			ExtentTest childTest1 = testListener.startChild("Existing User Login Test");
			childTest1.setDescription("This test verifies that existing user is able to login");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			AccountPage accountPage = new AccountPage(browser);
			String loggedInUser = accountPage.signInWithYourEmail(accountData);
			CS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()), "Existing user login test");

			ExtentTest childTest2 = testListener.startChild("Search Keyword Test");
			childTest2.setDescription("This test verifies that existing user is able to login");
			TestListner.extentMap.get().put("child2", childTest2);
			HeaderBar headerBar = new HeaderBar(browser);
			for (Header_FooterBarData headerbarData : headerbarDataList) {
				headerBar.search(headerbarData.get_search_keyword());
				PartialStringList SearchedResult = headerBar.getSearchedResult();
				CS.assertTrue(SearchedResult.contains(headerbarData.get_search_keyword().toLowerCase()), "271C");
				//CS.assertAll();
				/*
				 * ListingPage listingPage = new ListingPage(browser); CartPage
				 * cartPage = new CartPage(browser); ProductDetailData
				 * ProductDetailData2 =
				 * listingPage.addAnyShadeProductFromPeopleWhoBoughtWidget();
				 * CS.assertTrue(cartPage.isProductPresentInBag(
				 * ProductDetailData2), "79J"); browser.navigate().refresh();
				 * 
				 * boolean allProductWishlistAdded =
				 * listingPage.addToWishList(listingPage.wishlistProducts());
				 * CS.assertTrue(allProductWishlistAdded, "79B"); String
				 * offerText =
				 * listingPage.verifyOfferOn(listingPage.wishlistProducts());
				 * CS.assertTrue(offerText != null, "79H");
				 * listingPage.ScrollDown("1000", 4); int value =
				 * listingPage.getSearchedProductCount(); if (value > 40) {
				 * CS.assertTrue(true, "79E"); } List<ProductDetailData>
				 * productList = new ArrayList<ProductDetailData>(); productList
				 * = listingPage.ApplySort("NAME"); List<ProductDetailData>
				 * productListFromUI = new ArrayList<ProductDetailData>();
				 * productListFromUI.addAll(productList);
				 * System.out.println(productListFromUI.toString());
				 * Collections.sort(productList, (ProductDetailData a1,
				 * ProductDetailData a2) -> a1.getProductName()
				 * .compareTo(a2.getProductName()));
				 * CS.assertEquals(productListFromUI, productList, "79F");
				 * List<ProductDetailData> productListPriceFilter =
				 * listingPage.ApplyFilter("price", "Rs. 0 - Rs. 500");
				 * List<ProductDetailData> productlistwithPrizeFilter = new
				 * ArrayList<ProductDetailData>(); //
				 * productlistwithPrizeFilter.addAll(productListPriceFilter);
				 * productlistwithPrizeFilter = productListPriceFilter.stream()
				 * .filter(o -> Integer.parseInt(o.getProductPrize()) <
				 * 500).collect(Collectors.toList());
				 * System.out.println(productListPriceFilter);
				 * System.out.println(productlistwithPrizeFilter);
				 * CS.assertEquals(productlistwithPrizeFilter,
				 * productListPriceFilter, "79G");
				 */
			}
		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser,e);
			Assert.fail();
		} finally {

			LoginSync.getInstance().clearLoginLock(accountData);
			browser.quit();
		}

	}

	@Test(groups = { "searchFeature", "searchAddToBag","Sourabh"})
	public void Search_and_Add_To_Bag() throws Throwable, Throwable {
		Framework frameWork = new Framework();
		AccountData accountData = LoginSync.getInstance().getLogin();
		WebDriver browser = frameWork.getBrowser(CommonConstants.NKYAA);
		// PropertyConfiguration pf = new PropertyConfiguration();
		// Properties prop = pf.getInstance();
		// this.environmentURL = prop.getProperty("environmentName");
		List<Header_FooterBarData> headerbarDataList = frameWork.getDataList(Header_FooterBarData.class, "TD1");
		CustomSoftAssert CS = new CustomSoftAssert(SanAssertData, browser);
		// Header_FooterBarData headerbarData = new Header_FooterBarData();
		try {
			ExtentTest childTest1 = testListener.startChild("Existing User Login Test");
			childTest1.setDescription("This test verifies that existing user is able to login");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			AccountPage accountPage = new AccountPage(browser);
			String loggedInUser = accountPage.signInWithYourEmail(accountData);
			CS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()), "Existing user login test");

			ExtentTest childTest2 = testListener.startChild("Popular Search Test");
			childTest2.setDescription("This test verifies that existing user is able to search");
			TestListner.extentMap.get().put("child2", childTest2);
			HeaderBar headerBar = new HeaderBar(browser);
			for (Header_FooterBarData headerbarData : headerbarDataList) {
				headerBar.search(headerbarData.get_search_keyword());
				PartialStringList SearchedResult = headerBar.getSearchedResult();
				CS.assertTrue(SearchedResult.contains(headerbarData.get_search_keyword().toLowerCase()), "271C");

				ExtentTest childTest3 = testListener.startChild("Add to Wishlist Test");
				childTest3.setDescription("This test verifies that user is able to add product to wishlist");
				TestListner.extentMap.get().put("child3", childTest3);
				ListingPage listingPage = new ListingPage(browser);
				// CartPage cartPage = new CartPage(browser);
				browser.navigate().refresh();
				boolean allProductWishlistAdded = listingPage.addToWishList(listingPage.wishlistProducts());
				CS.assertTrue(allProductWishlistAdded, "79B");

				ExtentTest childTest4 = testListener.startChild("Verify Offer Test");
				childTest4.setDescription("This test verifies that new user is able to view offer");
				TestListner.extentMap.get().put("child4", childTest4);
				String offerText = listingPage.verifyOfferOn(listingPage.wishlistProducts());
				CS.assertTrue(offerText != null, "79H");

				ExtentTest childTest5 = testListener.startChild("Scroll 4-5 slot Test");
				childTest5.setDescription("This test verifies that new user is able to scroll the page 4-5 times");
				TestListner.extentMap.get().put("child5", childTest5);
				listingPage.ScrollDown("1000", 4);
				int value = listingPage.getSearchedProductCount();
				if (value > 40) {
					CS.assertTrue(true, "79E");
				}

				ExtentTest childTest6 = testListener.startChild("Apply Sort Test");
				childTest6.setDescription("This test verifies that new user is able to apply sort");
				TestListner.extentMap.get().put("child6", childTest6);
				List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
				productList = listingPage.ApplySort("NAME");
				List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
				productListFromUI.addAll(productList);
				System.out.println(productListFromUI.toString());
				Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
						.compareTo(a2.getProductName()));
				CS.assertEquals(productListFromUI, productList, "79F");

				ExtentTest childTest7 = testListener.startChild("Apply Filter Test");
				childTest7.setDescription("This test verifies that new user is able to apply filter");
				TestListner.extentMap.get().put("child7", childTest7);
				List<ProductDetailData> productListPriceFilter = listingPage.ApplyFilter("price", "Rs. 0 - Rs. 500");
				List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
				// productlistwithPrizeFilter.addAll(productListPriceFilter);
				productlistwithPrizeFilter = productListPriceFilter.stream()
						.filter(o -> Integer.parseInt(o.getProductPrize()) < 500).collect(Collectors.toList());
				System.out.println(productListPriceFilter);
				System.out.println(productlistwithPrizeFilter);
				CS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "79G");
			}
		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser,e);
			Assert.fail();
		} finally {

			LoginSync.getInstance().clearLoginLock(accountData);
			browser.quit();
		}

	}

	@Test(groups = { "searchFeature","sanity","Wsanity" ,"Wsearch","search", "local1"})
	public void Auto_Suggest_Test() throws Throwable, Throwable {
		Framework frameWork = new Framework();
		AccountData accountData = LoginSync.getInstance().getLogin();
		WebDriver browser = frameWork.getBrowser(CommonConstants.NKYAA);
		// PropertyConfiguration pf = new PropertyConfiguration();
		// Properties prop = pf.getInstance();
		// this.environmentURL = prop.getProperty("environmentName");
		List<Header_FooterBarData> headerbarDataList = frameWork.getDataList(Header_FooterBarData.class, "TC3");
		CustomSoftAssert CS = new CustomSoftAssert("automation Regression Coverage", "DeskTop");
		// Header_FooterBarData headerbarData = new Header_FooterBarData();
		try {
			ExtentTest childTest1 = testListener.startChild("Auto Suggest in Search Test");
			childTest1.setDescription("This test verifies that new user is able to view auto suggest in search");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			AccountPage_React accountPage = new AccountPage_React(browser);
			accountPage.ReactSignInwithEmail(accountData);
			HeaderBar headerBar = new HeaderBar(browser);
			for (Header_FooterBarData headerbarData : headerbarDataList) {
				headerBar.search(headerbarData.get_search_keyword());
				PartialStringList SearchedResult = headerBar.getValueFromAutoSuggetionList(CommonConstants.NKYAA);
				CS.assertTrue(SearchedResult.contains(headerbarData.get_search_keyword().toLowerCase()), "271A");
			}
		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser,e);
			Assert.fail();
		} finally {

			LoginSync.getInstance().clearLoginLock(accountData);
			browser.quit();
		}
	}

	@Test(groups = { "searchFeature", "searchtrending","Wsearch","search"})
	public void Verify_Trending_Search() throws Throwable, Throwable {
		Framework frameWork = new Framework();
		WebDriver browser = frameWork.getBrowser(CommonConstants.NKYAA);
		Header_FooterBarData Listingpage = framework.getData(Header_FooterBarData.class, "sizenavigation");
		// PropertyConfiguration pf = new PropertyConfiguration();
		// Properties prop = pf.getInstance();
		// this.environmentURL = prop.getProperty("environmentName");
		List<Header_FooterBarData> headerbarDataList = frameWork.getDataList(Header_FooterBarData.class, "TC3");
		CustomSoftAssert CS = new CustomSoftAssert("automation Regression Coverage", "DeskTop");
		// Header_FooterBarData headerbarData = new Header_FooterBarData();
		try {
			ExtentTest childTest1 = testListener.startChild("Verify User able to open Listing page");
			childTest1.setDescription("This test verifies that user is able to open Listing page");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			HeaderBar headerBar = new HeaderBar(browser);
			headerBar.navigateToCategory(Listingpage,environmentURL);
			ListingPage listingPage = new ListingPage(browser);
			PartialStringList SearchedResultplp = null;
			PartialStringList searchresultPD = null;
			List<ProductDetailData> productList = listingPage.getProductList(listingPage.allProducts());
			ExtentTest childTest2 = testListener.startChild("Verify Trending search result working from PLP");
			childTest2.setDescription("This test verifies that Trending search result working from PLP");
			TestListner.extentMap.get().put("child2", childTest2);
			for (Header_FooterBarData headerbarData : headerbarDataList) {
				headerBar.search("");
				SearchedResultplp = headerBar.getTrendingSearchList();
				//System.out.print(SearchedResultplp);
				CS.assertTrue(!(SearchedResultplp.isEmpty()), "271B");
			}
			ProductPage productpage = new ProductPage(browser);
			ExtentTest childTest3 = testListener.startChild("Verify Trending search result working from PD");
			childTest3.setDescription("This test verifies that Trending search result working from PD");
			TestListner.extentMap.get().put("child3", childTest3);
			for(ProductDetailData product : productList){
				searchresultPD = headerBar.getSearchResultFromPD(product,environmentURL);
				//System.out.print(searchresultPD);
				break;
			}
			ExtentTest childTest4 = testListener.startChild("Verify Trending search result is same in PD and PLP");
			childTest4.setDescription("This test verifies that Trending search result is same in all pages");
			TestListner.extentMap.get().put("child4", childTest4);
			CS.assertEquals(SearchedResultplp, searchresultPD, "271Z");
			TestListner.testing.get().log(LogStatus.INFO, "Trending Search Result same in all pages");

		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser,e);
			Assert.fail();
		} finally {

			browser.quit();
		}
	}


	@Test( groups = {"sanity", "Wsanity","Wsearch","search", "local1" })
	public void Search_Keyword() throws Throwable, Throwable {
	   Framework frameWork = new Framework();
	   AccountData accountData = LoginSync.getInstance().getLogin();
	   WebDriver browser = frameWork.getBrowser("Web");
	   List<Header_FooterBarData> HeaderBar_List=frameWork.getDataList(Header_FooterBarData.class, "TD4");
	   CustomSoftAssert CS = new CustomSoftAssert(SanAssertData, browser);
	   try {
	      ExtentTest childTest1 = testListener.startChild("Login Test ");
	      childTest1.setDescription("This test verifies that user is able to login with valid credentials");
	      TestListner.extentMap.get().put("child1", childTest1);
	      browser.get(environmentURL);
	      AccountPage_React accountPage = new AccountPage_React(browser);
	      String loggedInUserName= accountPage.ReactSignInwithEmail(accountData);
	      CS.assertTrue(accountData.getFirstName().equalsIgnoreCase(loggedInUserName),"Login with Valid Credentials");
	      //browser.get(environmentURL);
	      String str2 = browser.getCurrentUrl();
	      //String [] arrOfStr2 = str2.split("\\.", 2);
	      HeaderBar HeaderBar = new HeaderBar(browser);
	      HeaderBar.clearCart();
	      ListingPage_React lpm= new ListingPage_React(browser);
	    //  ListingPage ListingPage_M = new ListingPage(browser);
	      boolean bolFinal =true;
	      boolean isProductAdded_R=false;
	      boolean isProductAdded_M=false;
	      BrowserAction browserAction = new BrowserAction();
	      for (Header_FooterBarData HeaderBar_Data : HeaderBar_List) {
	         String search_keyword= HeaderBar_Data.get_search_keyword();
	         ExtentTest childTest = testListener.startChild("Search with a keyword/Brand: "+search_keyword+" name test ");
	         childTest.setDescription("This test verifies that user is able to search with a keyword/Brand name: "+ search_keyword+" on page");
	         TestListner.extentMap.get().put("child".concat(search_keyword), childTest);
	         //PartialStringList SearchedResult = HeaderBar_Mobile.getSearchedResult();
	         List<ProductDetailData> productList = null;
	        // if(lpm.IslistingPagereactornot())
	         //{
	            HeaderBar.search_React(search_keyword);
	            lpm.ScrollDown("-4000", 4);
	            CS.assertTrue(bolFinal,"271D");
	            ExtentTest childTest3 = testListener.startChild("Add to Bag from Search page");
	            childTest3.setDescription("This test verifies that user is able to add product to bag from search page");
	            TestListner.extentMap.get().put("child3", childTest3);

	           // if(lpm.IslistingPagereactornot()) {
	               //String str1 = browser.getCurrentUrl();
	               //String [] arrOfStr1 = str1.split("\\.", 2);
	               String newurl =browserAction.switchtocurrentenviornment(browser,str2);
	               browser.navigate().to(newurl);
	               CartPage_React CartPage_R = new CartPage_React(browser);
	               productList = lpm.getProductList(ListingPage_React.allProducts());
	               ProductDetailData productDetadata = lpm.addAnyShadeProductFromAllProduct();
	               isProductAdded_R = CartPage_R.isProductPresentInBag(productDetadata);
	               CS.assertTrue(isProductAdded_R,"271F");
	           // }
	            //else{
	              // CartPage CartPage_M = new CartPage(browser);
	              // productList = ListingPage_M.getProductList(ListingPage.SearchProductlist());
	              // ProductDetailData productDetadata = ListingPage_M.addAnyShadeProductFromSearchProducts();
	               //isProductAdded_M = CartPage_M.isProductPresentInBag(productDetadata);
	               //CS.assertTrue(isProductAdded_M,"271E");
	            //}
	         //}
	        /* else
	         {
	               HeaderBar.search(search_keyword);
	           // String str1 = browser.getCurrentUrl();
	            //String [] arrOfStr1 = str1.split("\\.", 2);
	            if(lpm.IslistingPagereactornot()) {
	               String newurl =browserAction.switchtocurrentenviornment(browser,str2);
	               browser.navigate().to(newurl);
	               CartPage_React CartPage_R = new CartPage_React(browser);
	               productList = lpm.getProductList(ListingPage_React.allProducts());
	               ProductDetailData productDetadata = lpm.addAnyShadeProductfromReactSearchPage();
	               isProductAdded_R = CartPage_R.isProductPresentInBag(productDetadata);
	               CS.assertTrue(isProductAdded_R,"271F");
	            }
	            else{
	               productList = ListingPage_M.getProductList(ListingPage.SearchProductlist());
	               CartPage CartPage_M = new CartPage(browser);
	               ProductDetailData productDetadata = ListingPage_M.addAnyShadeProductFromSearchProducts();
	               isProductAdded_M = CartPage_M.isProductPresentInBag(productDetadata);
	               CS.assertTrue(isProductAdded_M,"271E");
	            }
	         }*/



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
	               TestListner.testing.get().log(LogStatus.INFO, productName +" don't have search keyword : " +  search_keyword);
	               bolFinal=bol;
	            }

	         }
	         CS.assertTrue(bolFinal,"271D");
	      }
	      

	   }
	   catch (Exception e) {
	      printStackStraceinLog(e);
	      framework.logErrorWithSnapshot(browser,e);
	      Reporter.log(e.toString(), true);
	      Assert.fail();


	   }

	   finally {


	      browser.quit();
	   }

	}
	
	/**********************************************************************
	 * Home Page
	 ***********************************************************************************************************************/

	@Test(groups = { "homePage", "Analytics", "homepage01" })

	public void Verify_Banner_redirection() throws URISyntaxException, Throwable {

		Framework framwork = new Framework();

		AccountData accountData = LoginSync.getInstance().getLogin();
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert CS = new CustomSoftAssert(SanAssertData, browser);
		Boolean bolFinal = true;

		try {
			ExtentTest childTest1 = testListener.startChild("Home Page Banner images are not broken ");
			childTest1.setDescription("This test verifies that Home page Banner images are not broken ");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			HomePage hm = new HomePage(browser);
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


	@Test(groups = {"sanity","Wsanity","serviceBook","payonvisit","sanity_Live","N_sanity_Live","Wcheckout","checkout"})

	public void View_Gallery_And_Book_Beauty_Service_test() throws Throwable {
		Header_FooterBarData headerBarData = framework.getData(Header_FooterBarData.class,"beautyservices_navigation");
		BeautyServiceData beautyServiceData = framework.getData(BeautyServiceData.class, "cityNavigation");
		CheckoutData checkOutData = framework.getData(CheckoutData.class, "simplepay");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, browser);
		AccountData Login = LoginSync.getInstance().getLogin();
		try {
			ExtentTest childTest = testListener.startChild("Navigate to Beauty Services");
			childTest.setDescription("This test verifies that user is able to navigate to Beauty Services.");
			TestListner.extentMap.get().put("child", childTest);
			browser.get(environmentURL);
			HeaderBar headerBar = new HeaderBar(browser);
			headerBar.navigateToPopUps(headerBarData ,CommonConstants.NKYAA,environmentURL);
			BeautyServicePage beautyServicePage = new BeautyServicePage(browser);
			beautyServicePage.navigateToQuickLink(beautyServiceData,environmentURL);
			boolean isNavigatedToBeautyServices=beautyServicePage.selectCityAndLocation(beautyServiceData);
			customSoftAssert.assertTrue(isNavigatedToBeautyServices,"L71");

			ExtentTest childTest2 = testListener.startChild("View Gallery of Beauty Services");
			childTest2.setDescription("This test verifies that user is able to see images in Gallery of any Beauty Service.");
			TestListner.extentMap.get().put("child2", childTest2);

			BeautyServiceData beautyServiceDataFromViewGallery = beautyServicePage.view_Gallery();
			//CartPage cartPage = new CartPage(browser);
			customSoftAssert.assertTrue(beautyServiceDataFromViewGallery!=null,"L67");
			ExtentTest childTest1 = testListener.startChild("Book Beauty Service");
			childTest1.setDescription("This test that verifies that user is able to add any Beauty Service to cart.");
			TestListner.extentMap.get().put("child1", childTest1);

			AccountPage_React accountPage = new AccountPage_React(browser);
			String loggedUsername = accountPage.signInWithYourEmail(Login);
			customSoftAssert.assertTrue((Login.getFirstName().toLowerCase().contains(loggedUsername.toLowerCase().replaceAll("[^0-9]", ""))),  "Login test");
			headerBar.clearCart_Magento();

			BeautyServiceData beautyServiceDataFromList = beautyServicePage.BookAnyService();
			customSoftAssert.assertTrue((beautyServiceDataFromList!=null?true:false),"L66");

			//login
			/*ExtentTest childTest4 = testListener.startChild("Partial payment/Pay On Visit test");
			childTest4.setDescription("This test verifies that existing user is able to apply Pay On visit services");
			TestListner.extentMap.get().put("child4", childTest4);

			CheckOutPage_React checkOutPage = new CheckOutPage_React(browser);
			CartPage cartPage=new CartPage(browser);
			cartPage.checkOut();
			Thread.sleep(5000);
			while (!browser.getCurrentUrl().contains("checkout")) {
				Thread.sleep(1000);
			}
			if (browser.getCurrentUrl().contains(environmentURL.substring(environmentURL.lastIndexOf("/") + 1))) {
				checkOutPage.savedAddressSelect();
				// System.out.println(checkOutPage.isAddressSelected())
				// If signed in then it should land on shipping address page.
			} if(checkOutPage.isAddressSelected()==null) {
				checkOutPage.fillNewAddressBeautyService(checkOutData);
			}
			Thread.sleep(3000);
			boolean isPovApplied = checkOutPage.applyPOV();
			customSoftAssert.assertTrue(isPovApplied, "L237");

			ExtentTest childTest5 = testListener.startChild("NetBanking Checkout test");
			childTest5.setDescription("This test verifies that existing user is able to checkout through netbanking"+ "");
			TestListner.extentMap.get().put("child5", childTest5);
			boolean isNetbanking = checkOutPage.netBankingCheckout();
			customSoftAssert.assertTrue(isNetbanking, "L66");*/
		}

		catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser,e);
			Assert.fail();

		} finally {

			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();
		}

	}

	/**********************************************************************
	 * Book Beauty Service test.
	 ***********************************************************************/

	@Test(groups = { "sanity","Wsanity","beautyfiltersort","sanity_Live","N_sanity_Live","Wfilter","filter"})

	public void Beauty_Service_Apply_Sort_And_Apply_Filter() throws Throwable {
		//List<String> filterType = Arrays.asList("Service","Gender","Price");
		Header_FooterBarData headerBarData = framework.getData(Header_FooterBarData.class,"beautyservices_navigation");
		BeautyServiceData beautyServiceData = framework.getData(BeautyServiceData.class, "cityNavigation");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		//ListingPage listingPage = new ListingPage(browser);
		try {
			ExtentTest childTest = testListener.startChild("Beauty Service Apply Sort");
			childTest.setDescription("This test verifies that user is able to apply sort on beauty service");
			TestListner.extentMap.get().put("childTest", childTest);
			browser.get(environmentURL);
			HeaderBar headerBar = new HeaderBar(browser);
			headerBar.navigateToPopUps(headerBarData ,CommonConstants.NKYAA,environmentURL);
			BeautyServicePage beautyServicePage = new BeautyServicePage(browser);
			beautyServicePage.navigateToQuickLink(beautyServiceData,environmentURL);
			beautyServicePage.selectCityAndLocation(beautyServiceData);
			List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
			productList = beautyServicePage.ApplySort("NAME");
			List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
			productListFromUI.addAll(productList);
			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
					.compareTo(a2.getProductName()));
			SanAssertCS.assertEquals(productListFromUI, productList, "L69");
			ExtentTest childTest1 = testListener.startChild("Beauty Service Apply Filter");
			childTest1.setDescription("This test verifies that user is able to apply filter on beauty service");
			TestListner.extentMap.get().put("childTest1", childTest1);
			List<ProductDetailData> productListPriceFilter = beautyServicePage.ApplyFilter("price",
					"Rs. 500 - Rs. 1,000");
			List<ProductDetailData> productlistwithPrizeFilter = new ArrayList<ProductDetailData>();
			// productlistwithPrizeFilter.addAll(productListPriceFilter);
			productlistwithPrizeFilter = productListPriceFilter.stream()
					.filter(o -> Integer.parseInt(o.getProductPrize()) >= 500
					& Integer.parseInt(o.getProductPrize()) <= 1000)
					.collect(Collectors.toList());
			System.out.println(productlistwithPrizeFilter.size());
			System.out.println(productListPriceFilter.size());
			SanAssertCS.assertEquals(productlistwithPrizeFilter, productListPriceFilter, "L68");

		}
		catch (Exception e) {
			framework.logErrorWithSnapshot(browser,e);
			Reporter.log(e.toString());
			Assert.fail();

		} finally {
			// LoginSync.getInstance().clearLoginLock(accountData);
			browser.quit();
		}

	}

	/**********************************************************************
	 * Book Beauty Service Apply Sort test
	 ***********************************************************************/

	@Test(groups = { "Mayank1" })
	public void VerifyWriteQuestion() throws URISyntaxException, Throwable {

		AccountData Login = LoginSync.getInstance().getLogin();
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);

		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		try {

			browser.get(environmentURL);
			AccountPage accountPage = new AccountPage(browser);
			String LoggedUserName = accountPage.signInWithYourEmail(Login).toLowerCase();
			SanCS.assertEquals(LoggedUserName, Login.getFirstName().toLowerCase(), "104");


		} catch (Throwable e) {
			framework.logErrorWithSnapshot(browser,e);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {

			LoginSync.getInstance().clearLoginLock(Login);
			browser.quit();

		}

	}

	@Test(groups = { "Wsanity" ,"Debug"})
	public void Verify_Nykaa_Network() throws Throwable {
		NykaaNetworkData nykaaNetworkData = framework.getData(NykaaNetworkData.class, "acne");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		AccountData accountData = framework.getData(AccountData.class, "newlogin");
		CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, browser);
		try{
			ExtentTest childTest1 = testListener.startChild("New User Login test");
			childTest1.setDescription("This test verifies that New user is able to Sign-up.");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			HeaderBar headerbar = new HeaderBar(browser);
			AccountPage_React accountPage = new AccountPage_React(browser);
			accountPage.NewRegister_React(accountData);
			ExtentTest childTest2 = testListener.startChild("Nykaa Network Entry Point");
			childTest2.setDescription("This test verifies that Nykaa Network Entry Point is Working.");
			TestListner.extentMap.get().put("child1", childTest2);
			headerbar.navigateToNetwork(environmentURL);
			CommunityPage communityPage = new CommunityPage(browser);
			communityPage.getStartedWindow();
			ExtentTest childTest3 = testListener.startChild("Topic Selection to New User");
			childTest3.setDescription("This test verifies that New user should able to Follow topic .");
			TestListner.extentMap.get().put("child1", childTest3);
			String topic = communityPage.topicSelectionWindow();
			customSoftAssert.assertTrue(communityPage.isTopicSelected(topic),"C1T2");
			ExtentTest childTest4 = testListener.startChild("Question Posting");
			childTest4.setDescription("This test verifies that User is able to Post question in Nykaa Network.");
			TestListner.extentMap.get().put("child1", childTest4);
			String question =communityPage.postAQuestion(nykaaNetworkData);
			customSoftAssert.assertTrue(communityPage.isQuestionPosted(question),"C1T3");
			Thread.sleep(5000);
			ExtentTest childTest5 = testListener.startChild("Answer the Question");
			childTest5.setDescription("This test verifies that User able to answer previous posted questions.");
			TestListner.extentMap.get().put("child1", childTest5);
			String reply = communityPage.replyToTheQuestion(nykaaNetworkData);
			customSoftAssert.assertTrue(communityPage.isreplyPosted(reply),"C1T5");
			ExtentTest childTest6 = testListener.startChild("Explore more Topic");
			childTest6.setDescription("This test verifies that User able to Follow/Unfollow more topic.");
			TestListner.extentMap.get().put("child1", childTest6);
			communityPage.explore();
			ExtentTest childTest7 = testListener.startChild("Check User Profile");
			childTest7.setDescription("This test verifies that User able to View his profile.");
			TestListner.extentMap.get().put("child1", childTest7);
			communityPage.userupdate();
			browser.quit();



		}catch (Throwable e){
			e.printStackTrace();

		}finally {
			browser.quit();
		}

	}

	/**********************************************************************
	 * React Pages test- Listing Pages.
	 ***********************************************************************/

	@Test(groups = { "sanity","Wsanity","react","sanity_Live","N_sanity_Live"})

	public void React_Page_Check_for_L1_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		FooterBar footer=new FooterBar(browser);
		AccountData Login = LoginSync.getInstance().getLogin();
		HeaderBar headerbar=new HeaderBar(browser);
		//boolean signUpResult=false;
		try {
			ExtentTest childTest1 = testListener.startChild("Existing User Login test");
			childTest1.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			AccountPage_React accountPage = new AccountPage_React(browser);
			String LoggedUserName = accountPage.ReactSignInwithEmail(Login).toLowerCase();

			SanAssertCS.assertTrue((Login.getFirstName().toLowerCase().contains(LoggedUserName.toLowerCase().replaceAll("[^0-9]", ""))),  "Login Test");

			ExtentTest childTest14 = testListener.startChild("Add any Shade/Size product to cart from L1 category React page from widget test ");
			childTest14.setDescription("This test verifies that user is able to add any Shade/Size product from L1 category React page widget into cart");
			TestListner.extentMap.get().put("child14", childTest14);

			headerbar.navigateToCategory(headerbarData,environmentURL);
			ListingPage_React listingPage = new ListingPage_React(browser);
			CartPage_React cartPage = new CartPage_React(browser);
			headerbar.clearCart();
			ProductDetailData productDetailData1 = listingPage.addAnyShadeProductFromWidget();
			if(productDetailData1.getProductName()==null)
			{
				productDetailData1 = listingPage.addAnySizeProductFromWidget();
				if(productDetailData1.getProductName()==null)
				{
					productDetailData1 = listingPage.addAnySimpleProductFromWidget();
				}
			}
			if(productDetailData1.getProductName().equalsIgnoreCase("Widget not found"))
			{
				SanAssertCS.assertTrue(false, "R_79C");
			}
			else
			{
				SanAssertCS.assertTrue(cartPage.isProductPresentInBag(productDetailData1), "R_79C");
			}
			ExtentTest childTest2 = testListener.startChild("Add any Simple product to cart from L1 category React page all products section test ");
			childTest2.setDescription("This test verifies that user is able to add any Simple product from L1 category React page all products section into cart");
			TestListner.extentMap.get().put("child2", childTest2);
			//footer.refreshAndCloseFrame(browser);
			ProductDetailData SimpleProductDetailData = listingPage.addAnySimpleProductFromAllProducts();
			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(SimpleProductDetailData), "R_79D_A");

			ExtentTest childTest9 = testListener.startChild("Add any Shade product to cart from L1 category React page all products section test ");
			childTest9.setDescription("This test verifies that user is able to add any Shade product from L1 category React page all products section into cart");
			TestListner.extentMap.get().put("child9", childTest9);
			//footer.refreshAndCloseFrame(browser);
			ProductDetailData ShadeProductDetailData = listingPage.addAnyShadeProductFromAllProduct();
			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ShadeProductDetailData), "R_79D_B");

			ExtentTest childTest10 = testListener.startChild("Add any Size product to cart from L1 category React page all products section test ");
			childTest10.setDescription("This test verifies that user is able to add any Size product from L1 category React page all products section into cart");
			TestListner.extentMap.get().put("child10", childTest10);
			//footer.refreshAndCloseFrame(browser);
			ProductDetailData SizeProductDetailData = listingPage.addAnySizeProductFromAllProduct();
			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(SizeProductDetailData), "R_79D_C");

			ExtentTest childTest5 = testListener.startChild("Add any product from L1 category React page widget to wishlist test ");
			childTest5.setDescription("This test verifies that user is able to add any product from L1 category React page widget section to wishlist");
			TestListner.extentMap.get().put("child5", childTest5);
			//footer.refreshAndCloseFrame(browser);
			if(productDetailData1.getProductName().equalsIgnoreCase("Widget not found"))
			{
				SanAssertCS.assertTrue(false, "R_79A");
			}
			else
			{
				boolean widgetWishlistAdded = listingPage.addToWishList(listingPage.widgetProducts(),null);
				SanAssertCS.assertTrue(widgetWishlistAdded, "R_79A");
			}

			ExtentTest childTest6 = testListener.startChild("Add Simple product to wishlist from L1 category React page from all products section test ");
			childTest6.setDescription("This test verifies that user is able to add any simple product from L1 category React page all products section to wishlist");
			TestListner.extentMap.get().put("child6", childTest6);

			boolean simpleProductWishlistAdded = listingPage.addToWishList(ListingPage_React.allProducts(),"simple");
			SanAssertCS.assertTrue(simpleProductWishlistAdded, "R_79B_A");

			ExtentTest childTest12 = testListener.startChild("Add Shade product to wishlist from L1 category React page from all products section test ");
			childTest12.setDescription("This test verifies that user is able to add Shade product from L1 category React page all products section to wishlist");
			TestListner.extentMap.get().put("child12", childTest12);

			boolean shadeProductWishlistAdded = listingPage.addToWishList(ListingPage_React.allProducts(),"shade");
			SanAssertCS.assertTrue(shadeProductWishlistAdded, "R_79B_B");

			ExtentTest childTest13 = testListener.startChild("Add Size product to wishlist from L1 category React page from all products section test ");
			childTest13.setDescription("This test verifies that user is able to add Size product from L1 category React page all products section to wishlist");
			TestListner.extentMap.get().put("child13", childTest13);

			boolean sizeProductWishlistAdded = listingPage.addToWishList(ListingPage_React.allProducts(),"size");
			SanAssertCS.assertTrue(sizeProductWishlistAdded, "R_79B_C");

			ExtentTest childTest7 = testListener.startChild("Verify Offer text test ");
			childTest7.setDescription("This test verifies the offer on products in all products section of React page");
			TestListner.extentMap.get().put("child7", childTest7);

			String offerText = listingPage.verifyOfferOn(ListingPage_React.allProducts() ,CommonConstants.NKYAA);
			SanAssertCS.assertTrue(offerText != null, "R_79K");

			ExtentTest childTest8 = testListener.startChild("Load More Products test ");
			childTest8.setDescription("This test verifies that products are loaded successfully on L1 category React page when user scrolls clicks Load More");
			TestListner.extentMap.get().put("child8", childTest8);

			listingPage.LoadMoreProducts(2,false);
			int value = listingPage.getProductCountOfAllProducts();
			if (value > 40) {
				SanAssertCS.assertTrue(true, "R_79E");
			}

			ExtentTest childTest11 = testListener.startChild("Back to Top test ");
			childTest11.setDescription("This test verifies that user is able go to top by clicking Back To Top button on React page");
			TestListner.extentMap.get().put("child11", childTest11);

			SanAssertCS.assertTrue(listingPage.backToTop(),"R_79F");



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

	@Test(groups = { "sanity","Wsanity","react","Wlisting","listing", "local1"})
	public void React_Page_Check_for_L2_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l2category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		try {
			ExtentTest childTest11 = testListener.startChild("Existing User Login test");
			childTest11.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child11", childTest11);
			browser.get(environmentURL);
			AccountPage_React accountPage = new AccountPage_React(browser);
			AccountData Login = LoginSync.getInstance().getLogin();
			String LoggedUserName = accountPage.ReactSignInwithEmail(Login).toLowerCase();
			SanAssertCS.assertTrue((Login.getFirstName().toLowerCase().contains(LoggedUserName.toLowerCase().replaceAll("[^0-9]", ""))),  "Login Test");

			ExtentTest childTest1 = testListener.startChild("Add any Shade/Size product from L2 category React page widget test ");
			childTest1.setDescription("This test verifies that user is able to add any Shade/Size product from L2 category React page widget into cart");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			HeaderBar headerbar = new HeaderBar(browser);
			headerbar.clearCart();
			headerbar.navigateToCategory(headerbarData,environmentURL);
			ListingPage_React listingPage = new ListingPage_React(browser);
			listingPage.navigateToShopAll("shop-all",environmentURL);
			// listingPage.addAnyShadeProductFromWidget();
			CartPage_React cartPage = new CartPage_React(browser);
			ProductDetailData productDetailData1 = listingPage.addAnyShadeProductFromWidget();
			if(productDetailData1.getProductName()==null)
			{
				productDetailData1 = listingPage.addAnySizeProductFromWidget();
				if(productDetailData1.getProductName()==null)
				{
					productDetailData1 = listingPage.addAnySimpleProductFromWidget();
				}
			}
			if(productDetailData1.getProductName().equalsIgnoreCase("Widget not found"))
			{
				SanAssertCS.assertTrue(false, "R_80C");
			}
			else
			{
				SanAssertCS.assertTrue(cartPage.isProductPresentInBag(productDetailData1), "R_80C");
			}
			ExtentTest childTest2 = testListener.startChild("Add any Shade/Size product from L2 category React page all products section test ");
			childTest2.setDescription("This test verifies that user is able to add any Shade/Size product from L2 category React page all products section into cart");
			TestListner.extentMap.get().put("child2", childTest2);

			ProductDetailData ProductDetailData = listingPage.addAnySizeProductFromAllProduct();
			if(ProductDetailData.getProductName()==null)
			{
				ProductDetailData = listingPage.addAnySizeProductFromAllProduct();
				if(ProductDetailData.getProductName()==null)
				{
					ProductDetailData = listingPage.addAnySimpleProductFromAllProducts();
				}
			}
			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData), "R_80D");

			browser.navigate().refresh();

			ExtentTest childTest5 = testListener.startChild("Add any product from L2 category React page widget to wishlist test ");
			childTest5.setDescription("This test verifies that user is able to add any product from L2 category React page widget section to wishlist");
			TestListner.extentMap.get().put("child5", childTest5);

			if(productDetailData1.getProductName().equalsIgnoreCase("Widget not found"))
			{
				SanAssertCS.assertTrue(false, "R_80A");
			}
			else
			{
				boolean widgetWishlistAdded = listingPage.addToWishList(listingPage.widgetProducts(),null);
				SanAssertCS.assertTrue(widgetWishlistAdded, "R_80A");
			}

			ExtentTest childTest6 = testListener.startChild("Add any product from L2 category React page all products to wishlist test ");
			childTest6.setDescription("This test verifies that user is able to add any product from L2 category React page all products section to wishlist");
			TestListner.extentMap.get().put("child6", childTest6);

			boolean allProductWishlistAdded = listingPage.addToWishList(ListingPage_React.allProducts(),"simple");
			SanAssertCS.assertTrue(allProductWishlistAdded, "R_80B");

			ExtentTest childTest7 = testListener.startChild("Verify Offer text test ");
			childTest7.setDescription("This test verifies the offer on products in all products section of React page");
			TestListner.extentMap.get().put("child7", childTest7);

			String offerText = listingPage.verifyOfferOn(ListingPage_React.allProducts(),CommonConstants.NKYAA);
			SanAssertCS.assertTrue(offerText != null, "R_80K");

			ExtentTest childTest8 = testListener.startChild("Load More Products test ");
			childTest8.setDescription("This test verifies that products are loaded successfully on L2 category React page when user scrolls clicks Load More");
			TestListner.extentMap.get().put("child8", childTest8);

			listingPage.LoadMoreProducts(2,false);
			int value = listingPage.getProductCountOfAllProducts();
			if (value > 40) {
				SanAssertCS.assertTrue(true, "R_80E");
			}

			ExtentTest childTest12 = testListener.startChild("Back to Top test ");
			childTest12.setDescription("This test verifies that user is able go to top by clicking Back To Top button on React page");
			TestListner.extentMap.get().put("child12", childTest12);

			SanAssertCS.assertTrue(listingPage.backToTop(),"R_80F");



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

	@Test(groups = { "sanity","Wsanity","react","Wlisting","listing","local1"})
	public void React_Page_Check_for_L3_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l3category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);

		try {
			ExtentTest childTest11 = testListener.startChild("Existing User Login test");
			childTest11.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child11", childTest11);
			browser.get(environmentURL);
			AccountPage_React accountPage = new AccountPage_React(browser);
			AccountData Login = LoginSync.getInstance().getLogin();
			String LoggedUserName = accountPage.ReactSignInwithEmail(Login).toLowerCase();
			SanAssertCS.assertTrue((Login.getFirstName().toLowerCase().contains(LoggedUserName.toLowerCase().replaceAll("[^0-9]", ""))),  "Login Test");


			ExtentTest childTest1 = testListener.startChild("Add any Shade/Size product from L2 category React page widget test ");
			childTest1.setDescription("This test verifies that user is able to add any Shade/Size product from L3 category React page widget into cart");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			HeaderBar headerbar = new HeaderBar(browser);

            headerbar.clearCart();
			headerbar.navigateToCategory(headerbarData,environmentURL);

			
			ListingPage_React listingPage = new ListingPage_React(browser);
			listingPage.navigateToShopAll("shop-all",environmentURL);
			CartPage_React cartPage = new CartPage_React(browser);
			ProductDetailData productDetailData1 = listingPage.addAnyShadeProductFromWidget();
			if(productDetailData1.getProductName().equalsIgnoreCase("Product type not found"))
			{
				productDetailData1 = listingPage.addAnySizeProductFromWidget();
				if(productDetailData1.getProductName().equalsIgnoreCase("Product type not found"))
				{
					productDetailData1 = listingPage.addAnySimpleProductFromWidget();
				}
			}
			if(productDetailData1.getProductName().equalsIgnoreCase("Widget not found"))
			{
				SanAssertCS.assertTrue(false, "R_81C");
			}
			else
			{
				SanAssertCS.assertTrue(cartPage.isProductPresentInBag(productDetailData1), "R_81C");
			}
			ExtentTest childTest2 = testListener.startChild("Add any Shade/Size product from L3 category React page all products section test ");
			childTest2.setDescription("This test verifies that user is able to add any Shade/Size product from L3 category React page all products section into cart");
			TestListner.extentMap.get().put("child2", childTest2);

			ProductDetailData ProductDetailData = listingPage.addAnyShadeProductFromAllProduct();
			if(ProductDetailData.getProductName().equalsIgnoreCase("Product type not found"))
			{
				ProductDetailData = listingPage.addAnySizeProductFromAllProduct();
				if(ProductDetailData.getProductName().equalsIgnoreCase("Product type not found"))
				{
					ProductDetailData = listingPage.addAnySimpleProductFromAllProducts();
				}
			}
			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData), "R_81D");

			browser.navigate().refresh();

			ExtentTest childTest5 = testListener.startChild("Add any product from L3 category React page widget to wishlist test ");
			childTest5.setDescription("This test verifies that user is able to add any product from L3 category React page widget section to wishlist");
			TestListner.extentMap.get().put("child5", childTest5);

			if(productDetailData1.getProductName().equalsIgnoreCase("Widget not found"))
			{
				SanAssertCS.assertTrue(false, "R_81A");
			}
			else
			{
				boolean widgetWishlistAdded = listingPage.addToWishList(listingPage.widgetProducts(),null);
				SanAssertCS.assertTrue(widgetWishlistAdded, "R_81A");
			}

			ExtentTest childTest6 = testListener.startChild("Add any product from L3 category React page all products to wishlist test ");
			childTest6.setDescription("This test verifies that user is able to add any product from L3 category React page all products section to wishlist");
			TestListner.extentMap.get().put("child6", childTest6);

			boolean allProductWishlistAdded = listingPage.addToWishList(ListingPage_React.allProducts(),null);
			SanAssertCS.assertTrue(allProductWishlistAdded, "R_81B");

			ExtentTest childTest7 = testListener.startChild("Verify Offer text test ");
			childTest7.setDescription("This test verifies the offer on products in all products section of React page");
			TestListner.extentMap.get().put("child7", childTest7);

			String offerText = listingPage.verifyOfferOn(ListingPage_React.allProducts(),CommonConstants.NKYAA);
			SanAssertCS.assertTrue(offerText != null, "R_81K");

			ExtentTest childTest8 = testListener.startChild("Load More Products test ");
			childTest8.setDescription("This test verifies that products are loaded successfully on L3 category React page when user scrolls clicks Load More");
			TestListner.extentMap.get().put("child8", childTest8);

			listingPage.LoadMoreProducts(2,false);
			int value = listingPage.getProductCountOfAllProducts();
			if (value > 40) {
				SanAssertCS.assertTrue(true, "R_81E");
			}

			ExtentTest childTest12 = testListener.startChild("Back to Top test ");
			childTest12.setDescription("This test verifies that user is able go to top by clicking Back To Top button on React page");
			TestListner.extentMap.get().put("child12", childTest12);

			SanAssertCS.assertTrue(listingPage.backToTop(),"R_81F");

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

	@Test(groups = {"sanity","Wsanity","react","sorting","Wsorting", "local1"})
	public void React_Page_Sorting_on_L1_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
		List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		List<String> printListSorted = new ArrayList<String>();
		ListingPage_React listingPage = new ListingPage_React(browser);
		HeaderBar headerbar = new HeaderBar(browser);

		try {
			ExtentTest childTest1 = testListener.startChild("Sort by Name test ");
			childTest1.setDescription("This test verifies the user is able to sort products by Name on react page");
			TestListner.extentMap.get().put("child1", childTest1);

			browser.get(environmentURL);

			headerbar.navigateToCategory(headerbarData,environmentURL);
			productList = listingPage.ApplySort("NAME");
			productListFromUI.addAll(productList);
			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
					.compareTo(a2.getProductName()));
			for(int i=0;i<productList.size();i++)
			{
				printList.add(productList.get(i).getProductName());
			}
			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after Name Sorting: "+printList.toString());
			SanAssertCS.assertEquals(productListFromUI, productList, "R_79G");

			ExtentTest childTest2 = testListener.startChild("Sort by Price: Low To High test ");
			childTest2.setDescription("This test verifies the user is able to sort products by Price: Low To High on react page");
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
			childTest3.setDescription("This test verifies the user is able to sort products by Price: High To Low on react page");
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
			childTest4.setDescription("This test verifies the user is able to sort products by Discount on react page");
			TestListner.extentMap.get().put("child4", childTest4);
			productList.clear();
			productListFromUI.clear();
			printList.clear();
			printListSorted.clear();
			productList = listingPage.ApplySort("DISCOUNT");
			productListFromUI.addAll(productList);
			for(int i=0;i<productList.size();i++)
			{
				printList.add(productList.get(i).getDiscount());
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


	@Test(groups = {"sanity","Wsanity","react","sanity_Live","N_sanity_Live","sorting","Wsorting"})

	public void React_Page_Sorting_on_L2_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l2category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
		List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		List<String> printListSorted = new ArrayList<String>();
		ListingPage_React listingPage = new ListingPage_React(browser);
		HeaderBar headerbar = new HeaderBar(browser);

		try {
			ExtentTest childTest1 = testListener.startChild("Sort by Name test ");
			childTest1.setDescription("This test verifies the user is able to sort products by Name on react page");
			TestListner.extentMap.get().put("child1", childTest1);

			browser.get(environmentURL);

			headerbar.navigateToCategory(headerbarData,environmentURL);
			listingPage.navigateToShopAll("shop-all",environmentURL);
			productList = listingPage.ApplySort("NAME");
			productListFromUI.addAll(productList);
			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
					.compareToIgnoreCase(a2.getProductName()));
			for(int i=0;i<productList.size();i++)
			{
				printList.add(productList.get(i).getProductName());
			}
			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after Name Sorting: "+printList.toString());
			SanAssertCS.assertEquals(productListFromUI, productList, "R_80G");

			ExtentTest childTest2 = testListener.startChild("Sort by Price: Low To High test ");
			childTest2.setDescription("This test verifies the user is able to sort products by Price: Low To High on react page");
			TestListner.extentMap.get().put("child2", childTest2);
			productList.clear();
			productListFromUI.clear();
			printList.clear();
			productList = listingPage.ApplySort("PRICE: LOW TO HIGH");
			productListFromUI.addAll(productList);
			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductPrize().compareTo( a2.getProductPrize()));
			for(int i=0;i<productList.size();i++)
			{
				printList.add(productList.get(i).getProductPrize());
			}
			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after PRICE: LOW TO HIGH Sorting: "+printList.toString());
			SanAssertCS.assertEquals(productListFromUI, productList, "R_80H");

			ExtentTest childTest3 = testListener.startChild("Sort by Price: High To Low test ");
			childTest3.setDescription("This test verifies the user is able to sort products by Price: High To Low on react page");
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
			SanAssertCS.assertEquals(printListSorted, printList, "R_80Z");

			ExtentTest childTest4 = testListener.startChild("Sort by Discount test ");
			childTest4.setDescription("This test verifies the user is able to sort products by Discount on react page");
			TestListner.extentMap.get().put("child4", childTest4);
			productList.clear();
			productListFromUI.clear();
			printList.clear();
			printListSorted.clear();
			productList = listingPage.ApplySort("DISCOUNT");
			productListFromUI.addAll(productList);
			for(int i=0;i<productList.size();i++)
			{
				printList.add(productList.get(i).getDiscount());
			}
			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> Integer.parseInt(a1.getDiscount())-Integer.parseInt( a2.getDiscount()));
			Collections.reverse(productList);
			for(int i=0;i<productList.size();i++)
			{
				printListSorted.add(productList.get(i).getDiscount());
			}
			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after DISCOUNT Sorting: "+printList.toString());
			SanAssertCS.assertEquals(printListSorted, printList, "R_80I");



		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser,e);
			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally {
			browser.quit();
		}
	}

	@Test(groups = {"sanity","Wsanity","react","sorting","Wsorting","local1"})
	public void React_Page_Sorting_on_L3_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l3category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		List<ProductDetailData> productList = new ArrayList<ProductDetailData>();
		List<ProductDetailData> productListFromUI = new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		List<String> printListSorted = new ArrayList<String>();
		ListingPage_React listingPage = new ListingPage_React(browser);
		HeaderBar headerbar = new HeaderBar(browser);

		try {
			ExtentTest childTest1 = testListener.startChild("Sort by Name test ");
			childTest1.setDescription("This test verifies the user is able to sort products by Name on react page");
			TestListner.extentMap.get().put("child1", childTest1);

			browser.get(environmentURL);

			headerbar.navigateToCategory(headerbarData,environmentURL);
			listingPage.navigateToShopAll("shop-all",environmentURL);
			productList = listingPage.ApplySort("NAME");
			productListFromUI.addAll(productList);
			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductName()
					.compareToIgnoreCase(a2.getProductName()));
			for(int i=0;i<productList.size();i++)
			{
				printList.add(productList.get(i).getProductName());
			}
			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after Name Sorting: "+printList.toString());
			SanAssertCS.assertEquals(productListFromUI, productList, "R_81G");

			ExtentTest childTest2 = testListener.startChild("Sort by Price: Low To High test ");
			childTest2.setDescription("This test verifies the user is able to sort products by Price: Low To High on react page");
			TestListner.extentMap.get().put("child2", childTest2);
			productList.clear();
			productListFromUI.clear();
			printList.clear();
			productList = listingPage.ApplySort("PRICE: LOW TO HIGH");
			productListFromUI.addAll(productList);
			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> a1.getProductPrize().compareTo( a2.getProductPrize()));
			for(int i=0;i<productList.size();i++)
			{
				printList.add(productList.get(i).getProductPrize());
			}
			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after PRICE: LOW TO HIGH Sorting: "+printList.toString());
			SanAssertCS.assertEquals(productListFromUI, productList, "R_81H");

			ExtentTest childTest3 = testListener.startChild("Sort by Price: High To Low test ");
			childTest3.setDescription("This test verifies the user is able to sort products by Price: High To Low on react page");
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
			SanAssertCS.assertEquals(printListSorted, printList, "R_81Z");

			ExtentTest childTest4 = testListener.startChild("Sort by Discount test ");
			childTest4.setDescription("This test verifies the user is able to sort products by Discount on react page");
			TestListner.extentMap.get().put("child4", childTest4);
			productList.clear();
			productListFromUI.clear();
			printList.clear();
			printListSorted.clear();
			productList = listingPage.ApplySort("DISCOUNT");
			productListFromUI.addAll(productList);
			for(int i=0;i<productList.size();i++)
			{
				printList.add(productList.get(i).getDiscount());
			}
			Collections.sort(productList, (ProductDetailData a1, ProductDetailData a2) -> Integer.parseInt(a1.getDiscount())-Integer.parseInt( a2.getDiscount()));
			Collections.reverse(productList);
			for(int i=0;i<productList.size();i++)
			{
				printListSorted.add(productList.get(i).getDiscount());
			}
			TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after DISCOUNT Sorting: "+printList.toString());
			SanAssertCS.assertEquals(printListSorted, printList, "R_81I");



		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser,e);
			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally {
			browser.quit();
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test(groups = { "sanity","Wsanity","react","filter","Wfilter","local1"})
	public void React_Page_Price_Filter_on_L1_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
		List<ProductDetailData> productlistwithPrizeFilter=new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		HeaderBar headerbar = new HeaderBar(browser);
		ListingPage_React listingPage = new ListingPage_React(browser);
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
			headerbar.navigateToCategory(headerbarData,environmentURL);

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
					productListPriceFilter = listingPage.ApplyFilter("Price", val,false,true);
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


	@Test(groups = { "sanity","Wsanity","react","sanity_Live","N_sanity_Live","filter","Wfilter"})

	public void React_Page_Price_Filter_on_L2_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l2category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
		List<ProductDetailData> productlistwithPrizeFilter=new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		HeaderBar headerbar = new HeaderBar(browser);
		ListingPage_React listingPage = new ListingPage_React(browser);
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
			headerbar.navigateToCategory(headerbarData,environmentURL);
			listingPage.navigateToShopAll("shop-all",environmentURL);
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
					productListPriceFilter = listingPage.ApplyFilter("Price", val,false,true);
				}

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
				filtersCleared=listingPage.clearAllFilters();
				filtersClearedList.add(filtersCleared?"Filters cleared for"+val :"Filters not cleared for"+val );
			}
			SanAssertCS.assertTrue(PriceMatches,"R_80Q");

			ExtentTest childTestFilter = testListener.startChild("Clear All Filters test");
			childTestFilter.setDescription("This test verifies the user is able to clear applied filters on react page");
			TestListner.extentMap.get().put("childTestFilter", childTestFilter);
			TestListner.testing.get().log(LogStatus.INFO, "Clear Filter status: "+filtersClearedList.toString());
			SanAssertCS.assertTrue(! filtersClearedList.toString().contains("Filters not cleared"),"R_80V_1");

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
			SanAssertCS.assertTrue(PriceMatches,"R_80S");


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

	@Test(groups = { "sanity","Wsanity","react","filter","Wfilter","local1"})
	public void React_Page_Price_Filter_on_L3_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l3category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
		List<ProductDetailData> productlistwithPrizeFilter=new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		HeaderBar headerbar = new HeaderBar(browser);
		ListingPage_React listingPage = new ListingPage_React(browser);
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
			headerbar.navigateToCategory(headerbarData,environmentURL);
			listingPage.navigateToShopAll("shop-all",environmentURL);
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
					productListPriceFilter = listingPage.ApplyFilter("Price", val,false,true);
				}

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
				filtersCleared=listingPage.clearAllFilters();
				filtersClearedList.add(filtersCleared?"Filters cleared for"+val :"Filters not cleared for"+val );
			}
			SanAssertCS.assertTrue(PriceMatches,"R_81Q");

			ExtentTest childTestFilter = testListener.startChild("Clear All Filters test");
			childTestFilter.setDescription("This test verifies the user is able to clear applied filters on react page");
			TestListner.extentMap.get().put("childTestFilter", childTestFilter);
			TestListner.testing.get().log(LogStatus.INFO, "Clear Filter status: "+filtersClearedList.toString());
			SanAssertCS.assertTrue(! filtersClearedList.toString().contains("Filters not cleared"),"R_81V_1");

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
			SanAssertCS.assertTrue(PriceMatches,"R_81S");


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

	@Test(groups = { "sanity","Wsanity","react","brandfilter","filter","Wfilter" ,"local1"})
	public void React_Page_Brand_Filter_on_L1_category_page() throws Throwable {


Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);

		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		HeaderBar headerbar = new HeaderBar(browser);
		ListingPage_React listingPage = new ListingPage_React(browser);
		try {
			ExtentTest childTest1 = testListener.startChild("Navigate to React PLP test ");
			childTest1.setDescription("This test verifies the user is able to navigate to react page");
			TestListner.extentMap.get().put("childTest1", childTest1);
			int count=1;
			browser.get(environmentURL);
			headerbar.navigateToCategory(headerbarData,environmentURL);
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

	@Test(groups = { "sanity","Wsanity","react","filter","Wfilter" ,"local1"})
	public void React_Page_Brand_Filter_on_L2_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l2category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		HeaderBar headerbar = new HeaderBar(browser);
		ListingPage_React listingPage = new ListingPage_React(browser);
		try {
			ExtentTest childTest1 = testListener.startChild("Navigate to React PLP test ");
			childTest1.setDescription("This test verifies the user is able to navigate to react page");
			TestListner.extentMap.get().put("childTest1", childTest1);
			int count=1;
			browser.get(environmentURL);
			headerbar.navigateToCategory(headerbarData,environmentURL);
			listingPage.navigateToShopAll("shop-all",environmentURL);
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
						if(! (productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(val.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim())|| (productListPriceFilter.get(i).getProductName().split("'")[0].toLowerCase().contains("ogx"))))
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
				TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Brand "+ val+": "+printList.toString());
				TestListner.testing.get().log(LogStatus.INFO,"Brand match list :: "+BrandNameMatches.toString());
				SanAssertCS.assertTrue(BrandNameMatchesTemp,"Brand Filter test for: "+val);
				BrandNameMatches.add(BrandNameMatchesTemp?"Brand matches for"+val :"Brand does not match for"+val );
			}
			SanAssertCS.assertTrue(! BrandNameMatches.contains("Brand does not match"), "R_80T");

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
					else if( productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(value.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim()))
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
			SanAssertCS.assertTrue(countMatches && ! BrandNameMatches.contains("Brand does not match"), "R_80U");

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
			SanAssertCS.assertTrue(BrandNameMatchesTemp,"R_80V");



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


	@Test(groups = { "sanity","Wsanity","react","sanity_Live1","N_sanity_Live" ,"filter","Wfilter3"})

	public void React_Page_Brand_Filter_on_L3_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l3category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		HeaderBar headerbar = new HeaderBar(browser);
		ListingPage_React listingPage = new ListingPage_React(browser);
		try {
			ExtentTest childTest1 = testListener.startChild("Navigate to React PLP test ");
			childTest1.setDescription("This test verifies the user is able to navigate to react page");
			TestListner.extentMap.get().put("childTest1", childTest1);
			int count=1;
			browser.get(environmentURL);
			headerbar.navigateToCategory(headerbarData,environmentURL);
			listingPage.navigateToShopAll("shop-all",environmentURL);
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
						if(! (productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(val.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim())|| (productListPriceFilter.get(i).getProductName().split("'")[0].toLowerCase().contains("ogx"))))
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
				TestListner.testing.get().log(LogStatus.INFO,"Expected Result List after filter by Brand "+ val+": "+printList.toString());
				TestListner.testing.get().log(LogStatus.INFO,"Brand match list :: "+BrandNameMatches.toString());
				SanAssertCS.assertTrue(BrandNameMatchesTemp,"Brand Filter test for: "+val);
				BrandNameMatches.add(BrandNameMatchesTemp?"Brand matches for"+val :"Brand does not match for"+val );
			}
			SanAssertCS.assertTrue(! BrandNameMatches.contains("Brand does not match"), "R_81T");

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
					else if( productListPriceFilter.get(i).getProductName().replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim().contains(value.split(" ")[0].replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim()))
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
			SanAssertCS.assertTrue(countMatches && ! BrandNameMatches.contains("Brand does not match"), "R_81U");

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
			SanAssertCS.assertTrue(BrandNameMatchesTemp,"R_81V");


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


	@Test(groups = { "sanity","Wsanity" ,"react","sanity_Live","N_sanity_Live","filter","Wfilter"})

	public void React_Page_Discount_Filter_on_L1_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
		List<ProductDetailData> productlistwithPrizeFilter=new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		HeaderBar headerbar = new HeaderBar(browser);
		ListingPage_React listingPage = new ListingPage_React(browser);
		boolean PriceMatches=true;
		boolean filtersCleared=false;
		List<String> filtersClearedList=new ArrayList<String>();
		List<String> priceMatchedList=new ArrayList<String>();
		try 
		{
			ExtentTest childTest1 = testListener.startChild("Navigate to React Page test ");
			childTest1.setDescription("This test verifies the user is able to navigate to a react page");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			headerbar.navigateToCategory(headerbarData,environmentURL);

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

	@Test(groups = { "sanity","Wsanity" ,"react","filter","Wfilter", "local1"})
	public void React_Page_Discount_Filter_on_L2_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l2category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
		List<ProductDetailData> productlistwithPrizeFilter=new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		HeaderBar headerbar = new HeaderBar(browser);
		ListingPage_React listingPage = new ListingPage_React(browser);
		boolean PriceMatches=true;
		boolean filtersCleared=false;
		List<String> filtersClearedList=new ArrayList<String>();
		List<String> priceMatchedList=new ArrayList<String>();
		try 
		{
			ExtentTest childTest1 = testListener.startChild("Navigate to React Page test ");
			childTest1.setDescription("This test verifies the user is able to navigate to a react page");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			headerbar.navigateToCategory(headerbarData,environmentURL);
			listingPage.navigateToShopAll("shop-all",environmentURL);
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
			SanAssertCS.assertTrue(! priceMatchedList.toString().contains("Discount does not match"),"R_80X");

			ExtentTest childTestFilter = testListener.startChild("Clear All Filters test");
			childTestFilter.setDescription("This test verifies the user is able to clear applied filters on react page");
			TestListner.extentMap.get().put("childTestFilter", childTestFilter);
			TestListner.testing.get().log(LogStatus.INFO, "Clear Filter status: "+filtersClearedList.toString());
			SanAssertCS.assertTrue(! filtersClearedList.toString().contains("Filters not cleared"),"R_80V_1");


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


	@Test(groups = { "sanity","Wsanity" ,"react","filter","Wfilter1"})

	public void React_Page_Discount_Filter_on_L3_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l3category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
		List<ProductDetailData> productlistwithPrizeFilter=new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		HeaderBar headerbar = new HeaderBar(browser);
		ListingPage_React listingPage = new ListingPage_React(browser);
		boolean PriceMatches=true;
		boolean filtersCleared=false;
		List<String> filtersClearedList=new ArrayList<String>();
		List<String> priceMatchedList=new ArrayList<String>();
		try 
		{
			ExtentTest childTest1 = testListener.startChild("Navigate to React Page test ");
			childTest1.setDescription("This test verifies the user is able to navigate to a react page");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			headerbar.navigateToCategory(headerbarData,environmentURL);
			listingPage.navigateToShopAll("shop-all",environmentURL);
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
			SanAssertCS.assertTrue(! priceMatchedList.toString().contains("Discount does not match"),"R_81X");

			ExtentTest childTestFilter = testListener.startChild("Clear All Filters test");
			childTestFilter.setDescription("This test verifies the user is able to clear applied filters on react page");
			TestListner.extentMap.get().put("childTestFilter", childTestFilter);
			TestListner.testing.get().log(LogStatus.INFO, "Clear Filter status: "+filtersClearedList.toString());
			SanAssertCS.assertTrue(! filtersClearedList.toString().contains("Filters not cleared"),"R_81V_1");


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

	@Test(groups = { "sanity","Wsanity" ,"react","filter","Wfilter","local1"})
	public void React_Page_Multiple_Filter_on_L1_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		HeaderBar headerbar = new HeaderBar(browser);
		ListingPage_React listingPage = new ListingPage_React(browser);
		boolean PriceMatches=true;
		boolean filtersCleared=false;
		try {
			ExtentTest childTest1 = testListener.startChild("Navigate to React PLP test ");
			childTest1.setDescription("This test verifies the user is able to navigate to react page");
			TestListner.extentMap.get().put("childTest1", childTest1);
			int count=1;
			browser.get(environmentURL);
			headerbar.navigateToCategory(headerbarData,environmentURL);
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


	@Test(groups = { "sanity","Wsanity" ,"react","sanity_Live","N_sanity_Live","multipleFilter","filter","Wfilter"})

	public void React_Page_Multiple_Filter_on_L2_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l2category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		HeaderBar headerbar = new HeaderBar(browser);
		ListingPage_React listingPage = new ListingPage_React(browser);
		boolean PriceMatches=true;
		boolean filtersCleared=false;
		try {
			ExtentTest childTest1 = testListener.startChild("Navigate to React PLP test ");
			childTest1.setDescription("This test verifies the user is able to navigate to react page");
			TestListner.extentMap.get().put("childTest1", childTest1);
			int count=1;
			browser.get(environmentURL);
			headerbar.navigateToCategory(headerbarData,environmentURL);
			listingPage.navigateToShopAll("shop-all",environmentURL);
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
					expected=brand.replace("OGX", "Organix").toLowerCase();
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
			SanAssertCS.assertTrue(BrandNameMatchesTemp && PriceMatches, "R_80Y");

			ExtentTest childTestFilter = testListener.startChild("Clear All Filters test");
			childTestFilter.setDescription("This test verifies the user is able to clear applied filters on react page");
			TestListner.extentMap.get().put("childTestFilter", childTestFilter);
			filtersCleared=listingPage.clearAllFilters();
			TestListner.testing.get().log(LogStatus.INFO, "Clear Filter status: "+filtersCleared);
			SanAssertCS.assertTrue(filtersCleared,"R_80V_1");

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

	@Test(groups = { "sanity","Wsanity" ,"react","filter","Wfilter","local1"})
	public void React_Page_Multiple_Filter_on_L3_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l3category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		List<ProductDetailData> productListPriceFilter=new ArrayList<ProductDetailData>();
		List<String> printList = new ArrayList<String>();
		HeaderBar headerbar = new HeaderBar(browser);
		ListingPage_React listingPage = new ListingPage_React(browser);
		boolean PriceMatches=true;
		boolean filtersCleared=false;
		try {
			ExtentTest childTest1 = testListener.startChild("Navigate to React PLP test ");
			childTest1.setDescription("This test verifies the user is able to navigate to react page");
			TestListner.extentMap.get().put("childTest1", childTest1);
			int count=1;
			browser.get(environmentURL);
			headerbar.navigateToCategory(headerbarData,environmentURL);
			listingPage.navigateToShopAll("shop-all",environmentURL);
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
			SanAssertCS.assertTrue(BrandNameMatchesTemp && PriceMatches, "R_81Y");

			ExtentTest childTestFilter = testListener.startChild("Clear All Filters test");
			childTestFilter.setDescription("This test verifies the user is able to clear applied filters on react page");
			TestListner.extentMap.get().put("childTestFilter", childTestFilter);
			filtersCleared=listingPage.clearAllFilters();
			TestListner.testing.get().log(LogStatus.INFO, "Clear Filter status: "+filtersCleared);
			SanAssertCS.assertTrue(filtersCleared,"R_81V_1");

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

	/**********************************************************************
	 * React Pages test- Product Pages.
	 ***********************************************************************/

	@Test(groups = { "productPage", "WproductPage","sanity","Wsanity","sanity_Live","N_sanity_Live"})
	public void React_Page_Simple_Product_Page_for_L1_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		Header_FooterBarData headerbarData1 = framework.getData(Header_FooterBarData.class, "l2category_react");
		

		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		HeaderBar headerbar=new HeaderBar(browser);
		try 
		{
			ExtentTest childTest11 = testListener.startChild("Existing User Login test");
			childTest11.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child11", childTest11);
			browser.get(environmentURL);
			AccountPage_React accountPage = new AccountPage_React(browser);
			AccountData Login = LoginSync.getInstance().getLogin();
			String LoggedUserName = accountPage.ReactSignInwithEmail(Login).toLowerCase();
			SanAssertCS.assertTrue((Login.getFirstName().toLowerCase().contains(LoggedUserName.toLowerCase().replaceAll("[^0-9]", ""))),  "Login Test");

			ExtentTest childTest2 = testListener.startChild("Add Simple Product from Product Page Test");
			childTest2.setDescription("This test verifies that new user is able to add simple procuct from PDP");
			TestListner.extentMap.get().put("child2", childTest2);
			headerbar.clearCart();
			headerbar.navigateToCategory(headerbarData,environmentURL);
			ProductPage_React productPage = new ProductPage_React(browser);
			CartPage_React cartPage = new CartPage_React(browser);
			ProductDetailData ProductDetailData1 = productPage.addAnySimpleProductFromProductPAge();
			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData1), "R_82A");

			ExtentTest childTest3 = testListener.startChild("Customer Also Viewed Widget Test");
			childTest3.setDescription("This test verifies that user is able to view PAV widget");
			TestListner.extentMap.get().put("child3", childTest3);
			SanAssertCS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "R_82C");

			ExtentTest childTest4 = testListener.startChild("Customer Also Bought Widget Test");
			childTest4.setDescription("This test verifies that user is able to view PAB widget");
			TestListner.extentMap.get().put("child4", childTest4);
			SanAssertCS.assertTrue(productPage.isPeopleWhoboughtDisplayed(), "R_82D");

			ExtentTest childTest5 = testListener.startChild("Add to Wishlist Test");
			childTest5.setDescription("This test verifies that user is able to add product to wishlist");
			TestListner.extentMap.get().put("child5", childTest5);
			browser.navigate().refresh();
			SanAssertCS.assertTrue(productPage.addToWishlist(), "R_82B");

			ExtentTest childTest6 = testListener.startChild("Add Product to Cart from Wishlist Test");
			childTest6.setDescription("This test verifies that user is able to add product to cart from wishlist");
			TestListner.extentMap.get().put("child6", childTest6);
			headerbar.clearCart();
			accountPage.navigateToWishlist();
			ListingPage_React listingPage = new ListingPage_React(browser);
			ProductDetailData wishlistProductDetaildata = listingPage.addAnySimpleProductFromWishlist();

			headerbar.navigateToCategory(headerbarData,environmentURL);//to navigate to react page in order to use react cart methods

			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(wishlistProductDetaildata), "Add to bag from Wishlist");


		} catch (Exception e) {
			printStackStraceinLog(e);
			e.printStackTrace();
			framework.logErrorWithSnapshot(browser,e);
			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally {

			//LoginSync.getInstance().clearLoginLock(accountData);
			browser.quit();
		}
	}


	@Test(groups = { "productaddtobag", "productaddtobagsimple1","sanity","Wsanity","sanity_Live","N_sanity_Live"})

	public void React_Page_Shade_Product_Page_for_L1_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);
		HeaderBar headerbar=new HeaderBar(browser);
		try 
		{
			ExtentTest childTest11 = testListener.startChild("Existing User Login test");
			childTest11.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child11", childTest11);
			browser.get(environmentURL);
			AccountPage_React accountPage = new AccountPage_React(browser);
			AccountData Login = LoginSync.getInstance().getLogin();
			String LoggedUserName = accountPage.ReactSignInwithEmail(Login).toLowerCase();
			SanAssertCS.assertTrue((Login.getFirstName().toLowerCase().contains(LoggedUserName.toLowerCase().replaceAll("[^0-9]", ""))),  "Login Test");

			ExtentTest childTest2 = testListener.startChild("Add Shade Product from Product Page Test");
			childTest2.setDescription("This test verifies that new user is able to add shade procuct from PDP");
			TestListner.extentMap.get().put("child2", childTest2);
			headerbar.clearCart();
			headerbar.navigateToCategory(headerbarData,environmentURL);
			ProductPage_React productPage = new ProductPage_React(browser);
			CartPage_React cartPage = new CartPage_React(browser);
			ProductDetailData ProductDetailData1 = productPage.addAnyShadeProductFromProductPAge();
			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData1), "R_82E");

			ExtentTest childTest3 = testListener.startChild("Customer Also Viewed Widget Test");
			childTest3.setDescription("This test verifies that user is able to view PAV widget");
			TestListner.extentMap.get().put("child3", childTest3);
			SanAssertCS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "R_82G");

			ExtentTest childTest4 = testListener.startChild("Customer Also Bought Widget Test");
			childTest4.setDescription("This test verifies that user is able to view PAB widget");
			TestListner.extentMap.get().put("child4", childTest4);
			SanAssertCS.assertTrue(productPage.isPeopleWhoboughtDisplayed(), "R_82I");

			ExtentTest childTest5 = testListener.startChild("Add to Wishlist Test");
			childTest5.setDescription("This test verifies that user is able to add product to wishlist");
			TestListner.extentMap.get().put("child5", childTest5);
			browser.navigate().refresh();
			SanAssertCS.assertTrue(productPage.addToWishlist(), "R_82F");

			/*ExtentTest childTest6 = testListener.startChild("Add Product to Cart from Wishlist Test");
				childTest6.setDescription("This test verifies that user is able to add product to cart from wishlist");
				TestListner.extentMap.get().put("child6", childTest6);*/
			/*headerbar.clearCart_React();
				accountPage.navigateToWishlist();
				ListingPage listingPage = new ListingPage(browser);
				ProductDetailData wishlistProductDetaildata = listingPage.addAnyShadeProductwishlist();
				headerbar.navigateToCategory(headerbarData);//to navigate to react page in order to use react cart methods
			 */				//SanAssertCS.assertTrue(cartPage.isProductPresentInBag(wishlistProductDetaildata), "Add to bag from Wishlist");

		} catch (Exception e) {
			printStackStraceinLog(e);
			e.printStackTrace();
			framework.logErrorWithSnapshot(browser,e);
			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally {

			//LoginSync.getInstance().clearLoginLock(accountData);
			browser.quit();
		}
	}


	@Test(groups = { "productPage", "WproductPage","sanity","Wsanity", "local1"})
	public void React_Page_Size_Product_Page_for_L1_category_page() throws Throwable {

		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanAssertCS = new CustomSoftAssert(SanAssertData, browser);

		HeaderBar headerbar=new HeaderBar(browser);
		try 
		{
			ExtentTest childTest11 = testListener.startChild("Existing User Login test");
			childTest11.setDescription("This test verifies that user is able to login with existing credentials.");
			TestListner.extentMap.get().put("child11", childTest11);
			browser.get(environmentURL);
			AccountPage_React accountPage = new AccountPage_React(browser);
			AccountData Login = LoginSync.getInstance().getLogin();
			String LoggedUserName = accountPage.ReactSignInwithEmail(Login).toLowerCase();
			SanAssertCS.assertTrue((Login.getFirstName().toLowerCase().contains(LoggedUserName.toLowerCase().replaceAll("[^0-9]", ""))),  "Login Test");

			ExtentTest childTest2 = testListener.startChild("Add Size Product from Product Page Test");
			childTest2.setDescription("This test verifies that new user is able to add size procuct from PDP");
			TestListner.extentMap.get().put("child2", childTest2);
			headerbar.clearCart();
			headerbar.navigateToCategory(headerbarData,environmentURL);
			ProductPage_React productPage = new ProductPage_React(browser);
			CartPage_React cartPage = new CartPage_React(browser);
			ProductDetailData ProductDetailData1 = productPage.addAnySizeProductFromProductPage();
			SanAssertCS.assertTrue(cartPage.isProductPresentInBag(ProductDetailData1), "R_82J");

			ExtentTest childTest3 = testListener.startChild("Customer Also Viewed Widget Test");
			childTest3.setDescription("This test verifies that user is able to view PAV widget");
			TestListner.extentMap.get().put("child3", childTest3);
			SanAssertCS.assertTrue(productPage.isCustomerAlsoviewedDisplayed(), "R_82L");

			ExtentTest childTest4 = testListener.startChild("Customer Also Bought Widget Test");
			childTest4.setDescription("This test verifies that user is able to view PAB widget");
			TestListner.extentMap.get().put("child4", childTest4);
			SanAssertCS.assertTrue(productPage.isPeopleWhoboughtDisplayed(), "R_82M");

			ExtentTest childTest5 = testListener.startChild("Add to Wishlist Test");
			childTest5.setDescription("This test verifies that user is able to add product to wishlist");
			TestListner.extentMap.get().put("child5", childTest5);
			browser.navigate().refresh();
			SanAssertCS.assertTrue(productPage.addToWishlist(), "R_82K");

			/*ExtentTest childTest6 = testListener.startChild("Add Product to Cart from Wishlist Test");
				childTest6.setDescription("This test verifies that user is able to add product to cart from wishlist");
				TestListner.extentMap.get().put("child6", childTest6);*/
			/*headerbar.clearCart_React();
				accountPage.navigateToWishlist();
				ListingPage listingPage = new ListingPage(browser);
				ProductDetailData wishlistProductDetaildata = listingPage.addAnyShadeProductwishlist();
				headerbar.navigateToCategory(headerbarData);//to navigate to react page in order to use react cart methods
			 */				//SanAssertCS.assertTrue(cartPage.isProductPresentInBag(wishlistProductDetaildata), "Add to bag from Wishlist");

		} catch (Exception e) {
			printStackStraceinLog(e);
			e.printStackTrace();
			framework.logErrorWithSnapshot(browser,e);
			Reporter.log(e.toString(), true);
			Assert.fail();

		} finally {

			//LoginSync.getInstance().clearLoginLock(accountData);
			browser.quit();
		}
	}

	@Test(groups = { "Notifyme","React", "Debug"})
	public void React_Page_NotifyMe() throws Throwable {

	   Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "l1category_react");
	   WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
	   CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
	   AccountData Login = LoginSync.getInstance().getLogin();
	   AccountData accountData = framework.getData(AccountData.class, "loginset");
	   try {
	      ExtentTest childTest1 = testListener.startChild("Register user able to Login in System");
	      childTest1.setDescription("This test verifies that Existing user is able to Login in System");
	      TestListner.extentMap.get().put("child1", childTest1);
	      browser.get(environmentURL);
	      HeaderBar headerbar = new HeaderBar(browser);
	      headerbar.navigateToCategory(headerbarData,environmentURL);
	         AccountPage_React accountPage = new AccountPage_React(browser);
	         String loggedInUser = accountPage.ReactSignInwithEmail(accountData);
	         SanCS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()),
	               "New user registration test");
	      ExtentTest childTest2 = testListener.startChild("Verify Notfiy Me From React Listing Page");
	      childTest2.setDescription("This test verifies that Notify Me is working from React Listing Page");
	      TestListner.extentMap.get().put("child3", childTest2);
	         ListingPage_React listingPage = new ListingPage_React(browser);
	      boolean NotifyfromPLP = listingPage.Notifyme(Login);
	         SanCS.assertTrue(NotifyfromPLP, "R_82NN");
	         /***********************************************************************************************/
	      ExtentTest childTest3 = testListener.startChild("Verify Notfiy Me From React Product Page");
	      childTest3.setDescription("This test verifies that Notify Me is working from React Product Page");
	      TestListner.extentMap.get().put("child3", childTest3);
	      ProductPage_React productPage = new ProductPage_React(browser);
	      boolean NotifyfromPD = productPage.Notifyme(Login,environmentURL);
	      SanCS.assertTrue(NotifyfromPD, "R_83NN");
	   }catch (Exception e) {
	      printStackStraceinLog(e);
	      framework.logErrorWithSnapshot(browser, e);
	      Reporter.log(e.toString());
	      Assert.fail();
	   } finally {
	      browser.quit();
	   }
	
	}
	
	@Test(groups = { "subscribe","React", "local1"})
	public void subscibe() throws Throwable {
		Header_FooterBarData headerbarData = framework.getData(Header_FooterBarData.class, "sizenavigation");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		AccountData Login = LoginSync.getInstance().getLogin();
		CheckoutData checKoutData = framework.getData(CheckoutData.class, "validCredit");
		try {
			ExtentTest childTest1 = testListener.startChild("Subscribe the Product By Guest User");
			childTest1.setDescription("This test verifies Guest User able Subscribe Product");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			HeaderBar headerbar = new HeaderBar(browser);
			headerbar.navigateToCategory(headerbarData,environmentURL);
			//String loggedInUser = accountPage.ReactSignInwithEmail(accountData);
			//SanCS.assertTrue(loggedInUser.toLowerCase().equals(accountData.getFirstName()),
			//    "User able to Login thorugh G+");
			//headerbar.navigateToCategory(headerbarData);
			ExtentTest childTest3 = testListener.startChild("Verify Subscription from React Product Page");
			childTest3.setDescription("This test verifies that subscription from React Product Page");
			TestListner.extentMap.get().put("child3", childTest3);
			ProductPage_React productPage = new ProductPage_React(browser);
			boolean Subscription = productPage.Subscribe(Login, checKoutData,environmentURL);
			SanCS.assertTrue(Subscription, "R_82NN");

		}catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser, e);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {
			browser.quit();
		}
	}


	/**********************************************************************
	 * Login/ Logout tests.
	 ***********************************************************************/

	   @Test(groups = {"Wlogin","login","sanity","Wsanity","sanity_Live","N_sanity_Live"})

	public void SignUp_For_New_User() throws Throwable {
		Header_FooterBarData L1categoryReact = framework.getData(Header_FooterBarData.class, "l1category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		AccountData accountData = framework.getData(AccountData.class, "newlogin");
		AccountPage_React accountPagereact=new AccountPage_React(browser);
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		browser.get(environmentURL);
		try{
			ExtentTest childTest1 = testListener.startChild("New User Registration on Home Page");
			childTest1.setDescription("This test verifies that User is able to sign up from Home Page");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			HeaderBar headerBar = new HeaderBar(browser);
			String loginUser = accountPagereact.NewRegister_React(accountData);
			SanCS.assertTrue((accountData.getFirstName().toLowerCase().contains(loginUser.toLowerCase().replaceAll("[^0-9]", ""))), "270");
			accountPagereact.LogOut(CommonConstants.NKYAA);

			ExtentTest childTest2 = testListener.startChild("New User Registration on React Page");
			childTest2.setDescription("This test verifies that User is able to sign up from React Page");
			TestListner.extentMap.get().put("child2", childTest2);
			headerBar.navigateToCategory(L1categoryReact,environmentURL);

			String loginUser1=accountPagereact.NewRegister_React(accountData);
			SanCS.assertTrue((accountData.getFirstName().toLowerCase().contains(loginUser1.toLowerCase().replaceAll("[^0-9]", ""))), "270_R");



		}catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		browser.quit();

	}

	/*@Test(groups = {"Magento_Login"})
	public void Login_Logout_HomePage_Magento () throws Throwable {
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		browser.get(environmentURL);
		//AccountData FbLogin = framework.getData(AccountData.class, "facebook");
		AccountData GLogin = framework.getData(AccountData.class, "google");
		AccountData Login = LoginSync.getInstance().getLogin();
		try{
			ExtentTest childTest1 = testListener.startChild("Login- Email Id on Home Page- Magento");
			childTest1.setDescription("This test verifies that User is able to Login using email id on Home Page");
			TestListner.extentMap.get().put("child1", childTest1);
			AccountPage_React accountPage = new AccountPage_React(browser);
			//AccountPage_React accountPage_react = new AccountPage_React(browser);
			String NLogin = accountPage.ReactSignInwithEmail(Login);
			SanCS.assertEquals(NLogin.toLowerCase(), Login.getFirstName().toLowerCase(), "L1H1");

			ExtentTest childTest4 = testListener.startChild("Logout on Home Page- Magento");
			childTest4.setDescription("This test verifies that User is able to Logout on Home Page");
			TestListner.extentMap.get().put("child4", childTest4);
			boolean isNLogout = accountPage.LogOut();
			SanCS.assertTrue(isNLogout, "L1H2");
			/*ExtentTest childTest2 = testListener.startChild("Login - Logout(Facebook) from Home Page");
	         childTest2.setDescription("This test verifies that User is able to Login and Logout (Facebook) Through Home Page");
	         TestListner.extentMap.get().put("child2", childTest2);
	         String FLogin = accountPage.signInWithFacebook(FbLogin);
	         SanCS.assertEquals(FLogin, Login.getFirstName(), "L1H3");
	         boolean isFLogout = accountPage.LogOut();
	         SanCS.assertTrue(isFLogout, "L1H4");
			ExtentTest childTest3 = testListener.startChild("GOOGLE+ Login on Home Page");
			childTest3.setDescription("This test verifies that User is able to Login with GOOGLE+ on Home Page");
			TestListner.extentMap.get().put("child3", childTest3);
			String GoLogin = accountPage.signInWithGooglePlus(GLogin);
			System.out.println(GoLogin);
			SanCS.assertEquals(GoLogin.toLowerCase(), Login.getFirstName().toLowerCase(), "L1H5");

			ExtentTest childTest5 = testListener.startChild("GOOGLE+ logout on Home Page");
			childTest5.setDescription("This test verifies that User is able to Logout on Home Page if logged in with GOOGLE+ ");
			TestListner.extentMap.get().put("child5", childTest5);
			boolean isGoLogout = accountPage.LogOut();
			SanCS.assertTrue(isGoLogout, "L1H6");
		}catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		browser.quit();

	}*/

	@Test(groups = {"React_Login"})
	public void Login_Logout_L1_Page () throws Throwable {
		Header_FooterBarData L1category = framework.getData(Header_FooterBarData.class, "l1category");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		browser.get(environmentURL);
		AccountData Login = LoginSync.getInstance().getLogin();
		//AccountData FbLogin = framework.getData(AccountData.class, "facebook");
		AccountData GLogin = framework.getData(AccountData.class, "google");
		try{
			ExtentTest childTest1 = testListener.startChild("Login - Logout(Normal Login) from Listing Page");
			childTest1.setDescription("This test verifies that User is able to Login and Logout (Normal Login) Through Listing Page");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			HeaderBar headerBar = new HeaderBar(browser);
			headerBar.clearCart();
			headerBar.navigateToCategory(L1category,environmentURL);
			AccountPage_React accountPage = new AccountPage_React(browser);
			String NLogin = accountPage.signInWithYourEmail(Login);
			SanCS.assertEquals(NLogin.toLowerCase(), Login.getFirstName().toLowerCase(), "L1L1");
			accountPage.LogOut(CommonConstants.NKYAA);
			/*ExtentTest childTest2 = testListener.startChild("Login - Logout(Facebook) from Listing Page");
	         childTest2.setDescription("This test verifies that User is able to Login and Logout (Facebook) Through Listing Page");
	         TestListner.extentMap.get().put("child2", childTest2);
	         SanCS.assertTrue(isNLogout, "L1L2");
	         String FLogin = accountPage.signInWithFacebook(FbLogin);
	         SanCS.assertEquals(FLogin, Login.getFirstName(), "L1L3");
	         boolean isFLogout = accountPage.LogOut();
	         SanCS.assertTrue(isFLogout, "L1L4");*/
			ExtentTest childTest3 = testListener.startChild("Login - Logout(Goggle) from Listing Page");
			childTest3.setDescription("This test verifies that User is able to Login and Logout (Google) Through Listing Page");
			TestListner.extentMap.get().put("child3", childTest3);
			String GoLogin = accountPage.signInWithGooglePlus(GLogin);
			System.out.println(GoLogin);
			SanCS.assertEquals(GoLogin.toLowerCase().toLowerCase(), Login.getFirstName().toLowerCase(), "L1L5");
			boolean isGoLogout = accountPage.LogOut(CommonConstants.NKYAA);
			SanCS.assertTrue(isGoLogout, "L1L6");
		}catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		browser.quit();

	}
	@Test(groups = {"Wlogin","login","sanity","Wsanity","sanity_Live","N_sanity_Live","Sourabh"})
	public void Login_Logout_L1_ReactPage () throws Throwable {
		Header_FooterBarData L1categoryReact = framework.getData(Header_FooterBarData.class, "l1category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		browser.get(environmentURL);
		AccountData Login = LoginSync.getInstance().getLogin();
		//AccountData FbLogin = framework.getData(AccountData.class, "facebook");
		AccountData GLogin = framework.getData(AccountData.class, "google");
		try{
			ExtentTest childTest1 = testListener.startChild("Login using Email Id on React Page");
			childTest1.setDescription("This test verifies that User is able to Login using email id on React Page");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			HeaderBar headerBar = new HeaderBar(browser);
			headerBar.clearCart();
			headerBar.navigateToCategory(L1categoryReact,environmentURL);
			AccountPage_React accountPage_react = new AccountPage_React(browser);
			String NLogin = accountPage_react.ReactSignInwithEmail(Login);
			SanCS.assertTrue(Login.getFirstName().toLowerCase().contains(NLogin.replace("...","").toLowerCase()), "L1R1");
			ExtentTest childTest4 = testListener.startChild("Logout on React Page");
			childTest4.setDescription("This test verifies that User is able to Logout on React Page");
			TestListner.extentMap.get().put("child4", childTest4);
			boolean isNLogout = accountPage_react.LogOut(CommonConstants.NKYAA);
			SanCS.assertTrue(isNLogout, "L1R2");
			/*ExtentTest childTest2 = testListener.startChild("Login - Logout(Facebook) from React Page");
	         childTest2.setDescription("This test verifies that User is able to Login and Logout (Facebook) Through React Page");
	         TestListner.extentMap.get().put("child2", childTest2);
	         String FLogin= accountPage_react.signInWithFacebook(FbLogin);
	         SanCS.assertEquals(FLogin, FbLogin.getFirstName(), "L1R3");
	         boolean isFLogout = accountPage_react.LogOut();
	         SanCS.assertTrue(isFLogout, "L1R4");*/
			ExtentTest childTest3 = testListener.startChild("GOOGLE+ Login on React Page");
			childTest3.setDescription("This test verifies that User is able to Login using Google+ on React Page");
			TestListner.extentMap.get().put("child3", childTest3);
			String GoLogin = accountPage_react.signInWithGooglePlus(GLogin);
			SanCS.assertTrue(Login.getFirstName().toLowerCase().contains(GoLogin.replace("...","").toLowerCase()), "L1R5");

			ExtentTest childTest6 = testListener.startChild("GOOGLE+ Logout on React Page");
			childTest6.setDescription("This test verifies that User is able to Logout from React Page if logged in using Google+");
			TestListner.extentMap.get().put("child6", childTest6);
			headerBar.navigateToCategory(L1categoryReact,environmentURL);
			boolean isGoLogout = accountPage_react.LogOut("");
			SanCS.assertTrue(isGoLogout, "L1R6");
		}catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		browser.quit();

	}

	@Test(groups = {"sanity", "Wlogin","login","sanity","Wsanity", "local1"})
	public void Login_Logout_MyOrder () throws Throwable {
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		browser.get(environmentURL);
		AccountData Login = LoginSync.getInstance().getLogin();
		//AccountData FbLogin = framework.getData(AccountData.class, "facebook");
		AccountData GLogin = framework.getData(AccountData.class, "google");
		try{
			ExtentTest childTest1 = testListener.startChild("Login- Email Id on Home Page and Navigate to My Orders");
			childTest1.setDescription("This test verifies that User is able to Login using email id on Home Page and navigate to My Order Page");
			TestListner.extentMap.get().put("child1", childTest1);
			browser.get(environmentURL);
			//headerBar.navigateToCategory(L1category);
			AccountPage_React accountPage = new AccountPage_React(browser);
			String NLogin = accountPage.ReactSignInwithEmail(Login);
			SanCS.assertEquals(NLogin.toLowerCase(), Login.getFirstName().toLowerCase(), "L1M1");

			ExtentTest childTest4 = testListener.startChild("Logout on My Order Page");
			childTest4.setDescription("This test verifies that User is able to Logout on My Order Page");
			TestListner.extentMap.get().put("child4", childTest4);
			boolean isNLogout = accountPage.Login_Logout_Myorder();
			SanCS.assertTrue(isNLogout, "L1M2");
			/*ExtentTest childTest2 = testListener.startChild("Login - Logout(Facebook) from My Order Page");
		         childTest2.setDescription("This test verifies that User is able to Login and Logout (Facebook) Through My Order Page");
		         TestListner.extentMap.get().put("child2", childTest2);
		         String isFLogin = accountPage.signInWithFacebook(FbLogin);
		         SanCS.assertEquals(isFLogin, Login.getFirstName(), "L1M3");
		         boolean isFLogout = accountPage.Myorder();
		         SanCS.assertTrue(isFLogout, "L1M4");*/
			ExtentTest childTest3 = testListener.startChild("GOOGLE+ Login on Home Page and Navigate to My Orders");
			childTest3.setDescription("This test verifies that User is able to Login with GOOGLE+ on Home Page and navigstr to My Order Page");
			TestListner.extentMap.get().put("child3", childTest3);
			String isGoLogin = accountPage.signInWithGooglePlus(GLogin);
			SanCS.assertTrue(Login.getFirstName().toLowerCase().contains(isGoLogin.replace("...","").toLowerCase()), "L1M5");

			ExtentTest childTest5 = testListener.startChild("GOOGLE+ logout on My Order Page");
			childTest5.setDescription("This test verifies that User is able to Logout on My Order Page if logged in with GOOGLE+ ");
			TestListner.extentMap.get().put("child5", childTest5);
			boolean isGoLogout = accountPage.Login_Logout_Myorder();
			SanCS.assertTrue(isGoLogout, "L1M6");
		}catch (Exception e) { printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser, e);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {
			browser.quit();

		}
	}


	@Test(groups = {"sanity", "Tip_Tile","sanity","Wsanity"})
	public void Tip_tile () throws Throwable {
		Header_FooterBarData L1categoryReact = framework.getData(Header_FooterBarData.class, "l2category_react");
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		CustomSoftAssert SanCS = new CustomSoftAssert(SanAssertData, browser);
		browser.get(environmentURL);
		try {
			ExtentTest childTest1 = testListener.startChild("Tip tile Testing");
			childTest1.setDescription("This Test is to Verify Tip tile is Appearing on Position that mentioned in API On Listing Page");
			TestListner.extentMap.get().put("child1", childTest1);
			HeaderBar headerBar = new HeaderBar(browser);
			headerBar.navigateToCategory(L1categoryReact,environmentURL);
			ListingPage_React listingPage = new ListingPage_React(browser);
		    boolean isprsent = listingPage.Tiptile(environmentURL);
			SanCS.assertTrue(isprsent, "L1R6");
		}
		catch(Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser, e);
			Reporter.log(e.toString());
			Assert.fail();
		} finally {
			browser.quit();

		}
	}

	@Test( groups = { "ProductPageaddtobag", "Analytics", "cart02" })
	//retryAnalyzer = Retry.class,
	public void Create_Test_data_User_in_Non_prod() throws Throwable, Throwable {
		Framework framework = new Framework();
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		browser.get(environmentURL);
		List<AccountData> AccountData_List = framework.getDataList(AccountData.class, "loginset");
		try {
			ExtentTest childTest1 = testListener.startChild("New User Register Test");
			childTest1.setDescription("This test verifies that new user is able to register");
			TestListner.extentMap.get().put("child1", childTest1);

			AccountPage accountPage = new AccountPage(browser);
			for (AccountData accountdata : AccountData_List){
				String loggedInUser = accountPage.NewRegister_nonprod(accountdata);
				if(loggedInUser!="User already registered") {
					Thread.sleep(5000);
					accountPage.LogOut();
					Thread.sleep(5000);
				}
			}

		} catch (Exception e) {
			printStackStraceinLog(e);
			framework.logErrorWithSnapshot(browser, e);
			Reporter.log(e.toString());
			Assert.fail();

		} finally {
			browser.close();
			browser.quit();
		}

	}



	public void AllListingPageUI() throws Throwable {
		
		WebDriver browser = framework.getBrowser(CommonConstants.NKYAA);
		browser.get(environmentURL);
		HeaderBar headerBar = new HeaderBar(browser);
		
		List<String> allCategories=headerBar.getAllCategories();
		for(String mainMenu:allCategories)
		{
		//headerBar.navigateToCategory();
		}
	}


	
	
	}
