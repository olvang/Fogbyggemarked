package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

}
