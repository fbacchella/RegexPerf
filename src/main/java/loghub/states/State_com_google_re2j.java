package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.google.re2j.Pattern;

import loghub.Runner;

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

    @Override
    protected String[] find(Pattern pattern, String searched) {
        throw new AssertionError("Not supported");
    }

}
