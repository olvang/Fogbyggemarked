package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

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
        if(width.equals("")) {
            //Don't forget to update test if this error message is changed.
            throw new ValidationFailedException("Dette felt skal udfyldes.");
        }
        try {
            this.width = Integer.parseInt(width);
        }catch (NumberFormatException ex) {
            throw new ValidationFailedException("Bredden skal være et tal.");
        }
        validate();
    }

    //-------------//
    // Validation //
    //------------//
    @Override
    public boolean validate() throws ValidationFailedException {
        //If width is below 200, a ValidationFailedException is thrown
        if (width < 200) {
            throw new ValidationFailedException("Bredde må ikke være under 2m.");
        //If width is above the width limit, a ValidationFailedException is thrown
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

    //-----------//
    // Comparing //
    //-----------//

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        //This try/catch block allows the component to be compared to an int
        try {
            if ((Integer) o == width) return true;
        }catch (ClassCastException ex) {}

        if( getClass() != o.getClass() ) return false;
        WidthComponent component = (WidthComponent) o;
        return width == component.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width);
    }
}
