package com.groovin101.refresher;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void riverTest() {
        Integer i = 0;

        while (i < 1000) {
            if (i%3==0) {
                int totalFromEachNum = 0;
                for (int charIndex=0;charIndex<i.toString().length();charIndex++) {
                    totalFromEachNum += new Integer(i.toString().substring(charIndex,charIndex+1));
                }
                System.out.println("["+i+"]\t\t"+totalFromEachNum);
            }
            i++;
        }
    }
}
