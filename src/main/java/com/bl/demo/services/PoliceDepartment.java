/************************************************************************+
 * @purpose : Police Department Check Vehicle Details For Safety Purpose
 * @author : Vaibhav Patil
 * @date : 27/5/2020
 ************************************************************************/
package com.bl.demo.services;

import com.bl.demo.enums.VehicleDetails;
import com.bl.demo.exception.ParkingSystemException;
import com.bl.demo.model.Vehicles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.bl.demo.ParkingSystem.*;

public class PoliceDepartment {
    private final int one = 1;
    private final int two = 2;
    private final int three = 3;
    private final int four = 4;
    Vehicles vehicle = null;
    private int startPosition = 1;
    private int endPosition = parkingCapacity();

    /**+
     * @purpose : Basis Of Various Properties Get Data And Store In Map
     * @param property
     */
    public void CarDetails(String property) {
        for (int slot = startPosition; slot <= endPosition; slot++ ) {
            if (vehicles.get(slot) != null)
                vehicle = vehicles.get(slot);
            if (PoliceDepartment.compareProperty(vehicle,property)) {
                carDetails.put(slot,vehicle);
            }
        }
    }

    /**+
     * @pupose : Take Property From User And Check Boolean Status
     * @param vehicle
     * @param property
     * @return : Boolean Flag
     */
    public static boolean compareProperty(Vehicles vehicle, String property) {
        switch (property) {
            case "Toyota" :
                return vehicle.getModel().equals(property) && vehicle.getVehicleColor().equals(VehicleDetails.Blue.getVehicleDetails());
            case "White" :
                return vehicle.getVehicleColor().equals(property);
            case "BMW" :
                return vehicle.getModel().equals(property);
            case "Handicap" :
                return vehicle.getDriver().equals(property);
        }
        return false;
    }

    /**+
     * @purpose : Check For Data Load
     * @param property
     * @return : Size Of Map
     */
    public int numberOfVehicles(String property) {
        CarDetails(property);
        return carDetails.size();
    }

    /**+
     * @purpose : Basis Of Parked Car It Show Locations
     * @param vehicle
     * @return : Location Of Cars
     * @throws ParkingSystemException
     */
    public int slotNumber(Vehicles vehicle) throws ParkingSystemException {
        return SlotDetails.getSlot(vehicle);
    }

    /**+
     * @purpose : It Gives Number Plate
     * @param slotNumber
     * @return : Number Plate Of Car
     */
    public String numberPlate(int slotNumber) {
        Vehicles vehicle = (Vehicles) carDetails.get(slotNumber);
        return vehicle.getNumberPlate();
    }

    /**+
     * @purpose : It Gives Driver Name
     * @param slotNumber
     * @return : Driver Name Of car
     */
    public String driverName(int slotNumber) {
        Vehicles vehicle = (Vehicles) carDetails.get(slotNumber);
        return vehicle.getDriverName();
    }

    /**+
     * @purpose : Store The Vehicle Data Basis Of Time
     * @param vehicle
     */
    public void vehicleParkingTimeData(Vehicles vehicle) {
        longStandByVehicles.add(vehicle);
    }

    /**+
     * @purpose : Basis Of Time It Gives Records
     * @return : List Of Vehicles
     */
    public List longStandByVehicle() {
        return longStandByVehicles;
    }

    /**+
     * @purpose : Basis Of B and D Slots It Gives Handicap Driver Records
     * @return : List Of Handicap Drivers
     */
    public HashMap handicapDriversBAndDSlot() {
        startPosition = numberOfRows() + one;
        endPosition = numberOfRows() * two;
        CarDetails(VehicleDetails.Handicap.getVehicleDetails());
        startPosition = numberOfRows() * three + one;
        endPosition = numberOfRows() * four;
        CarDetails(VehicleDetails.Handicap.getVehicleDetails());
        return carDetails;
    }

    /**+
     * @purpose : Store The All Parked Vehicle Data
     * @param slotNumber
     * @param vehicle
     */
    public void vehicleDetails(int slotNumber, Vehicles vehicle) {
        vehicleData.put(slotNumber,vehicle);
    }

    /**+
     * @purpose : Basis Of Parked Vehicle Data Give Records
     * @return : It Give Map Of Parked Vehicles
     */
    public HashMap vehicleData() {
        return vehicleData;
    }
}
