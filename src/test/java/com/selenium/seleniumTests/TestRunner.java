package com.selenium.seleniumTests;

import com.selenium.pages.AboutUsPage;
import com.selenium.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.io.IOException;

/**
 * Created by jdw1015 on 7/25/18.
 */
public class TestRunner {
    public WebDriver driver = new ChromeDriver();
    HomePage homePage;
    AboutUsPage aboutUs;

    @BeforeTest
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver", "/src/test/resources/chromedriver.js");

        driver.get("https://mailchimp.com/");
        homePage = PageFactory.initElements(driver, HomePage.class);
        aboutUs = PageFactory.initElements(driver, AboutUsPage.class);
    }

    @Test
    public void aboutUsTest() throws InterruptedException, IOException{
        homePage.goToAboutPage();
        aboutUs.sendInfoCsv();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
