package Pages;

import Utilities.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//This class is our home page, where we pick a desired sum range, region, and category of present.
public class HomePage extends BasePage {

    //Globally calling our driver and wait.
    private WebDriver driver;
    private WebDriverWait wait;

    //Locally instantiating this class with its own local instances of driver and wait.
    public HomePage() throws Exception {
        this.driver = DriverSingleton.getDriverInstance();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //This public method is calling three private methods, as the steps necessary to continue to the next page
    public void pickPresent() throws Exception {
        pickSum();
        pickRegion();
        pickCategory();
        pressPickPresent();
    }

    //Picking the desired sum from the dropdown.
    private void pickSum() throws InterruptedException {
        //Waiting for sumbox to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[title='סכום']")));
        //Going to sumbox
        WebElement sumBox = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[title='סכום']")));
        //This is a bad practice, but the only way I could get this to work
        Thread.sleep (1000);
        //Clicking on sumbox
        sumBox.click();

        //Choose 200-299nis
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[value='3']"))).click();
    }

    //Picking the desired region from the dropdown.
    private void pickRegion() {
        //Go to sum box
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[title='אזור']"))).click();
        //Choose "South"
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[value='12']"))).click();
    }

    //Picking the desired category from the dropdown.
    private void pickCategory() {
        //Go to category box
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[title='קטגוריה']"))).click();
        //Initialize javascript executor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Scrolling down to the desired category
        js.executeScript("arguments[0].scrollIntoView(true);", getElement(By.cssSelector("li[value='315']")));
        //Choose "Enrichment & Workshops"
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[value='315']"))).click();
    }

    //Pressing the "Pick me a Present" button
    private void pressPickPresent() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[rel='nofollow'"))).click();
    }


}
