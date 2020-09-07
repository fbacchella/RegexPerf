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
public class State_org_apache_regexp extends Runner<org.apache.regexp.RE>{

    @Override
    protected RE generate(String i) {
        return new org.apache.regexp.RE(i);
    }

    @Override
    protected boolean match(RE pattern, String searched) {
        return pattern.match(searched);
    }

}
