package com.bl.demo.enums;

public enum VehicleDetails {
    Normal("Normal"),
    Handicap("Handicap"),
    Small("Small"),
    Large("Large"),
    White("White"),
    Black("Black"),
    Blue("Blue"),
    Toyota("Toyota");

    private String driver;

    VehicleDetails(String vehicleDetails) {
        this.driver = vehicleDetails;
    }

    public String getVehicleDetails() {
        return driver;
    }
}
