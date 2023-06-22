package pages;

import infra.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public class Reports {

    //Setting properties
    WebDriver driver;

    //Constructors
    public Reports (WebDriver driver) {
        this.driver = driver;}

    //Locators
    private By settings = By.id("settings_nav_li");
    private By reportsTab = By.id("nav_reports");
    private By editFields = By.xpath("//*[@id=\"edit_fields_link_10090822564995185\"]");
    private By xOut = By.cssSelector("a[class=\"sidepanel_close\"]");


    //Methods
    public void clickSetting(){driver.findElement(settings).click();
        Utils.sleep(5);}
    public void clickReportTab(){driver.findElement(reportsTab).click();Utils.sleep(5);}
    public void clickEditFields(){driver.findElement(editFields).click();Utils.sleep(5);}
    public void clickXOUT(){driver.findElement(xOut).click();Utils.sleep(5);}
}
