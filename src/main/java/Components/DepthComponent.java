package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

public class DepthComponent implements Component {
    private int depth;

    private int depthLimit = 2000;

    //-------------//
    // Constructor //
    //-------------//
    public DepthComponent(int depth) throws ValidationFailedException {
        this.depth = depth;
        validate();
    }

    public DepthComponent(String depth) throws ValidationFailedException {
        if(depth.equals("")) {
            //Don't forget to update test if this error message is changed.
            throw new ValidationFailedException("Dette felt skal udfyldes.");
        }
        try {
            this.depth = Integer.parseInt(depth);
        }catch (NumberFormatException ex) {
            throw new ValidationFailedException("Dybden skal være et tal.");
        }
        validate();
    }

    //-------------//
    // Validation //
    //------------//
    @Override
    public boolean validate() throws ValidationFailedException {
        //If depth is below 200, a ValidationFailedException is thrown
        if( depth < 200) {
            throw new ValidationFailedException("Dybde må ikke være under 2m.");
        //If depth is above the depth limit, a ValidationFailedException is thrown
        } else if (depth > depthLimit) {
            throw new ValidationFailedException("Dybde må ikke være over " + (depthLimit / 100) + "m.");
        }
        return true;
    }

    //-----------------//
    // Getters/Setters //
    //-----------------//
    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) throws ValidationFailedException {
        this.depth = depth;
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
            if ((Integer) o == depth) return true;
        }catch (ClassCastException ex) {}

        if( getClass() != o.getClass() ) return false;
        DepthComponent component = (DepthComponent) o;
        return depth == component.depth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(depth);
    }
}
