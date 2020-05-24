package com.bl.demo;

import java.util.ArrayList;
import java.util.List;

public class ParkingSystem {

    private List vehicles;
    private List<ParkingLotObserver> observers;
    private int actualCapacity;
    private AirportSecurity airportSecurity;

    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    public ParkingSystem(int capacity) {
        this.observers = new ArrayList<>();
        this.vehicles = new ArrayList();
        this.actualCapacity = capacity;
    }

    public void park(Object vehicle) throws ParkingSystemException {
        if (isVehiclePark(vehicle))
            throw new ParkingSystemException("Vehicle Already Parked");
        if (this.vehicles.size() == this.actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingSystemException("Parking Lot Is Full");
        }
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
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsAvailable();
            }
            return true;
        }
        return false;
    }
}
