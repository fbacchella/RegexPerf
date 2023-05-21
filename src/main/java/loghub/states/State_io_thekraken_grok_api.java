package loghub.states;

import java.util.Map;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import io.thekraken.grok.api.Grok;
import io.thekraken.grok.api.Match;
import io.thekraken.grok.api.exception.GrokException;
import loghub.Runner;

@State(Scope.Benchmark)
public class State_io_thekraken_grok_api extends Runner<Grok> {

    @Override
    protected Grok[] getPatternStorage(int size) {
        return new Grok[size];
    }

    @Override
    protected Grok generate(String i) throws GrokException {
        Grok grok = new Grok();
        grok.addPattern("TIME", "\\d{4}[\\/-]\\d{2}[\\/-]\\d{2}[\\-\\s]\\d{2}:\\d{2}:\\d{2}([\\.,]\\d+)?");
        grok.addPattern("SEVERITY", "(?i)TRACE|DEBUG|PERF|NOTE|INFO|WARN|ERROR|FATAL");
        grok.addPattern("THREAD", "([\\\"\\w\\d\\.\\,\\-_\\@\\s\\/\\:\\#\\\\\\=\\{\\}\\&\\+\\%\\)\\(]*)((\\.\\.\\.\\[).*(ing\\]))?");
        grok.addPattern("LOGGER", "[\\w\\d\\.\\-_]*");
        grok.addPattern("MESSAGE", ".*");
        grok.compile(i, false);
        return grok;
    }

    @Override
    protected boolean match(Grok pattern, String searched) {
        Match gm = pattern.match(searched);
        gm.captures();
        return gm.getMatch() != null;
    }

    @Override
    protected String[] find(Grok pattern, String searched) {
        Match gm = pattern.match(searched);
        gm.captures();
        Map<String, Object> content = gm.toMap();
        return content.values().stream().map(Object::toString).toArray(String[]::new);
    }

    @Override
    protected String translate(int pattnum) {
        if (pattnum == 6) {
            return "\\[%{TIME:time}\\]\\s+\\[\\s?%{SEVERITY:severity}\\s?\\]\\s+\\[%{THREAD:thread}\\]\\s+\\[%{LOGGER:logger}\\]\\s%{MESSAGE:message}";
        } else {
            return super.translate(pattnum);
        }
    }

}
