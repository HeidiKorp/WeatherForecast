package weatherSpecifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConsoleReader {

    private String city = "";
    private String country = "";

    public ConsoleReader(BufferedReader reader) throws IOException {
        setCity(reader);
        setCountry(reader);
    }

    public void setCity(BufferedReader reader) throws IOException {
        System.out.println("Enter city name: ");
        city = reader.readLine();
    }

    public void setCountry(BufferedReader reader) throws IOException {
        System.out.println("Enter coutry code: ");
        country = reader.readLine();
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

}
