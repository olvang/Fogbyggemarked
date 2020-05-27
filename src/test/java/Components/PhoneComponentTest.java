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
    public void testFailStartWith0() throws ValidationFailedException {
        String phone = "01234567";
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

    @Test
    public void testSetSuccess() throws ValidationFailedException {
        String var1 = "32145676";
        String var2 = "98765432";
        PhoneComponent component = new PhoneComponent(var1);
        try {
            component.setPhone(var2);
            assertEquals(var2, component.getPhone());
        } catch (ValidationFailedException e) {
            fail("Setting failed");
        }
    }

    @Test
    public void testSetFail() throws ValidationFailedException {
        String var1 = "12345678";
        String var2 = "";
        PhoneComponent component = null;
        try {
            component = new PhoneComponent(var1);
        } catch (ValidationFailedException e) {
            fail("First validation failed");
        }
        try {
            component.setPhone(var2);
        }catch (ValidationFailedException e) {
            assertEquals(var1, component.getPhone());
        }
    }

}
