package com.bl.demo.observer;

public class ParkingLotOwner implements ParkingLotObserver {
    private boolean isFullCapacity;
    private double time;

    public ParkingLotOwner() {
    }

    public ParkingLotOwner(double time) {
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    @Override
    public void capacityIsFull() {
        isFullCapacity = true;
    }

    @Override
    public void capacityIsAvailable() {
        isFullCapacity = false;
    }

    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }
}
