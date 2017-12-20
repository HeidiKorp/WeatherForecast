package weatherProgram;

import dataParser.DataExtractor;
import dataParser.ReportCompiler;
import org.junit.Before;
import org.junit.Test;
import weatherSpecifier.CurrentWeatherURLCompiler;

import javax.xml.crypto.Data;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestWeatherProgram {

    private String current;
    private String forecast;
    private WeatherReport report;
    private ApiInfoGetter apiInfoGetter;
    private RequestedCityNameWriter nameWriter;
    private WeatherRepository repositoryMock;
    private WeatherRepository repository;
    private CurrentWeatherURLCompiler compiler;
    private InfoToFileWriter fileWriterMock;
    private BufferedWriter bufferedWriter;
    private InfoToFileWriter fileWriter;

    @Before
    public void makeObjects() throws IOException {
        current = "{\"coord\":{\"lon\":24.75,\"lat\":59.44},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"base\":\"stations\",\"main\":{\"temp\":276.15,\"pressure\":992,\"humidity\":93,\"temp_min\":276.15,\"temp_max\":276.15},\"visibility\":10000,\"wind\":{\"speed\":5.7,\"deg\":220},\"clouds\":{\"all\":40},\"dt\":1510518000,\"sys\":{\"type\":1,\"id\":5014,\"message\":0.0218,\"country\":\"EE\",\"sunrise\":1510467028,\"sunset\":1510495155},\"id\":588409,\"name\":\"Tallinn\",\"cod\":200}";
        forecast = "{\"cod\":\"200\",\"message\":0.004,\"cnt\":40,\"list\":[{\"dt\":1510531200,\"main\":{\"temp\":276.44,\"temp_min\":276.44,\"temp_max\":278.274,\"pressure\":1004.87,\"sea_level\":1008.54,\"grnd_level\":1004.87,\"humidity\":100,\"temp_kf\":-1.84},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":88},\"wind\":{\"speed\":7.51,\"deg\":256.001},\"rain\":{\"3h\":0.5},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-13 00:00:00\"},{\"dt\":1510542000,\"main\":{\"temp\":276.2,\"temp_min\":276.2,\"temp_max\":277.574,\"pressure\":1007.02,\"sea_level\":1010.72,\"grnd_level\":1007.02,\"humidity\":100,\"temp_kf\":-1.38},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":88},\"wind\":{\"speed\":6.36,\"deg\":262.002},\"rain\":{\"3h\":1.92},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-13 03:00:00\"},{\"dt\":1510552800,\"main\":{\"temp\":276.06,\"temp_min\":276.06,\"temp_max\":276.982,\"pressure\":1009.75,\"sea_level\":1013.31,\"grnd_level\":1009.75,\"humidity\":100,\"temp_kf\":-0.92},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":76},\"wind\":{\"speed\":4.17,\"deg\":281.501},\"rain\":{\"3h\":1.7},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-13 06:00:00\"},{\"dt\":1510563600,\"main\":{\"temp\":277.63,\"temp_min\":277.63,\"temp_max\":278.088,\"pressure\":1012.4,\"sea_level\":1015.96,\"grnd_level\":1012.4,\"humidity\":100,\"temp_kf\":-0.46},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":68},\"wind\":{\"speed\":3.91,\"deg\":287.001},\"rain\":{\"3h\":0.705},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2017-11-13 09:00:00\"},{\"dt\":1510574400,\"main\":{\"temp\":278.704,\"temp_min\":278.704,\"temp_max\":278.704,\"pressure\":1013.76,\"sea_level\":1017.35,\"grnd_level\":1013.76,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":64},\"wind\":{\"speed\":3.8,\"deg\":286.001},\"rain\":{\"3h\":0.0050000000000008},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2017-11-13 12:00:00\"},{\"dt\":1510585200,\"main\":{\"temp\":277.912,\"temp_min\":277.912,\"temp_max\":277.912,\"pressure\":1015.44,\"sea_level\":1019,\"grnd_level\":1015.44,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":88},\"wind\":{\"speed\":3.02,\"deg\":280.506},\"rain\":{\"3h\":0.035},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-13 15:00:00\"},{\"dt\":1510596000,\"main\":{\"temp\":277.351,\"temp_min\":277.351,\"temp_max\":277.351,\"pressure\":1016.65,\"sea_level\":1020.21,\"grnd_level\":1016.65,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":80},\"wind\":{\"speed\":2.5,\"deg\":261.002},\"rain\":{\"3h\":0.115},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-13 18:00:00\"},{\"dt\":1510606800,\"main\":{\"temp\":277.303,\"temp_min\":277.303,\"temp_max\":277.303,\"pressure\":1016.86,\"sea_level\":1020.39,\"grnd_level\":1016.86,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":88},\"wind\":{\"speed\":2.68,\"deg\":277.502},\"rain\":{\"3h\":0.37},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-13 21:00:00\"},{\"dt\":1510617600,\"main\":{\"temp\":276.881,\"temp_min\":276.881,\"temp_max\":276.881,\"pressure\":1016.84,\"sea_level\":1020.49,\"grnd_level\":1016.84,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":68},\"wind\":{\"speed\":2.5,\"deg\":276.501},\"rain\":{\"3h\":0.025},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-14 00:00:00\"},{\"dt\":1510628400,\"main\":{\"temp\":277.006,\"temp_min\":277.006,\"temp_max\":277.006,\"pressure\":1016.79,\"sea_level\":1020.42,\"grnd_level\":1016.79,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":88},\"wind\":{\"speed\":2.82,\"deg\":270.502},\"rain\":{\"3h\":0.035},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-14 03:00:00\"},{\"dt\":1510639200,\"main\":{\"temp\":277.103,\"temp_min\":277.103,\"temp_max\":277.103,\"pressure\":1016.8,\"sea_level\":1020.42,\"grnd_level\":1016.8,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":2.74,\"deg\":249.502},\"rain\":{\"3h\":0.3},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-14 06:00:00\"},{\"dt\":1510650000,\"main\":{\"temp\":278.168,\"temp_min\":278.168,\"temp_max\":278.168,\"pressure\":1017.09,\"sea_level\":1020.71,\"grnd_level\":1017.09,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":88},\"wind\":{\"speed\":4.27,\"deg\":261.501},\"rain\":{\"3h\":0.21},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2017-11-14 09:00:00\"},{\"dt\":1510660800,\"main\":{\"temp\":278.439,\"temp_min\":278.439,\"temp_max\":278.439,\"pressure\":1017.38,\"sea_level\":1020.88,\"grnd_level\":1017.38,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":4.98,\"deg\":268.5},\"rain\":{\"3h\":0.115},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2017-11-14 12:00:00\"},{\"dt\":1510671600,\"main\":{\"temp\":278.137,\"temp_min\":278.137,\"temp_max\":278.137,\"pressure\":1017.86,\"sea_level\":1021.5,\"grnd_level\":1017.86,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":5.86,\"deg\":253.003},\"rain\":{\"3h\":0.025},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-14 15:00:00\"},{\"dt\":1510682400,\"main\":{\"temp\":277.121,\"temp_min\":277.121,\"temp_max\":277.121,\"pressure\":1017.87,\"sea_level\":1021.42,\"grnd_level\":1017.87,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"clouds\":{\"all\":36},\"wind\":{\"speed\":5.53,\"deg\":236.003},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-14 18:00:00\"},{\"dt\":1510693200,\"main\":{\"temp\":276.436,\"temp_min\":276.436,\"temp_max\":276.436,\"pressure\":1016.61,\"sea_level\":1020.13,\"grnd_level\":1016.61,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":68},\"wind\":{\"speed\":6.57,\"deg\":200.502},\"rain\":{\"3h\":0.065},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-14 21:00:00\"},{\"dt\":1510704000,\"main\":{\"temp\":276.704,\"temp_min\":276.704,\"temp_max\":276.704,\"pressure\":1014.39,\"sea_level\":1017.93,\"grnd_level\":1014.39,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":9.55,\"deg\":195.005},\"rain\":{\"3h\":0.785},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-15 00:00:00\"},{\"dt\":1510714800,\"main\":{\"temp\":278.527,\"temp_min\":278.527,\"temp_max\":278.527,\"pressure\":1013.98,\"sea_level\":1017.62,\"grnd_level\":1013.98,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":80},\"wind\":{\"speed\":6.4,\"deg\":270.003},\"rain\":{\"3h\":1.175},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-15 03:00:00\"},{\"dt\":1510725600,\"main\":{\"temp\":278.023,\"temp_min\":278.023,\"temp_max\":278.023,\"pressure\":1015.44,\"sea_level\":1018.97,\"grnd_level\":1015.44,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02n\"}],\"clouds\":{\"all\":24},\"wind\":{\"speed\":7.68,\"deg\":279.501},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-15 06:00:00\"},{\"dt\":1510736400,\"main\":{\"temp\":279.244,\"temp_min\":279.244,\"temp_max\":279.244,\"pressure\":1016.66,\"sea_level\":1020.2,\"grnd_level\":1016.66,\"humidity\":96,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":7.35,\"deg\":264.501},\"rain\":{},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2017-11-15 09:00:00\"},{\"dt\":1510747200,\"main\":{\"temp\":279.973,\"temp_min\":279.973,\"temp_max\":279.973,\"pressure\":1017.23,\"sea_level\":1020.73,\"grnd_level\":1017.23,\"humidity\":97,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"02d\"}],\"clouds\":{\"all\":8},\"wind\":{\"speed\":7,\"deg\":258.001},\"rain\":{},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2017-11-15 12:00:00\"},{\"dt\":1510758000,\"main\":{\"temp\":279.574,\"temp_min\":279.574,\"temp_max\":279.574,\"pressure\":1016.35,\"sea_level\":1019.94,\"grnd_level\":1016.35,\"humidity\":99,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":7.06,\"deg\":235.501},\"rain\":{\"3h\":0.049999999999999},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-15 15:00:00\"},{\"dt\":1510768800,\"main\":{\"temp\":279.452,\"temp_min\":279.452,\"temp_max\":279.452,\"pressure\":1014.13,\"sea_level\":1017.73,\"grnd_level\":1014.13,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":8.59,\"deg\":233.502},\"rain\":{\"3h\":2.5},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-15 18:00:00\"},{\"dt\":1510779600,\"main\":{\"temp\":278.878,\"temp_min\":278.878,\"temp_max\":278.878,\"pressure\":1015.95,\"sea_level\":1019.42,\"grnd_level\":1015.95,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":56},\"wind\":{\"speed\":10.72,\"deg\":297},\"rain\":{\"3h\":2.58},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-15 21:00:00\"},{\"dt\":1510790400,\"main\":{\"temp\":278.553,\"temp_min\":278.553,\"temp_max\":278.553,\"pressure\":1018.13,\"sea_level\":1021.58,\"grnd_level\":1018.13,\"humidity\":98,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":10.01,\"deg\":293},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-16 00:00:00\"},{\"dt\":1510801200,\"main\":{\"temp\":278.229,\"temp_min\":278.229,\"temp_max\":278.229,\"pressure\":1019.5,\"sea_level\":1023.06,\"grnd_level\":1019.5,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":8.56,\"deg\":287.503},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-16 03:00:00\"},{\"dt\":1510812000,\"main\":{\"temp\":277.824,\"temp_min\":277.824,\"temp_max\":277.824,\"pressure\":1021.3,\"sea_level\":1024.79,\"grnd_level\":1021.3,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"clouds\":{\"all\":32},\"wind\":{\"speed\":7.76,\"deg\":289.508},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-16 06:00:00\"},{\"dt\":1510822800,\"main\":{\"temp\":278.811,\"temp_min\":278.811,\"temp_max\":278.811,\"pressure\":1023.46,\"sea_level\":1027.04,\"grnd_level\":1023.46,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":68},\"wind\":{\"speed\":7.54,\"deg\":301.003},\"rain\":{\"3h\":0.010000000000002},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2017-11-16 09:00:00\"},{\"dt\":1510833600,\"main\":{\"temp\":279.128,\"temp_min\":279.128,\"temp_max\":279.128,\"pressure\":1025.13,\"sea_level\":1028.74,\"grnd_level\":1025.13,\"humidity\":98,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":76},\"wind\":{\"speed\":8.31,\"deg\":295.001},\"rain\":{},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2017-11-16 12:00:00\"},{\"dt\":1510844400,\"main\":{\"temp\":278.548,\"temp_min\":278.548,\"temp_max\":278.548,\"pressure\":1026.68,\"sea_level\":1030.3,\"grnd_level\":1026.68,\"humidity\":99,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"clouds\":{\"all\":36},\"wind\":{\"speed\":7.99,\"deg\":295.501},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-16 15:00:00\"},{\"dt\":1510855200,\"main\":{\"temp\":277.666,\"temp_min\":277.666,\"temp_max\":277.666,\"pressure\":1027.98,\"sea_level\":1031.63,\"grnd_level\":1027.98,\"humidity\":99,\"temp_kf\":0},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"clouds\":{\"all\":32},\"wind\":{\"speed\":6.44,\"deg\":300.503},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-16 18:00:00\"},{\"dt\":1510866000,\"main\":{\"temp\":277.018,\"temp_min\":277.018,\"temp_max\":277.018,\"pressure\":1028.89,\"sea_level\":1032.51,\"grnd_level\":1028.89,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02n\"}],\"clouds\":{\"all\":20},\"wind\":{\"speed\":4.16,\"deg\":293.503},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-16 21:00:00\"},{\"dt\":1510876800,\"main\":{\"temp\":275.564,\"temp_min\":275.564,\"temp_max\":275.564,\"pressure\":1029.47,\"sea_level\":1033.17,\"grnd_level\":1029.47,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":2.27,\"deg\":240.502},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-17 00:00:00\"},{\"dt\":1510887600,\"main\":{\"temp\":274.179,\"temp_min\":274.179,\"temp_max\":274.179,\"pressure\":1029.01,\"sea_level\":1032.61,\"grnd_level\":1029.01,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":4.08,\"deg\":185.503},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-17 03:00:00\"},{\"dt\":1510898400,\"main\":{\"temp\":273.91,\"temp_min\":273.91,\"temp_max\":273.91,\"pressure\":1027.94,\"sea_level\":1031.63,\"grnd_level\":1027.94,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":0},\"wind\":{\"speed\":3.41,\"deg\":167.507},\"rain\":{},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-17 06:00:00\"},{\"dt\":1510909200,\"main\":{\"temp\":275.643,\"temp_min\":275.643,\"temp_max\":275.643,\"pressure\":1027.43,\"sea_level\":1031.01,\"grnd_level\":1027.43,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":12},\"wind\":{\"speed\":6.89,\"deg\":172.001},\"rain\":{\"3h\":0.02},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2017-11-17 09:00:00\"},{\"dt\":1510920000,\"main\":{\"temp\":277.438,\"temp_min\":277.438,\"temp_max\":277.438,\"pressure\":1025.74,\"sea_level\":1029.34,\"grnd_level\":1025.74,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":64},\"wind\":{\"speed\":6.91,\"deg\":167.001},\"rain\":{\"3h\":0.059999999999999},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2017-11-17 12:00:00\"},{\"dt\":1510930800,\"main\":{\"temp\":276.986,\"temp_min\":276.986,\"temp_max\":276.986,\"pressure\":1024.26,\"sea_level\":1027.8,\"grnd_level\":1024.26,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":88},\"wind\":{\"speed\":8.42,\"deg\":168.01},\"rain\":{\"3h\":0.11},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-17 15:00:00\"},{\"dt\":1510941600,\"main\":{\"temp\":276.536,\"temp_min\":276.536,\"temp_max\":276.536,\"pressure\":1022.82,\"sea_level\":1026.37,\"grnd_level\":1022.82,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":80},\"wind\":{\"speed\":9.17,\"deg\":171.506},\"rain\":{\"3h\":0.07},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-17 18:00:00\"},{\"dt\":1510952400,\"main\":{\"temp\":276.111,\"temp_min\":276.111,\"temp_max\":276.111,\"pressure\":1021.27,\"sea_level\":1024.85,\"grnd_level\":1021.27,\"humidity\":100,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":92},\"wind\":{\"speed\":9.46,\"deg\":172.003},\"rain\":{\"3h\":0.17},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2017-11-17 21:00:00\"}],\"city\":{\"id\":588409,\"name\":\"Tallinn\",\"coord\":{\"lat\":59.437,\"lon\":24.7535},\"country\":\"EE\"}}";
        report = new WeatherReport(new DataExtractor(), new ReportCompiler());
        report.parseJson(current, forecast);
        apiInfoGetter = new ApiInfoGetter();
        repositoryMock = mock(WeatherRepository.class);
        repository = new WeatherRepository();
        nameWriter = new RequestedCityNameWriter(apiInfoGetter);
        compiler = new CurrentWeatherURLCompiler();
        fileWriterMock = mock(InfoToFileWriter.class);
        bufferedWriter = mock(BufferedWriter.class);
        fileWriter = new InfoToFileWriter();
    }

    @Test
    public void reportMakerCorrect() throws IOException {
        assertEquals(report.getHighestTemperatures(),
                report.getHighestTemperatures());
    }

    @Test
    public void testUrlResponse() throws IOException {
        ApiInfoGetter getter = new ApiInfoGetter();
        getter.getApiInfo("http://api.openweathermap.org/data/2.5/weather?q=Tallinn&APPID=7d1fdfe09df058c46a81bb575c22ac96");
    }

    @Test
    public void testEmptyURLResponse() throws IOException {
        ApiInfoGetter getter = new ApiInfoGetter();
        assertEquals(getter.getApiInfo(""), "");
    }

    @Test(expected = FileNotFoundException.class)
    public void testWriteCityToFileNotFoundException() throws IOException {
        fileWriterMock.writeCityIntoFile("Tallinn", new BufferedWriter(new FileWriter("")));
    }

    @Test(expected = FileNotFoundException.class)
    public void testWriteReportToFileNotFoundException() throws IOException {
        fileWriterMock.writeWeatherIntoFile("Talinn", new BufferedWriter(new FileWriter("")));
    }

    @Test
    public void testWritingIntoFile() throws IOException {
        fileWriter.writeWeatherIntoFile("Weather", bufferedWriter);
        verify(bufferedWriter).write("Weather");
    }

    @Test
    public void testCloseWritingIntoFile() throws IOException {
        fileWriter.writeWeatherIntoFile("Weather", bufferedWriter);
        verify(bufferedWriter).close();
    }

    @Test
    public void testNoFileFound() {
        fileWriter.writeCityIntoFile(null, null);
    }

    @Test
    public void testSetCityName() {
        repository.setCity("Tallinn");
        assertEquals("Tallinn", repository.getCity());
    }

    @Test
    public void testSetCountry() {
        repository.setCountry("EE");
        assertEquals("EE", repository.getCountry());
    }

    @Test
    public void testSetCurrentWeatherUrl() {
        repository.setCurrentWeatherUrl("Curr");
        assertEquals("Curr", repository.getCurrentWeatherUrl());
    }

    @Test
    public void testSetforecastWeatherUrl() {
        repository.setForecastWeatherUrl("Fore");
        assertEquals("Fore", repository.getForecastWeatherUrl());
    }

    @Test
    public void testUrlCompiler() {
        CurrentWeatherURLCompiler compiler = new CurrentWeatherURLCompiler(null);
        System.out.println(compiler.compileURL());
    }

    @Test
    public void testGetCurrentUrl() throws IOException {
        assertEquals(nameWriter.getCurrentWeatherData(apiInfoGetter, ""), "");
    }

    @Test
    public void testGetForecastUrl() throws IOException {
        assertEquals(nameWriter.getForecastWeatherData(apiInfoGetter, ""), "");
    }

}
