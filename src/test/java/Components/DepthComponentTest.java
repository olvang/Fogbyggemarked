package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;
import org.junit.Test;
import testDataSetup.TestDataSetup;

import static org.junit.Assert.*;

public class DepthComponentTest{

    @Test
    public void testSuccess() throws ValidationFailedException {
        int expected = 510;
        DepthComponent component = new DepthComponent(510);

        assertEquals(component.getDepth(), expected);
    }

    @Test
    public void testSetValue() throws ValidationFailedException {
        int expected = 210;
        DepthComponent component = new DepthComponent(510);

        component.setDepth(expected);

        assertEquals(component.getDepth(), expected);
    }

    @Test
    public void testSuccessString() throws ValidationFailedException {
        int expected = 510;
        DepthComponent component = new DepthComponent("510");

        assertEquals(component.getDepth(), expected);
    }

    @Test (expected = ValidationFailedException.class)
    public void testStringFail () throws ValidationFailedException {
        DepthComponent component = new DepthComponent("hej");
    }

    @Test (expected = ValidationFailedException.class)
    public void testZero() throws ValidationFailedException {
        DepthComponent component = new DepthComponent(0);
    }

    @Test (expected = ValidationFailedException.class)
    public void testBelowZero() throws ValidationFailedException {
        DepthComponent component = new DepthComponent(-1);
    }

    @Test
    public void testBoundaryValueOne() throws ValidationFailedException {
        int expected = 200;
        DepthComponent component = new DepthComponent(200);

        assertEquals(component.getDepth(), expected);
    }

    @Test (expected = ValidationFailedException.class)
    public void testMassiveValue() throws ValidationFailedException {
        DepthComponent component = new DepthComponent(2000000);
    }

    @Test
    public void testEmptyString() {
        String expected = "Dette felt skal udfyldes.";
        String actual = "No exception was thrown";
        try {
            DepthComponent component = new DepthComponent("");
        }catch (ValidationFailedException ex) {
            actual = ex.getMessage();
        }
        assertEquals(expected, actual);
    }

    //Equals tests
    @Test
    public void testEqualsInt() throws ValidationFailedException {
        DepthComponent component1 = new DepthComponent(300);

        assertTrue(component1.equals(300));
    }

    @Test
    public void testEqualsObject() throws ValidationFailedException {
        DepthComponent component1 = new DepthComponent(300);
        DepthComponent component2= new DepthComponent(300);

        assertTrue(component1.equals(component2));
    }

    @Test
    public void testEqualsObjectFail() throws ValidationFailedException {
        DepthComponent component1 = new DepthComponent(300);
        DepthComponent component2= new DepthComponent(301);

        assertFalse(component1.equals(component2));
    }

    @Test
    public void testEqualsIntFail() throws ValidationFailedException {
        DepthComponent component1 = new DepthComponent(300);

        assertFalse(component1.equals(311));
    }

    @Test
    public void testEqualsWrongObjectType() throws ValidationFailedException {
        DepthComponent component1 = new DepthComponent(310);

        assertFalse(component1.equals(new WidthComponent(310)));
    }

    @Test
    public void testSetSuccess() throws ValidationFailedException {
        int var1 = 400;
        int var2 = 200;
        DepthComponent component = new DepthComponent(var1);
        try {
            component.setDepth(var2);
            assertEquals(var2, component.getDepth());
        } catch (ValidationFailedException e) {
            fail("Setting failed");
        }
    }

    @Test
    public void testSetFail() throws ValidationFailedException {
        int var1 = 400;
        int var2 = 10;
        DepthComponent component = null;
        try {
            component = new DepthComponent(var1);
        } catch (ValidationFailedException e) {
            fail("First validation failed");
        }
        try {
            component.setDepth(var2);
        }catch (ValidationFailedException e) {
            assertEquals(var1, component.getDepth());
        }
    }

}
