package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.google.re2j.Pattern;

import loghub.Runner;

/**
 * See <a href="https://github.com/google/re2j">https://github.com/google/re2j</a>
 *
 * @author Fabrice Bacchella
 *
 */
@State(Scope.Benchmark)
public class State_com_google_re2j extends Runner<Pattern> {

    @Override
    protected boolean filterPattern(int patternNum) {
        return patternNum != 3 && patternNum != 5 && patternNum != 6;
    }

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
        return pattern.matches(searched);
    }

}
