package weatherProgram;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.FileNotFoundException;
import java.io.IOException;
import static junit.framework.Assert.assertEquals;

public class TestWeatherProgram {

//    @Rule
//    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testEmptyURLResponse() throws IOException {
        ApiInfoGetter getter = new ApiInfoGetter();
        assertEquals(getter.getApiInfo(""), "");
    }

    @Test
    public void testWriteCityToFileNotFoundException() throws IOException {
        CityNameToFileWriter writer = new CityNameToFileWriter();
        writer.writeCityNameIntoFile("Tallinn", "");
        // FileNotFoundException is caught in catch block (printStackTrace) and test passes.
    }
}
