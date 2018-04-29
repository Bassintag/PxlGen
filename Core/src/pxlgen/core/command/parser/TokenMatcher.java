package pxlgen.core.command.parser;

import java.util.regex.Matcher;

/**
 * TokenMatcher.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 28/04/2018
 */
public class TokenMatcher {

    private final CommandTokenType tokenType;

    private String match;
    private String input;

    public TokenMatcher(String input, CommandTokenType tokenType) {
        this(tokenType);
        this.setInput(input);
    }

    public TokenMatcher(CommandTokenType tokenType) {
        this.tokenType = tokenType;
        match = null;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public CommandTokenType getTokenType() {
        return tokenType;
    }

    public void advance(int amount) {
        input = input.substring(amount);
        match = null;
    }

    public int getMatchLength() {
        Matcher matcher = tokenType.getPattern().matcher(input);
        if (matcher.find()) {
            match = matcher.group();
            return matcher.group().length();
        }
        return -1;
    }

    public String getMatch() {
        return match;
    }
}
