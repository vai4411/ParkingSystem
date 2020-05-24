package com.bl.demo;

import org.junit.Assert;
import org.junit.Test;

public class ParkingSystemTest {

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        ParkingSystem parkingSystem = new ParkingSystem();
        boolean isParked = parkingSystem.park(new Object());
        Assert.assertTrue(isParked);
    }
}
