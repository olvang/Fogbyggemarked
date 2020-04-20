package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        try {
            this.height = Integer.parseInt(height);
        }catch (Exception ex) {
            throw new ValidationFailedException("Højden skal være et tal.");
        }
        validate();
    }

    //-------------//
    // Validation //
    //------------//
    @Override
    public boolean validate() throws ValidationFailedException {
        if(height < 1) {
            throw new ValidationFailedException("Højde må ikke være under 0m");
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
}
