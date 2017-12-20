package weatherProgram;

import dataParser.DataExtractor;
import dataParser.ReportCompiler;

import java.io.IOException;
import java.util.List;

public class WeatherReport {

    private DataExtractor extractor;
    private ReportCompiler compiler;


    public WeatherReport(DataExtractor extractor, ReportCompiler compiler) {
        this.extractor = extractor;
        this.compiler = compiler;
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

    public void parseJson(String currentWeatherData, String forecastWeatherData) {
        extractor.parseFromJson(currentWeatherData, forecastWeatherData);
    }

    public void makeReport(String currentWeatherData, String forecastWeatherData) throws IOException {
        parseJson(currentWeatherData, forecastWeatherData);
        compiler.writeReportToFile(this, getCityName() + ".txt", new InfoToFileWriter());
    }

}
