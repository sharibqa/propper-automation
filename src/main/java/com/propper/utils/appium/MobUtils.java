package com.propper.utils.appium;

import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.propper.utils.selenium.WebUtils;

import io.appium.java_client.android.AndroidDriver;

/**
 * This class is responsible for performing all actions required to automate
 * mobile device applications. i.e Android/tablets etc.
 * 
 */
public class MobUtils extends WebUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MobUtils.class);


	public static AndroidDriver<WebElement> driver;
	static {driver = (AndroidDriver<WebElement>) WebUtils.getDriver();}

	/** It will rotate the android device screen to LANDSCAPE mode.
	 * 
	 */
	public static void setDeviceOrientation(String orientation) {
		ScreenOrientation screenOrientation = orientation.equalsIgnoreCase("PORTRAIT") ? ScreenOrientation.PORTRAIT : ScreenOrientation.LANDSCAPE;
		driver.rotate(screenOrientation);
	}

}
