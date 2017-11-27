package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import loghub.Runner;

/**
 * See https://github.com/HaraldKi/monqjfa
 * @author Fabrice Bacchella
 *
 * @param <P>
 */
@State(Scope.Benchmark)
public class State_monq_jfa<P> extends Runner<P> {

    @Override
    protected P generate(String i) {
        throw new AssertionError("Not mavenized");
    }

    @Override
    protected boolean match(P pattern, String searched) {
        throw new AssertionError("Not Mavenized");
    }


}
