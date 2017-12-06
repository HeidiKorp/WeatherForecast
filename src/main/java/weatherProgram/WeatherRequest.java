package weatherProgram;


import weatherSpecifier.CurrentWeatherURLCompiler;
import weatherSpecifier.ForecastWeatherURLCompiler;

import java.io.*;

/*
 * This class holds just the specifications of the request.
 */
public class WeatherRequest {

    private CurrentWeatherURLCompiler currentWeather;
    private CurrentWeatherURLCompiler forecastWeather;
    private String city;
    private String country;


    public WeatherRequest(String city, String country, CurrentWeatherURLCompiler currentWeather,
                          ForecastWeatherURLCompiler forecastWeather, InfoToFileWriter writer, WeatherReportMaker maker) throws IOException {
        this.city = city;
        this.country = country;
        this.currentWeather = currentWeather;
        this.forecastWeather = forecastWeather;
        writer.writeCityIntoFile(city, "C:\\Users\\korph\\IdeaProjects\\Weather\\src\\main\\java\\input.txt");
        maker.getWeather(getCurrentWeatherData(), getForecastWeatherData()).makeReport();
    }

    public String getCurrentWeatherData() throws IOException {
        return new ApiInfoGetter().getApiInfo(currentWeather.compileURL());
    }

    public String getForecastWeatherData() throws IOException {
        return new ApiInfoGetter().getApiInfo(forecastWeather.compileURL());
    }



}
