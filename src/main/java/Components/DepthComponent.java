package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DepthComponent implements Component {
    private int depth;

    private int depthLimit = 2000;

    public DepthComponent(int depth) throws ValidationFailedException {
        this.depth = depth;
        validate();
    }

    @Override
    public boolean validate() throws ValidationFailedException {
        if( depth < 1) {
            throw new ValidationFailedException("Dybde må ikke være under 0.");
        } else if (depth > depthLimit) {
            throw new ValidationFailedException("Dybde må ikke være over " + (depthLimit / 100) + "m.");
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
