package com.groovin101.refresher.general;

import org.junit.Test;

import java.util.Objects;

public class ObjectsTest {

    @Test(expected = NullPointerException.class)
    public void requireNonNull_nullThrowsException() {
        Objects.requireNonNull(null,"this is gonna throw an NPE");
    }
}
