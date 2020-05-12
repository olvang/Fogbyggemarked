package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;
import testDataSetup.TestDataSetup;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class CityComponentTest {

    @Test
    public void testCorrectCity() {
        String city =  "Århus";
        try {
            CityComponent cityComponent = new CityComponent(city);
            assertTrue(cityComponent.validate());
        } catch (ValidationFailedException e) {
            fail("Verification failed");
        }
    }

    @Test
    public void testCorrectCityAccens() {
        String city =  "Áârhüs";
        try {
            CityComponent cityComponent = new CityComponent(city);
            assertTrue(cityComponent.validate());
        } catch (ValidationFailedException e) {
            fail("Verification failed");
        }
    }

    @Test (expected = ValidationFailedException.class)
    public void testIncorrectCity() throws ValidationFailedException {
        String city = "200 this is not a city lol";
        CityComponent cityComponent = new CityComponent(city);
    }

    @Test (expected = ValidationFailedException.class)
    public void testNoCity() throws ValidationFailedException {
        String city = "";
        CityComponent cityComponent = new CityComponent(city);
    }

    @Test (expected = ValidationFailedException.class)
    public void testNullCity() throws ValidationFailedException {
        String city = null;
        CityComponent cityComponent = new CityComponent(city);
    }
}
