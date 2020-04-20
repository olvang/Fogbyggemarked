package Components;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ShedWidthComponent implements Component {
    int width;

    public ShedWidthComponent(int width, WidthComponent carportWidth) {
        this.width = width;
    }

    @Override
    public boolean Validate() {
        throw new NotImplementedException();
    }
}
