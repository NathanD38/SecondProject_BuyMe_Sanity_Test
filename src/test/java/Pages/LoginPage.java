package Pages;

import Utilities.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


//This class is our login page, where we click the login button, entering our credentials and pressing login.
public class LoginPage extends BasePage {

    //Globally calling our driver and wait.
    private WebDriver driver;
    private WebDriverWait wait;

    //Locally instantiating this class with its own local instances of driver and wait.
    public LoginPage() throws Exception {
        this.driver = DriverSingleton.getDriverInstance();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //This public login method is calling three private methods, as the steps necessary to complete the login process.
    public void login() throws Exception {
        pressLoginButton();
        enterCredentials();
        pressLogin();
    }

    //Pressing the login/signup button
    private void pressLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.className("notSigned"))).click();
    }

    //Entering user's credentials (email and password), and asserting the value in the email field
    private void enterCredentials() throws Exception {
        sendToElement(By.cssSelector("input[placeholder='מייל']"), "so@sh.com");

        sendToElement(By.cssSelector("input[placeholder='סיסמה']"), "Q1e3t5u7");

        //Asserting the value in the email field (printing is for my own sanity check)
        String actualEmail = getElement(By.cssSelector("input[placeholder='מייל']")).getAttribute("value");
        String enteredEmail = "so@sh.com";
        Assert.assertEquals(actualEmail, enteredEmail);
        System.out.println("Entered email is: " + actualEmail);

    }

    //Pressing the login button
    private void pressLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))).click();
    }
}
