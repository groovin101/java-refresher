package com.groovin101.refresher.model;

public class AirplaneImpl implements Vehicle {

    @Override
    public void move() {
        fly();
    }

    private void fly() {
        System.out.println("Airplane is flying!");
    }
}
