package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaterialLengthComponentTest {

    @Test
    public void testSuccess() throws ValidationFailedException {
        int expected = 51;
        MaterialLengthComponent component = new MaterialLengthComponent(expected);

        assertEquals(component.getLength(), expected);
    }

    @Test
    public void testSetValue() throws ValidationFailedException {
        int expected = 10;
        MaterialLengthComponent component = new MaterialLengthComponent(51);

        component.setLength(expected);

        assertEquals(component.getLength(), expected);
    }

    @Test
    public void testSuccessString() throws ValidationFailedException {
        int expected = 51;
        MaterialLengthComponent component = new MaterialLengthComponent("51");

        assertEquals(component.getLength(), expected);
    }

    @Test (expected = ValidationFailedException.class)
    public void testStringFail () throws ValidationFailedException {
        MaterialLengthComponent component = new MaterialLengthComponent("hej");
    }

    @Test
    public void testZero() throws ValidationFailedException {
        MaterialLengthComponent component = new MaterialLengthComponent(0);
        assertEquals(0, component.getLength());
    }

    @Test (expected = ValidationFailedException.class)
    public void testBelowZero() throws ValidationFailedException {
        MaterialLengthComponent component = new MaterialLengthComponent(-1);
    }

    @Test
    public void testBoundaryValueOne() throws ValidationFailedException {
        int expected = 1;
        MaterialLengthComponent component = new MaterialLengthComponent(1);

        assertEquals(component.getLength(), expected);
    }

    @Test
    public void testEmptyString() {
        String expected = "Dette felt skal udfyldes.";
        String actual = "No exception was thrown";
        try {
            MaterialLengthComponent component = new MaterialLengthComponent("");
        }catch (ValidationFailedException ex) {
            actual = ex.getMessage();
        }
        assertEquals(expected, actual);
    }

    //Equals tests
    @Test
    public void testEqualsInt() throws ValidationFailedException {
        MaterialLengthComponent component1 = new MaterialLengthComponent(10);

        assertTrue(component1.equals(10));
    }

    @Test
    public void testEqualsObject() throws ValidationFailedException {
        MaterialLengthComponent component1 = new MaterialLengthComponent(10);
        MaterialLengthComponent component2= new MaterialLengthComponent(10);

        assertTrue(component1.equals(component2));
    }

    @Test
    public void testEqualsObjectFail() throws ValidationFailedException {
        MaterialLengthComponent component1 = new MaterialLengthComponent(10);
        MaterialLengthComponent component2= new MaterialLengthComponent(11);

        assertFalse(component1.equals(component2));
    }

    @Test
    public void testEqualsIntFail() throws ValidationFailedException {
        MaterialLengthComponent component1 = new MaterialLengthComponent(10);

        assertFalse(component1.equals(11));
    }

    @Test
    public void testEqualsWrongObjectType() throws ValidationFailedException {
        MaterialLengthComponent component1 = new MaterialLengthComponent(10);

        assertFalse(component1.equals(new MaterialWidthComponent(10)));
    }
}
