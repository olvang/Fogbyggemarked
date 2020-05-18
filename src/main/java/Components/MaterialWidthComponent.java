package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

/**
 * <p>Component used to validate a Material Width int</p>
 */
public class MaterialWidthComponent implements Component {
    private int width;

    /**
     * Constructor for the component
     * Calls the validate function
     * Converts the string to a int
     * @param width Material Width string to validate
     */
    public MaterialWidthComponent(String width) throws ValidationFailedException {
        if(width.equals("")) {
            throw new ValidationFailedException("Dette felt skal udfyldes.");
        }
        try {
            this.width = Integer.parseInt(width);
        }catch ( NumberFormatException ex ) {
            throw new ValidationFailedException("Bredde skal være et tal.");
        }
        validate();
    }

    /**
     * Constructor for the component
     * Calls the validate function
     * @param width Material Width int to validate
     */
    public MaterialWidthComponent(int width) throws ValidationFailedException {
        this.width = width;
        validate();
    }

    /**
     * Validates the Material Width
     * @return True if the Material Width validates according to the rules
     * @exception ValidationFailedException Thrown if the Material Width trying to be validated does not comply with the rules
     */
    @Override
    public boolean validate() throws ValidationFailedException {
        if(width < 0) {
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

    /**
     * Used to compare the component with a Integer
     * @return True if the Material Width is equal to the Integer its comparing to, else false
     */
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
}
