package weatherSpecifier;

public class CurrentWeatherURLCompiler {

    static String CITY = "";

    public CurrentWeatherURLCompiler() { }

    public CurrentWeatherURLCompiler(String city) {
        CITY = city;
    }

    public String compileURL() {
        String url = "";
        try {
            url = "http://api.openweathermap.org/data/2.5/weather?q=" + CITY + "&APPID=7d1fdfe09df058c46a81bb575c22ac96";
        } catch (NullPointerException e) {
            System.out.println("No such data");
        } catch (Exception e) {
            System.out.println("Couldn't find data with this city name");
            System.exit(0);
            url = "";
        }
        return url;
    }
}
