package by.tasks.data;

public enum Country {
    LT("Lithuania", "EUR"),
    UK("United Kingdom", "GBP");

    private final String name;
    private final String currency;

    Country(String name, String currency) {
        this.name = name;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }
}
