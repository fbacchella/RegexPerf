package loghub;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;

import org.openjdk.jmh.annotations.Benchmark;

public class StringToBytes {

    private static final String DEFAULT_ENCODING_UTF_8 = "UTF-8";
    private static final Charset CHARSET_DEFAULT_UTF_8 = Charset.forName(DEFAULT_ENCODING_UTF_8);
    private static final String DEFAULT_ENCODING_US_ASCII = "US-ASCII";
    private static final Charset CHARSET_DEFAULT_US_ASCII = Charset.forName(DEFAULT_ENCODING_US_ASCII);
    private static final String DEFAULT_ENCODING_UTF_16 = "UTF-16LE";
    private static final Charset CHARSET_DEFAULT_UTF_16 = Charset.forName(DEFAULT_ENCODING_UTF_16);
    private static final String STR = StringToBytes.class.toGenericString();

    @Benchmark
    public byte[] getBytesUTF16LE() {
        final int length = STR.length();
        final char buffer[] = new char[length];
        STR.getChars(0, length, buffer, 0);
        final byte b[] = new byte[length*2];
        for (int j = 0; j < length; j++) {
            b[j*2] = (byte) (buffer[j] & 0xFF);
            b[j*2+1] = (byte) (buffer[j] >> 8);
        }
        return b;
    }

    @Benchmark
    public byte[] getBytesAscii() {
        int length = STR.length();
        char buffer[] = new char[length];
        STR.getChars(0, length, buffer, 0);
        byte b[] = new byte[length];
        for (int j = 0; j < length; j++) {
            b[j] = (byte) (buffer[j] & 0x7F);
        }
        return b;
    }

    static final ThreadLocal<CharsetEncoder> asciiencode = ThreadLocal.withInitial( () -> {
        CharsetEncoder encoder = Charset.forName("US-ASCII").newEncoder();
        return encoder.onUnmappableCharacter(CodingErrorAction.REPORT);
    });

    static final ThreadLocal<CharBuffer> charbuffergenerator = ThreadLocal.withInitial( () -> CharBuffer.allocate(STR.length()));

    @Benchmark
    public byte[] getBytesCharsetEncoder() {
        try {
            CharsetEncoder encoder = asciiencode.get();
            CharBuffer buffer  = charbuffergenerator.get();
            buffer.clear();
            buffer.append(STR);
            buffer.flip();
            ByteBuffer outbuffer = encoder.encode(buffer);
            return outbuffer.array();
        } catch (CharacterCodingException e) {
            throw new RuntimeException(e);
        }
    }

    static final ThreadLocal<char[]> holder = ThreadLocal.withInitial(() -> new char[STR.length()]);
    @Benchmark
    public byte[] getBytesAsciiReuse() {
        int length = STR.length();
        char[] buffer = holder.get();
        STR.getChars(0, length, buffer, 0);
        byte b[] = new byte[length];
        for (int j = 0; j < length; j++) {
            b[j] = (byte) (buffer[j] & 0x7F);
        }
        return b;
    }

    @Benchmark
    public byte[] getBytesAsciiUnsafe() {
        final int length = STR.length();
        final char buffer[] = UnsafeUtils.toCharArray(STR);
        final byte b[] = new byte[length];
        for (int j = 0; j < length; j++) {
            b[j] = (byte) (buffer[j] & 0x7F);
        }
        return b;
    }

    @Benchmark
    public byte[] byName_UTF_16() throws UnsupportedEncodingException {
        return STR.getBytes(DEFAULT_ENCODING_UTF_16);
    }

    @Benchmark
    public byte[] byCharset_UTF_16() throws UnsupportedEncodingException {
        return STR.getBytes(CHARSET_DEFAULT_UTF_16);
    }

    @Benchmark
    public byte[] byName_UTF_8() throws UnsupportedEncodingException {
        return STR.getBytes(DEFAULT_ENCODING_UTF_8);
    }

    @Benchmark
    public byte[] byCharset_UTF_8() throws UnsupportedEncodingException {
        return STR.getBytes(CHARSET_DEFAULT_UTF_8);
    }

    @Benchmark
    public byte[] byName_US_ASCII() throws UnsupportedEncodingException {
        return STR.getBytes(DEFAULT_ENCODING_US_ASCII);
    }

    @Benchmark
    public byte[] byCharset_US_ASCII() throws UnsupportedEncodingException {
        return STR.getBytes(CHARSET_DEFAULT_US_ASCII);
    }

}
