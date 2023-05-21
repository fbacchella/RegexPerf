package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import gnu.regexp.RE;
import gnu.regexp.REException;
import loghub.Runner;

@State(Scope.Benchmark)
public class State_gnu_regexp extends Runner<RE> {

    @Override
    protected RE[] getPatternStorage(int size) {
        return new RE[size];
    }

    @Override
    protected boolean filterPattern(int patternNum) {
        return patternNum != 6;
    }

    @Override
    protected RE generate(String i) {
        try {
            return new RE(i);
        } catch (REException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean match(RE pattern, String searched) {
        return pattern.isMatch(searched);
    }

    @Override
    protected String[] find(RE pattern, String searched) {
        throw new AssertionError("Not supported");
    }

}
