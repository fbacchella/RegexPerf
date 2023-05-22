package loghub.states;

import dregex.Regex;
import loghub.Runner;

public class State_dregex extends Runner<Regex> {

    @Override
    protected Regex[] getPatternStorage(int size) {
        return new Regex[size];
    }

    @Override
    protected boolean filterPattern(int patternNum) {
        return patternNum != 3 && patternNum != 5 && patternNum != 6;
    }

    @Override
    protected Regex generate(String pattern) throws Exception {
        // Anchor are not supported
        if (pattern.startsWith("^")) {
            pattern = pattern.substring(1);
        }
        return Regex.compile(pattern);
    }

    @Override
    protected boolean match(Regex pattern, String searched) {
        return pattern.matches(searched);
    }

}
