import java.util.Locale;

public class WeatherRequest {

    private String city;
    private Locale countryAlphaCode;


    public WeatherRequest(String city, Locale countryAlphaCode) {
        this.city = city;
        this.countryAlphaCode = countryAlphaCode;
    }

    public String getCity() {
        return city;
    }

    public Locale getCountryAlphaCode() {
        return countryAlphaCode;
    }
}
