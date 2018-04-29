package pxlgen.core.exception;

/**
 * CommandParsingException.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
public class CommandParsingException extends Exception {
    public CommandParsingException() {
        super();
    }

    public CommandParsingException(String message) {
        super(message);
    }

    public CommandParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandParsingException(Throwable cause) {
        super(cause);
    }

    protected CommandParsingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
