package by.tasks.tests;

import by.tasks.data.Country;
import by.tasks.pages.currency_conversion_calculator.CurrencyConversionCalculatorPage;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static by.tasks.pages.currency_conversion_calculator.CurrencyConversionCalculatorPage.openCurrencyConversionCalculatorPage;

public class CountryChangeTest extends BaseTest {

    @Test
    @Description("When user selects country (select option is in the footer), rates must be updated and currency" +
                 "option should be changed to the respective default currency for that country.")
    public void testOnCountryChangeRatesMustBeUpdatedAndCurrencyShouldBeChanged() {
        Country rndCountry = helpers.getRandomCountryValue();
        CurrencyConversionCalculatorPage currencyConversionCalculatorPage = openCurrencyConversionCalculatorPage();
        String rateBefore = currencyConversionCalculatorPage.getUsdOfficialRate();

        currencyConversionCalculatorPage.selectCountry(rndCountry.getName());
        String rateAfter = currencyConversionCalculatorPage.getUsdOfficialRate();
        String currencyAfter = currencyConversionCalculatorPage.getSellCurrency();

        assertions.assertNotEquals(rateAfter, rateBefore);
        assertions.assertEquals(currencyAfter, rndCountry.getCurrency());
    }
}
