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

public class Login {

    //Setting properties
    WebDriver driver;

    //Constructors
    public Login(WebDriver driver) {
        this.driver = driver;}

    //Locators
    private By loginEmail = By.id("lg_email");
    private By password = (By.id("lg_password"));
    private By error = (By.xpath("//*[@id=\"lb_login_messaging_bottom\"]/div/a"));
    private By submit = (By.id("login_submit"));
    private By projectOnPage = (By.id("projects_nav"));
    private By viewAll = By.xpath("//*[@id=\"project_nav_view_all\"]");
    private By searchProject = (By.cssSelector("input[placeholder=\"Enter the Project ID, Project Name or Org Name \"]"));
    private By chooseProject = (By.xpath("//*[@id=\"projects_nav_all_dialog_info\"]/div/div[2]"));
    //Methods
    public void enterLoginEmail() throws ParserConfigurationException, IOException, SAXException {driver.findElement(loginEmail).sendKeys(getDataItem("USERNAME", 0));
        Utils.sleep(5);}
    public void enterWrongPassword () throws ParserConfigurationException, IOException, SAXException {driver.findElement(password).sendKeys(getDataItem("PASSWORD",1));Utils.sleep(5);}
    public void clickOutOfError (){driver.findElement(error).click();Utils.sleep(7);}
    public void enterPassword () throws ParserConfigurationException, IOException, SAXException {driver.findElement(password).sendKeys(getDataItem("PASSWORD",0));Utils.sleep(5);}
    public void clearPassword (){driver.findElement(password).clear();Utils.sleep(5);}
    public void clickSubmit (){driver.findElement(submit).click();Utils.sleep(20);}
    public void clickProjectOnPage () {driver.findElement(projectOnPage).click();Utils.sleep(20);}
    public void clickViewAll (){driver.findElement(viewAll).click();Utils.sleep(20);}
    public void enterProjectNumber () throws ParserConfigurationException, IOException, SAXException {driver.findElement(searchProject).sendKeys(getDataItem("PROJECTNUM",0));Utils.sleep(5);}
    public void enterProjectNumber2 () throws ParserConfigurationException, IOException, SAXException {driver.findElement(searchProject).sendKeys(getDataItem("PROJECTNUM",1));Utils.sleep(5);}
    public void clickChooseProject () {driver.findElement(chooseProject).click();Utils.sleep(5);}
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

    private static String getData (String keyName) throws ParserConfigurationException, IOException, SAXException {
        String CONFIG_FILE_LOCATION;
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
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }

}
