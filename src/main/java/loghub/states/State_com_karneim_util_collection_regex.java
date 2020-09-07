package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.karneim.util.collection.regex.Pattern;

import loghub.Runner;

/**
 * @author fa4
 *
 */
@State(Scope.Benchmark)
public class State_com_karneim_util_collection_regex extends Runner<Pattern> {

    @Override
    protected Pattern[] getPatternStorage(int size) {
        return new Pattern[size];
    }

    @Override
    protected Pattern generate(String i) {
        return new com.karneim.util.collection.regex.Pattern(i);
    }

    @Override
    protected boolean match(Pattern pattern, String searched) {
        return pattern.contains(searched);
    }

}
