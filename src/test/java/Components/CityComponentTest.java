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
        String city =  "Helsingør";
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

    @Test
    public void testSetSuccess() throws ValidationFailedException {
        String city1 = "Københavnby";
        String city2 = "Københavnby";
        CityComponent component = new CityComponent(city1);
        try {
            component.setCity(city2);
            assertEquals(city2, component.getCity());
        } catch (ValidationFailedException e) {
            fail("Setting failed");
        }
    }

    @Test
    public void testSetFail() throws ValidationFailedException {
        String city1 = "Københavnby";
        String city2 = "";
        CityComponent component = null;
        try {
            component = new CityComponent(city1);
        } catch (ValidationFailedException e) {
            fail("First validation failed");
        }
        try {
            component.setCity(city2);
        }catch (ValidationFailedException e) {
            assertEquals(city1, component.getCity());
        }
    }
}
