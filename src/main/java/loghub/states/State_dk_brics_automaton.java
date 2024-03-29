package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;
import loghub.Runner;

/**
 * See <a href="https://www.brics.dk/automaton/">...</a>
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
    protected boolean filterPattern(int patternNum) {
        return patternNum != 6;
    }

    @Override
    protected RunAutomaton generate(String i) {
        RegExp r = new RegExp(i, RegExp.ALL);
        return new RunAutomaton(r.toAutomaton(), true);
    }

    @Override
    protected boolean match(RunAutomaton pattern, String searched) {
        return pattern.run(searched);
    }

    @Override
    protected String[] matchGroup(RunAutomaton pattern, String searched) {
        // Not handled, AutomatonMatcher.group(int) is a trap
        throw new AssertionError(NOT_SUPPORTED);
    }

    @Override
    protected String[] findGroup(RunAutomaton pattern, String searched) {
        // Not handled, AutomatonMatcher.group(int) is a trap
        throw new AssertionError(NOT_SUPPORTED);
    }

    @Override
    protected String translate(int pattnum) {
        if (pattnum == 0) {
            return "(([^:]+)://)?([^:/]+)(:([0-9]+))?(/.*)";
        } else {
            return super.translate(pattnum);
        }
    }

}
