package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

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

    @Test
    public void testEmptyString() {
        String expected = "Dette felt skal udfyldes.";
        String actual = "No exception was thrown";
        try {
            WidthComponent component = new WidthComponent("");
        }catch (ValidationFailedException ex) {
            actual = ex.getMessage();
        }
        assertEquals(expected, actual);
    }

    //Equals tests
    @Test
    public void testEqualsInt() throws ValidationFailedException {
        WidthComponent component1 = new WidthComponent(10);

        assertTrue(component1.equals(10));
    }

    @Test
    public void testEqualsObject() throws ValidationFailedException {
        WidthComponent component1 = new WidthComponent(10);
        WidthComponent component2= new WidthComponent(10);

        assertTrue(component1.equals(component2));
    }

    @Test
    public void testEqualsObjectFail() throws ValidationFailedException {
        WidthComponent component1 = new WidthComponent(10);
        WidthComponent component2= new WidthComponent(11);

        assertFalse(component1.equals(component2));
    }

    @Test
    public void testEqualsIntFail() throws ValidationFailedException {
        WidthComponent component1 = new WidthComponent(10);

        assertFalse(component1.equals(11));
    }

    @Test
    public void testEqualsWrongObjectType() throws ValidationFailedException {
        WidthComponent component1 = new WidthComponent(10);

        assertFalse(component1.equals(new String()));
    }

}
