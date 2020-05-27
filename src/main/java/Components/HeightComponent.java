package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

public class HeightComponent implements Component{
    private int height;

    private int heightLimit = 1000;

    //-------------//
    // Constructor //
    //-------------//
    public HeightComponent(int height) throws ValidationFailedException {
        this.height = height;
        validate();
    }

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

    //-------------//
    // Validation //
    //------------//
    @Override
    public boolean validate() throws ValidationFailedException {
        //If height is below 200, a ValidationFailedException is thrown
        if(height < 200) {
            throw new ValidationFailedException("Højde må ikke være under 2m");
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

    @Override
    public int hashCode() {
        return Objects.hash(height);
    }
}
