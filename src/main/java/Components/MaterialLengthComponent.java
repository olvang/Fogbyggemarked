package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;
/**
 * <p>Component used to validate a Material Length int</p>
 */
public class MaterialLengthComponent implements Component {
    private int length;

    /**
     * <p>Constructor for the component</p>
     * <p>Calls the validate function</p>
     * <p>Convert the string to a int</p>
     * @param length Material length string to validate
     */
    public MaterialLengthComponent(String length) throws ValidationFailedException {
        if(length.equals("")) {
            throw new ValidationFailedException("Dette felt skal udfyldes.");
        }
        try {
            this.length = Integer.parseInt(length);
        }catch ( NumberFormatException ex ) {
            throw new ValidationFailedException("Længde skal være et tal.");
        }
        validate();
    }

    /**
     * <p>Constructor for the component</p>
     * <p>Calls the validate function</p>
     * @param length Material length int to validate
     */
    public MaterialLengthComponent(int length) throws ValidationFailedException {
        this.length = length;
        validate();
    }

    /**
     * <p>Validates the Material length</p>
     * @return True if the Material length validates according to the rules
     * @exception ValidationFailedException Thrown if the Material length trying to be validated does not comply with the rules
     */
    @Override
    public boolean validate() throws ValidationFailedException {
        if(length < 0) {
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

    /**
     * <p>Used to compare the component with a Integer</p>
     * @return True if the Material length is equal to the Integer its comparing to, else false
     */
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
