package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

public class ShedWidthComponent implements Component {
    private int width;

    private WidthComponent carportConnection;

    //-------------//
    // Constructor //
    //-------------//
    public ShedWidthComponent(int width, WidthComponent carportWidth) throws ValidationFailedException {
        this.width = width;
        this.carportConnection = carportWidth;
        validate();
    }

    public ShedWidthComponent(String width, WidthComponent carportWidth) throws ValidationFailedException {
        if(width.equals("")) {
            //Don't forget to update test if this error message is changed.
            throw new ValidationFailedException("Dette felt skal udfyldes.");
        }
        try {
            this.width = Integer.parseInt(width);
        }catch (Exception ex) {
            throw new ValidationFailedException("Skur bredden skal være et tal.");
        }
        this.carportConnection = carportWidth;
        validate();
    }

    //-------------//
    // Validation //
    //------------//
    @Override
    public boolean validate() throws ValidationFailedException {
        //If width is 0 or lower, a ValidationFailedException is thrown
        if (width < 1) {
            throw new ValidationFailedException("Skur bredde må ikke være under 0.");
        //If width is larger than the carport it is connected to, a ValidationFailedException is thrown
        } else if (width > carportConnection.getWidth()) {
            throw new ValidationFailedException("Skur bredde må ikke være større end carporten.");
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
        ShedWidthComponent component = (ShedWidthComponent) o;
        return width == component.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width);
    }
}
