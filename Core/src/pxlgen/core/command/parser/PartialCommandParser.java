package pxlgen.core.command.parser;

import pxlgen.core.Direction;
import pxlgen.core.command.Command;
import pxlgen.core.command.PartialCommand;
import pxlgen.core.exception.CommandParsingException;

/**
 * PartialCommandParser.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 14/09/2018
 */
public class PartialCommandParser extends Parser {

    public PartialCommandParser(String input) {
        super(input);
    }

    private void bool(Command command) throws CommandParsingException {
        String value = token.getValue();
        if (value.equals("true") || value.equals("false")) {
            command.addParam(Boolean.parseBoolean(next()));
        }
    }

    private void direction(Command command) throws CommandParsingException {
        String value = token.getValue();
        try {
            command.addParam(Direction.valueOf(value));
        } catch (IllegalArgumentException ignored) {
        } finally {
            next();
        }
    }

    private void builtinIdentifier(Command command) throws CommandParsingException {
        try {
            bool(command);
        } catch (CommandParsingException e) {
            direction(command);
        }
    }

    private void number(Command command) throws CommandParsingException {
        String value = next();
        command.addParam(Float.parseFloat(value));
    }

    private void string(Command command) throws CommandParsingException {
        String value = next();
        command.addParam(value.substring(1, value.length() - 1));
    }

    private PartialCommand command() throws CommandParsingException {
        String label = type() == CommandTokenType.IDENTIFIER ? next() : null;
        String domain = null;
        if (found(CommandTokenType.DOT)) {
            String value = type() == CommandTokenType.IDENTIFIER ? next() : "";
            domain = label;
            label = value;
        }
        PartialCommand command = new PartialCommand(label, domain);
        if (found(CommandTokenType.LPAR)) {
            command.setHasParams(true);
            while (!found(CommandTokenType.RPAR)) {
                if (type() == CommandTokenType.IDENTIFIER) builtinIdentifier(command);
                else if (type() == CommandTokenType.NUMBER) number(command);
                else if (type() == CommandTokenType.STRING) string(command);
                if (type() != CommandTokenType.RPAR && !found(CommandTokenType.COMMA))
                    break;
            }
        }
        return command;
    }

    public PartialCommand parse() throws CommandParsingException {
        next();
        return command();
    }
}
