package validator;

import weatherProgram.WeatherCollection;
import weatherProgram.WeatherReport;
import weatherProgram.WeatherRequest;

import java.util.Formatter;

import static org.junit.Assert.fail;


public class Validator {

    private WeatherCollection repository;
    private WeatherRequest request;
    private WeatherReport report;

    public Validator(String city, String countryCode, String tempSystem) {
        repository = new WeatherCollection();
        request = new WeatherRequest(city);
        report = repository.getWeather(request);
    }

    public void validateLowestTempIsSmallerThanHighestTemp(float highestTemp, float lowestTemp) {
        try {
            if (highestTemp < lowestTemp) {
                throw new Exception();
            }
        } catch (Exception e) {
            fail("Fail: highest temperature is bigger than lowest temperature.");
        }
    }

    public void validateTemperatureIsInCorrectRange(String tempSystem, float highestTemp, float lowestTemp) {
        try {
            if (tempSystem.equals("celsius") && highestTemp > 60
                    || tempSystem.equals("fahrenheit") && highestTemp > 160
                    || tempSystem.equals("kelvin") && highestTemp > 350) {
                throw new Exception("Error: highest temperature is too high.");

            } else if (tempSystem.equals("celsius") && lowestTemp < -273
                    || tempSystem.equals("fahrenheit") && lowestTemp < -460
                    || tempSystem.equals("kelvin") && lowestTemp < 0) {
                throw new Exception("Error: lowest temperature is too low.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    public void validateCoordinateRange(float latitude, float longitude) {
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
            fail();
        }
    }

    public void validateCoordinateFormat(String coordinates) {
        Formatter formatter = new Formatter();
        float nr = 33;
        try {
            if (!coordinates.matches("\\d+:\\d+")) {
                throw new Exception();
            }
        } catch (Exception e) {
            String he = String.format("%.0f:%.0f", nr, nr);
            fail("Coordinates should be in format " + he + ". Found " + coordinates);
        }

    }

    public void validateMeasurementSystem() {
        // TODO validate if the response and request are in the same measurement system.
    }


}
