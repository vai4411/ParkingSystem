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
        parkingSystem = new ParkingSystem(2);
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        try {
            parkingSystem.park(vehicle);
            boolean isParked = parkingSystem.isVehiclePark(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldThrowException() {
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(vehicle);
        } catch (ParkingSystemException e) {
            Assert.assertEquals("Vehicle Already Parked",e.getMessage());
        }
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            parkingSystem.park(vehicle);
            boolean isUnParked = parkingSystem.unPark(vehicle);
            Assert.assertTrue(isUnParked);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenAVehicle_WhenOtherVehicleUnParked_ShouldReturnFalse() {
        try {
            parkingSystem.park(vehicle);
            boolean isUnParked = parkingSystem.unPark(new Object());
            Assert.assertFalse(isUnParked);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenAVehicle_WhenNullVehicleUnParked_ShouldReturnFalse() {
        try {
            parkingSystem.park(vehicle);
            boolean isUnParked = parkingSystem.unPark(null);
            Assert.assertFalse(isUnParked);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformTheOwner() {
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        parkingSystem.registerParkingLotObserver(parkingLotOwner);
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(new Object());
            parkingSystem.park(new Object());
        } catch (ParkingSystemException e) { }
        boolean capacityFull = parkingLotOwner.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldTrowTheException() {
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        parkingSystem.registerParkingLotObserver(parkingLotOwner);
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(new Object());
            parkingSystem.park(new Object());
        } catch (ParkingSystemException e) {
            Assert.assertEquals("Parking Lot Is Full",e.getMessage());
        }
    }

    @Test
    public void givenCapacityIsTwo_ShouldBeAbleToParkTwoVehicles() {
        Object vehicle2 = new Object();
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(vehicle2);
            boolean isParked1 = parkingSystem.isVehiclePark(vehicle);
            boolean isParked2 = parkingSystem.isVehiclePark(vehicle2);
            Assert.assertTrue(isParked1 && isParked2);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(new Object());
            parkingSystem.park(new Object());
        } catch (ParkingSystemException e) { }
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnFalse() {
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        parkingSystem.registerParkingLotObserver(parkingLotOwner);
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(new Object());
            parkingSystem.park(new Object());
        } catch (ParkingSystemException e) { }
        parkingSystem.unPark(vehicle);
        boolean capacityFull = parkingLotOwner.isCapacityFull();
        Assert.assertFalse(capacityFull);
    }
}
