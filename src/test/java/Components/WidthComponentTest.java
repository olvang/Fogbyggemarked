package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;
import testDataSetup.TestDataSetup;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class WidthComponentTest{

    @Test
    public void testSuccess() throws ValidationFailedException {
        int expected = 510;
        WidthComponent component = new WidthComponent(510);

        assertEquals(component.getWidth(), expected);
    }

    @Test
    public void testSetValue() throws ValidationFailedException {
        int expected = 210;
        WidthComponent component = new WidthComponent(510);

        component.setWidth(expected);

        assertEquals(component.getWidth(), expected);
    }

    @Test
    public void testSuccessString() throws ValidationFailedException {
        int expected = 510;
        WidthComponent component = new WidthComponent("510");

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
        int expected = 200;
        WidthComponent component = new WidthComponent(200);

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
        WidthComponent component1 = new WidthComponent(210);

        assertTrue(component1.equals(210));
    }

    @Test
    public void testEqualsObject() throws ValidationFailedException {
        WidthComponent component1 = new WidthComponent(210);
        WidthComponent component2= new WidthComponent(210);

        assertTrue(component1.equals(component2));
    }

    @Test
    public void testEqualsObjectFail() throws ValidationFailedException {
        WidthComponent component1 = new WidthComponent(210);
        WidthComponent component2= new WidthComponent(211);

        assertFalse(component1.equals(component2));
    }

    @Test
    public void testEqualsIntFail() throws ValidationFailedException {
        WidthComponent component1 = new WidthComponent(210);

        assertFalse(component1.equals(211));
    }

    @Test
    public void testEqualsWrongObjectType() throws ValidationFailedException {
        WidthComponent component1 = new WidthComponent(210);

        assertFalse(component1.equals(new String()));
    }


    @Test
    public void testSetSuccess() throws ValidationFailedException {
        int var1 = 400;
        int var2 = 200;
        WidthComponent component = new WidthComponent(var1);
        try {
            component.setWidth(var2);
            assertEquals(var2, component.getWidth());
        } catch (ValidationFailedException e) {
            fail("Setting failed");
        }
    }

    @Test
    public void testSetFail() throws ValidationFailedException {
        int var1 = 400;
        int var2 = 10;
        WidthComponent component = null;
        try {
            component = new WidthComponent(var1);
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
