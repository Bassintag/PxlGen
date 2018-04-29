package pxlgen.core.command.parser;

import java.util.regex.Pattern;

/**
 * CommandTokenType.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 28/04/2018
 */
public enum CommandTokenType {

    IDENTIFIER("[A-Za-z_]\\w*"),
    LPAR("\\("),
    RPAR("\\)"),
    NUMBER("-?\\d+(\\.\\d+)?"),
    STRING("\\\"\\w*\\\""),
    COMMA(","),
    SPACE("\\s+", true),
    EOF("$");

    private final Pattern pattern;
    private final boolean skip;

    CommandTokenType(String pattern) {
        this(pattern, false);
    }

    CommandTokenType(String pattern, boolean skip) {
        this.pattern = Pattern.compile("^" + pattern);
        this.skip = skip;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public boolean skip() {
        return skip;
    }
}
