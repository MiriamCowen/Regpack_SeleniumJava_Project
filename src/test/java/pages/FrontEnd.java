package pages;

import infra.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static pages.Constants.CONFIG_XML;

public class FrontEnd {

    //Setting properties
    WebDriver driver;

    //Constructors
    public FrontEnd(WebDriver driver) {
        this.driver = driver;}

        //Locators
        private By frontEnd = By.id("project_front_end_view");
        private By frontEndLogin = By.id("_main_login_btn");
        private By frontEndEmail = By.id("_main_email");
        private By frontEndPassword = By.id("_lg_password");
        private By login = By.id("_main_submit_btn");
        private By home = By.id("f_280817_3403932_op1");
        private By dashboard = By.id("dashboard_top_link");
        private By addProfile = By.id("new_member_link");
        private By goToNextStep = By.id("_main_submit_btn");
        private By dontAddAnotherChild = By.cssSelector("a[class=\"btn_cancel \"]");

        //Methods
        public void clickFrontEnd(){driver.findElement(frontEnd).click();}
        public void clickFrontEndLogin(){
            goNewTab(1);
            //goNewTab(0);(go back to backend)
            goToIframe();
            driver.findElement(frontEndLogin).click();}

    public void goToIframe () {
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);
    }


    private void goNewTab(int tabIndex) {
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(tabIndex));
    }

    public void enterUserEmail() throws ParserConfigurationException, IOException, SAXException {driver.findElement(frontEndEmail).sendKeys(getDataItem("EMAIL",0));Utils.sleep(20);}
        public void enterUserPassword() throws ParserConfigurationException, IOException, SAXException {driver.findElement(frontEndPassword).sendKeys(getDataItem("PASSWORD",2));Utils.sleep(20);}
        public void clickLogin(){driver.findElement(login).click();Utils.sleep(20);}
        public void clickHome(){driver.findElement(home).click();Utils.sleep(20);}
        public void clickDashboard(){driver.findElement(dashboard).click();Utils.sleep(20);}
        public void clickAddProfile(){driver.findElement(addProfile).click();Utils.sleep(20);}
        public void clickGoToNextStep(){driver.findElement(goToNextStep).click();Utils.sleep(20);}
        public void clickDontAddAnotherChild(){driver.switchTo().defaultContent();driver.findElement(dontAddAnotherChild).click();
            Utils.sleep(20);
        }






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

