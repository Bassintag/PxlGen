package pxlgen.core.exception;

import pxlgen.core.command.Command;

/**
 * NoMatchingDefinitionException.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 10/09/2018
 */
public class NoMatchingDefinitionException extends InvalidCommandException {

    private final String message;

    public NoMatchingDefinitionException(Command command) {
        super();
        StringBuilder sb = new StringBuilder("No matching definition for command: ");
        if (command.getDomain() != null)
            sb.append(command.getDomain()).append(".");
        sb.append(command.getName()).append(" with params: ");
        for (int i = 0; i < command.getParamsCount(); i += 1) {
            if (i > 0) sb.append(", ");
            sb.append(command.getParam(i).getClass().toString());
        }
        message = sb.toString();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
