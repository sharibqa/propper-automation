package com.propper.tests.web;

import com.propper.core.BaseConfiguration;
import com.propper.page.actions.web.PropperCheckout;
import com.propper.page.validators.web.LoginValidator;
import com.propper.reporter.ExtentReporter;
import com.propper.utils.selenium.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutTest.class);

    private PropperCheckout proppercheckout;
    private LoginValidator loginValidator;

    @BeforeMethod
    public void setup() {
        proppercheckout = new PropperCheckout(driver);
        loginValidator = new LoginValidator();
    }

    @Test(testName = "TC_01", description = "To validate Successful B2B checkout")
    public void validate_checkout() throws InterruptedException {
        proppercheckout.closePopUp();
        proppercheckout.setQuantity("1");
        proppercheckout.addtoCart();
        proppercheckout.cartCheckout();
        proppercheckout.enterAddress();
        proppercheckout.payment();
        proppercheckout.orderPage();
        String orderNumber=proppercheckout.getOrderNumber();
        loginValidator.validateSuccessLogin(orderNumber,"Your order");
        }

    @Test(testName = "TC_02", description = "To validate Successful B2B checkout with qty 3")
    public void validate_checkout_with_qty_three() throws InterruptedException {
        proppercheckout.closePopUp();
        proppercheckout.setQuantity("3");
        proppercheckout.addtoCart();
        proppercheckout.cartCheckout();
        proppercheckout.enterAddress();
        proppercheckout.payment();
        proppercheckout.orderPage();
        loginValidator.validateSuccessCheckout(proppercheckout.getOrderNumber(),"Your order");
    }
    @AfterMethod
    public void tearDown()
    {
        WebUtils.terminateBrowser();
    }
}
