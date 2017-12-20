package weatherProgram;


import java.io.*;

/*
 * This class holds just the specifications of the request.
 */
public class RequestedCityNameWriter {

    private ApiInfoGetter apiInfoGetter;

    public RequestedCityNameWriter(ApiInfoGetter apiInfoGetter) {
        this.apiInfoGetter = apiInfoGetter;
    }

    public void writeCityToFile(InfoToFileWriter writer, WeatherReport report, WeatherRepository repository) throws IOException {
        writer.writeCityIntoFile(repository.getCity(), new BufferedWriter(new FileWriter( "C:\\Users\\korph\\IdeaProjects\\Weather\\src\\main\\java\\input.txt")));
        report.makeReport(getCurrentWeatherData(apiInfoGetter, repository.getCurrentWeatherUrl()), getForecastWeatherData(apiInfoGetter, repository.getForecastWeatherUrl()));
    }

    public String getCurrentWeatherData(ApiInfoGetter apiInfoGetter, String url) throws IOException {
        return apiInfoGetter.getApiInfo(url);
    }

    public String getForecastWeatherData(ApiInfoGetter apiInfoGetter, String url) throws IOException {
        return apiInfoGetter.getApiInfo(url);
    }



}
