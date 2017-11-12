package weatherSpecifier;

import weatherProgram.WeatherRequest;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RequestMaker {

    private String city;
    private String country;

    public RequestMaker() {

    }

    public RequestMaker(String city, String country) throws IOException {
        this.city = city;
        this.country = country;
    }

    public WeatherRequest makeRequest() {
        if (city == null || country == null) {
            return new WeatherRequest("Tallinn", "EE");
        } else {
            return new WeatherRequest(city, country);
        }

    }
}
