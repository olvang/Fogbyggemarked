package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;
/**
 * @author fdinsen
 * @version 1.0
 * @since 1.0
 */

public class MaterialWidthComponent implements Component {
    private int width;

    //-------------//
    // Constructor //
    //-------------//
    public MaterialWidthComponent(String width) throws ValidationFailedException {
        if(width.equals("")) {
            throw new ValidationFailedException("Dette felt skal udfyldes.");
        }
        try {
            this.width = Integer.parseInt(width);
        }catch ( Exception ex ) {
            throw new ValidationFailedException("Bredde skal være et tal.");
        }
        validate();
    }

    public MaterialWidthComponent(int width) throws ValidationFailedException {
        this.width = width;
        validate();
    }

    //-------------//
    // Validation //
    //------------//
    @Override
    public boolean validate() throws ValidationFailedException {
        if(width < 1) {
            throw new ValidationFailedException("Bredde må ikke være under 0.");
        }
        return true;
    }

    //-----------------//
    // Getters/Setters //
    //-----------------//
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) throws ValidationFailedException{
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
        MaterialWidthComponent component = (MaterialWidthComponent) o;
        return width == component.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width);
    }
}
