package Components;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class WidthComponent implements Component {
    int width;

    public WidthComponent(int width) {
        this.width = width;

    }

    @Override
    public boolean Validate() {
        throw new NotImplementedException();
    }
}
