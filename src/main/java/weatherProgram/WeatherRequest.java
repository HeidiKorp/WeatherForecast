package weatherProgram;


import weatherSpecifier.CurrentWeather;
import weatherSpecifier.ForecastWeather;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/*
 * This class holds just the specifications of the request.
 */
public class WeatherRequest {

    private CurrentWeather currentWeather;
    String filename = "";

    public WeatherRequest(String city) {
         currentWeather = new CurrentWeather(city);
    }

    public WeatherRequest(String city, String country) {
        currentWeather = new ForecastWeather(city, country);
    }

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    public void writeRequestedDataIntoFile(String fileName) throws IOException {
        try {
            URL website = new URL(currentWeather.compileURL());
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






}
