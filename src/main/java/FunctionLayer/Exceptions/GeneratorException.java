package FunctionLayer.Exceptions;

/**
 * The purpose of GeneratorException is to throw an exception with a message, when a generator fails/can't calculate it's BillLines
 */
public class GeneratorException extends Exception {
    public GeneratorException(String msg) {
        super(msg);
    }
}


