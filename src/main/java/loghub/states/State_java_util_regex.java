package loghub.states;

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
public class State_java_util_regex extends Runner<Pattern>{

    @Override
    protected Pattern[] getPatternStorage(int size) {
        return new Pattern[size];
    }

    @Override
    protected Pattern generate(String i) {
        return Pattern.compile(i);
    }

    @Override
    protected boolean match(Pattern pattern, String searched) {
        return pattern.matcher(searched).find();
    }

}
