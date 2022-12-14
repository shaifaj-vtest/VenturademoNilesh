package com.QA.Base;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.QA.reports.ExtentReport;
import com.QA.utlis.TestUtils;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class BaseTest {
	protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
	protected static ThreadLocal<Properties> props = new ThreadLocal<Properties>();
	protected static ThreadLocal<HashMap<String, String>> strings = new ThreadLocal<HashMap<String, String>>();
	protected static ThreadLocal<String> platform = new ThreadLocal<String>();
	protected static ThreadLocal<String> dateTime = new ThreadLocal<String>();
	protected static ThreadLocal<String> deviceName = new ThreadLocal<String>();
	private static AppiumDriverLocalService server;

	static String userName = System.getenv("BROWSERSTACK_USERNAME") == null ? "nileshkharche_fQN5xV"
			: System.getenv("BROWSERSTACK_USERNAME"); // Add username here
	static String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY") == null ? "uqLrxy8YqX4fWkmnzYsT"
			: System.getenv("BROWSERSTACK_ACCESS_KEY"); // Add accessKey here
	public static String gridURL = "@hub-cloud.browserstack.com/wd/hub";
	String hub = "https://" + userName + ":" + accessKey + gridURL;

	String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
	String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");

	TestUtils utils = new TestUtils();

	public AppiumDriver getDriver() {
		return driver.get();
	}

	public void setDriver(AppiumDriver driver2) {
		driver.set(driver2);
	}

	public Properties getProps() {
		return props.get();
	}

	public void setProps(Properties props2) {
		props.set(props2);
	}

	public HashMap<String, String> getStrings() {
		return strings.get();
	}

	public void setStrings(HashMap<String, String> strings2) {
		strings.set(strings2);
	}

	public String getPlatform() {
		return platform.get();
	}

	public void setPlatform(String platform2) {
		platform.set(platform2);
	}

	public String getDateTime() {
		return dateTime.get();
	}

	public void setDateTime(String dateTime2) {
		dateTime.set(dateTime2);
	}

	public String getDeviceName() {
		return deviceName.get();
	}

	public void setDeviceName(String deviceName2) {
		deviceName.set(deviceName2);
	}

	public BaseTest() {

		// PageFactory.initElements(getDriver(), this);

		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
	}

//	@BeforeMethod
//	public void beforeMethod() {
//		((CanRecordScreen) getDriver()).startRecordingScreen();
//	}

//	// stop video capturing and create *.mp4 file
//	@AfterMethod
//	public synchronized void afterMethod(ITestResult result) throws Exception {
//		String media = ((CanRecordScreen) getDriver()).stopRecordingScreen();
//
//		Map<String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();
//		String dirPath = "videos" + File.separator + params.get("platformName") + "_" + params.get("deviceName")
//				+ File.separator + getDateTime() + File.separator
//				+ result.getTestClass().getRealClass().getSimpleName();
//
//		File videoDir = new File(dirPath);
//
//		synchronized (videoDir) {
//			if (!videoDir.exists()) {
//				videoDir.mkdirs();
//			}
//		}
//		FileOutputStream stream = null;
//		try {
//			stream = new FileOutputStream(videoDir + File.separator + result.getName() + ".mp4");
//			stream.write(Base64.decodeBase64(media));
//			stream.close();
//			utils.log().info("video path: " + videoDir + File.separator + result.getName() + ".mp4");
//		} catch (Exception e) {
//			utils.log().error("error during video capture" + e.toString());
//		} finally {
//			if (stream != null) {
//				stream.close();
//			}
//		}
//	}

	public boolean checkIfAppiumServerIsRunnning(int port) throws Exception {
		boolean isAppiumServerRunning = false;
		ServerSocket socket;
		try {
			socket = new ServerSocket(port);
			socket.close();
		} catch (IOException e) {
			System.out.println("1");
			isAppiumServerRunning = true;
		} finally {
			socket = null;
		}
		return isAppiumServerRunning;
	}

	// for Windows
	public AppiumDriverLocalService getAppiumServerDefault() {
		return AppiumDriverLocalService.buildDefaultService();
	}

	// for Mac. Update the paths as per your Mac setup
	public AppiumDriverLocalService getAppiumService() {
		HashMap<String, String> environment = new HashMap<String, String>();
		environment.put("PATH",
				"/Library/Internet Plug-Ins/JavaAppletPlugin.plugin/Contents/Home/bin:/Users/omprakashchavan/Library/Android/sdk/tools:/Users/omprakashchavan/Library/Android/sdk/platform-tools:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Library/Apple/usr/bin"
						+ System.getenv("PATH"));
		environment.put("ANDROID_HOME", "/Users/omprakashchavan/Library/Android/sdk");
		return AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File("/usr/local/bin/node"))
						.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js")).usingPort(4723)
						.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//				.withArgument(() -> "--allow-insecure","chromedriver_autodownload")
						.withEnvironment(environment).withLogFile(new File("ServerLogs/server.log")));
	}

	@Parameters({ "emulator", "platformName", "deviceName" })
	@BeforeTest
	public void beforeTest(@Optional("androidOnly") String emulator, String platformName, String deviceName)
			throws Exception {
		setDateTime(utils.dateTime());
		setPlatform(platformName);
		setDeviceName(deviceName);
		URL url;
		InputStream inputStream = null;
		InputStream stringsis = null;
		Properties props = new Properties();
		AppiumDriver driver;

		String strFile = "logs" + File.separator + platformName + "_" + deviceName;
		File logFile = new File(strFile);
		if (!logFile.exists()) {
			logFile.mkdirs();
		}
		// route logs to separate file for each thread
		ThreadContext.put("ROUTINGKEY", strFile);
		utils.log().info("log path: " + strFile);

		try {
			props = new Properties();
			String propFileName = "config.properties";
			String xmlFileName = "strings/strings.xml";

			utils.log().info("load " + propFileName);
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			props.load(inputStream);
			setProps(props);

			utils.log().info("load " + xmlFileName);
			stringsis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
			setStrings(utils.parseStringXML(stringsis));

			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setCapability("device", deviceName);
			desiredCapabilities.setCapability("platform", platformName);
			desiredCapabilities.setCapability("os_version", "9.0");
			desiredCapabilities.setCapability("browserstack.local", "true");
			desiredCapabilities.setCapability("browserstack.localIdentifier", browserstackLocalIdentifier);
			desiredCapabilities.setCapability("project", "VenturaWealth");
			desiredCapabilities.setCapability("build", "Ventura2.0");
			desiredCapabilities.setCapability("name", "Ventura Demo");

			switch (platformName) {
			case "Android":
				desiredCapabilities.setCapability("app", "bs://a330caa361256dcbde4ca410b783541216464927");

				if (emulator.equalsIgnoreCase("true")) {
					desiredCapabilities.setCapability("avd", deviceName);
					desiredCapabilities.setCapability("avdLaunchTimeout", 120000);
				}
				String androidAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
						+ File.separator + "resources" + File.separator + "app" + File.separator
						+ "com.ventura.venturawealth_152_apps.evozi.com.apk";
				// String androidAppUrl =
				// getClass().getResource(props.getProperty("androidAppLocation")).getFile();
				utils.log().info("appUrl is" + androidAppUrl);

				driver = new AndroidDriver(new URL(hub), desiredCapabilities);
				break;
			case "iOS":
				desiredCapabilities.setCapability("automationName", props.getProperty("iOSAutomationName"));
				String iOSAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
						+ File.separator + "resources" + File.separator + "app" + File.separator
						+ "SwagLabsMobileApp.app";
				utils.log().info("appUrl is" + iOSAppUrl);
				driver = new IOSDriver(new URL(hub), desiredCapabilities);
				break;
			default:
				throw new Exception("Invalid platform! - " + platformName);
			}
			setDriver(driver);
			utils.log().info("driver initialized: " + driver);
		} catch (Exception e) {
			utils.log().fatal("driver initialization failure. ABORT!!!\n" + e.toString());
			throw e;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (stringsis != null) {
				stringsis.close();
			}
		}
	}

	public void waitForVisibility(MobileElement e) {
		WebDriverWait wait = new WebDriverWait(getDriver(), TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public void waitForVisibility(WebElement e) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public void clear(MobileElement e) {
		waitForVisibility(e);
		e.clear();
	}

	public void click(MobileElement e) {
		waitForVisibility(e);
		e.click();
	}

	public void click(MobileElement e, String msg) {
		waitForVisibility(e);
		utils.log().info(msg);
		ExtentReport.getTest().log(Status.INFO, msg);
		e.click();
	}

	public void sendKeys(MobileElement e, String txt) {
		waitForVisibility(e);
		e.sendKeys(txt);
	}

	public void sendKeys(MobileElement e, String txt, String msg) {
		waitForVisibility(e);
		utils.log().info(msg);
		ExtentReport.getTest().log(Status.INFO, msg);
		e.sendKeys(txt);
	}

	public String getAttribute(MobileElement e, String attribute) {
		waitForVisibility(e);
		return e.getAttribute(attribute);
	}

	public String getText(MobileElement e, String msg) {
		String txt = null;
		switch (getPlatform()) {
		case "Android":
			txt = getAttribute(e, "text");
			break;
		case "iOS":
			txt = getAttribute(e, "label");
			break;
		}
		utils.log().info(msg + txt);
		ExtentReport.getTest().log(Status.INFO, msg + txt);
		return txt;
	}

	public void closeApp() {
		((InteractsWithApps) getDriver()).closeApp();
	}

	public void launchApp() {
		((InteractsWithApps) getDriver()).launchApp();
	}

	public MobileElement scrollToElement(String name) {
		return (MobileElement) ((FindsByAndroidUIAutomator) getDriver())
				.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
						+ ".scrollable(true)).scrollIntoView(" + "new UiSelector().description(\"" + name + "\"));");
	}

	public void iOSScrollToElement() {
		RemoteWebElement element = (RemoteWebElement) getDriver().findElement(By.name("test-ADD TO CART"));
		String elementID = element.getId();
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", elementID);
//	  scrollObject.put("direction", "down");
//	  scrollObject.put("predicateString", "label == 'ADD TO CART'");
//	  scrollObject.put("name", "test-ADD TO CART");
		scrollObject.put("toVisible", "sdfnjksdnfkld");
		getDriver().executeScript("mobile:scroll", scrollObject);
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		if (getDriver() != null) {
			getDriver().quit();
		}
	}
}
