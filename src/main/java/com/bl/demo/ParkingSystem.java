package com.bl.demo;

import com.bl.demo.enums.VehicleDetails;
import com.bl.demo.exception.ParkingSystemException;
import com.bl.demo.observer.ParkingLotObserver;
import com.bl.demo.observer.ParkingLotOwner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ParkingSystem {

    public static int noOfSlots;
    public static HashMap vehicles;
    private List<ParkingLotObserver> observers;
    private static int actualCapacity;
    private int slotNumber = 1;
    private HashMap entryTimeOfVehicles;
    private HashMap exitTimeOfVehicles;
    private double totalTime;
    private double entryTime;
    private double exitTime;
    private Date date;

    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    public ParkingSystem(int capacity, int noOfSlots) {
        this.observers = new ArrayList<>();
        this.vehicles = new HashMap();
        this.actualCapacity = capacity;
        this.noOfSlots = noOfSlots;
        this.entryTimeOfVehicles = new HashMap<>();
        this.exitTimeOfVehicles = new HashMap<>();
        this.date = new Date();
    }

    public static int parkingLotCapacity() {
        return actualCapacity * noOfSlots;
    }

    public void park(Vehicles vehicle) throws ParkingSystemException {
        if (isVehiclePark(vehicle))
            throw new ParkingSystemException("Vehicle Already Parked");
        if (this.vehicles.size() == parkingLotCapacity()) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingSystemException("Parking Lot Is Full");
        }
        this.entryTimeOfVehicles.put(vehicle,date.getTime());
        this.vehicles.put(slotNumber,vehicle);
        slotNumber = SlotDetails.swapSlots(slotNumber,vehicle);
    }

    public boolean isVehiclePark(Vehicles vehicle) {
        for (int slot = 1 ; slot <=parkingLotCapacity() ; slot++ ) {
            if (this.vehicles.get(slot) == vehicle)
                return true;
        }
        return false;
    }

    public boolean unPark(Vehicles vehicle) {
        if (vehicle == null) return false;
        for (int slot = 1 ; slot <=parkingLotCapacity() ; slot++ ) {
            if (this.vehicles.get(slot) == vehicle) {
                this.vehicles.remove(slot);
                this.exitTimeOfVehicles.put(vehicle,date.getTime());
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsAvailable();
                }
                return true;
            }
        }
        return false;
    }

    public double getTotalTime(Vehicles vehicle) {
        entryTime = (long) this.entryTimeOfVehicles.get(vehicle);
        exitTime = (long) this.exitTimeOfVehicles.get(vehicle);
        new ParkingLotOwner(exitTime - entryTime);
        return exitTime - entryTime;
    }

//    public void slotNumber(Vehicles vehicle) throws ParkingSystemException {
//        slotNumber = 0;
//        for (int slot = 1 ; slot <=parkingLotCapacity() ; slot++ ) {
//            if (this.vehicles.get(slot) == vehicle) {
//                slotNumber = slot;
//            }
//        }
//        if (vehicle.getDriver().equals(VehicleDetails.Handicap.getDriver())) {
//            int slot = 1;
//            while (this.vehicles.get(slot) != null) {
//                slot++;
//                slotNumber = slot;
//            }
//        }
//        if (slotNumber == 0)
//            throw new ParkingSystemException("Vehicle Is Not Present");
//    }

    public int getSlot(Vehicles vehicle) throws ParkingSystemException {
//        slotNumber(vehicle);
//        SlotDetails.getSlot(vehicle);
        return SlotDetails.getSlot(vehicle);
    }

    public double getTime(Vehicles vehicle) {
        return getTotalTime(vehicle);
    }
}