package com.bl.demo.enums;

public enum VehicleDetails {
    Normal("Normal"),
    Handicap("Handicap");

    private String driver;

    VehicleDetails(String driver) {
        this.driver = driver;
    }

    public String getDriver() {
        return driver;
    }
}
