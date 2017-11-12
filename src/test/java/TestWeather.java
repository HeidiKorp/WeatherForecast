import org.junit.Test;
import validator.Validator;
import weatherProgram.WeatherReportMaker;
import weatherProgram.WeatherReport;
import weatherProgram.WeatherRequest;
import weatherSpecifier.ConsoleReader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

public class TestWeather {

    private Validator validator = new Validator("Tallinn", "EE");
    private WeatherRequest request = new WeatherRequest("Tallinn", "EE");
    private WeatherReportMaker collection = new WeatherReportMaker();
    private WeatherReport report = collection.getWeather(request);
    private ConsoleReader reader;

    public TestWeather() throws IOException {
    }


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
            assert report.getCityName().equals(request.getCity());
        } catch (NullPointerException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testRequestedAndReceivedCountry() {
        try {
            assert report.getCountry().equals(request.getCountry());
        } catch (NullPointerException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testValidateDayOneHighestTempBiggerThanLowest() {
        validator.validateLowestTempIsSmallerThanHighestTemp(report.getHighestTemperatures().get(0), report.getLowestTemperatures().get(0));
    }

    @Test
    public void testValidateDayTwoHighestTempBiggerThanLowest() {
        validator.validateLowestTempIsSmallerThanHighestTemp(report.getHighestTemperatures().get(1), report.getLowestTemperatures().get(1));
    }

    @Test
    public void testValidateDayThreeHighestTempBiggerThanLowest() {
        validator.validateLowestTempIsSmallerThanHighestTemp(report.getHighestTemperatures().get(2), report.getLowestTemperatures().get(2));
    }

    @Test
    public void testValidateTempKelvinTooHigh() {
        validator.validateTemperatureIsInCorrectRange(9000, 30);
    }

    @Test
    public void testValidateTempKelvinTooLow() {
        validator.validateTemperatureIsInCorrectRange(100, -200);
    }

    @Test
    public void testValidateKelvinCorrect() {
        validator.validateTemperatureIsInCorrectRange(280, 276);
    }

    @Test
    public void testValidateTempKelvinTooHighTooLow() {
        validator.validateTemperatureIsInCorrectRange(9000, -90);
    }

    @Test
    public void testValidateLatitudeTooBig() {
        validator.validateCoordinateRange(100, 20);
    }

    @Test
    public void testValidateLatitudeTooSmall() {
        validator.validateCoordinateRange(-100, 20);
    }

    @Test
    public void testValidateLongitudeTooBig() {
        validator.validateCoordinateRange(60, 200);
    }

    @Test
    public void testValidateLongitudeTooSmall() {
        validator.validateCoordinateRange(60, -200);
    }

    @Test
    public void testValidateLatitudeAndLongitudeCorrect() {
        validator.validateCoordinateRange(90, 180);
    }

    @Test
    public void testCoordinatesFormatTwoDigits() {
        validator.validateCoordinateFormat("33:92");
    }

    @Test
    public void testCoordinatesFormatOneDigit() {
        validator.validateCoordinateFormat("2:2");
    }

    @Test
    public void testCoordinatesFormatCommaSeparator() {
        validator.validateCoordinateFormat("22,33");
    }

    @Test
    public void testCoordinatesFormatSpaceSeparator() {
        validator.validateCoordinateFormat("33 44");
    }

    @Test
    public void testCanGetCityNameFromResponse() {
        assert report.getCityName().equals(request.getCity());
    }

    @Test
    public void testCanGetHighestTempFromResponse() {
        try {
            assert !report.getHighestTemperatures().isEmpty();
        } catch (AssertionError e) {
            fail("Cannot find highest temperature.");
        }
    }

    @Test
    public void testCanGetLowestTempFromReport() {
        try {
            assert !report.getLowestTemperatures().isEmpty();
        } catch (AssertionError e) {
            fail("Cannot find lowest temperature.");
        }
    }

}
