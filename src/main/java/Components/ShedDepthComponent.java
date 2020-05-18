package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

public class ShedDepthComponent implements Component {
    private int depth;

    private DepthComponent carportConnection;
    private Integer carportDepth;

    //-------------//
    // Constructor //
    //-------------//
    public ShedDepthComponent(int depth, DepthComponent carportDepth) throws ValidationFailedException {
        this.depth = depth;
        this.carportConnection = carportDepth;
        validate();
    }

    public ShedDepthComponent(String depth, DepthComponent carportDepth) throws ValidationFailedException {
        if(depth.equals("")) {
            //Don't forget to update test if this error message is changed.
            throw new ValidationFailedException("Dette felt skal udfyldes.");
        }
        try {
            this.depth = Integer.parseInt(depth);
        }catch (NumberFormatException ex) {
            throw new ValidationFailedException("Skur dybden skal være et tal.");
        }
        this.carportConnection = carportDepth;
        validate();
    }

    public ShedDepthComponent(String depth, String cartportDepth) throws ValidationFailedException {
        if(depth.equals("")) {
            //Don't forget to update test if this error message is changed.
            throw new ValidationFailedException("Dette felt skal udfyldes.");
        }
        try {
            this.depth = Integer.parseInt(depth);
            this.carportDepth = Integer.parseInt(cartportDepth);
        }catch (NumberFormatException ex) {
            throw new ValidationFailedException("Skur dybden skal være et tal.");
        }
        validate();
    }

    //-------------//
    // Validation //
    //------------//
    @Override
    public boolean validate() throws ValidationFailedException {
        //If depth is below 100, a ValidationFailedException is thrown
        if(depth < 100) {
            throw new ValidationFailedException("Skur dybde må ikke være under 1m");
            //If depth is larger than the carport it is connected to, a ValidationFailedException is thrown
        } else if (carportDepth != null && depth > carportDepth){
            throw new ValidationFailedException("Skur dybde må ikke være større end carporten");
        } else if (carportConnection != null && depth > carportConnection.getDepth()) {
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
