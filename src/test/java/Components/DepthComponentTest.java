package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;
import org.junit.Test;
import static org.junit.Assert.*;

public class DepthComponentTest {

    @Test
    public void testSuccess() throws ValidationFailedException {
        int expected = 51;
        DepthComponent component = new DepthComponent(51);

        assertEquals(component.getDepth(), expected);
    }

    @Test
    public void testSetValue() throws ValidationFailedException {
        int expected = 10;
        DepthComponent component = new DepthComponent(51);

        component.setDepth(expected);

        assertEquals(component.getDepth(), expected);
    }

    @Test
    public void testSuccessString() throws ValidationFailedException {
        int expected = 51;
        DepthComponent component = new DepthComponent("51");

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
        int expected = 1;
        DepthComponent component = new DepthComponent(1);

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
        DepthComponent component1 = new DepthComponent(10);

        assertTrue(component1.equals(10));
    }

    @Test
    public void testEqualsObject() throws ValidationFailedException {
        DepthComponent component1 = new DepthComponent(10);
        DepthComponent component2= new DepthComponent(10);

        assertTrue(component1.equals(component2));
    }

    @Test
    public void testEqualsObjectFail() throws ValidationFailedException {
        DepthComponent component1 = new DepthComponent(10);
        DepthComponent component2= new DepthComponent(11);

        assertFalse(component1.equals(component2));
    }

    @Test
    public void testEqualsIntFail() throws ValidationFailedException {
        DepthComponent component1 = new DepthComponent(10);

        assertFalse(component1.equals(11));
    }

    @Test
    public void testEqualsWrongObjectType() throws ValidationFailedException {
        DepthComponent component1 = new DepthComponent(10);

        assertFalse(component1.equals(new WidthComponent(10)));
    }

}
