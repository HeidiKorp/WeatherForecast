package validator;

import weatherProgram.WeatherReportMaker;
import weatherProgram.WeatherReport;
import weatherProgram.WeatherRequest;

import java.io.IOException;
import java.util.Formatter;

import static org.junit.Assert.fail;


public class Validator {

    private WeatherReportMaker repository;
    private WeatherRequest request;
    private WeatherReport report;

    public Validator(String city, String countryCode) {
        repository = new WeatherReportMaker();
        request = new WeatherRequest(city, countryCode);
        //report = repository.getWeather(request);
    }

    public void validateLowestTempIsSmallerThanHighestTemp(double highestTemp, double lowestTemp) {
        try {
            if (highestTemp < lowestTemp) {
                throw new Exception("Fail: highest temperature is bigger than lowest temperature.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void validateTemperatureIsInCorrectRange(double highestTemp, double lowestTemp) {
        try {
            if (highestTemp > 350) {
                throw new Exception("Error: highest temperature is too high.");
            } else if (lowestTemp < 0) {
                throw new Exception("Error: lowest temperature is too low.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public void validateCoordinateRange(double latitude, double longitude) {
        try {
            if (latitude < -90) {
                throw new Exception("Error: latitude is too small.");
            } else if (latitude > 90) {
                throw new Exception("Error: latitude is too big.");
            } else if (longitude < -180) {
                throw new Exception("Error: longitude is too small.");
            } else if (longitude > 180) {
                throw new Exception("Error: longitude is too big.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void validateCoordinateFormat(String coordinates) {
        Formatter formatter = new Formatter();
        double nr = 33;
        try {
            if (!coordinates.matches("\\d+:\\d+")) {
                String he = String.format("%.0f:%.0f", nr, nr);
                throw new Exception("Coordinates should be in format " + he + ". Found " + coordinates);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
