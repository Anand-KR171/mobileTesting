package appiumFramework;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class capability {

	static String DEVICE_NAME;
	static String PLATFORM_NAME;
	static String APP_PACKAGE;
	static String APP_ACTIVITY;
	static String CHROMEDRIVER_EXECUTABLE;
	public AppiumDriverLocalService service;

	public static void startEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("C:\\Users\\AnandKR\\Mobile_Automation\\Framework\\src\\main\\resources\\emulator.bat");
		Thread.sleep(30000);
		System.out.println("Emulator Started");
	}

	public AppiumDriverLocalService startServer() {
		// To run in the server
		// service = AppiumDriverLocalService.buildDefaultService();

		// To run with the local Appium
		service = AppiumDriverLocalService.buildService(
				new AppiumServiceBuilder().usingDriverExecutable(new File("c:\\Program Files\\nodejs\\node.exe"))
						.withAppiumJS(new File(
								"C:\\Users\\AnandKR\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
						.withIPAddress("127.0.0.1").usingPort(4723));
		service.start();
		return service;
	}

	public static boolean checkifserverisRunning(int port) {
		boolean isServerRunning = false;
		ServerSocket serversocket;
		try {
			serversocket = new ServerSocket(port);
			serversocket.close();
		} catch (IOException e) {
			isServerRunning = true;
		} finally {
			serversocket = null;
		}
		return isServerRunning;

	}

	public static AndroidDriver<AndroidElement> capabilities(String DEVICE_NAME, String PLATFORM_NAME,
			String APP_PACKAGE, String APP_ACTIVITY, String CHROMEDRIVER_EXECUTABLE)
			throws IOException, InterruptedException {
		// i want to install/open the App in my emulator
		FileReader fs = new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\global.properties");
		Properties prop = new Properties();
		prop.load(fs);

		DEVICE_NAME = prop.getProperty("DEVICE_NAME");
		PLATFORM_NAME = prop.getProperty("PLATFORM_NAME");
		APP_PACKAGE = prop.getProperty("APP_PACKAGE");
		APP_ACTIVITY = prop.getProperty("APP_ACTIVITY");
		CHROMEDRIVER_EXECUTABLE = prop.getProperty("CHROMEDRIVER_EXECUTABLE");

		if (DEVICE_NAME.contains("Anand"))
			startEmulator();

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		// dc.setCapability(MobileCapabilityType.APP,fs.getAbsolutePath()); better for
		// IOS
		// to connect to the Appium
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE);
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);
		dc.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, CHROMEDRIVER_EXECUTABLE);
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),dc);
		return driver;

	}

}
