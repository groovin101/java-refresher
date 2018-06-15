package com.groovin101.refresher.model;

import java.util.function.Consumer;

public interface Car extends Vehicle {

    // Override the default method inherited from our super interface Vehicle
    @Override
    default void move() {
        drive(s -> System.out.println("Generic car is driving"));
    }

    default void drive(Consumer<? super Car> action) {
        action.accept(this);
    }
}
