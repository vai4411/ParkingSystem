package com.bl.demo;

import com.bl.demo.enums.VehicleDetails;
import com.bl.demo.exception.ParkingSystemException;
import com.bl.demo.observer.AirportSecurity;
import com.bl.demo.observer.ParkingLotOwner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingSystemTest {
    Vehicles vehicle = null;
    ParkingSystem parkingSystem = null;

    @Before
    public void setUp() throws Exception {
        vehicle = new Vehicles(VehicleDetails.Normal.getDriver());
        parkingSystem = new ParkingSystem(2,3);
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
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            boolean isUnParked = parkingSystem.unPark(new Vehicles(VehicleDetails.Normal.getDriver()));
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
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
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
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
        } catch (ParkingSystemException e) {
            Assert.assertEquals("Parking Lot Is Full",e.getMessage());
        }
    }

    @Test
    public void givenCapacityIsTwo_ShouldBeAbleToParkTwoVehicles() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getDriver());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            boolean isParked1 = parkingSystem.isVehiclePark(vehicle);
            boolean isParked2 = parkingSystem.isVehiclePark(vehicle);
            Assert.assertTrue(isParked1 && isParked2);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
        } catch (ParkingSystemException e) { }
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void givenOwnerWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnFalse() {
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        parkingSystem.registerParkingLotObserver(parkingLotOwner);
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.unPark(vehicle);
        } catch (ParkingSystemException e) { }
        boolean capacityFull = parkingLotOwner.isCapacityFull();
        Assert.assertFalse(capacityFull);
    }

    @Test
    public void givenSecurityWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnFalse() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.unPark(vehicle);
        } catch (ParkingSystemException e) { }
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assert.assertFalse(capacityFull);
    }

    @Test
    public void givenParkingLotOwnerWantToKnowAttendantToParkVehicle_ShouldReturnLotNumber() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getDriver());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(vehicle2);
            parkingSystem.unPark(vehicle);
            int slotNumber = parkingSystem.getSlot(vehicle2);
            Assert.assertEquals(3,slotNumber);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLotOwnerWantToKnowAttendantToParkVehicle_WhenVehicleNotPresent_ShouldThrowException() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getDriver());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(vehicle2);
            parkingSystem.unPark(vehicle);
            int slotNumber = parkingSystem.getSlot(vehicle);
        } catch (ParkingSystemException e) {
            Assert.assertEquals("Vehicle Is Not Present",e.getMessage());
        }
    }

    @Test
    public void givenAVehicle_WhenParkedAndUnParked_ShouldReturnTime() {
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getDriver());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(vehicle2);
            parkingSystem.unPark(vehicle);
            double parkingTime = parkingSystem.getTime(vehicle);
            double vehicleTime = parkingLotOwner.getTime();
            Assert.assertEquals(vehicleTime,parkingTime,0.0);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLotOwnerWantToEvenlyDirectCars_WhenFloorOne_ShouldReturnLotNumber() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getDriver());
        Vehicles vehicle3 = new Vehicles(VehicleDetails.Normal.getDriver());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(vehicle2);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(vehicle3);
            Assert.assertEquals(1,parkingSystem.getSlot(vehicle));
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLotOwnerWantToEvenlyDirectCars_WhenFloorTwo_ShouldReturnLotNumber() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getDriver());
        Vehicles vehicle3 = new Vehicles(VehicleDetails.Normal.getDriver());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(vehicle2);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(vehicle3);
            Assert.assertEquals(5,parkingSystem.getSlot(vehicle2));
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLotOwnerWantToEvenlyDirectCars_WhenFloorThree_ShouldReturnLotNumber() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getDriver());
        Vehicles vehicle3 = new Vehicles(VehicleDetails.Normal.getDriver());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(vehicle2);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getDriver()));
            parkingSystem.park(vehicle3);
            Assert.assertEquals(2,parkingSystem.getSlot(vehicle3));
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLot_WhenHandicapDriverComes_ThenReturnNearestSlotNumber() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getDriver());
        Vehicles vehicle3 = new Vehicles(VehicleDetails.Handicap.getDriver());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(vehicle2);
            Assert.assertEquals(2,parkingSystem.getSlot(vehicle3),0.0);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLot_WhenNormalDriverComes_ThenReturnNearestSlotNumber() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Handicap.getDriver());
        Vehicles vehicle3 = new Vehicles(VehicleDetails.Normal.getDriver());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(vehicle2);
            Assert.assertEquals(3,parkingSystem.getSlot(vehicle3),0.0);
        } catch (ParkingSystemException e) { }
    }
}
