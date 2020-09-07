package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import loghub.Runner;

/**
 * See http://jint.sourceforge.net, no maven
 * @author Fabrice Bacchella
 *
 * @param <P>
 */
@State(Scope.Benchmark)
public class State_kmy_regex_util extends Runner<Object> {

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
        throw new AssertionError("Not mavenized");
    }

    @Override
    protected String[] find(Object pattern, String searched) {
        // TODO Auto-generated method stub
        return null;
    }

}
