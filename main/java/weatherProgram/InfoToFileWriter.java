package weatherProgram;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class InfoToFileWriter {

    private static String cities = "";

    void writeCityIntoFile(String info, BufferedWriter writer) {
        try {
            addCity(info);
            writer.write(getCities());
            writer.close();
        } catch (NullPointerException e) {
            System.out.println("No file or no info");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find the file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeWeatherIntoFile(String info, BufferedWriter writer) {
        try {
            writer.write(info);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addCity(String newCity) {
        if (!cities.isEmpty()) {
            cities = cities + "\n" + newCity;
        } else {
            cities = newCity;
        }
    }

    private String getCities() {
        return cities;
    }
}
