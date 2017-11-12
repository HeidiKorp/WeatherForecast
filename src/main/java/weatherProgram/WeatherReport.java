package weatherProgram;

import dataParser.DataExtractor;
import dataParser.ReportCompiler;

import java.util.List;

public class WeatherReport {

    private String currentWeatherData;
    private String forecastWeatherData;
    private DataExtractor extractor;


    public WeatherReport(String currentWeatherData, String forecastWeatherData) {
        this.currentWeatherData = currentWeatherData;
        this.forecastWeatherData = forecastWeatherData;
        extractor = new DataExtractor(this);
    }



    public List<Double> getHighestTemperatures() { return extractor.getMaxTemperature(); }

    public List<Double> getLowestTemperatures() { return extractor.getMinTemperature(); }

    public double getCurrentTemperature() {
        return extractor.getCurrentTemperature();
    }

    public String getCoordinates() {
        return String.format("%.2f:%.2f", getLatitude(), getLongitude());
    }

    public String getCityName() { return extractor.getCityName(); }

    public String getCountry() { return extractor.getCountry(); }

    public double getLatitude() {
        return extractor.getLatitude();
    }

    public double getLongitude() {
        return extractor.getLongitude();
    }

    public String getCurrentWeatherData() { return currentWeatherData; }

    public String getForecastWeatherData() { return forecastWeatherData; }

    public String getReport(String fileName) {
        ReportCompiler compiler = new ReportCompiler();
        return compiler.compileReport(this, fileName);
    }
}
