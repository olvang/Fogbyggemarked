package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class MaterialWidthComponentTest {

    @Test
    public void testSuccess() throws ValidationFailedException {
        int expected = 51;
        MaterialWidthComponent component = new MaterialWidthComponent(expected);

        assertEquals(component.getWidth(), expected);
    }

    @Test
    public void testSetValue() throws ValidationFailedException {
        int expected = 10;
        MaterialWidthComponent component = new MaterialWidthComponent(51);

        component.setWidth(expected);

        assertEquals(component.getWidth(), expected);
    }

    @Test
    public void testSuccessString() throws ValidationFailedException {
        int expected = 51;
        MaterialWidthComponent component = new MaterialWidthComponent("51");

        assertEquals(component.getWidth(), expected);
    }

    @Test (expected = ValidationFailedException.class)
    public void testStringFail () throws ValidationFailedException {
        MaterialWidthComponent component = new MaterialWidthComponent("hej");
    }

    @Test (expected = ValidationFailedException.class)
    public void testZero() throws ValidationFailedException {
        MaterialWidthComponent component = new MaterialWidthComponent(0);
    }

    @Test (expected = ValidationFailedException.class)
    public void testBelowZero() throws ValidationFailedException {
        MaterialWidthComponent component = new MaterialWidthComponent(-1);
    }

    @Test
    public void testBoundaryValueOne() throws ValidationFailedException {
        int expected = 1;
        MaterialWidthComponent component = new MaterialWidthComponent(1);

        assertEquals(component.getWidth(), expected);
    }

    @Test
    public void testEmptyString() {
        String expected = "Dette felt skal udfyldes.";
        String actual = "No exception was thrown";
        try {
            MaterialWidthComponent component = new MaterialWidthComponent("");
        }catch (ValidationFailedException ex) {
            actual = ex.getMessage();
        }
        assertEquals(expected, actual);
    }

    //Equals tests
    @Test
    public void testEqualsInt() throws ValidationFailedException {
        MaterialWidthComponent component1 = new MaterialWidthComponent(10);

        assertTrue(component1.equals(10));
    }

    @Test
    public void testEqualsObject() throws ValidationFailedException {
        MaterialWidthComponent component1 = new MaterialWidthComponent(10);
        MaterialWidthComponent component2= new MaterialWidthComponent(10);

        assertTrue(component1.equals(component2));
    }

    @Test
    public void testEqualsObjectFail() throws ValidationFailedException {
        MaterialWidthComponent component1 = new MaterialWidthComponent(10);
        MaterialWidthComponent component2= new MaterialWidthComponent(11);

        assertFalse(component1.equals(component2));
    }

    @Test
    public void testEqualsIntFail() throws ValidationFailedException {
        MaterialWidthComponent component1 = new MaterialWidthComponent(10);

        assertFalse(component1.equals(11));
    }

    @Test
    public void testEqualsWrongObjectType() throws ValidationFailedException {
        MaterialWidthComponent component1 = new MaterialWidthComponent(10);

        assertFalse(component1.equals(new MaterialLengthComponent(10)));
    }
}
