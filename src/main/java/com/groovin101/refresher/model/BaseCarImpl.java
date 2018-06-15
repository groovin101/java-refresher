package com.groovin101.refresher.model;

public class BaseCarImpl extends BaseVehicleImpl implements Car {


    public BaseCarImpl(String make, String model, Integer year, String vin) {
        super(make, model, year, vin);
    }

    @Override
    public String toString() {
        return String.format("[Car: %s %s %s]", getYear().get(), getMake(), getModel());
    }
}
