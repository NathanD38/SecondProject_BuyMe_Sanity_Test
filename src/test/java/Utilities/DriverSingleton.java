package Utilities;

import Pages.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverSingleton extends BasePage {

    private static ExtentReports extent = ExtentReportFactory.getReporter();
    private static ExtentTest test = extent.createTest("Web Automation Project", "BuyMe Website - Sanity test");
    private static WebDriver driver;

    public DriverSingleton() throws Exception {
    }

    public static WebDriver getDriverInstance() throws Exception {

        if (driver == null) {
            try {
                String type = (getData("browserType"));
                if (type.equals("Chrome")) {
                    System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER_PATH);
                    driver = new ChromeDriver();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    driver.manage().window().maximize();
                    test.log(Status.PASS, "ChromeDriver established successfully!");

                } else if (type.equals("FF")) {
                    System.setProperty("webdriver.firefox.driver", Constants.FIREFOXDRIVER_PATH);
                    driver = new FirefoxDriver();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    driver.manage().window().maximize();
                    test.log(Status.PASS, "FirefoxDriver established successfully!");
                }


            } catch (Exception e) {
                e.printStackTrace();
                test.log(Status.FAIL,"Driver connection failed! " + e.getMessage());
                throw new Exception("Driver failed!");
            }
        }
        return driver;
    }
}
