package loghub.states;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import loghub.Runner;

/**
 * See http://jakarta.apache.org/oro/, retired
 * @author Fabrice Baccchella
 *
 */
@State(Scope.Benchmark)
public class State_org_apache_oro_text_regex extends Runner<Pattern> {

    Perl5Compiler perl5Compiler = new Perl5Compiler();
    ThreadLocal<Perl5Matcher> perl5Matcher = ThreadLocal.withInitial(Perl5Matcher::new);

    @Override
    protected Pattern[] getPatternStorage(int size) {
        return new Pattern[size];
    }

    @Override
    protected Pattern generate(String i) {
        try {
            Pattern regexpr = perl5Compiler.compile(i);
            return regexpr;
        } catch (MalformedPatternException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean match(Pattern pattern, String searched) {
        return perl5Matcher.get().matches(searched, pattern);
    }

    @Override
    protected String[] find(Pattern pattern, String searched) {
        throw new AssertionError("Not supported");
    }

}
