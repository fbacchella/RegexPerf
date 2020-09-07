package loghub.states;

import loghub.Runner;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.basistech.tclre.HsrePattern;
import com.basistech.tclre.PatternFlags;
import com.basistech.tclre.RePattern;
import com.basistech.tclre.RegexException;

@State(Scope.Benchmark)
public class State_com_basistech_tclre extends Runner<RePattern> {

    @Override
    protected RePattern[] getPatternStorage(int size) {
        return new RePattern[size];
    }

    @Override
    protected RePattern generate(String i) {
        try {
            return HsrePattern.compile(i, PatternFlags.ADVANCED);
        } catch (RegexException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean match(RePattern pattern, String searched) {
        return pattern.matcher(searched).matches();
    }

}
