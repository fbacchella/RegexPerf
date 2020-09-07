package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import loghub.Runner;

@State(Scope.Benchmark)
public class State_com_ibm_regex extends Runner<Object> {

    @Override
    protected Object[] getPatternStorage(int size) {
        throw new AssertionError("Library not found");
    }

    @Override
    protected Object generate(String i) {
        throw new AssertionError("Library not found");
    }

    @Override
    protected boolean match(Object pattern, String searched) {
        throw new AssertionError("Library not found");
    }

}
