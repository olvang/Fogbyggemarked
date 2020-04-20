package Components;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class HeightComponent implements Component{
    int height;

    public HeightComponent(int height) {
        this.height = height;
    }

    @Override
    public boolean Validate() {
        throw new NotImplementedException();
    }
}
