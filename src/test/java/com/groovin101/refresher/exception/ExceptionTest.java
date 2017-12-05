package com.groovin101.refresher.exception;

import org.junit.Test;

import java.io.IOException;

/**
 *
 */
public class ExceptionTest {

    @Test
    public void closeWithResources_Exception() {
        try (ThisDoorIsCloseable closeableResource = new ThisDoorIsCloseable()) {
            closeableResource.open();
        }
        catch (Exception e) {
            printExceptionMessage(e);
        }
        //no finally needed - close is called automatically
    }

    @Test
    public void closeWithResources_closeCausesException() {

        try (ThisDoorBroke closeableResource = new ThisDoorBroke()) {
            closeableResource.open();
        }
        catch (Exception e) {
            printExceptionMessage(e);
        }
    }

    @Test
    public void closeWithResources_multipleExceptions_oneIsSuppressed() {

        try (ThisDoorBroke closeableResource = new ThisDoorBroke();
             ThisDoorBroke closeableResource2 = new ThisDoorBroke()) {
            closeableResource.open();
            closeableResource2.open();
        }
        catch (Exception e) {
            System.out.println("Caught " + e.getClass().getName() + e.getMessage());
            for (Throwable t : e.getSuppressed()) {
                System.out.println("Suppressed " + t.getClass().getName() + t.getMessage());
            }
        }
    }

    @Test
    public void clunkyTryCatch() {
        ThisDoorIsCloseable closeableResource = null;
        try {
            closeableResource = new ThisDoorIsCloseable();
            closeableResource.open();
        }
        catch (Exception e) {
            printExceptionMessage(e);
        }
        finally {
            try {
                closeableResource.close();
            }
            catch (IOException e) {
                System.out.println("Problem trying to close Reader: " + e.getClass().getName() + " - " + e.getMessage());
            }
        }
    }

    private void printExceptionMessage(Exception e) {
        System.out.println("Ow! Stubbed my toe handling this exception! \n" + e.getClass().getName() + ": " + e.getMessage());
    }
}
