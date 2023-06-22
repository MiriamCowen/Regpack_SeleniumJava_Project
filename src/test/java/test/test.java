package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.experimental.categories.Categories;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import pages.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class test {

    private static WebDriver driver;
    Login login = new Login(driver);
    UserManagement userManagement = new UserManagement(driver);
    Payments payments = new Payments(driver);
    Products products = new Products(driver);
    Reports reports = new Reports(driver);
    Email email = new Email(driver);
    Form form = new Form(driver);
    Autobill autobill = new Autobill(driver);
    ProjectSettings projectSettings = new ProjectSettings(driver);
    AdminManagement adminManagement = new AdminManagement(driver);
    FrontEnd frontEnd = new FrontEnd(driver);

    private static ExtentReports extentReports = new ExtentReports();
    private static ExtentSparkReporter reporter = new ExtentSparkReporter("target/Spark/report.html");

    @BeforeClass
    public static void beforeTests() throws IOException, SAXException, ParserConfigurationException {
        System.out.println("startAllTests");
        System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_LOCATION);
        driver = new ChromeDriver();
        System.out.println("new driver");
        driver.manage().window().maximize();
        extentReports.attachReporter(reporter);
        //driver.manage().deleteAllCookies();
        // driver.get(getDataItem("URL", 0));
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
        driver.manage().timeouts().implicitlyWait(22, TimeUnit.SECONDS);
    }

    @Test
    public void validateLogin() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        try {
            driver.get(getDataItem("URL", 0));
            Thread.sleep(2000);
            login.enterLoginEmail();
            Thread.sleep(2000);
            String expectedMail = "miriam+1@regpacks.com";
            String actualMail = driver.findElement(By.id("lg_email")).getAttribute("value");
            Thread.sleep(2000);
            System.out.println("mail = " + actualMail);
            if (expectedMail.equals(actualMail))
                System.out.println("Step insert mail passed");
            else System.out.println("Step insert mail failed");
            login.enterPassword();
            Thread.sleep(2000);
            String pass = driver.findElement(By.id("lg_password")).getAttribute("value");
            Thread.sleep(2000);
            System.out.println("pass = " + pass);
            login.clickSubmit();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void alogin() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        driver.get(getDataItem("URL", 0));
        //report
        ExtentTest loginTest1 = extentReports.createTest("Login test 1");
        loginTest1.log(Status.INFO, "Login to the site with wrong password.");
        login.enterLoginEmail();
        //Verify that the system won't allow you to log in with the wrong password.
        login.enterWrongPassword();
        login.clickSubmit();
        loginTest1.fail("Incorrect password", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\" + " Login test 1")).build());
        login.clickOutOfError();
        //Login with correct password.
        ExtentTest loginTest2 = extentReports.createTest(" Login test 2");
        loginTest2.log(Status.INFO, "Login to the site with correct password");
        login.clearPassword();
        login.enterPassword();
        String pass = driver.findElement(By.id("lg_password")).getText();
        System.out.println("pass = " + pass);
        login.clickSubmit();
        //Go to the sandbox project. - screenshot showing you logged into the project.
        login.clickProjectOnPage();
        login.clickViewAll();
        //Go to sandbox project
        try {
            login.enterProjectNumber();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        login.clickChooseProject();
        loginTest2.pass("Login test passed", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\" + " Login test 2")).build());
    }

    @Ignore
    @Test
    public void buserManagement() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        //report
        ExtentTest UserManagementTest1 = extentReports.createTest("User management test 1");
        UserManagementTest1.log(Status.INFO, "Issue a payment without entering in cardholder information.");
        //Click on user management tab.
        userManagement.clickUserManagement();
        userManagement.clickTools();
        // Create a family - email + password
        userManagement.clickCreateFamily();
        driver.findElement(By.id("create_user_username")).sendKeys(email1());
        userManagement.clickSetPassword();
        userManagement.enterUserPassword();
        userManagement.clickFamily();
        userManagement.clickSetUpYourMerchantAccount();
        userManagement.clickFamilyPanel();
        //Add first name of parent and save
        userManagement.enterParentFirstName();
        userManagement.saveFirstName();
        //Create subunit
        userManagement.clickCreateNewChild();
        //Add first name of child and save.
        userManagement.enterChildFirstName();
        userManagement.saveFirstName();
        //Hover over to product panel.
        userManagement.clickCartPanel();
        //Add a product to the subunit.
        userManagement.clickAddProduct();
        userManagement.clickRecurringProduct();
        userManagement.clickAddChosenProduct();
        //Hover over to Autobill panel.
        userManagement.clickAddAutobillPanel();
        // Add an autobill plan.
        userManagement.clickAddAutobillPlan();
        userManagement.clickAddDepositOptionPlan();
        userManagement.clickAddChosenAutobillPlan();
        //Hover over to Payment panel.
        userManagement.clickPaymentPanel();
        //Click process payment and choose full balance
        userManagement.clickProcessPayment();
        //Try to make a payment without cardholder name.
        userManagement.clickFullBalance();
        userManagement.clickIssuePayment();
        UserManagementTest1.fail("Missing name", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\" + " User management test 1")).build());
        //Make a payment with cardholder name.
        userManagement.enterFirstNameCardHolder();
        userManagement.enterLastNameCardHolder();
        ExtentTest UserManagementTest2 = extentReports.createTest("User management test 2");
        UserManagementTest2.log(Status.INFO, "Issue a payment after entering in card holder information.");
        userManagement.clickIssuePayment();
        userManagement.clickYesButton();
        //session date
       // userManagement.selectDate(3);
        //Make a payment. - screenshot showing payment was processed successfully.
        UserManagementTest2.pass("User management test passed.", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\" + " User management test 2")).build());
    }

    @Ignore
    @Test
    public void cpayments() throws InterruptedException, IOException {
        //report
        ExtentTest PaymentTabTest1 = extentReports.createTest("Payment tab test 1");
        PaymentTabTest1.log(Status.INFO, "Check payment tab.");
        //Click on payments tab.
        payments.clickPayments();
        payments.clickPaymentsTab();
        //Hover from payments to upcoming charges.
        payments.clickUpcomingCharges();
        //Click has a payment method.
        payments.clickHasPaymentMethod();
        payments.clickPaymentsTab();
        // Filter for last month.
        payments.clickLastMonth();
        //Clear filter.
        payments.clickClearFilter();
        PaymentTabTest1.pass("Payment tab test passed.", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\" + " Payment tab test 1")).build());
    }

    @Test
    public void dproducts() throws IOException, InterruptedException, ParserConfigurationException, SAXException {
        //report
        ExtentTest ProductTabTest1 = extentReports.createTest("Product tab test 1");
        ProductTabTest1.log(Status.INFO, "Create merchandise product.");
        products.clickSetting();
        //Click on products tab.
        products.clickProductTab();
        //Create new category.
        products.clickProductCategory();
        products.enterCategoryName();
        products.clickCreateCategory();
        //Create merchandise product.
        products.clickCreateProduct();
        products.clickMerchandise();
        products.enterMerchandiseName();
        products.enterProductPrice();
        products.clickSaveProduct();
        products.clickCreateProduct();
        products.clickCreateSession();
        products.clickOpenDate();
        products.clickAvailableDate();
        products.selectDate(7);
        products.clickExpireDate();
        products.selectDate(15);
        //Put in name, price and save. - screenshot of product created succesfully.
        ProductTabTest1.pass("Product is created successfully.", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\" + " Product tab test 1")).build());
    }

    @Ignore
    //Validation of product
    @Test
    public void validateProduct() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        try {
            Thread.sleep(10000);
            products.clickSetting();
            Thread.sleep(5000);
            products.clickProductTab();
            Thread.sleep(5000);
            products.clickCreateProduct();
            Thread.sleep(5000);
            products.clickMerchandise();
            Thread.sleep(5000);
            products.enterMerchandiseName();
            String expectedName = "merchandise event";
            String actualName = driver.findElement(By.id("product_name")).getAttribute("value");
            System.out.println("Name = " + actualName);
            if (expectedName.equals(actualName))
                System.out.println("name passed");
            else System.out.println("name failed");
            String expectedPrice = "235";
            String actualPrice = driver.findElement(By.xpath("//*[@id=\"product_price\"]")).getAttribute("value");
            System.out.println("Price = " + actualPrice);
            if (expectedPrice.equals(actualPrice))
                System.out.println("price passed");
            else System.out.println("price failed");
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Ignore
    @Test
    public void ereports() throws InterruptedException, IOException {
        //report
        ExtentTest ReportTest1 = extentReports.createTest("Edit report.");
        ReportTest1.log(Status.INFO, "Click on edit report and x out.");
        reports.clickSetting();
        //Click on report tab.
        reports.clickReportTab();
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//*[@id=\"update_item_link_10090822564995185\"]"))).perform();
        //Click on edit report fields.
        reports.clickEditFields();
        //X out.
        reports.clickXOUT();
        ReportTest1.pass("Edit report opens successfully.", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\" + " Report test 1")).build());
    }

    @Ignore
    @Test
    public void femails() throws InterruptedException, IOException {
        //report
        ExtentTest EmailTest1 = extentReports.createTest("Email tab.");
        EmailTest1.log(Status.INFO, "Email tab");
        email.clickSettings();
        //Click on email tab.
        email.clickEmailTab();
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//*[@id=\"update_item_link_100908225_40731533\"]"))).perform();
        //Click edit subject body.
        email.clickEditSubjectBody();
        EmailTest1.pass("Edit report opens successfully.", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\" + " Email test 1")).build());

    }

    @Ignore
    @Test
    public void gforms() throws InterruptedException, IOException {
        //report
        ExtentTest FormTest1 = extentReports.createTest("Form tab.");
        FormTest1.log(Status.INFO, "Form tab");
        form.clickSettings();
        // Click on form tab.
        form.clickForms();
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//*[@id=\"form_280817\"]/div[5]"))).perform();
        // Edit form fields.
        form.clickEditFormFields();
        form.clickAddFieldToForm();
        Actions action2 = new Actions(driver);
        action2.moveToElement(driver.findElement(By.id("field_link_shortanswer"))).perform();
        //Add a short answer field.
        form.clickShortAnswerField();
        // Save.
        form.clickSaveForm();
        FormTest1.pass("Form saves successfully.", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\" + " Form test 1")).build());
    }

    @Ignore
    @Test
    public void hautobill() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        //report
        ExtentTest AutobillTest1 = extentReports.createTest("Autobill tab.");
        AutobillTest1.log(Status.INFO, "Autobill tab.");
        autobill.clickSettings();
        //Click on autobill tab.
        autobill.clickAutobillPanel();
        //Open and close snapshot.
        autobill.clickAutobillSnapshot();
        autobill.clickAutobillSnapshot();
        //Create a new autobill plan.
        autobill.clickCreateNewAutobillPlan();
        autobill.enterPlanName();
        autobill.clickGoToNextStep();
        autobill.clickupdatePaymentSchedule();
        AutobillTest1.pass("Autobill saves successfully.", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\" + " Autobill test 1")).build());
    }

    @Ignore
    @Test
    public void iprojectsettings() throws InterruptedException, IOException {
        //report
        ExtentTest ProjectSettingsTest1 = extentReports.createTest("Project Settings tab.");
        ProjectSettingsTest1.log(Status.INFO, "Project Settings tab.");
        projectSettings.clickSettings();
        //Click on project settings tab.
        projectSettings.clickProjectSettings();
        //Hover over to product settings.
        projectSettings.clickProductSettingsTab();
        //projectSettings.clickSaveButtonNo();
        projectSettings.clickPurchaseProtection();
        ProjectSettingsTest1.pass("Moving to product settings tab.", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\" + " Project settings test 1")).build());

    }

    @Ignore

    @Test
    public void jadminmanagement() throws InterruptedException, IOException, ParserConfigurationException, SAXException {
        //report
        ExtentTest AdminManagementTest1 = extentReports.createTest("Admin management tab.");
        AdminManagementTest1.log(Status.INFO, "Admin management tab.");
        // Move to project with admin management tab.
        Thread.sleep(2000);
        login.clickProjectOnPage();
        Thread.sleep(2000);
        login.clickViewAll();
        Thread.sleep(2000);
        login.enterProjectNumber2();
        Thread.sleep(2000);
        login.clickChooseProject();
        Thread.sleep(2000L);
        adminManagement.clickSettings();
        //Click on admin management tab.
        adminManagement.clickAdminManagement();
        adminManagement.clickInviteNewAdmin();
        //Create a new admin. Save all info in config.xml using send keys...get data item...
        driver.findElement(By.id("admin_email")).sendKeys(email2());
        adminManagement.enterAdminFirstName();
        adminManagement.enterAdminLastName();
        adminManagement.clickGoToNextStep();
        //Enable access to all projects and invite.
        adminManagement.clickEnableAccessToProjects();
        adminManagement.clickYes();
        adminManagement.clickGoToNextSep2();
        AdminManagementTest1.pass("Inviting admin.", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\" + " Admin management test 1")).build());
    }

    @Test
    public void kfrontend() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        //report
        ExtentTest FrontEndTest1 = extentReports.createTest("Front end test.");
        FrontEndTest1.log(Status.INFO, "Front end test.");
        Thread.sleep(5000);
        login.clickProjectOnPage();
        Thread.sleep(5000);
        login.clickViewAll();
        Thread.sleep(5000);
        login.enterProjectNumber();
        Thread.sleep(5000);
        login.clickChooseProject();
        Thread.sleep(5000);
        login.clickProjectOnPage();
        Thread.sleep(5000);
        frontEnd.clickFrontEnd();
        Thread.sleep(5000);
        //Click on login.
        frontEnd.clickFrontEndLogin();
        Thread.sleep(5000);
//        frontEnd.clickFrontEndLogin();
//        Thread.sleep(5000);
        //Login with existing user password and username.
        frontEnd.enterUserEmail();
        frontEnd.enterUserPassword();
        frontEnd.clickLogin();
        FrontEndTest1.pass("Logging into front end.", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\screenshots\\" + " Front end test 1")).build());
        frontEnd.clickHome();
        frontEnd.clickDashboard();
        frontEnd.clickAddProfile();
        frontEnd.clickGoToNextStep();
        frontEnd.clickGoToNextStep();
        frontEnd.clickGoToNextStep();
        frontEnd.clickDontAddAnotherChild();
        frontEnd.goToIframe();
        frontEnd.clickDashboard();

    }

    @AfterClass
    public static void afterclass() throws InterruptedException, IOException {
        System.out.println("after class");
        driver.quit();
        extentReports.flush();
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

    private static String takeScreenShot(String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath + ".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath + ".png";
    }


    public static String email1() {
        Date d1 = new Date();
        Long l1 = new Long(d1.getTime());
        String newMail1 = l1.toString().concat("@gmail.com");
        System.out.println(newMail1);

        return newMail1;
    }


    public static String email2() {
        Date d2 = new Date();
        Long l2 = new Long(d2.getTime());
        String newMail2 = l2.toString().concat("@gmail.com");
        System.out.println(newMail2);

        return newMail2;
    }

}



