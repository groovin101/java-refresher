package com.groovin101.refresher.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface Vehicle {

    default String getMake() {
        return "unknown make";
    }

    default String getModel() {
        return "unknown model";
    }

    default Optional<String> getVin() {
        return Optional.empty();
    }

    default Optional<Integer> getYear() {
        return Optional.empty();
    }

    default void move() {
        System.out.println("Some kind of vehicle is moving!");
    }

    static List<Vehicle> vehicles() {
        return Arrays.asList(new CarLuxuryImpl("Ferarri", "Testarosa", 1983, "78483RM295JVW201K"),
                new AirplaneImpl(),
                new ATeamMonstrosityImpl(),
                new CarPOSImpl("Ford", "Pinto", 1972),
                new Car() {});
    }
}
