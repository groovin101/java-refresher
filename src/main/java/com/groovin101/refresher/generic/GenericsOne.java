package com.groovin101.refresher.generic;

import java.util.ArrayList;
import java.util.List;

//this is bad because we can have a class of type X that implements a compareTo() at a superclass level like Object.compareTo()
//public class GenericsOne<T extends Comparable> {
//make sure to declare the generic to extend Comparable of itself
public class GenericsOne<T extends Comparable<T>> {

    public void displaySortOrder(T one, T two) {

        if (one.compareTo(two) < 1) {
            System.out.println(one);
            System.out.println(two);
        }

    }

    public void arrayOfWildcardType(List<?> wildcardList) {
        List<?>[] x = new ArrayList<?>[1];
        List<String> l = new ArrayList<>();
    }

}
