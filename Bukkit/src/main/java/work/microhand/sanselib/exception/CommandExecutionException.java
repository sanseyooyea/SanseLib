package work.microhand.sanselib.exception;

/**
 * @author SanseYooyea
 */
public class CommandExecutionException extends Throwable {
    private final int errorCode;

    public CommandExecutionException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
