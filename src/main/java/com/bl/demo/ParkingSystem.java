package com.bl.demo;

import com.bl.demo.exception.ParkingSystemException;
import com.bl.demo.observer.ParkingLotObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingSystem {

    private int floor;
    private HashMap vehicles;
    private List<ParkingLotObserver> observers;
    private int actualCapacity;
    private int slotNumber = 1;

    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    public ParkingSystem(int capacity, int noOfFloor) {
        this.observers = new ArrayList<>();
        this.vehicles = new HashMap();
        this.actualCapacity = capacity;
        this.floor = noOfFloor;
    }

    public int parkingLotCapacity() {
        return actualCapacity * floor;
    }

    public void park(Object vehicle) throws ParkingSystemException {
        if (isVehiclePark(vehicle))
            throw new ParkingSystemException("Vehicle Already Parked");
        if (this.vehicles.size() == parkingLotCapacity()) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingSystemException("Parking Lot Is Full");
        }
        this.vehicles.put(slotNumber,vehicle);
        slotNumber++;
    }

    public boolean isVehiclePark(Object vehicle) {
        for (int slot = 1 ; slot <=parkingLotCapacity() ; slot++ ) {
            if (this.vehicles.get(slot) == vehicle)
                return true;
        }
        return false;
    }

    public boolean unPark(Object vehicle) {
        if (vehicle == null) return false;
        for (int slot = 1 ; slot <=parkingLotCapacity() ; slot++ ) {
            if (this.vehicles.get(slot) == vehicle) {
                this.vehicles.remove(slot);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsAvailable();
                }
                return true;
            }
        }
        return false;
    }

    public void slotNumber(Object vehicle) throws ParkingSystemException {
        slotNumber = 0;
        for (int slot = 1 ; slot <=parkingLotCapacity() ; slot++ ) {
            if (this.vehicles.get(slot) == vehicle) {
                slotNumber = slot;
            }
        }
        if (slotNumber == 0)
            throw new ParkingSystemException("Vehicle Is Not Present");
    }

    public int getSlot(Object vehicle) throws ParkingSystemException {
        slotNumber(vehicle);
        return slotNumber;
    }
}