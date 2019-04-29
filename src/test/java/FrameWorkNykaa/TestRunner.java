package FrameWorkNykaa;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import TestCaseSuite.*;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import com.relevantcodes.extentreports.ExtentTest;

import PagesNykaa.ProductPage;

/**
 * @author nevil
 *
 */
public class TestRunner {

	private static final String String = null;
	public static volatile HashMap<String,ExtentTest> parentExtentMap=new HashMap<String,ExtentTest>();
	public static boolean isLive = false;

	public static void main(String[] args) throws Throwable {

		TestListenerAdapter tla = testngPlay();
		if(!tla.getFailedTests().isEmpty())
		{
			String TestGroup = System.getProperty("TestGroup");
			if(TestGroup.contains("production"))
			{
				GatewaySMS gateWaySms = new GatewaySMS();
				gateWaySms.sendSms();
			}
		}
		/*
		 * boolean isFailed; isFailed = testngPlay();
		 */
		//Email email = new Email();
		/* email.sendMail(isFailed); */
		//email.sendMail(tla);

	}



	/**
	 * @param string2
	 */
	private static void sendSms(String string2) {
		// TODO Auto-generated method stub

	}



	/**
	 * below method to run testng programmatically 
	 * @return its returns instance of testlistenerAdapter
	 * @throws Throwable
	 */
	@SuppressWarnings("deprecation")
	public static TestListenerAdapter testngPlay() throws Throwable {
		try {
			GoogleSheets  gs = new 	GoogleSheets();
			com.google.gdata.data.spreadsheet.SpreadsheetEntry Worksheet = GoogleSheets.getSpreadSheet("automation Regression Coverage");
			// String TestcaseIds = gs.getExecutionalTestcases(Worksheet,"DeskTop");

			String  TestcaseIds ="sanity";

			//PropertyConfiguration pf = new PropertyConfiguration();
			//Properties prop = pf.getInstance();
			TestListenerAdapter tla = new TestListenerAdapter();
			TestNG testng = new TestNG();
			TestListner testliten = new TestListner();
			testng.addListener(testliten);
			testng.addListener(tla);

			String TestGroup = System.getProperty("TestGroup");
			if(TestGroup!=null)
			{testng.setGroups(TestGroup);
			}
			else{

				testng.setGroups(TestcaseIds);
			}

			//testng.setGroups(prop.getProperty("TestGroup")); // this will collect the group and will all testcase will be run under same group
			if((TestGroup!=null && !(TestGroup.contains("api")||TestGroup.contains("API")||TestGroup.contains("Api"))) || (TestcaseIds!=null && !(TestcaseIds.contains("api")||TestcaseIds.contains("API")||TestcaseIds.contains("Api"))) )
			{
				testng.setParallel("methods");

				testng.setDataProviderThreadCount(6);

				testng.setThreadCount(6);//no of parallel testcases can be run is defined by this
			}
			testng.setVerbose(2);

			testng.setTestClasses(new Class[] {DataBaseActivity.class, Z_APITestSuite.class, Z_SanitySuite_New.class,Framework.class,ProductPage.class, Z_ProductionSuite.class,Z_SanitySuite_Mobile.class });

			//testng.addListener(tla);
			testng.run();
			if(TestRunner.isLive && ! tla.getFailedTests().isEmpty())
			{
				Email email=new Email();
				email.sendMail(tla);
			}
			return testliten;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		/*
		 * if(tla.getFailedTests().isEmpty()) { return false; } return true;
		 */

	}

}
