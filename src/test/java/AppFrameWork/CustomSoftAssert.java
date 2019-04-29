package AppFrameWork;

import DataNykaa.AssertData;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Reporter;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import FrameWorkNykaa.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class CustomSoftAssert extends SoftAssert {
	GoogleSheets googleSheet=null;
	SpreadsheetEntry Worksheet = null;
	String Sheetname = null;
	URL listFeedUrl=null;
	String ResultFieldName = null;
	AndroidDriver webdriver = null;
	Framework framework = new Framework();
	String FieldName = null;
	String URL = null;
	 
	public CustomSoftAssert(String WorkSheetName,String SheetName) throws  Throwable {
		Sheetname = SheetName;
		googleSheet = new GoogleSheets();
		Worksheet = googleSheet.getSpreadSheet(WorkSheetName);
	
		
	}

	public CustomSoftAssert(AssertData assertData) throws Throwable{
		Sheetname = assertData.getSheetName();
		googleSheet = new GoogleSheets();
		Worksheet = googleSheet.getSpreadSheet(assertData.getWorkSheetName());
       	listFeedUrl = googleSheet.getFeedFromSheet(Worksheet, Sheetname);
       	ResultFieldName = assertData.getResultFiledName();
    }

	public CustomSoftAssert(AssertData assertData, AndroidDriver driver) throws  Throwable{
	   Sheetname = assertData.getSheetName();
	   googleSheet = new GoogleSheets();
       Worksheet = googleSheet.getSpreadSheet(assertData.getWorkSheetName());
       listFeedUrl = googleSheet.getFeedFromSheet(Worksheet, Sheetname);
       ResultFieldName = assertData.getResultFiledName();
       webdriver = driver;
       FieldName = assertData.getTestcaseDesc();
       URL = assertData.getJenKinsURL()+ Thread.currentThread().getStackTrace()[2].getMethodName();
       
	}

	@Override
	public void assertTrue(boolean condition, String message) {
	// TODO Auto-generated method stub
	//super.assertFalse(!condition, message);
   super.assertTrue(condition, message);
}

	@Override
	public void onAssertFailure(IAssert<?> assertCommand,AssertionError ex) {
		String CaseID = assertCommand.getMessage();
	     
	
		try {
			
			//googleSheet.updateResultField(CaseID, Worksheet,Sheetname, "Fail",ResultFieldName);
			googleSheet.updateResultField(CaseID, Worksheet,Sheetname, URL ,ResultFieldName);
			String TestCaseDescription = googleSheet.getFieldValue(CaseID, Worksheet,Sheetname,  FieldName);
			//googleSheet.updateRemarkField(CaseID, Worksheet,Sheetname, ex.getLocalizedMessage());
			Reporter.log("<p><font size=\"3\" color=\"red\">"+TestCaseDescription+"--"+CaseID+"--Failed\"</font></p>",true);
			framework.captureScreenshot(webdriver);
			String Base64 = framework.captureScreenshot(webdriver);
			TestListner.testing.get().log(LogStatus.FAIL, "<p><font color=\"red\">"+TestCaseDescription+"--"+CaseID+"--Failed!\"</font></p>");
			TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + Base64));



		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	              
	
		
		
		// TODO Auto-generated method stub
		super.onAssertFailure(assertCommand);
	}
	
	@Override
	public void onAssertSuccess(IAssert<?> assertCommand) {
		String CaseID = assertCommand.getMessage();
		
		try {
			googleSheet.updateResultField(CaseID, Worksheet,Sheetname, "Pass",ResultFieldName);
			String TestCaseDescription = googleSheet.getFieldValue(CaseID, Worksheet,Sheetname,  FieldName);
			Reporter.log("<p><font size=\"3\" color=\"green\">"+TestCaseDescription+"--"+CaseID+"--Passed\"</font></p>",true);
			TestListner.testing.get().log(LogStatus.PASS, "<p><font color=\"green\">"+TestCaseDescription+"--"+CaseID+"--Passed!\"</font></p>");


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		super.onAssertSuccess(assertCommand);
		
	              
	
	}

}
