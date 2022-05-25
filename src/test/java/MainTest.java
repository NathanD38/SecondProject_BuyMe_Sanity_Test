import Pages.*;
import Utilities.DriverSingleton;
import Utilities.ExtentReportSingleton;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//This is our MainTest class, where we run our test suite. It inherits from BasePage the method getData.
public class MainTest extends BasePage {
    /*
    Globally calling our ExtentReportFactory and its reporter method;
    as well as the ExtentTest with assignment of our own test suite details.
    */
    private static ExtentReports extent = ExtentReportSingleton.getReporter();
    private static ExtentTest test = extent.createTest("Web Automation Project", "BuyMe Website - Sanity test");
    private static WebDriver driver;


    //Locally instantiating this class with its own local instance of driver.
    public MainTest() throws Exception {
        driver = DriverSingleton.getDriverInstance();
    }


    //Our @BeforeClass calls the URL for our suite, located in the xml config file.
    @BeforeClass
    public static void runOnceBeforeClass() throws Exception {
        driver.get(getData("URL"));
    }

    /*
    This first test is meant to run once, to check whether the registration process is successful.
    It initializes the SignUpPage class and calls the method signUp() within it.
    It is wrapped between try-catch blocks based on the outcome of the test result.
     */
//    @Test
//    public void test01_signUp() {
//        try {
//            new Pages.SignUpPage().signUp();
//            test.pass("Signing Up/Registration was successful!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        test.fail("Signing Up/Registration was not successful! " + e.getMessage());
//        }
//    }

    /*
    This second test is meant to execute on every run, to check whether the login process is successful.
    It initializes the LoginPage class and calls the method login() within it.
    It is wrapped between try-catch blocks based on the outcome of the test result.
     */
    @Test
    public void test02_login() {
        try {
            new LoginPage().login();
            test.pass("Login was successful!");
        } catch (Exception e) {
            e.printStackTrace();
            test.fail("Login was not successful! " + e.getMessage());
        }

    }

    /*
    This third test is meant to execute on every run, to check whether the process of picking a present is successful.
    It initializes the HomePage class and calls the method pickPresent() within it.
    It is wrapped between try-catch blocks based on the outcome of the test result.
     */
    @Test
    public void test03_homePage() {
        try {
            new HomePage().pickPresent();
            test.pass("Picking a present based on sum, region and category was successful!");
        } catch (Exception e) {
            e.printStackTrace();
            test.fail("Picking a present based on sum, region and category was not successful! " + e.getMessage());
        }
    }

    /*
    This fourth test is meant to execute on every run, to check whether the process of picking a business
    on the displayed results is successful.
    It initializes the BusinessPage class and calls the method pickBusiness() within it.
    It is wrapped between try-catch blocks based on the outcome of the test result.
    */
    @Test
    public void test04_businessPage() {
        try {
            new BusinessPage().pickBusiness();
            test.pass("Picking a business from the list was successful!");
        }catch (Exception e) {
            e.printStackTrace();
            test.fail("Picking a business from the list was not successful! " + e.getMessage());
        }
    }

    /*
    This fourth test is meant to execute on every run, to check whether the process of entering
    the required info and uploading a pic is successful.
    It initializes the InformationPage class and calls the method info() within it.
    It is wrapped between try-catch blocks based on the outcome of the test result.
    */
    @Test
    public void test05_informationPage() {
        try {
            new InformationPage().info();
            test.pass("Entering receiver's info, picking an event, typing a blessing and uploading a pic was successful!");
        }catch (Exception e) {
            e.printStackTrace();
            test.fail("Entering receiver's info, picking an event, typing a blessing and uploading a pic was not successful! " + e.getMessage());
        }
    }

    /*
    This fifth test is meant to execute on every run, to check whether the process of choosing a sending method,
    entering the required info and reaching the final stage is successful.
    It initializes the SendingMethodPage class and calls the method sendingMethod() within it.
    It is wrapped between try-catch blocks based on the outcome of the test result.
    */
    @Test
    public void test06_sendingMethodPage() {
        try {
            new SendingMethodPage().sendingMethod();
            test.pass("Picking a sending method, entering sender's name and receiver's email was successful!!");
        } catch (Exception e) {
            e.printStackTrace();
            test.fail("Picking a sending method, entering sender's name and receiver's email was not successful!! " + e.getMessage());
        }
    }

    /*
    Our @AfterClass is meant to run after all tests have been completed.
    It calls our extentReport and with the flush() method generates our extent report.
    It is also meant to exit the driver (i.e. close the browse). This part is in comment for my own sanity alone.
    */
    @AfterClass
    public static void tearDown() {
        extent.flush();
//        driver.quit();
    }


}
