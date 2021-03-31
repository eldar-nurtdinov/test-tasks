package by.tasks.tests;

import by.tasks.pages.currency_conversion_calculator.CurrencyConversionCalculatorPage;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static by.tasks.pages.currency_conversion_calculator.CurrencyConversionCalculatorPage.openCurrencyConversionCalculatorPage;


public class NegativeSellInputTest extends BaseTest {

    @DataProvider(name = "sellInputDataProvider")
    public Object[][] sellInputDataProvider() {
        return new Object[][] {{"100$"}, {"one million"}, {"100#"}};
    }

    @Test(dataProvider = "sellInputDataProvider")
    @Description("Test when user fills sell amount box with invalid value")
    public void testWhenUserFillsSellAmountBoxWithInvalidValue(String inputData) {
        CurrencyConversionCalculatorPage currencyConversionCalculatorPage = openCurrencyConversionCalculatorPage();
        currencyConversionCalculatorPage.waitForItemInTableWillBeDisplayed();
        currencyConversionCalculatorPage.setSellValue(inputData);
        currencyConversionCalculatorPage.pressEnterSellInput();
        assertions.assertTrue(currencyConversionCalculatorPage.isCurrencyExchangeInvalidRequestPopUpDisplayed());
    }
}
