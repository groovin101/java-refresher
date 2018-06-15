package com.groovin101.refresher.lambda;

import com.groovin101.refresher.model.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class ComparatorTest {

    private List<Person> people;

    @Before
    public void init() {
        people = new ArrayList<>(Arrays.asList(
                new Person("Ravyn", 41),
                new Person("River", 39),
                new Person("Starfire", 34),
                new Person("Bart", 71),
                new Person("Jaime", 70)));
    }

    @Test
    public void sort_oldSchool_anonClass() {

        people.sort(
                new Comparator<Person>() {
                    @Override
                    public int compare(Person o1, Person o2) {

                        return Integer.compare(o1.getAge(), o2.getAge());
                    }
                });

        assertEquals("Starfire", people.get(0).getFirstName());

        people.forEach(p -> System.out.println(p));
    }

    @Test
    public void sort_lambda() {

        //Think of the Comparator as a BiFunction<T,T,Integer> whose abstract method |   int compare(T t, T t2)   | must be implemented
        people.sort((p1, p2) -> p1.getAge().compareTo(p2.getAge()));

        assertEquals("Starfire", people.get(0).getFirstName());

        people.forEach(System.out::println);
    }

    //Comparator.comparing() takes a Function<T,U> and returns a Comparator
    //Comparator<T> comparator = Comparator.comparing(Function<T,U> fnToReturnComparable);
    //Note that we are using Integer as our Comparable, but could as easily use a Function<Person,String> because String is also Comparable
    @Test
    public void comparing_v1_explicit() {

        Function<Person, Integer> fnToReturnAComparable = (person -> {
            Integer comparable = person.getAge();
            return comparable;
        });
        people.sort(Comparator.comparing(fnToReturnAComparable));
        assertEquals("Starfire", people.get(0).getFirstName());
    }

    //same as v1 but less verbose
    @Test
    public void comparing_v2_succinct() {

        people.sort(Comparator.comparing(person -> person.getAge()));
        assertEquals("Starfire", people.get(0).getFirstName());
    }

    //same as v2 but w/ method reference
    @Test
    public void comparing_v3_withMethodReference() {

        people.sort(Comparator.comparing(Person::getAge));
        assertEquals("Starfire", people.get(0).getFirstName());
    }

    @Test
    public void comparing_chainedCalls() {

        List<Person> turtlePeople = Arrays.asList(new Person("Turtle B", 71),
                new Person("Turtle C", 70),
                new Person("Turtle A", 70));

        turtlePeople.sort(Comparator.comparing(Person::getAge)
                .thenComparing(Person::getFirstName));

        assertEquals("Turtle A", turtlePeople.get(0).getFirstName());
    }
}