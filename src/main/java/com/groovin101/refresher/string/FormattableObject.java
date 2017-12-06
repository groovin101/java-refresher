package com.groovin101.refresher.string;

import java.util.Formattable;
import java.util.Formatter;

/**
 *
 */
public class FormattableObject implements Formattable {

    private String prop1;
    public void setProp1(String prop1) {
        this.prop1 = prop1;
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("[4"+prop1+"]");
    }
}
