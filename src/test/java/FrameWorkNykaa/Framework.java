package FrameWorkNykaa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import DataNykaa.ObjectRepository;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeSuite;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import DataNykaa.AnalyticsData;
import DataNykaa.EnvironmentParameterData;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;

public class Framework {

	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Framework.class.getName());
	public static ExtentReports extentReports;
	public static final String USERNAME = "saurabhagrawal6";
	public static final String AUTOMATE_KEY = "Jd3isuPXqzgNFQcvtXsL";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	public static String environmentURL;
	public static String webPageLoadTestResultUrl = "";
	public static String accessKey = "TXcgVmusqXezjZ6yojVa";
	public static String userName = "ashishjain21";
	public static String apkPath;


	static volatile SessionFactory SessionFactory;
	Session session = null;
	DataBaseActivity dbActivity = new DataBaseActivity();

	public WebDriver getBrowser() throws Throwable {

		/* DesiredCapabilities caps = new DesiredCapabilities();
		    caps.setCapability("browser", "chrome");
		    caps.setCapability("browserstack.debug", "true");
		    caps.setCapability("build", "First build");

		    WebDriver driver = new RemoteWebDriver(new URL(URL),caps);*/
		String browsername;

		EnvironmentParameterData environmentData = getData(EnvironmentParameterData.class, "MsiteMan");

		PropertyConfiguration propertyConfig = new PropertyConfiguration();
		Properties pf = propertyConfig.getInstance();
		browsername = pf.getProperty("BrowserName");
		browsername = environmentData.getBrowsername();
		WebDriver driver = null;
		if(browsername.equalsIgnoreCase("firefox"))
			//if(true)
		{/****commented***/
			File file = new File("src/test/resources/geckodriver.exe").getCanonicalFile();
			System.setProperty("webdriver.gecko.driver", file.getAbsolutePath().replace("\\", "\\\\"));
			FirefoxProfile ffprofile = new FirefoxProfile();
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			ffprofile.setAcceptUntrustedCertificates(true);
			capabilities.setCapability("acceptInsecureCerts",true);
			ffprofile.setAssumeUntrustedCertificateIssuer(true);
			ffprofile.setPreference("dom.webnotifications.enabled", false);
			ffprofile.setPreference("security.insecure_field_warning.contextual.enabled",false);
			capabilities.setCapability(FirefoxDriver.PROFILE, ffprofile);
			driver = new FirefoxDriver(capabilities);
			//	driver.manage().window().maximize();

		}
		if(browsername.equalsIgnoreCase("chrome"))

		{
			File file;
			if(System.getProperty("os.name").contains("Mac")){
				file = new File("src/test/resources/chromedriver").getCanonicalFile();
			}else {
				file = new File("src/test/resources/chromedriver.exe").getCanonicalFile();
			}

			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath().replace("\\", "\\\\"));
			System.out.println(file.getAbsolutePath().replace("\\", "\\\\"));
			try {
				ChromeOptions options = new ChromeOptions();
				//	options.addArguments("--disable-extensions");

				driver = new ChromeDriver(options);
				driver.manage().window().maximize();

			} catch (Exception e) {
				e.printStackTrace();
			}
			driver.manage().window().maximize();

		}

		if(browsername.equalsIgnoreCase("mobile"))
		{
			File file;
			if(System.getProperty("os.name").contains("Mac")){
				file = new File("src/test/resources/chromedriver").getCanonicalFile();
			}else {
				file = new File("src/test/resources/chromedriver.exe").getCanonicalFile();
			}
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath().replace("\\", "\\\\"));
			System.out.println(file.getAbsolutePath().replace("\\", "\\\\"));
			try 
			{
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--user-agent=Mozilla/5.0 (Linux; Android 6.0; HTC One M9 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36");
				options.addArguments("--start-maximized");

				driver = new ChromeDriver(options);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			Dimension d=new Dimension(500, 700);
			driver.manage().window().setSize(d);

		}
		return driver;
	}

	//	public WebDriver getBrowser() throws Throwable {
	//
	//		String browsername;		
	//		EnvironmentParameterData environmentData = getData(EnvironmentParameterData.class, "Web");
	//		
	//		PropertyConfiguration propertyConfig = new PropertyConfiguration();
	//		Properties pf = propertyConfig.getInstance();
	//		browsername = pf.getProperty("BrowserName");
	//		browsername = environmentData.getBrowsername();
	//		WebDriver driver = null;
	//		if(browsername.equalsIgnoreCase("firefox"))
	//			//if(true)
	//		{/****commented***/
	//			File file = new File("src/test/resources/geckodriver.exe").getCanonicalFile();
	//			System.setProperty("webdriver.gecko.driver", file.getAbsolutePath().replace("\\", "\\\\"));
	//			FirefoxProfile ffprofile = new FirefoxProfile();
	//			DesiredCapabilities capabilities = new DesiredCapabilities();
	//			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	//			ffprofile.setAcceptUntrustedCertificates(true);
	//			capabilities.setCapability("acceptInsecureCerts",true);
	//			ffprofile.setAssumeUntrustedCertificateIssuer(true);
	//			ffprofile.setPreference("dom.webnotifications.enabled", false);
	//			ffprofile.setPreference("security.insecure_field_warning.contextual.enabled",false);
	//			capabilities.setCapability(FirefoxDriver.PROFILE, ffprofile);
	//			driver = new FirefoxDriver(capabilities);
	//		//	driver.manage().window().maximize();
	//
	//		}
	//		if(browsername.equalsIgnoreCase("chrome"))
	//
	//		{
	//			File file;
	//			if(System.getProperty("os.name").contains("Mac")){
	//				file = new File("src/test/resources/chromedriver").getCanonicalFile();
	//			}else {
	//				file = new File("src/test/resources/chromedriver.exe").getCanonicalFile();
	//			}
	//
	//			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath().replace("\\", "\\\\"));
	//			System.out.println(file.getAbsolutePath().replace("\\", "\\\\"));
	//			try {
	//				ChromeOptions options = new ChromeOptions();
	//				//	options.addArguments("--disable-extensions");
	//			
	//				driver = new ChromeDriver(options);
	//				driver.manage().window().maximize();
	//
	//			} catch (Exception e) {
	//				e.printStackTrace();
	//			}
	//			driver.manage().window().maximize();
	//
	//		}
	//
	//		if(browsername.equalsIgnoreCase("mobile"))
	//		{
	//			File file;
	//			if(System.getProperty("os.name").contains("Mac")){
	//				file = new File("src/test/resources/chromedriver").getCanonicalFile();
	//			}else {
	//				file = new File("src/test/resources/chromedriver.exe").getCanonicalFile();
	//			}
	//			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath().replace("\\", "\\\\"));
	//			System.out.println(file.getAbsolutePath().replace("\\", "\\\\"));
	//			try 
	//			{
	//				ChromeOptions options = new ChromeOptions();
	//				options.addArguments("--user-agent=Mozilla/5.0 (Linux; Android 6.0; HTC One M9 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36");
	//				options.addArguments("--start-maximized");
	//				
	//				driver = new ChromeDriver(options);
	//			}
	//			catch (Exception e) 
	//			{
	//				e.printStackTrace();
	//			}
	//			Dimension d=new Dimension(500, 700);
	//			driver.manage().window().setSize(d);
	//
	//		}
	//		return driver;
	//	}


	public WebDriver getBrowser(String platform) throws Throwable {

		/* DesiredCapabilities caps = new DesiredCapabilities();
		    caps.setCapability("browser", "chrome");
		    caps.setCapability("browserstack.debug", "true");
		    caps.setCapability("build", "First build");

		    WebDriver driver = new RemoteWebDriver(new URL(URL),caps);*/
		if (platform == null) {
			platform = "Web";
		}
		String browsername;
		EnvironmentParameterData environmentData = getData(EnvironmentParameterData.class, platform);
		PropertyConfiguration propertyConfig = new PropertyConfiguration();
		Properties pf = propertyConfig.getInstance();
		browsername = pf.getProperty("BrowserName");
		browsername = environmentData.getBrowsername();
		WebDriver driver = null;
		if (browsername.equalsIgnoreCase("firefox"))
			//if(true)
		{/****commented***/
			File file = new File("src/test/resources/geckodriver.exe").getCanonicalFile();
			System.setProperty("webdriver.gecko.driver", file.getAbsolutePath().replace("\\", "\\\\"));
			FirefoxProfile ffprofile = new FirefoxProfile();
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			ffprofile.setAcceptUntrustedCertificates(true);
			capabilities.setCapability("acceptInsecureCerts", true);
			ffprofile.setAssumeUntrustedCertificateIssuer(true);
			ffprofile.setPreference("dom.webnotifications.enabled", false);
			ffprofile.setPreference("security.insecure_field_warning.contextual.enabled", false);
			capabilities.setCapability(FirefoxDriver.PROFILE, ffprofile);
			driver = new FirefoxDriver(capabilities);
			driver.manage().window().maximize();

		}
		if (browsername.equalsIgnoreCase("chrome"))

		{

			File file;
			if (System.getProperty("os.name").contains("Mac")) {
				file = new File("src/test/resources/chromedriver").getCanonicalFile();
			} else {
				file = new File("src/test/resources/chromedriver.exe").getCanonicalFile();
			}
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath().replace("\\", "\\\\"));
			System.out.println(file.getAbsolutePath().replace("\\", "\\\\"));
			try {
				ChromeOptions options = new ChromeOptions();
				//	options.addArguments("--disable-extensions");
				options.addArguments("disable-infobars");
				driver = new ChromeDriver(options);

			} catch (Exception e) {
				e.printStackTrace();
			}
			driver.manage().window().maximize();

		}

		if (browsername.equalsIgnoreCase("mobile")) {
			File file;
			if (System.getProperty("os.name").contains("Mac")) {
				file = new File("src/test/resources/chromedriver").getCanonicalFile();
			} else {
				file = new File("src/test/resources/chromedriver.exe").getCanonicalFile();
			}
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath().replace("\\", "\\\\"));
			System.out.println(file.getAbsolutePath().replace("\\", "\\\\"));
			try {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--user-agent=Mozilla/5.0 (Linux; Android 6.0; HTC One M9 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36");
				options.addArguments("--start-maximized");
				options.addArguments("disable-infobars");
				driver = new ChromeDriver(options);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Dimension d = new Dimension(500, 700);
			driver.manage().window().setSize(d);

		}

		return driver;
	}

	public AndroidDriver getAndroidDriver() throws IOException, InterruptedException, URISyntaxException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		AppiumDriverLocalService appiumService = null;
		String appiumServiceUrl = null;
		String deviceName = null;
		AndroidDriver driver = null;
		if(CommonConstants.LOCAL_RUN) {
			deviceName = LoginSync.getDeviceInstance().getDevice();
			appiumService  = getAppiumDriverLocalService(deviceName);
			appiumServiceUrl = appiumService.getUrl().toString();
			System.out.println("Device NAME ****************" + deviceName);
			System.out.println("Appium Service Address ************************: - " + appiumServiceUrl);
			capabilities.setCapability(MobileCapabilityType.UDID, deviceName);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			capabilities.setCapability("newCommandTimeout", "45000");
			capabilities.setCapability("automationName", "uiautomator2");
			capabilities.setCapability("no-reset"," false");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			capabilities.setCapability(MobileCapabilityType.PLATFORM, MobilePlatform.ANDROID);
			capabilities.setCapability("newCommandTimeout", "45000");
			capabilities.setCapability("browserstack.local", "true");
			capabilities.setCapability("appPackage", "com.fsn.nykaa");
			capabilities.setCapability("appActivity", "com.fsn.nykaa.SplashScreenActivity");
			driver = new AndroidDriver(new URL(appiumServiceUrl), capabilities);

		}else {
			deviceName = System.getProperty("DeviceName");
			capabilities.setCapability("device",deviceName);
			capabilities.setCapability("newCommandTimeout", "45000");
			capabilities.setCapability("automationName", "uiautomator2");
			capabilities.setCapability("no-reset"," false");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			capabilities.setCapability(MobileCapabilityType.PLATFORM, MobilePlatform.ANDROID);
			capabilities.setCapability("newCommandTimeout", "45000");
			capabilities.setCapability("browserstack.local", "true");
			String app = getApkPath();
			System.out.println("Apk Path **************  -"+app);
			capabilities.setCapability("app", app);
			capabilities.setCapability("appPackage", "com.fsn.nykaa");
			capabilities.setCapability("appActivity", "com.fsn.nykaa.SplashScreenActivity");
			driver = new AndroidDriver(new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"),capabilities);
		}

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		return driver;
	}

	public AndroidDriver getProxyAndroidDriver() throws IOException, InterruptedException {
		AnalyticsLogAnalyser analyticLogAnalyser = AnalyticsLogAnalyser.getInstance();


		//         AppiumDriverLocalService appiumService = appiumServiceLocal;
		//         String appiumServiceUrl = appiumService.getUrl().toString();
		//         System.out.println("Appium Service Address ************************: - " + appiumServiceUrl);
		//         System.out.println("Appium Service Device *************************: - " + DeviceName);
		//         DesiredCapabilities capabilities = new DesiredCapabilities();
		//         capabilities.setCapability(MobileCapabilityType.UDID, DeviceName);
		//         capabilities.setCapability(MobileCapabilityType.UDID, DeviceName);
		//         capabilities.setCapability("newCommandTimeout", "45000");
		//         capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);
		//         capabilities.setCapability("automationName", "uiautomator2");
		//         capabilities.setCapability(MobileCapabilityType.NO_RESET,true);
		//        // capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
		//         capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		//         capabilities.setCapability(MobileCapabilityType.PLATFORM, MobilePlatform.ANDROID);
		//         capabilities.setCapability(MobileCapabilityType.PROXY, AnalyticsLogAnalyser.proxy );
		//         capabilities.setCapability("appPackage", "com.fsn.nykaa");
		//         capabilities.setCapability("newCommandTimeout", "45000");
		//         capabilities.setCapability("appActivity", "com.fsn.nykaa.SplashScreenActivity");
		//
		//         analyticLogAnalyser.createNewHar("NewHar");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("newCommandTimeout", "45000");
		String envDevice = System.getProperty("DeviceName");
		if(envDevice!=null){
			capabilities.setCapability("device",envDevice);
		}else {
			capabilities.setCapability("device", "Google Nexus 6");
		}
		capabilities.setCapability("automationName", "uiautomator2");
		capabilities.setCapability("no-reset"," false");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.PLATFORM, MobilePlatform.ANDROID);
		String app = getApkPath();
		System.out.println("Apk Path **************  -"+app);
		capabilities.setCapability("app", app);
		capabilities.setCapability("newCommandTimeout", "45000");
		capabilities.setCapability("browserstack.local", "true");
		capabilities.setCapability("browserstack.debug", "true");
		//capabilities.setCapability(MobileCapabilityType.ACCEPT_INSECURE_CERTS,"true");
		// capabilities.setCapability(MobileCapabilityType.ACCEPT_SSL_CERTS,"true");
		capabilities.setCapability(MobileCapabilityType.PROXY, AnalyticsLogAnalyser.proxy );
		capabilities.setCapability("appPackage", "com.fsn.nykaa");
		capabilities.setCapability("appActivity", "com.fsn.nykaa.SplashScreenActivity");

		analyticLogAnalyser.createNewHar("NewHar");



		AndroidDriver driver = new AndroidDriver(new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"),capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		return driver;

		// 		AndroidDriver driver = null;
		// 		try {
		//
		// 			driver = new AndroidDriver(new URL(appiumServiceUrl), capabilities);
		// 			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		// 			//System.out.println(analyticLogAnalyser.getHarPostDataParam());
		//
		// 		} catch (Exception e) {
		//
		// 			e.printStackTrace();
		//
		// 		}
		//return driver;
	}

	public AppiumDriverLocalService getAppiumDriverLocalService(String DeviceName) {


		String Appium_Node_Path;
		String Appium_JS_Path;
		if (System.getProperty("os.name").equals("Mac OS X")) {
			Appium_Node_Path = "/usr/local/bin/node";
			Appium_JS_Path = "/usr/local/lib/node_modules/appium/build/lib/main.js";
		} else {
			Appium_Node_Path = System.getProperty("Appium_Node_Path");
			Appium_JS_Path = System.getProperty("Appium_JS_Path");
		}

		//   AppiumDriverLocalService appiumService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().withIPAddress("127.0.0.1").usingAnyFreePort().usingDriverExecutable(new File(Appium_Node_Path)).withAppiumJS(new File(Appium_JS_Path)).withArgument(GeneralServerFlag.ROBOT_ADDRESS, DeviceName).withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, Integer.toString(ThreadLocalRandom.current().nextInt(100, 200 + 1))));
		AppiumDriverLocalService appiumService =AppiumDriverLocalService.buildDefaultService();

		appiumService.start();
		return appiumService;
	}

	public <T> List<T> getDataList(Class clazz, String ID) {
		List<T> listOfhibernateData = new ArrayList<T>(); 
		try {
			if (SessionFactory == null) {
				SessionFactory = getSessionInstance();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}

		try {
			session = SessionFactory.openSession();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(clazz);
		criteria.add(Restrictions.like("testcaseid", ID));
		listOfhibernateData = criteria.list();
		tx.commit();
		session.close();
		return listOfhibernateData;

	}

	public <T> List<T> getDataProvider(Class clazz, String ID) {
		List<T> listOfhibernateData = new ArrayList<T>();
		try {
			if (SessionFactory == null) {
				SessionFactory = getSessionInstance();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}

		Session session = null;
		try {
			session = SessionFactory.openSession();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(clazz);
		criteria.add(Restrictions.like("itrtestcaseid", ID));
		listOfhibernateData = criteria.list();
		tx.commit();
		session.close();

		List<T> DataProviderList = new ArrayList<T>();
		for (T data : listOfhibernateData) {
			DataProviderList.add((T) new Object[]{data});
		}
		return DataProviderList;


	}

	private void createExcelFile(Workbook workbook) {
		FileOutputStream fos = null;
		try {
			String path = new File("test-output").getAbsolutePath();
			fos = new FileOutputStream(new File(path + File.separator + "AnalyticsData.xlsx"));

			workbook.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HashSet<Integer> randomNumbers(int count) {
		ArrayList<Integer> mainList = new ArrayList<Integer>();
		HashSet<Integer> numbers = new HashSet<Integer>();
		int ran = 0;
		for (int i = 1; i <= count; i++) {
			mainList.add(i);
		}
		int divisor = (count / 10) + (count % 10) / 5;

		while (numbers.size() < count / divisor) {
			ran = (int) (Math.random() * count);
			numbers.add(mainList.get(ran));
			mainList.remove(ran);
			count -= 1;
		}

		return numbers;
	}

	public String hitWebPageTestAPI(WebDriver browser) {
		String ResultPage = "";
		String currentURL = browser.getCurrentUrl();
		String url = "http://www.webpagetest.org/runtest.php?url=" + currentURL + "&runs=1&f=json&k=A.dda23481a23f10b40457d0dcd22033b0";
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			JsonObject jobj = new JsonParser().parse(response.toString()).getAsJsonObject();
			if (jobj.get("data").isJsonObject()) {
				JsonObject jobj1 = new JsonParser().parse(jobj.get("data").toString()).getAsJsonObject();
				ResultPage = jobj1.get("userUrl").toString();
			}
		} catch (Exception e) {
		}

		return ResultPage;

	}

	public static String convertQuoteToOrder(String ip, Integer port, String userName, String key, String cmd) {
		com.jcraft.jsch.Session session = null;
		ChannelExec channel = null;
		String result = null;
		try {
			JSch jsch = new JSch();
			jsch.addIdentity(key);
			System.out.println("Private Key Added.");
			JSch.setConfig("StrictHostKeyChecking", "no");

			session = jsch.getSession(userName, ip, port);
			// session.setPassword(password);
			session.connect();

			channel = (ChannelExec) session.openChannel("exec");
			InputStream in = channel.getInputStream();
			channel.setErrStream(System.err);
			channel.setCommand(cmd);
			channel.connect();

			StringBuilder message = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = reader.readLine()) != null) {
				message.append(line).append("\n");
			}
			channel.disconnect();
			while (!channel.isClosed()) {

			}
			System.out.println(channel.getExitStatus() + " : " + message.toString());
			result = message.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				try {
					session.disconnect();
				} catch (Exception e) {

				}
			}
		}
		return result;
	}


	public <T> T getData(Class clazz, String ID) {
		List<T> listOfhibernateData = new ArrayList<T>(); 
		try {
			if (SessionFactory == null) {
				SessionFactory = getSessionInstance();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		Session session = SessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(clazz);
		criteria.add(Restrictions.like("testcaseid", ID));
		listOfhibernateData = criteria.list();
		tx.commit();
		session.flush();
		session.close();


		return listOfhibernateData.get(0);

	}

	public <T> Locator getLocator(Class clazz, String ID) {
		List<T> listOfhibernateData = new ArrayList<T>();
		try {
			if (SessionFactory == null) {
				SessionFactory = getSessionInstance();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		Session session = SessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(clazz);
		criteria.add(Restrictions.like("objectName", ID));
		listOfhibernateData = criteria.list();
		tx.commit();
		session.flush();
		session.close();

		// getting object repository from hybernate
		ObjectRepository repo = (ObjectRepository) listOfhibernateData.get(0);
		repo.setByLocator(By.xpath(repo.getLocatorValue()));
		Locator locator = new Locator(repo.getByLocator(), repo.getLocatorNameForReporting());
		return locator;
	}

	public <T>  void updateData(Object object) {

		try {
			if (SessionFactory == null) {
				SessionFactory = getSessionInstance();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		Session session = SessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		try {
			session.update(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tx.commit();
		session.flush();
		session.close();
		System.out.println(session.isConnected());



	}


	public <T> T getAllData(Class clazz) {
		List<T> listOfhibernateData = new ArrayList<T>(); 
		try {
			if (SessionFactory == null) {
				SessionFactory = getSessionInstance();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		Session session = SessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(clazz);

		listOfhibernateData = criteria.list();
		tx.commit();
		session.flush();
		session.close();
		System.out.println(session.isConnected());

		return (T) listOfhibernateData;

	}



	public static SessionFactory getSessionFactory() {
		return SessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		SessionFactory = sessionFactory;
	}

	private SessionFactory getSessionInstance() throws Throwable {
		String path = new File("src/test/resources/hibernate.cfg.xml").getCanonicalPath();
		return new Configuration().configure(new File(path).getCanonicalFile()).buildSessionFactory();
	}

	public String captureScreenshot(WebDriver browser) throws IOException {
		File scrFile = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(new File("test-output").getAbsolutePath() + File.separator
				+ Reporter.getCurrentTestResult().getMethod().getMethodName() + ".jpg"));
		String scrBase64Image = ((TakesScreenshot) browser).getScreenshotAs(OutputType.BASE64);
		Reporter.log(
				"<br> <img src=\"data:image/png;base64," + scrBase64Image + "\" height=\"700\" width=\"800\"> <br>");
		//System.out.println("<img src=�data:image/png;base64," + scrBase64Image + "�>");
		Reporter.log(browser.getCurrentUrl());
		return scrBase64Image;	
	}
	public void logErrorWithSnapshot(WebDriver browser, Throwable e) throws IOException {
		TestListner.testing.get().log(LogStatus.ERROR, e.getMessage());
		try {
			TestListner.testing.get().log(LogStatus.INFO, TestListner.testing.get().addBase64ScreenShot("data:image/png;base64," + captureScreenshot(browser)));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public String getEnvironment() throws Throwable {
		PropertyConfiguration pf = new PropertyConfiguration();
		Properties prop = pf.getInstance();
		/* environment = prop.getProperty("environmentName"); */
		return "tets";
	}


	public void killDriverProcess() throws IOException
	{
		try {
			String command = "TASKKILL /F /IM chromedriver.exe";
			Process p = Runtime.getRuntime().exec(command);
			p.destroy();
			p.destroyForcibly();
			System.exit(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void CreateExcelForAnalyticsData() throws Throwable, Throwable {
		List<AnalyticsData> analyitcsDataList = new ArrayList<AnalyticsData>();
		Framework framework = new Framework();
		analyitcsDataList = framework.getAllData(AnalyticsData.class);

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("AnalyticsData");


		Field[] fields = AnalyticsData.class.getDeclaredFields();
		Row header = sheet.createRow(0);
		int columnCount = 0;
		for(Field field:fields)
		{
			Cell cell = header.createCell(++columnCount);
			CellStyle style = workbook.createCellStyle(); 
			sheet.autoSizeColumn(0);



			//style.setWrapText(true);
			style.setFillBackgroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());

			cell.setCellValue(field.getName());
			cell.setCellStyle(style);
		}
		columnCount = 0;
		int Rowcount = 0;
		for(AnalyticsData analyticData :analyitcsDataList)
		{
			Row Row =	sheet.createRow(++Rowcount);
			for (int i = 1; i <=header.getPhysicalNumberOfCells(); i++) {
				Cell headercell = header.getCell(i);

				Cell RowCell = Row.createCell(i);
				Field field =  analyticData.getClass().getDeclaredField(headercell.getStringCellValue());
				field.setAccessible(true);
				String value = (String) field.get(analyticData);
				RowCell.setCellValue(value);


			}



		}

		createExcelFile(workbook)     ;

	}

	@BeforeSuite(alwaysRun= true)
	public void beforeSuite() throws Throwable
	{

		File fileres = new File("src/test/resources/ExtentReport.html").getCanonicalFile();
		File file = new File(fileres.getAbsolutePath().replace("\\", "\\\\"));
		file.createNewFile();

		PropertyConfigurator.configure("src/test/resources/log4j.properties");
		extentReports = new ExtentReports(fileres.getAbsolutePath().replace("\\", "\\\\"),true);
		SessionFactory = getSessionInstance();
		dbActivity.StartConnection();

		dbActivity.dropTableFromGsheet();
		dbActivity.createTableFromGSpreadsheets();
		//dbActivity.dumpDataIntoDatabase();
		GoogleSheets gs = new GoogleSheets();

		com.google.gdata.data.spreadsheet.SpreadsheetEntry Worksheet = GoogleSheets.getSpreadSheet("automation Regression Coverage");
		com.google.gdata.data.spreadsheet.SpreadsheetEntry Worksheet1 = GoogleSheets.getSpreadSheet("Automation Data");
		com.google.gdata.data.spreadsheet.SpreadsheetEntry Worksheet3 = GoogleSheets.getSpreadSheet("Object_Repository_For_Automation");
		com.google.gdata.data.spreadsheet.SpreadsheetEntry Worksheet2 = GoogleSheets.getSpreadSheet("Sanity Checklist");
		//gs.clearField(Worksheet,"DeskTop" ,"Result");
		gs.clearField(Worksheet1, "APIData", "Result");
		if (!CommonConstants.LOCAL_RUN) {
			setApkPath(uploadApk());
			//gs.clearField(Worksheet2,"Desktop" ,"AutomationResult");
			//gs.clearField(Worksheet2,"Msite" ,"AutomationResult");
		}

	}

	public String uploadApk() throws URISyntaxException, IOException {
		//
		String path;
		if (System.getProperty("os.name").equals("Mac OS X")) {
			path = "/Users/YashZanwar/.jenkins/workspace/firstProject/UploadApk.apk";
		}else {
			//path = System.getProperty("Apk file");
			System.out.println(System.getenv("JENKINS_APK"));
			String fileName = System.getProperty("Apk_File");
			path = CommonConstants.JENKINS_PATH + "\\"+fileName;

		}

		HttpClient httpclient = new DefaultHttpClient();
		URI uri = new URI("https://"+userName+":"+accessKey+"@api.browserstack.com/app-automate/upload");
		HttpPost postRequest = new HttpPost(uri);
		MultipartEntity nameValuePairs = new MultipartEntity();
		nameValuePairs.addPart("file",new FileBody(new File(path)));//App path
		postRequest.setEntity(nameValuePairs);
		HttpResponse response = httpclient.execute(postRequest);
		HttpEntity entity = response.getEntity();
		String responseString = EntityUtils.toString(entity, "UTF-8");
		System.out.println(responseString);
		responseString =responseString.replace("{\"app_url\":\"","");
		responseString = responseString.split("\"}")[0];
		System.out.println(responseString);
		return responseString;
	}


	public String TestuploadApk() throws URISyntaxException, IOException {
		String path;
		if (System.getProperty("os.name").equals("Mac OS X")) {
			path = "/Users/YashZanwar/.jenkins/workspace/firstProject/UploadApk.apk";
		}else {
			//path = "C:\\Users\\Administrator\\.jenkins\\workspace\\Android_App_Automation\\UploadApk.apk";
			path = "C:\\Users\\Public\\apk\\Android-App.apk";

		}
		HttpClient httpclient = new DefaultHttpClient();
		URI uri = new URI("https://"+userName+":"+accessKey+"@api.browserstack.com/app-automate/upload");
		HttpPost postRequest = new HttpPost(uri);
		MultipartEntity nameValuePairs = new MultipartEntity();
		nameValuePairs.addPart("file",new FileBody(new File(path)));//App path
		postRequest.setEntity(nameValuePairs);
		HttpResponse response = httpclient.execute(postRequest);
		HttpEntity entity = response.getEntity();
		String responseString = EntityUtils.toString(entity, "UTF-8");
		System.out.println(responseString);
		responseString =responseString.replace("{\"app_url\":\"","");
		responseString = responseString.split("\"}")[0];
		System.out.println(responseString);
		return responseString;

	}



	@AfterSuite(alwaysRun = true)
	public void SessionTearDown() throws Throwable{
		CreateExcelForAnalyticsData();
		extentReports.flush();
		//extentReports.close();
		SessionFactory.close();
		dbActivity.dropTableFromGsheet();
		//dbActivity.afterTest();

		//static Logger log4jConfigFile = Logger.getLogger(log4jConfigFile.class.getName());
		System.out.println(SessionFactory.isClosed());

		//to copy html report as backup
		File dest = null;
		try 
		{
			DateFormat dateFormat = new SimpleDateFormat("dd_MM_HH_mm");
			Date date = new Date();
			File source =  new File("src/test/resources/ExtentReport.html");
			dest=new File("src/test/resources/ExtentReports");
			FileUtils.copyFileToDirectory(source,  dest);
			File oldfile =new File("src/test/resources/ExtentReports/ExtentReport.html");
			File newfile =new File("src/test/resources/ExtentReports/Report_"+dateFormat.format(date)+".html");
			oldfile.renameTo(newfile);
		}


		catch (Exception e) {
			e.printStackTrace();
		}

		try{
			File[] files = dest.listFiles();

			Arrays.sort(files, new Comparator<File>() {
				public int compare(File f1, File f2) {
					return Long.compare(f2.lastModified(),f1.lastModified());
				}
			});

			if(files.length>50)
			{
				for(int i=files.length-1;i>=50;i--)
				{
					files[i].delete();
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		finally 
		{
			//killDriverProcess();
		}


	}

	public JsonObject Get_API_Response(WebDriver browser, String apiURL )
	{
		String ResultPage="";
		String currentURL=browser.getCurrentUrl();
		JsonObject jobj=null;
		//String url = apiURL;
		try
		{
			URL obj = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			jobj = new JsonParser().parse(response.toString()).getAsJsonObject();

		}
		catch(Exception e)
		{}

		return jobj;

	}

	public static void setApkPath(String apkPath) {
		Framework.apkPath = apkPath;
	}


	public static String getApkPath() {
		return apkPath;
	}
}
