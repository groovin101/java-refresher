package com.groovin101.refresher.general;

import org.junit.Test;

public class RecursionTest {

    @Test
    public void squareRoot() {
        printSquareRoot(20);
    }

    private void printSquareRoot(int n) {
        int square = n*n;
        System.out.println(String.format("%d * %d = %d", n, n, square));
        if (n > 0) {
            printSquareRoot(n-1);
        }
    }

    @Test
    public void factorial() {
        printFactorial(1,20);
    }

    private void printFactorial(long identity, long n) {
        long factorial = identity * n;
        System.out.println(String.format("%,d * %,d = %,d", identity, n, factorial));
        if (n >= 2) {
            printFactorial(factorial,n-1);
        }
    }
}
