/**
 * 
 */
package TestCaseSuite;

import DataNykaa.APIData;
import DataNykaa.AssertData;
import DataNykaa.EnvironmentParameterData;
import FrameWorkNykaa.CustomSoftAssert;
import FrameWorkNykaa.Framework;
import FrameWorkNykaa.Retry;
import FrameWorkNykaa.TestListner;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.awaitility.Awaitility;
import org.junit.rules.Timeout;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mongodb.util.JSON;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.security.KeyStore;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.hamcrest.Matchers.containsString;
import static org.awaitility.Awaitility.*;
/**
 * @author nevil
 *
 */

public class Z_APITestSuite {

	String baseUrl = null;
	TestListner testListener = new TestListner();
	AssertData APIAssertData = new AssertData("Automation Data","APIData","Result","Name","");

	static String Token ;
	static String CustomerId;
	protected String mTestCaseName = "";
	Response resp = null;
	@BeforeSuite(alwaysRun=true)
	public void preSetup()
	{
		Framework frameWork = new Framework();
		EnvironmentParameterData environmentData = frameWork.getData(EnvironmentParameterData.class, "api");
		baseUrl = environmentData.getBaseurl();
		Awaitility.reset();
        Awaitility.setDefaultPollDelay(100, TimeUnit.MILLISECONDS);
        Awaitility.setDefaultPollInterval(3, TimeUnit.SECONDS);
        Awaitility.setDefaultTimeout(7, TimeUnit.SECONDS);
	}

	@AfterMethod(alwaysRun=true)
	public void flushExtent()
	{
		Framework.extentReports.flush();
	}

	@DataProvider(name = "Data Provide For TC1" ,parallel = true)
	public Iterator<Object[]>  dataListForSearch() {
		Framework framework = new Framework();
		List<Object[]> apiDataList = framework.getDataProvider(APIData.class, "ITR_TC1");
		return apiDataList.listIterator();

	}
	@Test(dataProvider="Data Provide For TC1",groups = {"api"},dependsOnMethods="LOGIN_API_TEST")
	public synchronized void API_TESTS(APIData apiData) throws Throwable
	{
		//Thread.sleep(5000);
		ExtentTest childTest1 = testListener.startChild(apiData.getName()+" test ");
		TestListner.extentMap.get().put("child1", childTest1);
		CustomSoftAssert CS = new CustomSoftAssert(APIAssertData);
		RestAssured.baseURI = baseUrl;
		RestAssured.basePath =apiData.getPath();
		childTest1.log(LogStatus.INFO, "BaseURL: "+baseUrl);
		childTest1.log(LogStatus.INFO, "BasePath: "+apiData.getPath());
		childTest1.log(LogStatus.INFO, "API Parameters: "+apiData.getParameter());
		
		try {
			if(apiData.getMethod().equalsIgnoreCase("post"))
			{
				childTest1.log(LogStatus.INFO, "Method : POST");
				resp = RestAssured.given().config(RestAssured.config().sslConfig(
						new SSLConfig().relaxedHTTPSValidation())).contentType(ContentType.URLENC).body(apiData.getParameter().replace("TokenValue", Token).replace("CustomerValue", CustomerId)).
						when()
						.post("");
				
				AtomicBoolean flag= new AtomicBoolean(false);
				if(resp.jsonPath().get("type").equals("object")||resp.jsonPath().get("status").equals("fail"))
				{
					flag.set(true);
				}
				await().atMost(50,TimeUnit.SECONDS).untilTrue(flag);
				System.out.println(apiData.getParameter().replace("TokenValue", Token).replace("CustomerValue", CustomerId));
				childTest1.log(LogStatus.INFO, "Request :"+ apiData.getParameter().replace("TokenValue", Token).replace("CustomerValue", CustomerId));
				System.out.println(resp.asString());
				childTest1.log(LogStatus.INFO, "Response :"+ resp.asString());

				Reporter.log(resp.asString());
				resp.then().assertThat().statusCode(200);
				CS.assertTrue(resp.asString().contains(apiData.getExpected()),apiData.getTestcaseid());
				CS.assertAll();
				resp.then().assertThat().body(containsString(apiData.getExpected()));
				CS.assertEquals(resp.jsonPath().get("status").toString(), apiData.getExpected());
				childTest1.log(LogStatus.INFO, "Expected Satus : "+ apiData.getExpectedStatus() +"  ||  Actual Status : "+resp.jsonPath().get("status").toString());
				childTest1.log(LogStatus.INFO, "Expected Value to check : "+ apiData.getExpected());
				if(! resp.jsonPath().get("status").toString().contains(apiData.getExpectedStatus()))
				{
					childTest1.log(LogStatus.FAIL, "Incorrect Status");
				}

			}
			if(apiData.getMethod().equalsIgnoreCase("get"))
			{
				childTest1.log(LogStatus.INFO, "Method : GET");
				resp = RestAssured.given().config(RestAssured.config().sslConfig(
						new SSLConfig().relaxedHTTPSValidation())).contentType(ContentType.URLENC).
						when()
						.get("?"+apiData.getParameter());
				System.out.println(resp.asString());
				childTest1.log(LogStatus.INFO, "Response :"+ resp.asString());
				Reporter.log(resp.asString());
				CS.assertTrue(resp.asString().contains(apiData.getExpected()),apiData.getTestcaseid());
				
				resp.then().assertThat().body(containsString(apiData.getExpected()));
				resp.then().assertThat().statusCode(200);
				childTest1.log(LogStatus.INFO, "Expected Satus : "+ apiData.getExpectedStatus() +"  ||  Actual Status : "+resp.jsonPath().get("status").toString());
				childTest1.log(LogStatus.INFO, "Expected Value to check : "+ apiData.getExpected());
				if(! resp.jsonPath().get("status").toString().contains(apiData.getExpectedStatus()))
				{
					childTest1.log(LogStatus.FAIL, "Incorrect Status");
				}

				CS.assertAll();

			}
		} catch (AssertionError e) {
			childTest1.log(LogStatus.INFO, "Expected Satus : "+ apiData.getExpectedStatus() +"  ||  Actual Status : "+resp.jsonPath().get("status").toString());
			childTest1.log(LogStatus.FAIL, "Expected Value is not present in response : "+ apiData.getExpected());
			Assert.fail("Failed Testcase");
		}
	}




