package weatherSpecifier;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestWeatherSpecifer {

    @Test
    public void testCurrentWeatherURLCompileCorrectURL() {
        CurrentWeatherURLCompiler currentWeatherURLCompiler = new CurrentWeatherURLCompiler("Tartu");
        assertEquals(currentWeatherURLCompiler.compileURL(),
                "http://api.openweathermap.org/data/2.5/weather?q=Tartu&APPID=7d1fdfe09df058c46a81bb575c22ac96");
    }

    @Test
    public void testRequestMakerCorrectCityFromConsole() throws IOException {
        BufferedReader br1 = mock(BufferedReader.class);
        when(br1.readLine()).thenReturn("Tartu").thenReturn("EE");
        ConsoleReader reader = new ConsoleReader(br1);
        RequestMaker maker = new RequestMaker(reader.getCity(), reader.getCountry());
        assertEquals("Tartu", maker.makeRequest().getCity());
    }

    @Test
    public void testRequestMakerCorrectCountryFromConsole() throws IOException {
        BufferedReader br1 = mock(BufferedReader.class);
        when(br1.readLine()).thenReturn("Tartu").thenReturn("EE");
        ConsoleReader reader = new ConsoleReader(br1);
        RequestMaker maker = new RequestMaker(reader.getCity(), reader.getCountry());
        assertEquals("EE", maker.makeRequest().getCountry());
    }

    @Test
    public void testRequestMakerCorrectCityDefault() throws IOException {
        RequestMaker maker = new RequestMaker();
        assertEquals("Tallinn", maker.makeRequest().getCity());
    }

    @Test
    public void testRequestMakerCorrectCountryDefault() throws IOException {
        RequestMaker maker = new RequestMaker();
        assertEquals("EE", maker.makeRequest().getCountry());
    }

    @Test
    public void testForecastWeatherURLCompileCorrectURL() {
        ForecastWeatherURLCompiler compiler = new ForecastWeatherURLCompiler("Tartu", "EE");
        assertEquals(compiler.compileURL(),
                "http://api.openweathermap.org/data/2.5/forecast?q=Tartu,EE&APPID=7d1fdfe09df058c46a81bb575c22ac96");
    }

}
