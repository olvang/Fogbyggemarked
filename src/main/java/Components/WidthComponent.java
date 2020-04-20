package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class WidthComponent implements Component {
    private int width;

    private int widthLimit = 2000;

    //-------------//
    // Constructor //
    //-------------//
    public WidthComponent(int width) throws ValidationFailedException {
        this.width = width;
        validate();
    }

    public WidthComponent(String width) throws ValidationFailedException {
        try {
            this.width = Integer.parseInt(width);
        }catch (Exception ex) {
            throw new ValidationFailedException("Bredden skal være et tal.");
        }
        validate();
    }

    //-------------//
    // Validation //
    //------------//
    @Override
    public boolean validate() throws ValidationFailedException {
        if (width < 1) {
            throw new ValidationFailedException("Bredde må ikke være under 0m.");
        } else if (width > widthLimit) {
            throw new ValidationFailedException("Bredde må ikke være over " + (widthLimit / 100) + "m.");
        }
        return true;
    }

    //-----------------//
    // Getters/Setters //
    //-----------------//
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) throws ValidationFailedException {
        this.width = width;
        validate();
    }
}
