package com.groovin101.refresher.model;

public class CarPOSImpl extends BaseCarImpl {

    public CarPOSImpl(String make, String model, Integer year) {
        super(make, model, year, null);
    }

    @Override
    public void move() {
        drive(car -> {
            System.out.println(String.format("Piece of shit %s coming through and we don't care what paint job we destroy!!", car.getMake()));
        });
    }
}
