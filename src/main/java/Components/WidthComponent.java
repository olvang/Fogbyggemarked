package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class WidthComponent implements Component {
    private int width;

    private int widthLimit = 2000;

    public WidthComponent(int width) throws ValidationFailedException {
        this.width = width;
        validate();
    }

    @Override
    public boolean validate() throws ValidationFailedException {
        if (width < 1) {
            throw new ValidationFailedException("Bredde må ikke være under 0m.");
        } else if (width > widthLimit) {
            throw new ValidationFailedException("Bredde må ikke være over " + (widthLimit / 100) + "m.");
        }
        return true;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) throws ValidationFailedException {
        this.width = width;
        validate();
    }
}
