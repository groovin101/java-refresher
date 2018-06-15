package com.groovin101.refresher.stream;

import com.groovin101.refresher.model.Person;
import com.groovin101.refresher.model.Vehicle;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

public class StreamTest {

    //Demonstrates a bit of streams, but also lambda and method reference
    @Test
    public void forEach() {
        Vehicle.vehicles().stream().forEach(v -> v.move());

        System.out.println(" ----------------- ...and now using method reference ----------------- ");
        Vehicle.vehicles().stream()
                .forEach(Vehicle::move);
    }

    @Test
    public void mapFilter_changeStreamOfPersonToStreamOfInteger_50AndOlder() {
        List<Person> people = Person.reservoirDogs();
        people.stream()
                .map(person -> person.getAge())
                .filter(age -> age > 50)
                .forEach(a -> {
                    assertTrue(a.equals(68) || a.equals(60) || a.equals(56));
                    System.out.println(a);
                });
    }

    @Test
    public void mapFilter_operateOnPersonStream_50AndOlder() {
        List<Person> people = Person.reservoirDogs();
        people.stream()
                .filter(person -> person.getAge() > 50)
                .forEach(p -> {
                    assertTrue(p.getAge().equals(68) || p.getAge().equals(60) || p.getAge().equals(56));
                    System.out.println(p);
                });
    }

    //creating streams....

    @Test
    public void generate() {
        Stream.generate(() -> " return value from a Supplier functional interface here " + Calendar.getInstance().getTimeInMillis())
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    public void iterate() {
        Stream.iterate(4, num -> num+2)
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    public void randomValues() {
        IntStream intstream = ThreadLocalRandom.current().ints();      //also see DoubleStream and LongStream
        intstream.limit(5).forEach(System.out::println);
    }
}