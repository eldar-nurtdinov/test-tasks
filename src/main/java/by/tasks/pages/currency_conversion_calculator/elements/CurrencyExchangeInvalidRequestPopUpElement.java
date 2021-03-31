package by.tasks.pages.currency_conversion_calculator.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CurrencyExchangeInvalidRequestPopUpElement {

    final static String POP_UP_ALERT = "#currency-exchange-app .alert";

    WebDriver driver;

    private final WebElement popUpAlert;

    public CurrencyExchangeInvalidRequestPopUpElement(WebDriver driver) {
        this.driver = driver;
        popUpAlert = driver.findElement(By.cssSelector(POP_UP_ALERT));
    }

    @Step("Check on popUpAlertIsDisplayed")
    public boolean isPopUpAlertDisplayed() {
        return popUpAlert.isDisplayed();
    }
}
