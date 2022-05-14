package Pages;

import Utilities.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

/*This class is our information page, where we pick the receiver, enter their info.
pick an event, type a blessing, uploading a pic, and continue to the next page.
*/
public class InformationPage extends BasePage {

    //Globally calling our driver and wait.
    private WebDriver driver;
    private WebDriverWait wait;

    //Locally instantiating this class with its own local instances of driver and wait.
    public InformationPage() throws Exception {
        this.driver = DriverSingleton.getDriverInstance();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //This public login method is calling six private methods, as the steps necessary to complete all the required information.
    public void info() {
        pressElseButton();
        enterElseInfo();
        pickEvent();
        enterBlessing();
        uploadPic();
        pressNextButton();
    }

    //Pressing "For someone else" button, i.e., the receiver.
    private void pressElseButton() {
        clickElement(By.cssSelector("div[gtm='למישהו אחר']"));
    }

    //Entering the information of the receiver's name and asserting it.
    private void enterElseInfo() {
        WebElement senderBox = getElement(By.cssSelector("input[maxlength='25']"));
        //Clearing previously entered names (from previous runs)
        senderBox.clear();
        senderBox.sendKeys("קווין בייקון");

        //Asserting entered value in the receiver's name field.
        String actualReceiverName = senderBox.getAttribute("value");
        String receiverName = "קווין בייקון";
        Assert.assertEquals(actualReceiverName, receiverName);
        System.out.println("Entered receiver name is: " + actualReceiverName);
    }

    //Picking an event from the dropdown using javascript executor's scrolling function.
    private void pickEvent() {
        clickElement(By.className("selected-text"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", getElement(By.cssSelector("li[value='32']")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[value='32']"))).click();
    }

    //Replacing the auto-generated message with our modified one.
    private void enterBlessing() {
        // Clear the auto-generated message.
        clickElement(By.className("textarea-clear-all"));
        //Entering our own modified message.
        sendToElement(By.cssSelector("textarea[rows='4']"), "גם המרחק לא יעצור אותי מלהפתיע אותך, ככה סתם כי בא לי:)\n" +
                "קבל פינוק! אין עליך, קווין בייקון!");
    }

    //Uploading a picture
    private void uploadPic() {
        //Clear previously uploaded pic (from previous runs)
        clickElement(By.cssSelector("span[class='remove-media']"));
        //Upload a pic without opening the file explorer
        sendToElement(By.cssSelector("input[type='file']"), "C:\\Users\\Nate\\IdeaProjects\\SecondProject\\src\\test\\java\\Utilities\\Kevin Bacon.jpg");
    }

    //Pressing the "Next" button
    private void pressNextButton() {
        clickElement(By.cssSelector("button[type='submit']"));
    }
}
