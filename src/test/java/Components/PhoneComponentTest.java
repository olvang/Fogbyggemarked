package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;
import testDataSetup.TestDataSetup;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class PhoneComponentTest {

    @Test
    public void testCorrectPhone() {
        String phone = "12345678";
        try {
            PhoneComponent phoneComponent = new PhoneComponent(phone);
            assertTrue(phoneComponent.validate());
        } catch (ValidationFailedException ex) {
            fail("Validation failed");
        }
    }

    @Test (expected = ValidationFailedException.class)
    public void testTooShort() throws ValidationFailedException {
        String phone = "1234567";
        PhoneComponent phoneComponent = new PhoneComponent(phone);
    }

    @Test (expected = ValidationFailedException.class)
    public void testTooLong() throws ValidationFailedException {
        String phone = "123456789";
        PhoneComponent phoneComponent = new PhoneComponent(phone);
    }

    @Test (expected = ValidationFailedException.class)
    public void testIncorrectSymbol() throws ValidationFailedException {
        String phone = "1234567a";
        PhoneComponent phoneComponent = new PhoneComponent(phone);
    }

    @Test (expected = ValidationFailedException.class)
    public void testEmpty() throws ValidationFailedException {
        String phone = "";
        PhoneComponent phoneComponent = new PhoneComponent(phone);
    }

    @Test (expected = ValidationFailedException.class)
    public void testNull() throws ValidationFailedException {
        String phone = null;
        PhoneComponent phoneComponent = new PhoneComponent(phone);
    }
}
