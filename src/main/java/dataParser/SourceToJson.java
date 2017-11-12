package dataParser;

import java.util.List;

public class SourceToJson {

    private double temp;
    private List<Double> temp_max;
    private List<Double> temp_min;
    private String coord;

    SourceToJson(double temp, List<Double> maxTemps, List<Double> minTemps, String coord) {
        this.temp = temp;
        this.temp_max = maxTemps;
        this.temp_min = minTemps;
        this.coord = coord;
    }
}