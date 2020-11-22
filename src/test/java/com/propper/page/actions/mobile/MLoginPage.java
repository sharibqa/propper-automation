package com.propper.page.actions.mobile;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.propper.page.actions.web.LoginPage;

/** This class contains all actions/operations that will be performed on Login page in mobile browser view..
 * 
 * @author Jaikant.lnu
 *
 */
public class MLoginPage extends LoginPage {

	private static final Logger LOGGER = LoggerFactory.getLogger(MLoginPage.class);

	
	public MLoginPage(WebDriver driver) {
		super(driver);
	}
	
	// override parent class method here if any new implementation needed
	// you can also add new methods here if needed

}
