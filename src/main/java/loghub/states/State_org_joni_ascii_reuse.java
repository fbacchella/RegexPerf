package loghub.states;

import org.jcodings.specific.ASCIIEncoding;
import org.joni.Matcher;
import org.joni.Option;
import org.joni.Regex;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import loghub.Runner;

@State(Scope.Benchmark)
public class State_org_joni_ascii_reuse extends Runner<org.joni.Regex> {

    static final ThreadLocal<char[]> holder = ThreadLocal.withInitial(() -> new char[4096]);
    private static byte[] getBytesAscii(String searched) {
        int length = searched.length();
        char[] buffer = holder.get();
        searched.getChars(0, length, buffer, 0);
        byte b[] = new byte[length];
        for (int j = 0; j < length; j++) {
            b[j] = (byte) (buffer[j] & 0x7F);
        }
        return b;
    }

    @Override
    protected Regex[] getPatternStorage(int size) {
        return new Regex[size];
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
        return matcher.search(0, str.length, Option.DEFAULT) != -1;
    }

}
