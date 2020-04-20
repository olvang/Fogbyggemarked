package Components;

import FunctionLayer.Exceptions.ValidationFailedException;

public interface Component {
    public abstract boolean validate() throws ValidationFailedException;
    
}
