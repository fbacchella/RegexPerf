package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import gnu.regexp.RE;
import gnu.regexp.REException;
import loghub.Runner;

@State(Scope.Benchmark)
public class State_gnu_regexp extends Runner<RE> {

    @Override
    protected RE generate(String i) {
        try {
            return new gnu.regexp.RE(i);
        } catch (REException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean match(RE pattern, String searched) {
        return pattern.isMatch(searched);
    }

}
