/*******************************************************+
 * @purpose : Provide Vehicle Properties In Enum Format
 * @author : Vaibhav Patil
 * @date : 27/5/2020
 ********************************************************/
package com.bl.demo.enums;

public enum VehicleDetails {
    Normal("Normal"),
    Handicap("Handicap"),
    Small("Small"),
    Large("Large"),
    White("White"),
    Black("Black"),
    Blue("Blue"),
    Toyota("Toyota"),
    BMW("BMW");

    private String driver;

    VehicleDetails(String vehicleDetails) {
        this.driver = vehicleDetails;
    }

    public String getVehicleDetails() {
        return driver;
    }
}
