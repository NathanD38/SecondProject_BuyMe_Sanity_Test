package Utilities;

import Pages.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

/*
 This is our singleton class which creates the instance of a webdriver,
 based on a browsertype in our xml config file.
 It also log the results of whether the driver was established successfully.
 */
public class DriverSingleton extends BasePage {

    /*
    Globally calling our ExtentReportFactory and its reporter method;
    as well as the ExtentTest with assignment of our own test suite details.
    We also globally call our driver.
    */
    private static ExtentReports extent = ExtentReportSingleton.getReporter();
    private static ExtentTest test = extent.createTest("Web Automation Project", "BuyMe Website - Sanity test");
    private static WebDriver driver;

    //Locally instantiating this singleton.
    public DriverSingleton() throws Exception {
    }

    /*
    Creating a method that will create one instance of the driver and return the same instance everywhere in our suite.
    It is wrapped between try-catch blocks based on the outcome of the test result.
     */

    public static WebDriver getDriverInstance() throws Exception {

        if (driver == null) {
            try {
                String type = (getData("browserType"));
                if (type.equals("Chrome")) {
                    System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER_PATH);
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    driver = new ChromeDriver(chromeOptions);
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    driver.manage().window().maximize();
                    //logging the results in our extent report - passing when chrome was established.
                    test.log(Status.PASS, "ChromeDriver established successfully!");

                } else if (type.equals("FF")) {
                    System.setProperty("webdriver.firefox.driver", Constants.FIREFOXDRIVER_PATH);
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    driver = new FirefoxDriver(firefoxOptions);
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    driver.manage().window().maximize();
                    //logging the results in our extent report - passing when firefox was established.
                    test.log(Status.PASS, "FirefoxDriver established successfully!");
                }


            } catch (Exception e) {
                e.printStackTrace();
                ////logging the results in our extent report - failing when driver was not established.
                test.log(Status.FAIL,"Driver connection failed! " + e.getMessage());
                throw new Exception("Driver failed!");
            }
        }
        return driver;
    }
}
