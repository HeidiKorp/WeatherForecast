package weatherProgram;

import java.io.IOException;

public interface Weather {

    public WeatherReport getWeather(String currentData, String forecastData) throws IOException;
    //public WeatherReport getForecastWeather(WeatherRequest request);
}
