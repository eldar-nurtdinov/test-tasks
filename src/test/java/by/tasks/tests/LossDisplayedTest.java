package by.tasks.tests;

import by.tasks.pages.currency_conversion_calculator.CurrencyConversionCalculatorPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static by.tasks.pages.currency_conversion_calculator.CurrencyConversionCalculatorPage.openCurrencyConversionCalculatorPage;

public class LossDisplayedTest extends BaseTest {

    @Test
    @Description("When bank provider's exchange amount for selling (X) is lower than the amount, provided by" +
                 "Company (Y), then a text box is displayed, representing the loss (X-Y)")
    public void testLossDisplayed() {
        CurrencyConversionCalculatorPage currencyConversionCalculatorPage = openCurrencyConversionCalculatorPage();
        BigDecimal companyAmount = new BigDecimal(currencyConversionCalculatorPage.getUsdCompanyAmount());
        BigDecimal swedbankAmount = new BigDecimal(currencyConversionCalculatorPage.getUsdSwedbankAmount());
        BigDecimal diff = swedbankAmount.subtract(companyAmount);
        if (diff.signum() < 0) {
            BigDecimal companySwedbankDiff = new BigDecimal(currencyConversionCalculatorPage.getCompanySwedbankDiff());
            Assert.assertEquals(companySwedbankDiff.compareTo(diff), 0);
        } else {
            Assert.fail("There is no diff in Company and SwedBank amount");
        }
    }
}
