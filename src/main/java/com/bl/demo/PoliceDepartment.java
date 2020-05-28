package com.bl.demo;

import com.bl.demo.enums.VehicleDetails;
import com.bl.demo.exception.ParkingSystemException;
import com.bl.demo.model.Vehicles;

import static com.bl.demo.ParkingSystem.*;

public class PoliceDepartment {

    public void whiteCarLocation() {
        Vehicles vehicle = null;
        for (int slot = 1 ; slot <=parkingLotCapacity() ; slot++ ) {
            if (vehicles.get(slot) != null)
                vehicle = vehicles.get(slot);
            if (vehicle.getVehicleColor().equals(VehicleDetails.White.getVehicleDetails())) {
                whiteCarData.put(vehicle,slot);
            }
        }
    }

    public int getWhiteCarNumbers() throws ParkingSystemException {
        whiteCarLocation();
        return whiteCarData.size();
    }

    public int carLocation(Vehicles vehicle) throws ParkingSystemException {
        whiteCarLocation();
        return (int) whiteCarData.get(vehicle);
    }
}
