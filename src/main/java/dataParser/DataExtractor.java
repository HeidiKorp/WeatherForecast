package dataParser;

import com.google.gson.Gson;
import dataContainer.DataContainer;
import weatherProgram.WeatherReport;

public class DataExtractor {

    private String weatherData;
    private Gson gson;
    private DataContainer parsedData;

    public DataExtractor(WeatherReport report) {
        this.weatherData = report.getWeatherData();
        gson = new Gson();
        parsedData = gson.fromJson(weatherData, DataContainer.class);
    }

    public double getLongitude() {

        //if (Double.parseDouble(parsedData.coord.lon) == 0) {
            return Double.parseDouble(parsedData.city.coord.lon);
//        } else {
//            return Double.parseDouble(parsedData.coord.lon);
//        }
    }

//
//    public double getLatitude() {
//        return Double.parseDouble(parsedData.coord.lat);
//    }

    // This is for the forecast. Coord is in city.
    //public double getLongitude() { return Double.parseDouble(parsedData.city.coord.lon); }

    public double getLatitude() { return Double.parseDouble(parsedData.city.coord.lat); }

    public double getCurrentTemperature() { return Double.parseDouble(parsedData.main.temp); }

    public double getMinTemperature() { return Double.parseDouble(parsedData.main.temp_min); }

    public double getMaxTemperature() { return Double.parseDouble(parsedData.main.temp_max); }

}
