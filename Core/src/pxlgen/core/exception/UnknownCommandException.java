package pxlgen.core.exception;

/**
 * UnknownCommandException.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
public class UnknownCommandException extends InvalidCommandException {

    public UnknownCommandException(String name) {
        super("Unknown command: '" + name + "'");
    }

}
