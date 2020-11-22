package com.propper.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.propper.utils.appium.MobUtils;
import com.propper.utils.common.Config;
import com.propper.utils.selenium.DriverPool;
import com.propper.utils.selenium.WebUtils;

import io.appium.java_client.android.AndroidDriver;

public class BaseConfiguration {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseConfiguration.class);

	public static WebDriver driver;
	private ITestContext context;
	private static final String APPLICATION_URL = Config.getProperty("appURL");
	

	@Parameters({ "browser", "nodeURL", "deviceOrientation" })
	@BeforeMethod
	public void setup(@Optional("CHROME")String browser, @Optional("")String nodeURL, @Optional("LANDSCAPE") String deviceOrientation, ITestContext ctx) {
		try {
			if (browser.contains("mobile")) {
				driver = (AndroidDriver<WebElement>)DriverPool.getDriver(browser, nodeURL);
				WebUtils.setDriver(driver);
				MobUtils.setDeviceOrientation(deviceOrientation);
				WebUtils.navigateToURL(APPLICATION_URL);
			} else {
				driver = DriverPool.getDriver(browser, nodeURL);
				WebUtils.setDriver(driver);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				WebUtils.navigateToURL(APPLICATION_URL);
			}
		} catch (Exception e) {
			LOGGER.error("Error occured {} ", e.getMessage());
			throw new WebDriverException(e.getMessage());
		}

		this.context = DriverPool.setupContext(driver, ctx, browser, nodeURL);
	}
	
}
