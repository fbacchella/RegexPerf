package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;
import loghub.Runner;

/**
 * See http://cs.au.dk/~amoeller/automaton/
 * @author Fabrice Bacchella
 *
 */
@State(Scope.Benchmark)
public class State_dk_brics_automaton extends Runner<RunAutomaton> {

    @Override
    protected RunAutomaton[] getPatternStorage(int size) {
        return new RunAutomaton[size];
    }

    @Override
    protected RunAutomaton generate(String i) {
        RegExp r = new RegExp(i);
        return new RunAutomaton(r.toAutomaton());
    }

    @Override
    protected boolean match(RunAutomaton pattern, String searched) {
        return pattern.run(searched);
    }

    @Override
    protected String[] find(RunAutomaton pattern, String searched) {
        // TODO Auto-generated method stub
        return null;
    }

}
