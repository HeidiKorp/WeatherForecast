import weatherProgram.WeatherReportMaker;
import weatherProgram.WeatherReport;
import weatherProgram.WeatherRequest;
import weatherSpecifier.ConsoleReader;
import weatherSpecifier.RequestMaker;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        //RequestMaker maker = new RequestMaker();
        ConsoleReader reader = new ConsoleReader(new BufferedReader(new InputStreamReader(System.in)));
        RequestMaker maker = new RequestMaker(reader.getCity(), reader.getCountry());
        WeatherRequest request = maker.makeRequest();



        //WeatherRequest request = new WeatherRequest("Tallinn", "EE");
        //request.writeRequestedDataIntoFile("C:\\Users\\korph\\IdeaProjects\\Weather\\src\\main\\java\\currentWeather.txt");
        WeatherReportMaker collection = new WeatherReportMaker();
//        collection.getWeather(request);
        WeatherReport report = collection.getWeather(request);
//
//        System.out.println(report.getCurrentWeatherData());
//        System.out.println(report.getLongitude());
//        System.out.println(report.getLatitude());
//        System.out.println(report.getHighestTemperatures());
//        System.out.println(report.getLowestTemperatures());
//        System.out.println(report.getCityName());
//        System.out.println(report.getCountry());


//        System.out.println(report.getWeather());
//        System.out.println(report.getCurrentTemperature());
//        System.out.println("Min temp " + report.getLowestTemperatures());
//        System.out.println("Max temp: " + report.getHighestTemperatures());
//        System.out.println(report.getCoordinates());
//        System.out.println(report.getReport("C:\\Users\\korph\\IdeaProjects\\Weather\\src\\main\\java\\output.txt"));
    }
}
