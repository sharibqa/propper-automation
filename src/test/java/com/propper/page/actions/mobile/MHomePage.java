package com.propper.page.actions.mobile;

import static com.propper.reporter.ExtentReporter.info;
import static com.propper.utils.selenium.WebUtils.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.propper.page.actions.web.HomePage;

public class MHomePage extends HomePage {

	private WebDriver driver;

	public MHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * abountPropperLink = //div[.='ABOUT PROPPER'] dealerLoginLink = (//a[.='Dealer Login'])[2]
	 */
	@FindBy(how = How.CSS, using = "span[data-action='toggle-nav']")
	WebElement navBar;

	@FindBy(how = How.XPATH, using = "//a[.=' Account ']")
	WebElement accountTab;
	
	@FindBy(how = How.XPATH, using = "(//a[.=' My Account '])[2]")
	WebElement myAccountLink;
	
	@FindBy(how = How.XPATH, using = "//div[.='ABOUT PROPPER']")
	WebElement aboutPropperLink;
	
	@FindBy(how = How.XPATH, using = "(//a[.='Dealer Login'])[2]")
	WebElement dealerLoginLink;
	
	/**
	 * It will open the login page.
	 */
	public void openLoginPage() {
		info("Opening login page ..........");

		waitForElementPresence("span[data-action='toggle-nav']_css", 10);
		actionClick(navBar);
		waitForElementPresence("//a[.=' Account ']_xpath", 10);
		actionClick(accountTab);
		waitForElementPresence("(//a[.=' My Account '])[2]_xpath", 10);
		actionClick(myAccountLink);
	}	
	
	/**
	 * It will open the dealer login page.
	 */
	public void openDealerLoginPage() {
		info("Opening dealer login page ..........");

		waitForElementPresence("//div[.='ABOUT PROPPER']_xpath", 10);
		actionClick(aboutPropperLink);
		waitForElementPresence("(//a[.='Dealer Login'])[2]_xpath", 10);
		actionClick(dealerLoginLink);
	}

}
