package weatherProgram;

import weatherSpecifier.CurrentWeatherURLCompiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ApiInfoGetter {

    public String getApiInfo(String url) throws IOException {
        StringBuilder response = new StringBuilder();;
        try {
            URL website = new URL(url);
            URLConnection connection = website.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
            in.close();

            System.out.println(response.toString());
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }
}
