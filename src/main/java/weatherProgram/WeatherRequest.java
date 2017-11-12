package weatherProgram;


import weatherSpecifier.CurrentWeatherURLCompiler;
import weatherSpecifier.ForecastWeatherURLCompiler;

import javax.swing.text.StyledEditorKit;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/*
 * This class holds just the specifications of the request.
 */
public class WeatherRequest {

    private CurrentWeatherURLCompiler currentWeather;
    private CurrentWeatherURLCompiler forecastWeather;
    //String filename = "";
    private String city;
    private String country;


    public WeatherRequest(String city, String country) {
        this.city = city;
        this.country = country;
        this.currentWeather = new CurrentWeatherURLCompiler(city);
        this.forecastWeather = new ForecastWeatherURLCompiler(city, country);
        CityNameToFileWriter writer = new CityNameToFileWriter();
        writer.writeCityNameIntoFile(city, "C:\\Users\\korph\\IdeaProjects\\Weather\\src\\main\\java\\input.txt");
    }

    public String getCity() { return city; }

    public String getCountry() { return country; }

    public String getCurrentWeatherData() throws IOException {
        return new ApiInfoGetter().getApiInfo(currentWeather.compileURL());
    }

    public String getForecastWeatherData() throws IOException {
        return new ApiInfoGetter().getApiInfo(forecastWeather.compileURL());
    }



}
