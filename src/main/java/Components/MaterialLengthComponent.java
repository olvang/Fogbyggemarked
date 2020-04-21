package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;
/**
 * @author fdinsen
 * @version 1.0
 * @since 1.0
 */

public class MaterialLengthComponent implements Component {
    private int length;

    //-------------//
    // Constructor //
    //-------------//
    public MaterialLengthComponent(String length) throws ValidationFailedException {
        if(length.equals("")) {
            throw new ValidationFailedException("Dette felt skal udfyldes.");
        }
        try {
            this.length = Integer.parseInt(length);
        }catch ( Exception ex ) {
            throw new ValidationFailedException("Længde skal være et tal.");
        }
        validate();
    }

    public MaterialLengthComponent(int length) throws ValidationFailedException {
        this.length = length;
        validate();
    }

    //-------------//
    // Validation //
    //------------//
    @Override
    public boolean validate() throws ValidationFailedException {
        if(length < 1) {
            throw new ValidationFailedException("Længde må ikke være under 0.");
        }
        return true;
    }

    //-----------------//
    // Getters/Setters //
    //-----------------//
    public int getLength() {
        return length;
    }
    public void setLength(int length) throws ValidationFailedException {
        this.length = length;
        validate();
    }

    //-----------//
    // Comparing //
    //-----------//
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ) return false;
        //This try/catch block allows the component to be compared to an int
        try {
            if ((Integer) o == length) return true;
        } catch (ClassCastException ex) {}

        if (getClass() != o.getClass() ) return false;
        MaterialLengthComponent that = (MaterialLengthComponent) o;
        return length == that.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length);
    }
}
