package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import jregex.Matcher;
import jregex.Pattern;
import loghub.Runner;

/**
 * See <a href="https://jregex.sourceforge.net">https://jregex.sourceforge.net</a>, Last Update: 2013-06-12
 * @author Fabrice Bacchella
 *
 */
@State(Scope.Benchmark)
public class State_jregex extends Runner<Pattern> {

    @Override
    protected Pattern[] getPatternStorage(int size) {
        return new Pattern[size];
    }

    @Override
    protected Pattern generate(String i) {
        return new Pattern(i);
    }

    @Override
    protected boolean match(Pattern pattern, String searched) {
        return pattern.matcher(searched).matches();
    }

    @Override
    protected String[] find(Pattern pattern, String searched) {
        Matcher m = pattern.matcher(searched);
        if (m.matches()) {
            return m.groups();
        } else {
            return null;
        }
    }

    @Override
    protected String translate(int pattnum) {
        if (pattnum == 6) {
            return "\\[(\\d{4}[\\/-]\\d{2}[\\/-]\\d{2}[\\-\\s]\\d{2}:\\d{2}:\\d{2}([\\.,]\\d+)?)\\]\\s+\\[\\s?(TRACE|DEBUG|PERF|NOTE|INFO|WARN|ERROR|FATAL)\\s?\\]\\s+\\[(([\\\"\\w\\d\\.\\,\\-_\\@\\s\\/\\:\\#\\\\\\=\\{\\}\\&\\+\\%\\)\\(]*)((\\.\\.\\.\\[).*(ing\\]))?)\\]\\s+\\[([\\w\\d\\.\\-_]*)\\]\\s(.*)";
        } else {
            return super.translate(pattnum);
        }
    }

}
