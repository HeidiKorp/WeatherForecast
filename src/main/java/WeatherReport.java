package weatherProgram;

public class WeatherReport {

    public WeatherReport(String jsonWeatherData) {
        // TODO Compile a report using other methods to get necessary data.
    }

    public float getHighestTemperature() {
        return 0;
    }

    public float getLowestTemperature() {
        return 0;
    }

    public String getCoordinates() {
        return String.format("%.0f:%.0f", getLatitude(), getLongitude());
    }

    public String getCityName() {
        return null;
    }

    public String getCountry() {
        return null;
    }

    public float getLatitude() {
        return 0;
    }

    public float getLongitude() {
        return 0;
    }

    public float changeTempSystem(String system, float temp) {
        float changedTemp = 0;
        if (system.equals("celsius")) {
            // TODO change temp into fahrenheit system;
            changedTemp = 0;
        } else if (system.equals("fahreinheit")) {
            changedTemp = 0;
        } else if (system.equals("kelvin")) {
            changedTemp = 0;
        }
        return changedTemp;
    }

    public String getReport() {
        // TODO Compile the full report;
        return null;
    }
}
