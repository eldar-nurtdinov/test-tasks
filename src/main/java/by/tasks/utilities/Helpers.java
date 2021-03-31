package by.tasks.utilities;

import by.tasks.data.Country;

import java.util.Random;

public class Helpers {

    public Country getRandomCountryValue() {
        Country[] countries = Country.values();
        int rnd = new Random().nextInt(countries.length);
        return countries[rnd];
    }

}
