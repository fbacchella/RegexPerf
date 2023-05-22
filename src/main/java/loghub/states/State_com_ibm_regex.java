package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import loghub.Runner;

@State(Scope.Benchmark)
public class State_com_ibm_regex extends Runner<Object> {

    @Override
    protected Object[] getPatternStorage(int size) {
        return new Object[size];
    }

    @Override
    protected Object generate(String i) {
        throw new AssertionError(LIBRARY_NOT_FOUND);
    }

    @Override
    protected boolean match(Object pattern, String searched) {
        throw new AssertionError(LIBRARY_NOT_FOUND);
    }

    @Override
    protected boolean find(Object pattern, String searched) {
        throw new AssertionError(LIBRARY_NOT_FOUND);
    }

    @Override
    protected String[] matchGroup(Object pattern, String searched) {
        throw new AssertionError(LIBRARY_NOT_FOUND);
    }

    @Override
    protected String[] findGroup(Object pattern, String searched) {
        throw new AssertionError(LIBRARY_NOT_FOUND);
    }

}
