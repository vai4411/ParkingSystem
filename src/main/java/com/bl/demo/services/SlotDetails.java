package com.bl.demo.services;

import com.bl.demo.enums.VehicleDetails;
import com.bl.demo.exception.ParkingSystemException;
import com.bl.demo.model.Vehicles;

import static com.bl.demo.ParkingSystem.*;

public class SlotDetails {
    private static int slotNumber;

    public static int swapSlots(int slotNumber) {
        slotNumber += (parkingLotCapacity() / noOfSlots);
        if (slotNumber > parkingLotCapacity())
            slotNumber = 0;
        return slotNumber;
    }

    public static void slotNumber(Vehicles vehicle) throws ParkingSystemException {
        slotNumber = 0;
        for (int slot = 1 ; slot <=parkingLotCapacity() ; slot++ ) {
            if (vehicles.get(slot) == vehicle) {
                slotNumber = slot;
            }
        }
        if (vehicle.getDriver().equals(VehicleDetails.Handicap.getVehicleDetails())) {
            int slot = 1;
            while (vehicles.get(slot) != null) {
                slot++;
                slotNumber = slot;
            }
        }
        if (slotNumber == 0)
            throw new ParkingSystemException("Vehicle Is Not Present");
    }

    public static int getSlot(Vehicles vehicle) throws ParkingSystemException {
        slotNumber(vehicle);
        return slotNumber;
    }
}
