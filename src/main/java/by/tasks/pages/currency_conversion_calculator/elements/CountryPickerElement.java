package by.tasks.pages.currency_conversion_calculator.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CountryPickerElement {

    private final static String COUNTRY_LOCATOR = "countries-dropdown";

    WebDriver driver;

    private final WebElement countryDropDown;

    public CountryPickerElement(WebDriver driver) {
        this.driver = driver;
        countryDropDown = driver.findElement(By.id(COUNTRY_LOCATOR));
    }

    @Step("Select country by country dropdown.")
    public void selectCountry(String country) {
        countryDropDown.click();
        driver.findElement(By.linkText(country)).click();
    }
}
