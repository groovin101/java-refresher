package com.groovin101.refresher.general;

import com.groovin101.refresher.model.Car;
import org.junit.Test;

public class InterfaceTest {

    @Test
    public void instantiateInterfaceWithNoImplementation() {
        new Car() {}.drive(car -> System.out.println("Out of gas, we're not going anywhere!"));
    }
}
