package by.tasks.tests;

import by.tasks.utilities.Assertions;
import by.tasks.utilities.Browser;
import by.tasks.utilities.Helpers;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.ScreenshotException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    private static final String SCREENSHOT_FOLDER = "target/screenshots/";
    private static final String SCREENSHOT_FORMAT = ".png";
    protected Assertions assertions;
    protected Helpers helpers;

    @BeforeMethod
    public void init() {
        assertions = new Assertions();
        helpers = new Helpers();
        Browser.initialize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        setScreenshot(result);

        if (Browser.Driver() != null) {
            Browser.Driver().quit();
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    protected byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    public void setScreenshot(ITestResult result) {
        if (!result.isSuccess()) {
            try {
                WebDriver returned = new Augmenter().augment(Browser.Driver());
                if (returned != null) {
                    saveScreenshot(((TakesScreenshot) returned).getScreenshotAs(OutputType.BYTES));
                }
            } catch (ScreenshotException se) {
                se.printStackTrace();
            }
        }
    }
}
