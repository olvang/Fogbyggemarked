package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;
import testDataSetup.TestDataSetup;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class HeightComponentTest{

    @Test
    public void testSuccess() throws ValidationFailedException {
        int expected = 510;
        HeightComponent component = new HeightComponent(510);

        assertEquals(component.getHeight(), expected);
    }

    @Test
    public void testSetValue() throws ValidationFailedException {
        int expected = 300;
        HeightComponent component = new HeightComponent(510);

        component.setHeight(expected);

        assertEquals(component.getHeight(), expected);
    }

    @Test
    public void testSuccessString() throws ValidationFailedException {
        int expected = 510;
        HeightComponent component = new HeightComponent("510");

        assertEquals(component.getHeight(), expected);
    }

    @Test (expected = ValidationFailedException.class)
    public void testStringFail () throws ValidationFailedException {
        HeightComponent component = new HeightComponent("hej");
    }

    @Test (expected = ValidationFailedException.class)
    public void testZero() throws ValidationFailedException {
        HeightComponent component = new HeightComponent(0);
    }

    @Test (expected = ValidationFailedException.class)
    public void testBelowZero() throws ValidationFailedException {
        HeightComponent component = new HeightComponent(-1);
    }

    @Test
    public void testBoundaryValueOne() throws ValidationFailedException {
        int expected = 200;
        HeightComponent component = new HeightComponent(200);

        assertEquals(component.getHeight(), expected);
    }

    @Test (expected = ValidationFailedException.class)
    public void testMassiveValue() throws ValidationFailedException {
        HeightComponent component = new HeightComponent(2000000);
    }

    @Test
    public void testEmptyString() {
        String expected = "Dette felt skal udfyldes.";
        String actual = "No exception was thrown";
        try {
            HeightComponent component = new HeightComponent("");
        }catch (ValidationFailedException ex) {
            actual = ex.getMessage();
        }
        assertEquals(expected, actual);
    }

    //Equals tests
    @Test
    public void testEqualsInt() throws ValidationFailedException {
        HeightComponent component1 = new HeightComponent(300);

        assertTrue(component1.equals(300));
    }

    @Test
    public void testEqualsObject() throws ValidationFailedException {
        HeightComponent component1 = new HeightComponent(300);
        HeightComponent component2= new HeightComponent(300);

        assertTrue(component1.equals(component2));
    }

    @Test
    public void testEqualsObjectFail() throws ValidationFailedException {
        HeightComponent component1 = new HeightComponent(300);
        HeightComponent component2= new HeightComponent(301);

        assertFalse(component1.equals(component2));
    }

    @Test
    public void testEqualsIntFail() throws ValidationFailedException {
        HeightComponent component1 = new HeightComponent(300);

        assertFalse(component1.equals(301));
    }

    @Test
    public void testEqualsWrongObjectType() throws ValidationFailedException {
        HeightComponent component1 = new HeightComponent(300);

        assertFalse(component1.equals(new String()));
    }

    @Test
    public void testSetSuccess() throws ValidationFailedException {
        int var1 = 200;
        int var2 = 200;
        HeightComponent component = new HeightComponent(var1);
        try {
            component.setHeight(var2);
            assertEquals(var2, component.getHeight());
        } catch (ValidationFailedException e) {
            fail("Setting failed");
        }
    }

    @Test
    public void testSetFail() throws ValidationFailedException {
        int var1 = 200;
        int var2 = 10;
        HeightComponent component = null;
        try {
            component = new HeightComponent(var1);
        } catch (ValidationFailedException e) {
            fail("First validation failed");
        }
        try {
            component.setHeight(var2);
        }catch (ValidationFailedException e) {
            assertEquals(var1, component.getHeight());
        }
    }

}
