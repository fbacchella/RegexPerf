package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.google.code.regexp.Pattern;

import loghub.Runner;

@State(Scope.Benchmark)
public class State_com_google_code_regexp extends Runner<Pattern> {

    @Override
    protected Pattern generate(String i) {
        return Pattern.compile(i);
    }

    @Override
    protected boolean match(Pattern pattern, String searched) {
        return pattern.matcher(searched).find();
    }

}
