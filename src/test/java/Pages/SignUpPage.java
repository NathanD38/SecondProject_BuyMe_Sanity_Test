package Pages;

import Utilities.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

/* This class is meant to be run once, separate from the other tests.
It allows us to check whether the registration process works successfully.
 */
public class SignUpPage extends BasePage {

    //Globally calling our driver and wait
    private WebDriver driver;
    private WebDriverWait wait;

    //Locally instantiating this class with its own local instances of driver and wait.
    public SignUpPage() throws Exception {
        this.driver = DriverSingleton.getDriverInstance();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //This public signup method is calling four private methods, as the steps necessary to complete the registration process.
    public void signUp() {
        pressSignUpButton();
        pressSignUpLink();
        enterCredentials();
        pressSignUp();
    }

    //Pressing the login/signup button
    private void pressSignUpButton() {
        clickElement(By.className("notSigned"));
    }

    //Pressing the signup link in the opened dialog box
    private void pressSignUpLink() {
        clickElement(By.className("text-link"));
    }

    //Entering the user's credentials, and asserting that the entered values in the name and email fields.
    private void enterCredentials() {
        sendToElement(By.cssSelector("input[placeholder='שם פרטי']"), "משה");

        //Asserting the username field (printing is for my own sanity check)
        String userName = getElement(By.cssSelector("input[placeholder='שם פרטי']")).getAttribute("value");
        String firstName = "משה";
        Assert.assertEquals(userName, firstName);
        System.out.println("Entered email is: " + userName);


        sendToElement(By.cssSelector("input[placeholder='מייל']"), "so@sh.com");

        //Asserting the email field (printing is for my own sanity check)
        String actualEmail = getElement(By.cssSelector("input[placeholder='מייל']")).getAttribute("value");
        String enteredEmail = "so@sh.com";
        Assert.assertEquals(actualEmail, enteredEmail);
        System.out.println("Entered email is: " + actualEmail);

        sendToElement(By.cssSelector("input[placeholder='סיסמה']"), "Q1e3t5u7"); //write password
        sendToElement(By.cssSelector("input[placeholder='אימות סיסמה']"), "Q1e3t5u7"); //repeat password
    }

    //Pressing the signup or register button
    private void pressSignUp() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))).click();
    }
}
