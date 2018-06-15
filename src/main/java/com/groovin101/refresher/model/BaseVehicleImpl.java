package com.groovin101.refresher.model;

import java.util.Optional;

public class BaseVehicleImpl implements Vehicle {

    private String make;
    private String model;
    private Optional<Integer> year;
    private Optional<String> vin;

    @Override
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public Optional<Integer> getYear() {
        return year;
    }

    public void setYear(Optional<Integer> year) {
        this.year = year;
    }

    @Override
    public Optional<String> getVin() {
        return vin;
    }

    public void setVin(Optional<String> vin) {
        this.vin = vin;
    }

    public BaseVehicleImpl(String make, String model, Integer year) {
        this(make, model, year, null);
    }

    public BaseVehicleImpl(String make, String model, Integer year, String vin) {
        this.make = make;
        this.model = model;
        this.year = Optional.ofNullable(year);
        this.vin = Optional.ofNullable(vin);
    }
}
