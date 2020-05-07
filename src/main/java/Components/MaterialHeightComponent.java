package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Objects;
/**
 * @author fdinsen
 * @version 1.0
 * @since 1.0
 */

public class MaterialHeightComponent implements Component {
    private int height;

    //-------------//
    // Constructor //
    //-------------//
    public MaterialHeightComponent(String height) throws ValidationFailedException {
        if(height.equals("")) {
            throw new ValidationFailedException("Dette felt skal udfyldes.");
        }
        try {
            this.height = Integer.parseInt(height);
        }catch ( Exception ex ) {
            throw new ValidationFailedException("Bredde skal være et tal.");
        }
        validate();
    }

    public MaterialHeightComponent(int height) throws ValidationFailedException {
        this.height = height;
        validate();
    }

    //-------------//
    // Validation //
    //------------//
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
        this.height = height;
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
            if ((Integer) o == height) return true;
        }catch (ClassCastException ex) {}

        if( getClass() != o.getClass() ) return false;
        MaterialHeightComponent component = (MaterialHeightComponent) o;
        return height == component.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height);
    }
}
