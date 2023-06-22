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


public class UserManagement {

    //Setting properties
    WebDriver driver;

    //Constructors
    public UserManagement(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    private By userManagement = By.xpath("//*[@id=\"nav_users\"]");
    private By tools = By.xpath("//*[@id=\"admin_tools_btn\"]/span");
    private By createFamily = By.cssSelector("a[class=\"btn_clear btn_greywhite btn_addition btn_noborder\"]");
    private By email = By.id("create_user_username");
    private By setPassword = By.cssSelector("a[class=\"choice_box_link  \"]");
    private By userPassword = By.id("create_user_password");
    private By setUpYourMerchantAccount = By.xpath("//*[@id=\"merchant_account_issue_msg_popup\"]/a[1]");
    private By family = By.id("create_user_dialog_btn");
    private By familyPanel = By.linkText("Family");
    private By FirstName = By.id("first_name");
    private By save = By.id("save_form");
    private By createNewChild = By.id("user_panel_dialog_user_family_add");
    private By cartPanel = By.id("user_panel_cart");
    private By addProduct = By.id("user_panel_dialog_user_cart_add");
    private By recurringProduct = By.cssSelector("input[data_product_id=\"1873731\"");
    private By addChosenProduct = By.id("user_addproducts_dialog_add_products");
    private By autobillPanel = By.id("user_panel_autobill");
    private By addAutobillPlan = By.id("user_panel_dialog_user_autobill_edit");
    private By addDepositOptionPlan = By.cssSelector("a[rel=\"56019799_100908225\"]");
    private By addChosenAutobillPlan = By.id("user_addautobill_dialog_edit_autobill");
    private By paymentPanel = By.id("user_panel_payments");
    private By processPayment = By.id("user_panel_dialog_process_payment_btn");
    private By fullBalance = By.cssSelector("a[data_is_payment_due=\"0\"]");
    private By firstNameCardHolder = By.cssSelector("input[autocomplete=\"given-name\"]");
    private By lastNameCardHolder = By.cssSelector("input[autocomplete=\"family-name\"]");
    private By issuePayment = By.id("process_payment_dialog_issue");
    private By yesButton = By.cssSelector("a[class=\"btn_clear btn_red \"]");

    //Methods
    public void clickUserManagement() {
        driver.findElement(userManagement).click();
        Utils.sleep(5);
    }

    public void clickTools() {
        driver.findElement(tools).click();Utils.sleep(5);
    }

    public void clickCreateFamily() {
        driver.findElement(createFamily).click();Utils.sleep(5);
    }

    public void clickSetPassword() {
        driver.findElement(setPassword).click();Utils.sleep(5);
    }

    public void enterUserPassword() throws ParserConfigurationException, IOException, SAXException {
        driver.findElement(userPassword).sendKeys(getDataItem("PASSWORD", 2));Utils.sleep(5);
    }

    public void clickSetUpYourMerchantAccount() {
        driver.findElement(setUpYourMerchantAccount).click();Utils.sleep(5);
    }

    public void clickFamily() {
        driver.findElement(family).click();Utils.sleep(5);
    }

    public void clickFamilyPanel() {
        driver.findElement(familyPanel).click(); Utils.sleep(5);
    }

    public void enterParentFirstName() throws ParserConfigurationException, IOException, SAXException {
        driver.findElement(FirstName).sendKeys(getDataItem("FIRSTNAME", 0));Utils.sleep(5);
    }

    public void saveFirstName() {
        driver.findElement(save).click();Utils.sleep(5);
    }

    public void clickCreateNewChild() {
        driver.findElement(createNewChild).click();Utils.sleep(5);
    }

    public void enterChildFirstName() throws ParserConfigurationException, IOException, SAXException {
        driver.findElement(FirstName).sendKeys(getDataItem("FIRSTNAME", 1)); Utils.sleep(5);
    }

    public void clickCartPanel() {
        driver.findElement(cartPanel).click(); Utils.sleep(5);
    }

    public void clickAddProduct() {
        driver.findElement(addProduct).click(); Utils.sleep(5);
    }

    public void clickRecurringProduct() {
        driver.findElement(recurringProduct).click();Utils.sleep(5);
    }

    public void clickAddChosenProduct() {
        driver.findElement(addChosenProduct).click();Utils.sleep(5);
    }

    public void clickAddAutobillPanel() {
        driver.findElement(autobillPanel).click();Utils.sleep(5);
    }

    public void clickAddAutobillPlan() {
        driver.findElement(addAutobillPlan).click();Utils.sleep(5);
    }

    public void clickAddDepositOptionPlan() {
        driver.findElement(addDepositOptionPlan).click(); Utils.sleep(5);
    }

    public void clickAddChosenAutobillPlan() {
        driver.findElement(addChosenAutobillPlan).click(); Utils.sleep(5);
    }

    public void clickPaymentPanel() {
        driver.findElement(paymentPanel).click(); Utils.sleep(5);
    }

    public void clickProcessPayment() {
        driver.findElement(processPayment).click(); Utils.sleep(5);
    }

    public void clickFullBalance() {
        driver.findElement(fullBalance).click(); Utils.sleep(5);
    }

    public void enterFirstNameCardHolder() throws ParserConfigurationException, IOException, SAXException {
        driver.findElement(firstNameCardHolder).sendKeys(getDataItem("FIRSTNAMECARDHOLDER", 0)); Utils.sleep(5);
    }

    public void enterLastNameCardHolder() throws ParserConfigurationException, IOException, SAXException {
        driver.findElement(lastNameCardHolder).sendKeys(getDataItem("LASTNAMECARDHLDER", 0)); Utils.sleep(5);
    }

    public void clickIssuePayment() {
        driver.findElement(issuePayment).click(); Utils.sleep(5);
    }

    public void clickYesButton() {
        driver.findElement(yesButton).click(); Utils.sleep(5);
    }


    private static String getDataItem(String keyName, int index) throws ParserConfigurationException, IOException, SAXException, SAXException, SAXException {
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
