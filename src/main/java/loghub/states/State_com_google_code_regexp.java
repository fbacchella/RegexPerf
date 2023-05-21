package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.google.code.regexp.Matcher;
import com.google.code.regexp.Pattern;

import loghub.Runner;

/**
 * See <a href="https://tony19.github.io/named-regexp/doc/0.2.5/com/google/code/regexp/package-summary.html">https://tony19.github.io/named-regexp/doc/0.2.5/com/google/code/regexp/package-summary.html</a>
 *
 * @author Fabrice Bacchella
 *
 */
@State(Scope.Benchmark)
public class State_com_google_code_regexp extends Runner<Pattern> {

    @Override
    protected Pattern[] getPatternStorage(int size) {
        return new Pattern[size];
    }

    @Override
    protected Pattern generate(String i) {
        return Pattern.compile(i);
    }

    @Override
    protected boolean match(Pattern pattern, String searched) {
        return pattern.matcher(searched).find();
    }

    @Override
    protected String[] find(Pattern pattern, String searched) {
        Matcher m = pattern.matcher(searched);
        if (m.find()) {
            String[] found = new String[m.groupCount()];
            for (int i = 0; i < found.length; i++) {
                found[i] = m.group(i);
            }
            return found;
        } else {
            return null;
        }
    }

}
