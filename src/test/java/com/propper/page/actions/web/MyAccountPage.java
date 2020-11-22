package com.propper.page.actions.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.propper.utils.selenium.WebUtils.click;
import static com.propper.utils.selenium.WebUtils.waitForElementPresence;


public class MyAccountPage {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyAccountPage.class);

	private WebDriver driver;

	@FindBy(how = How.CSS, using = "div.box.box-information")
	WebElement contactInfoBox;
	

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/** It will give the Contact Information present in box.
	 * @return String
	 */
	public String getContactInfo() {
		waitForElementPresence("div.box.box-information_css", 10);
		String contactInfo = contactInfoBox.getAttribute("innerText");
		
		return contactInfo;
	}
}
