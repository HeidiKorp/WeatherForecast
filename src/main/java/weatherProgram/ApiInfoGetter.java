package weatherProgram;

import weatherSpecifier.CurrentWeatherURLCompiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.spec.ECField;

public class ApiInfoGetter {

    public String getApiInfo(String url) throws IOException {
        StringBuilder response = new StringBuilder();
        if (validateUrl(url)) {
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

                return response.toString();
            } catch (MalformedURLException e) {
                System.out.println("Incorrect url");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Couldn't find such file");
            }
            return response.toString();
        }
        return "";
    }

    private boolean validateUrl(String url) {
        if (url.isEmpty()) {
            System.out.println("Couldn't find such url");
            return false;
        } else {
            return true;
        }
    }
}
