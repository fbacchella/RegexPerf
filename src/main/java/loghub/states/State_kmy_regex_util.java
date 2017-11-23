package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import loghub.Runner;

/**
 * See http://jint.sourceforge.net, no maven
 * @author Fabrice Bacchella
 *
 * @param <P>
 */
@State(Scope.Benchmark)
public abstract class State_kmy_regex_util<P> extends Runner<P/*kmy.regex.util.Regex*/> {

}
