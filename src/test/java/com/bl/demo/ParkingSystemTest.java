package com.bl.demo;

import com.bl.demo.enums.VehicleDetails;
import com.bl.demo.exception.ParkingSystemException;
import com.bl.demo.model.Vehicles;
import com.bl.demo.observer.AirportSecurity;
import com.bl.demo.observer.ParkingLotOwner;
import com.bl.demo.observer.PoliceDepartment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingSystemTest {
    Vehicles vehicle = null;
    ParkingSystem parkingSystem = null;

    @Before
    public void setUp() {
        vehicle = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                VehicleDetails.Black.getVehicleDetails());
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
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            boolean isUnParked = parkingSystem.unPark(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                                        VehicleDetails.Black.getVehicleDetails()));
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
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
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
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
        } catch (ParkingSystemException e) {
            Assert.assertEquals("Parking Lot Is Full",e.getMessage());
        }
    }

    @Test
    public void givenCapacityIsTwo_ShouldBeAbleToParkTwoVehicles() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
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
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
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
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
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
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.unPark(vehicle);
        } catch (ParkingSystemException e) { }
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assert.assertFalse(capacityFull);
    }

    @Test
    public void givenParkingLotOwnerWantToKnowAttendantToParkVehicle_ShouldReturnLotNumber() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
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
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
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
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(vehicle2);
            parkingSystem.unPark(vehicle);
            double parkingTime = parkingSystem.getTime(vehicle);
            double vehicleTime = parkingLotOwner.getTime();
            Assert.assertEquals(vehicleTime,parkingTime,0.0);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLotOwnerWantToEvenlyDirectCars_WhenFloorOne_ShouldReturnLotNumber() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
        Vehicles vehicle3 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(vehicle2);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(vehicle3);
            Assert.assertEquals(1,parkingSystem.getSlot(vehicle));
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLotOwnerWantToEvenlyDirectCars_WhenFloorTwo_ShouldReturnLotNumber() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
        Vehicles vehicle3 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(vehicle2);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(vehicle3);
            Assert.assertEquals(5,parkingSystem.getSlot(vehicle2));
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLotOwnerWantToEvenlyDirectCars_WhenFloorThree_ShouldReturnLotNumber() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
        Vehicles vehicle3 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(vehicle2);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(vehicle3);
            Assert.assertEquals(2,parkingSystem.getSlot(vehicle3));
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLot_WhenHandicapDriverComes_ThenReturnNearestSlotNumber() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
        Vehicles vehicle3 = new Vehicles(VehicleDetails.Handicap.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(vehicle2);
            Assert.assertEquals(2,parkingSystem.getSlot(vehicle3),0.0);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLot_WhenNormalDriverComes_ThenReturnNearestSlotNumber() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Handicap.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
        Vehicles vehicle3 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(vehicle2);
            Assert.assertEquals(3,parkingSystem.getSlot(vehicle3),0.0);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLot_WhenLargeVehicleComes_ThenReturnSlotNumber() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(vehicle2);
            Assert.assertEquals(3,parkingSystem.getSlot(vehicle2));
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLot_WhenWhiteCarParked_ShouldReturnTotalWhiteCars() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.White.getVehicleDetails());
        Vehicles vehicle3 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.Blue.getVehicleDetails());
        Vehicles vehicle4 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.White.getVehicleDetails());
        Vehicles vehicle5 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.Black.getVehicleDetails());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(vehicle2);
            parkingSystem.park(vehicle3);
            parkingSystem.park(vehicle4);
            parkingSystem.park(vehicle5);
            int whiteCar = policeDepartment.getNumberOfVehicles(VehicleDetails.White.getVehicleDetails());
            Assert.assertEquals(2,whiteCar);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLot_WhenPoliceTryToCheckUnknownCarParked_ShouldReturnZero() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.White.getVehicleDetails());
        Vehicles vehicle3 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.White.getVehicleDetails());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(vehicle2);
            parkingSystem.park(vehicle3);
            int unknownVehicle = policeDepartment.getNumberOfVehicles(VehicleDetails.Black.getVehicleDetails());
            Assert.assertEquals(0,unknownVehicle);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLot_WhenWhiteCarParked_ShouldReturnSlotNumber() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.White.getVehicleDetails());
        Vehicles vehicle3 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.White.getVehicleDetails());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(vehicle2);
            parkingSystem.park(vehicle3);
            int whiteCar = policeDepartment.getSlot(vehicle2);
            Assert.assertEquals(3,whiteCar);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLot_WhenToyotaCarParked_ShouldReturnTotalToyotaCars() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.White.getVehicleDetails(),VehicleDetails.Toyota.getVehicleDetails(),"MH0423","yash");
        Vehicles vehicle1 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.Blue.getVehicleDetails(),VehicleDetails.Toyota.getVehicleDetails(),"MH0424","raj");
        Vehicles vehicle3 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.White.getVehicleDetails(),VehicleDetails.Toyota.getVehicleDetails(),"MH0425","abc");
        Vehicles vehicle4 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.White.getVehicleDetails(),VehicleDetails.Toyota.getVehicleDetails(),"MH0426","xyz");
        Vehicles vehicle5 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.Blue.getVehicleDetails(),VehicleDetails.Toyota.getVehicleDetails(),"MH0426","def");
        Vehicles vehicle6 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.Blue.getVehicleDetails(),VehicleDetails.Toyota.getVehicleDetails(),"MH0427","yft");
        try {
            parkingSystem.park(vehicle2);
            parkingSystem.park(vehicle1);
            parkingSystem.park(vehicle3);
            parkingSystem.park(vehicle4);
            parkingSystem.park(vehicle5);
            parkingSystem.park(vehicle6);
            int toyotaCar = policeDepartment.getNumberOfVehicles(VehicleDetails.Toyota.getVehicleDetails());
            Assert.assertEquals(3,toyotaCar);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLot_WhenToyotaCarParked_ShouldReturnSlotNumber() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.Blue.getVehicleDetails(),VehicleDetails.Toyota.getVehicleDetails(),"MH0423","yash");
        Vehicles vehicle1 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.White.getVehicleDetails(),VehicleDetails.Toyota.getVehicleDetails(),"MH0424","raj");
        try {
            parkingSystem.park(vehicle2);
            parkingSystem.park(vehicle1);
            int toyotaCar = policeDepartment.getSlot(vehicle2);
            Assert.assertEquals(1,toyotaCar);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLot_WhenToyotaCarParked_ShouldReturnNumberPlate() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.Blue.getVehicleDetails(),VehicleDetails.Toyota.getVehicleDetails(),"MH0423","yash");
        Vehicles vehicle1 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.White.getVehicleDetails(),VehicleDetails.Toyota.getVehicleDetails(),"MH0424","raj");
        try {
            parkingSystem.park(vehicle2);
            parkingSystem.park(vehicle1);
            policeDepartment.CarDetails(VehicleDetails.Toyota.getVehicleDetails());
            int countOfVehicle = policeDepartment.getSlot(vehicle2);
            String toyotaCar = policeDepartment.getNumberPlate(countOfVehicle);
            Assert.assertEquals("MH0423",toyotaCar);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLot_WhenToyotaCarParked_ShouldReturnName() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.Blue.getVehicleDetails(),VehicleDetails.Toyota.getVehicleDetails(),"MH0423","yash");
        Vehicles vehicle1 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.White.getVehicleDetails(),VehicleDetails.Toyota.getVehicleDetails(),"MH0424","raj");
        try {
            parkingSystem.park(vehicle2);
            parkingSystem.park(vehicle1);
            policeDepartment.CarDetails(VehicleDetails.Toyota.getVehicleDetails());
            int countOfVehicle = policeDepartment.getSlot(vehicle2);
            String toyotaCar = policeDepartment.getDriverName(countOfVehicle);
            Assert.assertEquals("yash",toyotaCar);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLot_WhenBMWCarParked_ShouldReturnSlotNumber() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.Blue.getVehicleDetails(),VehicleDetails.BMW.getVehicleDetails(),"MH0423","yash");
        Vehicles vehicle1 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.White.getVehicleDetails(),VehicleDetails.BMW.getVehicleDetails(),"MH0424","raj");
        try {
            parkingSystem.park(vehicle2);
            parkingSystem.park(vehicle1);
            int toyotaCar = policeDepartment.getSlot(vehicle2);
            Assert.assertEquals(1,toyotaCar);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLot_WhenBMWCarParked_ShouldReturnTotalBMWCars() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.White.getVehicleDetails(),VehicleDetails.BMW.getVehicleDetails(),"MH0423","yash");
        Vehicles vehicle1 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.Blue.getVehicleDetails(),VehicleDetails.Toyota.getVehicleDetails(),"MH0424","raj");
        Vehicles vehicle3 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.White.getVehicleDetails(),VehicleDetails.BMW.getVehicleDetails(),"MH0425","abc");
        Vehicles vehicle4 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.White.getVehicleDetails(),VehicleDetails.BMW.getVehicleDetails(),"MH0426","xyz");
        Vehicles vehicle5 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.Blue.getVehicleDetails(),VehicleDetails.Toyota.getVehicleDetails(),"MH0426","def");
        Vehicles vehicle6 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                VehicleDetails.Blue.getVehicleDetails(),VehicleDetails.Toyota.getVehicleDetails(),"MH0427","yft");
        try {
            parkingSystem.park(vehicle2);
            parkingSystem.park(vehicle1);
            parkingSystem.park(vehicle3);
            parkingSystem.park(vehicle4);
            parkingSystem.park(vehicle5);
            parkingSystem.park(vehicle6);
            int toyotaCar = policeDepartment.getNumberOfVehicles(VehicleDetails.BMW.getVehicleDetails());
            Assert.assertEquals(3,toyotaCar);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenVehicle_WhenParkedAndUnParked_ShouldTotalNumber() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                VehicleDetails.Black.getVehicleDetails());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(vehicle2);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                    VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                    VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                    VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                    VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.getTotalParkedTime();
            List parkingTime = policeDepartment.longStandByVehicle();
            Assert.assertEquals(6,parkingTime.size());
        } catch (ParkingSystemException e) { }
    }
}
