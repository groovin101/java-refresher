package com.groovin101.refresher.string;

import org.junit.Before;
import org.junit.Test;

import java.util.StringJoiner;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class StringJoinerTest {

    @Before
    public void setup() {

    }
    @Test
    public void x() {
        int i = 10;
        int j = 10;
        int before = ++i;
        System.out.println("before = " + before + " and orig = " + i);
        int after = j++;
        System.out.println("after = " + after + " and orig = " + j);
    }

    @Test
    public void delimiter() {
        StringJoiner stringJoiner = new StringJoiner(", x, ");
        stringJoiner.add("one").add("two").add("three");
        assertEquals("one, x, two, x, three", stringJoiner.toString());
    }

    @Test
    public void delimiter_noContents() {
        StringJoiner stringJoiner = new StringJoiner(", x, ");
        assertEquals("", stringJoiner.toString());
    }

    @Test
    public void bookends() {
        StringJoiner stringJoiner = new StringJoiner(", x, ", "-->", "<--");
        stringJoiner.add("one").add("two").add("three");
        assertEquals("-->one, x, two, x, three<--", stringJoiner.toString());
    }

    @Test
    public void bookends_noContents() {
        StringJoiner stringJoiner = new StringJoiner(", x, ", "-->", "<--");
        assertEquals("--><--", stringJoiner.toString());
    }

    @Test
    public void bookends_noContents_override() {
        StringJoiner stringJoiner = new StringJoiner(", x, ", "-->", "<--");
        stringJoiner.setEmptyValue("nothing-in-here-so-displaying-this-instead");
        assertEquals("nothing-in-here-so-displaying-this-instead", stringJoiner.toString());
    }

    @Test
    public void bookends_noContents_override_addCalled() {
        StringJoiner stringJoiner = new StringJoiner(", x, ", "[", "]");
        stringJoiner.setEmptyValue("if-add-is-called-this-override-will-not-apply");
        stringJoiner.add("");
        assertEquals("[]", stringJoiner.toString());
    }

}
