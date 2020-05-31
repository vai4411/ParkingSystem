/***************************************************************************************+
 * @purpose : Slot Details Maintain Data about Parked Vehicle Slots And Available Slots
 * @author : Vaibhav Patil
 * @date : 27/5/2020
 ****************************************************************************************/
package com.bl.demo.services;

import com.bl.demo.enums.VehicleDetails;
import com.bl.demo.exception.ParkingSystemException;
import com.bl.demo.model.Vehicles;

import static com.bl.demo.ParkingSystem.*;

public class SlotDetails {
    private static int slotNumber;

    /**+
     * @purpose : Evenly Gives Slot Numbers
     * @param slotNumber
     * @return : New Slot Number
     */
    public static int swapSlots(int slotNumber) {
        slotNumber += (parkingCapacity() / noOfSlots);
        if (slotNumber > parkingCapacity()) {
            slotNumber = (slotNumber % parkingCapacity()) + 1;
        }
        return slotNumber;
    }

    /**+
     * @pupose :  Its Locate Nearest Available Position For Handicap Driver
     * @param vehicle
     * @throws ParkingSystemException
     */
    public static void findSlotNumber(Vehicles vehicle) throws ParkingSystemException {
        slotNumber = 0;
        for (int slot = 1; slot <= parkingCapacity() ; slot++ ) {
            if (vehicles.get(slot) == vehicle) {
                slotNumber = slot;
            }
        }
        if (vehicle.getDriver().equals(VehicleDetails.Handicap.getVehicleDetails())) {
            int slot = 1;
            while (vehicles.get(slot) != null) {
                slot++;
                slotNumber = slot;
            }
        }
        if (slotNumber == 0)
            throw new ParkingSystemException("Vehicle Is Not Present");
    }

    /**+
     * @purpose : Basis Of parked Vehicle It Gives Slot Number
     * @param vehicle
     * @return : Slot Number Of Vehicle
     * @throws ParkingSystemException
     */
    public static int getSlot(Vehicles vehicle) throws ParkingSystemException {
        findSlotNumber(vehicle);
        return slotNumber;
    }
}
