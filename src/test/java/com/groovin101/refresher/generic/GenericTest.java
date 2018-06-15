package com.groovin101.refresher.generic;

import org.junit.Test;

import java.util.ArrayList;

public class GenericTest {

    @Test
    public void pushAll_invariantTypeAsInputParamIsNotFlexible() {
        Stackk<Number> stackOfNumbers = new Stackk<>();
        stackOfNumbers.pushAllDeficient(new ArrayList<Number>());      // works of course, but the Type Argument matches exactly
     // stackOfStrings.pushAllDeficient(new ArrayList<Integer>());   // <-- WILL NOT WORK (even though Integer is a Number)
    }

    @Test
    public void pushAll_wildcardTypeAsInputParameterIsFlexible() {
        Stackk<Number> stackOfNumbers = new Stackk<>();
        stackOfNumbers.pushAll(new ArrayList<Number>());
        stackOfNumbers.pushAll(new ArrayList<Integer>());
    }

    // --------------

    @Test
    public void popItems_invariantTypeCannotAcceptCompatibleObjects() {
        Stackk<Number> stackOfNumbers = new Stackk<>();
        stackOfNumbers.popAllDeficient(new ArrayList<Number>());       // works because the Type Arguments match
        //stackOfNumbers.popAllDeficient(new ArrayList<Object>());       // <-- even though Number is a subclass of Object, will not work because a List<Object> is not a List<Number>
    }

    @Test
    public void popItems_wildcardAsInputParameterAllowsSupertypeToBeInsertedIntoConsumingList() {
        Stackk<Number> stackOfNumbers = new Stackk<>();
        stackOfNumbers.popAll(new ArrayList<Number>());
        stackOfNumbers.popAll(new ArrayList<Object>());       // works because Object is a superclass of Number, so our wildcard <? super Number> allows us to add Numbers to our list of Objects
    }
}