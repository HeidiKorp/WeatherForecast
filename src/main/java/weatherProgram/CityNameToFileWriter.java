package weatherProgram;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CityNameToFileWriter {

    void writeCityNameIntoFile(String city, String filename) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename));
            bufferedWriter.write(city + "\n");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
