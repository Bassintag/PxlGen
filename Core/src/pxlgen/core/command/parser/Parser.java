package pxlgen.core.command.parser;

import pxlgen.core.command.Command;
import pxlgen.core.exception.CommandParsingException;
import pxlgen.core.exception.UnexpectedTokenException;

/**
 * Parser.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 14/09/2018
 */
public abstract class Parser {
    private final CommandLexer lexer;

    CommandToken token;

    public Parser(String input) {
        this(new CommandLexer(input));
    }

    public Parser(CommandLexer lexer) {
        this.lexer = lexer;
    }

    protected String next() throws CommandParsingException {
        String ret = token == null ? null : token.getValue();
        token = lexer.token();
        return ret;
    }

    protected boolean found(CommandTokenType commandTokenType) throws CommandParsingException {
        if (token.getType() != commandTokenType)
            return false;
        next();
        return true;
    }

    protected String expect(CommandTokenType commandTokenType) throws CommandParsingException {
        String ret = token.getValue();
        if (!found(commandTokenType))
            throw new UnexpectedTokenException(commandTokenType, token);
        return ret;
    }

    protected CommandTokenType type() {
        return token.getType();
    }

    protected String value() {
        return token.getValue();
    }

    public abstract Command parse() throws CommandParsingException;
}
