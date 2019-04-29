package TestCaseSuite_NykaaMan;

import DataNykaa.AccountData;
import DataNykaa.AssertData;
import DataNykaa.CheckoutData;
import DataNykaa.Header_FooterBarData;
import DataNykaa.ProductDetailData;
import PagesNykaa_NykaaMan.*;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;


import java.util.Properties;

import FrameWorkNykaa.AppAction;
import FrameWorkNykaa.CustomSoftAssert;
import FrameWorkNykaa.Framework;
import FrameWorkNykaa.LoginSync;
import FrameWorkNykaa.PartialStringList;
import FrameWorkNykaa.Retry;
import FrameWorkNykaa.TestListner;


public class Z_SanitySuite extends AppAction {

    Framework framework = new Framework();
    TestListner testListener = new TestListner();
    String ClassName = this.getClass().getSimpleName();
    String PackageName = this.getClass().getPackage().getName();
    Properties properties = System.getProperties();
    AssertData SanAssertData = new AssertData("Sanity Checklist", "Android", "AutomationResultAndroid", "Elements",
            properties.getProperty("BUILD_URL") + "testngreports/" + PackageName + "/" + ClassName + "/");

    @Test(retryAnalyzer = Retry.class, groups = {"Login1", "sanity", "p1sanity"})
    public void Verify_Email_Already_Associated_With_Facebook_And_Google() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData FbLoginData = frameWork.getData(AccountData.class, "facebook");
        AccountData GplusLoginData = frameWork.getData(AccountData.class, "google");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Login With E-Mail Already Registered with Facebook");
            childTest1.setDescription("This test verifies login with email which is already registered with facebook.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            customSoftAssert.assertTrue(loginPage.LoginWithEmailAlreadyAssociatedWithFB(FbLoginData), "TCID7");
            HeaderBar headerBar = new HeaderBar(driver);
            headerBar.BackButtonClick();
            ExtentTest childTest2 = testListener.startChild("Login With E-Mail Already Registered with Google");
            childTest2.setDescription("This test verifies login with email which is already registered with google.");
            TestListner.extentMap.get().put("child2", childTest2);
            customSoftAssert.assertTrue(loginPage.LoginWithEmailAlreadyAssociatedWithGplus(GplusLoginData), "TCID6");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            driver.quit();

        }

    }

    @Test(retryAnalyzer = Retry.class, groups = {"Login1", "sanity", "p1sanity"})
    public void Verify_User_Is_Login_To_The_Nykaa_With_Facebook_And_Google() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData FbLoginData = frameWork.getData(AccountData.class, "facebook");
        AccountData GplusLogin = frameWork.getData(AccountData.class, "google");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Login with Facebook");
            childTest1.setDescription("This test verifies login with Facebook id.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            String ExpectedName = loginPage.LoginWithFacebook(FbLoginData);
            System.out.println("ExpectedName : " + ExpectedName);
            HeaderBar headerBar = new HeaderBar(driver);
            String FaceBookLoggedInUserName = headerBar.getLoggedInUserName().toLowerCase();
            customSoftAssert.assertTrue(FaceBookLoggedInUserName.contains(ExpectedName), "TCID1");
            ExtentTest childTest2 = testListener.startChild("Check LogOut");
            childTest2.setDescription("This test verifies whether logout is working or not");
            TestListner.extentMap.get().put("child2", childTest2);
            headerBar.Logout();
            ExtentTest childTest3 = testListener.startChild("Login With Google");
            childTest3.setDescription("This test verifies login with google account.");
            TestListner.extentMap.get().put("child3", childTest3);
            String expectedName = loginPage.LoginWithExistingGoogleId(GplusLogin).toLowerCase();
            String LoggedInUser = headerBar.getLoggedInUserName().toLowerCase();
            customSoftAssert.assertTrue(LoggedInUser.contains(expectedName), "TCID2");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            driver.quit();
        }
    }

    @Test(retryAnalyzer = Retry.class, groups = {"Login1", "sanity", "p1sanity"})
    public void Verify_User_Is_Login_And_Register_To_The_Nykaa() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = LoginSync.getInstance().getLogin();
        AccountData newLogin = frameWork.getData(AccountData.class, "newlogin");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Login With Username And Password");
            childTest1.setDescription("This test verifies login with a valid username and password.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            String ExpectedName = loginPage.LoginWithUserNameAndPassword(Login);
            HeaderBar headerBar = new HeaderBar(driver);
            String LoggedInUserName = headerBar.getLoggedInUserName().toLowerCase();
            customSoftAssert.assertTrue(LoggedInUserName.contains(ExpectedName), "TCID3");
            ExtentTest childTest2 = testListener.startChild("Check LogOut");
            childTest2.setDescription("This test verifies whether logout is working or not");
            TestListner.extentMap.get().put("child2", childTest2);
            customSoftAssert.assertTrue(headerBar.Logout(), "TCID5");
            ExtentTest childTest3 = testListener.startChild("Registration on Nykaa");
            childTest3.setDescription("This test verifies for registration on nykaa");
            TestListner.extentMap.get().put("child3", childTest3);
            ExpectedName = loginPage.RegisterNewUser(newLogin);
            LoggedInUserName = headerBar.getLoggedInUserName().toLowerCase();
            customSoftAssert.assertTrue(LoggedInUserName.contains(ExpectedName), "TCID4");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();
        }
    }

    @Test(retryAnalyzer = Retry.class, priority = 1, groups = {"Login", "sanity", "cart", "listing", "p1sanity"})
    public void Verify_User_Add_To_Bag_From_Listing_Page() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData newLogin = frameWork.getData(AccountData.class, "newlogin");
        Header_FooterBarData headerFooterBarData = frameWork.getData(Header_FooterBarData.class, "NykMan_shadenavigationApp");
        Header_FooterBarData headerFooterBarData1 = frameWork.getData(Header_FooterBarData.class, "NykMan_sizenavigationApp");
        Header_FooterBarData headerFooterBarData2 = frameWork.getData(Header_FooterBarData.class, "NykMan_simplenavigationApp");
        CheckoutData checkoutData = frameWork.getData(CheckoutData.class, "simplepay");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Registration on Nykaa");
            childTest1.setDescription("This test does registration on nykaa.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.RegisterNewUser(newLogin);
            ExtentTest childTest2 = testListener.startChild("Add To Bag Shade Product");
            childTest2.setDescription("This test add to bag shade product from listing page.");
            TestListner.extentMap.get().put("child2", childTest2);
            HeaderBar headerbar = new HeaderBar(driver);
            headerbar.navigateToListingPage(headerFooterBarData);
            ListingPage listingPage = new ListingPage(driver);
            ProductDetailData productDetailData = listingPage.addToBagAnyShadeConfigurableProduct();
            CartPage cartPage = new CartPage(driver);
            customSoftAssert.assertTrue(cartPage.isProductPresentInBag(productDetailData), "TCID24");
            headerbar.BackButtonClick();
            // headerbar.BackButton Click();
            ExtentTest childTest3 = testListener.startChild("Add To Bag Size Product");
            childTest3.setDescription("This test add to bag size product from listing page.");
            TestListner.extentMap.get().put("child3", childTest3);
            headerbar.navigateToListingPage(headerFooterBarData1);
            ProductDetailData productDetailData11 = listingPage.addToBagAnySizeConfigurableProduct();
            customSoftAssert.assertTrue(cartPage.isProductPresentInBag(productDetailData11), "TCID25");
            headerbar.BackButtonClick();
            ExtentTest childTest4 = testListener.startChild("Add To Bag And Add To Wishlist Simple Product");
            childTest4.setDescription("This test add to bag  and add to wishlist simple product from listing page.");
            TestListner.extentMap.get().put("child4", childTest4);
            /*HeaderBar headerbar = new HeaderBar(driver);
            ListingPage listingPage = new ListingPage(driver);
            CartPage cartPage = new CartPage(driver);*/
            headerbar.navigateToListingPage(headerFooterBarData2);
            customSoftAssert.assertTrue(listingPage.addToWishlist(), "TCID27");
            customSoftAssert.assertTrue(listingPage.changeTheViewOfProduct(), "TCID28");
            ProductDetailData productDetailData2 = listingPage.addToBagAnySimpleProduct();
            System.out.println("isProductPresentInBag(productDetailData2) : " + cartPage.isProductPresentInBag(productDetailData2));
            customSoftAssert.assertTrue(cartPage.isProductPresentInBag(productDetailData2), "TCID26");
            ExtentTest childTest5 = testListener.startChild("Apply Coupon And Price Calculation");
            childTest5.setDescription("This test applies coupon and then check price calculations.");
            TestListner.extentMap.get().put("child5", childTest5);
            customSoftAssert.assertTrue(cartPage.applyCoupon(checkoutData) && cartPage.checkPriceCalculation(), "TCID33");
            ExtentTest childTest6 = testListener.startChild("Checkout With Netbanking");
            childTest6.setDescription("This test verifies checkout with netbanking.");
            TestListner.extentMap.get().put("child6", childTest6);
            cartPage.checkOut();
            CheckOutPage checkOutPage = new CheckOutPage(driver);
            checkOutPage.fillAddress(checkoutData);
            customSoftAssert.assertTrue(checkOutPage.payViaNetbankingTopFive(checkoutData), "TCID55");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            driver.quit();
        }
    }

    @Test(retryAnalyzer = Retry.class, priority = 1, groups = {"Login", "sanity", "cart", "checkout", "pdp", "p2sanity"})
    public void Verify_User_Add_To_Bag_From_Product_Page() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = LoginSync.getInstance().getLogin();
        Header_FooterBarData headerFooterBarData = frameWork.getData(Header_FooterBarData.class, "NykMan_shadenavigationApp");
        Header_FooterBarData headerFooterBarData1 = frameWork.getData(Header_FooterBarData.class, "NykMan_sizenavigation_Man");
        CheckoutData cardDetails = framework.getData(CheckoutData.class, "validapp");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Login in Nykaa");
            childTest1.setDescription("This test does login with valid username and password.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(Login);
            HeaderBar headerbar = new HeaderBar(driver);
            ExtentTest childTest2 = testListener.startChild("Delete All the Cart Items");
            childTest2.setDescription("This test deletes all the product in the cart.");
            TestListner.extentMap.get().put("child2", childTest2);
            headerbar.clearCart();
            ExtentTest childTest3 = testListener.startChild("Add To bag Shade Product");
            childTest3.setDescription("This test add to bag shade product from product page.");
            TestListner.extentMap.get().put("child3", childTest3);
            headerbar.navigateToListingPage(headerFooterBarData);
            ListingPage listingPage = new ListingPage(driver);
            listingPage.navigateToAnyShadeProductDetailPage();
            ProductPage productPage = new ProductPage(driver);
            ProductDetailData productdetail = productPage.addToBagShadeProductPage();
            CartPage cartPage = new CartPage(driver);
            customSoftAssert.assertTrue(cartPage.isProductPresentInBag(productdetail), "TCID11");
            ExtentTest childTest4 = testListener.startChild("Add To Bag Size Product");
            childTest4.setDescription("This test add to bag size product from product page.");
            TestListner.extentMap.get().put("child4", childTest4);
            Thread.sleep(5000);
            headerbar.BackButtonClick();
            Thread.sleep(2000);
            headerbar.BackButtonClick();
            Thread.sleep(3000);
            headerbar.navigateToListingPage(headerFooterBarData1);
            listingPage.applyFilter("Price", "₹500 - ₹999");
            listingPage.navigateToAnySizeProduct();
            ProductPage productPage1 = new ProductPage(driver);
            ProductDetailData productdetail1 = productPage1.addToBagSizeProductPage();
            customSoftAssert.assertTrue(cartPage.isProductPresentInBag(productdetail1), "TCID12");
            ExtentTest childTest5 = testListener.startChild("Check Shipping Charge");
            childTest5.setDescription("This test checks if shipping is free.");
            TestListner.extentMap.get().put("child5", childTest5);
            cartPage.checkShippingCharge();
            customSoftAssert.assertTrue(cartPage.isShippingFree(), "TCID32");
            ExtentTest childTest6 = testListener.startChild("Checkout With Credit Card");
            childTest6.setDescription("This test verifies checkout with credit card.");
            TestListner.extentMap.get().put("child6", childTest6);
           /* headerbar.openCart();
            cartPage.checkOut();
            CheckOutPage checkOutPage = new CheckOutPage(driver);
            customSoftAssert.assertTrue(checkOutPage.payViaCard(cardDetails),"TCID56");*/
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();
        }
    }

    @Test(priority = 1, groups = {"Login", "sanity", "pdp"})
    public void Verify_Customer_Also_Bought_And_Viewed() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = LoginSync.getInstance().getLogin();
        Header_FooterBarData headerFooterBarData = frameWork.getData(Header_FooterBarData.class, "NykMan_simplenavigationApp");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Login in Nykaa");
            childTest1.setDescription("This test does login with valid username and password.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(Login);
            ExtentTest childTest2 = testListener.startChild("Delete All the Cart Items");
            childTest2.setDescription("This test deletes all the product in the cart.");
            TestListner.extentMap.get().put("child2", childTest2);
            HeaderBar headerbar = new HeaderBar(driver);
            headerbar.clearCart();
            ExtentTest childTest3 = testListener.startChild("Add To bag Simple Product");
            childTest3.setDescription("This test add to bag simple product from product page.");
            TestListner.extentMap.get().put("child3", childTest3);
            headerbar.navigateToListingPage(headerFooterBarData);
            ListingPage listingPage = new ListingPage(driver);
            listingPage.navigateToAnySimpleProduct();
            ProductPage productPage = new ProductPage(driver);
            CartPage cartPage = new CartPage(driver);
            ProductDetailData productdetail = productPage.addToBagSimpleProductPage();
            Thread.sleep(3000);
            customSoftAssert.assertTrue(cartPage.isProductPresentInBag(productdetail), "TCID13");
            ExtentTest childTest4 = testListener.startChild("Add To Bag Customer Also Bought");
            childTest4.setDescription("This test add to bag customer also bought from product page.");
            TestListner.extentMap.get().put("child4", childTest4);
            ProductDetailData productdetail1 = productPage.addToBagCustomerAlsoBought();
            customSoftAssert.assertTrue(cartPage.isProductPresentInBag(productdetail1), "TCID15");
            ExtentTest childTest5 = testListener.startChild("Add To Bag Customer Also Viewed");
            childTest5.setDescription("This test add to bag customer also viewed.");
            TestListner.extentMap.get().put("child5", childTest5);
            ProductDetailData productdetail2 = productPage.addToBagCustomerAlsoViewed();
            customSoftAssert.assertTrue(cartPage.isProductPresentInBag(productdetail2), "TCID14");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();
        }
    }

    @Test(retryAnalyzer = Retry.class, priority = 2, groups = {"Login", "sanity", "cart"})
    public void User_Is_Able_To_Modify_Wishlist() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = frameWork.getData(AccountData.class, "newlogin");
        Header_FooterBarData headerFooterBarData = frameWork.getData(Header_FooterBarData.class, "NykMan_simplenavigationApp");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Registration on Nykaa");
            childTest1.setDescription("This test verifies for registration on nykaa");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.RegisterNewUser(Login);
            ExtentTest childTest2 = testListener.startChild("Add to Wishlist");
            childTest2.setDescription("This test adds the product to wishlist from product page.");
            TestListner.extentMap.get().put("child2", childTest2);
            HeaderBar headerbar = new HeaderBar(driver);
            headerbar.navigateToListingPage(headerFooterBarData);
            ListingPage listingPage = new ListingPage(driver);
            listingPage.navigateToAnySimpleProduct();
            ProductPage productPage = new ProductPage(driver);
            ProductDetailData productdetail1 = productPage.addProductToWishlist();
            headerbar.BackButtonClick();
            Thread.sleep(3000);
            headerbar.BackButtonClick();
            Thread.sleep(3000);
            headerbar.navigateToWishlistPage();
            Thread.sleep(2000);
            customSoftAssert.assertTrue(headerbar.isProductPresentInWishList(productdetail1), "TCID16");
            ExtentTest childTest3 = testListener.startChild("Add To bag From Wishlist");
            childTest3.setDescription("This test add to bag product from wishlist page.");
            TestListner.extentMap.get().put("child3", childTest3);
            ProductDetailData productdetail = listingPage.addToBagFromWishlist();
            Thread.sleep(5000);
            CartPage cartPage = new CartPage(driver);
            headerbar.BackButtonClick();
            headerbar.openCart();
            customSoftAssert.assertTrue(cartPage.isProductPresentInBag(productdetail), "TCID21");
            ExtentTest childTest4 = testListener.startChild("Empty Shopping Bag when products are in wishlist");
            childTest4.setDescription("This test empties the shopping bag when products are in wishlist.");
            TestListner.extentMap.get().put("child4", childTest4);
            headerbar.openCart();
            cartPage.removeAllProduct();
            customSoftAssert.assertTrue(cartPage.isCartEmpty() && cartPage.isWishlistEmptyInCart(), "TCID23");
            ExtentTest childTest5 = testListener.startChild("Remove From Wishlist");
            childTest5.setDescription("This test removes the product from wishlist page.");
            TestListner.extentMap.get().put("child5", childTest5);
            Thread.sleep(5000);
            headerbar.navigateToWishlistPage();
            listingPage.addToBagFromWishlist();
            customSoftAssert.assertTrue(listingPage.removeFromWishlist(), "TCID17");
            ExtentTest childTest6 = testListener.startChild("Empty Shopping Bag when no products in wishlist");
            childTest6.setDescription("This test empties the shopping bag when no products are in wishlist.");
            TestListner.extentMap.get().put("child6", childTest6);
            headerbar.BackButtonClick();
            headerbar.clearCart();
            customSoftAssert.assertTrue(cartPage.isCartEmpty(), "TCID22");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();
        }
    }

    @Test(priority = 3, groups = {"Login", "pdp", "sanity"})
    public void Verify_Try_It_On_And_Notify_Me_And_Reward_Points() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = frameWork.getData(AccountData.class, "newlogin");
        Header_FooterBarData headerFooterBarData = frameWork.getData(Header_FooterBarData.class, "NykMan_shadenavigationApp");
        ProductDetailData productDetailData = frameWork.getData(ProductDetailData.class, "dynamiccombo");
        Header_FooterBarData headerFooterBarData1 = frameWork.getData(Header_FooterBarData.class, "browseByBrands");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Registration on Nykaa");
            childTest1.setDescription("This test verifies for registration on nykaa");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.RegisterNewUser(Login);
            ExtentTest childTest2 = testListener.startChild("Delete All the Cart Items");
            childTest2.setDescription("This test deletes all the product in the cart.");
            TestListner.extentMap.get().put("child2", childTest2);
            HeaderBar headerbar = new HeaderBar(driver);
            headerbar.clearCart();
            /*ExtentTest childTest3 = testListener.startChild("Verify Try It On");
            childTest3.setDescription("This test verifies if try it on is working.");
            TestListner.extentMap.get().put("child3",childTest3);
            headerbar.navigateToListingPage(headerFooterBarData);*/
            ListingPage listingPage = new ListingPage(driver);
           /* listingPage.navigateToDynamicCombo(productDetailData);
            ProductPage productPage = new ProductPage(driver);
            customSoftAssert.assertTrue(productPage.verifyTryitOn(), "TCID19");*/
            //headerbar.BackButtonClick();
            headerbar.BackButtonClick();
            ExtentTest childTest4 = testListener.startChild("Check Notify Me");
            childTest4.setDescription("This test verifies if notify me is working.");
            TestListner.extentMap.get().put("child4", childTest4);
            headerbar.navigateToBrandsPage(headerFooterBarData1);
            listingPage.navigateToOutofStockProduct();
            customSoftAssert.assertTrue(listingPage.isNotifyMeClicked(), "TCID20");
            headerbar.BackButtonClick();
            ExtentTest childTest5 = testListener.startChild("Add To Bag Shade Product");
            childTest5.setDescription("This test add to bag shade product from listing page.");
            TestListner.extentMap.get().put("child5", childTest5);
            headerbar.navigateToListingPage(headerFooterBarData);
            listingPage.addToBagAnyShadeConfigurableProduct();
            ExtentTest childTest6 = testListener.startChild("Apply Rewards Points");
            childTest6.setDescription("This test verifies if user is able to apply rewards point or not.");
            TestListner.extentMap.get().put("child6", childTest6);
            CartPage cartPage = new CartPage(driver);
            customSoftAssert.assertTrue(cartPage.applyRewardPoints(), "TCID34");

          /*  List<ProductDetailData> comboProductDetaillist = productPage.addDynamicCombo();
            CartPage cartPage = new CartPage(driver);
            customSoftAssert.assertTrue(cartPage.isComboProductsPresentInBag(comboProductDetaillist), "TCID18");*/
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();
        }
    }

    @Test(priority = 1, groups = {"Login", "sanity", "cart", "checkout"})
    public void Sorting_And_Order_Value_Less_Than_500_And_Price_Calculation_And_Checkout_With_Saved_Card() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = frameWork.getData(AccountData.class, "savedcardlogin");
        Header_FooterBarData headerFooterBarData = frameWork.getData(Header_FooterBarData.class, "NykMan_TD1");
        Header_FooterBarData headerFooterBarData1 = frameWork.getData(Header_FooterBarData.class, "NykMan_simplenavigationApp");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        CheckoutData checkoutData = frameWork.getData(CheckoutData.class, "valid");
        try {
            ExtentTest childTest1 = testListener.startChild("Login in Nykaa");
            childTest1.setDescription("This test does login with valid username and password.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(Login);
            ExtentTest childTest2 = testListener.startChild("Delete All the Cart Items");
            childTest2.setDescription("This test deletes all the product in the cart.");
            TestListner.extentMap.get().put("child2", childTest2);
            HeaderBar headerbar = new HeaderBar(driver);
            headerbar.clearCart();
            ExtentTest childTest3 = testListener.startChild("Search on Home Page");
            childTest3.setDescription("This test verifies search results on home page.");
            TestListner.extentMap.get().put("child3", childTest3);
            headerbar.search(headerFooterBarData);
            ListingPage listingPage = new ListingPage(driver);
            SearchListingPage searchListingPage = new SearchListingPage(driver);
            PartialStringList SearchedResult = searchListingPage.getSearchedResult();
            customSoftAssert.assertTrue(SearchedResult.contains(headerFooterBarData.get_search_keyword().toLowerCase()), "TCID39");
            ExtentTest childTest4 = testListener.startChild("Check Sorting Functionality");
            childTest4.setDescription("This test verifies if sorting is working or not.");
            TestListner.extentMap.get().put("child4", childTest4);
            headerbar.BackButtonClick();
            headerbar.navigateToListingPage(headerFooterBarData1);
            String sortName = listingPage.applySort("Discount: High to Low");
            customSoftAssert.assertTrue(listingPage.isSortApplied(sortName), "TCID29");
            ExtentTest childTest5 = testListener.startChild("Check Price Filter");
            childTest5.setDescription("This test verifies if price filter of price between ₹0 - ₹499 is working or not.");
            TestListner.extentMap.get().put("child5", childTest5);
            headerbar.BackButtonClick();
            headerbar.navigateToListingPage(headerFooterBarData1);
            String criteria = listingPage.applyFilter("Price", "₹0 - ₹499");
            customSoftAssert.assertTrue(listingPage.isCountOfProductSame() && listingPage.isFilterApplied(criteria), "TCID30");
            ExtentTest childTest6 = testListener.startChild("Check Shipping Price");
            childTest6.setDescription("This test verifies if shipping charge charge is applied or not.");
            TestListner.extentMap.get().put("child6", childTest6);
            listingPage.addToBagAnyProduct();
            CartPage cartPage = new CartPage(driver);
            cartPage.checkShippingCharge();
            customSoftAssert.assertTrue(cartPage.isShippingChargedApplied(), "TCID31");
            ExtentTest childTest7 = testListener.startChild("Check Price Calculation");
            childTest7.setDescription("This test checks price calculation.");
            TestListner.extentMap.get().put("child7", childTest7);
            headerbar.BackButtonClick();
            customSoftAssert.assertTrue(cartPage.checkPriceCalculation(), "TCID68");
            //         CartPage cartPage = new CartPage(driver);
            ExtentTest childTest8 = testListener.startChild("Apply Coupon");
            childTest8.setDescription("This test applies coupon.");
            TestListner.extentMap.get().put("child8", childTest8);
            customSoftAssert.assertTrue(cartPage.applyCoupon(checkoutData), "TCID87");
            ExtentTest childTest9 = testListener.startChild("Checkout with Saved Card");
            childTest9.setDescription("This test check payment via saved card.");
            TestListner.extentMap.get().put("child9", childTest9);
            cartPage.checkOut();
            CheckOutPage checkOutPage = new CheckOutPage(driver);
            customSoftAssert.assertTrue(checkOutPage.payViaSavedCard(checkoutData), "TCID67");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();
        }
    }

    @Test(priority = 3, groups = {"sanity", "menu"})
    public void Verify_Menu_Button_Link_And_Search() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = LoginSync.getInstance().getLogin();
        Header_FooterBarData headerFooterBarData = frameWork.getData(Header_FooterBarData.class, "NykMan_shadenavigationApp");
        Header_FooterBarData header_footerBarData1 = frameWork.getData(Header_FooterBarData.class, "browseByBrands");
        Header_FooterBarData headerFooterBarData2 = frameWork.getData(Header_FooterBarData.class, "NykMan_TD1");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Login in Nykaa");
            childTest1.setDescription("This test does login with valid username and password.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(Login);
            ExtentTest childTest2 = testListener.startChild("Navigate to Home page");
            childTest2.setDescription("This test verifies if home page is opening or not.");
            TestListner.extentMap.get().put("child2", childTest2);
            HeaderBar headerBar = new HeaderBar(driver);
            customSoftAssert.assertTrue(headerBar.navigateToHomePage(), "TCID36");
            ExtentTest childTest3 = testListener.startChild("Navigate to Listing Page");
            childTest3.setDescription("This test verifies if user is navigating to correct listing page.");
            TestListner.extentMap.get().put("child3", childTest3);
            customSoftAssert.assertTrue(headerBar.navigateToListingPage(headerFooterBarData), "TCID35");
            ExtentTest childTest4 = testListener.startChild("Search On Listing Page");
            childTest4.setDescription("This test searches on Listing Page.");
            TestListner.extentMap.get().put("child4", childTest4);
            headerBar.BackButtonClick();
            headerBar.search(headerFooterBarData2);
            SearchListingPage searchListingPage = new SearchListingPage(driver);
            PartialStringList SearchedResult = searchListingPage.getSearchedResult(headerFooterBarData2);
            customSoftAssert.assertTrue(SearchedResult.contains(headerFooterBarData2.get_search_keyword().toLowerCase()), "TCID40");
            ExtentTest childTest5 = testListener.startChild("Navigate to Brands Page");
            childTest5.setDescription("This test navigates to Brand Page.");
            TestListner.extentMap.get().put("child5", childTest5);
            headerBar.BackButtonClick();
            //  headerBar.BackButtonClick();
            customSoftAssert.assertTrue(headerBar.navigateToBrandsPage(header_footerBarData1), "TCID37");
            ExtentTest childTest6 = testListener.startChild("Search on Brands Page");
            childTest6.setDescription("This test searches on brands page.");
            TestListner.extentMap.get().put("child6", childTest6);
            headerBar.BackButtonClick();
            headerBar.search(headerFooterBarData2);
            PartialStringList SearchedResult1 = searchListingPage.getSearchedResult(headerFooterBarData2);
            customSoftAssert.assertTrue(SearchedResult1.contains(headerFooterBarData2.get_search_keyword().toLowerCase()), "TCID41");
            ExtentTest childTest7 = testListener.startChild("Navigate To Offers Page");
            childTest7.setDescription("This test navigates to offers page.");
            TestListner.extentMap.get().put("child7", childTest7);
            headerBar.BackButtonClick();
            // headerBar.BackButtonClick();
            customSoftAssert.assertTrue(headerBar.navigateToOffersPage(), "TCID38");
            ExtentTest childTest8 = testListener.startChild("Navigate To Terms And Conditions Page");
            childTest8.setDescription("This test navigates to terms and conditions page.");
            TestListner.extentMap.get().put("child8", childTest8);
            headerBar.BackButtonClick();
            //           customSoftAssert.assertTrue(headerBar.navigateToStyleDiva(),"TCID42");
            //customSoftAssert.assertTrue(headerBar.navigateToTnC(),"TCID47");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();
        }
    }

    @Test(priority = 3, groups = {"Login", "sanity", "home"})
    public void Verify_Home_Page_Link() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = LoginSync.getInstance().getLogin();
        Header_FooterBarData headerFooterBarData = frameWork.getData(Header_FooterBarData.class, "NykMan_shadenavigationApp");
        Header_FooterBarData headerFooterBarData1 = frameWork.getData(Header_FooterBarData.class, "NykMan_simplenavigationApp");
        Header_FooterBarData headerFooterBarData2 = frameWork.getData(Header_FooterBarData.class, "NykMan_sizenavigation_Man");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Login in Nykaa");
            childTest1.setDescription("This test does login with valid username and password.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(Login);
            ExtentTest childTest2 = testListener.startChild("Check Search, Cart, Menu Icons Are Present");
            childTest2.setDescription("This test verifies if search icon, cart icon, and menu button is present or not.");
            TestListner.extentMap.get().put("child2", childTest2);
            HeaderBar headerbar = new HeaderBar(driver);
            customSoftAssert.assertTrue(headerbar.isActionBarPresent() && headerbar.isCartIconPresent() && headerbar.isMenuButtonPresent() && headerbar.isSearchBarPresesnt(), "TCID43");
            ExtentTest childTest3 = testListener.startChild("Check Account Section");
            childTest3.setDescription("This test verifies if account section is opening.");
            TestListner.extentMap.get().put("child3", childTest3);
            customSoftAssert.assertTrue(headerbar.isAccountSectionPresent(), "TCID46");
            //customSoftAssert.assertTrue(headerbar.isHelpCenterOpened(),"TCID48");
            ExtentTest childTest4 = testListener.startChild("Check Recently Viewed Widget And USP");
            childTest4.setDescription("This test check presence of recently viewed widget and USP.");
            TestListner.extentMap.get().put("child4", childTest4);
            headerbar.navigateToListingPage(headerFooterBarData);
            ListingPage listingPage = new ListingPage(driver);
            listingPage.navigateToAnyShadeProductDetailPage();
            headerbar.BackButtonClick();
            headerbar.BackButtonClick();
            headerbar.navigateToListingPage(headerFooterBarData1);
            listingPage.navigateToAnySimpleProduct();
            headerbar.BackButtonClick();
            headerbar.BackButtonClick();
            headerbar.navigateToListingPage(headerFooterBarData2);
            listingPage.navigateToAnySizeProduct();
            headerbar.BackButtonClick();
            headerbar.BackButtonClick();
            HomePage homePage = new HomePage(driver);
            //customSoftAssert.assertTrue(homePage.isRecentlyViewedWidgetPresent(),"TCID45");
            //customSoftAssert.assertTrue(homePage.isUSPPresent(),"TCID44");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();
        }
    }

    @Test(priority = 3, groups = {"sanity", "Local"})
    public void Verify_Landing_Pages() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = frameWork.getData(AccountData.class, "newlogin");
        Header_FooterBarData headerFooterBarData = frameWork.getData(Header_FooterBarData.class, "NykMan_shadenavigationApp");
        Header_FooterBarData headerFooterBarData1 = frameWork.getData(Header_FooterBarData.class, "browseByBrands");
        Header_FooterBarData headerFooterBarData2 = frameWork.getData(Header_FooterBarData.class, "NykMan_simplenavigationApp");
        Header_FooterBarData headerFooterBarData3 = frameWork.getData(Header_FooterBarData.class, "NykMan_sizenavigation_Man");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Registration on Nykaa");
            childTest1.setDescription("This test verifies for registration on nykaa.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.RegisterNewUser(Login);
            HeaderBar headerBar = new HeaderBar(driver);
            ExtentTest childTest2 = testListener.startChild("Navigate to Brands Page");
            childTest2.setDescription("This test navigates to brand page.");
            TestListner.extentMap.get().put("child2", childTest2);
            customSoftAssert.assertTrue(headerBar.navigateToBrandsPage(headerFooterBarData1), "TCID49");
            ExtentTest childTest4 = testListener.startChild("Navigate to Listing Page");
            childTest4.setDescription("This test navigates to listing page.");
            TestListner.extentMap.get().put("child4", childTest4);
            headerBar.BackButtonClick();
            customSoftAssert.assertTrue(headerBar.navigateToListingPage(headerFooterBarData), "TCID50");
            ExtentTest childTest5 = testListener.startChild("Landing Page From Cart Page");
            childTest5.setDescription("This test verifies landing page from cart page.");
            TestListner.extentMap.get().put("child5", childTest5);
            ListingPage listingPage = new ListingPage(driver);
            listingPage.addToBagAnyShadeConfigurableProduct();
            CartPage cartPage = new CartPage(driver);
            //  ProductDetailData productDetailData = cartPage.goToProductDetailPage();
            ProductPage productPage = new ProductPage(driver);
            // customSoftAssert.assertTrue(productPage.isProductDetailedPageOpened(productDetailData),"TCID51");
            ExtentTest childTest6 = testListener.startChild("Landing Page from Wishlist Page");
            childTest6.setDescription("This test verifies landing page from wishlist page.");
            TestListner.extentMap.get().put("child6", childTest6);
            // headerBar.BackButtonClick();
            productPage.addProductToWishlist();
            headerBar.BackButtonClick();
            //headerBar.BackButtonClick();
            //  headerBar.BackButtonClick();
            headerBar.navigateToWishlistPage();
            WishListPage wishListPage = new WishListPage(driver);
            ProductDetailData productDetailData1 = wishListPage.goToProductDetailPageFromWIshList();
            customSoftAssert.assertTrue(productPage.isProductDetailedPageOpened(productDetailData1), "TCID52");
            //headerBar.pressBackButtonUntilHomePage();
            headerBar.BackButtonClick();
            headerBar.BackButtonClick();
            ExtentTest childTest7 = testListener.startChild("Check Search, Cart, Menu Icons Are Present");
            childTest7.setDescription("This test verifies if search icon, cart icon, and menu button is present or not.");
            TestListner.extentMap.get().put("child7", childTest7);
            customSoftAssert.assertTrue(headerBar.isActionBarPresent() && headerBar.isCartIconPresent() && headerBar.isMenuButtonPresent() && headerBar.isSearchBarPresesnt(), "TCID43");
            ExtentTest childTest8 = testListener.startChild("Check Account Section And Help Center");
            childTest8.setDescription("This test verifies if account section and help center is opening.");
            TestListner.extentMap.get().put("child8", childTest8);
            customSoftAssert.assertTrue(headerBar.isAccountSectionPresent(), "TCID46");
            // customSoftAssert.assertTrue(headerBar.isHelpCenterOpened(),"TCID48");
           /* ExtentTest childTest9 = testListener.startChild("Check Recently Viewed Widget And USP");
            childTest9.setDescription("This test check presence of recently viewed widget and USP.");
            TestListner.extentMap.get().put("child9",childTest9);
            headerBar.navigateToListingPage(headerFooterBarData);
            listingPage.navigateToAnyShadeProductDetailPage();
            headerBar.BackButtonClick();
            headerBar.BackButtonClick();
            headerBar.navigateToListingPage(headerFooterBarData1);
            listingPage.navigateToAnySimpleProduct();
            headerBar.BackButtonClick();
            headerBar.BackButtonClick();
            headerBar.navigateToListingPage(headerFooterBarData3);
            listingPage.navigateToAnySizeProduct();
            headerBar.BackButtonClick();
            headerBar.BackButtonClick();
            HomePage homePage = new HomePage(driver);*/
            //customSoftAssert.assertTrue(homePage.isRecentlyViewedWidgetPresent(),"TCID45");
            //customSoftAssert.assertTrue(homePage.isUSPPresent(),"TCID44");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();
        }
    }

    @Test(priority = 3, groups = {"sanity", "cart", "pdp", "orders"})
    public void Verify_Trending_Search_And_Order_Section() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = LoginSync.getInstance().getLogin();
        Header_FooterBarData headerFooterBarData = frameWork.getData(Header_FooterBarData.class, "NykMan_shadenavigationApp");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Login in Nykaa");
            childTest1.setDescription("This test does login with valid username and password.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(Login);
            ExtentTest childTest2 = testListener.startChild("My Order Section");
            childTest2.setDescription("This test verifies my order section, order summary.");
            TestListner.extentMap.get().put("child2", childTest2);
            HeaderBar headerBar = new HeaderBar(driver);
            headerBar.navigateToMyOrders();
            customSoftAssert.assertTrue(headerBar.isOrdersListed(), "TCID63");
            MyOrdersPage myOrdersPage = new MyOrdersPage(driver);
            myOrdersPage.goToOrderDetailsPage();
            customSoftAssert.assertTrue(myOrdersPage.isOrderDetailedPageOpened(), "TCID64");
            customSoftAssert.assertTrue(myOrdersPage.goToOrderSummarySection(), "TCID69");
            headerBar.BackButtonClick();
            headerBar.BackButtonClick();
            ExtentTest childTest3 = testListener.startChild("Delete All the Cart Items");
            childTest3.setDescription("This test deletes all the product in the cart.");
            TestListner.extentMap.get().put("child3", childTest3);
            headerBar.clearCart();
            ExtentTest childTest4 = testListener.startChild("Trending Search");
            childTest4.setDescription("This test searches the trending items on nykaa.");
            TestListner.extentMap.get().put("child4", childTest4);
            // String searchedList = headerBar.searchTrendingByCategories();
            ListingPage listingPage = new ListingPage(driver);
            // customSoftAssert.assertTrue(listingPage.isTopCategorySearched(searchedList),"TCID53");
            ExtentTest childTest5 = testListener.startChild("Check Share Functionality");
            childTest5.setDescription("This test verifies share functionality of product.");
            TestListner.extentMap.get().put("child5", childTest5);
            //    headerBar.BackButtonClick();
            headerBar.navigateToListingPage(headerFooterBarData);
            listingPage.applyFilter("Price", "₹0 - ₹499");
            listingPage.applySort("Price: High to Low");
            listingPage.navigateToAnyShadeProductDetailPage();
            ProductPage productPage = new ProductPage(driver);
            customSoftAssert.assertTrue(productPage.checkShareFunctionality(), "TCID60");
            ExtentTest childTest6 = testListener.startChild("Check Spend Now Feature");
            childTest6.setDescription("This test check spend now feature.");
            TestListner.extentMap.get().put("child6", childTest6);
            productPage.addToBagSimpleProductPage();
            CartPage cartPage = new CartPage(driver);
            //customSoftAssert.assertTrue(cartPage.isSpendMoreTextVisible(),"TCID62");
            ExtentTest childTest7 = testListener.startChild("Delete First Product");
            childTest7.setDescription("This test delete the product in the cart.");
            TestListner.extentMap.get().put("child7", childTest7);
            ProductDetailData productDetailData = cartPage.deleteFirstProduct();
            //ProductDetailData productDetailData = cartPage.deleteFirstProductFromCart();
            customSoftAssert.assertTrue(!cartPage.isProductPresentInBag(productDetailData), "TCID61");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();
        }
    }

    @Test(retryAnalyzer = Retry.class, priority = 1, groups = {"sanity", "checkout", "sanity_Live"})
    public void Verify_Checkout_With_Different_Methods() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData accountData = LoginSync.getInstance().getLogin();
        Header_FooterBarData navigateToShade = framework.getData(Header_FooterBarData.class, "NykMan_shadenavigationApp");
        CheckoutData checkoutData = frameWork.getData(CheckoutData.class, "UPI");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Login in Nykaa");
            childTest1.setDescription("This test does login with valid username and password.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(accountData);
            ExtentTest childTest2 = testListener.startChild("Delete All the Cart Items");
            childTest2.setDescription("This test deletes all the product in the cart.");
            TestListner.extentMap.get().put("child2", childTest2);
            HeaderBar headerBar = new HeaderBar(driver);
            headerBar.clearCart();
            ExtentTest childTest3 = testListener.startChild("Add To bag shade product");
            childTest3.setDescription("This test add to bag a shade product.");
            TestListner.extentMap.get().put("child3", childTest3);
            headerBar.navigateToListingPage(navigateToShade);
            ListingPage listingPage = new ListingPage(driver);
            listingPage.applyFilter("Price", "₹0 - ₹499");
            listingPage.addToBagAnyShadeConfigurableProduct();
            ExtentTest childTest4 = testListener.startChild("Checkout with UPI Payment");
            childTest4.setDescription("This test verifies payment via UPI.");
            TestListner.extentMap.get().put("child4", childTest4);
            CartPage cartPage = new CartPage(driver);
            headerBar.openCart();
            cartPage.checkOut();
            CheckOutPage checkOutPage = new CheckOutPage(driver);
            /*customSoftAssert.assertTrue(checkOutPage.payViaWalletsMobikwik(),"TCID55");
            driver.pressKeyCode(AndroidKeyCode.BACK);*/
            customSoftAssert.assertTrue(checkOutPage.payViaUPI(checkoutData), "TCID86");
            driver.pressKeyCode(AndroidKeyCode.BACK);
            ExtentTest childTest5 = testListener.startChild("Checkout with NetBanking");
            childTest5.setDescription("This test verifies payment via netbanking.");
            TestListner.extentMap.get().put("child5", childTest5);
            customSoftAssert.assertTrue(checkOutPage.payViaNetbankingTopFive(checkoutData), "TCID54");
            driver.pressKeyCode(AndroidKeyCode.BACK);
            ExtentTest childTest6 = testListener.startChild("Checkout with Netbanking Payment via DropDown");
            childTest6.setDescription("This test verifies payment via Netbanking from Drop Down.");
            TestListner.extentMap.get().put("child6", childTest6);
            customSoftAssert.assertTrue(checkOutPage.payViaNetBankingFromDropDown(checkoutData), "TCID88");
            headerBar.BackButtonClick();
            ExtentTest childTest7 = testListener.startChild("Checkout with COD");
            childTest7.setDescription("This test check payment via COD and order creation page.");
            TestListner.extentMap.get().put("child7", childTest7);
            customSoftAssert.assertTrue(checkOutPage.payViaCOD(checkoutData), "TCID57");
            customSoftAssert.assertTrue(checkOutPage.isOrderCreated(), "TCID58");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(accountData);
            driver.quit();
        }
    }

    @Test(priority = 3, groups = {"sanity", "rating", "pdp"})
    public void Verify_Rating_And_Review_And_Subscription_And_Add_Edit_Address() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = frameWork.getData(AccountData.class, "newlogin");
        Header_FooterBarData header_footerBarData = frameWork.getData(Header_FooterBarData.class, "NykMan_shadenavigationApp");
        Header_FooterBarData header_footerBarData1 = frameWork.getData(Header_FooterBarData.class, "subscription");
        ProductDetailData productDetailData = frameWork.getData(ProductDetailData.class, "validreview");
        CheckoutData checkoutData = frameWork.getData(CheckoutData.class, "valid");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Registration on Nykaa");
            childTest1.setDescription("This test verifies for registration on nykaa.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.RegisterNewUser(Login);
            ExtentTest childTest2 = testListener.startChild("Add New Address");
            childTest2.setDescription("This test adds a new address.");
            TestListner.extentMap.get().put("child2", childTest2);
            MoreSection moreSection = new MoreSection(driver);
            Thread.sleep(2000);
            moreSection.MyAccountSection();
            moreSection.NavigateToMyAddresses();
            String telephone = moreSection.fillNewAddress(checkoutData);
            customSoftAssert.assertTrue(moreSection.isAddressAddedorEdited(telephone), "TCID65");
            ExtentTest childTest3 = testListener.startChild("Edit Address");
            childTest3.setDescription("This test edits the address.");
            TestListner.extentMap.get().put("child3", childTest3);
            String telephone1 = moreSection.editSavedAddress();
            customSoftAssert.assertTrue(moreSection.isAddressAddedorEdited(telephone1), "TCID66");
            HeaderBar headerBar = new HeaderBar(driver);
            headerBar.BackButtonClick();
            headerBar.BackButtonClick();
            ExtentTest childTest4 = testListener.startChild("Give Rating and reviews for product");
            childTest4.setDescription("This test gives rating and reviews to the product.");
            TestListner.extentMap.get().put("child4", childTest4);
            Thread.sleep(3000);
            headerBar.navigateToListingPage(header_footerBarData);
            ListingPage listingPage = new ListingPage(driver);
            listingPage.navigateToAnyShadeProductDetailPage();
            ProductPage productPage = new ProductPage(driver);
            customSoftAssert.assertTrue(productPage.verifyRating(productDetailData), "TCID81");
            ExtentTest childTest5 = testListener.startChild("Like reviews");
            childTest5.setDescription("This test likes the reviews.");
            TestListner.extentMap.get().put("child5", childTest5);
            customSoftAssert.assertTrue(productPage.productReviewLike(), "TCID82");
            headerBar.BackButtonClick();
            headerBar.BackButtonClick();
            //headerBar.BackButtonClick();
            ExtentTest childTest6 = testListener.startChild("Subscription of product");
            childTest6.setDescription("This test does subscription on the product.");
            TestListner.extentMap.get().put("child6", childTest6);
            headerBar.navigateToBrandsPage(header_footerBarData1);
            listingPage.navigateToAnySimpleProduct();
            // productPage.doSubscription(checkoutData);
            //  customSoftAssert.assertTrue(productPage.isSubscriptionDone(),"TCID83");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();
        }
    }

    @Test(priority = 3, groups = {"sanity", "listing", "filters"})
    public void Verify_Filters_With_Brands_And_Discount() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = LoginSync.getInstance().getLogin();
        Header_FooterBarData header_footerBarData = frameWork.getData(Header_FooterBarData.class, "NykMan_sizenavigation_Man");
        Header_FooterBarData header_footerBarData1 = frameWork.getData(Header_FooterBarData.class, "NykMan_simplenavigationApp");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Login in Nykaa");
            childTest1.setDescription("This test does login with valid username and password.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(Login);
            HeaderBar headerBar = new HeaderBar(driver);
            ExtentTest childTest2 = testListener.startChild("Check Brand Filter");
            childTest2.setDescription("This test verifies brand filter.");
            TestListner.extentMap.get().put("child2", childTest2);
            headerBar.navigateToListingPage(header_footerBarData);
            ListingPage listingPage = new ListingPage(driver);
            String criteria = listingPage.applyFilter("Brand");
            customSoftAssert.assertTrue(listingPage.isCountOfProductSame() && listingPage.isFilterApplied(criteria), "TCID90");
            ExtentTest childTest3 = testListener.startChild("Check Discount Filter");
            childTest3.setDescription("This test verifies discount filter.");
            TestListner.extentMap.get().put("child3", childTest3);
            criteria = listingPage.applyFilter("Discount");
            customSoftAssert.assertTrue(listingPage.isCountOfProductSame() && listingPage.isFilterApplied(criteria), "TCID91");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();
        }
    }


    @Test(groups = {"Login1" , "sanity"})
    public void Verify_User_Is_Login_To_The_Nykaa_With_Google() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData GplusLogin = frameWork.getData(AccountData.class, "google");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Login With Google");
            childTest1.setDescription("This test verifies login with google account.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            String expectedName = loginPage.LoginWithExistingGoogleId(GplusLogin).toLowerCase();
            HeaderBar headerBar = new HeaderBar(driver);
            String LoggedInUser = headerBar.getLoggedInUserName().toLowerCase();
            customSoftAssert.assertTrue(LoggedInUser.contains(expectedName), "TCID2");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            driver.quit();
        }
    }

    @Test(retryAnalyzer = Retry.class, groups = {"Login1"})
    public void Verify_User_Is_Able_To_Register() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData newLogin = frameWork.getData(AccountData.class, "newlogin");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Registration on Nykaa");
            childTest1.setDescription("This test verifies for registration on nykaa");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            String ExpectedName = loginPage.RegisterNewUser(newLogin);
            HeaderBar headerBar = new HeaderBar(driver);
            String LoggedInUserName = headerBar.getLoggedInUserName().toLowerCase();
            customSoftAssert.assertTrue(LoggedInUserName.contains(ExpectedName), "TCID4");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            driver.quit();
        }
    }

    @Test(priority = 2, groups = {"Login"})
    public void Notify_Me_For_All_Type_of_Product() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = LoginSync.getInstance().getLogin();
        Header_FooterBarData headerFooterBarData = frameWork.getData(Header_FooterBarData.class, "browseByBrands");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Login in Nykaa");
            childTest1.setDescription("This test does login with valid username and password.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(Login);
            ExtentTest childTest2 = testListener.startChild("Check Notify Me");
            childTest2.setDescription("This test verifies if notify me is working.");
            TestListner.extentMap.get().put("child2", childTest2);
            HeaderBar headerbar = new HeaderBar(driver);
            headerbar.navigateToBrandsPage(headerFooterBarData);
            ListingPage listingPage = new ListingPage(driver);
            listingPage.navigateToOutofStockProduct();
            customSoftAssert.assertTrue(listingPage.isNotifyMeClicked(), "TCID20");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();
        }
    }

    @Test(priority = 2, groups = {"Login", "cart"})
    public void Apply_Coupon_And_Apply_Reward_points() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = frameWork.getData(AccountData.class, "newlogin");
        Header_FooterBarData headerFooterBarData = frameWork.getData(Header_FooterBarData.class, "NykMan_shadenavigationApp");
        CheckoutData checkoutData = frameWork.getData(CheckoutData.class, "simplepay");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Registration on Nykaa");
            childTest1.setDescription("This test verifies for registration on nykaa");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.RegisterNewUser(Login);
            ExtentTest childTest2 = testListener.startChild("Add To Bag Shade Product");
            childTest2.setDescription("This test add to bag shade product from listing page.");
            TestListner.extentMap.get().put("child2", childTest2);
            HeaderBar headerbar = new HeaderBar(driver);
            headerbar.navigateToListingPage(headerFooterBarData);
            ListingPage listingPage = new ListingPage(driver);
            listingPage.addToBagAnyShadeConfigurableProduct();
            ExtentTest childTest3 = testListener.startChild("Apply Rewards Points");
            childTest3.setDescription("This test verifies if user is able to apply rewards point or not.");
            TestListner.extentMap.get().put("child3", childTest3);
            CartPage cartPage = new CartPage(driver);
            customSoftAssert.assertTrue(cartPage.applyRewardPoints(), "TCID34");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();
        }
    }

    @Test(priority = 2, groups = {"orders"})
    public void Verify_My_Order_Section() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = LoginSync.getInstance().getLogin();
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Login in Nykaa");
            childTest1.setDescription("This test does login with valid username and password.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(Login);
            ExtentTest childTest2 = testListener.startChild("My Order Section");
            childTest2.setDescription("This test verifies my order section, order summary.");
            TestListner.extentMap.get().put("child2", childTest2);
            HeaderBar headerBar = new HeaderBar(driver);
            headerBar.navigateToMyOrders();
            customSoftAssert.assertTrue(headerBar.isOrdersListed(), "TCID63");
            MyOrdersPage myOrdersPage = new MyOrdersPage(driver);
            myOrdersPage.goToOrderDetailsPage();
            customSoftAssert.assertTrue(myOrdersPage.isOrderDetailedPageOpened(), "TCID64");
            customSoftAssert.assertTrue(myOrdersPage.goToOrderSummarySection(), "TCID69");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();
        }
    }

    @Test(priority = 2, groups = {"Login"})
    public void Verify_Add_Edit_Address() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = frameWork.getData(AccountData.class, "newlogin");
        CheckoutData checkoutData = frameWork.getData(CheckoutData.class, "simplepay");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Registration on Nykaa");
            childTest1.setDescription("This test verifies for registration on nykaa.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.RegisterNewUser(Login);
            ExtentTest childTest2 = testListener.startChild("Add New Address");
            childTest2.setDescription("This test adds a new address.");
            TestListner.extentMap.get().put("child2", childTest2);
            MoreSection moreSection = new MoreSection(driver);
            moreSection.MyAccountSection();
            moreSection.NavigateToMyAddresses();
            String telephone = moreSection.fillNewAddress(checkoutData);
            customSoftAssert.assertTrue(moreSection.isAddressAddedorEdited(telephone), "TCID65");
            ExtentTest childTest3 = testListener.startChild("Edit Address");
            childTest3.setDescription("This test edits the address.");
            TestListner.extentMap.get().put("child3", childTest3);
            String telephone1 = moreSection.editSavedAddress();
            customSoftAssert.assertTrue(moreSection.isAddressAddedorEdited(telephone1), "TCID66");

        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();
        }
    }

    @Test(retryAnalyzer = Retry.class, priority = 1, groups = {})
    public void Verify_Checkout_With_Saved_Card() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData savedcard = frameWork.getData(AccountData.class, "savedcardlogin");
        Header_FooterBarData header_footerBarData = frameWork.getData(Header_FooterBarData.class, "simplenavigationApp");
        CheckoutData checkoutData = frameWork.getData(CheckoutData.class, "valid");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Login in Nykaa");
            childTest1.setDescription("This test does login with valid username and password.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(savedcard);
            ExtentTest childTest2 = testListener.startChild("Delete All the Cart Items");
            childTest2.setDescription("This test deletes all the product in the cart.");
            TestListner.extentMap.get().put("child2", childTest2);
            HeaderBar headerBar = new HeaderBar(driver);
            headerBar.clearCart();
            ExtentTest childTest3 = testListener.startChild("Add To Bag Simple product");
            childTest3.setDescription("This test add to bag a simple product.");
            TestListner.extentMap.get().put("child3", childTest3);
            headerBar.navigateToListingPage(header_footerBarData);
            ListingPage listingPage = new ListingPage(driver);
            listingPage.addToBagAnySimpleProduct();
            CartPage cartPage = new CartPage(driver);
            ExtentTest childTest4 = testListener.startChild("Apply Coupon");
            childTest4.setDescription("This test applies coupon.");
            TestListner.extentMap.get().put("child4", childTest4);
            customSoftAssert.assertTrue(cartPage.applyCoupon(checkoutData), "TCID87");
            ExtentTest childTest5 = testListener.startChild("Checkout with Saved Card");
            childTest5.setDescription("This test check payment via saved card.");
            TestListner.extentMap.get().put("child5", childTest5);
            cartPage.checkOut();
            CheckOutPage checkOutPage = new CheckOutPage(driver);
            customSoftAssert.assertTrue(checkOutPage.payViaSavedCard(checkoutData), "TCID67");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            driver.quit();
        }
    }

    @Test(priority = 2, groups = {"checkout", "cart", "testRun"})
    public void Price_Calculation() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = LoginSync.getInstance().getLogin();
        Header_FooterBarData header_footerBarData = frameWork.getData(Header_FooterBarData.class, "NykMan_shadenavigationApp");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Login in Nykaa");
            childTest1.setDescription("This test does login with valid username and password.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(Login);
            ExtentTest childTest2 = testListener.startChild("Delete All the Cart Items");
            childTest2.setDescription("This test deletes all the product in the cart.");
            TestListner.extentMap.get().put("child2", childTest2);
            HeaderBar headerBar = new HeaderBar(driver);
            headerBar.clearCart();
            ExtentTest childTest3 = testListener.startChild("Check Price Calculation");
            childTest3.setDescription("This test checks price calculation.");
            TestListner.extentMap.get().put("child3", childTest3);
            headerBar.navigateToListingPage(header_footerBarData);
            ListingPage listingPage = new ListingPage(driver);
            listingPage.addToBagAnyShadeConfigurableProduct();
            CartPage cartPage = new CartPage(driver);
            customSoftAssert.assertTrue(cartPage.checkPriceCalculation(), "TCID68");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();
        }
    }

    @Test(priority = 3, groups = {"subscription", "pdp"})
    public void Verify_Subscription_Of_Product() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = frameWork.getData(AccountData.class, "newlogin");
        Header_FooterBarData header_footerBarData = frameWork.getData(Header_FooterBarData.class, "subscription");
        CheckoutData checkoutData = frameWork.getData(CheckoutData.class, "valid");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Registration on Nykaa");
            childTest1.setDescription("This test verifies for registration on nykaa.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.RegisterNewUser(Login);
            ExtentTest childTest2 = testListener.startChild("Subscription on production");
            childTest2.setDescription("This test does subscription on the product.");
            TestListner.extentMap.get().put("child2", childTest2);
            HeaderBar headerBar = new HeaderBar(driver);
            headerBar.navigateToBrandsPage(header_footerBarData);
            ListingPage listingPage = new ListingPage(driver);
            listingPage.navigateToAnySimpleProduct();
            ProductPage productPage = new ProductPage(driver);
            //  productPage.doSubscription(checkoutData);
            // customSoftAssert.assertTrue(productPage.isSubscriptionDone(),"TCID83");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();
        }
    }

    @Test(priority = 1, groups = {"checkout"})
    public void Verfiy_Checkout_With_NetBanking_Dropdown() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = LoginSync.getInstance().getLogin();
        Header_FooterBarData header_footerBarData = frameWork.getData(Header_FooterBarData.class, "sizenavigationApp");
        CheckoutData checkoutData = frameWork.getData(CheckoutData.class, "valid");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            ExtentTest childTest1 = testListener.startChild("Login in Nykaa");
            childTest1.setDescription("This test does login with valid username and password.");
            TestListner.extentMap.get().put("child1", childTest1);
            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(Login);
            ExtentTest childTest2 = testListener.startChild("Delete All the Cart Items");
            childTest2.setDescription("This test deletes all the product in the cart.");
            TestListner.extentMap.get().put("child2", childTest2);
            HeaderBar headerBar = new HeaderBar(driver);
            headerBar.clearCart();
            ExtentTest childTest3 = testListener.startChild("Add To Bag size product");
            childTest3.setDescription("This test add to bag size product.");
            TestListner.extentMap.get().put("child3", childTest3);
            headerBar.navigateToListingPage(header_footerBarData);
            ListingPage listingPage = new ListingPage(driver);
            listingPage.addToBagAnySizeConfigurableProduct();
            ExtentTest childTest4 = testListener.startChild("Checkout with Netbanking Payment");
            childTest4.setDescription("This test verifies payment via Netbanking.");
            TestListner.extentMap.get().put("child4", childTest4);
            CartPage cartPage = new CartPage(driver);
            cartPage.checkOut();
            CheckOutPage checkOutPage = new CheckOutPage(driver);
            customSoftAssert.assertTrue(checkOutPage.payViaNetBankingFromDropDown(checkoutData), "TCID88");
        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();
        }
    }

