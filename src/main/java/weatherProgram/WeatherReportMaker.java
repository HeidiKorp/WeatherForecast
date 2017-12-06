package weatherProgram;

import dataParser.DataExtractor;
import weatherSpecifier.ConsoleReader;

import java.io.*;

public class WeatherReportMaker implements Weather {


    public WeatherReport getWeather(String currentData, String forecastData) throws IOException {
        return new WeatherReport(currentData, forecastData, new DataExtractor());
    }
}
