package loghub.states;

import java.nio.charset.StandardCharsets;

import org.jcodings.specific.UTF16LEEncoding;
import org.joni.Matcher;
import org.joni.Option;
import org.joni.Regex;
import org.joni.Region;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import loghub.Runner;
import loghub.UnsafeUtils;

@State(Scope.Benchmark)
public class State_org_joni_utf16le extends Runner<org.joni.Regex> {

    @Override
    protected Regex[] getPatternStorage(int size) {
        return new Regex[size];
    }

    private static byte[] getBytesUTF16LE(String searched) {
        int length = searched.length();
        char buffer[] = UnsafeUtils.toCharArray(searched);
        byte b[] = new byte[length * 2];
        for (int j = 0; j < length; j++) {
            b[j*2] = (byte) (buffer[j] & 0xFF);
            b[j*2+1] = (byte) (buffer[j] >> 8);
        }
        return b;
    }


    @Override
    protected Regex generate(String i) {
        byte[] pattern = getBytesUTF16LE(i);
        return new Regex(pattern, 0, pattern.length, Option.NONE, UTF16LEEncoding.INSTANCE);
    }

    @Override
    protected boolean match(Regex pattern, String searched) {
        byte[] str = getBytesUTF16LE(searched);
        Matcher matcher = pattern.matcher(str);
        return matcher.search(0, str.length, Option.DEFAULT) != -1;
    }

    @Override
    protected String[] find(Regex pattern, String searched) {
        byte[] str = getBytesUTF16LE(searched);
        Matcher matcher = pattern.matcher(str);
        if (matcher.search(0, str.length, Option.DEFAULT) != -1) {
            Region region = matcher.getEagerRegion();
            String[] found = new String[region.numRegs];
            for (int i = 0 ; i < region.numRegs ; i++) {
                int begin = region.beg[i];
                int end = region.end[i];
                if (begin != -1 && end != -1) {
                    found[i] = new String(str, begin, end - begin, StandardCharsets.UTF_16LE);
                }
            }
            return found;
        } else {
            return null;
        }
    }

}
