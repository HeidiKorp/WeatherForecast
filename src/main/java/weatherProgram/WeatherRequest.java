package weatherProgram;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/*
 * This class holds just the specifications of the request.
 */
public class WeatherRequest {

    private final String CITY;
    private final String COUNTRY;
    String filename = "";

    public WeatherRequest(String city) {
        CITY = city;
        COUNTRY = null;
    }

    public WeatherRequest(String city, String country) {
        CITY = city;
        COUNTRY = country;
    }

    // Use different constructors so if you add EE, it means you have to request forecast.
    public void writeRequestedDataIntoFile(String fileName) throws IOException {
        try {
            this.filename = fileName;
            URL website = new URL(this.compileURL(this.CITY));
            URLConnection connection = website.openConnection();
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));

            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);

            in.close();

            bufferedWriter.write(response.toString());
            bufferedWriter.close();
            System.out.println(response.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String compileURL(String city) {
        return "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=7d1fdfe09df058c46a81bb575c22ac96";
    }

    private String compileURL(String city, String country) {
        return null;
    }

    public String getCity() {
        return CITY;
    }

}
