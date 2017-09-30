package weatherProgram;

public class WeatherCollection implements Weather {

    private String jsonWeatherData;

    @Override
    public WeatherReport getCurrentWeather(WeatherRequest request) {
        // TODO Get the object of weatherProgram.WeatherRequest and add its parameters into the URL. Connect the URL.
        // TODO Get the lowest and highest temperature of the current day.
        return new WeatherReport(null);
    }

    @Override
    public WeatherReport getForecastWeather(WeatherRequest request) {
        // TODO Get the lowest and highest temp of the 3 next days.
        return new WeatherReport(null);
    }
}

