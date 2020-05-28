/***********************************************************************************+
 * @purpose : AirPort Security Observer Check For Parking Capacity Full Or Available
 * @author : Vaibhav Patil
 * @date : 26/5/2020
 ***********************************************************************************/
package com.bl.demo.observer;

public class AirportSecurity implements ParkingLotObserver {
    private boolean isFullCapacity;

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
