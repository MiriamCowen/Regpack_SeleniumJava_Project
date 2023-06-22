package pages;

import infra.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static pages.Constants.CONFIG_XML;

public class AdminManagement {

    //Setting properties
    WebDriver driver;

    //Constructors
    public AdminManagement(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    private By settings = By.id("settings_nav_li");
    private By adminManagement = By.id("nav_admin_management");
    private By inviteNewAdmin = By.id("invite_new_admin");
    private By adminEmail = By.id("admin_email");
    private By adminFirstName = By.id("admin_first_name");
    private By adminLastName = By.id("admin_last_name");
    private By goToNextStep = By.id("admin_panel_dialog_admin_profile_add");
    private By enableAccessToProjects = By.id("all_projects_access_label");
    private By yes = By.cssSelector("a[class=\"btn_clear btn_red \"]");
    private By goToNextStep2 = By.id("admin_panel_dialog_admin_proj_next");

    //Methods
    public void clickSettings() {
        driver.findElement(settings).click();Utils.sleep(5);
    }

    public void clickAdminManagement() {
        driver.findElement(adminManagement).click();Utils.sleep(5);
    }

    public void clickInviteNewAdmin() {
        driver.findElement(inviteNewAdmin).click();Utils.sleep(5);
    }

    public void enterAdminFirstName() throws ParserConfigurationException, IOException, SAXException {
        driver.findElement(adminFirstName).sendKeys(getDataItem("ADMINFIRSTNAME", 0));Utils.sleep(5);
    }

    public void enterAdminLastName() throws ParserConfigurationException, IOException, SAXException {driver.findElement(adminLastName).sendKeys(getDataItem("ADMINLASTNAME",0));
        Utils.sleep(5);}
    public void clickGoToNextStep(){driver.findElement(goToNextStep).click();Utils.sleep(5);}
    public void clickEnableAccessToProjects(){driver.findElement(enableAccessToProjects).click();Utils.sleep(5);}
    public void clickYes(){driver.findElement(yes).click();Utils.sleep(5);}
    public void clickGoToNextSep2(){driver.findElement(goToNextStep2).click();Utils.sleep(5);}

    private static String getDataItem (String keyName, int index) throws ParserConfigurationException, IOException, SAXException, SAXException, SAXException {
        File configXmlFile = new File(Constants.CONFIG_XML);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        dBuilder = dbFactory.newDocumentBuilder();

        Document doc = null;

        assert dBuilder != null;
        doc = dBuilder.parse(configXmlFile);

        if (doc != null) {
            doc.getDocumentElement().normalize();
        }
        assert doc != null;
        return doc.getElementsByTagName(keyName).item(index).getTextContent();
    }
}






