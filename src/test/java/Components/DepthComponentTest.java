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

}
