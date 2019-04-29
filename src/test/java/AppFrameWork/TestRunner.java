package AppFrameWork;

import TestCaseSuitesApp.Z_SanitySuite;
import TestCaseSuitesApp.Z_SanitySuite_Analytics;
import FrameWorkNykaa.*;
import com.relevantcodes.extentreports.ExtentTest;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import java.util.HashMap;


public class TestRunner {

	private static final String String = null;
    public static volatile HashMap<String,ExtentTest> parentExtentMap=new HashMap<String,ExtentTest>();

	public static void main(String[] args) throws Throwable {
		TestListenerAdapter tla = testngPlay();
	if(!tla.getFailedTests().isEmpty())
	{
		String TestGroup = System.getProperty("TestGroup");
		if(TestGroup.contains("production"))
		{
		/*	GatewaySMS gateWaySms = new GatewaySMS();
			gateWaySms.sendSms();*/
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

	public static TestListenerAdapter testngPlay() throws Throwable {
		try {

			GoogleSheets gs = new GoogleSheets();
		com.google.gdata.data.spreadsheet.SpreadsheetEntry Worksheet = GoogleSheets.getSpreadSheet("automation Regression Coverage");
	        String TestcaseIds = gs.getExecutionalTestcases(Worksheet,"DeskTop");
	        if(System.getProperty("TestGroup")!=null){
	        	TestcaseIds= System.getProperty("TestGroup");
			}else {
				TestcaseIds = "testRun";
			}
			//TestListenerAdapter tla = new TestListenerAdapter();
			TestNG testng = new TestNG();
			TestListner testliten = new TestListner();
			testng.addListener(testliten);
			testng.setThreadCount(6);
			testng.setDataProviderThreadCount(6);
			testng.setParallel("methods");
			String TestGroup = System.getProperty("TestGroup");

			  if(TestGroup!=null)
			  {testng.setGroups(TestGroup);
			  }
			  else{

				  testng.setGroups(TestcaseIds);
			  }

            //testng.setGroups(prop.getProperty("TestGroup")); // this will collect the group and will all testcase will be run under same group
			//no of parallel testcases can be run is defined by this
	        testng.setVerbose(12);
			testng.setTestClasses(new Class[] {DataBaseActivity.class, Z_SanitySuite.class, Framework.class,Z_SanitySuite_Analytics.class});
			//testng.setTestClasses(new Class[] {DataBaseActivity.class, Z_SanitySuite.class});
			testng.addListener(testliten);
            testng.run();
            return testliten;
		} catch (Exception e) {
			return null;
		}
		/*
		 * if(tla.getFailedTests().isEmpty()) { return false; } return true;
		 */

	}

}
