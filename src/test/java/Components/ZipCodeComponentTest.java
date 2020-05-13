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
}
