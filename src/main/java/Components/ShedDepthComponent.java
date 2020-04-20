package Components;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ShedDepthComponent implements Component {
    int depth;

    public ShedDepthComponent(int depth, DepthComponent carportDepth) {
        this.depth = depth;
    }

    @Override
    public boolean Validate() {
        throw new NotImplementedException();
    }
}
