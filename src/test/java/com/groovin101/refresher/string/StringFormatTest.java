package com.groovin101.refresher.string;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @See https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html for details on format options
 */
public class StringFormatTest {

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

    @Test
    public void formatFlag_displayRadix() {
        assertEquals("0x13", String.format("%#x", 19));
        assertEquals("023", String.format("%#o", 19)); //octal radix uses a prefix of zero
    }

    @Test
    public void formatFlag_padWithSpaces_rightJustify() {
        //these don't line up
        assertEquals("first: 12 and second: 123", String.format("first: %d and second: %d", 12, 123));
        assertEquals("first: 1 and second: 12", String.format("first: %d and second: %d", 1, 12));
        //these do
        assertEquals("first:   12 and second:  123", String.format("first: %4d and second: %4d", 12, 123));
        assertEquals("first:    1 and second:   12", String.format("first: %4d and second: %4d", 1, 12));
    }

    @Test
    public void formatFlag_padWithSpaces_leftJustify() {
        //these don't line up
        assertEquals("first:12 and second:123", String.format("first:%d and second:%d", 12, 123));
        assertEquals("first:1 and second:12", String.format("first:%d and second:%d", 1, 12));
        //these do
        assertEquals("first:12   and second:123 ", String.format("first:%-4d and second:%-4d", 12, 123));
        assertEquals("first:1    and second:12  ", String.format("first:%-4d and second:%-4d", 1, 12));
    }

    @Test
    public void formatFlag_padWithZeroes() {
        assertEquals("first: 0012 and second: 0123", String.format("first: %04d and second: %04d", 12, 123));
        assertEquals("first: 0001 and second: 0012", String.format("first: %04d and second: %04d", 1, 12));
    }

    @Test
    public void formatFlag_addNumericGroupingSeparator() {
        assertEquals("1,234,567", String.format("%,d", 1234567));
    }

    @Test
    public void formatFlag_addPrecisionToFloat() {
        assertEquals("1,234,567.00000", String.format("%,.5f", 1234567.0));
    }

    @Test
    public void formatFlag_lineUpNegativeAndPositiveValues() {
        //we want to line these up
        assertEquals("123", String.format("%d", 123));
        assertEquals("-456", String.format("%d", -456));
        //line 'em up
        assertEquals(" 123", String.format("% d", 123));
        assertEquals("-456", String.format("% d", -456));
    }

    @Test
    public void formatFlag_addPositiveAndNegativeMarkers() {
        assertEquals("+123", String.format("%+d", 123));
        assertEquals("-456", String.format("%+d", -456));
    }

    @Test
    public void formatFlag_addParensToIndicateNegativeValues() {
        assertEquals("123", String.format("%(d", 123));
        assertEquals("(456)", String.format("%(d", -456));

// still add parens, but also sub a space where the positive indicator (+) would be
//        assertEquals(" 123", String.format("% (d", 123));
//        assertEquals("(456)", String.format("% (d", -456));
//        System.out.println(String.format("% (d", 123));
//        System.out.println(String.format("% (d", -123));
//        System.out.println(String.format("% (d", 1234));
//        System.out.println(String.format("% (d", -1234));
    }

    @Test
    public void format_funkinWithTheArgIndex() {
        assertEquals("out of order", String.format("%3$s %2$s %1$s", "order", "of", "out"));
    }
}
