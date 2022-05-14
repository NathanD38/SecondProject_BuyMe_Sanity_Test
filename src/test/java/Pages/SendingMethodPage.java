package Pages;

import Utilities.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/*This class is our final page, before payment,
 where we pick the sending method, entering the receiver's email and sender's name.
*/
public class SendingMethodPage extends BasePage {


    //Globally calling our driver (wait was not necessary here).
    private WebDriver driver;

    //Locally instantiating this class with its own local instance of driver.
    public SendingMethodPage() throws Exception {
        this.driver = DriverSingleton.getDriverInstance();
    }

    //This public method  is calling four private methods, as the steps necessary to complete the final stage.
    public void sendingMethod() {
        pressNowButton();
        pickMethod();
        enterReceiverInfo();
        pressPayButton();
    }

    //Pressing "Now" button
    private void pressNowButton() {
        clickElement(By.cssSelector("div[gtm='עכשיו']"));
    }

    //Picking a sending method - email or sms
    private void pickMethod() {
        //picked an email method
        clickElement(By.cssSelector("svg[gtm='method-email']"));
    }

    //Entering the receiver's email and the sender's name and asserting the latter.
    private void enterReceiverInfo() {
        WebElement receiverBox = getElement(By.cssSelector("input[type='email']"));
        //Clearing previously entered emails (from previous runs)
        receiverBox.clear();
        receiverBox.sendKeys("soelse@sh.com");

        WebElement senderBoxName = getElement(By.cssSelector("input[placeholder='שם שולח המתנה']"));
        //Clearing previously entered names (from previous runs)
        senderBoxName.clear();
        senderBoxName.sendKeys("משה");

        //Asserting sender's name (printing is for my own sanity check)
        String senderName = "משה";
        String actualSenderName = senderBoxName.getAttribute("value");
        Assert.assertEquals(actualSenderName, senderName);
        System.out.println("Entered sender name is: " + actualSenderName);
    }

    //Pressing the "Pay" button
    private void pressPayButton() {
        clickElement(By.cssSelector("button[type='submit']"));
    }
}
