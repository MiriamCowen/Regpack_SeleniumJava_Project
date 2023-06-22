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

public class Autobill {

    //Setting properties
    WebDriver driver;

    //Constructors
    public Autobill (WebDriver driver) {
        this.driver = driver;}

    //Locators
    private By settings = By.id("settings_nav_li");
    private By autobillPanel = By.id("nav_autobill");
    private By autobillSnapshotArrow = By.cssSelector("a[class=\"category_toggle_link\"]");
    private By createNewAutobillPlan = By.id("create_new_unit_link");
    private By planName = By.xpath("//*[@id=\"plan_name\"]");
    private By goToNextStep = By.id("plan_panel_dialog_autobill_plan_settings_add");
    private By updatePaymentSchedule = By.id("plan_panel_dialog_autobill_plan_sch_generate");

    //Methods
    public void clickSettings(){driver.findElement(settings).click();
        Utils.sleep(5);}
    public void clickAutobillPanel(){driver.findElement(autobillPanel).click();Utils.sleep(5);}
    public void clickAutobillSnapshot(){driver.findElement(autobillSnapshotArrow).click();Utils.sleep(5);}
    public void clickCreateNewAutobillPlan(){driver.findElement(createNewAutobillPlan).click();Utils.sleep(5);}
    public void enterPlanName() throws ParserConfigurationException, IOException, SAXException {driver.findElement(planName).sendKeys(getDataItem("AUTOBILLPLANNAME",0));Utils.sleep(5);}
    public void clickGoToNextStep(){driver.findElement(goToNextStep).click();Utils.sleep(5);}
    public void clickupdatePaymentSchedule(){driver.findElement(updatePaymentSchedule).click();Utils.sleep(5);}


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
