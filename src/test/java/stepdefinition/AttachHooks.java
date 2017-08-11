package stepdefinition;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khelplay.utils.CommonFunctionLibrary;
import com.khelplay.utils.ConfigManager;
import com.khelplay.utils.DriverFactory;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AttachHooks {

	private Scenario scenario;
	public static WebDriver driver;
	DesiredCapabilities capabilities;
	private static Logger LOGGER = LoggerFactory.getLogger(AttachHooks.class);
	public static boolean flag = false, saleFlag = false, unsoldFlag = false, winFlag = false;
	CommonFunctionLibrary functionLibrary;

	@Before
	public void setUp(Scenario scenario) throws IOException {
		LOGGER.info("Inside set up method of before hook");
		ConfigManager.loadConfig();
		this.scenario = scenario;
		System.out.println(scenario.getName());

		if (System.getenv("ExecutionPlatform") != null) {
			if (System.getenv("ExecutionPlatform").equalsIgnoreCase("Api"))
				ConfigManager.setProperty("ExecutionPlatform", "api");
			System.out.println("Environment Variable fetched from jenkins = " + System.getenv("ExecutionPlatform"));
			ConfigManager.getProperty("ExecutionPlatform");
		}

		if (System.getenv("ExecutionPlatform") != null) {
			if (System.getenv("ExecutionPlatform").equalsIgnoreCase("Web"))
				ConfigManager.setProperty("ExecutionPlatform", "Web");
			System.out.println("Environment Variable fetched from jenkins = " + System.getenv("ExecutionPlatform"));
			ConfigManager.getProperty("ExecutionPlatform");
		}

		if (ConfigManager.getProperty("ExecutionPlatform").equalsIgnoreCase("Mobile")) {
			if (ConfigManager.getProperty("PlatformName").equalsIgnoreCase("Android")) {
				try {
					DriverFactory.appiumStart();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				capabilities = new DesiredCapabilities();
				capabilities.setCapability("emulator", true);
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigManager.getProperty("DeviceName"));
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
						ConfigManager.getProperty("PlatformName"));
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
						ConfigManager.getProperty("PlatformVersion"));
				capabilities.setCapability(MobileCapabilityType.APP, ConfigManager.getProperty("ApkPath"));
				capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60000");

				try {
					driver = new AndroidDriver(
							new URL("http://127.0.0.1:" + ConfigManager.getProperty("AppiumPort") + "/wd/hub"),
							capabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				if (ConfigManager.getProperty("browserName").equalsIgnoreCase("MobileWeb")) {
					driver.get(ConfigManager.getProperty("EnvironmentURL"));
				}

			} /*
				 * else if
				 * (ConfigManager.getProperty("PlatformName").equalsIgnoreCase(
				 * "IOS")) { capabilities = new DesiredCapabilities();
				 * capabilities.setCapability("deviceName",
				 * ConfigManager.getProperty("deviceName"));
				 * capabilities.setCapability("platformVersion",
				 * ConfigManager.getProperty("platformVersion"));
				 * capabilities.setCapability("platformName",
				 * ConfigManager.getProperty("platformName"));
				 * capabilities.setCapability("app",
				 * ConfigManager.getProperty("apkPath")); try { driver = new
				 * IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				 * capabilities); } catch (MalformedURLException e) {
				 * e.printStackTrace(); }
				 * driver.manage().timeouts().implicitlyWait(40,
				 * TimeUnit.SECONDS); }
				 */
		}
		/*
		 * if (ConfigManager.getProperty("ExecutionPlatform").equalsIgnoreCase(
		 * "MobileWeb")) { if
		 * (ConfigManager.getProperty("PlatformName").equalsIgnoreCase("Android"
		 * )) { try { DriverFactory.appiumStart(); } catch (IOException e) {
		 * e.printStackTrace(); } catch (InterruptedException e) {
		 * e.printStackTrace(); } capabilities = new DesiredCapabilities();
		 * capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
		 * ConfigManager.getProperty("DeviceName"));
		 * capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
		 * ConfigManager.getProperty("PlatformName"));
		 * capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
		 * ConfigManager.getProperty("PlatformVersion"));
		 * capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,
		 * ConfigManager.getProperty("Browser_Name"));
		 * capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,
		 * "60000"); try { driver = new AndroidDriver(new
		 * URL("http://127.0.0.1:4723/wd/hub"), capabilities); } catch
		 * (MalformedURLException e) { e.printStackTrace(); }
		 * driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		 * driver.get(ConfigManager.getProperty("EnvironmentURL"));
		 * driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); } }
		 */
		/*
		 * if ((ConfigManager.getProperty("ExecutionPlatform").equalsIgnoreCase(
		 * "Web"))) { System.out.println("Our browser will be invoked here"); if
		 * (ConfigManager.getProperty("browserName").equalsIgnoreCase("chrome"))
		 * { System.setProperty("webdriver.chrome.driver",
		 * System.getProperty("user.dir") + "\\chromedriver.exe"); ChromeOptions
		 * options = new ChromeOptions();
		 * options.addArguments("--disable-extensions"); driver = new
		 * ChromeDriver(options); } if
		 * (ConfigManager.getProperty("browserName").equalsIgnoreCase("firefox")
		 * ) { driver = new FirefoxDriver();
		 * 
		 * } if
		 * (ConfigManager.getProperty("browserName").equalsIgnoreCase("IE")) {
		 * System.setProperty("webdriver.ie.driver",
		 * "D:\\SkilrockAutomation\\IEDriverServer.exe"); driver = new
		 * InternetExplorerDriver(); } System.out.println("Browrse Opened");
		 * driver.get(ConfigManager.getProperty("EnvironmentURL_LMS"));
		 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 * driver.manage().window().maximize();
		 * System.out.println(driver.getTitle()); }
		 */
		functionLibrary = new CommonFunctionLibrary(driver);
	}

	@After
	public void tearDown() throws InstantiationException, IllegalAccessException, IOException {
		if (ConfigManager.getProperty("ExecutionPlatform").equalsIgnoreCase("Api")) {
			System.out.println("API Execution Stop :) ");
		}
		if (ConfigManager.getProperty("ExecutionPlatform").equalsIgnoreCase("Mobile")) {
			functionLibrary.embedScreenshot(scenario);
			driver.quit();

			// DriverFactory.appiumStop();
		} else if (ConfigManager.getProperty("ExecutionPlatform").equalsIgnoreCase("Web")) {
			functionLibrary.embedScreenshot(scenario);
			driver.quit();
		}

	}

}