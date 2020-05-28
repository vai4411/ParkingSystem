package com.bl.demo.model;

public class Vehicles {
    private String driver;
    private String vehicleType;
    private String vehicleColor;
    private String model;
    private String numberPlate;
    private String driverName;

    public Vehicles(String driver, String vehicleType, String vehicleColor) {
        this.driver = driver;
        this.vehicleType = vehicleType;
        this.vehicleColor = vehicleColor;
    }

    public Vehicles(String driver, String vehicleType, String vehicleColor, String model, String numberPlate, String driverName) {
        this.driver = driver;
        this.vehicleType = vehicleType;
        this.vehicleColor = vehicleColor;
        this.model = model;
        this.numberPlate = numberPlate;
        this.driverName = driverName;
    }

    public String getDriver() {
        return driver;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public String getModel() {
        return model;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public String getDriverName() {
        return driverName;
    }
}
