import org.testng.annotations.*;

import static modules.ApiRequests.getWeather;
import static modules.Logging.*;
import static org.testng.Assert.*;


public class Api_tests {
    static float min = 9999;
    static float max = -9999;

    @BeforeTest
    private void beforeTest() {
        writeStrToFile(logname,"--- Some Api Test ---");

    }

    @BeforeMethod
    private void testBefore() {
        writeStrToFile(logname,"----> Api test: ");
    }

    @AfterMethod
    private void testAfter() {
        writeStrToFile(logname,"<---- Api test complete ");
    }

    @Test (description = "Some Test", priority=1, enabled = true)
    private void weather() {
        writeStrToFile(logname, "---- Weather  ----");
        try {
            min = Float.parseFloat(System.getenv("Weather_MIN"));
            max = Float.parseFloat(System.getenv("Weather_MAX"));
        }
        catch (NumberFormatException | NullPointerException nfe){
            writeStrToFile(logname, "Error in max|min weather! NumberFormatException: " + nfe.getMessage());
        }
        if (min == 9999)  min = -10;
        if (max == -9999)  max = 100;
        writeStrToFile(logname, "Min temp: " + min + " - Max temp: " + max);

        float temp;

        temp = getWeather();
        assertTrue(temp < max);
        assertTrue(temp > min);
       // assert temp > min : "--- less when min temp: " + temp + "---";
       // assert temp < max : "--- more when max temp: " + temp + "---";

    }


    @AfterTest
    private void afterTest() {
        writeStrToFile(logname,"--- Some Api Test complete ---");
    }
}
