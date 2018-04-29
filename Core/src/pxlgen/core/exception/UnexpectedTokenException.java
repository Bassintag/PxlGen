package pxlgen.core.exception;

import pxlgen.core.command.parser.CommandToken;
import pxlgen.core.command.parser.CommandTokenType;

/**
 * UnexpectedTokenException.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
public class UnexpectedTokenException extends CommandParsingException {
    public UnexpectedTokenException(CommandToken commandToken) {
        super("Unexpected token " + commandToken.getType().name() + " with value '" + commandToken.getValue() + "'");
    }

    public UnexpectedTokenException(CommandTokenType expected, CommandToken found) {
        super("Expected token: " + expected.name() +
                " but instead got: " + found.getType().name() + " with value '" + found.getValue() + "'");

    }
}
