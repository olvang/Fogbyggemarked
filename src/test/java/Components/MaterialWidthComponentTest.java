package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;
import testDataSetup.TestDataSetup;

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

    @Test
    public void testZero() throws ValidationFailedException {
        MaterialWidthComponent component = new MaterialWidthComponent(0);
        assertEquals(0, component.getWidth());
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

    @Test
    public void testSetSuccess() throws ValidationFailedException {
        int var1 = 10;
        int var2 = 20;
        MaterialWidthComponent component = new MaterialWidthComponent(var1);
        try {
            component.setWidth(var2);
            assertEquals(var2, component.getWidth());
        } catch (ValidationFailedException e) {
            fail("Setting failed");
        }
    }

    @Test
    public void testSetFail() throws ValidationFailedException {
        int var1 = 10;
        int var2 = -10;
        MaterialWidthComponent component = null;
        try {
            component = new MaterialWidthComponent(var1);
        } catch (ValidationFailedException e) {
            fail("First validation failed");
        }
        try {
            component.setWidth(var2);
        }catch (ValidationFailedException e) {
            assertEquals(var1, component.getWidth());
        }
    }
}
