package com.propper.page.actions.web;

import static com.propper.reporter.ExtentReporter.info;
import static com.propper.utils.selenium.WebUtils.click;
import static com.propper.utils.selenium.WebUtils.waitForElementPresence;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/** This class contains all actions/operations that will be performed on Home page in Desktop web view.
 * 
 * @author Jaikant.lnu
 *
 */
public class HomePage {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomePage.class);

	private WebDriver driver;

	@FindBy(how = How.CSS, using = "li.authorization-link")
	WebElement headerLoginLink;
	
	@FindBy(how = How.XPATH, using = "//a[.='Dealer Login' and not(@title)]")
	WebElement dealerLoginLink;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	/**
	 * It will open the login page.
	 */
	public void openLoginPage() {
		info("Opening login page ..........");

		waitForElementPresence("li.authorization-link_css", 10);
		click(headerLoginLink);
	}	
	
	/**
	 * It will open the login page.
	 */
	public void openDealerLoginPage() {
		info("Opening dealer login page ..........");

		waitForElementPresence("//a[.='Dealer Login' and not(@title)]_xpath", 10);
		click(dealerLoginLink);
	}

}
