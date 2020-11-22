package com.propper.utils.selenium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * It contains pool of web drivers for mobile devices.
 * 
 * @author Jaikant
 *
 */
public class MobileDriverPool {

	private static final Logger LOGGER = LoggerFactory.getLogger(MobileDriverPool.class);

	/**
	 * It will return driver for mobile chrome browser.
	 * 
	 * @param nodeURL
	 * @return driver for mobile chrome browser.
	 * @throws MalformedURLException
	 */
	public static AndroidDriver<MobileElement> getAndroidChromeDriver(String nodeURL) throws MalformedURLException {
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("w3c", false);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "chrome");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
		capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
		capabilities.setCapability("unicodeKeyboard", true);
	//	capabilities.setCapability("resetKeyboard", true);
	//	capabilities.setCapability("autoGrantPermissions", true);		
		capabilities.setCapability("newCommandTimeout", 60 * 5); // command timeout set to 5 minutes
		options.setCapability("capabilities", capabilities);


		LOGGER.info("Launching android chrome driver with capabilities : {}", options);

		return new AndroidDriver<MobileElement>(new URL(nodeURL), options);
	}

}
