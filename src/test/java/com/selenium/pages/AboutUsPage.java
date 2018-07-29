package com.selenium.pages;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


/**
 * Created by jdw1015 on 7/25/18.
 */
public class AboutUsPage {
    WebDriver driver ;

    public AboutUsPage(WebDriver driver){this.driver= driver;
    }

    public WebDriverWait waitFor(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        return wait;
    }

    @FindBy(how = How.XPATH, using="//[@id='page-about']/body/div[2]/article/div/section[1]/div/h2")
    private WebElement aboutPageName;

    @FindBy(how = How.XPATH, using="//[@id='page-about']/body/div[2]/article/div/section[5]/div[1]/h2")
    private WebElement leadershipTitle;



    public List<String> getLeadershipNames(){
        List<String> names = new ArrayList<String>();
        Assert.assertTrue(driver.getCurrentUrl().contains("/about"));

        List<WebElement> elements = driver.findElements(By.className("mb1"));
        for (WebElement element : elements) {
            names.add(element.getText());
        }

        return names;
    }

    public List<String> getLeadershipTitles(){
        List<String> titles = new ArrayList<String>();

        Assert.assertTrue(driver.getCurrentUrl().contains("/about"));
        List<WebElement> elements = driver.findElements(By.cssSelector("a > span"));
        for (WebElement element : elements) {
            titles.add(element.getText());
        }

        return titles;
    }

    public List<String> getLeadershipBio(){
        List<String> bio = new ArrayList<String>();

        Assert.assertTrue(driver.getCurrentUrl().contains("/about"));
        List<WebElement> elements = driver.findElements(By.cssSelector("a.bio_link"));
        for (WebElement element : elements) {
            String trimString = element.getAttribute("data-description").replaceAll("<p>", "").replaceAll("</p>", "");
            bio.add(trimString);
        }

        return bio;
    }

    public void sendInfoCsv() throws IOException{

        BufferedWriter writer = Files.newBufferedWriter(Paths.get("./Leadership.csv"));
        CSVPrinter csvPrinter  = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("Name", "Title", "Bio"));
        List<String> names = getLeadershipNames();
        List<String> titles =getLeadershipTitles();
        List<String> bios =getLeadershipBio();

        Iterator<String> tempNames = names.iterator();
        Iterator<String> tempTitles = titles.iterator();
        Iterator<String> tempBios = bios.iterator();

        while (tempNames.hasNext() && tempTitles.hasNext() && tempBios.hasNext()) {
            csvPrinter.printRecord(tempNames.next(), tempTitles.next(), tempBios.next());
        }
        csvPrinter.flush();
        csvPrinter.close();
    }
}
