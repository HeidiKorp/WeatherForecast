import weatherProgram.WeatherCollection;
import dataParser.DataExtractor;
import weatherProgram.WeatherReport;
import weatherProgram.WeatherRequest;

public class Main {

    public static void main(String[] args) throws Exception {
        WeatherRequest request = new WeatherRequest("Tallinn");
        //request.writeRequestedDataIntoFile("C:\\Users\\korph\\IdeaProjects\\Weather\\src\\main\\java\\currentWeather.txt");
        WeatherCollection collection = new WeatherCollection();
        collection.getCurrentWeather(request);
        WeatherReport report = collection.getCurrentWeather(request);
        DataExtractor extractor = new DataExtractor(report);
//
//        System.out.println(report.getWeatherData());
        System.out.println(report.getLongitude());
//        System.out.println(report.getLatitude());
//        System.out.println("Min temp " + report.getLowestTemperature());
//        System.out.println("Max temp: " + report.getHighestTemperature());
//        System.out.println(report.getCoordinates());
//        System.out.println(report.getReport("C:\\Users\\korph\\IdeaProjects\\Weather\\src\\main\\java\\output.txt"));
    }
}
