package dataParser;

import com.google.gson.Gson;
import weatherProgram.WeatherReport;

import java.io.*;
import java.net.MalformedURLException;

public class ReportCompiler {

    public String compileReport(WeatherReport report, String fileName) {
        SourceToJson sourceArray = new SourceToJson(report.getCurrentTemperature(), report.getCoordinates());

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            bufferedWriter.write(new Gson().toJson(sourceArray));
            bufferedWriter.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().toJson(sourceArray);
    }
}
