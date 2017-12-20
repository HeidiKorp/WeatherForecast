package weatherProgram;


public class WeatherRepository {

    private String currentWeatherUrl;
    private String forecastWeatherUrl;
    private String city;
    private String country;

    public String getCurrentWeatherUrl() {
        return currentWeatherUrl;
    }

    public void setCurrentWeatherUrl(String currentWeather) {
        this.currentWeatherUrl = currentWeather;
    }

    public void setForecastWeatherUrl(String forecastWeatherUrl) {
        this.forecastWeatherUrl = forecastWeatherUrl;
    }
    public String getForecastWeatherUrl() {
        return forecastWeatherUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
