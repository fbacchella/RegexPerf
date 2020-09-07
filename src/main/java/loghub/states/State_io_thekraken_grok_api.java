package loghub.states;

import java.util.Map;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import io.thekraken.grok.api.Grok;
import io.thekraken.grok.api.Match;
import io.thekraken.grok.api.exception.GrokException;
import loghub.Runner;

@State(Scope.Benchmark)
public class State_io_thekraken_grok_api extends Runner<io.thekraken.grok.api.Grok> {

    @Override
    protected Grok[] getPatternStorage(int size) {
        return new Grok[size];
    }

    @Override
    protected Grok generate(String i) {
        try {
            io.thekraken.grok.api.Grok grok = new io.thekraken.grok.api.Grok();
            grok.compile(i, false);
            return grok;
        } catch (GrokException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean match(Grok pattern, String searched) {
        Match gm = pattern.match(searched);
        gm.captures();
        @SuppressWarnings("unused")
        Map<String, Object> content = gm.toMap();
        return gm.getMatch() != null;
    }

}
