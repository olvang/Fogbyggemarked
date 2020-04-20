package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

}
