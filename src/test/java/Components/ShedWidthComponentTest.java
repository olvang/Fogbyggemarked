package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class ShedWidthComponentTest {
    @Test
    public void testSuccess() throws ValidationFailedException {
        int expected = 51;
        WidthComponent carportComponent = new WidthComponent(100);
        ShedWidthComponent component = new ShedWidthComponent(51, carportComponent);

        assertEquals(component.getWidth(), expected);
    }

    @Test
    public void testSetValue() throws ValidationFailedException {
        int expected = 10;
        WidthComponent carportComponent = new WidthComponent(100);
        ShedWidthComponent component = new ShedWidthComponent(51, carportComponent);

        component.setWidth(expected);

        assertEquals(component.getWidth(), expected);
    }

    @Test
    public void testSuccessString() throws ValidationFailedException {
        int expected = 51;
        WidthComponent carportComponent = new WidthComponent(100);
        ShedWidthComponent component = new ShedWidthComponent("51", carportComponent);

        assertEquals(component.getWidth(), expected);
    }

    @Test (expected = ValidationFailedException.class)
    public void testStringFail () throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(100);
        ShedWidthComponent component = new ShedWidthComponent("hej", carportComponent);
    }

    @Test (expected = ValidationFailedException.class)
    public void testZero() throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(100);
        ShedWidthComponent component = new ShedWidthComponent(0, carportComponent);
    }

    @Test (expected = ValidationFailedException.class)
    public void testBelowZero() throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(100);
        ShedWidthComponent component = new ShedWidthComponent(-1, carportComponent);
    }

    @Test
    public void testBoundaryValueOne() throws ValidationFailedException {
        int expected = 1;
        WidthComponent carportComponent = new WidthComponent(100);
        ShedWidthComponent component = new ShedWidthComponent(1, carportComponent);

        assertEquals(component.getWidth(), expected);
    }

    @Test (expected = ValidationFailedException.class)
    public void testMassiveValue() throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(100);
        ShedWidthComponent component = new ShedWidthComponent(2000000, carportComponent);
    }

    @Test (expected = ValidationFailedException.class)
    public void testShedLargerThanCarport() throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(10);
        ShedWidthComponent component = new ShedWidthComponent(51, carportComponent);
    }

    @Test
    public void testEmptyString() {
        String expected = "Dette felt skal udfyldes.";
        String actual = "No exception was thrown";
        try {
            WidthComponent carportComp = new WidthComponent(1);
            ShedWidthComponent component = new ShedWidthComponent("", carportComp);
        }catch (ValidationFailedException ex) {
            actual = ex.getMessage();
        }
        assertEquals(expected, actual);
    }

    //Equals tests
    @Test
    public void testEqualsInt() throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(100);

        ShedWidthComponent component1 = new ShedWidthComponent(10, carportComponent);

        assertTrue(component1.equals(10));
    }

    @Test
    public void testEqualsObject() throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(100);

        ShedWidthComponent component1 = new ShedWidthComponent(10, carportComponent);
        ShedWidthComponent component2= new ShedWidthComponent(10, carportComponent);

        assertTrue(component1.equals(component2));
    }

    @Test
    public void testEqualsObjectFail() throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(100);

        ShedWidthComponent component1 = new ShedWidthComponent(10, carportComponent);
        ShedWidthComponent component2= new ShedWidthComponent(11, carportComponent);

        assertFalse(component1.equals(component2));
    }

    @Test
    public void testEqualsIntFail() throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(100);

        ShedWidthComponent component1 = new ShedWidthComponent(10, carportComponent);

        assertFalse(component1.equals(11));
    }

    @Test
    public void testEqualsWrongObjectType() throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(100);

        ShedWidthComponent component1 = new ShedWidthComponent(10, carportComponent);

        assertFalse(component1.equals(new WidthComponent(10)));
    }

}
