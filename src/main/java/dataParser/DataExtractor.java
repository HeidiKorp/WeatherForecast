package dataParser;

import com.google.gson.Gson;
import dataContainer.DataContainer;
import weatherProgram.WeatherReport;
import weatherSpecifier.CurrentWeather;
import weatherSpecifier.ForecastWeather;

public class DataExtractor {

    private String weatherData;
    private Gson gson;
    private DataContainer parsedData;
    private CurrentWeather currentWeather;

    public DataExtractor(WeatherReport report, CurrentWeather currentWeather) {
        this.weatherData = report.getWeatherData();
        gson = new Gson();
        parsedData = gson.fromJson(weatherData, DataContainer.class);
        this.currentWeather = currentWeather;
    }

    public double getLongitude() {
        if (currentWeather instanceof ForecastWeather) {
            return Double.parseDouble(parsedData.city.coord.lon);
        } else {
            return Double.parseDouble(parsedData.coord.lon);
        }
    }


    public double getLatitude() {
        if (currentWeather instanceof ForecastWeather) {
            return Double.parseDouble(parsedData.city.coord.lat);
        } else {
            return Double.parseDouble(parsedData.coord.lat);
        }
    }


    public double getCurrentTemperature() { return Double.parseDouble(parsedData.main.temp); }

    public double getMinTemperature() { return Double.parseDouble(parsedData.main.temp_min); }

    public double getMaxTemperature() { return Double.parseDouble(parsedData.main.temp_max); }

}
