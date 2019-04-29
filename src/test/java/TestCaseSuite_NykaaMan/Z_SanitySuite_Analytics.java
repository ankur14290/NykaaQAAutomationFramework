package TestCaseSuite_NykaaMan;

//import AppFrameWork_NykaaMan.*;
import DataNykaa.AccountData;
import DataNykaa.AnalyticsDataApp;
import DataNykaa.AssertData;
import DataNykaa.CheckoutData;
import DataNykaa.Header_FooterBarData;
import FrameWorkNykaa.CustomSoftAssert;
import PagesNykaa_NykaaMan.*;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import FrameWorkNykaa.TestListner;

import FrameWorkNykaa.AnalyticsLogAnalyser;
import FrameWorkNykaa.Framework;
import FrameWorkNykaa.LoginSync;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import net.lightbody.bmp.core.har.HarPostDataParam;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class Z_SanitySuite_Analytics {

    Framework framework = new Framework();
    TestListner testListener = new TestListner();
    String ClassName = this.getClass().getSimpleName();
    String PackageName = this.getClass().getPackage().getName();
    Properties properties = System.getProperties();
    AssertData SanAssertData = new AssertData("Sanity Checklist", "Android", "AutomationResultAndroid", "Elements",
            properties.getProperty("BUILD_URL") + "testngreports/" + PackageName + "/" + ClassName + "/");


    @Test(priority = 1, groups = {"AnalyticsTest"})
    public void Search_Analytics() throws Throwable {
        Framework frameWork = new Framework();
        AnalyticsLogAnalyser analyticLogAnalyser = AnalyticsLogAnalyser.getInstance();
        AnalyticsValidator analyticsValidator = new AnalyticsValidator();
        Map mapEventPageCheckResult = new HashMap<String, Object>();
        HashMap<String, Object> variableData = new HashMap<String, Object>();


        List<String> passList = new ArrayList<String>();
        List<String> failList = new ArrayList<String>();
        List<String> missingList = new ArrayList<String>();
        List<String> errorList = new ArrayList<String>();


        String Device = LoginSync.getDeviceInstance().getDevice();
        System.out.println("Device NAME ****************" + Device);
        AppiumDriverLocalService appiumServices = frameWork.getAppiumDriverLocalService(Device);
        AndroidDriver driver = frameWork.getProxyAndroidDriver();
        AccountData newLogin = frameWork.getData(AccountData.class, "newlogin");
        AccountData accountData = LoginSync.getInstance().getLogin();
        Header_FooterBarData headerFooterBarData = frameWork.getData(Header_FooterBarData.class, "TD1");
        // Header_FooterBarData headerFooterBarData1 = frameWork.getData(Header_FooterBarData.class, "sizenavigationApp");
        // Header_FooterBarData headerFooterBarData2 = frameWork.getData(Header_FooterBarData.class, "simplenavigationApp");
        CheckoutData checkoutData = frameWork.getData(CheckoutData.class, "simplepay");

        AnalyticsDataApp analyticsAddToCart = frameWork.getData(AnalyticsDataApp.class, "addtocart");
        CustomSoftAssert customSoftAssert = new CustomSoftAssert(SanAssertData, driver);

        HeaderBar headerBar = new HeaderBar(driver);
        ListingPage searchResult = new ListingPage(driver);

        try {


            LoginPage loginPage = new LoginPage(driver);
            loginPage.LoginWithUserNameAndPassword(accountData);


            ExtentTest childTest1 = testListener.startChild("Keyword Search");
            childTest1.setDescription("This test will Search the Keyword Given");
            TestListner.extentMap.get().put("child1", childTest1);

            ExtentTest childTest2 = testListener.startChild("AutoSuggestor Search");
            childTest2.setDescription("This test use the autosuggestor.");
            TestListner.extentMap.get().put("child2", childTest2);

            ExtentTest childTest3 = testListener.startChild("History Search");
            childTest3.setDescription("This test will use history Search Option");
            TestListner.extentMap.get().put("child3", childTest3);

            ExtentTest childTest4 = testListener.startChild("Top Category Search");
            childTest4.setDescription("This test use Top Category search.");
            TestListner.extentMap.get().put("child4", childTest4);


            ExtentTest childTest5 = testListener.startChild("Top Brand Search");
            childTest5.setDescription("This test use Top Brand search.");
            TestListner.extentMap.get().put("child5", childTest5);

            //Search Suggestion

            boolean isSearchBarPresent = headerBar.isSearchBarPresesnt();

            if (isSearchBarPresent) {
                headerBar.searchBarClick();
                headerBar.search(headerFooterBarData);
            }

            System.out.println(searchResult.getBreadcrumbsValue());

            if (searchResult.getBreadcrumbsValue().contains(headerFooterBarData.get_search_keyword())) {
                childTest2.log(LogStatus.INFO, "AutoSuggestor Search Pass");
                System.out.println("Search Result Displayed");
            }
            List<HarPostDataParam> harPostData = analyticLogAnalyser.getHarPostDataParam("omtrdc", "SearchedFromSuggestions");
            mapEventPageCheckResult = analyticsValidator.validateAnalyticsData("searchSuggestion", harPostData);

            passList = (List<String>) mapEventPageCheckResult.get("PassList");
            failList = (List<String>) mapEventPageCheckResult.get("FailList");
            errorList = (List<String>) mapEventPageCheckResult.get("ErrorList");
            missingList = (List<String>) mapEventPageCheckResult.get("MissingList");

            if (failList.isEmpty() && errorList.isEmpty() && missingList.isEmpty()) {

                childTest2.log(LogStatus.INFO, "Evar value Expected and Actual are Equal : " + passList);
                childTest2.log(LogStatus.PASS, "All Evar Expected are present on Page");

            } else {
                childTest2.log(LogStatus.INFO, "Evar or Value Missing Which are tracked: " + failList);
                childTest2.log(LogStatus.INFO, "Event Missing which are expected: " + missingList);
                childTest2.log(LogStatus.INFO, "PassList " + passList);
                childTest2.log(LogStatus.FAIL, "All Evar Expected are not present on Page");
            }

            passList.clear();
            failList.clear();
            missingList.clear();
            errorList.clear();


            //Search History
            searchResult.navigateUpClick();
            headerBar.searchBarClick();
            headerBar.searchHistory(headerFooterBarData);

            if (searchResult.getBreadcrumbsValue().contains(headerFooterBarData.get_search_keyword())) {
                childTest3.log(LogStatus.PASS, "Search History Result Pass");
                System.out.println("Search Result Displayed");
            }

            harPostData = analyticLogAnalyser.getHarPostDataParam("omtrdc", "SearchedFromHisory");
            mapEventPageCheckResult = analyticsValidator.validateAnalyticsData("searchFromHistory", harPostData);

            passList = (List<String>) mapEventPageCheckResult.get("PassList");
            failList = (List<String>) mapEventPageCheckResult.get("FailList");
            errorList = (List<String>) mapEventPageCheckResult.get("ErrorList");
            missingList = (List<String>) mapEventPageCheckResult.get("MissingList");

            if (failList.isEmpty() && errorList.isEmpty() && missingList.isEmpty()) {

                childTest3.log(LogStatus.INFO, "Evar value Expected and Actual are Equal : " + passList);
                childTest3.log(LogStatus.PASS, "All Evar Expected are present on Page");

            } else {
                childTest3.log(LogStatus.INFO, "Evar or Value Missing Which are tracked: " + failList);
                childTest3.log(LogStatus.INFO, "Event Missing which are expected: " + missingList);
                childTest3.log(LogStatus.INFO, "PassList " + passList);
                childTest3.log(LogStatus.FAIL, "All Evar Expected are not present on Page");
            }

            passList.clear();
            failList.clear();
            missingList.clear();
            errorList.clear();

//			//Keyword Based Search
//			searchResult.navigateUpClick();
//			headerBar.searchBarClick();
//			headerBar.searchKeyword(headerFooterBarData);
//			
//			if(searchResult.getBreadcrumbsValue().contains(headerFooterBarData.get_search_keyword())){
//				childTest1.log(LogStatus.PASS, "KeyWord Based Result Pass");
//				System.out.println("Search Result Displayed");
//			}


            //TOP Categories Search
            searchResult.navigateUpClick();
            String trendingProduct = headerBar.searchTrendingByCategories();

            if (searchResult.getBreadcrumbsValue().contains(trendingProduct)) {
                childTest4.log(LogStatus.PASS, "Top Category Result Pass");
                System.out.println("Search Result Displayed");
            }


            harPostData = analyticLogAnalyser.getHarPostDataParam("omtrdc", "Trending@nykaa");
            mapEventPageCheckResult = analyticsValidator.validateAnalyticsData("topCategoriesSearch", harPostData);

            passList = (List<String>) mapEventPageCheckResult.get("PassList");
            failList = (List<String>) mapEventPageCheckResult.get("FailList");
            errorList = (List<String>) mapEventPageCheckResult.get("ErrorList");
            missingList = (List<String>) mapEventPageCheckResult.get("MissingList");

            if (failList.isEmpty() && errorList.isEmpty() && missingList.isEmpty()) {

                childTest4.log(LogStatus.INFO, "Evar value Expected and Actual are Equal : " + passList);
                childTest4.log(LogStatus.PASS, "All Evar Expected are present on Page");

            } else {
                childTest4.log(LogStatus.INFO, "Evar or Value Missing Which are tracked: " + failList);
                childTest4.log(LogStatus.INFO, "Event Missing which are expected: " + missingList);
                childTest4.log(LogStatus.INFO, "PassList " + passList);
                childTest4.log(LogStatus.FAIL, "All Evar Expected are not present on Page");
            }

            passList.clear();
            failList.clear();
            missingList.clear();
            errorList.clear();

            //Top Brand Search
            searchResult.navigateUpClick();
            String topBrand = headerBar.searchBytopBrands();

            if (searchResult.getBreadcrumbsValue().contains(topBrand)) {
                childTest4.log(LogStatus.PASS, "Top Brand Result Pass");
                System.out.println("Search Result Displayed");
            }

            if (searchResult.getBreadcrumbsValue().contains(trendingProduct)) {
                childTest4.log(LogStatus.PASS, "Top Category Result Pass");
                System.out.println("Search Result Displayed");
            }


        } catch (Throwable e) {
            TestListner.testing.get().log(LogStatus.FAIL, e.toString());
            String Base64 = frameWork.captureScreenshot(driver);
            TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));
            Reporter.log(e.toString(), true);
            Assert.fail();
        } finally {

            LoginSync.getDeviceInstance().clearDeviceLock(Device);
            driver.quit();
            appiumServices.stop();
        }
    }


}