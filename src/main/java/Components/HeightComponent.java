package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

/**
 * <p>Component used to validate a Carport Height</p>
 */
public class HeightComponent implements Component{
    private int height;

    private int heightLimit = 1000;

    /**
     * <p>Constructor for the component</p>
     * <p>Calls the validate function</p>
     * @param height The height int to validate
     */
    public HeightComponent(int height) throws ValidationFailedException {
        this.height = height;
        validate();
    }

    /**
     * <p>Constructor for the component</p>
     * <p>Calls the validate function</p>
     * <p>Converts to int</p>
     * @param height The height string to validate
     */
    public HeightComponent(String height) throws ValidationFailedException {
        if(height.equals("")) {
            //Don't forget to update test if this error message is changed.
            throw new ValidationFailedException("Dette felt skal udfyldes.");
        }
        try {
            this.height = Integer.parseInt(height);
        }catch (NumberFormatException ex) {
            throw new ValidationFailedException("Højden skal være et tal.");
        }
        validate();
    }


    /**
     * <p>Validates the Height</p>
     * @return True if the height validates according to the rules
     * @exception ValidationFailedException Thrown if the height trying to be validated does not comply with the rules
     */
    @Override
    public boolean validate() throws ValidationFailedException {
        //If height is 0 or lower, a ValidationFailedException is thrown
        if(height < 1) {
            throw new ValidationFailedException("Højde må ikke være under 0m");
        //If height is above the height limit, a ValidationFailedException is thrown
        } else if (height > heightLimit) {
            throw new ValidationFailedException("Højde må ikke være over " + (heightLimit / 100) + "m." );
        }
        return true;
    }

    //-----------------//
    // Getters/Setters //
    //-----------------//
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) throws ValidationFailedException {
        this.height = height;
        validate();
    }

    //-----------//
    // Comparing //
    //-----------//

    /**
     * <p>Used to compare the component with a Integer</p>
     * @return True if the height is equal to the Integer its comparing to, else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        //This try/catch block allows the component to be compared to an int
        try {
            if ((Integer) o == height) return true;
        }catch (ClassCastException ex) {}

        if( getClass() != o.getClass() ) return false;
        HeightComponent component = (HeightComponent) o;
        return height == component.height;
    }
}
