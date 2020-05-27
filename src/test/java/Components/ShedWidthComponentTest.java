package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;
import testDataSetup.TestDataSetup;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class ShedWidthComponentTest  {
    @Test
    public void testSuccess() throws ValidationFailedException {
        int expected = 510;
        WidthComponent carportComponent = new WidthComponent(600);
        ShedWidthComponent component = new ShedWidthComponent(510, carportComponent);

        assertEquals(component.getWidth(), expected);
    }

    @Test
    public void testSetValue() throws ValidationFailedException {
        int expected = 300;
        WidthComponent carportComponent = new WidthComponent(400);
        ShedWidthComponent component = new ShedWidthComponent(200, carportComponent);

        component.setWidth(expected);

        assertEquals(component.getWidth(), expected);
    }

    @Test
    public void testSuccessString() throws ValidationFailedException {
        int expected = 510;
        WidthComponent carportComponent = new WidthComponent(600);
        ShedWidthComponent component = new ShedWidthComponent("510", carportComponent);

        assertEquals(component.getWidth(), expected);
    }

    @Test (expected = ValidationFailedException.class)
    public void testStringFail () throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(210);
        ShedWidthComponent component = new ShedWidthComponent("hej", carportComponent);
    }

    @Test (expected = ValidationFailedException.class)
    public void testZero() throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(210);
        ShedWidthComponent component = new ShedWidthComponent(0, carportComponent);
    }

    @Test (expected = ValidationFailedException.class)
    public void testBelowZero() throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(210);
        ShedWidthComponent component = new ShedWidthComponent(-1, carportComponent);
    }

    @Test
    public void testBoundaryValueOne() throws ValidationFailedException {
        int expected = 100;
        WidthComponent carportComponent = new WidthComponent(210);
        ShedWidthComponent component = new ShedWidthComponent(100, carportComponent);

        assertEquals(component.getWidth(), expected);
    }

    @Test (expected = ValidationFailedException.class)
    public void testMassiveValue() throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(210);
        ShedWidthComponent component = new ShedWidthComponent(2000000, carportComponent);
    }

    @Test (expected = ValidationFailedException.class)
    public void testShedLargerThanCarport() throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(210);
        ShedWidthComponent component = new ShedWidthComponent(51, carportComponent);
    }

    @Test
    public void testEmptyString() {
        String expected = "Dette felt skal udfyldes.";
        String actual = "No exception was thrown";
        try {
            WidthComponent carportComp = new WidthComponent(210);
            ShedWidthComponent component = new ShedWidthComponent("", carportComp);
        }catch (ValidationFailedException ex) {
            actual = ex.getMessage();
        }
        assertEquals(expected, actual);
    }

    //Equals tests
    @Test
    public void testEqualsInt() throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(210);

        ShedWidthComponent component1 = new ShedWidthComponent(110, carportComponent);

        assertTrue(component1.equals(110));
    }

    @Test
    public void testEqualsObject() throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(210);

        ShedWidthComponent component1 = new ShedWidthComponent(110, carportComponent);
        ShedWidthComponent component2= new ShedWidthComponent(110, carportComponent);

        assertTrue(component1.equals(component2));
    }

    @Test
    public void testEqualsObjectFail() throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(210);

        ShedWidthComponent component1 = new ShedWidthComponent(110, carportComponent);
        ShedWidthComponent component2= new ShedWidthComponent(111, carportComponent);

        assertFalse(component1.equals(component2));
    }

    @Test
    public void testEqualsIntFail() throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(210);

        ShedWidthComponent component1 = new ShedWidthComponent(110, carportComponent);

        assertFalse(component1.equals(111));
    }

    @Test
    public void testEqualsWrongObjectType() throws ValidationFailedException {
        WidthComponent carportComponent = new WidthComponent(210);

        ShedWidthComponent component1 = new ShedWidthComponent(110, carportComponent);

        assertFalse(component1.equals(new WidthComponent(210)));
    }


    @Test
    public void testSetSuccess() throws ValidationFailedException {
        int var1 = 200;
        int var2 = 150;
        WidthComponent carport = new WidthComponent(300);
        ShedWidthComponent component = new ShedWidthComponent(var1, carport);
        try {
            component.setWidth(var2);
            assertEquals(var2, component.getWidth());
        } catch (ValidationFailedException e) {
            fail("Setting failed");
        }
    }

    @Test
    public void testSetFail() throws ValidationFailedException {
        int var1 = 200;
        int var2 = 10;
        WidthComponent carport = new WidthComponent(300);
        ShedWidthComponent component = null;
        try {
            component = new ShedWidthComponent(var1, carport);
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
