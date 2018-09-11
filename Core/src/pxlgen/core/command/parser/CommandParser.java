package pxlgen.core.command.parser;

import pxlgen.core.Direction;
import pxlgen.core.command.Command;
import pxlgen.core.exception.CommandParsingException;
import pxlgen.core.exception.UnexpectedTokenException;

/**
 * CommandParser.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 29/04/2018
 */
public class CommandParser {

    private final CommandLexer lexer;

    private CommandToken token;

    public CommandParser(String input) {
        this(new CommandLexer(input));
    }

    public CommandParser(CommandLexer lexer) {
        this.lexer = lexer;
    }

    private void next() throws CommandParsingException {
        token = lexer.token();
    }

    private boolean found(CommandTokenType commandTokenType) throws CommandParsingException {
        if (token.getType() != commandTokenType)
            return false;
        next();
        return true;
    }

    private String expect(CommandTokenType commandTokenType) throws CommandParsingException {
        String ret = token.getValue();
        if (!found(commandTokenType))
            throw new UnexpectedTokenException(commandTokenType, token);
        return ret;
    }

    private boolean bool() throws CommandParsingException {
        String value = token.getValue();
        if (value.equals("true") || value.equals("false"))
            return Boolean.parseBoolean(value);
        throw new UnexpectedTokenException(token);
    }

    private Direction direction() throws CommandParsingException {
        String value = token.getValue();
        try {
            return Direction.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new UnexpectedTokenException(token);
        }
    }

    private Object builtinIdentifier() throws CommandParsingException {
        Object ret;
        try {
            ret = bool();
        } catch (CommandParsingException e) {
            ret = direction();
        }
        expect(CommandTokenType.IDENTIFIER);
        return ret;
    }

    private float number() throws CommandParsingException {
        String value = expect(CommandTokenType.NUMBER);
        return Float.parseFloat(value);
    }

    private String string() throws CommandParsingException {
        String value = expect(CommandTokenType.STRING);
        return value.substring(1, value.length() - 1);
    }

    private Command command() throws CommandParsingException {
        String label = expect(CommandTokenType.IDENTIFIER);
        String domain = null;
        if (found(CommandTokenType.DOT)) {
            domain = label;
            label = expect(CommandTokenType.IDENTIFIER);
        }
        Command command = new Command(label, domain);
        expect(CommandTokenType.LPAR);
        while (!found(CommandTokenType.RPAR)) {
            if (token.getType() == CommandTokenType.IDENTIFIER)
                command.addParam(builtinIdentifier());
            else if (token.getType() == CommandTokenType.NUMBER)
                command.addParam(number());
            else
                command.addParam(string());
            if (token.getType() != CommandTokenType.RPAR)
                expect(CommandTokenType.COMMA);
        }
        return command;
    }

    public Command parse() throws CommandParsingException {
        next();
        Command command = command();
        expect(CommandTokenType.EOF);
        return command;
    }
}
