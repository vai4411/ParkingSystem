package com.bl.demo.model;

public class Vehicles {
    private String driver;
    private String vehicleType;

    public Vehicles(String driver, String vehicleType) {
        this.driver = driver;
        this.vehicleType = vehicleType;
    }

    public String getDriver() {
        return driver;
    }

    public String getVehicleType() {
        return vehicleType;
    }
}
