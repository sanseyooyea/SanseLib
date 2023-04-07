package work.microhand.sanselib.exception;

/**
 * @author SanseYooyea
 */
public class ParameterParseException extends Exception {

    public ParameterParseException(String message) {
        super(message);
    }

    public ParameterParseException(String message, Throwable cause) {
        super(message, cause);
    }
}