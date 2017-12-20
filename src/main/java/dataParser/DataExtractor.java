package dataParser;

import com.google.gson.Gson;
import dataContainer.DataContainer;
import weatherProgram.WeatherReport;
import weatherSpecifier.CurrentWeatherURLCompiler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataExtractor {

    private DataContainer parsedCurrentData = null;
    private DataContainer parsedForecastData = null;


    public void parseFromJson(String currentData, String forecastData) {
        validateData(currentData, forecastData);
    }

    private void validateData(String currentData, String forecastData) {
        if (!currentData.isEmpty() && !forecastData.isEmpty()) {
            parsedCurrentData = new FromJson().parseFromJson(currentData);
            parsedForecastData = new FromJson().parseFromJson(forecastData);
        } else {
            System.out.println("Couldn't find weather data");
            System.exit(0);
        }
    }

    public double getLongitude() { return parsedCurrentData.coord.lon; }

    public double getLatitude() { return parsedCurrentData.coord.lat; }

    public String getCityName() { return parsedCurrentData.name; }

    public String getCountry() { return parsedCurrentData.sys.country; }

    public double getCurrentTemperature() { return parsedCurrentData.main.temp; }

    public List<Double> getMinTemperature() {
        List<Double> temporaryMinTemp = new ArrayList<>();
        List<Double> day1MinTemps = new ArrayList<>();
        List<Double> day2MinTemps = new ArrayList<>();
        List<Double> day3MinTemps = new ArrayList<>();
        List<Double> minTemps = new ArrayList<>();

        parsedForecastData.list.stream()
                .map(temp -> temp.main.temp_min)
                .forEach(temporaryMinTemp::add);

        for (int index = 0; index < 8; index++) {
            day1MinTemps.add(temporaryMinTemp.get(index));
        }
        for (int index = 8; index < 16; index++) {
            day2MinTemps.add(temporaryMinTemp.get(index));
        }
        for (int index = 16; index < 24; index++) {
            day3MinTemps.add(temporaryMinTemp.get(index));
        }

        minTemps.add(day1MinTemps.stream()
                .collect(Collectors.summarizingDouble(Double::doubleValue))
                .getMin());
        minTemps.add(day2MinTemps.stream()
                .collect(Collectors.summarizingDouble(Double::doubleValue))
                .getMin());
        minTemps.add(day3MinTemps.stream()
                .collect(Collectors.summarizingDouble(Double::doubleValue))
                .getMin());

        return minTemps;
    }

    public List<Double> getMaxTemperature() {
        List<Double> temporaryMaxTemp = new ArrayList<>();
        List<Double> day1MaxTemps = new ArrayList<>();
        List<Double> day2MaxTemps = new ArrayList<>();
        List<Double> day3MaxTemps = new ArrayList<>();
        List<Double> maxTemps = new ArrayList<>();

        parsedForecastData.list.stream()
                .map(temp -> temp.main.temp_max)
                .forEach(temporaryMaxTemp::add);

        for (int index = 0; index < 8; index++) {
            day1MaxTemps.add(temporaryMaxTemp.get(index));
        }
        for (int index = 8; index < 16; index++) {
            day2MaxTemps.add(temporaryMaxTemp.get(index));
        }
        for (int index = 16; index < 24; index++) {
            day3MaxTemps.add(temporaryMaxTemp.get(index));
        }

        maxTemps.add(day1MaxTemps.stream()
                .collect(Collectors.summarizingDouble(Double::doubleValue))
                .getMax());
        maxTemps.add(day2MaxTemps.stream()
                .collect(Collectors.summarizingDouble(Double::doubleValue))
                .getMax());
        maxTemps.add(day3MaxTemps.stream()
                .collect(Collectors.summarizingDouble(Double::doubleValue))
                .getMax());

        return maxTemps;
    }

}
