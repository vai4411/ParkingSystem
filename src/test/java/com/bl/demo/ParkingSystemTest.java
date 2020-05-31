package com.bl.demo;

import com.bl.demo.enums.ExceptionType;
import com.bl.demo.enums.VehicleDetails;
import com.bl.demo.exception.ParkingSystemException;
import com.bl.demo.model.Vehicles;
import com.bl.demo.observer.AirportSecurity;
import com.bl.demo.observer.ParkingLotOwner;
import com.bl.demo.services.PoliceDepartment;
import com.bl.demo.services.SlotDetails;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

    //UC-1
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
            Assert.assertEquals(ExceptionType.AlreadyParkedVehicle.getException(),e.getMessage());
        }
    }

    //UC-2
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

    //UC-3
    @Test
    public void givenParkingLot_WhenParkingLotIsFull_ShouldInformTheOwner() {
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
    public void givenParkingLot_WhenParkingLotIsFull_ShouldTrowTheException() {
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
            Assert.assertEquals(ExceptionType.ParkingFull.getException(),e.getMessage());
        }
    }

    @Test
    public void givenParkingLot_WhenCapacityIsTwo_ShouldBeAbleToParkTwoVehicles() {
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

    //UC-4
    @Test
    public void givenParkingLot_WhenParkingLotIsFull_ShouldInformAirportSecurity() {
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

    //UC-5
    @Test
    public void givenOwner_WhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnFalse() {
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

    //Uc-6
    @Test
    public void givenSecurity_WhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnFalse() {
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

    //UC-7
    @Test
    public void givenParkingLot_WhenOwnerWantToKnowAttendantToParkVehicle_ShouldReturnLotNumber() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
        Vehicles vehicle3 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                VehicleDetails.Black.getVehicleDetails());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(vehicle2);
            parkingSystem.park(vehicle3);
            int slotNumber = new SlotDetails().getSlot(vehicle2);
            Assert.assertEquals(3,slotNumber);
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLot_WhenVehicleNotPresent_ShouldThrowException() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(vehicle2);
            parkingSystem.unPark(vehicle);
            int slotNumber = new SlotDetails().getSlot(vehicle);
        } catch (ParkingSystemException e) {
            Assert.assertEquals(ExceptionType.VehicleNotFound.getException(),e.getMessage());
        }
    }

    //UC-8
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
            double parkingTime = parkingSystem.parkingTimeDetails(vehicle);
            double vehicleTime = parkingLotOwner.getTime();
            Assert.assertEquals(vehicleTime,parkingTime,0.0);
        } catch (ParkingSystemException e) { }
    }

    //UC-9
    @Test
    public void givenParkingLot_WhenOwnerWantToEvenlyDirectCars_ShouldReturnFirstLotNumber() {
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
            Assert.assertEquals(1,new SlotDetails().getSlot(vehicle));
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLot_WhenOwnerWantToEvenlyDirectCars_ShouldReturnThirdLotNumber() {
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
            Assert.assertEquals(5,new SlotDetails().getSlot(vehicle2));
        } catch (ParkingSystemException e) { }
    }

    @Test
    public void givenParkingLot_WhenOwnerWantToEvenlyDirectCars_ShouldReturnSecondLotNumber() {
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
            Assert.assertEquals(4,new SlotDetails().getSlot(vehicle3));
        } catch (ParkingSystemException e) { }
    }

    //UC-10
    @Test
    public void givenParkingLot_WhenHandicapDriverComes_ThenReturnNearestSlotNumber() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
        Vehicles vehicle3 = new Vehicles(VehicleDetails.Handicap.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(vehicle2);
            Assert.assertEquals(2,new SlotDetails().getSlot(vehicle3),0.0);
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
            Assert.assertEquals(3,new SlotDetails().getSlot(vehicle3),0.0);
        } catch (ParkingSystemException e) { }
    }

    //UC-11
    @Test
    public void givenParkingLot_WhenLargeVehicleComes_ThenReturnSlotNumber() {
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Large.getVehicleDetails(),
                                            VehicleDetails.Black.getVehicleDetails());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(vehicle2);
            Assert.assertEquals(3,new SlotDetails().getSlot(vehicle2));
        } catch (ParkingSystemException e) { }
    }

    //UC-12
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
            int whiteCar = policeDepartment.numberOfVehicles(VehicleDetails.White.getVehicleDetails());
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
            int unknownVehicle = policeDepartment.numberOfVehicles(VehicleDetails.Black.getVehicleDetails());
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
            int whiteCar = policeDepartment.slotNumber(vehicle2);
            Assert.assertEquals(3,whiteCar);
        } catch (ParkingSystemException e) { }
    }

    //UC-13
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
            int toyotaCar = policeDepartment.numberOfVehicles(VehicleDetails.Toyota.getVehicleDetails());
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
            int toyotaCar = policeDepartment.slotNumber(vehicle2);
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
            int countOfVehicle = policeDepartment.slotNumber(vehicle2);
            String toyotaCar = policeDepartment.numberPlate(countOfVehicle);
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
            int countOfVehicle = policeDepartment.slotNumber(vehicle2);
            String toyotaCar = policeDepartment.driverName(countOfVehicle);
            Assert.assertEquals("yash",toyotaCar);
        } catch (ParkingSystemException e) { }
    }

    //UC-14
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
            int toyotaCar = policeDepartment.slotNumber(vehicle2);
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
            int toyotaCar = policeDepartment.numberOfVehicles(VehicleDetails.BMW.getVehicleDetails());
            Assert.assertEquals(3,toyotaCar);
        } catch (ParkingSystemException e) { }
    }

    //UC-15
    @Test
    public void givenParkingLot_WhenCarParkedInHalfHours_ShouldReturnTotalNumber() {
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
            parkingSystem.totalParkingTime();
            List parkingTime = policeDepartment.longStandByVehicle();
            Assert.assertEquals(6,parkingTime.size());
        } catch (ParkingSystemException e) { }
    }

    //UC-16
    @Test
    public void givenParkingLot_WhenHandicapDriverParkedCarInBAndDSlots_ShouldReturnNumberOfHandicapDriverVehicles() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                VehicleDetails.Black.getVehicleDetails());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(vehicle2);
            parkingSystem.park(new Vehicles(VehicleDetails.Handicap.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                    VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                    VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Handicap.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                    VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Handicap.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                    VehicleDetails.Black.getVehicleDetails()));
            HashMap handicapDrivers = policeDepartment.handicapDriversBAndDSlot();
            Assert.assertEquals(1,handicapDrivers.size());
        } catch (ParkingSystemException e) { }
    }

    //UC-17
    @Test
    public void givenVehicle_WhenPoliceWantAllParkedCarDetails_ShouldReturnCarDetails() {
        PoliceDepartment policeDepartment = new PoliceDepartment();
        Vehicles vehicle2 = new Vehicles(VehicleDetails.Handicap.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                VehicleDetails.Black.getVehicleDetails());
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(vehicle2);
            parkingSystem.park(new Vehicles(VehicleDetails.Handicap.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                    VehicleDetails.Black.getVehicleDetails()));
            HashMap vehicleData = parkingSystem.vehicles;
            HashMap vehicleDetails = policeDepartment.vehicleData();
            Assert.assertEquals(vehicleData.size(),vehicleDetails.size());
        } catch (ParkingSystemException e) { }
    }
}
