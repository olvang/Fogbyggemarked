package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ShedWidthComponent implements Component {
    private int width;

    private WidthComponent carportConnection;

    //-------------//
    // Constructor //
    //-------------//
    public ShedWidthComponent(int width, WidthComponent carportWidth) throws ValidationFailedException {
        this.width = width;
        this.carportConnection = carportWidth;
        validate();
    }

    public ShedWidthComponent(String width, WidthComponent carportWidth) throws ValidationFailedException {
        try {
            this.width = Integer.parseInt(width);
        }catch (Exception ex) {
            throw new ValidationFailedException("Skur bredden skal være et tal.");
        }
        this.carportConnection = carportWidth;
        validate();
    }

    //-------------//
    // Validation //
    //------------//
    @Override
    public boolean validate() throws ValidationFailedException {
        if (width < 1) {
            throw new ValidationFailedException("Skur bredde må ikke være under 0.");
        } else if (width > carportConnection.getWidth()) {
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
}
