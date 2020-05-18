package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;
/**
 * <p>Component used to validate a Shed Width int</p>
 */
public class ShedWidthComponent implements Component {
    private int width;

    private WidthComponent carportConnection;
    private Integer carportWidth;

    /**
     * Constructor for the component
     * Calls the validate function
     * @param width Width int to validate
     * @param carportWidth Carport width to validate with
     */
    public ShedWidthComponent(int width, WidthComponent carportWidth) throws ValidationFailedException {
        this.width = width;
        this.carportConnection = carportWidth;
        validate();
    }

    /**
     * Constructor for the component
     * Calls the validate function
     * Convert width string to a int
     * @param width Width string to validate
     * @param carportWidth Carport Width to validate with
     */
    public ShedWidthComponent(String width, WidthComponent carportWidth) throws ValidationFailedException {
        if(width.equals("")) {
            //Don't forget to update test if this error message is changed.
            throw new ValidationFailedException("Dette felt skal udfyldes.");
        }
        try {
            this.width = Integer.parseInt(width);
        }catch (NumberFormatException ex) {
            throw new ValidationFailedException("Skur bredden skal være et tal.");
        }
        this.carportConnection = carportWidth;
        validate();
    }

    /**
     * Constructor for the component
     * Convert Width string and cartport Width string to a int
     * Calls the validate function
     * @param width Width string to validate
     * @param carportWidth Carport width string to validate with
     */
    public ShedWidthComponent(String width, String carportWidth) throws ValidationFailedException {
        if(width.equals("")) {
            //Don't forget to update test if this error message is changed.
            throw new ValidationFailedException("Dette felt skal udfyldes.");
        }
        try {
            this.width = Integer.parseInt(width);
            this.carportWidth = Integer.parseInt(carportWidth);
        }catch (NumberFormatException ex) {
            throw new ValidationFailedException("Skur bredden skal være et tal.");
        }
        validate();
    }

    /**
     * Validates the Shed Width
     * @return True if the Shed Width validates according to the rules
     * @exception ValidationFailedException Thrown if the Shed Width trying to be validated does not comply with the rules
     */
    @Override
    public boolean validate() throws ValidationFailedException {
        //If width is 0 or lower, a ValidationFailedException is thrown
        if (width < 1) {
            throw new ValidationFailedException("Skur bredde må ikke være under 0.");
        //If width is larger than the carport it is connected to, a ValidationFailedException is thrown
        } else if (carportWidth != null && width > carportWidth){
            throw new ValidationFailedException("Skur dybde må ikke være større end carporten");
        }else if (carportConnection != null && width > carportConnection.getWidth()) {
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

    /**
     * Used to compare the component with a Integer
     * @return True if the Shed Width is equal to the Integer its comparing to, else false
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
        ShedWidthComponent component = (ShedWidthComponent) o;
        return width == component.width;
    }
}
