package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.stevesoft.pat.RegSyntax;
import com.stevesoft.pat.Regex;

import loghub.Runner;

/**
 * See http://www.javaregex.com
 * @author Fabrice Bacchella
 *
 */
@State(Scope.Benchmark)
public class State_com_stevesoft_pat extends Runner<Regex>{

    @Override
    protected Regex[] getPatternStorage(int size) {
        return new Regex[size];
    }

    @Override
    protected Regex generate(String i) {
        try {
            Regex pattern = new Regex();
            pattern.compile(i);
            pattern.optimize();
            return pattern;
        } catch (RegSyntax e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean match(Regex pattern, String searched) {
        return pattern.search(searched);
    }

}
