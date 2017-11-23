package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import loghub.Runner;

@State(Scope.Benchmark)
public abstract class State_com_ibm_regex<P> extends Runner<P/*com.ibm.regex.RegularExpression*/> {

}
