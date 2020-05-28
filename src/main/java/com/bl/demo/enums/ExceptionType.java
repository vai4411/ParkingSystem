/*******************************************************+
 * @purpose : Provide Exception Statement In Enum Format
 * @author : Vaibhav Patil
 * @date : 28/5/2020
 ********************************************************/
package com.bl.demo.enums;

public enum  ExceptionType {
    AlreadyParkedVehicle("Vehicle Already Parked"),
    ParkingFull("Parking Lot Is Full"),
    VehicleNotFound("Vehicle Is Not Present");

    private String exception;

    ExceptionType(String exception) {
        this.exception = exception;
    }

    public String getException() {
        return exception;
    }
}
