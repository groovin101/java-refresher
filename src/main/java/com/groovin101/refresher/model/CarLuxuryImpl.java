package com.groovin101.refresher.model;

public class CarLuxuryImpl extends BaseCarImpl {


    public CarLuxuryImpl(String make, String model, Integer year, String vin) {
        super(make, model, year, vin);
    }

    @Override
    public void move() {
        drive(car -> {
            System.out.println(String.format("Luxury car %s, vin# %s driving. Make way peasants!", car, car.getVin().get()));
        });
    }
}
