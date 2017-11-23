package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import loghub.Runner;

/**
 * See https://github.com/HaraldKi/monqjfa
 * @author Fabrice Bacchella
 *
 * @param <P>
 */
@State(Scope.Benchmark)
public abstract class State_monq_jfa<P> extends Runner<P/*monq.jfa.Regexp*/> {

}
