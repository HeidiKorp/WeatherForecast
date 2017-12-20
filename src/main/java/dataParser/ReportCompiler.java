package dataParser;

import com.google.gson.Gson;
import weatherProgram.InfoToFileWriter;
import weatherProgram.WeatherReport;

import java.io.*;
import java.net.MalformedURLException;

public class ReportCompiler {

    private String newReport;

    public void writeReportToFile(WeatherReport report, String fileName, InfoToFileWriter writer) throws IOException {
        SourceToJson sourceArray = new SourceToJson(report.getCurrentTemperature(), report.getHighestTemperatures(),
                report.getLowestTemperatures(), report.getCoordinates());
        newReport = new Gson().toJson(sourceArray);
        writer.writeWeatherIntoFile(newReport, new BufferedWriter(new FileWriter(fileName)));
    }

    public String getReport() {
        return newReport;
    }
}
