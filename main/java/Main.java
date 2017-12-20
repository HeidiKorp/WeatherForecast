import weatherProgram.WeatherRepository;
import weatherSpecifier.ConsoleReader;
import weatherSpecifier.WeatherRequestMaker;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            WeatherRequestMaker maker = new WeatherRequestMaker(new ConsoleReader().makeConsoleReader(), new WeatherRepository());
            maker.makeRequest();
            WeatherRequestMaker maker2 = new WeatherRequestMaker(new ConsoleReader().makeConsoleReader(), new WeatherRepository());
            maker2.makeRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
