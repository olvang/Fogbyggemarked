package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;
import testDataSetup.TestDataSetup;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class EmailComponentTest {

    @Test
    public void testCorrectEmail() {
        String email = "peter@gmail.com";
        try {
            EmailComponent emailComponent = new EmailComponent(email);
            assertTrue(emailComponent.validate());
        } catch (ValidationFailedException ex) {
            fail("Validation failed");
        }
    }

    @Test
    public void testCorrectEmailCapital() {
        String email = "PeTeR@HoTmaIl.com";
        try {
            EmailComponent emailComponent = new EmailComponent(email);
            assertTrue(emailComponent.validate());
        } catch (ValidationFailedException ex) {
            fail("Validation failed");
        }
    }

    @Test (expected = ValidationFailedException.class)
    public void testIncorrectNoAtSymbol() throws ValidationFailedException {
        String email = "PeTeRatHoTmaIl.com";
        EmailComponent emailComponent = new EmailComponent(email);
    }

    @Test (expected = ValidationFailedException.class)
    public void testIncorrectNoTopDomain() throws ValidationFailedException {
        String email = "PeTeRa@tHoTmaIl";
        EmailComponent emailComponent = new EmailComponent(email);
    }

    @Test (expected = ValidationFailedException.class)
    public void testIncorrectNoDomain() throws ValidationFailedException {
        String email = "PeTeRa@.com";
        EmailComponent emailComponent = new EmailComponent(email);
    }

    @Test (expected = ValidationFailedException.class)
    public void testEmpty() throws ValidationFailedException {
        String email = "";
        EmailComponent emailComponent = new EmailComponent(email);
    }

    @Test (expected = ValidationFailedException.class)
    public void testNull() throws ValidationFailedException {
        String email = null;
        EmailComponent emailComponent = new EmailComponent(email);
    }
}
