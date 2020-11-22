package com.propper.tests.web;

import com.propper.core.BaseConfiguration;
import com.propper.page.actions.web.HomePage;
import com.propper.page.actions.web.LoginPage;
import com.propper.page.actions.web.MyAccountPage;
import com.propper.page.actions.web.PropperCheckout;
import com.propper.page.validators.web.LoginValidator;
import com.propper.utils.common.Config;
import com.propper.utils.selenium.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class BrokenLinksTest extends BaseConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrokenLinksTest.class);
    private HomePage homePageActions;
    private PropperCheckout proppercheckout;

    @BeforeMethod
    public void setUp() {
        homePageActions = new HomePage(driver);
        proppercheckout = new PropperCheckout(driver);
      }

    @Test(testName = "TC_01", description = "To validate all links")
    public void validate_b2b_login() {
        /*proppercheckout.closePopUp();
        WebUtils.getAllLinks();*/
        System.out.println("Test file");
    }

    @AfterMethod
    public void tearDown() throws IOException {
        WebUtils.updateBrokenLinksDetails();
        WebUtils.terminateBrowser();
    }

}
