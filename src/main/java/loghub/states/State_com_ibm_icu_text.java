package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.ibm.icu.text.UnicodeSet;

import loghub.Runner;

@State(Scope.Benchmark)
public class State_com_ibm_icu_text extends Runner<com.ibm.icu.text.UnicodeSet> {

    @Override
    protected UnicodeSet[] getPatternStorage(int size) {
        return new UnicodeSet[size];
    }

    @Override
    protected UnicodeSet generate(String i) {
        return new com.ibm.icu.text.UnicodeSet( "[" + i + "]");
    }

    @Override
    protected boolean match(UnicodeSet pattern, String searched) {
        return pattern.containsAll(searched);
    }

    @Override
    protected String[] find(UnicodeSet pattern, String searched) {
        throw new AssertionError(NOT_SUPPORTED);
    }

}
