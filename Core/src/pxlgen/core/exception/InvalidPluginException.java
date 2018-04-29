package pxlgen.core.exception;

/**
 * InvalidPluginException.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 22/04/2018
 */
public class InvalidPluginException extends Exception {
    public InvalidPluginException() {
        super();
    }

    public InvalidPluginException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPluginException(Throwable cause) {
        super(cause);
    }

    protected InvalidPluginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidPluginException(String message) {
        super(message);
    }
}