/*
    @Test(retryAnalyzer = Retry.class, priority = 2, groups = {"sanity", "1Local"})
    public void Guest_user() throws Throwable {
        Framework frameWork = new Framework();
        AndroidDriver driver = frameWork.getAndroidDriver();
        AccountData Login = frameWork.getData(AccountData.class , "loginset");
        Header_FooterBarData header_footerBarData = frameWork.getData(Header_FooterBarData.class , "shadenavigationApp");
        Header_FooterBarData subscription = frameWork.getData(Header_FooterBarData.class, "subscription");
        ProductDetailData productDetailData = frameWork.getData(ProductDetailData.class, "validreview");
        CheckoutData checkoutData = frameWork.getData(CheckoutData.class,"valid");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);
        try {
            LoginPage loginPage = new LoginPage(driver);
            ListingPage listingPage = new ListingPage(driver);
            CartPage cartPage = new CartPage(driver);
            CheckOutPage checkOutPage = new CheckOutPage(driver);
            ProductPage productPage = new ProductPage(driver);
            HeaderBar headerBar = new HeaderBar(driver);
            loginPage.LoginWithGuestUser();
            customSoftAssert.assertTrue(headerBar.isMyAccountGuestLoginPresent(Login),"TCID70");
            headerBar.Logout();
            loginPage.LoginWithGuestUser();
            customSoftAssert.assertTrue(headerBar.isGuestReferPresent(Login), "TCID71");
            driver.pressKeyCode(AndroidKeyCode.BACK);
            headerBar.Logout();
            loginPage.LoginWithGuestUser();
            customSoftAssert.assertTrue(headerBar.isGuestTrackOrderPresent(Login), "TCID72");
            driver.pressKeyCode(AndroidKeyCode.BACK);
            headerBar.Logout();
            loginPage.LoginWithGuestUser();
            customSoftAssert.assertTrue(headerBar.isGuestSubscriptionPresent(Login), "TCID73");
            driver.pressKeyCode(AndroidKeyCode.BACK);
            headerBar.Logout();
            loginPage.LoginWithGuestUser();
            customSoftAssert.assertTrue(headerBar.isGuestWishlistPresent(Login), "TCID74");
            headerBar.Logout();
            loginPage.LoginWithGuestUser();
            customSoftAssert.assertTrue(headerBar.isGuestHelpCenterPresent(Login), "TCID75");
            driver.pressKeyCode(AndroidKeyCode.BACK);
            headerBar.Logout();
            loginPage.LoginWithGuestUser();
            headerBar.navigateToListingPage(header_footerBarData);
            listingPage.addToBagAnyShadeConfigurableProduct();
            headerBar.openCart();
            cartPage.checkOut();
            customSoftAssert.assertTrue(headerBar.isGuestLoginPopUp(), "TCID76");
            loginPage.LoginWithGuestUser();
       //     cartPage.CartLogin();
            loginPage.LoginWithUserNameAndPassword(Login);
       //     cartPage.CartCODValue();
            cartPage.checkOut();
            cartPage.checkOut();
            customSoftAssert.assertTrue(checkOutPage.payViaCOD(checkoutData), "TCID77");
            driver.pressKeyCode(AndroidKeyCode.BACK);
            headerBar.Logout();
            loginPage.LoginWithGuestUser();
            headerBar.navigateToListingPage(header_footerBarData);
            listingPage.navigateToAnyShadeProductDetailPage();
 //           productPage.GuestRatingButton(productDetailData);
            loginPage.LoginWithUserNameAndPassword(Login);
            customSoftAssert.assertTrue(productPage.productRating(productDetailData),"TCID78");
            driver.pressKeyCode(AndroidKeyCode.BACK);
            driver.pressKeyCode(AndroidKeyCode.BACK);
            headerBar.Logout();
            loginPage.LoginWithGuestUser();
            headerBar.navigateToListingPage(header_footerBarData);
            listingPage.navigateToAnyShadeProductDetailPage();
 //           customSoftAssert.assertTrue(productPage.GuestReviewLike(productDetailData,Login), "TCID79");
            driver.pressKeyCode(AndroidKeyCode.BACK);
            driver.pressKeyCode(AndroidKeyCode.BACK);
            driver.pressKeyCode(AndroidKeyCode.BACK);
            headerBar.Logout();
            loginPage.LoginWithGuestUser();
            headerBar.navigateToBrandsPage(subscription);
            listingPage.navigateToAnySizeProduct();
      //      customSoftAssert.assertTrue(productPage.GuestSubscribe(productDetailData,Login,checkoutData), "TCID80");
        } catch (Throwable e) {
            //	framework.captureScreenshot(browser);
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {
            LoginSync.getInstance().clearLoginLock(Login);
            driver.quit();


        }
    }
*/
}