package by.tasks.data;

public enum Country {
    UK("United Kingdom", "GBP"),
    RU("Russia", "RUB"),
    UA("Ukraine", "UAH"),
    ALL("Albania", "ALL");

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
