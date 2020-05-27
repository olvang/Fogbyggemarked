package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;

import static org.junit.Assert.*;

public class InclineComponentTest {

    @Test
    public void testSuccess() throws ValidationFailedException {
        int expected = 23;
        InclineComponent component = new InclineComponent(23);

        assertEquals(component.getIncline(), expected);
    }

    @Test
    public void testSetValue() throws ValidationFailedException {
        int expected = 23;
        InclineComponent component = new InclineComponent(25);

        component.setIncline(expected);

        assertEquals(component.getIncline(), expected);
    }

    @Test
    public void testZero() throws ValidationFailedException {
        InclineComponent component = new InclineComponent(0);
        assertEquals(0, component.getIncline());
    }

    @Test (expected = ValidationFailedException.class)
    public void testBelowZero() throws ValidationFailedException {
        InclineComponent component = new InclineComponent(-1);
    }

    @Test
    public void testBoundaryValueOne() throws ValidationFailedException {
        int expected = 25;
        InclineComponent component = new InclineComponent(25);

        assertEquals(component.getIncline(), expected);
    }
    @Test
    public void testBoundaryValueTwo() throws ValidationFailedException {
        int expected = 20;
        InclineComponent component = new InclineComponent(20);

        assertEquals(component.getIncline(), expected);
    }

    @Test (expected = ValidationFailedException.class)
    public void testMassiveValue() throws ValidationFailedException {
        InclineComponent component = new InclineComponent(2000000);
    }

    @Test
    public void testSetSuccess() throws ValidationFailedException {
        int var1 = 25;
        int var2 = 23;
        InclineComponent component = new InclineComponent(var1);
        try {
            component.setIncline(var2);
            assertEquals(var2, component.getIncline());
        } catch (ValidationFailedException e) {
            fail("Setting failed");
        }
    }

    @Test
    public void testSetFail() throws ValidationFailedException {
        int var1 = 25;
        int var2 = 2;
        InclineComponent component = null;
        try {
            component = new InclineComponent(var1);
        } catch (ValidationFailedException e) {
            fail("First validation failed");
        }
        try {
            component.setIncline(var2);
        }catch (ValidationFailedException e) {
            assertEquals(var1, component.getIncline());
        }
    }

}
