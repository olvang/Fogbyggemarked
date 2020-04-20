package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class HeightComponentTest {

    @Test
    public void testSuccess() throws ValidationFailedException {
        int expected = 51;
        HeightComponent component = new HeightComponent(51);

        assertEquals(component.getHeight(), expected);
    }

    @Test
    public void testSetValue() throws ValidationFailedException {
        int expected = 10;
        HeightComponent component = new HeightComponent(51);

        component.setHeight(expected);

        assertEquals(component.getHeight(), expected);
    }

    @Test
    public void testSuccessString() throws ValidationFailedException {
        int expected = 51;
        HeightComponent component = new HeightComponent("51");

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
        int expected = 1;
        HeightComponent component = new HeightComponent(1);

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
        HeightComponent component1 = new HeightComponent(10);

        assertTrue(component1.equals(10));
    }

    @Test
    public void testEqualsObject() throws ValidationFailedException {
        HeightComponent component1 = new HeightComponent(10);
        HeightComponent component2= new HeightComponent(10);

        assertTrue(component1.equals(component2));
    }

    @Test
    public void testEqualsObjectFail() throws ValidationFailedException {
        HeightComponent component1 = new HeightComponent(10);
        HeightComponent component2= new HeightComponent(11);

        assertFalse(component1.equals(component2));
    }

    @Test
    public void testEqualsIntFail() throws ValidationFailedException {
        HeightComponent component1 = new HeightComponent(10);

        assertFalse(component1.equals(11));
    }

    @Test
    public void testEqualsWrongObjectType() throws ValidationFailedException {
        HeightComponent component1 = new HeightComponent(10);

        assertFalse(component1.equals(new String()));
    }

}
