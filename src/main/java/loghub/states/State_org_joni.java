package loghub.states;

import java.nio.charset.StandardCharsets;

import org.jcodings.specific.UTF8Encoding;
import org.joni.Matcher;
import org.joni.Option;
import org.joni.Regex;
import org.joni.Region;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import loghub.Runner;

@State(Scope.Benchmark)
public class State_org_joni extends Runner<org.joni.Regex> {

    @Override
    protected Regex[] getPatternStorage(int size) {
        return new Regex[size];
    }

    @Override
    protected Regex generate(String i) {
        byte[] pattern = i.getBytes(StandardCharsets.UTF_8);
        return new Regex(pattern, 0, pattern.length, Option.NONE, UTF8Encoding.INSTANCE);
    }

    @Override
    protected boolean match(Regex pattern, String searched) {
        byte[] str = searched.getBytes(StandardCharsets.UTF_8);
        Matcher matcher = pattern.matcher(str);
        return matcher.search(0, str.length, Option.DEFAULT) != -1;
    }

    @Override
    protected String[] find(Regex pattern, String searched) {
        byte[] str = searched.getBytes(StandardCharsets.UTF_8);
        Matcher matcher = pattern.matcher(str);
        if (matcher.search(0, str.length, Option.DEFAULT) != -1) {
            Region region = matcher.getEagerRegion();
            String[] found = new String[region.numRegs];
            for (int i = 0 ; i < region.numRegs ; i++) {
                int begin = region.beg[i];
                int end = region.end[i];
                if (begin != -1 && end != -1) {
                    found[i] = new String(str, begin, end - begin, StandardCharsets.UTF_8);
                }
            }
            return found;
        } else {
            return null;
        }
    }

}
