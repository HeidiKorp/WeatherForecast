package weatherSpecifier;

import org.junit.Before;
import org.junit.Test;
import weatherProgram.Weather;
import weatherProgram.WeatherRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestWeatherSpecifier {

    private WeatherRepository repository;
    private ConsoleReader consoleReader;
    private WeatherRequestMaker maker;

    @Before
    public void createStarters() throws IOException {
        repository = new WeatherRepository();
        consoleReader = mock(ConsoleReader.class);
        when(consoleReader.getCity()).thenReturn("Tartu");
        when(consoleReader.getCountry()).thenReturn("EE");
        maker = new WeatherRequestMaker(consoleReader, repository);
    }

    @Test
    public void testCurrentWeatherURLCompileCorrectURL() {
        CurrentWeatherURLCompiler currentWeatherURLCompiler = new CurrentWeatherURLCompiler("Tartu");
        assertEquals(currentWeatherURLCompiler.compileURL(),
                "http://api.openweathermap.org/data/2.5/weather?q=Tartu&APPID=7d1fdfe09df058c46a81bb575c22ac96");
    }

    @Test
    public void testMakerConstructorSetCity() throws IOException {
        assertEquals(repository.getCity(), "Tartu");
    }

    @Test
    public void testMakerConstructorSetCountry() throws IOException {
        assertEquals(repository.getCountry(), "EE");
    }

    @Test
    public void testWeatherRequestSetCurrentUrl() throws IOException {
        maker.setUrls();
        assertEquals("http://api.openweathermap.org/data/2.5/weather?q=Tartu&APPID=7d1fdfe09df058c46a81bb575c22ac96", repository.getCurrentWeatherUrl());
    }

    @Test
    public void testWeatherRequestSetForecastUrl() {
        maker.setUrls();
        assertEquals("http://api.openweathermap.org/data/2.5/forecast?q=Tartu,EE&APPID=7d1fdfe09df058c46a81bb575c22ac96", repository.getForecastWeatherUrl());
    }

    @Test
    public void testRequestMakerCorrectCityFromConsole() throws IOException {
        BufferedReader br1 = mock(BufferedReader.class);
        when(br1.readLine()).thenReturn("Tartu").thenReturn("EE");
        ConsoleReader reader = new ConsoleReader(br1);
        WeatherRequestMaker maker = new WeatherRequestMaker(reader.getCity(), reader.getCountry(), repository);
        assertEquals("Tartu", reader.getCity());
    }

    @Test
    public void testRequestCityAndCountryEmptyDefaultCity() throws IOException {
        WeatherRequestMaker maker = new WeatherRequestMaker("Narva", "FI", repository);
        maker.validateConsoleInput("", "", repository);
        assertEquals("Tallinn", repository.getCity());
    }

    @Test
    public void testRequestCityAndCountryEmptyDefaultCountry() {
        WeatherRequestMaker maker = new WeatherRequestMaker("Narva", "FI", repository);
        maker.validateConsoleInput("", "", repository);
        assertEquals("EE", repository.getCountry());
    }

    @Test
    public void testForecastWeatherURLCompileCorrectURL() {
        ForecastWeatherURLCompiler compiler = new ForecastWeatherURLCompiler("Tartu", "EE");
        assertEquals(compiler.compileURL(),
                "http://api.openweathermap.org/data/2.5/forecast?q=Tartu,EE&APPID=7d1fdfe09df058c46a81bb575c22ac96");
    }

}
