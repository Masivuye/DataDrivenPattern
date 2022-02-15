package com.appliedselenium.base;

import com.appliedselenium.base.Utils.Constants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestBase {

    ExtentReports extent;    // Create an instance of Extent Reports
    ExtentHtmlReporter htmlReporter; // Create an instance of the htmlReport and we will add this to the extent reports instance
    ExtentTest logger;

    //create Email class object
    SendMailSSLWithAttachment sendEmail = new SendMailSSLWithAttachment();

    //initialize the WebDriver interface reference variable
    public static WebDriver driver;

    public static Logger logs = Logger.getLogger("devpinoyLogger");

    //Generic Method to check the presence of an element
    //This method accepts 1 parameter by of type By and returns a boolean value
    public String generateDateTimeStamp() {

        Date dt = new Date();

//			Timestamp ts = new Timestamp (dt.getTime());

        return new SimpleDateFormat("yyyy-dd-mm_hhmmss").format(dt);

//			return ts.toString();
    }

    @BeforeTest
    public void BeforeTestMethod(){
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//reports//" + generateDateTimeStamp() + ".html");
        // Title for the Test Report
        htmlReporter.config().setDocumentTitle("Automation Test Results ");
        // Set the Report name
        htmlReporter.config().setReportName("Automation Test Results ");
        // Set the Dark Theme for Extent Reports
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "Masi's Windows");
        extent.setSystemInfo("Environment", "Health Systems Technologies");
        extent.setSystemInfo("User Name", "Masivuye");
    }


    // First method to be called before automation suite is executed
    @BeforeMethod
    @Parameters(value={"BrowserName"})
    public void setup(String browserName, Method testMethod ) {
        logger = this.extent.createTest(testMethod.getName());
        setupDriver(browserName);
        driver.manage().window().maximize();
        driver.get(Constants.url);
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        logs.debug("Application launched: " + Constants.url);

    }
    public void setupDriver(String browserName){
        // invoke the browser mentioned in config.prop and assign it to driver reference variable
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
            driver = new ChromeDriver();
            logs.debug("Chrome browser launched");
        }
        else if(browserName.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver",
                    System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
            driver = new ChromeDriver();
            logs.debug("FireFox browser launched");
        }
        else if(browserName.equalsIgnoreCase("edge")){
            System.setProperty("webdriver.edge.driver",
                    System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\msedgedriver.exe");
            driver = new ChromeDriver();
            logs.debug("Edge browser launched");
        }
        //open the url and assign an implicit wait (as configured in config.properties)

    }
    /*public  String capture(WebDriver driver) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File(System.getProperty("user.dir") +"//Images//" + System.currentTimeMillis()
                + ".png");
        String errflpath = Dest.getAbsolutePath();
        Files.copy(scrFile.toPath(), Dest.toPath());
        return errflpath;
    }*/

    // Mehod called after all the tests are executed
    @AfterMethod
    public void CloseDriver(ITestResult results) throws InterruptedException {
        if(results.getStatus()==ITestResult.SUCCESS){
            String methodName = results.getMethod().getMethodName();
            String logText = "Test Case" + methodName + "Passed";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.BLUE);
            logger.log(Status.PASS,m);
        }
            else if(results.getStatus()==ITestResult.FAILURE){
                String methodName = results.getMethod().getMethodName();
                String logText = "Test Case" + methodName + "Failed";
                Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREY);
                logger.log(Status.FAIL,m);
        }
        else if(results.getStatus()==ITestResult.SKIP){
            String methodName = results.getMethod().getMethodName();
            String logText = "Test Case" + methodName + "Skipped";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.PURPLE);
            logger.log(Status.SKIP,m);
        }
        driver.close();
        logs.debug("Browser Closed");

    }
    @AfterTest
    public void afterTestMethod()
    {
        sendEmail.sendEmailMethod();
        extent.flush();

    }

}
