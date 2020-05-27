package FunctionLayer.Exceptions;

/**
 * Used when a error happens associated with the database
 */
public class DatabaseException extends Exception{
    public DatabaseException(String msg) {
        super(msg);
    }
}
