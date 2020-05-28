package com.bl.demo.observer;

import com.bl.demo.enums.VehicleDetails;
import com.bl.demo.exception.ParkingSystemException;
import com.bl.demo.model.SlotDetails;
import com.bl.demo.model.Vehicles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.bl.demo.ParkingSystem.*;

public class PoliceDepartment {
//    public HashMap<Integer,Vehicles> longStandByVehicles;

    public void CarDetails(String property) {
        Vehicles vehicle = null;
        for (int slot = 1 ; slot <=parkingLotCapacity() ; slot++ ) {
            if (vehicles.get(slot) != null)
                vehicle = vehicles.get(slot);
            if (PoliceDepartment.compareProperty(vehicle,property)) {
                carDetails.put(slot,vehicle);
            }
        }
    }

    public static boolean compareProperty(Vehicles vehicle, String property) {
        switch (property) {
            case "Toyota" :
                return vehicle.getModel().equals(property) && vehicle.getVehicleColor().equals(VehicleDetails.Blue.getVehicleDetails());
            case "White" :
                return vehicle.getVehicleColor().equals(property);
            case "BMW" :
                return vehicle.getModel().equals(property);
        }
        return false;
    }

    public int getNumberOfVehicles(String property) {
        CarDetails(property);
        return carDetails.size();
    }

    public int getSlot(Vehicles vehicle) throws ParkingSystemException {
        return SlotDetails.getSlot(vehicle);
    }

    public String getNumberPlate(int slotNumber) {
        Vehicles vehicle = (Vehicles) carDetails.get(slotNumber);
        return vehicle.getNumberPlate();
    }

    public String getDriverName(int slotNumber) {
        Vehicles vehicle = (Vehicles) carDetails.get(slotNumber);
        return vehicle.getDriverName();
    }

    public void vehicleParkingTimeData(Vehicles vehicle) {
        longStandByVehicles.add(vehicle);
    }

    public List longStandByVehicle() {
        return longStandByVehicles;
    }
}
