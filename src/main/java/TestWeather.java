import org.junit.Test;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.util.Locale.UK;
import static org.junit.Assert.assertEquals;

public class TestWeather {

    @Test
    public void testSuccessfulConnectionToAPI() {
        String strUrl = "http://samples.openweathermap.org";
        try {
            URL url = new URL(strUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            assertEquals(HttpURLConnection.HTTP_OK, urlConnection.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRequestedAndReceivedNameOfCity() {
        try {
            WeatherRequest request = new WeatherRequest("London", UK);
            WeatherCollection repository = new WeatherCollection();
            WeatherReport report = repository.getCurrentWeather(request);
            assertEquals(request.getCity(), report.getCityName());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRequestedAndReceivedCountry() {
        try {
            WeatherRequest request = new WeatherRequest("London", UK);
            WeatherCollection repository = new WeatherCollection();
            WeatherReport report = repository.getCurrentWeather(request);
            assertEquals(request.getCountryAlphaCode(), report.getCountry());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLowestTemperatureIsSmallerThanHighestTemp() {
        try {
            WeatherRequest request = new WeatherRequest("London", UK);
            WeatherCollection repository = new WeatherCollection();
            WeatherReport report = repository.getCurrentWeather(request);
            assert report.getLowestTemperature() <= report.getHighestTemperature();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIfCoordinatesInCorrectFormat() {
        try {
            WeatherRequest request = new WeatherRequest("London", UK);
            WeatherCollection repository = new WeatherCollection();
            WeatherReport report = repository.getCurrentWeather(request);
            assert report.getCoordinates().matches("\\d{3}:\\d{3}");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

}
