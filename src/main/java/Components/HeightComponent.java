package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class HeightComponent implements Component{
    private int height;

    private int heightLimit = 1000;

    public HeightComponent(int height) throws ValidationFailedException {
        this.height = height;
        validate();
    }

    @Override
    public boolean validate() throws ValidationFailedException {
        if(height < 1) {
            throw new ValidationFailedException("Højde må ikke være under 0m");
        } else if (height > heightLimit) {
            throw new ValidationFailedException("Højde må ikke være over " + (heightLimit / 100) + "m." );
        }
        return true;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) throws ValidationFailedException {
        this.height = height;
        validate();
    }
}
