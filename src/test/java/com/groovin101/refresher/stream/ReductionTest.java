package com.groovin101.refresher.stream;

import com.groovin101.refresher.model.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import static org.junit.Assert.*;

/**
 * Demonstrate different ways to perform the reduction step
 */
public class ReductionTest {

    @Test
    public void anyMatch() {
        assertTrue("Mr Orange should be present",
                Person.reservoirDogs().stream()
                        .anyMatch(person -> person.getLastName().equals("Orange")));
    }

    @Test
    public void allMatch() {
        assertTrue("Everybody should be less than 192",
                Person.reservoirDogs().stream()
                        .allMatch(p -> p.getAge() < 192));
    }

    @Test
    public void noneMatch() {
        assertTrue("Nobody is named Mr. Black",
                Person.reservoirDogs().stream()
                        .noneMatch(p -> p.getLastName().equals("Black")));
    }

    @Test
    public void reduce() {

        List<Person> people = Person.reservoirDogs();

        int sumOfAges = people.stream()
                .map(person -> person.getAge())
                .reduce(0, (age1, age2) -> age1 + age2);

        //print out the results
        people.stream()
                .map(person -> String.format("Age: %d +", person.getAge()))
                .forEach(System.out::println);
        System.out.println("Total: " + sumOfAges);
    }

    @Test
    public void reduce_startingWithADifferentIdentity() {

        List<Integer> numbers = Arrays.asList(3, 4);

        Integer sumOfAllNumbers = numbers.stream()
                .reduce(11, (int1, int2) -> int1 + int2);

        assertEquals("The identity is the first number in our operation, so should result in 11 extra",
                18, sumOfAllNumbers.intValue());
    }





    //TODO: THIS DOES NOT WORK - NEED TO BETTER UNDERSTAND PARALLEL OPERATIONS
    //BAD @Test
    public void reduce_parallelOperations() {

        List<Person> people = Person.reservoirDogs();

        List<Integer> identity = new ArrayList<Integer>(); //the identity where we will start

        BiFunction<List<Integer>,Person,List<Integer>> accumulator = (list,p) -> {
            System.out.println("accumulator: " + list );
            list.add(p.getAge());
            return list;
        };

        BinaryOperator<List<Integer>> combiner = (listFromOneProcessor,listFromOtherProcessor) -> {
            System.out.println("combiner: " + listFromOneProcessor + " | " + listFromOtherProcessor);
            listFromOneProcessor.addAll(listFromOtherProcessor);
            return listFromOneProcessor;
        };

        List<Integer> ages = people.parallelStream()
                .reduce(identity,
                        accumulator,
                        combiner);
        //ages.forEach(System.out::println);

        //TODO: THIS DOES NOT WORK - NEED TO BETTER UNDERSTAND PARALLEL OPERATIONS
        fail("this is NOT RIGHT!!");
    }
}