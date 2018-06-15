package com.groovin101.refresher.model;

public class ATeamMonstrosityImpl implements Vehicle {

    @Override
    public void move() {
        System.out.println(new StringBuilder("Vrrooom vroom vrooooom!!").append(
                " ... Slow down fool, you trying to kill us!?").append(
                " ... <Flamethrower Roars>"));
    }
}
