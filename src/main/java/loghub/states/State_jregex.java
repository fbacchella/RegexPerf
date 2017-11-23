package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import jregex.Pattern;
import loghub.Runner;

/**
 * See http://jregex.sourceforge.net, Last Update: 2013-06-12
 * @author Fabrice Bacchella
 *
 */
@State(Scope.Benchmark)
public class State_jregex extends Runner<Pattern> {

    @Override
    protected Pattern generate(String i) {
        return new jregex.Pattern(i);
    }

    @Override
    protected boolean match(Pattern pattern, String searched) {
        return pattern.matcher(searched).matches();
    }

}
