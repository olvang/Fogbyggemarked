package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class MaterialHeightComponentTest {

    @Test
    public void testSuccess() throws ValidationFailedException {
        int expected = 51;
        MaterialHeightComponent component = new MaterialHeightComponent(expected);

        assertEquals(component.getHeight(), expected);
    }

    @Test
    public void testSetValue() throws ValidationFailedException {
        int expected = 10;
        MaterialHeightComponent component = new MaterialHeightComponent(51);

        component.setHeight(expected);

        assertEquals(component.getHeight(), expected);
    }

    @Test
    public void testSuccessString() throws ValidationFailedException {
        int expected = 51;
        MaterialHeightComponent component = new MaterialHeightComponent("51");

        assertEquals(component.getHeight(), expected);
    }

    @Test (expected = ValidationFailedException.class)
    public void testStringFail () throws ValidationFailedException {
        MaterialHeightComponent component = new MaterialHeightComponent("hej");
    }

    @Test
    public void testZero() throws ValidationFailedException {
        MaterialHeightComponent component = new MaterialHeightComponent(0);
        assertEquals(0, component.getHeight());
    }

    @Test (expected = ValidationFailedException.class)
    public void testBelowZero() throws ValidationFailedException {
        MaterialHeightComponent component = new MaterialHeightComponent(-1);
    }

    @Test
    public void testBoundaryValueOne() throws ValidationFailedException {
        int expected = 1;
        MaterialHeightComponent component = new MaterialHeightComponent(1);

        assertEquals(component.getHeight(), expected);
    }

    @Test
    public void testEmptyString() {
        String expected = "Dette felt skal udfyldes.";
        String actual = "No exception was thrown";
        try {
            MaterialHeightComponent component = new MaterialHeightComponent("");
        }catch (ValidationFailedException ex) {
            actual = ex.getMessage();
        }
        assertEquals(expected, actual);
    }

    //Equals tests
    @Test
    public void testEqualsInt() throws ValidationFailedException {
        MaterialHeightComponent component1 = new MaterialHeightComponent(10);

        assertTrue(component1.equals(10));
    }

    @Test
    public void testEqualsObject() throws ValidationFailedException {
        MaterialHeightComponent component1 = new MaterialHeightComponent(10);
        MaterialHeightComponent component2= new MaterialHeightComponent(10);

        assertTrue(component1.equals(component2));
    }

    @Test
    public void testEqualsObjectFail() throws ValidationFailedException {
        MaterialHeightComponent component1 = new MaterialHeightComponent(10);
        MaterialHeightComponent component2= new MaterialHeightComponent(11);

        assertFalse(component1.equals(component2));
    }

    @Test
    public void testEqualsIntFail() throws ValidationFailedException {
        MaterialHeightComponent component1 = new MaterialHeightComponent(10);

        assertFalse(component1.equals(11));
    }

    @Test
    public void testEqualsWrongObjectType() throws ValidationFailedException {
        MaterialHeightComponent component1 = new MaterialHeightComponent(10);

        assertFalse(component1.equals(new MaterialWidthComponent(10)));
    }
}
