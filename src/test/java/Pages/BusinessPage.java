package Pages;

import Utilities.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

//This class is our main page where we pick desired sum, region and category of a present.
public class BusinessPage extends BasePage {

    //Globally calling our driver and wait.
    private WebDriver driver;
    private WebDriverWait wait;

    //Locally instantiating this class with its own local instances of driver and wait.
    public BusinessPage() throws Exception {
        this.driver = DriverSingleton.getDriverInstance();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //This public method is calling four private methods, where we accomplish the necessary steps to move to the next stage.
    public void pickBusiness() {
        assertURL();
        pickOneBusiness();
        enterSum();
        pressToChoose();
    }

    //This was required by the project; not a necessary step - asserting the currentURl (based on the chosen sum, region and category).
    private void assertURL() {
        String siteURL = "https://buyme.co.il/search?budget=3&category=315&region=12";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, siteURL);
        System.out.println("Current URL is: " + actualURL);
    }

    //Picking one business from the results.
    private void pickOneBusiness() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='https://buyme.co.il/supplier/2473090?budget=3&category=315&query=&region=12'"))).click();
    }

    //Entering the desired sum (within the range) in the opened internal page.
    private void enterSum() {
        sendToElement(By.cssSelector("input[placeholder='הכנס סכום']"), "250");
    }

    //Pressing the choose button to continue to the next stage.
    private void pressToChoose() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))).click();
    }
}
