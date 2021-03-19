package by.tasks.pages.currency_conversion_calculator;

import by.tasks.pages.currency_conversion_calculator.elements.CountryPickerElement;
import by.tasks.utilities.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.qameta.allure.Step;

public class CurrencyConversionCalculatorPage {

    public static final String URL = "/v2/en-LT/fees/currency-conversion-calculator";

    @FindBy(xpath = "//input[@data-ng-model='currencyExchangeVM.filter.to_amount']")
    private WebElement buyInput;

    @FindBy(xpath = "//input[@data-ng-model='currencyExchangeVM.filter.from_amount']")
    private WebElement sellInput;

    @FindBy(css = ".js-localization-popover")
    private WebElement countrySelector;

    @FindBy(xpath = "(//span[contains(@class,'flag-icon-us')]/../following-sibling::*)[1]")
    private WebElement usdOfficialRate;

    @FindBy(xpath = "//div[contains(@class, 'ui-select-match')]")
    private WebElement sellCurrency;

    @FindBy(xpath = "(//span[contains(@class,'flag-icon-us')]/../following-sibling::*)[3]")
    private WebElement usdCompanyAmount;

    @FindBy(xpath = "(//span[contains(@class,'flag-icon-us')]/../following-sibling::*)[4]//span[@class='ng-binding']")
    private WebElement usdSwedbankAmount;

    @FindBy(xpath = "(//span[contains(@class,'flag-icon-us')]/../following-sibling::*)[4]//span[contains(@class, 'other-bank-loss')]")
    private WebElement companySwedbankDiff;

    WebDriver driver;

    public CurrencyConversionCalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static CurrencyConversionCalculatorPage openCurrencyConversionCalculatorPage() {
        Browser.goTo(CurrencyConversionCalculatorPage.URL);
        return new CurrencyConversionCalculatorPage(Browser.Driver());
    }

    @Step("Get BUY input value")
    public String getBuyValue() {
        return buyInput.getAttribute("value");
    }

    @Step("Get SELL input value")
    public String getSellValue() {
        return sellInput.getAttribute("value");
    }

    @Step("Set BUY input value")
    public void setBuyValue(String value) {
        buyInput.clear();
        buyInput.sendKeys(value);
    }

    @Step("Set SELL input value")
    public void setSellValue(String value) {
        sellInput.clear();
        sellInput.sendKeys(value);
    }

    @Step("Select country")
    public void selectCountry(String country) {
        countrySelector.click();
        CountryPickerElement countryPicker = new CountryPickerElement(driver);
        countryPicker.selectCountry(country);
    }

    @Step("Get USD official rate")
    public String getUsdOfficialRate() {
        return usdOfficialRate.getText();
    }

    @Step("Get SELL currency")
    public String getSellCurrency() {
        return sellCurrency.getText();
    }

    @Step("Get USD company amount")
    public String getUsdCompanyAmount() {
        return usdCompanyAmount.getText();
    }

    @Step("Get USD Swedbank amount")
    public String getUsdSwedbankAmount() {
        return usdSwedbankAmount.getText();
    }

    @Step("Get company Swedbank difference")
    public String getCompanySwedbankDiff() {
        return companySwedbankDiff.getText().replaceAll("[()^]*", "");
    }
}
