package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;
import testDataSetup.TestDataSetup;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class NameComponentTest {

    @Test
    public void testCorrectName() {
        String name = "Peter Petersen";
        try {
            NameComponent nameComponent = new NameComponent(name);
            assertTrue(nameComponent.validate());
        } catch (ValidationFailedException e) {
            fail("Validation failed");
        }
    }

    @Test
    public void testCorrectNameAccents() {
        String name = "PétÈr Påtørsæn";
        try {
            NameComponent nameComponent = new NameComponent(name);
            assertTrue(nameComponent.validate());
        } catch (ValidationFailedException e) {
            fail("Validation failed");
        }
    }

    @Test (expected = ValidationFailedException.class)
    public void testIncorrectName() throws ValidationFailedException {
        String name = "Peter Peters.en";
        NameComponent nameComponent = new NameComponent(name);
    }

    @Test (expected = ValidationFailedException.class)
    public void testNoName() throws ValidationFailedException {
        String name = "";
        NameComponent nameComponent = new NameComponent(name);
    }

    @Test (expected = ValidationFailedException.class)
    public void testNullName() throws ValidationFailedException {
        String name = null;
        NameComponent nameComponent = new NameComponent(name);
    }
}
