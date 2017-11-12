package weatherSpecifier;

public class ForecastWeatherURLCompiler extends CurrentWeatherURLCompiler {

    private static String COUNTRY = "";

    public ForecastWeatherURLCompiler(String city, String country) {
        CITY = city;
        COUNTRY = country;
    }

    public String compileURL() {
        return "http://api.openweathermap.org/data/2.5/forecast?q=" + CITY + "," + COUNTRY + "&APPID=7d1fdfe09df058c46a81bb575c22ac96";
    }
}
