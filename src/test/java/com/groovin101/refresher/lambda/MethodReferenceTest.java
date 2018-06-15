package com.groovin101.refresher.lambda;

import com.groovin101.refresher.model.Person;
import org.junit.Test;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

public class MethodReferenceTest {

    @Test
    public void callMethod_onLambdaParameterClassItself() {

        Function<Person, Integer> f;

        f = person -> person.getAge();
        //becomes
        f = Person::getAge;
    }

    @Test
    public void callMethod_onOtherClassWhoseMethodTakesSameArgumentAsLambda() {

        Consumer<String> printer;

        printer = string -> System.out.println(string);
        //becomes
        printer = System.out::println;
    }

    @Test
    public void callMethod_onOtherClassWhoseMethodTakesSameArgumentAsLambda_multipleArgs() {

        //Remember that a BinaryOperator<Integer> is the same as a BiFunction<Integer,Integer,Integer>
        BinaryOperator<Integer> intAdder;

        intAdder = (int1,int2) -> Integer.sum(int1, int2);
        //becomes
        intAdder = Integer::sum;
    }
}