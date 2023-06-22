package pages;

import infra.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProjectSettings {

    //Setting properties
    WebDriver driver;

    //Constructors
    public ProjectSettings (WebDriver driver) {
        this.driver = driver;}

    //Locators
    private By settings = By.id("settings_nav_li");
    private By projectSettings = By.xpath("//*[@id=\"project_menus\"]/li/a");
    private By productSettingsTab = By.id("products_settings_tab");
    private By saveButtonNo = By.cssSelector("a[class=\"btn_clear btn_red \"]");
    private By purchaseProtection = By.id("purchase_protection_settings_tab");

    //Methods
    public void clickSettings(){driver.findElement(settings).click();
        Utils.sleep(5);}
    public void clickProjectSettings(){driver.findElement(projectSettings).click();Utils.sleep(5);}
    public void clickProductSettingsTab(){driver.findElement(productSettingsTab).click();Utils.sleep(5);}
    public void clickSaveButtonNo(){driver.findElement(saveButtonNo).click();Utils.sleep(5);}
    public void clickPurchaseProtection(){driver.findElement(purchaseProtection).click();Utils.sleep(5);}
}
