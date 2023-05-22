package loghub.states;

import java.nio.charset.StandardCharsets;

import org.jcodings.specific.ASCIIEncoding;
import org.joni.Matcher;
import org.joni.Option;
import org.joni.Regex;
import org.joni.Region;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import loghub.Runner;

@State(Scope.Benchmark)
public class State_org_joni_ascii extends Runner<org.joni.Regex> {

    @Override
    protected Regex[] getPatternStorage(int size) {
        return new Regex[size];
    }

    private static byte[] getBytesAscii(String searched) {
        int length = searched.length();
        char[] buffer = new char[length];
        searched.getChars(0, length, buffer, 0);
        byte[] b = new byte[length];
        for (int j = 0; j < length; j++) {
            b[j] = (byte) (buffer[j] & 0x7F);
        }
        return b;
    }

    @Override
    protected Regex generate(String i) {
        byte[] pattern = getBytesAscii(i);
        return new Regex(pattern, 0, pattern.length, Option.NONE, ASCIIEncoding.INSTANCE);
    }

    @Override
    protected boolean match(Regex pattern, String searched) {
        byte[] str = getBytesAscii(searched);
        Matcher matcher = pattern.matcher(str);
        return matcher.match(0, str.length, Option.DEFAULT) != -1;
    }

    @Override
    protected boolean find(Regex pattern, String searched) {
        byte[] str = getBytesAscii(searched);
        Matcher matcher = pattern.matcher(str);
        return matcher.search(0, str.length, Option.DEFAULT) != -1;
    }

    @Override
    protected String[] matchGroup(Regex pattern, String searched) {
        byte[] str = getBytesAscii(searched);
        Matcher matcher = pattern.matcher(str);
        if (matcher.match(0, str.length, Option.DEFAULT) != -1) {
            Region region = matcher.getEagerRegion();
            String[] found = new String[region.getNumRegs()];
            for (int i = 0 ; i < region.getNumRegs() ; i++) {
                int begin = region.getBeg(i);
                int end = region.getEnd(i);
                if (begin != -1 && end != -1) {
                    found[i] = new String(str, begin, end - begin, StandardCharsets.US_ASCII);
                }
            }
            return found;
        } else {
            return null;
        }
    }

}
