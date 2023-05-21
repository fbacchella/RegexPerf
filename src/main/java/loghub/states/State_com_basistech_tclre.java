package loghub.states;

import loghub.Runner;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.basistech.tclre.HsrePattern;
import com.basistech.tclre.PatternFlags;
import com.basistech.tclre.RePattern;
import com.basistech.tclre.RegexException;

/**
 * See <a href="https://github.com/basis-technology-corp/tcl-regex-java">https://github.com/basis-technology-corp/tcl-regex-java</a> and <a href="https://basis-technology-corp.github.io/tcl-regex-java/apidocs/index.html">https://basis-technology-corp.github.io/tcl-regex-java/apidocs/index.html</a>
 * @author Fabrice Bacchella
 *
 */
@State(Scope.Benchmark)
public class State_com_basistech_tclre extends Runner<RePattern> {

    @Override
    protected RePattern[] getPatternStorage(int size) {
        return new RePattern[size];
    }

    @Override
    protected RePattern generate(String i) throws RegexException {
        return HsrePattern.compile(i, PatternFlags.ADVANCED);
    }

    @Override
    protected boolean filterPattern(int patternNum) {
        return patternNum != 6;
    }

    @Override
    protected boolean match(RePattern pattern, String searched) {
        return pattern.matcher(searched).matches();
    }

    @Override
    protected String[] find(RePattern pattern, String searched) {
        throw new AssertionError("Not supported");
    }

}
