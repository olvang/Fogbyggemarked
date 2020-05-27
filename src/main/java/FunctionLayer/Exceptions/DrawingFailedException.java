package FunctionLayer.Exceptions;

/**
 * Used when a error happens associated with drawing a SVG
 */
public class DrawingFailedException extends Exception {
    public DrawingFailedException(String msg)  {
        super(msg);
    }
}
