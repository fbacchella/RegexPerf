package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import loghub.Runner;

@State(Scope.Benchmark)
public class State_gnu_rex<P> extends Runner<P/*gnu.rex.Rex*/> {

    @Override
    protected P generate(String i) {
        throw new AssertionError("Library not found");
    }

    @Override
    protected boolean match(P pattern, String searched) {
        throw new AssertionError("Library not found");
    }


}
