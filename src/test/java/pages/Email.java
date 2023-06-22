package pages;

import infra.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Email {


    //Setting properties
    WebDriver driver;

    //Constructors
    public Email (WebDriver driver) {
        this.driver = driver;}

    //Locators
    private By settings = By.id("settings_nav_li");
    private By emailTab = By.id("nav_emails");
    private By editSubjectBody = By.xpath("//*[@id=\"edit_fields_link_100908225_40731533\"]");

    //Methods
    public void clickSettings(){driver.findElement(settings).click();
        Utils.sleep(5);}
    public void clickEmailTab(){driver.findElement(emailTab).click();Utils.sleep(5);}
    public void clickEditSubjectBody(){driver.findElement(editSubjectBody).click();Utils.sleep(5);}

}
