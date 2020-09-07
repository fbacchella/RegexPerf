package loghub.states;

import org.jcodings.specific.UTF16LEEncoding;
import org.joni.Matcher;
import org.joni.Option;
import org.joni.Regex;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import loghub.Runner;
import loghub.UnsafeUtils;

@State(Scope.Benchmark)
public class State_org_joni_utf16le extends Runner<org.joni.Regex> {

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

}
