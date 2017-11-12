package weatherProgram;

import java.io.*;

public class WeatherReportMaker implements Weather {

    public WeatherReport getWeather(WeatherRequest request) throws IOException {
        return new WeatherReport(request.getCurrentWeatherData(),
                request.getForecastWeatherData());
    }



//    public WeatherReport getWeatherByWritingIntoFile(WeatherRequest request) {
//        StringBuilder stringBuilderCurrent = new StringBuilder();
//        StringBuilder stringBuilderForecast = new StringBuilder();
//        String line;
//        try {
//            //BufferedReader reader = new BufferedReader(new FileReader(request.filename));
//            BufferedReader readerCurrent = new BufferedReader(new FileReader("C:\\Users\\korph\\IdeaProjects\\Weather\\src\\main\\java\\currentWeather.txt"));
//            BufferedReader readerForecast = new BufferedReader(new FileReader("C:\\Users\\korph\\IdeaProjects\\Weather\\src\\main\\java\\forecast.txt"));
//            while ((line = readerCurrent.readLine()) != null) {
//                stringBuilderCurrent.append(line);
//            }
//            while ((line = readerForecast.readLine()) != null) {
//                stringBuilderForecast.append(line);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //System.out.println(stringBuilder.toString());
//        return new WeatherReport(stringBuilderCurrent.toString(), stringBuilderForecast.toString());
//
//    }

}
