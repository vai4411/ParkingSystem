/*******************************************************+
 * @purpose : Provide Interface For Observer Classes
 * @author : Vaibhav Patil
 * @date : 26/5/2020
 ********************************************************/
package com.bl.demo.observer;

public interface ParkingLotObserver {
    public void capacityIsFull();
    public void capacityIsAvailable();
}
