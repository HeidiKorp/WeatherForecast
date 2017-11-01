package weatherProgram;

import java.io.*;

public class WeatherCollection implements Weather {

    public WeatherReport getCurrentWeather(WeatherRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            //BufferedReader reader = new BufferedReader(new FileReader(request.filename));
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\korph\\IdeaProjects\\Weather\\src\\main\\java\\forecast.txt"));
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(stringBuilder.toString());
        return new WeatherReport(stringBuilder.toString());
        //return new WeatherReport("{\"coord\":{\"lon\":24.75,\"lat\":59.44},\"weather\":[{\"id\":301,\"main\":\"Drizzle\",\"description\":\"drizzle\",\"icon\":\"09n\"},{\"id\":701,\"main\":\"Mist\",\"description\":\"mist\",\"icon\":\"50n\"}],\"base\":\"stations\",\"main\":{\"temp\":275.15,\"pressure\":976,\"humidity\":100,\"temp_min\":275.15,\"temp_max\":275.15},\"visibility\":4600,\"wind\":{\"speed\":3.6,\"deg\":160},\"clouds\":{\"all\":90},\"dt\":1509225600,\"sys\":{\"type\":1,\"id\":5014,\"message\":0.0037,\"country\":\"EE\",\"sunrise\":1509168792,\"sunset\":1509201311},\"id\":588409,\"name\":\"Tallinn\",\"cod\":200}");
    }

}
