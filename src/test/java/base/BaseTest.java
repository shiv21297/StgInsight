package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.TestUtils;
import utils.reporting.ExtentManager;

import java.lang.reflect.Method;
import java.util.Properties;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected Properties ConfigReader;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void startReport() {
        extent = ExtentManager.getInstance();
    }

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browserName, Method method) {
        playwright = Playwright.create();
        browser = BrowserFactory.createBrowserContext(playwright, browserName);
        context = browser.newContext();
        page = context.newPage();
        page.navigate(utils.ConfigReader.get("baseURL"));
        // Start Logging in the test in Extent Report
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        boolean screenshotEnabled = Boolean.parseBoolean(utils.ConfigReader.get("screenshotOnFailure"));

//        if (!result.isSuccess() && screenshotEnabled) {
//            String testName = result.getMethod().getMethodName();
//            String screenshotPath = TestUtils.takeScreenshot(page, testName + "_Failed");
//            test.fail("Test Failed. screenshot attached.").addScreenCaptureFromPath(screenshotPath);
//        } else {
//            test.pass("Test Passed.");
//            if (playwright != null) {
//                playwright.close();
        }
    }

//    @AfterSuite
//    public void flushReport() {
//        extent.flush();
//    }
//}