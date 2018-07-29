package com.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by jdw1015 on 7/25/18.
 */
public class HomePage {
    WebDriver driver ;

    public HomePage(WebDriver driver){
        this.driver= driver;
    }

    @FindBy(how = How.LINK_TEXT, using ="About MailChimp")
    private WebElement aboutLink;

    @FindBy(how = How.LINK_TEXT, using="Sign Up Free")
    private WebElement signUp;


    public AboutUsPage goToAboutPage(){
        Assert.assertTrue(driver.getCurrentUrl().contains("mailchimp"));

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(signUp));

        aboutLink.click();
        return new AboutUsPage(driver);
    }
}
