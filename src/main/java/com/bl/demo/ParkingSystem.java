package com.bl.demo;

import java.util.ArrayList;
import java.util.List;

public class ParkingSystem {

    private List vehicles;
    private ParkingLotOwner parkingLotOwner;
    private int actualCapacity;

    public ParkingSystem(int capacity) {
        this.vehicles = new ArrayList();
        this.actualCapacity = capacity;
    }

    public void park(Object vehicle) throws ParkingSystemException {
        if (this.vehicles.size() == this.actualCapacity) {
            parkingLotOwner.capacityIsFull();
            throw new ParkingSystemException("Parking Lot Is Full");
        }
        if (isVehiclePark(vehicle))
            throw new ParkingSystemException("Vehicle Already Parked");
        this.vehicles.add(vehicle);
    }

    public boolean isVehiclePark(Object vehicle) {
        if (this.vehicles.contains(vehicle))
            return true;
        return false;
    }

    public boolean unPark(Object vehicle) {
        if (vehicle == null) return false;
        if (this.vehicles.contains(vehicle)) {
            this.vehicles.remove(vehicle);
            return true;
        }
        return false;
    }

    public void registerOwner(ParkingLotOwner parkingLotOwner) {
        this.parkingLotOwner = parkingLotOwner;
    }
}
