package Components;

import FunctionLayer.Exceptions.ValidationFailedException;

/**
 * <p>The component interface used to extend from</p>
 */
public interface Component {
    /**
     * <p>Ensures that all components has the validates method</p>
     * @return True if the data is validated according to the rules in the component
     * @exception ValidationFailedException Thrown if the data trying to be validated does not comply with the rules in the component
     */
    public abstract boolean validate() throws ValidationFailedException;
    
}
