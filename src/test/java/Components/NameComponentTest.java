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

    @Test
    public void testSetSuccess() throws ValidationFailedException {
        String var1 = "Frederik Dinsen";
        String var2 = "Peter petersen";
        NameComponent component = new NameComponent(var1);
        try {
            component.setName(var2);
            assertEquals(var2, component.getName());
        } catch (ValidationFailedException e) {
            fail("Setting failed");
        }
    }

    @Test
    public void testSetFail() throws ValidationFailedException {
        String var1 = "Frederik Dinsen";
        String var2 = "";
        NameComponent component = null;
        try {
            component = new NameComponent(var1);
        } catch (ValidationFailedException e) {
            fail("First validation failed");
        }
        try {
            component.setName(var2);
        }catch (ValidationFailedException e) {
            assertEquals(var1, component.getName());
        }
    }
}
