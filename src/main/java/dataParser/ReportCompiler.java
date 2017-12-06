package dataParser;

import com.google.gson.Gson;
import weatherProgram.InfoToFileWriter;
import weatherProgram.WeatherReport;

import java.io.*;
import java.net.MalformedURLException;

public class ReportCompiler {

    public String compileReport(WeatherReport report, String fileName, InfoToFileWriter writer) {
        SourceToJson sourceArray = new SourceToJson(report.getCurrentTemperature(), report.getHighestTemperatures(),
                report.getLowestTemperatures(), report.getCoordinates());

        writer.writeWeatherIntoFile(new Gson().toJson(sourceArray), fileName);
        return new Gson().toJson(sourceArray);
    }
}
