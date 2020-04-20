package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ShedDepthComponent implements Component {
    private int depth;

    private DepthComponent carportConnection;

    public ShedDepthComponent(int depth, DepthComponent carportDepth) throws ValidationFailedException {
        this.depth = depth;
        this.carportConnection = carportDepth;
        validate();
    }

    @Override
    public boolean validate() throws ValidationFailedException {
        if(depth < 1) {
            throw new ValidationFailedException("Skur dybde må ikke være under 0");
        } else if (depth > carportConnection.getDepth()) {
            throw new ValidationFailedException("Skur dybde må ikke være større end carporten");
        }
        return true;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) throws ValidationFailedException {
        this.depth = depth;
        validate();
    }
}
