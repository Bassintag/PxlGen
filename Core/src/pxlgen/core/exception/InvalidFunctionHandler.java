package pxlgen.core.exception;

/**
 * InvalidPluginException.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 22/04/2018
 */
public class InvalidFunctionHandler extends Exception {
    public InvalidFunctionHandler() {
        super();
    }

    public InvalidFunctionHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFunctionHandler(Throwable cause) {
        super(cause);
    }

    protected InvalidFunctionHandler(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidFunctionHandler(String message) {
        super(message);
    }
}
