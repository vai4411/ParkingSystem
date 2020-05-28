/*******************************************************+
 * @purpose : Provide Custom Exception Messages
 * @author : Vaibhav Patil
 * @date : 28/5/2020
 ********************************************************/
package com.bl.demo.exception;

public class ParkingSystemException extends Exception {
    public ParkingSystemException(String message) {
        super(message);
    }
}
