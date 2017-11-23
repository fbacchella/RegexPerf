package loghub;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

public class StringToBytesTest {
    
    private final StringToBytes bytters = new StringToBytes();

    @Test
    public void byName_UTF_16() throws UnsupportedEncodingException {
        Assert.assertNotNull(bytters.byName_UTF_16()); 
    }

    @Test
    public void byCharset_UTF_16() throws UnsupportedEncodingException {
        Assert.assertNotNull(bytters.byCharset_UTF_16()); 
    }

    @Test
    public void byName_UTF_8() throws UnsupportedEncodingException {
        Assert.assertNotNull(bytters.byName_UTF_8()); 
    }

    @Test
    public void byCharset_UTF_8() throws UnsupportedEncodingException {
        Assert.assertNotNull(bytters.byCharset_UTF_8()); 
    }

    @Test
    public void byName_ISO_8859_1() throws UnsupportedEncodingException {
        Assert.assertNotNull(bytters.byName_ISO_8859_1()); 
    }

    @Test
    public void byCharset_ISO_8859_1() throws UnsupportedEncodingException {
        Assert.assertNotNull(bytters.byCharset_ISO_8859_1()); 
    }

    @Test
    public void getBytesUTF16LE() throws UnsupportedEncodingException {
        Assert.assertNotNull(bytters.getBytesUTF16LE()); 
    }

    @Test
    public void getBytesAscii() throws UnsupportedEncodingException {
        Assert.assertNotNull(bytters.getBytesAscii()); 
    }

}
