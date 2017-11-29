package loghub;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

public class StringToBytesTest {

    private final StringToBytes bytters = new StringToBytes();

    @Test
    public void getBytesUTF16LE() throws UnsupportedEncodingException {
        Assert.assertEquals(66, bytters.getBytesUTF16LE().length); 
    }

    @Test
    public void byName_UTF_16() throws UnsupportedEncodingException {
        Assert.assertEquals(66, bytters.byName_UTF_16().length); 
    }

    @Test
    public void byCharset_UTF_16() throws UnsupportedEncodingException {
        Assert.assertEquals(66, bytters.byCharset_UTF_16().length); 
    }

    @Test
    public void byName_UTF_8() throws UnsupportedEncodingException {
        Assert.assertEquals(33, bytters.byName_UTF_8().length);
    }

    @Test
    public void byCharset_UTF_8() throws UnsupportedEncodingException {
        Assert.assertEquals(33, bytters.byCharset_UTF_8().length);
    }

    @Test
    public void byName_ISO_8859_1() throws UnsupportedEncodingException {
        Assert.assertEquals(33, bytters.byName_US_ASCII().length); 
    }

    @Test
    public void byCharset_ISO_8859_1() throws UnsupportedEncodingException {
        Assert.assertNotNull(bytters.byCharset_US_ASCII().length); 
    }

    @Test
    public void getBytesAscii() throws UnsupportedEncodingException {
        Assert.assertEquals(33, bytters.getBytesAscii().length); 
    }

    @Test
    public void getBytesAsciiReuse() throws UnsupportedEncodingException {
        Assert.assertEquals(33, bytters.getBytesAsciiReuse().length); 
    }

    @Test
    public void getBytesAsciiUnsafe() throws UnsupportedEncodingException {
        Assert.assertEquals(33, bytters.getBytesAsciiUnsafe().length);
    }

    @Test
    public void getBytesCharsetEncoder() throws UnsupportedEncodingException {
        Assert.assertEquals(33, bytters.byCharsetEncoder_US_ASCII().length);
    }

}
