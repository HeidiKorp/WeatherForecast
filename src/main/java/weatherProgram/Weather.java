package weatherProgram;

import java.io.IOException;

public interface Weather {

    public WeatherReport getWeather(WeatherRequest request) throws IOException;
    //public WeatherReport getForecastWeather(WeatherRequest request);
}
