package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.stevesoft.pat.RegSyntax;
import com.stevesoft.pat.Regex;

import loghub.Runner;

/**
 * See <a href="https://www.javaregex.com">https://www.javaregex.com</a>
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
    protected boolean filterPattern(int patternNum) {
        return patternNum != 6;
    }

    @Override
    protected ThreadLocal<Regex> generate(String i) throws RegSyntax {
        Regex pattern = new Regex();
        pattern.compile(i);
        pattern.optimize();
        return ThreadLocal.withInitial(() -> new Regex(pattern));
    }

    @Override
    protected boolean match(ThreadLocal<Regex> pattern, String searched) {
        return pattern.get().matchAt(searched, 0);
    }

}
