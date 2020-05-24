package com.bl.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingSystemTest {
    Object vehicle = null;
    ParkingSystem parkingSystem = null;

    @Before
    public void setUp() throws Exception {
        vehicle = new Object();
        parkingSystem = new ParkingSystem();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        boolean isParked = parkingSystem.park(new Object());
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        parkingSystem.park(vehicle);
        boolean isParked = parkingSystem.park(new Object());
        Assert.assertFalse(isParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        parkingSystem.park(vehicle);
        boolean isUnParked = parkingSystem.unPark(vehicle);
        Assert.assertTrue(isUnParked);
    }

    @Test
    public void givenAVehicle_WhenOtherVehicleUnParked_ShouldReturnFalse() {
        parkingSystem.park(vehicle);
        boolean isUnParked = parkingSystem.unPark(new Object());
        Assert.assertFalse(isUnParked);
    }

    @Test
    public void givenAVehicle_WhenNullVehicleUnParked_ShouldReturnFalse() {
        parkingSystem.park(vehicle);
        boolean isUnParked = parkingSystem.unPark(null);
        Assert.assertFalse(isUnParked);
    }
}
