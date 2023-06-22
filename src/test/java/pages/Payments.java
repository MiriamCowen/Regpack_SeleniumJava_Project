package pages;

import infra.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Payments {

    //Setting properties
    WebDriver driver;

    //Constructors
    public Payments (WebDriver driver) {
        this.driver = driver;}

    //Locators
    private By payments = By.id("nav_payments");
    private By paymentsTab = By.id("payments_tab");
    private By upcomingCharges = By.id("upcoming_tab");
    private By paymentMethod = By.cssSelector("a[action_data=\"has_payment_method\"]");
    private By lastMonth = By.id("filter_lastmonth");
    private By clearFilter = By.cssSelector("a[class=\"clear_filter_compact\"]");

    //Methods
    public void clickPayments(){driver.findElement(payments).click();
        Utils.sleep(5);}
    public void clickPaymentsTab(){driver.findElement(paymentsTab).click();Utils.sleep(5);}
    public void clickUpcomingCharges(){driver.findElement(upcomingCharges).click();Utils.sleep(5);}
    public void clickHasPaymentMethod(){driver.findElement(paymentMethod).click();Utils.sleep(5);}
    public void clickLastMonth(){driver.findElement(lastMonth).click();Utils.sleep(5);}
    public void clickClearFilter(){driver.findElement(clearFilter).click();Utils.sleep(5);}


}
