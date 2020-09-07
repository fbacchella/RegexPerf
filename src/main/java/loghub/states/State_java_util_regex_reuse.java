package loghub.states;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import loghub.Runner;

/**
 * See http://developer.java.sun.com/developer/technicalArticles/releases/1.4regex/
 * @author Fabrice Bacchella
 *
 */
@State(Scope.Benchmark)
public class State_java_util_regex_reuse extends Runner<ThreadLocal<Matcher>>{

    @SuppressWarnings("unchecked")
    @Override
    protected ThreadLocal<Matcher>[] getPatternStorage(int size) {
        return (ThreadLocal<Matcher>[]) new ThreadLocal<?>[size];
    }

    @Override
    protected ThreadLocal<Matcher> generate(String i) {
        return ThreadLocal.withInitial(() -> Pattern.compile(i).matcher(""));
    }

    @Override
    protected boolean match(ThreadLocal<Matcher> pattern, String searched) {
        return pattern.get().reset(searched).find();
    }

}
