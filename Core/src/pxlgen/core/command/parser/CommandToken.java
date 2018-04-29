package pxlgen.core.command.parser;

/**
 * CommandToken.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 22/04/2018
 */
public class CommandToken {

    private final CommandTokenType type;

    private final String value;

    public CommandToken(CommandTokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public CommandTokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
