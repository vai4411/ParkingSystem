/**********************************************************************
 * @purpose : Parking System Store Data About Vehicles And Parking Lot
 * @author : Vaibhav Patil
 * @date : 26/5/2020
 **********************************************************************/
package com.bl.demo;

import com.bl.demo.exception.ParkingSystemException;
import com.bl.demo.model.Vehicles;
import com.bl.demo.observer.ParkingLotObserver;
import com.bl.demo.observer.ParkingLotOwner;
import com.bl.demo.services.PoliceDepartment;
import com.bl.demo.services.SlotDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class ParkingSystem {

    public static int noOfSlots;
    public static HashMap<Integer, Vehicles> vehicles;
    private List<ParkingLotObserver> observers;
    private static int actualCapacity;
    private int slotNumber = 1;
    private HashMap entryTimeOfVehicles;
    private HashMap exitTimeOfVehicles;
    private double entryTime;
    private double exitTime;
    private Date date;
    public static HashMap carDetails;
    public static ArrayList longStandByVehicles;
    long time = 0;
    public Vehicles vehicle = null;
    public static HashMap vehicleData;

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
        this.carDetails = new HashMap<>();
        this.longStandByVehicles = new ArrayList();
        this.vehicleData = new HashMap();
    }

    /**+
     * @purpose : Basis Of Slots Calculate Total Capacity
     * @return : Total Capacity Of Parking Lot
     */
    public static int parkingCapacity() {
        return actualCapacity * noOfSlots;
    }

    /**+
     * @purpose : Divide Slots Rows
     * @return : Total Number Of Rows
     */
    public static int numberOfRows() {
        return parkingCapacity() / ( noOfSlots * 2 );
    }

    /**+
     * @purpose : Store Vehicles Record
     * @param vehicle
     * @throws ParkingSystemException
     */
    public void park(Vehicles vehicle) throws ParkingSystemException {
        if (isVehiclePark(vehicle))
            throw new ParkingSystemException("Vehicle Already Parked");
        if (this.vehicles.size() == parkingCapacity()) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingSystemException("Parking Lot Is Full");
        }
        this.entryTimeOfVehicles.put(vehicle, date.getTime());
        this.vehicles.put(slotNumber, vehicle);
        new PoliceDepartment().vehicleDetails(slotNumber,vehicle);
        slotNumber = SlotDetails.swapSlots(slotNumber);
    }

    /**+
     * @purpose : Check Vehicle Parked Or Not
     * @param vehicle
     * @return : Check Parking Status
     */
    public boolean isVehiclePark(Vehicles vehicle) {
        if (this.vehicles.containsValue(vehicle))
                return true;
        return false;
    }

    /**+
     * @purpose : Remove Vehicle Record
     * @param vehicle
     * @return : Vehicle Removed Or Not
     */
    public boolean unPark(Vehicles vehicle) {
        if (vehicle == null) return false;
        if (this.vehicles.containsValue(vehicle)) {
            this.vehicles.remove(vehicle);
            this.exitTimeOfVehicles.put(vehicle, date.getTime());
            observers.stream()
                    .forEach(e -> e.capacityIsAvailable());
            return true;
        }
        return false;
    }

    /**+
     * @purpose : Pass Total Time To Parking Owner
     * @param vehicle
     * @return : Total Parking Time
     */
    public double parkingTimeDetails(Vehicles vehicle) {
        entryTime = (long) this.entryTimeOfVehicles.get(vehicle);
        exitTime = (long) this.exitTimeOfVehicles.get(vehicle);
        new ParkingLotOwner(exitTime - entryTime);
        return exitTime - entryTime;
    }

    /**+
     * @purpose : Pass Vehicle Information To Police Department
     * @param slot
     */
    public void parkingTimeCalculation(int slot) {
        vehicle = this.vehicles.get(slot);
        time = (long) this.entryTimeOfVehicles.get( vehicle);
        if ((date.getTime() - time) < 3000000F)
            new PoliceDepartment().vehicleParkingTimeData(vehicle);
    }
    /**+
     * @purpose : Add Records Of Car Parked In Half Hours
     */
    public void totalParkingTime() {
        IntStream.rangeClosed(1,parkingCapacity())
                .filter(e -> vehicles.get(e) != null)
                .forEach(e -> parkingTimeCalculation(e));
        }
}