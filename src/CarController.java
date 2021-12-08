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
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<MotorVehicle> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Scania());
        cc.cars.add(new Saab95());


        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (MotorVehicle car : cars) {

                if (car.getX() < 0) {
                    car.setDirection(car.dir += 180);
                } else if (car.getY() < 0) {
                    car.setDirection(car.dir += 180);
                } else if (car.getX() > frame.getWidth() - 100) { // Image width
                    car.setDirection(car.dir += 180);
                } else if (car.getY() > frame.getHeight() - 300) { // Image height + control bar height
                    car.setDirection(car.dir += 180);
                }

                try {
                    car.move();
                    int x = (int) Math.round(car.getX());
                    int y = (int) Math.round(car.getY());
                } catch (Exception ex) {
                    System.out.println("Move error on " + car);
                }
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());



                frame.drawPanel.moveit(car, x, y);


                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }
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
