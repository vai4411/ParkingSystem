package com.bl.demo.mock;

import com.bl.demo.observer.ParkingLotOwner;

public class OwnerMock {
    private ParkingLotOwner parkingLotOwnerMock;

    public OwnerMock(ParkingLotOwner parkingLotOwnerMock) {
        this.parkingLotOwnerMock = parkingLotOwnerMock;
    }

    public boolean isCapacityFull() {
        return this.parkingLotOwnerMock.isCapacityFull();
    }
}
