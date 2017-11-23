package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import loghub.Runner;

/**
 * See http://developer.java.sun.com/developer/technicalArticles/releases/1.4regex/
 * @author Fabrice Bacchella
 *
 */
@State(Scope.Benchmark)
public class State_java_util_regex extends Runner<java.util.regex.Pattern>{
    @Override
    protected java.util.regex.Pattern generate(String i) {
        return java.util.regex.Pattern.compile(i);
    }

    @Override
    protected boolean match(java.util.regex.Pattern pattern, String searched) {
        return pattern.matcher(searched).find();
    }
}
