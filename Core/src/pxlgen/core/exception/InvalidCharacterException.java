package pxlgen.core.exception;

/**
 * InvalidCharacterException.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
public class InvalidCharacterException extends CommandParsingException {
    public InvalidCharacterException(char c) {
        super("Invalid character '" + c + "'");
    }
}
