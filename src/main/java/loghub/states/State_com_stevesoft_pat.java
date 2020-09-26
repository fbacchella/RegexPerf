package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.stevesoft.pat.RegSyntax;
import com.stevesoft.pat.Regex;

import loghub.Runner;

/**
 * See http://www.javaregex.com
 * @author Fabrice Bacchella
 *
 */
@State(Scope.Benchmark)
public class State_com_stevesoft_pat extends Runner<ThreadLocal<Regex>>{

    @SuppressWarnings("unchecked")
    @Override
    protected ThreadLocal<Regex>[] getPatternStorage(int size) {
        return (ThreadLocal<Regex>[]) new ThreadLocal<?>[size];
    }

    @Override
    protected ThreadLocal<Regex> generate(String i) {
        try {
            Regex pattern = new Regex();
            pattern.compile(i);
            pattern.optimize();
            return ThreadLocal.withInitial(() -> new Regex(pattern));
        } catch (RegSyntax e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean match(ThreadLocal<Regex> pattern, String searched) {
        return pattern.get().search(searched);
    }

    @Override
    protected String[] find(ThreadLocal<Regex> pattern, String searched) {
        throw new AssertionError("Not supported");
    }

}
