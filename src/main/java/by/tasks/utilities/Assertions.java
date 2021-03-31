package by.tasks.utilities;

import io.qameta.allure.Step;
import org.testng.Assert;

public class Assertions {

    @Step("Check actual value on equals expected value: {actual}, {expected}")
    public <T> void assertEquals(final T actual, final T expected) {
        Assert.assertEquals(actual, expected);
    }

    @Step("Check actual value on not equals expected value: {actual}, {expected}")
    public <T> void assertNotEquals(final T actual, final T expected) {
        Assert.assertNotEquals(actual, expected);
    }

    @Step("Check actual value on equals True value: {actual}")
    public void assertTrue(boolean actual) {
        Assert.assertTrue(actual);
    }

    @Step("Fail with message: {message}")
    public void fail(String message) {
        Assert.fail(message);
    }

}
