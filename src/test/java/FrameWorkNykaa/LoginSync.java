package FrameWorkNykaa;

import DataNykaa.AccountData;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author nevil
 *
 */
public class LoginSync {

	public volatile static LoginSync INSTANCE = null;
	public volatile static LoginSync DeviceINSTANCE = null;
	public volatile static LoginSync DRIVERINSTANCE = null;
	public volatile static BlockingQueue<AccountData> UsedAccountLogine = new LinkedBlockingQueue<AccountData>();
	public volatile  static BlockingQueue<AccountData> FreeListLoginQue = new LinkedBlockingQueue<AccountData>();
	public volatile static BlockingQueue<String> UsedAccountDevice = new LinkedBlockingQueue<String>();
	public volatile  static BlockingQueue<String> FreeListDeviceQue = new LinkedBlockingQueue<String>();
	public volatile static BlockingQueue<AndroidDriver> UsedAndroidDriver = new LinkedBlockingQueue<AndroidDriver>();
	public volatile static BlockingQueue<AndroidDriver> FreeAndroidDriver = new LinkedBlockingQueue<AndroidDriver>();
	List<String> deviceList = new ArrayList<String>();
	Framework frameWork = new Framework();
	HashMap<String, String> hm = new HashMap<String, String>();

	private LoginSync() {
	}

	public static void init() {

	}

	public static synchronized LoginSync getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new LoginSync();
			INSTANCE.populateLogins();
		}
		return INSTANCE;
	}

	public static synchronized LoginSync getDeviceInstance() throws IOException {
		if (DeviceINSTANCE == null) {
			DeviceINSTANCE = new LoginSync();
			DeviceINSTANCE.populateDevice();
		}
		return DeviceINSTANCE;
	}


	public static synchronized LoginSync getAndroidDriverInstace() throws IOException, InterruptedException, URISyntaxException {
		if (DRIVERINSTANCE == null)
		    {
			DRIVERINSTANCE = new LoginSync();
			DRIVERINSTANCE.populateDriver();
		    }
           return DRIVERINSTANCE;
	}


	/*
	 * protected Object clone() throws CloneNotSupportedException { throw new
	 * CloneNotSupportedException(); }
	 */
	private void populateLogins() {

		Framework framework = new Framework();
		List<AccountData> accountDataList = framework.getDataList(AccountData.class, "loginset");
		FreeListLoginQue = new LinkedBlockingQueue<AccountData>();
		for (AccountData accountData : accountDataList) {
			FreeListLoginQue.add(accountData);

		}
		// Set<Entry<String, String>> entries = hm.entrySet();
		// FreeLoginQue = new
		// LinkedBlockingQueue<HashMap.Entry<String,String>>(entries);

	}

	private void populateDevice() throws IOException {
		Framework frameWork = new Framework();
		//List<AccountData> deviceList = frameWork.getDataList(AccountData.class,"Devices");

		try{

		File dir=new File("C:\\Program Files (x86)\\Android\\android-sdk\\platform-tools");
		Process process = Runtime.getRuntime().exec("adb devices");


		BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = null;



		Pattern pattern = Pattern.compile("^([a-zA-Z0-9:.\\-]+)(\\s+)(device)");
		Matcher matcher;

		while ((line = in.readLine()) != null)
		{
			if (line.matches(pattern.pattern()))
			{
				matcher = pattern.matcher(line);
				if (matcher.find())
					System.out.println(matcher.group(1));
				deviceList.add(matcher.group(1));
			}
		}
           for(String deviceid: deviceList)
           {
			   Runtime.getRuntime().exec("adb -s "+deviceid+" shell am start -n 'io.appium.unlock/.Unlock' -a 'android.intent.action.MAIN' -c 'android.intent.category.LAUNCHER' -f '0x10200000'");
		   }


	} catch (IOException e)
	{
		e.printStackTrace();
	}
		FreeListDeviceQue = new LinkedBlockingQueue<String>();
		for (String device : deviceList)
		{
			FreeListDeviceQue.add(device);

		}

	}

	private void populateDriver() throws IOException, InterruptedException, URISyntaxException {
		//List<AppiumDriverLocalService> appiumServer = new ArrayList<AppiumDriverLocalService>();
		populateDevice();
		for (int i = 0; i <= deviceList.size()-1; i++)
		{
			String Appium_Node_Path ="/usr/local/bin/node";
			String Appium_JS_Path="/usr/local/lib/node_modules/appium/build/lib/main.js";
			AppiumDriverLocalService appiumService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().withIPAddress("127.0.0.1").usingAnyFreePort().usingDriverExecutable(new File(Appium_Node_Path)).withAppiumJS(new File(Appium_JS_Path)).withArgument(GeneralServerFlag.ROBOT_ADDRESS, deviceList.get(i)).withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, Integer.toString(ThreadLocalRandom.current().nextInt(100, 200 + 1))));
			appiumService.start();
		//	AndroidDriver driver = frameWork.getAndroidDriver(appiumService, deviceList.get(i));
		//	FreeAndroidDriver.add(driver);


		}
	}

	public String getDevice() throws InterruptedException {
		String device = FreeListDeviceQue.take();
		UsedAccountDevice.put(device);
		// UsedLoginque.put(login);

		return device;
	}
	// hm.
	// hm.put("nevil.panchal","Password123");

	public AccountData getLogin() throws InterruptedException {
		// populateLogins();// not required
		// Entry<String, String> login = FreeLoginQue.take();
		AccountData accountData = FreeListLoginQue.take();
		UsedAccountLogine.put(accountData);
		// UsedLoginque.put(login);

		return accountData;
	}

	public AndroidDriver getDriver() throws InterruptedException {
		AndroidDriver androidDriver = FreeAndroidDriver.take();
		UsedAndroidDriver.put(androidDriver);
		return androidDriver;
	}

	public void clearLoginLock(AccountData accountData) throws InterruptedException {
		if (UsedAccountLogine.remove(accountData))
		{
			FreeListLoginQue.put(accountData);
		}

	}

	public void clearDeviceLock(String Device) throws InterruptedException {
		if (UsedAccountDevice.remove(Device))
		{
			FreeListDeviceQue.put(Device);
		}

	}

	public void clearAndroidDriverLock(AndroidDriver driver) throws InterruptedException{

		if(UsedAndroidDriver.remove(driver))
		{
			FreeAndroidDriver.put(driver);
		}
	}
}
