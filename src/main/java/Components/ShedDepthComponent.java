package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

/**
 * <p>Component used to validate a Shed Depth int</p>
 */
public class ShedDepthComponent implements Component {
    private int depth;

    private DepthComponent carportConnection;
    private Integer carportDepth;

    /**
     * Constructor for the component
     * Calls the validate function
     * @param depth Depth int to validate
     * @param carportDepth Carport depth to validate with
     */
    public ShedDepthComponent(int depth, DepthComponent carportDepth) throws ValidationFailedException {
        this.depth = depth;
        this.carportConnection = carportDepth;
        validate();
    }

    /**
     * Constructor for the component
     * Calls the validate function
     * Convert Depth string to a int
     * @param depth Depth string to validate
     * @param carportDepth Carport depth to validate with
     */
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

    /**
     * Constructor for the component
     * Convert Depth string and cartport Depth string to a int
     * Calls the validate function
     * @param depth Depth string to validate
     * @param carportDepth Carport depth string to validate with
     */
    public ShedDepthComponent(String depth, String carportDepth) throws ValidationFailedException {
        if(depth.equals("")) {
            //Don't forget to update test if this error message is changed.
            throw new ValidationFailedException("Dette felt skal udfyldes.");
        }
        try {
            this.depth = Integer.parseInt(depth);
            this.carportDepth = Integer.parseInt(carportDepth);
        }catch (NumberFormatException ex) {
            throw new ValidationFailedException("Skur dybden skal være et tal.");
        }
        validate();
    }

    /**
     * Validates the Shed Depth
     * @return True if the Shed Depth validates according to the rules
     * @exception ValidationFailedException Thrown if the Shed Depth trying to be validated does not comply with the rules
     */
    @Override
    public boolean validate() throws ValidationFailedException {
        //If depth is 0 or lower, a ValidationFailedException is thrown
        if(depth < 1) {
            throw new ValidationFailedException("Skur dybde må ikke være under 0");
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
    /**
     * Used to compare the component with a Integer
     * @return True if the Shed Depth is equal to the Integer its comparing to, else false
     */
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
}
