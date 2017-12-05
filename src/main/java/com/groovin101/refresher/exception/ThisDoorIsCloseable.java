package com.groovin101.refresher.exception;

import java.io.Closeable;
import java.io.IOException;

/**
 *
 */
public class ThisDoorIsCloseable implements Closeable {

    public void open() throws Exception {
        System.out.println("[Door opens] Close da doh!");
    }

    @Override
    public void close() throws IOException {
        System.out.println("SLAM!");
    }
}
