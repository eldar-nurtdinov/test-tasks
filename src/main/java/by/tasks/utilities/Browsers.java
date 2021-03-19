package by.tasks.utilities;

public enum Browsers {
    CHROME ("chrome"),
    FIREFOX ("firefox");

    private final String browserName;
    Browsers (String browserName) {this.browserName = browserName;}

    public String getBrowserName () {
        return this.browserName;
    }
}
