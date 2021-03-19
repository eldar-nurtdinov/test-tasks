package by.tasks.webdriver;

import by.tasks.utilities.Browsers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * Factory to instantiate a WebDriver object. It returns an instance of the driver (local invocation).
 */
public class WebDriverFactory {

    private WebDriverFactory() {
    }

    /*
     * Factory method to return a WebDriver instance given the browser to hit
     *
     * @param browser : Browser representing the local browser to hit
     *
     * @return WebDriver instance
     */
    public static WebDriver getInstance(String browserName, ChromeOptions options) {
        WebDriver webDriver = null;

        if (Browsers.CHROME.getBrowserName().equals(browserName)) {
            WebDriverManager.chromedriver().setup();
            if (options != null) {
                webDriver = new ChromeDriver(options);
            } else {
                webDriver = new ChromeDriver();
            }
        } else if (Browsers.FIREFOX.getBrowserName().equals(browserName)) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser!");
        }

        return webDriver;
    }
}
