package com.propper.tests.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.propper.core.BaseConfiguration;
import com.propper.page.actions.web.HomePage;
import com.propper.page.actions.web.LoginPage;
import com.propper.page.actions.web.MyAccountPage;
import com.propper.page.validators.web.LoginValidator;
import com.propper.utils.common.Config;

public class LoginTest extends BaseConfiguration{

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginTest.class);
	
	private LoginPage loginACtions;
	private HomePage homePageActions;
	private MyAccountPage myAccountPage;
	private LoginValidator loginValidator;
	
	@BeforeClass
	public void setUp() {
		loginACtions = new LoginPage(driver);
		homePageActions = new HomePage(driver);
		myAccountPage = new MyAccountPage(driver);
		loginValidator = new LoginValidator();
	}
	
	@Test (testName = "TC_01", description = "To validate Successful B2B login")
	public void validate_b2b_login() {
		homePageActions.openLoginPage();
		loginACtions.loginWithDefaultCreds();
		String contactInfoOnPage = myAccountPage.getContactInfo();
		loginValidator.validateSuccessLogin(contactInfoOnPage, Config.getProperty("username"));
	}
	
	@Test (testName = "TC_02", description = "To validate successfull dealer login.")
	public void validate_dealer_login() {
		
		String dealerUserName = Config.getProperty("dealerUsername");
		// act
		homePageActions.openDealerLoginPage();
		loginACtions.login(dealerUserName, Config.getProperty("dealerPassword"));
		String contactInfoOnPage = myAccountPage.getContactInfo();
		loginValidator.validateSuccessLogin(contactInfoOnPage, dealerUserName);
	}
	
}
