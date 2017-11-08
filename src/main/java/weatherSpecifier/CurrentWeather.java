package weatherSpecifier;

public class CurrentWeather {

    String CITY = "";
    String COUNTRY = "";
    String url;

    CurrentWeather() { }

    public CurrentWeather(String city) {
        CITY = city;
        COUNTRY = null;
    }

    public String compileURL() {
        return "http://api.openweathermap.org/data/2.5/weather?q=" + CITY + "&APPID=7d1fdfe09df058c46a81bb575c22ac96";
    }

        public String getCity() {
        return CITY;
    }

}
