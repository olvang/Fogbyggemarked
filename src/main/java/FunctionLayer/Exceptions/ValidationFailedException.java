package FunctionLayer.Exceptions;

/**
 * Used when a error happens associated with validation in a component
 */
public class ValidationFailedException extends Exception {
    public ValidationFailedException(String message) {
        super(message);
    }
}
