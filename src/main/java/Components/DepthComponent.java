package Components;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DepthComponent implements Component {

    int depth;

    public DepthComponent(int depth) {
        this.depth = depth;
    }

    @Override
    public boolean Validate() {
        throw new NotImplementedException();
    }
}
