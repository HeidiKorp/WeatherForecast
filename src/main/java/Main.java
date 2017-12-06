import weatherProgram.WeatherReportMaker;
import weatherProgram.WeatherReport;
import weatherProgram.WeatherRequest;
import weatherSpecifier.ConsoleReader;
import weatherSpecifier.RequestMaker;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {

        RequestMaker maker = new RequestMaker();
        //ConsoleReader reader = new ConsoleReader(new BufferedReader(new InputStreamReader(System.in)));
        //RequestMaker maker = new RequestMaker(reader.getCity(), reader.getCountry());

        maker.makeRequest();
//        RequestMaker maker2 = new RequestMaker();
//        maker2.makeRequest();

    }
}
