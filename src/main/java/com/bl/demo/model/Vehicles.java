package com.bl.demo.model;

public class Vehicles {
    private String driver;
    private String vehicleType;
    private String vehicleColor;

    public Vehicles(String driver, String vehicleType, String vehicleColor) {
        this.driver = driver;
        this.vehicleType = vehicleType;
        this.vehicleColor = vehicleColor;
    }

    public String getDriver() {
        return driver;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }
}
