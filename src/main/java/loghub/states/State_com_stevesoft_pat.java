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
public class State_com_stevesoft_pat extends Runner<com.stevesoft.pat.Regex>{

    @Override
    protected com.stevesoft.pat.Regex generate(String i) {
        try {
            Regex pattern = new com.stevesoft.pat.Regex();
            pattern.compile(i);
            pattern.optimize();
            return pattern;
        } catch (RegSyntax e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean match(com.stevesoft.pat.Regex pattern, String searched) {
        return pattern.search(searched);
    }

}
