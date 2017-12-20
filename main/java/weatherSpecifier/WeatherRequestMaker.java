package weatherSpecifier;

import dataParser.DataExtractor;
import dataParser.ReportCompiler;
import weatherProgram.*;

import java.io.IOException;

public class WeatherRequestMaker {

    private WeatherRepository repository;

    public WeatherRequestMaker(ConsoleReader reader, WeatherRepository repository) throws IOException {
        this.repository = repository;
        repository.setCity(reader.getCity());
        repository.setCountry(reader.getCountry());
        validateConsoleInput(reader.getCity(), reader.getCountry(), repository);
    }

    WeatherRequestMaker(String city, String country, WeatherRepository repository) {
        repository.setCity(city);
        repository.setCountry(country);
    }

    public void setUrls() {
        repository.setCurrentWeatherUrl(new CurrentWeatherURLCompiler(repository.getCity()).compileURL());
        repository.setForecastWeatherUrl(new ForecastWeatherURLCompiler(repository.getCity(), repository.getCountry()).compileURL());

    }

    void validateConsoleInput(String city, String country, WeatherRepository repository) {
        if (city.isEmpty()) {
            repository.setCity("Tallinn");
        }
        if (country.isEmpty()) {
            repository.setCountry("EE");
        }
    }

    public void makeRequest() throws IOException {
        setUrls();
        new RequestedCityNameWriter(new ApiInfoGetter()).writeCityToFile(new InfoToFileWriter(),
                new WeatherReport(new DataExtractor(), new ReportCompiler()), repository);
    }
}
