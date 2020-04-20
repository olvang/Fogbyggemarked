package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

}
