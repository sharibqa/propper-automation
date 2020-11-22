package com.propper.tests.mobile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.propper.core.BaseConfiguration;
import com.propper.page.actions.mobile.MHomePage;
import com.propper.page.actions.mobile.MLoginPage;
import com.propper.page.actions.mobile.MMyAccountPage;
import com.propper.page.actions.web.HomePage;
import com.propper.page.actions.web.LoginPage;
import com.propper.page.actions.web.MyAccountPage;
import com.propper.page.validators.mobile.MLoginValidator;
import com.propper.page.validators.web.LoginValidator;
import com.propper.utils.common.Config;
import com.propper.utils.selenium.WebUtils;

public class MLoginTest extends BaseConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(MLoginTest.class);

	private MLoginPage loginACtions;
	private MHomePage homePageActions;
	private MMyAccountPage myAccountPage;
	private MLoginValidator loginValidator;
	
	@BeforeClass
	public void setUp() {
		loginACtions = new MLoginPage(driver);
		homePageActions = new MHomePage(driver);
		myAccountPage = new MMyAccountPage(driver);
		loginValidator = new MLoginValidator();
	}
	
	@Test (testName = "TC_01", description = "To validate Successful B2B login")
	public void validate_b2b_login() {
		homePageActions.openLoginPage();
		loginACtions.loginWithDefaultCreds();
		String contactInfoOnPage = myAccountPage.getContactInfo();
		loginValidator.validateSuccessLogin(contactInfoOnPage, Config.getProperty("username"));
		WebUtils.sleep(1);
	}
	
	@Test (testName = "TC_02", description = "To validate successfull dealer login.")
	public void validate_dealer_login() {
		
		String dealerUserName = Config.getProperty("dealerUsername");
		
		homePageActions.openDealerLoginPage();
		loginACtions.login(dealerUserName, Config.getProperty("dealerPassword"));
		String contactInfoOnPage = myAccountPage.getContactInfo();
		loginValidator.validateSuccessLogin(contactInfoOnPage, dealerUserName);
		WebUtils.sleep(1);
	}

}
