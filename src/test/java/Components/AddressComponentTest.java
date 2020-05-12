package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;
import testDataSetup.TestDataSetup;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class AddressComponentTest {

    @Test
    public void testCorrectAddress() {
        String address = "Københavnsvej 200";
        try {
            AddressComponent addressComponent = new AddressComponent(address);
            assertTrue(addressComponent.validate());
        } catch (ValidationFailedException e) {
            fail("Validation failed");
        }
    }

    @Test
    public void testCorrectAddressApartmnet() {
        String address = "Århusvej 20, 2 th";
        try {
            AddressComponent addressComponent = new AddressComponent(address);
            assertTrue(addressComponent.validate());
        } catch (ValidationFailedException e) {
            fail("Validation failed");
        }
    }

    @Test (expected = ValidationFailedException.class)
    public void testIncorrectAddress() throws ValidationFailedException {
        String address = "200 Darling Street";
        AddressComponent addressComponent = new AddressComponent(address);
    }

    @Test (expected = ValidationFailedException.class)
    public void testNoAddress() throws ValidationFailedException {
        String address = "";
        AddressComponent addressComponent = new AddressComponent(address);
    }

    @Test (expected = ValidationFailedException.class)
    public void testNullAddress() throws ValidationFailedException {
        String address = null;
        AddressComponent addressComponent = new AddressComponent(address);
    }
}
