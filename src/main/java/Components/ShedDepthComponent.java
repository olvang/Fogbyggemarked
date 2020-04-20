package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

public class ShedDepthComponent implements Component {
    private int depth;

    private DepthComponent carportConnection;

    //-------------//
    // Constructor //
    //-------------//
    public ShedDepthComponent(int depth, DepthComponent carportDepth) throws ValidationFailedException {
        this.depth = depth;
        this.carportConnection = carportDepth;
        validate();
    }

    public ShedDepthComponent(String depth, DepthComponent carportDepth) throws ValidationFailedException {
        try {
            this.depth = Integer.parseInt(depth);
        }catch (Exception ex) {
            throw new ValidationFailedException("Skur dybden skal være et tal.");
        }
        this.carportConnection = carportDepth;
        validate();
    }

    //-------------//
    // Validation //
    //------------//
    @Override
    public boolean validate() throws ValidationFailedException {
        //If depth is 0 or lower, a ValidationFailedException is thrown
        if(depth < 1) {
            throw new ValidationFailedException("Skur dybde må ikke være under 0");
        //If depth is larger than the carport it is connected to, a ValidationFailedException is thrown
        } else if (depth > carportConnection.getDepth()) {
            throw new ValidationFailedException("Skur dybde må ikke være større end carporten");
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
        ShedDepthComponent component = (ShedDepthComponent) o;
        return depth == component.depth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(depth);
    }
}
