package com.propper.utils.selenium;

import static com.propper.utils.common.Constants.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;

/**
 * It contains pool of web drivers for desktop web applications.
 * 
 * @author Jaikant
 *
 */
public class DriverPool {
	private static final Logger LOGGER = LoggerFactory.getLogger(DriverPool.class);

	/**
	 * It will get the WebDriver for specified browser.
	 * 
	 * @param browser
	 * @param nodeURL
	 * @return
	 */
	public static WebDriver getDriver(String browser, String nodeURL) {

		WebDriver driver = null;
		try {
			if (browser.toLowerCase().contains(MOBILE)) {
				LOGGER.info(LOG_DESIGN + "Getting Mobile Remote web driver for : {} and node URL is : {} ", browser,
						nodeURL);
				driver = getMobileRemoteDriver(browser, nodeURL);
			} else if (!nodeURL.isEmpty()) {
				LOGGER.info(LOG_DESIGN + "Getting Remote web driver for : {} and node URL is : {} ", browser, nodeURL);
				driver = getRemoteDriver(browser, nodeURL);
			} else {
				driver = getWebDriver(browser);
				LOGGER.info(LOG_DESIGN + "Getting web driver for browser : {}", browser);
			}
		} catch (Exception e) {
			LOGGER.error(LOG_DESIGN + "!!!!!!!! Exception occurred while getting webdriver : {}", e.getMessage());
		}

		return driver;
	}

	/**
	 * @param browser browser name
	 * @param nodeURL node URL where want to run execution
	 * @return RemoteWebDriver corresponding to the given browser value
	 * @throws MalformedURLException
	 */
	public static WebDriver getRemoteDriver(String browser, String nodeURL) throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		switch (browser.toLowerCase()) {
		case CHROME:
			cap = DesiredCapabilities.chrome();
			break;
		case FIREFOX:
			cap = DesiredCapabilities.firefox();
			break;
		case INTERNET_EXPLORER:
			cap = DesiredCapabilities.internetExplorer();
			break;
		default:
			cap = DesiredCapabilities.chrome();
			break;
		}
		cap.setPlatform(Platform.extractFromSysProperty(System.getProperty("os.name"),System.getProperty("os.version")));

		return new RemoteWebDriver(new URL(nodeURL), cap);
	}

	/**
	 * @param browser browser name
	 * @param nodeURL node URL where want to run execution
	 * @return RemoteWebDriver corresponding to the given browser value
	 * @throws MalformedURLException
	 */
	public static WebDriver getMobileRemoteDriver(String browser, String nodeURL) throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		WebDriver driver = null;
		switch (browser.toLowerCase()) {
		case MOBILE_CHROME_ANDROID:
			driver = MobileDriverPool.getAndroidChromeDriver(nodeURL);
			break;
		}

		return driver;
	}

	/**
	 * @param browser browser name
	 * @return WebDriver corresponding to the given browser value
	 * @throws MalformedURLException
	 */
	public static WebDriver getWebDriver(String browser) throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		WebDriver driver = null;
		switch (browser.toLowerCase()) {
		case CHROME:
			driver = getChromeDriver(cap);
			break;
		case FIREFOX:
			driver = getFirefoxGeckoDriver();
			break;
		case INTERNET_EXPLORER:
			driver = getIEDriver();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			break;
		case CHROME_EMULATOR:
			driver = getChromeDriverForChromeEmulator();
			break;
		default:
			driver = getChromeDriver(cap);
			break;
		}
		cap.setPlatform(Platform.extractFromSysProperty(System.getProperty("os.name"), System.getProperty("os.version")));

		return driver;
	}

	/**
	 * @return instance of firefox gecko driver
	 */
	public static WebDriver getFirefoxGeckoDriver() {
		System.setProperty("webdriver.gecko.driver", GECKODRIVER_PATH);

		return new FirefoxDriver();
	}

	/**
	 * @return instance of chrome driver
	 */
	public static WebDriver getChromeDriver(DesiredCapabilities cap) {
		System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		return new ChromeDriver(options);
	}

	/**
	 * It gives instance of IEDriver
	 * 
	 * @return instance of IEDriver
	 */
	public static WebDriver getIEDriver() {
		System.setProperty("webdriver.ie.driver", IEDRIVER_PATH);

		return new InternetExplorerDriver();
	}

	/**
	 * It gives chrome driver to run on chrome browser emulator
	 * 
	 * @return instance of chromedriver
	 */
	@SuppressWarnings("deprecation")
	public static WebDriver getChromeDriverForChromeEmulator() {
		System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
		Map<String, Object> deviceMetrics = new HashMap<String, Object>();
		deviceMetrics.put("width", 360);
		deviceMetrics.put("height", 740);
		deviceMetrics.put("pixelRatio", 4.0);
		Map<String, Object> mobileEmulation = new HashMap<String, Object>();
		mobileEmulation.put("deviceMetrics", deviceMetrics);
		mobileEmulation.put("userAgent",
				"Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("mobileEmulation", mobileEmulation);
		// options.addExtensions(new File(EXTENSIONS_BASE_LOCATION +
		// "AdblockExtension.crx"));
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		return new ChromeDriver(capabilities);
	}

	/**
	 * It sets the context attributes.
	 * 
	 * @param driver
	 * @param ctx
	 * @return ITestContext
	 */
	public static ITestContext setupContext(WebDriver driver, ITestContext ctx, String browser, String nodeURL) {
        ctx.setAttribute("driver", driver);
        ctx.setAttribute("browser", browser);
        ctx.setAttribute("nodeURL", nodeURL);
        
        return ctx;
    }
}
