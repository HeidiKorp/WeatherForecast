package weatherProgram;

import weatherSpecifier.CurrentWeatherURLCompiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.spec.ECField;

public class ApiInfoGetter {

    public String getApiInfo(String url) {
        StringBuilder response = new StringBuilder();
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
        } catch (FileNotFoundException e) {
            System.out.println("Incorrect url");
        } catch (UnknownHostException e) {
            System.out.println("Couldn't connect to internet");
        } catch (IOException e) {
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println("Couldn't find such file");
        }
        return response.toString();

    }

}
