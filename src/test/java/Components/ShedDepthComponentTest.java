package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;
import testDataSetup.TestDataSetup;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class ShedDepthComponentTest {
    @Test
    public void testSuccess() throws ValidationFailedException {
        int expected = 51;
        DepthComponent carportComponent = new DepthComponent(100);
        ShedDepthComponent component = new ShedDepthComponent(51, carportComponent);

        assertEquals(component.getDepth(), expected);
    }

    @Test
    public void testSetValue() throws ValidationFailedException {
        int expected = 10;
        DepthComponent carportComponent = new DepthComponent(100);
        ShedDepthComponent component = new ShedDepthComponent(51, carportComponent);

        component.setDepth(expected);

        assertEquals(component.getDepth(), expected);
    }

    @Test
    public void testSuccessString() throws ValidationFailedException {
        int expected = 51;
        DepthComponent carportComponent = new DepthComponent(100);
        ShedDepthComponent component = new ShedDepthComponent("51", carportComponent);

        assertEquals(component.getDepth(), expected);
    }

    @Test (expected = ValidationFailedException.class)
    public void testStringFail () throws ValidationFailedException {
        DepthComponent carportComponent = new DepthComponent(100);
        ShedDepthComponent component = new ShedDepthComponent("hej", carportComponent);
    }

    @Test (expected = ValidationFailedException.class)
    public void testZero() throws ValidationFailedException {
        DepthComponent carportComponent = new DepthComponent(100);
        ShedDepthComponent component = new ShedDepthComponent(0, carportComponent);
    }

    @Test (expected = ValidationFailedException.class)
    public void testBelowZero() throws ValidationFailedException {
        DepthComponent carportComponent = new DepthComponent(100);
        ShedDepthComponent component = new ShedDepthComponent(-1, carportComponent);
    }

    @Test
    public void testBoundaryValueOne() throws ValidationFailedException {
        int expected = 1;
        DepthComponent carportComponent = new DepthComponent(100);
        ShedDepthComponent component = new ShedDepthComponent(1, carportComponent);

        assertEquals(component.getDepth(), expected);
    }

    @Test (expected = ValidationFailedException.class)
    public void testMassiveValue() throws ValidationFailedException {
        DepthComponent carportComponent = new DepthComponent(100);
        ShedDepthComponent component = new ShedDepthComponent(2000000, carportComponent);
    }

    @Test (expected = ValidationFailedException.class)
    public void testShedLargerThanCarport() throws ValidationFailedException {
        DepthComponent carportComponent = new DepthComponent(10);
        ShedDepthComponent component = new ShedDepthComponent(51, carportComponent);
    }

    @Test
    public void testEmptyString() {
        String expected = "Dette felt skal udfyldes.";
        String actual = "No exception was thrown";
        try {
            DepthComponent carportComp = new DepthComponent(1);
            ShedDepthComponent component = new ShedDepthComponent("", carportComp);
        }catch (ValidationFailedException ex) {
            actual = ex.getMessage();
        }
        assertEquals(expected, actual);
    }


    //Equals tests
    @Test
    public void testEqualsInt() throws ValidationFailedException {
        DepthComponent carportComponent = new DepthComponent(100);

        ShedDepthComponent component1 = new ShedDepthComponent(10, carportComponent);

        assertTrue(component1.equals(10));
    }

    @Test
    public void testEqualsObject() throws ValidationFailedException {
        DepthComponent carportComponent = new DepthComponent(100);

        ShedDepthComponent component1 = new ShedDepthComponent(10, carportComponent);
        ShedDepthComponent component2= new ShedDepthComponent(10, carportComponent);

        assertTrue(component1.equals(component2));
    }

    @Test
    public void testEqualsObjectFail() throws ValidationFailedException {
        DepthComponent carportComponent = new DepthComponent(100);

        ShedDepthComponent component1 = new ShedDepthComponent(10, carportComponent);
        ShedDepthComponent component2= new ShedDepthComponent(11, carportComponent);

        assertFalse(component1.equals(component2));
    }

    @Test
    public void testEqualsIntFail() throws ValidationFailedException {
        DepthComponent carportComponent = new DepthComponent(100);

        ShedDepthComponent component1 = new ShedDepthComponent(10, carportComponent);

        assertFalse(component1.equals(11));
    }

    @Test
    public void testEqualsWrongObjectType() throws ValidationFailedException {
        DepthComponent carportComponent = new DepthComponent(100);

        ShedDepthComponent component1 = new ShedDepthComponent(10, carportComponent);

        assertFalse(component1.equals(new WidthComponent(10)));
    }

}
