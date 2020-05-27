package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;
import testDataSetup.TestDataSetup;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class ZipCodeComponentTest {

    @Test
    public void testCorrectZip() {
        String zip = "1234";
        try {
            ZipCodeComponent zipCodeComponent = new ZipCodeComponent(zip);
            assertTrue(zipCodeComponent.validate());
        } catch (ValidationFailedException ex) {
            fail("Validation failed");
        }
    }

    @Test (expected = ValidationFailedException.class)
    public void testTooShort() throws ValidationFailedException {
        String zip = "123";
        ZipCodeComponent zipCodeComponent = new ZipCodeComponent(zip);
    }

    @Test (expected = ValidationFailedException.class)
    public void testFailStartingWith0() throws ValidationFailedException {
        String zip = "0123";
        ZipCodeComponent zipCodeComponent = new ZipCodeComponent(zip);
    }

    @Test (expected = ValidationFailedException.class)
    public void testTooLong() throws ValidationFailedException {
        String zip = "12345";
        ZipCodeComponent zipCodeComponent = new ZipCodeComponent(zip);
    }

    @Test (expected = ValidationFailedException.class)
    public void testIncorrectSymbol() throws ValidationFailedException {
        String zip = "123a";
        ZipCodeComponent zipCodeComponent = new ZipCodeComponent(zip);
    }

    @Test (expected = ValidationFailedException.class)
    public void testEmpty() throws ValidationFailedException {
        String zip = "";
        ZipCodeComponent zipCodeComponent = new ZipCodeComponent(zip);
    }

    @Test (expected = ValidationFailedException.class)
    public void testNull() throws ValidationFailedException {
        String zip = null;
        ZipCodeComponent zipCodeComponent = new ZipCodeComponent(zip);
    }

    @Test
    public void testSetSuccess() throws ValidationFailedException {
        String var1 = "3214";
        String var2 = "5544";
        ZipCodeComponent component = new ZipCodeComponent(var1);
        try {
            component.setZip(var2);
            assertEquals(var2, component.getZip());
        } catch (ValidationFailedException e) {
            fail("Setting failed");
        }
    }

    @Test
    public void testSetFail() throws ValidationFailedException {
        String var1 = "1234";
        String var2 = "";
        ZipCodeComponent component = null;
        try {
            component = new ZipCodeComponent(var1);
        } catch (ValidationFailedException e) {
            fail("First validation failed");
        }
        try {
            component.setZip(var2);
        }catch (ValidationFailedException e) {
            assertEquals(var1, component.getZip());
        }
    }
}
