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
