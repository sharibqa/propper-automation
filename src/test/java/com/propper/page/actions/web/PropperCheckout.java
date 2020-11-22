package com.propper.page.actions.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.propper.utils.selenium.WebUtils.*;

public class PropperCheckout {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropperCheckout.class);
    private final WebDriver driver;
    @FindBy(how = How.XPATH, using = "(//button[@type='submit'])[2]")
    WebElement addToCartButton;
    @FindBy(how = How.XPATH, using = "(//button[@class='action primary checkout'])[2]")
    WebElement checkoutButton;
    @FindBy(how = How.XPATH, using = "//*[local-name()='svg']/ancestor::a[@title='Close']")
    WebElement closeButton;
    @FindBy(how = How.XPATH, using = "//input[@id='qty']")
    WebElement qtyInputBox;
    @FindBy(how = How.XPATH, using = "(//input[@id='customer-email'])[1]")
    WebElement email;
    @FindBy(how = How.XPATH, using = "(//input[@name='firstname'])[1]")
    WebElement firstName;
    @FindBy(how = How.XPATH, using = "(//input[@name='lastname'])[1]")
    WebElement lastName;
    @FindBy(how = How.XPATH, using = "//input[@name='street[0]']")
    WebElement addressLine1;
    @FindBy(how = How.XPATH, using = "//input[@name='city']")
    WebElement city;
    @FindBy(how = How.XPATH, using = "//select[@name='region_id']")
    WebElement state;
    @FindBy(how = How.XPATH, using = "//input[@name='postcode']")
    WebElement zipCode;
    @FindBy(how = How.XPATH, using = "//input[@name='telephone']")
    WebElement telephone;
    @FindBy(how = How.XPATH, using = "(//tr[@class='row'])[1]")
    WebElement shipping;
    @FindBy(how = How.XPATH, using = "//button[@class='button action continue primary']")
    WebElement next;
    @FindBy(how = How.XPATH, using = "//input[@id='checkmo']")
    WebElement paymentType;
    @FindBy(how = How.XPATH, using = "//div[@jsname='v2vpaf']")
    WebElement feedback;
    @FindBy(how = How.XPATH, using = "//button[@jsname='cEjotb']")
    WebElement yesFeedback;
    @FindBy(how = How.XPATH, using = "(//iframe[contains(@src,\"shopping\")])[2]")
    WebElement reviews;
    @FindBy(how = How.XPATH, using = "//span[contains(text(),'YES')]")
    WebElement yesButton;
    @FindBy(how = How.XPATH, using = "//div[@class='checkout-success']/p[1]")
    WebElement orderNumber;


    public PropperCheckout(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setQuantity(String qty) {
        waitForElementPresence("//input[@id='qty']_xpath", 30);
        clearTextBox("//input[@id='qty']_xpath");
        enterTxt(qtyInputBox, qty);

    }

    public void addtoCart() {
        sleep(5);
        click(addToCartButton);
        sleep(5);
        switchToTab();
    }

    public void closePopUp() {
        waitForElementPresence("//*[local-name()='svg']/ancestor::a[@title='Close']_xpath", 30);
       click(closeButton);
        }

    public void cartCheckout() {

        waitForElementPresence("(//button[@class='action primary checkout'])[2]_xpath", 10);
        WebElement nextElement = scrollingToElementofAPage(checkoutButton);
        click(checkoutButton);
        //click(checkoutButton);
    }

    public void enterAddress() throws InterruptedException {
        Thread.sleep(8000l);
        waitForElementPresence("(//input[@id='customer-email'])[1]_xpath",30);
        enterTxt(email, "test" + generateRandomNoInDateAndTime() + "@test.com");
        enterTxt(firstName, "qa Test");
        enterTxt(lastName, "qa last");
        enterTxt(addressLine1, "1139 N Jackson St Apt 325");
        enterTxt(city, "Milwaukee");
        //click(state);
        scrollingToElementofAPage(state);
        selectDropDownByIndex(state, 64);
        enterTxt(zipCode, "53202");
        enterTxt(telephone, "8055062404");
        waitForElementPresence("(//tr[@class='row'])[1]_xpath",30);
        WebElement nextElement = scrollingToElementofAPage(next);
        jsClick(shipping);
        Thread.sleep(7000l);
        waitForElementPresence("//button[@class='button action continue primary']_xpath");
        click(nextElement);
        Thread.sleep(7000l);

    }

    public void payment() throws InterruptedException {
        waitForElementPresence("(//div[@class='payment-method-title field choice'])[2]_xpath", 20);
        click(paymentType);
        Thread.sleep(7000l);
        waitForElementPresence("(//button[@class='action primary checkout'])[2]_xpath", 20);
        WebElement nextElement = scrollingToElementofAPage(checkoutButton);
        click(nextElement);
        Thread.sleep(7000l);
    }

    public void orderPage() throws InterruptedException {
        Thread.sleep(8000l);
        switchToFrame(reviews);
        jsClick(yesButton);
    }

    public String getOrderNumber() throws InterruptedException {
        Thread.sleep(8000l);
        switchToDefaultFrame();
        String orderdetails = getText(orderNumber);
        System.out.println(orderdetails);
        return orderdetails;

    }

}
