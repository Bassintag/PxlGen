package pxlgen.core.command.parser;

import pxlgen.core.exception.InvalidCharacterException;

import java.util.ArrayList;
import java.util.List;

/**
 * CommandLexer.java created for PxlGen
 *
 * @author Antoine
 * @version 1.0
 * @since 22/04/2018
 */
public class CommandLexer {

    private String             input;
    private List<TokenMatcher> matchers;

    public CommandLexer(String input) {
        this.input = input;
        this.matchers = new ArrayList<>();
        for (CommandTokenType tokenType : CommandTokenType.values()) {
            TokenMatcher matcher = new TokenMatcher(input, tokenType);
            matchers.add(matcher);
        }
    }

    private void advance(int amount) {
        for (TokenMatcher matcher : matchers) {
            matcher.advance(amount);
        }
        input = input.substring(amount);
    }

    private TokenMatcher getValidMatcher() {
        TokenMatcher found = null;
        int best = -1;
        for (TokenMatcher matcher : matchers) {
            int len = matcher.getMatchLength();
            if (len > best) {
                best = len;
                found = matcher;
            }
        }
        return found;
    }

    public CommandToken token() throws InvalidCharacterException {
        TokenMatcher matcher;
        CommandToken token;
        do {
            matcher = getValidMatcher();
            if (matcher == null)
                throw new InvalidCharacterException(input.length() == 0 ? '\0' : input.charAt(0));
            token = new CommandToken(matcher.getTokenType(), matcher.getMatch());
            advance(token.getValue().length());
        } while (matcher.getTokenType().skip());
        return token;
    }
}
