import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {

    void toggleEngineOn() {
        for (MotorVehicle car : cars) {
            car.toggleEngineOn();
        }
    }
    void toggleEngineOff() {
        for (MotorVehicle car : cars) {
            car.toggleEngineOff();
        }
    }
    void brake(int amount) {
        double brakeAmount = ((double) amount) / 100;
        for (MotorVehicle car : cars) {
            car.brake(brakeAmount);
        }
    }
    // Calls the gas method for each car once
    void gas(int amount) throws Exception {
        double gasAmount = ((double) amount) / 100;
        for (MotorVehicle car : cars) {
            car.gas(gasAmount);
        }
    }
    void setTurboOn() {
        for (MotorVehicle car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }
    void setTurboOff() {
        for (MotorVehicle car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }
    void liftBed() throws Exception {
        for (MotorVehicle car : cars) {
            if (car instanceof Scania && !((Scania) car).isPlatformRaised()) {
                ((Scania) car).togglePlatform();
            }
        }
    }
    void lowerBed() throws Exception {
        for (MotorVehicle car : cars) {
            if (car instanceof Scania && ((Scania) car).isPlatformRaised()) {
                ((Scania) car).togglePlatform();
            }
        }
    }
}