	@Test(groups = {"api"},priority=0)
	public synchronized void LOGIN_API_TEST() throws Throwable
	{     Framework frameWork = new Framework();

	ExtentTest childTest1 = testListener.startChild("Login API test ");
	childTest1.setDescription("This test verifies that user is able to login with existing credentials using Login API.");
	TestListner.extentMap.get().put("child1", childTest1);
	APIData    apiData  = frameWork.getData(APIData.class, "login");
	CustomSoftAssert CS = new CustomSoftAssert(APIAssertData);
	RestAssured.baseURI = baseUrl;
	RestAssured.basePath =apiData.getPath();

	childTest1.log(LogStatus.INFO, "BaseURL: "+baseUrl);
	childTest1.log(LogStatus.INFO, "BasePath: "+apiData.getPath());
	childTest1.log(LogStatus.INFO, "API Parameters: "+apiData.getParameter());

	KeyStore keyStore = null;
	SSLConfig config = null;

	try {
		if(apiData.getMethod().equalsIgnoreCase("post"))
		{
			childTest1.log(LogStatus.INFO, "Method : POST");
			Response resp = RestAssured.given().config(RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())).contentType(ContentType.URLENC).body(apiData.getParameter()).when().post("");
			System.out.println(resp.asString());
			childTest1.log(LogStatus.INFO, "Response : "+resp.asString());
			resp.then().assertThat().statusCode(200);
			Reporter.log(resp.asString());
			CS.assertTrue(resp.asString().contains(apiData.getExpected()),apiData.getTestcaseid());

			String tempResponse=resp.asString().substring(1);
			if(! tempResponse.startsWith("{"))
			{
				tempResponse="{"+tempResponse;
			}
			resp.then().assertThat().body(containsString(apiData.getExpected()));

			JsonPath jsonPath = new JsonPath(tempResponse.trim());
			Token = jsonPath.get("response.authentication_token");
			CustomerId = jsonPath.getString("response.customer_id");
			
			childTest1.log(LogStatus.INFO, "Expected Satus : "+ apiData.getExpectedStatus() +"  ||  Actual Status : "+jsonPath.get("status").toString());
			if(! jsonPath.get("status").toString().contains(apiData.getExpectedStatus()))
			{
				childTest1.log(LogStatus.FAIL, "Incorrect Status");
			}
			else
			{
				childTest1.log(LogStatus.PASS, "Status is Correct");
			}
			
			CS.assertAll();
		}
	} catch (AssertionError e) {
		// TODO Auto-generated catch block
		childTest1.log(LogStatus.FAIL, "Incorrect Status");
		Assert.fail("Failed Testcase Please check log");
		
	}
	}
}
