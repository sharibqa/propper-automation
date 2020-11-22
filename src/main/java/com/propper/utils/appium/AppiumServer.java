package com.propper.utils.appium;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.propper.utils.selenium.DriverPool;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

/**
 * This class will be used to start and stop the appium server.
 * 
 * @author Jaikant
 *
 */
public class AppiumServer {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppiumServer.class);
	public static AppiumDriverLocalService appium;

	/**
	 * It will start the appium server.
	 */
	public static void start() {
		if (Objects.isNull(appium)) {
		terminateAppiumProcess();
		String appiumMainJsPath = System.getProperty("user.home") + System.getProperty("file.separator")
																  + "AppData/Roaming/npm/node_modules/appium/build/lib/main.js";
		try {
			AppiumServiceBuilder builder = new AppiumServiceBuilder()
										  .withAppiumJS(new File(appiumMainJsPath))
										  .withArgument(GeneralServerFlag.SESSION_OVERRIDE);
			appium = builder.build();
			appium.start();
			LOGGER.info("Successfully started appium server..");
		} catch (Exception e) {
			LOGGER.info("Exception occurred while starting appium server. {}", e.getMessage());

		}
	}
	}

	/**
	 * It will stop the appium server.
	 */
	public static void stop() {
		LOGGER.info("Stopping appium server..");
		if (Objects.nonNull(appium))
			appium.stop();
		LOGGER.info("Stopped appium server.");
	}

	/**
	 * It will terminate the already running appium process.
	 */
	public static void terminateAppiumProcess() {
		LOGGER.info("Killing any running appium process");
		try {
			Process process = Runtime.getRuntime().exec("taskkill /F /IM node.exe");
			process.destroy();
			LOGGER.info("Successfully killed running appium process.");
		} catch (IOException e) {
			LOGGER.info("Exception occurred while terminating appium process. {}", e.getMessage());
		}

	}
}