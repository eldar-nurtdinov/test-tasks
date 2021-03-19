package by.tasks.utilities;

import by.tasks.webdriver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Browser {

    private static final String BASE_URL = System.getProperty("siteUrl");
    private static final String BROWSER_NAME = PropertyLoader.loadProperty("browser.name");
    private static final String IS_HEADLESS_RUN = PropertyLoader.loadProperty("browser.is.headless.run");
    private static WebDriver webDriver;

    public static void initialize() {
        ChromeOptions options = new ChromeOptions();
        if (BROWSER_NAME.equals(Browsers.CHROME.getBrowserName()) && (Boolean.parseBoolean(IS_HEADLESS_RUN))) {
            options.addArguments("--headless");
        }
        webDriver = WebDriverFactory.getInstance(BROWSER_NAME, options);
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        goTo("");
    }

    public static String getTitle() {
        return webDriver.getTitle();
    }


    public static WebDriver Driver() {
        return webDriver;
    }

    public static void goTo(String url) {
        webDriver.get(BASE_URL + url);
    }

    public static void close() {
        webDriver.close();
    }
}
