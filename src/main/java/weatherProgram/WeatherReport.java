package weatherProgram;

import dataParser.DataExtractor;
import dataParser.ReportCompiler;

import java.util.List;

public class WeatherReport {

    private String currentWeatherData;
    private String forecastWeatherData;
    private DataExtractor extractor;


    public WeatherReport(String currentWeatherData, String forecastWeatherData, DataExtractor extractor) {
        this.currentWeatherData = currentWeatherData;
        this.forecastWeatherData = forecastWeatherData;
        this.extractor = extractor;
        extractor.parseFromJson(getCurrentWeatherData(), getForecastWeatherData());
    }

    public List<Double> getHighestTemperatures() { return extractor.getMaxTemperature(); }

    public List<Double> getLowestTemperatures() { return extractor.getMinTemperature(); }

    public double getCurrentTemperature() {
        return extractor.getCurrentTemperature();
    }

    public String getCoordinates() {
        return String.format("%.2f:%.2f", getLongitude(), getLatitude());
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

    public void makeReport() {
        new ReportCompiler().compileReport(this, getCityName() + ".txt", new InfoToFileWriter());
    }

}
