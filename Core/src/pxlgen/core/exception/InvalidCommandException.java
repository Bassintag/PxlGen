package pxlgen.core.exception;

/**
 * InvalidPluginException.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 22/04/2018
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super();
    }

    public InvalidCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCommandException(Throwable cause) {
        super(cause);
    }

    protected InvalidCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
