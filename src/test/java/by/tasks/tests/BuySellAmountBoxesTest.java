package by.tasks.tests;

import by.tasks.pages.currency_conversion_calculator.CurrencyConversionCalculatorPage;
import io.qameta.allure.Description;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static by.tasks.pages.currency_conversion_calculator.CurrencyConversionCalculatorPage.openCurrencyConversionCalculatorPage;

public class BuySellAmountBoxesTest extends BaseTest {

    private static final String FILL_VALUE = "1000";

    @Test
    @Description("When user fills \"BUY\" amount, \"SELL\" amount box is being emptied and vice versa.")
    public void testWhenUserFillsBuyAmountSellAmountBoxIsBeingEmptied() {
        SoftAssert softAssert = new SoftAssert();
        CurrencyConversionCalculatorPage currencyConversionCalculatorPage = openCurrencyConversionCalculatorPage();

        currencyConversionCalculatorPage.setBuyValue(FILL_VALUE);
        softAssert.assertEquals(currencyConversionCalculatorPage.getSellValue(), StringUtils.EMPTY);
        currencyConversionCalculatorPage.setSellValue(FILL_VALUE);
        softAssert.assertEquals(currencyConversionCalculatorPage.getBuyValue(), StringUtils.EMPTY);
        softAssert.assertAll();
    }
}
