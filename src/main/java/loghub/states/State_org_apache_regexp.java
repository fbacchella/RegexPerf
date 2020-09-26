package loghub.states;

import org.apache.regexp.RE;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import loghub.Runner;

/**
 * See http://jakarta.apache.org/regexp/ (retired)
 * @author Fabrice Bacchella
 *
 */
@State(Scope.Benchmark)
public class State_org_apache_regexp extends Runner<ThreadLocal<RE>>{

    @SuppressWarnings("unchecked")
    @Override
    protected ThreadLocal<RE>[] getPatternStorage(int size) {
        return (ThreadLocal<RE>[]) new ThreadLocal<?>[size];
    }

    @Override
    protected ThreadLocal<RE> generate(String i) {
        return ThreadLocal.withInitial(() -> new RE(i));
    }

    @Override
    protected boolean match(ThreadLocal<RE> pattern, String searched) {
        return pattern.get().match(searched);
    }

    @Override
    protected String[] find(ThreadLocal<RE> pattern, String searched) {
        throw new AssertionError("Not supported");
    }

}
