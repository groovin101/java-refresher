package com.groovin101.refresher.exception;

import java.io.IOException;

/**
 *
 */
public class ThisDoorBroke extends ThisDoorIsCloseable {

    @Override
    public void close() throws IOException {
        System.out.println("[Attempts to push door closed]");
        throw new IOException("Sproing! Door won't close.");
    }
}
