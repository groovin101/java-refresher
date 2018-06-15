package com.groovin101.refresher.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LambdaTest {

    @Test
    public void passCodeAsArgUsingLambdaExpression() {

        String[] longAndShortNames = new String[] {"123456789", "123", "12345", "12345678"};

        //OLD SCHOOL WAY
        //        Arrays.sort(longAndShortNames, new Comparator<String>() {
        //            @Override
        //            public int compare(String o1, String o2) {
        //                return Integer.compare(o1.length(), o2.length());
        //            }
        //        });

        Arrays.sort(longAndShortNames, (o1, o2) -> Integer.compare(o1.length(), o2.length()));
        assertEquals("123", longAndShortNames[0]);
        assertEquals("123456789", longAndShortNames[3]);

        Arrays.sort(longAndShortNames, (o1, o2) -> -1);
        assertNotEquals("123", longAndShortNames[0]);
    }

    @Test
    public void customFunctionalInterface() {

        FnInterface f = (t) -> String.format("This is the method implemented by our lambda. Arg type: %s and value: %s", t.getClass().getCanonicalName(), t);

        System.out.println(f.singleAbstractMethod(new Integer(7)));
        System.out.println(f.singleAbstractMethod("Yo"));
    }

    @Test
    public void lambdaWithTheFunctionInterface() {
        Map<String,Integer> mapWithVal = new HashMap<>();
        String EXPECTED_KEY = "iexpectthiskey";

        //without method reference
        Function<Map<String,Integer>,Integer> lookerUpper = t -> t.get(EXPECTED_KEY);

        mapWithVal.put(EXPECTED_KEY, 99);
        assertEquals("Should have found a value of 99", new Integer(99), lookerUpper.apply(mapWithVal));
    }

    @Test
    public void lambdaUsingMethodReference() {

        //without method reference
        BinaryOperator<Integer> fnInterface = (i1, i2) -> i1 + i2;

        //WITH method reference
        BinaryOperator<Integer> fnInterfaceThatSumsNumbers = Integer::sum;

        assertEquals(new Integer(5), fnInterface.apply(2,3));
        assertEquals(new Integer(6), fnInterfaceThatSumsNumbers.apply(2,4));
    }

    @Test
    public void lambdaUsingMethodReference_noReturnObject() {

        //without method reference
        Consumer printer = s -> System.out.println(s);
        printer.accept("printme");

        //WITH method reference
        printer = System.out::println;
        printer.accept("printme w/ a method reference");
    }

    //This FnInterface duplicates what is provided by the built-in Function FunctionalInterface<T,R> ie FunctionalInterface<T,String>
    @FunctionalInterface
    interface FnInterface<T> {

        String singleAbstractMethod(T t);
    }
}
