package weatherProgram;

import dataParser.DataExtractor;
import dataParser.ReportCompiler;

public class WeatherReport {

    private String weatherData;
    private DataExtractor extractor;

    public WeatherReport(String jsonWeatherData) {
        this.weatherData = jsonWeatherData;
        this.extractor = new DataExtractor(this);
    }

    public double getHighestTemperature() {
        return extractor.getMaxTemperature();
    }

    public double getLowestTemperature() {
        return extractor.getMinTemperature();
    }

    public double getCurrentTemperature() {
        return extractor.getCurrentTemperature();
    }

    public String getCoordinates() {
        return String.format("%.2f:%.2f", getLatitude(), getLongitude());
    }
//
//    public String getCityName() {
//        return null;
//    }
//
//    public String getCountry() {
//        return null;
//    }
//
    public double getLatitude() {
        return extractor.getLatitude();
    }

    public double getLongitude() {
        return extractor.getLongitude();
    }

    public String getWeatherData() { return weatherData; }

    public String getReport(String fileName) {
        ReportCompiler compiler = new ReportCompiler();
        return compiler.compileReport(this, fileName);
    }
}
