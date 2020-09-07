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
public class State_monq_jfa extends Runner<Object> {

    @Override
    protected Object[] getPatternStorage(int size) {
        return new Object[size];
    }

    @Override
    protected Object generate(String i) {
        throw new AssertionError("Not mavenized");
    }

    @Override
    protected boolean match(Object pattern, String searched) {
        throw new AssertionError("Not Mavenized");
    }

    @Override
    protected String[] find(Object pattern, String searched) {
        return null;
    }

}
