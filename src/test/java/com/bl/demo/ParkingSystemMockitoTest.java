package com.bl.demo;

import com.bl.demo.enums.VehicleDetails;
import com.bl.demo.exception.ParkingSystemException;
import com.bl.demo.mock.OwnerMock;
import com.bl.demo.model.Vehicles;
import com.bl.demo.observer.ParkingLotOwner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.when;

public class ParkingSystemMockitoTest {
    Vehicles vehicle = null;
    ParkingSystem parkingSystem = null;

    @Before
    public void setUp() {
        vehicle = new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                VehicleDetails.Black.getVehicleDetails());
        parkingSystem = new ParkingSystem(2,3);
    }

    @Mock
    ParkingLotOwner ParkingLotOwnerMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void givenParkingLot_WhenParkingLotIsFull_ShouldInformTheOwner() {
        OwnerMock ownerMock = new OwnerMock(ParkingLotOwnerMock);
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
        when(ParkingLotOwnerMock.isCapacityFull()).thenReturn(true);
        boolean capacityFull = ownerMock.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void givenOwner_WhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnFalse() {
        OwnerMock ownerMock = new OwnerMock(ParkingLotOwnerMock);
        try {
            parkingSystem.park(vehicle);
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                    VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.park(new Vehicles(VehicleDetails.Normal.getVehicleDetails(),VehicleDetails.Small.getVehicleDetails(),
                    VehicleDetails.Black.getVehicleDetails()));
            parkingSystem.unPark(vehicle);
        } catch (ParkingSystemException e) { }
        when(ParkingLotOwnerMock.isCapacityFull()).thenReturn(false);
        boolean capacityFull = ownerMock.isCapacityFull();
        Assert.assertFalse(capacityFull);
    }
}
