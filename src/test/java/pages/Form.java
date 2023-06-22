package pages;

import infra.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Form {

    //Setting properties
    WebDriver driver;

    //Constructors
    public Form (WebDriver driver) {
        this.driver = driver;}

    //Locators
    private By settings = By.id("settings_nav_li");
    private By forms = By.id("nav_forms");
    private By editFormFields = By.xpath("//*[@id=\"edit_fields_link_280817\"]");
    private By addFieldToForm = By.id("add_field_to_form");
    private By shortAnswerFields = By.id("add_field_btn_bottom_link_shortanswer");
    private By saveForm = By.xpath("//*[@id=\"save_form_btn\"]/span");

    //Methods
    public void clickSettings(){driver.findElement(settings).click();
        Utils.sleep(5);}
    public void clickForms(){driver.findElement(forms).click();Utils.sleep(5);}
    public void clickEditFormFields(){driver.findElement(editFormFields).click();Utils.sleep(5);}
    public void clickAddFieldToForm(){driver.findElement(addFieldToForm).click();Utils.sleep(5);}
    public void clickShortAnswerField(){driver.findElement(shortAnswerFields).click();Utils.sleep(5);}
    public void clickSaveForm(){driver.findElement(saveForm).click();Utils.sleep(5);}
}
