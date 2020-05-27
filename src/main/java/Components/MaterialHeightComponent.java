package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Objects;
/**
 * <p>Component used to validate a Material Height int</p>
 */
public class MaterialHeightComponent implements Component {
    private int height;

    /**
     * <p>Constructor for the component</p>
     * <p>Calls the validate function</p>
     * <p>Convert the string to a int</p>
     * @param height Material Height string to validate
     */
    public MaterialHeightComponent(String height) throws ValidationFailedException {
        if(height.equals("")) {
            throw new ValidationFailedException("Dette felt skal udfyldes.");
        }
        try {
            this.height = Integer.parseInt(height);
        }catch ( NumberFormatException ex ) {
            throw new ValidationFailedException("Bredde skal være et tal.");
        }
        validate();
    }

    /**
     * <p>Constructor for the component</p>
     * <p>Calls the validate function</p>
     * @param height Material Height int to validate
     */
    public MaterialHeightComponent(int height) throws ValidationFailedException {
        this.height = height;
        validate();
    }

    /**
     * <p>Validates the Material Height</p>
     * @return True if the Material Height validates according to the rules
     * @exception ValidationFailedException Thrown if the Material Height trying to be validated does not comply with the rules
     */
    @Override
    public boolean validate() throws ValidationFailedException {
        if(height < 0) {
            throw new ValidationFailedException("Højde må ikke være under 0.");
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
        int old = this.height;
        this.height = height;
        try {
            validate();
        } catch (ValidationFailedException e) {
            this.height = old;
            throw new ValidationFailedException(e.getMessage());
        }
    }

    //-----------//
    // Comparing //
    //-----------//
    /**
     * <p>Used to compare the component with a Integer</p>
     * @return True if the Material Height is equal to the Integer its comparing to, else false
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
        MaterialHeightComponent component = (MaterialHeightComponent) o;
        return height == component.height;
    }
}
