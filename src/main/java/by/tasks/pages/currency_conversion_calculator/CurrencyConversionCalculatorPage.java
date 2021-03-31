package by.tasks.pages.currency_conversion_calculator;

import by.tasks.pages.currency_conversion_calculator.elements.CountryPickerElement;
import by.tasks.pages.currency_conversion_calculator.elements.CurrencyExchangeInvalidRequestPopUpElement;
import by.tasks.utilities.Browser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

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

    private final String rowsCount = "//tbody//tr";
    private final String companyAmountByRowId = "(//tbody//tr)[%d]//*[@data-title=\"Paysera rate\"]//span[@class='ng-binding']";
    private final String swedbankAmountByRowId = "(//tbody//tr)[%d]//*[@data-title=\"Swedbank amount\"]//span[@class='ng-binding']";
    private final String sebAmountByRowId = "(//tbody//tr)[%d]//*[@data-title=\"SEB amount\"]//span[@class='ng-binding']";
    private final String citadeleAmountByRowId = "(//tbody//tr)[%d]//*[@data-title=\"Citadele amount\"]//span[@class='ng-binding']";
    private final String luminorAmountByRowId = "(//tbody//tr)[%d]//*[@data-title=\"Luminor amount\"]//span[@class='ng-binding']";
    private final String swedbankAmountByRowIdBase = "(//tbody//tr)[%d]//*[@data-title=\"Swedbank amount\"]";
    private final String sebAmountByRowIdBase = "(//tbody//tr)[%d]//*[@data-title=\"SEB amount\"]";
    private final String citadeleAmountByRowIdBase = "(//tbody//tr)[%d]//*[@data-title=\"Citadele amount\"]";
    private final String luminorAmountByRowIdBase = "(//tbody//tr)[%d]//*[@data-title=\"Luminor amount\"]";
    private final String swedbankOtherBankLossByRowId =
            "(//tbody//tr)[%d]//*[@data-title=\"Swedbank amount\"]//span[contains(@class,\"other-bank-loss\")]";
    private final String sebOtherBankLossByRowId =
            "(//tbody//tr)[%d]//*[@data-title=\"SEB amount\"]//span[contains(@class,\"other-bank-loss\")]";
    private final String citadeleOtherBankLossByRowId =
            "(//tbody//tr)[%d]//*[@data-title=\"Citadele amount\"]//span[contains(@class,\"other-bank-loss\")]";
    private final String luminorOtherBankLossByRowId =
            "(//tbody//tr)[%d]//*[@data-title=\"Luminor amount\"]//span[contains(@class,\"other-bank-loss\")]";

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

    @Step("Is alert pop up for displayed with CurrencyExchange.InvalidRequest")
    public boolean isCurrencyExchangeInvalidRequestPopUpDisplayed() {
        CurrencyExchangeInvalidRequestPopUpElement popUpElement = new CurrencyExchangeInvalidRequestPopUpElement(driver);
        return popUpElement.isPopUpAlertDisplayed();
    }

    @Step("Press Enter on Sell Input")
    public void pressEnterSellInput() {
        sellInput.sendKeys(Keys.ENTER);
    }

    @Step("Wait for paysera table item will be displayed")
    public void waitForItemInTableWillBeDisplayed() {
        WebDriverWait pageLoadWaiter = new WebDriverWait(driver, 10);
        pageLoadWaiter.until(ExpectedConditions.visibilityOf(usdCompanyAmount));
    }

    public String getCompanyAmountByRowId(int rowId) {
        return driver.findElement(By.xpath(String.format(companyAmountByRowId, rowId))).getText().replaceAll(",", "");
    }

    private String getBankProviderAmountByRowId(String xpathValid, String xpathOnCatch, int rowId) {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        String result;
        try {
            result = driver.findElement(By.xpath(String.format(xpathValid, rowId))).getText().replaceAll(",", "");
        } catch (NoSuchElementException e) {
            result = driver.findElement(By.xpath(String.format(xpathOnCatch, rowId))).getText().replaceAll(",", "");
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return result;
    }

    private String getBankProviderLossByRowId(String xpathValid, int rowId) {
        try {
            return driver.findElement(By.xpath(String.format(xpathValid, rowId))).getText().replaceAll("[()^]*", "");
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Element with locator xpath: "
                    + String.format(xpathValid, rowId) + " not found");
        }
    }

    public String getSwedbankAmountByRowId(int rowId) {
        return getBankProviderAmountByRowId(swedbankAmountByRowId, swedbankAmountByRowIdBase, rowId);
    }

    public String getSebAmountByRowId(int rowId) {
        return getBankProviderAmountByRowId(sebAmountByRowId, sebAmountByRowIdBase, rowId);
    }

    public String getCitadeleAmountByRowId(int rowId) {
        return getBankProviderAmountByRowId(citadeleAmountByRowId, citadeleAmountByRowIdBase, rowId);
    }

    public String getLuminorAmountByRowId(int rowId) {
        return getBankProviderAmountByRowId(luminorAmountByRowId, luminorAmountByRowIdBase, rowId);
    }

    public String getSwedbankOtherBankLossByRowId(int rowId) {
        return getBankProviderLossByRowId(swedbankOtherBankLossByRowId, rowId);
    }

    public String getSebOtherBankLossByRowId(int rowId) {
        return getBankProviderLossByRowId(sebOtherBankLossByRowId, rowId);
    }

    public String getCitadeleOtherBankLossByRowId(int rowId) {
        return getBankProviderLossByRowId(citadeleOtherBankLossByRowId, rowId);
    }

    public String getLuminorOtherBankLossByRowId(int rowId) {
        return getBankProviderLossByRowId(luminorOtherBankLossByRowId, rowId);
    }

    public int getRowsCount() {
        return driver.findElements(By.xpath(rowsCount)).size();
    }
}
