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
     * <p>Constructor for the component</p>
     * <p>Calls the validate function</p>
     * @param depth Depth int to validate
     * @param carportDepth Carport depth to validate with
     */
    public ShedDepthComponent(int depth, DepthComponent carportDepth) throws ValidationFailedException {
        this.depth = depth;
        this.carportConnection = carportDepth;
        validate();
    }

    /**
     * <p>Constructor for the component</p>
     * <p>Calls the validate function</p>
     * <p>Convert Depth string to a int</p>
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
     * <p>Constructor for the component</p>
     * <p>Convert Depth string and cartport Depth string to a int</p>
     * <p>Calls the validate function</p>
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
     * <p>Validates the Shed Depth</p>
     * @return True if the Shed Depth validates according to the rules
     * @exception ValidationFailedException Thrown if the Shed Depth trying to be validated does not comply with the rules
     */
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
        int old = this.depth;
        this.depth = depth;
        try {
            validate();
        } catch (ValidationFailedException e) {
            this.depth = old;
            throw new ValidationFailedException(e.getMessage());
        }
    }

    //-----------//
    // Comparing //
    //-----------//
    /**
     * <p>Used to compare the component with a Integer</p>
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
