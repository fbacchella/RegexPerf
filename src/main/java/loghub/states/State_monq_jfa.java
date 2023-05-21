package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import loghub.Runner;

/**
 * See <a href="https://github.com/HaraldKi/monqjfa">...</a>
 * @author Fabrice Bacchella
 */
@State(Scope.Benchmark)
public class State_monq_jfa extends Runner<Object> {

    @Override
    protected Object[] getPatternStorage(int size) {
        return new Object[size];
    }

    @Override
    protected boolean filterPattern(int patternNum) {
        return false;
    }

    @Override
    protected Object generate(String i) {
        throw new AssertionError(NOT_MAVENIZED);
    }

    @Override
    protected boolean match(Object pattern, String searched) {
        throw new AssertionError(NOT_MAVENIZED);
    }

    @Override
    protected String[] find(Object pattern, String searched) {
        throw new AssertionError(NOT_MAVENIZED);
    }

}
