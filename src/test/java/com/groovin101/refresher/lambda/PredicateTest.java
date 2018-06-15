package com.groovin101.refresher.lambda;

import org.junit.Before;
import org.junit.Test;

import java.util.function.Predicate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PredicateTest {

    public PredicateTest() {
        super();
    }

    private Predicate<String> predicateToTestIfStringIsLongEnough;

    @Before
    public void init() {
        predicateToTestIfStringIsLongEnough = s -> s.length() > 5;
    }

    @Test
    public void simpleLambdaImpl() {
        assertTrue(predicateToTestIfStringIsLongEnough.test("long-flippin-string"));
        assertFalse(predicateToTestIfStringIsLongEnough.test("nope"));
    }

    @Test
    public void chainingCallsToEnsureObjectMeetsMoreThanOneBooleanCondition() {
        Predicate<String> stringStartsWithZ = s -> s.startsWith("z");
        assertTrue(predicateToTestIfStringIsLongEnough.and(
                stringStartsWithZ).test("zoooooooo"));
        assertFalse(predicateToTestIfStringIsLongEnough.and(
                stringStartsWithZ).test("boooooooo"));
    }

    @Test
    public void chainingCallsAlternateSyntax() {
        assertTrue(((Predicate<String>)(s -> s.length() > 5)).and(s -> s.startsWith("z")).test("zoooooooo"));
        assertFalse(((Predicate<String>)(s -> s.length() > 5)).and(s -> s.startsWith("z")).test("boooooooo"));
    }
}