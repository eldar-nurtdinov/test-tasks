package by.tasks.tests;

import by.tasks.pages.currency_conversion_calculator.CurrencyConversionCalculatorPage;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static by.tasks.pages.currency_conversion_calculator.CurrencyConversionCalculatorPage.openCurrencyConversionCalculatorPage;

public class LossDisplayedTest extends BaseTest {

    @Test
    @Description("When bank provider's exchange amount for selling (X) is lower than the amount, provided by" +
                 "Company (Y), then a text box is displayed, representing the loss (X-Y)")
    public void testLossDisplayed() {
        CurrencyConversionCalculatorPage currencyConversionCalculatorPage = openCurrencyConversionCalculatorPage();
        currencyConversionCalculatorPage.waitForItemInTableWillBeDisplayed();
        int counter = currencyConversionCalculatorPage.getRowsCount();
        for (int i = 1; i <= counter; i++) {
            String companyAmount = currencyConversionCalculatorPage.getCompanyAmountByRowId(i);
            String swedBankAmount = currencyConversionCalculatorPage.getSwedbankAmountByRowId(i);
            String sebAmount = currencyConversionCalculatorPage.getSebAmountByRowId(i);
            String citadeleAmount = currencyConversionCalculatorPage.getCitadeleAmountByRowId(i);
            String luminorAmount = currencyConversionCalculatorPage.getLuminorAmountByRowId(i);

            if (!swedBankAmount.equals("-") && !swedBankAmount.isEmpty() && !swedBankAmount.equals(companyAmount)) {
                String companySwedbankOtherBankLoss =
                        currencyConversionCalculatorPage.getSwedbankOtherBankLossByRowId(i);
                companyBankCompare(companyAmount, swedBankAmount, companySwedbankOtherBankLoss);
            }

            if (!sebAmount.equals("-") && !sebAmount.isEmpty() && !sebAmount.equals(companyAmount)) {
                String companySebOtherBankLoss = currencyConversionCalculatorPage.getSebOtherBankLossByRowId(i);
                companyBankCompare(companyAmount, sebAmount, companySebOtherBankLoss);
            }

            if (!citadeleAmount.equals("-") && !citadeleAmount.isEmpty() && !citadeleAmount.equals(companyAmount)) {
                String companyCitadeleOtherBankLoss =
                        currencyConversionCalculatorPage.getCitadeleOtherBankLossByRowId(i);
                companyBankCompare(companyAmount, citadeleAmount, companyCitadeleOtherBankLoss);
            }

            if (!luminorAmount.equals("-") && !luminorAmount.isEmpty() && !luminorAmount.equals(companyAmount)) {
                String companyLuminorOtherBankLoss =
                        currencyConversionCalculatorPage.getLuminorOtherBankLossByRowId(i);
                companyBankCompare(companyAmount, luminorAmount, companyLuminorOtherBankLoss);
            }
        }
    }

    public void companyBankCompare(String companyAmountStr, String bankProviderAmountStr, String companyBankDiffStr) {
        BigDecimal companyAmount = new BigDecimal(companyAmountStr);
        BigDecimal bankProviderAmount = new BigDecimal(bankProviderAmountStr);
        BigDecimal diff = bankProviderAmount.subtract(companyAmount);
        if (diff.signum() < 0) {
            BigDecimal companyBankProviderDiff = new BigDecimal(companyBankDiffStr);
            assertions.assertEquals(companyBankProviderDiff.compareTo(diff), 0);
        }
    }
}
