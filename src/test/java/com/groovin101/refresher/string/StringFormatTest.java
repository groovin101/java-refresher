package com.groovin101.refresher.string;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class StringFormatTest {

    @Before
    public void setup() {

    }

    @Test
    public void format_commonConversions() {
        assertEquals("double-integral", "result is: 19", String.format("result is: %d", 19));
        assertEquals("octal", "result is: 23", String.format("result is: %o", 19));
        assertEquals("hex", "result is: 13", String.format("result is: %x", 19));
        assertEquals("hex", "result is: 13", String.format("result is: %X", 19));
        assertEquals("octal", "result is: 23", String.format("result is: %o", 19));
        assertEquals("floating-point", "result is: 19.000000", String.format("result is: %f", 19.0));
        assertEquals("scientific-notation", "result is: 1.900000e+01", String.format("result is: %e", 19.0));
        assertEquals("scientific-notation", "result is: 1.234000E+03", String.format("result is: %E", 1234.0));
        assertEquals("String", "result is: unchanged", String.format("result is: %s", "unchanged"));
    }

    @Test
    public void format_customFormattableImplementation() {
        FormattableObject customFormattable = new FormattableObject();
        customFormattable.setProp1("shizzle");
        assertEquals("custom formatter no workum", "result is: [4shizzle]", String.format("result is: %s", customFormattable));
    }

    @Test
    public void format_plainOldJavaObject() {
        NonFormattableObject regularNonFormattableObject = new NonFormattableObject();
        assertEquals("result is: output-from-to-string-here", String.format("result is: %s", regularNonFormattableObject));
    }
}
