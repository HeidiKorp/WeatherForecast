package weatherSpecifier;

import weatherProgram.InfoToFileWriter;
import weatherProgram.WeatherReportMaker;
import weatherProgram.WeatherRequest;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RequestMaker {

    private String city;
    private String country;
    private ConsoleReader reader;

    public RequestMaker() throws IOException {
        reader = new ConsoleReader().makeConsoleReader();
        this.city = reader.getCity();
        this.country = reader.getCountry();
        validateConsoleInput(city, country);
    }

    RequestMaker(String city, String country) {
        this.city = city;
        this.country = country;
    }

    void validateConsoleInput(String city, String country) {
        if (city.isEmpty()) {
            this.city = "Tallinn";
        }
        if (country.isEmpty()) {
            this.country = "EE";
        }
    }

    public void makeRequest() throws IOException {
        new WeatherRequest(city, country, new CurrentWeatherURLCompiler(city),
                new ForecastWeatherURLCompiler(city, country), new InfoToFileWriter(), new WeatherReportMaker());
    }

    public String getCity() { return city; }

    public String getCountry() { return country; }
}
