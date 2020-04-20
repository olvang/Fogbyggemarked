package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WidthComponentTest {

    @Test
    public void testSuccess() throws ValidationFailedException {
        int expected = 51;
        WidthComponent component = new WidthComponent(51);

        assertEquals(component.getWidth(), expected);
    }

    @Test
    public void testSetValue() throws ValidationFailedException {
        int expected = 10;
        WidthComponent component = new WidthComponent(51);

        component.setWidth(expected);

        assertEquals(component.getWidth(), expected);
    }

    @Test
    public void testSuccessString() throws ValidationFailedException {
        int expected = 51;
        WidthComponent component = new WidthComponent("51");

        assertEquals(component.getWidth(), expected);
    }

    @Test (expected = ValidationFailedException.class)
    public void testStringFail () throws ValidationFailedException {
        WidthComponent component = new WidthComponent("hej");
    }

    @Test (expected = ValidationFailedException.class)
    public void testZero() throws ValidationFailedException {
        WidthComponent component = new WidthComponent(0);
    }

    @Test (expected = ValidationFailedException.class)
    public void testBelowZero() throws ValidationFailedException {
        WidthComponent component = new WidthComponent(-1);
    }

    @Test
    public void testBoundaryValueOne() throws ValidationFailedException {
        int expected = 1;
        WidthComponent component = new WidthComponent(1);

        assertEquals(component.getWidth(), expected);
    }

    @Test (expected = ValidationFailedException.class)
    public void testMassiveValue() throws ValidationFailedException {
        WidthComponent component = new WidthComponent(2000000);
    }
}
