package weatherProgram;

/*
 * This class holds just the specifications of the request.
 */
public class WeatherRequest {

    private final String CITY;
    private final String COUNTRY_ALPHA_CODE;
    private final String TEMP_SYSTEM;


    public WeatherRequest(String city, String countryAlphaCode, String tempSystem) {
        CITY = city;
        COUNTRY_ALPHA_CODE = countryAlphaCode;
        TEMP_SYSTEM = tempSystem;
    }

    public String getCity() {
        return CITY;
    }

    public String getCountryAlphaCode() {
        return COUNTRY_ALPHA_CODE;
    }

    public String getTempSystem() { return TEMP_SYSTEM; }
}
