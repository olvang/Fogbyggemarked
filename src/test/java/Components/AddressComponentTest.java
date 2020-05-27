package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Assert;
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

    @Test
    public void testSetAddressSuccess() throws ValidationFailedException {
        String address1 = "Århusvej 20, 2 th";
        String address2 = "Københavnvej 22, 1 tv";
        AddressComponent address = new AddressComponent(address1);
        try {
            address.setAddress(address2);
            assertEquals(address2, address.getAddress());
        } catch (ValidationFailedException e) {
            fail("Setting failed");
        }
    }

    @Test
    public void testSetAddressFail() throws ValidationFailedException {
        String address1 = "Århusvej 20, 2 th";
        String address2 = "";
        AddressComponent address = null;
        try {
            address = new AddressComponent(address1);
        } catch (ValidationFailedException e) {
            fail("First validation failed");
        }
        try {
            address.setAddress(address2);
        }catch (ValidationFailedException e) {
            assertEquals(address1, address.getAddress());
        }
    }

}
