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

public class Products {

    //Setting properties
    WebDriver driver;

    //Constructors
    public Products (WebDriver driver) {
        this.driver = driver;}

    //Locators
    private By settings = By.id("settings_nav_li");
    private By productTab = By.cssSelector("a[href=\"/reg/admin/products/\"]");
    private By productCategory = By.id("create_new_cat_link");
    private By productCategoryName = By.id("cat_name");
    private By createCategory = By.id("add_cat_button");
    private By createProduct = By.id("create_new_unit_link");
    private By merchandise = By.xpath("//*[@id=\"base_product_type_holder_short\"]/div[5]/div/em/b");
    private By productName = By.id("product_name");
    private By price = By.xpath("//*[@id=\"product_price\"]");
    private By saveProduct = By.cssSelector("a[class=\"btn_green btn_clear btn_right product_panel_submit_btn\"]");
    private By createSession = By.xpath("//*[@id=\"base_product_type_holder_short\"]/div[3]/div/label");
    private By openDates = By.cssSelector("a[style=\"cursor: pointer;\"]");
    private By availableDate = By.id("product_start_date");
    private By expireDate = By.id("product_end_date");
    public static By getDateSelector(int day) {
        return By.cssSelector(".datepicker.showing_dialog .datepicker--cell-day[data-date='" + day + "']");
    }



    //Methods
    public void clickSetting(){driver.findElement(settings).click();
        Utils.sleep(10);}
    public void clickProductTab(){driver.findElement(productTab).click();Utils.sleep(10);}
    public void clickProductCategory(){driver.findElement(productCategory).click();Utils.sleep(10);}
    public void enterCategoryName() throws ParserConfigurationException, IOException, SAXException {driver.findElement(productCategoryName).sendKeys(getDataItem("PRODUCTCATEGORYNAME",0));Utils.sleep(10);}
    public void clickCreateCategory(){driver.findElement(createCategory).click();Utils.sleep(10);}
    public void clickCreateProduct(){driver.findElement(createProduct).click();Utils.sleep(10);}
    public void clickMerchandise(){driver.findElement(merchandise).click();Utils.sleep(10);}
    public void enterMerchandiseName() throws ParserConfigurationException, IOException, SAXException {driver.findElement(productName).sendKeys(getDataItem("PRODUCTNAME",0));Utils.sleep(10);}
    public void enterProductPrice() throws ParserConfigurationException, IOException, SAXException {driver.findElement(price).sendKeys(getDataItem("PRODUCTPRICE",0));Utils.sleep(10);}
    public void clickSaveProduct(){driver.findElement(saveProduct).click();Utils.sleep(10);}
    public void clickCreateSession(){driver.findElement(createSession).click();Utils.sleep(10);}
    public void clickOpenDate(){driver.findElement(openDates).click();Utils.sleep(10);}
    public void clickAvailableDate(){driver.findElement(availableDate).click();Utils.sleep(10);}
    public void clickExpireDate(){driver.findElement(expireDate).click();Utils.sleep(10);}
    public void selectDate(int date){driver.findElement(getDateSelector(date)).click(); Utils.sleep(10);}

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
